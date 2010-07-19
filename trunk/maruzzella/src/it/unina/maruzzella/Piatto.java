package it.unina.maruzzella;

public class Piatto implements IPiatto {
	private String nome;
	private double prezzo;
	

	
	public Piatto(String nome, double prezzo) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
