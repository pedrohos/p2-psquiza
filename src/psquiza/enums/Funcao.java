package psquiza.enums;

public enum Funcao {
	ESTUDANTE("estudante"), PROFESSOR("professor"), EXTERNO("externo");

	private String funcao;

	Funcao(String funcao) {
		this.funcao = funcao;
	}
	
	public String getFuncao() {
		return funcao;
	}
	
	public static Funcao atribuiFuncao(String funcao) {
		switch (funcao.toLowerCase()) {
		case "estudante":
			return Funcao.ESTUDANTE;
		case "professor":
			return Funcao.PROFESSOR;
		case "externo":
			return Funcao.EXTERNO;
		default:
			throw new IllegalArgumentException("Funcao invalida.");
		}
	}
}
