package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.servicos.EmprestimoServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class EmprestimoManagedBean {

	private Emprestimo emprestimo;
	private List<Emprestimo> listaDeEmprestimos;
	
	@Inject
	private EmprestimoServico servico;
	
	public EmprestimoManagedBean() {
	}
	
	public void salvar() {
		servico.salvar(getEmprestimo());
		Mensageiro.notificaInformacao("Parabéns!", "Emprestimo efetuado com sucesso!");
		carregaListaDeEmprestimos();
		limpar();
	}
	
	public void devolver(Emprestimo emprestimo) {
		servico.devolver(emprestimo);
		Mensageiro.notificaInformacao("Parabéns!", "Livro devolvido com sucesso!");
		carregaListaDeEmprestimos();
		limpar();
	}

	public void limpar() {
		setEmprestimo(new Emprestimo());
	}
	
	private void carregaListaDeEmprestimos() {
		setListaDeEmprestimos(servico.carregaTodosEmprestimosDoBanco());
	}
	
	public List<Emprestimo> getListaDeEmprestimos() {
		if (listaDeEmprestimos == null) {
			carregaListaDeEmprestimos();
		}
		return listaDeEmprestimos;
	}
	
	public void setListaDeEmprestimos(List<Emprestimo> listaDeEmprestimos) {
		this.listaDeEmprestimos = listaDeEmprestimos;
	}
	
	public Emprestimo getEmprestimo() {
		if (emprestimo == null) {
			limpar();
		}
		return emprestimo;
	}
	
	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	
}
