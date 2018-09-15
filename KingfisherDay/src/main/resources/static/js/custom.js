jQuery(document).ready(function($) {
	
	var connectionURL='';
	var mobileDetect = new MobileDetect(window.navigator.userAgent);
	//For Phone Gap mobile app, url is required.
	if(mobileDetect.mobile()){
		connectionURL='http://kfday.herokuapp.com';
	}
	console.log("connectionURL="+connectionURL);
	
	if(localStorage.loginemail && localStorage.loginpwd){
		loginWithEmailAndPassword(localStorage.loginemail, localStorage.loginpwd);
	}

	var stompClient = null;
	var socket = null;

	function onConnected() {
		stompClient.subscribe('/user/topic/getCurrentQuestion', onMessageReceivedQuiz);
		stompClient.subscribe('/topic/broadcastCurrentQuestion', onMessageReceivedQuiz);
		var d = new Date();
		var n = d.getTime();
		stompClient.send("/app/getCurrentQuestion", {},
				'{"currentMili":' + n + '}');
	}

	function onError(e) {
		console.log("error:" + e);
	}


	function onMessageReceivedQuiz(payload) {
		var data=JSON.parse(payload.body);
		
		var template,
		contentHtml;

		if(data.current) {
			template = $('#ui-template-quiz').html();                    
		}else{
			template = $('#ui-template-quiz-inactive').html();
		}
		contentHtml = Mustache.to_html(template, data);
		$('#question-container').html(contentHtml);
	}

	$('.js-open-quiz').on('click', function () {
		socket = new SockJS(connectionURL+'/quizWS');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);

	});

	$(document).on("change", 'input[name="q_answer"]', function(e) { 
		e.preventDefault();
		$('.js-submit-answer').removeClass('disabled');
	});

	$(document).on("click", '.js-submit-answer', function(e) { 
		e.preventDefault();
		$(this).addClass('disabled');
		
		var radioValueOfAnsweredQuestion = $("input[name='q_answer']:checked").val();
        if(radioValueOfAnsweredQuestion){
            alert("Your have selected:" + radioValueOfAnsweredQuestion);
        }
        var questionID=$('#questionID').text().trim();
        var emailId=$('#account_email').text().trim();

		var url=connectionURL+'/saveResponse/'+questionID+'/'+emailId+'/'+radioValueOfAnsweredQuestion;
		console.log(url);

		$.ajax({
			url: url,
			processData: false,
			contentType: false,
			type: 'get',
			success: function (res) {
				console.log(res);
			},
			error: function(error) {
				console.log(error);
			}
		})
		
	});

	$(document).on("click", '.js-prev-question', function(e) { 
		$('.close-modal').click();
	});

	$.validator.addMethod('imageValidator', function (img, element) {
		console.log(ing);
		return false;
	}, "not supported");

	$('#register-form').validate({
		rules : {
			password : {
				minlength : 6
			},
			confirm_password : {
				minlength : 6,
				equalTo : "#reg-password"
			}
		}
	});
	
	$('.js-signin-start').on('click', function() {
        if (localStorage.rememberme && localStorage.rememberme != '') {
            $('#remember').attr('checked', 'checked');
            $('#login-email').val(localStorage.loginemail);
            $('#login-password').val(localStorage.loginpwd);
        } else {
            $('#remember').removeAttr('checked');
            $('#login-email').val('');
            $('#login-password').val('');
        }
    });
	
	$('#login-form').on('submit', function(e){

		e.preventDefault();

		if($('#login-form').valid()) {

			var data = {
					name: $('#login-email').val(),
					password: $('#login-password').val()
			}

			console.log(data);
			loginWithEmailAndPassword(data.name,data.password);
		}
	});
	
	$('#logoff').on('click', function(e){

		e.preventDefault();
		
		localStorage.clear();
		
		hideContentAfterLogOff();
		
		location.reload(true);
		
	});
	
	function loginWithEmailAndPassword(email, password){
		var url=connectionURL+'/getEmployee/'+email+'/'+password;
		console.log("Login URL:"+url);
		
		$.ajax({
			url: url,
			processData: false,
			contentType: false,  
			type: 'get',
			success: function (employee) {
				console.log(employee);

				if(employee){
					$('.close-modal').click();
					var template1 = $('#ui-template-account-details').html();
					var template2 = $('#ui-template-account-photo').html();    
					$('#user_details').html(Mustache.to_html(template1, employee));
					$('.home-slider').html(Mustache.to_html(template2, employee));
					
					if ($('#remember').is(':checked')) {
						localStorage.loginemail = $('#login-email').val();
						localStorage.loginpwd = $('#login-password').val();
						localStorage.rememberme = $('#remember').val();
					}
					displayItemsAfterLogin();

				}else{
					$('#error-panel').html("Ooops!! Login credential is invalid");
				}
			},
			error: function(error) {
				console.log(error);
				$('#error-panel').html("Ooops!! Looks like there is some technical problem!!");
			}
			
		})
	}
	function displayItemsAfterLogin() {
		$('#signIn').css('display','none');
		
		$('#logoff').css('display','block');
		$('#events').css('display','block');
		$('#quiz').css('display','block');
		$('#contest').css('display','block');
	}
	
	function hideContentAfterLogOff(){
		$('#signIn').css('display','block');
		
		$('#logoff').css('display','none');
		$('#events').css('display','none');
		$('#quiz').css('display','none');
		$('#contest').css('display','none');
	}
	
	$('#register-form').on('submit', function(e){

		e.preventDefault();

		if($('#register-form').valid()) {

			var data = {
					name: $('#reg-name').val(),
					email: $('#reg-email').val(),
					password: $('#reg-password').val(),
					mobile: $('#reg-mobile').val(),
					foodpref: $('input[name="options"]:checked').val(),
					image: $('input[name="account-file"]').get(0).files[0]
			}
			var imageSizeinKB=data.image.size/1024;
			if(imageSizeinKB > 100){
		       $('#error-panel').html("File is too big!!. Max size: 100 KB");
		       return;
		    };
		    
			var fd = new FormData();
			fd.append('photoFile', data.image);

			console.log(data);
			var url=connectionURL+'/registerEmployee/'+data.name+'/'+data.email+'/'+data.foodpref+'/'+data.password+'/'+data.mobile+"/";
			console.log(url);
			
			$.ajax({
				url: url,     
				data: fd,
				processData: false,
				contentType: false,   
				enctype: 'multipart/form-data', 
				type: 'post',
				success: function (employee) {
					console.log(employee);
					if(employee){
						$('.close-modal').click();
						var template1 = $('#ui-template-account-details').html();
						var template2 = $('#ui-template-account-photo').html();    
						$('#user_details').html(Mustache.to_html(template1, employee));
						$('.home-slider').html(Mustache.to_html(template2, employee));
						//save into local storage
						localStorage.loginemail = data.email;
						localStorage.loginpwd = data.password;
						
						displayItemsAfterLogin();
					}else{
						$('#error-panel').html("Ooops!! Registration failed. Re-check your input data!!");
					}
				},
				error: function(error) {
					console.log(error.responseJSON.message);
					$('#error-panel').html(error.responseJSON.message);
				}
			})

		}
	})
});
//'https://api.myjson.com/bins/ux73o', 'https://api.myjson.com/bins/ncltw'
