package com.stefanini.hackathon2.servicos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Livro;
import com.stefanini.hackathon2.repositorios.EmprestimoRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class EmprestimoServico {

	@Inject
	private EmprestimoRepositorio repositorio;

	@Transacional
	public void salvar(Emprestimo emprestimo) {
		if (emprestimo.getIdEmprestimo() == null) {
			if (emprestimo.getStatus() == null) {
				for (Livro livroVerificaEstoque : emprestimo.getLivros()) {
					if (livroVerificaEstoque.getQuantidadeEstoque() <= 2) {
						System.out.println("não rolou");
					} else {
						for (Livro livroDiminuirEstoque : emprestimo.getLivros()) {
							livroDiminuirEstoque.setQuantidadeEstoque(livroDiminuirEstoque.getQuantidadeEstoque() - 1);
						}
						emprestimo.setStatus("Alugado");
						emprestimo.setDataRetirada(LocalDateTime.now());
						repositorio.inserir(emprestimo);
					}
				}
			}
		}
	}

	@Transacional
	public List<Emprestimo> carregaTodosEmprestimosDoBanco() {
		return repositorio.todosEmprestimos();
	}

	@Transacional
	public void devolver(Emprestimo emprestimo) {
		if (emprestimo.getStatus() != null) {
			emprestimo.setStatus(null);
			emprestimo.setDataDevolucao(LocalDateTime.now());
			Duration dur = Duration.between(emprestimo.getDataRetirada(), emprestimo.getDataDevolucao());
			for (Livro livroAtribuirEstoque : emprestimo.getLivros()) {
				livroAtribuirEstoque.setQuantidadeEstoque(livroAtribuirEstoque.getQuantidadeEstoque() + 1);
			}
			if (dur.toDays() > 7) {
				emprestimo.setDiasAtrasados((int) dur.toDays() - 7);
				repositorio.devolver(emprestimo);
			} else {
				emprestimo.setDiasAtrasados(0);
				repositorio.devolver(emprestimo);
			}
		}
	}
}
