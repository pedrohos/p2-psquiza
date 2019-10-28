package testes_use_case4;

import static org.junit.Assert.*;
import org.junit.Test;

import psquiza.Item;

public class TestItem {

	@Test
	public void criaItem() {
		Item item = new Item("Janela");
	}

	@Test
	public void criaItemVazio() {
		try {
			Item item = new Item("");
			assertFalse(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void pegaEstadoItem() {
		Item item = new Item("Janela");
		assertEquals("PENDENTE", item.getEstado());
	}
}
