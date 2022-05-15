<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Solar Request Details</title>
	
	<!-- Import Js file and Jquery files -->
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
<center>	

    <div class="container">
		
	<br>
	<p></p>

	   <div>
	        <h1><b>Request Solar Panel</b></h1>
	   </div>
	                    
	   <br/>
	   
	   <form id="solarRequest">
	                            
	        <div>
	             <label>Customer Name :-</label>
	             <div>
	                  <input type="text" id="name" class="form-control" name="name">
	             </div>
	        </div>
	                            
	        <div>
	             <label>NIC :-</label>
	             <div>
	                  <input type="text" id="nic" class="form-control" name="nic">
	             </div>
	        </div>
	                            
	        <div>
	             <label>Address :-</label>
	             <div>
	                  <input type="text" id="address" class="form-control" name="address">
	             </div>
	        </div>
	                            
	        <div>
	             <label>Phone Number :-</label>
	             <div>
	                  <input type="number" id="phone" class="form-control" name="phone">
	             </div>
	        </div>
	                            
	        <div>
	             <label>Email :-</label>
	             <div>
	                  <input type="email" id="email" class="form-control" name="email">
	             </div>
	        </div>
	                            
	        <div>
	             <label>Area :-</label>
	             <div>
	                  <input type="text" id="area" class="form-control" name="area">
	             </div>
	        </div>
	                            
	        <div>
	             <label>Quantity :-</label>
	             <div>
	                  <input type="number" id="quantity" class="form-control" name="quantity">
	             </div>
	        </div>
	                        	                            
	        <div>
	             <label>Request Solar Panel Name :-</label>
	             <div>
	                  <input type="text" id="solar_panel" class="form-control" name="solar_panel">
	             </div>
	        </div>
	                            
	        <br/>
	                            
	        <div>
	             <button type="submit" class="btn btn-success">
	                    SAVE
	             </button>
	             <a href="requestEdit&Delete.jsp" class="btn btn-success">
	                 List
	             </a>
	        </div>
	  </form>
	</div>
</center>
</body>
</html>


<script>

//Form JQUERY Validations 

$(document).ready(function () {

    $("#solarRequest").validate({
        rules: {
        	name: "required",
        	nic: "required",
        	address: "required",
        	phone: "required",
        	email: {
                email: true,
                required: true
            },
            area: "required",
            quantity: "required",
            solar_panel: "required"
        },
        messages: {
        	name: "Name is Required!",
        	nic: "NIC is Required!",
        	address: "Address is Required!",
        	phone: "Phone number is Required!",
        	email: {
                email: "Format Error",
                required: "Format Error"
            },
            area: "Area is Required!",
            quantity: "Quantity is Required!",
            solar_panel: "Solar Type is Required!"
        },
        submitHandler: function () {
        	var fromData = JSON.stringify({
                "name" : $('#name').val(),
                "nic" : $('#nic').val(),
                "address" : $('#address').val(),
                "phone" : $('#phone').val(),
                "email" : $('#email').val(),
                "area" : $('#area').val(),
                "quantity" : $('#quantity').val(),
                "solar_panel" : $('#solar_panel').val()
            });
        	
        	console.log(fromData);//Display inserted data on the console

            $.ajax({
                type: "POST",
                url: 'api/personal',
                dataType : 'json',
				contentType : 'application/json',
				data: fromData,
                success: function(data){
                	console.log(data);
                	if(data['success']== "1"){
                		alert("Added Successfull!");
                        $("#solarRequest")[0].reset();
					}else{
						alert("Successfull!");
					}
                },
                failure: function(errMsg) {
                	alert("Connection Error!");
                }
            });
        }
    });

    $("#solarRequest").submit(function(e) {
        e.preventDefault();
    });

});

</script>