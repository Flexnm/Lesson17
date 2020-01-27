package projectDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool { // This is a singleton.

	private static ConnectionPool instance = new ConnectionPool();
	private ArrayList<Connection> connections;
	private static final int MAX_CONNECTIONS = 10;

	private ConnectionPool() {
		connections = new ArrayList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			for (int i = 1; i <= MAX_CONNECTIONS; i++) {
				connections.add(DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/project_cupons?serverTimezone=UTC", "root", "1234"));
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to find class: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Connection failed: " + e.getMessage());
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public synchronized Connection getConnection() {
		while (connections.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		Connection con = connections.get(0);
		connections.remove(connections.get(0));
		return con;
	}

	public synchronized void restoreConnection(Connection con) {
		connections.add(con);
		notify(); // wake up the waiting thread.
	}

	public void closeAllConnections() {
		try {
			for (Connection con : connections) {
				con.close();
			}
		} catch (SQLException e) {
		}
	}
}