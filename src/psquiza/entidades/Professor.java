package psquiza.entidades;

import psquiza.Util;

public class Professor implements Especialidade {

	private String formacao;
	private String data;
	private String unidade;

	public Professor(String formacao, String unidade, String data) {
		Util.validaAtributo(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		Util.validaAtributo(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		Util.validaAtributo(data, "Campo data nao pode ser nulo ou vazio.");
		Util.validaData(data);
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	public String getFormacao() {
		return formacao;
	}

	public String getData() {
		return data;
	}

	public String getUnidade() {
		return unidade;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s", getFormacao(), getUnidade(), getData());
	}

	@Override
	public void setAtributo(String atributo, String novo) {
		
		switch (atributo) {

		case "FORMACAO":
			formacao = novo;
			break;
		case "DATA":
			data = novo;
			break;
		case "UNIDADE":
			unidade = novo;
			break;

		}

	}

}
