package testes_use_case2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorPesquisador;

class ControladorPesquisadorTest {

	private ControladorPesquisador controle;

	@BeforeEach
	void criaContorle() {
		controle = new ControladorPesquisador();
	}

	@Test
	void testCadastraPesquisador() {
		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");
	}

	@Test
	void testCadastraPesquisadorAtributoVazio() {
		try {
			controle.cadastraPesquisador("", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
					"https://foto.lucian");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisadorAtributoNulo() {
		try {
			controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", null, "https://foto.lucian");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisadorEmailInvalido() {
		try {
			controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "@.com",
					"https://foto.lucian");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisadorFotoInvalida() {
		try {
			controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian@julo.com",
					"foto.lucian");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisadorFuncaoInvalida() {
		try {
			controle.cadastraPesquisador("Lucian", "vagabundo", "Estudante de computacao", "lucian@julo.com",
					"http://foto.lucian");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisador() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		assertEquals("Lucian (estudante) - Estudante de computacao - lucian.costa@ccc.ufcg - https://foto.lucian",
				controle.exibePesquisador("lucian.costa@ccc.ufcg"));

		controle.alteraPesquisador("lucian.costa@ccc.ufcg", "EMAIL", "juninho@eniedson.com");

		assertEquals("Lucian (estudante) - Estudante de computacao - juninho@eniedson.com - https://foto.lucian",
				controle.exibePesquisador("juninho@eniedson.com"));

	}

	@Test
	void testAlteraPesquisadorArgumentoVazio() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		try {

			controle.alteraPesquisador("lucian.costa@ccc.ufcg", "EMAIL", "");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	void testAlteraPesquisadorInativo() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		controle.desativaPesquisador("lucian.costa@ccc.ufcg");

		try {

			controle.alteraPesquisador("lucian.costa@ccc.ufcg", "EMAIL", "junin@eniedson");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	void testAlteraPesquisadorArgumentoNulo() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		try {

			controle.alteraPesquisador("lucian.costa@ccc.ufcg", "EMAIL", null);
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	void testAlteraPesquisadorAtributoInvalido() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		try {

			controle.alteraPesquisador("lucian.costa@ccc.ufcg", "email", "lucian.costa@ccc.ufcg");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	void testAlteraPesquisadorNovoAtributoInvalido() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		try {

			controle.alteraPesquisador("lucian.costa@ccc.ufcg", "EMAIL", "emaillll.com");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {
		}

	}

	@Test
	void testAlteraPesquisadorInexistente() {
		try {

			controle.alteraPesquisador("lucian.costa@ccc.ufcg", "email", "emaillll@.com");
			fail("Excessao deve ser lancada");
		} catch (NoSuchElementException e) {
		}

	}

	@Test
	void testDesativaPesquisador() {
		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");
		controle.desativaPesquisador("lucian.costa@ccc.ufcg");
		assertFalse(controle.pesquisadorEhAtivo("lucian.costa@ccc.ufcg"));
	}

	@Test
	void testDesativaPesquisadorDesativado() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");
		controle.desativaPesquisador("lucian.costa@ccc.ufcg");
		assertFalse(controle.pesquisadorEhAtivo("lucian.costa@ccc.ufcg"));

		try {
			controle.desativaPesquisador("lucian.costa@ccc.ufcg");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	void testAtivaPesquisador() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		controle.desativaPesquisador("lucian.costa@ccc.ufcg");

		controle.ativaPesquisador("lucian.costa@ccc.ufcg");

		assertTrue(controle.pesquisadorEhAtivo("lucian.costa@ccc.ufcg"));

	}

	@Test
	void testAtivaPesquisadorAtivo() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		try {

			controle.ativaPesquisador("lucian.costa@ccc.ufcg");
			fail("Excessao deve ser lancada");
		} catch (IllegalArgumentException e) {

		}

	}

	@Test
	void testAtivaPesquisadorInexistente() {

		try {

			controle.ativaPesquisador("lucian.costa@ccc.ufcg");
			fail("Excessao deve ser lancada");
		} catch (NoSuchElementException e) {

		}

	}

	@Test
	void testExibePesquisador() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		assertEquals("Lucian (estudante) - Estudante de computacao - lucian.costa@ccc.ufcg - https://foto.lucian",
				controle.exibePesquisador("lucian.costa@ccc.ufcg"));

	}

	@Test
	void testExibePesquisadorInexistente() {
		try {
			assertEquals("Lucian (estudante) - Estudante de computacao - lucian.costa@ccc.ufcg - https://foto.lucian",
					controle.exibePesquisador("lucian.costa@ccc.ufcg"));
			fail("Excessao deve ser lancada");
		} catch (NoSuchElementException e) {
		}

	}

	@Test
	void testPesquisadorEhAtivo() {

		controle.cadastraPesquisador("Lucian", "estudante", "Estudante de computacao", "lucian.costa@ccc.ufcg",
				"https://foto.lucian");

		assertTrue(controle.pesquisadorEhAtivo("lucian.costa@ccc.ufcg"));

		controle.desativaPesquisador("lucian.costa@ccc.ufcg");

		assertFalse(controle.pesquisadorEhAtivo("lucian.costa@ccc.ufcg"));

	}

}
