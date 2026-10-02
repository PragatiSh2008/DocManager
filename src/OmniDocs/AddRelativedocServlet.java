package OmniDocs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.util.UUID;

/**
 * Servlet implementation class AddRelativedocServlet
 */
@WebServlet("/AddRelativedocServlet")
@MultipartConfig(
	    maxFileSize = 1024 * 1024 * 5,      // 5MB maximum file size limit
	    maxRequestSize = 1024 * 1024 * 10   // 10MB maximum total request size limit
	)
public class AddRelativedocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	java.sql.Statement mtstmt = null;
	java.sql.ResultSet myrs = null;
	java.sql.Connection mycon = null;
	
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
		
		if (session == null || session.getAttribute("email") == null) {
		    response.sendRedirect("Login.jsp");
		    return; // Stops executing the rest of the page
		}

		String docType =request.getParameter("documentName");
		System.out.println("documment type is: "+docType);
		
		Part docfile = request.getPart("documentImage");
		System.out.println("document type"+ docfile.getClass().getSimpleName());
		
		//getting file name
		String FileName = docfile.getSubmittedFileName();
		String uniqueFileName = UUID.randomUUID().toString() + "_" + FileName;
		
		//getting the whole path
		String uploadPath = "C:\\Users\\pc100\\eclipse-workspace\\DocManager\\WebContent\\uploads";
		File uploaddir = new File(uploadPath);
		
		/*if (uploaddir.exists()) {
		    response.getWriter().write("File saved successfully at: " + uploaddir.getAbsolutePath());
		} else {
		    response.getWriter().write("File was NOT saved!");
		}*/

	    docfile.write(uploadPath + File.separator + uniqueFileName);
		//System.out.println("File successfully uploaded: "+FileName);

	    
		String docNote = request.getParameter("documentNote");
		System.out.println("documment note is: "+docNote);

		//String relativeName = request.getParameter("relativeName");
		
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
	    
	    String RelativeName = request.getParameter("relativeName");
	    
	    try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			mycon = DriverManager.getConnection(url, user, password);
			
			CallableStatement mystmt = mycon.prepareCall("{call insertrelativedoc(?,?,?,?,?)}");
			
			mystmt.setString(1, tableName);
			mystmt.setString(2, docType);
	        mystmt.setString(3, uniqueFileName);
			mystmt.setString(4, docNote);
			mystmt.setString(5, RelativeName);
			
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
