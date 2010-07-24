package it.unina.maruzzella;

import static org.junit.Assert.*;

import java.beans.Expression;

import org.easymock.EasyMock;
import org.junit.Test;

import static org.easymock.EasyMock.*;


public class TestGestoreOrdinazioni {
	
	/*Test1
	 * 
	 * Semplice test del construttore
	 */
	@Test
	public void testCostruttoreOrdinazione()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		assertNotNull(gestOrdinazioni);
	}
	
	/*Test2
	 * 
	 * Semplice test del metodo getNTavoli()
	 */
	@Test
	public void testGetNTavoliSenzaTavoli()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		assertEquals(0, gestOrdinazioni.getNTavoli());
	}

	/*Test3
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
	
	
	/*Test4
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
	
	
	/*Test5
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
	
	
	/*Test6
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
	
	
	/*Test7
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
	
	
	/*Test8
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
	
	
	/*Test9
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
	
	
	/*Test10
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
	
	
	/*Test11
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
	
	
	/*Test12
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

	
	/*Test13
	 * 
	 * Semplice test per il metodo ordinaPiatto numTavolo maggiore totale tavoli (un tavol0)
	 */
	@Test(expected=InvalidInputException.class)
	public void testOrdinaPiattoSuTavoloLibero()throws InvalidInputException{
		
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int tavoloRichiesto=1;
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
	
	
	/*Test14
	 *
	 */
	@Test(expected=InvalidInputException.class)
	public void testOrdinaPiattoTavoloZeroSiTavoli()throws InvalidInputException{
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int tavoloRichiesto=0;
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
	
	
	/*Test 15
	 * 
	 * chiediConto Tavolo Zero
	 * 
	 */
	@Test(expected= InvalidInputException.class)
	public void testChiediContoTavoloZero()throws InvalidInputException{
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int numTavolo=0;
		@SuppressWarnings("unused")
		double conto=gestOrdinazioni.chiediConto(numTavolo);
	}
	
	
	/*Test 16
	 * 
	 * chiediConto Tavolo 1 senza aver inserito alcun tavolo
	 * 
	 */
	@Test(expected=InvalidInputException.class)
	public void testChiediContoNoTavoli()throws InvalidInputException{
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int numTavolo=1;
		@SuppressWarnings("unused")
		double conto=gestOrdinazioni.chiediConto(numTavolo);
	}
	
	
	/*Test 17
	 * 
	 * chiediConto tavolo maggiore del num totale di tavoli
	 * 
	 */
	@Test(expected=InvalidInputException.class)
	public void testChiediContoNumTavoloTroppoGrande()throws InvalidInputException{
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int tavoloRichiesto=2;
		int numeroTavolo = 1;
		int maxCoperti = 7;
		double costoCoperto = 2;
		
	
		ITavolo tavolo = createMock(Tavolo.class);
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti), EasyMock.eq(costoCoperto))).andReturn(tavolo);
		expect(tavolo.isLibero()).andReturn(true);
		expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		tavolo.setCoperti(maxCoperti);

		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		
		replay(tavolo, tavoloCreator);
		//end
		
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		gestOrdinazioni.chiediConto(tavoloRichiesto);

	}
	
	
	/*Test 18
	 * 
	 * chiediConto su Tavolo Libero
	 * 
	 */
	@Test(expected=InvalidInputException.class)
	public void testChiediContoSuTavoloLibero() throws InvalidInputException{
		IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		
		int tavoloRichiesto=1;
		int numeroTavolo = 1;
		int maxCoperti = 7;
		double costoCoperto = 2;
		
	
		ITavolo tavolo = createMock(Tavolo.class);
		ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		
		//Record and playback
		expect(tavoloCreator.creaTavolo(EasyMock.geq(1),EasyMock.eq(maxCoperti), EasyMock.eq(costoCoperto))).andReturn(tavolo);
		expect(tavolo.isLibero()).andReturn(true);
		expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		tavolo.setCoperti(maxCoperti);

		gestOrdinazioni.setTavoloCreator(tavoloCreator);
		
		replay(tavolo, tavoloCreator);
		//end
		
		gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		gestOrdinazioni.chiediConto(tavoloRichiesto);
	}
	
	
	/*Test 19
	 * 
	 * chiediConto Tavolo OK! una ordinazione senza piatti ordinati
	 * 
	 */
	@Test
	 public void testChiediContoUnOrdinazioneNoPiatti() throws InvalidInputException{
		  IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		  
		  int tavoloRichiesto=1;
		  int numeroTavolo = 1;
		  int maxCoperti = 4;
		  double costoCoperto = 3.5;
		  
		  double contoAspettato = maxCoperti * costoCoperto;
		  
		  
		  ITavolo tavolo = createMock(Tavolo.class);
		  IOrdinazione ordinazione = createMock(Ordinazione.class);
		  ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		  IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		  
		  //Record and playback
		  expect(tavoloCreator.creaTavolo(numeroTavolo,maxCoperti,costoCoperto)).andReturn(tavolo);
		  expect(tavolo.isLibero()).andReturn(true);
		  expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		  tavolo.setCoperti(maxCoperti);
		  expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo);
		  //expect relative a richiesta conto
		  expect(ordinazione.getTavolo()).andReturn(tavolo);
		  expect(ordinazione.calcolaConto()).andReturn(contoAspettato);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo);
		  
		  gestOrdinazioni.setTavoloCreator(tavoloCreator);
		  gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		  
		  //end
		  
		  replay(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		  gestOrdinazioni.creaOrdinazione(maxCoperti);
		  double contoCalcolato = gestOrdinazioni.chiediConto(tavoloRichiesto);
		  verify(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  
		  assertEquals(contoAspettato, contoCalcolato, 0);
	}
	
	
	/*Test 20
	 * 
	 * chiediConto Tavolo OK! una ordinazione - 1 piatto ordinato
	 * 
	 */
	@Test
	public void testChiediContoUnOrdinazioneUnPiatto() throws InvalidInputException {
		  IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		  
		  int tavoloRichiesto=1;
		  int numeroTavolo = 1;
		  int maxCoperti = 4;
		  double costoCoperto = 3.5;
		  
		  String nomePiatto = "Tagliatelle di nonna Pina";
		  double prezzoPiatto = 99.99;
		  
		  double contoAspettato = prezzoPiatto + (maxCoperti * costoCoperto);
		  
		  
		  ITavolo tavolo = createMock(Tavolo.class);
		  IOrdinazione ordinazione = createMock(Ordinazione.class);
		  ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		  IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		  
		  //Record and playback
		  expect(tavoloCreator.creaTavolo(numeroTavolo,maxCoperti,costoCoperto)).andReturn(tavolo);
		  expect(tavolo.isLibero()).andReturn(true);
		  expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		  tavolo.setCoperti(maxCoperti);
		  expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo).times(2);

		  expect(ordinazione.getTavolo()).andReturn(tavolo).times(2);
		  ordinazione.ordinaPiatto((IPiatto) and(anyObject(), eq(new Piatto(nomePiatto, prezzoPiatto))));
		  expect(ordinazione.calcolaConto()).andReturn(contoAspettato);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo);
		  
		  gestOrdinazioni.setTavoloCreator(tavoloCreator);
		  gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		  
		  //end
		  
		  replay(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		  gestOrdinazioni.creaOrdinazione(maxCoperti);
		  gestOrdinazioni.ordinaPiatto(tavoloRichiesto, nomePiatto, prezzoPiatto);
		  double contoCalcolato = gestOrdinazioni.chiediConto(tavoloRichiesto);
		  verify(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  
		  assertEquals(contoAspettato, contoCalcolato, 0);
	}
	
	
	/*Test 21
	 * 
	 * chiediConto Tavolo OK! una ordinazione - 2 piatto ordinato
	 * 
	 */
	@Test
	public void testChiediContoUnOrdinazioneDuePiatti() throws InvalidInputException{
		  IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		  
		  int tavoloRichiesto=1;
		  int numeroTavolo = 1;
		  int maxCoperti = 4;
		  double costoCoperto = 3.5;
		  
		  String nomePiatto1 = "Tagliatelle di nonna Pina";
		  double prezzoPiatto1 = 99.99;
		  
		  String nomePiatto2 = "Cotoletta alla milanese";
		  double prezzoPiatto2 = 49.99;
		  
		  double contoAspettato = prezzoPiatto1 + prezzoPiatto2 + (maxCoperti * costoCoperto);
		  
		  
		  ITavolo tavolo = createMock(Tavolo.class);
		  IOrdinazione ordinazione = createMock(Ordinazione.class);
		  ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		  IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		  
		  //Record and playback
		  expect(tavoloCreator.creaTavolo(numeroTavolo,maxCoperti,costoCoperto)).andReturn(tavolo);
		  expect(tavolo.isLibero()).andReturn(true);
		  expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		  tavolo.setCoperti(maxCoperti);
		  expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo).times(3);

		  expect(ordinazione.getTavolo()).andReturn(tavolo).times(3);
		  ordinazione.ordinaPiatto((IPiatto) and(anyObject(), eq(new Piatto(nomePiatto1, prezzoPiatto1))));
		  ordinazione.ordinaPiatto((IPiatto) and(anyObject(), eq(new Piatto(nomePiatto2, prezzoPiatto2))));
		  expect(ordinazione.calcolaConto()).andReturn(contoAspettato);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo);
		  
		  gestOrdinazioni.setTavoloCreator(tavoloCreator);
		  gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		  
		  //end
		  
		  replay(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		  gestOrdinazioni.creaOrdinazione(maxCoperti);
		  gestOrdinazioni.ordinaPiatto(tavoloRichiesto, nomePiatto1, prezzoPiatto1);
		  gestOrdinazioni.ordinaPiatto(tavoloRichiesto, nomePiatto2, prezzoPiatto2);
		  double contoCalcolato = gestOrdinazioni.chiediConto(tavoloRichiesto);
		  verify(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  
		  assertEquals(contoAspettato, contoCalcolato, 0);
	}
	
	
	/*Test 22
	 * 
	 * chiediConto su Ordinazioni molteplici ordina un piatto su una sola ord
	 * 
	 */
	@Test
	public void testChiediContoMoltepliciOrdinazioniUnPiatto() throws InvalidInputException{
		  IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		  
		  int tavoloRichiesto=2;
		  
		  int numeroTavolo1 = 1;
		  int maxCoperti1 = 4;
		  double costoCoperto1 = 3.5;
		  
		  int numeroTavolo2 = 2;
		  int maxCoperti2 = 6;
		  double costoCoperto2 = 2;
		  
		  String nomePiatto = "Tagliatelle di nonna Pina";
		  double prezzoPiatto = 99.99;
		  
		  double contoAspettato = prezzoPiatto + (maxCoperti2 * costoCoperto2);
		  
		  
		  ITavolo tavolo1 = createMock(Tavolo.class);
		  ITavolo tavolo2 = createMock(Tavolo.class);
		  IOrdinazione ordinazione1 = createMock(Ordinazione.class);
		  IOrdinazione ordinazione2 = createMock(Ordinazione.class);
		  ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		  IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		  
		  //Record and playback
		  expect(tavoloCreator.creaTavolo(numeroTavolo1,maxCoperti1,costoCoperto1)).andReturn(tavolo1);
		  expect(tavoloCreator.creaTavolo(numeroTavolo2,maxCoperti2,costoCoperto2)).andReturn(tavolo2);
		  expect(tavolo1.isLibero()).andReturn(true);
		  expect(tavolo2.isLibero()).andReturn(true);
		  expect(tavolo1.getMaxCoperti()).andReturn(maxCoperti1);
		  expect(tavolo2.getMaxCoperti()).andReturn(maxCoperti2);
		  tavolo2.setCoperti(maxCoperti2);
		  expect(ordinazioneCreator.creaOrdinazione(tavolo2)).andReturn(ordinazione2);
		  expect(tavolo2.getNumero()).andReturn(numeroTavolo2).times(3);
		  expect(ordinazione2.getTavolo()).andReturn(tavolo2).times(2);
		  
		  ordinazione2.ordinaPiatto((IPiatto) and(anyObject(), eq(new Piatto(nomePiatto, prezzoPiatto))));
		  expect(ordinazione2.calcolaConto()).andReturn(contoAspettato);
		  
		  
		  //end
		  
		  gestOrdinazioni.setTavoloCreator(tavoloCreator);
		  gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		  
		  
		  replay(tavolo1, tavolo2, ordinazione1, ordinazione2, tavoloCreator, ordinazioneCreator);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti1, costoCoperto1);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti2, costoCoperto2);
		  int tavoloAssegnato = gestOrdinazioni.creaOrdinazione(maxCoperti2);
		  gestOrdinazioni.ordinaPiatto(tavoloRichiesto, nomePiatto, prezzoPiatto);
		  double contoCalcolato = gestOrdinazioni.chiediConto(tavoloRichiesto);
		  verify(tavolo1, tavolo2, ordinazione1, ordinazione2, tavoloCreator, ordinazioneCreator);
		  
		  assertEquals(tavoloAssegnato, tavoloRichiesto);
		  assertEquals(contoAspettato, contoCalcolato, 0);
	}
	
	
	/*Test 23
	 * 
	 * chiediConto su Ordinazioni molteplici ordina un piatto per ogni ordinazione
	 * 
	 */
	@Test
	public void testChiediContoMoltepliciOrdinazioniDuePiatti() throws InvalidInputException {
		  IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		  
		  int tavoloRichiesto=2;
		  
		  int numeroTavolo1 = 1;
		  int maxCoperti1 = 4;
		  double costoCoperto1 = 3.5;
		  
		  int numeroTavolo2 = 2;
		  int maxCoperti2 = 6;
		  double costoCoperto2 = 2;
		  
		  String nomePiatto = "Tagliatelle di nonna Pina";
		  double prezzoPiatto = 99.99;
		  
		  double contoAspettato = prezzoPiatto + (maxCoperti2 * costoCoperto2);
		  
		  
		  ITavolo tavolo1 = createMock(Tavolo.class);
		  ITavolo tavolo2 = createMock(Tavolo.class);
		  IOrdinazione ordinazione1 = createMock(Ordinazione.class);
		  IOrdinazione ordinazione2 = createMock(Ordinazione.class);
		  ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		  IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		  
		  //Record and playback
		  expect(tavoloCreator.creaTavolo(numeroTavolo1,maxCoperti1,costoCoperto1)).andReturn(tavolo1);
		  expect(tavoloCreator.creaTavolo(numeroTavolo2,maxCoperti2,costoCoperto2)).andReturn(tavolo2);
		  expect(tavolo1.isLibero()).andReturn(true);
		  expect(tavolo2.isLibero()).andReturn(true);
		  expect(tavolo1.getMaxCoperti()).andReturn(maxCoperti1);
		  expect(tavolo2.getMaxCoperti()).andReturn(maxCoperti2);
		  tavolo2.setCoperti(maxCoperti2);
		  expect(ordinazioneCreator.creaOrdinazione(tavolo2)).andReturn(ordinazione2);
		  expect(tavolo2.getNumero()).andReturn(numeroTavolo2).times(3);
		  expect(ordinazione2.getTavolo()).andReturn(tavolo2).times(2);
		  
		  ordinazione2.ordinaPiatto((IPiatto) and(anyObject(), eq(new Piatto(nomePiatto, prezzoPiatto))));
		  expect(ordinazione2.calcolaConto()).andReturn(contoAspettato);
		  
		  
		  //end
		  
		  gestOrdinazioni.setTavoloCreator(tavoloCreator);
		  gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		  
		  
		  replay(tavolo1, tavolo2, ordinazione1, ordinazione2, tavoloCreator, ordinazioneCreator);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti1, costoCoperto1);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti2, costoCoperto2);
		  int tavoloAssegnato = gestOrdinazioni.creaOrdinazione(maxCoperti2);
		  gestOrdinazioni.ordinaPiatto(tavoloRichiesto, nomePiatto, prezzoPiatto);
		  double contoCalcolato = gestOrdinazioni.chiediConto(tavoloRichiesto);
		  verify(tavolo1, tavolo2, ordinazione1, ordinazione2, tavoloCreator, ordinazioneCreator);
		  
		  assertEquals(tavoloAssegnato, tavoloRichiesto);
		  assertEquals(contoAspettato, contoCalcolato, 0);
		  
		  fail("questo test e' il copia e incolla del test 22");
	}
	
	
	/*Test 24
	 * 
	 * chiediConto su Ordinazione non piu' attiva
	 */
	@Test(expected=InvalidInputException.class)
	public void testChiediContoOrdinazioniNonAttiva() throws InvalidInputException {
		  IGestoreOrdinazioni gestOrdinazioni = new GestoreOrdinazioni();
		  
		  int tavoloRichiesto=1;
		  int numeroTavolo = 1;
		  int maxCoperti = 4;
		  double costoCoperto = 3.5;
		  
		  double contoAspettato = maxCoperti * costoCoperto;
		  
		  
		  ITavolo tavolo = createMock(Tavolo.class);
		  IOrdinazione ordinazione = createMock(Ordinazione.class);
		  ITavoloCreator tavoloCreator = createMock(TavoloCreator.class);
		  IOrdinazioneCreator ordinazioneCreator = createMock(OrdinazioneCreator.class);
		  
		  //Record and playback
		  expect(tavoloCreator.creaTavolo(numeroTavolo,maxCoperti,costoCoperto)).andReturn(tavolo);
		  expect(tavolo.isLibero()).andReturn(true);
		  expect(tavolo.getMaxCoperti()).andReturn(maxCoperti);
		  tavolo.setCoperti(maxCoperti);
		  expect(ordinazioneCreator.creaOrdinazione(tavolo)).andReturn(ordinazione);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo);
		  //expect relative a richiesta conto
		  expect(ordinazione.getTavolo()).andReturn(tavolo).times(1);
		  expect(ordinazione.calcolaConto()).andReturn(contoAspettato);
		  expect(tavolo.getNumero()).andReturn(numeroTavolo);
		  
		  gestOrdinazioni.setTavoloCreator(tavoloCreator);
		  gestOrdinazioni.setOrdinazioneCreator(ordinazioneCreator);
		  
		  //end
		  
		  replay(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  gestOrdinazioni.aggiungiTavolo(maxCoperti, costoCoperto);
		  gestOrdinazioni.creaOrdinazione(maxCoperti);
		  double contoCalcolato = gestOrdinazioni.chiediConto(tavoloRichiesto);
		  verify(tavolo, ordinazione, tavoloCreator, ordinazioneCreator);
		  
		  assertEquals(contoAspettato, contoCalcolato, 0);
		  
		  gestOrdinazioni.chiediConto(tavoloRichiesto);
	}
}

