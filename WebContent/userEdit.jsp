<%@page import="entity.User"%>
<% User user = (User) session.getAttribute("User"); %>

   <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Mergen Travel Book</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell" id="bell"> <span style="color:white; font-weight: bolder;">0</span></i></a>
                </li>
                <li><a href="#"><i class="fa fa-ellipsis-v" aria-hidden="true"></i></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%= user.getFullname() %><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="http://localhost:8080/travelApp/Logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
         		
	<ul class="nav navbar-nav side-nav">
		<div class="panel panel-primary" style="width: 100%; margin-bottom: 0px;">
	
		 <div class="panel-heading text-center" style="font-size: 130%;"><%= user.getFullname() %></div>
		 <div class="panel-body"><img src="image/profile.gif" width="60%" height="145px" style="display: block; margin: auto;"></div>
	</div>
              
<li class="active">
<div class="panel panel-primary"  style="height: 390px;">
  <div class="panel-heading text-center" style="font-size: 130%;">Information</div>
  <div class="panel-body">

   		<form class="form-signin" role="form" style="padding: 0px;  margin-top: 5px;">
        
            <div class="entry-group">
                          <div class="row" style="margin-bottom: 10px;">
		    <div class="col-sm-6">   
		      	<label>Full Name:<input type="text" value="<%= user.getFullname() %>" class="form-control" name="fullName" id="fullName" placeholder="Full Name" required>
		      	</label>
		    </div>
		    <div class="col-sm-6">
		      	<label>Gender: <input type="text"  value="<%= user.getGender() %>" class="form-control" name="gender" id="gender" placeholder="gender" required></label>								 
		    </div>
		  </div>

           <div class="row" style="margin-bottom: 10px;">
		    <div class="col-sm-6">
		      <label>State: <input type="text"  value="<%= user.getState() %>" class="form-control" name="state" id="state" placeholder="state" required></label>	
		    </div>
		    <div class="col-sm-6">
		      	<label>City: <input type="text" value="<%= user.getCity() %>" class="form-control" name="city" id="city" placeholder="city" required></label>
		    </div>
		  </div>

		  <div class="row" style="margin-bottom: 10px;">
		    <div class="col-sm-6">
		    	<label>Zipcode: <input type="number" value="<%= user.getZipcode() %>" class="form-control" placeholder="zipcode" name="zipcode" id="zipcode" required></label>				
		    </div>
		    <div class="col-sm-6">
		   	     <label>Birth Year: <input type="number" value="<%= user.getBirthyear() %>" class="form-control" name="birthYear" id="birthYear"  placeholder="Birth Year" required></label>	
		    </div>
		  </div>

		  <div class="row" style="margin-bottom: 10px;">
		    <div class="col-sm-12">
		    	<label>Email: <input type="email" value="<%= user.getEmail() %>" class="form-control" name="loginEmail" id="loginEmail"  placeholder="Email address" readonly required></label>									      	 
		    </div>
		  </div>

             <button class="btn btn-md btn-primary btn-block" type="submit"  id="updateInfo"><i class="fa fa-floppy-o" aria-hidden="true"></i> Update Information</button>
           </div>
      </form>

  </div>
</div>

						 
     </li>   
 </ul>       
        </div>
        <!-- /.navbar-collapse -->
    </nav>
    
    	  
 <div id="page-wrapper">

 <div class="container-fluid">

 <div class="panel-group">		
 <div class="panel panel-default">
   <div class="panel-body">
   	<h1 class="text-center"><img src="image/weatherlogo.gif" title="weather" height="120px" width="120px"></img> Weather Info</h1>
   	<ul class="nav nav-tabs">
	  <li class="active"><a data-toggle="tab" href="#home" id="weatherOne">Current Weather Data</a></li>
	  <li><a data-toggle="tab" href="#menu1" id="weatherTwo">5 day / 3 hours forecast</a></li>
	</ul>

	<div class="tab-content">
	  <div id="home" class="tab-pane fade in active">	
		  <div class="well"  style="text-align: center;">		
		  		<h3>Current Weather Info</h3>
		  		<div class="form-inline">
			      <label>Zipcode: <input type="text"  value="<%= user.getZipcode() %>" class="form-control" id="searchWeatherMain" placeholder="state" required></label>
			      <button class="btn btn-md btn-primary" id="searchMainButton"><i class="fa fa-search" aria-hidden="true"></i></button><br/>
			    </div>
		  	 	<div id ="weatherHome">
		  	 		
		  	 	</div>
		  </div>	
	  </div>
	  <div id="menu1" class="tab-pane fade">
	  	 <div class="well" style="text-align: center;">
	  	 		<h3>5 day / 3 hours Weather Info</h3>
		  		 <div class="form-inline">
			      <label>Zipcode: <input type="text"  value="<%= user.getZipcode() %>" class="form-control" id="searchWeatherSecond" placeholder="state" required></label>
			      <button class="btn btn-md btn-primary" id="searchMainSecond"><i class="fa fa-search" aria-hidden="true"></i></button><br/>
			    </div>
	  	 
	  	 		<div id="weatherSecond">
		  	 		 <table id="#myTable" class="table">
					    <thead>
					      <tr>
					        <th class="text-center">Date</th>
					        <th class="text-center">Temp</th>
					        <th class="text-center">Pressure</th>
			                <th class="text-center">Humidity</th>
					      </tr>
					    </thead>
					    <tbody>
					    	
					    </tbody>
					  </table>
				</div>
			  </div>
		  </div>
		</div>

    </div>
  </div>
</div>
    