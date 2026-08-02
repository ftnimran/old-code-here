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

/*
 * app.service('resService', function() { this.myFunc = function (x) { return
 * x.toString(16); } });
 */

res.filter('indianCurrency', function() {
	return function(value) {
		var x = parseFloat(value).toFixed(2);
		x=x.toString();
		var afterPoint = '';
		if(x.indexOf('.') > 0)
			afterPoint = x.substring(x.indexOf('.'),x.length);
		x = Math.floor(x);
		x=x.toString();
		
		// for negative numbers
		var isNegative = false;
		if(x.includes("-")){
			x= x.split("-")[1];
			isNegative = true;
		}
		
		var lastThree = x.substring(x.length-3);
		var otherNumbers = x.substring(0,x.length-3);
		if(otherNumbers != '')
			lastThree = ',' + lastThree;
		var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
		if(isNegative){
			res = "-" + res;
		}
		// return '₹' + res;
		return res;
		// return x;
	}
});

res.filter('inWords', function() {
	return function(num) {
		var a = ['','One ','Two ','Three ','Four ', 'Five ','Six ','Seven ','Eight ','Nine ','Ten ','Eleven ','Twelve ','Thirteen ','Fourteen ','Fifteen ','Sixteen ','Seventeen ','Eighteen ','Nineteen '];
		var b = ['', '', 'Twenty','Thirty','Forty','Fifty', 'Sixty','Seventy','Eighty','Ninety'];		
		
		if(num) {
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

res.filter('decimal', function() {
	return function(value) {
		var x = parseFloat(value).toFixed(2);
		return x;
	}
});

res.service('commonService', function() {
	this.currentDate = function () {
		var d = new Date();

		var datestring = ("0" + d.getDate()).slice(-2)  + "/" + ("0" + (d.getMonth() + 1)).slice(-2) + "/" + d.getFullYear();

		return datestring;
	}
});

res.controller('CommonController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout, commonService, $filter) {
	$scope.started = false;
	$scope.workTemplateGroupItems=[];
	/*
	 * $scope.Date = function(){
	 * 
	 * var d = new Date();
	 * 
	 * var datestring = ("0" + d.getDate()).slice(-2) + "/" + ("0" +
	 * (d.getMonth() + 1)).slice(-2) + "/" + d.getFullYear();
	 * 
	 * return datestring; };
	 */

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

	$scope.changePasswordFunction = function(isValid) {

		if (!isValid) 
			return false;

		$loading.start('sample-1');

		$scope.changePasswordData.currentPassword = hash($scope.changePasswordData.currentPassword);
		$scope.changePasswordData.password = hash($scope.changePasswordData.password);
		$scope.changePasswordData.confirmPassword = hash($scope.changePasswordData.confirmPassword);

		var responsePromise = $http.post('dochangepassword', $scope.changePasswordData);

		responsePromise.success(function(data, status, headers, config) {

			$rootScope.responseObject = data;

			if($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					$rootScope.responseObject.successMessage = null;
				}, 10000);
				$window.location.href = '#changepassword';
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
	};
	
	$scope.loadUserList = function() {

		$loading.start('sample-1');
		fetchUserList();
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

		if(null!= officeTypeId && officeTypeId!="null"){
			$loading.start('sample-1');
			var response = $http.get('fetchOfficesByOfficeType/'+officeTypeId);
			response.success(function(data, status, headers, config) {
				$scope.offices = data;
				$loading.finish('sample-1');
			});
		}
		
	};
	
	$scope.loadOfficeNames = function(officeTypeId) {
//
		//$('#officesHidden').val( '');
		$scope.exeOffices =null;
		$scope.usersDet = null;
		if(null!= officeTypeId && officeTypeId!="null"){
			$loading.start('sample-1');
			var response = $http.get('fetchOfficesByOfficeType/'+officeTypeId);
			response.success(function(data, status, headers, config) {
				$scope.officeNames = data;
				$loading.finish('sample-1');
			});
		}
		
	};
	
	$scope.loadSqmOffices = function(officeTypeId) {

		if(null!= officeTypeId){
			$loading.start('sample-1');
			var response = $http.get('fetchOfficesByOfficeTypeAndSqmUser/'+officeTypeId+'/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				
				$scope.offices = data;
				var checkedOffices="";
				for(var i=0;i<$scope.offices.length;i++){
					// userData.officesHidden
					
					if($scope.offices[i].isSqmChecked==1){
						checkedOffices+=$scope.offices[i].id+",";	
						// alert('sdsdsd=='+$scope.offices[i].id);
					}
				}
				
				if(checkedOffices.length>0){
				var lastChar = checkedOffices.slice(-1);
				if (lastChar == ',') {
					checkedOffices = checkedOffices.slice(0, -1);
				}
				  $('#officesHidden').val(checkedOffices+"");
				}
			
				$loading.finish('sample-1');
			});
		}
		
	};
	
	
	
	$scope.searchByNameEmailOrAnyFilters = function(){
		$loading.start('sample-1');
		if(($('#searchBox').val()!="" && $('#searchBox').val().trim().length >=4) || $('#designation').val()!="" || $('#status').val()!=""){
			reDraw();
		}
		else{
			$loading.finish('sample-1');
		}
	};

	$scope.refreshFunction = function() {
		$loading.start('sample-1');
		$window.location.reload();
		
	};
	
	$scope.loadUserRoles = function() {

		$loading.start('sample-1');
		var responseRoles = $http.get('fetchRoles');
		responseRoles.success(function(data, status, headers, config) {
			$scope.roles = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadUserDetailAdmin = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchUserDetails/'+$routeParams.id);

		response.success(function(data, status, headers, config) {
			$scope.userData = data;
			$scope.userData.designationId = $scope.userData.designationId+"";
			$scope.userData.officeTypeId = $scope.userData.officeTypeId+"";
			$scope.loadOffices($scope.userData.officeTypeId)
			$scope.userData.officeId = $scope.userData.officeId+"";
			$loading.finish('sample-1');
		});
	};
	// Rakesh
	$scope.loadSqmUserDetailAdmin = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchSqmUserDetails/'+$routeParams.id);

		response.success(function(data, status, headers, config) {
			$scope.userData = data;
			$scope.userData.designationId = $scope.userData.designationId+"";
			$scope.userData.officeTypeId = $scope.userData.officeTypeId+"";
			$scope.loadOffices($scope.userData.officeTypeId)
			$scope.userData.officeId = $scope.userData.officeId+"";
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadInspUserDetailAdmin = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchInspUserDetails/'+$routeParams.id);

		response.success(function(data, status, headers, config) {
			$scope.userData = data;
			$scope.loadEEofficesBySupdtOfficeIdsCeEncChecked($scope.userData.officerOfficeId,$scope.userData.userId);
			
			$scope.loadUsersForOfficeIdAndDesg($scope.userData.officerOfficeId);
			$scope.loadOfficeNames($scope.userData.officeTypeId);
			$scope.userData.designationId = $scope.userData.officeTypeId+"";
			$scope.userData.userId=$scope.userData.userId+"";
			$scope.userData.officeTypeId = $scope.userData.officeTypeId+"";
			/*$scope.loadOffices($scope.userData.officeTypeId)*/
			$scope.userData.officeId = $scope.userData.officeId+"";
			$scope.userData.office = $scope.userData.officerOfficeId+"";
			
			//userData.office
			$loading.finish('sample-1');
		});
	};
	$scope.editUser = function(isValid) {

		if (!isValid) {
			return false;
		}
		
		var responsePromise = $http.get('checkIsOICByOfficeId/'+ $scope.userData.officeId);
			
		// $loading.start('sample-1');
			
			responsePromise.success(function(data, status, headers, config) {
	
				
				if( data == true && $scope.userData.isOIC=="1" && $scope.userData.oldStatus=='Pending Activation' && $scope.userData.status=='Active'){
					if (confirm("An OIC with this Office is already associated. Do you really want to change OIC?")) {
						$scope.userData.id = $routeParams.id;
						$scope.userData.password=null;
						$loading.start('sample-1');
						var responsePromise = $http.post('editUser', $scope.userData);
				
						responsePromise.success(function(data, status, headers, config) {
				
							$rootScope.responseObject = data;
				
							if($rootScope.responseObject.successMessage != null) {
								$timeout(function() {
									$rootScope.responseObject.successMessage = null;
								}, 5000);
								$window.location.href = '#manageusers';
							}
							if($rootScope.responseObject.errorMessage != null) {
								$timeout(function() {
									$rootScope.responseObject.errorMessage = null;
								}, 5000);
							}
							$loading.finish('sample-1');
						});
					}
				}else{
					if (confirm("Are you sure you want to save the data?")) {
						$scope.userData.id = $routeParams.id;
						$scope.userData.password=null;
						$loading.start('sample-1');
						var responsePromise = $http.post('editUser', $scope.userData);
				
						responsePromise.success(function(data, status, headers, config) {
				
							$rootScope.responseObject = data;
				
							if($rootScope.responseObject.successMessage != null) {
								$timeout(function() {
									$rootScope.responseObject.successMessage = null;
								}, 5000);
								$window.location.href = '#manageusers';
							}
							if($rootScope.responseObject.errorMessage != null) {
								$timeout(function() {
									$rootScope.responseObject.errorMessage = null;
								}, 5000);
							}
							$loading.finish('sample-1');
						});
					}
				}
				
			});
	};
	
	
	
	$scope.deleteUser = function(userId) {		
		if (confirm("Are you sure to delete this entry?")) {
			$loading.start('sample-1');

			var responsePromise = $http.get('deleteUser/'+ userId);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageusers';
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
	// Rakesh
	$scope.deleteSqmUser = function(userId) {		
		if (confirm("Are you sure to delete this entry?")) {
			$loading.start('sample-1');

			var responsePromise = $http.get('deleteUser/'+ userId);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageSqmUsers';
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
		// alert($scope.districts);
			$loading.finish('sample-1');
		});
	};
// Rakesh
	$scope.loadDistricts = function() {
		// alert('dfdf');
		$loading.start('sample-1');
	// $scope.entrepreneurData.registeredAddress.districtId = "";
		var response = $http.get('fetchDistricts');
		response.success(function(data, status, headers, config) {
			$scope.districts = data;
// alert($scope.districts);
			$loading.finish('sample-1');
		});
	};
	$scope.loadDistrictsOfMP = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchDistrictsOfMP');
		response.success(function(data, status, headers, config) {
			$scope.districtsMP = data;
			$loading.finish('sample-1');
		});
	};

	$scope.loadStateIdOfMP = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchStateIdOfMP');
		response.success(function(data, status, headers, config) {
			$scope.entrepreneurData.factoryAddress.stateId = data+"";
			$loading.finish('sample-1');
		});
	};

	$scope.resetFunction = function() {
		$loading.start('sample-1');
		
		$timeout(function () {
			reDraw();
		}, 0);
	};

	$scope.loadBills = function() {

		$loading.start('sample-1');
		fetchBills();
	};
	
	$scope.loadBillsSub = function() {

		$loading.start('sample-1');
		fetchBills();
	};
	
	
	$scope.loadWorksForBillingP = function() {

		$loading.start('sample-1');
		/*alert("hbii");*/
		fetchWorksByName();
	};
	
	
	$scope.loadBillsForInspection = function() {

		$loading.start('sample-1');
		loadBillsForInspection();
	};
	
	
	
	$scope.loadPendingWorkEstimations = function() {

		$loading.start('sample-1');
		fetchPendingWorkEstimations();
	};
	
	// nikhil
	$scope.loadHistoryWorkEstimations = function() {

		$loading.start('sample-1');
		fetchHistoryWorkEstimations($routeParams.id, $routeParams.estimationId);
	};
	
	$scope.loadWorksForBilling = function(){
		var response = $http.get('fetchWorksByName', {params: {'searchBoxVal': $('#searchBox').val()}});
		response.success(function(data, status, headers, config) {
			$scope.works = data;
			$loading.finish('sample-1');
		});
	}

	$scope.searchByWorksName = function(){

		$loading.start('sample-1');
		if($('#searchBox').val()!="" && $('#searchBox').val().trim().length >=4){
			// $scope.tableshow = true;
			var response = $http.get('fetchWorksByName', {params: {'searchBoxVal': $('#searchBox').val()}});
			response.success(function(data, status, headers, config) {
				$scope.works = data;
				$loading.finish('sample-1');
			});
		}
		else{
			$loading.finish('sample-1');
		}
	};
	

	
	$scope.validateRemainingAmount = function() {
		
		$scope.errorMessage2 = null;
		
		if($scope.billData.remainingAmountForPaymentWithoutGst) {
			if(parseInt($scope.billData.remainingAmountForPaymentWithoutGst) < 0 ) {
			 	$scope.errorMessage2 = "You have negative remaining amount, and new bill can’t be generated";
			}
		}
	}
	
	
	$scope.loadAddNewBillForm = function() {
		
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}). 
		then(function (){
			$loading.start('sample-1');
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			$scope.inspectionByListSDO = [];
			$scope.inspectionByListSE = [];
			/*
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 */
			if($scope.workData.executiveEngineerOfficeId) {
			var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response1.success(function(data, status, headers, config) {
					$scope.measuredByList = data;
			});
			var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response2.success(function(data, status, headers, config) {
				$scope.inspectionByListAE = data;
			});
			var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
		  }
			
		}).then(function (){
			$loading.start('sample-1');
			
			if($scope.workData.workSubTypeId == null) {
				$scope.workData.workSubTypeId = -1;
			}
			var response = $http.get('fetchWorkTemplateItems/'+$scope.workData.workTypeId+'/'+$scope.workData.workSubTypeId);
			response.success(function(data, status, headers, config) {
				$scope.workTemplateItems = data;
				$loading.finish('sample-1');
			}).then(function (){
				$loading.start('sample-1');
				var response = $http.get('fetchLastBillDetails/'+$routeParams.id);
				response.success(function(data, status, headers, config) {
					$scope.lastBillData = data;
					$loading.finish('sample-1');
				}).then(function (){
					$scope.billData={};
					//making billing flag as 0 as per the Change Request letter dated 14/09/2021 - as no need of billing flag now.
					$scope.workData.billingFlag=0;
					if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
																			// Nivida
																			// tender
					if($scope.workData.billingFlag==0){
						
						//take tenderCost in place of probableAmountOfWork
					/* $scope.billData.remainingAmountForPayment = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
					 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);*/
						 //check for Revised Estimation
						 if($scope.lastBillData.estimationRevised){
							 //$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
							 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						 }else{
							 $scope.billData.remainingAmountForPayment = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
							 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						 }
					
					}else{

						if($scope.workData.maxBillingAmount==null)  {
							$scope.billData.remainingAmountForPayment = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
							$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}
						else{
							$scope.billData.remainingAmountForPayment = ($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
							$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}

					}
					
					} else { // RES Vibhagiya - GP --
					
					if($scope.lastBillData.estimationRevised){
						//$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
						 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
					 }else{
						 $scope.billData.remainingAmountForPayment = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills?$scope.lastBillData.totalAmountPreviousBills:0);
						 $scope.billData.remainingAmountForPaymentWithoutGst=$scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions?$scope.lastBillData.billAmountWithoutGstAndDeductions:0);
					 }
				 }
					
					if ($scope.lastBillData && $scope.lastBillData.billItems && $scope.lastBillData.billItems.length > 0) {
					    
						$scope.billData.billItems = [];
						
						// adding last bill items
						$scope.billData.billItems.push.apply($scope.billData.billItems, $scope.lastBillData.billItems);
						
						// adding work template items which are not in last bill
						angular.forEach($scope.workTemplateItems, function(workTemplateItem) {
							var item = $scope.lastBillData.billItems.find(x => x.itemOfWork == workTemplateItem.itemDesc);
							if(!item){
								if(workTemplateItem.group == false){ 
									var billItem = {itemOfWork : workTemplateItem.itemDesc, unit : workTemplateItem.unit, amountUptodate : 0, amountPreviousBill : 0,  rate : workTemplateItem.rate , hasChild : workTemplateItem.hasChild, readOnlyFlag : workTemplateItem.readOnly,  workTemplateId : workTemplateItem.id , parentId :  workTemplateItem.parentItem? workTemplateItem.parentItem.id : 0 }
									$scope.billData.billItems.push(billItem);
								}
							} 
						})
					 
						$scope.billData.lastBillNo = $scope.lastBillData.billNo;
						$scope.billData.lastBillIndex = $scope.lastBillData.billIndex;
						
					} else {

						$scope.billData.billItems = [];
						angular.forEach($scope.workTemplateItems, function(workTemplateItem) {
							if(workTemplateItem.group == false){  
								var billItem = {itemOfWork : workTemplateItem.itemDesc, unit : workTemplateItem.unit, amountUptodate : 0, amountPreviousBill : 0, rate : workTemplateItem.rate , hasChild : workTemplateItem.hasChild, readOnlyFlag : workTemplateItem.readOnly, workTemplateId : workTemplateItem.id , parentId :  workTemplateItem.parentItem ? workTemplateItem.parentItem.id : 0}
								$scope.billData.billItems.push(billItem);
							}
						});
					}
					
					$scope.billData.inspectedBy = {};
					 // 2 gp - 1,3 RES
					if($scope.workData.agencyTypeBean.agencyTypeId == 2) {  // GP
						// $scope.billData.measurementById =
						// $scope.workData.subEngineerId+"";
						if(null!= $scope.workData.subEngineerId){
							$scope.billData.inspectedBy.id= $scope.workData.subEngineerId+"";
						}
					} else {
						// $scope.billData.measurementById =
						// $scope.workData.assistantEngineerId+"";
						if(null!= $scope.workData.assistantEngineerId){
							$scope.billData.inspectedBy.id =   $scope.workData.assistantEngineerId+"";
						}
					}
					
					if(null!= $scope.workData.subEngineerId){
						$scope.billData.measurementById = $scope.workData.subEngineerId+"";
					}
					
					
					$scope.calculateTotalAmountUpToDate();$scope.calculateTotalAmountPreviousBill();
					
					$scope.validateRemainingAmount();
					$scope.validateBillInDraftOrNot();
					
					
				});
			
				
				
		});
		});
	};

	
	$scope.loadAddNewBillForEstimationForm = function() {
		
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}). 
		then(function (){
			$loading.start('sample-1');
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			/*
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 */
			if($scope.workData.executiveEngineerOfficeId) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
				response1.success(function(data, status, headers, config) {
					$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
				response2.success(function(data, status, headers, config) {
					$scope.inspectionByListAE = data;
				});
				
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
			}
		}).then(function (){
			$loading.start('sample-1');
			
			if($scope.workData.workSubTypeId == null) {
				$scope.workData.workSubTypeId = -1;
			}
			var response = $http.get('fetchWorkEstimationDetailsById/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				
				$scope.workData.contingencyAmount = data.workChargeContingencyAmt;
				
				$scope.workTemplateItems = data.workTemplateItems;
				$loading.finish('sample-1');
			}).then(function (){
				$loading.start('sample-1');
				var response = $http.get('fetchLastBillDetails/'+$routeParams.id);
				response.success(function(data, status, headers, config) {
					$scope.lastBillData = data;
					$loading.finish('sample-1');
				}).then(function (){
					$scope.billData={};
					//making billing flag as bydefault 0 as per CR.
					$scope.workData.billingFlag=0;
					if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
						// Nivida
						// tender
						// amount
						if($scope.workData.billingFlag==0){
							if($scope.lastBillData.estimationRevised){
								//$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
								 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							 }else{
								 $scope.billData.remainingAmountForPayment = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0)   - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								 $scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0)   - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							 }
						}else
						{
							/*$scope.billData.remainingAmountForPayment = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0)   - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
						 $scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0)   - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);*/


							if($scope.workData.maxBillingAmount==null)  {
								$scope.billData.remainingAmountForPayment = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}
							else{
								$scope.billData.remainingAmountForPayment = ($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}


						}

					} else { // RES Vibhagiya - GP --
						if($scope.lastBillData.estimationRevised){
							//$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
							 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						 }else{
							 $scope.billData.remainingAmountForPayment = $scope.workData.totalCostString - $scope.workData.contingencyAmount -  ($scope.lastBillData.totalAmountPreviousBills?$scope.lastBillData.totalAmountPreviousBills:0);
							 $scope.billData.remainingAmountForPaymentWithoutGst = $scope.workData.totalCostString - $scope.workData.contingencyAmount -  ($scope.lastBillData.billAmountWithoutGstAndDeductions?$scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						 }
					
					}
					
					
					
					if ($scope.lastBillData && $scope.lastBillData.billItems && $scope.lastBillData.billItems.length > 0) {
					    
						$scope.billData.billItems = [];
						
						// adding last bill items
						$scope.billData.billItems.push.apply($scope.billData.billItems, $scope.lastBillData.billItems);
						
						// adding work template items which are not in last bill
						angular.forEach($scope.workTemplateItems, function(workTemplateItem) {
							var item = $scope.lastBillData.billItems.find(x => x.itemOfWork == workTemplateItem.itemDesc);
							if(!item){
								var billItem = {itemOfWork : workTemplateItem.itemDesc, unit : workTemplateItem.unit, amountUptodate :  0, quantityUptodate : 0 , estimationQuantity : workTemplateItem.quantity, amountPreviousBill : 0,  rate : workTemplateItem.rate , hasChild : workTemplateItem.hasChild, readOnlyFlag : workTemplateItem.readOnly,  workTemplateId : workTemplateItem.id , parentId :  workTemplateItem.parentItem? workTemplateItem.parentItem.id : 0 }
								$scope.billData.billItems.push(billItem);
								
							} 
						})
					 
						$scope.billData.lastBillNo = $scope.lastBillData.billNo;
						$scope.billData.lastBillIndex = $scope.lastBillData.billIndex;
						
					} else {

						$scope.billData.billItems = [];
						angular.forEach($scope.workTemplateItems, function(workTemplateItem) {
							var billItem = {itemOfWork : workTemplateItem.itemDesc, unit : workTemplateItem.unit, amountUptodate : 0, quantityUptodate : 0, estimationQuantity : workTemplateItem.quantity, amountPreviousBill : 0, rate : workTemplateItem.rate , hasChild : workTemplateItem.hasChild, readOnlyFlag : workTemplateItem.readOnly, workTemplateId : workTemplateItem.id , parentId :  workTemplateItem.parentItem ? workTemplateItem.parentItem.id : 0}
							$scope.billData.billItems.push(billItem);
						
						});
					}
					
					$scope.billData.inspectedBy = {};
					 // 2 gp - 1,3 RES
					if($scope.workData.agencyTypeBean.agencyTypeId == 2) {  // GP
						// $scope.billData.measurementById =
						// $scope.workData.subEngineerId+"";
						if(null!= $scope.workData.subEngineerId){
							$scope.billData.inspectedBy.id= $scope.workData.subEngineerId+"";
						}
					} else {
						// $scope.billData.measurementById =
						// $scope.workData.assistantEngineerId+"";
						if(null!= $scope.workData.assistantEngineerId){
							$scope.billData.inspectedBy.id =   $scope.workData.assistantEngineerId+"";
						}
					}
					
					if(null!= $scope.workData.subEngineerId){
						$scope.billData.measurementById = $scope.workData.subEngineerId+"";
					}
					
					$scope.calculateTotalAmountUpToDate();
					
					$scope.calculateTotalAmountPreviousBill();
					
					
					$scope.validateRemainingAmount();
					$scope.validateBillInDraftOrNot();
					
					
				});
			
				
				
		});
		});
		
	};
	
	$scope.changeTemplateType = function() {
		if (confirm("Any data entered on the screen will be lost. Are you sure you want to change Template Type?")) {
			$scope.workTemplateItems=null;
			$scope.standardTemplateTypeId=null;
			$scope.initializeAllAmount();
			if($scope.templateType==2){
				$('#standardTemplateTypeTd').css("display", '');
				$('#sorEstimation').css("display", 'none');
				$('#SORDiv').css("display", 'none');
				$('#nonSORDiv').css("display", '');
				$loading.start('sample-1');
				var response2 = $http.get('fetchStandardTemplateTypesByWorkTypeId?workTypeId='+$scope.workData.workTypeId);
				response2.success(function(data, status, headers, config) {
					$scope.standardTemplateTypes = data;
					$loading.finish('sample-1');
				});
			}else if($scope.templateType==3){
				$('#sorEstimation').css("display", '');
				$('#nonSORDiv').css("display", 'none');
				$('#SORDiv').css("display", '');
			}
			else{
				$('#sorEstimation').css("display", 'none');
				$('#standardTemplateTypeTd').css("display", 'none');
				$('#SORDiv').css("display", 'none');
				$('#nonSORDiv').css("display", '');
				$loading.start('sample-1');
				var response2 = $http.get('fetchWorkTemplateItemsForEstimation/'+$scope.workData.workTypeId+'/'+$scope.templateType);
				response2.success(function(data, status, headers, config) {
					$scope.workTemplateItems = data;
					$loading.finish('sample-1');
				});
			}
		}else{
			if($scope.templateType==2){
				$scope.templateType=1;
				$("input[name=templateType][value=1]").prop('checked', true);
				$("input[name=templateType][value=2]").prop('checked', false);
			}else{
				$scope.templateType=2;
				$("input[name=templateType][value=1]").prop('checked', false);
				$("input[name=templateType][value=2]").prop('checked', true);
			}
		}
	};
	
	$scope.changeStandardTemplateType = function() {
			$loading.start('sample-1');
			var response2 = $http.get('fetchWorkTemplateItemsForEstimation/'+$scope.workData.workTypeId+'/'+$scope.templateType+'?standardTemplateTypeId='+$scope.standardTemplateTypeId);
			response2.success(function(data, status, headers, config) {
				$scope.workTemplateItems = data;
				$scope.calculateTotalAmount();
				$loading.finish('sample-1');
			});
	};
	
	$scope.loadWorkListBySqmInspectionByWorkId = function() {

		$loading.start('sample-1');
		fetchWorkListBySqmInspectionByWorkId($routeParams.workId);
	};
	
	$scope.loadWorkListByOfficerInspectionByWorkId = function() {

		$loading.start('sample-1');
		fetchWorkListByOfficerInspectionByWorkId($routeParams.workId);
	};
	
	$scope.loadWorkListGeneralInspectionByWorkId = function() {

		$loading.start('sample-1');
		fetchWorkListGeneralInspectionByWorkId($routeParams.workId);
	};
	
	
	$scope.loadAddNewWorkEstimationForm = function() {
		
		if($scope.templateType==null){
			$scope.templateType=1;
		}
		
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}).then(function (){
			
			$loading.start('sample-1');
			var response1 = $http.get('fetchWorkEstimationDetailsById/'+$scope.workData.workId);
			response1.success(function(data, status, headers, config) {
				// $scope.workData = data;
				
				    $scope.workData.competentAuthName=data.competentAuthName;
				    $scope.workData.competentAuthDesig=data.competentAuthDesig;
				    $scope.workData.revisedLetterNo=data.revisedLetterNo;
				    $scope.workData.letterNoDate=data.letterNoDate;
				    
				    
					$scope.workData.estimatedAmount = data.estimatedAmount;
					
					$scope.workData.grandTotalOriginalAmoumnt = data.grandTotal;
					$scope.workData.grandTotal = data.grandTotal;
					$scope.workData.expectedTenderedAmt = data.expectedTenderedAmt;
					$scope.workData.expectedTenderedRatePer = data.expectedTenderedRatePer;
					$scope.workData.overheadChargesPer = data.overheadChargesPer;
					$scope.workData.overheadChargesAmt = data.overheadChargesAmt;
					$scope.workData.labourWelfareComponentPer = data.labourWelfareComponentPer;
					$scope.workData.labourWelfareComponentAmt = data.labourWelfareComponentAmt;
					$scope.workData.applicableGstPer = data.applicableGstPer;
					$scope.workData.applicableGstAmt = data.applicableGstAmt;
					
					$scope.workData.workChargeContingencyPer = data.workChargeContingencyPer;
					$scope.workData.workChargeContingencyAmt = data.workChargeContingencyAmt;
					$scope.workData.administrativeExpenditurePer = data.administrativeExpenditurePer;
					$scope.workData.administrativeExpenditureAmt = data.administrativeExpenditureAmt;
					$scope.workData.othersCharges = data.othersCharges;
					$scope.workData.comments=data.comments;
					$scope.workData.subEngComments=data.subEngComments;
					$scope.workData.subEngFwdDate=data.subEngFwdDate;
					$scope.workData.sdoComments=data.sdoComments;
					$scope.workData.sdoFwdDate=data.sdoFwdDate;
					$scope.workData.aeComments=data.aeComments;
					$scope.workData.aeFwdDate=data.aeFwdDate;
					$scope.workData.eeComments=data.eeComments;
					$scope.workData.eeFwdDate=data.eeFwdDate;
					$scope.workData.seComments=data.seComments;
					$scope.workData.seFwdDate=data.seFwdDate;
					$scope.workData.ceComments=data.ceComments;
					$scope.workData.ceFwdDate=data.ceFwdDate;
					if(null!=$routeParams.estimationType){
						$scope.workData.estimationStatusId=null;
					}else{
						$scope.workData.estimationStatusId=data.estimationStatusId;
					}
					
					
					$scope.workTemplateItems = data.workTemplateItems;
					$scope.workData.estimationId=data.estimationId;
					$scope.workData.status=data.status;
					$scope.workData.estimationSubmissionDate=data.estimationSubmissionDate;
					$scope.calculateTotalAmount();
					$scope.calculateGrandTotal();
					// $scope.calculateTotalLabourComponent();
					$scope.tenderRateGreaterThan10=false;
					
					$scope.workData.estimationType=data.estimationType;
					$scope.workData.hasNonSorItems=data.hasNonSorItems;
					$scope.workData.tenPercentCheck=data.tenPercentCheck;
					
					
				
				if(null== data.workTemplateItems){
					$loading.start('sample-1');
					var response2 = $http.get('fetchWorkTemplateItemsForEstimation/'+$scope.workData.workTypeId+'/'+$scope.templateType);
					response2.success(function(data, status, headers, config) {
						$scope.workTemplateItems = data;
						$loading.finish('sample-1');
					});
				}else{
					$loading.finish('sample-1');
				}
			});
		});
	};
	
