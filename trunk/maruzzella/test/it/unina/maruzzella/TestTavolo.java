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
		int numeroTavolo=7;
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
	

	/* Test4
	 * 
	 * Testiamo il costruttore del tavolo con numero tavolo al limite+1
	 */
	@Test
	public void testCostruttoreNumeroTavoloDue() throws InvalidInputException {
		int numeroTavolo=2;
		int maxCoperti=5;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}
	
	
	/* Test5
	 * 
	 * Testiamo il costruttore del tavolo con maxCoperti non valido
	 */
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreMaxCopertiNonValido() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=1;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
	}
	
	
	/* Test6
	 * 
	 * Testiamo il costruttore del tavolo con maxCoperti al limite
	 */
	@Test
	public void testCostruttoreMaxCopertiDue() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=2;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}
	
	
	/* Test7
	 * 
	 * Testiamo il costruttore del tavolo con maxCoperti al limite+1
	 */
	@Test
	public void testCostruttoreMaxCopertiTre() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=3;
		double costoCoperto=2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}
	
	
	/* Test8
	 * 
	 * Testiamo il costruttore del tavolo con costo non valido
	 */
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreCostoNonValido() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = -0.001;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
	}
	
	
	/* Test9
	 * 
	 * Testiamo il costruttore del tavolo con costo al limite
	 */
	@Test
	public void testCostruttoreCostoZero() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 0;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}
	
	
	/* Test10
	 * 
	 * Testiamo il costruttore del tavolo con costo al limite circa
	 */
	@Test
	public void testCostruttoreCostoQuasiZero() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 0.001;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		assertEquals(numeroTavolo, tavolo.getNumero());
		assertEquals(maxCoperti, tavolo.getMaxCoperti());
		assertEquals("Costo Errato",costoCoperto,tavolo.getCostoCoperto(),0);
	}
	
	
	/* Test11
	 * 
	 * Testiamo il metodo setCoperti e getCoperti
	 */
	@Test
	public void testSetCopertiGetCoperti() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = 3;	
		tavolo.setCoperti(coperti);
	}
	
	
	/* Test12
	 * 
	 * Testiamo il metodo setCoperti e getCoperti con valore non valido (minimo-1)
	 */
	@Test(expected=InvalidInputException.class)
	public void testSetCopertiGetCopertiZero() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = 0;	
		tavolo.setCoperti(coperti);
	}

	
	/* Test13
	 * 
	 * Testiamo il metodo setCoperti e getCoperti con valore valido (minimo)
	 */
	@Test
	public void testSetCopertiGetCopertiUno() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = 1;	
		tavolo.setCoperti(coperti);
		
		assertEquals(coperti, tavolo.getCoperti());
	}
	
	
	/* Test14
	 * 
	 * Testiamo il metodo setCoperti e getCoperti con valore valido (minimo+1)
	 */
	@Test
	public void testSetCopertiGetCopertiDue() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = 2;	
		tavolo.setCoperti(coperti);
		
		assertEquals(coperti, tavolo.getCoperti());
	}
	
	
	/* Test15
	 * 
	 * Testiamo il metodo setCoperti e getCoperti con valore non valido (massimo+1)
	 */
	@Test(expected=InvalidInputException.class)
	public void testSetCopertiGetCopertiSuperaLimite() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = maxCoperti+1;	
		tavolo.setCoperti(coperti);
	}
	
	
	/* Test16
	 * 
	 * Testiamo il metodo setCoperti e getCoperti con valore massimo
	 */
	@Test
	public void testSetCopertiGetCopertiMassimo() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = maxCoperti;	
		tavolo.setCoperti(coperti);
		
		assertEquals(coperti, tavolo.getCoperti());
	}
	
	
	/* Test17
	 * 
	 * Testiamo il metodo setCoperti e getCoperti con valore massimo-1
	 */
	@Test
	public void testSetCopertiGetCopertiQuasiMassimo() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = maxCoperti-1;	
		tavolo.setCoperti(coperti);
		
		assertEquals(coperti, tavolo.getCoperti());
	}
	
	
	/* Test18
	 * 
	 * Testiamo il metodo getCoperti senza setCoperti
	 */
	@Test
	public void testGetCopertiSenzaSetCoperti() throws InvalidInputException {
		int numeroTavolo=7;
		int maxCoperti=5;
		double costoCoperto = 2;
		
		tavolo = new Tavolo(numeroTavolo, maxCoperti, costoCoperto);
		
		int coperti = 0;
		
		assertEquals(coperti, tavolo.getCoperti());
	}
}
