package psquiza.enums;

/**
 * Representacao do Risco de uma Atividade.
 * 
 * O Risco pode ser BAIXO, MEDIO ou ALTO.
 * 
 * @author Pedro Henrique
 */
public enum Risco {
	
	/**
	 * Tipos possiveis de Risco:
	 * BAIXO, MEDIO e ALTO
	 */
	BAIXO("BAIXO"), MEDIO("MEDIO"), ALTO("ALTO");

	/**
	 * Armazena o risco atual.
	 */
	private String risco;

	/**
	 * Altera o risco atual com o recebido por parametro.
	 * 
	 * @param risco e o novo risco.
	 */
	Risco(String risco) {
		this.risco = risco;
	}

	/**
	 * Retorna o risco atual.
	 * 
	 * @return e retornado o risco atual.
	 */
	public String getRisco() {
		return this.risco;
	}
	
	/**
	 * Retorna o risco referente ao nome do nivel do risco.
	 * 
	 * @param nivelRisco e o nivel de risco.
	 * @return e retornado o risco.
	 */
	public static Risco atribuiRisco(String nivelRisco) {
		switch (nivelRisco) {
		case "BAIXO":
			return Risco.BAIXO;
		case "MEDIO":
			return Risco.MEDIO;
		case "ALTO":
			return Risco.ALTO;
		default:
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
	}
}
