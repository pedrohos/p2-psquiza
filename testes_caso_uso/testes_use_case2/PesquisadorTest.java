package testes_use_case2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import psquiza.entidades.Pesquisador;
import org.junit.jupiter.api.Test;

class PesquisadorTest {

	private Pesquisador junio;
	private Pesquisador regina;
	private Pesquisador pedro;
	private Pesquisador lucian;

	@BeforeEach
	void criaPesquisadores() {
		junio = new Pesquisador("Eniedson Junior", "Estudante de Computação", "eniedson.junior@ccc.ufcg",
				"https://foto.junio.com", "Estudante");
		regina = new Pesquisador("Regina Felipe", "Estudante de computacao", "regina.felip3e@ccc.ufcg",
				"https://fotoRegina.com", "Estudante");
		pedro = new Pesquisador("Pedro Henrique", "Estudante também", "pedro.henrique@ccc.ufcg",
				"https://foto.pedro.com", "Estudante");
		lucian = new Pesquisador("Lucian julio", "Outro estudante", "lucian.julio@ccc.ufcg", "http://fotoLucian.com",
				"Estudante");
	}

	@Test
	void testEhAtivo() {
		assertTrue(junio.ehAtivo());
		junio.setAtivo(false);
		assertFalse(junio.ehAtivo());
	}

	@Test
	void testSetAtivo() {
		junio.setAtivo(false);
		regina.setAtivo(false);
		lucian.setAtivo(true);
		pedro.setAtivo(true);

		assertTrue(pedro.ehAtivo());
		assertTrue(lucian.ehAtivo());
		assertFalse(regina.ehAtivo());
		assertFalse(junio.ehAtivo());

	}

	@Test
	void testSetNome() {
		try {
		pedro.setNome(null);
		}catch(IllegalArgumentException e) {}
	}

	@Test
	void testSetBiografia() {
		regina.setBiografia("A estudante mais linda de cc");
		assertEquals(
				"Regina Felipe (Estudante) - A estudante mais linda de cc - regina.felip3e@ccc.ufcg - https://fotoRegina.com",
				regina.toString());
	}

	@Test
	void testSetEmail() {
		try {
			lucian.setEmail("");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testSetFoto() {
		try {
			junio.setFoto("\n  :3");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testSetFuncao() {
		try {
			regina.setFuncao("Faz os melhores testes");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEqualsObject() {
		assertTrue(regina.equals(regina));
		assertFalse(junio.equals(lucian));
	}

	@Test
	void testToString() {
		assertEquals(
				"Eniedson Junior (Estudante) - Estudante de Computação - eniedson.junior@ccc.ufcg - https://foto.junio.com",
				junio.toString());
		assertEquals(
				"Regina Felipe (Estudante) - Estudante de computacao - regina.felip3e@ccc.ufcg - https://fotoRegina.com",
				regina.toString());
		assertEquals("Lucian julio (Estudante) - Outro estudante - lucian.julio@ccc.ufcg - http://fotoLucian.com",
				lucian.toString());
		assertEquals("Pedro Henrique (Estudante) - Estudante também - pedro.henrique@ccc.ufcg - https://foto.pedro.com",
				pedro.toString());
	}

}
