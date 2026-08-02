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
		$routeProvider
		.when('/addLegacyDataRoute', {
			templateUrl: 'addLegacyDataMapping',
			controller : 'EEController'
		})
		.when('/editLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/editEELegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyEEDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/viewHLegacyData/:workLoggingId', {
			templateUrl: function(params){ return 'viewHLegacyData/' +params.workLoggingId; }, 
			controller : 'EEController'
		})
		.when('/reviseLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'reviseLegacyDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		
		.when('/historyLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'historyLegacyDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/viewLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/addWorkRequisitionDataRoute', {
			templateUrl: 'addWorkRequisitionDataMapping',
			controller : 'EEController'
		})
		.when('/editWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'editWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/editEEWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'editEEWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/viewWorkRequisitionDataRouteForReq/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMappingForReq/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/viewLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'viewLegacyDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.when('/budgetRequestList', {
			templateUrl: 'budgetRequestList',
			controller : 'EEController'
		})
		
		.when('/addBudgetRequest', {
			templateUrl: 'addBudgetRequest',
			controller : 'EEController'
		})
		.when('/editBudgetRequest/:id',{
			templateUrl: function(params){ return 'editBudgetRequest/' + params.id; }, 
			controller : 'EEController'
		})
	  .when('/viewBudgetRequest/:id',{
			templateUrl: function(params){ return 'viewBudgetRequest/' + params.id; }, 
			controller : 'EEController'
		})
		
		 .when('/addBudgetSurrender/:id',{
			templateUrl: function(params){ return 'addBudgetSurrender/' + params.id; }, 
			controller : 'EEController'
		})
		.otherwise({
				redirectTo: '/'
			});
	}]);
