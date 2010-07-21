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
	public void ordinaPiatto(IPiatto piattoOrdinato) throws InvalidInputException;
	
	
	//getNPiattiOrdinati
	//ritorna il numero dei piatti ordinati
	//INPUT: none
	//OUTPUT: int
	public int getNPiattiOrdinati();
	
	//getPiattoOrdinato
	//ritorna il numero dei piatti ordinati
	//INPUT: int:i = index del piatto
	//OUTPUT: iPiatto
	public IPiatto getPiattoOrdinato(int i) throws InvalidInputException;


	public ITavolo getTavolo();
}
