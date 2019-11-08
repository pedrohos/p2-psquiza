package psquiza.controladores;

import java.util.NoSuchElementException;

import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.entidades.Pesquisa;

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

	/**
	 * Metodo responsavel por busca por todos os objetos do sistema que possuam o
	 * termo informado.
	 * 
	 * @param termo termo a ser buscado.
	 * 
	 * @return lista de todos os objetos encontrados.
	 */
	public String busca(String termo) {
		Util.validaAtributo(termo, "Campo termo nao pode ser nulo ou vazio.");

		String listagem = String.format("%s | %s | %s | %s | %s", controladorPesquisa.buscaPesquisa(termo),
				controladorPesquisador.buscaPesquisador(termo), controladorMetas.buscaProblema(termo),
				controladorMetas.buscaObjetivo(termo), controladorAtividade.buscaAtividade(termo));
		listagem = listagem.replace("| â € ", "");
		listagem = listagem.replace("â € | ", "");
		listagem = listagem.replace("â €", "");

		if (listagem.trim().isEmpty()) {
			throw new NoSuchElementException("Nenhum resultado encontrado");
		}

		return listagem;
	}

	/**
	 * Metodo que busca um objeto especifico, informado atravez do indice
	 * (numeroDoResultado) na busca pelo termo informado.
	 * 
	 * @param termo             termo a ser buscado no sistema.
	 * @param numeroDoResultado numero do resultado da busca a ser retornado.
	 * 
	 * @return objeto encontrado.
	 */
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

	/**
	 * Metodo que retorna o numero de resultados da busca pelo termo informado.
	 * 
	 * @param termo termo a ser buscado no sistema.
	 * 
	 * @return numero de resultados encontrados.
	 */
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

	/**
	 * 
	 * Recebe uma pesquisa que serÃ¡ associada ao pesquisador passado. Lanca erros em
	 * caso da pesquisa nÃ£o existir ou estar desativada e ainda se os parametros
	 * recebios forme nulos, vazios ou com formato invÃ¡lido. Uma pesquisa Ã©
	 * associada quando o pesquisador ainda nÃ£o tem aquela pesquisa em seu array e
	 * nÃ£o Ã© associada caso a pesquisa jÃ¡ exista no array.
	 * 
	 * @param idPesquisa       pesquisa que sera adicionada no pesquisador.
	 * @param emailPesquisador email do pesquisador em que a pesquisa serÃ¡
	 *                         adicionada.
	 * @return (true) caso a pesquisa seja associada e (false) caso nÃ£o possa
	 *         ocorrer a associacao.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		Pesquisa pesquisa = controladorPesquisa.getPesquisa(idPesquisa);
		return controladorPesquisador.associaPesquisador(pesquisa, emailPesquisador);
	}

	/**
	 * Recebe uma pesquisa que sera desassociada do pesquisador passado. Lanca erros
	 * em caso da pesquisa nao existir ou estar desativada e ainda se os parametros
	 * recebios forem nulos, vazios ou com formato invalido. Uma pesquisa Ã©
	 * desassociada quando o pesquisador tem aquela pesquisa em seu array e nÃ£o Ã©
	 * desassociada caso a pesquisa nao exista no array.
	 * 
	 * @param idPesquisa       pesquisa que sera adicionada no pesquisador.
	 * @param emailPesquisador email do pesquisador em que a pesquisa serÃ¡
	 *                         adicionada.
	 * @return (true) caso a pesquisa seja desassociada e (false) caso nÃ£o possa
	 *         ocorrer a desassociacao.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		Pesquisa pesquisa = controladorPesquisa.getPesquisa(idPesquisa);
		return controladorPesquisador.desassociaPesquisador(pesquisa, emailPesquisador);
	}

	/**
	 * Cadastra a especialidade de Professora em uma Pesquisadora, que como novas
	 * caracteristicas tem uma formacao, uma unidade de alocacao e uma data. A
	 * funcao lanca erros caso os parametros recebidos sejam nulos, vazios ou
	 * invalidos e tambÃ©m caso a pesquisadora nao exista ou sua funcao nao seja
	 * professora.
	 * 
	 * @param email    referencia a pesquisadora a ser cadastrada as especialidades
	 * @param formacao formacao da pesquisadora professora
	 * @param unidade  unidade de alocacao da professora
	 * @param data     data data de contratacao
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		controladorPesquisador.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	/**
	 * Cadastra a especialidade de Aluna em uma Pesquisadora, que como novas
	 * caracteristicas tem um semestr e um indice de eficiencia academica. A funcao
	 * lanca erros caso os parametros recebidos sejam nulos, vazios ou invalidos e
	 * tambÃ©m caso a pesquisadora nao exista ou sua funcao nao seja aluna.
	 * 
	 * @param email    email que identifica a pesquisadora aluna.
	 * @param semestre semestre de ingresso da aluna.
	 * @param IEA      indice de eficiencia academica.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		controladorPesquisador.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	/**
	 * Lista pesquisadores de um mesmo tipo, o tipo Ã© recebido como parametro e pode
	 * ser "EXTERNO", "ALUNA" ou "PROFESSORA". Lanca erros casos o tipo passado seja
	 * vazio, nulo o invalido (diferente dos tipos existentes). Retorna uma String
	 * que contem a representacao em String dos pesquisadores.
	 * 
	 * @param tipo tipo no qual vao ser listados os pesquisadores.
	 * @return String com representacao em string dos pesquisadores.
	 */
	public String listaPesquisadores(String tipo) {
		return controladorPesquisador.listaPesquisadores(tipo);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controladorAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return controladorAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return controladorAtividade.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return controladorAtividade.getDuracao(codigoAtividade);
	}
}
