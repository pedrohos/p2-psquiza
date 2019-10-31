package testes_use_case1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.Sistema;

class ControladorPesquisaTest {

	private Sistema sistema;

	@BeforeEach
	void preparaSistema() {
		sistema = new Sistema();
		sistema.cadastraPesquisa("Super computadores resolvendo problemas humanos.", "computadores, IA");
	}

	@Test
	void testCadastraPesquisa() {
		sistema.cadastraPesquisa("Pesquisando os animais terrestres", "biologia, animais");
		assertEquals("BIO1 - Pesquisando os animais terrestres - biologia, animais", sistema.exibePesquisa("BIO1"));
	}
	
	@Test
	void testCadastraPesquisaMesmosInteresses() {
		sistema.cadastraPesquisa("computadores legais", "computador");
		assertEquals("COM2 - computadores legais - computador", sistema.exibePesquisa("COM2"));
	}

	@Test
	void testValidacaoUmInteresse() {
		sistema.cadastraPesquisa("Pesquisando os animais terrestres", "biologia");
		assertEquals("BIO1 - Pesquisando os animais terrestres - biologia", sistema.exibePesquisa("BIO1"));
	}

	@Test
	void testValidacaoQuatroInteresse() {
		sistema.cadastraPesquisa("Pesquisando os animais terrestres", "biologia, ciencia, animais, alimentacao");
		assertEquals("BIO1 - Pesquisando os animais terrestres - biologia, ciencia, animais, alimentacao",
				sistema.exibePesquisa("BIO1"));
	}

	@Test
	void testValidacaoInteresseTresCaracteres() {
		sistema.cadastraPesquisa("Pesquisando os animais terrestres", "bio, ciencia, animais, alimentacao");
		assertEquals("BIO1 - Pesquisando os animais terrestres - bio, ciencia, animais, alimentacao",
				sistema.exibePesquisa("BIO1"));
	}

	@Test
	void testValidacaoCamposDeInteressesGrandeLimite() {
		sistema.cadastraPesquisa("Pesquisando os animais terrestres",
				"biobiobiobiobiobiobbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		assertEquals("BIO1 - Pesquisando os animais terrestres - biologia, ciencia, animais, alimentacao",
				sistema.exibePesquisa("BIO1"));
	}

	@Test
	void testCadastraPesquisaDescricaoVazia() {
		try {
			sistema.cadastraPesquisa("", "biologia");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisaDescricaoNula() {
		try {
			sistema.cadastraPesquisa(null, "biologia");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisaCamposInteresseVazios() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres", "");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testCadastraPesquisaCamposInteresseNulo() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres", null);
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) { 
		}
	}

	@Test
	void testCValidacaoInteresseVazioInicio() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres", " ,biologia");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testValidacaoInteresseVazioMeio() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres", "biologia, , animais");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testValidacaoInteresseVazioFim() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres", "biologia,  ");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testValidacaoInteressePequeno() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres", "biologia, ab");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testValidacaoInteressesGrande() {
		try {
			sistema.cadastraPesquisa("Pesquisando os animais terrestres",
					"biobiobiobiobiobiobbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
			fail("Deve ser lancada uma excessao.");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisa() {
		sistema.alteraPesquisa("COM1", "descricao", "Uma pesquisa diferenciada.");
	}

	@Test
	void testEncerraPesquisa() {
		fail("Not yet implemented");
	}

	@Test
	void testAtivaPesquisa() {
		fail("Not yet implemented");
	}

	@Test
	void testExibePesquisa() {
		fail("Not yet implemented");
	}

	@Test
	void testEhAtiva() {
		fail("Not yet implemented");
	}

}
