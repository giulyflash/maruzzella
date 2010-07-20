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
	
	@Test(expected=InvalidInputException.class)
	public void testCostruttorePrezzoNegativo() throws InvalidInputException{
		String nome="Margherita";
		double prezzo=-2;
		
		piatto= new Piatto(nome,prezzo);
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
	
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreNomeVuoto() throws Exception{
		String nome="";
		double prezzo=1;
		
		piatto= new Piatto(nome,prezzo);
		
	}
	
	/* Test5
	 * 
	 * Test con nome null
	 */
	
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreNomeNull() throws Exception{
		String nome=null;
		double prezzo=200;
		
		piatto= new Piatto(nome,prezzo);
	}
	
	/* Test 6
	 * 
	 * Testiamo il costruttore della classe dando in INPUT nome  con spazio
	 */
	
	
	@Test
	public void testCostruttorePiattoNomeConSpazio() throws Exception {
		String nome="Ripeno Fritto con Ricotta e Pepe";
		double prezzo=5;
		
		piatto= new Piatto(nome, prezzo);
		
		assertEquals(nome, piatto.getNome());
		assertEquals("Prezzo Errato",prezzo,piatto.getPrezzo(),0);
	}
	
	/* Test 7
	 * 
	 * Testiamo il costruttore della classe dando in INPUT nome  con apostrofo
	 */
	
	
	@Test
	public void testCostruttorePiattoNomeConApostrofo() throws Exception {
		String nome="Pizza da' regina d'Inghilterra";
		double prezzo=6;
		
		piatto= new Piatto(nome, prezzo);
		
		assertEquals(nome, piatto.getNome());
		assertEquals("Prezzo Errato",prezzo,piatto.getPrezzo(),0);
		
	}

}
