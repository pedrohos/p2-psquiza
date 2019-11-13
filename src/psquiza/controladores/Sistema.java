package psquiza.controladores;

import java.util.NoSuchElementException;

import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.entidades.Pesquisa;
import psquiza.entidades.Pesquisador;

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

	/**
	 * Metodo para cadastrar um problema
	 * 
	 * @param descricao   descricao do problema
	 * @param viabilidade viabilidade do problema
	 */
	public void cadastraProblema(String descricao, int viabilidade) {
		controladorMetas.cadastraProblema(descricao, viabilidade);
	}

	/**
	 * Metodo para cadastrar um objetivo
	 * 
	 * @param tipo        tipo do objetivo
	 * @param descricao   descricao do objetivo
	 * @param aderencia   aderencia do objetivo
	 * @param viabilidade viabilidade do objetivo
	 */
	public void cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		controladorMetas.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Metodo para apagar um problema atraves do codigo
	 * 
	 * @param codigo codigo do problema
	 */
	public void apagarProblema(String codigo) {
		controladorMetas.apagarProblema(codigo);
	}

	/**
	 * Metodo para apagar objetivo
	 * 
	 * @param codigo codigo do objetivo
	 */
	public void apagarObjetivo(String codigo) {
		controladorMetas.apagarObjetivo(codigo);
	}

	/**
	 * Metodo para exibir um problema
	 * 
	 * @param codigo codigo do problema
	 * @return retorna a exibicao do problema
	 */
	public String exibeProblema(String codigo) {
		return controladorMetas.exibeProblema(codigo);
	}

	/**
	 * Metodo para exibir um objetivo
	 * 
	 * @param codigo codigo do objetivo
	 * @return retorna a exibicao do objetivo
	 */
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

	public boolean desassociaProblema(String idPesquisa) {
		return controladorPesquisa.desassociaProblema(idPesquisa);
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
		listagem = listagem.replace(" |  ", "");
		if (listagem.charAt(0) == '|')
			listagem = listagem.substring(2);
		if (listagem.length() >= 3 && listagem.substring(listagem.length() - 3).equals(" | ")) {
			listagem = listagem.substring(0, listagem.length() - 3);
		}
		if (listagem.length() >= 2 && listagem.substring(listagem.length() - 2).equals("| ")) {
			listagem = listagem.substring(0, listagem.length() - 2);
		}
		if (listagem.length() >= 1 && listagem.charAt(listagem.length() - 1) == '|') {
			listagem = listagem.substring(0, listagem.length() - 1);
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

		if (busca(termo).length() == 0) {
			throw new NoSuchElementException("Nenhum resultado encontrado");
		}

		return busca(termo).split(" \\| ").length;
	}

	/**
	 * Metodo para associar uma atividade a uma pesquisa
	 * 
	 * @param codigoPesquisa  codigo da pesquisa
	 * @param codigoAtividade codigo da atividade
	 * @return retorna true se a associacao deu certo ou false caso nao de certo
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = controladorAtividade.getAtividade(codigoAtividade);
		return controladorPesquisa.associaAtividade(codigoPesquisa, atividade);
	}

	/**
	 * Metodo para desassociar uma atividade de uma pesquisa
	 * 
	 * @param codigoPesquisa  codigo da pesquisa
	 * @param codigoAtividade codigo da atividade
	 * @return retorna true se a desassociacao deu certo ou false caso nao de certo
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = controladorAtividade.getAtividade(codigoAtividade);
		return controladorPesquisa.desassociaAtividade(codigoPesquisa, atividade);
	}

	/**
	 * Metodo para executar uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @param item            item que sera executado
	 * @param duracao         duracao da execucao
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		Util.validaAtributo(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		controladorPesquisa.verificaAssosiacaoAtividade(codigoAtividade);
		controladorAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	public String listaPesquisas(String ordem) {
		return controladorPesquisa.listaPesquisas(ordem);
	}

	/**
	 * 
	 * Recebe uma pesquisa que sera associada ao pesquisador passado. Lanca erros em
	 * caso da pesquisa nÃ£o existir ou estar desativada e ainda se os parametros
	 * recebios forme nulos, vazios ou com formato invalido. Uma pesquisa e
	 * associada quando o pesquisador ainda nao tem aquela pesquisa em seu array e
	 * nao e associada caso a pesquisa ja exista no array.
	 * 
	 * @param idPesquisa       pesquisa que sera adicionada no pesquisador.
	 * @param emailPesquisador email do pesquisador em que a pesquisa sera
	 *                         adicionada.
	 * @return (true) caso a pesquisa seja associada e (false) caso nao possa
	 *         ocorrer a associacao.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		Pesquisador pesquisador = controladorPesquisador.getPesquisador(emailPesquisador);
		return controladorPesquisa.associaPesquisador(pesquisador, idPesquisa);
	}

	/**
	 * Recebe uma pesquisa que sera desassociada do pesquisador passado. Lanca erros
	 * em caso da pesquisa nao existir ou estar desativada e ainda se os parametros
	 * recebios forem nulos, vazios ou com formato invalido. Uma pesquisa e
	 * desassociada quando o pesquisador tem aquela pesquisa em seu array e nao e
	 * desassociada caso a pesquisa nao exista no array.
	 * 
	 * @param idPesquisa       pesquisa que sera adicionada no pesquisador.
	 * @param emailPesquisador email do pesquisador em que a pesquisa serÃ¡
	 *                         adicionada.
	 * @return (true) caso a pesquisa seja desassociada e (false) caso nÃ£o possa
	 *         ocorrer a desassociacao.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		Pesquisador pesquisador = controladorPesquisador.getPesquisador(emailPesquisador);
		return controladorPesquisa.desassociaPesquisador(pesquisador, idPesquisa);
	}

	/**
	 * Cadastra a especialidade de Professora em uma Pesquisadora, que como novas
	 * caracteristicas tem uma formacao, uma unidade de alocacao e uma data. A
	 * funcao lanca erros caso os parametros recebidos sejam nulos, vazios ou
	 * invalidos e tambem caso a pesquisadora nao exista ou sua funcao nao seja
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
	 * tambem caso a pesquisadora nao exista ou sua funcao nao seja aluna.
	 * 
	 * @param email    email que identifica a pesquisadora aluna.
	 * @param semestre semestre de ingresso da aluna.
	 * @param IEA      indice de eficiencia academica.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		controladorPesquisador.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	/**
	 * Lista pesquisadores de um mesmo tipo, o tipo e recebido como parametro e pode
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

	/**
	 * Metodo para cadastrar um resultado a uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @param resultado       resultado que sera cadastrado
	 * @return retorna um inteiro que indica o numero do resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return controladorAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	/**
	 * Metodo para remover um resultado de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @param numeroResultado numero do resultado
	 * @return retorna true se o resultado foi removido, false caso nao seja
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return controladorAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	/**
	 * Metodo para listar os resultados de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @return retorna a representacao dos resultados
	 */
	public String listaResultados(String codigoAtividade) {
		return controladorAtividade.listaResultados(codigoAtividade);
	}

	/**
	 * Metodo para pegar a duracao de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @return retorna a duracao da atividade
	 */
	public int getDuracao(String codigoAtividade) {
		return controladorAtividade.getDuracao(codigoAtividade);
	}
}