$scope.loadAddNewWorkEstimationFormH = function() {
		
		if($scope.templateType==null){
			$scope.templateType=1;
		}
		
		$scope.estimationId = $routeParams.estimationId;
		$scope.id = $routeParams.id;
		
		var response = $http.get('fetchWorkDetailsH/'+$routeParams.estimationId);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}).then(function (){
			
			$loading.start('sample-1');
			var response1 = $http.get('fetchWorkEstimationDetailsByIdH/'+$routeParams.estimationId);
			response1.success(function(data, status, headers, config) {
				// $scope.workData = data;
				
					$scope.workData.estimatedAmount = data.estimatedAmount;
					
					$scope.workData.grandTotal = data.grandTotal;
					$scope.workData.expectedTenderedAmt = data.expectedTenderedAmt;
					$scope.workData.expectedTenderedRatePer = data.expectedTenderedRatePer;
					$scope.workData.overheadChargesPer = data.overheadChargesPer;
					$scope.workData.overheadChargesAmt = data.overheadChargesAmt;
					$scope.workData.labourWelfareComponentPer = data.labourWelfareComponentPer;
					$scope.workData.labourWelfareComponentAmt = data.labourWelfareComponentAmt;
					$scope.workData.applicableGstPer = data.applicableGstPer;
					$scope.workData.applicableGstAmt = data.applicableGstAmt;
					
					$scope.workData.workChargeContingencyPer = data.workChargeContingencyPer;
					$scope.workData.workChargeContingencyAmt = data.workChargeContingencyAmt;
					$scope.workData.administrativeExpenditurePer = data.administrativeExpenditurePer;
					$scope.workData.administrativeExpenditureAmt = data.administrativeExpenditureAmt;
					$scope.workData.othersCharges = data.othersCharges;
					$scope.workData.comments=data.comments;
					$scope.workData.subEngComments=data.subEngComments;
					$scope.workData.subEngFwdDate=data.subEngFwdDate;
					$scope.workData.sdoComments=data.sdoComments;
					$scope.workData.sdoFwdDate=data.sdoFwdDate;
					$scope.workData.aeComments=data.aeComments;
					$scope.workData.aeFwdDate=data.aeFwdDate;
					$scope.workData.eeComments=data.eeComments;
					$scope.workData.eeFwdDate=data.eeFwdDate;
					$scope.workData.seComments=data.seComments;
					$scope.workData.seFwdDate=data.seFwdDate;
					$scope.workData.ceComments=data.ceComments;
					$scope.workData.ceFwdDate=data.ceFwdDate;
					if(null!=$routeParams.estimationType){
						$scope.workData.estimationStatusId=null;
					}else{
						$scope.workData.estimationStatusId=data.estimationStatusId;
					}
					
					
					$scope.workTemplateItems = data.workTemplateItems;
					$scope.workData.estimationId=data.estimationId;
					$scope.workData.status=data.status;
					$scope.workData.estimationSubmissionDate=data.estimationSubmissionDate;
					$scope.calculateTotalAmount();
					$scope.calculateGrandTotal();
					// $scope.calculateTotalLabourComponent();
					$scope.tenderRateGreaterThan10=false;
					
					$scope.workData.estimationType=data.estimationType;
				
				if(null== data.workTemplateItems){
					$loading.start('sample-1');
					var response2 = $http.get('fetchWorkTemplateItemsForEstimation/'+$scope.workData.workTypeId+'/'+$scope.templateType);
					response2.success(function(data, status, headers, config) {
						$scope.workTemplateItems = data;
						$loading.finish('sample-1');
					});
				}else{
					$loading.finish('sample-1');
				}
			});
		});
	};
	
	$scope.calculateUpToDateAmount = function(billItem) {
		
		if(billItem.quantityUptodate && billItem.rate)
			billItem.amountUptodate = parseFloat(billItem.quantityUptodate * billItem.rate).toFixed(0);
		else
			billItem.amountUptodate = 0;
	}
	
	

	$scope.amountValidate = function(billItem) {
		
		$scope.errorMessage1 = null;
		
		if(billItem.amountPreviousBill && billItem.amountUptodate) {
			if(parseInt(billItem.amountPreviousBill) > parseInt(billItem.amountUptodate)) {
			 	$scope.errorMessage1 = "Previous bill amount cannot be greater than Up-to-date Amount";
			}
		}
		
		$scope.errorMessage3 = null;
		
		if(billItem.quantityUptodate && billItem.quantityPreviousBill) {
			if(parseInt(billItem.quantityPreviousBill) > parseInt(billItem.quantityUptodate)) {
			 	$scope.errorMessage3 = "Previous bill Quantity cannot be greater than Up-to-date Quantity";
			}
		}
		
	}
	
	
	$scope.amountValidateEstimation = function(billItem) {
		
		$scope.errorMessage1 = null;
		
		if(billItem.amountPreviousBill && billItem.amountUptodate) {
			if(parseInt(billItem.amountPreviousBill) > parseInt(billItem.amountUptodate)) {
			 	$scope.errorMessage1 = "Previous bill amount cannot be greater than Up-to-date Amount";
			 	return false;
			}
		}
		
		$scope.errorMessage3 = null;
		
		if(billItem.quantityUptodate && billItem.estimationQuantity) {
			if(parseInt(billItem.estimationQuantity) < parseInt(billItem.quantityUptodate)) {
			 	$scope.errorMessage3 = "Items quantity cannot be greater than estimation quantity";
			 	return false;
			}
		}
		
		
		return true;
	}
	
	
	
	
	$scope.calculateTotalAmountUpToDate = function() {
		
		$scope.billData.totalAmountUpToDate = $scope.billData.billItems.reduce(
				function (totalAmountUpToDate,billItem) { return parseInt(totalAmountUpToDate) + parseInt(billItem.amountUptodate?billItem.amountUptodate :0) ; }, 0);
	}
	
	$scope.calculateTotalAmount = function() {
		var totalAmt=parseFloat(0).toFixed(0);
	angular.forEach($scope.workTemplateItems, function(workTemplateItem) {
		var amt=workTemplateItem.amount;
		if(null!= amt && amt!=''){
			totalAmt=(parseFloat(totalAmt)+parseFloat(amt)).toFixed(0);
		}
	});

	$scope.workData.estimatedAmount=totalAmt;
	$scope.estimatedAmountInLakh=totalAmt/100000;
	if(null!= $scope.workData.totalLabourComponent){
		$scope.labourInPctg=(($scope.workData.totalLabourComponent/totalAmt)*100).toFixed(2);
		$scope.mnregs=($scope.workData.totalLabourComponent/0.6).toFixed(2);
		$scope.material=($scope.mnregs-$scope.workData.totalLabourComponent).toFixed(2);
	}
	$scope.calculateExpectedTenderedAmt();
	$scope.calculateOverheadChargesAmt();
	$scope.calculateLabourWelfareComponentAmt();
	$scope.calculateApplicableGstAmt();
	$scope.calculateWorkChargeContingencyAmt();
	$scope.calculateAdministrativeExpenditureAmt();
}

	$scope.initializeAllAmount = function() {
		$scope.workData.estimatedAmount=parseFloat(0).toFixed(0);
		$scope.estimatedAmountInLakh=parseFloat(0).toFixed(0);
		$scope.workData.expectedTenderedAmt=parseFloat(0).toFixed(0);
		$scope.workData.overheadChargesAmt=parseFloat(0).toFixed(0);
		$scope.workData.labourWelfareComponentAmt=parseFloat(0).toFixed(0);
		$scope.workData.labourWelfareComponentAmt=parseFloat(0).toFixed(0);
		$scope.workData.applicableGstAmt=parseFloat(0).toFixed(0);
		$scope.workData.workChargeContingencyAmt=parseFloat(0).toFixed(0);
		$scope.workData.administrativeExpenditureAmt=parseFloat(0).toFixed(0);
		$scope.calculateGrandTotal();
	}
	
$scope.calculateExpectedTenderedAmt = function() {
	$scope.tenderRateGreaterThan10=false;
	if(null!= $scope.workData.expectedTenderedRatePer && $scope.workData.expectedTenderedRatePer<=10){
		var amt=parseFloat(0).toFixed(0);
		if(null!= $scope.workData.expectedTenderedRatePer && null!=$scope.workData.estimatedAmount){
			var amount=$scope.workData.expectedTenderedRatePer*0.01*$scope.workData.estimatedAmount;
			amt=parseFloat(amount).toFixed(0);
		}
		$scope.workData.expectedTenderedAmt=amt;
		$scope.calculateApplicableGstAmt();
		$scope.calculateGrandTotal();
	}else if(null!= $scope.workData.expectedTenderedRatePer && $scope.workData.expectedTenderedRatePer>10){
		$scope.tenderRateGreaterThan10=true;
	}
}

$scope.calculateOverheadChargesAmt = function() {
	var amt=parseFloat(0).toFixed(0);
	if(null!= $scope.workData.overheadChargesPer && null!=$scope.workData.estimatedAmount){
		var amount=$scope.workData.overheadChargesPer*0.01*$scope.workData.estimatedAmount;
		amt=parseFloat(amount).toFixed(0);
	}
	$scope.workData.overheadChargesAmt=amt;
	$scope.calculateGrandTotal();
}

$scope.calculateLabourWelfareComponentAmt = function() {
	var amt=parseFloat(0).toFixed(0);
	if(null!= $scope.workData.labourWelfareComponentPer && null!=$scope.workData.estimatedAmount){
		var amount=$scope.workData.labourWelfareComponentPer*0.01*$scope.workData.estimatedAmount;
		amt=parseFloat(amount).toFixed(0);
	}
	$scope.workData.labourWelfareComponentAmt=amt;
	$scope.calculateGrandTotal();
}

$scope.calculateApplicableGstAmt = function() {
	var amt=parseFloat(0).toFixed(0);
	if(null!= $scope.workData.applicableGstPer && null!=$scope.workData.estimatedAmount && null!=$scope.workData.expectedTenderedAmt){
	    var consolidateAmt=parseFloat($scope.workData.estimatedAmount)+parseFloat($scope.workData.expectedTenderedAmt);
		var amount=$scope.workData.applicableGstPer*0.01*consolidateAmt;
		amt=parseFloat(amount).toFixed(0);
	}
	$scope.workData.applicableGstAmt=amt;
	$scope.calculateGrandTotal();
}

$scope.calculateWorkChargeContingencyAmt = function() {
	var amt=parseFloat(0).toFixed(0);
	if(null!= $scope.workData.workChargeContingencyPer && null!=$scope.workData.estimatedAmount){
		var amount=$scope.workData.workChargeContingencyPer*0.01*$scope.workData.estimatedAmount;
		amt=parseFloat(amount).toFixed(0);
	}
	$scope.workData.workChargeContingencyAmt=amt;
	$scope.calculateGrandTotal();
}

$scope.calculateAdministrativeExpenditureAmt = function() {
	var amt=parseFloat(0).toFixed(0);
	if(null!= $scope.workData.administrativeExpenditurePer && null!=$scope.workData.estimatedAmount){
		var amount=$scope.workData.administrativeExpenditurePer*0.01*$scope.workData.estimatedAmount;
		amt=parseFloat(amount).toFixed(0);
	}
	$scope.workData.administrativeExpenditureAmt=amt;
	$scope.calculateGrandTotal();
}

$scope.calculateGrandTotal = function() {
	var amt=parseFloat(0).toFixed(0);
	if(null!= $scope.workData.estimatedAmount && null!=$scope.workData.expectedTenderedAmt && null!= $scope.workData.overheadChargesAmt && null!= $scope.workData.labourWelfareComponentAmt && null!= $scope.workData.applicableGstAmt && null!= $scope.workData.workChargeContingencyAmt && null!= $scope.workData.administrativeExpenditureAmt && null!= $scope.workData.othersCharges){
		amt=(parseFloat($scope.workData.estimatedAmount)+parseFloat($scope.workData.expectedTenderedAmt)+parseFloat($scope.workData.overheadChargesAmt)+parseFloat($scope.workData.labourWelfareComponentAmt)+parseFloat($scope.workData.applicableGstAmt)+parseFloat($scope.workData.workChargeContingencyAmt)+parseFloat($scope.workData.administrativeExpenditureAmt)+parseFloat($scope.workData.othersCharges)).toFixed(0);
	}
	$scope.workData.grandTotal=amt;
}

$scope.calculateTotalLabourComponent = function() {
	var totalAmt=0;
angular.forEach($scope.workTemplateItems, function(workTemplateItem) {
	var amt=workTemplateItem.labourComponentValue;
	if(null!= amt){
		totalAmt=(parseFloat(totalAmt)+parseFloat(amt)).toFixed(2);
	}
});
$scope.workData.totalLabourComponent=totalAmt;
$scope.mnregs=($scope.workData.totalLabourComponent/0.6).toFixed(2);
$scope.material=($scope.mnregs-$scope.workData.totalLabourComponent).toFixed(2);
if(null!= $scope.workData.estimatedAmount){
	$scope.labourInPctg=((totalAmt/$scope.workData.estimatedAmount)*100).toFixed(2);
	$scope.otherHead=($scope.workData.estimatedAmount-$scope.material).toFixed(2);
}
}
	
    $scope.calculateQuantity = function(billItem) {
    	
    	var no=0;
    	var length=0;
    	var width=0;
    	var heightDepth=0;
    	
    	if(billItem.no){
    		no=billItem.no;
    	}
    	if(billItem.measureLength){
    		length=billItem.length;
    	}else{
    		length=1;
    	}
    	
    	if(billItem.measureWidth){
    		width=billItem.width;
    	}else{
    		width=1
    	}
    	if(billItem.measureHeightDepth){
    		heightDepth=billItem.heightDepth;
    	}else{
    		heightDepth=1;
    	}
    		billItem.quantity = parseFloat(no*length*width*heightDepth).toFixed(2);
    		$scope.calculateAmount(billItem);
	}
    
$scope.calculateAmount = function(billItem) {
    	
    	var rate=0;
    	var no=0;
    	var length=0;
    	var width=0;
    	var heightDepth=0;
    	
    	if(billItem.rate){
    		rate=billItem.rate;
    	}
    	
    	if(billItem.no){
    		no=billItem.no;
    	}
    	if(billItem.measureLength){
    		length=billItem.length;
    	}else{
    		length=1;
    	}
    	
    	if(billItem.measureWidth){
    		width=billItem.width;
    	}else{
    		width=1
    	}
    	if(billItem.measureHeightDepth){
    		heightDepth=billItem.heightDepth;
    	}else{
    		heightDepth=1;
    	}
    		billItem.amount=parseFloat(rate*(no*length*width*heightDepth)).toFixed(0);
    		$scope.calculateTotalAmount();
	}

$scope.calculateSORQuantity = function(billItem) {
	
	var no=0;
	var length=0;
	var width=0;
	var heightDepth=0;
	
	if(billItem.no){
		no=billItem.no;
	}
	if(billItem.length){
		length=billItem.length;
	}else{
		length=1;
	}
	
	if(billItem.width){
		width=billItem.width;
	}else{
		width=1
	}
	if(billItem.heightDepth){
		heightDepth=billItem.heightDepth;
	}else{
		heightDepth=1;
	}	
		$scope.workTemplateItems=$scope.itemGroupData;
		billItem.quantity = parseFloat(no*length*width*heightDepth).toFixed(2);
		$scope.calculateSORAmount(billItem);
}

