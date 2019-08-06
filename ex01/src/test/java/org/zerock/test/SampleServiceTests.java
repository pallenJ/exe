package org.zerock.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.SampleService;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {

	  @Setter(onMethod_ = {@Autowired})
	  private SampleService service;
	
	  //@Test
	  public void testClass() throws SQLException {
		  
		  MysqlDataSource ds = new MysqlDataSource();
	        ds.setDatabaseName("mysql");
	        ds.setUser("root");

	        try (Connection conn = ds.getConnection()) {
	          try (Statement stmt = conn.createStatement()) {
	            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (name VARCHAR(40), price INT)");// 테이블 생성
	          }

	          try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO products VALUES (?, ?)")) {
	            stmt.setString(1, "bike");
	            stmt.setInt(2, 10900);
	            stmt.executeUpdate();
	            stmt.setString(1, "shoes");
	            stmt.setInt(2, 7400);
	            stmt.executeUpdate();
	            stmt.setString(1, "phone");
	            stmt.setInt(2, 29500);
	            stmt.executeUpdate();
	          }//넣을 값 입력

	          try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE name = ?")) {
	            stmt.setString(1, "shoes");
	            ResultSet rs = stmt.executeQuery();// INSERT 쿼리 실행
	            rs.next();
	            System.out.println(rs.getInt(2));// 2번째 넣은 값 즉 10900이 나오면 성공
	          }
	        }
		  
		  log.info(service);
		  log.info(service.getClass().getName());
	  }
	  @Test
	  public void testAdd() throws Exception {
		  log.info(service.doAdd("311", "23"));
	  }
	  
}
