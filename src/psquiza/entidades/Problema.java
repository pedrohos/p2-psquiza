package psquiza.entidades;

import psquiza.Util;

/**
 * Clase que representa um problema no sistema
 * 
 * @author Lucian Julio
 *
 */
public class Problema {

	/**
	 * Atributo que representa a descricao do problema
	 */
	private String descricao;
	/**
	 * Atributo que representa a viabilidade de um problema
	 */
	private int viabilidade;
	/**
	 * Atributo que representa o codigo do problema
	 */
	private String codigo;

	/**
	 * Metodo para constroir um problema
	 * 
	 * Caso a descricao do Problema seja vazio ou nulo, sera lancada um IllegalArgumentException:
	 * "Campo descricao nao pode ser nulo ou vazio."
	 * Caso a vibilidade do Problema nao esteja no intervalo de 1 a 5, sera lancada um IllegalArgumentException:
	 * "Campo viabilidade nao pode ser nulo ou vazio."
	 * Caso o codigo do Problema seja vazio ou nulo, sera lancada um IllegalArgumentException:
	 * "Campo codigo nao pode ser nulo ou vazio."
	 * 
	 * @param descricao   descricao do problema
	 * @param viabilidade viabilidade do problema
	 * @param codigo      codigo do problema
	 */
	public Problema(String descricao, int viabilidade, String codigo) {
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validarLimite(viabilidade, 1, 5, "Campo viabilidade nao pode ser nulo ou vazio.");
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	/**
	 * Metodo para gerar a representacao de um problema
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.viabilidade;
	}

}
