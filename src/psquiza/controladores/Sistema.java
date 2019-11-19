package psquiza.controladores;

import java.io.IOException;
import java.util.NoSuchElementException;

import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.entidades.Objetivo;
import psquiza.entidades.Pesquisador;
import psquiza.entidades.Problema;

public class Sistema {

	/**
	 * Armazena o controlador que gerencia as atividades.
	 */
	private ControladorAtividade controladorAtividade;

	/**
	 * Armazena o controlador que gerencia as pesquisas.
	 */
	private ControladorPesquisa controladorPesquisa;

	/**
	 * Armazena o controlador que gerencia os objetivos e problemas (metas).
	 */
	private ControladorMetas controladorMetas;

	/**
	 * Armazena o controlador que gerencia as pesquisadores.
	 */
	private ControladorPesquisador controladorPesquisador;

	/**
	 * Armazena o controlador de salvamento e carregamento dos outros controladores.
	 */
	private GerenciadorControladores gerenciadorControladores;

	/**
	 * Local onde sera salvo e carregado os controladores.
	 */
	private final String estadoSistema = "estado.dat";

	/**
	 * Constroi um sistema inicializando os controladores de Pesquisa, Atividade,
	 * Metas e Pesquisador e controladores.
	 */
	public Sistema() {
		this.controladorAtividade = new ControladorAtividade();
		this.controladorPesquisa = new ControladorPesquisa();
		this.controladorMetas = new ControladorMetas();
		this.controladorPesquisador = new ControladorPesquisador();
		this.gerenciadorControladores = new GerenciadorControladores();
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

	/**
	 * Retorna a quantidade itens pendentes em determinada atividade.
	 * 
	 * @param id e o id referente a atividade.
	 * @return e retornado a quantidade de itens com o estado PENDENTE de dada
	 *         atividade.
	 */
	public int contaItensPendentes(String id) {
		return controladorAtividade.contaItensPendentes(id);
	}

	/**
	 * Retorna a quantidade itens realizados em determinada atividade.
	 * 
	 * @param id e o id referente a atividade.
	 * @return e retornado a quantidade de itens com o estado REALIZADO de dada
	 *         atividade.
	 */
	public int contaItensRealizados(String id) {
		return controladorAtividade.contaItensRealizados(id);
	}

	/**
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
	 * Ativa uma pesquisa desativada, retorna uma excessao caso a pesquisa
	 * soliciatada ja esteja ativada.
	 * 
	 * @param codigo codigo da pesquisa
	 */
	public void ativaPesquisa(String codigo) {
		controladorPesquisa.ativaPesquisa(codigo);
	}

	/**
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

	/**
	 * Retorna um boolean indicando se o pesquisador e ativo ou nao.
	 * 
	 * @param email e o email referente ao pesquisador.
	 * @return e retornado um boolean indicando true caso o pesquisador seja ativo,
	 *         caso contrario, e retornado false.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		return controladorPesquisador.pesquisadorEhAtivo(email);
	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		Problema problema = controladorMetas.getProblema(idProblema);
		return controladorPesquisa.associaProblema(idPesquisa, problema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return controladorPesquisa.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		Objetivo objetivo = controladorMetas.getObjetivo(idObjetivo);
		if (controladorPesquisa.associaObjetivo(idPesquisa, objetivo) == false)
			return false;
		return controladorMetas.associaPesquisa(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		Objetivo objetivo = controladorMetas.getObjetivo(idObjetivo);
		if (controladorPesquisa.desassociaObjetivo(idPesquisa, objetivo) == false)
			return false;
		return controladorMetas.desassociaPesquisa(idPesquisa, idObjetivo);
	}

	/**
	 * Busca por todos os objetos do sistema que possuam o termo informado.
	 * 
	 * @param termo e termo a ser buscado.
	 * @return e retornada a lista de todos os objetos encontrados.
	 */
	public String busca(String termo) {
		Util.validaAtributo(termo, "Campo termo nao pode ser nulo ou vazio.");

		String listagem = String.format("%s | %s | %s | %s | %s", controladorPesquisa.buscaPesquisa(termo),
				controladorPesquisador.buscaPesquisador(termo), controladorMetas.buscaProblema(termo),
				controladorMetas.buscaObjetivo(termo), controladorAtividade.buscaAtividade(termo));
		listagem  = Util.removeVazios(listagem);

		return listagem;
	}

	/**
	 * Busca um objeto especifico, informado atravez do indice (numeroDoResultado)
	 * na busca pelo termo informado.
	 * 
	 * @param termo             e o termo a ser buscado no sistema.
	 * @param numeroDoResultado e o numero do resultado da busca a ser retornado.
	 * @return e retornado o objeto encontrado.
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
	 * Retorna o numero de resultados da busca pelo termo informado.
	 * 
	 * @param termo termo a ser buscado no sistema.
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
	 * Associar uma atividade a uma pesquisa.
	 * 
	 * @param codigoPesquisa  e o codigo referente a pesquisa
	 * @param codigoAtividade e o codigo referente a atividade
	 * @return retorna true se a associacao foi efetuada com sucesso, caso
	 *         contrario, false.
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = controladorAtividade.getAtividade(codigoAtividade);
		return controladorPesquisa.associaAtividade(codigoPesquisa, atividade);
	}

	/**
	 * Desassociar uma atividade de uma pesquisa.
	 * 
	 * @param codigoPesquisa  e o codigo referente a pesquisa.
	 * @param codigoAtividade e o codigo referente a atividade.
	 * @return retorna true se a desassociacao deu certo ou false caso nao de certo
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = controladorAtividade.getAtividade(codigoAtividade);
		return controladorPesquisa.desassociaAtividade(codigoPesquisa, atividade);
	}

	/**
	 * Metodo para executar uma atividade
	 * 
	 * Caso o codigoAtividade seja vazio ou nulo, sera lancado um
	 * IllegalArgumentException: "Campo codigoAtividade nao pode ser nulo ou vazio."
	 * 
	 * @param codigoAtividade e o codigo referente a atividade
	 * @param item            e o item que sera executado
	 * @param duracao         e a duracao da execucao
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

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		controladorAtividade.defineProximaAtividade(idPrecedente, idSubsquente);
	}

	public void tiraProximaAtividade(String idPrecedente) {
		controladorAtividade.tiraProximaAtividade(idPrecedente);
	}

	public int contaProximos(String idPrecedente) {
		return controladorAtividade.contaProximos(idPrecedente);
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return controladorAtividade.pegaProximo(idAtividade, enesimaAtividade);
	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return controladorAtividade.pegaMaiorRiscoAtividades(idAtividade);
	}

	/**
	 * Salva o estado atual dos controladores de: Atividade, Metas, Pesquisa e
	 * Pesquisadores No arquivo localizado em: estadoSistema
	 * 
	 * Caso ocorra algum erro no salvamento do arquivo, sera printado o stackTrace
	 * do erro.
	 */
	public void salvar() {
		try {
			gerenciadorControladores.salva(estadoSistema, controladorAtividade, controladorMetas, controladorPesquisa,
					controladorPesquisador);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega os controladores salvos no arquivo localizado em estadoSistema.
	 * 
	 * Os controladores do sistema sao substituidos por novos controladores com os
	 * atributos carregados do arquivo carregado anteriormente.
	 * 
	 * Caso ocorra um IOException ou um ClassNotFoundException, sera printado o
	 * stackTrace da excecao.
	 */
	public void carregar() {
		try {
			gerenciadorControladores.carrega(estadoSistema);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		this.controladorAtividade = gerenciadorControladores.carregaAtividade();
		this.controladorMetas = gerenciadorControladores.carregaMetas();
		this.controladorPesquisa = gerenciadorControladores.carregaPesquisa();
		this.controladorPesquisador = gerenciadorControladores.carregaPesquisador();
	}

	/**
	 * Metodo para configurar a estrategia para proxima atividade
	 * 
	 * @param estrategia estrategia que sera definida
	 */
	public void configuraEstrategia(String estrategia) {
		controladorPesquisa.configuraEstrategia(estrategia);

	}

	/**
	 * Metodo para pegar a proxima atividade
	 * 
	 * @param codigoPesquisa codigo da pesquisa
	 * @return retorna retorna o codigo da proxima atividade a ser executada
	 */
	public String proximaAtividade(String codigoPesquisa) {
		return controladorPesquisa.proximaAtividade(codigoPesquisa);
	}

	public void gravarResumo(String codigoPesquisa) {
		controladorPesquisa.gravarResumo(codigoPesquisa);

	}

	public void gravarResultados(String codigoPesquisa) {
		controladorPesquisa.gravarResultados(codigoPesquisa);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controladorAtividade == null) ? 0 : controladorAtividade.hashCode());
		result = prime * result + ((controladorMetas == null) ? 0 : controladorMetas.hashCode());
		result = prime * result + ((controladorPesquisa == null) ? 0 : controladorPesquisa.hashCode());
		result = prime * result + ((controladorPesquisador == null) ? 0 : controladorPesquisador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sistema other = (Sistema) obj;
		if (controladorAtividade == null) {
			if (other.controladorAtividade != null)
				return false;
		} else if (!controladorAtividade.equals(other.controladorAtividade))
			return false;
		if (controladorMetas == null) {
			if (other.controladorMetas != null)
				return false;
		} else if (!controladorMetas.equals(other.controladorMetas))
			return false;
		if (controladorPesquisa == null) {
			if (other.controladorPesquisa != null)
				return false;
		} else if (!controladorPesquisa.equals(other.controladorPesquisa))
			return false;
		if (controladorPesquisador == null) {
			if (other.controladorPesquisador != null)
				return false;
		} else if (!controladorPesquisador.equals(other.controladorPesquisador))
			return false;
		return true;
	}
}
