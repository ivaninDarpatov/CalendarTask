package test;

import java.time.LocalDate;

import org.junit.Test;

import model.Reservation;
import model.DAO.ReservationDAO;

public class AddReservationTest {

	@Test
	public void test() {
		LocalDate sDate = LocalDate.parse("2016-11-09");
		LocalDate eDate = LocalDate.parse("2016-12-04");
		float cost = 100F;
		try {
			ReservationDAO.addReservation(new Reservation(sDate, eDate, cost));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
