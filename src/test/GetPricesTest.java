package test;

import org.junit.Test;

import model.DAO.PriceDAO;

public class GetPricesTest {

	@Test
	public void test() {
		try {
			System.out.println(PriceDAO.getWeekdayPrice());
			System.out.println(PriceDAO.getWeekendPrice());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
