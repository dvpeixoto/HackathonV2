package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Livro;
import com.stefanini.hackathon2.repositorios.LivroRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class LivroServico {

	@Inject
	private LivroRepositorio repositorio;
	
	@Transacional
	public void salvar(Livro livro) {
		if (livro.getId() == null) {
			repositorio.inserir(livro);
		} else {
			repositorio.atualizar(livro);
		}
	}

	@Transacional
	public List<Livro> carregaTodosLivrosDoBanco() {
		return repositorio.todosLivros();
	}

	@Transacional
	public void deletar(Livro livro) {
		repositorio.remover(livro);;
	}
	
}
