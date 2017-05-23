<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  

<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">

	<!-- JQuery -->
	<script src="library/jquery/jquery-3.2.1.min.js"></script>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="library/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="library/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<!-- Roboto google font -->
	<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

	<!-- My library -->
	<link rel="stylesheet" href="style/main.css">

	<!-- Favicon -->
	<link href="image/favicon.ico" rel="shortcut icon" >

	<!-- Font awesome -->
	<link rel="stylesheet" href="library/font-awesome-4.7.0/css/font-awesome.css">

	<!-- Main JS -->
	<script src="js/mainScript.js"></script>

	<style>
		background-color: white;
	</style>

	<title>Mergen Travel</title>
</head>
<body>

 <div id="wrapper">
       	<jsp:include page="userEdit.jsp" /> 
       
        	<div class="panel panel-default">
				  <div class="panel-body">

				  		<div style="width: 50%; margin-left: auto; margin-right: auto; margin-bottom: 20px;">
    						<div class="widget-area no-padding blank">
								<div class="status-upload">
									<form enctype="multipart/form-data">
										<textarea name="postContent" id="postContent" placeholder="What are you doing right now?"></textarea>
										<ul>
											<li>
											    <input type="file" name="pictureFile" id="pictureFile"/>   																					
											</li>
										</ul>
										<button type="submit" class="btn btn-success green"  id="postSection"><i class="fa fa-share"></i>POST</button>
									</form>
								</div><!-- Status Upload  -->
							</div><!-- Widget Area -->
						</div>

						<br/><br/>

						<div class="postJagsaalt">
					        <div id='postsList'>
							</div>
				       </div> 

				  </div>
			</div>
          		

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
		
</body>
</html>