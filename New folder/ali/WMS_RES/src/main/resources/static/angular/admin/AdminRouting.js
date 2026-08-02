var res = angular.module('res', ['ngRoute','darthwade.dwLoading','ngIdle','ui.bootstrap']);
res.config(['KeepaliveProvider', 'IdleProvider', function(KeepaliveProvider, IdleProvider) {
	  IdleProvider.idle(1800);
	  IdleProvider.timeout(2);
	  KeepaliveProvider.interval(2);
	}]);
res.run(['Idle', function(Idle) {
Idle.watch();
}]);
res.config( ['$routeProvider', function($routeProvider,$routeParams) {
		$routeProvider
		.when('/editLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyDataMapping/' + params.id; }, 
			controller : 'AdminController'
		})
		.when('/manageSORs', {
				templateUrl: 'manageSORs',
				controller : 'AdminController'
		})
		.when('/addSORForm', {
				templateUrl: 'addSORForm',
				controller : 'AdminController'
		}) 
		.when('/editSORForm/:id', {
				templateUrl: function(params){ return 'editSORForm/' + params.id; }, 
				controller : 'AdminController'
		}) 
		.when('/addChapterForm/:id', {
				templateUrl: function(params){ return 'addChapterForm/' + params.id; }, 
				controller : 'AdminController'
		})
		.when('/addChapterForm', {
				templateUrl: 'addChapterForm',
				controller : 'AdminController'
		})
		.when('/editChapterForm/:id/:chapter', {
			templateUrl: function(params){ return 'editChapterForm/' + params.id+'/'+params.chapter; }, 
		//	templateUrl: function(params){ return 'editChapterForm/20/1' ; }, 
				controller : 'AdminController'
		}) 
		.when('/viewLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'viewLegacyDataMapping/' + params.id; }, 
			controller : 'AdminController'
		})
		.when('/viewWorkRequisitionDataRouteView/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMappingView/' + params.id; }, 
			controller : 'AdminController'
		})
		.otherwise({
				redirectTo: '/'
		});
	}]);
