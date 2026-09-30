<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  
<%
if (session == null || session.getAttribute("email") == null) {
    response.sendRedirect("Login.jsp");
    return; // Stops executing the rest of the page
}
%>


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
        
        <a href="#"><button type="button" class="btn btn-dark" style="margin-right: 40px;">Add User</button></a>
        
        <a href="homepage.jsp"><button type="button" class="btn btn-dark">Logout</button></a>
        
      </div>
    </div>
  </div>
</nav>
<%
	String Relative = request.getParameter("relative");
	if("exists".equals(Relative)){
%>
	<div class="alert alert-danger" role="alert">
  		A Relative with this name alredy exists!! make sure to use a unique name!! 
	</div>
<%
	}
%>

<body>
	<!-- side navbar -->
<!-- Main Page Wrapper -->
<div class="container-fluid p-0">
  <!-- CRITICAL: Everything must be inside this row to stay side-by-side -->
  <div class="row g-0" style="min-height: calc(100vh - 56px);"> 
    
    <div class="col-md-2 bg-dark text-white min-vh-100 p-3" style="background-color: #212529 !important;">
		    
		    <!-- 1. Profile Header Block (Centered) -->
		    <div class="text-center pt-4 pb-3">
		        <!-- Circular Profile Avatar Image/Placeholder -->
		        <div class="rounded-circle bg-secondary d-inline-flex align-items-center justify-content-center m-auto shadow-sm" 
		             style="width: 100px; height: 100px; overflow: hidden; color: rgba(255,255,255,0.7); font-size: 20px;">
		             Profile
		        </div>
		        <!-- Display Username -->
		        <h5 class="mt-3 font-weight-bold text-white small" style="letter-spacing: 0.5px;">${sessionScope.name}</h5>
		    </div>
		    
		    <!-- Subtle Divider Line -->
		    <hr style="border-top: 1px solid rgba(255,255,255,0.1); margin-top: 5px; margin-bottom: 25px;">
		    
		    <!-- 2. Navigation Menu Links (with heavy bottom margins to space them out) -->
		    <div class="nav flex-column px-2" style="font-size: 16px;">
		        <a href="conflogin.jsp?view=password" id="Link1" class="text-white mb-4 d-block text-decoration-none transition-link">View Password</a>
		        <a href="DisplayDocServlet" id="displayDocument" class="text-white mb-4 d-block text-decoration-none transition-link">My Documents</a>
		        <a href="conflogin.jsp?view=addDoc" id="btnAddDocument" class="text-white mb-4 d-block text-decoration-none transition-link">Add Document</a>
		        <a href="AddRelativeServlet" class="text-white mb-4 d-block text-decoration-none transition-link">Relations</a>
		        <a href="Addrelative.jsp" id = "addrelative" class="text-white mb-4 d-block text-decoration-none transition-link">Add Relative</a>
		        <a href="homepage.jsp" class="text-white mb-4 d-block text-decoration-none transition-link">Log Out</a>
		    </div>
		
		</div>


<!-- 2. RIGHT MAIN CONTENT COLUMN -->
<div class="col-md-9 col-lg-10 p-4 bg-light">
  <h2 class="fw-bold text-secondary mb-4">Welcome ${sessionScope.name}!!</h2>
  
  <div class="card p-4 shadow-sm">
    <p class="text-muted m-0" id="display">
		<!-- Form here -->
				<div class="card shadow-sm border-0 rounded" id="display" style="background-color: #ffffff; max-width: 850px; margin-top: 1.5rem;">
		    <!-- Form Header to match the 'Add New Document' style -->
		    <div class="card-header border-0 text-white font-weight-bold" style="background-color: #1a1d20; padding: 1rem 1.5rem; font-size: 1.1rem; border-top-left-radius: 8px; border-top-right-radius: 8px;">
		        Add New Relative
		    </div>
		    
		    <div class="card-body p-4 text-start">
		        <form action="AddRelativeServlet" method="POST" id="relativeForm">
		            
		            <!-- Field 1: Relative Name -->
		            <div class="mb-4">
		                <label for="relativeName" class="form-label fw-semibold text-secondary small d-block mb-2">Relative's Full Name *</label>
		                <input type="text" 
		                       class="form-control form-control-lg fs-6" 
		                       id="relativeName" 
		                       name="relativeName" 
		                       placeholder="e.g., Rahul Kumar" 
		                       required>
		            </div>
		            
		            <!-- Field 2: Relation Type Dropdown -->
		            <div class="mb-4">
		                <label for="relationType" class="form-label fw-semibold text-secondary small d-block mb-2">Relation Type *</label>
		                <select class="form-control form-select form-control-lg fs-6" id="relationType" name="relationType" required>
		                    <option value="" selected disabled>Select relationship category...</option>
		                    <option value="Child">Child</option>
		                    <option value="Spouse">Spouse</option>
		                    <option value="Parent">Parent</option>
		                    <option value="Sibling">Sibling</option>
		                </select>
		            </div>
		            
		            <!-- Bottom Action Controls to match the original style -->
		            <div class="d-flex justify-content-end gap-2 mt-5">
		                <button type="reset" class="btn btn-outline-secondary px-4 py-2 small" style="border-radius: 4px;">Clear</button>
		                <button type="submit" class="btn btn-primary px-4 py-2 font-weight-bold shadow-sm" style="background-color: #0d6efd; border: none; border-radius: 4px;">
		                    Save Relative
		                </button>
		            </div>
		            
		        </form>
		    </div>
		</div>
    </p>
  </div>
</div>

</div>
</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>