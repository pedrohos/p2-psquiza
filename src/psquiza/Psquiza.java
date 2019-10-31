package psquiza;

import easyaccept.EasyAccept;
import psquiza.controladores.Sistema;

public class Psquiza {

	private Sistema sistema;

	public Psquiza() {
		sistema = new Sistema(); 
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
		return sistema.cadastraPesquisa(descricao, campoDeInteresse);
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
		sistema.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * 
	 * Função que encerra (desativa) uma pesquisa e recebe o motivo da desativação.
	 * 
	 * @param codigo codigo da pesquisa
	 * @param motivo motivo de ser desativada a pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		sistema.encerraPesquisa(codigo, motivo);
	}


	/**
	 * 
	 * Ativa uma pesquisa desativada.
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		sistema.ativaPesquisa(codigo);
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
		return sistema.exibePesquisa(codigo);
	}

	/**
	 * Retorna o estado de uma pesquisa, se é ativa ou desativa.
	 * 
	 * @param codigo codigo da pesquisa
	 * @return string true caso a pesquisa esteja ativa e false caso não esteja.
	 */

	public String pesquisaEhAtiva(String codigo) {
		return sistema.pesquisaEhAtiva(codigo);
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		sistema.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		sistema.alteraPesquisador(email, atributo, novoValor);
	}

	public void desativaPesquisador(String email) {
		sistema.desativaPesquisador(email);
	}

	public void ativaPesquisador(String email) {
		sistema.ativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return sistema.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return sistema.pesquisadorEhAtivo(email);
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

	public void cadastraProblema(String descricao, int viabilidade) {
		sistema.cadastraProblema(descricao, viabilidade);
	}

	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		sistema.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		sistema.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		sistema.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return sistema.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return sistema.exibeObjetivo(codigo);
	}

	public static void main(String[] args) {
		args = new String[] { "psquiza.Psquiza", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
				"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt" };

		EasyAccept.main(args);
	}
}
