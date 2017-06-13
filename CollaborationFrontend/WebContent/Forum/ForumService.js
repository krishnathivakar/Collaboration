'use strict';

app.service('ForumService', ['$http','$q','$rootScope',
	function($http, $q, $rootScope) {

			console.log("ForumService...")

			var BASE_URL = 'http://localhost:8083/RestServices'

				
				 var factory = {
			        fetchAllForums: fetchAllForums,
			        createForum: createForum,
			        updateForum:updateForum,
			       
			    };
			 
			    return factory;

			    function fetchAllForums() {
			    	console.log("calling fetchAllForums ")
					return $http.get(BASE_URL + '/forums').then(
							function(response) {
								return response.data;
							}, null);
				};

				function createForum(forum){
					console.log("calling create Forum")
					return $http.post(BASE_URL + '/forums', forum) // 1
					.then(function(response) {
						return response;
					}, function(errResponse) {
						console.error('Error while creating Forum');
						return $q.reject(errResponse);
					});
				};

				function updateForum(forum){
					console.log("calling fetchAllForums ")
					return $http.put(BASE_URL + '/forum/', Forum) // 2
					.then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating Forum');
						return $q.reject(errResponse);
					});
				};

				

		} ]);