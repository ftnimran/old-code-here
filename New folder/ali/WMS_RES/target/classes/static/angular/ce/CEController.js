var res = angular.module('res');

/*res.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
            	scope.fileExtentionErrorAs = false;
            	            		
            	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
        	
				if (allowedExtensions.indexOf(fileExtension) < 0) {
					scope.fileExtentionErrorAs = true;
				} else {
					scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
					});
				}					
            	            	
            });
        }
    };
}]);*/

res.directive('fileModelAs', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModelAs);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
//            	scope.noFileError = false;
//            	scope.maxSizeError = false;
            	scope.fileExtentionErrorAs = false;
            	
            	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
				if (allowedExtensions.indexOf(fileExtension) < 0) {
					scope.fileExtentionErrorAs = true;
				} else {
					scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
					});
				}
            	
            	
            	/*if (element[0].files[0]) {
            		var fileSize = element[0].files[0].size;            		
                	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
					if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} 
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorAs = true;
					}
					if (scope.maxSizeError == false && scope.fileExtentionError == false) {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
						});
					}
					else {            		
//	            		scope.noFileError = true;
	            		scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
	            	}    
            	}   */      	
            });
        }
    };
}]);


res.directive('fileModelTs', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModelTs);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
//            	scope.noFileError = false;
//            	scope.maxSizeError = false;
            	scope.fileExtentionErrorTs = false;
            	
            	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
            	
            	if (allowedExtensions.indexOf(fileExtension) < 0) {
					scope.fileExtentionErrorTs = true;
				} else {
					scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
					});
				}
            	
            	
            	/*if (element[0].files[0]) {
            		var fileSize = element[0].files[0].size;            		
                	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
					if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} 
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorTs = true;
					}
					if (scope.maxSizeError == false && scope.fileExtentionError == false) {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
						});
					}
            	} else {            		
//            		scope.noFileError = true;
            		scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
            		});
            	}    */        	
            });
        }
    };
}]);

res.directive('fileModelDc', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModelDc);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
//            	scope.noFileError = false;
//            	scope.maxSizeError = false;
            	scope.fileExtentionErrorDc = false;
            	
            	if (element[0].files[0]) {
            		var fileSize = element[0].files[0].size;            		
                	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
					/*if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} */
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorDc = true;
					} else {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
					}
					/*if (scope.maxSizeError == false && scope.fileExtentionError == false) {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
						});
					}*/
            	} else {            		
//            		scope.noFileError = true;
            		/*scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
            		});*/
            	}            	
            });
        }
    };
}]);

res.directive('fileModelEc', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModelEc);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
//            	scope.noFileError = false;
//            	scope.maxSizeError = false;
            	scope.fileExtentionErrorEc = false;
            	
            	if (element[0].files[0]) {
            		var fileSize = element[0].files[0].size;            		
                	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
					/*if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} */
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorEc = true;
					} else {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
					}
					/*if (scope.maxSizeError == false && scope.fileExtentionError == false) {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
						});
					}*/
            	} else {            		
//            		scope.noFileError = true;
            		/*scope.$apply(function() {
						modelSetter(scope, element[0].files[0]);
            		});*/
            	}            	
            });
        }
    };
}]);

res.directive('fileModelAc', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModelAc);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
//            	scope.noFileError = false;
//            	scope.maxSizeError = false;
            	scope.fileExtentionErrorAc = false;
            	
            	if (element[0].files[0]) {
            		var fileSize = element[0].files[0].size;            		
                	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1);            	
            	
					/*if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} */
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorAc = true;
					} else {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
					}
					/*if (scope.maxSizeError == false && scope.fileExtentionError == false) {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
						});
					}*/
            	} else {            		
//            		scope.noFileError = true;
            		/*scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
            		});*/
            	}            	
            });
        }
    };
}]);



/*res.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;        
    });
}]);*/

/*//Directive for chart, pass in chart options
res.directive('hcChart', function () {
    return {
        restrict: 'E',
        template: '<div></div>',
        scope: {
            options: '='
        },
        link: function (scope, element) {
            Highcharts.chart(element[0], scope.options);
        }
    };
})*/