$scope.calculateSORAmount = function(billItem) {
	
	var rate=0;
	var no=0;
	var length=0;
	var width=0;
	var heightDepth=0;
	
	if(billItem.rate){
		rate=billItem.rate;
	}
	
	if(billItem.no){
		no=billItem.no;
	}
	if(billItem.length){
		length=billItem.length;
	}else{
		length=1;
	}
	
	if(billItem.width){
		width=billItem.width;
	}else{
		width=1
	}
	if(billItem.heightDepth){
		heightDepth=billItem.heightDepth;
	}else{
		heightDepth=1;
	}
		billItem.amount=parseFloat(rate*(no*length*width*heightDepth)).toFixed(0);
		$scope.calculateTotalAmount();
}
	
 
	
	$scope.calculateTotalAmountPreviousBill = function(billItem) {
		
		$scope.billData.totalAmountPreviousBill = $scope.billData.billItems.reduce(function (totalAmountPreviousBill,billItem) { return totalAmountPreviousBill + (billItem.amountPreviousBill ? parseFloat(billItem.amountPreviousBill) : 0); }, 0);
	}
	$scope.itemCount=0;

	$scope.addBillItemEntry = function() {			
    	//$scope.billData.billItems.push({});
    	var billItem={};
    	billItem.itemCount=$scope.itemCount+1
    	$scope.billData.billItems.push(billItem);
	};
	
	

	$scope.removeBillItemEntry = function(index) {
		$scope.billItem={};
		$scope.billItem.itemCount={};
		$scope.billItem.itemCount = $scope.itemCount - 1;
		$scope.billData.billItems.splice(index, 1);
		$scope.calculateTotalAmountUpToDate();$scope.calculateTotalAmountPreviousBill();
	};
	
	$scope.removeWorkEstimationItemEntry = function(index) {
		var element=$scope.workTemplateItems[index];
		if(null!=element.hasChild && element.hasChild){
			var parentId=element.parentId;
			if(null!= parentId){
				var parentItem=$filter('filter')($scope.workTemplateItems, {id: parentId}, true)[0];
				if(parentItem.childsCount>1){
					
					var childItems=$filter('filter')($scope.workTemplateItems, {parentId: element.id}, true);
					
					angular.forEach(childItems, function(value, key) {
						$scope.workTemplateItems.splice(index+1, 1);
					});
					
					parentItem.childsCount=parentItem.childsCount-1;
					$scope.workTemplateItems.splice(index, 1);
					
					var lastChildItem=childItems[childItems.length-1];
					lastChildItem.lastElement=true;
					
				}else{
					alert("You can not remove last child. Remove Parent Directly or add another child for removing this one.")
					return;
				}
			}else{
				var childItems=$filter('filter')($scope.workTemplateItems, {parentId: element.id}, true);
				angular.forEach(childItems, function(value, key) {
					if(childItems[key].hasChild){
						var childsChildItems=$filter('filter')($scope.workTemplateItems, {parentId: childItems[key].id}, true);
						angular.forEach(childsChildItems, function(value, key) {
							$scope.workTemplateItems.splice(index+2, 1);
						});
						$scope.workTemplateItems.splice(index+1, 1);
						var lastChildItem=childsChildItems[childsChildItems.length-1];
						lastChildItem.lastElement=true;
					}else{
						$scope.workTemplateItems.splice(index+1, 1);
					}
				});
				$scope.workTemplateItems.splice(index, 1);
				var lastChildItem=childItems[childItems.length-1];
				lastChildItem.lastElement=true;
			}
		}else{
			var parentIds=element.parentId;
			if(null!= parentIds){
				var parentItem=$filter('filter')($scope.workTemplateItems, {id: parentIds}, true)[0];
				if(null!= parentItem && parentItem.hasChild){
					if(parentItem.childsCount>1){
						parentItem.childsCount=parentItem.childsCount-1;
						$scope.workTemplateItems.splice(index, 1);
						var childItems=$filter('filter')($scope.workTemplateItems, {parentId: parentIds}, true);
						var lastChildItem=childItems[childItems.length-1];
						lastChildItem.lastElement=true;
					}else{
						alert("You can not remove last child. Remove Parent Directly or add another child for removing this one.")
						return;
					}
				}
			 }else{
				 $scope.workTemplateItems.splice(index, 1); 
			 }
		  }
		$scope.calculateTotalAmount();
		$scope.calculateTotalLabourComponent();
	};
	
	$scope.loadWorkDetail = function() {		
		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);
		
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$scope.workTenderBean = {};
			$scope.workAgreementBean = {};
			$scope.contratorDepositData = {};
			$scope.workTenderBean.contratorDepositsList = [];
			$scope.workData.issuingAuthorityId = $scope.workData.issuingAuthorityId+"";
			$loading.finish('sample-1');
		});
	};
	
	

	
	
	$scope.loadWorkDetailForNewWorkAgreement = function() {		
		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);		
		response.success(function(data, status, headers, config) {
			$scope.workData = data;			
			$scope.workAgreementBean = {};
			$loading.finish('sample-1');
		}).
		then(function (){
			$loading.start('sample-1');			
			var response1 = $http.get('fetchPhysicalStageByWorkTypeId/'+$scope.workData.workTypeBean.workTypeId);		
			response1.success(function(data, status, headers, config) {
				$scope.physicalStages = data;
				$scope.revisedPhysicalStages = data;
				$loading.finish('sample-1');
				});
			});
	};
	
	$scope.loadWorkDetailForViewWorkAgreement = function() {		
		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetails/'+$routeParams.id);		
		response.success(function(data, status, headers, config) {
			$scope.workData = data;			
			$loading.finish('sample-1');
		});
	};
	// Rakesh
	$scope.loadWorkDetailForViewWorkAgreementByTender = function() {	
		// alert($routeParams.tenderId);
		$loading.start('sample-1');
		var response = $http.get('fetchWorkDetailsByTender/'+$routeParams.workId+"/"+$routeParams.tenderId);		
		response.success(function(data, status, headers, config) {
			$scope.workData = data;			
			$loading.finish('sample-1');
		});
	};
	//
	$scope.loadWorkDetailByDistricts = function(district) {
		// alert('sss');
		// alert($scope.selectedDistrics);
		$loading.start('sample-1');
		// var response = $http.get('fetchWorkDetailsDistrics/'+districts;
// var arr = new Array($scope.selectedDistrics);
// alert('Array=='+arr);
		// var myString = $scope.selectedDistrics;
		district = '[' + district +']';
		var json = $.parseJSON(district);
		// alert(json);
	    // result = array.map(function (a) { return a.location_id; }).join();
		var responsePromise = $http.post('fetchWorkDetailsDistrics', json);
		responsePromise.success(function(data, status, headers, config) {
			// alert('done');
			$scope.listOfWorksByDistrict = data;
			/*
			 * for(var i=0;$scope.listOfWorksByDistrict.length;i++){
			 * //$('#loadwork').append(new Option('optionName', 'optionValue'));
			 * $('#yourSelectBoxId').append("<option value='Saab'>Saab</option>"); }
			 */
			$loading.finish('sample-1');
		});
	};
	
	//
	// $scope.selectedDistrics = [];
	    $scope.addWorkDistrict = function(district) {	
	    	// alert('Angular '+district);
	    	
		// $scope.selectedDistrics = [];
		// $scope.selectedDistrics.push(district);
		// $scope.selectedDistrics=district;
		// alert($scope.selectedDistrics);
		
		// $scope.loadWorkDetailByDistricts(district);
		
		// alert($routeParams.tenderId);
		/*
		 * $loading.start('sample-1'); //var response =
		 * $http.get('fetchWorkDetailsDistrics/'+districts; var responsePromise =
		 * $http.post('fetchWorkDetailsDistrics', districts);
		 * response.success(function(data, status, headers, config) {
		 * $scope.workData = data; $loading.finish('sample-1'); });
		 */
	};
	
	$scope.loadFinancialMileStone = function() {		
		$loading.start('sample-1');
		var response = $http.get('fetchFinancialMileStone');		
		response.success(function(data, status, headers, config) {
			$scope.financialStages = data;
			$scope.revisedFinancialStages = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadWorkAgreementDetail = function() {		
		$loading.start('sample-1');
		var response = $http.get('fetchWorkAgreementDetails/'+$routeParams.id);		
		response.success(function(data, status, headers, config) {
			$scope.workAgreementBean = data;
			$scope.financialStages = data.workFinancialMileStoneBeanList;
			$scope.physicalStages = data.workPhysicalMileStoneBeanList;			
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadWorkAgreementDetailByTenderId = function() {		
		// alert('$routeParams.tenderId '+$routeParams.tenderId);
		$loading.start('sample-1');
		var response = $http.get('fetchWorkAgreementDetailsByTenderId/'+$routeParams.tenderId);		
		response.success(function(data, status, headers, config) {
			$scope.workAgreementBean = data;
			$scope.financialStages = data.workFinancialMileStoneBeanList;
			$scope.physicalStages = data.workPhysicalMileStoneBeanList;			
			$loading.finish('sample-1');
		});
	};
	
$scope.loadPhysicalMileStone = function(workTypeId) {
		$loading.start('sample-1');		
		var response = $http.get('fetchPhysicalStageByWorkTypeId/'+workTypeId);		
		response.success(function(data, status, headers, config) {
			$scope.physicalStages = data;
			$scope.revisedPhysicalStages = data;
			$loading.finish('sample-1');
		});
	};	
	
	$scope.loadMilestoneRevisionHistory = function() {		
		$loading.start('sample-1');
		var response = $http.get('fetchMilestoneRevisionHistory/'+$scope.workAgreementBean.workAgreementId);		
		response.success(function(data, status, headers, config) {
			$scope.milestoneRevisionHistory = data;			
			$loading.finish('sample-1');
		});
	};	
	
	$scope.loadWorkAgreementRevisionDetails = function(revisionId) {		
		$loading.start('sample-1');		
		var response = $http.get('fetchWorkAgreementRevisionDetails/'+revisionId);		
		response.success(function(data, status, headers, config) {
			$scope.workAgreementRevisionBean = data;
			$scope.viewDetails=true;
			$loading.finish('sample-1');
		});
	};	
	
	$scope.validateWorkAgreementDetailsTab = function() {
		$timeout(function(){ 
			$scope.onContinue = true;
			if (
				($scope.workAgreementBean.agreementNumber!=null || $scope.workAgreementBean.agreementNumber!=undefined) &&
				($scope.workAgreementBean.agreementDate!=null || $scope.workAgreementBean.agreementDate!=undefined) &&
				($scope.workAgreementBean.tentativeCompletionDate!=null || $scope.workAgreementBean.tentativeCompletionDate!=undefined) &&
				($scope.workAgreementBean.writtenOrderDate!=null || $scope.workAgreementBean.writtenOrderDate!=undefined)
			) {
				$('.nav-pills .nav-item:nth-child(1)').find('a').addClass('disabled');
				$('.nav-pills .nav-item:nth-child(2)').find('a').removeClass('disabled');				
				$('.nav-pills .active').parent().next('li').find('a').trigger('click');
			}
		},0);
	};
	
	$scope.validateWorkAgreementMileStoneTab = function() {
		$timeout(function(){ 
			$scope.onBack = true;			
			$('.nav-pills .nav-item:nth-child(1)').find('a').removeClass('disabled');
			$('.nav-pills .nav-item:nth-child(2)').find('a').addClass('disabled');		
			$('.nav-pills .active').parent().prev('li').find('a').trigger('click');			
		},0);
	};
	
	$scope.addWorkAgreement = function(isValid) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to save the data?")) {			
			if($scope.saveAsDraft == true) {				 
				$scope.workAgreementBean.workAgreementStatusId = 1;
			}
			else if($scope.submit == true) {
				$scope.workAgreementBean.workAgreementStatusId = 2;
			}			
			$loading.start('sample-1');
			$scope.workAgreementBean.workId = $routeParams.id;
			$scope.workAgreementBean.tenderId = $scope.workTenderBean.tenderId;
			$scope.workAgreementBean.workFinancialMileStoneBeanList = $scope.financialStages;
			$scope.workAgreementBean.workPhysicalMileStoneBeanList = $scope.physicalStages;
			
			var responsePromise = $http.post('addWorkAgreement', $scope.workAgreementBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#workAgreementList';
				}
				$loading.finish('sample-1');
			});			 
		}
		else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
	};
	
	$scope.addRevisedWorkAgreement = function(isValid) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			$scope.workAgreementBean.workId = $routeParams.id;			
			$scope.workAgreementBean.workFinancialMileStoneBeanList = $scope.revisedFinancialStages;
			$scope.workAgreementBean.workPhysicalMileStoneBeanList = $scope.revisedPhysicalStages;
			
			var responsePromise = $http.post('addRevisedWorkAgreement', $scope.workAgreementBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#viewWorkAgreement/'+$routeParams.id;
				}
				$loading.finish('sample-1');
			});			 
		}
	};
	
	$scope.updateWorkAgreement = function(isValid) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to save the data?")) {
			if($scope.saveAsDraft == true) {				 
				$scope.workAgreementBean.workAgreementStatusId = 1;
			}
			else if($scope.submit == true) {
				$scope.workAgreementBean.workAgreementStatusId = 2;
			}			
			$loading.start('sample-1');
			$scope.workAgreementBean.workId = $routeParams.id;
			$scope.workAgreementBean.tenderId = $scope.workTenderBean.tenderId;			
			$scope.workAgreementBean.workFinancialMileStoneBeanList = $scope.financialStages;
			$scope.workAgreementBean.workPhysicalMileStoneBeanList = $scope.physicalStages;
			
			var responsePromise = $http.post('updateWorkAgreement', $scope.workAgreementBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#workAgreementList';
				}
				$loading.finish('sample-1');
			});			 
		}
		
		else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
	};
	
	$scope.loadAdministrativeDetail = function() {
		$loading.start('sample-1');
		var response = $http.get('fetchAdministrativeDetailsByWorkId/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.administrativeData = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadBlocksByDistrict = function(districtId) {

		$loading.start('sample-1');
		$scope.workData.blockId = "";
		var response = $http.get('fetchBlocksByDistrict/'+districtId);
		response.success(function(data, status, headers, config) {
			$scope.blocks = data;
			$loading.finish('sample-1');
		});
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
	
	
	 $scope.searchByWorkName = function(){
		 $loading.start('sample-1');
		 if($('#searchBox').val().trim()!=""){
			 reDraw();
		 }
		 else{
			 $loading.finish('sample-1');
		 }
	 };
	
	$scope.addBillData = function(billForm, isValid) {

		$scope.errorMessage1 = null;
		
		angular.forEach($scope.billData.billItems, function(billItem) {
			if(billItem.amountPreviousBill && billItem.amountUptodate) {
				if(parseInt(billItem.amountPreviousBill) > parseInt(billItem.amountUptodate)) {
					$scope.errorMessage1 = "Previous bill amount cannot be greater than upto date Amount";
					isValid = false;
					return false;
				}
			}
		});
		
		$scope.errorMessage2 = null;
		if($scope.billData.remainingAmountForPaymentWithoutGst) {
			if(parseInt($scope.billData.remainingAmountForPaymentWithoutGst) < 0 ) {
			 	$scope.errorMessage2 = "You have negative remaining amount, and new bill can’t be generated";
			 	isValid = false;
				return false;
			}
		}
		
		$scope.errorMessage10 = null;
		if($scope.lastBillData.statusId) {
			if($scope.lastBillData.statusId ==1 ) {
			 	$scope.errorMessage10 = "Your previous bill is in draft stage,so you can't generate new Bill!";
			 	isValid = false;
				return false;
			}
		}
	
		
		angular.forEach($scope.billData.billItems, function(billItem) {
			return $scope.amountValidateEstimation(billItem);
		});
		
		 if (!isValid) {
			return false; 
		 }
		 
		 var msg = "";
		  if($scope.saveAsDraft==true){
			  msg= "Do you want to save the bill ? Have you filled Deductions ?  ";
			  
		  }
		  else if($scope.submitted==true){
			  msg= "Do you really want to submit the bill ?  Have you filled Deductions ? ";
		  }
		  
		  else if($scope.forwardForInspection==true){
			  msg= "Do you really want to forward bill for Inspection ?";
			  
		  }
		  else if($scope.forwardForPayment==true){
			  msg= "Do you really want to forward bill for Payment ?  Have you filled Deductions ? ";
		  }
		  
		  if($scope.isRevised==true){
			  $scope.billData.isRevised = true;
		  }
		 
		  if (confirm(msg)) {
			  
			$loading.start('sample-1');
			$scope.billData.workId = $routeParams.id;
			if($scope.saveAsDraft==true){
				$scope.billData.status = 'Saved As Draft';
				$scope.billData.statusId = 1;
			}

			else if($scope.submitted==true){
				$scope.billData.status = 'submitted';
				$scope.billData.statusId = 2;
			}
			
			else if($scope.forwardForInspection==true){
				$scope.billData.status = 'Fowarded For Inspection';
				$scope.billData.statusId = 3;
			}
			else if($scope.forwardForPayment==true){
				$scope.billData.status = 'Fowarded For Payment';
				$scope.billData.statusId = 4;
			}
			
			if($scope.lastBillData==''){
				$scope.billData.manualBill = true;
				 
			}
			
			var responsePromise = $http.post('saveBillData', $scope.billData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#searchWorkForBill';
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
				}, 30000);
				$loading.finish('sample-1');
			});
		}
		  
		  else {
			  $scope.saveAsDraft = false;
			  $scope.submitted = false;
			  $scope.forwardForInspection=false;
			  $scope.forwardForPayment=false;
		  }
	};
	
	
	
	$scope.addBillDataContractor = function(billForm, isValid) {

		$scope.errorMessage1 = null;
		
		angular.forEach($scope.billData.billItems, function(billItem) {
			if(billItem.amountPreviousBill && billItem.amountUptodate) {
				if(parseInt(billItem.amountPreviousBill) > parseInt(billItem.amountUptodate)) {
					$scope.errorMessage1 = "Previous bill amount cannot be greater than upto date Amount";
					isValid = false;
					return false;
				}
			}
		});
		
		$scope.errorMessage2 = null;
		if($scope.billData.remainingAmountForPaymentWithoutGst) {
			if(parseInt($scope.billData.remainingAmountForPaymentWithoutGst) < 0 ) {
			 	$scope.errorMessage2 = "You have negative remaining amount, and new bill can’t be generated";
			 	isValid = false;
				return false;
			}
		}
		
		$scope.errorMessage10 = null;
		if($scope.lastBillData.statusId) {
			if($scope.lastBillData.statusId ==1 ) {
			 	$scope.errorMessage10 = "Your previous bill is in draft stage,so you can't generate new Bill!";
			 	isValid = false;
				return false;
			}
		}
	
		
		angular.forEach($scope.billData.billItems, function(billItem) {
			return $scope.amountValidateEstimation(billItem);
		});
		
		 if (!isValid) {
			return false; 
		 }
		 
		 var msg = "";
		  if($scope.saveAsDraft==true){
			  msg= "Do you want to save the bill ? Have you filled Deductions ?  ";
			  
		  }
		  else if($scope.submitted==true){
			  msg= "Do you really want to submit the bill ?  Have you filled Deductions ? ";
		  }
		  
		  else if($scope.forwardForInspection==true){
			  msg= "Do you really want to forward bill for Inspection ?";
			  
		  }
		  else if($scope.forwardForPayment==true){
			  msg= "Do you really want to forward bill for Payment ?  Have you filled Deductions ? ";
		  }
		  
		  if($scope.isRevised==true){
			  $scope.billData.isRevised = true;
		  }
		 
		  if (confirm(msg)) {
			  
			$loading.start('sample-1');
			$scope.billData.workId = $routeParams.id;
			if($scope.saveAsDraft==true){
				$scope.billData.status = 'Saved As Draft';
				$scope.billData.statusId = 1;
			}

			else if($scope.submitted==true){
				$scope.billData.status = 'submitted';
				$scope.billData.statusId = 11;
			}
			
			else if($scope.forwardForInspection==true){
				$scope.billData.status = 'Fowarded For Inspection';
				$scope.billData.statusId = 3;
			}
			else if($scope.forwardForPayment==true){
				$scope.billData.status = 'Fowarded For Payment';
				$scope.billData.statusId = 4;
			}
			
			if($scope.lastBillData==''){
				$scope.billData.manualBill = true;
				 
			}
			
			var responsePromise = $http.post('saveBillDataContractor', $scope.billData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#searchWorkForBillContractor';
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
				}, 30000);
				$loading.finish('sample-1');
			});
		}
		  
		  else {
			  $scope.saveAsDraft = false;
			  $scope.submitted = false;
			  $scope.forwardForInspection=false;
			  $scope.forwardForPayment=false;
		  }
	};
	
	
	$scope.addWorkEstimationData = function(form, isValid) {

		if (!isValid){ 
			return false;
			}
			
			if ($scope.tenderRateGreaterThan10){ 
			return false;
			}
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			$scope.workData.workId = $routeParams.id;
			$scope.workData.workTemplateItems=$scope.workTemplateItems;
			if(null==$scope.workData.estimationType)
			$scope.workData.estimationType='ORIGINAL';
			
			// NON-SOR FLAG - Explicitly check karo
			if ($scope.workData.isNonSorSubmit === true) {
				// Non-SOR button se aaya
				console.log('Non-SOR submit detected');
				$scope.workData.hasNonSorItems = true;
			} 

			// Reset flag
			$scope.workData.isNonSorSubmit = undefined;
			
			// NON-SOR FLAG - Explicitly check karo
			if ($scope.workData.tenPercentCheck === true) {
				// Non-SOR button se aaya
				console.log('Non-SOR submit detected');
				$scope.workData.tenPercentCheck = true;
			} 

			// Reset flag
			$scope.workData.tenPercentCheck = undefined;
			
			if ($scope.workData.revert != null && $scope.workData.revert != undefined) {
				$scope.workData.revert = $scope.workData.revert;
			} 
			//$scope.workData.estimationId=null;
			if($scope.saveAsDraft==true){
				$scope.workData.status = 'Draft';
			}
			if($scope.revert==true){
				$scope.workData.status = 'Revert';
			}
			if($scope.submit==true){
				$scope.workData.status = 'Submitted';
			}
			var responsePromise = $http.post('saveWorkEstimationData', $scope.workData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#viewPendingWorkEstimations';
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
		
		else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
	};
	
	$scope.saveRevisedWorkEstimationData = function(isValid) {

		if (!isValid || $scope.tenderRateGreaterThan10) 
			return false;
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			$scope.workData.workId = $routeParams.id;
			$scope.workData.workTemplateItems=$scope.workTemplateItems;
			$scope.workData.estimationType=$routeParams.estimationType;
			$scope.workData.parentId=$scope.workData.estimationId;
			$scope.workData.estimationId=null;
			
			if($scope.saveAsDraft==true){
				$scope.workData.status = 'Draft';
			}
			if($scope.submit==true){
				$scope.workData.status = 'Submitted';
			}
			var responsePromise = $http.post('saveWorkEstimationData', $scope.workData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#viewPendingWorkEstimations';
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
		
		else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
	};
	
	$scope.deleteBill = function(id) {		
		if (confirm("Are you sure to delete this entry?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('deleteBill/'+ id);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#viewBills';
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
	
	
	// not req check
	/*
	 * $scope.loadEditBillForm = function() {
	 * 
	 * var response = $http.get('fetchWorkDetailsByBillId/'+$routeParams.id);
	 * response.success(function(data, status, headers, config) {
	 * $scope.workData = data;
	 * 
	 * if($scope.workData.agencyTypeBean.agencyTypeId == 1) { // RES Nivida -
	 * tender amount $scope.remainingAmountForPayment =
	 * $scope.workData.tenderCost -
	 * ($scope.lastBillData.totalAmountPreviousBills?$scope.lastBillData.totalAmountPreviousBills:0); }
	 * else { // RES Vibhagiya - GP -- Administrative Sanction Amount
	 * $scope.remainingAmountForPayment = $scope.workData.totalCostString -
	 * ($scope.lastBillData.totalAmountPreviousBills?$scope.lastBillData.totalAmountPreviousBills:0); }
	 * 
	 * $loading.finish('sample-1'); }).then(function (){
	 * 
	 * 
	 * }); };
	 */
	
	
		$scope.printWindow = function(url){
			
	    window.open("#printBill/"+ $routeParams.id, '_blank','heigth=600,width=600'); 
	} 
		
	$scope.editSaveBillData = function(form, isValid) {

		$scope.errorMessage1 = null;
		
		angular.forEach($scope.billData.billItems, function(billItem) {
			if(billItem.amountPreviousBill && billItem.amountUptodate) {
				if(parseInt(billItem.amountPreviousBill) > parseInt(billItem.amountUptodate)) {
					$scope.errorMessage1 = "Previous bill amount cannot be greater than upto date Amount";
					isValid = false;
					return false;
				}
			}
		});
		
		$scope.errorMessage2 = null;
		if($scope.billData.remainingAmountForPaymentWithoutGst) {
			if(parseInt($scope.billData.remainingAmountForPaymentWithoutGst) < 0 ) {
			 	$scope.errorMessage2 = "You have negative remaining amount, and new bill can’t be generated";
			 	isValid = false;
				return false;
			}
		}
	
		  if (!isValid)  {
			  return false;
		  }
		 
		  var msg = "";
		  if($scope.saveAsDraft==true){
			  msg= "Do you want to save the bill ?";
			  
		  }
		  else if($scope.submitted==true){
			  msg= "Do you really want to submit the bill ?";
		  }
		  
		  else if($scope.forwardForInspection==true){
			  msg= "Do you really want to forward bill for Inspection ?";
			  
		  }
		  else if($scope.forwardForPayment==true){
			  msg= "Do you really want to forward bill for Payment ?";
		  }
		  
		  if($scope.isRevised==true){
			  $scope.billData.isRevised = true;
		  }

		if (confirm(msg)) {
			$loading.start('sample-1');
			$scope.billData.workId = $routeParams.id;
			if($scope.saveAsDraft==true){
				$scope.billData.status = 'Saved As Draft';
				$scope.billData.statusId = 1;
			}
			else if($scope.submitted==true){
				$scope.billData.status = 'submitted';
				$scope.billData.statusId = 2;
			}
			else if($scope.forwardForInspection==true){
				$scope.billData.status = 'Fowarded For Inspection';
				$scope.billData.statusId = 3;
			}
			else if($scope.forwardForPayment==true){
				$scope.billData.status = 'Fowarded For Payment';
				$scope.billData.statusId = 4;
			}
			
			var responsePromise = $http.post('editSaveBillData', $scope.billData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#viewBills';
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
				$rootScope.responseObject.errorMessage = "Some error occured while saving the edited data";
				$timeout(function() {
					$rootScope.responseObject.errorMessage = null;
				}, 10000);
				$loading.finish('sample-1');
			});
		}
		
		else {
			$scope.saveAsDraft = false;
			$scope.submitted = false;
			$scope.forwardForInspection=false;
			$scope.forwardForPayment=false;
		}



		
	};
	
	
	$scope.editSaveBillDataContractor = function(form, isValid) {

		$scope.errorMessage1 = null;
		
		angular.forEach($scope.billData.billItems, function(billItem) {
			if(billItem.amountPreviousBill && billItem.amountUptodate) {
				if(parseInt(billItem.amountPreviousBill) > parseInt(billItem.amountUptodate)) {
					$scope.errorMessage1 = "Previous bill amount cannot be greater than upto date Amount";
					isValid = false;
					return false;
				}
			}
		});
		
		$scope.errorMessage2 = null;
		if($scope.billData.remainingAmountForPaymentWithoutGst) {
			if(parseInt($scope.billData.remainingAmountForPaymentWithoutGst) < 0 ) {
			 	$scope.errorMessage2 = "You have negative remaining amount, and new bill can’t be generated";
			 	isValid = false;
				return false;
			}
		}
	
		  if (!isValid)  {
			  return false;
		  }
		 
		  var msg = "";
		  if($scope.saveAsDraft==true){
			  msg= "Do you want to save the bill ?";
			  
		  }
		  else if($scope.submitted==true){
			  msg= "Do you really want to submit the bill ?";
		  }
		  
		  else if($scope.forwardForInspection==true){
			  msg= "Do you really want to forward bill for Inspection ?";
			  
		  }
		  else if($scope.forwardForPayment==true){
			  msg= "Do you really want to forward bill for Payment ?";
		  }
		  
		  if($scope.isRevised==true){
			  $scope.billData.isRevised = true;
		  }

		if (confirm(msg)) {
			$loading.start('sample-1');
			$scope.billData.workId = $routeParams.id;
			if($scope.saveAsDraft==true){
				$scope.billData.status = 'Saved As Draft';
				$scope.billData.statusId = 1;
			}
			else if($scope.submitted==true){
				$scope.billData.status = 'submitted';
				$scope.billData.statusId = 11;
			}
			else if($scope.forwardForInspection==true){
				$scope.billData.status = 'Fowarded For Inspection';
				$scope.billData.statusId = 3;
			}
			else if($scope.forwardForPayment==true){
				$scope.billData.status = 'Fowarded For Payment';
				$scope.billData.statusId = 4;
			}
			
			var responsePromise = $http.post('editSaveBillDataContractor', $scope.billData);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#viewBillsContractor';
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
				$rootScope.responseObject.errorMessage = "Some error occured while saving the edited data";
				$timeout(function() {
					$rootScope.responseObject.errorMessage = null;
				}, 10000);
				$loading.finish('sample-1');
			});
		}
		
		else {
			$scope.saveAsDraft = false;
			$scope.submitted = false;
			$scope.forwardForInspection=false;
			$scope.forwardForPayment=false;
		}



		
	};
	
 
	$scope.loadBillDetail = function() {

		$loading.start('sample-1');
		$scope.paymentBean = {};
		$scope.paymentBean.paymentDetailBeanList= [];
		$scope.paymentBean.paymentDetailBeanList [0] = {};
		
		var response = $http.get('fetchBillDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.billData = data;
			$scope.billData.totalAmountUpToDate = 0;
			$scope.billData.totalAmountPreviousBill = 0;
			
			$loading.finish('sample-1');
		});
		
	};

	
	$scope.fetchBillListForWork = function() {

		$loading.start('sample-1');
		
		var response = $http.get('fetchBillListForWork/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.billList = data;
			$loading.finish('sample-1');
		});
		
		
		var response1 = $http.get('fetchBillDeduction/'+$routeParams.id);
		response1.success(function(data, status, headers, config) {
			$scope.deductionBean = data;
			$loading.finish('sample-1');
		});
		
	};
	
	$scope.fetchFileListForWork = function(filesFilter) {
		
		$loading.start('sample-1');
		var response = $http.get('fetchFileListForWork/'+$routeParams.id+'/'+filesFilter);
		response.success(function(data, status, headers, config) {			
			for (var i = 0; i < data.length; i++) {
				if (data[i].date!=null) {
					var dt = data[i].date.split('/');
					data[i].date = new Date(dt[2], dt[1]-1, dt[0]);
				}
			}
			$scope.fileList = data;
			$loading.finish('sample-1');
		});
		
	}	
	
	$scope.loadContengecyData = function() {

		var response = $http.get('fetchBillDetails/'+$routeParams.id);
		
		response.success(function(data, status, headers, config) {
			$scope.billData = data;
			$loading.finish('sample-1');
		}).then(function (){
			$loading.start('sample-1');
			var response = $http.get('fetchContengecyData/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				$scope.contengencyBean = data;
				$scope.contengencyBean.totalConengencyAmount= $scope.billData.work.contingencyAmount;
				$loading.finish('sample-1');
			})
		});
	}; 
	
	$scope.loadPaymentData = function() {

		var response = $http.get('fetchBillDetails/'+$routeParams.id);
		
		response.success(function(data, status, headers, config) {
			$scope.billData = data;
			$loading.finish('sample-1');
		}).then(function (){
			$loading.start('sample-1');
			var response = $http.get('fetchPaymentData/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				$scope.paymentBean = data;
				$loading.finish('sample-1');
			})
		});
	};

	$scope.calculateTotalPaymentAmount = function() {
		$scope.totalAmt = parseFloat(0).toFixed(0);
		angular.forEach($scope.paymentBean.paymentDetailBeanList, function(paymentDetailBean) {
		var amt = paymentDetailBean.amount;
		if(null!= amt){
			$scope.totalAmt = (parseFloat($scope.totalAmt)+parseFloat(amt)).toFixed(0);
		}
	});
}
	
	
	
	function GetFormattedDate(date) {
	    var month = date.getMonth() + 1;
	    var day = date.getDate();
	    var year = date.getFullYear();
	    return  new Date(day + "/" + month + "/" + year);
	}
	
	function parse(dateString) {
		
		var dateParts = dateString.toString().split("/");
		var dateObject = new Date(dateParts[2], dateParts[1] - 1, dateParts[0]); 	
		return dateObject;
	}
	 

	
	
	$scope.saveContengencyData = function(isValid) {
		
		if (!isValid) 
			return false;
			
			var expenditureDate = parse($scope.contengencyBean.expenditureDate);
		    var billDate = parse($scope.billData.billDate);
     //changes given by palak to remove this condition and can not be greater than today date
		/*if (expenditureDate <  billDate) {
			$scope.errorMessage1 = "expenditureDate can not be Before Bill Date";
			return false;
		}*/
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			 
			$scope.contengencyBean.billId = $routeParams.id;	
			
			var responsePromise = $http.post('saveContengencyData', $scope.contengencyBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#viewPayments';
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
 
	$scope.savePaymentData = function(isValid) {
		
		if (!isValid) 
			return false;

		$scope.errorMessage1 = "";
		
		var cashbookDate = parse($scope.paymentBean.cashbookDate);
		var billDate = parse($scope.billData.billDate);
		var today = new Date();
	//changes given by palak to remove this condition and can not be greater than today date
		/*if (cashbookDate <  billDate) {
			$scope.errorMessage1 = "Cashbook Date can not be Before Bill Date";
			return false;
		}*/
		
		if ($scope.totalAmt != parseInt($scope.billData.billAmount)) {
			$scope.errorMessage1 = "Total Amount can not be greater or smaller than Bill Amount";
			return false;
		}
		
		if (cashbookDate > today) {
			$scope.errorMessage1 = "cashbook Date Can not be After Current Date";
			return false;
		}
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
			 
			$scope.paymentBean.billId = $routeParams.id;	
			
			var responsePromise = $http.post('savePaymentData', $scope.paymentBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#viewPayments';
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

	$scope.addpaymentItemEntry = function() {
		
    	$scope.paymentBean.paymentDetailBeanList.push({});
	};
	
	$scope.removepaymentItemEntry = function(index) {
		 
		  if($scope.paymentBean.paymentDetailBeanList.length > 1) {
			$scope.paymentBean.paymentDetailBeanList.splice(index, 1);	
		 }
		
	};
	
	
	$scope.loadBillDetailsforEdit = function() {

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}).
		then(function (){
			/*$loading.start('sample-1');
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response3.success(function(data, status, headers, config) {
						$scope.inspectionByListEE = data;
				});
				
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }*/
			$loading.start('sample-1');
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			/*
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 */
			if($scope.workData.executiveEngineerOfficeId) {
			var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response1.success(function(data, status, headers, config) {
					$scope.measuredByList = data;
			});
			var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response2.success(function(data, status, headers, config) {
				$scope.inspectionByListAE = data;
			});
			
			var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
		  }
			
		}).then(function (){
			$loading.start('sample-1');
			var response = $http.get('fetchBillDetailsForEdit/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				$scope.billData = data;
				$scope.billData.totalAmountUpToDate = 0;
				$scope.billData.totalAmountPreviousBill = 0;
				
				for(i = 0 ; i < $scope.billData.billItems.length; i++) {
					$scope.billData.totalAmountUpToDate = parseInt($scope.billData.totalAmountUpToDate) + parseInt($scope.billData.billItems[i].amountUptodate? $scope.billData.billItems[i].amountUptodate : 0);
					$scope.billData.totalAmountPreviousBill = parseInt($scope.billData.totalAmountPreviousBill) + parseInt($scope.billData.billItems[i].amountPreviousBill ? $scope.billData.billItems[i].amountPreviousBill: 0); 
				}
				
				if($scope.workData.tenderedRateSign == '+') {
					$scope.gt1 = parseFloat(($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountUpToDate).toFixed(0);
					$scope.gt2 = parseFloat(($scope.billData.totalAmountPreviousBill *$scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountPreviousBill).toFixed(0);
				}
				
				if($scope.workData.tenderedRateSign =='-') {
					$scope.gt1 = parseFloat($scope.billData.totalAmountUpToDate - ($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100)).toFixed(0);
					$scope.gt2 =  parseFloat($scope.billData.totalAmountPreviousBill - ($scope.billData.totalAmountPreviousBill * $scope.workData.tenderedRatePer/100)).toFixed(0);
				}
				
				if($scope.workData.tenderedRateSign == null ) {
					$scope.gt1 =  $scope.billData.totalAmountUpToDate  ;
					$scope.gt2 =  ($scope.billData.totalAmountPreviousBill ?  $scope.billData.totalAmountPreviousBill : 0)  ;
				} 
				
				 
				if(Number.isNaN($scope.billData.totalAmountPreviousBill) || $scope.billData.totalAmountPreviousBill == 0) {
					$scope.gt2 = 0;
				}
				
				
				$scope.r1 = parseInt($scope.gt1? $scope.gt1: 0) - parseInt($scope.gt2 ? $scope.gt2: 0) + parseInt($scope.billData.gst ?$scope.billData.gst: 0) + parseInt($scope.billData.addOthers ? $scope.billData.addOthers:0);
			 
				$scope.r2 =  parseInt($scope.r1) - ( parseInt($scope.billData.securityDeposit ? $scope.billData.securityDeposit : 0) + parseInt($scope.billData.incomeTax?$scope.billData.incomeTax: 0) + parseInt($scope.billData.upkar?$scope.billData.upkar:0) + parseInt($scope.billData.royalty?$scope.billData.royalty:0) + parseInt($scope.billData.other?$scope.billData.other:0) + parseInt($scope.billData.costOfBillForm?$scope.billData.costOfBillForm:0 )
									+ parseInt($scope.billData.miscDeposit?$scope.billData.miscDeposit:0) + parseInt($scope.billData.performanceGuarantee?$scope.billData.performanceGuarantee:0) + parseInt($scope.billData.advancePayments?$scope.billData.advancePayments:0) );
				
				
				
				/*
				 * $scope.billData.inspectedBy = {}; // 2 gp - 1,3 RES
				 * if($scope.workData.agencyTypeBean.agencyTypeId == 2) { // GP
				 * //$scope.billData.measurementById =
				 * $scope.workData.subEngineerId+"";
				 * $scope.billData.inspectedBy.id=
				 * $scope.workData.subEngineerId+""; } else {
				 * //$scope.billData.measurementById =
				 * $scope.workData.assistantEngineerId+"";
				 * $scope.billData.inspectedBy.id =
				 * $scope.workData.assistantEngineerId+""; }
				 * 
				 * $scope.billData.measurementById =
				 * $scope.workData.subEngineerId+"";
				 */
				
				$scope.billData.measurementById = data.measurementById+"";
				$scope.billData.inspectedBy.id = data.inspectedBy.id+"";
				if(null!=  data.inspectedByEE){
				$scope.billData.inspectedByEE.id = data.inspectedByEE.id+"";
				}
				
				$loading.finish('sample-1');
			}).then(function (){

				$loading.start('sample-1');
				var response = $http.get('fetchPrevBillDetailsByBillId/'+$routeParams.id);
				response.success(function(data, status, headers, config) {
					 	
					$scope.lastBillData = data;
					//making billing flag as 0 as per the Change Request letter dated 14/09/2021.
					$scope.workData.billingFlag==0;
					
					if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
						// Nivida
						// tender
						// amount
						//take tender cost in place of PAC Amount
						/*$scope.billData.remainingAmountForPayment = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
						$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);*/
						/*	$scope.billData.remainingAmountForPayment = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
						$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);

						 */

						// RES
						// Nivida
						// tender
						// amount
						$scope.workData.billingFlag=0;
						if($scope.workData.billingFlag==0){
							
							if($scope.lastBillData.estimationRevised){
								 //$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
								 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							 }else{
								//take tenderCost in place of probableAmountOfWork
									/* $scope.billData.remainingAmountForPayment = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
													 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);*/
									$scope.billData.remainingAmountForPayment = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
									$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							 }
						}else{

							if($scope.workData.maxBillingAmount==null)  {
								$scope.billData.remainingAmountForPayment = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}
							else{
								$scope.billData.remainingAmountForPayment = ($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}

						}



					} else { // RES Vibhagiya - GP -- Administrative
									// Sanction Amount
						if($scope.lastBillData.estimationRevised){
							 //$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
							 $scope.billData.remainingAmountForPaymentWithoutGst=($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						 }else{
							 $scope.billData.remainingAmountForPayment = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
							$scope.billData.remainingAmountForPaymentWithoutGst = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						 }
					}
					if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
																			// Nivida
																			// tender
																			// amount
					    $scope.billData.totalAmountPreviousBillsWOgstWOaddOthers = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBillsWOgstWOaddOthers:0);
					} else { // RES Vibhagiya - GP -- Administrative Sanction
								// Amount
						$scope.billData.totalAmountPreviousBillsWOgstWOaddOthers = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBillsWOgstWOaddOthers:0);
					}
					
					
					
					$scope.billData.lastBillNo = $scope.lastBillData.billNo;
					$scope.billData.lastBillIndex = $scope.lastBillData.billIndex;
					$scope.billData.lastBillDate =   $scope.lastBillData.billDate;
					
					$loading.finish('sample-1');
					
					// $scope.calculateTotalAmountUpToDate();$scope.calculateTotalAmountPreviousBill();
					
					$scope.validateRemainingAmount();
					
				})
			
			}) ;
			
		})
		
		
	};
	
	
 
	
	$scope.loadBillDetailsforPrint = function() {

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}).
		then(function (){
			$loading.start('sample-1');
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			/*
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 */
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }
			
		}).then(function (){
			$loading.start('sample-1');
			var response = $http.get('fetchBillDetailsForPrint/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				$scope.billData = data;
				$scope.billData.totalAmountUpToDate = 0;
				$scope.billData.totalAmountPreviousBill = 0;
				
				for(i = 0 ; i < $scope.billData.billItems.length; i++) {
					$scope.billData.totalAmountUpToDate = parseInt($scope.billData.totalAmountUpToDate) + parseInt($scope.billData.billItems[i].amountUptodate? $scope.billData.billItems[i].amountUptodate : 0);
					$scope.billData.totalAmountPreviousBill = parseInt($scope.billData.totalAmountPreviousBill) + parseInt($scope.billData.billItems[i].amountPreviousBill ? $scope.billData.billItems[i].amountPreviousBill: 0); 
				}
				
				if($scope.workData.tenderedRateSign == '+') {
					$scope.gt1 = parseFloat(($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountUpToDate).toFixed(0);
					$scope.gt2 = parseFloat(($scope.billData.totalAmountPreviousBill *$scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountPreviousBill).toFixed(0);
				}
				
				if($scope.workData.tenderedRateSign =='-') {
					$scope.gt1 = parseFloat($scope.billData.totalAmountUpToDate - ($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100)).toFixed(0);
					$scope.gt2 =  parseFloat($scope.billData.totalAmountPreviousBill - ($scope.billData.totalAmountPreviousBill * $scope.workData.tenderedRatePer/100)).toFixed(0);
				}
				
				if($scope.workData.tenderedRateSign == null ) {
					$scope.gt1 =  $scope.billData.totalAmountUpToDate  ;
					$scope.gt2 =  ($scope.billData.totalAmountPreviousBill ?  $scope.billData.totalAmountPreviousBill : 0)  ;
				} 
				
				 
				if(Number.isNaN($scope.billData.totalAmountPreviousBill) || $scope.billData.totalAmountPreviousBill == 0) {
					$scope.gt2 = 0;
				}
				
				
				$scope.r1 = parseInt($scope.gt1? $scope.gt1: 0) - parseInt($scope.gt2 ? $scope.gt2: 0) + parseInt($scope.billData.gst ?$scope.billData.gst: 0) + parseInt($scope.billData.addOthers ? $scope.billData.addOthers:0);
			 
				$scope.r2 =  parseInt($scope.r1) - ( parseInt($scope.billData.securityDeposit ? $scope.billData.securityDeposit : 0) + parseInt($scope.billData.incomeTax?$scope.billData.incomeTax: 0) + parseInt($scope.billData.upkar?$scope.billData.upkar:0) + parseInt($scope.billData.royalty?$scope.billData.royalty:0) + parseInt($scope.billData.other?$scope.billData.other:0) + parseInt($scope.billData.costOfBillForm?$scope.billData.costOfBillForm:0 )
									+ parseInt($scope.billData.miscDeposit?$scope.billData.miscDeposit:0) + parseInt($scope.billData.performanceGuarantee?$scope.billData.performanceGuarantee:0) + parseInt($scope.billData.advancePayments?$scope.billData.advancePayments:0) );
				
				$scope.totalC =  parseInt($scope.billData.securityDeposit ? $scope.billData.securityDeposit : 0) + parseInt($scope.billData.incomeTax?$scope.billData.incomeTax: 0) + parseInt($scope.billData.upkar?$scope.billData.upkar:0) + parseInt($scope.billData.royalty?$scope.billData.royalty:0) + parseInt($scope.billData.other?$scope.billData.other:0) + parseInt($scope.billData.costOfBillForm?$scope.billData.costOfBillForm:0 )
						+ parseInt($scope.billData.miscDeposit?$scope.billData.miscDeposit:0) + parseInt($scope.billData.performanceGuarantee?$scope.billData.performanceGuarantee:0) + parseInt($scope.billData.advancePayments?$scope.billData.advancePayments:0) ;
				
				$scope.addGstAndOthers =  parseInt($scope.billData.gst ?$scope.billData.gst: 0) + parseInt($scope.billData.addOthers ? $scope.billData.addOthers:0);
				
				/*
				 * $scope.billData.inspectedBy = {}; // 2 gp - 1,3 RES
				 * if($scope.workData.agencyTypeBean.agencyTypeId == 2) { // GP
				 * //$scope.billData.measurementById =
				 * $scope.workData.subEngineerId+"";
				 * $scope.billData.inspectedBy.id=
				 * $scope.workData.subEngineerId+""; } else {
				 * //$scope.billData.measurementById =
				 * $scope.workData.assistantEngineerId+"";
				 * $scope.billData.inspectedBy.id =
				 * $scope.workData.assistantEngineerId+""; }
				 * 
				 * $scope.billData.measurementById =
				 * $scope.workData.subEngineerId+"";
				 */
				
				$scope.billData.measurementById = data.measurementById+"";
				$scope.billData.inspectedBy.id = data.inspectedBy.id+"";
				if(null!= data.inspectedByEE){
				$scope.billData.inspectedByEE.id = data.inspectedByEE.id+"";
				}
				
				
				$loading.finish('sample-1');
			}).then(function (){

				$loading.start('sample-1');
				var response = $http.get('fetchPrevBillDetailsByBillId/'+$routeParams.id);
				response.success(function(data, status, headers, config) {
					 	
					$scope.lastBillData = data;
					
					if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
																			// Nivida
																			// tender
																			// amount
						
						$scope.billData.remainingAmountForPayment = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
						$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						
						} else { // RES Vibhagiya - GP -- Administrative
									// Sanction Amount
						$scope.billData.remainingAmountForPayment = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
						$scope.billData.remainingAmountForPaymentWithoutGst = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}
					if($scope.lastBillData.estimationRevised){
						if($scope.workData.agencyTypeBean.agencyTypeId == 1){
							$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}else{
							$scope.billData.remainingAmountForPaymentWithoutGst = $scope.lastBillData.finalAsBillingAmount - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}
					 }
					
					$scope.billData.lastBillNo = $scope.lastBillData.billNo;
					$scope.billData.lastBillIndex = $scope.lastBillData.billIndex;
					$scope.billData.lastBillDate =   $scope.lastBillData.billDate;
					
					
					var response1 = $http.get('fetchPaymentData/'+$routeParams.id);
					response1.success(function(data, status, headers, config) {
						$scope.paymentBean = data;
						/*$loading.finish('sample-1');*/
					});
					
					
					$loading.finish('sample-1');
					
					// $scope.calculateTotalAmountUpToDate();$scope.calculateTotalAmountPreviousBill();
					
					$scope.validateRemainingAmount();
					
				})
			
			}) ;
			
		})
		
		
	};
	
	
	// /richa
	
	$scope.loadBillDetailsforEditEstimation = function() {

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			$loading.finish('sample-1');
		}).
		then(function (){
			/*$loading.start('sample-1');
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }*/
			
			$loading.start('sample-1');
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			/*
			 * if($scope.workData.userBean.officeBean) { var response1 =
			 * $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			 * response1.success(function(data, status, headers, config) {
			 * $scope.measuredByList = data; }); }
			 */
			if($scope.workData.executiveEngineerOfficeId) {
			var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response1.success(function(data, status, headers, config) {
					$scope.measuredByList = data;
			});
			var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response2.success(function(data, status, headers, config) {
				$scope.inspectionByListAE = data;
			});
			
			var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
		  }
			
		}).then(function (){
			$loading.start('sample-1');
			var response = $http.get('fetchBillDetailsForEdit/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				$scope.billData = data;
				$scope.billData.totalAmountUpToDate = 0;
				$scope.billData.totalAmountPreviousBill = 0;
				
				for(i = 0 ; i < $scope.billData.billItems.length; i++) {
					$scope.billData.totalAmountUpToDate = parseInt($scope.billData.totalAmountUpToDate) + parseInt($scope.billData.billItems[i].amountUptodate);
					// $scope.billData.totalAmountPreviousBill =
					// parseInt($scope.billData.totalAmountPreviousBill) +
					// parseInt($scope.billData.billItems[i].amountPreviousBill);
					$scope.billData.totalAmountPreviousBill = parseInt($scope.billData.totalAmountPreviousBill) + parseInt($scope.billData.billItems[i].amountPreviousBill ? $scope.billData.billItems[i].amountPreviousBill: 0);
				}
				
				if($scope.workData.tenderedRateSign == '+') {
					$scope.gt1 = parseFloat(($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountUpToDate).toFixed(0);
					$scope.gt2 = parseFloat(($scope.billData.totalAmountPreviousBill *$scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountPreviousBill).toFixed(0);
				}
				
				if($scope.workData.tenderedRateSign =='-') {
					$scope.gt1 = parseFloat($scope.billData.totalAmountUpToDate - ($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100)).toFixed(0);
					$scope.gt2 =  parseFloat($scope.billData.totalAmountPreviousBill - ($scope.billData.totalAmountPreviousBill * $scope.workData.tenderedRatePer/100)).toFixed(0);
				}
				
				if($scope.workData.tenderedRateSign == null ) {
					$scope.gt1 =  $scope.billData.totalAmountUpToDate  ;
					$scope.gt2 =  ($scope.billData.totalAmountPreviousBill ?  $scope.billData.totalAmountPreviousBill : 0)  ;
				} 
				
				 
				if(Number.isNaN($scope.billData.totalAmountPreviousBill) || $scope.billData.totalAmountPreviousBill == 0) {
					$scope.gt2 = 0;
				}
				
				
				$scope.r1 = parseInt($scope.gt1? $scope.gt1: 0) - parseInt($scope.gt2 ? $scope.gt2: 0) + parseInt($scope.billData.gst ?$scope.billData.gst: 0) + parseInt($scope.billData.addOthers ? $scope.billData.addOthers:0);
			 
				$scope.r2 =  parseInt($scope.r1) - ( parseInt($scope.billData.securityDeposit ? $scope.billData.securityDeposit : 0) + parseInt($scope.billData.incomeTax?$scope.billData.incomeTax: 0) + parseInt($scope.billData.upkar?$scope.billData.upkar:0) + parseInt($scope.billData.royalty?$scope.billData.royalty:0) + parseInt($scope.billData.other?$scope.billData.other:0) + parseInt($scope.billData.costOfBillForm?$scope.billData.costOfBillForm:0 )
									+ parseInt($scope.billData.miscDeposit?$scope.billData.miscDeposit:0) + parseInt($scope.billData.performanceGuarantee?$scope.billData.performanceGuarantee:0) + parseInt($scope.billData.advancePayments?$scope.billData.advancePayments:0) );
				
				
				
				/*
				 * $scope.billData.inspectedBy = {}; // 2 gp - 1,3 RES
				 * if($scope.workData.agencyTypeBean.agencyTypeId == 2) { // GP
				 * //$scope.billData.measurementById =
				 * $scope.workData.subEngineerId+"";
				 * $scope.billData.inspectedBy.id=
				 * $scope.workData.subEngineerId+""; } else {
				 * //$scope.billData.measurementById =
				 * $scope.workData.assistantEngineerId+"";
				 * $scope.billData.inspectedBy.id =
				 * $scope.workData.assistantEngineerId+""; }
				 * 
				 * $scope.billData.measurementById =
				 * $scope.workData.subEngineerId+"";
				 */
				
				$scope.billData.measurementById = data.measurementById+"";
				$scope.billData.inspectedBy.id = data.inspectedBy.id+"";
				// $scope.billData.inspectedByEE.id = data.inspectedByEE.id+"";
				if(null!= data.inspectedByEE){
				$scope.billData.inspectedByEE.id = data.inspectedByEE.id+"";
				}
				
				
				$loading.finish('sample-1');
			}).then(function (){

				$loading.start('sample-1');
				var response = $http.get('fetchPrevBillDetailsByBillId/'+$routeParams.id);
				
				response.success(function(data, status, headers, config) {
					 	
					$scope.lastBillData = data;
					$scope.workData.billingFlag==0;
					
					if($scope.workData.agencyTypeBean.agencyTypeId == 1) {/*  // RES
																			// Nivida
																			// tender
																			// amount
						
						$scope.billData.remainingAmountForPayment = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
						$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						
					*/
						  // RES
						// Nivida
						// tender
						// amount
						if($scope.workData.billingFlag==0){
							if($scope.lastBillData.estimationRevised){
								//$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
								$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0)   - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}else{
								$scope.billData.remainingAmountForPayment = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0)   - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0)   - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);	
							}
						}else
						{
							/*$scope.billData.remainingAmountForPayment = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0)   - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
						 $scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0)   - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);*/


							if($scope.workData.maxBillingAmount==null)  {
								$scope.billData.remainingAmountForPayment = ($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.administrationSanctionAmount? $scope.workData.administrationSanctionAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}
							else{
								$scope.billData.remainingAmountForPayment = ($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.totalAmountPreviousBills ? $scope.lastBillData.totalAmountPreviousBills:0);
								$scope.billData.remainingAmountForPaymentWithoutGst=($scope.workData.maxBillingAmount? $scope.workData.maxBillingAmount : 0) - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData.billAmountWithoutGstAndDeductions ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
							}


						}	
					
					
					
					} else { // RES Vibhagiya - GP -- Administrative Sanction
								// Amount
						if($scope.lastBillData.estimationRevised){
							//$scope.billData.remainingAmountForPaymentWithoutGst=$scope.lastBillData.finalAsBillingAmount;
							$scope.billData.remainingAmountForPaymentWithoutGst = $scope.lastBillData.finalAsBillingAmount - $scope.workData.contingencyAmount - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}
						else{
							$scope.billData.remainingAmountForPayment = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
							$scope.billData.remainingAmountForPaymentWithoutGst = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
						}
						
						}
                         
                     if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
																				// Nivida
																				// tender
																				// amount
						
						$scope.billData.totalAmountPreviousBillsWOgstWOaddOthers = ($scope.workData.tenderCost? $scope.workData.tenderCost : 0) - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBillsWOgstWOaddOthers:0);
						
						} else { // RES Vibhagiya - GP -- Administrative
									// Sanction Amount
						$scope.billData.totalAmountPreviousBillsWOgstWOaddOthers = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBillsWOgstWOaddOthers:0);
						}
					
					
					
					
					$scope.billData.lastBillNo = $scope.lastBillData.billNo;
					$scope.billData.lastBillIndex = $scope.lastBillData.billIndex;
					$scope.billData.lastBillDate =   $scope.lastBillData.billDate;
					
					$loading.finish('sample-1');
					
					// $scope.calculateTotalAmountUpToDate();$scope.calculateTotalAmountPreviousBill();
					
					$scope.validateRemainingAmount();
					
				})
			
			}) ;
			
		})
		
		
	};
	
	
	$scope.loadExecutiveWorkList2 = function() {
		$loading.start('sample-1');
		fetchExecutiveOfficeWorkReportStatus($routeParams.id);
	};
	$scope.loadAdminStatusWorkList2 = function() {
		$loading.start('sample-1');
		fetchAdminWorkReportStatus($routeParams.id);
	};
	$scope.loadSuperintendingWorkList2 = function() {
		$loading.start('sample-1');
		fetchSuperintendingOfficeWorkReportStatus($routeParams.id);
	};
	$scope.loadChiefWorkList2 = function() {
		$loading.start('sample-1');
		fetchChiefOfficeWorkReportStatus($routeParams.id);
	};
	

	$scope.loadExecutiveWorkList = function() {
		$loading.start('sample-1');
		fetchExecutiveWorkList();
	};
	// nikhil
	$scope.loadExecutiveWorkListForNewWork = function() {
		$loading.start('sample-1');
		fetchExecutiveWorkListForNewWork($routeParams.id);
	};
	
	$scope.loadSuperintendingWorkList = function() {
		$loading.start('sample-1');
		fetchSuperintendingWorkList();
	};
	
	$scope.loadSuperintendingWorkListForNewWork = function() {
		$loading.start('sample-1');
		fetchSuperintendingWorkListForNewWork();
	};
	
	$scope.loadChiefWorkList = function() {
		$loading.start('sample-1');
		fetchChiefWorkList();
	};
	$scope.loadChiefWorkListForNewWork = function() {
		$loading.start('sample-1');
		fetchChiefWorkListForNewWork();
	};
	
	$scope.loadWorkList = function() {

		$loading.start('sample-1');
		fetchWorkList();
		
	};
	
	$scope.loadDataPendingForInspection = function() {

		$loading.start('sample-1');
		fetchDataPendingForInspection();
		$scope.name=$routeParams.name;
	};
	
	$scope.loadDataFinalBillPending = function() {

		$loading.start('sample-1');
		fetchDataFinalBillPending();
		$scope.name=$routeParams.name;
	};
	
	$scope.loadDataPhysicalCCDispatch = function() {

		$loading.start('sample-1');
		fetchDataPhysicalCCDispatch();
		$scope.name=$routeParams.name;
	};
	
	$scope.loadWorkListForHistory = function() {

		$loading.start('sample-1');
		fetchWorkListForHistory();
	};
	// nikhil report
	$scope.loadWorkListStatus = function() {

		$loading.start('sample-1');
		fetchWorkListTwoStatus($routeParams.id);
	};
	
	$scope.loadWorkListStatus2 = function() {

		$loading.start('sample-1');
		fetchWorkListTwoStatus2($routeParams.id, $routeParams.officeId);
	};
	
	// Admin
	$scope.loadWorkListByAdmin = function() {

		$loading.start('sample-1');
		fetchWorkListByAdmin();
	};
	// Rakesh
	$scope.loadWorkListByAdminHistory = function() {

		$loading.start('sample-1');
		fetchWorkListByAdminHistory($routeParams.workId, $routeParams.technicalSanctionId);
	};
	
	
	
	$scope.loadWorkListByTechnical = function() {

		$loading.start('sample-1');
		fetchWorkListByTechnical();
	};
	
	$scope.loadWorkListBySqmInspection = function() {

		$loading.start('sample-1');
		fetchWorkListBySqmInspection();
	};
	
	$scope.loadWorkListByOfficerInspection = function() {

		$loading.start('sample-1');
		fetchWorkListByOfficerInspection();
	};
	
	
	// Rakesh
	$scope.loadWorkTechnicalSactionHistory = function() {
// alert('called!!')
		$loading.start('sample-1');
		
		fetchTechnicalSactionHistory($routeParams.workId, $routeParams.workEstimationId);
	};
	
	// nikhil
	$scope.loadTSIssuingAuthority = function() {
		$loading.start('sample-1');
		var response = $http.get('fetchTSIssuingAuthorityFromDesignationTable');
		response.success(function(data, status, headers, config) {
			$scope.tsIssuingAuthorities = data;
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
	
	
	$scope.loadAccountHead = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchAccountHead');
		response.success(function(data, status, headers, config) {
			$scope.accountHeads = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.workListExportToExcel = function(isLegacy) {
		$window.open('workListExportToExcel/'+isLegacy);
	};
	
	/*
	 * $scope.workListExportToExcelForAdmin = function(isLegacy) {
	 * $window.open('workListExportToExcelForAdmin/'+isLegacy); };
	 */
	
	$scope.workListExportToExcelTest = function(isLegacy) {
		
		var workTypeId = $scope.workData.workTypeId;
		var workSubTypeId = $scope.workData.workSubTypeId;
		var workLineDepartmentId = $scope.workData.workLineDepartmentId;
		var accountHeadId = $scope.workData.accountHeadId;
		var executionAgencyId = $scope.workData.agencyTypeId;
		var workStatusId = $scope.workData.workStatusId;
		var blockId = $scope.workData.blockId;
		var gramPanchayatId = $scope.workData.gramPanchayatId;
		var villageId = $scope.workData.villageId;
		var workContractorId = $scope.workData.contractorId;
		var districtId = $scope.searchDistrict;
		
		$window.open('workListExportToExcelTest/'+isLegacy +'?workTypeId=' +workTypeId
				+'&workSubTypeId=' +workSubTypeId
				+'&workLineDepartmentId=' +workLineDepartmentId
				+'&accountHeadId=' +accountHeadId
				+'&executionAgencyId=' +executionAgencyId
				+'&blockId=' +blockId
				+'&gramPanchayatId=' +gramPanchayatId
				+'&villageId=' +villageId
				+'&districtId=' +districtId
				+'&workStatusId=' +workStatusId
				+'&workContractorId=' +workContractorId
		);
	};
	
	
	$scope.addWorkEstimationItemEntry = function(index) {			
		var workTemplateItem =$scope.workTemplateItems[index];
		
		var workTemplate={};
    	workTemplate.hasChild=false;
    	
    	workTemplate.measureLength=workTemplateItem.measureLength;
    	workTemplate.measureWidth=workTemplateItem.measureWidth;
    	workTemplate.measureHeightDepth=workTemplateItem.measureHeightDepth;
    	workTemplate.group=workTemplateItem.group;
    	workTemplate.parentItem=workTemplateItem.parentItem;
    	workTemplate.parentIndex=workTemplateItem.parentIndex;
    	workTemplate.parentId=workTemplateItem.parentId
    	workTemplate.new=true;
    	workTemplate.lastElement=true;
    	workTemplate.leafNode=workTemplateItem.leafNode;
    	if(workTemplateItem.group){
    		workTemplate.new=false;
    		workTemplate.leafNode=false;
    	}else if(!workTemplateItem.hasChild && null==workTemplateItem.sorItemNo && null!= workTemplate.parentItem && workTemplate.parentItem.sorItemNo!=null){
    		workTemplate.new=false;
    	}
    	
    	var cumulativeChildsCount=0;
    	if(null!= workTemplateItem.cumulativeChildsCount){
    		cumulativeChildsCount=workTemplateItem.cumulativeChildsCount;
    	}
    	var newlyAddedIndex=index+cumulativeChildsCount+1;
    	
      if(null!=workTemplateItem.hasChild && (!workTemplateItem.hasChild || $scope.workTemplateItems[workTemplateItem.parentIndex]!=null) && null!=workTemplateItem.parentItem){
    	  var parentTemplate=$scope.workTemplateItems[workTemplateItem.parentIndex];
    	  parentTemplate.cumulativeChildsCount=$scope.workTemplateItems[workTemplateItem.parentIndex].cumulativeChildsCount+1;
    	  if(null!= $scope.workTemplateItems[parentTemplate.parentIndex]){
    		  $scope.workTemplateItems[parentTemplate.parentIndex].cumulativeChildsCount=$scope.workTemplateItems[parentTemplate.parentIndex].cumulativeChildsCount+1;
    	  }
    	}
      if(null!= workTemplate.parentId){
    	  var parentItem=$filter('filter')($scope.workTemplateItems, {id: workTemplate.parentId}, true)[0];
    	  parentItem.childsCount=parentItem.childsCount+1;
      }
    	
    	$scope.workTemplateItems.splice(newlyAddedIndex, 0, workTemplate);
    	workTemplateItem.lastElement=false;
		
	};
	
	$scope.addWorkEstimationItemEntryChild = function(index, type) {			
		$scope.itemData={};
		if(null!=index && index!=''){
			$scope.itemData.index=index;
			$scope.itemData.type=type;
			$scope.itemData.group='No';
		}
	};
	
    $scope.addNewItem = function(isValid) {
    	if (!isValid) 
			return false;
    	var workTemplate={};
    	workTemplate.hasChild=false;
    	workTemplate.sorItemNo=$scope.itemData.itemSorNo;
    	workTemplate.itemDesc=$scope.itemData.itemDesc;
    	workTemplate.unit=$scope.itemData.itemUnit;
    	workTemplate.rate=$scope.itemData.rate;
		// NON-SOR FLAG CHECK
			if($scope.itemData.group=='No' || $scope.itemData.group=='Yes'){
			workTemplate.isNonSOR = true;
			$scope.workData.hasNonSorItems = true;  // Work level flag
			console.log('Non-SOR item added');
		} 
			
    	if($scope.itemData.group=='Yes'){
    		workTemplate.group=true;
    	}else{
    		workTemplate.group=false;
    	}
    	if($scope.itemData.isLengthApplicable=='Yes'){
    		workTemplate.measureLength=true;
    	}else{
    		workTemplate.measureLength=false;
    	}
    	if($scope.itemData.isWidthApplicable=='Yes'){
    		workTemplate.measureWidth=true;
    	}else{
    		workTemplate.measureWidth=false;
    	}
    	if($scope.itemData.isHeightDepthApplicable=='Yes'){
    		workTemplate.measureHeightDepth=true;
    	}else{
    		workTemplate.measureHeightDepth=false;
    	}
    	workTemplate.length='';
    	workTemplate.width='';
    	workTemplate.heightDepth='';
    	workTemplate.quantity='';
    	workTemplate.amount='';
    	workTemplate.no='';
    	var listing=angular.copy($scope.workTemplateItems);
    	$(listing.reverse()).each(function( key, value) {
    		if(null!= value['serialNo'] && value['serialNo']!=''){
    			workTemplate.serialNo=value['serialNo']+1;
    			return false;
    		}
    	});
    	$scope.workTemplateItems.push(workTemplate);
    	
    	$("#add-new-item-popup1").modal("hide");
	};
	
	$scope.addNewChildItem = function(isValid) {
    	if (!isValid) 
			return false;
    	
    	if($scope.itemData.index!=null && $scope.itemData.index!=''){
    		var workTemplate={};
        	workTemplate.hasChild=false;
        	workTemplate.sorItemNo=$scope.itemData.itemSorNo;
        	workTemplate.itemDesc=$scope.itemData.itemDesc;
        	workTemplate.unit=$scope.itemData.itemUnit;
        	workTemplate.rate=$scope.itemData.rate;
        	if($scope.itemData.isLengthApplicable=='Yes'){
        		workTemplate.measureLength=true;
        	}else{
        		workTemplate.measureLength=false;
        	}
        	if($scope.itemData.isLengthApplicable=='Yes'){
        		workTemplate.measureLength=true;
        	}else{
        		workTemplate.measureLength=false;
        	}
        	if($scope.itemData.isWidthApplicable=='Yes'){
        		workTemplate.measureWidth=true;
        	}else{
        		workTemplate.measureWidth=false;
        	}
        	if($scope.itemData.isHeightDepthApplicable=='Yes'){
        		workTemplate.measureHeightDepth=true;
        	}else{
        		workTemplate.measureHeightDepth=false;
        	}
        	workTemplate.length='';
        	workTemplate.width='';
        	workTemplate.heightDepth='';
        	workTemplate.quantity='';
        	workTemplate.amount='';
        	workTemplate.no='';
        	workTemplate.lastElement=true;
        	
        	var listing=angular.copy($scope.workTemplateItems);
        	$(listing.reverse()).each(function( key, value) {
        		if(null!= value['serialNo'] && value['serialNo']!=''){
        			workTemplate.serialNo=value['serialNo']+1;
        			return false;
        		}
        	});
        	// $scope.workTemplateItems.push(workTemplate);
        	if(null!=$scope.itemData.type && $scope.itemData.type=='child'){
        		var parentItem=$scope.workTemplateItems[$scope.itemData.index];
        		if(null!= parentItem){
            		parentItem.childsCount=parentItem.childsCount+1;
            		parentItem.hasChild=true;
            	}
        		workTemplate.leafNode=true;
        		workTemplate.parentId=parentItem.id;
        	}else{
        		var clickedItem=$scope.workTemplateItems[$scope.itemData.index];
        		if(null!= clickedItem){
            		clickedItem.lastElement=false;
            	}
        		if(null!= clickedItem.parentId){
        			var parentItem=$filter('filter')($scope.workTemplateItems, {id: clickedItem.parentId}, true)[0];
        			if(null!=parentItem){
        				parentItem.childsCount=parentItem.childsCount+1;
                		workTemplate.parentId=parentItem.id;
                		workTemplate.leafNode=true;
        			}
            	}
        	}
        	
        	$scope.workTemplateItems.splice(parseInt($scope.itemData.index)+1, 0, workTemplate);
        	
        	$("#add-new-item-popup").modal("hide");
    	}else{
    		$scope.addNewItem(isValid);
    	}
    	
	};
	
	$scope.loadIssuingAuthority = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchIssuingAuthority');
		response.success(function(data, status, headers, config) {
			$scope.issuingAuthorities = data;
			$loading.finish('sample-1');
		});
	};
	
	// Nikhil
	
	/*
	 * $scope.changeActionType =function(type){ $scope.submit=type; }
	 */
	
$scope.addAdminSanction = function(workFormName, isValid, ldPdfFile) {
	
	if($scope.editAs == 'yes') {
		// no file required.
		
		}else{

		if(ldPdfFile) {
			$scope.noFileError = (ldPdfFile)?false:true;
		} else {
			$scope.noFileError = true;
		}

	}
		var maxSizeUpload = 5000000;// in bytes (here 5 MB)
		if(ldPdfFile){
			$scope.fileSizeErrorLd = (ldPdfFile.size > maxSizeUpload)?true:false;
		}
		
		
		if($scope.noFileError)
			return false;	
		if($scope.fileSizeErrorLd)
			return false;	
	
		
		$scope.errorMessage1 ="";
		if (parse($scope.workData.technicalSanctionBean.technicalSanctionDate) > parse($scope.workData.administrationSanctionDate)) {
			$scope.errorMessage1 = "Administration Sanction Date Can not be before Technical Sanction Date";
			return false;
		}
		
		 var today = new Date();
		 var administrationSanctionDate = parse($scope.workData.administrationSanctionDate);
		
		if (administrationSanctionDate > today) {
			$scope.errorMessage1 = "Administration Sanction Date Can not be After Current Date";
			return false;
		}
		
		if ($scope.workData.workEstimationBean.grandTotal > $scope.workData.totalCost) {
			$scope.errorMessage1 = "Administrative Sanction amount cannot be less than Technical sanction amount.";
			return false;
		}
		
		
		if (!isValid)
			return false;		
		
		if (confirm("Are you sure you want to save the data?")) {
			
			$loading.start('sample-1');
			
			if($scope.saveAsDraft == true){
				 
				$scope.administrationSanctionStatusId = 1;
			}
			else if($scope.submit == true){
				 // workRequestStatusId
				$scope.administrationSanctionStatusId = 2;
			}
			var fd = new FormData();
			
			fd.append('administrationSanctionStatusId', $scope.administrationSanctionStatusId);
			
			if ($scope.workData.workId) {
				fd.append('workId', $scope.workData.workId);
			}
			if($scope.workData.technicalSanctionId){
				fd.append('technicalSanctionId', $scope.workData.technicalSanctionId);
			}
			
			if($scope.workData.issuingAuthorityId){
			fd.append('issuingAuthorityId', $scope.workData.issuingAuthorityId);
			}
			
			if(ldPdfFile){
				fd.append('administrationSanctionFile', ldPdfFile);
			}
			 
			if ($scope.workData.administrationSanctionNo) {
				fd.append('administrationSanctionNo', $scope.workData.administrationSanctionNo);
			}
			if ($scope.workData.proposeddistance) {
				fd.append('proposeddistance', $scope.workData.proposeddistance);
			}
			
			
			if ($scope.workData.administrationSanctionDate) {
				fd.append('administrationSanctionDate', $scope.workData.administrationSanctionDate);
			}
			
			
			if ($scope.workData.totalCost) {
				fd.append('totalCost', $scope.workData.totalCost);
			}	
			
			if ($scope.workData.competentAuthName) {
				fd.append('competentAuthName', $scope.workData.competentAuthName);
			}
			if ($scope.workData.competentAuthDesig) {
				fd.append('competentAuthDesig', $scope.workData.competentAuthDesig);
			}
			
			$loading.start('sample-1');

			var responsePromise = $http.post('addAdminSanction', fd, {
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
					$window.location.href = '#manageAdministrationSanctionDataRoute';				
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
		
		else {
			$scope.saveAsDraft = false;
			$scope.submit = false;
		}
		
	};
/*
 * $("#headerCheckbox").change(function() { var checkboxes = $('#dynamic-table
 * td input:checkbox'); var checked = this.checked;
 * $(checkboxes).prop("checked", checked); $.each($scope.workData, function(
 * index, value ){ value.isChecked = checked; }) });
 */
	
	$scope.unlockButtonId = function() {
		
		
		
		$loading.start('sample-1');
		

		/*
		 * $('input[name="workId"]:checked').each(function() {
		 * console.log(this.value); });
		 */
		
		$loading.finish('sample-1');
		
		/*
		 * var response = $http.get('fetchIssuingAuthority');
		 * response.success(function(data, status, headers, config) {
		 * $scope.issuingAuthorities = data; $loading.finish('sample-1'); });
		 */
	};
	

	$scope.loadDistrictsOfMPNew = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchDistrictsOfMPNew');
		response.success(function(data, status, headers, config) {
			$scope.districtsMP = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.loadExeOffices = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchExeOffices');
		response.success(function(data, status, headers, config) {
			$scope.exeOffices = data;
			$loading.finish('sample-1');
		});
	/*	$scope.$on('$viewContentLoaded', function(event) {
			console.log('load');
			});*/
	};
	
	$scope.loadExeOfficesForOffices = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchExeOfficesForOffices');
		response.success(function(data, status, headers, config) {
			$scope.exeOffices = data;
			$loading.finish('sample-1');
		});
	/*	$scope.$on('$viewContentLoaded', function(event) {
			console.log('load');
			});*/
	};
	
	$scope.loadSupdtOffices = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchSupdtOffices');
		response.success(function(data, status, headers, config) {
			$scope.supdtOffices = data;
			$loading.finish('sample-1');
		});
	/*	$scope.$on('$viewContentLoaded', function(event) {
			console.log('load');
			});*/
	};
	
	
	$scope.reloadJqueryDatatable = function(){
		$loading.start('sample-1');
			reDraw();
			$loading.finish('sample-1');
		
	};
	$scope.downloadDocument = function(documentId) {
		
		$window.open('downloadDocument/'+documentId);
	};


$scope.loadWorkType = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchWorkType');
	response.success(function(data, status, headers, config) {
		$scope.workTypes = data;
		$loading.finish('sample-1');
	});
};

$scope.loadNameOfSqm = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchNameOfSqm');
	response.success(function(data, status, headers, config) {
		$scope.nameOfSqm = data;
		$loading.finish('sample-1');
	});
};

$scope.loadWorkSubTypeByWorkTypeId = function(workTypeId) {

	$loading.start('sample-1');
	
	var response = $http.get('fetchWorkSubTypeByWorkTypeId/'+workTypeId);
	response.success(function(data, status, headers, config) {
		$scope.workSubTypes = data;
		
			/*angular.forEach($scope.workSubTypes, function(value, key) {
			console.log("2nd>>"value.id);
		});*/
		
			/*$('#workSubTypeId').multiselect('rebuild');*/
			// $('#workSubTypeId').multiselect('refresh');
		/*$scope.fetchExeAgWiseExpenditureListData($scope.financialYearId, $scope.districtId, $scope.executionAgencyId,$scope.lineDepartmentId,$scope.accountHeadId,$scope.workStatusId,$scope.month,$scope.workTypeId,$scope.workSubTypeId);*/
		
		
		$loading.finish('sample-1');
		
		
	});
};


$scope.loadWorkSubTypeByWorkTypeIdForMultiselect = function(workTypeId) {

	$loading.start('sample-1');
	
	var response = $http.get('fetchWorkSubTypeByWorkTypeId/'+workTypeId);
	response.success(function(data, status, headers, config) {
		$scope.workSubTypes = data;
		
			/*angular.forEach($scope.workSubTypes, function(value, key) {
			console.log("2nd>>"value.id);
		});*/
		
			/*$('#workSubTypeId').multiselect('rebuild');*/
			// $('#workSubTypeId').multiselect('refresh');
		/*$scope.fetchExeAgWiseExpenditureListData($scope.financialYearId, $scope.districtId, $scope.executionAgencyId,$scope.lineDepartmentId,$scope.accountHeadId,$scope.workStatusId,$scope.month,$scope.workTypeId,$scope.workSubTypeId);*/
		
		
		$loading.finish('sample-1');
		setTimeout(function(){
		$('#workSubTypeId').selectpicker('refresh');
		}, 1000) ;
		
	});
};


$scope.ddl_workSubType_setting = {
		  displayProp: 'workSubTypeNameE', idProperty: 'workSubTypeId',
	        template: '{{option.workSubTypeNameE}}',
	        showCheckAll: true,
	        showUncheckAll: true,
	        selectionLimit: 0,
	        checkBoxes: true
};

$scope.loadLineDepartment = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchLineDepartment');
	response.success(function(data, status, headers, config) {
		$scope.lineDepartments = data;
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

$scope.loadWorkStatusType = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchWorkStatusType');
	response.success(function(data, status, headers, config) {
		$scope.workStatusTypes = data;
		$loading.finish('sample-1');
	});
};

/*
 * $scope.loadBlocksByDistrictNew = function(districtId) {
 * 
 * $loading.start('sample-1'); //
 * $scope.entrepreneurData.registeredAddress.blockId = ""; var response =
 * $http.get('fetchBlocksByDistrictNew/'+districtId);
 * response.success(function(data, status, headers, config) { $scope.blocks =
 * data; // $scope.blocks.block.blockCode = $scope.blocks.block.blockCode+"";
 * $loading.finish('sample-1'); }); };
 */

$scope.loadUserDetail = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchUserDetailsFromLoggedInUserName');

	response.success(function(data, status, headers, config) {
		$scope.workData = data;
		
		$scope.workData.userBean.officeBean.officeName = $scope.workData.userBean.officeBean.officeName + "";
		$scope.workData.userBean.districtBean.districtName = $scope.workData.userBean.districtBean.districtName + "";
		$scope.workData.userBean.districtBean.districtId = $scope.workData.userBean.districtBean.districtId;
		
		/*
		 * $scope.loadBlocksByDistrictNew($scope.workData.districtId);
		 * $scope.workData.blockId = $scope.workData.blockId+"";
		 */
		// Dependency create ke liye beans me set the value
// $scope.workData.userBean.officeBean.id =
// $scope.workData.userBean.officeBean.id + "";
		
		
	}).then(function(){
		if(null!= $scope.workData.userBean.districtBean.districtId){
			var response = $http.get('fetchBlocksByDistrictNew/'+$scope.workData.userBean.districtBean.districtId);
			response.success(function(data, status, headers, config) {
				$scope.blocks = data;		
				
			});
		}else{
			$loading.finish('sample-1');
		}
		
	});
};


$scope.loadGramPanchayatByBlockCode = function(blockCode) {

	$loading.start('sample-1');
// $scope.entrepreneurData.registeredAddress.blockId = "";
	var response = $http.get('fetchGramPanchayatByBlockCode/'+blockCode);
	response.success(function(data, status, headers, config) {
		$scope.gramPanchayats = data;
	
		
		
		
// $scope.workData.gramPanchayatBean.gpCode =
// $scope.gramPanchayats.gramPanchayatBean.gpCode+"";
		$loading.finish('sample-1');
	});
};



$scope.loadVillageByGramPanchayatCode = function(gramPanchayatCode) {		
	$loading.start('sample-1');
// $scope.entrepreneurData.registeredAddress.blockId = "";
	var response = $http.get('fetchVillageByGramPanchayatCode/'+gramPanchayatCode);
	response.success(function(data, status, headers, config) {
		$scope.villages = data;
// $scope.workData.gramPanchayatBean.gpCode =
// $scope.gramPanchayats.gramPanchayatBean.gpCode+"";
		$loading.finish('sample-1');
	});
};


$scope.resetLegacyFunction = function() {
	$loading.start('sample-1');
	
	
	$('#workTypeId').val('');
	$('#workSubTypeId').val('');
	$('#workLineDepartmentId').val('');
	$('#accountHeadId').val('');
	$('#executionAgencyId').val('');
	$('#workStatusId').val('');
	$('#districtId').val('');
	$('#blockId').val('');
	$('#gramPanchayatId').val('');
	$('#villageId').val('');
	
	
	reDraw();
	
	$scope.test = [];
	
	$scope.workSubTypes='';
	$scope.workData.workSubTypeId="";
	$scope.gramPanchayats='';
	$scope.workData.gramPanchayatId="";
	$scope.villages='';
	$scope.workData.village="";
	
	$scope.workData.workTypeId='';
	$scope.workData.lineDepartmentId='';
	$scope.workData.agencyTypeId='';
	$scope.workData.workStatusId='';
	$scope.searchDistrict='';
	$scope.workData.blockId='';
// workData.gramPanchayatId
// workData.villageId
	$scope.workData.contractorId='';
	$scope.workData.financialYearId='';
	
	/*
	 * $timeout(function () { reDrawTwo(); }, 0);
	 */
};

$scope.loadBlocksByDistrictNew = function(districtId) {

	$loading.start('sample-1');
// $scope.entrepreneurData.registeredAddress.blockId = "";
	var response = $http.get('fetchBlocksByDistrictNew/'+districtId);
	response.success(function(data, status, headers, config) {
		$scope.blocks = data;
// $scope.blocks.block.blockCode = $scope.blocks.block.blockCode+"";
		$loading.finish('sample-1');
	});
};

 
$scope.loadInspectionData = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionData/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadGeneralInspectionDataNew = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchGeneralInspectionDataNew/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}





$scope.loadInspectionDataEE = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionDataForEE/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionDataForSqm = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionDataForSqm/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionDataForSqmNew = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionDataSQM/'+$routeParams.id+'/'+$routeParams.inspectionId);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionDataForOfficer = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionDataForOfficer/'+$routeParams.id+'/'+$routeParams.inspectionId);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionDataForOfficerNew = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionDataForOfficerNew/'+$routeParams.id+'/'+ $routeParams.inspectionId);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionImagesOfficerNew = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImagesOfficerNew/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionImagesByInspectionId = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImagesByInspectionId/'+$routeParams.inspectionId);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}
$scope.loadGeneralInspectionImagesByInspectionId = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImagesByInspectionId/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}





