package psquiza.ordenacao;

import java.util.Comparator;

import psquiza.entidades.Atividade;

public class OrdenaAtividade implements Comparator<Atividade>{

	@Override
	public int compare(Atividade o1, Atividade o2) {
		return o1.getId().compareTo(o2.getId()) * -1;
	}

}
