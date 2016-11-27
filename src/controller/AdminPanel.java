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
		String password = request.getParameter("password");
		if (password.equals("123")) {
			doGet(request, response);			
		} else {
			response.sendRedirect("index.jsp");
		}
	}

}
