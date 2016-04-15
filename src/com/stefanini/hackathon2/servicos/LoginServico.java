package com.stefanini.hackathon2.servicos;

import java.util.List;

import javax.inject.Inject;

import com.stefanini.hackathon2.entidades.Login;
import com.stefanini.hackathon2.repositorios.LoginRepositorio;
import com.stefanini.hackathon2.transacao.Transacional;

public class LoginServico {

	@Inject
	private LoginRepositorio repositorio;
	
	@Transacional
	public void salvar(Login login) {
		if (login.getIdLogin() == null) {
			repositorio.inserir(login);
		} else {
			repositorio.atualizar(login);
		}
	}

	@Transacional
	public List<Login> carregaTodosLoginsDoBanco() {
		return repositorio.todosLogin();
	}

	@Transacional
	public void deletar(Login login) {
		repositorio.remover(login);;
	}
	
}
