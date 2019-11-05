package psquiza.entidades;

import psquiza.Util;

public class PesquisadorAluno extends  Pesquisador {
	
	private int semestre;
	private String iea;

	public PesquisadorAluno(String nome, String biografia, String email, String foto, String funcao, String iea, int semestre) {
		super(nome, biografia, email, foto, funcao);
		this.semestre = semestre;
		this.iea = iea;
	}

	public String getSemestre() {
		return String.format("%so SEMESTRE", semestre);
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public String getIea() {
		return iea.replace(".", ",");
	}

	public void setIea(String iea) {
		this.iea = iea;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s", super.toString(),getSemestre(),getIea());
	}
	
	

}
