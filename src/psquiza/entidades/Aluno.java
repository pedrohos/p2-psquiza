package psquiza.entidades;

import psquiza.Util;

/**
 * 
 * Classe que representa a especialidade que um Pesquisador pode assumir. Além
 * das caracteristicas do Pesquisador de outros tipos, ele possui semestre de
 * estudo e IEA, um índice de eficiencia academica.
 * 
 * @author Regina Leticia Santos Felipe
 *
 */
public class Aluno implements Especialidade {

	/**
	 * Inteiro que representa o semestre do Aluno
	 */
	private int semestre;
	/**
	 * Double que representa o indice de eficiencia academica.
	 */
	private double iea;

	/**
	 * Constroi um aluno recebendo seu iea e semestre.
	 * 
	 * @param iea      Indice de Eficiencia Academica
	 * @param semestre Semestre de Ingresso.
	 */
	public Aluno(double iea, int semestre) {
		this.semestre = semestre;
		this.iea = iea;
	}

	/**
	 * Retorna o semestre do aluno
	 * 
	 * @return semestre da aluna
	 */
	public String getSemestre() {
		return String.format("%so SEMESTRE", semestre);
	}

	/**
	 * Retorna o indice de eficiencia do aluno(a)
	 * 
	 * @return indice de eficiencia academica
	 */
	public String getIea() {
		return String.valueOf(iea);
	}

	/**
	 * Muda o atributo passado que pode ser o semestre ou o IEA pelo novo valor
	 * passado.
	 * 
	 * @param atributo Atributo a ser alterado
	 * @param novo     novo atributo a ser adicionado
	 */
	@Override
	public void setAtributo(String atributo, String novo) {

		switch (atributo) {
		case "SEMESTRE":
			Util.validaNumero(Integer.parseInt(novo) - 1, "Atributo semestre com formato invalido.");
			semestre = Integer.parseInt(novo);
			break;
		case "IEA":
			Util.validaIEA(Double.parseDouble(novo));
			iea = Double.parseDouble(novo);
			break;
		default:
			throw new IllegalArgumentException();

		}
	}

	/**
	 * Retorna a representaçao em string do aluno.
	 * 
	 * @return String que representa a especialidade aluna.
	 */
	@Override
	public String toString() {
		return String.format("%s - %s", getSemestre(), getIea());
	}

}
