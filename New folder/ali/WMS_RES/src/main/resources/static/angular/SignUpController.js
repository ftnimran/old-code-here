var res = angular.module('res', ['ngRoute','darthwade.dwLoading','ngIdle','ui.bootstrap']);

res.controller('SignUpController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout) {
	$scope.started = false;
	
	$scope.doTheBack = function() {
		  window.history.back();
		};
	
	function closeModals() {
		if ($scope.warning) {
			$scope.warning.close();
			$scope.warning = null;
		}

		if ($scope.timedout) {
			$scope.timedout.close();
			$scope.timedout = null;
		}
	}

	$scope.$on('IdleStart', function() {
		closeModals();
	});

	$scope.$on('IdleEnd', function() {
		closeModals();
	});

	$scope.$on('IdleTimeout', function() {
		closeModals();
		alert("Your Session has expired, Please relogin.");
		$window.location.reload();
	});

	$scope.startOrStopSpinner = function(isStart) {

		if(isStart) {
			$loading.start('sample-1');
		} else {
			$loading.finish('sample-1');			
		}

	};
	
	$scope.loadDesignations = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchDesignations');
		response.success(function(data, status, headers, config) {
			$scope.designations = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadOfficeTypes = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchOfficeTypes');
		response.success(function(data, status, headers, config) {
			$scope.officeTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadOffices = function(officeTypeId) {

		$loading.start('sample-1');
		var response = $http.get('fetchOfficesByOfficeType/'+officeTypeId);
		response.success(function(data, status, headers, config) {
			$scope.offices = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadSubDivisionalOffices = function (eeOfficeId, sdoDesignationId) {

    $loading.start('sample-1');

    if (sdoDesignationId != null && sdoDesignationId == "9") {

        $http.get('fetchSubDivionOfficesByOfficeId/' + eeOfficeId)
            .then(function (response) {

                var data = response.data;

                if (Array.isArray(data) && data.length > 0) {
                    $scope.subDivionOffices = data;
                } else {
                    $scope.subDivionOffices = [];
                }

                $loading.finish('sample-1');
            })
            .catch(function (error) {

                console.error("Error in API:", error);

                $scope.subDivionOffices = [];
                $loading.finish('sample-1');
            });

    } else {
        // If designation is not 9 → clear list
        $scope.subDivionOffices = [];
        $loading.finish('sample-1');
    }
};

	
	$scope.doSignUp = function(isValid) {

		if (!isValid) 
			return false;

		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			
			var password = $scope.signUpData.password;
			var confirmPassword = $scope.signUpData.confirmPassword;
			$scope.signUpData.password = hash($scope.signUpData.password);
			$scope.signUpData.confirmPassword = hash($scope.signUpData.confirmPassword);
			
			var responsePromise = $http.post('doSignUp', $scope.signUpData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = 'login?register';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 10000);
					$scope.signUpData.password = password;
					$scope.signUpData.confirmPassword = confirmPassword;
					//$window.location.href = '/res_owms/signup';
				}
				$loading.finish('sample-1');
			});
			responsePromise.error(function() {
				$rootScope.responseObject = {};
				$rootScope.responseObject.errorMessage = "Some error occured while saving the data";
				$timeout(function() {
					$rootScope.responseObject.errorMessage = null;
				}, 10000);
				$loading.finish('sample-1');
			});
		}
	};

});