$scope.loadInspectionImages = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImages/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadGeneralInspectionImages = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchGeneralInspectionImages/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionImagesSqm = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImagesSqmNew/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionImagesOfficer = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImagesOfficer/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionFilesSqm = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionFilesSqm/'+$routeParams.id );
	response.success(function(data, status, headers, config) {
		$scope.inspectionFileData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionFilesOfficer = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionFilesOfficer/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionFileData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionImagesEE = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionImagesEE/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	});
 
}

$scope.loadInspectionCCData = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionCCData/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionData = data;
		$loading.finish('sample-1');
	}).then(function (){
		if(null==$scope.inspectionData || $scope.inspectionData.length==0){
			$loading.start('sample-1');
			var response1 = $http.get('fetchInspectionCCDataFromFinalBill/'+$routeParams.id);
			response1.success(function(data, status, headers, config) {
				$scope.inspectionData = data;
				$loading.finish('sample-1');
			})
		}
	});
 
}

$scope.loadInspectionCCImages = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchInspectionCCImages/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.inspectionImageData = data;
		$loading.finish('sample-1');
	}).then(function (){
		if(null==$scope.inspectionData || $scope.inspectionData.length==0){
			$loading.start('sample-1');
			var response1 = $http.get('fetchInspectionCCImagesFromFinalBill/'+$routeParams.id);
			response1.success(function(data, status, headers, config) {
				$scope.inspectionImageData = data;
				$loading.finish('sample-1');
			})
		}
	});
 
}


