package psquiza;

public class Problema {
	
	private String descricao;
	private int viabilidade;
	private String codigo;

	public Problema(String descricao, int viabilidade, String codigo) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.codigo + " - "+this.descricao + " - "+this.viabilidade;
	}
	
	
	

}
