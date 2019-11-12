package psquiza.entidades;

/**
 * 
 * Interface que determina o comportamento de uma especialidade que um
 * pesquisador pode assumir.
 * 
 * @author Regina Leticia Santos Felipe
 *
 */
public interface Especialidade {

	/**
	 * Representacao em String da especialidade.
	 * 
	 * @return string que representa a especialidade.
	 */
	public String toString();

	/**
	 * Modifica o atributo da Especialidade de acordo com o parametro atributo e o
	 * novo valor.
	 * 
	 * @param atributo Atributo da especialidade a ser alterado.
	 * @param novo     Novo valor que vai ficar no atributo.
	 */
	public void setAtributo(String atributo, String novo);

}
