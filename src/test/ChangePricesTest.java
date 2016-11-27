package test;

import org.junit.Test;

import model.DAO.PriceDAO;

public class ChangePricesTest {

	@Test
	public void test() {
		try {
			PriceDAO.changeWeekdayPrice(10.5F);
			PriceDAO.changeWeekendPrice(11.5F);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