$scope.resetLegacyWithDistrictFunction = function() {
	$loading.start('sample-1');
	$('#workTypeId').val('');
	$('#workSubTypeId').val('');
	$('#workLineDepartmentId').val('');
	$('#accountHeadId').val('');
	$('#executionAgencyId').val('');
	$('#workStatusId').val('');
	
	$('#districtId').val('');
	$('#blockId').val('');
	$('#gramPanchayatId').val('');
	$('#villageId').val('');
	
	reDraw();
	
	
	$scope.workSubTypes='';
	$scope.workData.workSubTypeId="";
	
	$scope.blocks='';
	$scope.workData.blockId="";
	$scope.gramPanchayats='';
	$scope.workData.gramPanchayatId="";
	$scope.villages='';
	$scope.workData.village="";
	
	$scope.workData.workTypeId='';
	$scope.workData.lineDepartmentId='';
	$scope.workData.agencyTypeId='';
	$scope.workData.workStatusId='';
	$scope.searchDistrict='';
// workData.gramPanchayatId
// workData.villageId
	$scope.workData.contractorId='';
	$scope.workData.financialYearId='';
	
	/*
	 * $timeout(function () { reDrawTwo(); }, 0);
	 */
};

$scope.fetchStatusWiseWorkCountList = function() {

	$loading.start('sample-1');
	fetchStatusWiseWorkCountList();
};

$scope.fetchStatusWiseWorkCountListSelection = function() {

	$loading.start('sample-1');
	fetchStatusWiseWorkCountListSelection();
};

$scope.fetchPendingForInspectionCount = function() {

	$loading.start('sample-1');
	fetchPendingForInspectionCount();
};
$scope.fetchFinalBillPendingCount = function() {

	$loading.start('sample-1');
	fetchFinalBillPendingCount();
};

$scope.fetchPhysicalCCDispatchCount = function() {

	$loading.start('sample-1');
	fetchPhysicalCCDispatchCount();
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


$scope.loadFinancialYear = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchFinancialYear');
	response.success(function(data, status, headers, config) {
		$scope.financialYears = data;
		$loading.finish('sample-1');
	});
};

$scope.loadOfficesWhereParentOfficeIdIsNotNull = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchOffices');
	response.success(function(data, status, headers, config) {
		$scope.offices = data;
		$loading.finish('sample-1');
	});
};


 

$scope.loadOriginalBillDetails  = function() {

	$loading.start('sample-1');
	
	$scope.gt1 = 0;
	$scope. gt2 = 0;
	 
	var response = $http.get('fetchWorkDetailsByBillId/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.workData = data;
		$loading.finish('sample-1');
	}).
	then(function (){
		$loading.start('sample-1');
		
		$scope.measuredByList = [];
		if($scope.workData.userBean.officeBean) {
			var response1 = $http.get('fetchEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
			response1.success(function(data, status, headers, config) {
					$scope.measuredByList = data;
			});
		}
		
	}).then(function (){
		$loading.start('sample-1');
		var response = $http.get('fetchOriginalBillDetails/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.billData = data;
			$scope.billData.totalAmountUpToDate = 0;
			$scope.billData.totalAmountPreviousBill = 0;
			
			for(i = 0 ; i < $scope.billData.billItems.length; i++) {
				$scope.billData.totalAmountUpToDate = parseInt($scope.billData.totalAmountUpToDate) + parseInt($scope.billData.billItems[i].amountUptodate);
				$scope.billData.totalAmountPreviousBill = parseInt($scope.billData.totalAmountPreviousBill) + parseInt($scope.billData.billItems[i].amountPreviousBill); 
			}
			
			if($scope.workData.tenderedRateSign == '+') {
				$scope.gt1 = parseFloat(($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountUpToDate).toFixed(0);
				$scope.gt2 = parseFloat(($scope.billData.totalAmountPreviousBill *$scope.workData.tenderedRatePer/100) + $scope.billData.totalAmountPreviousBill).toFixed(0);
			}
			
			if($scope.workData.tenderedRateSign =='-') {
				$scope.gt1 = parseFloat($scope.billData.totalAmountUpToDate - ($scope.billData.totalAmountUpToDate * $scope.workData.tenderedRatePer/100)).toFixed(0);
				$scope.gt2 =  parseFloat($scope.billData.totalAmountPreviousBill - ($scope.billData.totalAmountPreviousBill * $scope.workData.tenderedRatePer/100)).toFixed(0);
			}
			
			if($scope.workData.tenderedRateSign == null ) {
				$scope.gt1 =  $scope.billData.totalAmountUpToDate  ;
				$scope.gt2 =  ($scope.billData.totalAmountPreviousBill ?  $scope.billData.totalAmountPreviousBill : 0)  ;
			} 
			
			 
			if(Number.isNaN($scope.billData.totalAmountPreviousBill) || $scope.billData.totalAmountPreviousBill == 0) {
				$scope.gt2 = 0;
			}
			
			
			$scope.r1 = parseInt($scope.gt1? $scope.gt1: 0) - parseInt($scope.gt2 ? $scope.gt2: 0) + parseInt($scope.billData.gst ?$scope.billData.gst: 0) + parseInt($scope.billData.addOthers ? $scope.billData.addOthers:0);
		 
			$scope.r2 =  parseInt($scope.r1) - ( parseInt($scope.billData.securityDeposit ? $scope.billData.securityDeposit : 0) + parseInt($scope.billData.incomeTax?$scope.billData.incomeTax: 0) + parseInt($scope.billData.upkar?$scope.billData.upkar:0) + parseInt($scope.billData.royalty?$scope.billData.royalty:0) + parseInt($scope.billData.other?$scope.billData.other:0) + parseInt($scope.billData.costOfBillForm?$scope.billData.costOfBillForm:0 )
								+ parseInt($scope.billData.miscDeposit?$scope.billData.miscDeposit:0) + parseInt($scope.billData.performanceGuarantee?$scope.billData.performanceGuarantee:0) + parseInt($scope.billData.advancePayments?$scope.billData.advancePayments:0) );
			
			$scope.totalC =  parseInt($scope.billData.securityDeposit ? $scope.billData.securityDeposit : 0) + parseInt($scope.billData.incomeTax?$scope.billData.incomeTax: 0) + parseInt($scope.billData.upkar?$scope.billData.upkar:0) + parseInt($scope.billData.royalty?$scope.billData.royalty:0) + parseInt($scope.billData.other?$scope.billData.other:0) + parseInt($scope.billData.costOfBillForm?$scope.billData.costOfBillForm:0 )
			+ parseInt($scope.billData.miscDeposit?$scope.billData.miscDeposit:0) + parseInt($scope.billData.performanceGuarantee?$scope.billData.performanceGuarantee:0) + parseInt($scope.billData.advancePayments?$scope.billData.advancePayments:0) ;
			
			$scope.addGstAndOthers =  parseInt($scope.billData.gst ?$scope.billData.gst: 0) + parseInt($scope.billData.addOthers ? $scope.billData.addOthers:0);
			
			$scope.billData.inspectedBy = {};
			 // 2 gp - 1,3 RES
			if($scope.workData.agencyTypeBean.agencyTypeId == 2) {  // GP
				// $scope.billData.measurementById =
				// $scope.workData.subEngineerId+"";
				$scope.billData.inspectedBy.id= $scope.workData.subEngineerId+"";
				
			} else {
				// $scope.billData.measurementById =
				// $scope.workData.assistantEngineerId+"";
				$scope.billData.inspectedBy.id =   $scope.workData.assistantEngineerId+"";
			}
			
			$scope.billData.measurementById = $scope.workData.subEngineerId+"";
			
			$loading.finish('sample-1');
		})
		.then(function (){
			$loading.start('sample-1');
			var response = $http.get('fetchPrevBillDetailsByBillId/'+$routeParams.id);
			response.success(function(data, status, headers, config) {
				 	
				$scope.lastBillData = data;
				
				if($scope.workData.agencyTypeBean.agencyTypeId == 1) {  // RES
																		// Nivida
																		 
					$scope.billData.remainingAmountForPayment = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
					$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.workData.probableAmountOfWork? $scope.workData.probableAmountOfWork : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
					
					} else { // RES Vibhagiya - GP
					$scope.billData.remainingAmountForPayment = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.totalAmountPreviousBills:0);
					$scope.billData.remainingAmountForPaymentWithoutGst = $scope.workData.totalCostString - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
					}
				if($scope.lastBillData.estimationRevised){
					if($scope.workData.agencyTypeBean.agencyTypeId == 1){
						$scope.billData.remainingAmountForPaymentWithoutGst = ($scope.lastBillData.finalAsBillingAmount? $scope.lastBillData.finalAsBillingAmount : 0) -  ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0) + $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
					}else{
						$scope.billData.remainingAmountForPaymentWithoutGst = $scope.lastBillData.finalAsBillingAmount - $scope.workData.contingencyAmount - ($scope.workData.totalExpenditureTill31March2018String? $scope.workData.totalExpenditureTill31March2018String:0)	+ $scope.workData.totalExpenditureOnContingencyTill31March2018 - ($scope.lastBillData ? $scope.lastBillData.billAmountWithoutGstAndDeductions:0);
					}
				 }
				
				var response1 = $http.get('fetchPaymentData/'+$routeParams.id);
				response1.success(function(data, status, headers, config) {
					$scope.paymentBean = data;
					/*$loading.finish('sample-1');*/
				});
				
				$loading.finish('sample-1');
				
				// $scope.calculateTotalAmountUpToDate();$scope.calculateTotalAmountPreviousBill();
				
				$scope.validateRemainingAmount();
				
			})
		});
		
	})
	
	
};

$scope.fwdForPayment = function (billId) {

	var responsePromise = $http.post('fwdForPayment/'+billId);

	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 5000);
			$window.location.href = '#viewPayments';				
		}
		$loading.finish('sample-1');
	});
};

$scope.revertToPhysicalInspectionCompleted = function (billId) {

	var responsePromise = $http.post('revertToPhysicalInspectionCompleted/'+billId);

	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 5000);
			$window.location.href = '#viewBills';				
		}
		$loading.finish('sample-1');
	});
};
  


$scope.enableDisableFields = function (index) {
	
	var instrumentNo = document.getElementById("instrumentNo_"+ index);
	var bankName = document.getElementById("bankName_"+ index);
	var instrumentDate = document.getElementById("instrumentDate_"+ index);
	
	var e = document.getElementById("paymentMode_"+ index);
	var paymentMode = e.options[e.selectedIndex].value;
	 
	if('Cash' == paymentMode) {
		
		instrumentNo.disabled = true;
		instrumentDate.disabled = false;
		bankName.disabled = true;
	}
	
	else if('Online Transaction' == paymentMode || 'NEFT / RTGS' == paymentMode) {
		
		instrumentNo.disabled = false;
		instrumentDate.disabled = false;
		bankName.disabled = false;
	}
	
	else if('Demand Draft' == paymentMode  || 'cheque' == paymentMode ) {
		instrumentNo.disabled = false;
		instrumentDate.disabled = false;
		bankName.disabled = false;
	}
	
};


$scope.loadWorkDetailTwo = function() {	
	
	$loading.start('sample-1');
	var response = $http.get('fetchWorkDetails/'+$routeParams.workId);
	
	var estimateId = $routeParams.estimationId;
	
	var technicalSanctionId = $routeParams.technicalSanctionId;
	
	response.success(function(data, status, headers, config) {		
		$scope.workData = data;		
		$scope.workData.workEstimateId = estimateId;		
		$scope.workData.technicalSanctionId = technicalSanctionId;
		$scope.workData.workTypeId = $scope.workData.workTypeId+"";
		$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
		$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
		if($scope.workData.workSubTypeId)
		$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
// $scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
		
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
// $scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
		if($scope.workData.assistantEngineerId == null)
			{
		$scope.workData.assistantEngineerId = "";
			} else {
				$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
			}
		
		
// $scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
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


$scope.loadWorkAgreementList = function() {
	$loading.start('sample-1');
	fetchWorkAgreementList();
};
// Rakesh
$scope.loadWorkAgreementHistoryList = function() {
	$loading.start('sample-1');
	fetchWorkAgreementHistoryList($routeParams.workId,$routeParams.tenderId);
};

$scope.addTechnicalSanction = function(workFormName,isValid, ldPdfFile) {
	
		if($scope.editAs == 'yes'){
		}
		else{
			
			if(ldPdfFile)
			{
				$scope.noFileError = (ldPdfFile)?false:true;
			} else {
				$scope.noFileError = true;
			}
		}
	var maxSizeUpload = 5000000;// in bytes (here 5 MB)
	if(ldPdfFile){
		$scope.fileSizeErrorLd = (ldPdfFile.size > maxSizeUpload) ? true: false;
		}
	
	if($scope.noFileError)
		return false;	
	if($scope.fileSizeErrorLd)
		return false;	
	
	
	$scope.errorMessage1 ="";
	if (parse($scope.workData.workEstimationBean.estimationSubmissionDate) > parse($scope.workData.tsDispatchDate)) {
		$scope.errorMessage1 = "Dispatch Date Can not be before Estimation Approval Date";
		return false;
	}
	
		 var today = new Date();
		 var tsDispatchDate = parse($scope.workData.tsDispatchDate);
	 
		if (tsDispatchDate > today) {
			$scope.errorMessage1 = "Dispatch Date Can not be After Current Date";
			return false;
		}
	
	
	if (!isValid)
		return false;		
	
	if (confirm("Are you sure you want to save the data?")) {
		
		$loading.start('sample-1');
		
		if($scope.saveAsDraft == true){
			 
			$scope.technicalSanctionStatusId = 2;
		}
		else if($scope.submit == true){
			 
			$scope.technicalSanctionStatusId =  3;
		}
		
		
		
		var fd = new FormData();
		
		fd.append('technicalSanctionBean.technicalSanctionStatusId', $scope.technicalSanctionStatusId);
		
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
		if($scope.workData.workEstimateId){
			fd.append('workEstimateId', $scope.workData.workEstimateId);
		}
		
	   fd.append('technicalSanctionBean.technicalSanctionAmount', $scope.workData.workEstimationBean.grandTotal);
		
	  fd.append('technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionType', $scope.workData.workEstimationBean.estimationType);

//		
// fd.append('workRequestStatusId', $scope.workRequestStatusId);
		
		if(ldPdfFile){
			fd.append('technicalSanctionBean.technicalSanctionFile', ldPdfFile);
		}
		
		
		if ($scope.workData.tsDispatchNumber) {
			fd.append('technicalSanctionBean.tsDispatchNumber', $scope.workData.tsDispatchNumber);
		}
		
		if ($scope.workData.technicalSanctionNo) {
			fd.append('technicalSanctionBean.technicalSanctionNo', $scope.workData.technicalSanctionNo);
		}
		
		if ($scope.workData.technicalSanctionNoOld) {
			fd.append('technicalSanctionBean.technicalSanctionNoOld', $scope.workData.technicalSanctionNoOld);
		}
		
		if ($scope.workData.technicalSanctionDate) {
			fd.append('technicalSanctionBean.technicalSanctionDate', $scope.workData.technicalSanctionDate);
		}
		
		
		if ($scope.workData.tsDispatchDate) {
			fd.append('technicalSanctionBean.tsDispatchDate', $scope.workData.tsDispatchDate);
		}
		
		if ($scope.workData.competentAuthName) {
			fd.append('technicalSanctionBean.competentAuthName', $scope.workData.competentAuthName);
		}
		
		if ($scope.workData.competentAuthDesig) {
			fd.append('technicalSanctionBean.competentAuthDesig', $scope.workData.competentAuthDesig);
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

		responsePromise.error(function() {
			$rootScope.responseObject = {};
			$rootScope.responseObject.errorMessage = "Some error occured while saving the data";
			$timeout(function() {
				$rootScope.responseObject.errorMessage = null;
			}, 10000);
			$loading.finish('sample-1');
		});
		
	}
	
	else {
		$scope.saveAsDraft = false;
		$scope.submit = false;
	}
};

$scope.addTender = function(workFormName, isValid) {
	
	if ($scope.workTenderBean.contratorDepositsList.length==0) {
		alert("Please Add atleast one Row in Contractor Deposits.");
		return false;
	}
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data?")) {
		
		if($scope.saveAsDraft == true) {				 
			$scope.workTenderBean.tenderStatusId = 1;
		}
		else if($scope.submit == true) {
			$scope.workTenderBean.tenderStatusId = 2;
		}
		
		$loading.start('sample-1');
		$scope.workTenderBean.workId = $routeParams.id;
		$scope.workTenderBean.administrationSanctionId = $routeParams.administrationSanctionId;
		
		var responsePromise = $http.post('addTender', $scope.workTenderBean);

		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					$rootScope.responseObject.successMessage = null;
				}, 5000);
				if($rootScope.responseObject.role=='ROLE_EE'){
					$window.location.href = '#manageTenderDataRoute';
				}else{
					$window.location.href = '#manageTenderDataRouteForEnc';
				}
			}
			$loading.finish('sample-1');
		});
	}
	
	else {
		$scope.saveAsDraft = false;
		$scope.submit = false;
	}
	
};
$scope.loadPhysicalStageByWorkTypeId = function(workTypeId) {
	$loading.start('sample-1');
	var response = $http.get('fetchPhysicalStageByWorkTypeId/'+workTypeId);
	response.success(function(data, status, headers, config) {
		$scope.physicalStageTypes = data;
		$loading.finish('sample-1');
	});
};

$scope.loadWorkDetailForPrintTs = function() {	
	
	$loading.start('sample-1');
	var response = $http.get('fetchWorkDetailsForPrintTs/'+$routeParams.workId+'/'+$routeParams.workEstimationId);
	/*
	 * var estimateId = $routeParams.estimationId;
	 * 
	 * var technicalSanctionId = $routeParams.technicalSanctionId;
	 */
	
	response.success(function(data, status, headers, config) {
		$scope.workData = data;/*
								 * $scope.workData.workEstimateId = estimateId;
								 * $scope.workData.technicalSanctionId =
								 * technicalSanctionId;
								 */
		$scope.workData.workTypeId = $scope.workData.workTypeId+"";
		$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
		$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
		if($scope.workData.workSubTypeId)
		$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
// $scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
		
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
		$scope.workData.gramPanchayatName =$scope.workData.gramPanchayatName;
		$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
		if(null!=$scope.workData.villageId){
			$scope.workData.villageId = $scope.workData.villageId+"";
		}
		
		$scope.workData.workStatusId = $scope.workData.workStatusId+"";
		
		
		$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
// $scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
		if($scope.workData.assistantEngineerId == null)
			{
			$scope.workData.assistantEngineerId = "";
		} else {
				$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
			}
		
		
// $scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
		if($scope.workData.subEngineerId == null)
		{
			$scope.workData.subEngineerId = "";
		} else {
			$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
		}
		$scope.workData.technicalSanctionTypeId = $scope.workData.technicalSanctionBean.technicalSanctionTypeBean.technicalSanctionTypeId+"";
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



$scope.editTender = function(workFormName, isValid) {
	
	if ($scope.workTenderBean.contratorDepositsList.length==0) {
		alert("Please Add atleast one Row in Contractor Deposits.");
		return false;
	}
	
	if (!isValid) {				
		return false;	
	}
	
	if (confirm("Are you sure you want to save the data?")) {
		
		if($scope.saveAsDraft == true) {				 
			$scope.workTenderBean.tenderStatusId = 1;
		}
		else if($scope.submit == true) {				 
			$scope.workTenderBean.tenderStatusId = 2;
		}
		
		$loading.start('sample-1');
		$scope.workTenderBean.workId = $routeParams.id;
		$scope.workTenderBean.administrationSanctionId = $routeParams.administrationSanctionId;
		
		var responsePromise = $http.post('editTender', $scope.workTenderBean);

		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);
				$window.location.href = '#manageTenderDataRoute';
			}
			$loading.finish('sample-1');
		});
	}
	
	else {
		$scope.saveAsDraft = false;
		$scope.submit = false;
	}
};

$scope.loadDepositeCategory = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchDepositeCategory');
	response.success(function(data, status, headers, config) {
		$scope.DepositeCategories = data;
		$loading.finish('sample-1');
	});
};
///yaha

//yaha


$scope.loadExAgWiseExpenditureList = function() {
	$scope.getCurrentFiscalYear();
	
	
	
	/*$scope.financialYearId=$scope.myFincialYear;*/
	$scope.exeOfficeId='0';
	$scope.executionAgencyId='0';
	$scope.lineDepartmentId='0';
	$scope.accountHeadId='0';
	$scope.workStatusId='0';
	$scope.month='0';
	$scope.workTypeId='0';
	$scope.workSubTypeId='0';
	
	
	


	$loading.start('sample-1');
	
	
	/*var response = $http.get('fetchWorkDetailsByEstimation/'+$routeParams.workId+'/'+$routeParams.estimationId);
	
	var estimateId = $routeParams.estimationId;
	
	var technicalSanctionId = $routeParams.technicalSanctionId;
	
	response.success(function(data, status, headers, config) {
		$scope.workData = data;
	*/
	//alert($scope.districtId+"=="+$scope.financialYearId)
	$http.get('fetchExeAgWiseExpenditureList/'+ $scope.financialYearId + '/' + $scope.exeOfficeId + '/' + $scope.executionAgencyId + '/' + $scope.lineDepartmentId + '/' + $scope.accountHeadId + '/' + $scope.workStatusId + '/' + $scope.month + '/' + $scope.workTypeId+ '/' + $scope.workSubTypeId).then(function(response) {
		
		
		 var years = $scope.financialYearId.split("-");

		 $scope.currentFinancialYear = $scope.financialYearId;

		 $scope.prevFinancialYear = (years[0]-1).toString() + "-" + years[0].toString();

		 $scope.months = ['Apr, '+years[0], 'May, '+years[0], 'Jun, '+years[0], 'Jul, '+years[0], 'Aug, '+years[0], 'Sep, '+years[0], 'Oct, '+years[0],
			 'Nov, '+years[0], 'Dec, '+years[0], 'Jan, '+years[1], 'Feb, '+years[1], 'Mar, '+years[1]];
		 
		 $scope.months2 = ['Apr, '+years[0], 'May, '+years[0], 'Jun, '+years[0], 'Jul, '+years[0], 'Aug, '+years[0], 'Sep, '+years[0], 'Oct, '+years[0],
			 'Nov, '+years[0], 'Dec, '+years[0], 'Jan, '+years[1], 'Feb, '+years[1], 'Mar, '+years[1]];

		
		 $scope.yearlyMonthlyContributionReportData = response.data;
		 
		 $scope.currentFinancialYearAmtEmployeeSum = 0;
		 $scope.currentFinancialYearAmtEmployerSum = 0;
		 $scope.currentFinancialYearAmtSum = 0;
		 $scope.prevFinancialYearAmtEmployeeSum = 0;
		 $scope.prevFinancialYearAmtEmployerSum = 0;
		 $scope.prevFinancialYearAmtSum = 0;
		 
		
		 
		 /*$scope.currentFinancialYearAmtEmployeeSum = $scope.yearlyMonthlyContributionReportData.map(item => item.currentFinancialYearEmployeeAmt).reduce((prev, next) => prev + next);
		 $scope.currentFinancialYearAmtEmployerSum = $scope.yearlyMonthlyContributionReportData.map(item => item.currentFinancialYearEmployerAmt).reduce((prev, next) => prev + next);
		 $scope.currentFinancialYearAmtSum = $scope.yearlyMonthlyContributionReportData.map(item => item.currentFinancialYearAmt).reduce((prev, next) => prev + next);
		 
		 $scope.prevFinancialYearAmtEmployeeSum = $scope.yearlyMonthlyContributionReportData.map(item => item.prevFinancialYearEmployeeAmt).reduce((prev, next) => prev + next);
		 $scope.prevFinancialYearAmtEmployerSum = $scope.yearlyMonthlyContributionReportData.map(item => item.prevFinancialYearEmployerAmt).reduce((prev, next) => prev + next);
		 $scope.prevFinancialYearAmtSum = $scope.yearlyMonthlyContributionReportData.map(item => item.prevFinancialYearAmt).reduce((prev, next) => prev + next);*/
		 
		 $loading.finish('sample-1');
	}).then(function(response) {
		$scope.loadFinancialYear();
		$scope.loadDistrictsOfMPNew();
		$scope.loadAgencyType();
	
	});
}


$scope.fetchExeAgWiseExpenditureListData = function(financialYearId ,exeOfficeId , executionAgencyId,lineDepartmentId,accountHeadId,workStatusId,month,workTypeId,workSubTypeId) {

	 //if(!$scope.yearlyMonthlyContributionReportData){ 
	
	/*alert("value of Ditrict id is >>>>>"+exeOfficeId);
	alert("value of linedeptId id is >>>>>"+lineDepartmentId);
	alert("value of accountHeadId id is >>>>>"+accountHeadId);
	alert("value of workStatusId id is >>>>>"+workStatusId);*/
	
	 
	 $loading.start('sample-1');
	 
	 $scope.yearlyMonthlyContributionReportDataOther = null;// empty the Other District List

	 var years = financialYearId.split("-");

	 $scope.currentFinancialYear = financialYearId;
	 
	 if(exeOfficeId==''){
		 $scope.exeOfficeId='0';
		}
	 
	 var lineDeptIds='0';
	 if(lineDepartmentId==''){
		 $scope.lineDepartmentId='0';
		}else{
			lineDeptIds=$scope.lineDepartmentId.toString();
		}
	 var accountHeadIds='0';
	 if(accountHeadId==''){
		 $scope.accountHeadId='0';
		}else{
			accountHeadIds=$scope.accountHeadId.toString();
		}
	 var workStatusIds='0';
	 if(workStatusId==''){
		 $scope.workStatusId='0';
		}else{
			workStatusIds=$scope.workStatusId.toString();
		}
	 if(month==''){
		 $scope.month='0';
		}
	 if(workTypeId==''){
		 $scope.workTypeId='0';
		}
	  var workSubTypeIds='0';
	 if(workSubTypeId==''){
		 $scope.workSubTypeId='0';
		}else{
			workSubTypeIds=$scope.workSubTypeId.toString();
		}

	 $scope.prevFinancialYear = (years[0]-1).toString() + "-" + years[0].toString();

	 $scope.months = ['Apr, '+years[0], 'May, '+years[0], 'Jun, '+years[0], 'Jul, '+years[0], 'Aug, '+years[0], 'Sep, '+years[0], 'Oct, '+years[0],
		 'Nov, '+years[0], 'Dec, '+years[0], 'Jan, '+years[1], 'Feb, '+years[1], 'Mar, '+years[1]];

	 var response = $http.get('fetchExeAgWiseExpenditureList/'+ $scope.financialYearId + '/' + $scope.exeOfficeId + '/' + $scope.executionAgencyId+ '/' + lineDeptIds + '/' + accountHeadIds+ '/' + workStatusIds+ '/' + $scope.month + '/' + $scope.workTypeId+ '/' + workSubTypeIds);

	 response.success(function(data, status, headers, config) {
		 
		/* $(function() {
				$(document).ready(function() {
					$('#exeOfficeId').multiselect({
		
						includeSelectAllOption : true,
						maxHeight : 400,
						dropUp : false
		
					});
				});
			});
		 alert(data[1].month);
		 $scope.months = [data[3].month+','+years[0], data[4].month+','+years[0], data[5].month+','+years[0], data[6].month+','+years[0], data[7].month+','+years[0], data[8].month+','+years[0], data[9].month+','+years[0],
			 data[10].month+','+years[0], data[11].month+','+years[0], data[0].month+','+years[1], data[1].month+','+years[1], data[2].month+','+years[1]];*/
		 if(data.length>0 && data[0].month=='0'){
			 $scope.months2 = ['April, '+years[0], 'May, '+years[0], 'Jun, '+years[0], 'Jul, '+years[0], 'Aug, '+years[0], 'Sep, '+years[0], 'Oct, '+years[0],
				 'Nov, '+years[0], 'Dec, '+years[0], 'Jan, '+years[1], 'Feb, '+years[1], 'Mar, '+years[1]];
			 
		 }
		 else if(data.length>0 && data[0].month!='0'){
		 
		 /*var temp = new Array();
			
			temp = data[0].month.split(",");
			$scope.months=temp;*/
			 $scope.months2 = [];
			 if(data[0].month.includes("APRIL")){
				 /*alert("April");*/
				 $scope.months2.push('April, '+years[0]);
			 }
			 if(data[0].month.includes("May")){
				 $scope.months2.push('May, '+years[0]);
			 }
			 if(data[0].month.includes("June")){
				 $scope.months2.push('Jun, '+years[0]);
			 }
			 if(data[0].month.includes("July")){
				 $scope.months2.push('Jul, '+years[0]);
			 }
			 if(data[0].month.includes("August")){
				 $scope.months2.push('Aug, '+years[0]);
			 }
			 if(data[0].month.includes("September")){
				 $scope.months2.push('Sep, '+years[0]);
			 }
			 if(data[0].month.includes("October")){
				 $scope.months2.push('Oct, '+years[0]);
			 }
			 if(data[0].month.includes("November")){
				 $scope.months2.push('Nov, '+years[0]);
			 }
			 if(data[0].month.includes("December")){
				 $scope.months2.push('Dec, '+years[0]);
			 }
			 if(data[0].month.includes("January")){
				 /*alert("january");*/
				 $scope.months2.push('Jan, '+years[1]);
				 /*alert($scope.months2);*/
			 }
			 if(data[0].month.includes("February")){
				/* alert("febuary");*/
				 $scope.months2.push('Feb, '+years[1]);
			 }
			 if(data[0].month.includes("March")){
				 $scope.months2.push('Mar, '+years[1]);
			 }
		 }
		 
		 $scope.yearlyMonthlyContributionReportData = data;
		
		 
		
		 
		
		 
		

		 $loading.finish('sample-1');
	 }).then(function(response) {

		 $scope.loadFinancialYear();
			$scope.loadDistrictsOfMPNew();
			$scope.loadAgencyType();
	 });
	 //}
};

