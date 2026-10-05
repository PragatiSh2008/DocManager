<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="homepage.jsp">OmniDocs<img src="DocLogo.webp" style="height: 45px; width: auto; "></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      </ul>
      <div class="d-flex" role="search">
        <a href = "ContactUs.jsp"  class="navbar-brand"style="margin-right: 40px;">Contact Us</a>
        
        <a href="SignUp.jsp"><button type="button" class="btn btn-dark" style="margin-right: 40px;">Get Started</button></a>
      
        <a href="Login.jsp"><button type="button" class="btn btn-dark">Login</button></a>
      </div>
    </div>
  </div>
</nav>

<%
	String success = request.getParameter("success");
	if("yes".equals(success)){
%>
	<div class="alert alert-primary" role="alert">
  		User added successfully!! Procees to login!!
	</div>
<%
	}
%>

<%
	String success1 = request.getParameter("success1");
	if("no".equals(success1)){
%>
	<div class="alert alert-danger" role="alert">
  		User alredy exists!! Proceed with login!!
	</div>
<%
	}
%>

<%
	String user = request.getParameter("user");
	if("invalid".equals(user)){
%>
	<div class="alert alert-danger" role="alert">
  		User does not exists! SignUp!
	</div>
<%
	}
%>

<%
    String error = request.getParameter("error");
    if ("email_exists".equals(error)) {
%>
        <div style="color: red; font-weight: bold; margin-bottom: 15px;">
            An account with this email address already exists! Please try logging in.
        </div>
<%
    }
%>

<body style="background-color:#FEF8E5 ">
	
	<h1 style="text-align:center;font-size:3rem;margin-top:70px;">Sign Up For OmniDocs!!</h1>
	<p style="text-align: center;font-size:1.5rem;">Lets get all your doccuments organised and available at one place !!</p>
	
		 <div class="contact-section" style="
    width: 100%;
    min-height: 80vh; /* Changed from 100vh so it doesn't push the page too low */
    padding: 20px;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
  ">
    
    <!-- White card box -->
    <div class="contact-card" style="
      background-color: #ffffff; 
      width: 100%; 
      max-width: 600px; 
      padding: 40px; 
      border-radius: 12px; 
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15); 
      box-sizing: border-box;
    ">
      
      <!-- Form container -->
      <form style="display: flex; flex-direction: column; gap: 24px;" action = "SignUpServlet" method="POST">
        
        <!-- Name Field -->
        <div class="form-group" style="display: flex; flex-direction: column; gap: 8px;">
          <label for="name" style="font-family: system-ui, sans-serif; font-size: 16px; color: #333333; font-weight: 500;">Your Name</label>
          <input type="text" id="name" name="name" required style="width: 100%; padding: 12px; font-size: 16px; border: 1px solid #dcdcdc; border-radius: 6px; background-color: #fbfcfd; box-sizing: border-box; outline: none; font-family: inherit;">
        </div>

        <!-- Email Field -->
        <div class="form-group" style="display: flex; flex-direction: column; gap: 8px;">
          <label for="email" style="font-family: system-ui, sans-serif; font-size: 16px; color: #333333; font-weight: 500;">Email</label>
          <input type="email" id="email" name="email" required style="width: 100%; padding: 12px; font-size: 16px; border: 1px solid #dcdcdc; border-radius: 6px; background-color: #fbfcfd; box-sizing: border-box; outline: none; font-family: inherit;" required>
        </div>

        <!-- password Field -->
        <div class="form-group" style="display: flex; flex-direction: column; gap: 8px;">
          <label for="password" style="font-family: system-ui, sans-serif; font-size: 16px; color: #333333; font-weight: 500;">Password</label>
          <input type="password" id="password" name="password" required style="width: 100%; padding: 12px; font-size: 16px; border: 1px solid #dcdcdc; border-radius: 6px; background-color: #fbfcfd; box-sizing: border-box; outline: none; font-family: inherit;" required>
        </div>
	
		<!-- Country -->
		<div class="form-group" style="display: flex; flex-direction: column; gap: 8px;">
		  <label for="country" style="font-family: system-ui, sans-serif; font-size: 16px; color: #333333; font-weight: 500;">Country</label>
		  <select id="country" name="country" class="form-control" required style="width: 100%; padding: 12px; font-size: 16px; border: 1px solid #dcdcdc; border-radius: 6px; background-color: #fbfcfd; box-sizing: border-box; outline: none; font-family: inherit;" required>
		    <option value="IN">India</option>
		  </select>
		</div>
        
        <!-- Button -->
        <button type="submit" style="align-self: flex-start; background-color: #46bccc; color: white; font-family: system-ui, sans-serif; font-size: 18px; font-weight: bold; padding: 12px 32px; border: none; border-radius: 8px; cursor: pointer;">SignUp</button>
        
      </form>
    </div>

  </div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>