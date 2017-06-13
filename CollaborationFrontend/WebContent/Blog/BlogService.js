'use strict';

app.service('BlogService', ['$http','$q','$rootScope',
	function($http, $q, $rootScope) {

			console.log("blogService...")

			var BASE_URL =' http://localhost:8083/RestServices'

				 var factory = {
			        fetchAllBlogs: fetchAllBlogs,
			        createBlog: createBlog,
			        updateBlog:updateBlog,
			        AcceptedBlogs: AcceptedBlogs
			    };
			 
			    return factory;

			    function fetchAllBlogs() {
					console.log("calling fetchAllblogs ")
					return $http.get(BASE_URL + '/blogs').then(
							function(response) {
								return response.data;
							}, null);
				};
				
				function AcceptedBlogs() {
	            	console.log("calling AcceptedBlogs ") 
	            	
	            	
	                    return $http.get(BASE_URL+'/acceptedblog')
	                            .then(
	                                    function(response){
	                                         return response.data;
	                                    	console.log(response)
	                                    },  
	                                   null
	                            );  
	            };
	            
				function notAcceptedBlogs() {
		            	console.log("calling notAcceptedBlogs ") 
		            	
		            	
		                    return $http.get(BASE_URL+'/notAcceptedblog')
		                            .then(
		                                    function(response){
		                                         return response.data;
		                                    	console.log(response)
		                                    },  
		                                   null
		                            );  
		            };

				function createBlog(Blog) {
					console.log("calling create Blog")
					return $http.post(BASE_URL + '/blogs', Blog) // 1
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while creating Blog');
						return $q.reject(errResponse);
					});
				};

				function updateBlog(Blog) {
					console.log("calling fetchAllBlogs ")
					return $http.put(BASE_URL + '/Blog/', Blog) // 2
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating Blog');
						return $q.reject(errResponse);
					});
				};

		

		} ]);