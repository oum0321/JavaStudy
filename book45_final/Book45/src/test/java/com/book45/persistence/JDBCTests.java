package com.book45.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testConnection() {
		try (Connection conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe","book45", "1234")) {
			
			log.info(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	}
}
