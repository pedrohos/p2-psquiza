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
		sistema.cadastraPesquisa("Super computadores resolvendo problemas humanos.", "computadores, Inteligencia");
		sistema.cadastraPesquisa("Outra pesquisa sobre super computadores resolvendo problemas humanos.", "humanos");
		sistema.encerraPesquisa("HUM1", "Nao sei");
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
		assertEquals(
				"BIO1 - Pesquisando os animais terrestres - biobiobiobiobiobiobbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
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
	void testValidacaoInteresseVazioInicio() {
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
	void testAlteraPesquisaDescricao() {
		sistema.alteraPesquisa("COM1", "DESCRICAO", "Uma pesquisa diferenciada.");
		assertEquals("COM1 - Uma pesquisa diferenciada. - computadores, Inteligencia", sistema.exibePesquisa("COM1"));
	}

	@Test
	void testAlteraPesquisaCamposInteresses() {
		sistema.alteraPesquisa("COM1", "CAMPO", "biologia");
		assertEquals("COM1 - Super computadores resolvendo problemas humanos. - biologia",
				sistema.exibePesquisa("COM1"));
	}

	@Test
	void testAlteraPesquisaEncerrada() {
		try {
			sistema.alteraPesquisa("HUM1", "CAMPO", "biologia");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaCodigoNulo() {
		try {
			sistema.alteraPesquisa(null, "CAMPO", "biologia");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaCodigoVazio() {
		try {
			sistema.alteraPesquisa("", "CAMPO", "biologia");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaConteudoNulo() {
		try {
			sistema.alteraPesquisa("COM1", null, "biologia");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaConteudoVazio() {
		try {
			sistema.alteraPesquisa("COM1", "", "biologia");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaNovoValorNulo() {
		try {
			sistema.alteraPesquisa("COM1", "descricao", null);
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaNovoValorVazio() {
		try {
			sistema.alteraPesquisa("COM1", "descricao", "");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaCamposInteressesInvalidos() {
		try {
			sistema.alteraPesquisa("COM1", "CAMPO", "biologia, , ");
			fail("Deveria ser lançada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAlteraPesquisaInexistente() {
		try {
			sistema.alteraPesquisa("ALT1", "CAMPO", "biologia");
			fail("Deveria ser lançada uma excessao");
		} catch (NullPointerException e) {
		}
	}

	@Test
	void testEncerraPesquisaJaEncerrada() {
		try {
			sistema.encerraPesquisa("HUM1", "Nao sei");
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEncerraPesquisaCodigoNulo() {
		try {
			sistema.encerraPesquisa(null, "Nao sei");
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEncerraPesquisaCodigoVazio() {
		try {
			sistema.encerraPesquisa("", "Nao sei");
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEncerraPesquisaMotivoNulo() {
		try {
			sistema.encerraPesquisa("COM1", null);
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEncerraPesquisaMotivoVazio() {
		try {
			sistema.encerraPesquisa("COM1", "");
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEncerraPesquisaInexistente() {
		try {
			sistema.encerraPesquisa("ALT1", "Oi");
			fail("Deveria ser lancada uma excessao");
		} catch (NullPointerException e) {
		}
	}

	@Test
	void testAtivaPesquisa() {
		sistema.ativaPesquisa("HUM1");
		sistema.exibePesquisa("HUM1");
	}

	@Test
	void testAtivaPesquisaJaAtiva() {
		try {
			sistema.ativaPesquisa("COM1");
			fail("Uma excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAtivaPesquisaCodigoNulo() {
		try {
			sistema.ativaPesquisa(null);
			fail("Uma excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAtivaPesquisaCodigoVazio() {
		try {
			sistema.ativaPesquisa("");
			fail("Uma excessao deveria ser lancada");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testAtivaPesquisaInexistente() {
		try {
			sistema.ativaPesquisa("ALT1");
			fail("Uma excessao deveria ser lancada");
		} catch (NullPointerException e) {
		}
	}

	@Test
	void testExibePesquisa() {
		assertEquals("COM1 - Super computadores resolvendo problemas humanos. - computadores, Inteligencia",
				sistema.exibePesquisa("COM1"));
	}

	@Test
	void testExibePesquisaInexistente() {
		try {
			sistema.exibePesquisa("ALT1");
			fail("Deveria ser lancada uma excessao");
		} catch (NullPointerException e) {
		}
	}

	@Test
	void testExibePesquisaCodigoNulo() {
		try {
			sistema.exibePesquisa(null);
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testExibePesquisaCodigoVazio() {
		try {
			sistema.exibePesquisa("");
			fail("Deveria ser lancada uma excessao");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	void testEhAtiva() {
		assertEquals(true, sistema.pesquisaEhAtiva("COM1"));
	}

	@Test
	void testEhAtivaFalse() {
		assertEquals(false, sistema.pesquisaEhAtiva("HUM1"));
	}

}
