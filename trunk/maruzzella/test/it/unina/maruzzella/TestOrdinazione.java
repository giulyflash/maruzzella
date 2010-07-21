package it.unina.maruzzella;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class TestOrdinazione {

	/**Test1
	 * 
	 * Semplice test del construttore, ci aspettiamo un'istanza di Tavolo
	 */
	
	@Test
	public void testCostruttoreOrdinazione()throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		
		
		IOrdinazione ordinazione = new Ordinazione(tavolo);
		
		assertNotNull(ordinazione);
	}
	
	/**Test2
	 * 
	 * test del construttore, passando parametro=null
	 * Ci aspettiamo eccezione
	 */
	
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreConNull() throws InvalidInputException{
		IOrdinazione ordinazione = new Ordinazione(null);
	}
	
	/**Test3
	 * 
	 * test getNPiattiOrdinati senza Ordinazioni
	 */
	
	@Test
	public void testGetNPiattiOrdinati() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		
		IOrdinazione ordinazione = new Ordinazione(tavolo);
		
		assertEquals(0, ordinazione.getNPiattiOrdinati());
	}
	
	
	/**Test4
	 * 
	 * test getNPiattiOrdinati con 1 ordinazione
	 */
	
	@Test
	public void testGetNPiattiOrdinatiUnOrdinazione() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto = createMock(Piatto.class);
		
		IOrdinazione ordinazione = new Ordinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto);
		
		assertEquals(1, ordinazione.getNPiattiOrdinati());
	}
	
	/**Test5
	 * 
	 * test getNPiattiOrdinati con 2 ordinazioni
	 */
	
	@Test
	public void testGetNPiattiOrdinatiDueOrdinazioni() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto1 = createMock(Piatto.class);
		IPiatto piatto2 = createMock(Piatto.class);
		
		IOrdinazione ordinazione = new Ordinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto1);
		ordinazione.ordinaPiatto(piatto2);
		
		
		assertEquals(2, ordinazione.getNPiattiOrdinati());
	}
	
	/**Test6
	 * 
	 * test getPiattoOrdinato con 1 ordinazioni
	 */
	
	@Test
	public void testGetPiattoOrdinatoUnOrdinazione() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto1 = createMock(Piatto.class);
		
		IOrdinazione ordinazione = new Ordinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto1);
		
		assertSame(piatto1, ordinazione.getPiattoOrdinato(1));
	}
	
}