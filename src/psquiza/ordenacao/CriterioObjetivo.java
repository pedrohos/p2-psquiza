package psquiza.ordenacao;

import psquiza.entidades.Pesquisa;

/**
 * Caracteriza um criterio de ordenacao de pesquisa pelos objetivos.
 * A prioridade para ordenacao e:
 * 1. Quem possuir a maior quantidades de objetivo.
 * 2. Ordem lexicografica inversa do codigo da pesquisa.
 * 
 * @author Pedro Henrique
 */
public class CriterioObjetivo implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		if (o1.qtdObjetivos() > o2.qtdObjetivos()) {
			return -1;
		}
		
		if (o1.qtdObjetivos() < o2.qtdObjetivos()) {
			return 1;
		}
		
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}
}
