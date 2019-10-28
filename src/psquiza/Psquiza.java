package psquiza;

import easyaccept.EasyAccept;

public class Psquiza {

	private Sistema sistema;

	public Psquiza() {
		sistema = new Sistema();
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return sistema.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		sistema.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		sistema.encerraPesquisa(codigo, motivo);
	}

	public void encerraPesquisa(String codigo) {
		sistema.encerraPesquisa(codigo);
	}

	public void ativaPesquisa(String codigo) {
		sistema.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return sistema.exibePesquisa(codigo);
	}

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
