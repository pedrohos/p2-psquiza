package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Pesquisa;

/**
 * Ordenador de pesquisa.
 * Serve como interface para 3 criterios de ordenacao diferentes,
 * sendo eles: Objetivo, Pesquisa e Problema.
 * 
 * @author Pedro Henrique
 */
public interface OrdenaPesquisa extends Comparator<Pesquisa> {
	
	@Override
	public int compare(Pesquisa o1, Pesquisa o2);
}

