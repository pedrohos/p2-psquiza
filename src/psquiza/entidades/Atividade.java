package psquiza.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;

import psquiza.Util;
import psquiza.enums.Risco;

/**
 * Representacao de uma Atividade no sistema. Cada Atividade apresenta uma
 * descricao, um nivel de risco, uma descricao do risco, uma duracao (periodo),
 * um id que o identifica unicamente em seu controlador e uma lista de itens que
 * compoem a Atividade.
 * 
 * O nivel de risco deve ser um dos 3: BAIXO MEDIO ALTO
 * 
 * E possivel alem de criar uma Atividade, cadastrar um item, retornar a
 * quantidade de itens no status de PENDENTE e de REALIZADO.
 * 
 * @author Pedro Henrique
 */
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Armazena a descricao da Atividade.
	 */
	private String descricao;
	/**
	 * Armazena um inteiro que representa a posicao de cada item.
	 */
	private Integer posicaoItem = 1;
	/**
	 * Armazena a descricao do risco da Atividade.
	 */
	private String descricaoRisco;

	/**
	 * Armazena o nivel de risco da Atividade, podem estar em um de 3 estados:
	 * BAIXO, MEDIO ou ALTO
	 */
	private Risco nivelRisco;

	/**
	 * Representa a duracao da Atividade.
	 */
	private int periodo;

	/**
	 * Representa o identificador unico da Atividade.
	 */
	private String id;

	/**
	 * Armazena um conjunto ordenado de itens que compoem a atividade.
	 */
	private LinkedHashMap<Integer, Item> itens;

	/**
	 * Armazena um conjunto ordenados de resultados que compoem a atividade.
	 */
	private List<String> resultados;

	/**
	 * Guarda a proxima atividade
	 */
	private Atividade proxima;

	/**
	 * Constroi um nova atividade, validando se os parametros passados sao vazios ou
	 * nulos, caso sejam sera lancado um IllegalArgumentException:
	 * 
	 * Descricao vazia ou nulo: "Campo Descricao nao pode ser nulo ou vazio." Nivel
	 * de Risco vazio ou nulo: "Campo nivelRisco nao pode ser nulo ou vazio." ID
	 * vazio ou nulo: "Campo codigo nao pode ser nulo ou vazio." Descricao do Risco
	 * vazia ou nula: "Campo descricaoRisco nao pode ser nulo ou vazio." Caso o
	 * Nivel de Risco nao seja BAIXO, MEDIO ou ALTO, sera lancado um
	 * IllegalArgumentException: "Valor invalido do nivel do risco."
	 * 
	 * @param descricao      e a descricao da atividade.
	 * @param nivelRisco     e o nivel de risco da ativdade.
	 * @param descricaoRisco e a descricao do risco da atividade.
	 * @param id             e o identificador unico da atividade.
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String id) {
		Util.validaAtributo(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		Util.validaAtributo(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		Util.validaAtributo(id, "Campo codigo nao pode ser nulo ou vazio.");
		this.nivelRisco = Risco.atribuiRisco(nivelRisco);
		Util.validaAtributo(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");

		this.descricao = descricao;
		this.descricaoRisco = descricaoRisco;
		this.id = id;
		this.itens = new LinkedHashMap<>();
		this.resultados = new ArrayList<>();
	}

	/**
	 * Cadastra um item no conjunto ordenado de itens da Atividade.
	 * 
	 * Caso o item seja vazio ou nulo sera lancado um IllegalArgumentException:
	 * "Item nao pode ser nulo ou vazio."
	 * 
	 * @param item e o item que sera adicionado.
	 */
	public void cadastraItem(String item) {
		Util.validaAtributo(item, "Item nao pode ser nulo ou vazio.");
		this.itens.put(this.posicaoItem, new Item(item));
		this.posicaoItem += 1;
	}

	/**
	 * Retorna a quantidade de itens com o status de PENDENTE.
	 * 
	 * @return e retornado a quantidade de itens PENDENTE.
	 */
	public int getItensPendentes() {
		int resultado = 0;
		for (Integer item : itens.keySet()) {
			if (itens.get(item).getEstado().equals("PENDENTE")) {
				resultado++;
			}
		}
		return resultado;
	}

	/**
	 * Retorna uma representacao em string que mostra o estado do item e o seu
	 * estado, que pode ser pendente ou resolvido.
	 * 
	 * @return o estado dos itens de uma pesquisa.
	 */
	public String getItens() {
		String saida = "";
		int ind = 1;
		for (Item item : itens.values()) {
			saida += String.format("            - %s - ITEM%d\n", item.getEstado(), ind);
			ind++;
		}
		return saida;
	}

	/**
	 * Retorna uma string que mostra os itens realizados e as comentarios sobre as
	 * realizações
	 * 
	 * @return retorna uma string com resultados.
	 */
	public String getResultados() {
		String saida = "";
		int valor = 0;
		if (getItensRealizados() != 0) {
			valor = periodo / getItensRealizados();
		} else {
			valor = periodo;
		}
		int ind = 1;
		for (Item i : itens.values()) {
			if (i.getEstado().equals("REALIZADO")) {
				saida += String.format("            - ITEM%d - %d\n", ind, valor);
				ind++;
			}
		}

		for (String r : resultados) {
			if (r != null) {
				saida += "            - " + r + "\n";
			}
		}

		return saida;
	}

	/**
	 * Retorna a quantidade de itens com o status REALIZADO.
	 * 
	 * @return e retornado a quantidade de itens REALIZADO.
	 */
	public int getItensRealizados() {
		int resultado = 0;
		for (Integer item : itens.keySet()) {
			if (itens.get(item).getEstado().equals("REALIZADO")) {
				resultado++;
			}
		}
		return resultado;
	}

	/**
	 * Metodo que retorna o id de uma atividade
	 * 
	 * @return String que representa o id de uma atividade
	 */
	public String getId() {
		return id;
	}

	/**
	 * Método que retorna a descricao de uma atividade
	 * 
	 * @return uma string que representa descricao de uma atividade
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Retorna o nivel de risco de uma atividade que pode ser BAIXO, MEDIO ou ALTO.
	 * 
	 * @return String que representa o nivel do risco de uma atividade
	 */
	public String getNivelRisco() {
		return this.nivelRisco.getRisco();
	}

	/**
	 * Metodo que retorna a descricao do risco de uma atividade
	 * 
	 * @return String da descricao do risco de uma atividade
	 */
	public String getDescricaoRisco() {
		return this.descricaoRisco;
	}

	/**
	 * Metodo que define a atividade a ser executada apos essa e que pode lancar
	 * excessoes caso a proxima atividade ja esteja setada ou um loop seja
	 * encontrado.
	 * 
	 * @param proxima atividade a ser definida.
	 */
	public void definirProxima(Atividade proxima) {
		if (this.proxima != null)
			throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
		verificaLoop(proxima);

		this.proxima = proxima;
	}

	/**
	 * Metodo que exclui a proxima atividade da atividade em questao, tornando assim
	 * possivel a definicao de uma nova proxima atividade.
	 */
	public void tiraProxima() {
		this.proxima = null;
	}

	/**
	 * Metodo responsavel por contar a quantidade de proximas atividades apos a
	 * ativividade em questao.
	 * 
	 * @return numero de atividades subsequentes a mesma.
	 */
	public int contaProximos() {
		if (this.proxima == null)
			return 0;
		return proxima.contaProximos() + 1;
	}

	/**
	 * Metodo responsavel por pegar a enesima atividade subsequente a esta e que
	 * pode lancar uma excessao caso o indice passado nao exista.
	 * 
	 * @param index index que defini qual atividade deve ser pega.
	 * 
	 * @return id da atividade, caso seja encontrada.
	 */
	public String pegaProximo(int index) {
		if (index == 0)
			return this.id;
		if (proxima == null)
			throw new NoSuchElementException("Atividade inexistente.");
		return proxima.pegaProximo(index - 1);
	}

	/**
	 * Metodo que prepara para a busca da atividade subsequente a esta que possua o
	 * maior risco e que pode lancar excessao caso nao exista nenhuma atividade
	 * subsequente.
	 * 
	 * @return codigo da atividade subsequente a esta com o maior risco.
	 */
	public String pegaMaiorRiscoAtividades() {
		if (proxima == null)
			throw new NoSuchElementException("Nao existe proxima atividade.");
		return proxima.pegaMaiorRiscoAtividades(Risco.BAIXO, null);
	}

	/**
	 * Metodo que realiza a busca pelo maior risco entra as atividades subsequentes
	 * a esta.
	 * 
	 * @param maior       maior risco ate o momento.
	 * @param codigoMaior codigo da atividade que possui o maior risco ate o
	 *                    momento.
	 * 
	 * @return codigo da atividade com o maior risco.
	 */
	private String pegaMaiorRiscoAtividades(Risco maior, String codigoMaior) {
		if (!maior.ehMaior(nivelRisco)) {
			maior = this.nivelRisco;
			codigoMaior = this.id;
		}

		if (proxima == null)
			return codigoMaior;
		return proxima.pegaMaiorRiscoAtividades(maior, codigoMaior);
	}

	/**
	 * Metodo que verifica a existencia de um loop e lanca uma excessao caso seja
	 * encontrado.
	 * 
	 * @param a atividade a ser verificada.
	 */
	private void verificaLoop(Atividade a) {
		if (this.equals(a))
			throw new IllegalArgumentException("Criacao de loops negada.");
		if (a.proxima != null)
			verificaLoop(a.proxima);
	}

	/**
	 * Retorna a representacao toString de Atividade no formato: DESCRICAO
	 * (NIVELRISCO - DESCRICAORISCO)
	 * 
	 * @return e retornado a representacao toString de Atividade.
	 */
	@Override
	public String toString() {
		String resultado = String.format("%s (%s - %s)", getDescricao(), getNivelRisco(), getDescricaoRisco());
		Iterator<Item> it = itens.values().iterator();
		while (it.hasNext()) {
			Item item = it.next();
			resultado += String.format(" | %s - %s", item.getEstado(), item.getNome());
		}

		return resultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * Metodo para executar uma atividade
	 * 
	 * @param item    item da atvidade
	 * @param duracao duracao da atividade
	 */
	public void executaAtividade(int item, int duracao) {
		if (!itens.containsKey(item)) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
		if (itens.get(item).getEstado().equals("REALIZADO")) {
			throw new IllegalArgumentException("Item ja executado.");
		}
		itens.get(item).setEstado("REALIZADO");
		this.periodo += duracao;
	}

	/**
	 * Metodo para cadastrar um resultado
	 * 
	 * @param resultado resultado que sera cadastrado
	 * @return retorna a posicao do resultado
	 */
	public int cadastraResultado(String resultado) {
		this.resultados.add(resultado);
		return resultados.size();
	}

	/**
	 * Metodo para remover um resultado
	 * 
	 * @param numeroResultado numer do resultado
	 * @return retorna true caso seja removido, false caso nao seja
	 */
	public boolean removeResultado(int numeroResultado) {
		if (numeroResultado > resultados.size()) {
			throw new IllegalArgumentException("Resultado nao encontrado.");
		}
		if (resultados.get(numeroResultado - 1) == null) {
			return false;
		}
		resultados.set(numeroResultado - 1, null);
		return true;
	}

	/**
	 * Metodo para listar os resultados
	 * 
	 * @return retorna uma string com a representacao dos resultados
	 */
	public String listaResultados() {
		String saida = "";
		for (int i = 0; i < resultados.size(); i++) {
			if (resultados.get(i) != null) {
				if (i == resultados.size() - 1) {
					saida += resultados.get(i);
				} else {
					saida += resultados.get(i) + " | ";
				}
			}
		}
		return saida;
	}

	/**
	 * Metodo para pegar a duracao de uma atividade
	 * 
	 * @return retorna a duracao da atividade
	 */
	public int getDuracao() {
		return this.periodo;
	}

	/**
	 * Metodo para pegar o nivel de risco de uma ativiade no formato inteiro
	 * 
	 * @return retorna um inteiro que representa o nivel de risco
	 */
	public int getNivelRiscoInt() {
		return this.nivelRisco.getRiscoInt();
	}
}
