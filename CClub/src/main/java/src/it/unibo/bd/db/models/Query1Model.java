package src.it.unibo.bd.db.models;

public class Query1Model {
	private final String cf;
	private final String name;
	private final String surname;
	private final String birthDate;
	
	public Query1Model(String cf, String name, String surname, String birthDate) {
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
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



	@Override
	public String toString() {
		return "Query1Model [cf=" + cf + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + "]";
	}
	
}
