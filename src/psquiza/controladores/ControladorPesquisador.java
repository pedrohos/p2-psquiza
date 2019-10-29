package psquiza.controladores;

import java.util.HashMap;
import java.util.NoSuchElementException;

import psquiza.Util;
import psquiza.entidades.Pesquisador;

public class ControladorPesquisador {

	private HashMap<String, Pesquisador> pesquisadores;

	public ControladorPesquisador() {
		pesquisadores = new HashMap<String, Pesquisador>();
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		Util.validaAtributo(nome, "Campo nome nao pode ser nulo ou vazio.");
		Util.validaAtributo(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		Util.validaAtributo(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		Util.validaFoto(fotoURL);

		pesquisadores.put(email, new Pesquisador(nome, biografia, email, fotoURL, funcao));
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(atributo, "Atributo nao pode ser vazio ou nulo.");
		if (atributo.equals("FOTO")) {
			Util.validaAtributo(novoValor, "Campo " + atributo.toLowerCase() + "URL nao pode ser nulo ou vazio.");
		} else {
			Util.validaAtributo(novoValor, "Campo " + atributo.toLowerCase() + " nao pode ser nulo ou vazio.");
		}
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		switch (atributo) {
		case "NOME":
			pesquisadores.get(email).setNome(novoValor);
			break;
		case "FUNCAO":
			pesquisadores.get(email).setFuncao(novoValor);
			break;
		case "BIOGRAFIA":
			pesquisadores.get(email).setBiografia(novoValor);
			break;
		case "EMAIL":
			Util.validaEmail(novoValor);
			Pesquisador aux = pesquisadores.remove(email);
			aux.setEmail(novoValor);
			pesquisadores.put(novoValor, aux);
			break;
		case "FOTO":
			Util.validaFoto(novoValor);
			pesquisadores.get(email).setFoto(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	public void desativaPesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		pesquisadores.get(email).setAtivo(false);
	}

	public void ativaPesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}

		pesquisadores.get(email).setAtivo(true);
	}

	public String exibePesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		return pesquisadores.get(email).toString();
	}

	public boolean pesquisadorEhAtivo(String email) {
		Util.validaAtributo(email, "Email nao pode ser vazio ou nulo.");
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}
		
		return pesquisadores.get(email).ehAtivo();
	}

}
