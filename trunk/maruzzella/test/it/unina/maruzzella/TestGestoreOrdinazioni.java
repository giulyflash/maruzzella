package it.unina.maruzzella;

import static org.junit.Assert.*;

import java.beans.Expression;

import org.easymock.EasyMock;
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
	 * Metodo getNTavoli(), inserendo un solo tavolo.
	 * In questo test e' previsto anche il test del metodo aggiungiTavolo.
	 */
	@Test
	public void testGetNTavoliUnTavolo()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int maxCoperti = 7;
		double costoCoperto = 2;
		
		ITavolo tavolo= createMock(Tavolo.class);
		
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti), EasyMock.eq(costoCoperto))).andReturn(tavolo);
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		
		replay(tavolo, tavoloCreator);
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		verify(tavolo, tavoloCreator);
		
		assertEquals(1, gestOrdinazioni.getNTavoli());
	}
	
	
	/**Test4
	 * 
	 * Metodo getNTavoli(), inserendo due tavoli.
	 * In questo test e' previsto anche il test del metodo aggiungiTavolo.
	 */
	@Test
	public void testGetNTavoliDueTavoli()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int maxCoperti1 = 4;
		double costoCoperto1 = 1.5;
		int maxCoperti2 = 2;
		double costoCoperto2 = 0;
		
		ITavolo tavolo1= createMock(Tavolo.class);
		ITavolo tavolo2= createMock(Tavolo.class);
		
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti1), EasyMock.eq(costoCoperto1))).andReturn(tavolo1);
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti2), EasyMock.eq(costoCoperto2))).andReturn(tavolo2);
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		
		replay(tavolo1, tavolo2, tavoloCreator);
		gestOrdinazioni.aggiungiTavolo(maxCoperti1, costoCoperto1);
		gestOrdinazioni.aggiungiTavolo(maxCoperti2, costoCoperto2);
		verify(tavolo1, tavolo2, tavoloCreator);
		
		assertEquals(2, gestOrdinazioni.getNTavoli());
	}
	
	
	/**Test5
	 * 
	 * Semplice test per il metodo creaOrdinazione
	 */
	@Test
	public void testCreaOrdinazioneUnaOrdinazioneUnTavolo()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int numeroTavolo = 1;
		int maxCoperti = 7;
		double costoCoperto = 2;
		
		ITavolo tavolo = createMock(Tavolo.class);
		IOrdinazione ordinazione = createMock(Ordinazione.class);
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti), EasyMock.eq(costoCoperto))).andReturn(tavolo);
		expect(tavolo.isLibero()).andReturn(true);
		expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo, tavoloCreator);
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		int numeroTavoloRestituito = gestOrdinazioni.creaOrdinazione(maxCoperti);
		verify(tavolo, tavoloCreator);
		
		assertEquals(numeroTavolo, numeroTavoloRestituito);
	}
}
