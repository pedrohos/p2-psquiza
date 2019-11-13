package teste_use_case6;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import psquiza.controladores.ControladorPesquisa;
import psquiza.controladores.ControladorPesquisador;
import psquiza.entidades.Pesquisa;

public class TestControladorPesquisador {

	private ControladorPesquisador cPesquisador = new ControladorPesquisador();
	private ControladorPesquisa cPesquisa = new ControladorPesquisa();

	@Test
	public void associaPesquisador() {
		cPesquisador.cadastraPesquisador("Lucian", "chorar", "nao tenho", "theoa@2016", "https://notblind");
		cPesquisa.cadastraPesquisa("pesquisa teste", "computacao");
		Pesquisa p = cPesquisa.getPesquisa("COM1");
		assertTrue(cPesquisador.associaPesquisador(p, "theoa@2016"));
	}

	@Test
	public void associaPesquisadorEmailInvalido() {
		cPesquisador.cadastraPesquisador("Lucian", "chorar", "nao tenho", "theoa@2016", "https://notblind");
		cPesquisa.cadastraPesquisa("pesquisa teste", "computacao");
		Pesquisa p = cPesquisa.getPesquisa("COM1");
		try {
			cPesquisador.associaPesquisador(p, "theoa@");
			fail();
		} catch (IllegalArgumentException e) {

		}

	}
	
	@Test
	public void desassociaPesquisador() {
		cPesquisador.cadastraPesquisador("Lucian", "chorar", "nao tenho", "theoa@2016", "https://notblind");
		cPesquisa.cadastraPesquisa("pesquisa teste", "computacao");
		Pesquisa p = cPesquisa.getPesquisa("COM1");
		cPesquisador.associaPesquisador(p, "theoa@2016");
		assertTrue(cPesquisador.desassociaPesquisador(p, "theoa@2016"));
	}
	
	@Test
	public void desassociaPesquisadorEmailInvalido() {
		cPesquisador.cadastraPesquisador("Lucian", "chorar", "nao tenho", "theoa@2016", "https://notblind");
		cPesquisa.cadastraPesquisa("pesquisa teste", "computacao");
		Pesquisa p = cPesquisa.getPesquisa("COM1");
		cPesquisador.associaPesquisador(p, "theoa@2016");
		try {
			cPesquisador.desassociaPesquisador(p, "theo");
			fail();
		} catch (IllegalArgumentException e) {

		}
	}
	
	
}
