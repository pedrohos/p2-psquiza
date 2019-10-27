package psquiza;

import java.util.HashMap;
import java.util.NoSuchElementException;

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
		validaEmail(email);
		validaFoto(fotoURL);

		pesquisadores.put(email, new Pesquisador(nome, biografia, email, fotoURL, funcao));
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		Util.validaAtributo(novoValor, "Campo " + atributo + " nao pode ser nulo ou vazio.");
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(atributo, "Campo atributo nao pode ser nulo ou vazio.");
		validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		switch (atributo) {
		case "nome":
			pesquisadores.get(email).setNome(novoValor);
			break;
		case "funcao":
			pesquisadores.get(email).setFuncao(novoValor);
			break;
		case "biografia":
			pesquisadores.get(email).setBiografia(novoValor);
			break;
		case "email":
			validaEmail(novoValor);
			Pesquisador aux = pesquisadores.remove(email);
			aux.setEmail(novoValor);
			pesquisadores.put(novoValor, aux);
			break;
		case "fotoURL":
			validaFoto(novoValor);
			pesquisadores.get(email).setFoto(novoValor);
			break;
		}
	}

	public void desativaPesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		validaEmail(email);

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
		validaEmail(email);

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
		validaEmail(email);

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
		validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}
		
		return pesquisadores.get(email).ehAtivo();
	}

	private void validaEmail(String email) {
		if (!email.contains("@") || email.split("@").length != 2 || email.split("@")[0].trim().isEmpty() || email.split("@")[1].trim().isEmpty()) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	private void validaFoto(String foto) {
		if (foto.length() < 7 || (!foto.substring(0, 7).equals("http://") && !foto.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

}
