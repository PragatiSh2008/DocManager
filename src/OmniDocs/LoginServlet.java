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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
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
		
		
		String name;
	    String email = request.getParameter("email");
		String passwd = request.getParameter("password");
		
		int atIndex = email.indexOf('@');

		String prefix = (atIndex > 0) ? email.substring(0, atIndex) : email;

		 // 2. Remove EVERYTHING except standard letters (a-z) and numbers (0-9)
		String cleanName = prefix.replaceAll("[^a-zA-Z0-9]", "_");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			mycon = DriverManager.getConnection(url, user, password);
			
			CallableStatement mystmt = mycon.prepareCall("{call getuser(?)}");
			
			mystmt.setString(1, email);
			myrs = mystmt.executeQuery();
			
			if(myrs.next()) {
				String password = myrs.getString("password");
				name = myrs.getString("Name");
				
				//System.out.println("Name is: "+name);
				//System.out.println("Password entered: "+passwd);
				//System.out.println("Password In db: "+Password);
				session.setAttribute("email",email);
				session.setAttribute("Password", passwd);
				session.setAttribute("name", name);
				session.setAttribute("tablename", cleanName);

				if(password.equals(passwd)) {
					response.sendRedirect("conflogin.jsp");
				}else {
					response.sendRedirect("Login.jsp?error=invalid");
					return;
				}
			}else {
				response.sendRedirect("SignUp.jsp?user=invalid");
				return;
			}
		
		}catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }

	}

}
