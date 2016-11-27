package test;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Test;

import model.DAO.ReservationDAO;

public class GetAllReservedDatesTest {

	@Test
	public void test() {
		try {
			Set<LocalDate> dates = ReservationDAO.getAllReservedDates();
			System.out.println(dates.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
