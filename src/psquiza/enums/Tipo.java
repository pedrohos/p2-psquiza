package psquiza.enums;

/**
 * Representacao de Tipo no sistema.
 * 
 * O Tipo pode ser GERAL ou ESPECIFICO
 * 
 * @author Pedro Henrique
 */
public enum Tipo {
	
	/**
	 * Tipos possiveis:
	 * GERAL e ESPECIFICO
	 */
	GERAL("GERAl"), ESPECIFICO("ESPECIFICO");
	
	/**
	 * Armazena o tipo atual.
	 */
	private String tipo;
	
	/**
	 * Substitui o tipo atual com o passado pelo parametro.
	 * 
	 * @param tipo e o novo tipo.
	 */
	Tipo (String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Retorna o tipo atual.
	 * 
	 * @return e retornado o tipo atual.
	 */
	public String getTipo() {
		return this.tipo;
	}
	
	/**
	 * Atribui um tipo ao atributo tipo a partir de uma String
	 * passada por parametro.
	 * 
	 * Caso o tipo nao seja vailido sera lancado um IllegalArgumentException:
	 * "Valor invalido do nivel do tipo."
	 * 
	 * @param tipo e o novo tipo
	 * @return e retornado o tipo alterado. 
	 */
	public static Tipo atribuiTipo(String tipo) {
		switch (tipo) {
		case "GERAL":
			return Tipo.GERAL;
		case "ESPECIFICO":
			return Tipo.ESPECIFICO;
		default:
			throw new IllegalArgumentException("Valor invalido do nivel do tipo.");
		}
	}
}
