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
	 * Tipos possiveis de Risco: BAIXO, MEDIO e ALTO
	 */
	BAIXO(new Object[] { "BAIXO", 0 }), MEDIO(new Object[] { "MEDIO", 1 }), ALTO(new Object[] { "ALTO", 2 });

	/**
	 * Armazena o risco atual.
	 */
	private Object[] risco;

	/**
	 * Altera o risco atual com o recebido por parametro.
	 * 
	 * @param risco e o novo risco.
	 */
	Risco(Object[] risco) {
		this.risco = risco;
	}

	/**
	 * Retorna o risco atual.
	 * 
	 * @return e retornado o risco atual.
	 */
	public String getRisco() {
		return (String) this.risco[0];
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

	public boolean ehMaior(Risco r) {
		return (int) this.risco[1] > (int) r.risco[1];
	}

	/**
	 * Metodo que pega o nivel de risco em formato inteiro
	 * 
	 * @return retorna o nivel do risco
	 */
	public int getRiscoInt() {
		return (int) this.risco[1];
	}
}