res.controller('CEController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout) {
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
	
		var map = null;
	$scope.mapOnInit = function() {
		map = mapVariable();

	};
	$scope.loadStates = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchStates');
		response.success(function(data, status, headers, config) {
			$scope.states = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadDistrictsByState = function(stateId) {

		$loading.start('sample-1');
		$scope.entrepreneurData.registeredAddress.districtId = "";
		var response = $http.get('fetchDistrictsByState/'+stateId);
		response.success(function(data, status, headers, config) {
			$scope.districts = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.loadBlocksByDistrict = function(districtId) {

		$loading.start('sample-1');
		$scope.entrepreneurData.registeredAddress.blockId = "";
		var response = $http.get('fetchBlocksByDistrict/'+districtId);
		response.success(function(data, status, headers, config) {
			$scope.blocks = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadDistrictsOfMP = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchDistrictsOfMP');
		response.success(function(data, status, headers, config) {
			$scope.districts = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadWorkType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkType');
		response.success(function(data, status, headers, config) {
			$scope.workTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadWorkSubTypeByWorkTypeId = function(workTypeId) {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkSubTypeByWorkTypeId/'+workTypeId);
		response.success(function(data, status, headers, config) {
			$scope.workSubTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadUserDetail = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchUserDetailsFromLoggedInUserName');

		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			// Dependency create ke liye beans me set the value
//			$scope.workData.userBean.officeBean.id = $scope.workData.userBean.officeBean.id + "";
			
			$scope.workData.userBean.officeBean.officeName = $scope.workData.userBean.officeBean.officeName + "";
			$scope.workData.userBean.districtBean.districtName = $scope.workData.userBean.districtBean.districtName + "";
			$scope.workData.userBean.districtBean.districtId = $scope.workData.userBean.districtBean.districtId;
			$scope.loadAssistantEngineerByOfficeId($scope.workData.userBean.officeBean.id);
			$scope.loadSubEngineerByOfficeId($scope.workData.userBean.officeBean.id);
			/*$scope.userData.officeTypeId = $scope.userData.officeTypeId+"";
			$scope.loadOffices($scope.userData.officeTypeId)
			$scope.userData.officeId = $scope.userData.officeId+"";*/
//			$loading.finish('sample-1');
		}).then(function(){
			var response = $http.get('fetchBlocksByDistrictNew/'+$scope.workData.userBean.districtBean.districtId);
			response.success(function(data, status, headers, config) {
				$scope.blocks = data;		
				$loading.finish('sample-1');
			});
		});
	};
	
	$scope.loadAssistantEngineerByOfficeId = function(officeId) {

		$loading.start('sample-1');
		var response = $http.get('fetchAssistantEngineerByOfficeId/'+officeId);
		response.success(function(data, status, headers, config) {
			$scope.assistantEngineers = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadSubEngineerByOfficeId = function(officeId) {
		$loading.start('sample-1');
		var response = $http.get('fetchSubEngineerByOfficeId/'+officeId);
		response.success(function(data, status, headers, config) {
			$scope.subEngineers = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.loadLineDepartment = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchLineDepartment');
		response.success(function(data, status, headers, config) {
			$scope.lineDepartments = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadDistrictsOfMPNew = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchDistrictsOfMPNew');
		response.success(function(data, status, headers, config) {
			$scope.districtsMP = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadBlocksByDistrictNew = function(districtId) {

		$loading.start('sample-1');
//		$scope.entrepreneurData.registeredAddress.blockId = "";
		var response = $http.get('fetchBlocksByDistrictNew/'+districtId);
		response.success(function(data, status, headers, config) {
			$scope.blocks = data;
//			$scope.blocks.block.blockCode = $scope.blocks.block.blockCode+"";
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadGramPanchayatByBlockCode = function (blockCode) {

    $loading.start('sample-1');

    $http.get('fetchGramPanchayatByBlockCode/' + blockCode)
        .success(function (data) {

            $scope.gramPanchayats = data;
            $scope.villages = [];

            // 🔥 IMPORTANT FIX
            if ($scope.workData.gramPanchayatId) {
                $scope.workData.gramPanchayatId =
                    Number($scope.workData.gramPanchayatId);
            }

            $loading.finish('sample-1');
        });
};

	
	
	$scope.loadAgencyType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchAgencyType');
		response.success(function(data, status, headers, config) {
			$scope.agencyTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadPhysicalStageType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchPhysicalStageType');
		response.success(function(data, status, headers, config) {
			$scope.physicalStageTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadTechnicalSanctionType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchTechnicalSanctionType');
		response.success(function(data, status, headers, config) {
			$scope.technicalSanctionTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadAdministrationSanctionType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchAdministrationSanctionType');
		response.success(function(data, status, headers, config) {
			$scope.administrationSanctionTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.loadIssuingAuthority = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchIssuingAuthority');
		response.success(function(data, status, headers, config) {
			$scope.issuingAuthorities = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.addWorkData = function(isValid, asPdfFile, tsPdfFile, acPdfFile, dcPdfFile, ecPdfFile) {

		//$scope.noFileError = (asPdfFile)?false:true;
		/*$scope.fileExtentionErrorAs = (asPdfFile)?false:true;
		$scope.fileExtentionErrorTs = (tsPdfFile)?false:true;
		$scope.fileExtentionErrorAc = (acPdfFile)?false:true;
		$scope.fileExtentionErrorDc = (dcPdfFile)?false:true;
		$scope.fileExtentionErrorEc = (ecPdfFile)?false:true;*/
		
		if (!isValid)
			return false;		
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			
			var fd = new FormData();
			
			if(acPdfFile){
				fd.append('agreementCopyFile', acPdfFile);
				}
			
			if(asPdfFile){
			fd.append('administrationSanctionBean.administrationSanctionFile', asPdfFile);
			}
			
			if(tsPdfFile){
			fd.append('technicalSanctionBean.technicalSanctionFile', tsPdfFile);
			}
			
			if(dcPdfFile){
			fd.append('technicalSanctionBean.latestDrawingCopyFile', dcPdfFile);
			}
			
			if(ecPdfFile){
			fd.append('technicalSanctionBean.estimateFile', ecPdfFile);
			}
			

			/*if ($scope.caseData.caseId) {
				fd.append('caseId', $scope.caseData.caseId);
			}*/
			
			if ($scope.workData.workName) {
				fd.append('workName', $scope.workData.workName);
			}
			
			if ($scope.workData.workTypeBean.workTypeId) {
				fd.append('workTypeBean.workTypeId', $scope.workData.workTypeBean.workTypeId);
			}
			
			if ($scope.workData.workSubTypeBean.workSubTypeId) {
				fd.append('workSubTypeBean.workSubTypeId', $scope.workData.workSubTypeBean.workSubTypeId);
			}
			
			if ($scope.workData.lineDepartmentBean.lineDepartmentId) {
				fd.append('lineDepartmentBean.lineDepartmentId', $scope.workData.lineDepartmentBean.lineDepartmentId);
			}
			
			if ($scope.workData.accountHead) {
				fd.append('accountHead', $scope.workData.accountHead);
			}
			
			if ($scope.workData.agencyTypeBean.agencyTypeId) {
				fd.append('agencyTypeBean.agencyTypeId', $scope.workData.agencyTypeBean.agencyTypeId);
			}
			
			if ($scope.workData.contractorBean.id) {
				fd.append('contractorBean.id', $scope.workData.contractorBean.id);
			}
			
			if ($scope.workData.agencyName) {
				fd.append('agencyName', $scope.workData.agencyName);
			}
			
			if ($scope.workData.totalExpenditureTill31March2018String) {
				fd.append('totalExpenditureTill31March2018String', $scope.workData.totalExpenditureTill31March2018String);
			}
			
			if ($scope.workData.workStatusBean.workStatusid) {
				fd.append('workStatusBean.workStatusid', $scope.workData.workStatusBean.workStatusid);
			}
			
			if ($scope.workData.physicalStageTypeBean.physicalStageId) {
				fd.append('physicalStageTypeBean.physicalStageId', $scope.workData.physicalStageTypeBean.physicalStageId);
			}
			
			if ($scope.workData.tentativeCompletionDateString) {
				fd.append('tentativeCompletionDateString', $scope.workData.tentativeCompletionDateString);
			}
			
			if ($scope.workData.totalAmountRecievedTill31March2018String) {
				fd.append('totalAmountRecievedTill31March2018String', $scope.workData.totalAmountRecievedTill31March2018String);
			}
			
			if ($scope.workData.agreementDateString) {
				fd.append('agreementDateString', $scope.workData.agreementDateString);
			}
			
			if ($scope.workData.tenderedRateSign) {
				fd.append('tenderedRateSign', $scope.workData.tenderedRateSign);
			}
			
			if ($scope.workData.tenderedRatePer) {
				fd.append('tenderedRatePer', $scope.workData.tenderedRatePer);
			}
			
			
			if ($scope.workData.userBean.districtBean.districtId) {
				fd.append('district.districtId', $scope.workData.userBean.districtBean.districtId);
			}
			
			if ($scope.workData.block.blockId) {
				fd.append('block.blockId', $scope.workData.block.blockId);
			}
			
			if ($scope.workData.gramPanchayatBean.gramPanchayatId) {
				fd.append('gramPanchayatBean.gramPanchayatId', $scope.workData.gramPanchayatBean.gramPanchayatId);
			}
			
			if ($scope.workData.villageBean.villageId) {
				fd.append('villageBean.villageId', $scope.workData.villageBean.villageId);
			}
			
			if ($scope.workData.locationAddress) {
				fd.append('locationAddress', $scope.workData.locationAddress);
			}
			
			if ($scope.workData.workLocationLatitude) {
				fd.append('workLocationLatitude', $scope.workData.workLocationLatitude);
			}
			
			if ($scope.workData.workLocationLongitude) {
				fd.append('workLocationLongitude', $scope.workData.workLocationLongitude);
			}
			
			if ($scope.workData.locationGeometery) {
				fd.append('locationGeometery', $scope.workData.locationGeometery);
			}
			
			if ($scope.workData.userBean.officeBean.id) {
				fd.append('userBean.officeBean.id', $scope.workData.userBean.officeBean.id);
			}
			
			if ($scope.workData.assistantEngineer.id) {
				fd.append('assistantEngineer.id', $scope.workData.assistantEngineer.id);
			}
			
			if ($scope.workData.subEngineer.id) {
				fd.append('subEngineer.id', $scope.workData.subEngineer.id);
			}
			
			if ($scope.workData.technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId) {
				fd.append('technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId', $scope.workData.technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId);
			}
			
			if ($scope.workData.technicalSanctionBean.technicalSanctionNo) {
				fd.append('technicalSanctionBean.technicalSanctionNo', $scope.workData.technicalSanctionBean.technicalSanctionNo);
			}
			
			if ($scope.workData.technicalSanctionBean.technicalSanctionDate) {
				fd.append('technicalSanctionBean.technicalSanctionDate', $scope.workData.technicalSanctionBean.technicalSanctionDate);
			}
			
			if ($scope.workData.estimatedCostString) {
				fd.append('estimatedCostString', $scope.workData.estimatedCostString);
			}
			
			if ($scope.workData.administrationSanctionBean.administrationSanctionTypeBean.administrationSanctionTypeId) {
				fd.append('administrationSanctionBean.administrationSanctionTypeBean.administrationSanctionTypeId', $scope.workData.administrationSanctionBean.administrationSanctionTypeBean.administrationSanctionTypeId);
			}
			
			if ($scope.workData.administrationSanctionBean.administrationSanctionNo) {
				fd.append('administrationSanctionBean.administrationSanctionNo', $scope.workData.administrationSanctionBean.administrationSanctionNo);
			}
			
			if ($scope.workData.administrationSanctionBean.administrationSanctionDate) {
				fd.append('administrationSanctionBean.administrationSanctionDate', $scope.workData.administrationSanctionBean.administrationSanctionDate);
			}

			if ($scope.workData.totalCostString) {
				fd.append('totalCostString', $scope.workData.totalCostString);
			}
			
			if ($scope.workData.administrationSanctionBean.issuingAuthorityBean.issuingAuthorityId) {
				fd.append('administrationSanctionBean.issuingAuthorityBean.issuingAuthorityId', $scope.workData.administrationSanctionBean.issuingAuthorityBean.issuingAuthorityId);
			}
			
			$loading.start('sample-1');

			var responsePromise = $http.post('addWork', fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			});

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageLegacyDataRoute';				
				}
				$loading.finish('sample-1');
			});
			
		
			/*var responsePromise = $http.post('addWork', $scope.workData);
			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#manageLegacyDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 10000);
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
			});*/
		}
	};
	
	$scope.loadWorkList = function() {

		$loading.start('sample-1');
		fetchWorkList();
	};
	
	$scope.deleteWork = function(workId) {		
		if (confirm("Are you sure to delete this entry?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('deleteWork/'+ workId);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageLegacyDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 5000);
				}
				$loading.finish('sample-1');
			});
		}else{
			return false;
		}
	};
	
	$scope.loadWorkDetail = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			
			$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
			$scope.workData.workTypeId = $scope.workData.workTypeId+"";
			$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
			if($scope.workData.workSubTypeId)
				$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
			$scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
			
			if($scope.workData.physicalStageId == null)
			{
				$scope.workData.physicalStageId = "";
			} else {
				$scope.workData.physicalStageId = $scope.workData.physicalStageId + "";	
			}
			
			$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
			$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
			if($scope.workData.contractorId)
			$scope.workData.contractorId=$scope.workData.contractorId+"";
			$scope.loadContractors($scope.workData.contractorName);
			
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
		
			$scope.workData.districtId = $scope.workData.districtId+"";
			$scope.loadBlocksByDistrictNew($scope.workData.districtId);
			$scope.workData.blockId = $scope.workData.blockId+"";
			$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
			$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
			$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
			if($scope.workData.villageId){
				$scope.workData.villageId = $scope.workData.villageId+"";
			}
			
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
			
			
			$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
			$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
			if($scope.workData.assistantEngineerId == null)
				{
			$scope.workData.assistantEngineerId = "";
				} else {
					$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
				}
			
			
			$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
			if($scope.workData.subEngineerId == null)
			{
				$scope.workData.subEngineerId = "";
			} else {
				$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
			}
			
			$scope.workData.technicalSanctionTypeId = $scope.workData.technicalSanctionTypeId+"";
			$scope.workData.administrationSanctionTypeId = $scope.workData.administrationSanctionTypeId+"";
			
			if($scope.workData.issuingAuthorityId)
			$scope.workData.issuingAuthorityId = $scope.workData.issuingAuthorityId+"";
			
			if($scope.workData.tsIssuingAuthorityId)
				$scope.workData.tsIssuingAuthorityId = $scope.workData.tsIssuingAuthorityId+"";
				
				
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.editWorkData = function(isValid, asPdfFile, tsPdfFile, acPdfFile, dcPdfFile, ecPdfFile) {

		//$scope.noFileError = (asPdfFile)?false:true;
		/*$scope.fileExtentionErrorAs = (asPdfFile)?false:true;
		$scope.fileExtentionErrorTs = (tsPdfFile)?false:true;
		$scope.fileExtentionErrorAc = (acPdfFile)?false:true;
		$scope.fileExtentionErrorDc = (dcPdfFile)?false:true;
		$scope.fileExtentionErrorEc = (ecPdfFile)?false:true;*/
		
		if (!isValid)
			return false;		
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			
			var fd = new FormData();
			
			if(acPdfFile){
				fd.append('agreementCopyFile', acPdfFile);
				}
			
			if(asPdfFile){
			fd.append('administrationSanctionFile', asPdfFile);
			}
			
			if(tsPdfFile){
			fd.append('technicalSanctionFile', tsPdfFile);
			}
			
			if(dcPdfFile){
			fd.append('latestDrawingCopyFile', dcPdfFile);
			}
			
			if(ecPdfFile){
			fd.append('estimateFile', ecPdfFile);
			}
			

			if ($scope.workData.workId) {
				fd.append('workId', $scope.workData.workId);
			}
			
			if ($scope.workData.workName) {
				fd.append('workName', $scope.workData.workName);
			}
			
			if ($scope.workData.workTypeId) {
				fd.append('workTypeId', $scope.workData.workTypeId);
			}
			
			if ($scope.workData.workSubTypeId) {
				fd.append('workSubTypeId', $scope.workData.workSubTypeId);
			}
			
			if ($scope.workData.lineDepartmentId) {
				fd.append('lineDepartmentId', $scope.workData.lineDepartmentId);
			}
			
			if ($scope.workData.accountHead) {
				fd.append('accountHead', $scope.workData.accountHead);
			}
			
			if ($scope.workData.agencyTypeId) {
				fd.append('agencyTypeId', $scope.workData.agencyTypeId);
			}
			
			if ($scope.workData.contractorId) {
				fd.append('contractorId', $scope.workData.contractorId);
			}
			
			if ($scope.workData.agencyName) {
				fd.append('agencyName', $scope.workData.agencyName);
			}
			
			if ($scope.workData.totalExpenditureTill31March2018String) {
				fd.append('totalExpenditureTill31March2018String', $scope.workData.totalExpenditureTill31March2018String);
			}
			
			if ($scope.workData.workStatusId) {
				fd.append('workStatusId', $scope.workData.workStatusId);
			}
			
			if ($scope.workData.physicalStageId) {
				fd.append('physicalStageId', $scope.workData.physicalStageId);
			}
			
			if ($scope.workData.tentativeCompletionDateString) {
				fd.append('tentativeCompletionDateString', $scope.workData.tentativeCompletionDateString);
			}
			
			if ($scope.workData.totalAmountRecievedTill31March2018String) {
				fd.append('totalAmountRecievedTill31March2018String', $scope.workData.totalAmountRecievedTill31March2018String);
			}
			
			if ($scope.workData.agreementDateString) {
				fd.append('agreementDateString', $scope.workData.agreementDateString);
			}
			
			if ($scope.workData.tenderedRateSign) {
				fd.append('tenderedRateSign', $scope.workData.tenderedRateSign);
			}
			
			if ($scope.workData.tenderedRatePer) {
				fd.append('tenderedRatePer', $scope.workData.tenderedRatePer);
			}
			
			if ($scope.workData.districtId) {
				fd.append('districtId', $scope.workData.districtId);
			}
			
			if ($scope.workData.blockId) {
				fd.append('blockId', $scope.workData.blockId);
			}
			
			if ($scope.workData.gramPanchayatId) {
				fd.append('gramPanchayatId', $scope.workData.gramPanchayatId);
			}
			
			if ($scope.workData.villageId) {
				fd.append('villageId', $scope.workData.villageId);
			}
			
			if ($scope.workData.locationAddress) {
				fd.append('locationAddress', $scope.workData.locationAddress);
			}
			
			if ($scope.workData.workLocationLatitude) {
				fd.append('workLocationLatitude', $scope.workData.workLocationLatitude);
			}
			
			if ($scope.workData.workLocationLongitude) {
				fd.append('workLocationLongitude', $scope.workData.workLocationLongitude);
			}
			
			if ($scope.workData.locationGeometery) {
				fd.append('locationGeometery', $scope.workData.locationGeometery);
			}
			
			if ($scope.workData.executiveEngineerOfficeId) {
				fd.append('executiveEngineerOfficeId', $scope.workData.executiveEngineerOfficeId);
			}
			
			if ($scope.workData.assistantEngineerId) {
				fd.append('assistantEngineerId', $scope.workData.assistantEngineerId);
			}
			
			if ($scope.workData.subEngineerId) {
				fd.append('subEngineerId', $scope.workData.subEngineerId);
			}
			
			if ($scope.workData.technicalSanctionTypeId) {
				fd.append('technicalSanctionTypeId', $scope.workData.technicalSanctionTypeId);
			}
			
			if ($scope.workData.technicalSanctionNo) {
				fd.append('technicalSanctionNo', $scope.workData.technicalSanctionNo);
			}
			
			if ($scope.workData.technicalSanctionDate) {
				fd.append('technicalSanctionDate', $scope.workData.technicalSanctionDate);
			}
			
			if ($scope.workData.estimatedCostString) {
				fd.append('estimatedCostString', $scope.workData.estimatedCostString);
			}
			
			if ($scope.workData.administrationSanctionTypeId) {
				fd.append('administrationSanctionTypeId', $scope.workData.administrationSanctionTypeId);
			}
			
			if ($scope.workData.administrationSanctionNo) {
				fd.append('administrationSanctionNo', $scope.workData.administrationSanctionNo);
			}
			
			if ($scope.workData.administrationSanctionDate) {
				fd.append('administrationSanctionDate', $scope.workData.administrationSanctionDate);
			}

			if ($scope.workData.totalCostString) {
				fd.append('totalCostString', $scope.workData.totalCostString);
			}
			
			if ($scope.workData.issuingAuthorityId) {
				fd.append('issuingAuthorityId', $scope.workData.issuingAuthorityId);
			}
			
			$loading.start('sample-1');

			var responsePromise = $http.post('editWork', fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			});

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
//					$window.location.href = '#/editLegacyDataRoute/'+$scope.workData.workId;
					$window.location.href = '#manageLegacyDataRoute';	
				}
				$loading.finish('sample-1');
			});
			
		
			/*var responsePromise = $http.post('addWork', $scope.workData);
			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#manageLegacyDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 10000);
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
			});*/
		}
	};
	
	
	
/*	$scope.editWorkData = function(isValid) {

		if (!isValid) 
			return false;

		if (confirm("Are you sure you want to save the data?")) {
			$scope.workData.workId = $routeParams.id;
			$loading.start('sample-1');
			var responsePromise = $http.post('editWork', $scope.workData);
	
			responsePromise.success(function(data, status, headers, config) {
	
				$rootScope.responseObject = data;
	
				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 5000);
					$window.location.href = '#manageLegacyDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 5000);
				}
				$loading.finish('sample-1');
			});
		}
	};*/
	
	$scope.loadWorkStatusType = function() {
		$loading.start('sample-1');
		var response = $http.get('fetchWorkStatusType');
		response.success(function(data, status, headers, config) {
			$scope.workStatusTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadPhysicalStageByWorkTypeId = function(workTypeId) {
		$loading.start('sample-1');
		var response = $http.get('fetchPhysicalStageByWorkTypeId/'+workTypeId);
		response.success(function(data, status, headers, config) {
			$scope.physicalStageTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadContractors = function(name) {
		if(null!= name && name.length>3){
			$loading.start('sample-1');
			var response = $http.get('fetchContractors/'+name);
			response.success(function(data, status, headers, config) {
				$scope.contractors = data;
				$loading.finish('sample-1');
			});
		}else{
			$scope.contractors=null;
		}
	};
	
	$scope.onVillageChange = function() {
		
		
		var village = $scope.villages.find(x => x.villageId == $scope.workData.villageBean.villageId);
		if(village.latitudeString !== "null" && village.longitudeString !== "null")
			{
		$('#mapLatitude').val(village.latitudeString);
		$('#mapLongitude').val(village.longitudeString);
		
		$('#mapUpdateButton').click();
		
		$('#google-map-popup').modal('show');
	}
	};
	
$scope.onVillageChangeDuringEdit = function() {
		
		
		var village = $scope.villages.find(x => x.villageId == $scope.workData.villageId);
		if(village.latitudeString !== "null" && village.longitudeString !== "null")
			{
		$('#mapLatitude').val(village.latitudeString);
		$('#mapLongitude').val(village.longitudeString);
		
		$('#mapUpdateButton').click();
		
		$('#google-map-popup').modal('show');
	}
	};
	
	
	$("#google-map-popup").on("hidden.bs.modal", function () {
	    
		$scope.workData.workLocationLatitude = $('#mapLatitude').val();
		$scope.workData.workLocationLongitude = $('#mapLongitude').val();
	});
	

	$scope.downloadDocument = function(documentId) {		
			$window.open('downloadDocument/'+documentId);
	};
	
	$scope.loadWorkDetailInCe = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$scope.workData.workTypeId = $scope.workData.workTypeId+"";
			$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
			$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
			$scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
			
			if($scope.workData.physicalStageId == null)
			{
				$scope.workData.physicalStageId = "";
			} else {
				$scope.workData.physicalStageId = $scope.workData.physicalStageId + "";	
			}
			$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
			
			$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
			$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
			if($scope.workData.contractorId)
			$scope.workData.contractorId=$scope.workData.contractorId+"";
			$scope.loadContractors($scope.workData.contractorName);
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
		
			$scope.workData.districtId = $scope.workData.districtId+"";
			$scope.loadBlocksByDistrictNew($scope.workData.districtId);
			$scope.workData.blockId = $scope.workData.blockId+"";
			$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
			$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
			$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
			$scope.workData.villageId = $scope.workData.villageId+"";
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
			
			
			$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
			$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
			if($scope.workData.assistantEngineerId == null)
				{
			$scope.workData.assistantEngineerId = "";
				} else {
					$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
				}
			
			
			$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
			if($scope.workData.subEngineerId == null)
			{
				$scope.workData.subEngineerId = "";
			} else {
				$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
			}
			
			$scope.workData.technicalSanctionTypeId = $scope.workData.technicalSanctionTypeId+"";
			$scope.workData.administrationSanctionTypeId = $scope.workData.administrationSanctionTypeId+"";
			if($scope.workData.tsIssuingAuthorityId)
			$scope.workData.tsIssuingAuthorityId = $scope.workData.tsIssuingAuthorityId+"";
			
			if($scope.workData.issuingAuthorityId)
			$scope.workData.issuingAuthorityId = $scope.workData.issuingAuthorityId+"";
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadWorkSubTypeByWorkTypeId = function(workTypeId) {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkSubTypeByWorkTypeId/'+workTypeId);
		response.success(function(data, status, headers, config) {
			$scope.workSubTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadPhysicalStageByWorkTypeId = function(workTypeId) {
		$loading.start('sample-1');
		var response = $http.get('fetchPhysicalStageByWorkTypeId/'+workTypeId);
		response.success(function(data, status, headers, config) {
			$scope.physicalStageTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadBlocksByDistrictNew = function(districtId) {

		$loading.start('sample-1');
//		$scope.entrepreneurData.registeredAddress.blockId = "";
		var response = $http.get('fetchBlocksByDistrictNew/'+districtId);
		response.success(function(data, status, headers, config) {
			$scope.blocks = data;
//			$scope.blocks.block.blockCode = $scope.blocks.block.blockCode+"";
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadGramPanchayatByBlockCode = function (blockCode) {

    $loading.start('sample-1');

    $http.get('fetchGramPanchayatByBlockCode/' + blockCode)
        .success(function (data) {

            $scope.gramPanchayats = data;
            $scope.villages = [];

            // 🔥 IMPORTANT FIX
            if ($scope.workData.gramPanchayatId) {
                $scope.workData.gramPanchayatId =
                    Number($scope.workData.gramPanchayatId);
            }

            $loading.finish('sample-1');
        });
};
	

$scope.loadVillageByGramPanchayatCode = function (gramPanchayatCode) {

    $loading.start('sample-1');

    $http.get('fetchVillageByGramPanchayatCode/' + gramPanchayatCode)
        .success(function (data) {

            $scope.villages = data;

            // 🔥 IMPORTANT FIX
            if ($scope.workData.villageId) {
                $scope.workData.villageId =
                    Number($scope.workData.villageId);
            }

            $loading.finish('sample-1');
        });
};

	
	$scope.loadAssistantEngineerByOfficeId = function(officeId) {

		$loading.start('sample-1');
		var response = $http.get('fetchAssistantEngineerByOfficeId/'+officeId);
		response.success(function(data, status, headers, config) {
			$scope.assistantEngineers = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadSubEngineerByOfficeId = function(officeId) {
		$loading.start('sample-1');
		var response = $http.get('fetchSubEngineerByOfficeId/'+officeId);
		response.success(function(data, status, headers, config) {
			$scope.subEngineers = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.unlockWork = function(id) {		
		if (confirm("Are you sure , you want to unlock this work for future editing?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('unlockWork/'+ id);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageLegacyDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 5000);
				}
				$loading.finish('sample-1');
			});
		}else{
			return false;
		}
	};
	
	
	$scope.unlockWorkRequisition = function(id) {		
		if (confirm("Are you sure , you want to unlock this work for future editing?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('unlockWork/'+ id);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageWorkRequisitionDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 5000);
				}
				$loading.finish('sample-1');
			});
		}else{
			return false;
		}
	};
	
		$scope.onchangeGp = function () {
    if (!$scope.workData.gramPanchayatId) return;

    $http.get('fetchLgdGpCode/' + $scope.workData.gramPanchayatId)
        .then(function (res) {
            $scope.grampanchayat = res.data;
            return $http.post('fetchGpGeojson/' + $scope.grampanchayat.gpCode);
        })
        .then(function (res) {
            drawGeoJsonOnMap(res.data.data[0].admingeojsoninfo);
        })
        .catch(function (err) {
            console.error('GP GeoJSON error', err);
        });
};

	$scope.onchangeVillage = function () {
    if (!$scope.workData.villageId) return;

    $http.get('fetchVillageCode/' + $scope.workData.villageId)
        .then(function (res) {
            $scope.village = res.data;
            return $http.post('fetchVillageGeojson/' + $scope.village.villageCode);
        })
        .then(function (res) {
            drawGeoJsonOnMap(res.data.data[0].admingeojsoninfo);
        })
        .catch(function (err) {
            console.error('Village GeoJSON error', err);
        });
};
	$scope.loadWorkRequisitionDetailInCe = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$scope.workData.workTypeId = $scope.workData.workTypeId+"";
			$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
			$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
			$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
			$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
			$scope.workData.districtId = $scope.workData.districtId+"";
				$scope.loadBlocksByDistrictNew($scope.workData.districtId);
						
						
						$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
						if (!$scope.workData.block) {
						    $scope.workData.block = {};
						}
						$scope.workData.block.blockId = $scope.workData.blockId + "";
						
					
						$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
						if (!$scope.workData.gp) {
						    $scope.workData.gp = {};
						}
						$scope.workData.gp.gramPanchayatId = $scope.workData.gramPanchayatId+"";
						
						if (!$scope.workData.village) {
						    $scope.workData.village = {};
						}
						$scope.workData.village.villageId = $scope.workData.villageId+"";
						
						if ($scope.workData.workId != "" && $scope.workData.workId != null && $scope.workData.workId != null) {
						//$scope.loadVillagesByProject($scope.workData.projectId, $scope.workData.gramPanchayatId);
						$scope.loadVillageByGPId($scope.workData.gramPanchayatId);
					}
					if ($scope.workData.gramPanchayatId != null || $scope.workData.gramPanchayatId == "") {
						if ($scope.workData.villageId != null || $scope.workData.villageId != "") {
							$scope.onchangeGp($scope.workData.gramPanchayatId);
						}
						$scope.workData.gramPanchayatId += "";
		
					}
		
					if ($scope.workData.villageId != null || $scope.workData.villageId != "") {
						$scope.workData.villageId = $scope.workData.villageId + "";
					}
		
		
		
					if ($scope.workData.villageId != null || $scope.workData.villageId != "") {
						$scope.onchangeVillage();
					}
						
			$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
			
			$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
			$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
			if($scope.workData.assistantEngineerId == null)
				{
			$scope.workData.assistantEngineerId = "";
				} else {
					$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
				}
			
			
			$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
			if($scope.workData.subEngineerId == null)
			{
				$scope.workData.subEngineerId = "";
			} else {
				$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
			}
			
			
			
			/*$scope.workData.workTypeId = $scope.workData.workTypeId+"";
			$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
			$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
			$scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
			
			if($scope.workData.physicalStageId == null)
			{
				$scope.workData.physicalStageId = "";
			} else {
				$scope.workData.physicalStageId = $scope.workData.physicalStageId + "";	
			}
			
			$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
			$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
			if($scope.workData.contractorId)
			$scope.workData.contractorId=$scope.workData.contractorId+"";
			
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
		
			$scope.workData.districtId = $scope.workData.districtId+"";
			$scope.loadBlocksByDistrictNew($scope.workData.districtId);
			$scope.workData.blockId = $scope.workData.blockId+"";
			$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
			$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
			$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
			$scope.workData.villageId = $scope.workData.villageId+"";
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
			
			
			$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
			$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
			if($scope.workData.assistantEngineerId == null)
				{
			$scope.workData.assistantEngineerId = "";
				} else {
					$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
				}
			
			
			$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
			if($scope.workData.subEngineerId == null)
			{
				$scope.workData.subEngineerId = "";
			} else {
				$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
			}
			
			$scope.workData.technicalSanctionTypeId = $scope.workData.technicalSanctionTypeId+"";
			$scope.workData.administrationSanctionTypeId = $scope.workData.administrationSanctionTypeId+"";
			
			if($scope.workData.issuingAuthorityId)
			$scope.workData.issuingAuthorityId = $scope.workData.issuingAuthorityId+"";*/
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadVillageByGPId = function(gramPanchayatId) {

		//console.log("loadVillageByGPId"+gramPanchayatId);
		var response1 = $http.get('fetchGramPanchayatId/' + gramPanchayatId);
		response1.success(function(data, status, headers, config) {
			//console.log(JSON.stringify(response1))
			$scope.villages1 = data;
			//console.log("Villages"+$scope.workData.villageId);
		}).then(() => {

			if (gramPanchayatId && gramPanchayatId != null && gramPanchayatId != 'null') {
				$loading.start('sample-1');
				//console.log("$scope.villages1.gramPanchayatCode"+$scope.villages1.gramPanchayatCode);
				$http.get('fetchVillageByGramPanchayatCode/' + $scope.villages1.gramPanchayatCode)
					.then(function(response) {
						// Assuming response.data is an array of villages

						$scope.villages = response.data.sort((a, b) => {
							// Compare village names to sort alphabetically
							if (a.villageName < b.villageName) {
								return -1;
							}
							if (a.villageName > b.villageName) {
								return 1;
							}
							return 0;
						});
						// $scope.workData.villageId = $scope.villages[0].villageId;
						$loading.finish('sample-1');
					})
					.catch(function(error) {
						// Handle error here
						console.error('Error fetching villages:', error);
						$loading.finish('sample-1');
					});

			}

		});
	};
	
	
function drawGeoJsonOnMap(geojsonStr) {
    let geojsonFeature = JSON.parse(geojsonStr).features[0];

    if (geojsonLayer) {
        map.removeLayer(geojsonLayer);
        geojsonLayer = null;
    }

    geojsonLayer = L.geoJson(geojsonFeature, {
        style: {
            color: "#FFFF00",
            weight: 3,
            fillColor: "#FFFF00",
            fillOpacity: 0.1
        }
    });

    geojsonLayer.addTo(map);
    map.fitBounds(geojsonLayer.getBounds());
}
	
	
	$scope.loadContractors = function(name) {
		if(null!= name && name.length>3){
			$loading.start('sample-1');
			var response = $http.get('fetchContractors/'+name);
			response.success(function(data, status, headers, config) {
				$scope.contractors = data;
				$loading.finish('sample-1');
			});
		}else{
			$scope.contractors=null;
		}
	};
	

	
	$scope.loadAccountHead = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchAccountHead');
		response.success(function(data, status, headers, config) {
			$scope.accountHeads = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadTSIssuingAuthority = function() {
		$loading.start('sample-1');
		var response = $http.get('fetchTSIssuingAuthorityFromDesignationTable');
		response.success(function(data, status, headers, config) {
			$scope.tsIssuingAuthorities = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadHistoryWorkLegacy = function() {

		$loading.start('sample-1');
		fetchHistoryWorkLegacy($routeParams.id);
	};
	
$scope.loadWorkDetailHistoryLegacy = function() {	
		
		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetailsHistoryLegacy/'+$routeParams.workLoggingId);
		
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$scope.workData.workTypeId = $scope.workData.workTypeId+"";
			$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
			$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
			if($scope.workData.workSubTypeId)
			$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
			$scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
			
			if($scope.workData.physicalStageId == null)
			{
				$scope.workData.physicalStageId = "";
			} else {
				$scope.workData.physicalStageId = $scope.workData.physicalStageId + "";	
			}
			
			$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
			$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
			if($scope.workData.contractorId)
			$scope.workData.contractorId=$scope.workData.contractorId+"";
			$scope.loadContractors($scope.workData.contractorName);
			
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
		
			$scope.workData.districtId = $scope.workData.districtId+"";
			$scope.loadBlocksByDistrictNew($scope.workData.districtId);
			$scope.workData.blockId = $scope.workData.blockId+"";
			$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
			$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
			$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
			if(null!=$scope.workData.villageId){
				$scope.workData.villageId = $scope.workData.villageId+"";
			}
			
			$scope.workData.workStatusId = $scope.workData.workStatusId+"";
			
			
			$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
			$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
			if($scope.workData.assistantEngineerId == null)
				{
			$scope.workData.assistantEngineerId = "";
				} else {
					$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
				}
			
			
			$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
			if($scope.workData.subEngineerId == null)
			{
				$scope.workData.subEngineerId = "";
			} else {
				$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
			}
			
			$scope.workData.technicalSanctionTypeId = $scope.workData.technicalSanctionTypeId+"";
			if($scope.workData.tsIssuingAuthorityId)
			$scope.workData.tsIssuingAuthorityId = $scope.workData.tsIssuingAuthorityId+"";
			$scope.workData.administrationSanctionTypeId = $scope.workData.administrationSanctionTypeId+"";
			
			if($scope.workData.issuingAuthorityId)
			$scope.workData.issuingAuthorityId = $scope.workData.issuingAuthorityId+"";
			
			if($scope.workData.probableAmountOfWork == null)
				{
				$scope.workData.probableAmountOfWork = "";
				}
			else {
				$scope.workData.probableAmountOfWork = $scope.workData.probableAmountOfWork+"";
			}
			
			$loading.finish('sample-1');
		});
	};
	
	$scope.editWorkDataRevise = function(isValid, asPdfFile, tsPdfFile, acPdfFile, dcPdfFile, ecPdfFile) {

		//$scope.noFileError = (asPdfFile)?false:true;
		/*$scope.fileExtentionErrorAs = (asPdfFile)?false:true;
		$scope.fileExtentionErrorTs = (tsPdfFile)?false:true;
		$scope.fileExtentionErrorAc = (acPdfFile)?false:true;
		$scope.fileExtentionErrorDc = (dcPdfFile)?false:true;
		$scope.fileExtentionErrorEc = (ecPdfFile)?false:true;*/
		
		
		if(tsPdfFile){
			if(tsPdfFile.size > 5000000)
				return false;
		}
	    
		
		if(asPdfFile){
		if(asPdfFile.size > 5000000)
			return false;
		}
		
	    if(dcPdfFile){
		if(dcPdfFile.size > 5000000)
			return false;
	    }
	    
	    if(acPdfFile){
		if(acPdfFile.size > 5000000)
			return false;
	    }
	    
	    if(ecPdfFile){
		if(ecPdfFile.size > 5000000)
			return false;
	    }
		
		if (!isValid)
			return false;		
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			
			var fd = new FormData();
			
			
			 if($scope.finalSubmit==true){
				/*$scope.workData.status = 'Active';*/
				$scope.workData.workRequestStatusId=2;
			}
			
			 
			
			/*fd.append('status', $scope.workData.status);*/
			fd.append('workRequestStatusId', $scope.workData.workRequestStatusId);
			
			if(acPdfFile){
				fd.append('agreementCopyFile', acPdfFile);
				}
			
			if(asPdfFile){
			fd.append('administrationSanctionFile', asPdfFile);
			}
			
			if(tsPdfFile){
			fd.append('technicalSanctionFile', tsPdfFile);
			}
			
			if(dcPdfFile){
			fd.append('latestDrawingCopyFile', dcPdfFile);
			}
			
			if(ecPdfFile){
			fd.append('estimateFile', ecPdfFile);
			}
			

			if ($scope.workData.workId) {
				fd.append('workId', $scope.workData.workId);
			}
			if ($scope.workData.technicalSanctionId) {
				fd.append('tsParentId', $scope.workData.technicalSanctionId);
			}
			if ($scope.workData.administrationSanctionId) {
				fd.append('asParentId', $scope.workData.administrationSanctionId);
			}
			
			if ($scope.workData.workName) {
				fd.append('workName', $scope.workData.workName);
			}
			
			if ($scope.workData.distance) {
				fd.append('distance', $scope.workData.distance);
			}
			if ($scope.workData.completeddistance) {
				fd.append('completeddistance', $scope.workData.completeddistance);
			}
			
			if ($scope.workData.workTypeId) {
				fd.append('workTypeId', $scope.workData.workTypeId);
			}
			
			if ($scope.workData.workSubTypeId) {
				fd.append('workSubTypeId', $scope.workData.workSubTypeId);
			}
			
			if ($scope.workData.lineDepartmentId) {
				fd.append('lineDepartmentId', $scope.workData.lineDepartmentId);
			}
			
			if ($scope.workData.accountHeadId) {
				fd.append('accountHeadId', $scope.workData.accountHeadId);
			}
			
			if ($scope.workData.executiveEngineerOfficeName) {
				fd.append('executiveEngineerOfficeName', $scope.workData.executiveEngineerOfficeName);
			}
			
			
			if ($scope.workData.agencyTypeId) {
				fd.append('agencyTypeId', $scope.workData.agencyTypeId);
			}
			
			if ($scope.workData.contractorId) {
				fd.append('contractorId', $scope.workData.contractorId);
			}
			
			/*if ($scope.workData.agencyName) {
				fd.append('agencyName', $scope.workData.agencyName);
			}*/
			
			if ($scope.workData.totalExpenditureTill31March2018String) {
				fd.append('totalExpenditureTill31March2018String', $scope.workData.totalExpenditureTill31March2018String);
			}
			
			if ($scope.workData.totalExpenditureOnContingencyTill31March2018) {
				fd.append('totalExpenditureOnContingencyTill31March2018', $scope.workData.totalExpenditureOnContingencyTill31March2018);
			}
			
			
			
			if ($scope.workData.workStatusId) {
				fd.append('workStatusId', $scope.workData.workStatusId);
			}
			
			if ($scope.workData.physicalStageId) {
				fd.append('physicalStageId', $scope.workData.physicalStageId);
			}
			
			if ($scope.workData.tentativeCompletionDateString) {
				fd.append('tentativeCompletionDateString', $scope.workData.tentativeCompletionDateString);
			}
			
			if ($scope.workData.totalAmountRecievedTill31March2018String) {
				fd.append('totalAmountRecievedTill31March2018String', $scope.workData.totalAmountRecievedTill31March2018String);
			}
			
			if ($scope.workData.agreementDateString) {
				fd.append('agreementDateString', $scope.workData.agreementDateString);
			}
			
			if ($scope.workData.agreementNumber) {
				fd.append('agreementNumber', $scope.workData.agreementNumber);
			}
			
			if ($scope.workData.tenderedRateSign) {
				fd.append('tenderedRateSign', $scope.workData.tenderedRateSign);
			}
			
			if ($scope.workData.pacAmount) {
				fd.append('pacAmount', $scope.workData.pacAmount);
			}

			if ($scope.workData.tenderCost) {
				fd.append('tenderCost', $scope.workData.tenderCost);
			}
			
			
			if ($scope.workData.tenderedRatePer) {
				fd.append('tenderedRatePer', $scope.workData.tenderedRatePer);
			}
			
			if ($scope.workData.districtId) {
				fd.append('districtId', $scope.workData.districtId);
			}
			
			if ($scope.workData.blockId) {
				fd.append('blockId', $scope.workData.blockId);
			}
			
			if ($scope.workData.gramPanchayatId) {
				fd.append('gramPanchayatId', $scope.workData.gramPanchayatId);
			}
			
			if ($scope.workData.villageId) {
				fd.append('villageId', $scope.workData.villageId);
			}
			
			if ($scope.workData.locationAddress) {
				fd.append('locationAddress', $scope.workData.locationAddress);
			}
			
			if ($scope.workData.workLocationLatitude) {
				fd.append('workLocationLatitude', $scope.workData.workLocationLatitude);
			}
			
			if ($scope.workData.workLocationLongitude) {
				fd.append('workLocationLongitude', $scope.workData.workLocationLongitude);
			}
			
			if ($scope.workData.locationGeometery) {
				fd.append('locationGeometery', $scope.workData.locationGeometery);
			}
			
			if ($scope.workData.executiveEngineerOfficeId) {
				fd.append('executiveEngineerOfficeId', $scope.workData.executiveEngineerOfficeId);
			}
			
			if ($scope.workData.assistantEngineerId) {
				fd.append('assistantEngineerId', $scope.workData.assistantEngineerId);
			}
			
			if ($scope.workData.subEngineerId) {
				fd.append('subEngineerId', $scope.workData.subEngineerId);
			}
			
			if ($scope.workData.technicalSanctionTypeId) {
				
				if ($scope.workData.technicalSanctionTypeId!="null") {
				fd.append('technicalSanctionTypeId', $scope.workData.technicalSanctionTypeId);
				}
			}
			
			if ($scope.workData.technicalSanctionNo) {
				fd.append('technicalSanctionNo', $scope.workData.technicalSanctionNo);
			}
			
			if ($scope.workData.technicalSanctionDate) {
				fd.append('technicalSanctionDate', $scope.workData.technicalSanctionDate);
			}
			
			if ($scope.workData.estimatedCostString) {
				fd.append('estimatedCostString', $scope.workData.estimatedCostString);
			}
			
			if ($scope.workData.tsIssuingAuthorityId) {
				fd.append('tsIssuingAuthorityId', $scope.workData.tsIssuingAuthorityId);
			}
			
			if ($scope.workData.tsAuthorityName) {
				fd.append('tsAuthorityName', $scope.workData.tsAuthorityName);
			}
			
			if ($scope.workData.administrationSanctionTypeId) {
				fd.append('administrationSanctionTypeId', $scope.workData.administrationSanctionTypeId);
			}
			
			if ($scope.workData.administrationSanctionNo) {
				fd.append('administrationSanctionNo', $scope.workData.administrationSanctionNo);
			}
			
			if ($scope.workData.administrationSanctionDate) {
				fd.append('administrationSanctionDate', $scope.workData.administrationSanctionDate);
			}

			if ($scope.workData.totalCostString) {
				fd.append('totalCostString', $scope.workData.totalCostString);
			}
			
			if ($scope.workData.contingencyAmount) {
				fd.append('contingencyAmount', $scope.workData.contingencyAmount);
			}
			
			if ($scope.workData.issuingAuthorityId) {
				fd.append('issuingAuthorityId', $scope.workData.issuingAuthorityId);
			}
			
			
			if ($scope.workData.asAuthorityName) {
				fd.append('asAuthorityName', $scope.workData.asAuthorityName);
			}
			
			if ($scope.workData.remarks) {
				fd.append('remarks', $scope.workData.remarks);
			}
			
			if (null!= $scope.workData.probableAmountOfWork) {
				fd.append('probableAmountOfWork', $scope.workData.probableAmountOfWork);
			}
			
			
			$loading.start('sample-1');

			var responsePromise = $http.post('editWorkRevise', fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
				}
			});

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
//					$window.location.href = '#/editLegacyDataRoute/'+$scope.workData.workId;
					$window.location.href = '#viewCEWorksForLegacy';	
				}
				$loading.finish('sample-1');
			});
			
		
			/*var responsePromise = $http.post('addWork', $scope.workData);
			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#manageLegacyDataRoute';
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 10000);
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
			});*/
		}
	};
	$scope.calculateTenderCost = function(pacAmount, percentage, sign, asCost) {
		
		if(pacAmount!=null&&percentage!=null&&sign!=null&&asCost!=null)
			{
		var tenderCost =null;
		if(sign=="+")
			tenderCost = parseFloat(pacAmount) + (parseFloat(pacAmount)*(parseFloat(percentage)/100));
		else
			tenderCost = parseFloat(pacAmount) - (parseFloat(pacAmount)*(parseFloat(percentage)/100));
		
		if(tenderCost > asCost){
			alert("Tender Cost Cannot be More Than Administration Cost");
			$scope.workData.pacAmount = null;
			$scope.workData.tenderCost = null;
		}
		else {
		$scope.workData.tenderCost = tenderCost.toFixed(0);
		}
			} else {
				$scope.workData.tenderCost = null;
			}
		
		/*$('#tenderCostId').val(tenderCost);*/
		};
		$scope.compareAdminAmount = function(largeAmount, smallAmount) {
			
			if(largeAmount!=null && smallAmount!=null){
			if(parseFloat(largeAmount)<parseFloat(smallAmount)){
				alert("Contingency Amount  cannot be greater than Administrative Sanction Amount (including contingency) ");
				$scope.workData.totalCostString=null;
				$scope.workData.contingencyAmount=null;
			}
			}
			
			/*workData.technicalSanctionBean.technicalSanctionDate*/
			
			};
			$scope.calculatePawAmount = function(asAmount, contingencyAmount, tenderedRatePer, tenderedRateSign) {
				
				var paw =null;
				
				if(asAmount!=null&&contingencyAmount!=null&&tenderedRatePer!=null&&tenderedRateSign!=null)
				{
				if(tenderedRateSign=="+")
					{
					paw = parseFloat(asAmount) - parseFloat(contingencyAmount);
					$scope.workData.probableAmountOfWork = paw.toFixed( 2 );;
					}
				else if (tenderedRateSign=="-") {
					paw = ((parseFloat(asAmount) - parseFloat(contingencyAmount))- ((parseFloat(asAmount) - parseFloat(contingencyAmount))*(parseFloat(tenderedRatePer)/100)));
					$scope.workData.probableAmountOfWork = paw.toFixed( 2 );;
				} else {
					$scope.workData.probableAmountOfWork = null;
				}
				}
				
				};
$scope.compareTotalExpenditureAmount = function(largeAmount, smallAmount) {
					
					if(largeAmount!=null && smallAmount!=null){
				if(parseFloat(largeAmount)<parseFloat(smallAmount)){
					alert("Total Expenditure On Contingency Till 31 March 2018 cannot be greater than Total Expenditure Till 31 March 2018 (including contingency) ");
					$scope.workData.totalExpenditureTill31March2018String=null;
					$scope.workData.totalExpenditureOnContingencyTill31March2018=null;
				}
					}
				
				/*workData.technicalSanctionBean.technicalSanctionDate*/
				
				};
				
				
			
	
	
	
});