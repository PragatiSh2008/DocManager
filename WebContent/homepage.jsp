<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">OmniDocs<img src="DocLogo.webp" style="height: 45px; width: auto; "></a>
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
	if (session!=null){
		session.invalidate();
	}
%>

  <body>
  		<h1 style="text-align: center;margin-top: 120px;font-size: 3rem;"><b>Your Digital File Vault</b></h1>
  		<p style="text-align: center;font-size: 2rem;">Securely store, organize, and access all your digital assets <br/>in one workspace.</p>
  		<p style="text-align: center;"><a href="SignUp.jsp"><button type="button" class="btn btn-info" style="padding: 12px 24px; font-size: 1.25rem;">Get Started</button></a></p>
    	
    	<div style="background: linear-gradient(to bottom, #FFFFFF 50%, #c7e1f5 50%); padding: 40px 0;">
    		<img src="dataAd.jpg"  style="height: 600px; margin-left: 60px; margin-right: auto;width: 90%;">
    	</div>
    	
    	<div style="background-color: #c7e1f5; padding: 60px 20px;">
    		<!-- Put the text or buttons you want inside the colored section here -->
    		<h1 style="text-align: center;"><b>Stop Searching. Start Organizing.</b></h1>
    		<p style="text-align: center;line-height:2;font-size: 1.3rem;">Your Digital File Vault is a secure, centralized document management platform designed to<br/> protect your most valuable assets. By pairing advanced encryption with strict credential-based<br/> access, the platform ensures that your sensitive files remain completely private and accessible <br/>only to authorized account holders.</p>
		</div>
    
    	<div style="background-color: #c7e1f5; padding: 60px 20px;">
    	
    	</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  </body>
</html>