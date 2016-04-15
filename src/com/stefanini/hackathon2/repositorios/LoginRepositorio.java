package com.stefanini.hackathon2.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.stefanini.hackathon2.entidades.Login;

@SuppressWarnings("all")
public class LoginRepositorio {

	@Inject
	private EntityManager entityManager;
	
	public void inserir(Login login) {
		entityManager.persist(login);
	}

	public List<Login> todosLogin() {
		return entityManager.createQuery("select l from " + Login.class.getSimpleName() + " l").getResultList();
	}
	
	public void remover(Login login) {
		entityManager.remove(entityManager.merge(login));
	}

	public void removerPorId(Integer id) {
		Login entity = entityManager.find(Login.class, id);
		entityManager.remove(entity);	
	}

	public void atualizar(Login login) {
		entityManager.merge(login);	
	}
	
	public Login pesquisarPorID(Integer id) {
		return entityManager.find(Login.class, id);
	}
	
}
