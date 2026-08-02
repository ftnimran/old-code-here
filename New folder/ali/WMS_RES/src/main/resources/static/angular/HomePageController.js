var res = angular.module('res', ['ngRoute','darthwade.dwLoading','ngIdle','ui.bootstrap']);

res.controller('HomePageController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout) {
	$scope.started = false;
	
	$scope.fetchDistrictWiseWorkStatusList = function() {

		$loading.start('sample-1');
		fetchDistrictWiseWorkStatusList();
	};
	
	$scope.doTheBack = function() {
		  window.history.back();
		};
	
	

});