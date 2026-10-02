package OmniDocs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class DisplayDocServlet
 */
@WebServlet("/DisplayDocServlet")
public class DisplayDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	java.sql.Statement mtstmt = null;
	java.sql.ResultSet myrs = null;
	java.sql.Connection mycon = null;
	
	String url = "jdbc:mysql://mysql.railway.internal:3306/omnidocs";
	String user = "root";
	String password = "Pragati@2008";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DocClass> docLst = new ArrayList<>();
		
		HttpSession session = request.getSession();
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			mycon = DriverManager.getConnection(url, user, password);
			
			CallableStatement mystmt = mycon.prepareCall("{call getDoc(?)}");
			
			mystmt.setString(1, tableName);
			
			myrs = mystmt.executeQuery();
			docLst.clear();
			while(myrs.next()) {
				String relation = myrs.getString("relative_name");
				String relationType = myrs.getString("relation_type");
				if("Self".equals(relation)&&("Self".equals(relationType))) {
					String docType = myrs.getString("document_name");
					String uniqueFileName = myrs.getString("document_image");
					String docNote = myrs.getString("document_note");
					
					DocClass obj = new DocClass(docType,uniqueFileName,docNote);
					docLst.add(obj);
					
					session.setAttribute("docLst", docLst);
				}
				
			}
			if(!myrs.next()) {
				session.setAttribute("docLst", docLst);
			}
			response.sendRedirect("DisplayDocs.jsp");
			}catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
