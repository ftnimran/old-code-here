var res = angular.module('res', ['ngRoute','darthwade.dwLoading','ngIdle','ui.bootstrap']);
res.config(['KeepaliveProvider', 'IdleProvider', function(KeepaliveProvider, IdleProvider) {
	  IdleProvider.idle(1800);
	  IdleProvider.timeout(2);
	  KeepaliveProvider.interval(2);
	}]);

res.run(['Idle', function(Idle) {
Idle.watch();
}]);
res
	.config( ['$routeProvider', function($routeProvider) {
	
		/*.when('/addLegacyDataRoute', {
			templateUrl: 'addLegacyDataMapping',
			controller : 'EEController'
		})
		.when('/manageLegacyDataRoute',{
			templateUrl: 'manageLegacyDataMapping',
			controller : 'EEController'
		})
		.when('/editLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyDataMapping/' + params.id; }, 
			controller : 'EEController'
		})*/
		$routeProvider
		.when('/viewLegacyWorkDataRoute/:id',{
			templateUrl: function(params){ return 'viewWorkLegacyDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/viewWorkRequisitionDataRouteForReq/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMappingForReq/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/viewWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/viewWorkRequisitionDataRouteView/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMappingView/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/editLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/viewLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'viewLegacyDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/reviseLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'reviseLegacyDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/historyLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'historyLegacyDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/viewHLegacyData/:workLoggingId', {
			templateUrl: function(params){ return 'viewHLegacyData/' +params.workLoggingId; }, 
			controller : 'CEController'
		})
		.when('/editWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'editWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.otherwise({
				redirectTo: '/'
			});
	}]);
