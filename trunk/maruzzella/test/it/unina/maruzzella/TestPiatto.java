package it.unina.maruzzella;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPiatto {
	
	IPiatto piatto;

	/* Test 1
	 * 
	 * Testiamo il costruttore della classe dando in INPUT nome e prezzo
	 */
	
	
	@Test
	public void testCostruttorePiatto() {
		String nome="Margherita";
		double prezzo=3.50;
		
		piatto= new Piatto(nome, prezzo);
		
		assertEquals(nome, piatto.getNome());
		assertEquals("Prezzo Errato",prezzo,piatto.getPrezzo(),0);
		
	}

}
