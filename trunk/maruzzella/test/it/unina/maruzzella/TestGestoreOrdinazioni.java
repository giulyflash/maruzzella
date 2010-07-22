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

}
