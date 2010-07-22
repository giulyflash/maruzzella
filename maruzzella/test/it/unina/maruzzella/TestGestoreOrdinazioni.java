package it.unina.maruzzella;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.easymock.EasyMock.*;


public class TestGestoreOrdinazioni {
	
	/**Test1
	 * 
	 * Semplice test del construttore
	 */
	@Test
	public void testCostruttoreOrdinazione()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		assertNotNull(gestOrdinazioni);
	}
	
	/**Test2
	 * 
	 * Semplice test del metodo getNTavoli()
	 */
	@Test
	public void testGetNTavoliSenzaTavoli()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		assertEquals(0, gestOrdinazioni.getNTavoli());
	}

	/**Test3
	 * 
	 * Metodo getNTavoli(), inserendo un solo tavolo
	 */
	@Test
	public void testGetNTavoliUnTavolo()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int maxCoperti = 7;
		int costoCoperto = 2;
		
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		
		assertEquals(1, gestOrdinazioni.getNTavoli());
		
		fail("questo unit test non isola la classe GestoreOrdinazioni");
	}
}
