package com.stefanini.hackathon2.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.stefanini.hackathon2.entidades.Emprestimo;
import com.stefanini.hackathon2.entidades.Livro;

@SuppressWarnings("all")
public class EmprestimoRepositorio {

	@Inject
	private EntityManager entityManager;

	public void inserir(Emprestimo emprestimo) {
		entityManager.persist(emprestimo);
	}

	public List<Emprestimo> todosEmprestimos() {
		return entityManager.createQuery("select l from " + Emprestimo.class.getSimpleName() + " l").getResultList();
	}

	public void devolver(Emprestimo emprestimo) {
		entityManager.merge(emprestimo);
	}

	public Emprestimo pesquisarPorID(Integer id) {
		return entityManager.find(Emprestimo.class, id);
	}

	public void atualizar(Emprestimo emprestimo) {
		entityManager.merge(emprestimo);
	}

}
