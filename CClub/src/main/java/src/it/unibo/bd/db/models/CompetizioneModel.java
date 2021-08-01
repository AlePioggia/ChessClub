package src.it.unibo.bd.db.models;

public class CompetizioneModel {
	private final String dataAttivita;
	private final String orarioInizio;
	private final String orarioFine;
	private final String codicePartecipazione;
	
	public CompetizioneModel(String dataAttivita, String orarioInizio, String orarioFine, String codicePartecipazione) {
		this.dataAttivita = dataAttivita;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.codicePartecipazione = codicePartecipazione;
	}

	public String getDataAttivita() {
		return dataAttivita;
	}

	public String getOrarioInizio() {
		return orarioInizio;
	}

	public String getOrarioFine() {
		return orarioFine;
	}

	public String getCodicePartecipazione() {
		return codicePartecipazione;
	}

	@Override
	public String toString() {
		return "CompetizioneModel [dataAttivita=" + dataAttivita + ", orarioInizio=" + orarioInizio + ", orarioFine="
				+ orarioFine + ", codicePartecipazione=" + codicePartecipazione + "]";
	}
	
}
