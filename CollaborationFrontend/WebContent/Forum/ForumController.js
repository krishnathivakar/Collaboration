'use strict';

app.controller('ForumController', ['$scope','ForumService','CommentService','$location','$rootScope','$cookieStore','$http',
	function($scope, ForumService,CommentService, $location, $rootScope, $cookieStore,
				$http) {
			console.log("ForumController...")
			 var self = this;
			self.forum = {forumId:'',forumTitle : '',forumContent : '',userId:'',userName:'',addDate:''};
			console.log("comment")
			self.comment = {id:'',forumId : '',userId : '',comments:'',userName:'',timeStamp:'',userMail:''};
			//this.forum = {forumId:'',forumTitle : '',forumContent : '',userId:'',userName:''};
			this.forums = [];// json array
				$scope.cmt={};
			self.comments = [];
			/*$scope.orderByMe = function(x) {
				$scope.myOrderBy = x;
			}
			*/
			
			
			
			self.submit = submit;
		    self.update = update;
		    self.get = get;
		    self.getComment = getComment;
		self.createComment = createComment;
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

			function createComment(comment){
				console.log("createComment...")
				
					
				$scope.recentForum = $rootScope.viewForumm;
				console.log($scope.recentForum);
					CommentService.createComment(comment).then(function(d) {
						self.comment = d;
						
					alert("Thank you for creating message")
					$location.path("/home")
				}, function(errResponse) {
					console.error('Error while creating Comment.');
				});
			};

			function createForum(forum){
				console.log("createForum...")
				ForumService.createForum(forum).then(function(d) {
					self.forum = d;
					$scope.cforum = self.forum;
					$rootScope.currentForum = $scope.cforum;
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
				CommentService.fetchAllComments(forum.forumId) .then(function(d) {
					self.forumComments = d;
					$rootScope.fcomment = d;
					console.log($rootScope.fcomment);
					console.log(self.forumComments);
					
					
					$scope.fv=forum;
					$scope.cmt=d;
					console.log($scope.fv);
					console.log($scope.cmt);
					console.log("fetchingAllComments...")
					
					$rootScope.viewForumm=$scope.fv;
					$rootScope.ct=$scope.cmt;
					$location.path("/viewForum");
				}, function(errResponse) {
					console.error('Error while fetching Comments');
				});
				
				
				
			};
			
			function getComment(forumId){
				console.log("fetchingAllComments...")
				CommentService.fetchAllComments(forumId) .then(function(d) {
					self.forumComments = d;
					console.log(self.forumComments)
				}, function(errResponse) {
					console.error('Error while fetching Comments');
				});
			};
			
			
			 function submit() {
				{
					console.log('Saving New Forum', self.forum);
					createForum(self.forum);
				}
				reset();
			};

			 function reset() {
				self.forum = {forumId:null,id:'',forumTitle : '',forumContent : '',userId:'',userName:'',addDate:''};
				self.comment = {id:null,forumId : '',userId : '',comments:'',userName:'',timeStamp:'',userMail:''};
				//$scope.myForm.$setPristine(); // reset Form
			};

		} ]);
