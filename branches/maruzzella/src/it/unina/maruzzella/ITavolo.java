package it.unina.maruzzella;

public interface ITavolo {
	
	public int getNumero();
	
	
	public boolean isLibero();
	public void setLibero(boolean status);
	
	public void setCoperti(int numCoperti) throws InvalidInputException;
	public int getCoperti();
	
	public int getMaxCoperti();
	
	public double calcolaCostoCoperti();
	public double getCostoCoperto();

}
