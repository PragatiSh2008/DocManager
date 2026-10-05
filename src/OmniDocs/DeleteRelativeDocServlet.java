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
 * Servlet implementation class DeleteRelativeDocServlet
 */
@WebServlet("/DeleteRelativeDocServlet")
public class DeleteRelativeDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	java.sql.Statement mtstmt = null;
	java.sql.ResultSet myrs = null;
	java.sql.Connection mycon = null;
	
	/*String url = "jdbc:mysql://mysql.railway.internal:3306/omnidocs";
	String user = "root";
	String password = "Pragati2008";*/
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
		HttpSession session = request.getSession();
		
		String RelativeName = request.getParameter("relativeName");
		
		if (session == null || session.getAttribute("email") == null) {
		    response.sendRedirect("Login.jsp");
		    return; // Stops executing the rest of the page
		}
		
		String tableName ="";

		String docfile = request.getParameter("filename");
        if(session!=null) {	
			tableName = (String) session.getAttribute("tablename");
        }
        if (tableName == null) {
	        // If the session expired or user isn't logged in, send them back to login page
	        response.sendRedirect("login.jsp?error=session_expired");
	        return;
        }
        
        System.out.println("table name is: "+tableName);
        System.out.println("document being deleted is: "+docfile);
        
        try {
        	mycon = OmniDocs.DbConnection.getConnection();
			
			CallableStatement mystmt = mycon.prepareCall("{call deletedoc(?,?)}");
			
			mystmt.setString(1, tableName);
			mystmt.setString(2, docfile);
	       
			int rowsAffected = mystmt.executeUpdate();
			if(rowsAffected>0) {
				response.sendRedirect("RelativeDocServlet?RelativeName="+RelativeName);
			}
			else {
				response.sendRedirect("RelativeDocServlet?RelativeName="+RelativeName);
			}
			}catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }

	}

}
