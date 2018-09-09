angular
		.module('kfApp', [ 'cgBusy' ])
		.directive('fileUpload', function() {
			return {
				scope : true, // create a new scope
				link : function(scope, el, attrs) {
					el.bind('change', function(event) {
						var files = event.target.files;
						// iterate files since 'multiple' may be specified on
						// the element
						for (var i = 0; i < files.length; i++) {
							// emit event upward
							scope.$emit("fileSelected", {
								file : files[i]
							});
						}
					});
				}
			};
		})
		.controller(
				'kfController',
				function($scope, $http) {

					var URL = '';

					$scope.showLoginPage = true;
					$scope.foodPref = 'VEG';
					$scope.uploadText = 'Select your file';
					$scope.showGetOTP = true;
					$scope.showVerifyOTP = false;

					var randomFixedInteger = function(length) {
						return Math.floor(Math.pow(10, length - 1)
								+ Math.random()
								* (Math.pow(10, length)
										- Math.pow(10, length - 1) - 1));
					}

					var otp = randomFixedInteger(5);

					$scope.gotoRegister = function() {
						$scope.showErrorInvalidEmail = false;
						$scope.showErrorEmptyPassword = false;
						$scope.showErrorIncorrectCredential = false;

						console.log('gotoRegister');

						$scope.showLoginPage = false;
						$scope.showRegisterPage = true;
					}

					$scope.$on("fileSelected", function(event, args) {
						$scope.$apply(function() {
							$scope.photoFile = args.file;
						});
					});

					$scope.fileNameChanged = function() {
						$scope.fileSelected = true;
						$scope.uploadText = 'File selected';
					}

					$scope.registerSubmitOTP = function() {
						console.log('registerSubmitOTP');
						if ($scope.registerEmail == undefined
								|| ($scope.registerEmail != undefined && !$scope.registerEmail
										.endsWith('@tcs.com'))
								|| ($scope.registerEmail != undefined && !$scope.registerEmail == null)) {
							$scope.showErrorInvalidEmail = true;
							return;
						}
						$scope.showErrorInvalidEmail = false;

						if ($scope.registerPassword == undefined
								|| ($scope.registerPassword != undefined && !$scope.registerPassword == null)
								|| ($scope.registerPassword != undefined && $scope.registerPassword == '')) {
							$scope.showErrorEmptyPassword = true;
							return;
						}
						$scope.showErrorEmptyPassword = false;
						console.log($scope.registerName);
						if ($scope.registerName == undefined
								|| ($scope.registerName != undefined && !$scope.registerName == null)
								|| ($scope.registerName != undefined && $scope.registerName == '')) {
							$scope.showErrorEmptyName = true;
							return;
						}
						$scope.showErrorEmptyName = false;

						if ($scope.photoFile == undefined) {
							$scope.showErrorNoPhotoSelected = true;
							return;
						}
						$scope.showErrorNoPhotoSelected = false;

						if (!($scope.photoFile.name.endsWith('.jpg') || $scope.photoFile.name
								.endsWith('.jpeg'))
								|| $scope.photoFile.size > 5500000) {
							$scope.showErrorPhotoFileIncorrect = true;
							return;
						}
						$scope.showErrorPhotoFileIncorrect = false;

						if ($scope.registerMobile == undefined
								|| ($scope.registerMobile != undefined && $scope.registerMobile.length != 10)) {
							$scope.showErrorIncorrectMobile = true;
							return;
						}
						$scope.promise = $http
								.get(
										URL + '/isExistsEmployee/'
												+ $scope.registerEmail + '/'
												+ $scope.registerMobile)
								.then(
										function mySuccess(response) {
											var data = response.data;
											console.log(data);
											if (data) {
												$scope.showErrorUserAlreadyExists = true;
												return;
											} else {
												$scope.showErrorUserAlreadyExists = false;
												$scope.promise = $http
														.get(
																URL
																		+ '/sendOTP/'
																		+ $scope.registerMobile
																		+ '/'
																		+ otp)
														.then(
																function mySuccess(
																		response) {
																	var data = response.data;
																	console
																			.log(data);
																	$scope.showVerifyOTP = true;
																	$scope.showGetOTP = false;
																	return;
																},
																function myError(
																		response) {
																	window
																			.alert('Oops! Some error has occured!');
																	console
																			.log(response);
																	return;
																});
											}
										},
										function myError(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
											return;
										});
					}

					$scope.validateOTP = function() {
						if ($scope.registerOTP != undefined
								&& otp == $scope.registerOTP) {
							$scope.showErrorOTP = false;
							var fd = new FormData();
							fd.append('photoFile', $scope.photoFile);
							$scope.promise = $http.post(
									URL + '/registerEmployee/'
											+ $scope.registerName + '/'
											+ $scope.registerEmail + '/'
											+ $scope.foodPref + '/'
											+ $scope.registerPassword + '/'
											+ $scope.registerMobile, fd, {
										transformRequest : angular.identity,
										headers : {
											'Content-Type' : undefined
										}
									}).then(function mySuccess(response) {
								console.log(response);
								$scope.showRegisterPage = false;
								$scope.showLoginPage = true;
								$scope.showRegisterSuccess = true;
							}, function myError(response) {
								window.alert('Oops! Some error has occured!');
								console.log(response);
								return;
							});
						} else {
							$scope.showErrorOTP = true;
							return;
						}
					}

					$scope.loginSubmit = function() {
						console.log('loginSubmit');
						$scope.showRegisterSuccess = false;
						if ($scope.loginEmail == undefined
								|| ($scope.loginEmail != undefined && !$scope.loginEmail
										.endsWith('@tcs.com'))
								|| ($scope.loginEmail != undefined && $scope.loginEmail == '')) {
							$scope.showErrorInvalidEmail = true;
							return;
						}
						$scope.showErrorInvalidEmail = false;

						if ($scope.loginPassword == undefined
								|| ($scope.loginPassword != undefined && !$scope.loginPassword == null)
								|| ($scope.loginPassword != undefined && $scope.loginPassword == '')) {
							$scope.showErrorEmptyPassword = true;
							return;
						}
						$scope.showErrorEmptyPassword = false;

						$scope.promise = $http
								.get(
										URL + '/getEmployee/'
												+ $scope.loginEmail + '/'
												+ $scope.loginPassword)
								.then(
										function mySuccess(response) {
											var data = response.data;
											console.log(data);
											if (data) {
												$scope.name = data.name;
												$scope.photo = data.photo;
												$scope.showErrorIncorrectCredential = false;
												$scope.showLoginPage = false;
												$scope.showMenuPage = true;
												$scope.showHeaderDP = true;
											} else {
												$scope.showErrorIncorrectCredential = true;
												return;
											}
										},
										function myError(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
											return;
										});
					}

					$scope.stompClient = null;

					$scope.clickEvent = function() {
						$scope.menuActive = 'event';
					}

					$scope.clickPhoto = function() {
						$scope.menuActive = 'photo';
						$scope.showQuiz = true;
					}

					$scope.clickQuiz = function() {
						$scope.questionUnavailable = true;
						$scope.questionUnavailbleText = "Please wait checking quiz availibility ....";
						$scope.menuActive = 'quiz';
						$scope.showQuiz = true;
						var socket = new SockJS('/quizWS');
						stompClient = Stomp.over(socket);
						stompClient.connect({}, onConnected, onError);
					}

					function onConnected() {
						console.log('onConnected()');
						stompClient.subscribe('/user/topic/getCurrentQuestion',
								onMessageReceivedQuiz);
						stompClient.subscribe(
								'/topic/broadcastCurrentQuestion',
								onMessageReceivedQuiz);
						var d = new Date();
						var n = d.getTime();
						stompClient.send("/app/getCurrentQuestion", {},
								'{"currentMili":' + n + '}');
					}

					function onError(error) {
						console.log('onError()');
						console.log(error);
						$scope.questionUnavailable = true;
						$scope.questionUnavailbleText = "Server is unreachable at the moment, please try after sometime.";
					}

					function onMessageReceivedQuiz(payload) {
						console.log('oneMessageReceived()')
						$scope.currentQuestionData = JSON.parse(payload.body);
						console.log($scope.currentQuestionData);
						if ($scope.currentQuestionData.questionUnavailbleText) {
							$scope.questionUnavailbleText = 'This section will be availble during Quiz event on Kingfisher Day (5th October 2018)';
							$scope.questionAvailable = false;
							$scope.questionUnavailable = true;
						} else {
							$scope.questionAvailable = true;
							$scope.questionUnavailable = false;
						}
						$scope.$digest();
					}
				});