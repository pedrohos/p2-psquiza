package psquiza.entidades;

import psquiza.Util;

public class Aluno implements Especialidade {

	private int semestre;
	private double iea;

	public Aluno(double iea, int semestre) {
		this.semestre = semestre;
		this.iea = iea;
	}

	public String getSemestre() {
		return String.format("%so SEMESTRE", semestre);
	}

	public String getIea() {
		
		return String.valueOf(iea).replace(".", ",");
	}
	
	@Override
	public void setAtributo(String atributo, String novo) {
		
		switch (atributo) {
			case "SEMESTRE":
				semestre = Integer.parseInt(novo);
				break;
			case "IEA":
				iea = Double.parseDouble(novo);
				break;

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
