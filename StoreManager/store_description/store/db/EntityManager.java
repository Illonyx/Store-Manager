package store.db;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityManager {
	
	private final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private Class<? extends java.sql.Driver> DRIVER;
	String url = "jdbc:mysql://localhost:3306/";
	private Connection conn;
	final static Logger LOG = LoggerFactory.getLogger(EntityManager.class);
	
	public String user = "root", password="noruda";

	//--------------------------------------------------------------------------------------
	//Permet de créer le singleton
	//--------------------------------------------------------------------------------------
	
	private EntityManager() {
	}

	private static class EntityManagerHolder {
		/** Instance unique non préinitialisée */
		private final static EntityManager instance = new EntityManager();
	}

	/** Point d'accès pour l'instance unique du singleton */
	public static EntityManager getInstance() {
		return EntityManagerHolder.instance;
	}
	
	//---------------------------------------------------------------------------
	// Driver and connection managers
	//---------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	private synchronized void loadDriver() {
		if (null == DRIVER) {
			try {
				DRIVER = (Class<? extends Driver>) Class
						.forName(DB_DRIVER_CLASS);
			} catch (ClassNotFoundException e) {
				LOG.error("Driver {} not found.", DB_DRIVER_CLASS, e);
			}
		}
	}
	
	public Connection getConnection(String dbName) throws SQLException{
		conn = DriverManager.getConnection(url+dbName,user,password);
		return conn;
	}
	
	public Connection getStoreDB() throws SQLException{
		loadDriver();
		return getConnection("store");
	}

	//----------------------------------------------------------
	// Close Connection and DB
	//----------------------------------------------------------
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
	}
	
	
}