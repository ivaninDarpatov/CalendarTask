package test;

import org.junit.Test;

import model.DAO.PriceDAO;

public class InitializePricesTest {

	@Test
	public void test() {
		try {
			PriceDAO.initializePrices();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
