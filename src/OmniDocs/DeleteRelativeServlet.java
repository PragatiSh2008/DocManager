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
 * Servlet implementation class DeleteRelativeServlet
 */
@WebServlet("/DeleteRelativeServlet")
public class DeleteRelativeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	java.sql.Connection mycon = null;
	java.sql.Statement mystmt = null;
	java.sql.ResultSet myrs = null;
	
	String url = "jdbc:mysql://mysql.railway.internal:3306/omnidocs";
	String user = "root";
	String password = "Pragati@2008";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
	    String Name = request.getParameter("Name");
		String Type = request.getParameter("Type");

		String tableName ="";
		//String cleanName = "";
		
		if (session != null) {
	        // 2. Read the attribute (Must explicitly cast it back to a String)
	        tableName = (String) session.getAttribute("tablename");
	    }

	    // 3. Validation Check
	    if (tableName == null) {
	        // If the session expired or user isn't logged in, send them back to login page
	        response.sendRedirect("login.jsp?error=session_expired");
	        return;
	    }
	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			mycon = DriverManager.getConnection(url, user, password);
			
			CallableStatement mystmt = mycon.prepareCall("{call deleterelative(?,?,?)}");
			CallableStatement rstmt = mycon.prepareCall("{call deleterelativedoc(?,?)}");

			mystmt.setString(1, tableName);
			mystmt.setString(2, Name);
	        mystmt.setString(3, Type);
			
	        rstmt.setString(1, tableName);
	        rstmt.setString(2, Name);
			
			
			int rowsAffected = mystmt.executeUpdate();
			int rowsAffected2 = rstmt.executeUpdate();
			
			if(rowsAffected > 0 || rowsAffected2 > 0) {
				response.sendRedirect("AddRelativeServlet");
			}
			else {
				response.sendRedirect("AddRelativeServlet");
			}
			}catch (Exception e) {
		        e.printStackTrace();
		        
		        response.getWriter().println("Database Error: " + e.getMessage());
	
			}

	}
}