$scope.getCurrentFiscalYear = function() {
    //get current date
    var today = new Date();
     
    //get current month
    var curMonth = today.getMonth();
     
    var fiscalYr = "";
    if (curMonth > 3) { //
        var nextYr1 = (today.getFullYear() + 1).toString();
        //fiscalYr = today.getFullYear().toString() + "-" + nextYr1.charAt(2) + nextYr1.charAt(3);
        fiscalYr = today.getFullYear().toString() + "-" + nextYr1;
    } else {
        var nextYr2 = today.getFullYear().toString();
        //fiscalYr = (today.getFullYear() - 1).toString() + "-" + nextYr2.charAt(2) + nextYr2.charAt(3);
        fiscalYr = (today.getFullYear() - 1).toString() + "-" + nextYr2;
    }
    
    $scope.financialYearId = fiscalYr;
    
    $scope.currentFinancialYear = fiscalYr;
    
    $scope.prevFinancialYear = (today.getFullYear()-1).toString() + "-" + today.getFullYear().toString();
 };
	
	
	
	


$scope.loadDepositeType = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchDepositeType');
	response.success(function(data, status, headers, config) {
		$scope.DepositeTypes = data;
		$loading.finish('sample-1');
	});
};

$scope.loadBankName = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchBankName');
	response.success(function(data, status, headers, config) {
		$scope.BankNames = data;
		$loading.finish('sample-1');
	});
};

$scope.loadContractorType = function() {	
	$loading.start('sample-1');
	var response = $http.get('fetchContractorType');
	response.success(function(data, status, headers, config) {
		$scope.contractorTypes = data;
		$loading.finish('sample-1');
	});
};

$scope.loadWorkTender = function() {
	$loading.start('sample-1');
	fetchWorkTender();
};

// Rakesh
$scope.loadWorkTenderHistory = function() {
	$loading.start('sample-1');
	fetchWorkTenderHistory($routeParams.id,$routeParams.administrationSanctionId);
};


$scope.loadWorkTenderList = function() {
	
	$loading.start('sample-1');
	fetchWorkTenderList();
};


$scope.validateContractorDetails = function(contratorDepositData, validateFor) {
	
	if(contratorDepositData.instrumentDate !=null && new Date() !=null ) {
		if (parse(contratorDepositData.instrumentDate) > new Date()) {
		alert("instrumentDate should not be  greater than Today Date ");
		return true;
	}
}
	
	if(contratorDepositData.expiredOn!=null && $scope.workTenderBean.tenderOpeningDate != null ) {
		if (parse(contratorDepositData.expiredOn) <=  parse($scope.workTenderBean.tenderOpeningDate)) {
			alert("Expired On Date Should be greater then Tender Opening Date");
			return true;
		}
	}
	if(contratorDepositData.expiredOn !=null && contratorDepositData.instrumentDate !=null ) {
			if (parse(contratorDepositData.expiredOn) <=  parse(contratorDepositData.instrumentDate)) {
				alert("Expired On Date Should be greater then Instrument Date");
				return true;
			}
		}
	
	
	switch (validateFor) {
	case "categoryId":
		$scope.depositCategoryIdError = (contratorDepositData.depositCategoryId)?false:true;
		return $scope.depositCategoryIdError;
		break;
	case "typeId":
		$scope.depositTypeIdError = (contratorDepositData.depositTypeId)?false:true;
		return $scope.depositTypeIdError;
		break;
	case "amount":
		$scope.amountError = (contratorDepositData.amount)?false:true;
		return $scope.amountError;
		break;
	case "bankId":
		$scope.bankIdError = (contratorDepositData.bankId)?false:true;
		return $scope.bankIdError;
		break;
	case "instrumentNumber":
		$scope.instrumentNumberError = (contratorDepositData.instrumentNumber)?false:true;
		return $scope.instrumentNumberError;
		break;
	case "instrumentDate":
		$scope.instrumentDateError = (contratorDepositData.instrumentDate)?false:true;
		return $scope.instrumentDateError;
		break;	
	case "expiredOn":
		$scope.expiredOnError = (contratorDepositData.expiredOn)?false:true;
		return $scope.expiredOnError;
		break;
	default:
		$scope.depositCategoryIdError = (contratorDepositData.depositCategoryId)?false:true;
		$scope.depositTypeIdError = (contratorDepositData.depositTypeId)?false:true;
		$scope.amountError = (contratorDepositData.amount)?false:true;
		$scope.bankIdError = (contratorDepositData.bankId)?false:true;
		$scope.instrumentNumberError = (contratorDepositData.instrumentNumber)?false:true;
		$scope.instrumentDateError = (contratorDepositData.instrumentDate)?false:true;
		$scope.expiredOnError = (contratorDepositData.expiredOn)?false:true;
		return ($scope.depositCategoryIdError || $scope.depositTypeIdError || $scope.amountError || $scope.bankIdError || $scope.instrumentNumberError || $scope.expiredOnError);
		break;
	}
};


$scope.addContractorDetail = function(contratorDepositData) {
	
	if (!$scope.validateContractorDetails(contratorDepositData, "all")) {		
		angular.forEach($scope.DepositeCategories, function(value, key) {
			if (value.id==contratorDepositData.depositCategoryId) {
				var depositCategoryName = value.depositecatnameE;
				contratorDepositData.depositCategoryName = depositCategoryName;
			}
		});
		angular.forEach($scope.DepositeTypes, function(value, key) {
			if (value.id==contratorDepositData.depositTypeId) {
				var depositTypeName = value.depositetypenameE;		
				contratorDepositData.depositTypeName = depositTypeName;
			}
		});	
		
		angular.forEach($scope.BankNames, function(value, key) {
			if (value.bankId==contratorDepositData.bankId) {
				var bankName = value.bankName;		
				contratorDepositData.bankName = bankName;
			}
		});		
		$scope.workTenderBean.contratorDepositsList.push(contratorDepositData);
		$scope.contratorDepositData = {};
	}
};

$scope.removeContractorDetail = function(index) {
	if (confirm("Sure want to delete entry. Once removed can not be rollback.")) {
		$scope.workTenderBean.contratorDepositsList.splice(index, 1);
	}		
};

$scope.checkPercentageValue = function(percentage) {
	if(parseFloat(percentage)<0)
		$scope.workTenderBean.tenderedRate = 0;
	if(parseFloat(percentage)>100)
		$scope.workTenderBean.tenderedRate = 100;
};

$scope.calculateTenderCost = function(amountOfContract, percentage, sign, asCost) {
	
	if(amountOfContract!=null && percentage!=null && sign!=null && asCost!=null) {
		var tenderCost =null;
		if(sign=="+")
			tenderCost = parseFloat(amountOfContract) + (parseFloat(amountOfContract)*(parseFloat(percentage)/100));
		else
			tenderCost = parseFloat(amountOfContract) - (parseFloat(amountOfContract)*(parseFloat(percentage)/100));
	
		if(tenderCost > asCost) {
			alert("Tender Cost Cannot be More Than Administration Sanction Amount");
			/* $scope.workTenderBean.amountOfContract = null; */
			$scope.workTenderBean.tenderCost = null;
			$scope.workTenderBean.tenderedRate=null;
		}
		else {
			$scope.workTenderBean.tenderCost = tenderCost.toFixed(0);
		}
	} else {
		$scope.workTenderBean.tenderCost = null;
	}
};


$scope.loadWorkTenders = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchWorkTenderDetailsByWorkId/'+$routeParams.id);
	response.success(function(data, status, headers, config) {		
		$scope.workTenderBean = data;
		
		$scope.workTenderBean.depositeTypeId = data.depositeTypeId+"";
		$scope.workTenderBean.depositeCategoryId = data.depositeCategoryId+"";
		$scope.workTenderBean.bankId = data.bankId+"";
		
		$scope.loadContractors($scope.workTenderBean.contractorTxt);
		$scope.workTenderBean.contractorTxt = "";
		$scope.workTenderBean.contractorId = data.contractorId+"";
		
		$scope.contratorDepositData = {};
		$loading.finish('sample-1');
	});
};

$scope.loadWorkTendersByTenderId = function() {
	$loading.start('sample-1');
	var response = $http.get('fetchWorkTenderDetailsByTenderId/'+$routeParams.tenderId);
	response.success(function(data, status, headers, config) {		
		$scope.workTenderBean = data;
		
		$scope.workTenderBean.depositeTypeId = data.depositeTypeId+"";
		$scope.workTenderBean.depositeCategoryId = data.depositeCategoryId+"";
		$scope.workTenderBean.bankId = data.bankId+"";
		
		$scope.loadContractors($scope.workTenderBean.contractorTxt);
		$scope.workTenderBean.contractorTxt = "";
		$scope.workTenderBean.contractorId = data.contractorId+"";
		
		$scope.contratorDepositData = {};
		$loading.finish('sample-1');
	});
};

$scope.calculatePAC = function(amountOfContract,  asCost) {	
	if(amountOfContract!=null && asCost!=null) {	
		if(amountOfContract > asCost) {
			alert("Probable Amount Of Contract Cannot be More Than Administration Sanction Amount");
			$scope.workTenderBean.amountOfContract = null;			
		}		
	} else {
		$scope.workTenderBean.amountOfContract = null;
	}
};

$scope.loadWorkDetailThree = function() {	
	
	$loading.start('sample-1');
	$scope.loggedInUserRole = window.loggedInUserRole;
	var response = $http.get('fetchWorkDetailsByEstimation/'+$routeParams.workId+'/'+$routeParams.estimationId);
	
	var estimateId = $routeParams.estimationId;
	
	var technicalSanctionId = $routeParams.technicalSanctionId;
	
	response.success(function(data, status, headers, config) {
		$scope.workData = data;
		$scope.workData.workEstimateId = estimateId;
		$scope.workData.technicalSanctionId = technicalSanctionId;
		$scope.workData.workTypeId = $scope.workData.workTypeId+"";
		$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
		$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
		if($scope.workData.workSubTypeId)
		$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
// $scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
		
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
// $scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
		if($scope.workData.assistantEngineerId == null)
			{
		$scope.workData.assistantEngineerId = "";
			} else {
				$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
			}
		
		
// $scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
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
		
		
		$scope.workData.workEstimationBean = data.workEstimationBean;
		
		if($scope.workData.technicalSanctionStatusId == 0) {
			$scope.tsStatus ='Waiting for TS Generation';
		} else if ($scope.workData.technicalSanctionStatusId == 1) {
			$scope.tsStatus ='TS Generated';
		} else if ($scope.workData.technicalSanctionStatusId == 2) {
			$scope.tsStatus ='TS Under Process (Saved as Draft)';
		}  
		else if ($scope.workData.technicalSanctionStatusId == 4) {
			$scope.tsStatus ='TS Approved';
		} 
		else if ($scope.workData.technicalSanctionStatusId == 5) {
			$scope.tsStatus ='TS Forward to SE';
		}
		else if ($scope.workData.technicalSanctionStatusId == 6) {
			$scope.tsStatus ='TS Revert to EE';
		}
		else if ($scope.workData.technicalSanctionStatusId == 7) {
			$scope.tsStatus ='TS Forward to CE';
		}
		else {
			$scope.tsStatus ='TS Dispatched';
		}
		
		
		$loading.finish('sample-1');
	});
};


/*
 * $scope.editWorkData = function(isValid) {
 * 
 * if (!isValid) return false;
 * 
 * if (confirm("Are you sure you want to save the data?")) {
 * $scope.workData.workId = $routeParams.id; $loading.start('sample-1'); var
 * responsePromise = $http.post('editWork', $scope.workData);
 * 
 * responsePromise.success(function(data, status, headers, config) {
 * 
 * $rootScope.responseObject = data;
 * 
 * if($rootScope.responseObject.successMessage != null) { $timeout(function() {
 * $rootScope.responseObject.successMessage = null; }, 5000);
 * $window.location.href = '#manageLegacyDataRoute'; }
 * if($rootScope.responseObject.errorMessage != null) { $timeout(function() {
 * $rootScope.responseObject.errorMessage = null; }, 5000); }
 * $loading.finish('sample-1'); }); } };
 */


$scope.loadWorkDetailFour = function() {	
	
	$loading.start('sample-1');
	var response = $http.get('fetchWorkDetailsByTechnical/'+$routeParams.workId+'/'+$routeParams.technicalSanctionId);
	
	var estimateId = $routeParams.estimationId;
	
	var technicalSanctionId = $routeParams.technicalSanctionId;
	
	response.success(function(data, status, headers, config) {
		$scope.workData = data;
		$scope.workData.workEstimateId = estimateId;
		$scope.workData.technicalSanctionId = technicalSanctionId;
		$scope.workData.workTypeId = $scope.workData.workTypeId+"";
		$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
		$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
		if($scope.workData.workSubTypeId)
		$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
// $scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
		
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
// $scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
		if($scope.workData.assistantEngineerId == null)
			{
		$scope.workData.assistantEngineerId = "";
			} else {
				$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
			}
		
		
// $scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
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
		
		$scope.workData.workEstimationBean = data.workEstimationBean;
		
		$scope.workData.technicalSanctionBean = data.technicalSanctionBean;
		
		// set default amount for add
		if($scope.workData.totalCost == null) {
			$scope.workData.totalCost = data.workEstimationBean.grandTotal;	
		}
		
		if($scope.workData.administrativeSanctionStatusId == 0)
		{

			$scope.administrativeSanctionStatus ='Waiting for AS Approval';
		  
		} else if ($scope.workData.administrativeSanctionStatusId == 1 ) {

			$scope.administrativeSanctionStatus ='AS is saved as Draft';
		 
		}  else {
			 
			$scope.administrativeSanctionStatus ='AS Received';
				
		}
		
		$loading.finish('sample-1');
	});
};

$scope.loadPhysicalCCWorkList = function() {
	$loading.start('sample-1');	
	fetchPhysicalCCWorkList();
	$scope.ccDispatchDetailsBean={};
};

$scope.loadFinancialCCWorkList = function() {
	$loading.start('sample-1');	
	fetchFinancialCCWorkList();	
};

$scope.initiateCC = function(isValid) {	
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data?")) {
		$loading.start('sample-1');
		
		$scope.ccDetailsBean.workId = $routeParams.id;
		
		var responsePromise = $http.post('cc/initiateCCSubmit/', $scope.ccDetailsBean);
		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);
				$window.location.href = '#cc/workList';
			}
			$loading.finish('sample-1');
		});
	}
};

$scope.addPhysicalCCDispatchDetails = function(isValid, ccFile) {
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data ?")) {			
		
		if($scope.saveAsDraft == true) {				 
			$scope.ccDispatchDetailsBean.status = 1;
		} else if($scope.submit == true) {
			$scope.ccDispatchDetailsBean.status = 2;
		}
		
		var fd = new FormData();
		
		if ($scope.ccDispatchDetailsBean.id) {
			fd.append('id', $scope.ccDispatchDetailsBean.id);
		}
		
		if ($scope.ccDispatchDetailsBean.workId) {
			fd.append('workId', $scope.ccDispatchDetailsBean.workId);
		}		
		
		if ($scope.ccDispatchDetailsBean.dispatchNumber) {
			fd.append('dispatchNumber', $scope.ccDispatchDetailsBean.dispatchNumber);
		}
		
		if ($scope.ccDispatchDetailsBean.dispatchDate) {
			fd.append('dispatchDate', $scope.ccDispatchDetailsBean.dispatchDate);
		}
		
		if ($scope.ccDispatchDetailsBean.remarks) {
			fd.append('remarks', $scope.ccDispatchDetailsBean.remarks);
		}
		
		if ($scope.ccDispatchDetailsBean.status) {
			fd.append('status', $scope.ccDispatchDetailsBean.status);
		}		
		
		if(ccFile){
			fd.append('file', ccFile);
		}
		
		$loading.start('sample-1');	

		var responsePromise = $http.post('addPhysicalCCDispatchDetails', fd, {
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
				$scope.loadPhysicalCCWorkList();
				$('#update-dispatch-popup').modal('hide');
				$scope.saveAsDraft = $scope.submit = false; 
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
			$('#update-dispatch-popup').modal('hide');			
		});
		
		
	}
	else {
		$scope.saveAsDraft = false;
		$scope.submit = false;
	}
};

$scope.addFinancialCCDispatchDetails = function(isValid, ccFile) {
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data ?")) {			
		
		if($scope.saveAsDraft == true) {				 
			$scope.ccDispatchDetailsBean.status = 1;
		}
		else if($scope.submit == true) {
			$scope.ccDispatchDetailsBean.status = 2;
		}
		
		var fd = new FormData();
		
		if ($scope.ccDispatchDetailsBean.id) {
			fd.append('id', $scope.ccDispatchDetailsBean.id);
		}
		
		if ($scope.ccDispatchDetailsBean.workId) {
			fd.append('workId', $scope.ccDispatchDetailsBean.workId);
		}		
		
		if ($scope.ccDispatchDetailsBean.dispatchNumber) {
			fd.append('dispatchNumber', $scope.ccDispatchDetailsBean.dispatchNumber);
		}
		
		if ($scope.ccDispatchDetailsBean.dispatchDate) {
			fd.append('dispatchDate', $scope.ccDispatchDetailsBean.dispatchDate);
		}
		
		if ($scope.ccDispatchDetailsBean.remarks) {
			fd.append('remarks', $scope.ccDispatchDetailsBean.remarks);
		}
		
		if ($scope.ccDispatchDetailsBean.status) {
			fd.append('status', $scope.ccDispatchDetailsBean.status);
		}		
		
		if(ccFile){
			fd.append('file', ccFile);
		}			
		
		$loading.start('sample-1');

		var responsePromise = $http.post('addFinancialCCDispatchDetails', fd, {
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
				$scope.loadFinancialCCWorkList();
				$('#update-dispatch-popup').modal('hide');
				$scope.saveAsDraft = $scope.submit = false;
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
			$('#update-dispatch-popup').modal('hide');
		});
		
		
	}
	else {
		$scope.saveAsDraft = false;
		$scope.submit = false;
	}
};

$scope.loadPhysicalCCDispatchDetails = function(workid, type) {	
	$loading.start('sample-1');			
	var response = $http.get('fetchPhysicalCCDispatchDetails/'+workid);	
	response.success(function(data, status, headers, config) {
		if (data=="" || data==null) {
			$scope.ccDispatchForm.$setPristine();
			$scope.ccDispatchDetailsBean = {};
			$scope.ccDispatchDetailsBean.workId = workid;			
		} else {
			$scope.ccDispatchDetailsBean = data;
		}		
		$loading.finish('sample-1');
	}).then(function() {
		$('#'+type+'-dispatch-popup').modal('show');
	});
};

$scope.loadFinancialCCDispatchDetails = function(workid, type) {	
	$loading.start('sample-1');			
	var response = $http.get('fetchFinancialCCDispatchDetails/'+workid);	
	response.success(function(data, status, headers, config) {
		if (data=="" || data==null) {
			$scope.ccDispatchForm.$setPristine();
			$scope.ccDispatchDetailsBean = {};
			$scope.ccDispatchDetailsBean.workId = workid;			
		} else {
			$scope.ccDispatchDetailsBean = data;
		}		
		$loading.finish('sample-1');
	}).then(function() {
		$('#'+type+'-dispatch-popup').modal('show');
	});
};

$scope.issuePhysicalCC = function(workid) {
	if (confirm("Are you sure you want to proceed ?")) {	
		$loading.start('sample-1');	
		var responsePromise = $http.post('issuePhysicalCC', workid);
		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);			
				$scope.loadPhysicalCCWorkList();
			}		
			$loading.finish('sample-1');
		});
	}
};

$scope.issueFinancialCC = function(workid) {
	if (confirm("Are you sure you want to proceed ?")) {	
		$loading.start('sample-1');	
		var responsePromise = $http.post('issueFinancialCC', workid);
		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);			
				$scope.loadFinancialCCWorkList();
			}		
			$loading.finish('sample-1');
		});
	}
};


$scope.fwdForPhysicalCC = function (workId) {
	var responsePromise = $http.post('fwdForPhysicalCC/'+workId);
	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 5000);
			$window.location.href = '#cc/ccInspectionList';				
		}
		$loading.finish('sample-1');
	});
};

 
$scope.rejectCCLoad = function (workId) {
	
	$scope.ccDetailBean = {};
	$scope.ccDetailBean.workId = workId;
	$('#remark-popup-cc').modal('show');
	
};

$scope.fwdForCCReject = function (workId) {
	var responsePromise = $http.post('CCReject', $scope.ccDetailBean);
	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 5000);
			loadBillsForInspection();
			$('#remark-popup-cc').modal('hide');
		}
		$loading.finish('sample-1');
	});
};

$scope.resetItemDataValues = function() {
	var group=$scope.itemData.group;
	$scope.itemData={};
	$scope.itemData.group=group;
}

$scope.fetchInitiatedCCList = function() {
    $loading.start('sample-1');
    loadInitiatedCCList();
};


$scope.addLineDepartment = function(isValid) {

	if (!isValid) 
		return false;
	
	if (confirm("Are you sure you want to save the data?")) {
		$loading.start('sample-1');
		 
		var responsePromise = $http.post('addLineDepartment', $scope.lineDepartmentBean);

		responsePromise.success(function(data, status, headers, config) {

			$rootScope.responseObject = data;

			if($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					$rootScope.responseObject.successMessage = null;
				}, 10000);
				$window.location.href = '#manageLineDepartment';
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
	
	 
};

$scope.addAccountHead = function(isValid) {

	if (!isValid) 
		return false;
	
	if (confirm("Are you sure you want to save the data?")) {
		$loading.start('sample-1');
		 
		var responsePromise = $http.post('addAccountHead', $scope.accountHeadBean);

		responsePromise.success(function(data, status, headers, config) {

			$rootScope.responseObject = data;

			if($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					$rootScope.responseObject.successMessage = null;
				}, 10000);
				$window.location.href = '#manageAccountHead';
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
};


$scope.loadUpdateAccountHead = function(id) {	
	 
	$loading.start('sample-1');			
	var response = $http.get('fetchAccountHeadById/'+id);	
	response.success(function(data, status, headers, config) {
		if (data=="" || data==null) {
			$scope.accountHeadBean.$setPristine();
			$scope.accountHeadBean = {};
			$scope.accountHeadBean.accountHeadId = id;			
		} else {
			$scope.accountHeadBean = data;
		}		
		$loading.finish('sample-1');
	}).then(function() {
		$('#accountHead-update-popup').modal('show');
	});
};



$scope.loadupdateOfficer = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchWorkDetails/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.workData = data;
		$scope.workData.executiveEngineerOfficeId = $scope.workData.executiveEngineerOfficeId+"";
		$scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);		
		$scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
		
		
		if($scope.workData.assistantEngineerId == null) {
			$scope.workData.assistantEngineerId = "";
			} else {
				$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
			}
		
		if($scope.workData.subEngineerId == null) {
			$scope.workData.subEngineerId = "";
			} else {
				$scope.workData.subEngineerId = $scope.workData.subEngineerId + "";	
		  }
		
		$loading.finish('sample-1');
	}).then(function() {
		$('#officer-update-popup').modal('show');
	});
};

$scope.updateOfficer = function(form, isValid ) {
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data ?")) {			

		var responsePromise = $http.post('updateOfficer', $scope.workData);

		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);
			    
				$scope.loadWorkDetail();
				$('#officer-update-popup').modal('hide');
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
			$('#accountHead-update-popup').modal('hide');
		});
	}
	 
};

$scope.loadAssistantEngineerByOfficeId = function(officeId) {

	$loading.start('sample-1');
	var response = $http.get('fetchAssistantEngineerByOfficeId/'+officeId);
	response.success(function(data, status, headers, config) {
		$scope.assistantEngineers = data;
		$loading.finish('sample-1');
	});
};

$scope.loadUsersForOfficeIdAndDesg = function(officeId) {

	$loading.start('sample-1');
	var response = $http.get('fetchUsersForOfficeIdAndDesg/'+officeId);
	response.success(function(data, status, headers, config) {
		$scope.usersDet = data;
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



$scope.loadUpdateLineDepartment = function(id) {	
	$loading.start('sample-1');			
	var response = $http.get('fetchLineDepartmentById/'+id);	
	response.success(function(data, status, headers, config) {
		if (data=="" || data==null) {
			$scope.lineDepartmentBean.$setPristine();
			$scope.lineDepartmentBean = {};
			$scope.lineDepartmentBean.lineDepartmentId = id;			
		} else {
			$scope.lineDepartmentBean = data;
		}		
		$loading.finish('sample-1');
	}).then(function() {
		$('#lineDepartment-update-popup').modal('show');
	});
};




$scope.updateAccountHead = function(form, isValid ) {
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data ?")) {			

		var responsePromise = $http.post('addAccountHead', $scope.accountHeadBean);

		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);
			    
				$scope.loadAccountHeadList();
				$('#accountHead-update-popup').modal('hide');
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
			$('#accountHead-update-popup').modal('hide');
		});
	}
	 
};


$scope.updateLineDepartment = function(form, isValid ) {
	
	if (!isValid) {				
		return false;
	}
	
	if (confirm("Are you sure you want to save the data ?")) {			

		var responsePromise = $http.post('addLineDepartment', $scope.lineDepartmentBean);

		responsePromise.success(function(data, status, headers, config) {
			$rootScope.responseObject = data;
			if ($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					 $rootScope.responseObject.successMessage = null;
			    }, 5000);
			     loadLineDepartmentList();
				$('#lineDepartment-update-popup').modal('hide');
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
			$('#lineDepartment-update-popup').modal('hide');
		});
	}
	 
};

	$scope.loadAccountHeadList = function() {
		$loading.start('sample-1');	
		fetchAccountHeadList();	
	};
	$scope.loadLineDepartmentList = function() {
		$loading.start('sample-1');	
		fetchLineDepartmentList();	
	};


	$scope.rejectBillLoad = function (billId) {
		
		$scope.billBean = {};
		$scope.billBean.id = billId;
		$('#remark-popup').modal('show');
		
	};
	
	$scope.rejectBillLoadSub = function (billId) {
		
		$scope.billBean = {};
		$scope.billBean.id = billId;
		$('#remark-popup-sub').modal('show');
		
	};
 
	// Inspection List
$scope.rejectBill = function () {

	var responsePromise = $http.post('rejectBill', $scope.billBean);

	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 1000);
			loadBillsForInspection();
			// $window.location.href = '#inspectionList';
			$('#remark-popup').modal('hide');
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
		loadBillsForInspection();
		$('#remark-popup').modal('hide');
	});
};

//View Bills
$scope.rejectBill1 = function (isValid) {
	
	if (!isValid) {				
		return false;
	}
	if (confirm("Are you sure you want to save the data ?")) {
	$loading.start('sample-1');
	var responsePromise = $http.post('rejectBill', $scope.billBean);
	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 1000);
			//loadBills();
			$window.location.href = '#viewBills';
			$('#remark-popup').modal('hide');
			$('.modal-backdrop').remove();
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
		//loadBills();
		$window.location.href = '#viewBills';
		$('#remark-popup').modal('hide');
		$('.modal-backdrop').remove();
	 });
	}
};


// View Bills
/*$scope.rejectBill1 = function (isValid) {
	var responsePromise = $http.post('rejectBill', $scope.billBean);
	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 1000);
			$scope.loadBills();
			$('#remark-popup').modal('hide');
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
		$scope.loadBills();
		$('#remark-popup').modal('hide');
	});
};*/

