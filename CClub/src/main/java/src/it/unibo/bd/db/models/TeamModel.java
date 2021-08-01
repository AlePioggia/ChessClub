package src.it.unibo.bd.db.models;

public class TeamModel {

	private final String teamCode;
	private final String teamName;
	
	public TeamModel(final String teamCode, final String teamName  ) {
		this.teamCode = teamCode;
		this.teamName = teamName;
	}

	public String getTeamCode() {
		return this.teamCode;
	}

	public String getTeamName() {
		return this.teamName;
	}

	@Override
	public String toString() {
		return "TeamModel [teamCode=" + teamCode + ", teamName=" + teamName + "]";
	}
	
	
}
