package it.unina.maruzzella;

public class Tavolo implements ITavolo {
	
	int numero;//Numero del tavolo
	int coperti;//Numero delle persone sedute al tavolo
	int maxCoperti;//Numero massimo di coperti
	double costoCoperto;
	boolean libero;//Indica lo stato del tavolo
		
		
	
	
	public Tavolo(int numero, int maxCoperti, double costoCoperto) {
		super();
		this.numero = numero;
		this.maxCoperti = maxCoperti;
		this.costoCoperto = costoCoperto;
	}

	@Override
	public boolean isLibero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setLibero(boolean status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCoperti(int numCoperti) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCoperti() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxCoperti() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcolaCostoCoperti() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumero() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCostoCoperto() {
		// TODO Auto-generated method stub
		return 0;
	}

}
