package psquiza.entidades;

import psquiza.Util;
import psquiza.enums.Tipo;

/**
 * Classe para representar um objetivo
 * 
 * @author Lucian Julio
 *
 */
public class Objetivo {

	/**
	 * Atributo que indica a viabilidade do um objetivo
	 */
	private int viabilidade;
	/**
	 * Atributo que inddica a aderencia de um objetivo
	 */
	private int aderencia;
	/**
	 * Atribito que representa a descricao de um objetivo
	 */
	private String descricao;
	/**
	 * Atributo que representa o tipo do objetivo
	 */
	private Tipo tipo;
	/**
	 * Atributo que representa o codigo de um objetivo
	 */
	private String codigo;

	/**
	 * Metodo que constroi um objetivo a partir de um tipo, descricao, aderencia e viabilidade.
	 * 
	 * Caso o tipo do Objetivo seja vazio ou nulo, sera lancada um IllegalArgumentException:
	 * "Campo tipo nao pode ser nulo ou vazio."
	 * Caso a descricao do Objetivo seja vazia ou nula, sera lancada um IllegalArgumentException:
	 * "Campo descricao nao pode ser nulo ou vazio."
	 * Caso a aderencia do Objetivo seja invalida, sera lancada um IllegalArgumentException:
	 * "Campo aderencia nao pode ser nulo ou vazio."
	 * Caso a viabilidade do Objetivo seja invalida, sera lancada um IllegalArgumentException:
	 * "Campo viabilidade nao pode ser nulo ou vazio."
	 * Caso o codigo do Objetivo seja vazio ou nulo, sera lancada um IllegalArgumentException:
	 * "Campo codigo nao pode ser nulo ou vazio."
	 * 
	 * @param tipo        tipo do objetivo
	 * @param descricao   descricao do objetivo
	 * @param aderencia   aderencia do objetivo
	 * @param viabilidade viabilidade do objetivo
	 * @param codigo      codigo do objetivo
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
		Util.validaAtributo(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validaAtributo(String.valueOf(aderencia), "Campo aderencia nao pode ser nulo ou vazio.");
		Util.validaAtributo(String.valueOf(viabilidade), "Campo viabilidade nao pode ser nulo ou vazio.");
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		this.tipo = Tipo.atribuiTipo(tipo);
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	/**
	 * Metodo para gerar a representacao de um objetivo
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + (this.aderencia + this.viabilidade);
	}
}
