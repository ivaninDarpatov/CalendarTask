package test;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Set;

import org.junit.Test;

import model.Reservation;

public class DaysRangeTest {

	@Test
	public void test() {
		LocalDate sDate = LocalDate.parse("2016-01-01");
		LocalDate eDate = LocalDate.parse("2016-12-31");
		float cost = 0;
		
		try {
			Reservation res = new Reservation(sDate, eDate, cost);
			Set<LocalDate> dates = res.getDatesRange();
			assertTrue(dates.size() == 366);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
