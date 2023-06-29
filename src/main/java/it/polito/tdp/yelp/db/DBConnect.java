package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {
	/*public static Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mariadb://localhost/yelp?user=root&password=root" ;
		Connection conn = DriverManager.getConnection(jdbcURL) ;
		return conn;
	}
	
	 * 
	 */
	static HikariDataSource dataSource; //singleton
	// >> oggetto di cui può esistere solo un'istanza sola
	//lo crea solo una volta sola, se non è inizializzato lo crea, altrimenti viene solo richiamato tramite il get
	
	
	
	public static Connection getConnection() throws SQLException{
		if( dataSource == null) {
			//crea dS
			dataSource = new HikariDataSource();
			dataSource.setJdbcUrl("jdbc:mariadb://localhost/yelp");
			dataSource.setUsername("root");
			dataSource.setPassword("root");
			
		}
		return dataSource.getConnection();
		
	}
	
}
