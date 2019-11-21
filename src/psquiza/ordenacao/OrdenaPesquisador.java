package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Pesquisador;

/**
 * Ordenador de pesquisador.
 * A ordem e feita em ordem lexicograficamente inversa do email do
 * pesquisador.
 * 
 * @author Pedro Henrique
 */
public class OrdenaPesquisador implements Comparator<Pesquisador>{

	@Override
	public int compare(Pesquisador o1, Pesquisador o2) {
		return o1.getEmail().compareTo(o2.getEmail()) * -1;
	}

}
