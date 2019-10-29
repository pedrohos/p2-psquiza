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
}
