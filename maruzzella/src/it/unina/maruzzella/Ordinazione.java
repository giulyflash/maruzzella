package it.unina.maruzzella;

import java.util.*;

public class Ordinazione implements IOrdinazione {
	private ITavolo tavolo;
	private List<IPiatto> piatti;
	
	
	public Ordinazione(ITavolo tavoloAssociato)throws InvalidInputException{
		if (tavoloAssociato==null)
			throw new InvalidInputException("Tavolo NULL");
	}
	
	@Override
	public double calcolaConto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ordinaPiatto(IPiatto piattoOrdinato) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNPiattiOrdinati() {
		// TODO Auto-generated method stub
		return 0;
	}

}
