package psquiza.enums;

/**
 * Representacao de uma Funcao de um Pesquisador. A Funcao pode ser ESTUDANTE,
 * PROFESSOR ou EXTERNO.
 * 
 * @author Eniedson Fabiano Pereira da SIlva Junior
 */
public enum Funcao {

	/**
	 * Tipos possiveis de Funcao.
	 */
	ESTUDANTE("estudante"), PROFESSOR("professor"), EXTERNO("externo");

	/**
	 * Atributo que representa a funcao.
	 */
	private String funcao;

	/**
	 * Metodo que altera a funcao.
	 * 
	 * @param funcao nova funcao;
	 */
	Funcao(String funcao) {
		this.funcao = funcao;
	}

	/**
	 * Metodo que retorna a funcao.
	 * 
	 * @return funcao atual.
	 */
	public String getFuncao() {
		return funcao;
	}

	/**
	 * Metodo responsavel por atribuir a funcao e que pode lancar excessoes caso a
	 * funcao passada sejaa invalida.
	 * 
	 * @param funcao funcao a ser atribuida.
	 * 
	 * @return funcao.
	 */
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
