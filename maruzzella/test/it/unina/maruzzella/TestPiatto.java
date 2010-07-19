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
	
	/* Test4
	 * 
	 * Test con nome vuoto
	 */
	
	@Test
	public void testCostruttoreNomeVuoto() throws Exception{
		String nome="";
		double prezzo=1;
		
		try {
			piatto= new Piatto(nome,prezzo);
			fail("Eccezione non lanciata per inserimento nome vuoto");
		} catch (InvalidInputException e) {
			assertEquals("Nome non specificato", e.getMessage());
		}
		
	}
	
	/* Test5
	 * 
	 * Test con nome null
	 */
	
	@Test
	public void testCostruttoreNomeNull() throws Exception{
		String nome=null;
		double prezzo=200;
		
		try {
			piatto= new Piatto(nome,prezzo);
			fail("Eccezione non lanciata per inserimento nome null");
		} catch (InvalidInputException e) {
			assertEquals("Nome Null", e.getMessage());
		}
		
	}

}
