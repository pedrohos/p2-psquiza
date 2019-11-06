package psquiza.ordenacao;

import psquiza.entidades.Pesquisa;

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
