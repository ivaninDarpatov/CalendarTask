package controller;

import java.io.IOException;
import java.time.LocalDate;
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

@WebServlet("/user")
public class UserPanel extends HttpServlet {
	private static final long serialVersionUID = 5948471139866223109L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		float[] prices = new float[2];
		
		try {
			Gson jsonMaker = new Gson();
			
			prices[0] = PriceDAO.getWeekdayPrice();
			prices[1] = PriceDAO.getWeekendPrice();
			String pricesJSON = jsonMaker.toJson(prices);
			
			request.setAttribute("prices", pricesJSON);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("user_panel.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = "ok";

		String newResJSON = request.getHeader("toReserve");
		int month = Integer.parseInt(request.getHeader("currentMonth"));
		int year = Integer.parseInt(request.getHeader("currentYear"));
		Gson jsonMaker = new Gson();

		if (newResJSON != null) {
			
			newResJSON = request.getHeader("toReserve");
			String[] newRes = jsonMaker.fromJson(newResJSON, String[].class);
			
			String startDate = newRes[0];
			String endDate = newRes[1];
			String costStr = newRes[2];
			
			LocalDate sDate = LocalDate.parse(startDate);
			LocalDate eDate = LocalDate.parse(endDate);
			float cost = Float.parseFloat(costStr);
			
			try {			
				ReservationDAO.addReservation(new Reservation(sDate, eDate, cost));
			} catch (Exception e) {
				status = "fail";
			}
		}

		
		Set<LocalDate> reservedDates;
		try {
			reservedDates = ReservationDAO.getAllReservedDatesByMonth(month, year);
			String reservedDatesJSON = jsonMaker.toJson(reservedDates);	
			
			response.setHeader("reserved", reservedDatesJSON);	
		} catch (Exception e) {
			status = "fail";
		}

		response.addHeader("status", status);
		
		RequestDispatcher rd = request.getRequestDispatcher("user_panel.jsp");
		rd.forward(request, response);
	}

}
