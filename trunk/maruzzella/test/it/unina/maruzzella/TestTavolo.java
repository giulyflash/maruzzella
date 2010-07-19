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
	public void testCostruttoreTavolo() {
		int numeroTavolo=1;
		int maxCoperti=5;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
		
		
		
	}

}
