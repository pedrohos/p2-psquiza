package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Pesquisador;

public class OrdenaPesquisador implements Comparator<Pesquisador>{

	@Override
	public int compare(Pesquisador o1, Pesquisador o2) {
		return o1.getEmail().compareTo(o2.getEmail()) * -1;
	}

}
