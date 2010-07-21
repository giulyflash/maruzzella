package it.unina.maruzzella;

import java.util.*;

public class Ordinazione implements IOrdinazione {
	private ITavolo tavolo;
	private List<IPiatto> piatti;
	
	
	public Ordinazione(ITavolo tavoloAssociato)throws InvalidInputException{
		if (tavoloAssociato==null)
			throw new InvalidInputException("Tavolo NULL");
		
		piatti= new ArrayList<IPiatto>();
	}
	
	@Override
	public double calcolaConto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void ordinaPiatto(IPiatto piattoOrdinato) {
		// TODO Auto-generated method stub
		piatti.add(piattoOrdinato);

	}

	@Override
	public int getNPiattiOrdinati() {
		// TODO Auto-generated method stub
		return piatti.size();
	}

	@Override
	public IPiatto getPiattoOrdinato(int i) {
		// TODO Auto-generated method stub
		return this.piatti.get(i-1);
	}

}
