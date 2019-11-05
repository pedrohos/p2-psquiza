package psquiza.ordenacao;

import psquiza.entidades.Pesquisa;

public class CriterioProblema implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		int codigo1 = Integer.parseInt(o1.getCodigo().substring(1));
		int codigo2 = Integer.parseInt(o2.getCodigo().substring(1));
		
		if (codigo1 < codigo2) {
			return -1;
		}
		
		if (codigo1 > codigo2) {
			return 1;
		}
		
		return 0;
	}

}
