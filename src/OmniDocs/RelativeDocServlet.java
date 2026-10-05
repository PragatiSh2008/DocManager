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
 * Servlet implementation class RelativeDocServlet
 */
@WebServlet("/RelativeDocServlet")
public class RelativeDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	java.sql.Statement mtstmt = null;
	java.sql.ResultSet myrs = null;
	java.sql.Connection mycon = null;
	
	/*String url = "jdbc:mysql://mysql.railway.internal:3306/omnidocss";
	String user = "root";
	String password = "Pragati2008";*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        // Initialize the connection inside the method where exceptions can be thrown or caught
	        mycon = OmniDocs.DbConnection.getConnection();
	        
	        // ... Your database operations go here ...
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
		List<DocClass> RelativedocLst = new ArrayList<>();
		
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
	        mycon = OmniDocs.DbConnection.getConnection();

			
			CallableStatement mystmt = mycon.prepareCall("{call getDoc(?)}");
			
			mystmt.setString(1, tableName);
			
			myrs = mystmt.executeQuery();
			RelativedocLst.clear();
			
			String relativeName = request.getParameter("RelativeName");
	        
	        // 2. Handle cases where the value might be missing
	        if (relativeName != null && !relativeName.trim().isEmpty()) {
	            
	            // Your logic goes here (e.g., fetch documents from database)
	            System.out.println("Received Relative Name: " + relativeName);
	            
	        } else {
	            System.out.println("No relative name was provided.");
	        }
	        
			while(myrs.next()) {
				String relation = myrs.getString("relative_name");
				//String relationType = myrs.getString("relation_type");
				if(relativeName.equals(relation)) {
					String docType = myrs.getString("document_name");
					String uniqueFileName = myrs.getString("document_image");
					String docNote = myrs.getString("document_note");
					
					DocClass obj = new DocClass(docType,uniqueFileName,docNote);
					RelativedocLst.add(obj);
					
					session.setAttribute("RelativedocLst", RelativedocLst);
				}
				
			}
			if(!myrs.next()) {
				session.setAttribute("RelativedocLst", RelativedocLst);
			}
			response.sendRedirect("ViewRelativeData.jsp?RelativeName="+relativeName);
			}catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
