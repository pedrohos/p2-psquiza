package psquiza.ordenacao;

import psquiza.entidades.Pesquisa;

public class CriterioPesquisa implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}
}
