package org.zerock.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import lombok.extern.log4j.Log4j;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
	,"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class JDBCTest {

	@Test
	public void test() {
		
		  Connection con = null;
		  
		  try { 
		  con = DriverManager.getConnection("jdbc:mysql://localhost","pjm","pjmpjm");
		  Statement st = null; ResultSet rs = null;
		  
		  st = con.createStatement(); rs = st.executeQuery("SHOW DATABASES");
		  
		  if (st.execute("SHOW DATABASES")) { 
			  rs = st.getResultSet(); }
		  
		  while (rs.next()) { 
			  log.info(rs.getNString(1)); }
		  } catch (SQLException e) { 
			 log.error("SQLException: " + e.getMessage());
		     log.error("SQLState: " + e.getSQLState()); }

	}
	
}
