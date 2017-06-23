'use strict';

app.controller('UserController',['$scope','UserService','FriendService','$location','$rootScope','$cookies','$cookieStore','$http',function($scope, UserService,FriendService, $location, $rootScope,$cookies,$cookieStore, $http) {
							console.log("UserController...")
							var i = 0;
							var j = 0;
							var self = this;
							self.user = {id : '',name : '',password : '',mobile : '',address : '',email : '',zipcode : '',role : '',isOnline:''};

							self.currentUser = {id : '',name : '',password : '',mobile : '',address : '',email : '',zipcode : '',role : '',isOnline:''};
							self.friend = {id:'',friendId : '',userId: '',userName:'',status:'',friendName:'',isOnline:'',lastSeen:''};
							self.users = [];
							self.friends = [];// json array
							var arr=[];
							var friendarr=[];
							$scope.orderByMe = function(x) {
								$scope.myOrderBy = x;
							}  
							
							self.fetchUserList = function(){
								console.log("fetchUserList...")
								UserService .fetchAllUsers().then(function(d) {
									self.users = d;
									for(i=0; i<self.users.length; i++)
										{
										if(self.users[i].role!='ADMIN'){
											arr.push(self.users[i])													
										}
										}
									self.us = arr;	
									console.log(self.us)	
									},function(errResponse) {
										console.error('Error while fetching Users');
									});				
								
							};
							
							self.fetchAllRequestedFriend = function(loginUser){
								console.log("fetchAllRequestedFriend...")
								FriendService.fetchAllRequestedfriends(loginUser.id).then(function(d) {
									self.friends = d;
									console.log(self.friends)					
						
									},function(errResponse) {
										console.error('Error while fetching Friends');
									} );
								
							};

							self.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								$scope.loginUser =$rootScope.currentUser;
								
								self.fetchUserList();
								self.fetchAllRequestedFriend($scope.loginUser);
								
								for(i=0; i<self.friends.length; i++){
									for(j=0; j<self.us.length; j++){
										if(self.friends[i].friendId === self.us[j].id){
											self.us.splice(j, 1);
											console.log(self.us)
										}
									}
								}
								self.asd = self.us;
								/*$scope.userlist=self.asd;
								$rootScope.filteredList =$scope.userlist;
								
								console.log($rootScope.filteredList)*/
		
							//	$location.path('/find')
							};

							// self.fatchAllUsers();

							self.createUser = function(user) {
								console.log("createUser...")
								UserService.createUser(user).then(function(d) {
													alert("Thank you for registration")
													$location.path("/login")
												},
												function(errResponse) {
													console.error('Error while creating User.');
												});
							};

							self.myProfile = function() {
								console.log("myProfile...")
								UserService
										.myProfile()
										.then(function(d) {
													self.user = d;
													$location.path("/myProfile")
												},
												function(errResponse) {
													console.error('Error while fetch profile.');
												});
							};

							self.accept = function(id) {
								console.log("accept...")
								UserService
										.accept(id)
										.then(function(d) {
													self.user = d;
													self.fetchAllUsers
													$location.path("/manage_users")
													alert(self.user.errorMessage)

												}, 

												function(errResponse) {
													console.error('Error while updating User.');
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
								
									console.log('Update the user details',
											$rootScope.currentUser);
									self.updateUser($rootScope.currentUser);
								
								self.reset();
							};

							/*self.authenticate = function(user) { 
								console.log("authenticate...")
								UserService.authenticate(user)
										.then(function(d) {
													self.user = d;
													
														console.log("Valid credentials. Navigating to home page")
													
															console.log("You are admin")
															self.fetchAllUsers();
															console.log("Valid credentials. Navigating to admin page")
														
													
												},
												function(errResponse) {
													console.error('Error while authenticate Users');
												});
							};*/
  
							self.login = function() {
								UserService.login(self.user).then(function(response) {
									console.log(response.status)
									$scope.user = response.data;
								
									$rootScope.currentUser = response.data;
									$cookieStore.put("hi", response.data);
									$cookies.put('currentUser', response.data);
									
									 if($scope.user.role == 'STUDENT'){
										 $location.path('/home')
									  }else if($scope.user.role == 'ADMIN'){
										  $location.path('/admin')
									}else{
										 $location.path('/blog')
									}
									/*$location.path('/home')*/
								}, function(response) {
									console.log(response.status)
									$scope.message = response.data.message
									$location.path('/login')
								})  
							};
							self.logout = function() {
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('hi');
								$cookies.remove('currentUser');
								UserService.logout()
								$location.path('/login')

							}
							
							self.send = function(friendUser){
								console.log("sending friend request...")
								FriendService.createFriend(friendUser).then(function(d) {
									console.log(d)
								/*	self.frndreq=d;
									console.log(frndreq)*/
									
													//alert("Thank you for  creating friend")
									self.fetchAllUsers();
												},
												function(errResponse) {
													console.error('Error while creating friend..');
												});
							
							}
							
							
							// self.fetchAllUsers(); //calling the method    

							// better to call fetchAllUsers -> after login ???  

						/*	self.login = function() {
								{
									console.log('login validation????????',
											self.user);
									self.authenticate(self.user);
								}

							};*/

							self.submit = function() {
								
									console.log('Saving New User', self.user);
									self.createUser(self.user);
							
								self.reset();
							};

							self.reset = function() {
								self.user = {id : null,name : '',password : '',mobile : '',address : '',email : '',zipcode : '',role : '',isOnline:''};
								self.friend = {id:'',friendId : '',userId: '',userName:'',status:'',friendName:'',isOnline:'',lastSeen:''};
								//$scope.myForm.$setPristine(); // reset Form
							};

						} ]);
