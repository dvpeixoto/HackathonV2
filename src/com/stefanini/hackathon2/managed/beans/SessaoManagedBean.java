package com.stefanini.hackathon2.managed.beans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.servicos.LoginServico;

@ManagedBean
@SessionScoped
public class SessaoManagedBean {

	private String usuario;
	private String senha;

	@Inject
	private Login login;
	@Inject
	private LoginServico servico;

	public SessaoManagedBean() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login getLogin() {
		return login;
	}

	public LoginServico getServico() {
		return servico;
	}

	public String entrar() {
		for (Login loginDoBanco : servico.carregaTodosLoginsDoBanco()) {
			if (loginDoBanco.getUsuario().equals(usuario) && loginDoBanco.getSenha().equals(senha)) {
				this.login = loginDoBanco;
				if (login.getAdmin()) {
					return "funcionario.xhtml?faces-redirect=true";
				}
				if (login.getEmprestimo()) {
					return "emprestimo.xhtml?faces-redirect=true";
				} else {
					return "livro.xhtml?faces-redirect=true";
				}
			}
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário e/ou senha inválidos.", null));
		return "principal.xhtml?faces-redirect=true";
	}

}
