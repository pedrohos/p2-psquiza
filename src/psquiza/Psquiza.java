package psquiza;

import easyaccept.EasyAccept;
import psquiza.controladores.Sistema;

public class Psquiza {

	private Sistema sistema;

	public Psquiza() {
		sistema = new Sistema();
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraPesquisa(String, String)}}
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return sistema.cadastraPesquisa(descricao, campoDeInteresse);
	}

	/**
	 * {@link psquiza.controladores.Sistema#alteraPesquisa(String, String, String)}
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		sistema.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#encerraPesquisa(String, String)}
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		sistema.encerraPesquisa(codigo, motivo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#ativaPesquisa(String)}
	 */
	public void ativaPesquisa(String codigo) {
		sistema.ativaPesquisa(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#exibePesquisa(String)}
	 */

	public String exibePesquisa(String codigo) {
		return sistema.exibePesquisa(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#pesquisaEhAtiva(String)}
	 */

	public boolean pesquisaEhAtiva(String codigo) {
		return sistema.pesquisaEhAtiva(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraPesquisador(String, String, String, String, String)}
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		sistema.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * {@link psquiza.controladores.Sistema#alteraPesquisador(String, String, String)}
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		sistema.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * {@link psquiza.controladores.Sistema#desativaPesquisador(String)}
	 */
	public void desativaPesquisador(String email) {
		sistema.desativaPesquisador(email);
	}

	/**
	 * {@link psquiza.controladores.Sistema#ativaPesquisador(String)}
	 */
	public void ativaPesquisador(String email) {
		sistema.ativaPesquisador(email);
	}

	/**
	 * {@link psquiza.controladores.Sistema#exibePesquisador(String)}
	 */
	public String exibePesquisador(String email) {
		return sistema.exibePesquisador(email);
	}

	/**
	 * {@link psquiza.controladores.Sistema#pesquisadorEhAtivo(String)}
	 */
	public boolean pesquisadorEhAtivo(String email) {
		return sistema.pesquisadorEhAtivo(email);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraAtividade(String, String, String)}
	 */
	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		sistema.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	/**
	 * {@link psquiza.controladores.Sistema#apagaAtividade(String)}
	 */
	public void apagaAtividade(String id) {
		sistema.apagaAtividade(id);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraItem(String, String)}
	 */
	public void cadastraItem(String id, String item) {
		sistema.cadastraItem(id, item);
	}

	/**
	 * {@link psquiza.controladores.Sistema#exibeAtividade(String)}
	 */
	public String exibeAtividade(String id) {
		return sistema.exibeAtividade(id);
	}

	/**
	 * {@link psquiza.controladores.Sistema#contaItensPendentes(String)}
	 */
	public int contaItensPendentes(String id) {
		return sistema.contaItensPendentes(id);
	}

	/**
	 * {@link psquiza.controladores.Sistema#contaItensRealizados(String)}
	 */
	public int contaItensRealizados(String id) {
		return sistema.contaItensRealizados(id);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraProblema(String, int)}
	 */
	public void cadastraProblema(String descricao, int viabilidade) {
		sistema.cadastraProblema(descricao, viabilidade);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraObjetivo(String, String, int, int)}
	 */
	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		sistema.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * {@link psquiza.controladores.Sistema#apagarProblema(String)}
	 */
	public void apagarProblema(String codigo) {
		sistema.apagarProblema(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#apagarObjetivo(String)}
	 */
	public void apagarObjetivo(String codigo) {
		sistema.apagarObjetivo(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#exibeProblema(String)}
	 */
	public String exibeProblema(String codigo) {
		return sistema.exibeProblema(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#exibeObjetivo(String)}
	 */
	public String exibeObjetivo(String codigo) {
		return sistema.exibeObjetivo(codigo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#associaProblema(String, String)}
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		return sistema.associaProblema(idPesquisa, idProblema);
	}

	/**
	 * {@link psquiza.controladores.Sistema#desassociaProblema(String, String)}
	 */
	public boolean desassociaProblema(String idPesquisa) {
		return sistema.desassociaProblema(idPesquisa);
	}

	/**
	 * {@link psquiza.controladores.Sistema#associaObjetivo(String, String)}
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return sistema.associaObjetivo(idPesquisa, idObjetivo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#desassociaObjetivo(String, String)}
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return sistema.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#listaPesquisas(String)}
	 */
	public String listaPesquisas(String ordem) {
		return sistema.listaPesquisas(ordem);
	}

	public String busca(String termo) {
		return sistema.busca(termo);
	}

	public String busca(String termo, int numeroDoResultado) {
		return sistema.busca(termo, numeroDoResultado);
	}

	public int contaResultadosBusca(String termo) {
		return sistema.contaResultadosBusca(termo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#associaAtividade(String, String)}
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return sistema.associaAtividade(codigoPesquisa, codigoAtividade);
	}

	/**
	 * {@link psquiza.controladores.Sistema#desassociaAtividade(String, String)}
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return sistema.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	/**
	 * {@link psquiza.controladores.Sistema#executaAtividade(String, int, int)}
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		sistema.executaAtividade(codigoAtividade, item, duracao);
	}

	/**
	 * {@link psquiza.controladores.Sistema#associaPesquisador(String, String)}
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return sistema.associaPesquisador(idPesquisa, emailPesquisador);
	}

	/**
	 * {@link psquiza.controladores.Sistema#desassociaPesquisador(String, String)}
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return sistema.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraEspecialidadeProfessor(String, String, String, String)}
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		sistema.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraEspecialidadeAluno(String, Integer, double)}
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		sistema.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	/**
	 * {@link psquiza.controladores.Sistema#listaPesquisadores(String)}
	 */
	public String listaPesquisadores(String tipo) {
		return sistema.listaPesquisadores(tipo);
	}

	/**
	 * {@link psquiza.controladores.Sistema#cadastraResultado(String, String)}
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return sistema.cadastraResultado(codigoAtividade, resultado);
	}

	/**
	 * {@link psquiza.controladores.Sistema#removeResultado(String, int)}
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return sistema.removeResultado(codigoAtividade, numeroResultado);
	}

	/**
	 * {@link psquiza.controladores.Sistema#listaResultados(String)}
	 */
	public String listaResultados(String codigoAtividade) {
		return sistema.listaResultados(codigoAtividade);
	}

	/**
	 * {@link psquiza.controladores.Sistema#getDuracao(String)}
	 */
	public int getDuracao(String codigoAtividade) {
		return sistema.getDuracao(codigoAtividade);
	}

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		sistema.defineProximaAtividade(idPrecedente, idSubsquente);
	}

	public void tiraProximaAtividade(String idPrecedente) {
		sistema.tiraProximaAtividade(idPrecedente);
	}

	public int contaProximos(String idPrecedente) {
		return sistema.contaProximos(idPrecedente);
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return sistema.pegaProximo(idAtividade, enesimaAtividade);
	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return sistema.pegaMaiorRiscoAtividades(idAtividade);
	}
	
	public void salva() {
		sistema.salva();
	}
	
	public void carrega() {
		sistema.carrega();
	}

	public static void main(String[] args) {
		args = new String[] { "psquiza.Psquiza", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt", "testes_aceitacao/use_case_5.txt",
				"testes_aceitacao/use_case_6.txt", "testes_aceitacao/use_case_7.txt", "testes_aceitacao/use_case_8.txt",
				"testes_aceitacao/use_case_9.txt", "testes_aceitacao/use_case_10.txt",
				"testes_aceitacao/use_case_11.txt", "testes_aceitacao/use_case_12SALVAR.txt",
				"testes_aceitacao/use_case_12CARREGAR.txt" };
		EasyAccept.main(args);
	}
}
