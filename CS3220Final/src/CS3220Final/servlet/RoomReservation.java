package CS3220Final.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import CS3220Final.servlet.model.Reservation;


@WebServlet("/RoomReservation")
public class RoomReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    public void init( ServletConfig config ) throws ServletException {
        super.init( config );
        
    	List<Reservation> list=new ArrayList<>();
    	String days[]={"MON","TUE","WED","THR","FRI"};
    	String times[]={"08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00",
    							"16:00-17:00"};
    	list.add(new Reservation(times[1],days[1], "Kang"));
    	list.add(new Reservation(times[2], days[0], "Pamula"));
    	list.add(new Reservation(times[5], days[2], "Abbott"));
    	list.add(new Reservation(times[6], days[2], "Abbott"));
    	list.add(new Reservation(times[6], days[3], "Sun"));
    	list.add(new Reservation(times[7], days[3], "Sun"));
    	ServletContext context=getServletContext();
    	context.setAttribute("finalList", list);
    	context.setAttribute("finalDays", days);
    	context.setAttribute("finalTimes", times);

        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e ) {
            throw new ServletException( e );
        }
    }
    
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		request.getRequestDispatcher("/RoomReservation.jsp").forward(request, response);
//	}
	protected void doGet( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException {
	        request.getRequestDispatcher( "/RoomReservation.jsp" )
	            .forward( request, response );
	    }

	    protected void doPost( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException {
	        List<Reservation> reservation = new ArrayList<Reservation>();

	        String time = request.getParameter( "time" );
	        String day = request.getParameter( "day" );
	        String name = request.getParameter("name");
	        String sql = "select * from roomReservation where time = '" + time
	            + "' and day = '" + day + "'" + "' and name = '" + name + "'";

	        Connection c = null;
	        try {
	            String url = "jdbc:mysql://localhost/cs3220stu54";
	            c = DriverManager.getConnection( url, "cs3220stu54", "MwbTM!f*" );
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( sql );
	            
	            while( rs.next() ) {
	                Reservation res = new Reservation(url, url, url);
	               // reservation.setId( rs.getInt( "id" ) );
	                res.setTime( rs.getString( "time" ) );
	                res.setDay( rs.getString( "day" ) );
	                res.setName( rs.getString( "name" ) );
	              //  res.add( reservation );
	            }
	        }
	        catch( SQLException e ) {
	            throw new ServletException( e );
	        }
	        finally {
	            try {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e ) {
	                throw new ServletException( e );
	            }
	        }

	        request.setAttribute( "reservation", reservation );
	        request.getRequestDispatcher( "/RoomReservation.jsp" )
	            .forward( request, response );
	    }


//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

}