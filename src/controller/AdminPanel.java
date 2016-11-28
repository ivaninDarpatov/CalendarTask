package controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Reservation;
import model.DAO.PriceDAO;
import model.DAO.ReservationDAO;

@WebServlet("/admin")
public class AdminPanel extends HttpServlet {
	private static final long serialVersionUID = 5102927542867298982L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<Reservation> reservations;
		float[] prices = new float[2];
		
		try {
			Gson jsonMaker = new Gson();
			
			reservations = ReservationDAO.getAllReservations();
			String reservationsJSON = jsonMaker.toJson(reservations);
			
			prices[0] = PriceDAO.getWeekdayPrice();
			prices[1] = PriceDAO.getWeekendPrice();
			String pricesJSON = jsonMaker.toJson(prices);
			
			request.setAttribute("reservations", reservationsJSON);
			request.setAttribute("prices", pricesJSON);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("admin_panel.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = "ok";
		
		String newPricesJSON = request.getHeader("newPrices");
		Gson jsonMaker = new Gson();
		String[] newPrices = jsonMaker.fromJson(newPricesJSON, String[].class);
		
		String weekday = newPrices[0];
		String weekend = newPrices[1];
		
		float weekdayPrice;
		float weekendPrice;
		
		if (weekday.equals("")) {
			weekdayPrice = -1;
		} else {
			weekdayPrice = Float.parseFloat(weekday);
		}
		
		if (weekend.equals("")) {
			weekendPrice = -1;
		} else {
			weekendPrice = Float.parseFloat(weekend);
		}
		
		try {			
			float[] updatedPrices = new float[2];

			if (weekdayPrice >= 0 && weekendPrice >= 0) {
				PriceDAO.changeWeekdayPrice(weekdayPrice);
				PriceDAO.changeWeekendPrice(weekendPrice);
			} else {
				if (weekdayPrice >= 0) {
					PriceDAO.changeWeekdayPrice(weekdayPrice);
				} else {
					PriceDAO.changeWeekendPrice(weekendPrice);
				}
			}
			
			updatedPrices[0] = PriceDAO.getWeekdayPrice();
			updatedPrices[1] = PriceDAO.getWeekendPrice();
			
			newPricesJSON = jsonMaker.toJson(updatedPrices);
			response.addHeader("prices", newPricesJSON);	
		} catch (Exception e) {
			status = "fail";
		}

		response.addHeader("status", status);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin_panel.jsp");
		rd.forward(request, response);
	}

}
