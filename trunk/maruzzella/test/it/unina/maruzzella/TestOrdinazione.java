package it.unina.maruzzella;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class TestOrdinazione {

	IOrdinazione ordinazione = null;
	IOrdinazioneCreator ordinazioneCreator = null;
	
	@Before
	public void setUp() throws Exception {
		ordinazioneCreator = new OrdinazioneCreator();
	}
	
	/**Test1
	 * 
	 * Semplice test del construttore, ci aspettiamo un'istanza di Tavolo
	 */
	@Test
	public void testCostruttoreOrdinazione()throws InvalidInputException {
		ITavolo tavolo= createMock(Tavolo.class);
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		assertNotNull(ordinazione);
	}
	
	/**Test2
	 * 
	 * test del construttore, passando parametro=null
	 * Ci aspettiamo eccezione
	 */
	@Test(expected=InvalidInputException.class)
	public void testCostruttoreConNull() throws InvalidInputException{
		ordinazione = ordinazioneCreator.creaOrdinazione(null);
	}
	
	/**Test3
	 * 
	 * test getNPiattiOrdinati senza Ordinazioni
	 */
	@Test
	public void testGetNPiattiOrdinati() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
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
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
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
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
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
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto1);
		
		assertSame(piatto1, ordinazione.getPiattoOrdinato(1));
	}
	
	/**Test7
	 * 
	 * test getPiattoOrdinato con 2 ordinazioni
	 */
	@Test
	public void testGetPiattoOrdinatoDueOrdinazioni() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto1 = createMock(Piatto.class);
		IPiatto piatto2 = createMock(Piatto.class);
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto1);
		ordinazione.ordinaPiatto(piatto2);
		
		assertSame(piatto1, ordinazione.getPiattoOrdinato(1));
		assertSame(piatto2, ordinazione.getPiattoOrdinato(2));
	}
	
	
	/**Test8
	 * 
	 * test getPiattoOrdinato richiedendo 0
	 */
	@Test(expected=InvalidInputException.class)
	public void testGetPiattoOrdinatoChiedoZero() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto1 = createMock(Piatto.class);
		IPiatto piatto2 = createMock(Piatto.class);
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto1);
		ordinazione.ordinaPiatto(piatto2);
		
		assertSame(piatto1, ordinazione.getPiattoOrdinato(0));
	}
	
	
	/**Test9
	 * 
	 * test getPiattoOrdinato richiedendo numero piatto superiore a num Ordinazioni
	 */
	@Test(expected=InvalidInputException.class)
	public void testGetPiattoOrdinatoChiedoNumEccessivo() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto1 = createMock(Piatto.class);
		IPiatto piatto2 = createMock(Piatto.class);
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		ordinazione.ordinaPiatto(piatto1);
		ordinazione.ordinaPiatto(piatto2);
		
		assertSame(piatto1, ordinazione.getPiattoOrdinato(3));
	}
	
	
	/**Test10
	 * 
	 * test ordinaPiatto, passando parametro=null
	 * Ci aspettiamo eccezione
	 */
	@Test(expected=InvalidInputException.class)
	public void testOrdinaPiattoConNull() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		ordinazione.ordinaPiatto(null);
	}
	
	
	/**Test11
	 * 
	 * test calcolaConto senza Ordinazione
	 * Ci aspettiamo solo costo coperti
	 */
	@Test
	public void testCalcolaContoNoOrdini() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		double costoCoperti=10.00;
		
		expect(tavolo.calcolaCostoCoperti()).andReturn(costoCoperti);
		
		replay(tavolo);
		assertEquals("Conto Errato",costoCoperti, ordinazione.calcolaConto(),0);
		verify(tavolo);
		
	}
	
	
	/**Test12
	 * 
	 * test calcolaConto con un Piatto 
	 * Ci aspettiamo solo costo coperti
	 */
	@Test
	public void testCalcolaContoUnOrdine() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto = createMock(Piatto.class);
		double costoCoperti=10.00;
		double prezzoPiatto=10;
		
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		ordinazione.ordinaPiatto(piatto);
		
		expect(tavolo.calcolaCostoCoperti()).andReturn(costoCoperti);
		expect(piatto.getPrezzo()).andReturn(prezzoPiatto);
		
		replay(tavolo,piatto);
		assertEquals("Conto Errato",costoCoperti+prezzoPiatto, ordinazione.calcolaConto(),0);
		verify(tavolo,piatto);
		
	}
	
	
	/**Test13
	 * 
	 * test calcolaConto con 2 Piatti 
	 * Ci aspettiamo solo costo coperti
	 */
	@Test
	public void testCalcolaContoDueOrdini() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		IPiatto piatto1 = createMock(Piatto.class);
		IPiatto piatto2 = createMock(Piatto.class);
		
		double costoCoperti=10.00;
		double prezzoPiatto1=10;
		double prezzoPiatto2=15;
		
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		ordinazione.ordinaPiatto(piatto1);
		ordinazione.ordinaPiatto(piatto2);
		
		expect(tavolo.calcolaCostoCoperti()).andReturn(costoCoperti);
		expect(piatto1.getPrezzo()).andReturn(prezzoPiatto1);
		expect(piatto2.getPrezzo()).andReturn(prezzoPiatto2);
		
		replay(tavolo,piatto1,piatto2);
		assertEquals("Conto Errato",costoCoperti+prezzoPiatto1+prezzoPiatto2, ordinazione.calcolaConto(),0);
		verify(tavolo,piatto1,piatto2);
		
	}
	
	
	/**Test14
	 * 
	 * test getNPiattiOrdinati senza Ordinazioni
	 */
	@Test
	public void testGetTavolo() throws InvalidInputException{
		ITavolo tavolo= createMock(Tavolo.class);
		
		ordinazione = ordinazioneCreator.creaOrdinazione(tavolo);
		
		assertSame(tavolo, ordinazione.getTavolo());
	}
	
	
}
