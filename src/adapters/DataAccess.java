package adapters;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

public class DataAccess {
	private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	private int port = Integer.parseInt(System.getenv("OPENSHIFT_MYSQL_DB_PORT"));
	private String database = System.getenv("OPENSHIFT_MYSQL_DB_NAME");
	private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
	private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
	private String connectionString;

	private Connection connection;
	private Statement statement;

	public DataAccess() {
		this.connectionString = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.database;
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = (Connection) DriverManager.getConnection(this.connectionString, this.username, this.password);
			this.statement = (Statement) this.connection.createStatement();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public DataAccess(String host, int port, String database, String username, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.username = username;
		this.password = password;

		this.connectionString = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.database;
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = (Connection) DriverManager.getConnection(this.connectionString, this.username, this.password);
			this.statement = (Statement) this.connection.createStatement();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public ResultSet getResultSet(String sql) {
		try {
			return this.statement.executeQuery(sql);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			finalize();
		}
	}

	public int executeQuery(String sql) {
		try {
			ResultSet rs = this.statement.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			finalize();
		}
	}

	protected void finalize() {
		try {
			this.statement.close();
			this.connection.close();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setHost(String host) {this.host = host;}
	public void setPort(int port) {this.port = port;}
	public void setDatabase(String database) {this.database = database;}
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}

	public String getHost() {return this.host;}
	public int getPort() {return this.port;}
	public String getDatabase() {return this.database;}
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public String getConnectionString() {return this.connectionString;}
}
