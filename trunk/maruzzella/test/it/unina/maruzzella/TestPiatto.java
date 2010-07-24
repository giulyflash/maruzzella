package it.unina.maruzzella;

import static org.junit.Assert.*;

import org.junit.Test;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

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
	
	
	/* Test 8
	 * 
	 * Test del metodo equals()
	 */
	@Test
	public void testEquals() throws Exception {
		String nome="Pizza a due piani";
		double prezzo=16.8;
		
		piatto= new Piatto(nome, prezzo);
		
		assertTrue(piatto.equals(new Piatto(nome, prezzo)));
	}

	
	/* Test 9
	 * 
	 * Test del metodo equals() con parametro null
	 */
	@Test
	public void testEqualsNull() throws Exception {
		String nome="Pizza a tre piani";
		double prezzo=136.8;
		
		piatto= new Piatto(nome, prezzo);
		
		assertFalse(piatto.equals(null));
	}
	
	
	/* Test 10
	 * 
	 * Test del metodo equals() con piatto diverso per il nome
	 */
	@Test
	public void testEqualsNomeDiverso() throws Exception {
		
		String nome1="Pizza a otto piani";
		double prezzo1=16.8;
		
		String nome2="Pizza senza piani";
		double prezzo2=16.8;
		
		piatto= new Piatto(nome1, prezzo1);
		
		assertFalse(piatto.equals(new Piatto(nome2, prezzo2)));
	}
	
	
	/* Test 11
	 * 
	 * Test del metodo equals() con piatto diverso per il prezzo
	 */
	@Test
	public void testEqualsPrezzoDiverso() throws Exception {
		
		String nome1="Pizza a otto piani";
		double prezzo1=16.8;
		
		String nome2="Pizza a otto piani";
		double prezzo2=16.7;
		
		piatto= new Piatto(nome1, prezzo1);
		
		assertFalse(piatto.equals(new Piatto(nome2, prezzo2)));
	}
	
	
	/* Test 12
	 * 
	 * Test del metodo equals() con piatto diverso per nome e prezzo
	 */
	@Test
	public void testEqualsNomePrezzoDiverso() throws Exception {
		
		String nome1="Torta caprese";
		double prezzo1=29.8;
		
		String nome2="Pastina";
		double prezzo2=0.7;
		
		piatto= new Piatto(nome1, prezzo1);
		
		assertFalse(piatto.equals(new Piatto(nome2, prezzo2)));
	}
}
