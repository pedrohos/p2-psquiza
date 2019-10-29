package psquiza.enums;

/**
 * Representa o Estado de um item.
 * 
 * O Estado pode ser PENDENTE ou REALIZADO.
 * 
 * @author Pedro Henrique
 */
public enum Estado {
	
	/**
	 * Tipos possiveis de Estado:
	 * PENDENTE e REALIZADO
	 */
	PENDENTE("PENDENTE"), REALIZADO("REALIZADO");

	/**
	 * Armazena o estado atual.
	 */
	private String estado;

	/**
	 * Altera o estado atual com o recebido por parametro.
	 * 
	 * @param estado e o novo estado.
	 */
	Estado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna o estado atual.
	 * 
	 * @return e retornado o estado atual.
	 */
	public String getEstado() {
		return this.estado;
	}
	
	/**
	 * Retorna o estado real referente ao nome do estado.
	 * 
	 * @param estado e o novo estado.
	 * @return e retornado o estado.
	 */
	public static Estado atribuiEstado(String estado) {
		switch (estado) {
		case "PENDENTE":
			return Estado.PENDENTE;
		case "REALIZADO":
			return Estado.REALIZADO;
		default:
			throw new IllegalArgumentException("Valor invalido do estado.");
		}
	}
}
