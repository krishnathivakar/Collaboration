'use strict';

app.controller('JobController',['$scope','JobService','$location','$rootScope','$cookieStore','$http',function($scope, JobService, $location, $rootScope,$cookieStore, $http) {
							console.log("JobController...")
							var self = this;
							self.job = {id:'',title : '',companyName: '',technicalKnowledge:'',email:'',addDate:'',qualification:'',status:''};
							//this.job =  {id:'',title : '',description: '',addDate:'',qualification:'',status:''};
							this.jobs = []; // json array

							/*$scope.orderByMe = function(x) {  
								$scope.myOrderBy = x;
							}*/  


							self.submit = submit;
						    self.update = update;
						    
						    
							
							fetchAllJobs();
							reset();
						
							
							function fetchAllJobs() {
								console.log("fetchAllJobs...")
								JobService.fetchAllJobs().then(function(d) {
													self.jobs = d;
													console.log(self.jobs)
												},function(errResponse) {  
													console.error('Error while fetching Jobs');
												});
							};

							// this.fatchAllJobs();

							function createJob(job){
								console.log("createJob...")
								JobService.createJob(job).then(function(d) {
													alert("Thank you for creating message")
													$location.path("/index")
												},
												function(errResponse) {
													console.error('Error while creating Job.');
												});
							};

							

							

							function reject(id){
								console.log("reject...")
								var reason = prompt("Please enter the reason");
								JobService.reject(id, reason).then(function(d) {
											self.job = d;
											self.fetchAllJobs
											$location.path("/manage_Jobs")
											alert(self.Job.errorMessage)

										}, null);
							};

							function updateJob(currentJob){
								console.log("updateJob...")  
								JobService.updateJob(currentJob).then(
										self.fetchAllJobs, null);
							};

							function update() {
								{
									console.log('Update the Job details',
											$rootScope.currentJob);
									updateJob($rootScope.currentJob);
								}
					 			reset();
							};

							
							

							// this.fetchAllJobs(); //calling the method    

							// better to call fetchAllJobs -> after login ???

							
  
							 function submit() {
								{
									console.log('Saving New Job', self.job);
									createJob(self.job);
								}
								reset();  
							};

							function reset() {
								self.job = {id:null,title : '',companyName: '',technicalKnowledge:'',email:'',addDate:'',qualification:'',status:''};
								//$scope.myForm.$setPristine(); // reset Form
							};

						} ]);
