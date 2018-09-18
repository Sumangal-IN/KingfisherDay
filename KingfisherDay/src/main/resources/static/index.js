angular
		.module('kfApp', [ 'cgBusy', 'ngSanitize' ])
		.directive('fileUpload', function() {
			return {
				scope : true,
				link : function(scope, el, attrs) {
					el.bind('change', function(event) {
						var files = event.target.files;
						for (var i = 0; i < files.length; i++) {
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

					$scope.showQuiz = false;
					$scope.showPhoto = false;
					$scope.showEvent = false;

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

						if (!($scope.photoFile.name.endsWith('.jpg')
								|| $scope.photoFile.name.endsWith('.jpeg')
								|| $scope.photoFile.name.endsWith('.JPEG') || $scope.photoFile.name
								.endsWith('.JPG'))
								|| $scope.photoFile.size > 3500000) {
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
												$scope.showErrorIncorrectMobile = false;
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
												$scope.emailID = data.emailID;
												$scope.showErrorIncorrectCredential = false;
												$scope.showLoginPage = false;
												$scope.showMenuPage = true;
												$scope.showHeaderDP = true;
												$scope.menuActive = 'event';
												$scope.showEvent = true;
												$scope.showPhoto = false;
												$scope.showQuiz = false;
												$scope.loadEvents();
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
						$scope.showQuiz = false;
						$scope.showPhoto = false;
						$scope.showEvent = true;
						$scope.loadEvents();
					}

					$scope.loadEvents = function() {
						$scope.promise = $http
								.get(URL + '/getAllEvents')
								.then(
										function mySuccess(response) {
											console.log(response);
											$scope.events = response.data;
										},
										function myError(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
											return;
										});
					}

					$scope.clickPhoto = function() {
						$scope.menuActive = 'photo';
						$scope.showQuiz = false;
						$scope.showPhoto = true;
						$scope.showEvent = false;
					}

					$scope.clickQuiz = function() {
						$scope.menuActive = 'quiz';
						$scope.showQuiz = true;
						$scope.showPhoto = false;
						$scope.showEvent = false;
						if (!$scope.quizSocket) {
							$scope.questionAvailable = false;
							$scope.questionUnavailable = true;
							$scope.questionUnavailbleText = "Please wait connecting to server";
							$scope.connectingServer = true;
							stompClient = Stomp.over(new SockJS('/quizWS'));
							stompClient.connect({}, onConnectedQuiz,
									onErrorQuiz);
							$scope.$digest();
						}
					}

					$scope.selectOption = function(option) {
						$scope.optionActive == option;
						switch (option) {
						case 'A':
							option = $scope.currentQuestionData.optionA;
							break;
						case 'B':
							option = $scope.currentQuestionData.optionB;
							break;
						case 'C':
							option = $scope.currentQuestionData.optionC;
							break;
						case 'D':
							option = $scope.currentQuestionData.optionD;
							break;
						}
						console.log(option);
						$scope.promise = $http
								.get(
										URL
												+ '/saveResponse/'
												+ $scope.currentQuestionData.questionID
												+ '/' + $scope.emailID + '/'
												+ option)
								.then(
										function mySuccess(response) {
											console.log('answer submitted');
											$scope.optionActive == '';
											$scope.questionAvailable = false;
											$scope.questionUnavailable = true;
											$scope.questionUnavailbleText = 'Response captured, waiting for next question';
											$scope.connectingServer = true;
										},
										function myError(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
											return;
										});
					}

					function onConnectedQuiz() {
						console.log('onConnected()');
						$scope.quizSocket = true;
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

					function onErrorQuiz(error) {
						console.log('onError()');
						console.log(error);
						$scope.questionAvailable = false;
						$scope.questionUnavailable = true;
						$scope.connectingServer = true;
						$scope.$digest();
						$scope.quizSocket = false;
						// $scope.clickQuiz();
						stompClient = Stomp.over(new SockJS('/quizWS'));
						stompClient.connect({}, onConnectedQuiz, onErrorQuiz);
					}

					function onMessageReceivedQuiz(payload) {
						$scope.currentQuestionData = JSON.parse(payload.body);
						console.log($scope.currentQuestionData);
						if ($scope.currentQuestionData.questionUnavailbleText) {
							$scope.questionUnavailbleText = 'This section is closed now and will be availble only during Quiz event of TCS Kingfisher Day (5th October 2018)';
							$scope.questionAvailable = false;
							$scope.questionUnavailable = true;
						} else {
							$scope.questionAvailable = true;
							$scope.questionUnavailable = false;
						}
						$scope.connectingServer = false;
						$scope.$digest();
					}
				});