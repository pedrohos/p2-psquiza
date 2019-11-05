package psquiza.entidades;

import psquiza.Util;

public class PesquisadorProfessor extends Pesquisador{

	private String formacao;
	private String data;
	private String unidade;
	
	public PesquisadorProfessor(String nome, String biografia, String email, String foto, String funcao, String formacao, String unidade, String data) {
		super(nome, biografia, email, foto, funcao);
		Util.validaAtributo(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		Util.validaAtributo(unidade, "Campo unidade nao pode ser nulo ou vazio." );
		Util.validaAtributo(data, "Campo data nao pode ser nulo ou vazio.");
		Util.validaData(data);
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s", super.toString(),getFormacao(),getUnidade(), getData());
	}

}
