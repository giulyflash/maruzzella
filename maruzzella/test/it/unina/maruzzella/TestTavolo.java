package it.unina.maruzzella;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTavolo {
	
	ITavolo tavolo;
	

	/* Test1
	 * 
	 * Testiamo il costruttore del tavolo con tutti gli input validi
	 */
	@Test
	public void testCostruttoreTavolo() throws InvalidInputException {
		int numeroTavolo=1;
		int maxCoperti=5;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}
	
	
	/* Test2
	 * 
	 * Testiamo il costruttore del tavolo con numero non valido
	 */
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreNumeroTavoloZero() throws InvalidInputException {
		int numeroTavolo=0;
		int maxCoperti=5;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
	}
	
	
	/* Test3
	 * 
	 * Testiamo il costruttore del tavolo con numero tavolo al limite
	 */
	@Test
	public void testCostruttoreNumeroTavoloUno() throws InvalidInputException {
		int numeroTavolo=1;
		int maxCoperti=5;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}

}
