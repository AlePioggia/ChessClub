package src.it.unibo.bd.db.models;

public class ScacchistaModel {
	private final String cf;
	private final String name;
	private final String surname;
	private final String birthDate;
	private final String birthPlace;
	private final String elo;
	private final String nPart;
	private final String codSquadra;
	private final String codAbbonamento;
	
	public ScacchistaModel(final String cf, final String name, final String surname,
			 final String birthDate, final String birthPlace, final String elo 
			,final String nPart, final String codSquadra, final String codAbbonamento) {
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
		this.elo = elo;
		this.nPart = nPart;
		this.codSquadra = codSquadra;
		this.codAbbonamento = codAbbonamento;
	}

	public String getCf() {
		return cf;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public String getElo() {
		return elo;
	}

	public String getnPart() {
		return nPart;
	}

	public String getCodSquadra() {
		return codSquadra;
	}

	public String getCodAbbonamento() {
		return codAbbonamento;
	}

	@Override
	public String toString() {
		return "ScacchistaModel [cf=" + cf + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate
				+ ", birthPlace=" + birthPlace + ", elo=" + elo + ", nPart=" + nPart + ", codSquadra=" + codSquadra
				+ ", codAbbonamento=" + codAbbonamento + "]";
	}
		
}
