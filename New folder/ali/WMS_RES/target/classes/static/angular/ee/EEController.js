var res = angular.module('res');


res.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            var maxSizeUpload = 5000000;//in bytes (here 5 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {            	
            	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1); 
            	var fileSize = element[0].files[0].size; 
            	switch (attrs.fileModel) {
				case "asPdfFile":
					scope.maxSizeErrorAs = (fileSize > maxSizeUpload);
					scope.fileExtentionErrorAs = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorAs == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}							
				break;
				case "tsPdfFile":
					scope.maxSizeErrorTs = (fileSize > maxSizeUpload);					
					scope.fileExtentionErrorTs = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.maxSizeErrorTs == false && scope.fileExtentionErrorTs == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}					
				break;
				case "dcPdfFile":
					scope.maxSizeErrorDc = (fileSize > maxSizeUpload);
					scope.fileExtentionErrorDc = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorDc == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "acPdfFile":
					scope.maxSizeErrorAc = (fileSize > maxSizeUpload);
					scope.fileExtentionErrorAc = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorAc == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "ecPdfFile":
					scope.maxSizeErrorEc = (fileSize > maxSizeUpload);
					scope.fileExtentionErrorEc = (allowedExtensions.indexOf(fileExtension) < 0);							
					if (scope.fileExtentionErrorEc == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
					}
				break;
				case "ldPdfFile":
					scope.noFileError = false;
					scope.fileExtentionErrorLd = (allowedExtensions.indexOf(fileExtension) < 0);
					scope.fileSizeErrorLd = (fileSize > maxSizeUpload);
				if (scope.fileExtentionErrorLd == false) {
						scope.$apply(function() {modelSetter(scope, element[0].files[0]);});
			}
				break;
				default:
					/*scope.$apply(function() {modelSetter(scope, element[0].files[0]);});*/
				break;
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

/*res.directive('fileModelAs', ['$parse', function ($parse) {
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
            	
            	
            	if (element[0].files[0]) {
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
            	}         	
            });
        }
    };
}]);
*/

/*res.directive('fileModelTs', ['$parse', function ($parse) {
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
            	
            	
            	if (element[0].files[0]) {
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
            	}            	
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
            	
					if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} 
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorDc = true;
					} else {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
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
            	
					if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} 
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorEc = true;
					} else {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
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
            	
					if (fileSize > maxSizeUpload) {						
						scope.maxSizeError = true;
					} 
					if (allowedExtensions.indexOf(fileExtension) < 0) {
						scope.fileExtentionErrorAc = true;
					} else {
						scope.$apply(function() {
							modelSetter(scope, element[0].files[0]);
	            		});
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
            	}            	
            });
        }
    };
}]);*/



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

res.filter('inWords', function() {
	return function(num) {
		var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
		var b = ['', '', 'Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety'];
		
		if(num){
			if ((num = num.toString()).length > 9) return 'overflow';
			n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
			if (!n) return; var str = '';
			str += (n[1] != 0) ? (a[Number(n[1])] || b[n[1][0]] + ' ' + a[n[1][1]]) + 'Crore ' : '';
			str += (n[2] != 0) ? (a[Number(n[2])] || b[n[2][0]] + ' ' + a[n[2][1]]) + 'Lakh ' : '';
			str += (n[3] != 0) ? (a[Number(n[3])] || b[n[3][0]] + ' ' + a[n[3][1]]) + 'Thousand ' : '';
			str += (n[4] != 0) ? (a[Number(n[4])] || b[n[4][0]] + ' ' + a[n[4][1]]) + 'Hundred ' : '';
			str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (a[Number(n[5])] || b[n[5][0]] + ' ' + a[n[5][1]]) + 'only ' : '';
			return str;
		}
		}
});	

