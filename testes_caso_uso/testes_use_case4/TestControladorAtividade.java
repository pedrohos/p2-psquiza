package testes_use_case4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import psquiza.controladores.ControladorAtividade;

public class TestControladorAtividade {

	@Test
	public void TestCadastraAtividadeRiscoBaixo() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
	}

	@Test
	public void TestCadastraAtividadeRiscoMedio() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "MEDIO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
	}

	@Test
	public void TestCadastraAtividadeRiscoAlto() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "ALTO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
	}

	@Test
	public void TestCadastraAtividadeDescricaoNull() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("", "BAIXO", "mais ou menos");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void TestCadastraAtividadeRiscoNull() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("bom", "", "mais ou menos");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void TestCadastraAtividadeRiscoDescricaoRisco() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("bom", "BAIXO", "");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void TestCadastraAtividadeRiscoDesconhecido() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("bom", "MUITOALTO", "desas");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void TestApagaAtividade() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("bom", "BAIXO", "desas");
		c.apagaAtividade("A1");
	}

	@Test
	public void TestApagaAtividadeNaoCadastrada() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("bom", "BAIXO", "desas");
			c.apagaAtividade("A2");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void cadastraItem() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("bom", "BAIXO", "desas");
		c.cadastraItem("A1", "Monitoramento facebook/messenger");
	}

	@Test
	public void cadastraItemIdNull() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("bom", "BAIXO", "desas");
			c.cadastraItem("", "Monitoramento facebook/messenger");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void cadastraItemVazio() {
		ControladorAtividade c = new ControladorAtividade();
		try {
			c.cadastraAtividade("bom", "BAIXO", "desas");
			c.cadastraItem("A1", "");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void exibeAtividade() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		assertEquals(
				"Monitoramento de chats dos alunos de computacao do primeiro periodo. (BAIXO - Por se tratar de apenas um monitoramento, o risco nao e elevado.)",
				c.exibeAtividade("A1"));
	}

	@Test
	public void exibeAtividadeIdVazio() {
		try {
			ControladorAtividade c = new ControladorAtividade();
			c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
					"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
			c.exibeAtividade("");
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void contaItensPendentes() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		c.cadastraItem("A1", "tijolo");
		c.cadastraItem("A1", "Bloco de pedra");
		c.cadastraItem("A1", "reator nuclear");
		c.cadastraItem("A1", "frigideira");
		assertEquals(4, c.contaItensPendentes("A1"));
	}

	@Test
	public void contaItensPendentes2() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		c.cadastraItem("A1", "tijolo");
		c.cadastraItem("A1", "Bloco de pedra");
		c.cadastraItem("A1", "reator nuclear");
		c.cadastraItem("A1", "frigideira");
		assertEquals(4, c.contaItensPendentes("A1"));
		c.cadastraItem("A1", "cerveja");
		c.cadastraItem("A1", "carne");
		assertEquals(6, c.contaItensPendentes("A1"));
	}

	@Test
	public void ContaItensPendentesIdVazio() {
		try {
			ControladorAtividade c = new ControladorAtividade();
			c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
					"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
			c.cadastraItem("A1", "tijolo");
			c.cadastraItem("A1", "Bloco de pedra");
			c.cadastraItem("A1", "reator nuclear");
			c.cadastraItem("A1", "frigideira");
			assertEquals(4, c.contaItensPendentes("A1"));
			c.cadastraItem("A1", "cerveja");
			c.cadastraItem("A1", "carne");
			assertEquals(6, c.contaItensPendentes(""));
			fail("");
		} catch (Exception e) {
		}
	}

	@Test
	public void contaItensRealizados() {
		ControladorAtividade c = new ControladorAtividade();
		c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
				"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
		c.cadastraItem("A1", "tijolo");
		c.cadastraItem("A1", "Bloco de pedra");
		c.cadastraItem("A1", "reator nuclear");
		c.cadastraItem("A1", "frigideira");
		assertEquals(4, c.contaItensPendentes("A1"));
		c.cadastraItem("A1", "cerveja");
		c.cadastraItem("A1", "carne");
		assertEquals(0, c.contaItensRealizados("A1"));
	}

	@Test
	public void ContaItensRealizadosIdVazio() {
		try {
			ControladorAtividade c = new ControladorAtividade();
			c.cadastraAtividade("Monitoramento de chats dos alunos de computacao do primeiro periodo.", "BAIXO",
					"Por se tratar de apenas um monitoramento, o risco nao e elevado.");
			c.cadastraItem("A1", "tijolo");
			c.cadastraItem("A1", "Bloco de pedra");
			c.cadastraItem("A1", "reator nuclear");
			c.cadastraItem("A1", "frigideira");
			assertEquals(4, c.contaItensPendentes("A1"));
			c.cadastraItem("A1", "cerveja");
			c.cadastraItem("A1", "carne");
			assertEquals(0, c.contaItensRealizados(""));
			fail("");
		} catch (Exception e) {
		}
	}

}
