'use strict';

app.controller('BlogController', ['$scope','BlogService','$location','$rootScope','$cookieStore','$http',
		function($scope, BlogService, $location, $rootScope, $cookieStore,
				$http) {
			console.log("BlogController...")
			var self = this;
			self.blog = {id : '',title : '',status : '',description : '',addDate : '',userId:'',username:''};
			// self.blog = {id:'',title : '',status: '',description:''};
			self.blogs = []; // json array

			
			self.submit = submit;
		    self.update = update;
		    self.get = get;
		    self.AcceptedBlogs = AcceptedBlogs;
		    
			
			fetchAllBlogs();
			AcceptedBlogs();
			reset();
			
			function fetchAllBlogs() {
				BlogService.fetchAllBlogs().then(function(d) {
					self.blogs = d;
					console.log(self.blogs)
				}, function(errResponse) {
					console.error('Error while fetching Blogs');
				});
			}

			// this.fatchAllBlogs();
			function AcceptedBlogs() {
				console.log("AcceptedBlogs...")
				BlogService.AcceptedBlogs().then(function(d) {
									//alert("Thank you for creating message")
					console.log('d');
									self.blogsAccept = d;
								},
								function(errResponse) {
									console.error('Error while creating AcceptedBlogs.');
								});
			};
			function notAcceptedBlogs() {
				console.log("notAcceptedBlogs...")
				BlogService.notAcceptedBlogs().then(function(d) {
									//alert("Thank you for creating message")
									self.blogsNotAccepted = d;
								},
								function(errResponse) {
									console.error('Error while creating notAcceptedBlogs.');
								});
			};
			function createBlog(blog){
				console.log("createBlog...")
				BlogService.createBlog(blog).then(function(d) {
					alert("Thank you for creating message")
					$location.path("/ViewB")
				}, function(errResponse) {
					console.error('Error while creating Blog.');
				});
			};

			function reject(id){
				console.log("reject...")
				var reason = prompt("Please enter the reason");
				BlogService.reject(id, reason).then(function(d) {
					self.blog = d;
					self.fetchAllBlogs
					$location.path("/manage_Blogs")
					alert(self.Blog.errorMessage)

				}, null);
			};

			function updateBlog(currentBlog){
				console.log("updateBlog...")
				BlogService.updateBlog(currentBlog).then(self.fetchAllBlogs,
						null);
			};

			function update() {
				{
					console.log('Update the Blog details',
							$rootScope.currentBlog);
					updateBlog($rootScope.currentBlog);
				}
				reset();
			};

			// this.fetchAllBlogs(); //calling the method

			// better to call fetchAllBlogs -> after login ???

			function get(blog){
				$scope.bv=blog;
				console.log($scope.bv);
				$rootScope.viewBlog=$scope.bv;
				$location.path("/viewBlog");
				
				
			};
			
			 function submit() {
				
					console.log('Saving New Blog', self.blog);
					createBlog(self.blog);
				
				reset();
			};

			function reset() {
				self.blog = {id : null,title : '',status : '',description : '',addDate : '',userId:'',username:''};
				 //reset Form
			}

		} ]);
