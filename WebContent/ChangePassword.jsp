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
	String error = request.getParameter("error");
	if("invalid".equals(error)){
%>
	<div class="alert alert-danger" role="alert">
  		Invalid password or email! Login again 
	</div>
<%
	}
%>


<body style="background-color:#FEF8E5 ">
	<div class="w-100 d-flex justify-content-center align-items-center" style="min-height: calc(100vh - 56px); padding: 20px;">

    <!-- Square Container Box -->
    <div style="background-color: #ffffff; width: 450px; height: 450px; padding: 40px; box-shadow: 0 15px 35px rgba(0, 0, 0, 0.3); display: flex; flex-direction: column; justify-content: center;">
        
        <h2 class="text-center mb-4" style="font-weight: 700; color: #212529; letter-spacing: 0.5px;">CHANGE PASSWORD</h2>
        
        <!-- Signup Form Inputs -->
        <form action = "ChangePasswordMailServlet" method = "GET">
            <!-- Email Input Field -->
            <div class="mb-3">
                <label for="email" class="form-label text-muted small fw-bold">EMAIL ADDRESS</label>
                <input type="email" name = "EMailId" class="form-control form-control-lg" id="email" placeholder="name@example.com" required style="border-radius: 2px;">
            </div>
                      
            <!-- Submit Action Button -->
            <button type="submit" class="btn btn-dark btn-lg w-100" style="border-radius: 4px; font-weight: 600;">
                SUBMIT
            </button>
            
        </form>
        
    </div>

</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>