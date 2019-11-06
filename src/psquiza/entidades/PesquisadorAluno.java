package psquiza.entidades;

import psquiza.Util;

public class PesquisadorAluno implements Funcao {

	private int semestre;
	private String iea;

	public PesquisadorAluno(String iea, int semestre) {
		this.semestre = semestre;
		this.iea = iea;
	}

	public String getSemestre() {
		return String.format("%so SEMESTRE", semestre);
	}

	public String getIea() {
		return iea.replace(".", ",");
	}
	
	@Override
	public void setAtributo(String atributo, String novo) {
		
		switch (atributo) {
			case "semestre":
				semestre = Integer.parseInt(novo);
				break;
			case "iea":
				iea = novo;

		}
	}
	
	@Override
	public String getFuncao() {
		return "Aluno";
	}

	@Override
	public String toString() {
		return String.format("%s - %s",getSemestre(),getIea());
	}

}
