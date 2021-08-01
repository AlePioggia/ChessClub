package src.it.unibo.bd.db.models;

public class ClassificaModel {
	private final String cf;
	private final String name;
	private final String surname;
	private final String elo;
	private final int punteggio;
	
	public ClassificaModel(String cf, String name, String surname, String elo, int punteggio) {
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.elo = elo;
		this.punteggio = punteggio;
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

	public String getElo() {
		return elo;
	}

	public int getPunteggio() {
		return punteggio;
	}

	@Override
	public String toString() {
		return "ClassificaModel [cf=" + cf + ", name=" + name + ", surname=" + surname + ", elo=" + elo + ", punteggio="
				+ punteggio + "]";
	}
	
}
