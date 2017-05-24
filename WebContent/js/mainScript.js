$(document).ready(function(){

		var postCount = 0;

		// Weather Info Logic
		$("#weatherOne").click(homeClick);
		$("#weatherTwo").click(menuClick);
		
		$("#searchMainButton").click(homeClick);
		$("#searchMainSecond").click(menuClick);
		
		$('#postSection').click(postUpload);
		$('#updateInfo').click(updateInfo);
		
		$('#bell').click(bellClick);
		
		homeClick();
		getPostData();
		
		/* Disabled for temporary */
		notificationLogic();

		function bellClick()
		{
			getPostData();
		};

		function notificationLogic() {
		    setInterval(bellFunction, 5000);
		};

		function bellFunction()
		{
			$.ajax("http://localhost:8080/travelApp/GetBellFunction", {
				"type": "post"
			})
			.done(doneBellFunction)
			.fail(ajaxFailure);
		};

		function doneBellFunction(data)
		{
			var currentCount = parseInt(data);
			var difference = currentCount - postCount;

			if(difference > 0)
			{
				$('#bell span').text(difference);
			}
		};

		// Get All Post Data
		function getPostData()
		{
			$.ajax("http://localhost:8080/travelApp/GetAllPostData", {
				"type": "post"
			})
			.done(donePostData)
			.fail(ajaxFailure);
		};
		
		function donePostData(data)
		{
			$("#postJagsaalt").empty();
			$(".postsList").empty();

		    var html = '';

			$.each(data, function(i, val) {

				html += '<div class="panel panel-default">';
				html += '<div class="panel-body">';
				html += '<section class="post-heading">';
				html += '<div class="row">';
				html += '<div class="col-md-11">';
				html += '<div class="media">';
				html += '<div class="media-left">';
				html += '<a href="#">';
				html += '<img class="media-object photo-profile"';
				html += 'src="http://0.gravatar.com/avatar/38d618563e55e6082adf4c8f8c13f3e4?s=40&d=mm&r=g" width="40" height="40" alt="...">';
				html += '</a>';
				html += '</div>';
				html += '<div class="media-body">';
				html += '<a href="#" class="anchor-username"><h4 class="media-heading">' + val.user.fullname;
				html += '</h4></a>';
				html += '</div>';
				html += '</div>';
				html += '</div>';
				html += '<div class="col-md-1">';
				html += '<a href="#"><i class="glyphicon glyphicon-chevron-down"></i></a>';
				html += '</div>';
				html += '</div>';
				html += '</section>';
				html += '<section class="post-body"><br/>';

				let filePath = 'pictureUpload/' + val.imageName;

				html += '<img src="'+filePath+'" style="width: 100%; margin: 0px 5px;"></img><br/>';
				html += '<p>' + val.post + '</p>';
				html += '</section>';
				html += '<section class="post-footer"><hr>';
				html += '<div class="post-footer-option container">';
				html += '<li><span class="likeButton" data-post-id="'+val.idposts+'">';
				html += '<i class="glyphicon glyphicon-thumbs-up"></i> <span class="badge" data-like-count="'+ val.like +'">' + val.like + '</span></span>';
				html += '</li>';
				html += '<li><a href="#"><i class="glyphicon glyphicon-comment"></i> Comment</a></li><br/>';
				html += '</ul>';
				html += '</div>';
				html += '<div class="post-footer-comment-wrapper">';
				html += '<div class="comment-form">';
				html += '<textarea rows="4" cols="50" placeholder="Write A Comment" style="width: 100%; margin: 10px 5px;" class="commentText"></textarea>';
				html += '<button type="submit" data-post-id="'+val.idposts+'"  data-post-user="'+val.user.idusers+'" class="btn btn-success green commentButton"><i class="fa fa-share"></i>Comment</button><br/>';		
				html += '</div>';

				for (x in val.listComments) {
    				html += '<div class="comment">';
					html += '<div class="media">';
					html += '<div class="media-left">';
					html += '<a href="#">';
					html += '<img class="media-object photo-profile" src="http://0.gravatar.com/avatar/38d618563e55e6082adf4c8f8c13f3e4?s=40&d=mm&r=g" width="32" height="32" alt="...">';
					html += '</a>';
					html += '</div>';
					html += '<div class="media-body">';
					html += '<a href="#" class="anchor-username"><h4 class="media-heading">';
					html +=  val.listComments[x].fullname +  '</h4>';
					html += '<p>' + val.listComments[x].user.comment + '</p>';
					html += '</a>';

					html += '</div>';
					html += '</div>';
				}

				html += '</div>';
				html += '</div>';
				html += '</section>';
				html += '</div>';
				html += '</div>';
				
				$("#postsList").append(html);

				html = '';
			});

			$('.commentButton').click(commenAdd);
			$('.likeButton').click(likeClick);

			postCount = data.length;
		};
		
		function updateInfo()
		{
			event.preventDefault();
			
			let fullName = $('#fullName').val();
			let gender = $('#gender').val();
			let state = $('#state').val();
			let city = $('#city').val();
			let zipcode = $('#zipcode').val();
			let birthYear = $('#birthYear').val();
			let loginEmail = $('#loginEmail').val();

			$.ajax("http://localhost:8080/travelApp/EditUser", {
				"type": "post",
				"data": {
					"fullName": fullName,
					"gender" : gender,
					"state" : state,
					"city": city,
					"zipcode" : zipcode,
					"birthYear" : birthYear,
					"loginEmail" : loginEmail
				}
			})
			.done(alert('Successfully saved !!!'))
			.fail(ajaxFailure);
		};
		
		function postUpload()
		{
			event.preventDefault();
			var files = pictureFile.files;
			
			// Create a new FormData object.
			var formData = new FormData();
			
			// Loop through each of the selected files.
			for (var i = 0; i < files.length; i++) {
			  var file = files[i];

			  // Add the file to the request.
			  formData.append('photos[]', file, file.name);
			}
			
			var postContent =  $('#postContent').val();
			
			formData.append('postContent', postContent); 
						
			$.ajax("http://localhost:8080/travelApp/Post", {
				processData: false,
				contentType: false,
				"data": formData,
				"type": "post",
			})
			.done(formDone)
			.fail(ajaxFailure);
		};
		
		function formDone()
		{
			alert("Post Added !!!");
			getPostData();
		};
		
		function commenAdd()
		{
			debugger;
			let commentText = $('.commentText').val();
			let userId = $('.commentButton').attr('data-post-user');
			let postId = $('.commentButton').attr('data-post-id');
			
			$.ajax("http://localhost:8080/travelApp/CommentAdd", {
				"type": "post",
				"data": {
					"postId": postId,
					"commentText" : commentText,
					"userId" : userId
				}
			})
			.done(commentAddDone)
			.fail(ajaxFailure);
		}
		
		function commentAddDone()
		{
			alert('Comment Added !!!');
			getPostData();
		}
		
		function likeClick()
		{
			let postId = $('.likeButton').attr('data-post-id');
			var likeCount = $('.likeButton .badge').attr('data-like-count')

			$.ajax("http://localhost:8080/travelApp/LikeAdd", {
				"type": "post",
				"data": {
					"postId": postId,
					"like" : likeCount
				}
			})
			.done(likeButton)
			.fail(ajaxFailure);
		};
		
		function likeButton(data) {
			getPostData();
		};
		
		function homeClick()
		{
			var zipcode = $("#searchWeatherMain").val();
			
			$.ajax("http://api.openweathermap.org/data/2.5/weather", {
				"type": "get",
				"data": {
					"zip": zipcode,
					"appid": "2c7c106de9a4a02f0a3e15c6b694c7c3"
				}
			})
			.done(weatherGeneralInfo)
			.fail(ajaxFailure);
		};

		function menuClick()
		{
			var zipcode = $("#searchWeatherSecond").val();

			$.ajax("http://api.openweathermap.org/data/2.5/forecast", {
				"type": "get",
				"data": {
					"zip": zipcode,
					"appid": "2c7c106de9a4a02f0a3e15c6b694c7c3"
				}
			})
			.done(weatherDetailInfo)
			.fail(ajaxFailure);
		}

		function weatherGeneralInfo(data) {

			$("#weatherHome").empty();

			$("#weatherHome").append("<p><strong>City name:</strong> " + data.name + "<strong> |  Temparature:</strong>" + data.main.temp + " | <strong> Pressure:</strong> " + data.main.pressure + "</p>");

			$("#weatherHome").append("<p><strong>  Humidity:</strong> " + data.main.humidity + " | <strong>  Temp Max: </strong> " + data.main.temp_max + " | <strong>  Temp Min:</strong> " + data.main.temp_min + "</p>");
	
		}

		function weatherDetailInfo(data) {

			$("tbody").empty();
			$(".title").empty();
			
			$("#weatherSecond").prepend("<h3 class='title'> City: " + data.city.name + "</h3>");

			for(x in data.list)
			{				
				$("tbody").append("<tr><td>" + data.list[x].dt_txt + "</td>"
					 + "<td>" + data.list[x].main.temp + "</td>"
					 + "<td>" + data.list[x].main.pressure + "</td>"
					 + "<td>" + data.list[x].main.humidity + "</td></tr>");
			}
		}

		function ajaxFailure(xhr, status, exception) {
			console.log(xhr, status, exception);
		}
});