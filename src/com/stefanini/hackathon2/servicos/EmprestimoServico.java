package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.repositorios.EmprestimoRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class EmprestimoServico {

	@Inject
	private EmprestimoRepositorio repositorio;
	
	@Transacional
	public void salvar(Emprestimo emprestimo) {
		if (emprestimo.getId() == null) {
			repositorio.inserir(emprestimo);
		} else {
			repositorio.atualizar(emprestimo);
		}
	}

	@Transacional
	public List<Emprestimo> carregaTodosEmprestimosDoBanco() {
		return repositorio.todosEmprestimos();
	}

	@Transacional
	public void deletar(Emprestimo emprestimo) {
		repositorio.remover(emprestimo);;
	}
	
}
