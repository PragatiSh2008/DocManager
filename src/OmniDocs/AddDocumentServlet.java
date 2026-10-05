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
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.util.UUID;
import java.util.*;

/**
 * Servlet implementation class AddDocumentServlet
 */
@WebServlet("/AddDocumentServlet")
@MultipartConfig(
	    maxFileSize = 1024 * 1024 * 5,      // 5MB maximum file size limit
	    maxRequestSize = 1024 * 1024 * 10   // 10MB maximum total request size limit
	)
public class AddDocumentServlet extends HttpServlet {
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
			
			CallableStatement mystmt = mycon.prepareCall("{call insertdoc(?,?,?,?)}");
			
			mystmt.setString(1, tableName);
			mystmt.setString(2, docType);
	        mystmt.setString(3, uniqueFileName);
			mystmt.setString(4, docNote);
			
			
			
			int rowsAffected = mystmt.executeUpdate();
			if(rowsAffected>0) {
				response.sendRedirect("DisplayDocServlet");
			}
			else {
				response.sendRedirect("DisplayDocServlet");
			}
			}catch (Exception e) {
	        e.printStackTrace();
	        
	        Throwable rootCause = e.getCause();
	        if (rootCause instanceof org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException 
	            || e.getMessage().contains("FileSizeLimitExceededException")) {
	            
	            // Redirect the user back to the form with a clear, readable error string parameter
	            response.sendRedirect("conflogin.jsp?view=addDoc&error=File is too large! Maximum limit is 5MB.");
	            return;
	        }
	        
	        response.getWriter().println("Database Error: " + e.getMessage());
	    }

			
	}
}

