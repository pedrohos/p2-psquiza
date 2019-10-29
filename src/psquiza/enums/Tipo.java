package psquiza.enums;

public enum Tipo {
	GERAL("GERAl"), ESPECIFICO("ESPECIFICO");
	
	private String tipo;
	
	Tipo (String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
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
