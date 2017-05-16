var app = angular.module('myApp', [ 'ngRoute', 'ngCookies' ]);

app.config(function($routeProvider) {

	$routeProvider 
 
	/*.when('/', {
		templateUrl : 'index.html'
	})
 */
	.when('/login', {
		templateUrl : 'User/login.html'

	})

	.when('/register', {
		templateUrl : 'User/Register.html',
		controller : 'UserController',
		controllerAs: 'ctrl'
	})
	/*.when('/blog', {
		templateUrl : 'blog/blogform.html'

	})
	.when('/forum', {
		templateUrl : 'forum/forum.html',
		controller  : 'ForumController',
		controllerAs: 'ctrl'
	})
*/	.otherwise({
		redirectTo : '/login'
	});
});
