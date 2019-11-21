package psquiza.entidades;

import java.io.Serializable;

import psquiza.Util;

/**
 * Classe que representa um problema que uma pesquisa pode ter
 * 
 * @author Lucian Julio
 *
 */
public class Problema implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	 * Caso a descricao do Problema seja vazio ou nulo, sera lancada um
	 * IllegalArgumentException: "Campo descricao nao pode ser nulo ou vazio." Caso
	 * a vibilidade do Problema nao esteja no intervalo de 1 a 5, sera lancada um
	 * IllegalArgumentException: "Campo viabilidade nao pode ser nulo ou vazio."
	 * Caso o codigo do Problema seja vazio ou nulo, sera lancada um
	 * IllegalArgumentException: "Campo codigo nao pode ser nulo ou vazio."
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
	 * Metodo responsavel por retornar o atributo codigo.
	 * 
	 * @return valor do codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo responsavel por retornar o atributo descricao.
	 * 
	 * @return valor da descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo para gerar a representacao de um problema
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.viabilidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + viabilidade;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problema other = (Problema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (viabilidade != other.viabilidade)
			return false;
		return true;
	}
}
