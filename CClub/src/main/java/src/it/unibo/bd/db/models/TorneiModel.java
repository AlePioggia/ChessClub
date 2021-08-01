package src.it.unibo.bd.db.models;

public class TorneiModel {
	
	private final String dataAttivita;
	private final String orarioInizio;
	private final String orarioFine;
	private final String eloRange;
	private final String tempo;
	private final String incremento;
	
	public TorneiModel(String dataAttivita, String orarioInizio,String orarioFine, String eloRange, String tempo, String incremento) {
		this.dataAttivita = dataAttivita;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.eloRange = eloRange;
		this.tempo = tempo;
		this.incremento = incremento;
	}

	public String getDataAttivita() {
		return this.dataAttivita;
	}

	public String getOrarioInizio() {
		return this.orarioInizio;
	}

	public String getOrarioFine() {
		return this.orarioFine;
	}
	
	public String getEloRange() {
		return this.eloRange;
	}

	public String getTempo() {
		return this.tempo;
	}

	public String getIncremento() {
		return incremento;
	}

	@Override
	public String toString() {
		return "TorneiModel [dataAttivita=" + dataAttivita + ", orarioInizio=" + orarioInizio + ", eloRange=" + eloRange
				+ ", tempo=" + tempo + ", incremento=" + incremento + "]";
	}

}
