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
		.when('/editLegacyDataRoute/:id',{
			templateUrl: function(params){ return 'editLegacyDataMapping/' + params.id; }, 
			controller : 'SUPDTEController'
		})
		.when('/viewWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'viewWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'SUPDTEController'
		})
		.when('/viewLegacyWorkDataRoute/:id',{
			templateUrl: function(params){ return 'viewWorkLegacyDataMapping/' + params.id; }, 
			controller : 'SUPDTEController'
		})
			.when('/manageSqmUsersForSe', {
				templateUrl: 'manageSqmUsersForSe',
				controller : 'SUPDTEController'
			})
			.when('/editSqmUserFormForSe/:id', {
				templateUrl: function(params){ return 'editSqmUserFormForSe/' + params.id; }, 
				controller : 'SUPDTEController'
			})
			.otherwise({
				redirectTo: '/'
			});
	}]);
