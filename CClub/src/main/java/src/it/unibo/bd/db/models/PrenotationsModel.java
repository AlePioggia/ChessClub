package src.it.unibo.bd.db.models;

public class PrenotationsModel {
	private final String cf;
	private final String dataTorneo;
	private final String dataLezione;
	private final String dataSeminario;
	private final String dataCompetizione;
	
	public PrenotationsModel(String cf, String dataTorneo, String dataLezione, String dataSeminario,
			String dataCompetizione) {
		this.cf = cf;
		this.dataTorneo = dataTorneo;
		this.dataLezione = dataLezione;
		this.dataSeminario = dataSeminario;
		this.dataCompetizione = dataCompetizione;
	}

	public String getCf() {
		return cf;
	}

	public String getDataTorneo() {
		return dataTorneo;
	}

	public String getDataLezione() {
		return dataLezione;
	}

	public String getDataSeminario() {
		return dataSeminario;
	}

	public String getDataCompetizione() {
		return dataCompetizione;
	}

	@Override
	public String toString() {
		return "PrenotationsModel [cf=" + cf + ", dataTorneo=" + dataTorneo + ", dataLezione=" + dataLezione
				+ ", dataSeminario=" + dataSeminario + ", dataCompetizione=" + dataCompetizione + "]";
	}
	
}
