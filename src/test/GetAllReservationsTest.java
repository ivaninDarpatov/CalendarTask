package test;

import java.util.Set;

import org.junit.Test;

import model.Reservation;
import model.DAO.ReservationDAO;

public class GetAllReservationsTest {

	@Test
	public void test() {
		try {
			Set<Reservation> reservations = ReservationDAO.getAllReservations();
			System.out.println(reservations.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
