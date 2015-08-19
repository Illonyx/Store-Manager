package store.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DBBase {
	
	protected static final EntityManager em = EntityManager.getInstance();
	
	protected static Connection connection = null;
	protected static PreparedStatement ps = null;
	protected static Statement stmt = null;
	protected static ResultSet rs = null;
	
	final static Logger LOG = LoggerFactory.getLogger(DBBase.class);
	
	public static void closeDb() {
		try {
			if (rs != null)
				rs.close();

			if (ps != null)
				ps.close();

			if (stmt != null)
				stmt.close();

			if (connection != null) {
				connection.close();
				em.closeConnection();
			}

		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}

		rs = null;
		ps = null;
		stmt = null;
		connection = null;
	}
	
	
}
