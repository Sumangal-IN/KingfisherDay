angular
		.module('kfApp', [ 'cgBusy', 'ngSanitize' ])
		.controller(
				'kfController',
				function($scope, $http) {
					var URL = '';
					$scope.randomColors = [];
					for (var i = 0; i < 30; i++)
						$scope.randomColors.push(Math
								.floor((Math.random() * 5) + 1));

					$scope.showEventList = true;
					$scope.showComment = false;
					$scope.currentEvent = 0;

					$scope.promise = $http.get(URL + '/getAllEvents').then(
							function mySuccess(response) {
								$scope.events = response.data;
								console.log($scope.events);
							}, function mySuccess(response) {
								window.alert('Oops! Some error has occured!');
								console.log(response);
								return;
							});

					$scope.startEvent = function(event) {
						$scope.promise = $http
								.get(
										URL + '/changeEventState/'
												+ event.eventID + '/RUNNING')
								.then(
										function mySuccess(response) {
											$scope.currentEvent = event;
											console.log('setCurrentEvent: '
													+ event.eventID);
											$scope.drawerState = 'drawer_close';
											$scope.showEventList = false;
											$scope.showComment = true;
										},
										function mySuccess(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
											return;
										});
					}

					$scope.endEvent = function(event) {
						$scope.promise = $http
								.get(
										URL + '/changeEventState/'
												+ event.eventID + '/COMPLETED')
								.then(
										function mySuccess(response) {
											console.log('completeEvent: '
													+ event.eventID);
											$scope.promise = $http
													.get(URL + '/getAllEvents')
													.then(
															function mySuccess(
																	response) {
																$scope.drawerState = 'drawer_open';
																$scope.showEventList = true;
																$scope.showComment = false;
																$scope.events = response.data;
																console
																		.log($scope.events);
																$scope
																		.$digest()
															},
															function mySuccess(
																	response) {
																window
																		.alert('Oops! Some error has occured!');
																console
																		.log(response);
																return;
															});
										},
										function mySuccess(response) {
											window
													.alert('Oops! Some error has occured!');
											console.log(response);
											return;
										});
						return;
					}

				});