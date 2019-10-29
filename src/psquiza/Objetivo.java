package psquiza;

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
	private String tipo;
	/**
	 * Atributo que representa o codigo de um objetivo
	 */
	private String codigo;

	/**
	 * Metodo que constroi um objetivo
	 * 
	 * @param tipo        tipo do objetivo
	 * @param descricao   descricao do objetivo
	 * @param aderencia   aderencia do objetivo
	 * @param viabilidade viabilidade do objetivo
	 * @param codigo      codigo do objetivo
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
		this.tipo = tipo;
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
