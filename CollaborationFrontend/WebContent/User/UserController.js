'use strict';

app.controller('UserController',['$scope','UserService','$location','$rootScope','$cookieStore','$http',
						function($scope, UserService, $location, $rootScope,$cookieStore, $http) {
							console.log("UserController...")
							var self = this;
						
							self.user = {id : '',name : '',password : '',mobile : '',address : '',email : '',zipcode : '',role : ''
							};

							self.currentUser = {id : '',name : '',password : '',mobile : '',address : '',email : '',zipcode : '',role : ''
							};

							self.users = []; // json array

							/*$scope.orderByMe = function(x) {
								$scope.myOrderBy = x;
							}*/

							self.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};

							// this.fatchAllUsers();

							self.createUser = function(user) {
								console.log("createUser...")
								UserService
										.createUser(user)
										.then(
												function(d) {
													alert("Thank you for registration")
													$location.path("/")
												},
												function(errResponse) {
													console
															.error('Error while creating User.');
												});
							};

							self.myProfile = function() {
								console.log("myProfile...")
								UserService
										.myProfile()
										.then(
												function(d) {
													self.user = d;
													$location
															.path("/myProfile")
												},
												function(errResponse) {
													console
															.error('Error while fetch profile.');
												});
							};

							self.accept = function(id) {
								console.log("accept...")
								UserService
										.accept(id)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													$location
															.path("/manage_users")
													alert(self.user.errorMessage)

												},

												function(errResponse) {
													console
															.error('Error while updating User.');
												});
							};

							self.reject = function(id) {
								console.log("reject...")
								var reason = prompt("Please enter the reason");
								UserService.reject(id, reason).then(
										function(d) {
											self.user = d;
											self.fetchAllUsers
											$location.path("/manage_users")
											alert(self.user.errorMessage)

										}, null);
							};

							self.updateUser = function(currentUser) {
								console.log("updateUser...")
								UserService.updateUser(currentUser).then(
										self.fetchAllUsers, null);
							};

							self.update = function() {
								{
									console.log('Update the user details',
											$rootScope.currentUser);
									self.updateUser($rootScope.currentUser);
								}
								self.reset();
							};

							  
						self.login = function() {
								UserService.login(self.user)
								.then(function(response) {
									console.log(response);
									console.log(response.status);
									$scope.user = response.data;
								$rootScope.currentUser = response.data;
								console.log(user);
								$cookieStore.put("user", response.data);
									$location.path('/home');
								}, function(errResponse) {
									//console.log(response.status)
									$scope.message = response.data.message;
									$location.path('/login')
								})  
							};
							self.logout = function() {
								console.log("logout");
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}

							self.submit = function() {
								{
									console.log('Saving New User', self.user);
									self.createUser(self.user);
								}
								self.reset();
							};
 
							self.reset = function() {
								self.user = {id : '',name : '',password : '',mobile : '',address : '',email : '',zipcode : '',role : ''
								};
								//$scope.myForm.$setPristine(); // reset Form
							};

						} ]);
