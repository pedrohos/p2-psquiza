package psquiza;

public class Sistema {
	private ControladorAtividade controladorAtividade;
	private ControladorPesquisa controladorPesquisa;
	private ControladorMetas controladorMetas;

	public Sistema() {
		this.controladorAtividade = new ControladorAtividade();
		this.controladorPesquisa = new ControladorPesquisa();
		this.controladorMetas = new ControladorMetas();
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
	
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controladorPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}
	
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controladorPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		controladorPesquisa.encerraPesquisa(codigo, motivo);
	}
	
	public void encerraPesquisa(String codigo) {
		controladorPesquisa.encerraPesquisa(codigo);
	}
	
	public void ativaPesquisa(String codigo) {
		controladorPesquisa.ativaPesquisa(codigo);
	}
	
	public String exibePesquisa(String codigo) {
		return controladorPesquisa.exibePesquisa(codigo);
	}
	
	public String pesquisaEhAtiva(String codigo) {
		return controladorPesquisa.ehAtiva(codigo);
	}
	
	public void cadastraProblema(String descricao, int viabilidade) {
		controladorMetas.cadastraProblema(descricao, viabilidade);
	}

	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		controladorMetas.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		controladorMetas.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		controladorMetas.apagarObjetivo(codigo);
		
	}

	public String exibeProblema(String codigo) {
		return controladorMetas.exibeProblema(codigo);
		
	}

	public String exibeObjetivo(String codigo) {
		return controladorMetas.exibeObjetivo(codigo);
	}
}
