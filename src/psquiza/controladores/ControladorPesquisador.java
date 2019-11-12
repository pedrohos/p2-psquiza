package psquiza.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import psquiza.Util;
import psquiza.entidades.Aluno;
import psquiza.entidades.Pesquisa;
import psquiza.entidades.Pesquisador;
import psquiza.entidades.Professor;
import psquiza.ordenacao.OrdenaPesquisador;

/**
 * Classe controladora de pesquisadores.
 * 
 * @author Eniedson Fabiano Pereira da Silva Junior.
 */
public class ControladorPesquisador {

	/**
	 * HashMap responsavel por armazenar os pesquisadores cadastrados.
	 */
	private HashMap<String, Pesquisador> pesquisadores;

	/**
	 * Metodo responsavel por construir o controlador.
	 */
	public ControladorPesquisador() {
		pesquisadores = new HashMap<String, Pesquisador>();
	}

	/**
	 * Metodo responsavel por cadastrar os pesquisadores no sistema e que lanca
	 * excessoes caso os parametros informados sejam invalidos.
	 * 
	 * @param nome      nome do pesquisador a ser cadastrado.
	 * @param funcao    funcao do pesquisador a ser cadastrado.
	 * @param biografia biografia do pesquisador a ser cadastrado.
	 * @param email     email do pesquisador a ser cadastrado.
	 * @param fotoURL   url da foto do pesquisador a ser cadastrado.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {

		Util.validaAtributo(nome, "Campo nome nao pode ser nulo ou vazio.");
		Util.validaAtributo(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		Util.validaAtributo(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		Util.validaFoto(fotoURL);

		pesquisadores.put(email, new Pesquisador(nome, biografia, email, fotoURL, funcao));
	}

	/**
	 * Metodo responsavel por alterar os atributos do pesquisador e que pode lancar
	 * excessoes caso os parametros informados sejam invalidos, o pesquisador nao
	 * exista ou esteja inativo.
	 * 
	 * @param email     email do pesquisador a ser alterado.
	 * @param atributo  atributo do pesquisador que deve ser alterado.
	 * @param novoValor novo valor do atributo que deve ser alterado.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(atributo, "Atributo nao pode ser vazio ou nulo.");
		if (atributo.equals("FOTO")) {
			Util.validaAtributo(novoValor, "Campo " + atributo.toLowerCase() + "URL nao pode ser nulo ou vazio.");
		} else {
			Util.validaAtributo(novoValor, "Campo " + atributo.toLowerCase() + " nao pode ser nulo ou vazio.");
		}
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		switch (atributo) {
		case "NOME":
			pesquisadores.get(email).setNome(novoValor);
			break;
		case "FUNCAO":
			pesquisadores.get(email).setFuncao(novoValor);
			break;
		case "BIOGRAFIA":
			pesquisadores.get(email).setBiografia(novoValor);
			break;
		case "EMAIL":
			Util.validaEmail(novoValor);
			Pesquisador aux = pesquisadores.remove(email);
			aux.setEmail(novoValor);
			pesquisadores.put(novoValor, aux);
			break;
		case "FOTO":
			Util.validaFoto(novoValor);
			pesquisadores.get(email).setFoto(novoValor);
			break;
		case "SEMESTRE":
			Util.validaNumero(Integer.parseInt(novoValor) - 1, "Atributo semestre com formato invalido.");
			pesquisadores.get(email).setAtributoEspecialidade(atributo, novoValor);
			break;
		case "IEA":
			Util.validaIEA(Double.parseDouble(novoValor));
			pesquisadores.get(email).setAtributoEspecialidade(atributo, novoValor);
			break;
		case "DATA":
			Util.validaData(novoValor);
			pesquisadores.get(email).setAtributoEspecialidade(atributo, novoValor);
			break;
		case "FORMACAO":
			pesquisadores.get(email).setAtributoEspecialidade(atributo, novoValor);
			break;
		case "UNIDADE":
			pesquisadores.get(email).setAtributoEspecialidade(atributo, novoValor);
			break;
		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	/**
	 * Metodo responsavel por desativar um pesquisador e que pode lancar excessoes
	 * caso o email seja invalido, o pesquisador nao exista ou o mesmo ja esteja
	 * inativo.
	 * 
	 * @param email email do pesquisador a ser desativado.
	 */
	public void desativaPesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}
		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		pesquisadores.get(email).setAtivo(false);
	}

	/**
	 * Metodo responsavel por ativar um pesquisador e que pode lancar excessoes caso
	 * o email seja invalido, o pesquisador nao exista ou o mesmo ja esteja ativo.
	 * 
	 * @param email email do pesquisador a ser ativado.
	 */
	public void ativaPesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}

		pesquisadores.get(email).setAtivo(true);
	}

	/**
	 * Metodo responsavel por exibir o pesquisador e que pode lancar excessoes caso
	 * o email seja invalido, o pesquisador nao exista ou o mesmo esteja inativo.
	 * 
	 * @param email email do pesquisador a ser exibido.
	 * 
	 * @return representacao textual do pesquisador.
	 */
	public String exibePesquisador(String email) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		if (!pesquisadores.get(email).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		return pesquisadores.get(email).toString();
	}

	/**
	 * Metodo que retorna se o pesquisador e ativo ou nao e que pode lancar
	 * excessoes caso o email seja invalido ou o pesquisador nao exista.
	 * 
	 * @param email email do pesquisador a ser verificado.
	 * 
	 * @return boolean que informa se o pesquisador e ativo ou nao.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		Util.validaAtributo(email, "Email nao pode ser vazio ou nulo.");
		Util.validaEmail(email);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisador nao encontrado");
		}

		return pesquisadores.get(email).ehAtivo();
	}

	/**
	 * 
	 * Recebe uma pesquisa que será associada ao pesquisador passado. Lanca erros em
	 * caso da pesquisa não existir ou estar desativada e ainda se os parametros
	 * recebios forme nulos, vazios ou com formato inválido. Uma pesquisa é
	 * associada quando o pesquisador ainda não tem aquela pesquisa em seu array e
	 * não é associada caso a pesquisa já exista no array.
	 * 
	 * @param pesquisa pesquisa que sera adicionada no pesquisador.
	 * @param email    email do pesquisador em que a pesquisa será adicionada.
	 * @return (true) caso a pesquisa seja associada e (false) caso não possa
	 *         ocorrer a associacao.
	 */
	public boolean associaPesquisador(Pesquisa pesquisa, String email) {
		Util.validaAtributo(email, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		return pesquisadores.get(email).associaPesquisa(pesquisa);
	}

	/**
	 * Recebe uma pesquisa que será desassociada do pesquisador passado. Lanca erros
	 * em caso da pesquisa não existir ou estar desativada e ainda se os parametros
	 * recebios forme nulos, vazios ou com formato inválido. Uma pesquisa é
	 * desassociada quando o pesquisador tem aquela pesquisa em seu array e não é
	 * desassociada caso a pesquisa não exista no array.
	 * 
	 * @param pesquisa pesquisa que sera adicionada no pesquisador.
	 * @param email    email do pesquisador em que a pesquisa será adicionada.
	 * @return (true) caso a pesquisa seja desassociada e (false) caso não possa
	 *         ocorrer a desassociacao.
	 */
	public boolean desassociaPesquisador(Pesquisa pesquisa, String email) {
		Util.validaAtributo(email, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		return pesquisadores.get(email).desassociaPesquisa(pesquisa);
	}

	/**
	 * Metodo que busca na colecao de pesquisadores que possuam o termo informado na
	 * biografia.
	 * 
	 * @param termo termo a ser buscado.
	 * 
	 * @return lista de resultados.
	 */
	public String buscaPesquisador(String termo) {
		String listagem = "";
		List<Pesquisador> aux = pesquisadores.values().stream().collect(Collectors.toList());
		Collections.sort(aux, new OrdenaPesquisador());
		for (Pesquisador pesquisador : aux) {
			if (pesquisador.getBiografia().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += pesquisador.getEmail() + ": " + pesquisador.getBiografia();
				} else {
					listagem += " | " + pesquisador.getEmail() + ": " + pesquisador.getBiografia();
				}
			}
		}

		return listagem;
	}

	/**
	 * Cadastra a especialidade de Professora em uma Pesquisadora, que como novas
	 * caracteristicas tem uma formacao, uma unidade de alocacao e uma data. A
	 * funcao lanca erros caso os parametros recebidos sejam nulos, vazios ou
	 * invalidos e também caso a pesquisadora nao exista ou sua funcao nao seja
	 * professora.
	 * 
	 * @param email    referencia a pesquisadora a ser cadastrada as especialidades
	 * @param formacao formacao da pesquisadora professora
	 * @param unidade  unidade de alocacao da professora
	 * @param data     data data de contratacao
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		Util.validaAtributo(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		Util.validaAtributo(data, "Campo data nao pode ser nulo ou vazio.");
		Util.validaEmail(email);
		Util.validaData(data);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisadora nao encontrada.");
		}
		if (!pesquisadores.get(email).getFuncaoPesquisador().equals("professor")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}

		Professor especialidade = new Professor(formacao, unidade, data);
		pesquisadores.get(email).setEspecialidade(especialidade);

	}

	/**
	 * Cadastra a especialidade de Aluna em uma Pesquisadora, que como novas
	 * caracteristicas tem um semestr e um indice de eficiencia academica. A funcao
	 * lanca erros caso os parametros recebidos sejam nulos, vazios ou invalidos e
	 * também caso a pesquisadora nao exista ou sua funcao nao seja aluna.
	 * 
	 * @param email    email que identifica a pesquisadora aluna.
	 * @param semestre semestre de ingresso da aluna.
	 * @param IEA      indice de eficiencia academica.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		Util.validaAtributo(email, "Campo email nao pode ser nulo ou vazio.");
		Util.validaAtributo(String.valueOf(semestre), "Campo semestre nao pode ser nulo ou vazio.");
		Util.validaAtributo(String.valueOf(IEA), "Campo iea nao pode ser nulo ou vazio.");
		Util.validaNumero(semestre - 1, "Atributo semestre com formato invalido.");
		Util.validaEmail(email);
		Util.validaIEA(IEA);

		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisadora nao encontrada.");
		}
		if (!pesquisadores.containsKey(email)) {
			throw new NoSuchElementException("Pesquisadora nao encontrada.");
		}
		if (!pesquisadores.get(email).getFuncaoPesquisador().equals("estudante")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}

		Aluno especialidade = new Aluno(IEA, semestre);
		pesquisadores.get(email).setEspecialidade(especialidade);
	}

	/**
	 * Lista pesquisadores de um mesmo tipo, o tipo é recebido como parametro e pode
	 * ser "EXTERNO", "ALUNA" ou "PROFESSORA". Lanca erros casos o tipo passado seja
	 * vazio, nulo o invalido (diferente dos tipos existentes). Retorna uma String
	 * que contem a representacao em String dos pesquisadores.
	 * 
	 * @param tipo tipo no qual vao ser listados os pesquisadores.
	 * @return String com representacao em string dos pesquisadores.
	 */
	public String listaPesquisadores(String tipo) {

		Util.validaAtributo(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		ArrayList<Pesquisador> valores = new ArrayList<Pesquisador>(pesquisadores.values());
		Collections.sort(valores);
		String saida = "";

		if (tipo.equals("EXTERNO") || tipo.equals("ALUNA") || tipo.equals("PROFESSORA")) {

			for (Pesquisador pesquisador : valores) {
				if (pesquisador.getFuncaoPesquisador().equals(tipo.toLowerCase())) {
					saida += pesquisador.toString() + " | ";
				}
			}
		} else {
			throw new IllegalArgumentException(String.format("Tipo %s inexistente.", tipo));
		}

		return saida.substring(0, saida.length() - 3);
	}
}
