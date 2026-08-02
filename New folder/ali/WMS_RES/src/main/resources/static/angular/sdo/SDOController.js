var res = angular.module('res');


res.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {        	
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            var maxSizeUpload = 2000000;//in bytes (here 2 MB)
            var allowedExtensions = ['pdf', 'PDF'];
            
            element.bind('change', function() {
//            	scope.noFileError = false;
//            	scope.maxSizeError = false;
            /*	scope.fileExtentionErrorAs = false;    */	        	
            	var fileExtension = element[0].files[0].name.substring(element[0].files[0].name.lastIndexOf('.') + 1); 
            
            	switch (attrs.fileModel) {
				case "asPdfFile":
					/*scope.innovativeStartupDoc.maxSizeError = (fileSize > maxSizeUpload);*/
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
					scope.fileExtentionErrorLd = (allowedExtensions.indexOf(fileExtension) < 0);							
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
	
	$scope.loadGramPanchayatByBlockCode = function(blockCode) {

		$loading.start('sample-1');
//		$scope.entrepreneurData.registeredAddress.blockId = "";
		var response = $http.get('fetchGramPanchayatByBlockCode/'+blockCode);
		response.success(function(data, status, headers, config) {
			$scope.gramPanchayats = data;
			$scope.villages='';
//			$scope.workData.gramPanchayatBean.gpCode = $scope.gramPanchayats.gramPanchayatBean.gpCode+"";
			$loading.finish('sample-1');
		});
	};
	

	$scope.loadVillageByGramPanchayatCode = function(gramPanchayatCode) {		
		$loading.start('sample-1');
//		$scope.entrepreneurData.registeredAddress.blockId = "";
		var response = $http.get('fetchVillageByGramPanchayatCode/'+gramPanchayatCode);
		response.success(function(data, status, headers, config) {
			$scope.villages = data;
//			$scope.workData.gramPanchayatBean.gpCode = $scope.gramPanchayats.gramPanchayatBean.gpCode+"";
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
			if($scope.workData.tsIssuingAuthorityId)
			$scope.workData.tsIssuingAuthorityId = $scope.workData.tsIssuingAuthorityId+"";
			$scope.workData.administrationSanctionTypeId = $scope.workData.administrationSanctionTypeId+"";
			
			if($scope.workData.issuingAuthorityId)
			$scope.workData.issuingAuthorityId = $scope.workData.issuingAuthorityId+"";
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
			
			if ($scope.workData.issuingAuthorityId) {
				fd.append('issuingAuthorityId', $scope.workData.issuingAuthorityId);
			}
			
			
			if ($scope.workData.asAuthorityName) {
				fd.append('asAuthorityName', $scope.workData.asAuthorityName);
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
						
						if ($scope.workData.workTypeId) {
							fd.append('workTypeId', $scope.workData.workTypeId);
						}
						
						if ($scope.workData.workNatureId) {
							fd.append('workNatureId', $scope.workData.workNatureId);
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

				/*	if (!file && !$scope.workCreationData.kmlFileId) {
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
						$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
						$scope.loadBlocksByDistrictNew($scope.workData.districtId);
						$scope.workData.blockId = $scope.workData.blockId+"";
						$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
						$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
						$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
						$scope.workData.villageId = $scope.workData.villageId+"";
						$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
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
				
				
				
				$scope.editWorkRequisition = function(isValid, ldPdfFile) {

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
						
						if($scope.saveAsDraft==true){
							$scope.workData.workRequestStatusId = 1;
						}
						else if($scope.finalSubmit==true){
							$scope.workData.workRequestStatusId = 2;
						}
						
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
						
						if ($scope.workData.workId) {
							fd.append('workId', $scope.workData.workId);
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
						
						if ($scope.workData.agencyTypeId) {
							fd.append('agencyTypeId', $scope.workData.agencyTypeId);
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
				$scope.workCreationData.khasraNo = responseData.range_id;
				//var khasraGeom = JSON.parse(responseData.AttributeList[0].khasra_geom);
				//		var coordinates = khasraGeom.geometry.coordinates[0][0];
				//alert("latitude"+$scope.lat);
				$scope.workCreationData.latitude = templat;
				$scope.workCreationData.longitude = templong;



			}, function(error) {

				console.error("Error:", error);
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
$scope.pointData = {}
$scope.blocks = [];
$scope.gramPanchayats = [];
$scope.villages = [];
$scope.workData = $scope.workData || {};
$scope.workData.block = $scope.workData.block || {};
$scope.workData.gramPanchayatBean =  $scope.workData.gramPanchayatBean || {};
$scope.workData.userBean.districtBean = $scope.workData.userBean.districtBean || {};
$scope.pointData = {}
	
	$scope.getLatLngFrommap = function(templat, templong) {
		//alert("hello" + templat + templong);
	
		$http.post('getLatLng/' + templat + '/' + templong +'/' )
			.then(function(response) {
				let responseData = response.data;
			
				//	console.log("responseData$scope.workCreationData",JSON.stringify($scope.workCreationData));
				$scope.pointData = response.data;
				//$scope.district=responseData.AttributeList[0].district_id+""+responseData.AttributeList[0].district_nm;
				
					$scope.workData.userBean.districtBean = responseData.AttributeList.map(function(item) {
					return {
						districtId: item.district_id,
						districtNameH: item.district_nm
					};
				});
				$http.get('getDisctrictDetailByLgdDistrictCode/' + responseData.AttributeList[0].district_id)
				    .then(function(res) {
				        const district = res.data;
				
				        $scope.workData.userBean.districtBean.districtId = district.districtId + "";
				        $scope.workData.userBean.districtBean.districtNameH = district.districtNameH;
				    })
				    .catch(function(err) {
				        console.error("Error loading district:", err);
				    });
				//alert("$scope.district.districtGisCode ="+$scope.districtGisCode)

				//$scope.workCreationData.districtId=$scope.districts[0].districtId+"";
				// alert("$scope.districts[0].districtId ="+$scope.districts[0].districtId)
				if (!$scope.workData.block) {
  			  $scope.workData.block = {};
			}

				// alert(" $scope.workCreationData.districtId"+$scope.workCreationData.districtId);
				//$scope.loadWdfACByLgdDistCode($scope.workCreationData.districtId);
				$scope.blocks = responseData.AttributeList.map(function(item) {
					return {
						blockId: item.block_id,
						blockName: item.block_nm
					};
				});
				$scope.workData.block.blockId =$scope.blocks[0].blockId+ "";
				
				// on edit
				$scope.workData.blockId = $scope.workData.blockId || {};
				$scope.workData.blockId =$scope.blocks[0].blockId+ "";
				//alert("$scope.blocks[0].blockId"+$scope.blocks[0].blockId)      
				//alert("$scope.block.blockCode"+$scope.block.blockCode);
				//alert("$scope.districtGisCode"+$scope.districtGisCode)      
				// alert("$scope.districts[0].districtId"+$scope.districts[0].districtId);
				
			


					if (!$scope.workData.gramPanchayatBean) {
  			  $scope.workData.gramPanchayatBean = {};
			}


				// $scope.workCreationData.blockId={};
				console.log("$scope.gramPanchayats: " + JSON.stringify($scope.gramPanchayats));

				$scope.gramPanchayats = responseData.AttributeList.map(function(item) {
					return {
						gramPanchayatId: item.gp_id,
				gpName: item.gp_nm
					};
				});
		
				//if($scope.workCreationData.villageId == null){

				//	}
				/*$scope.gcodee = 0;
				$scope.vscode = 0;*/

				/* alert("$scope.gramPanchayat.gramPanchayatCode" + $scope.gramPanchayat.gramPanchayatCode);
					alert("$scope.gramPanchayats[0].gramPanchayatId" + $scope.gramPanchayats[0].gramPanchayatId);
				*/
				/*
								if ($scope.gramPanchayat.gramPanchayatCode != $scope.gramPanchayats[0].gramPanchayatId) {
									//alert(" you can not Select Out Side Selected GramPanchayat");
									alert("You Cannot Select Location Outside " + $scope.gramPanchayat.gramPanchayatName + " GramPanchayat");
				
									return;
				
								}*/
if (!$scope.workData.gramPanchayatBean) {
  			  $scope.workData.gramPanchayatBean = {};
			}
			// on add
			$scope.workData.gramPanchayatBean.gramPanchayatId = $scope.gramPanchayats[0].gramPanchayatId + "";
		
			// on edit
			$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId || {};
			$scope.workData.gramPanchayatId = $scope.gramPanchayats[0].gramPanchayatId + "";


				var gpcode = $scope.gramPanchayats[0].gramPanchayatId + "";
				var response = $http.get('fetchGramPanchayatByGPCode/' + gpcode);
				response.success(function(data, status, headers, config) {
					$scope.gramPanchayat = data;
					//alert($scope.workCreationData.villageId);
					/*if($scope.workCreationData.gramPanchayatId != null){
				  if (parseInt($scope.gramPanchayat.gramPanchayatId) !== parseInt($scope.workData.gramPanchayatBean.gramPanchayatId )){
					  alert(" you can not Select Out Side Selected GramPanchayat");
					  
							 $scope.gcodee = 1;
							 
						}
							//alert($scope.gramPanchayats[0].gramPanchayatId);
							//alert($scope.workCreationData.gramPanchayatId);
				  
				}*/

					//alert($scope.workCreationData.gramPanchayatId);
					if ($scope.workData.workId == null) {

						for (var i = 0; i < $scope.gramPanchayat.length; i++) {
							var gp = $scope.gramPanchayat[i];
							if (gp.gramPanchayatId === $scope.workData.gramPanchayatBean.gramPanchayatId) {


								$scope.workData.gramPanchayatBean.gramPanchayatId = gp.gramPanchayatId;
							}

						}


					}
					else {

						for (var i = 0; i < $scope.gramPanchayat.length; i++) {
							var gp = $scope.gramPanchayat[i];
							if (gp.gramPanchayatId === $scope.workData.gramPanchayatBean.gramPanchayatId) {
								$scope.workData.gramPanchayatBean.gramPanchayatId = gp.gramPanchayatId;
							}

						}
					}
				});
				//alert($scope.gcodee);
				//alert($scope.gramPanchayat.gramPanchayatId);

				/*$http.post('fetctByGramPanchayatCode/' + $scope.gramPanchayats[0].gramPanchayatId)
					.then(function(response) {
						$scope.gramPanchayats= response.data; // Use the actual response data
						alert("gramPanchayatId1188" + $scope.gramPanchayat[0].gramPanchayatId);
						$scope.workData.gramPanchayatBean.gramPanchayatId = $scope.gramPanchayat[0].gramPanchayatId;

						//alert("$scope.workCreationData.gramPanchayatId: " + $scope.gramPanchayats[0].gramPanchayatId);
					})
					.catch(function(error) {
						console.error("Error occurred:", error); // Log any error
						alert("Error: " + error.status + " - " + error.statusText);
					});*/

			
				// Process the AttributeList to extract village names and IDs
				$scope.villages = responseData.AttributeList.map(function(item) {
					return {
						villageId: item.village_id,
						villageName: item.village_nm

					};

				});
			$scope.workData.villageBean = $scope.workData.villageBean || {};
			 $scope.workData.villageBean.villageId = $scope.villages[0].villageId + ""
			
			
			//on edit
			 $scope.workData.villageId = $scope.villages[0].villageId + "";
			
				// alert("$scope.village.villageCode" + $scope.village.villageCode);
				//alert("$scope.villages[0].villageId" + $scope.villages[0].villageId);

				/*if ($scope.village.villageCode != $scope.villages[0].villageId) {
					alert(" You can not Select Location Out Side" + $scope.village.vilalgeNameH + "Village");
					return;
				}*/

			


				var vCode = $scope.villages[0].villageId + "";
				var response = $http.get('fetchVillageByVCode/' + vCode);
				response.success(function(data, status, headers, config) {
					$scope.village = data;
					/*if($scope.workCreationData.villageId != null){
						  if (parseInt($scope.Village.villageId) !== parseInt($scope.workData.villageBean.villageId)){
						
						$scope.vscode = 1;
					   }
					}*/



				//	if ($scope.workData.workId == null) {


				//		$scope.workData.villageBean.villageId = $scope.village.villageId + "";
				//	} else {

				//		$scope.workData.villageBean.villageId = parseInt($scope.villages[0].villageId);
				//	}
				});


				/* if($scope.gcodee === 1 ){
				   alert("you have selected point outside the grampanchayat ");
				   $scope.gcodee=0;
				   return;
			   	
				 }
				 if($scope.vscode === 1){
			   	
				   alert("you have selected outside village");
				   $scope.vscode =0;
				   return;
				 }*/




				//   alert("$scope.villages[0].villageId"+$scope.villages[0].villageId);
				//$scope.workData.villageBean.villageId = $scope.villages[0].villageId + "";
				$scope.khasra_number = responseData.AttributeList[0].khasra_number;
				$scope.workData.khasraNo = $scope.khasra_number;
				var khasraGeom = JSON.parse(responseData.AttributeList[0].khasra_geom);
				var coordinates = khasraGeom.geometry.coordinates[0][0];
				$scope.lat = coordinates[0][1];
				$scope.lon = coordinates[0][0];
				//alert("latitude"+$scope.lat);
				$scope.workData.workLocationLatitude = $scope.lat + "";
				$scope.workData.workLocationLongitude = $scope.lon + "";
				if (geojsonLayer != null) {
					map.removeLayer(geojsonLayer);
					geojsonLayer = null;
				}
				geojsonLayer = L.geoJson(JSON.parse(responseData.AttributeList[0].khasra_geom), {
					style: { color: "#FFFF00", weight: 3, fillColor: "#FFFF00", fillOpacity: 0.1 },
				})
				geojsonLayer.addTo(map);
				map.fitBounds(geojsonLayer.getBounds());
				return responseData;
			})
			.catch(function(error) {
				console.log("Error:", error);
			});
	};
	
		$scope.onchangeDistrict = function() {
		var districtId = $scope.workCreationData.districtId;

		// Fetch district details
		$http.get('getDisctrictDetailByLgdDistrictCode/' + districtId)
			.then(function(response) {
				$scope.district = response.data;
				console.log("districtGisCode", $scope.district.districtGisCode);
				var districtGisCode = $scope.district.districtGisCode;
				// Fetch GeoJSON data
				return $http.post('fetchGeojson/' + districtGisCode);
			})
			.then(function(response) {
				let data = response.data;
				console.log("onchangeDistrictdata", data);

				let geojsonFeature = JSON.parse(data.data[0].admingeojsoninfo).features[0]; // Get the entire GeoJSON feature
				console.log("geojsonFeature", geojsonFeature);

				// Remove the existing GeoJSON layer if it exists
				if (geojsonLayer != null) {
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
			})
			.catch(function(error) {
				console.error("Error fetching GeoJSON data:", error);
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
		// var blockId = $scope.workCreationData.blockId;
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


	$scope.onchangeGp = function() {
		//alert("GP");
		console.log($scope.workCreationData.gramPanchayatId + "gramPanchayatId")
		$http.get('fetchLgdGpCode/' + $scope.workCreationData.gramPanchayatId)
			.then(function(response) {
				$scope.grampanchayat = response.data; // Use the actual response data
				console.log(JSON.stringify($scope.gramPanchayat) + "hhh")
				return $http.post('fetchGpGeojson/' + $scope.gramPanchayat.gramPanchayatCode);

			})
			.then(function(response) {
				//alert("Block");
				let data = response.data;
				console.log("data", data);
				// let geojson = JSON.parse(data.data[0].admingeojsoninfo).features[0].properties;
				let geojsonFeature = JSON.parse(data.data[0].admingeojsoninfo).features[0]; // Get the entire GeoJSON feature
				if (geojsonLayer != null) {
					map.removeLayer(geojsonLayer);
					geojsonLayer = null;
				}
				// Create a GeoJSON layer with the entire feature
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
	}
	$scope.onchangeVillage = function() {
		console.log($scope.workCreationData.villageId + "villageId")

		$http.get('fetchVillageCode/' + $scope.workCreationData.villageId)
			.then(function(response) {
				$scope.village = response.data; // Use the actual response data
				return $http.post('fetchVillageGeojson/' + $scope.village.villageCode);

			}).then(function(response) {
				//alert("Block");
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
				if (geojsonLayer != null) {
					map.removeLayer(geojsonLayer);
					geojsonLayer = null;
				}
				// Create a GeoJSON layer with the entire feature
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
	}
	
	$scope.loadDistrictAndBlock = function() {
		//alert("hello");
		$http.get('workCreation')
			.then(function(response) {
				//alert("rtyu");
				//  alert("response"+JSON.stringify(response));
				$scope.workCreationData.districtId = response.data.userDistrictId;
				//  alert(abc22);
				$scope.workCreationData.blockId = response.data.blockId;
				$scope.districts = response.data.districts;
				$scope.blocks = response.data.blocks;
			}, function(error) {
				console.error('Error fetching data:', error);
			});
	};

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
        .then(function (response) {

            // Initialize if null
            if (!$scope.workData) $scope.workData = {};
            if (!$scope.workData.block) $scope.workData.block = {};

            $scope.block_new = response.data;

            if ($scope.block_new && $scope.block_new.blockId) {
                $scope.workData.block.blockId = String($scope.block_new.blockId);
            } else {
                console.error("Block ID missing in response");
                return;
            }
		
		   $scope.block = response.data;

            // Only execute after blockId is available
            $scope.loadGramPanchayatByBlockId($scope.workData.block.blockId);
            $scope.loadGramPanchayatByBlockIdfromOffice($scope.workData.block.blockId);

            if (!$scope.workData.workId) {
                $scope.onchangeBlock($scope.workData.blockId);
            }

        })
        .catch(function (error) {
            console.error("Error:", error);
        });
	};

	
	$scope.loadBlockByDistrictID = function(districtId) {
		//alert("districtId"+districtId);
		$loading.start('sample-1');

		var response1 = $http.get('fetchDistrict/' + districtId);
		response1.success(function(data, status, headers, config) {
			$scope.district = data;
			//alert("$scope.district.districtCode"+$scope.district.districtCode);
		}).then(() => {
			$http.get('fetchBlocksByDistrict/' + $scope.district.districtCode)
				.then(function(response) {
					// Assuming response.data is an array of blocks
					$scope.blocks = response.data.sort((a, b) => {
						// Compare block names to sort alphabetically
						if (a.blockName < b.blockName) {
							return -1;
						}
						if (a.blockName > b.blockName) {
							return 1;
						}
						return 0;
					});
					console.log("Blockkkkk", $scope.blocks);

					$loading.finish('sample-1');
				})
				.catch(function(error) {
					// Handle error here
					console.error('Error fetching blocks:', error);
					$loading.finish('sample-1');
				});

		});
	};
	
	$scope.loadGramPanchayatByBlockIdfromOffice = function(blockId) {
		//alert("loadGramPanchayatByBlockId"+blockId)

		var response = $http.get('fetchGpIdByBlockoffice/' + blockId)
		// alert("hello");
		response.success(function(data, status, headers, config) {
			$scope.officeGramPanchayat = data;
			//	alert("gramPanchayats"+JSON.stringify($scope.gramPanchayats));
		})
	}
	$scope.loadGramPanchayatByBlockId = function(blockId) {
		console.log("loadGramPanchayatByBlockId" + blockId)
		if (blockId && blockId != null && blockId != 'null') {

			var response1 = $http.get('fetchBlockById/' + blockId)
			response1.success(function(data, status, headers, config) {
				$scope.gramPanchayats1 = data;
			}).then(() => {
				$loading.start('sample-1');
				console.log("$scope.gramPanchayats1.blockCode" + $scope.gramPanchayats1.blockCode)
				$http.get('fetchGramPanchyatByBlock/' + $scope.gramPanchayats1.blockCode)
					.then(function(response) {
						// Assuming response.data is an array of gram panchayats
						$scope.gramPanchayats = response.data.sort((a, b) => {
							// Compare gram panchayat names to sort alphabetically
							if (a.gramPanchayatName < b.gramPanchayatName) {
								return -1;
							}
							if (a.gramPanchayatName > b.gramPanchayatName) {
								return 1;
							}
							return 0;
						});
						// $scope.workCreationData.gramanchayatId = $scope.gramPanchayats[0].gramPanchayatId;
						// console.log(" $scope.gramPanchayats11"+JSON.stringify($scope.gramPanchayats));
						$loading.finish('sample-1');
					})
					.catch(function(error) {
						// Handle error here
						console.error('Error fetching gram panchayats:', error);
						$loading.finish('sample-1');
					});


			});

		}
	};


	$scope.loadVillageByGPId = function(gramPanchayatId) {

		//console.log("loadVillageByGPId"+gramPanchayatId);
		var response1 = $http.get('fetchGramPanchayatId/' + gramPanchayatId);
		response1.success(function(data, status, headers, config) {
			//console.log(JSON.stringify(response1))
			$scope.villages1 = data;
			//console.log("Villages"+$scope.workCreationData.villageId);
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
						// $scope.workCreationData.villageId = $scope.villages[0].villageId;
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
				
});