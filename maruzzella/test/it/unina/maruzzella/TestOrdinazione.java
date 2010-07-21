package it.unina.maruzzella;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class TestOrdinazione {

	
	@Test
	public void testCostruttoreOrdinazione(){
		ITavolo tavolo= createMock(Tavolo.class);
		
		
		IOrdinazione ordinazione = new Ordinazione(tavolo);
		
		assertNotNull(ordinazione);
	}
}
