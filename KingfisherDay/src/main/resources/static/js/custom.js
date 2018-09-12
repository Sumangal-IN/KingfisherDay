
jQuery(document).ready(function($) {
	
	var connectionURL='http://localhost:8080';

    function getQuestion(url) {
        $.ajax({
            url: url,          
            type: 'get',
            success: function (data) {
                var template,
                    contentHtml;

                if(data.responsecode === '0') {
                    template = $('#ui-template-quiz-inactive').html();                    
                } else if(data.responsecode === '3'){
                       template = $('#ui-template-quiz-disabled').html(); 
                } else {
                    template = $('#ui-template-quiz').html();                    
                }
                contentHtml = Mustache.to_html(template, data);
                $('#question-container').html(contentHtml);
            },
            error: function(error) {
                console.log(error);
            }
        })
    }

    function onConnected() {
        stompClient.subscribe('/user/topic/getCurrentQuestion', onMessageReceivedQuiz);
        stompClient.subscribe('/topic/broadcastCurrentQuestion', onMessageReceivedQuiz);
    }

    function onError(e) {
        console.log("error:" + e);
    }
     
     
    function onMessageReceivedQuiz(payload) {
        console.log(payload)
    }

    $('#quiz-modal').on('shown.bs.modal', function () {
        //getQuestion('https://api.myjson.com/bins/om038');//url: 'https://api.myjson.com/bins/heno4', //Inactive quiz

        var stompClient = null,
                    socket = new SockJS('https://kfday.herokuapp.com/quizWS');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, onConnected, onError);

    });

    $(document).on("change", 'input[name="q_answer"]', function(e) { 
        e.preventDefault();
        $('.js-submit-answer').removeClass('disabled');
    });

    $(document).on("click", '.js-submit-answer', function(e) { 
        e.preventDefault();
        $('.js-next-question').removeClass('disabled');
        $(this).addClass('disabled');
    });

    $(document).on("click", '.js-next-question', function(e) { 
        getQuestion('https://api.myjson.com/bins/ux73o'); //url: 'https://api.myjson.com/bins/heno4', //Inactive quiz
    }); 
    $(document).on("click", '.js-prev-question', function(e) { 
        getQuestion('https://api.myjson.com/bins/sppzo'); //next question not active - back to prev question
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

    $('#login-form').on('submit', function(e){
				
        e.preventDefault();

        if($('#login-form').valid()) {

            var data = {
                name: $('#signin-username').val(),
                password: $('#signin-password').val()
            }
			
            console.log(data);
			var url=connectionURL+'/getEmployee/'+data.name+'/'+data.password;
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
                success: function (res) {
                    console.log(res);
                },
                error: function(error) {
                    console.log(error);
                }
            })

        }
    })
});
//'https://api.myjson.com/bins/ux73o', 'https://api.myjson.com/bins/ncltw'