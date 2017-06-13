'use strict';

app.controller('ForumController', ['$scope','ForumService','$location','$rootScope','$cookieStore','$http',
	function($scope, ForumService, $location, $rootScope, $cookieStore,
				$http) {
			console.log("ForumController...")
			 var self = this;
			self.forum = {forumId:'',forumTitle : '',forumContent : '',userId:'',userName:'',addDate:''};
			//this.forum = {forumId:'',forumTitle : '',forumContent : '',userId:'',userName:''};
			this.forums = []; // json array

			/*$scope.orderByMe = function(x) {
				$scope.myOrderBy = x;
			}
			*/
			
			
			
			self.submit = submit;
		    self.update = update;
		    self.get = get;
		
			fetchAllForums();
			reset();

			function fetchAllForums() {
				console.log("fetchingAllForums...")
				ForumService.fetchAllForums().then(function(d) {
					self.forums = d;
					console.log(self.forums)
				}, function(errResponse) {
					console.error('Error while fetching Forums');
				});
			};

		

			function createForum(forum){
				console.log("createForum...")
				ForumService.createForum(forum).then(function(d) {
					alert("Thank you for creating message")
					$location.path("/index")
				}, function(errResponse) {
					console.error('Error while creating Forum.');
				});
			};

			function reject(id){
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				ForumService.reject(id, reason).then(function(d) {
					self.forum = d;
					self.fetchAllForums
					$location.path("/manage_Forums")
					alert(self.forum.errorMessage)

				}, null);
			};

			function updateForum(currentForum){
				console.log("updateForum...")
				ForumService.updateForum(currentForum).then(
						self.fetchAllForums, null);
			};

			function update() {
				{
					console.log('Update the Forum details',
							$rootScope.currentForum);
					updateForum($rootScope.currentForum);
				}
				reset();
			};

			// this.fetchAllForums(); //calling the method

			// better to call fetchAllForums -> after login ???

			function get(forum){
				$scope.fv=forum;
				console.log($scope.fv);
				$rootScope.viewForumm=$scope.fv;
				$location.path("/viewForum");
				
				
			};
			
			
			 function submit() {
				{
					console.log('Saving New Forum', self.forum);
					createForum(self.forum);
				}
				reset();
			};

			 function reset() {
				self.forum = {forumId:'',id:'',forumTitle : '',forumContent : '',userId:'',userName:'',addDate:''};
				//$scope.myForm.$setPristine(); // reset Form
			};

		} ]);
