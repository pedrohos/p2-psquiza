package psquiza.controladores;

public class Sistema {

	private ControladorAtividade controladorAtividade;
	/**
	 * Controle que gerencia funcionalidades de uma Pesquisa.
	 */
	private ControladorPesquisa controladorPesquisa;
	private ControladorMetas controladorMetas;
	private ControladorPesquisador controladorPesquisador;

	/**
	 * Constrói um sistema inicializando os controladores de Pesquisa, Atividade,
	 * Metas e Pesquisador.
	 */
	public Sistema() {
		this.controladorAtividade = new ControladorAtividade();
		this.controladorPesquisa = new ControladorPesquisa();
		this.controladorMetas = new ControladorMetas();
		this.controladorPesquisador = new ControladorPesquisador();
	}

	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		controladorAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String id) {
		controladorAtividade.apagaAtividade(id);
	}

	public void cadastraItem(String id, String item) {
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

	/**
	 * 
	 * Função que cadastra uma nova pesquisa no sistema através de uma descrição e
	 * campo de interesse passados.
	 * 
	 * @param descricao        pequeno texto que resume o assunto da pesquisa
	 * @param campoDeInteresse Campo(s) a que a pesquisa se relaciona
	 * @return o código da pesquisa cadastrada
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controladorPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}

	/**
	 * 
	 * Altera um atributo da pesquisa, ou a descrição ou o Campo de Interesse
	 * 
	 * @param codigo               Código da pesquisa a ser alterada
	 * @param conteudoASerAlterado atributo a ser alterado
	 * @param novoConteudo         novo valor a ser adicionado
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controladorPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * 
	 * Função que encerra (desativa) uma pesquisa e recebe o motivo da desativação.
	 * 
	 * @param codigo codigo da pesquisa
	 * @param motivo motivo de ser desativada a pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		controladorPesquisa.encerraPesquisa(codigo, motivo);
	}

	/**
	 * 
	 * Função que encerra (desativa) uma pesquisa.
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void encerraPesquisa(String codigo) {
		controladorPesquisa.encerraPesquisa(codigo);
	}

	/**
	 * 
	 * Ativa uma pesquisa desativada, retorna uma excessão caso a pesquisa
	 * soliciatada já esteja ativada.
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		controladorPesquisa.ativaPesquisa(codigo);
	}

	/**
	 * 
	 * Retorna a representação em string de uma pesquisa no formato: "Codigo -
	 * Descricao - Campo de Interesse."
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string que representa uma pesquisa.
	 */
	public String exibePesquisa(String codigo) {
		return controladorPesquisa.exibePesquisa(codigo);
	}

	/**
	 * Retorna o estado de uma pesquisa, se é ativa ou desativa.
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string true caso a pesquisa esteja ativa e false caso não esteja.
	 */
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

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		controladorPesquisador.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		controladorPesquisador.alteraPesquisador(email, atributo, novoValor);
	}

	public void desativaPesquisador(String email) {
		controladorPesquisador.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		controladorPesquisador.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return controladorPesquisador.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return controladorPesquisador.pesquisadorEhAtivo(email);

	}
}
