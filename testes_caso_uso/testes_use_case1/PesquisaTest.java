package testes_use_case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.entidades.Pesquisa;

class PesquisaTest {

	private Pesquisa p1;
	private Pesquisa p2;
	private Pesquisa p3;
	
	@BeforeEach
	void criaPesquisas() {
		p1 = new Pesquisa("Descricao", "campo", "CAM1");
		p2 = new Pesquisa("Descricao2", "campo2", "CAM1");
		p3 = new Pesquisa("Descricao3", "campo3", "CAM2");
		p3.encerraPesquisa();
	}
	
	@Test
	void testHashCodeIguais() {
		assertEquals(p1.hashCode(), p2.hashCode());
	}
	
	@Test
	void testHashCodeDiferentes() {
		assertNotEquals(p1.hashCode(), p3.hashCode());
	}
	
	@Test
	void testEhAtiva() {
		assertEquals(true, p1.ehAtiva());
	}
	
	@Test
	void testNaoEhAtiva() {
		assertEquals(false, p3.ehAtiva());
	}

	@Test
	void testAtivaPesquisa() {
		p3.ativaPesquisa();
		assertEquals(true, p3.ehAtiva());
	}

	@Test
	void testEncerraPesquisa() {
		p2.encerraPesquisa();
		assertEquals(false, p2.ehAtiva());
	}

	@Test
	void testToString() {
		assertEquals("CAM1 - Descricao - campo", p1.toString());
	}

	@Test
	void testEqualsObject() {
		assertEquals(p1, p2);
	}
	
	@Test
	void testNotEqualsObject() {
		assertNotEquals(p1, p3);
	}

}
