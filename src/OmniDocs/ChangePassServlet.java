package OmniDocs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.DriverManager;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/ChangePassServlet")
public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	java.sql.Connection mycon = null;
	java.sql.Statement mystmt = null;
	java.sql.ResultSet myrs = null;
	
	/*private static final String URL = "jdbc:mysql://mysql.railway.internal:3306/omnidocs";
	private static final String USER_ID = "root";
	private static final String PASSWORD = "Pragati2008";*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	        // Initialize the connection inside the method where exceptions can be thrown or caught
	        mycon = OmniDocs.DbConnection.getConnection();
	        
	        // ... Your database operations go here ...
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		String passwd = request.getParameter("password");
		String ConfirmPasswd = request.getParameter("password1");
        HttpSession session = request.getSession();

		if(!passwd.equals(ConfirmPasswd)) {
			response.sendRedirect("Changepass.jsp?error=passMissMatch");
			return;
		}
		
		String email = request.getParameter("email");
	
		try {
	        mycon = OmniDocs.DbConnection.getConnection();
			
			CallableStatement stmt = mycon.prepareCall("{call updatepass(?, ?)}");
			stmt.setString(1, email);
			stmt.setString(2, passwd);
			
			int rowsUpdated = stmt.executeUpdate(); 

			if (rowsUpdated > 0) {
			    response.sendRedirect("Login.jsp?update=changedpass");
			    return;
			} else {
			    response.sendRedirect("Changepass.jsp?error1=notexist");
			    return;
			}
				
		}catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }

	}

}
