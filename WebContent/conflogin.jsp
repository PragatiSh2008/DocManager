<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  
<%
if (session == null || session.getAttribute("email") == null) {
    response.sendRedirect("Login.jsp?login=notfound");
    return; // Stops executing the rest of the page
}
%>

<%
	String error = request.getParameter("error");
	if("File is too large! Maximum limit is 5MB.".equals(error)){
%>
	<div class="alert alert-danger" role="alert">
  		File is too large! Maximum limit is 5MB.
	</div>
<%
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
    <p class="text-muted m-0" id="display">Select an item from the sidebar to view details.</p>
  </div>
</div>

</div>
</div>

<script>

//1. First, define the function so the browser knows it exists
function myfunction() {
 document.getElementById("display").innerHTML = "<h3>EMAIL ID: ${sessionScope.email}</h3><br><h3>NAME: ${sessionScope.name}</h3><br/><h3>PASSWORD: ${sessionScope.Password}</h3>";
}

//2. Attach the click listener to the element safely
const linkElement = document.getElementById("Link1");
if (linkElement) {
 linkElement.addEventListener('click', myfunction);
}

//3. Wait until the ENTIRE page layout finishes loading before checking the URL
window.onload = function() {
 const urlParams = new URLSearchParams(window.location.search);
 
 if (urlParams.get('view') === 'password') {
     // Run the function directly to ensure it updates immediately
     myfunction();
 }
 if (urlParams.get('view') === 'addDoc'){
	 addfunction();
 }
 
};	

//2. Declare the standalone function to add documents!!
function addfunction() {
    const displayArea = document.getElementById("display");
    
    // Inject the HTML template literal cleanly
    displayArea.innerHTML = `
    <div class="card shadow-sm border-0 rounded-3">
        <div class="card-header bg-dark text-white py-3">
            <h5 class="mb-0 fw-semibold">Add New Document</h5>
        </div>
        <div class="card-body p-4">
            <form action="AddDocumentServlet" method="POST" enctype="multipart/form-data">
                
                <div class="mb-3 text-start">
                    <label for="docName" class="form-label fw-semibold text-secondary">Document Name</label>
                    <select class="form-control" id="docName" name="documentName" required>
                        <option value="" selected disabled>Choose a document type...</option>
                        
                        <optgroup label="Primary Government IDs">
                        <option value="Aadhaar Card">Aadhaar Card</option>
                        <option value="PAN Card">PAN Card</option>
                        <option value="Voter ID Card">Voter ID Card (EPIC)</option>
                        <option value="Passport">Indian Passport</option>
                        <option value="Driving License">Driving License (DL)</option>
                        <option value="Ration Card">Ration Card</option>
                    </optgroup>

                    <optgroup label="Certificates & Civil Status">
                        <option value="Birth Certificate">Birth Certificate</option>
                        <option value="Marriage Certificate">Marriage Certificate</option>
                        <option value="Domicile Certificate">Domicile / Residential Certificate</option>
                        <option value="Caste Certificate">Caste Certificate (SC/ST/OBC)</option>
                        <option value="Income Certificate">Income Certificate</option>
                    </optgroup>

                    <optgroup label="Educational Documents">
                        <option value="10th Marksheet">Class 10th Marksheet & Certificate</option>
                        <option value="12th Marksheet">Class 12th Marksheet & Certificate</option>
                        <option value="UG Degree">Undergraduate Degree / Provisional</option>
                        <option value="PG Degree">Postgraduate Degree</option>
                        <option value="Leaving Certificate">School/College Leaving Certificate</option>
                    </optgroup>

                    <optgroup label="Financial & Employment">
                        <option value="Bank Statement">Bank Account Statement</option>
                        <option value="Bank Passbook">Bank Passbook (Front Page)</option>
                        <option value="ITR Form 16">Income Tax Returns / Form 16</option>
                        <option value="Salary Slips">Salary Slips</option>
                        <option value="Appointment Letter">Employment Appointment Letter</option>
                    </optgroup>

                    <optgroup label="Property & Utilities">
                        <option value="Property Deed">Property Sale Deed</option>
                        <option value="Utility Bill">Utility Bill (Electricity/Water)</option>
                        <option value="Rent Agreement">Rent Agreement</option>
                    </optgroup>
                    </select>
                </div>

                <div class="mb-3 text-start">
                    <label for="docImage" class="form-label fw-semibold text-secondary">Document Image</label>
                    <input type="file" class="form-control" id="docImage" name="documentImage" accept="image/*, application/pdf" onchange="if(this.files[0].size > 5242880){ alert('Upload Blocked: This file is too large! Maximum allowed size is 5MB.'); this.value = ''; }">
                </div>

                <div class="mb-4 text-start">
                    <label for="docNote" class="form-label fw-semibold text-secondary">Notes</label>
                    <textarea class="form-control" id="docNote" name="documentNote" rows="4" placeholder="Add any relevant references..."></textarea>
                </div>

                <div class="d-flex justify-content-end gap-2">
                    <button type="reset" class="btn btn-outline-secondary px-4">Clear</button>
                    <button type="submit" class="btn btn-primary px-4">Save Document</button>
                </div>

            </form>
        </div>
    </div>`;
}

// 2. Safely bind the standalone function to the button click event
const btnAddDocument = document.getElementById("btnAddDocument");
if (btnAddDocument) {
    btnAddDocument.addEventListener("click", addfunction);
}

</script>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>