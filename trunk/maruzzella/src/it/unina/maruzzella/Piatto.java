package it.unina.maruzzella;



public class Piatto implements IPiatto {
	private String nome;
	private double prezzo;
	

	
	public Piatto(String nome, double prezzo) throws InvalidInputException{
		super();
		
		//Controllo se prezzo >= 0
		
		if (prezzo<0)
			throw new InvalidInputException("Prezzo negativo");
		
		//Controllo se nome e' invalido (vuoto o null)
		if (nome==null)
			throw new InvalidInputException("Nome Null");
		if (nome.length()<=0)
			throw new InvalidInputException("Nome non specificato");
		
		
		
		this.nome = nome;
		this.prezzo = prezzo;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return this.prezzo;
	}
	

	public boolean equals(Object aPiatto) {
		
		if (aPiatto == null)
			return false;
		
		if (this == aPiatto)
			return true;
		
		if (this.getClass() != aPiatto.getClass())
			return false;

		IPiatto piattoDaControllare = (IPiatto) aPiatto;
		return ( (this.getNome().equals(piattoDaControllare.getNome())) &&
				 (this.getPrezzo() == piattoDaControllare.getPrezzo()));
	}
	
}
