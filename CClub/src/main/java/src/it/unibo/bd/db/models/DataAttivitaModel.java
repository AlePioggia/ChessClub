package src.it.unibo.bd.db.models;

public class DataAttivitaModel {

	private final String dataLezioni;
	private final String dataSeminari;
	private final String dataTornei;
	private final String dataCompetizioni;
	
	public DataAttivitaModel(String dataLezioni, String dataSeminari, String dataTornei, String dataCompetizioni) {
		this.dataLezioni = dataLezioni;
		this.dataSeminari = dataSeminari;
		this.dataTornei = dataTornei;
		this.dataCompetizioni = dataCompetizioni;
	}

	public String getDataLezioni() {
		return dataLezioni;
	}

	public String getDataSeminari() {
		return dataSeminari;
	}

	public String getDataTornei() {
		return dataTornei;
	}

	public String getDataCompetizioni() {
		return dataCompetizioni;
	}

	@Override
	public String toString() {
		return "DataAttivitaModel [dataLezioni=" + dataLezioni + ", dataSeminari=" + dataSeminari + ", dataTornei="
				+ dataTornei + ", dataCompetizioni=" + dataCompetizioni + "]";
	}
	
	
}
