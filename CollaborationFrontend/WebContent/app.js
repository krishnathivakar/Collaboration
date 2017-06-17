var app = angular.module('krishna', [ 'ngRoute', 'ngCookies' ]);

app.config(function($routeProvider) {

	$routeProvider

	.when('/', {
		templateUrl : 'index.html'
	})

	.when('/home', {
		templateUrl : 'Home/Home.html'

	})

	.when('/viewBlog', {
		templateUrl : 'Blog/Detail.html'

	})

	.when('/viewForum', {
		templateUrl : 'Forum/Detail.html',
		controller : 'ForumController',
		controllerAs : 'fc'
	})

	.when('/viewJob', {
		templateUrl : 'Job/Detail.html'

	})

	.when('/login', {
		templateUrl : 'User/Login.html',
		controller : 'UserController',
		controllerAs : 'uc'

	})

	.when('/logout', {
		templateUrl : 'home/home.html',
		controller : 'UserController',
		controllerAs : 'uc'
	})

	.when('/register', {
		templateUrl : 'User/Register.html',
		controller : 'UserController',
		controllerAs : 'uc'
	})

	.when('/blog', {
		templateUrl : 'Blog/blogform.html',
		controller : 'BlogController',
		controllerAs : 'bc'

	})

	.when('/viewB', {
		templateUrl : 'Blog/ViewBlog.html',
		controller : 'BlogController',
		controllerAs : 'bc'

	})

	.when('/forum', {
		templateUrl : 'Forum/forum.html',
		controller : 'ForumController',
		controllerAs : 'fc'

	})

	.when('/viewF', {
		templateUrl : 'Forum/viewForum.html',
		controller : 'ForumController',
		controllerAs : 'fc'

	})

	.when('/job', {
		templateUrl : 'Job/Job.html',
		controller : 'JobController',
		controllerAs : 'jc'

	})

	.when('/viewJ', {
		templateUrl : 'Job/viewJob.html',
		controller : 'JobController',
		controllerAs : 'jc'

	})

	.otherwise({
		redirectTo : 'Home/Home.html'
	});
});