package psquiza;

public enum Risco {
	BAIXO("BAIXO"), MEDIO("MEDIO"), ALTO("ALTO");

	private String risco;

	Risco(String risco) {
		this.risco = risco;
	}

	public String getRisco() {
		return this.risco;
	}
}
