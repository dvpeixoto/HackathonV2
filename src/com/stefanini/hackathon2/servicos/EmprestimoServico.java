package com.stefanini.hackathon2.servicos;

import java.time.LocalDate;

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
		if (emprestimo.getIdEmprestimo() == null) {
			if (emprestimo.getStatus() == null) {
				emprestimo.setStatus("Alugado");
				emprestimo.setDataRetirada(LocalDate.now());
				repositorio.inserir(emprestimo);
			} else {
				repositorio.atualizar(emprestimo);
			}
		}
	}

	@Transacional
	public List<Emprestimo> carregaTodosEmprestimosDoBanco() {
		return repositorio.todosEmprestimos();
	}

	@Transacional
	public void devolver(Emprestimo emprestimo) {
		if (!emprestimo.getStatus().equals(null)) {
			emprestimo.setStatus(null);
			emprestimo.setDataDevolucao(LocalDate.now());
			repositorio.devolver(emprestimo);
		}
	}

}
