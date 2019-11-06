package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Pesquisa;

public interface OrdenaPesquisa extends Comparator<Pesquisa> {
	
	@Override
	public int compare(Pesquisa o1, Pesquisa o2);
}

