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
					// $scope.promise = $http.get('http://localhost:8080/delay')
					// .then(function mySuccess(response) {
					// console.log(response);
					// }, function myError(response) {
					// console.log(response);
					// return;
					// });
					var URL = '';
					// var URL = 'http://64405d9f.ngrok.io';

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

					$scope.clickQuiz = function() {
						$scope.showQuiz = true;
					}

				});