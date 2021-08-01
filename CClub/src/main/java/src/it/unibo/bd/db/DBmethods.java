package src.it.unibo.bd.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DBmethods {
	
	private final List<String> tables = Arrays.asList("tornei", "seminari", "lezioni", "competizioni_fra_circoli");
	
	public boolean checkDates(String date) throws SQLException {			
    	DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	Statement statement = connection.createStatement();
    	return this.checkDateAbsensInTable(statement, tables, date);
	}
	
	private String generalSearchQuery(String tableName, String date) {
		return "SELECT * FROM " + tableName + " WHERE Data_attivita = '" + date + "';";
	}
	
	private boolean checkDateAbsensInTable(Statement statement, List<String> tableNames, String date) throws SQLException {
		for(String s : tableNames) {
			if (statement.executeQuery(this.generalSearchQuery(s, date)).next()) {
				return false;
			}
		}
		return true;
	}
	
	public Statement dbCommunication() throws SQLException {
		DBConnection dataSource = new DBConnection();
    	Connection connection = dataSource.getMySQLConnection();
    	return connection.createStatement();
	}
	
}
