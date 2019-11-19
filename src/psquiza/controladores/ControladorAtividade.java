package psquiza.controladores;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import psquiza.Util;
import psquiza.entidades.Atividade;
import psquiza.ordenacao.OrdenaAtividade;

/**
 * Representacao do gerenciador de Atividades no sistema.
 * 
 * Possui um ID gerador e um ID processado. Possui um mapa de atividades que sao
 * cadastradas e recuperadas pelo ID: "'A' + IDPROCESSADO" E possivel cadastrar,
 * exibir e apagar uma atividade. E possivel cadastrar e exibir itens de uma
 * atividade, bem contar os itens pendentes e os realizados.
 * 
 * @author Pedro Henrique
 */
public class ControladorAtividade implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Armazena um mapa que mapeia um id a uma Atividade.
	 */
	private HashMap<String, Atividade> atividades;

	/**
	 * Representa o id final da atividade.
	 */
	private String idAtual;

	/**
	 * Representa o id gerador do id da atividade.
	 */
	private int codigoId;

	/**
	 * Verifica se existe uma atividade a partir de seu codigo. Se a atividade
	 * existir no mapa de atividades e retornado true, caso contrario false.
	 * 
	 * @param codigo e o id que identifica a atividade.
	 * @return e retornado um boolean indicando se atividade existe ou nao.
	 */
	private boolean existeAtividade(String codigo) {
		if (this.atividades.containsKey(codigo)) {
			return true;
		}
		return false;
	}

	/**
	 * Constroi o controlador criando um novo mapa de atividades, definindo o id
	 * gerador como 1 inicialmente e armazenando um ID final.
	 */
	public ControladorAtividade() {
		this.atividades = new HashMap<>();
		this.codigoId = 1;
		this.idAtual = "A" + codigoId;
	}

	/**
	 * Constroi o controlador recuperando as informacoes do controlador ja salvo,
	 * contendo um mapa de atividades, o id gerador atual da atividade e o id final.
	 */
	public ControladorAtividade(HashMap<String, Atividade> atividades, int codigoId) {
		this.atividades = atividades;
		this.codigoId = codigoId;
		this.idAtual = "A" + codigoId;
	}

	/**
	 * Cadastra uma atividade no mapa de atividades. Cada atividade deve ser
	 * cadastrada com uma descricao, nivel de risco e descricao do risco.
	 * 
	 * O nivel de risco pode (deve) ser: BAIXO MEDIO ALTO
	 * 
	 * O id e mapeado como chave da atividade e um novo e gerado.
	 * 
	 * @param descricao      e a descricao da atividade.
	 * @param nivelRisco     e o nivel de risco da atividade.
	 * @param descricaoRisco e a descricao do risco da atividade.
	 */
	public void cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.atividades.put(this.idAtual, new Atividade(descricao, nivelRisco, descricaoRisco, this.idAtual));
		this.codigoId++;
		this.idAtual = "A" + codigoId;
	}

	/**
	 * Exclui uma atividade a partir de seu id.
	 * 
	 * Caso o id seja nulo ou vazio sera lancado um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio." Caso o id nao remeta a nenhuma atividade
	 * sera lancado um IllegalArgumentException: "Atividade nao encontrada"
	 * 
	 * @param id e o id que identifica a atividade.
	 */
	public void apagaAtividade(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		this.atividades.remove(id);
	}

	/**
	 * Cadastra um item em uma atividade. O item e composto por uma String.
	 * 
	 * Caso o id seja nulo ou vazio sera lancado um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio." Caso o item seja nulo ou vazio sera
	 * lancado um IllegalArgumentException: "Item nao pode ser nulo ou vazio." Caso
	 * o id nao remeta a nenhuma atividade sera lancado um IllegalArgumentException:
	 * "Atividade nao encontrada"
	 * 
	 * @param id   e o id que remete a uma atividade.
	 * @param item e a descricao do Item.
	 */
	public void cadastraItem(String id, String item) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		Util.validaAtributo(item, "Item nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		this.atividades.get(id).cadastraItem(item);
	}

	/**
	 * Exibe a representacao String de atividade, que segue o padrao: DESCRICAO
	 * (NIVELRISCO - DESCRICAORISCO)
	 * 
	 * Caso o id seja nulo ou vazio sera lancado um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio." Caso o id nao remeta a nenhuma atividade
	 * sera lancado um IllegalArgumentException: "Atividade nao encontrada"
	 * 
	 * @param id e o id que remete a uma atividade.
	 * @return retorna a representacao toString de dada atividade.
	 */
	public String exibeAtividade(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");

		return this.atividades.get(id).toString();
	}

	/**
	 * Retorna a quantidade itens que possui o status de PENDENTE de determinada
	 * atividade.
	 * 
	 * Caso o id nao remeta a nenhuma atividade sera lancado um
	 * IllegalArgumentException: "Atividade nao encontrada" Caso o id seja nulo ou
	 * vazio sera lancado um IllegalArgumentException: "Campo codigo nao pode ser
	 * nulo ou vazio."
	 * 
	 * @param id e o id que remete a determinada atividade.
	 * @return e retornado um int indicando a quantidade de itens PENDENTE.
	 */
	public int contaItensPendentes(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");

		return this.atividades.get(id).getItensPendentes();
	}

	/**
	 * Retorna a quantidade itens que possui o status de REALIZADO de determinada
	 * atividade.
	 * 
	 * Caso o id nao remeta a nenhuma atividade sera lancado um
	 * IllegalArgumentException: "Atividade nao encontrada" Caso o id seja nulo ou
	 * vazio sera lancado um IllegalArgumentException: "Campo codigo nao pode ser
	 * nulo ou vazio."
	 * 
	 * @param id e o id que remete a determinada atividade.
	 * @return e retornado um int indicando a quantidade de itens REALIZADO.
	 */
	public int contaItensRealizados(String id) {
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(id))
			throw new IllegalArgumentException("Atividade nao encontrada");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");

		return this.atividades.get(id).getItensRealizados();
	}

	/**
	 * Metodo que busca na colecao de atividades que possuam o termo informado na
	 * descricao e na descricao de risco.
	 * 
	 * @param termo termo a ser buscado.
	 * 
	 * @return lista de resultados.
	 */
	public String buscaAtividade(String termo) {
		String listagem = "";
		List<Atividade> aux = atividades.values().stream().collect(Collectors.toList());
		Collections.sort(aux, new OrdenaAtividade());
		for (Atividade atividade : aux) {
			if (atividade.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += atividade.getId() + ": " + atividade.getDescricao();
				} else {
					listagem += " | " + atividade.getId() + ": " + atividade.getDescricao();
				}
			}
			if (atividade.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				if (listagem.isEmpty()) {
					listagem += atividade.getId() + ": " + atividade.getDescricaoRisco();
				} else {
					listagem += " | " + atividade.getId() + ": " + atividade.getDescricaoRisco();
				}
			}
		}

		return listagem;
	}

	/**
	 * Coleta uma atividade do mapa atividades a partir do codigo da atividade.
	 * 
	 * Caso o codigo da atividade seja vazio ou nulo sera lancado um
	 * IllegalArgumentException: "Campo codigoAtividade nao pode ser nulo ou vazio."
	 * Caso o codigo da atividade nao remeta a nenhuma atividade sera lancado um
	 * IllegalArgumentException: "Atividade nao encontrada"
	 * 
	 * @param codigoAtividade
	 * @return
	 */
	public Atividade getAtividade(String codigoAtividade) {
		Util.validaAtributo(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}

		return atividades.get(codigoAtividade);
	}

	/**
	 * Metodo que executa uma atividade
	 * 
	 * @param codigoAtividade codigo da atividae
	 * @param item            item da atividade
	 * @param duracao         duracao da execucao da atividade
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		Util.validaNumero(item, "Item nao pode ser nulo ou negativo.");
		Util.validaNumero(duracao, "Duracao nao pode ser nula ou negativa.");
		if (!atividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao existe!");
		}
		atividades.get(codigoAtividade).executaAtividade(item, duracao);
	}

	/**
	 * Metodo que cadastra um resultado
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @param resultado       resultado que sera cadastrado
	 * @return retorna um inteiro representando o numero do resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		Util.validaAtributo(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Util.validaAtributo(resultado, "Resultado nao pode ser nulo ou vazio.");
		if(!atividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao existe!");
		}
		return atividades.get(codigoAtividade).cadastraResultado(resultado);
	}

	/**
	 * Metodo que remove um resultado de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @param numeroResultado numero do resultado
	 * @return retorna true caso seja removido, false caso nao seja
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		Util.validaAtributo(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Util.validaNumero(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		if (!atividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return atividades.get(codigoAtividade).removeResultado(numeroResultado);
	}

	/**
	 * Metodo que lista os resultados de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @return retorna a representacao dos resultados
	 */
	public String listaResultados(String codigoAtividade) {
		Util.validaAtributo(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return atividades.get(codigoAtividade).listaResultados();
	}

	/**
	 * Metodo que pega a duracao de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @return retorna a duracao da atividade
	 */
	public int getDuracao(String codigoAtividade) {
		Util.validaAtributo(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return atividades.get(codigoAtividade).getDuracao();
	}

	/**
	 * Metodo que define a proxima atividade de uma outra e que pode lancar
	 * excessoes caso algum parametro seja invalido ou uma das atividades nao
	 * exista.
	 * 
	 * @param idPrecedente id da atividade que seja definida uma proxima.
	 * @param idSubsquente id da atividade que sera definida como proxima.
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		Util.validaAtributo(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		Util.validaAtributo(idSubsquente, "Atividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(idPrecedente) || !atividades.containsKey(idSubsquente))
			throw new NoSuchElementException("Atividade nao encontrada.");

		atividades.get(idPrecedente).definirProxima(atividades.get(idSubsquente));
	}

	/**
	 * Metodo que tira a proxima atividade de uma outra e que pode lancar excessoes
	 * caso o id da atividade seja invalido ou nao exista a atividade.
	 * 
	 * @param idPrecedente id da atividade que a proxima sera tirada.
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		Util.validaAtributo(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(idPrecedente))
			throw new NoSuchElementException("Atividade nao encontrada.");

		atividades.get(idPrecedente).tiraProxima();
	}

	/**
	 * Metodo que conta a quantidade de proximas atividades de uma atividade e que
	 * pode lancar excessoes caso o id da atividade seja invalido ou a atividade em
	 * questao nao exista.
	 * 
	 * @param idPrecedente id da atividade que seram contadas as proximas.
	 * 
	 * @return quantidade de proximas atividades.
	 */
	public int contaProximos(String idPrecedente) {
		Util.validaAtributo(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(idPrecedente))
			throw new NoSuchElementException("Atividade nao encontrada.");

		return atividades.get(idPrecedente).contaProximos();
	}

	/**
	 * Metodo que pega a enesima atividade subsequente a outra e que pode lancar
	 * excessoes caso algum parametro seja invalido ou a atividade nao exista.
	 * 
	 * @param idAtividade      id da atividade que sera buscada uma proxima.
	 * @param enesimaAtividade indice da atividade buscada.
	 * 
	 * @return id da atividade encontrada.
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		Util.validaAtributo(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(idAtividade))
			throw new NoSuchElementException("Atividade nao encontrada.");
		if (enesimaAtividade < 1)
			throw new IllegalArgumentException("EnesimaAtividade nao pode ser negativa ou zero.");

		return atividades.get(idAtividade).pegaProximo(enesimaAtividade);
	}

	/**
	 * Metodo que pega a atividade subsequente a outra que possui o maior risco
	 * entre elas e que pode lancar excessoes caso o id da atividade seja invalido
	 * ou nao exista uma atividade com o mesmo.
	 * 
	 * @param idAtividade id da atividade que sera buscado.
	 * 
	 * @return atividade subsequente com maior risco.
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		Util.validaAtributo(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		if (!atividades.containsKey(idAtividade))
			throw new NoSuchElementException("Atividade nao encontrada.");

		return atividades.get(idAtividade).pegaMaiorRiscoAtividades();
	}

	public HashMap<String, Atividade> getMapaAtividades() {
		return this.atividades;
	}

	public int getCodigoId() {
		return this.codigoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividades == null) ? 0 : atividades.hashCode());
		result = prime * result + codigoId;
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
		ControladorAtividade other = (ControladorAtividade) obj;
		if (atividades == null) {
			if (other.atividades != null)
				return false;
		} else if (!atividades.equals(other.atividades))
			return false;
		if (codigoId != other.codigoId)
			return false;
		return true;
	}
}
