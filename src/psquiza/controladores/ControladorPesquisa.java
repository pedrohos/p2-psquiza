package psquiza.controladores;

import java.util.HashMap;

import psquiza.Util;
import psquiza.entidades.Pesquisa;

/**
 * 
 * Representa um controlador das funcionalidades de uma pesquisa, possui métodos
 * para gerar o codigo de um pesquisador, validar o campo de interesse passado
 * como parametro, retornar se existe a pesquisa solicitada, fora as
 * funcionalidades básicas como criar, editar, desativar e ativar uma pesquisa.
 * 
 * @author Regina Letícia Santos Felipe
 *
 */
public class ControladorPesquisa {

	/**
	 * Coleção de pesquisas, no formato: Chave - Pesquisa, onde um código gerado
	 * pelo sistema identifica unicamente uma pesquisa.
	 */
	private HashMap<String, Pesquisa> pesquisas;

	/**
	 * Cria um controle de pesquisa e inicializa a coleção de pesquisas.
	 */
	public ControladorPesquisa() {
		pesquisas = new HashMap<String, Pesquisa>();
	}

	/**
	 * 
	 * Método interno da classe que gera o código de uma Pesquisa, uma string no
	 * formato "SSSI", composto por três strings, as três primeiras letras do campo
	 * de interesse da pesquisa e um inteiro que representa a quantidade de vezes
	 * que aquelas três primeiras letras já foram usadas, iniciando do 1.
	 * 
	 * @param pesquisas HasMAp que possui todas pesquisas cadastradas
	 * @param nome      Campo de Interesse da pesquisa a se gerar o código
	 * @return codigo no formato "SSSI"
	 */
	private String geraCodigo(HashMap<String, Pesquisa> pesquisas, String nome) {

		int id = 1;
		String codigo = nome.substring(0, 3).toUpperCase() + id;
		while (pesquisas.containsKey(codigo)) {
			id += 1;
			codigo = nome.substring(0, 3).toUpperCase() + id;
		}
		return codigo;
	}

	/**
	 * 
	 * Método interno da classe que lança uma excessão caso o código do pesquisador
	 * passado não exista na coleção de pesquisadores.
	 * 
	 * @param pesquisas HasMAp que possui todas pesquisas cadastradas
	 * @param codigo    código da pesquisa solicitada
	 */
	private void existePesquisa(HashMap<String, Pesquisa> pesquisas, String codigo) {
		if (!pesquisas.containsKey(codigo)) {
			throw new NullPointerException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * 
	 * Método interno da classe que valida a string do campo de interesse e lança
	 * uma excessão caso não siga o formato solicitado. Para um Campo de interesse
	 * ser válido é necessario que possua até 255 caracteres e no mínimo 3.
	 * Separados por vírgulas, uma pesquisa pode possuir no máximo quatro campos de
	 * interesses.
	 * 
	 * @param campoDeInteresse
	 */
	private void validaCampoDeInteresse(String campoDeInteresse) {

		if (campoDeInteresse == null || campoDeInteresse.trim().isEmpty()) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		if (campoDeInteresse.contains(",,") || (campoDeInteresse.length() < 3 || campoDeInteresse.length() > 255)) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		} else {
			String[] campos = campoDeInteresse.split(",");
			if (campos.length > 4) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			} else {
				for (String campo : campos) {
					if (campo.trim().isEmpty() || campo.length() < 3) {
						throw new IllegalArgumentException("Formato do campo de interesse invalido.");
					}
				}
			}
		}
	}

	/**
	 * 
	 * Função que cadastra uma nova pesquisa no sistema através de uma descrição e
	 * campo de interesse passados.
	 * 
	 * @param descricao        pequeno texto que resume o assunto da pesquisa
	 * @param campoDeInteresse Campo(s) a que a pesquisa se relaciona
	 * @return o código da pesquisa cadastrada
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		Util.validaAtributo(descricao, "Descricao nao pode ser nula ou vazia.");
		validaCampoDeInteresse(campoDeInteresse);
		String codigo = geraCodigo(pesquisas, campoDeInteresse);
		pesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));
		return codigo;

	}

	/**
	 * 
	 * Altera um atributo da pesquisa, ou a descrição ou o Campo de Interesse
	 * 
	 * @param codigo               Código da pesquisa a ser alterada
	 * @param conteudoASerAlterado atributo a ser alterado
	 * @param novoConteudo         novo valor a ser adicionado
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		if (!pesquisas.get(codigo).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (conteudoASerAlterado.equals("DESCRICAO")) {
			Util.validaAtributo(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			pesquisas.get(codigo).setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("CAMPO")) {
			validaCampoDeInteresse(novoConteudo);
			pesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * 
	 * Função que encerra (desativa) uma pesquisa e recebe o motivo da desativação.
	 * 
	 * @param codigo codigo da pesquisa
	 * @param motivo motivo de ser desativada a pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		Util.validaAtributo(motivo, "Motivo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		pesquisas.get(codigo).encerraPesquisa();
	}

	/**
	 * 
	 * Ativa uma pesquisa desativada, retorna uma excessão caso a pesquisa
	 * soliciatada já esteja ativada.
	 * 
	 * @param codigo
	 */
	public void ativaPesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		pesquisas.get(codigo).ativaPesquisa();
	}

	/**
	 * 
	 * Retorna a representação em string de uma pesquisa no formato: "Codigo -
	 * Descricao - Campo de Interesse."
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string que representa uma pesquisa.
	 */
	public String exibePesquisa(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		return pesquisas.get(codigo).toString();
	}

	/**
	 * Retorna o estado de uma pesquisa, se é ativa ou desativa.
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string true caso a pesquisa esteja ativa e false caso não esteja.
	 */
	public String ehAtiva(String codigo) {
		Util.validaAtributo(codigo, "Codigo nao pode ser nulo ou vazio.");
		existePesquisa(pesquisas, codigo);
		if (pesquisas.get(codigo).ehAtiva()) {
			return "true";
		} else {
			return "false";
		}
	}

}