res.controller('EEController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout) {
	
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
	$scope.loadWorkNature = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkNature');
		response.success(function(data, status, headers, config) {
			$scope.workNatures = data;
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
			$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.userBean.officeBean.id);
			$scope.loadSubEngineerByOfficeId($scope.workData.userBean.officeBean.id);
			$scope.workData.workLocationLatitude=0;
			$scope.workData.workLocationLongitude=0;
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
	
	$scope.loadSubdivisionalOfficersByOfficeId = function(officeId) {

		$loading.start('sample-1');
		var response = $http.get('fetchSubdivisionalOfficersByOfficeId/'+officeId);
		response.success(function(data, status, headers, config) {
			$scope.subdivisionalOfficers = data;
			
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
	
	$scope.loadBlocksByDistrictNew = function (districtId) {

    $loading.start('sample-1');

    $http.get('fetchBlocksByDistrictNew/' + districtId)
        .success(function (data) {

            $scope.blocks = data;

            // 🔥 IMPORTANT FIX (same as GP & Village)
            if ($scope.workData.blockId) {
                $scope.workData.blockId = Number($scope.workData.blockId);
            }

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
		//if (!tsPdfFile)
			//return false;
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			
			var fd = new FormData();
			
			if($scope.saveAsDraft==true){
				/*$scope.workData.status = 'SaveAsDraft';*/
				$scope.workData.workRequestStatusId=1;
			}
			else if($scope.finalSubmit==true){
				/*$scope.workData.status = 'Active';*/
				$scope.workData.workRequestStatusId=2;
			}
			
			/*fd.append('status', $scope.workData.status);*/
			fd.append('workRequestStatusId', $scope.workData.workRequestStatusId);
			
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
			
			if ($scope.workData.distance) {
				fd.append('distance', $scope.workData.distance);
			}
			if ($scope.workData.completeddistance) {
				fd.append('completeddistance', $scope.workData.completeddistance);
			}
			
			if ($scope.workData.workTypeBean.workTypeId) {
				fd.append('workTypeBean.workTypeId', $scope.workData.workTypeBean.workTypeId);
			}
			
			if (null!=$scope.workData.workSubTypeBean && $scope.workData.workSubTypeBean.workSubTypeId) {
				fd.append('workSubTypeBean.workSubTypeId', $scope.workData.workSubTypeBean.workSubTypeId);
			}
			
			if ($scope.workData.lineDepartmentBean.lineDepartmentId) {
				fd.append('lineDepartmentBean.lineDepartmentId', $scope.workData.lineDepartmentBean.lineDepartmentId);
			}
			
			if ($scope.workData.accountHeadId) {
				fd.append('accountHeadId', $scope.workData.accountHeadId);
			}
			
			
			if ($scope.workData.agencyTypeBean.agencyTypeId) {
				fd.append('agencyTypeBean.agencyTypeId', $scope.workData.agencyTypeBean.agencyTypeId);
			}
			
			if ($scope.workData.contractorBean){
			if ($scope.workData.contractorBean.id) {
				fd.append('contractorBean.id', $scope.workData.contractorBean.id);
			}
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
			
			if ($scope.workData.userBean.officeBean.officeName) {
				fd.append('executiveEngineerOfficeName', $scope.workData.userBean.officeBean.officeName);
			}
			
			
			if ($scope.workData.workStatusBean.workStatusid) {
				fd.append('workStatusBean.workStatusid', $scope.workData.workStatusBean.workStatusid);
			}
			
			if (null!=$scope.workData.physicalStageTypeBean && $scope.workData.physicalStageTypeBean.physicalStageId) {
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
			
			if ($scope.workData.agreementNumber) {
				fd.append('agreementNumber', $scope.workData.agreementNumber);
			}
			
			if ($scope.workData.tenderedRateSign) {
				fd.append('tenderedRateSign', $scope.workData.tenderedRateSign);
			}
			
			if ($scope.workData.tenderedRatePer) {
				fd.append('tenderedRatePer', $scope.workData.tenderedRatePer);
			}
			
			if ($scope.workData.pacAmount) {
				fd.append('pacAmount', $scope.workData.pacAmount);
			}

			if ($scope.workData.tenderCost) {
				fd.append('tenderCost', $scope.workData.tenderCost);
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
			
			if ($scope.workData.userBean.officeBean.parentOffice.id) {
				fd.append('userBean.officeBean.parentOffice.id', $scope.workData.userBean.officeBean.parentOffice.id);
			}
			
			if ($scope.workData.userBean.officeBean.chiefEngineerOfficeId) {
				fd.append('userBean.officeBean.chiefEngineerOfficeId', $scope.workData.userBean.officeBean.chiefEngineerOfficeId);
			}
			
			if ($scope.workData.assistantEngineer){
			if ($scope.workData.assistantEngineer.id) {
				fd.append('assistantEngineer.id', $scope.workData.assistantEngineer.id);
			}
			}
			
			if ($scope.workData.subEngineer){
			if ($scope.workData.subEngineer.id) {
				fd.append('subEngineer.id', $scope.workData.subEngineer.id);
			}
			}
			
			
			
			if ($scope.workData.technicalSanctionBean)
			{
				if ($scope.workData.technicalSanctionBean.technicalSanctionTypeBean){
			if ($scope.workData.technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId) {
				fd.append('technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId', $scope.workData.technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId);
			}
			}
				
				if ($scope.workData.technicalSanctionBean.technicalSanctionNo) {
					fd.append('technicalSanctionBean.technicalSanctionNo', $scope.workData.technicalSanctionBean.technicalSanctionNo);
				}
				
				if ($scope.workData.technicalSanctionBean.technicalSanctionDate) {
					fd.append('technicalSanctionBean.technicalSanctionDate', $scope.workData.technicalSanctionBean.technicalSanctionDate);
				}	
				
				
				
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
			
			if ($scope.workData.administrationSanctionBean.administrationSanctionTypeBean.administrationSanctionTypeId) {
				fd.append('administrationSanctionBean.administrationSanctionTypeBean.administrationSanctionTypeId', $scope.workData.administrationSanctionBean.administrationSanctionTypeBean.administrationSanctionTypeId);
			}
			
			
			if ($scope.workData.administrationSanctionBean.administrationSanctionNo) {
				fd.append('administrationSanctionBean.administrationSanctionNo', $scope.workData.administrationSanctionBean.administrationSanctionNo);
			}
			
			if ($scope.workData.administrationSanctionBean.administrationSanctionDate) {
				fd.append('administrationSanctionBean.administrationSanctionDate', $scope.workData.administrationSanctionBean.administrationSanctionDate);
			}

			if ($scope.workData.contingencyAmount) {
				fd.append('contingencyAmount', $scope.workData.contingencyAmount);
			}
			
			if ($scope.workData.totalCostString) {
				fd.append('totalCostString', $scope.workData.totalCostString);
			}
			
			
			if ($scope.workData.administrationSanctionBean.issuingAuthorityBean){
			if ($scope.workData.administrationSanctionBean.issuingAuthorityBean.issuingAuthorityId) {
				fd.append('administrationSanctionBean.issuingAuthorityBean.issuingAuthorityId', $scope.workData.administrationSanctionBean.issuingAuthorityBean.issuingAuthorityId);
			}
			}
			
			if ($scope.workData.asAuthorityName) {
				fd.append('asAuthorityName', $scope.workData.asAuthorityName);
			}
			
			if ($scope.workData.remarks) {
				fd.append('remarks', $scope.workData.remarks);
			}
			
			if ($scope.workData.probableAmountOfWork) {
				fd.append('probableAmountOfWork', $scope.workData.probableAmountOfWork);
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
	
	/*$scope.loadWorkList = function() {

		$loading.start('sample-1');
		fetchWorkList();
	};*/
	
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
			if($scope.workData.totalExpenditureOnContingencyTill31March2018 == null)
			{
			$scope.workData.totalExpenditureOnContingencyTill31March2018 = 0;
			}
			if($scope.workData.contingencyAmount == null)
			{
			$scope.workData.contingencyAmount= 0;
			}
			$scope.workData.workNatureId = $scope.workData.workNatureId+"";
			$scope.workData.schemeTypeId = $scope.workData.schemeTypeId+"";
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
			
			if($scope.workData.isEstimationRevised != null)
			{
			$scope.workData.isEstimationRevised = $scope.workData.isEstimationRevised+ "";
				
			}
			
			$loading.finish('sample-1');
		});
	};
	
$scope.loadWorkDetailHistoryLegacy = function() {	
		
		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetailsHistoryLegacy/'+$routeParams.workLoggingId);
		
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$scope.workData.workNatureId = $scope.workData.workNatureId+"";
			$scope.workData.schemeTypeId = $scope.workData.schemeTypeId+"";
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
	
	
	$scope.editWorkData = function(isValid, asPdfFile, tsPdfFile, acPdfFile, dcPdfFile, ecPdfFile) {

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
		$scope.compareRevisedTSAmountWithTSAmount($scope.workData.estimatedCostString, $scope.workData.revisedTsAmt);
		$scope.compareRevisedASAmountWithASAmount($scope.workData.totalCostString, $scope.workData.revisedAsAmt);
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			
			var fd = new FormData();
			
			
			if($scope.saveAsDraft==true){
				/*$scope.workData.status = 'SaveAsDraft';*/
				$scope.workData.workRequestStatusId=1;
			}
			else if($scope.finalSubmit==true){
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
			
			if ($scope.specificFieldsEditFlag!=null) {
				fd.append('specificFieldsEditFlag', $scope.specificFieldsEditFlag);
				
				if ($scope.workData.isEstimationRevised) {
					fd.append('isEstimationRevised', $scope.workData.isEstimationRevised);
				}
				
				if ($scope.workData.competentAuthName) {
					fd.append('competentAuthName', $scope.workData.competentAuthName);
				}
				
				if ($scope.workData.competentAuthDesig) {
					fd.append('competentAuthDesig', $scope.workData.competentAuthDesig);
				}
				
				if ($scope.workData.revisedLetterNo) {
					fd.append('revisedLetterNo', $scope.workData.revisedLetterNo);
				}
				if ($scope.workData.letterNoDate) {
					fd.append('letterNoDate', $scope.workData.letterNoDate);
				}
				
				if ($scope.workData.revisedAsAmt) {
					fd.append('revisedAsAmt', $scope.workData.revisedAsAmt);
				}
				if ($scope.workData.revisedTsAmt) {
					fd.append('revisedTsAmt', $scope.workData.revisedTsAmt);
				}
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
		
		
		var  village = $scope.villages.find(x => x.villageId == $scope.workData.villageBean.villageId);
		if(village.latitudeString !== "null" && village.longitudeString !== "null")
			{
		$('#mapLatitude').val(village.latitudeString);
		$('#mapLongitude').val(village.longitudeString);
		
		$('#mapUpdateButton').click();
		
		$('#google-map-popup').modal('show');
	}
	};
	
$scope.onVillageChange2 = function() {
		
		
		
		$('#mapLatitude').val(26.723669);
		$('#mapLongitude').val(78.104375);
		
		$('#mapUpdateButton').click();
		
		$('#google-map-popup').modal('show');
	
	};
	
	
	$scope.loadHistoryWorkLegacy = function() {

		$loading.start('sample-1');
		fetchHistoryWorkLegacy($routeParams.id);
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
/*		$loading.start('sample-1');*/
		/*$scope.workData.workLocationLatitude = $('#mapLatitude').val();
		$scope.workData.workLocationLongitude = $('#mapLongitude').val();*/
		/*var a = $('#mapLatitude').val();
		
		$('#workLocationLatitudeId').val(a);
		
		var b = $('#mapLongitude').val();
		
		$('#workLocationLongitudeId').val(b);*/
		/*$scope.workData.workLocationLatitude = $('#mapLatitude').val();
		$scope.workData.workLocationLongitude = $('#mapLongitude').val();*/
		
		
			$timeout(function() {
				
				$timeout(function() {
					$loading.finish('sample-1');
			    }, 1000)
				
				$loading.start('sample-1');
		    }, 0);
	
			$scope.workData.workLocationLatitude = $('#mapLatitude').val();
			$scope.workData.workLocationLongitude = $('#mapLongitude').val();
		
		/*$loading.finish('sample-1');*/
	
	});

	
	$scope.downloadDocument = function(documentId) {		
			$window.open('downloadDocument/'+documentId);
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
	
	$scope.loadTSIssuingAuthority = function() {
		$loading.start('sample-1');
		var response = $http.get('fetchTSIssuingAuthorityFromDesignationTable');
		response.success(function(data, status, headers, config) {
			$scope.tsIssuingAuthorities = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.checkPercentageValue = function(percentage) {
		if(parseFloat(percentage)<0)
			$scope.workData.tenderedRatePer = 0;
		if(parseFloat(percentage)>100)
			$scope.workData.tenderedRatePer = 100;
	};
	
	$scope.compareDates = function(asDate, tsDate) {
		
		var asDateArr=asDate.split("/");
		var tsDateArr=tsDate.split("/");
		
	
	if(new Date(asDateArr[2], asDateArr[1]-1, asDateArr[0]) < new Date(tsDateArr[2], tsDateArr[1]-1, tsDateArr[0])){
		alert("Administration Sanction Date cannot be less than Technical Sanction Date");
		$scope.workData.administrationSanctionBean.administrationSanctionDate=null;
	}
	
	/*workData.technicalSanctionBean.technicalSanctionDate*/
	
	};
	
	$scope.compareDatesDuringEdit = function(asDate, tsDate) {
		
		var asDateArr=asDate.split("/");
		var tsDateArr=tsDate.split("/");
		
	
	if(new Date(asDateArr[2], asDateArr[1]-1, asDateArr[0]) < new Date(tsDateArr[2], tsDateArr[1]-1, tsDateArr[0])){
		alert("Administration Sanction Date cannot be less than Technical Sanction Date");
		$scope.workData.administrationSanctionDate=null;
	}
		
		/*workData.technicalSanctionBean.technicalSanctionDate*/
		
	};
		
		$scope.addWorkRequisition = function(isValid, ldPdfFile, kmlfile) {

					//$scope.noFileError = (asPdfFile)?false:true;
					/*$scope.fileExtentionErrorAs = (asPdfFile)?false:true;
					$scope.fileExtentionErrorTs = (tsPdfFile)?false:true;
					$scope.fileExtentionErrorAc = (acPdfFile)?false:true;
					$scope.fileExtentionErrorDc = (dcPdfFile)?false:true;
					$scope.fileExtentionErrorEc = (ecPdfFile)?false:true;*/
			
			
			$scope.noFileError = (ldPdfFile)?false:true;
			var maxSizeUpload = 5000000;//in bytes (here 5 MB)
			$scope.fileSizeErrorLd = (ldPdfFile.size > maxSizeUpload)?true:false;
		
			
			
			if($scope.noFileError)
				return false;	
			if($scope.fileSizeErrorLd)
				return false;	
					if (!isValid)
						return false;		
					
					if (confirm("Are you sure you want to save the data?")) {
						$loading.start('sample-1');
						
						var fd = new FormData();
						
						if($scope.saveAsDraft==true){
							$scope.workData.workRequestStatusId = 1;
						}
						else if($scope.finalSubmit==true){
							$scope.workData.workRequestStatusId = 2;
						}
						
						if($scope.workData.isKmlFile==true){
							$scope.workData.isKmlFile = true;
						}else{
							$scope.workData.isKmlFile = false;
						}
						fd.append('isKmlFile', $scope.workData.isKmlFile);
						
						fd.append('workRequestStatusId', $scope.workData.workRequestStatusId);
						
						if(ldPdfFile){
							fd.append('lineDepartmentFile', ldPdfFile);
							}

						/*if ($scope.caseData.caseId) {
							fd.append('caseId', $scope.caseData.caseId);
						}*/
						
						if ($scope.workData.workName) {
							fd.append('workName', $scope.workData.workName);
						}
						
						if ($scope.workData.distance) {
							fd.append('distance', $scope.workData.distance);
						}
						
						if ($scope.workData.workNatureId) {
							fd.append('workNatureId', $scope.workData.workNatureId);
						} 
						
						if ($scope.workData.schemeTypeId) {
							fd.append('schemeTypeId', $scope.workData.schemeTypeId);
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
						
						
						if ($scope.workData.letterNo) {
							fd.append('letterNo', $scope.workData.letterNo);
						}
						
						if ($scope.workData.letterDateString) {
							fd.append('letterDateString', $scope.workData.letterDateString);
						}
						
						if ($scope.workData.accountHeadId) {
							fd.append('accountHeadId', $scope.workData.accountHeadId);
						}

						/*if ($scope.workData.accountHead) {
							fd.append('accountHead', $scope.workData.accountHead);
						}*/
						
						if ($scope.workData.agencyTypeId) {
							fd.append('agencyTypeId', $scope.workData.agencyTypeId);
						}
						
						/*if ($scope.workData.contractorId) {
							fd.append('contractorId', $scope.workData.contractorId);
						}*/
						
						
						if ($scope.workData.userBean.districtBean.districtId) {
							fd.append('districtId', $scope.workData.userBean.districtBean.districtId);
						}
						
						if ($scope.workData.block.blockId) {
							fd.append('blockId', $scope.workData.block.blockId);
						}
						
						if ($scope.workData.gramPanchayatBean.gramPanchayatId) {
							fd.append('gramPanchayatId', $scope.workData.gramPanchayatBean.gramPanchayatId);
						}
						
						if ($scope.workData.villageBean.villageId) {
							fd.append('villageId', $scope.workData.villageBean.villageId);
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
						
						if ($scope.workData.userBean.officeBean.officeName) {
							fd.append('executiveEngineerOfficeName', $scope.workData.userBean.officeBean.officeName);
						}
						
						if ($scope.workData.userBean.officeBean.id) {
							fd.append('officeId', $scope.workData.userBean.officeBean.id);
						}
						
						if ($scope.workData.userBean.officeBean.parentOffice.id) {
							fd.append('parentOfficeId', $scope.workData.userBean.officeBean.parentOffice.id);
						}
						
						if ($scope.workData.userBean.officeBean.chiefEngineerOfficeId) {
							fd.append('chiefOfficeId', $scope.workData.userBean.officeBean.chiefEngineerOfficeId);
						}
						
						if ($scope.workData.assistantEngineer){
						if ($scope.workData.assistantEngineer.id) {
							fd.append('assistantEngineerId', $scope.workData.assistantEngineer.id);
						}
						}
						
						if ($scope.workData.subEngineer){
						if ($scope.workData.subEngineer.id) {
							fd.append('subEngineerId', $scope.workData.subEngineer.id);
							}
						}
						
						if ($scope.workData.subDivisionOfficer){
						if ($scope.workData.subDivisionOfficer.id) {
							fd.append('subDivisionOfficerId', $scope.workData.subDivisionOfficer.id);
							}
						}
						
						if ($scope.workData.remarks){
							
							fd.append('remarks', $scope.workData.remarks);
						
						}
						
						
						//var file2 = $scope.wdtKmlFile;
					//console.log(file); // actual File object
					
					if ($scope.workData.kmlFileId) {
						kmlfile = null;
						fd.append("kmlFile", kmlfile);

					}
					else {
						fd.append("kmlFile", kmlfile);
						/*if (file.size > 1048576) { // 1MB
							alert("File size should not exceed 1 MB.");
							throw new Error("File too large.");
						}*/
					}

				/*	if (!file && !$scope.workData.kmlFileId) {
						alert("Please select a KML file.");
						throw new Error("KML file missing.");
					}
*/

					/* var extension = file.name.split('.').pop().toLowerCase();
					 if (extension !== 'kml') {
						 alert("Only .kml files are allowed.");
						 throw new Error("Invalid file extension.");
					 }*/

					/*  var extension = (file.name && file.name.split('.').pop().toLowerCase()) || '';
					  if (extension !== 'kml') {
						  alert("Only .kml files are allowed.");
						  throw new Error("Invalid file extension.");
					  }*/

						
						$loading.start('sample-1');

						var responsePromise = $http.post('addRequisitionWork', fd, {
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
								$window.location.href = '#manageWorkRequisitionDataRoute';				
							}
							$loading.finish('sample-1');
						});
					}
				};
				
				
				$scope.loadWorkRequisitionDetailInEe = function() {
					
					$scope.workData.block = $scope.workData.block || {};
					$scope.workData.block.blockId = $scope.workData.blockId + "";
					$loading.start('sample-1');
					var response = $http.get('fetchWorkDetails/'+$routeParams.id);
					response.success(function(data, status, headers, config) {
						$scope.workData = data;
						$scope.workData.workNatureId = $scope.workData.workNatureId+"";
						$scope.workData.schemeTypeId = $scope.workData.schemeTypeId+"";
						$scope.workData.workTypeId = $scope.workData.workTypeId+"";
						$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
						$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
						$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
						$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
						if($scope.workData.contractorId)
						$scope.workData.contractorId=$scope.workData.contractorId+"";
						$scope.loadContractors($scope.workData.contractorName);
						
						
						$scope.workData.districtId = $scope.workData.districtId+"";
						$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
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
						
						if($scope.workData.workLocationLatitude==null){
							$scope.workData.workLocationLatitude=0;
						}
						if($scope.workData.workLocationLongitude==null){
							$scope.workData.workLocationLongitude=0;
						}
						 //  Set isKmlFile to true
						 if($scope.workData.kmlFileId!=null ){
				        $scope.workData.isKmlFile = true;
				        }else{
				       		 $scope.workData.isKmlFile = false;
				        }
			
						
						$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
						if($scope.workData.assistantEngineerId == null)
							{
						$scope.workData.assistantEngineerId = "";
							} else {
								$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
							}
						$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.executiveEngineerOfficeId);
						$scope.workData.subDivisionOfficer.id = $scope.workData.subDivisionOfficerId + "";	
							
						
						 
						$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
						if($scope.workData.subEngineerId == null)
						{
							$scope.workData.subEngineerId = "";
						} else {
							$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
						}
						
						
						/*$scope.workData.userBean.officeBean.officeName = $scope.workData.userBean.officeBean.officeName + "";
						$scope.workData.userBean.districtBean.districtName = $scope.workData.userBean.districtBean.districtName + "";
						$scope.workData.userBean.districtBean.districtId = $scope.workData.userBean.districtBean.districtId;
						$scope.loadAssistantEngineerByOfficeId($scope.workData.userBean.officeBean.id);
						$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.userBean.officeBean.id);
						$scope.workData.subDivisionOfficer.id = $scope.workData.subDivisionOfficerId + "";	
						$scope.loadSubEngineerByOfficeId($scope.workData.userBean.officeBean.id);*/
						
						
						
						/*$scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
						
						if($scope.workData.physicalStageId == null)
						{
							$scope.workData.physicalStageId = "";
						} else {
							$scope.workData.physicalStageId = $scope.workData.physicalStageId + "";	
						}
						
						
						
						if($scope.workData.contractorId)
						$scope.workData.contractorId=$scope.workData.contractorId+"";
						
						$scope.workData.workStatusId = $scope.workData.workStatusId+"";
					
						
						$scope.workData.workStatusId = $scope.workData.workStatusId+"";
						
						
						$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
						$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
						if($scope.workData.assistantEngineerId == null)
							{
						$scope.workData.assistantEngineerId = "";
							} else {
								$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
							}
						
						$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.executiveEngineerOfficeId);
						if($scope.workData.subDivisionOfficerId == null)
							{
						$scope.workData.subDivisionOfficerId = "";
							} else {
								$scope.workData.subDivisionOfficer.id = $scope.workData.subDivisionOfficerId + "";	
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
				
				
				
				$scope.editWorkRequisition = function(isValid, ldPdfFile, kmlfile) {

					//$scope.noFileError = (asPdfFile)?false:true;
					/*$scope.fileExtentionErrorAs = (asPdfFile)?false:true;
					$scope.fileExtentionErrorTs = (tsPdfFile)?false:true;
					$scope.fileExtentionErrorAc = (acPdfFile)?false:true;
					$scope.fileExtentionErrorDc = (dcPdfFile)?false:true;
					$scope.fileExtentionErrorEc = (ecPdfFile)?false:true;*/
					$scope.noFileError = (ldPdfFile)?false:true;
					var maxSizeUpload = 5000000;//in bytes (here 5 MB)
					if($scope.noFileError==false)
						{
					$scope.fileSizeErrorLd = (ldPdfFile.size > maxSizeUpload)?true:false;
						}
										
					if($scope.noFileError && $scope.workData.lineDepartmentFileId==0)
						return false;
					if($scope.fileSizeErrorLd)
						return false;
					if (!isValid)
						return false;		
					
					if (confirm("Are you sure you want to save the data?")) {
						$loading.start('sample-1');
						
						var fd = new FormData();
						
						if($scope.saveAsDraft==true){
							$scope.workData.workRequestStatusId = 1;
						}
						else if($scope.finalSubmit==true){
							$scope.workData.workRequestStatusId = 2;
						}
						
						if($scope.workData.isKmlFile==true && $scope.workData.kmlFileId != null){
							$scope.workData.isKmlFile = true;
						}else{
							$scope.workData.isKmlFile = false;
						}
						fd.append('isKmlFile', $scope.workData.isKmlFile);
						
						
						fd.append('workRequestStatusId', $scope.workData.workRequestStatusId);
						
						if(ldPdfFile){
							fd.append('lineDepartmentFile', ldPdfFile);
							}

						/*if ($scope.caseData.caseId) {
							fd.append('caseId', $scope.caseData.caseId);
						}*/
						
						if ($scope.workData.workName) {
							fd.append('workName', $scope.workData.workName);
						}
						
						if ($scope.workData.distance) {
							fd.append('distance', $scope.workData.distance);
						}
						
						if ($scope.workData.workId) {
							fd.append('workId', $scope.workData.workId);
						}
						
						if ($scope.workData.workNatureId) {
							fd.append('workNatureId', $scope.workData.workNatureId);
						}
						
							if ($scope.workData.schemeTypeId) {
							fd.append('schemeTypeId', $scope.workData.schemeTypeId);
						}
						
						if ($scope.workData.workTypeId) {
							fd.append('workTypeId', $scope.workData.workTypeId);
						}
						
						if ($scope.workData.workSubTypeId && $scope.workData.workSubTypeId!="null") {
							fd.append('workSubTypeId', $scope.workData.workSubTypeId);
						}
						
						if ($scope.workData.lineDepartmentId) {
							fd.append('lineDepartmentId', $scope.workData.lineDepartmentId);
						}
						
						
						if ($scope.workData.letterNo) {
							fd.append('letterNo', $scope.workData.letterNo);
						}
						
						if ($scope.workData.letterDateString) {
							fd.append('letterDateString', $scope.workData.letterDateString);
						}
						
						if ($scope.workData.accountHeadId) {
							fd.append('accountHeadId', $scope.workData.accountHeadId);
						}
						
						if ($scope.workData.agencyTypeId) {
							fd.append('agencyTypeId', $scope.workData.agencyTypeId);
						}
						
						/*if ($scope.workData.contractorId) {
							fd.append('contractorId', $scope.workData.contractorId);
						}*/
						
						if ($scope.workData.executiveEngineerOfficeName) {
							fd.append('executiveEngineerOfficeName', $scope.workData.executiveEngineerOfficeName);
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
						
						if ($scope.workData.executiveEngineerOfficeId) {
							fd.append('officeId', $scope.workData.executiveEngineerOfficeId);
						}
						
						/*if ($scope.workData.userBean.officeBean.parentOffice.id) {
							fd.append('parentOfficeId', $scope.workData.userBean.officeBean.parentOffice.id);
						}
						
						if ($scope.workData.userBean.officeBean.chiefEngineerOfficeId) {
							fd.append('chiefOfficeId', $scope.workData.userBean.officeBean.chiefEngineerOfficeId);
						}*/
						
						if ($scope.workData.assistantEngineerId){
						
							fd.append('assistantEngineerId', $scope.workData.assistantEngineerId);
						
						}
						
						if ($scope.workData.subEngineerId){
						
							fd.append('subEngineerId', $scope.workData.subEngineerId);
						
						}
						
						if ($scope.workData.remarks){
							
							fd.append('remarks', $scope.workData.remarks);
						
						}
						
						if ($scope.specificFieldsEditFlag!=null) {
							fd.append('specificFieldsEditFlag', $scope.specificFieldsEditFlag);
						}
						
							
							//var file2 = $scope.wdtKmlFile;
						//console.log(file); // actual File object
						
						if ($scope.workData.kmlFileId !=null && kmlfile==null) {
							fd.append("kmlFileId", $scope.workData.kmlFileId);
					
						}else {
							fd.append("kmlFile", kmlfile);
							/*if (file.size > 1048576) { // 1MB
								alert("File size should not exceed 1 MB.");
								throw new Error("File too large.");
							}*/
						}
	
						$loading.start('sample-1');

						var responsePromise = $http.post('editRequisitionWork', fd, {
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
								$window.location.href = '#manageWorkRequisitionDataRoute';				
							}
							$loading.finish('sample-1');
						});
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
				
				/*$scope.showHideTenderDetails = function(flag) {
					
					if(flag==2 || flag==3)
						{
						$scope.IsVisible = false;
						}
					if(flag==1){
						$scope.IsVisible = true;
					}
				};*/
				
				/*$scope.changeActionType =function(type){
					$scope.submit=type;
				}*/
				
				$scope.addTechnicalSanction = function(workFormName,isValid, ldPdfFile) {
					
					
					if (!isValid)
						return false;		
					
					if (confirm("Are you sure you want to save the data?")) {
						
						$loading.start('sample-1');
						
						
						if($scope.saveAsDraft == true){
							 
							$scope.workRequestStatusId = 1;
						}
						else if($scope.submit == true){
							 
							$scope.workRequestStatusId =  4;
						}
						
						var fd = new FormData();
						
						fd.append('technicalSanctionBean.workRequestStatusId', $scope.workData.workRequestStatusId);
						
						if ($scope.workData.isCeOfficeName) {
							fd.append('technicalSanctionBean.isCeOfficeName', $scope.workData.isCeOfficeName);
						}
						if ($scope.workData.isEeOfficeName) {
							fd.append('technicalSanctionBean.isEeOfficeName', $scope.workData.isEeOfficeName);
						}
						
						
						
						
						
						if ($scope.workData.isSeOfficeName) {
							fd.append('technicalSanctionBean.isSeOfficeName', $scope.workData.isSeOfficeName);
						}
						
						if ($scope.workData.isDistrictName) {
							fd.append('technicalSanctionBean.isDistrictName', $scope.workData.isDistrictName);
						}
						
						if ($scope.workData.isGrampanchayatName) {
							fd.append('technicalSanctionBean.isGrampanchayatName', $scope.workData.isGrampanchayatName);
						}
						if ($scope.workData.isLinedepartmentName) {
							fd.append('technicalSanctionBean.isLinedepartmentName', $scope.workData.isLinedepartmentName);
						}
						
						
						
						
						
						

						if ($scope.workData.workId) {
							fd.append('workId', $scope.workData.workId);
						}
						
						fd.append('workRequestStatusId', $scope.workRequestStatusId);
						
						if(ldPdfFile){
							fd.append('technicalSanctionFile', ldPdfFile);
						}
						
						if ($scope.workData.technicalSanctionNo) {
							fd.append('technicalSanctionNo', $scope.workData.technicalSanctionNo);
						}
						
						if ($scope.workData.tsDispatchNumber) {
							fd.append('tsDispatchNumber', $scope.workData.tsDispatchNumber);
						}
						
						
						if ($scope.workData.technicalSanctionDate) {
							fd.append('technicalSanctionDate', $scope.workData.technicalSanctionDate);
						}
						
						$loading.start('sample-1');

						var responsePromise = $http.post('addTechnicalSanction', fd, {
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
								$window.location.href = '#manageTechnicalSanctionDataRoute';
							}
							$loading.finish('sample-1');
						});
						 
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
				
			$scope.compareBothLength = function(largeAmount, smallAmount) {
					
					if(largeAmount!=null && smallAmount!=null){
				if(parseFloat(largeAmount)<parseFloat(smallAmount)){
					alert("Completed length  is always less than or equal to Sanctioned length ! ");
					$scope.workData.distance=null;
					$scope.workData.completeddistance=null;
				}
					}
				
				/*workData.technicalSanctionBean.technicalSanctionDate*/
				
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
					
					
					
					$scope.loadGramPanchayat = function() {

						$loading.start('sample-1');
						var response = $http.get('fetchGramPanchayat');
						response.success(function(data, status, headers, config) {
							$scope.agencyTypes = data;
							$loading.finish('sample-1');
						});
					};
				
					
					$scope.loadWorkRequisitionDetailInSube = function() {

						$loading.start('sample-1');
						var response = $http.get('fetchWorkDetails/'+$routeParams.id);
						response.success(function(data, status, headers, config) {
							$scope.workData = data;
							$scope.workData.workNatureId = $scope.workData.workNatureId+"";
							$scope.workData.workTypeId = $scope.workData.workTypeId+"";
							$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
							$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
							$scope.workData.lineDepartmentId = $scope.workData.lineDepartmentId+"";
							if($scope.workData.agencyTypeId)
								{
								if($scope.workData.agencyTypeId==2)
									{
							$scope.workData.agencyTypeId = $scope.workData.agencyTypeId+"";
									}
								else {
									$scope.workData.agencyTypeId = null;
								}
								}
							$scope.workData.districtId = $scope.workData.districtId+"";
							$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
							$scope.loadBlocksByDistrictNew($scope.workData.districtId);
							$scope.workData.blockId = $scope.workData.blockId+"";
							$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
							$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
							$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
							if($scope.workData.villageId!=null)
							$scope.workData.villageId = $scope.workData.villageId+"";
							$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
							
							if($scope.workData.workLocationLatitude==null){
								$scope.workData.workLocationLatitude=0;
							}
							if($scope.workData.workLocationLongitude==null){
								$scope.workData.workLocationLongitude=0;
							}
							
							
							$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
							if($scope.workData.assistantEngineerId == null)
								{
							$scope.workData.assistantEngineerId = "";
								} else {
									$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
								}
							
							$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.executiveEngineerOfficeId);
							if($scope.workData.subDivisionOfficerId == null)
							{
						$scope.workData.subDivisionOfficerId = "";
							} else {
								$scope.workData.subDivisionOfficer.id = $scope.workData.subDivisionOfficerId + "";	
							}
							$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
							if($scope.workData.subEngineerId == null)
							{
								$scope.workData.subEngineerId = "";
							} else {
								$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
							}
							
							
							/*$scope.workData.userBean.officeBean.officeName = $scope.workData.userBean.officeBean.officeName + "";
							$scope.workData.userBean.districtBean.districtName = $scope.workData.userBean.districtBean.districtName + "";
							$scope.workData.userBean.districtBean.districtId = $scope.workData.userBean.districtBean.districtId;
							$scope.loadAssistantEngineerByOfficeId($scope.workData.userBean.officeBean.id);
							$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.userBean.officeBean.id);
							$scope.loadSubEngineerByOfficeId($scope.workData.userBean.officeBean.id);*/
							
							
							
							/*$scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
							
							if($scope.workData.physicalStageId == null)
							{
								$scope.workData.physicalStageId = "";
							} else {
								$scope.workData.physicalStageId = $scope.workData.physicalStageId + "";	
							}
							
							
							
							if($scope.workData.contractorId)
							$scope.workData.contractorId=$scope.workData.contractorId+"";
							
							$scope.workData.workStatusId = $scope.workData.workStatusId+"";
						
							
							$scope.workData.workStatusId = $scope.workData.workStatusId+"";
							
							
							$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
							$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
							if($scope.workData.assistantEngineerId == null)
								{
							$scope.workData.assistantEngineerId = "";
								} else {
									$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
								}
						
								$scope.loadSubdivisionalOfficersByOfficeId($scope.workData.executiveEngineerOfficeId);
							if($scope.workData.subDivisionOfficerId == null)
							{
						$scope.workData.subDivisionOfficerId = "";
							} else {
								$scope.workData.subDivisionOfficer.id = $scope.workData.subDivisionOfficerId + "";	
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
				
				
					$scope.loadTechnicalDetail = function() {
						$loading.start('sample-1');
						var response = $http.get('fetchTechnicalDetailsByWorkId/'+$routeParams.id);
						response.success(function(data, status, headers, config) {
							$scope.technicalData = data;
							$loading.finish('sample-1');
						});
					};		
					
					
					$scope.calculatePawAmount = function(asAmount, contingencyAmount, tenderedRatePer, tenderedRateSign) {
						
						var paw =null;
						
						if(asAmount!=null&&contingencyAmount!=null&&tenderedRatePer!=null&&tenderedRateSign!=null)
						{
						if(tenderedRateSign=="+")
							{
							paw = parseFloat(asAmount) - parseFloat(contingencyAmount);
							$scope.workData.probableAmountOfWork = paw.toFixed( 2 );
							}
						else if (tenderedRateSign=="-") {
							paw = ((parseFloat(asAmount) - parseFloat(contingencyAmount))- ((parseFloat(asAmount) - parseFloat(contingencyAmount))*(parseFloat(tenderedRatePer)/100)));
							$scope.workData.probableAmountOfWork = paw.toFixed( 2 );
						} else {
							$scope.workData.probableAmountOfWork = null;
						}
						}
						
						};
						
	

	$scope.loadBudgetRequestDetailList = function() {
		
		var responsePromise = $http.get('fetchBudgetRequest/'+$routeParams.id);
		responsePromise.success(function(data, status, headers, config) {
			$scope.budgetRequestBean = data;
		});
		
		$loading.start('sample-1');
	    fetchBudgetRequestDetailList($routeParams.id);
	    
	    
	};	
	
	$scope.loadWorkListForBuget = function() {
		$loading.start('sample-1');
		fetchWorkListForBuget();
	};	
	

	$scope.reloadJqueryDatatable = function(){
		$loading.start('sample-1');
			reDraw();
			$loading.finish('sample-1');
		
	};
	
	$scope.loadBudgetRequestList = function() {
		$loading.start('sample-1');
		fetchBudgetRequestList();
	};	
	
	
	$scope.loadBudgetRequest = function() {
		$loading.start('sample-1');
		fetchBudgetRequest();
		
	};	
	
	$scope.saveBudgetRequest = function(isValid) {
		
		if (!isValid) 
			return false;
		
		var isNoneChecked = true; 
		 
		for (var i = 0; i < $('input[name^="selectbox"]').length; i++) {
			if ($('input[name^="selectbox"]')[i].checked == true) {
				if($('input[name^="requestedAmount"]')[i].value =='' || $('input[name^="requestedAmount"]')[i].value <=0) {
					
					alert("Please Enter amount, which is greate than Zero.");
					return false;
				}
				
				isNoneChecked = false;
				
				
			}
		}
		
		if (isNoneChecked) {
			alert("Please select atleast one check box to continue.");
			return false;
		}
		
		$scope.budgetRequestBean = {};
		$scope.budgetRequestBean.budgetRequestDetailBeanList = [];	
		
		for (var i = 0; i < $('input[name^="workId"]').length; i++) {
			if($('input[name^="selectbox"]')[i].checked == true ) {
				var data = {workId : $('input[name^="workId"]')[i].value, requestedAmount : $('input[name^="requestedAmount"]')[i].value, remainingAmountTotal : $('input[name^="remainingAmountTotal"]')[i].value, accountHeadNameE : $('input[name^="accountHeadNameE"]')[i].value};
				$scope.budgetRequestBean.budgetRequestDetailBeanList.push(data);	
			}
		}
		
		if (confirm("Are you sure you want to save the data?")) {
			
			if($scope.saveAsDraft == true) {				 
				$scope.budgetRequestBean.statusId = 1;
			}
			else if($scope.submit == true) {
				$scope.budgetRequestBean.statusId = 2;
			}
			
			$loading.start('sample-1');
			 
			var responsePromise = $http.post('saveBudgetRequest', $scope.budgetRequestBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#budgetRequestList';
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
			});
		} else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
	}
	
	
	$scope.saveEditBudgetRequest = function(isValid) {
		
		if (!isValid) 
			return false;
		
		$scope.budgetRequestBean = {};
		$scope.budgetRequestBean.budgetRequestDetailBeanList = [];	
		$scope.budgetRequestBean.id = $routeParams.id;
		
		for (var i = 0; i < $('input[name^="workId"]').length; i++) {	

			var data = {workId : $('input[name^="workId"]')[i].value, requestedAmount : $('input[name^="requestedAmount"]')[i].value,
					id : $('input[name^="id"]')[i].value, remainingAmountTotal : $('input[name^="remainingAmountTotal"]')[i].value};
			$scope.budgetRequestBean.budgetRequestDetailBeanList.push(data);	
		}
		
		if (confirm("Are you sure you want to save the data?")) {
			
			if($scope.saveAsDraft == true) {				 
				$scope.budgetRequestBean.statusId = 1;
			}
			else if($scope.submit == true) {
				$scope.budgetRequestBean.statusId = 2;
			}
			
			$loading.start('sample-1');
			 
			var responsePromise = $http.post('saveEditBudgetRequest', $scope.budgetRequestBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#budgetRequestList';
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
			});
		} else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
	}
	
	
$scope.saveSurrenderRequest = function(isValid) {
		
		if (!isValid) 
			return false;
		
		$scope.budgetRequestBean = {};
		$scope.budgetRequestBean.budgetRequestDetailBeanList = [];	
		$scope.budgetRequestBean.id = $routeParams.id;
		
		for (var i = 0; i < $('input[name^="workId"]').length; i++) {	

			var data = {workId : $('input[name^="workId"]')[i].value, surrendedAmount : $('input[name^="surrendedAmount"]')[i].value,
					id : $('input[name^="id"]')[i].value, approvedAmount : $('input[name^="approvedAmount"]')[i].value};
			$scope.budgetRequestBean.budgetRequestDetailBeanList.push(data);	
		}
		
		//for 
		var isEmpty=false;
		for (var i = 0; i < $('input[name^="workId"]').length; i++) {	

		/*alert($('input[name^="surrendedAmount"]')[i].value);*/
		if( $('input[name^="surrendedAmount"]')[i].value==""){
			/*alert($('input[name^="surrendedAmount"]')[i].value);*/	
		}
		else{
			isEmpty=true;
		}
		
}
		
		if(!isEmpty){
			alert("Please Enter Atleast One Surrender Amount!");
			return false;
		}
		
		if (confirm("Are you sure you want to save the data?")) {
			
		/*	if($scope.saveAsDraft == true) {				 
				$scope.budgetRequestBean.statusId = 1;
			}
			else if($scope.submit == true) {
				$scope.budgetRequestBean.statusId = 2;
			}
			*/
			$loading.start('sample-1');
			 
			var responsePromise = $http.post('saveSurrenderRequest', $scope.budgetRequestBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					
					$window.location.href = '#budgetRequestList';
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
			});
		} 
	}
	
	
	
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
	$scope.compareRevisedTSAmountWithTSAmount = function(tsAmount, revisedTsAmount) {
		
		if(tsAmount!=null && revisedTsAmount!=null){
		if(parseFloat(revisedTsAmount)<parseFloat(tsAmount)){
			alert("Revised Technical Sanction Amount must be greater than or equal to Technical Sanction Amount.");
			$scope.workData.revisedTsAmt=null;
			return false;
			}
		  }
		};
	$scope.compareRevisedASAmountWithASAmount = function(asAmount, revisedAsAmount) {
		
		if(asAmount!=null && revisedAsAmount!=null){
		if(parseFloat(revisedAsAmount)<parseFloat(asAmount)){
			alert("Revised Admistrative Sanction Amount must be greater than or equal to Administrative Sanction Amount.");
			$scope.workData.revisedAsAmt=null;
			return false;
			}
		  }
		};
	
		var map = null;
	$scope.mapOnInit = function() {
		map = mapVariable();

	};
	
	$scope.gpcodeList = [];
	$scope.KmlData = {};
	$scope.forestrange = false;
	$scope.processKmlFile = async function() {

		if (!$scope.KmlData.wdtKmlFile) {
			alert("Please select a KML file.");
			return;
		}

		if ($scope.KmlData.wdtKmlFile.size >= 1048576) {
			alert("File size should not exceed 1 MB.");
			return;
		}

		var file = $scope.KmlData.wdtKmlFile;
		var fileName = file.name;
		var fileExtension = fileName.split('.').pop().toLowerCase();

		if (fileExtension !== 'kml') {
			alert("Only .kml files are allowed.");
			return;
		}

		var formData = new FormData();
		formData.append('KmlFile', file);
		formData.append('districtId', $scope.workData.userBean.districtBean.districtId);
		

		try {
			const response = await $http.post('processKmlFile', formData, {
				transformRequest: angular.identity,
				headers: { 'Content-Type': undefined }
			});
			//$loading.start('sample-1');
			$rootScope.responseObject = response.data;

		//	$scope.workData.gramPanchayatId = response.data[0].gpCodeList[0] + "";
		//	$scope.workData.gramPanchayatId += ""
		//	$scope.loadVillagesByProject($scope.workData.projectId, $scope.workData.gramPanchayatId)
			for (let i = 0; i < response.data.length; i++) {
				const lat = Number(response.data[i].lattitude).toFixed(6);
				const lon = Number(response.data[i].longitude).toFixed(6);
				
				   if ($scope.KmlData.wdtKmlFile) {
        // Call your KML parsing logic here
        // ...

        // âœ… Set isKmlFile to true
        $scope.workData.isKmlFile = true;
			
			    } else {
			        // Optional: reset if no file is selected
			        $scope.workData.isKmlFile = false;
			    }			var templat = lat;
							var templong = lon;
				
							
				try {
					const res = await $http.post('getLatLng/' + lat + '/' + lon +'/');
					const attrList = res.data.AttributeList;
					if(attrList!=null){
					//const districtDetail = await $http.get('getDisctrictDetailByLgdDistrictCode/' + attrList[0].district_id);
					
					if (parseInt($scope.workData.userBean.districtBean.lgdDistrictCode) !== parseInt(attrList[0].district_id)) {
					alert("You Cannot Select Location Outside " +$scope.workData.userBean.districtBean.districtNameH + " District");
					return;
						$loading.finish('sample-1');
				
					}
					
					
					if (attrList && attrList.length > 0) {
						const khasra = attrList[0].khasra_number;

						console.log("Khasra at index", i, ":", khasra);

						if (khasra && khasra.trim() !== "") {
							console.log("âœ… Found khasra number:", khasra);
							$scope.getLatLngFrommap(lat, lon);
							lonGAPI = lon
							latapi = lat
							break;
						}
						else{
							let latFixed = Number(lat).toFixed(6);
							let lonFixed = Number(lon).toFixed(6);

				
							$scope.getLatLngforest(latFixed, lonFixed);
							if($scope.workData.latitude != '' && $scope.workData.longitude!= '')							
															 
							{
								geojsonLayer = null;
								lonGAPI = lonFixed
									$scope.forestrange  = true;			
																	
								latapi = latFixed
								break;
							}
						  }
						  $scope.workData.workLocationLatitude = $scope.lat + "";
							$scope.workData.workLocationLongitude = $scope.lon + "";
						}
						$loading.finish('sample-1');
					}
			
				} catch (innerErr) {
					console.error("Error calling getLatLng for:", lat, lon, innerErr);
					$loading.finish('sample-1');
				}
			}

		} catch (err) {
			console.error("Error uploading/processKmlFile:", err);
			alert("Error processing KML file.");
			$loading.finish('sample-1');
		}

		
	};
$scope.getLatLngforest = function(templat, templong) {
		//	alert(typeof(templat)+" "+ typeof(templong))
		$http.post('getLatLngforest/' + templat + '/' + templong)
			.then(function(response) {

				let responseData = response.data;
				console.log("Response Data:", responseData);
				$scope.workData.khasraNo = responseData.range_id;
				//var khasraGeom = JSON.parse(responseData.AttributeList[0].khasra_geom);
				//		var coordinates = khasraGeom.geometry.coordinates[0][0];
				//alert("latitude"+$scope.lat);
				$scope.workData.latitude = templat;
				$scope.workData.longitude = templong;



			}, function(error) {

				console.error("Error:", error);
			});
	};
$scope.workData = {
	userBean: {
		districtBean: null
	},
	block: {},
	gramPanchayatBean: {},
	 villageBean: {
        villageId: null
    }
};

$scope.pointData = {};
$scope.blocks = [];
$scope.gramPanchayats = [];
$scope.villages = [];

$scope.workData = $scope.workData || {};
$scope.workData.block = $scope.workData.block || {};
$scope.workData.gramPanchayatBean = $scope.workData.gramPanchayatBean || {};
$scope.workData.userBean = $scope.workData.userBean || {};
$scope.workData.userBean.districtBean = $scope.workData.userBean.districtBean || {};
$scope.workData.villageBean = $scope.workData.villageBean || {};

$scope.getLatLngFrommap = function (templat, templong) {

    console.log("Latitude:", templat);
    console.log("Longitude:", templong);

    $http.post('getLatLng/' + templat + '/' + templong + '/')
        .then(function (response) {

            let responseData = response.data;

            console.log("Full API response:", responseData);
            console.log("AttributeList:", responseData.AttributeList);

            if (!responseData.AttributeList || responseData.AttributeList.length === 0) {
                console.error("No GIS data found for this coordinate");
                return;
            }

            var attr = responseData.AttributeList[0];

            console.log("District:", attr.district_nm);
            console.log("Block:", attr.block_nm);
            console.log("Village:", attr.village_nm);
            console.log("Area Type:", attr.area_type);
            console.log("GP ID:", attr.gp_id);

            $scope.pointData = responseData;

            /* ---------------- Safe Object Initialization ---------------- */

            $scope.workData = $scope.workData || {};
            $scope.workData.block = $scope.workData.block || {};
            $scope.workData.gramPanchayatBean = $scope.workData.gramPanchayatBean || {};
            $scope.workData.userBean = $scope.workData.userBean || {};
            $scope.workData.userBean.districtBean = $scope.workData.userBean.districtBean || {};
            $scope.workData.villageBean = $scope.workData.villageBean || {};

            /* ---------------- District ---------------- */

            $scope.workData.userBean.districtBean.districtId = attr.district_id + "";
            $scope.workData.userBean.districtBean.districtNameH = attr.district_nm;

            console.log("District assigned:", $scope.workData.userBean.districtBean);

            $http.get('getDisctrictDetailByLgdDistrictCode/' + attr.district_id)
                .then(function (res) {

                    console.log("District detail API:", res.data);

                    var district = res.data;

                    $scope.workData.userBean.districtBean.districtId = district.districtId + "";
                    $scope.workData.userBean.districtBean.districtNameH = district.districtNameH;

                })
                .catch(function (err) {

                    console.error("District API error:", err);

                });

            /* ---------------- Block ---------------- */

            $scope.blocks = [{
                blockId: attr.block_id,
                blockName: attr.block_nm
            }];

            console.log("Block List:", $scope.blocks);

            if (attr.block_id) {

                $scope.workData.block.blockId = attr.block_id + "";
                $scope.workData.blockId = attr.block_id + "";

                console.log("Block assigned:", $scope.workData.block.blockId);

            } else {

                console.warn("Block ID not found in GIS response");

            }

            /* ---------------- Gram Panchayat ---------------- */

            $scope.gramPanchayats = [];

            if (attr.area_type === "RURAL" && attr.gp_id) {

                $scope.gramPanchayats = [{
                    gramPanchayatId: attr.gp_id,
                    gpName: attr.gp_nm
                }];

                console.log("Gram Panchayat List:", $scope.gramPanchayats);

                $scope.workData.gramPanchayatBean.gramPanchayatId = attr.gp_id + "";
                $scope.workData.gramPanchayatId = attr.gp_id + "";

                var gpcode = attr.gp_id;

                console.log("Calling GP API with:", gpcode);

                $http.get('fetchGramPanchayatByGPCode/' + gpcode)
                    .then(function (res) {

                        console.log("GP API response:", res.data);

                        $scope.gramPanchayat = res.data;

                    })
                    .catch(function (err) {

                        console.error("GP API error:", err);

                    });

            } else {

                console.log("Urban Area detected. Gram Panchayat not available.");

                $scope.gramPanchayats = [];

            }

            /* ---------------- Village ---------------- */

            if (attr.village_id) {

                $scope.villages = [{
                    villageId: attr.village_id,
                    villageName: attr.village_nm
                }];

                console.log("Village List:", $scope.villages);

                $scope.workData.villageBean.villageId = attr.village_id + "";
                $scope.workData.villageId = attr.village_id + "";

                var vCode = attr.village_id;

                console.log("Calling Village API with:", vCode);

                $http.get('fetchVillageByVCode/' + vCode)
                    .then(function (res) {

                        console.log("Village API response:", res.data);

                        $scope.village = res.data;

                    })
                    .catch(function (err) {

                        console.error("Village API error:", err);

                    });

            }

            /* ---------------- Khasra ---------------- */

            $scope.khasra_number = attr.khasra_number;
            $scope.workData.khasraNo = attr.khasra_number;

            console.log("Khasra Number:", attr.khasra_number);

            /* ---------------- Map Geometry ---------------- */

            var khasraGeom = JSON.parse(attr.khasra_geom);

            var coordinates = khasraGeom.geometry.coordinates[0][0];

            $scope.lat = coordinates[0][1];
            $scope.lon = coordinates[0][0];

            console.log("Work Latitude:", $scope.lat);
            console.log("Work Longitude:", $scope.lon);

            $scope.workData.workLocationLatitude = $scope.lat + "";
            $scope.workData.workLocationLongitude = $scope.lon + "";

            /* ---------------- Map Layer ---------------- */

            if (geojsonLayer != null) {

                map.removeLayer(geojsonLayer);
                geojsonLayer = null;

            }

            geojsonLayer = L.geoJson(JSON.parse(attr.khasra_geom), {
                style: {
                    color: "#FFFF00",
                    weight: 3,
                    fillColor: "#FFFF00",
                    fillOpacity: 0.1
                }
            });

            geojsonLayer.addTo(map);
            map.fitBounds(geojsonLayer.getBounds());

        })
        .catch(function (error) {

            console.error("getLatLng API error:", error);

        });
};



	/*let geojsonLayer;
	var marker=null;
	var finallat;
	var finallong;
	var mapclk=0;*/
	/*$scope.onchangeDistrict = function() {
		console.log($scope.selectedDistrict.district_id + "districtId")
		$http.post('fetchGeojson/' + $scope.selectedDistrict.district_id)
			.then(function(response) {
				//alert("ssh");
				let data = response.data;
				console.log("data", data);
				// let geojson = JSON.parse(data.data[0].admingeojsoninfo).features[0].properties;
				//console.log(geojson);
				//console.log(propertiesValue.admincode);
				//console.log(propertiesValue.admintype);
				//console.log(propertiesValue.adminname);
				//console.log(propertiesValue.lng);
				//console.log(propertiesValue.lat); 
				let geojsonFeature = JSON.parse(data.data[0].admingeojsoninfo).features[0]; // Get the entire GeoJSON feature

			console.log("geojsonFeature"+geojsonFeature);
				// Create a GeoJSON layer with the entire feature
				if (geojsonLayer != null) {
					map.removeLayer(geojsonLayer);
					geojsonLayer = null;
				}
				geojsonLayer = L.geoJson(geojsonFeature, {
					style: { color: "#FFFF00", weight: 3, fillColor: "#FFFF00", fillOpacity: 0.1 }
				});
				   
				// Add the GeoJSON layer to the map
				if (geojsonLayer) {
					// Add geojsonLayer to the map
					geojsonLayer.addTo(map);

					// Fit the map to the bounds of the GeoJSON layer
					map.fitBounds(geojsonLayer.getBounds());
				} else {
					console.log("geojsonLayer is not defined or null");
				}
			});
	}*/
	$scope.onchangeBlock = function(blockId) {
		// var blockId = $scope.workData.blockId;
		$http.get('fetchblockCode/' + blockId)

			.then(function(response) {
				if (response.data && response.data.blockCode) {
					$scope.block = response.data; // Use the actual response data
					console.log("BlockLatLong", $scope.block);
					return $http.post('fetchBlockGeojson/' + $scope.block.blockCode);

				} else {
					throw new Error("Invalid block data received");
				}
			})
			.then(function(response) {
				let data = response.data;
				if (data && data.data && data.data[0] && data.data[0].admingeojsoninfo) {
					let geojsonFeature = JSON.parse(data.data[0].admingeojsoninfo).features[0]; // Get the entire GeoJSON feature
					console.log("geojsonFeature", geojsonFeature);

					// Remove the existing GeoJSON layer if it exists
					if (typeof geojsonLayer !== 'undefined' && geojsonLayer !== null) {
						map.removeLayer(geojsonLayer);
						geojsonLayer = null;
					}

					// Create a new GeoJSON layer
					geojsonLayer = L.geoJson(geojsonFeature, {
						style: {
							color: "#FFFF00",
							weight: 3,
							fillColor: "#FFFF00",
							fillOpacity: 0.1
						}
					});

					// Add the GeoJSON layer to the map and fit the bounds
					if (geojsonLayer) {
						geojsonLayer.addTo(map);
						map.fitBounds(geojsonLayer.getBounds());
					} else {
						console.log("geojsonLayer is not defined or null");
					}
				} else {
					throw new Error("Invalid GeoJSON data received");
				}
			})
			.catch(function(error) {
				console.error("Error fetching GeoJSON data:", error);
			});
	}


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

	
	$scope.loadDistrictAndBlock = function() {
		//alert("hello");
		$http.get('workCreation')
			.then(function(response) {
				//alert("rtyu");
				//  alert("response"+JSON.stringify(response));
				$scope.workData.districtId = response.data.userDistrictId;
				//  alert(abc22);
				$scope.workData.blockId = response.data.blockId;
				$scope.districts = response.data.districts;
				$scope.blocks = response.data.blocks;
			}, function(error) {
				console.error('Error fetching data:', error);
			});
	};
	function sortByName(field) {
    return function (a, b) {
        return (a[field] || '').localeCompare(b[field] || '');
    };
}

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
	

	$scope.loadDistrictOfLoggedInUser = function() {
		$http.get('fetchDistrictOfLoggedInUser')
			.then(function(response) {
				//alert("rtyu");
				//alert("response"+JSON.stringify(response));
				$scope.user_district = response.data;
				$scope.workData.userBean.districtBean.districtId = $scope.user_district[0].districtId + "";
				$http.get('fetchDistrictOfLoggedInUser')
					.then(function(response) {
						$scope.district = response.data;
						console.log("districtGisCode" + $scope.district[0].districtGisCode);
						$scope.districtGisCode = $scope.district[0].districtGisCode;
						//alert("districtGisCode"+$scope.districtGisCode);

					})
				//$scope.loadProjectOfDistrcit($scope.workData.userBean.districtBean.districtId);
				//$scope.onchangeDistrict($scope.workData.userBean.districtBean.districtId);
				$scope.loadBlockByDistrictID($scope.workData.userBean.districtBean.districtId);
				$scope.loadBlockOfLoggedInUser();
			}, function(error) {
				console.error('Error fetching data:', error);
			});


	}

	$scope.loadBlockOfLoggedInUser = function () {
    $http.get('fetchBlockOfLoggedInUser')
        .then(function (res) {
            $scope.block_new = res.data;
            $scope.workData.blockId = String(res.data.blockId);

            return $http.get('fetchblockCode/' + $scope.workData.blockId);
        })
        .then(function (res) {
            $scope.block = res.data;
            $scope.loadGramPanchayatByBlockId($scope.workData.blockId);
            $scope.loadGramPanchayatByBlockIdfromOffice($scope.workData.blockId);

            if (!$scope.workData.workId) {
                $scope.onchangeBlock($scope.workData.blockId);
            }
        })
        .catch(function (err) {
            console.error('Block load error', err);
        });
};

	
	$scope.loadBlockByDistrictID = function (districtId) {
    if (!districtId) return;

    $loading.start('sample-1');

    $http.get('fetchDistrict/' + districtId)
        .then(function (res) {
            $scope.district = res.data;
            return $http.get('fetchBlocksByDistrict/' + $scope.district.districtCode);
        })
        .then(function (res) {
            $scope.blocks = res.data.sort(sortByName('blockName'));
        })
        .catch(function (err) {
            console.error('Error loading blocks', err);
        })
        .finally(function () {
            $loading.finish('sample-1');
        });
};

	
	$scope.loadGramPanchayatByBlockIdfromOffice = function (blockId) {
    if (!blockId) return;

    $http.get('fetchGpIdByBlockoffice/' + blockId)
        .then(function (res) {
            $scope.officeGramPanchayat = res.data;
        })
        .catch(function (err) {
            console.error('Office GP error', err);
        });
};

	$scope.loadGramPanchayatByBlockId = function (blockId) {
    if (!blockId) return;

    $loading.start('sample-1');

    $http.get('fetchBlockById/' + blockId)
        .then(function (res) {
            return $http.get('fetchGramPanchyatByBlock/' + res.data.blockCode);
        })
        .then(function (res) {
            $scope.gramPanchayats = res.data.sort(sortByName('gramPanchayatName'));
        })
        .catch(function (err) {
            console.error('GP load error', err);
        })
        .finally(function () {
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
	$scope.downloadUploadDocument = function(documentId) {
	
		console.log(" downloadDocument =" + documentId);
		$window.open('downloadUploadDocument/' + documentId);
	};
	
	$scope.loadSchemes = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchSchemes');
		response.success(function(data, status, headers, config) {
			$scope.schemes = data;
			$loading.finish('sample-1');
		});
	};

	$scope.loadSchemesByDesignation = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchSchemesByDesignation');
		response.success(function(data, status, headers, config) {
			$scope.schemes = data;
			$loading.finish('sample-1');
		});
	};
});