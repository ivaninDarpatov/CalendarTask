package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBConnection;

public class PriceDAO {
	private static final String INITIALIZE_WEEKDAY_PRICES_SQL = "INSERT INTO prices VALUES ('weekday', 0);";	
	private static final String INITIALIZE_WEEKEND_PRICES_SQL = "INSERT INTO prices VALUES ('weekend', 0);";
	private static final String CHANGE_WEEKDAY_PRICE_SQL = "UPDATE prices SET price = ? WHERE day_type = 'weekday';";
	private static final String CHANGE_WEEKEND_PRICE_SQL = "UPDATE prices SET price = ? WHERE day_type = 'weekend';";
	private static final String GET_WEEKDAY_PRICE_SQL = "SELECT price FROM prices WHERE day_type = 'weekday';";
	private static final String GET_WEEKEND_PRICE_SQL = "SELECT price FROM prices WHERE day_type = 'weekend';";
	
	public static void initializePrices() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(INITIALIZE_WEEKDAY_PRICES_SQL);
			ps.executeUpdate();
			ps = con.prepareStatement(INITIALIZE_WEEKEND_PRICES_SQL);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("failed to initialize prices", e);
		}
	}
	
	public static void changeWeekdayPrice(float newPrice) throws Exception {
		if (newPrice < 0) {
			throw new Exception("invalid new weekday price");
		}
		
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(CHANGE_WEEKDAY_PRICE_SQL);
			ps.setFloat(1, newPrice);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("failed to change weekday price", e);
		}
	}
	
	public static void changeWeekendPrice(float newPrice) throws Exception {
		if (newPrice < 0) {
			throw new Exception("invalid new weekday price");
		}
		
		Connection con = DBConnection.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(CHANGE_WEEKEND_PRICE_SQL);
			ps.setFloat(1, newPrice);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("failed to change weekend price", e);
		}
	}
	
	public static float getWeekdayPrice() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(GET_WEEKDAY_PRICE_SQL);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getFloat("price");
			} else {
				throw new Exception("failed to get weekday price");
			}
		} catch (SQLException e) {
			throw new Exception("failed to get weekday price", e);
		}
	}
	
	public static float getWeekendPrice() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(GET_WEEKEND_PRICE_SQL);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getFloat("price");
			} else {
				throw new Exception("failed to get weekend price");
			}
		} catch (SQLException e) {
			throw new Exception("failed to get weekend price", e);
		}
	}
}
