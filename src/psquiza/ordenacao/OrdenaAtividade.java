package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Atividade;

/**
 * Ordenador de atividade.
 * A ordem e feita em ordem lexicograficamente inversa do id da
 * atividade.
 * 
 * @author Pedro Henrique
 */
public class OrdenaAtividade implements Comparator<Atividade>{

	@Override
	public int compare(Atividade o1, Atividade o2) {
		return o1.getId().compareTo(o2.getId()) * -1;
	}

}
