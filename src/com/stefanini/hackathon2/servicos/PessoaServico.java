package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Pessoa;
import com.stefanini.hackathon2.repositorios.PessoaRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class PessoaServico {

	@Inject
	private PessoaRepositorio repositorio;
	
	@Transacional
	public void salvar(Pessoa pessoa) {
		if (pessoa.getIdPessoa() == null) {
			repositorio.inserir(pessoa);
		} else {
			repositorio.atualizar(pessoa);
		}
	}

	@Transacional
	public List<Pessoa> carregaTodasPessoasDoBanco() {
		return repositorio.todasPessoas();
	}

	@Transacional
	public void deletar(Pessoa pessoa) {
		repositorio.remover(pessoa);;
	}
	
}
