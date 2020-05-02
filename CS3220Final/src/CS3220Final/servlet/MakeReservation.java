package CS3220Final.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CS3220Final.servlet.model.Reservation;


@WebServlet("/MakeReservation")
public class MakeReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/RoomReservation.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name").trim();
		if(name==null || name.equals(""))
			response.sendRedirect("MakeReservation");
		else {
			List<Reservation> list=(List<Reservation>)getServletContext().getAttribute("finalList");
			String day=request.getParameter("selectedDay");
			String time=request.getParameter("selectedTime");
			for(Reservation r:list){
				if(r.getDay().equals(day) && r.getTime().equals(time)){
					request.getRequestDispatcher("/ReservationError.jsp").forward(request, response);
					return;
				}
			}
			list.add(new Reservation(time, day, name));
			request.getRequestDispatcher("/RoomReservation.jsp").forward(request, response);
		}
	}

}