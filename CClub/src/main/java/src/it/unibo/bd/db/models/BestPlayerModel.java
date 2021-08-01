package src.it.unibo.bd.db.models;

public class BestPlayerModel {
	
	private final String cf;
	private final String name;
	private final String surname;
	private final String elo;
	
	public BestPlayerModel(String cf, String name, String surname, String elo) {
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.elo = elo;
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

	@Override
	public String toString() {
		return "BestPlayerModel [cf=" + cf + ", name=" + name + ", surname=" + surname + ", elo=" + elo + "]";
	}
	
}
