package gr.uoi.cse.countrygraph.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory
{
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/country_stat_db?serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	
	public final Connection createConnection() throws SQLException
	{
		return DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
	}
	
	public static final ConnectionFactory getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		private static final ConnectionFactory INSTANCE = new ConnectionFactory();
	}
}