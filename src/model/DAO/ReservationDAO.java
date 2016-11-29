package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import DBConnection.DBConnection;
import model.Reservation;

public class ReservationDAO {
	private static final String GET_ALL_RESERVATIONS_SQL = "SELECT start_date, end_date, cost FROM reservations;";
	private static final String ADD_RESERVATION_SQL = "INSERT INTO reservations VALUES (null, ?, ?, ?);";

	private static boolean isFree(LocalDate startingDate, LocalDate endDate) throws Exception {
		Set<Reservation> reservations = ReservationDAO.getAllReservations();
		
		for (Reservation res : reservations) {
			if (!startingDate.isAfter(res.getStartingDate()) && !endDate.isBefore(res.getEndDate())) {
				return false;
			}
			
			if (startingDate.isBefore(res.getStartingDate()) && !endDate.isBefore(res.getStartingDate())) {
				return false;
			}
			
			if (endDate.isAfter(res.getEndDate()) && !startingDate.isAfter(res.getEndDate())) {
				return false;
			}
			
			if (!startingDate.isBefore(res.getStartingDate()) && !endDate.isAfter(res.getEndDate())) {
				return false;
			}
		}

		return true;
	}
	
	public static void addReservation(Reservation toAdd) throws Exception {
		if (toAdd == null) {
			throw new Exception("invalid reservation to add");
		}
		
		if (!ReservationDAO.isFree(toAdd.getStartingDate(), toAdd.getEndDate())) {
			throw new Exception("these dates are taken");
		}
		
		Connection con = DBConnection.getInstance().getConnection();
		LocalDate sDate = toAdd.getStartingDate();
		LocalDate eDate = toAdd.getEndDate();
		float cost = toAdd.getCost();
		
		try {
			PreparedStatement ps = con.prepareStatement(ADD_RESERVATION_SQL);
			ps.setDate(1, Date.valueOf(sDate));
			ps.setDate(2, Date.valueOf(eDate));
			ps.setFloat(3, cost);
			
			if (ps.executeUpdate() != 1) {
				throw new Exception("failed to add reservation");
			}
		} catch (SQLException e) {
			throw new Exception("failed to add reservation", e);
		}
	}
	
	public static Set<Reservation> getAllReservations() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(GET_ALL_RESERVATIONS_SQL);
			ResultSet rs = ps.executeQuery();
			Set<Reservation> result = new TreeSet<Reservation>((r1, r2)->r1.isBefore(r2));
			
			while (rs.next()) {
				LocalDate sDate = rs.getDate("start_date").toLocalDate();
				LocalDate eDate = rs.getDate("end_date").toLocalDate();
				float cost = rs.getFloat("cost");
				
				result.add(new Reservation(sDate, eDate, cost));
			}
			
			return result;
		} catch (SQLException e) {
			throw new Exception("failed to get all reservations", e);
		}
	}
	
	public static Set<LocalDate> getAllReservedDates() throws Exception {
		Set<Reservation> reservations = ReservationDAO.getAllReservations();
		Set<LocalDate> result = new HashSet<LocalDate>();
		
		for (Reservation res : reservations) {
			result.addAll(res.getDatesRange());
		}
		
		return result;
	}
	
	public static Set<LocalDate> getAllReservedDatesByMonth(int month, int year) throws Exception {
		Set<LocalDate> allDates = ReservationDAO.getAllReservedDates();
		Set<LocalDate> result = new HashSet<LocalDate>();
		
		for (LocalDate date : allDates) {
			if (date.getMonthValue() == month && date.getYear() == year) {
				result.add(date);
			}
		}
		
		return result;
	}
}
