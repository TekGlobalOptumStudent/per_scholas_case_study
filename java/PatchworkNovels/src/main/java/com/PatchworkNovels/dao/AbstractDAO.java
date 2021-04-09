package com.PatchworkNovels.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO {

	// JPA variables
	
	private static final String PERSISTENCE_UNIT_NAME = "PatchworkNovels";
	protected static EntityManagerFactory emf = null;
	protected static EntityManager em = null;
	
	// JDBC variables
	
	private static final String DATABASE_NAME = "patchworknovels";
	private static Connection connection;
	private static Statement statement;
	
	// JPA connect methods
	
	public static boolean connect() {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = emf.createEntityManager();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void dispose() {
		if(em != null && em.isOpen()) em.close();
		if(emf != null && emf.isOpen()) emf.close();
	}

	public static boolean runSQLFile(String filePath) {
		if(connect()) {
			try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./resources/sql/" + filePath))) {
				StringBuffer query = new StringBuffer();
				String line;
				while((line = bufferedReader.readLine()) != null) {
					query.append(line);
					if(line.contains(";")) {
						em.getTransaction().begin();
						em.createNativeQuery(query.toString()).executeUpdate();
						em.getTransaction().commit();
						query = new StringBuffer();
					}
				}
				return true;
			} catch(Exception e) {
				//e.printStackTrace();
			}
		}
		dispose();
		return false;
	}
	
	public static boolean createTables() {
		boolean ret = false;
		if(connect()) {
			em.getTransaction().begin();
			em.getTransaction().commit();
			ret = true;
		}
		dispose();
		return ret;
	}

	// JDBC methods
	
	public static boolean startJDBC(int databaseType, String user, String pass) {
		try {
		String dbUrl;
		// 1. mariadb
		// 2. mysql
		if(databaseType == 1) {
			//DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
			dbUrl = "jdbc:mariadb://localhost:3306/";
		} else {
			//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			dbUrl = "jdbc:mysql://localhost:3306/";
		}
		connection = DriverManager.getConnection(dbUrl, user, pass);
		return true;
		} catch(Exception e) {
			System.out.println("Please make sure you have configured the persistence.xml file with");
			System.out.println("your database details and that your database program is running.");
		}
		return false;
	}
	
	public static boolean stopJDBC() {
		try {
			statement.close();
			connection.close();
			return true;
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createDatabase() {
		try {
			statement = connection.createStatement();
			statement.execute("CREATE database IF NOT EXISTS " + DATABASE_NAME);
			statement.execute("USE " + DATABASE_NAME);
			return true;
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return false;
	}
	
	public static boolean dropDatabase() {
		try {
			statement = connection.createStatement();
			statement.execute("DROP database IF EXISTS " + DATABASE_NAME);
			return true;
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return false;
	}
}
