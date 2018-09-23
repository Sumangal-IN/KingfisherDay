jQuery(document).ready(function($) {
	/************************** Global Variable Section Start **************************/
	var connectionURL='';
	var mobileDetect = new MobileDetect(window.navigator.userAgent);
	var stompClient = null;
	var socket = null;
	var delay=3000;
	/************************** Global Variable Section End **************************/
	//For Phone Gap mobile app, url is required.
	if(mobileDetect.mobile()){
		connectionURL='http://kfday.herokuapp.com';
	}
	console.log("connectionURL="+connectionURL);
	
	if(localStorage.isloggedin === 'true'){
		hideSignInShowLogOff();
	}else{
		showSignInHideLogOff();
	}
	
	/************************** Functions Start **************************/
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
	
	function validateLoginAndOpenModal(modalToOpen){
		if(localStorage.isloggedin === 'true'){
			$.LoadingOverlay("show");
			setTimeout(function(){
				$.LoadingOverlay("hide");
			}, delay);
			$(modalToOpen).modal();
			return true;
		}else{
			$('.js-signin-start').click();
			return false;
		}
		
	}
	function loginWithEmailAndPassword(email, password){
		var url=connectionURL+'/getEmployee/'+email+'/'+password;
		console.log("Login URL:"+url);
		
		$.LoadingOverlay("show");
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
					
					localStorage.clear();
					
					localStorage.loginemail = email;
					localStorage.loginpwd = password;
					if ($('#remember').is(':checked')) {
						localStorage.rememberme = $('#remember').val();
					}

					localStorage.isloggedin = true;
					hideSignInShowLogOff();
					
				}else{
					$('#error-panel').html("Ooops!! Login credential is invalid");
				}
			},
			error: function(error) {
				console.log(error);
				$('#error-panel').html("Ooops!! Looks like there is some technical problem!!");
			},
			complete: function (jqXHR, status) {
				setTimeout(function(){
				    $.LoadingOverlay("hide");
				}, delay);
			}
			
		})
	}
	function hideSignInShowLogOff() {
		$("#signIn").hide();
		$("#logoff").show();
	}
	
	function showSignInHideLogOff(){
		$("#signIn").show();
		$("#logoff").hide();
	}
	/************************** Functions End **************************/
	
	/************************** Quiz Section Start **************************/


	$('.js-open-quiz').on('click', function (e) {
		
		e.preventDefault();
		if(validateLoginAndOpenModal('#quiz-modal')){
			socket = new SockJS(connectionURL+'/quizWS');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, onConnected, onError);
		}
		

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

		$.LoadingOverlay("show");
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
			},
			complete: function (jqXHR, status) {
				setTimeout(function(){
				    $.LoadingOverlay("hide");
				}, delay);
			}
		})
		
	});

	$(document).on("click", '.js-prev-question', function(e) { 
		$('.close-modal').click();
	});
	
	/************************** Quiz Section End **************************/

	/************************** Event Section Start **************************/
	
	$('#events').on('click', function(e){
		e.preventDefault();
		validateLoginAndOpenModal('#event-modal');
		
	});
	
	/************************** Event Section End **************************/
	
	/************************** Contest Section Start **************************/
	
	$('#contest').on('click', function(e){
		e.preventDefault();
		validateLoginAndOpenModal('#portfolio-modal');
		
	});
	/************************** Contest Section End **************************/
	
	
	/************************** Login/Registration Section Start **************************/
	
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

		$.LoadingOverlay("show");
		setTimeout(function(){
			$.LoadingOverlay("hide");
		}, delay);		
		//localStorage.clear();
		localStorage.isloggedin = false;
		
		showSignInHideLogOff();
		
		location.reload(true);
		
	});
	
	
	
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
			if(imageSizeinKB > 3072){
		       $('#error-panel').html("File is too big!!. Max size: 3 MB");
		       return;
		    };
		    
			var fd = new FormData();
			fd.append('photoFile', data.image);

			console.log(data);
			var url=connectionURL+'/registerEmployee/'+data.name+'/'+data.email+'/'+data.foodpref+'/'+data.password+'/'+data.mobile+"/";
			console.log(url);
			
			$.LoadingOverlay("show");
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
						$('#user_details').html(Mustache.to_html(template1, employee));
						
						//clear local storage
						localStorage.clear();
						//save into local storage
						localStorage.loginemail = data.email;
						localStorage.loginpwd = data.password;
						
						hideSignInShowLogOff();
					}else{
						$('#error-panel').html("Ooops!! Registration failed. Re-check your input data!!");
					}
				},
				error: function(error) {
					console.log(error);
					$('#error-panel').html("Ooops!! Registration failed. Re-check your input data!!");
					if(error.responseJSON && error.responseJSON.message){
						$('#error-panel').html(error.responseJSON.message);
					}
				},
				complete: function (jqXHR, status) {
					setTimeout(function(){
					    $.LoadingOverlay("hide");
					}, delay);
				}
			})

		}
	})
	/************************** Login/Registration Section End **************************/
});
//'https://api.myjson.com/bins/ux73o', 'https://api.myjson.com/bins/ncltw'
