package OmniDocs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	java.sql.Connection mycon = null;
	java.sql.Statement mystmt = null;
	java.sql.ResultSet myrs = null;
	
	String url = "jdbc:mysql://mysql.railway.internal:3306/omnidocs";
	String user = "root";
	String password = "Pragati2008";
	    
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();

	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String passwd = request.getParameter("password");

	    // Safely extract the part before '@'
	    int atIndex = email.indexOf('@');
	    String prefix = (atIndex > 0) ? email.substring(0, atIndex) : email;

		 // 2. Remove EVERYTHING except standard letters (a-z) and numbers (0-9)
		String cleanName = prefix.replaceAll("[^a-zA-Z0-9]", "_");
		System.out.println("clean name is: "+cleanName);
	    
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    
	    try (java.sql.Connection mycon = DriverManager.getConnection("jdbc:mysql://mysql.railway.internal:3306/omnidocs", "root", "Pragati2008");
	         CallableStatement mystmt = mycon.prepareCall("{call adduser(?, ?, ?)}");
	         CallableStatement stmt = mycon.prepareCall("{call usertable(?)}")) {

	        // Insert user into the main users table
	        mystmt.setString(1, name);
	        mystmt.setString(2, email);
	        mystmt.setString(3, passwd);
	        int rowsAffected = mystmt.executeUpdate();

	        // Create a dedicated table for this user
	        stmt.setString(1, cleanName);
	        stmt.execute();  // DDL — not executeQuery()

	        if (rowsAffected >= 1) {
	            response.sendRedirect("SignUp.jsp?success=yes");
	            return;
	        } else {
	            response.sendRedirect("SignUp.jsp?success1=no");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("SQL Error Code: " + e.getErrorCode());
	        System.out.println("SQL State: " + e.getSQLState());
	        out.write("Database error: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("SignUp.jsp?success1=no");
	    }
	}
}
