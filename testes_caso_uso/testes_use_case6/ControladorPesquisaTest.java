package testes_use_case6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import psquiza.controladores.Sistema;

public class ControladorPesquisaTest {
	Sistema sistema = new Sistema();

	@Test
	public void associaPesquisadorTest() {
		sistema.cadastraPesquisador("killua zoldyck", "estudante",
				"Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
				"hunterxhunter@1998", "https://godspeed");
		sistema.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
				"computacao, homofobia");
		sistema.associaPesquisador("COM1", "hunterxhunter@1998");
	}

	@Test
	public void associaPesquisadorTest1() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraPesquisa("Qual o melhor cavaleiro dos zodiaco", "cdz, cavaleiro");
		sistema.associaPesquisador("CDZ1", "cdz@1998");
	}

	@Test
	public void associaPesquisadorNaoCadastrado() {
		sistema.cadastraPesquisa("Qual o melhor cavaleiro dos zodiaco", "cdz, cavaleiro");
		try {
			sistema.associaPesquisador("CDZ1", "cdz@1998");
			fail("Deve ser lancada uma excessao.");
		} catch (Exception e) {
		}
	}

	@Test
	public void associaPesquisadorComPesquisaNaoCadastrada() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.associaPesquisador("CDZ1", "cdz@1998");
			fail("Deve ser lancada uma excessao.");
		} catch (Exception e) {

		}
	}

	@Test
	public void desassociaPesquisadorTest() {
		sistema.cadastraPesquisador("killua zoldyck", "estudante",
				"Interessado em eletricidade, o terceiro de cinco filhos da famosa familia Zaoldyeck.",
				"hunterxhunter@1998", "https://godspeed");
		sistema.cadastraPesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
				"computacao, homofobia");
		sistema.associaPesquisador("COM1", "hunterxhunter@1998");
		sistema.desassociaPesquisador("COM1", "hunterxhunter@1998");
	}

	@Test
	public void desassociaPesquisadorTest1() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraPesquisa("Qual o melhor cavaleiro dos zodiaco", "cdz, cavaleiro");
		sistema.associaPesquisador("CDZ1", "cdz@1998");
		sistema.desassociaPesquisador("CDZ1", "cdz@1998");
	}

	@Test
	public void desassociaPesquisadorNaoCadastrado() {
		sistema.cadastraPesquisa("Qual o melhor cavaleiro dos zodiaco", "cdz, cavaleiro");
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.associaPesquisador("CDZ1", "cdz@1998");
		try {
			sistema.desassociaPesquisador("CDZ1", "cd@1998");
			fail("Deve ser lançada uma excessao.");
		} catch (Exception e) {

		}
	}

	@Test
	public void desassociaPesquisadorComPesquisaNaoCadastrada() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraPesquisa("Qual o melhor cavaleiro dos zodiaco", "cdz, cavaleiro");
		sistema.associaPesquisador("CDZ1", "cdz@1998");
		try {
			sistema.desassociaPesquisador("CDZ2", "cdz@1998");
			fail("Deve ser lancada uma excessao.");
		} catch (Exception e) {

		}
	}

	@Test
	public void cadastraEspecialidadeProfessorTest() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
	}
	
	@Test
	public void cadastraEspecialidadeProfessorEmailVazio() {
		sistema.cadastraPesquisador("Seya","professor","Interesse na athena","cdz@1998","https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeProfessor("", "computacao", "ufcg", "07/10/1999");
			fail("Deve ser lançada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void cadastraEspecialidadeProfessorFormacaoVazia() {
		sistema.cadastraPesquisador("Seya","professor","Interesse na athena","cdz@1998","https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeProfessor("cdz@1998", "", "ufcg", "07/10/1999");
			fail("Deve ser lançada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void cadastraEspecialidadeProfessorUnidadeVazia() {
		sistema.cadastraPesquisador("Seya","estudante","Interesse na athena","cdz@1998","https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "", "07/10/1999");
			fail("Deve ser lançada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void cadastraEspecialidadeProfessorDataInvalida() {
		sistema.cadastraPesquisador("Seya","estudante","Interesse na athena","cdz@1998","https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07101999");
			fail("Deve ser lançada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void cadastraEspecialidadeProfessorDataVazia() {
		sistema.cadastraPesquisador("Seya","estudante","Interesse na athena","cdz@1998","https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "");
			fail("Deve ser lançada uma excessao");
		}catch(Exception e) {
			
		}
	}

	@Test
	public void cadastraEspecialidadeAlunoTest() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 2, 8.0);
	}
	
	@Test
	public void cadastraEspecialidadeAlunoEmailVazio() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeAluno("", 2, 8.0);
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void cadastraEspecialidadeAlunoSemestreInvalido() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeAluno("cdz@1998",-2, 8.0);
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void cadastraEspecialidadeAlunoIeaInvalido() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.cadastraEspecialidadeAluno("cdz@1998",2, -10);
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void listaPesquisadoresProfessores() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		assertEquals("Seya (professor) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro",sistema.listaPesquisadores("PROFESSORA"));
	}
	
	@Test
	public void listaPesquisadoresEstundates() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 2, 8);
		assertEquals("Seya (estudante) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro - 2o SEMESTRE - 8.0",sistema.listaPesquisadores("ALUNA"));
	}
	
	@Test
	public void listaPesquisadoresExterno() {
		sistema.cadastraPesquisador("Prairie Johnson", "externo", "Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos.", "theoa@2016", "https://notblind");
		assertEquals("Prairie Johnson (externo) - Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos. - theoa@2016 - https://notblind",sistema.listaPesquisadores("EXTERNO"));
		sistema.cadastraPesquisador("Seya", "externo", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		assertEquals("Seya (externo) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro | Prairie Johnson (externo) - Interessada no estudo de multiplas dimensoes e no estudo dos sentidos humanos. - theoa@2016 - https://notblind",sistema.listaPesquisadores("EXTERNO"));
	}
	
	@Test
	public void alteraNomePesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.alteraPesquisador("cdz@1998", "NOME", "Seya2");
	}
	
	@Test
	public void alteraNomePesquisadorAtributoVazio() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.alteraPesquisador("cdz@1998", "", "Seya2");
			fail("Devia lancar uma excessao");
		}catch(Exception e) {
			
		}
		
	}
	@Test
	public void alteraBiografiaPesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.alteraPesquisador("cdz@1998", "BIOGRAFIA", "Interesse no Shun");
	}
	
	@Test
	public void alteraFuncaoPesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.alteraPesquisador("cdz@1998", "FUNCAO", "professor");
	}
	
	@Test
	public void alteraFuncaoErradaPesquisador(){
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.alteraPesquisador("cdz@1998", "FUNCAO", "faxineiro da google");
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
		}
	}
	
	@Test
	public void alteraEmailPesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.alteraPesquisador("cdz@1998", "EMAIL", "faxineiro@1919");
	}
	
	@Test
	public void alteraEmailInvalidoPesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try{
			sistema.alteraPesquisador("cdz@1998", "EMAIL", "faxineiro1919");
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
		}
	}
	
	@Test
	public void alteraFotoPesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.alteraPesquisador("cdz@1998", "FOTO", "https://bronze");
	}
	
	@Test
	public void alteraFotoInvalidaPesquisador() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.alteraPesquisador("cdz@1998", "FOTO", "https:/bronze");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
	
	@Test
	public void alteraSemestreDePesquisadorNaoEspecializadoComoAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.alteraPesquisador("cdz@1998", "SEMESTRE", "3");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
	
	@Test
	public void alteraIeaDePesquisadorAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 3, 8);
		sistema.alteraPesquisador("cdz@1998", "IEA", "4");
	}
	
	@Test
	public void alteraSemestreDePesquisadorAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 3, 8);
		sistema.alteraPesquisador("cdz@1998", "SEMESTRE", "5");
	}
	
	@Test
	public void alteraIeaDePesquisadorProfessor() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
		try {
			sistema.alteraPesquisador("cdz@1998", "IEA", "4");
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
		}
	}
	
	@Test
	public void alteraSemestreDePesquisadorProfessor() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
		try {
			sistema.alteraPesquisador("cdz@1998", "SEMESTRE", "4");
			fail("Deve ser lancada uma excessao");
		}catch(Exception e) {
		}
	}
	
	@Test
	public void alteraFormacaoPesquisadorProfessor() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
		sistema.alteraPesquisador("cdz@1998", "FORMACAO", "biologia");
	}
	
	@Test
	public void alteraUnidadePesquisadorProfessor() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
		sistema.alteraPesquisador("cdz@1998", "UNIDADE", "uepb");
	}
	
	@Test
	public void alteraDataPesquisadorProfessor() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
		sistema.alteraPesquisador("cdz@1998", "DATA", "05/11/2003");
	}
	
	@Test
	public void alteraIeaDePesquisadorNaoEspecializadoComoAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.alteraPesquisador("cdz@1998", "IEA", "3");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
	
	@Test
	public void alteraUnidadePesquisadorAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 3, 8);
		try {
			sistema.alteraPesquisador("cdz@1998","UNIDADE", "UEPB");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Test
	public void alteraFormacaoPesquisadorAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 3, 8);
		try {
			sistema.alteraPesquisador("cdz@1998", "FORMACAO", "biologia");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
	
	@Test
	public void alteraDataPesquisadorAluno() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 3, 8);
		try {
			sistema.alteraPesquisador("cdz@1998", "DATA", "05/10/1999");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
	
	@Test
	public void alteraSemestreDePesquisadorNaoEspecializadoComoProfessor() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.alteraPesquisador("cdz@1998", "SEMESTRE", "3");
			fail("Deve ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
	
	@Test
	public void exibePesquisadorExterno(){
		sistema.cadastraPesquisador("Seya", "externo", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		assertEquals("Seya (externo) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro",sistema.exibePesquisador("cdz@1998"));
	}
	
	@Test
	public void exibePesquisadorAlunoNaoEspecializado(){
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		assertEquals("Seya (estudante) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro",sistema.exibePesquisador("cdz@1998"));
	}
	
	@Test
	public void exibePesquisadorProfessorNaoEspecializado(){
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		assertEquals("Seya (professor) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro",sistema.exibePesquisador("cdz@1998"));
	}
	
	@Test
	public void exibePesquisadorAlunoEspecializado() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeAluno("cdz@1998", 3, 8);
		assertEquals("Seya (estudante) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro - 3o SEMESTRE - 8.0",sistema.exibePesquisador("cdz@1998"));
	}
	
	@Test
	public void exibePesquisadorProfessorEspecializado() {
		sistema.cadastraPesquisador("Seya", "professor", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		sistema.cadastraEspecialidadeProfessor("cdz@1998", "computacao", "ufcg", "07/10/1999");
		assertEquals("Seya (professor) - Interesse na athena - cdz@1998 - https://seyaarmaduraouro - computacao - ufcg - 07/10/1999",sistema.exibePesquisador("cdz@1998"));
	}
	
	@Test
	public void exibePesquisadorNaoCadastrado() {
		sistema.cadastraPesquisador("Seya", "estudante", "Interesse na athena", "cdz@1998", "https://seyaarmaduraouro");
		try {
			sistema.exibePesquisador("cdz");
			fail("Devia ser lancada uma excessao");
		}catch (Exception e) {
		}
	}
}
