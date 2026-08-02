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
	/*	.when('/searchWorkForBillContractor', {
			templateUrl: 'searchWorkForBillContractor',
			controller : 'CommonController'
		})*/
		.when('/searchWorkForBillContractor', {
			templateUrl: 'searchWorkForBillContractor',
			controller : 'ContractorController'
		})
		
			.when('/viewBillsContractor', {
				templateUrl: 'viewBillsContractor',
				controller : 'ContractorController'
			})
		.otherwise({
				redirectTo: '/'
			});
	}]);
