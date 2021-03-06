package psquiza.ordenacao;

import psquiza.entidades.Pesquisa;

/**
 * Caracteriza um criterio de ordenacao de pesquisa.
 * A prioridade para ordenacao e a ordem lexicografica inversa do
 * codigo da pesquisa.
 * 
 * @author Pedro Henrique
 */
public class CriterioPesquisa implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}
}
