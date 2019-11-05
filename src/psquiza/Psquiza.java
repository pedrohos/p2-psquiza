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
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return sistema.desassociaProblema(idPesquisa, idProblema);
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

	public static void main(String[] args) {
		args = new String[] { "psquiza.Psquiza",
							  "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
					          "testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt",
					          "testes_aceitacao/use_case_5.txt"};

		EasyAccept.main(args);
	}
}
