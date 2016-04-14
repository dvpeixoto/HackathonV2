package com.stefanini.hackathon2.validadores;

import java.util.regex.Pattern;

public class Validador {

	public Validador() {
	}

	public boolean validaEmail(String emailInput) {
		String stringPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(stringPattern);
		return pattern.matcher(emailInput).matches();
	}

	public boolean validaUsuario(String usuarioInput) {
		String stringPattern = "(^[a-zA-Z0-9_]{6,16}$)";
		Pattern pattern = Pattern.compile(stringPattern);
		return pattern.matcher(usuarioInput).matches();
	}

	public boolean validaSenha(String senhaInput) {
		return (senhaInput.length() > 5 || senhaInput.length() < 17);
	}

	public boolean validaCpf(String cpfInput) {
		if (cpfInput.length() != 14) {
			return false;
		}
		int[] posicoesMultiplicadasPrimeiro = new int[] { 0, 0, 10, 9, 8, 6, 5, 4, 2, 1, 0 };
		int[] posicoesMultiplicadasSegundo = new int[] { 0, 0, 12, 10, 9, 8, 6, 5, 4, 2, 1, 0 };
		int soma = 0;

		for (int i = 10; i > 1; i--) {
			soma += i * Integer.parseInt(
					cpfInput.substring(posicoesMultiplicadasPrimeiro[i], posicoesMultiplicadasPrimeiro[i] + 1));
		}
		int resultado = ((11 - (soma % 11)) == 10 || (11 - (soma % 11)) == 11) ? 0 : 11 - (soma % 11);
		String parFinal = Integer.toString(resultado);
		soma = 0;

		for (int i = 10; i > 1; i--) {
			soma += i * Integer
					.parseInt(cpfInput.substring(posicoesMultiplicadasSegundo[i], posicoesMultiplicadasSegundo[i] + 1));
		}
		resultado = ((11 - (soma % 11)) == 10 || (11 - (soma % 11)) == 11) ? 0 : 11 - (soma % 11);
		parFinal += Integer.toString(resultado);

		return (parFinal.equals(cpfInput.substring(12)));
	}

	public boolean validaTelefone(String telInput) {
		return (telInput.length() != 14 || telInput.length() != 15);
	}

}