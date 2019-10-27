package psquiza;

public class Objetivo {
	
	private int viabilidade;
	private int aderencia;
	private String descricao;
	private String tipo;
	private String codigo;

	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.codigo + " - "+this.tipo + " - "+this.descricao+ " - "+(this.aderencia+this.viabilidade);
	}
}
