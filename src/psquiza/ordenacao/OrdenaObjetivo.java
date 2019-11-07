package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Objetivo;

public class OrdenaObjetivo implements Comparator<Objetivo>{

	@Override
	public int compare(Objetivo o1, Objetivo o2) {
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}

}
