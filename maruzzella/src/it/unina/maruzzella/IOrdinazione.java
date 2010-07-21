package it.unina.maruzzella;

public interface IOrdinazione {

	//calcolaConto
	//Calculate bill for associated table comprensive of contoCoperti
	//INPUT: none
	//OUTPUT: double representing bill
	public double calcolaConto();
	
	
	//ordinaPiatto
	//Add an IPiatto implementation Istance to the Ordinazione
	//INPUT: IPiatto implementation istance
	//OUTPUT: void
	public void ordinaPiatto(IPiatto piatto_ordinato);
}
