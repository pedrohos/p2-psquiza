package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Problema;

/**
 * Ordenador de problema.
 * A ordem e feita em ordem lexicograficamente inversa do codigo do
 * problema.
 * 
 * @author Pedro Henrique
 */
public class OrdenaProblema implements Comparator<Problema>{

	@Override
	public int compare(Problema o1, Problema o2) {
		return o1.getCodigo().compareTo(o2.getCodigo()) * -1;
	}

}
