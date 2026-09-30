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
import java.util.*;

/**
 * Servlet implementation class AddRelativeServlet
 */
@WebServlet("/AddRelativeServlet")
public class AddRelativeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   
		java.sql.Connection mycon = null;
		java.sql.Statement mystmt = null;
		java.sql.ResultSet myrs = null;
		
		String url = "jdbc:mysql://localhost:3306/omnidocs";
		String user = "root";
		String password = "Pragati@2008";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		List<RelativeClass> relativeclass = new ArrayList<>();
		
		if (session == null || session.getAttribute("email") == null) {
		    response.sendRedirect("Login.jsp");
		    return; // Stops executing the rest of the page
		}
		
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
	    relativeclass.clear();
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			mycon = DriverManager.getConnection(url, user, password);
			
			CallableStatement mystmt = mycon.prepareCall("{call getrelative(?)}");
			
			mystmt.setString(1, tableName);

			myrs = mystmt.executeQuery();
			while(myrs.next()) {
				String name = myrs.getString("relativename");
				String type = myrs.getString("relativetype");
				RelativeClass obj = new RelativeClass(name,type);
				
				System.out.println("relative name: "+name);
				System.out.println("relative type: "+type);
				
				relativeclass.add(obj);
				
				session.setAttribute("relativeclass", relativeclass);
				
			}
			
			if(!myrs.next()) {
				session.setAttribute("relativeclass", relativeclass);
			}
			response.sendRedirect("DisplayRelatives.jsp");
			}catch (Exception e) {
	        e.printStackTrace();        
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if (session == null || session.getAttribute("email") == null) {
		    response.sendRedirect("Login.jsp");
		    return; // Stops executing the rest of the page
		}
		
		String Name = request.getParameter("relativeName");

		String Type = request.getParameter("relationType");
		
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
			
			CallableStatement mystmt = mycon.prepareCall("{call addrelative(?,?,?)}");
			CallableStatement rstmt = mycon.prepareCall("{call getrelative(?)}");
			
			rstmt.setString(1, tableName);
			
			myrs = rstmt.executeQuery();
			while(myrs.next()) {
				String rname = myrs.getString("relativename");
				
				if(rname.equals(Name)) {
					response.sendRedirect("Addrelative.jsp?relative=exists");
					return;
				}
			}
			
			mystmt.setString(1, tableName);
			mystmt.setString(2, Name);
	        mystmt.setString(3, Type);
			
			int rowsAffected = mystmt.executeUpdate();
			if(rowsAffected>0) {
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
