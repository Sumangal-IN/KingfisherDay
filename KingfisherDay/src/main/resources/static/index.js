angular
		.module('kfApp', [])
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
					var URL = 'http://localhost:8080';
					var authKey = '130190AmlXD2ELBEmi581b5034';

					$scope.showLoginPage = true;
					$scope.foodPref = 'VEG';

					$scope.gotoRegister = function() {
						console.log('gotoRegister');
						$scope.showLoginPage = false;
						$scope.showRegisterPage = true;
					}

					$scope.$on("fileSelected", function(event, args) {
						$scope.$apply(function() {
							$scope.photoFile = args.file;
						});
					});

					$scope.registerSubmitOTP = function() {
						console.log('registerSubmitOTP');
						if ($scope.registerEmail == undefined
								|| ($scope.registerEmail != undefined && !$scope.registerEmail
										.endsWith('@tcs.com'))) {
							$scope.showErrorInvalidEmail = true;
							return;
						}
						$scope.showErrorInvalidEmail = false;

						if ($scope.registerPassword == undefined
								|| ($scope.registerPassword != undefined && !$scope.registerPassword == null)) {
							$scope.showErrorEmptyPassword = true;
							return;
						}
						$scope.showErrorEmptyPassword = false;

						if ($scope.registerName == undefined
								|| ($scope.registerName != undefined && !$scope.registerName == null)) {
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
						$scope.showErrorIncorrectMobile = false;

						var otp = '1234';

						var fd = new FormData();
						fd.append('photoFile', $scope.photoFile);
						$http.post(
								URL + '/registerEmployee/'
										+ $scope.registerName + '/'
										+ $scope.registerEmail + '/'
										+ $scope.foodPref + '/'
										+ $scope.registerPassword, fd, {
									transformRequest : angular.identity,
									headers : {
										'Content-Type' : undefined
									}
								}).then(function mySuccess(response) {
							console.log(response);
						}, function myError(response) {
							window.alert('Oops! Some error has occured!');
							console.log(response);
						});
					}

					$scope.loginSubmit = function() {
						console.log('loginSubmit');
						if ($scope.loginEmail == undefined
								|| ($scope.loginEmail != undefined && !$scope.loginEmail
										.endsWith('@tcs.com'))) {
							$scope.showErrorInvalidEmail = true;
							return;
						}
						$scope.showErrorInvalidEmail = false;

						if ($scope.loginPassword == undefined
								|| ($scope.loginPassword != undefined && !$scope.loginPassword == null)) {
							$scope.showErrorEmptyPassword = true;
							return;
						}
						$scope.showErrorEmptyPassword = false;

						console.log(URL + '/isValidEmployee/'
								+ $scope.loginEmail + '/'
								+ $scope.loginPassword);
						$http
								.get(
										URL + '/isValidEmployee/'
												+ $scope.loginEmail + '/'
												+ $scope.loginPassword)
								.then(
										function mySuccess(response) {
											var data = response.data;
											console.log(data);
											if (data) {
												$scope.showErrorIncorrectCredential = false;
												$scope.showLoginPage = false;
											} else {
												$scope.showErrorIncorrectCredential = true;
											}
										},
										function myError(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
										});

					}

				});