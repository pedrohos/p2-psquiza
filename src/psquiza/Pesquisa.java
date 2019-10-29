package psquiza;

/**
 * 
 * Representação de uma pesquisa realizada por um pesquisador. Possui uma
 * descricao, até quatro campos de interesse, um código gerado automáticamente
 * pelo sistema e um estado que indica se a pesquisa está ativa (1) ou
 * desativada (-1)
 *
 * @author Regina Letícia Santos Felipe - 119110519
 * 
 */
public class Pesquisa {

	/**
	 * Descricao da pesquisa que será realizada através de um resumo.
	 */
	private String descricao;

	/**
	 * Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados
	 * por vírgula e ter até 255 caracteres.
	 */
	private String campoDeInteresse;
	/**
	 * Código gerado automaticamente pelas primeiras três letras do campo de
	 * interesse mais um valor inteiro começando em 1.
	 */
	private String codigo;
	/**
	 * Status de uma pesquisa, se esta ativa ou desativada. Com o valor (1) para
	 * pesquisa ativada e (-1) para pesquisa desativada
	 */
	private boolean estado;

	/**
	 * Constrói uma pesquisa através da sua descrição, Campo de Interesse e código.
	 * Inicializando o estado da pesquisa como ativo.
	 * 
	 * @param descricao      texto livre sobre a pesquisa realizada
	 * @param campoInteresse áreas para quais a pesquisa engloba
	 * @param codigo         Código gerado automáticamente pelo sistema
	 */
	public Pesquisa(String descricao, String campoInteresse, String codigo) {
		this.descricao = descricao;
		this.campoDeInteresse = campoInteresse;
		this.codigo = codigo;
		this.estado = true;
	}

	/**
	 * Retorna o estado da pesquisa, se ativada ou não
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
	 * Função que retorna a descricao da pesquisa
	 * 
	 * @return descricao da pesquisa
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Função que retorna o campo de Interesse de uma pesquisa
	 * 
	 * @return campo de interesse da pesquisa
	 */
	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	/**
	 * Função que retorna o código gerado de uma pesquisa
	 * 
	 * @return código de uma pesquisa
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna um boolean se pesquisa é ou não ativa
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

	/**
	 * Retorna a representação em string de uma pesquisa no formato "CODIGO -
	 * DESCRICAO - CAMṔODEINTERESSE"
	 * 
	 * @return representação em string de uma pesquisa
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
		result = prime * result + ((campoDeInteresse == null) ? 0 : campoDeInteresse.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	/**
	 * Compara se uma pesquisa passada é igual a corrente pesquisa
	 * 
	 * @return true se objeto passado é igual a corrente pesquisa e false se o
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

}