$scope.rejectBill1SubE = function (isValid) {
	
	if (!isValid) {				
		return false;
	}

	var responsePromise = $http.post('rejectBill', $scope.billBean);
	responsePromise.success(function(data, status, headers, config) {
		$rootScope.responseObject = data;
		if ($rootScope.responseObject.successMessage != null) {
			$timeout(function() {
				 $rootScope.responseObject.successMessage = null;
		    }, 10000);
			$('#remark-popup-sub').modal('hide');
			$('.modal-backdrop').remove();
			$window.location.href = '#viewBills';
			/*$scope.loadBillsSub();*/
			
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
		$('#remark-popup-sub').modal('hide');
		$('.modal-backdrop').remove();
		$window.location.href = '#viewBills';
	});
};

$scope.fetchFYWiseExpenditureList = function() {
	$loading.start('sample-1');
	fetchFYWiseExpenditureList();
};

$scope.fetchExAgWiseExpenditureList = function() {
	$loading.start('sample-1');
	fetchExAgWiseExpenditureList();
};

$scope.getBudgetAllotmentList = function() {
	$loading.start('sample-1');
	getBudgetAllotmentList();
};


$scope.loadFullDetailsForWork = function() {		
	$loading.start('sample-1');
	var response= $http.get('fetchFullDetailsForWork/'+$routeParams.id);
	
	response.success(function(data, status, headers, config) {
		$scope.workData = data;
		$loading.finish('sample-1');
	});
};


$scope.loadFinancialYearForExpenditureReport = function() {
	$loading.start('sample-1');
	var responsePromise = $http.get('getCurrentFinancialYear');
	responsePromise.success(function(data, status, headers, config) {
		//alert(data.number+' hh');
		$scope.myFincialYear=data.number;
		if (data != null) {
			var responsePromise1 = $http.get('getIdFromFinancialYearName?fyName='+data.number);
			responsePromise1.success(function(data, status, headers, config) {
				$scope.financialYearId=data+"";
				
				//alert('test '+$scope.myFincialYear);
				$loading.finish('sample-1');
			});
		}
	});
};

$scope.onChangeFYForExpenditure = function(){
	$loading.start('sample-1');
		reDraw();
		$loading.finish('sample-1');
	
};

$scope.fetchEeWiseExpenditureList = function() {
	$loading.start('sample-1');
	$scope.monthYear = $routeParams.monthYear;
	fetchEeWiseExpenditureList($routeParams.monthYear);
};

$scope.fetchAccountWiseExpenditureList = function() {
	$loading.start('sample-1');
	$scope.monthYear = $routeParams.monthYear;
	$scope.eeId = $routeParams.eeId;
	fetchAccountWiseExpenditureList($routeParams.monthYear, $routeParams.eeId);
};

$scope.fetchLineDeptWiseExpenditureList = function() {
	$loading.start('sample-1');
	$scope.monthYear = $routeParams.monthYear;
	$scope.eeId = $routeParams.eeId;
	fetchLineDeptWiseExpenditureList($routeParams.monthYear, $routeParams.eeId);
};

$scope.fetchWorkTypeWiseExpenditureList = function() {
	$loading.start('sample-1');
	$scope.monthYear = $routeParams.monthYear;
	$scope.eeId = $routeParams.eeId;
	fetchWorkTypeWiseExpenditureList($routeParams.monthYear, $routeParams.eeId);
};



$scope.fetchWorkWiseExpenditureList = function() {
	$loading.start('sample-1');
	$scope.monthYear = $routeParams.monthYear;
	$scope.eeId = $routeParams.eeId;
	$scope.workType =  $routeParams.workType;
	fetchWorkWiseExpenditureList($routeParams.monthYear, $routeParams.eeId, $routeParams.workType);
};


$scope.fetchWorkWiseExpenditureListByLineDept = function() {
	$loading.start('sample-1');
	
	$scope.monthYear = $routeParams.monthYear;
	$scope.eeId = $routeParams.eeId;
	$scope.lineDept =  $routeParams.lineDept;
	fetchWorkWiseExpenditureListByLineDept($routeParams.monthYear, $routeParams.eeId, $routeParams.lineDept);
};


$scope.fetchWorkWiseExpenditureListByAccHead = function() {
	$loading.start('sample-1');
	$scope.monthYear = $routeParams.monthYear;
	$scope.eeId = $routeParams.eeId;
	$scope.accHead =  $routeParams.accHead;
	
	fetchWorkWiseExpenditureListByAccHead($routeParams.monthYear, $routeParams.eeId, $routeParams.accHead);
};

$scope.fetchContractor = function() {

	$loading.start('sample-1');
	var response = $http.get('fetchContractorById/'+$routeParams.id);
	response.success(function(data, status, headers, config) {
		$scope.contractorBean = data;
		$loading.finish('sample-1');
	});
};

$scope.addContractor = function(form, isValid) {

	if (!isValid) 
		return false;
	
	if (confirm("Are you sure you want to save the data?")) {
		$loading.start('sample-1');
	
		var responsePromise = $http.post('addContractor', $scope.contractorBean);

		responsePromise.success(function(data, status, headers, config) {

			$rootScope.responseObject = data;

			if($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					$rootScope.responseObject.successMessage = null;
				}, 10000);
				$window.location.href = '#manageContractor';
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
	 
};

$scope.editContractor = function(form, isValid) {

	if (!isValid) 
		return false;
	
	if (confirm("Are you sure you want to save the data?")) {
		$loading.start('sample-1');
		
		$scope.contractorBean.id = $routeParams.id; 
		var responsePromise = $http.post('editContractor', $scope.contractorBean);

		responsePromise.success(function(data, status, headers, config) {

			$rootScope.responseObject = data;

			if($rootScope.responseObject.successMessage != null) {
				$timeout(function() {
					$rootScope.responseObject.successMessage = null;
				}, 10000);
				$window.location.href = '#manageContractor';
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
	 
};


		$scope.fetchBankList = function() {
			$loading.start('sample-1');
			fetchBankList();
		};
		
		$scope.fetchExpAmountHistory = function() {
			$loading.start('sample-1');
			fetchExpAmountHistory1();
		};
		
		$scope.fetchChangeHistory = function() {
			$loading.start('sample-1');
			fetchChangeHistory();
		};
		
		
		$scope.loadBanksDetail = function() {
		
			$loading.start('sample-1');
			var response = $http.get('fetchBankDetails/'+$routeParams.bankId);
		
			response.success(function(data, status, headers, config) {
				$scope.bankData = data;
				$loading.finish('sample-1');
			});
		};
		
		
		
		 $scope.editBank = function(form, isValid) {		
				
				if (!isValid ) 
					return false;

				if (confirm("Are you sure you want to save the data?")) {
					$scope.bankData.bankId = $routeParams.bankId;
					$loading.start('sample-1');
					var responsePromise = $http.post('editBank', $scope.bankData);

					responsePromise.success(function(data, status, headers, config) {

						$rootScope.responseObject = data;

						if($rootScope.responseObject.successMessage != null) {
							$timeout(function() {
								$rootScope.responseObject.successMessage = null;
							}, 5000);
							$window.location.href = '#manageBank';
							
						}
						if($rootScope.responseObject.errorMessage != null) {
							$timeout(function() {
								$rootScope.responseObject.errorMessage = null;
							}, 5000);
						}
						$loading.finish('sample-1');
					});
					
					responsePromise.error(function() {
						$rootScope.responseObject = {};
						$rootScope.responseObject.errorMessage = "Some error occured while saving the data";
						$timeout(function() {
							$rootScope.responseObject.errorMessage = null;
						}, 5000);
						$loading.finish('sample-1');
					});
				}
			};
			
			
			$scope.addBank = function(isValid) {		

				if (!isValid ) 
					return false;

				if (confirm("Are you sure you want to save the data?")) {
					$loading.start('sample-1');
					var responsePromise = $http.post('addBank', $scope.bankData);

					responsePromise.success(function(data, status, headers, config) {

						$rootScope.responseObject = data;

						if($rootScope.responseObject.successMessage != null) {
							$timeout(function() {
								$rootScope.responseObject.successMessage = null;
							}, 5000);
							$window.location.href = '#manageBank';
							
						}
						if($rootScope.responseObject.errorMessage != null) {
							$timeout(function() {
								$rootScope.responseObject.errorMessage = null;
							}, 5000);
						}
						$loading.finish('sample-1');
					});
					
					responsePromise.error(function() {
						$rootScope.responseObject = {};
						$rootScope.responseObject.errorMessage = "Some error occured while saving the data";
						$timeout(function() {
							$rootScope.responseObject.errorMessage = null;
						}, 5000);
						$loading.finish('sample-1');
					});
				}
		};
		// Rakesh Working
		
		$scope.loadWorkWithOldDispatchDetail = function() {	
			$loading.start('sample-1');
			var response;
			if(null!=$routeParams.parentId){
				response = $http.get('fetchWorkDetailsByEstimationAndParent/'+$routeParams.workId+'/'+$routeParams.estimationId+'/'+$routeParams.parentId);
			}else{
				response = $http.get('fetchWorkDetailsByEstimation/'+$routeParams.workId+'/'+$routeParams.estimationId);
			}
			
			
			var estimateId = $routeParams.estimationId;
			
			var technicalSanctionId = $routeParams.technicalSanctionId;
			
			response.success(function(data, status, headers, config) {
				$scope.workData = data;
				$scope.workData.workEstimateId = estimateId;
				$scope.workData.technicalSanctionId = technicalSanctionId;
				$scope.workData.workTypeId = $scope.workData.workTypeId+"";
				$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
				$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
				if($scope.workData.workSubTypeId)
				$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
// $scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
				
				if($scope.workData.physicalStageId == null)
				{
					$scope.workData.physicalStageId = "";
				}
				else
				{
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
// $scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
				if($scope.workData.assistantEngineerId == null)
					{
				$scope.workData.assistantEngineerId = "";
					} else {
						$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
					}
				
				
// $scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
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
				
				
				$scope.workData.workEstimationBean = data.workEstimationBean;
				
				if($scope.workData.technicalSanctionStatusId == 0) {
					$scope.tsStatus ='Waiting for TS Generation';
				} else if ($scope.workData.technicalSanctionStatusId == 1) {
					$scope.tsStatus ='TS Generated';
				} else if ($scope.workData.technicalSanctionStatusId == 2) {
					$scope.tsStatus ='TS Under Process (Saved as Draft)';
				}  else {
					$scope.tsStatus ='TS Dispatched';
				}
				
				

				
				$loading.finish('sample-1');
			});
		};
// Rakesh
		$scope.loadWorkDetailFourParent = function() {	
			
			$loading.start('sample-1');
			var response=null;
			if($routeParams.parentId!=null)
			{
				response = $http.get('fetchWorkDetailsByTechnicalAndParent/'+$routeParams.workId+'/'+$routeParams.technicalSanctionId+'/'+$routeParams.parentId);
			}
			else{
				response = $http.get('fetchWorkDetailsByTechnical/'+$routeParams.workId+'/'+$routeParams.technicalSanctionId);	
			}
			 
			
			var estimateId = $routeParams.estimationId;
			
			var technicalSanctionId = $routeParams.technicalSanctionId;
			
			response.success(function(data, status, headers, config) {
				$scope.workData = data;
				$scope.workData.workEstimateId = estimateId;
				$scope.workData.technicalSanctionId = technicalSanctionId;
				$scope.workData.workTypeId = $scope.workData.workTypeId+"";
				$scope.workData.accountHeadId = $scope.workData.accountHeadId+"";
				$scope.loadWorkSubTypeByWorkTypeId($scope.workData.workTypeId);
				if($scope.workData.workSubTypeId)
				$scope.workData.workSubTypeId = $scope.workData.workSubTypeId+"";
// $scope.loadPhysicalStageByWorkTypeId($scope.workData.workTypeId);
				
				if($scope.workData.physicalStageId == null)
				{
					$scope.workData.physicalStageId = "";
				} else
				{
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
// $scope.loadAssistantEngineerByOfficeId($scope.workData.executiveEngineerOfficeId);
				if($scope.workData.assistantEngineerId == null)
					{
				$scope.workData.assistantEngineerId = "";
					} else {
						$scope.workData.assistantEngineerId = $scope.workData.assistantEngineerId + "";	
					}
				
				
// $scope.loadSubEngineerByOfficeId($scope.workData.executiveEngineerOfficeId)
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
				
				$scope.workData.workEstimationBean = data.workEstimationBean;
				
				$scope.workData.technicalSanctionBean = data.technicalSanctionBean;
				
				// set default amount for add
				if($scope.workData.totalCost == null) {
					$scope.workData.totalCost = data.workEstimationBean.grandTotal;	
				}
				
				if($scope.workData.administrativeSanctionStatusId == 0)
				{

					$scope.administrativeSanctionStatus ='Waiting for AS Approval';
				  
				} else if ($scope.workData.administrativeSanctionStatusId == 1 ) {

					$scope.administrativeSanctionStatus ='AS is saved as Draft';
				 
				}  else {
					 
					$scope.administrativeSanctionStatus ='AS Received';
						
				}
				
				$loading.finish('sample-1');
			});
		};
		// Rakesh Add SQM User module
	// $scope.isWorksError=false;
		// $scope.isOfficeError=false;
		$scope.addUser = function(isValid) {
			
			if($('#officesHidden').val()==null||$('#officesHidden').val()==''){
			
				$scope.isOfficeError=true;
			}else{
				$scope.isOfficeError=false;
			}
			//alert($('#worksIds').val());
			if($('#worksIds').val()==null||$('#worksIds').val()==''){
				// alert($('#officesHidden').val());
				$scope.isWorkError=true;
			}else{
				$scope.isWorkError=false;
			}

if (!isValid||$scope.isOfficeError||$scope.emailExist||$scope.isWorkError) {
				
				return false;
			}		$scope.userData.worksIds=$('#worksIds').val();
			//alert($scope.userData.worksIds);
			if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
			var inner=	$scope.userData.worksIds.split(',');
			
		  var outer=	$('#officesHidden').val().split(',');
	 	for(var i=0;i<outer.length;i++){
			for(var j=0;j<inner.length;j++){
			if(outer[i]+'-'==inner[j]){
			
				break;
				}else{
													if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){
					$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
					
					$scope.isWorkError=true;
					alert('Please select at least one work for respective selected office ');
					return false;
													}
				
					break;
				}
			}
		}
	 	//alert('2nd '+$scope.userData.worksIds);
				
			}else{
				$scope.userData.worksIds=$('#officesHidden').val();
			}
			
						if (confirm("Are you sure you want to save the data?")) {
							$scope.userData.id = $routeParams.id;
							$scope.userData.password=null;
							/*$scope.userData.worksIds=$('#worksIds').val();
							//alert($scope.userData.worksIds);
							if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
							var inner=	$scope.userData.worksIds.split(',');
							
						  var outer=	$('#officesHidden').val().split(',');
					 	for(var i=0;i<outer.length;i++){
							for(var j=0;j<inner.length;j++){
							if(outer[i]+'-'==inner[j]){
							
								break;
								}else{
																	if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){
									$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
									
									$scope.isWorkError=true;
									alert('Please select at least one work for respective selected office ');
									return false;
																	}
								
									break;
								}
							}
						}
					 	//alert('2nd '+$scope.userData.worksIds);
								
							}else{
								$scope.userData.worksIds=$('#officesHidden').val();
							}*/
							 //alert('ddd>>'+$scope.userData.worksIds);
							$loading.start('sample-1');
							var responsePromise = $http.post('addUser', $scope.userData);
					
							responsePromise.success(function(data, status, headers, config) {
					
								$rootScope.responseObject = data;
					
								if($rootScope.responseObject.successMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.successMessage = null;
									}, 5000);
									$window.location.href = '#manageSqmUsers';
								}
								if($rootScope.responseObject.errorMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.errorMessage = null;
									}, 5000);
								}
								$loading.finish('sample-1');
							});
						}
					// }
					
				// });
		};
		
$scope.addOfficerInsp = function(isValid) {
	/*alert($('#officesHidden').val());*/
			
			if($('#officesHidden').val()==null||$('#officesHidden').val()==''){
			
				$scope.isOfficeError=true;
			}else{
				$scope.isOfficeError=false;
			}
			//alert($('#worksIds').val());
			if($('#worksIds').val()==null||$('#worksIds').val()==''){
				// alert($('#officesHidden').val());
				$scope.isWorkError=true;
			}else{
				$scope.isWorkError=false;
			}

if (!isValid||$scope.isOfficeError||$scope.emailExist||$scope.isWorkError) {
				
				return false;
			}		$scope.userData.worksIds=$('#worksIds').val();
			//alert($scope.userData.worksIds);
			if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
			var inner=	$scope.userData.worksIds.split(',');
			
		  var outer=	$('#officesHidden').val().split(',');
	 	for(var i=0;i<outer.length;i++){
			for(var j=0;j<inner.length;j++){
			if(outer[i]+'-'==inner[j]){
			
				break;
				}else{
													if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){
					$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
					
					$scope.isWorkError=true;
					alert('Please select at least one work for respective selected office ');
					return false;
													}
				
					break;
				}
			}
		}
	 	//alert('2nd '+$scope.userData.worksIds);
				
			}else{
				$scope.userData.worksIds=$('#officesHidden').val();
			}
			
						if (confirm("Are you sure you want to save the data?")) {
							$scope.userData.id = $routeParams.id;
							$scope.userData.password=null;
							/*$scope.userData.worksIds=$('#worksIds').val();
							//alert($scope.userData.worksIds);
							if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
							var inner=	$scope.userData.worksIds.split(',');
							
						  var outer=	$('#officesHidden').val().split(',');
					 	for(var i=0;i<outer.length;i++){
							for(var j=0;j<inner.length;j++){
							if(outer[i]+'-'==inner[j]){
							
								break;
								}else{
																	if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){
									$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
									
									$scope.isWorkError=true;
									alert('Please select at least one work for respective selected office ');
									return false;
																	}
								
									break;
								}
							}
						}
					 	//alert('2nd '+$scope.userData.worksIds);
								
							}else{
								$scope.userData.worksIds=$('#officesHidden').val();
							}*/
							 //alert('ddd>>'+$scope.userData.worksIds);
							$loading.start('sample-1');
							var responsePromise = $http.post('addOfficerInsp', $scope.userData);
					
							responsePromise.success(function(data, status, headers, config) {
					
								$rootScope.responseObject = data;
					
								if($rootScope.responseObject.successMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.successMessage = null;
									}, 5000);
									$window.location.href = '#manageInspUsers';
								}
								if($rootScope.responseObject.errorMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.errorMessage = null;
									}, 5000);
								}
								$loading.finish('sample-1');
							});
						}
					// }
					
				// });
		};
		
		
		
		$scope.changeValidation=function() {
			
			$scope.isOfficeError=false;
		// $scope.isWorkError=false;
		}

		// Update Sqm User
		$scope.updateSqmUser = function(isValid) {
		alert($('#officesHidden').val()+'');
		
			if($('#officesHidden').val()==null||$('#officesHidden').val()==''){
				// alert($('#officesHidden').val());
				$scope.isOfficeError=true;
			}else{
				$scope.isOfficeError=false;
			}
			if($('#worksIds').val()==null||$('#worksIds').val()==''){
// alert($('#worksIds').val());
				$scope.isWorkError=true;
			}else{
				$scope.isWorkError=false;
			}

			
if (!isValid||$scope.isOfficeError||$scope.isWorkError) {
				
				return false;
			}
			
$scope.userData.worksIds=$('#worksIds').val();
// alert($scope.userData.worksIds);
if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
	var inner=	$scope.userData.worksIds.split(',');
	
  var outer=	$('#officesHidden').val().split(',');
	for(var i=0;i<outer.length;i++){
	for(var j=0;j<inner.length;j++){
	if(outer[i]+'-'==inner[j]){
		// tempStr+=tempStr;
		break;
		}else{
		
			if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){

				$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
				
				$scope.isWorkError=true;
				alert('Please select at least one work for respective selected office ');
				return false;
												
			}
			
		
			break;
		}
	}
}
		
	}else{
		$scope.userData.worksIds=$('#officesHidden').val();
		// alert("else");
	}
						if (confirm("Are you sure you want to update the data?")) {
							$scope.userData.id = $routeParams.id;
							$scope.userData.password=null;
						/*	$scope.userData.worksIds=$('#worksIds').val();
						// alert($scope.userData.worksIds);
						if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
							var inner=	$scope.userData.worksIds.split(',');
							
						  var outer=	$('#officesHidden').val().split(',');
					 	for(var i=0;i<outer.length;i++){
							for(var j=0;j<inner.length;j++){
							if(outer[i]+'-'==inner[j]){
								// tempStr+=tempStr;
								break;
								}else{
								
									if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){

										$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
										
										$scope.isWorkError=true;
										alert('Please select at least one work for respective selected office ');
										return false;
																		
									}
									
								
									break;
								}
							}
						}
								
							}else{
								$scope.userData.worksIds=$('#officesHidden').val();
								// alert("else");
							}*/
						// alert('ddd>>'+$scope.userData.worksIds);
							$loading.start('sample-1');
							var responsePromise = $http.post('updateSqmUser', $scope.userData);
					
							responsePromise.success(function(data, status, headers, config) {
					
								$rootScope.responseObject = data;
					
								if($rootScope.responseObject.successMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.successMessage = null;
									}, 5000);
									$window.location.href = '#manageSqmUsers';
								}
								if($rootScope.responseObject.errorMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.errorMessage = null;
									}, 5000);
								}
								$loading.finish('sample-1');
							});
						}
					// }
					
				// });
		};
		
		// Rakesh new code
		$scope.emailExist=false;
		$scope.checkUser = function(email) {
			// alert(email);
			$scope.emailExist=false;
			// $loading.start('sample-1');
			if(email!=null){
			var response = $http.get('checkSqmUserDetailsByEmailId/'+email);
		
			response.success(function(data, status, headers, config) {
				// $scope.bankData = data;
				
				if(data==true||data=='true'){
				$scope.emailExist=true;
				// alert(data);
			}
				else
					{
					// alert(data);
					$scope.emailExist=false;}
			
						
				// $scope.userForm.email.$error.email=true;
				// $loading.finish('sample-1');
			});
			}
		};
		
		
		$('#view-sqm-inspections-popup' ).on("shown.bs.modal", function (){

			 var workid = $('#workid').val().trim();
			 
			 $loading.start('sample-1');

			 var response = $http.get('getAllSqmAllocationsByWorkId/' + workid);

			 response.success(function(data, status, headers, config) {
				 $scope.inspectionList = data;
				 
				 $loading.finish('sample-1');
			 })
		 });
		
