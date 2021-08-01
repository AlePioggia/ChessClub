package src.it.unibo.bd.db.models;

public class MostPrenotationsModel {

	private final String cf;
	private final String name;
	private final String surname;
	private final String prenotations;
	
	public MostPrenotationsModel(String cf, String name, String surname, String nPrenotations) {
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.prenotations = nPrenotations;
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

	public String getPrenotations() {
		return prenotations;
	}

	@Override
	public String toString() {
		return "MostPrenotationsModel [cf=" + cf + ", name=" + name + ", surname=" + surname + ", nPrenotations="
				+ prenotations + "]";
	}
	
}
