package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Problema;

public class OrdenaProblema implements Comparator<Problema>{

	@Override
	public int compare(Problema o1, Problema o2) {
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}

}
