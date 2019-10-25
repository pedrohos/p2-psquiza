package psquiza;

import easyaccept.EasyAccept;

public class Psquiza {

	private Sistema sistema;

	public Psquiza() {
		sistema = new Sistema();
	}

	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		sistema.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String id) {
		sistema.apagaAtividade(id);
	}
	
	public void cadastraItem(String id, String item) {
		sistema.cadastraItem(id, item);
	}
	
	public String exibeAtividade(String id) {
		return sistema.exibeAtividade(id);
	}
	
	public int contaItensPendentes(String id) {
		return sistema.contaItensPendentes(id);
	}
	
	public int contaItensRealizados(String id) {
		return sistema.contaItensRealizados(id);
	}

	public static void main(String[] args) {
		args = new String[] { "psquiza.Psquiza", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt" };

		EasyAccept.main(args);
	}
}
