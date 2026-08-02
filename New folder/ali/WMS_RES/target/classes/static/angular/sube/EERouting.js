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
		.when('/addWorkRequisitionDataRoute', {
			templateUrl: 'addWorkRequisitionDataMapping',
			controller : 'EEController'
		})
		.when('/editWorkRequisitionDataRoute/:id',{
			templateUrl: function(params){ return 'editWorkRequisitionDataMapping/' + params.id; }, 
			controller : 'EEController'
		})
		.otherwise({
				redirectTo: '/'
			});
	}]);
