package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.servicos.LoginServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class LoginManagedBean {

	private Login login;
	private List<Login> listaDeLoginsCadastrados;
	
	@Inject
	private LoginServico servico;
	
	public LoginManagedBean() {
	}
	
	public void salvar() {
		servico.salvar(getLogin());
		Mensageiro.notificaInformacao("Parabéns!", "Login salvo com sucesso!");
		carregaListaDeLogins();
		limpar();
	}
	
	public void deletar(Login login) {
		servico.deletar(login);
		Mensageiro.notificaInformacao("Parabéns!", "Login deletado com sucesso!");
		carregaListaDeLogins();
		limpar();
	}
	
	public void limpar() {
		setLogin(new Login());
	}
	
	private void carregaListaDeLogins() {
		setListaDeLoginsCadastrados(servico.carregaTodosLoginsDoBanco());
	}
	
	public List<Login> getListaDeLoginsCadastrados() {
		if (listaDeLoginsCadastrados == null) {
			carregaListaDeLogins();
		}
		return listaDeLoginsCadastrados;
	}
	
	public void setListaDeLoginsCadastrados(List<Login> listaDeLoginsCadastrados) {
		this.listaDeLoginsCadastrados = listaDeLoginsCadastrados;
	}
	
	public Login getLogin() {
		if (login == null) {
			limpar();
		}
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
}
