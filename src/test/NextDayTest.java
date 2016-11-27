package test;

import java.time.LocalDate;

import org.junit.Test;

import model.Reservation;

public class NextDayTest {

	@Test
	public void test() {
		LocalDate current = LocalDate.parse("9999-12-31");
		try {
			System.out.println(current);
			System.out.println(Reservation.getNextDate(current));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
