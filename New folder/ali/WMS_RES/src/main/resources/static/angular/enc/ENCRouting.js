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
		
		
		.when('/budgetRequestApprovalList', {
			templateUrl: 'budgetRequestApprovalList',
			controller : 'ENCController'
		})
		
		.when('/budgetRequestApproval/:id',{
			templateUrl: function(params){ return 'budgetRequestApproval/' + params.id; }, 
			controller : 'ENCController'
		})
		
		 .when('/viewBudgetRequest/:id',{
			templateUrl: function(params){ return 'viewBudgetRequest/' + params.id; }, 
			controller : 'ENCController'
		})
		
		 .when('/viewBudgetRequestAllotement/:id',{
			templateUrl: function(params){ return 'viewBudgetRequestAllotement/' + params.id; }, 
			controller : 'ENCController'
		})
		
		 .when('/viewBudgetRequestAllotementAllAccHead/:accountHeadId/:accountHead',{
			templateUrl: function(params){ return 'viewBudgetRequestAllotementAllAccHead/' + params.accountHeadId+'/'+params.accountHead; }, 
			controller : 'ENCController'
		})
		.when('/editBudgetRequestApproval/:id',{
			templateUrl: function(params){ return 'editBudgetRequestApproval/' + params.id; }, 
			controller : 'ENCController'
		})
		
		.when('/budgetRequestAllotmentList', {
			templateUrl: 'budgetRequestAllotmentList',
			controller : 'ENCController'
		})
		
		.when('/budgetRequestAllotmentListAccHeadWise', {
			templateUrl: 'budgetRequestAllotmentListAccHeadWise',
			controller : 'ENCController'
		})
		.when('/addnewAllotment', {
			templateUrl: 'addnewAllotment',
			controller : 'ENCController'
		})
		 .when('/editAllotment/:id',{
			templateUrl: function(params){ return 'editAllotment/' + params.id; }, 
			controller : 'ENCController'
		})
		.when('/viewWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/reviseLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'reviseLegacyDataMapping/' + params.id; }, 
			controller : 'CEController'
		})
		.when('/viewLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'viewLegacyDataMapping/' + params.id; }, 
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
		.when('/viewWorkRequisitionDataRouteView/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMappingView/' + params.id; }, 
			controller : 'CEController'
		})
		.otherwise({
			redirectTo: '/'
		});
	}]);
