package OmniDocs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ChangePasswordMailServlet
 */
@WebServlet("/ChangePasswordMailServlet")
public class ChangePasswordMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String sender , receiver , password , subject , message;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sender = System.getenv("Email_User");
		System.out.println("sender is: "+sender);
		
		password =System.getenv("Email_Password");
		receiver = request.getParameter("EMailId");
		System.out.println("reciever is: "+receiver);
		
		subject = "Change Password";
		message = "To Change password Click on this link..<br/><a href = 'http://localhost:8080/DocManager/ChangePass.jsp?email="+receiver+"'>Change Password</a>";

		String result = sendMyMail(receiver, sender, subject, message, true, password);
	    request.setAttribute("message", result);
	    // Add this to print the output directly onto your blank browser screen
	    response.setContentType("text/html");
	    response.getWriter().println("<h1>" + result + "</h1>");
	}

	public String sendMyMail(String receiver, String sender, String subject, String message, boolean debug, String password) {
	    java.util.Properties props = new java.util.Properties();
	    extracted(props);

	    jakarta.mail.Session session = jakarta.mail.Session.getInstance(props, new jakarta.mail.Authenticator() {
	        protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
	            // Replace with your actual Mailtrap password string
	            return new jakarta.mail.PasswordAuthentication("ca181bae8794c5", "ad828578f5fb76");
	        }
	    });

	    try {
	        jakarta.mail.Message msg = new jakarta.mail.internet.MimeMessage(session);
	        msg.setFrom(new jakarta.mail.internet.InternetAddress(sender));
	        msg.setRecipients(jakarta.mail.Message.RecipientType.TO, jakarta.mail.internet.InternetAddress.parse(receiver));
	        msg.setSubject(subject);
	        msg.setContent(message, "text/html; charset=utf-8");
	        jakarta.mail.Transport.send(msg);
	        return "Email sent successfully! Please verify and change the password"; // Returns a string on success
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Failed to send email: " + e.getMessage(); // Returns a string on failure
	    }
	}

	private void extracted(java.util.Properties props) {
		props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
	    props.put("mail.smtp.port", "2525");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
