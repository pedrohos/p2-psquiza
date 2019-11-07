package psquiza.controladores;

import java.util.NoSuchElementException;

import psquiza.Util;
import psquiza.entidades.Atividade;

public class Sistema {

	private ControladorAtividade controladorAtividade;
	/**
	 * Controle que gerencia funcionalidades de uma Pesquisa.
	 */
	private ControladorPesquisa controladorPesquisa;
	private ControladorMetas controladorMetas;
	private ControladorPesquisador controladorPesquisador;

	/**
	 * Constroi um sistema inicializando os controladores de Pesquisa, Atividade,
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
	 * Funcao que cadastra uma nova pesquisa no sistema atraves de uma descricao e
	 * campo de interesse passados.
	 * 
	 * @param descricao        pequeno texto que resume o assunto da pesquisa
	 * @param campoDeInteresse Campo(s) a que a pesquisa se relaciona
	 * @return o codigo da pesquisa cadastrada
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controladorPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
	}

	/**
	 * 
	 * Altera um atributo da pesquisa, ou a descricao ou o Campo de Interesse
	 * 
	 * @param codigo               codigo da pesquisa a ser alterada
	 * @param conteudoASerAlterado atributo a ser alterado
	 * @param novoConteudo         novo valor a ser adicionado
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controladorPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * 
	 * Funcao que encerra (desativa) uma pesquisa e recebe o motivo da desativacao.
	 * 
	 * @param codigo codigo da pesquisa
	 * @param motivo motivo de ser desativada a pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		controladorPesquisa.encerraPesquisa(codigo, motivo);
	}

	/**
	 * 
	 * Ativa uma pesquisa desativada, retorna uma excessao caso a pesquisa
	 * soliciatada ja esteja ativada.
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		controladorPesquisa.ativaPesquisa(codigo);
	}

	/**
	 * 
	 * Retorna a representacao em string de uma pesquisa no formato: "Codigo -
	 * Descricao - Campo de Interesse."
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string que representa uma pesquisa.
	 */
	public String exibePesquisa(String codigo) {
		return controladorPesquisa.exibePesquisa(codigo);
	}

	/**
	 * Retorna o estado de uma pesquisa, se e ativa ou desativa.
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string true caso a pesquisa esteja ativa e false caso nao esteja.
	 */
	public boolean pesquisaEhAtiva(String codigo) {
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

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controladorPesquisa.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return controladorPesquisa.desassociaProblema(idPesquisa, idProblema);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		if (controladorPesquisa.associaObjetivo(idPesquisa, idObjetivo) == false)
			return false;
		return controladorMetas.associaPesquisa(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		if (controladorPesquisa.desassociaObjetivo(idPesquisa, idObjetivo) == false)
			return false;
		return controladorMetas.desassociaPesquisa(idPesquisa, idObjetivo);
	}

	public String busca(String termo) {
		Util.validaAtributo(termo, "Campo termo nao pode ser nulo ou vazio.");

		String listagem = String.format("%s | %s | %s | %s | %s", controladorPesquisa.buscaPesquisa(termo),
				controladorPesquisador.buscaPesquisador(termo), controladorMetas.buscaProblema(termo),
				controladorMetas.buscaObjetivo(termo), controladorAtividade.buscaAtividade(termo));
		listagem = listagem.replace("| ⠀ ", "");
		listagem = listagem.replace("⠀ | ", "");
		listagem = listagem.replace("⠀", "");

		if (listagem.trim().isEmpty()) {
			throw new NoSuchElementException("Nenhum resultado encontrado");
		}

		return listagem;
	}

	public String busca(String termo, int numeroDoResultado) {
		Util.validaAtributo(termo, "Campo termo nao pode ser nulo ou vazio.");
		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		String[] busca = busca(termo).split(" \\| ");

		if (busca.length - 1 < numeroDoResultado) {
			throw new NoSuchElementException("Entidade nao encontrada.");
		}

		return busca[numeroDoResultado - 1];
	}

	public int contaResultadosBusca(String termo) {
		Util.validaAtributo(termo, "Campo termo nao pode ser nulo ou vazio.");

		return busca(termo).split(" \\| ").length;
	}

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = controladorAtividade.getAtividade(codigoAtividade);
		return controladorPesquisa.associaAtividade(codigoPesquisa, atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = controladorAtividade.getAtividade(codigoAtividade);
		return controladorPesquisa.desassociaAtividade(codigoPesquisa, atividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		controladorAtividade.executaAtividade(codigoAtividade, item, duracao);

	}

	public String listaPesquisas(String ordem) {
		return controladorPesquisa.listaPesquisas(ordem);
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return false;
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return true;
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		controladorPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		controladorPesquisador.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public String listaPesquisadores(String tipo) {
		return null;
	}

}
