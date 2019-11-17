package psquiza.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import psquiza.Util;

/**
 * Representacao de uma pesquisa realizada por um pesquisador. Possui uma
 * descricao, ate quatro campos de interesse, um codigo gerado automaticamente
 * pelo sistema e um estado que indica se a pesquisa esta ativa (1) ou
 * desativada (-1)
 *
 * @author Regina Leticia Santos Felipe - 119110519
 * 
 */
public class Pesquisa implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Descricao da pesquisa que será realizada através de um resumo.
	 */
	private String descricao;

	/**
	 * Um marcador da area ou tema a ser colocado. Pode ter ate 4 tipos, separados
	 * por virgula e ter ate 255 caracteres.
	 */
	private String campoDeInteresse;
	/**
	 * Código gerado automaticamente pelas primeiras tres letras do campo de
	 * interesse mais um valor inteiro comecando em 1.
	 */
	private String codigo;
	/**
	 * Status de uma pesquisa, se esta ativa ou desativada. Com o valor (1) para
	 * pesquisa ativada e (-1) para pesquisa desativada
	 */
	private boolean estado;

	private String problema;

	private HashSet<String> objetivos;

	private List<Atividade> atividades;
	/**
	 * Array que armazena pesquisadores de uma pesquisa
	 */
	private ArrayList<Pesquisador> pesquisadores;

	/**
	 * Constroi uma pesquisa atraves da sua descricao, Campo de Interesse e codigo.
	 * Inicializando o estado da pesquisa como ativo.
	 * 
	 * Caso a descricao da Pesquisa seja vazia ou nula, sera lancada um
	 * IllegalArgumentException: "Campo descricao nao pode ser nulo ou vazio." Caso
	 * o campo de interesse da Pesquisa seja vazio ou nulo, sera lancada um
	 * IllegalArgumentException: "Campo interesse nao pode ser nulo ou vazio." Caso
	 * o codigo da Pesquisa seja vazio ou nulo, sera lancada um
	 * IllegalArgumentException: "Campo codigo nao pode ser nulo ou vazio."
	 * 
	 * @param descricao      texto livre sobre a pesquisa realizada
	 * @param campoInteresse areas para quais a pesquisa engloba
	 * @param codigo         codigo gerado automaticamente pelo sistema
	 */
	public Pesquisa(String descricao, String campoInteresse, String codigo) {
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validaAtributo(campoInteresse, "Campo interesse nao pode ser nulo ou vazio.");
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.campoDeInteresse = campoInteresse;
		this.codigo = codigo;
		this.estado = true;
		this.problema = "";
		this.objetivos = new HashSet<>();
		this.atividades = new ArrayList<>();
		this.pesquisadores = new ArrayList<>();
	}

	/**
	 * Retorna o estado da pesquisa, se ativada ou nao
	 * 
	 * @return retorna string que representa se a pesquisa esta ativa ou nao.
	 */
	public String getEstado() {
		String saida = "Desativada";
		if (estado) {
			saida = "Ativada";
		}
		return saida;
	}

	/**
	 * Funcao que retorna a descricao da pesquisa
	 * 
	 * @return descricao da pesquisa
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Funcao que retorna o campo de Interesse de uma pesquisa
	 * 
	 * @return campo de interesse da pesquisa
	 */
	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	/**
	 * Funcao que retorna o codigo gerado de uma pesquisa
	 * 
	 * @return codigo de uma pesquisa
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna um boolean se pesquisa a ou nao ativa
	 * 
	 * @return true para pesquisa ativa e false para pesquisa desativada
	 */
	public boolean ehAtiva() {
		return estado;
	}

	/**
	 * Ativa uma pesquisa caso esteja desativada
	 */
	public void ativaPesquisa() {
		if (ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		} else {
			estado = true;
		}
	}

	/**
	 * Desativa uma pesquisa caso esteja ativada
	 */
	public void encerraPesquisa() {
		if (!ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else {
			estado = false;
		}
	}

	/**
	 * Edita a descricao de uma pesquisa
	 * 
	 * @param descricao nova descricao da pesquisa
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Edita o campo de interesse de uma pesquisa
	 * 
	 * @param campoDeInteresse novo valor do campo de interesse
	 */
	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public boolean associaProblema(String idProblema) {
		Util.validaAtributo(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		if (this.problema.equals(idProblema))
			return false;

		if (!this.problema.isEmpty())
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");

		this.problema = idProblema;
		return true;
	}

	public boolean desassociaProblema() {
		if (this.problema.equals(""))
			return false;
		this.problema = "";
		return true;
	}

	private boolean possuiObjetivo(String idObjetivo) {
		for (String objetivo : objetivos) {
			if (objetivo.equals(idObjetivo)) {
				return true;
			}
		}
		return false;
	}

	public boolean associaObjetivo(String idObjetivo) {
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

		if (possuiObjetivo(idObjetivo))
			return false;

		objetivos.add(idObjetivo);
		return true;
	}

	public boolean possuiProblema() {
		if (!this.problema.equals("")) {
			return true;
		}
		return false;
	}

	public boolean desassociaObjetivo(String idObjetivo) {
		Util.validaAtributo(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

		if (!possuiObjetivo(idObjetivo))
			return false;

		objetivos.remove(idObjetivo);
		return true;
	}

	/**
	 * Retorna a representacao em string de uma pesquisa no formato "CODIGO -
	 * DESCRICAO - CAMPODEINTERESSE"
	 * 
	 * @return representacao em string de uma pesquisa
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - %s", codigo, descricao, campoDeInteresse);
	}

	/**
	 * Gera o hashCode de uma pesquisa
	 * 
	 * @return hashcode de uma pesquisa
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara se uma pesquisa passada a igual a corrente pesquisa
	 * 
	 * @return true se objeto passado a igual a corrente pesquisa e false se o
	 *         contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public boolean associaAtividade(Atividade atividade) {
		if (atividades.contains(atividade)) {
			return false;
		}
		return atividades.add(atividade);
	}

	public boolean desassociaAtividade(Atividade atividade) {
		if (!atividades.contains(atividade)) {
			return false;
		}
		return atividades.remove(atividade);

	}

	public int qtdObjetivos() {
		return this.objetivos.size();
	}

	/**
	 * Armazena pesquisador passada no array de pesquisadores caso nao exista.
	 * 
	 * @param pesquisador Pesquisa a ser armazenada.
	 * @return booleano caso a pesquisa seja associada
	 */
	public boolean associaPesquisador(Pesquisador pesquisador) {
		if (!ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (pesquisadores.contains(pesquisador)) {
			return false;
		} else {
			pesquisadores.add(pesquisador);
			return true;
		}

	}

	/**
	 * Remove pesquisador passado do array de pesquisadores caso nao exista.
	 * 
	 * @param pesquisador Pesquisa a ser removida.
	 * @return booleano caso a pesquisa nao seja desassociada
	 */
	public boolean desassociaPesquisador(Pesquisador pesquisador) {
		if (!ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (!pesquisadores.contains(pesquisador)) {
			return false;
		} else {
			pesquisadores.remove(pesquisador);
			return true;
		}

	}

	/**
	 * Metodo que verfica se uma atividade esta associada a pesquisa
	 * 
	 * @param codigoAtividade codigo da atividade
	 * @return retorna true se estiver associada a essa pesquisa, false caso nao
	 *         esteja
	 */
	public boolean verificaAssosiacaoAtividade(String codigoAtividade) {
		for (Atividade atividade : atividades) {
			if (atividade.getId().equals(codigoAtividade)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para pegar a proxima atividade
	 * 
	 * @param estrategia estrategia que vai ser usada para definir a proxima
	 *                   atividade
	 * @return retorna o codigo da proxima atividade
	 */
	public String proximaAtividade(String estrategia) {
		if (!pesquisaPossuiPendencias()) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
		}
		Atividade atividade = null;
		for (Atividade a : atividades) {
			if (a.getItensPendentes() > 0) {
				atividade = a;
				break;
			}
		}
		switch (estrategia) {
		case "MAIS_ANTIGA":
			for (Atividade tarefa : atividades) {
				if (atividadePossuiItensPendentes(atividade)) {
					return atividade.getId();
				}
			}
		case "MENOS_PENDENCIAS":
			for (int i = 1; i < atividades.size(); i++) {
				if (atividades.get(i).getItensPendentes() < atividade.getItensPendentes()
						&& atividades.get(i).getItensPendentes() != 0) {
					atividade = atividades.get(i);
				}
			}
			return atividade.getId();
		case "MAIOR_RISCO":
			for (int i = 1; i < atividades.size(); i++) {
				if (atividadePossuiItensPendentes(atividades.get(i))) {
					if (atividades.get(i).getNivelRiscoInt() > atividade.getNivelRiscoInt()) {
						atividade = atividades.get(i);
					}
				}
			}
			return atividade.getId();

		case "MAIOR_DURACAO":
			for (int i = 1; i < atividades.size(); i++) {
				if (atividadePossuiItensPendentes(atividades.get(i))) {
					if (atividades.get(i).getDuracao() > atividade.getDuracao()) {
						atividade = atividades.get(i);
					}
				}
			}
			return atividade.getId();
		default:
			throw new IllegalArgumentException("Estrategia nao definida");
		}
	}

	/**
	 * Metodo para checar se uma pesquisa possoui pelo menos uma atividade com algum
	 * item pendente
	 * 
	 * @return retorna true se possuir, false caso nao possua
	 */
	private boolean pesquisaPossuiPendencias() {
		for (Atividade atividade : atividades) {
			if (atividade.getItensPendentes() > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para cehcar se uma atividade possui um item pendente
	 * 
	 * @param atividade atividade que vai ser checada
	 * @return retorna true se a atividade possuir um item pendente, false caso nao
	 *         possua
	 */
	private boolean atividadePossuiItensPendentes(Atividade atividade) {
		if (atividade.getItensPendentes() == 0) {
			return false;
		}
		return true;
	}

	public String getResumo() {

		String resumo = "";

		String obj = "";
		for (String o : objetivos) {
			obj += String.format("- %s\n", o.toString());
		}

		String psq = "";
		for (Pesquisador p : pesquisadores) {
			psq = String.format("- %s\n", p.toString());
		}

		String atv = "";
		for (Atividade a : atividades) {
			atv += String.format("- %s (%s - %s)\n%s", a.getDescricao(), a.getNivelRisco(), a.getDescricaoRisco(),
					a.getItens());
		}

		resumo = String.format(
				"-Pesquisa: %s\n    -Pesquisadores:\n        %s    -Problema: %s\n    -Objetivos: \n        %s    -Atividades:\n        %s",
				toString(), psq, problema, obj, atv);

		return resumo;
	}

	public String getResultado() {

		String resultado = "";
		
		String atvd = "";
		
		for(Atividade a: atividades) {
			atvd+= String.format("        -%s\n%s",a.getDescricao(),a.getResuladoItens());
		}

		resultado = String.format("-Pesquisa: %s\n    -Resultados:\n%s ", toString(),atvd);

		return resultado;
	}
}
