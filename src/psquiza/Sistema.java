package psquiza;

public class Sistema {
	private ControladorAtividade controladorAtividade;

	public Sistema() {
		this.controladorAtividade = new ControladorAtividade();
	}

	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		controladorAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String id) {
		controladorAtividade.apagaAtividade(id);
	}
	
	public void cadastraItem(String id, String item ) {
		controladorAtividade.cadastraItem(id, item);
	}
	
	public String exibeAtividade(String id) {
		return controladorAtividade.exibeAtividade(id);
	}
	
	public int contaItensPendentes(String id) {
		return controladorAtividade.contaItensPendentes(id);
	}
	
	public int contaItensRealizados(String id) {
		return controladorAtividade.contaItensRealizados(id);
	}
}
