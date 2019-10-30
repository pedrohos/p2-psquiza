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
		junio = new Pesquisador("Eniedson Junior", "Estudante de Computação", "eniedson.junior@ccc.ufcg", "https://foto.junio.com", "Estudante");
		regina = new Pesquisador("Regina Felipe", "Estudante de computacao", "regina.felip3e@ccc.ufcg", "https://fotoRegina.com", "Estudante");
		pedro = new Pesquisador("Pedro Henrique", "Estudante também", "pedro.henrique@ccc.ufcg", "https://foto.pedro.com", "Estudante");
		lucian = new Pesquisador("Lucian julio", "Outro estudante", "lucian.julio@ccc.ufcg", "http://fotoLucian.com", "Estudante");
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
		pedro.setNome(null);
		assertEquals("null (estudante) - Estudante também - pedro.henrique@ccc.ufcg - https://foto.pedro.com", pedro.toString());
	}

	@Test
	void testSetBiografia() {
		regina.setBiografia("A estudante mais linda de cc");
		assertEquals("Regina Felipe (estudante) - A estudante mais linda de cc - regina.felip3e@ccc.ufcg - https://fotoRegina.com", regina.toString());
	}

	@Test
	void testSetEmail() {
		lucian.setEmail("");
		assertEquals("Lucian julio (estudante) - Outro estudante -  - http://fotoLucian.com",lucian.toString());
	}

	@Test
	void testSetFoto() {
		junio.setFoto("\n  :3");
		assertEquals("Eniedson Junior (estudante) - Estudante de Computação - eniedson.junior@ccc.ufcg - \n  :3", junio.toString());
	}

	@Test
	void testSetFuncao() {
		regina.setFuncao("Faz os melhores testes");
		assertEquals("Regina Felipe (Faz os melhores testes) - Estudante de computacao - regina.felip3e@ccc.ufcg - https://fotoRegina.com", regina.toString());
	}

	@Test
	void testEqualsObject() {
		assertTrue(regina.equals(regina));
		assertFalse(junio.equals(lucian));
	}

	@Test
	void testToString() {
		assertEquals("Eniedson Junior (estudante) - Estudante de Computação - eniedson.junior@ccc.ufcg - https://foto.junio.com", junio.toString());
		assertEquals("Regina Felipe (estudante) - Estudante de computacao - regina.felip3e@ccc.ufcg - https://fotoRegina.com", regina.toString());
		assertEquals("Lucian julio (estudante) - Outro estudante - lucian.julio@ccc.ufcg - http://fotoLucian.com",lucian.toString());
		assertEquals("Pedro Henrique (estudante) - Estudante também - pedro.henrique@ccc.ufcg - https://foto.pedro.com", pedro.toString());
	}

}
