package psquiza.entidades;

import java.io.Serializable;

import psquiza.Util;
import psquiza.enums.Tipo;

/**
 * Classe para representar um objetivo
 * 
 * @author Lucian Julio
 *
 */
public class Objetivo implements Serializable, Comparable<Objetivo> {

	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que indica a viabilidade do um objetivo
	 */
	private int viabilidade;
	/**
	 * Atributo que inddica a aderencia de um objetivo
	 */
	private int aderencia;
	/**
	 * Atribito que representa a descricao de um objetivo
	 */
	private String descricao;
	/**
	 * Atributo que representa o tipo do objetivo
	 */
	private Tipo tipo;
	/**
	 * Atributo que representa o codigo de um objetivo
	 */
	private String codigo;

	private String idPesquisa;

	/**
	 * Metodo que constroi um objetivo a partir de um tipo, descricao, aderencia e
	 * viabilidade.
	 * 
	 * Caso o tipo do Objetivo seja vazio ou nulo, sera lancada um
	 * IllegalArgumentException: "Campo tipo nao pode ser nulo ou vazio." Caso a
	 * descricao do Objetivo seja vazia ou nula, sera lancada um
	 * IllegalArgumentException: "Campo descricao nao pode ser nulo ou vazio." Caso
	 * a aderencia do Objetivo seja invalida, sera lancada um
	 * IllegalArgumentException: "Campo aderencia nao pode ser nulo ou vazio." Caso
	 * a viabilidade do Objetivo seja invalida, sera lancada um
	 * IllegalArgumentException: "Campo viabilidade nao pode ser nulo ou vazio."
	 * Caso a viabilidade do Objetivo nao esteja no intervalo de 1 a 5, sera lancada
	 * um IllegalArgumentException: "Valor invalido de aderencia" Caso a aderencia
	 * do Objetivo nao esteja no intervalo de 1 a 5, sera lancada um
	 * IllegalArgumentException: "Valor invalido de viabilidade." Caso o codigo do
	 * Objetivo seja vazio ou nulo, sera lancada um IllegalArgumentException: "Campo
	 * codigo nao pode ser nulo ou vazio."
	 * 
	 * @param tipo        tipo do objetivo
	 * @param descricao   descricao do objetivo
	 * @param aderencia   aderencia do objetivo
	 * @param viabilidade viabilidade do objetivo
	 * @param codigo      codigo do objetivo
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
		Util.validaAtributo(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Util.validaAtributo(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Util.validaAtributo(String.valueOf(aderencia), "Campo aderencia nao pode ser nulo ou vazio.");
		Util.validaAtributo(String.valueOf(viabilidade), "Campo viabilidade nao pode ser nulo ou vazio.");
		Util.validarLimite(aderencia, 1, 5, "Valor invalido de aderencia");
		Util.validarLimite(viabilidade, 1, 5, "Valor invalido de viabilidade.");
		Util.validaAtributo(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		this.tipo = Tipo.atribuiTipo(tipo);
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
		this.idPesquisa = "";
	}

	public boolean associaPesquisa(String idPesquisa) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		if (this.idPesquisa.equals(idPesquisa))
			return false;
		if (!this.idPesquisa.isEmpty())
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");

		this.idPesquisa = idPesquisa;
		return true;
	}

	public boolean desassociaPesquisa(String idPesquisa) {
		Util.validaAtributo(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		if (!this.idPesquisa.equals(idPesquisa))
			return false;

		this.idPesquisa = "";
		return true;
	}

	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Funcao que retorna a descricao da pesquisa.
	 * 
	 * @return descricao da pesquisa.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo para gerar a representacao de um objetivo
	 */
	@Override
	public String toString() {
		return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + (this.aderencia + this.viabilidade);
	}

	/**
	 * Método que compara dois objetivos e retorna um inteiro que representa a ordem
	 * de precedencia entre eles
	 * 
	 * @return inteiro que representa a ordem de precedencia entre dois inteiros.
	 */
	@Override
	public int compareTo(Objetivo o) {
		if (this.codigo == null) {
			return -1;
		}
		if (o.codigo == null) {
			return -1;
		}
		if (this.codigo.equals(o.codigo)) {
			return 0;
		} else {
			return this.codigo.compareTo(o.codigo);
		}
	}

<<<<<<< HEAD
	/**
	 * Método que retorna o id de uma pesquisa
	 * 
	 * @return codigo da pesquisa
	 */
=======
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aderencia;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idPesquisa == null) ? 0 : idPesquisa.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + viabilidade;
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
		Objetivo other = (Objetivo) obj;
		if (aderencia != other.aderencia)
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idPesquisa == null) {
			if (other.idPesquisa != null)
				return false;
		} else if (!idPesquisa.equals(other.idPesquisa))
			return false;
		if (tipo != other.tipo)
			return false;
		if (viabilidade != other.viabilidade)
			return false;
		return true;
	}
	
>>>>>>> 01a4a23a19565980b8f1802ac8bcae3e0cfaadd1
	public String getIdPesquisa() {
		return idPesquisa;
	}

}
