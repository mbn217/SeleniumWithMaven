package dBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;

import utilities.Environment;

public class dbUtils {



	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	static Environment env;
	public static void establishConnection() throws SQLException, ClassNotFoundException {
		env = ConfigFactory.create(Environment.class);
		String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		Class.forName(JDBC_DRIVER);
			try {
				connection = DriverManager.getConnection(env.geturl(),
						env.getDBUsername(),
						env.getDBPassword());
				System.out.println("Connection Successfully established");
			} catch (Exception e) {
				System.out.println(" SQL Connection Failed... ");
			}

		
	}
	
	public static int getRowsCount(String sql) throws SQLException {
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery(sql);
		resultSet.last();
		return resultSet.getRow();
	}
	
	public static List<Map<String,Object>> runSQLQuery(String sql) throws SQLException{
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery(sql);
		
		List<Map<String,Object>> list = new ArrayList<>();
		ResultSetMetaData rsMdata = resultSet.getMetaData();
		  
		int colCount = rsMdata.getColumnCount();
		  
		while(resultSet.next()) {
			  Map<String,Object> rowMap = new HashMap<>();
			  
			  for(int col = 1; col <= colCount; col++) {
				  rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));	  
			  }
			  
			  
			  list.add(rowMap);
		}
		
		return list;
		
	}
	
	public static void closeConnections() {
		try{
			if(resultSet != null) {
				resultSet.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getSingleDbData(String query) throws SQLException {
		try {
		//getDBConnection();
		String returnString = "";
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		System.out.println("\nExecuting query : " + query);
		resultSet = statement.executeQuery(query);
		resultSet.next();
		returnString = resultSet.getString(1);
		System.out.println("\nReturnResult : " +returnString);
		return returnString;
		}
		finally {
			if (statement != null) {
				statement.close();
				}
			}
	}
	

}

