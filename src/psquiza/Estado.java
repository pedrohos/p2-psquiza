package psquiza;

public enum Estado {
	PENDENTE("PENDENTE"), REALIZADO("REALIZADO");

	private String estado;

	Estado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return this.estado;
	}
}
