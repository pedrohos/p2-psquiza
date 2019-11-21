package psquiza.entidades;

import java.io.Serializable;

import psquiza.Util;

/**
 * Classe que representa a especialidade que um pesquisador pode assumir. Além
 * das caracterificas do Pesquisador de outros tipos, ele possui formacao, data
 * e a unidade que ele pertence.
 */
public class Professor implements Especialidade, Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Armazena a formacao do professor.
	 */
	private String formacao;
	/**
	 * Armazena a data que o professor foi contratado.
	 */
	private String data;
	/**
	 * Armazena a unidade que o professor foi alocado.
	 */
	private String unidade;

	/**
	 * Constroi um professor recebendo sua formacao, unidade de alocacao e data de
	 * contratacao
	 * 
	 * @param formacao formacao do professor
	 * @param unidade  unidade de alocacao
	 * @param data     data de contrato
	 */
	public Professor(String formacao, String unidade, String data) {
		Util.validaAtributo(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		Util.validaAtributo(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		Util.validaAtributo(data, "Campo data nao pode ser nulo ou vazio.");
		Util.validaData(data);
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	public String getFormacao() {
		return formacao;
	}

	public String getData() {
		return data;
	}

	public String getUnidade() {
		return unidade;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s", formacao, unidade, data);
	}

	/**
	 * Modifica um atributo do professor, passando o atributo que vai ser modificado
	 * e o novo valor que ele vai possuir
	 */
	@Override
	public void setAtributo(String atributo, String novo) {
		switch (atributo) {

		case "FORMACAO":
			formacao = novo;
			break;
		case "DATA":
			data = novo;
			break;
		case "UNIDADE":
			unidade = novo;
			break;
		default:
			throw new IllegalArgumentException();

		}

	}

}
