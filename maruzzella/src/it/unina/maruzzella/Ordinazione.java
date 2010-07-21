package it.unina.maruzzella;

import java.util.*;

public class Ordinazione implements IOrdinazione {
	private ITavolo tavolo;
	private List<IPiatto> piatti;
	
	
	public Ordinazione(ITavolo tavoloAssociato)throws InvalidInputException{
		if (tavoloAssociato==null)
			throw new InvalidInputException("Tavolo NULL");
		
		piatti= new ArrayList<IPiatto>();
		tavolo=tavoloAssociato;
	}
	
	@Override
	public double calcolaConto() {
		double conto= tavolo.calcolaCostoCoperti();
		
		for (int i=0; i<piatti.size();i++)
			conto+=piatti.get(i).getPrezzo();
		
		
		return conto;
	}

	@Override
	public void ordinaPiatto(IPiatto piattoOrdinato) throws InvalidInputException{
		
		if (piattoOrdinato==null)
			throw new InvalidInputException("Piatto NULL");
		
		piatti.add(piattoOrdinato);

	}

	@Override
	public int getNPiattiOrdinati() {
		// TODO Auto-generated method stub
		return piatti.size();
	}

	@Override
	public IPiatto getPiattoOrdinato(int i) throws InvalidInputException{
		
		if ( (i<1) || (i>piatti.size()) )
			throw new InvalidInputException("Indice piatto non valido");
		
		return this.piatti.get(i-1);
	}
	
	@Override
	public ITavolo getTavolo(){
		return null;
	}

}
