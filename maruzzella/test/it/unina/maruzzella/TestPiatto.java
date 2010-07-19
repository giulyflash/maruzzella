package it.unina.maruzzella;

import static org.junit.Assert.*;

import org.junit.Test;

import it.unina.maruzzella.InvalidInputException;


public class TestPiatto {
	
	IPiatto piatto;

	/* Test 1
	 * 
	 * Testiamo il costruttore della classe dando in INPUT nome e prezzo
	 */
	
	
	@Test
	public void testCostruttorePiatto() throws Exception {
		String nome="Margherita";
		double prezzo=3.50;
		
		piatto= new Piatto(nome, prezzo);
		
		assertEquals(nome, piatto.getNome());
		assertEquals("Prezzo Errato",prezzo,piatto.getPrezzo(),0);
		
	}
	
	/* Test2
	 * 
	 * Testiamo il costruttore con un prezzo negativo
	 * Ci aspettiamo il lancio di un'eccezione
	 */
	
	@Test
	public void testCostruttorePrezzoNegativo(){
		String nome="Margherita";
		double prezzo=-2;
		
		
		try {
			piatto= new Piatto(nome,prezzo);
			fail("Eccezione non lanciata per inserimento prezzo minore di 0");
		} catch (InvalidInputException e) {
			assertEquals("Prezzo negativo", e.getMessage());
		}
	}
	
	/* Test3
	 * 
	 * Test con prezzo 0
	 */
	
	@Test
	public void testCostruttorePrezzoNullo() throws Exception{
		String nome="Margherita";
		double prezzo=0;
		
		piatto= new Piatto(nome, prezzo);
		
		assertEquals(nome, piatto.getNome());
		assertEquals("Prezzo Errato",prezzo,piatto.getPrezzo(),0);
		
	}

}
