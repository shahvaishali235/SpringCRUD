package com.example.springCRUD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SpringCrudApplication {

    public SpringCrudApplication() throws SQLException {
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringCrudApplication.class, args);


			String url = "jdbc:mysql://localhost:3306/user_db";
			String username = "root";
			String password = "root";


	 try {
		Connection conn = DriverManager.getConnection(url, username, password);

	} catch (SQLException e) {
		throw new RuntimeException(e);
	}


		//System.out.println("✅ Connection successful!");

}}
