package psquiza.controladores;

import java.util.HashMap;

import psquiza.Util;
import psquiza.entidades.Pesquisa;

public class ControladorPesquisa {

	private HashMap<String, Pesquisa> pesquisas;

	public ControladorPesquisa() {
		pesquisas = new HashMap<String, Pesquisa>();
	}

	public static String geraCodigo(HashMap<String, Pesquisa> pesquisas, String nome) {

		int id = 1;
		String codigo = nome.substring(0, 3).toUpperCase() + id;
		while (pesquisas.containsKey(codigo)) {
			id += 1;
			codigo = nome.substring(0, 3).toUpperCase() + id;
		}
		return codigo;
	}

	public static void existePesquisa(HashMap<String, Pesquisa> pesquisas, String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
	}

	public static void validaCampoDeInteresse(String campoDeInteresse) {
		if (campoDeInteresse.contains(",,") || campoDeInteresse.trim().isEmpty()) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		} else if (campoDeInteresse.length() < 3 || campoDeInteresse.length() >= 255) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		} else {
			String[] campos = campoDeInteresse.split(",");
			if (campos.length > 4) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
			for (String campo : campos) {
				if (campo.length() < 3 || campo.trim().isEmpty() || campo.length() >= 255) {
					throw new IllegalArgumentException("Formato do campo de interesse invalido.");
				}
			}
		}
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		Util.validaAtributo(descricao, "Descricao nao pode ser nula ou vazia.");
		validaCampoDeInteresse(campoDeInteresse);
		String codigo = geraCodigo(pesquisas, campoDeInteresse);
		pesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));
		return codigo;

	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		if(!pesquisas.get(codigo).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("DESCRICAO")) {
			Util.validaAtributo(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisas.get(codigo).setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("CAMPO")){
			validaCampoDeInteresse(novoConteudo);
			pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		}else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	public void encerraPesquisa(String codigo, String motivo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		pesquisas.get(codigo).encerraPesquisa();
	}
	
	public void encerraPesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		pesquisas.get(codigo).encerraPesquisa();
	}


	public void ativaPesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		pesquisas.get(codigo).ativaPesquisa();
	}

	public String exibePesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		return pesquisas.get(codigo).toString();
	}

	public String ehAtiva(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		if (pesquisas.get(codigo).ehAtiva()) {
			return "true";
		}else {
			return "false";
		}
	}

}