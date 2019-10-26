package psquiza;

import java.util.HashMap;

public class ControladorPesquisador {
	
	private HashMap<String, Pesquisador> pesquisadores;
	
	public ControladorPesquisador() {
		pesquisadores = new HashMap<String, Pesquisador>();
	}
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		Util.validaAtributo(nome, "Campo NOME nao pode ser nulo ou vazio.");
		Util.validaAtributo(biografia, "Campo BIOGRAFIA nao pode ser nulo ou vazio.");
		Util.validaAtributo(email, "Campo EMAIL nao pode ser nulo ou vazio.");
		Util.validaAtributo(fotoURL, "Campo FOTO nao pode ser nulo ou vazio.");
		Util.validaAtributo(funcao, "Campo FUNCAO nao pode ser nulo ou vazio.");
		validaEmail(email);
		validaFoto(fotoURL);
		
		pesquisadores.put(email, new Pesquisador(nome, biografia, email, fotoURL, funcao));
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		
	}
	atributo pode ser NOME, FUNCAO, BIOGRAFIA, EMAIL e FOTO
	void desativaPesquisador(String email)
	void ativaPesquisador(String email)
	String exibePesquisador(String email)
	boolean pesquisadorEhAtivo(String email)
	
	private void validaEmail(String email) {
		if (!email.contains("@") || email.split("@")[0].trim().isEmpty() || email.split("@")[1].trim().isEmpty()) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}
	
	private void validaFoto(String foto) {
		if (!foto.substring(0, 9).equals("https://") && !foto.substring(0, 7).equals("http://")) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}
	
}
