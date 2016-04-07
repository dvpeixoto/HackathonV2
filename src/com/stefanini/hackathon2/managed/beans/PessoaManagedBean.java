package com.stefanini.hackathon2.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Pessoa;
import com.stefanini.hackathon2.servicos.PessoaServico;
import com.stefanini.hackathon2.util.Mensageiro;

@ManagedBean
@ViewScoped
public class PessoaManagedBean {

	private Pessoa pessoa;
	private List<Pessoa> listaDePessoasCadastradas;
	
	@Inject
	private PessoaServico servico;
	
	public PessoaManagedBean() {
	}
	
	public void salvar() {
		servico.salvar(getPessoa());
		Mensageiro.notificaInformacao("Parabéns!", "Cadastro salvo com sucesso!");
		carregaListaDePessoas();
		limpar();
	}
	
	public void deletar(Pessoa pessoa) {
		servico.deletar(pessoa);
		Mensageiro.notificaInformacao("Parabéns!", "Cadastro deletado com sucesso!");
		carregaListaDePessoas();
		limpar();
	}
	
	public void limpar() {
		setPessoa(new Pessoa());
	}
	
	private void carregaListaDePessoas() {
		setListaDePessoasCadastradas(servico.carregaTodasPessoasDoBanco());
	}
	
	public List<Pessoa> getListaDePessoasCadastradas() {
		if (listaDePessoasCadastradas == null) {
			carregaListaDePessoas();
		}
		return listaDePessoasCadastradas;
	}
	
	public void setListaDePessoasCadastradas(List<Pessoa> listaDePessoasCadastradas) {
		this.listaDePessoasCadastradas = listaDePessoasCadastradas;
	}
	
	public Pessoa getPessoa() {
		if (pessoa == null) {
			limpar();
		}
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
