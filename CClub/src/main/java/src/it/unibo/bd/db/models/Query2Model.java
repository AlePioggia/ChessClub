package src.it.unibo.bd.db.models;

public class Query2Model {
	private final String cf;
	private final String name;
	private final String surname;
	private final String totaleVersamenti;
	private final String totaleSpeso;
	
	public Query2Model(String cf, String name, String surname, String totaleVersamenti, String totaleSpeso) {
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.totaleVersamenti = totaleVersamenti;
		this.totaleSpeso = totaleSpeso;
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



	public String getTotaleVersamenti() {
		return totaleVersamenti;
	}



	public String getTotaleSpeso() {
		return totaleSpeso;
	}



	@Override
	public String toString() {
		return "Query2Model [cf=" + cf + ", name=" + name + ", surname=" + surname + ", totaleVersamenti="
				+ totaleVersamenti + ", totaleSpeso=" + totaleSpeso + "]";
	}
	
}
