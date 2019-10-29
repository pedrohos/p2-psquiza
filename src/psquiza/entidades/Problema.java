package psquiza.entidades;

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
	 * @param descricao   descricao do problema
	 * @param viabilidade viabilidade do problema
	 * @param codigo      codigo do problema
	 */
	public Problema(String descricao, int viabilidade, String codigo) {
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
