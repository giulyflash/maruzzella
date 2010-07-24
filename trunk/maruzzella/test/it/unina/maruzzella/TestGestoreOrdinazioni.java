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
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti), EasyMock.eq(costoCoperto))).andReturn(tavolo);
		expect(tavolo.isLibero()).andReturn(true);
		expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		tavolo.setCoperti(maxCoperti);
		
		expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		expect(tavolo.getNumero()).andReturn(numeroTavolo);
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo, tavoloCreator);
		//end
		
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		int numeroTavoloRestituito = gestOrdinazioni.creaOrdinazione(maxCoperti);
		verify(tavolo, tavoloCreator);
		
		assertEquals(numeroTavolo, numeroTavoloRestituito);
	}
	
	
	/**Test6
	 * 
	 * Occupiamo il primo tavolo con un'ordinazione
	 * Richiediamo nuova ordinazione ci restituirà primo tavolo libero
	 */
	@Test
	public void testCreaOrdinazioneConPrimoTavoloOccupato()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int numeroTavolo1 = 1;
		int numeroTavolo2 = 2;
		
		int maxCoperti1 = 7;
		int maxCoperti2 = 2;
		double costoCoperto1 = 2;
		double costoCoperto2 = 2;
		
		ITavolo tavolo1 = createMock(Tavolo.class);
		ITavolo tavolo2 = createMock(Tavolo.class);
		IOrdinazione ordinazione1 = createMock(Ordinazione.class);
		IOrdinazione ordinazione2 = createMock(Ordinazione.class);
		
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(numeroTavolo1,maxCoperti1,costoCoperto1)).andReturn(tavolo1);
		expect(tavoloCreator.creaTavolo(numeroTavolo2,maxCoperti2,costoCoperto2)).andReturn(tavolo2);
		
		//Ordinazione 1
		expect(tavolo1.isLibero()).andReturn(true);
		expect(tavolo1.getMaxCoperti()).andReturn(maxCoperti1);
		tavolo1.setCoperti(maxCoperti1);
		expect(ordinazioneCreator.creaOrdinazione(tavolo1)).andReturn(ordinazione1);
		expect(tavolo1.getNumero()).andReturn(numeroTavolo1);
		
		//Ordinazione 2
		expect(tavolo1.isLibero()).andReturn(false);
		expect(tavolo2.isLibero()).andReturn(true);
		expect(tavolo2.getMaxCoperti()).andReturn(maxCoperti2);
		tavolo2.setCoperti(maxCoperti2);
		expect(ordinazioneCreator.creaOrdinazione(tavolo2)).andReturn(ordinazione2);
		expect(tavolo2.getNumero()).andReturn(numeroTavolo2);
		
		//end
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo1, tavolo2, tavoloCreator, ordinazioneCreator);
		gestOrdinazioni.aggiungiTavolo(maxCoperti1, costoCoperto1);
		gestOrdinazioni.aggiungiTavolo(maxCoperti2, costoCoperto2);
		int numeroTavoloRestituito1 = gestOrdinazioni.creaOrdinazione(maxCoperti1);
		int numeroTavoloRestituito2 = gestOrdinazioni.creaOrdinazione(maxCoperti2);
		verify(tavolo1, tavolo2, tavoloCreator, ordinazioneCreator);
		
		assertEquals(numeroTavolo1, numeroTavoloRestituito1);
		//assertEquals(numeroTavolo2, numeroTavoloRestituito2);
	}
	
	
	/**Test7
	 * 
	 * Testiamo tutti i tavolo non liberi
	 */
	@Test
	public void testCreaOrdinazioneTuttiTavoliOccupati()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int numeroTavolo1 = 1;
		
		
		int maxCoperti1 = 7;
		int maxCoperti2 = 2;
		double costoCoperto1 = 2;
		
		
		ITavolo tavolo1 = createMock(Tavolo.class);
		ITavolo tavolo2 = createMock(Tavolo.class);
		IOrdinazione ordinazione1 = createMock(Ordinazione.class);
		IOrdinazione ordinazione2 = createMock(Ordinazione.class);
		
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(numeroTavolo1,maxCoperti1,costoCoperto1)).andReturn(tavolo1);
		
		
		//Ordinazione 1
		expect(tavolo1.isLibero()).andReturn(true);
		expect(tavolo1.getMaxCoperti()).andReturn(maxCoperti1);
		tavolo1.setCoperti(maxCoperti1);
		expect(ordinazioneCreator.creaOrdinazione(tavolo1)).andReturn(ordinazione1);
		expect(tavolo1.getNumero()).andReturn(numeroTavolo1);
		
		//Ordinazione 2
		expect(tavolo1.isLibero()).andReturn(false);
		
		//end
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo1, tavoloCreator, ordinazioneCreator);
		gestOrdinazioni.aggiungiTavolo(maxCoperti1, costoCoperto1);
		//gestOrdinazioni.aggiungiTavolo(maxCoperti2, costoCoperto2);
		int numeroTavoloRestituito1 = gestOrdinazioni.creaOrdinazione(maxCoperti1);
		int numeroTavoloRestituito2 = gestOrdinazioni.creaOrdinazione(maxCoperti2);
		verify(tavolo1,  tavoloCreator, ordinazioneCreator);
		
		assertEquals(numeroTavolo1, numeroTavoloRestituito1);
		//0 => Nessun tavolo associato
		assertEquals(0, numeroTavoloRestituito2);
	}
	
	/**Test8
	 * 
	 * Testiamo secondo tavolo non abbastanza grande
	 */
	@Test
	public void testCreaOrdinazioneTavoloNonCapiente()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int numeroTavolo1 = 1;
		int numeroTavolo2 = 2;
		
		int maxCoperti1 = 7;
		int maxCoperti2 = 2;
		double costoCoperto1 = 2;
		double costoCoperto2 = 2;
		
		ITavolo tavolo1 = createMock(Tavolo.class);
		ITavolo tavolo2 = createMock(Tavolo.class);
		IOrdinazione ordinazione1 = createMock(Ordinazione.class);
		IOrdinazione ordinazione2 = createMock(Ordinazione.class);
		
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(numeroTavolo1,maxCoperti1,costoCoperto1)).andReturn(tavolo1);
		expect(tavoloCreator.creaTavolo(numeroTavolo2,maxCoperti2,costoCoperto2)).andReturn(tavolo2);
		
		//Ordinazione 1
		expect(tavolo1.isLibero()).andReturn(true);
		expect(tavolo1.getMaxCoperti()).andReturn(maxCoperti1);
		tavolo1.setCoperti(maxCoperti1);
		expect(ordinazioneCreator.creaOrdinazione(tavolo1)).andReturn(ordinazione1);
		expect(tavolo1.getNumero()).andReturn(numeroTavolo1);
		
		//Ordinazione 2
		expect(tavolo1.isLibero()).andReturn(false);
		expect(tavolo2.isLibero()).andReturn(true);
		expect(tavolo2.getMaxCoperti()).andReturn(maxCoperti2);
		
		
		//end
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo1, tavolo2, tavoloCreator, ordinazioneCreator);
		
		gestOrdinazioni.aggiungiTavolo(maxCoperti1, costoCoperto1);
		gestOrdinazioni.aggiungiTavolo(maxCoperti2, costoCoperto2);
		int numeroTavoloRestituito1 = gestOrdinazioni.creaOrdinazione(maxCoperti1);
		//Seconda ordinazione numPersone > maxCoperti
		int numeroTavoloRestituito2 = gestOrdinazioni.creaOrdinazione(maxCoperti2+1);
		
		verify(tavolo1, tavolo2, tavoloCreator, ordinazioneCreator);
		
		assertEquals(numeroTavolo1, numeroTavoloRestituito1);
		//0=> Nessun tavolo associato
		assertEquals(0, numeroTavoloRestituito2);
	}
	
	/**Test9
	 * 
	 * Nessun tavolo in assoluto
	 */
	@Test
	public void testCreaOrdinazioneNoTavoli()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		

		int maxCoperti = 7;
		double costoCoperto = 2;
		
		ITavolo tavolo = createMock(Tavolo.class);
		IOrdinazione ordinazione = createMock(Ordinazione.class);
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo, tavoloCreator);

		int numeroTavoloRestituito = gestOrdinazioni.creaOrdinazione(maxCoperti);
		verify(tavolo, tavoloCreator);
		
		//0=> Nessun tavolo associato
		assertEquals(0, numeroTavoloRestituito);
	}
	
	/**Test10
	 * 
	 * Semplice test per il metodo ordinaPiatto numTavolo non valido
	 */
	@Test(expected=InvalidInputException.class)
	public void testOrdinaPiattoTavoloZero()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
	
		String nomePiatto="Maruzze con la spina";
		double prezzoPiatto=10.00;
		
		gestOrdinazioni.ordinaPiatto(0, nomePiatto, prezzoPiatto);
	}
	
	/**Test11
	 * 
	 * Semplice test per il metodo ordinaPiatto numTavolo maggiore totale tavoli (zero tavoli)
	 */
	@Test(expected=InvalidInputException.class)
	public void testOrdinaPiattoNumTavoloTroppoGrandeNoTavoli()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		String nomePiatto="Maruzze con la spina";
		double prezzoPiatto=10.00;
		
		gestOrdinazioni.ordinaPiatto(1, nomePiatto, prezzoPiatto);
	}
	
	
	/**Test12
	 * 
	 * Semplice test per il metodo ordinaPiatto numTavolo maggiore totale tavoli (un tavol0)
	 */
	@Test(expected=InvalidInputException.class)
	public void testOrdinaPiattoNumTavoloTroppoGrandeSiTavoli()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int tavoloRichiesto=2;
		String nomePiatto="Maruzze con la spina";
		double prezzoPiatto=10.00;
		int numeroTavolo = 1;
		int maxCoperti = 7;
		double costoCoperto = 2;
		
	
		ITavolo tavolo = createMock(Tavolo.class);
		IOrdinazione ordinazione = createMock(Ordinazione.class);
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti), EasyMock.eq(costoCoperto))).andReturn(tavolo);
		expect(tavolo.isLibero()).andReturn(true);
		expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		tavolo.setCoperti(maxCoperti);
		
		expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		expect(tavolo.getNumero()).andReturn(numeroTavolo);
		
		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		
		replay(tavolo, tavoloCreator);
		//end
		
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		
		gestOrdinazioni.ordinaPiatto(tavoloRichiesto, nomePiatto, prezzoPiatto);
		
	}
	
	
}

