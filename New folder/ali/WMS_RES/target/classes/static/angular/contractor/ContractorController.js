var res = angular.module('res');

res.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            var maxSizeUpload = 5000000;// in bytes (here 5 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
// scope.noFileError = false;
// scope.maxSizeError = false;
            /* scope.fileExtentionErrorAs = false; */	        	
            	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1); 
            	var fileSize = element[0].files[0].size; 
            	switch (attrs.fileModel) {
				case "asPdfFile":
					/*
					 * scope.innovativeStartupDoc.maxSizeError = (fileSize >
					 * maxSizeUpload);
					 */
					scope.fileExtentionErrorAs = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorAs == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}							
				break;
				case "tsPdfFile":
					scope.fileExtentionErrorTs = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorTs == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "dcPdfFile":
					scope.fileExtentionErrorDc = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorDc == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "acPdfFile":
					scope.fileExtentionErrorAc = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorAc == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "ecPdfFile":
					scope.fileExtentionErrorEc = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorEc == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "ldPdfFile":
					scope.noFileError = false;
					scope.fileExtentionErrorLd = (allowedExtensions.indexOf(fileExtension) < 0);
					scope.fileSizeErrorLd = (fileSize > maxSizeUpload);
// if (scope.fileExtentionErrorLd == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
// }
				break;							
				default:
					scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
				break;
			}
            	
            	
            	
            	
            	/*
				 * if (element[0].files[0]) { var fileSize =
				 * element[0].files[0].size; var fileExtension =
				 * element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') +
				 * 1);
				 * 
				 * if (fileSize > maxSizeUpload) { scope.maxSizeError = true; }
				 * if (allowedExtensions.indexOf(fileExtension) < 0) {
				 * scope.fileExtentionErrorAs = true; } if (scope.maxSizeError ==
				 * false && scope.fileExtentionError == false) {
				 * scope.$apply(function() { modelSetter(scope,
				 * element[0].files[0]); }); } else { // scope.noFileError =
				 * true; scope.$apply(function() { modelSetter(scope,
				 * element[0].files[0]); }); } }
				 */      	
            });
        }
    };
}]);
res.controller('ContractorController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout) {
	
	/*$scope.IsVisible = true;*/
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
	
	
	$scope.loadWorksForBillingContractor = function() {

		$loading.start('sample-1');
		/*alert("hbii");*/
		fetchWorksByNameContractor();
	};
	
	$scope.searchByWorkName = function(){
		 $loading.start('sample-1');
		 if($('#searchBox').val().trim()!=""){
			 reDraw();
		 }
		 else{
			 $loading.finish('sample-1');
		 }
	 };
	 
		$scope.loadBillsContractor = function() {

			$loading.start('sample-1');
			fetchBillsContractor();
		};
		
		
		$scope.searchByBillNoWorkNameOrAnyFilters = function(){
			$loading.start('sample-1');
			if(($('#searchBox').val()!="" && $('#searchBox').val().trim().length >=4) || $('#designation').val()!="" || $('#status').val()!=""){
				reDraw();
			}
			else{
				$loading.finish('sample-1');
			}
		};
		
		$scope.resetFunction = function() {
			$loading.start('sample-1');
			
			$timeout(function () {
				reDraw();
			}, 0);
		};
		
		$scope.deleteBillLoadContractor = function(id) {
			/*alert(id);*/
			$loading.start('sample-1');
			$scope.billBean={};
			$scope.billBean.id = id;
			$loading.finish('sample-1');
			$('#billDelete-update-popup-contractor').modal('show');
			
	     
		};
		
		$scope.deleteBillRemarksContractor = function(form, isValid ) {
			
			if (!isValid) {				
				return false;
			}
			
			if (confirm("Are you sure you want to Delete Bill ?")) {			

				var responsePromise = $http.post('deleteBillRemarks', $scope.billBean);

				responsePromise.success(function(data, status, headers, config) {
					$rootScope.responseObject = data;
					if ($rootScope.responseObject.successMessage != null) {
						$timeout(function() {
							 $rootScope.responseObject.successMessage = null;
					    }, 5000);
					    
						/*$scope.loadBills();*/
						$('#billDelete-update-popup-contractor').modal('hide');
						
					}
					$window.location.reload();
					$loading.finish('sample-1');
				});
				
				responsePromise.error(function() {
					$rootScope.responseObject = {};			
					$rootScope.responseObject.errorMessage = "Some error occured while saving the data";
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 10000);
					$loading.finish('sample-1');			
					$('#billDelete-update-popup-contractor').modal('hide');
				});
			}
		};
	
	
	
	
	

	
	
});