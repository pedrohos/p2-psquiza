package testes_use_case5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import psquiza.controladores.ControladorPesquisa;
import psquiza.controladores.Sistema;
import psquiza.entidades.Pesquisa;

class ControladorPesquisaTest {

	private ControladorPesquisa controlador;
	private Sistema sistema;
	String pesquisa;
	String objetivo1;
	
	@BeforeEach
	void criaControle(){
		controlador = new ControladorPesquisa();
		sistema = new Sistema();
		
		pesquisa = sistema.cadastraPesquisa("Pesquisa sobre vasamento de petroleo", "petroleo, animais, vasamento");
		///objetivo1 = sistema.cadastraObjetivo("ESPECIFICO", "Ajudar animais ameacados pelo vazamento de petroleo", 3, 4);
	}

	
	@Test
	void testaSaida() {
		assertEquals("PET1", pesquisa);
	}

}
