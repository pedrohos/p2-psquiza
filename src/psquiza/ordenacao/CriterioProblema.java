package psquiza.ordenacao;

import psquiza.entidades.Pesquisa;

public class CriterioProblema implements OrdenaPesquisa {

	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		if (o1.possuiProblema() == true && o2.possuiProblema() == false) {
			return -1;
		}
		
		if (o1.possuiProblema() == false && o2.possuiProblema() == true) {
			return 1;
		}
		
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}

}