$scope.multiDistrictSelect=function() {
	alert('distStr='+$scope.districtId);		
	// $('#districtId').val($scope.districtId+"");
	 
	 $('#distStr').val($scope.districtId+"");
		// $scope.isWorkError=false;
		};
		
		$scope.allSelected = false;
		
		$scope.cbChecked = function(){
		    $scope.allSelected = true;
		    angular.forEach($scope.districtsMP, function(v, k) {
		      if(!v.checked){
		        $scope.allSelected = false;
		      }
		    });
		  }
		  
		  $scope.toggleAll = function() {
		    var bool = true;
		    if ($scope.allSelected) {
		      bool = false;
		    }
		    angular.forEach($scope.districtsMP, function(v, k) {
		      v.checked = !bool;
		      $scope.allSelected = !bool;
		    });
		  }
		
		
		
		$scope.workTemplateGroupList=[]; 	
		  $scope.addNewGroup = function(isValid) {
			  	if (!isValid) 
					return false;
			 
			  	var workTemplate={};
			 /* 	var workTemplateGroup={};
			  	workTemplateGroup.groupName=$scope.itemGroupData.groupName;
			  	workTemplateGroup.remarks=$scope.itemGroupData.remarks;*/
			  	
		    	workTemplate.groupName=$scope.itemGroupData.groupName;
		    	workTemplate.remarks=$scope.itemGroupData.remarks;
		    	
		    	var listing=angular.copy($scope.workTemplateGroupItems);
		    	$(listing.reverse()).each(function( key, value) {
		    		if(null!= value['serialNo'] && value['serialNo']!=''){
		    			workTemplate.serialNo=value['serialNo']+1;
		    			return false;
		    		}
		    	});
		    	if($scope.itemGroupData.index!=null){
		    		$scope.workTemplateGroupItems[$scope.itemGroupData.index]=workTemplate;
		    	}
		    	else{
		    		$scope.workTemplateGroupItems.push(workTemplate);
		    	}
		    	$("#add-new-group-popup1").modal("hide");
			};
			
			$scope.loadSOR = function() {
				$loading.start('sample-1');
				var response = $http.get('fetchAllSOR');
				response.success(function(data, status, headers, config) {
					$scope.sors = data;
					$loading.finish('sample-1');
				});
			};
			
			
			$scope.removeGroupEntry = function(index) {
					$scope.workTemplateGroupItems.splice(index, 1);
			};
			$scope.editGroupEntry = function(index) {
				$scope.itemGroupData	=$scope.workTemplateGroupItems[index];
				$scope.itemGroupData.index=index;
		};
		//	($index)
		
		$scope.callChapterListBySORId = function () {
			var e = document.getElementById("sorId");
			var sorId = e.options[e.selectedIndex].value;
			var response = $http.get('fetchChaptersBySORId/'+sorId);
			response.success(function(data, status, headers, config) {
				$scope.chapterData = data;
				$loading.finish('sample-1');
			});
	
		};
		
		$scope.groupName={};
		$scope.callItemsByChapterId = function () {
			var e = document.getElementById("chapterId");
			var chapterId = e.options[e.selectedIndex].value;
			var response = $http.get('fetchItemsByChapterId/'+chapterId);
			
			response.success(function(data, status, headers, config) {
				$scope.myData = data;
				//alert($scope.itemGroupData.groupName);
				$scope.groupName=$scope.itemGroupData.groupName
				$loading.finish('sample-1');
			});
	
		};
		
		$scope.callItemsByYearChapterIdItemNoOrName = function () {
    var e = document.getElementById("chapterId");
    var chapterId = e.options[e.selectedIndex].value;
    var searchVal = $('#searchBox').val().trim();

    $loading.start('sample-1');

    // Ensure at least 4 characters before searching
    if (searchVal !== "" && searchVal.length >= 4) {
        var response = $http.get('fetchItemsByYearChapterIdItemNoOrName/' + chapterId + '/' + searchVal);

        response.then(function (res) {
            $scope.myData = res.data;
            // If groupName comes from the response:
            if (res.data && res.data.groupName) {
                $scope.groupName = res.data.groupName;
            }
            $loading.finish('sample-1');
        }, function (error) {
            console.error("Error fetching data:", error);
            $loading.finish('sample-1');
        });
    } else {
        $loading.finish('sample-1');
    }
};
		
		$scope.myData={};
		$scope.getTotal = function(){
		    var total = 0;
		    for(var i = 0; i < $scope.myData.length; i++){
		        var billItem12 = $scope.myData[i];
		        total += (parseFloat((billItem12.no==null || billItem12.no=='')? 0: billItem12.no) *parseFloat((billItem12.rate==null||billItem12.rate=='')?0:billItem12.rate));
		    }
		    return total;
		}
		$scope.addTheseRowItems = function(isValid) {
			//alert($scope.currentGroup.index)//for group disable comment
			$scope.itemGroupData=$scope.itemsListArray;
			//$scope.workTemplateGroupItems[$scope.currentGroup.index].newGroupItemList = $scope.itemsListArray;////for group disable comment
			document.getElementById("chapterId").value='';
			$scope.myData={};
			
			$("#add-sor-popup").modal("hide");
		}
		
		$scope.addSorItems =function(index) {			
			$scope.currentGroup=	$scope.workTemplateGroupItems[index];
			$scope.currentGroup.index=index;
		};
		
		$scope.addWorkEstimationItemsEntrySORFlow = function(index, type) {			
			$scope.sorChapterItemData={};
			if(null!=index && index!=''){
				$scope.sorChapterItemData.index=index;
				$scope.sorChapterItemData.type=type;
				$scope.sorChapterItemData.group='No';
			}
		};
		
		$scope.itemsListArray = [];
		$scope.selectSORItems = function(index) {
			
			if($scope.myData[index].confirmed){
				var workTemplate={};
				workTemplate.sorItemNoReadOnly=true;
				workTemplate.descReadOnly=true;
				workTemplate.measureLength=true;
				workTemplate.measureWidth=true;
				workTemplate.measureHeightDepth=true;
				workTemplate.rateReadOnly=true;
				workTemplate.unitReadOnly=true;
				workTemplate.noReadOnly=true;
				workTemplate.hasChild=true;
				
		    	workTemplate.id=$scope.myData[index].id;
		    	workTemplate.sorItemNo=$scope.myData[index].itemNumber;
		    	workTemplate.itemDesc=$scope.myData[index].description;
		    	workTemplate.unit=$scope.myData[index].unit.unit;
		    	workTemplate.rate=$scope.myData[index].rate;
		    	if($scope.myData[index].group=='Yes'){
		    		workTemplate.group=true;
		    	}else{
		    		workTemplate.group=false;
		    	}
		    	/*if($scope.myData[index].lengthApplicable=='Y'){
		    		workTemplate.measureLength=true;
		    	}else{
		    		workTemplate.measureLength=false;
		    	}
		    	if($scope.myData[index].widthApplicable=='Y'){
		    		workTemplate.measureWidth=true;
		    	}else{
		    		workTemplate.measureWidth=false;
		    	}
		    	if($scope.myData[index].heightApplicable=='Yes'){
		    		workTemplate.measureHeightDepth=true;
		    	}else{
		    		workTemplate.measureHeightDepth=false;
		    	}*/
		    	workTemplate.length=$scope.myData[index].length;
		    	workTemplate.width=$scope.myData[index].width;
		    	workTemplate.heightDepth=$scope.myData[index].heightDepth;
		    	workTemplate.quantity=$scope.myData[index].quantity;
		    	workTemplate.amount=$scope.myData[index].amount;
		    	workTemplate.no=$scope.myData[index].no;
		    	workTemplate.lengthApplicable=$scope.myData[index].lengthApplicable;
				workTemplate.widthApplicable=$scope.myData[index].widthApplicable;
				workTemplate.heightApplicable=$scope.myData[index].heightApplicable;
		    	var listing=angular.copy($scope.itemsListArray);
		    	
		    	
		    	workTemplate.childsCount=0;
		    	workTemplate.parentIndex=index++;
		    	workTemplate.isParent=true
		    	//workTemplate.parentItem=$scope.myData[index].id;
		    	workTemplate.hasChild=true;
		    	$(listing.reverse()).each(function( key, value) {
		    		if(null!= value['serialNo'] && value['serialNo']!=''){
		    			workTemplate.serialNo=value['serialNo']+1;
		    			return false;
		    		}
		    	});
		    	$scope.itemsListArray.push(workTemplate);
		    	
			}else{
				$scope.itemsListArray.splice(index, 1);
			}
			//$scope.itemsListArray.push($scope.myData[index]);
			
		};
		
	
		

		$scope.addNewEmptySubItemEntry = function(index) {		
			var workTemplateItem =$scope.itemGroupData[index];
			
			var workTemplate={};
	    	workTemplate.hasChild=false;
	    	
	    	workTemplate.measureLength=workTemplateItem.measureLength;
	    	workTemplate.measureWidth=workTemplateItem.measureWidth;
	    	workTemplate.measureHeightDepth=workTemplateItem.measureHeightDepth;
	    	workTemplate.unit=workTemplateItem.unit;
	    	workTemplate.id=workTemplateItem.id;
	    	workTemplate.rate=workTemplateItem.rate;
	    	workTemplate.rateReadOnly=true;
	    	
	    	//workTemplate.parentItem=workTemplateItem.parentId;
	    	/*workTemplate.parentIndex=index;
	    	workTemplate.parentId=workTemplateItem.id
	    	workTemplate.new=false;
	    	workTemplate.lastElement=true;
	    	workTemplate.leafNode=workTemplateItem.parentId;*/
	    	workTemplate.childsCount=workTemplateItem.childsCount++;
	    	workTemplate.isChild=true
	    	
	    	
	    	workTemplate.group=true;   //##Group
	    	
	    	workTemplate.sorItemNoReadOnly=true;
	    	workTemplate.noReadOnly=false;
	    	
	    	if(workTemplateItem.lengthApplicable=='Y'){
	    		workTemplate.measureLength=false;
	    	}else{
	    		workTemplate.measureLength=true;
	    	}
	    	if(workTemplateItem.widthApplicable=='Y'){
	    		workTemplate.measureWidth=false;
	    	}else{
	    		workTemplate.measureWidth=true;
	    	}
	    	if(workTemplateItem.heightApplicable=='Y'){
	    		workTemplate.measureHeightDepth=false;
	    	}else{
	    		workTemplate.measureHeightDepth=true;
	    	}
	    		
	    	
	    	if(workTemplateItem.group){
	    		workTemplate.new=false;
	    		workTemplate.leafNode=false;
	    	}else if(!workTemplateItem.hasChild && null==workTemplateItem.sorItemNo && null!= workTemplate.parentItem && workTemplate.parentItem.sorItemNo!=null){
	    		workTemplate.new=false;
	    	}
	    	
	    	var cumulativeChildsCount=0;
	    	if(null!= workTemplateItem.cumulativeChildsCount){
	    		cumulativeChildsCount=workTemplateItem.cumulativeChildsCount;
	    	}
	    	var newlyAddedIndex=index+cumulativeChildsCount+1;
	    	
	      if(null!=workTemplateItem.hasChild && (!workTemplateItem.hasChild ||  $scope.itemGroupData[workTemplateItem.parentIndex]!=null) && null!=workTemplateItem.parentItem){
	    	  var parentTemplate=$scope.itemGroupData[workTemplateItem.parentIndex];
	    	  parentTemplate.cumulativeChildsCount=$scope.itemGroupData[workTemplateItem.parentIndex].cumulativeChildsCount+1;
	    	  if(null!= $scope.itemGroupData[parentTemplate.parentIndex]){
	    		  $scope.itemGroupData[parentTemplate.parentIndex].cumulativeChildsCount=$scope.itemGroupData[parentTemplate.parentIndex].cumulativeChildsCount+1;
	    	  }
	    	}
	      if(null!= workTemplate.parentId){
	    	  var parentItem=$filter('filter')($scope.itemGroupData, {id: workTemplate.parentId}, true)[0];
	    	  parentItem.childsCount=parentItem.childsCount+1;
	      }
	    	
	    	$scope.itemGroupData.splice(newlyAddedIndex, 0, workTemplate);
	    	workTemplateItem.lastElement=false;
	    	$scope.calculateTotalAmount();
			$scope.calculateTotalLabourComponent();
			
		};
		
		$scope.fetchPaymentWiseExpenditureList = function() {
			$loading.start('sample-1');
			fetchPaymentWiseExpenditureList();
		}
		
		$scope.removeSubItemEntry = function(index) {
			var element=$scope.itemGroupData[index];
			if(element.isParent){
				var one =$scope.itemGroupData[index].id;
				var two = "";
				if($scope.itemGroupData[index+1]){
					two = $scope.itemGroupData[index+1].id
					if(one==two){
						alert("You can not remove parent, untill child Exist.")
					}else{
						$scope.itemGroupData.splice(index, 1)
					}
				}else{
					$scope.itemGroupData.splice(index, 1)
				}
			}
			
			if(element.isChild){
				$scope.itemGroupData.splice(index, 1)
			}
			
			
			$scope.calculateTotalAmount();
			$scope.calculateTotalLabourComponent();
		
			
			
	};
	
	
	
	$scope.changeFlag = function(workId) {		
		if (confirm("Are you sure to change this flag from  false To true?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('changeFlag/'+ workId);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#manageLegacyDataRouteForENC';
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
	
	$scope.getMaxBillAmt = function(workId,adminAmount,adminAmount1) {
		$scope.workData = {};
		$scope.workData.workId = workId;
		$scope.workData.adminAmount = adminAmount;
		$scope.workData.adminAmount1 = adminAmount1;
		//$('#adminAmount').val(adminAmount);
		// $('#workId').val(workId);
		$('#Ae-update-popup').modal('show');
	}
	
	$scope.addMaxBillAmt = function(workId,adminAmount,addMaxBillAmt1,form, isValid,role) {
	/*	alert(workId);
		alert(adminAmount);
		alert(addMaxBillAmt1);*/
		
		/*alert("please enter amount");
		return false;*/
		if (!isValid) {				
			return false;
		}
		if(adminAmount>addMaxBillAmt1){
			alert("Max Billing Amount can not greater than Administration Amount!");
			return false;
		}
		if (confirm("Are you sure to add expendture amount?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('changeFlag/'+ workId+'/'+adminAmount);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					if(role=='ENC'){
					$window.location.href = '#manageLegacyDataRouteForENC';
					}else{
						$window.location.href = '#viewCEWorksForAllData';
					}
				}
				if($rootScope.responseObject.errorMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.errorMessage = null;
					}, 5000);
					
				}
				$('#Ae-update-popup').modal('hide');
				$(".modal-backdrop").hide();
				$('.modal-backdrop').remove();
				$('body').removeClass('modal-open');
				$loading.finish('sample-1');
			});
		}else{
			return false;
		}
	};
	
	$scope.changeFlagToZ = function(workId) {		
		if (confirm("Are you sure to change this flag from  true To false?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('changeFlagToZ/'+ workId);
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
	
	$scope.allowSubEngToPrepareBill = function(workId) {		
		if (confirm("Are you sure to Allow Sub Eng To Prepare Bill Of Extra Amount?")) {
			$loading.start('sample-1');
			var responsePromise = $http.get('allowSubEngToPrepareBill/'+ workId);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#viewAllWorks';
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
	
//	//
	
	
	
	
	
	
	$scope.loadBillDetailsforAE = function(id) {
		/*alert(id);*/

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		
		var responseForUser = $http.get('fetchBillDetails/'+id);
		responseForUser.success(function(data, status, headers, config) {
			$scope.billData = data;
			$scope.billData.totalAmountUpToDate = 0;
			$scope.billData.totalAmountPreviousBill = 0;
			
			$scope.billBean={};
			$scope.billBean.inspectedBy = {};
			
			
			$scope.billBean.inspectedById = $scope.billData.inspectedBy.id+"";
			/*$scope.billBean.inspectedById = $scope.billData.inspectedBy.id+"";*/
			
			$scope.billBean.id = id;
			
			
			/*$loading.finish('sample-1');*/
		});
		
		
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			/*$loading.finish('sample-1');*/
		}).
		then(function (){
			/*$loading.start('sample-1');*/
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }
			
			
		
			
			
			$loading.finish('sample-1');
			
		}).then(function() {
			$('#Ae-update-popup').modal('show');
		});
	
		

		
		
	};
	
	$scope.loadBillDetailsforSESDO  = function(id) {
		/*alert(id);*/

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		
		var responseForUser = $http.get('fetchBillDetails/'+id);
		responseForUser.success(function(data, status, headers, config) {
			$scope.billData = data;
			$scope.billData.totalAmountUpToDate = 0;
			$scope.billData.totalAmountPreviousBill = 0;
			
			
			$scope.billBean={};
			
			$scope.billBean.inspectedById = {};
			$scope.billBean.inspectedByIdEE = null;
			
			
			$scope.billBean.inspectedById = $scope.billData.inspectedBy.id+"";
			if(null!=$scope.billData.inspectedByEE){
			
				/*$scope.billBean.inspectedByIdEE = {};*/
				/*alert($scope.billData.inspectedByEE.id);*/
			$scope.billBean.inspectedByIdEE = $scope.billData.inspectedByEE.id+"";
			}
			/*else{
				
				$scope.billBean.inspectedByIdEE=null;
				alert($scope.billData.inspectedByEE);
			}*/
			
			$scope.billBean.id = id;
			
			/*$loading.finish('sample-1');*/
		});
		
		
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			/*$loading.finish('sample-1');*/
		}).
		then(function (){
			/*$loading.start('sample-1');*/
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			$scope.inspectionByListSE = [];
			$scope.inspectionByListSDO = [];
			
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				
				var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }
			
			$loading.finish('sample-1');
			
		}).then(function() {
			$('#SeSdo-update-popup').modal('show');
		});
	
		

		
		
	};
	
	$scope.updateSESDOName = function(form, isValid ) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to update Inspected Users ?")) {			

			var responsePromise = $http.post('updateSESDOName', $scope.billBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
				    
					$scope.loadBills();
					$('#SeSdo-update-popup').modal('hide');
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
				$('#SeSdo-update-popup').modal('hide');
			});
		}
		 
	};
	
	$scope.loadBillDetailsforSESDOEE  = function(id) {
		/*alert(id);*/

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		
		var responseForUser = $http.get('fetchBillDetails/'+id);
		responseForUser.success(function(data, status, headers, config) {
			$scope.billData = data;
			$scope.billData.totalAmountUpToDate = 0;
			$scope.billData.totalAmountPreviousBill = 0;
			
			
			$scope.billBean={};
			
			$scope.billBean.inspectedById = {};
			$scope.billBean.inspectedByIdEE = null;
			
			
			$scope.billBean.inspectedById = $scope.billData.inspectedBy.id+"";
			if(null!=$scope.billData.inspectedByEE){
			
				/*$scope.billBean.inspectedByIdEE = {};*/
				/*alert($scope.billData.inspectedByEE.id);*/
			$scope.billBean.inspectedByIdEE = $scope.billData.inspectedByEE.id+"";
			}
			/*else{
				
				$scope.billBean.inspectedByIdEE=null;
				alert($scope.billData.inspectedByEE);
			}*/
			
			$scope.billBean.id = id;
			
			/*$loading.finish('sample-1');*/
		});
		
		
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			/*$loading.finish('sample-1');*/
		}).
		then(function (){
			/*$loading.start('sample-1');*/
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			$scope.inspectionByListSE = [];
			$scope.inspectionByListSDO = [];
			
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response3.success(function(data, status, headers, config) {
						$scope.inspectionByListEE = data;
				});
				var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }
			
			$loading.finish('sample-1');
			
		}).then(function() {
			$('#SeSdoEe-update-popup').modal('show');
		});
	
		

		
		
	};
	
	$scope.updateSESDOEEName = function(form, isValid ) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to update Inspected Users ?")) {			

			var responsePromise = $http.post('updateSESDOEEName', $scope.billBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
				    
					$scope.loadBills();
					$('#SeSdoEe-update-popup').modal('hide');
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
				$('#SeSdoEe-update-popup').modal('hide');
			});
		}
		 
	};
	
	$scope.loadBillDetailsforAEEE = function(id) {
		/*alert(id);*/

		$loading.start('sample-1');
		
		$scope.gt1 = 0;
		$scope. gt2 = 0;
		
		var responseForUser = $http.get('fetchBillDetails/'+id);
		responseForUser.success(function(data, status, headers, config) {
			$scope.billData = data;
			$scope.billData.totalAmountUpToDate = 0;
			$scope.billData.totalAmountPreviousBill = 0;
			
			
$scope.billBean={};
			
			$scope.billBean.inspectedById = {};
			$scope.billBean.inspectedByIdEE = null;
			
			
			$scope.billBean.inspectedById = $scope.billData.inspectedBy.id+"";
			if(null!=$scope.billData.inspectedByEE){
			
				/*$scope.billBean.inspectedByIdEE = {};*/
				/*alert($scope.billData.inspectedByEE.id);*/
			$scope.billBean.inspectedByIdEE = $scope.billData.inspectedByEE.id+"";
			}
			/*else{
				
				$scope.billBean.inspectedByIdEE=null;
				alert($scope.billData.inspectedByEE);
			}*/
			
			$scope.billBean.id = id;
			
			/*$loading.finish('sample-1');*/
		});
		
		
		 
		var response = $http.get('fetchWorkDetailsByBillId/'+id);
		response.success(function(data, status, headers, config) {
			$scope.workData = data;
			/*$loading.finish('sample-1');*/
		}).
		then(function (){
			/*$loading.start('sample-1');*/
			
			$scope.measuredByList = [];
			$scope.inspectionByListAE = [];
			$scope.inspectionByListEE = [];
			
			if($scope.workData.userBean.officeBean) {
				var response1 = $http.get('fetchSubEngAndAeByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response1.success(function(data, status, headers, config) {
						$scope.measuredByList = data;
				});
				var response2 = $http.get('fetchAeAndSubEngByOfficeId/'+$scope.workData.userBean.officeBean.id);
				response2.success(function(data, status, headers, config) {
						$scope.inspectionByListAE = data;
				});
				
				var response3 = $http.get('fetchExecutiveEngineersByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response3.success(function(data, status, headers, config) {
				$scope.inspectionByListEE = data;
			});
			var response4 = $http.get('fetchSubDivisionOfficerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response4.success(function(data, status, headers, config) {
					$scope.inspectionByListSDO = data;
			});
			
			var response5 = $http.get('fetchSubEngineerByOfficeId/'+$scope.workData.executiveEngineerOfficeId);
			response5.success(function(data, status, headers, config) {
					$scope.inspectionByListSE = data;
			});
	       }
			
			
			
		
			
			
			$loading.finish('sample-1');
			
		}).then(function() {
			$('#AeEe-update-popup').modal('show');
		});
	
		

		
		
	};
	
	
	$scope.updateAeName = function(form, isValid ) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to update Inspected User ?")) {			

			var responsePromise = $http.post('updateAeName', $scope.billBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
				    
					$scope.loadBills();
					$('#Ae-update-popup').modal('hide');
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
				$('#Ae-update-popup').modal('hide');
			});
		}
		 
	};
	
$scope.updateAeEeName = function(form, isValid ) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to update Inspected Users ?")) {			

			var responsePromise = $http.post('updateAeEeName', $scope.billBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
				    
					$scope.loadBills();
					$('#AeEe-update-popup').modal('hide');
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
				$('#Ae-update-popup').modal('hide');
			});
		}
		 
	};
	
	
$scope.updateBillStatusRemarks = function(form, isValid ) {
		
		if (!isValid) {				
			return false;
		}
		
		if (confirm("Are you sure you want to Reject & Revise ?")) {			

			var responsePromise = $http.post('updateBillStatusRemarks', $scope.billBean);

			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
				    
					/*$scope.loadBills();*/
					$('#billStatus-update-popup').modal('hide');
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
				$('#billStatus-update-popup').modal('hide');
			});
		}
		 
	};
	
$scope.deleteBillRemarks = function(form, isValid ) {
		
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
					$('#billDelete-update-popup').modal('hide');
					
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
				$('#billDelete-update-popup').modal('hide');
			});
		}
		 
	};
	
	$scope.updateBillStatus = function(id) {
		/*alert(id);*/
		$loading.start('sample-1');
		$scope.billBean={};
		$scope.billBean.id = id;
		$loading.finish('sample-1');
		$('#billStatus-update-popup').modal('show');
		
     
	};
	
	$scope.deleteBillLoad = function(id) {
		/*alert(id);*/
		$loading.start('sample-1');
		$scope.billBean={};
		$scope.billBean.id = id;
		$loading.finish('sample-1');
		$('#billDelete-update-popup').modal('show');
		
     
	};
	
	
	$scope.loadEEofficesBySupdtOfficeIds = function(supdtOfficeIds) {

		$loading.start('sample-1');
		if (typeof(supdtOfficeIds) != "undefined" && supdtOfficeIds.length>0 ) {
		var response = $http.get('fetchEEofficesBySupdtOfficeIds/'+supdtOfficeIds);
		response.success(function(data, status, headers, config) {
			
			$scope.exeOffices = data;
			
			
			
			
			setTimeout(function(){
				$('#exeOfficeId').selectpicker('refresh');
				$loading.finish('sample-1');
				}, 1000) ;
			
		});
		} else {
		
			$('#exeOfficeId').val('');
			/*$('#villageId').val('');
			$('#habitatId').val('');*/
			$scope.exeOfficeIds = null;
			$scope.exeOffices ='';
			
			/*$scope.workData.villageStrIds = null;
			$scope.workData.habitatStrIds = null;*/
			
			/*$scope.gramPanchayats = '';
			$scope.villages='';
			$scope.habitations='';*/
			
			
			
			setTimeout(function(){
				$('#exeOfficeId').selectpicker('refresh');
				/*$('#villageId').selectpicker('refresh');
				$('#habitatId').selectpicker('refresh');*/
				$loading.finish('sample-1');
				}, 1000) ;
		}
	};
	
	$scope.loadEEofficesBySupdtOfficeIdsCeEnc = function(supdtOfficeIds) {

		$loading.start('sample-1');
		if (typeof(supdtOfficeIds) != "undefined" ) {
		var response = $http.get('fetchEEofficesBySupdtOfficeIdsCeEnc/'+supdtOfficeIds);
		response.success(function(data, status, headers, config) {
			
			$scope.exeOffices = data;
			
			
			
			
			
			
			
			setTimeout(function(){
				$('#exeOfficeId').selectpicker('refresh');
				$loading.finish('sample-1');
				}, 1000) ;
			
		});
		} else {
		
			$('#exeOfficeId').val('');
			/*$('#villageId').val('');
			$('#habitatId').val('');*/
			$scope.exeOfficeIds = null;
			$scope.exeOffices ='';
			
			/*$scope.workData.villageStrIds = null;
			$scope.workData.habitatStrIds = null;*/
			
			/*$scope.gramPanchayats = '';
			$scope.villages='';
			$scope.habitations='';*/
			
			
			
			setTimeout(function(){
				$('#exeOfficeId').selectpicker('refresh');
				/*$('#villageId').selectpicker('refresh');
				$('#habitatId').selectpicker('refresh');*/
				$loading.finish('sample-1');
				}, 1000) ;
		}
	};
	
	$scope.loadEEofficesBySupdtOfficeIdsCeEncChecked = function(supdtOfficeIds,userId) {

		$loading.start('sample-1');
		if (typeof(supdtOfficeIds) != "undefined" ) {
		var response = $http.get('fetchEEofficesBySupdtOfficeIdsCeEncChecked/'+supdtOfficeIds+'/'+userId);
		response.success(function(data, status, headers, config) {
			
			$scope.exeOffices = data;
			
			var checkedOffices="";
			for(var i=0;i<$scope.exeOffices.length;i++){
				// userData.officesHidden
				
				if($scope.exeOffices[i].isSqmChecked==1){
					checkedOffices+=$scope.exeOffices[i].id+",";	
					// alert('sdsdsd=='+$scope.offices[i].id);
				}
			}
			
			if(checkedOffices.length>0){
			var lastChar = checkedOffices.slice(-1);
			if (lastChar == ',') {
				checkedOffices = checkedOffices.slice(0, -1);
			}
			  $('#officesHidden').val(checkedOffices+"");
			}
			
			
			
			
			setTimeout(function(){
				$('#exeOfficeId').selectpicker('refresh');
				$loading.finish('sample-1');
				}, 1000) ;
			
		});
		} else {
		
			$('#exeOfficeId').val('');
			/*$('#villageId').val('');
			$('#habitatId').val('');*/
			$scope.exeOfficeIds = null;
			$scope.exeOffices ='';
			
			/*$scope.workData.villageStrIds = null;
			$scope.workData.habitatStrIds = null;*/
			
			/*$scope.gramPanchayats = '';
			$scope.villages='';
			$scope.habitations='';*/
			
			
			
			setTimeout(function(){
				$('#exeOfficeId').selectpicker('refresh');
				/*$('#villageId').selectpicker('refresh');
				$('#habitatId').selectpicker('refresh');*/
				$loading.finish('sample-1');
				}, 1000) ;
		}
	};
	
	//loadEEofficesBySupdtOfficeIds
	$scope.loadWorkSubTypeByWorkTypeIds = function(workTypeIds) {
		

		$loading.start('sample-1');
		if (typeof(workTypeIds) != "undefined" && workTypeIds.length>0 ) {
		var response = $http.get('fetchWorkSubTypeByWorkTypeIds/'+workTypeIds);
		response.success(function(data, status, headers, config) {
			
			$scope.workSubtypes = data;
			
			
			
			
			setTimeout(function(){
				$('#workSubTypeId').selectpicker('refresh');
				$loading.finish('sample-1');
				}, 1000) ;
			
		});
		} else {
		
			$('#workSubTypeId').val('');
			/*$('#villageId').val('');
			$('#habitatId').val('');*/
			$scope.workData.workSubTypeId = null;
			$scope.workSubtypes ='';
			
			/*$scope.workData.villageStrIds = null;
			$scope.workData.habitatStrIds = null;*/
			
			/*$scope.gramPanchayats = '';
			$scope.villages='';
			$scope.habitations='';*/
			
			
			
			setTimeout(function(){
				$('#workSubTypeId').selectpicker('refresh');
				/*$('#villageId').selectpicker('refresh');
				$('#habitatId').selectpicker('refresh');*/
				$loading.finish('sample-1');
				}, 1000) ;
		}
	};
	
	$scope.loadDesignationsType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchDesignationsType');
		response.success(function(data, status, headers, config) {
			$scope.designations = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.loadUsersByDesig = function(desigId) {

		$loading.start('sample-1');
		
		/*var response = $http.get('fetchWorkSubTypeByWorkTypeId/'+workTypeId);*/
		var response = $http.get('loadUsersByDesig/'+desigId);
		response.success(function(data, status, headers, config) {
			$scope.users = data;
			
				/*angular.forEach($scope.workSubTypes, function(value, key) {
				console.log("2nd>>"value.id);
			});*/
			
				/*$('#workSubTypeId').multiselect('rebuild');*/
				// $('#workSubTypeId').multiselect('refresh');
			/*$scope.fetchExeAgWiseExpenditureListData($scope.financialYearId, $scope.districtId, $scope.executionAgencyId,$scope.lineDepartmentId,$scope.accountHeadId,$scope.workStatusId,$scope.month,$scope.workTypeId,$scope.workSubTypeId);*/
			
			
			$loading.finish('sample-1');
			
			
		});
	};
	
	
	$scope.updateOfficerInsp = function(isValid) {
		 /*alert($('#officesHidden').val()+'');*/
		
			if($('#officesHidden').val()==null||$('#officesHidden').val()==''){
				// alert($('#officesHidden').val());
				$scope.isOfficeError=true;
			}else{
				$scope.isOfficeError=false;
			}
			if($('#worksIds').val()==null||$('#worksIds').val()==''){
// alert($('#worksIds').val());
				$scope.isWorkError=true;
			}else{
				$scope.isWorkError=false;
			}

			
if (!isValid||$scope.isOfficeError||$scope.isWorkError) {
				
				return false;
			}
			
$scope.userData.worksIds=$('#worksIds').val();
// alert($scope.userData.worksIds);
if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
	var inner=	$scope.userData.worksIds.split(',');
	
  var outer=	$('#officesHidden').val().split(',');
	for(var i=0;i<outer.length;i++){
	for(var j=0;j<inner.length;j++){
	if(outer[i]+'-'==inner[j]){
		// tempStr+=tempStr;
		break;
		}else{
		
			if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){

				$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
				
				$scope.isWorkError=true;
				alert('Please select at least one work for respective selected office ');
				return false;
												
			}
			
		
			break;
		}
	}
}
		
	}else{
		$scope.userData.worksIds=$('#officesHidden').val();
		// alert("else");
	}
						if (confirm("Are you sure you want to update the data?")) {
							$scope.userData.id = $routeParams.id;
							$scope.userData.password=null;
						/*	$scope.userData.worksIds=$('#worksIds').val();
						// alert($scope.userData.worksIds);
						if($scope.userData.worksIds!=null&&$scope.userData.worksIds.length>0){
							var inner=	$scope.userData.worksIds.split(',');
							
						  var outer=	$('#officesHidden').val().split(',');
					 	for(var i=0;i<outer.length;i++){
							for(var j=0;j<inner.length;j++){
							if(outer[i]+'-'==inner[j]){
								// tempStr+=tempStr;
								break;
								}else{
								
									if(!($scope.userData.worksIds.indexOf((outer[i]+'-')) != -1)){

										$scope.userData.worksIds=	$scope.userData.worksIds+","+outer[i];
										
										$scope.isWorkError=true;
										alert('Please select at least one work for respective selected office ');
										return false;
																		
									}
									
								
									break;
								}
							}
						}
								
							}else{
								$scope.userData.worksIds=$('#officesHidden').val();
								// alert("else");
							}*/
						// alert('ddd>>'+$scope.userData.worksIds);
							$loading.start('sample-1');
							var responsePromise = $http.post('updateOfficerInsp', $scope.userData);
					
							responsePromise.success(function(data, status, headers, config) {
					
								$rootScope.responseObject = data;
					
								if($rootScope.responseObject.successMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.successMessage = null;
									}, 5000);
									$window.location.href = '#manageInspUsers';
								}
								if($rootScope.responseObject.errorMessage != null) {
									$timeout(function() {
										$rootScope.responseObject.errorMessage = null;
									}, 5000);
								}
								$loading.finish('sample-1');
							});
						}
					// }
					
				// });
		};
		
		$scope.loadNameOfOfficers = function() {

			$loading.start('sample-1');
			var response = $http.get('fetchNameOfOfficers');
			response.success(function(data, status, headers, config) {
				$scope.nameOfSqm = data;
				$loading.finish('sample-1');
			});
		};
		
	       $scope.selectSORItemsInBilling = function(index) {
	    	   
				if($scope.myData[index].confirmed){
					//for validation
		    		for(i = 0 ; i < $scope.billData.billItems[index].length; i++) {
		    			if($scope.billData.billItems[i].unit==$scope.myData[index].unit.unit &&
								$scope.billData.billItems[i].rate==$scope.myData[index].rate 
								&& ($scope.billData.billItems[i].itemOfWork==$scope.myData[index].description || 
										$scope.billData.billItems[i].itemOfWork==($scope.myData[index].parentDesc+'-'+$scope.myData[index].description))){
						//	alert($scope.billData.billItems[i].unit +' hh');
							//alert("Item already exist.");
							//return;
						}
		    		}
					//Validation
					var billItem={};
					billItem.workTemplateId=$scope.myData[index].workTemplateId;
					
					
					billItem.sorType=$scope.myData[index].sorType;
					
					if(null==$scope.myData[index].itemNumber){
						billItem.sorItemNo=$scope.myData[index].parentItemNumber;	
					}else{
						billItem.sorItemNo=$scope.myData[index].itemNumber;
					}
					
					if(null!= $scope.myData[index].chapter && null!= $scope.myData[index].chapter.sorBean){
						billItem.sorId=$scope.myData[index].chapter.sorBean.id;
					}
					
					
					billItem.unit=$scope.myData[index].unit.unit;
					billItem.rate=$scope.myData[index].rate;
					billItem.rateFinal=$scope.myData[index].rate;
					billItem.ratePercentage='100';
					billItem.itemOfWork=$scope.myData[index].parentDesc+'-'+$scope.myData[index].description;
					if($scope.myData[index].parentDesc==null)
					billItem.itemOfWork=$scope.myData[index].description;
					
					billItem.amountPreviousBill='0';
					billItem.amountUptodate='0';
					//new code 
					billItem.rateFinal=$scope.myData[index].rate;
			    	//$scope.billData.billItems.push(billItem);
					billItem.quantityUptodate=0;
				
					billItem.templateGroupId=$scope.billData.billItems[index].id;
					billItem.itemCount = $scope.itemCount+1;
					$scope.billData.billItems.push(billItem);
					
					//$scope.coi
					//$scope.billData.billItems[$scope.coi].billItems.push(billItem);
					//$scope.billData.billItems[index].unshift(billItem);
					
					alert("selected Item added successfully.");
			    	
				}else{
					//for(i = 0 ; i < $scope.billData.billItems.length; i++) {
					/*for(i = 0 ; i < $scope.billData.billItems[index].length; i++) {
						//$scope.billData.totalAmountUpToDate = parseInt($scope.billData.totalAmountUpToDate) + parseInt($scope.billData.billItems[i].amountUptodate? $scope.billData.billItems[i].amountUptodate : 0);
						//$scope.billData.totalAmountPreviousBill = parseInt($scope.billData.totalAmountPreviousBill) + parseInt($scope.billData.billItems[i].amountPreviousBill ? $scope.billData.billItems[i].amountPreviousBill: 0); 
					if($scope.billData.billItems[i].unit==$scope.myData[index].unit.unit &&
							$scope.billData.billItems[i].rate==$scope.myData[index].rate 
							&& ($scope.billData.billItems[i].itemOfWork==$scope.myData[index].description || 
									$scope.billData.billItems[i].itemOfWork==($scope.myData[index].parentDesc+'-'+$scope.myData[index].description))){
					//	alert($scope.billData.billItems[i].unit +' hh');
						$scope.billData.billItems.splice(i, 1);
						alert("unselected Item removed successfully.");
					}
					
					}*/
					
					var billItem = {};
					
					if(null==$scope.myData[index].itemNumber){
						billItem.sorItemNo=$scope.myData[index].parentItemNumber;	
					}else{
						billItem.sorItemNo=$scope.myData[index].itemNumber;
					}
					
					for(i = 0 ; i < $scope.billData.billItems.length; i++) {
		    			if(null != $scope.billData.billItems[i].sorItemNo && $scope.billData.billItems[i].sorItemNo == billItem.sorItemNo ){
		    				$scope.billData.billItems.splice(i, 1);
		    				itemCount = itemCount-1;
		    				alert("unselected Item removed successfully.");
						}
		    		}
					
				}
				//$scope.itemsListArray.push($scope.myData[index]);
				
			};
			
			
			
			$scope.resetFunctionForMultiselect = function() {
				$window.location.reload();
			};
			
			$scope.reloadJqueryDatatableEnc = function(){
				$loading.start('sample-1');
				$scope.setDistrictValue();
				
					reDraw();
					$loading.finish('sample-1');
				
			};
			
			$scope.setDistrictValue = function(){
				//alert($('#districtId').val());
				var districtId = $('#districtId').val();
				if(null != districtId) {
					$rootScope.searchDistrict = districtId;
				}
			};
			
			$scope.getDistrictValue = function(){
				if(null != $rootScope.searchDistrict) {
					$('#districtId').val($rootScope.searchDistrict);
					var districtId = $('#districtId').val();
					$scope.searchDistrict = districtId;
					$scope.searchDistrict = $scope.searchDistrict+"";
				}
			};
	
			$scope.validateBillInDraftOrNot = function() {
				
				$scope.errorMessage10 = null;
				
				if($scope.lastBillData.statusId) {
					if($scope.lastBillData.statusId == 1 ) {
					 	$scope.errorMessage10 = "Your previous bill is in draft stage,so you can't generate new Bill!";
					}
				}
			}
			
			//--------added by aman start code
	// AngularJS Controller for handling password expiry and sidebar visibility
			$scope.verifyUserPasswordExpiry = function() {
				// Show a loading indicator
				$loading.start('sample-1');
//alert("---");
				$http.get('verifyUserPasswordExpiry')
					.then(function(response) {
						// Handle success response
						if (response.status === 200) {
							$scope.responseObject = response.data; // Assuming response.data contains ResponseObject
    						 // var roleCode = response.data.roleCode;
    						// Check for expired password
							if (response.data.successMessage.includes("Your password has expired")) {
							//	alert('aaaaaaaa');
								// Password is expired, hide the sidebar and redirect to change password page
								alert("Your password has expired. Please Update your Password");
								document.getElementById("sideNav").style.display = "none"; // Hide sidebar
								window.location.href = '#/changepassword'; // Redirect to change password page

							}
							// Check for password about to expire
							else if (response.data.successMessage.includes("Your password will expire")) {
								//alert('bbbbb');
								// Show confirmation dialog for the user to update the password
								if (!$window.confirm(response.data.successMessage)) {
									  window.location.href = '#/dashboard'; // Redirect to dashboard if dismissed
								}
							}
							// Password is still valid
							else if (response.data.successMessage.includes("Your password is still valid")) {
								//alert('ccccccc');
								// Password is still valid, show the sidebar
								document.getElementById("sideNav").style.display = "block"; // Show sidebar
								  window.location.href = '#/dashboard';
							}
							else {
								alert(response.data.successMessage);

							}
						}
						$loading.finish('sample-1');
					});
			};
			
				
				
			$scope.wrongCurrentPassword = false;
			$scope.currentPasswordChecked = false;
			
			$scope.checkCurrentPassword = function () {
			
			    if (!$scope.changePasswordData.currentPassword)
			        return;
			
			    var data = {
			        currentPassword: hash($scope.changePasswordData.currentPassword)
			    };
			
			    $http.post('validateCurrentPassword', data)
			        .success(function (response) {
			
			            if (response.errorMessage === 'INVALID_CURRENT_PASSWORD') {
			                $scope.wrongCurrentPassword = true;
			                $scope.currentPasswordChecked = false;
			            } else {
			                $scope.wrongCurrentPassword = false;
			                $scope.currentPasswordChecked = true;
			            }
			        })
			        .error(function () {
			            $scope.wrongCurrentPassword = false;
			        });
			};									
				
	
});