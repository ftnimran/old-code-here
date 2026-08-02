
var res = angular.module('res');

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

res.controller('AdminController', function($scope, $loading, $rootScope, $window, $routeParams, $http, $timeout) {
	$scope.started = false;
	$scope.IsVisible = false;
	$scope.IsEditVisible = false;
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
	
	$scope.loadWorkType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchWorkType');
		response.success(function(data, status, headers, config) {
			$scope.workTypes = data;
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
	
	$scope.loadAccountHead = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchAccountHead');
		response.success(function(data, status, headers, config) {
			$scope.accountHeads = data;
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
	$scope.loadTechnicalSanctionType = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchTechnicalSanctionType');
		response.success(function(data, status, headers, config) {
			$scope.technicalSanctionTypes = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.searchByReferenceNameYearOrAnyFilters = function(){
		$loading.start('sample-1');
		if(($('#searchBox').val()!="" && $('#searchBox').val().trim().length >=4) || $('#referencename').val()!="" || $('#year').val()!=""){
			reDraw();
		}
		else{
			$loading.finish('sample-1');
		}
	};
	
	
	$scope.loadSORList = function() {

		$loading.start('sample-1');
		fetchSORList();
	};
	
	$scope.loadThisSORChaptersList = function() {
		$loading.start('sample-1');
		fetchThisSORChapterList($routeParams.id);
	};
	$scope.addSOR = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to save the data?")) {
			$loading.start('sample-1');
		
			var responsePromise = $http.post('addSOR', $scope.sorBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#manageSORs';
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
	
	
	$scope.fetchSOR = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchSORById/'+$routeParams.id);
		response.success(function(data, status, headers, config) {
			$scope.sorBean = data;
			$scope.sorBean.year.id=data.year.id+"";
			$loading.finish('sample-1');
		});
	};
	
	$scope.editSOR = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to update the data?")) {
			$loading.start('sample-1');
			
			$scope.sorBean.id = $routeParams.id; 
			var responsePromise = $http.post('editSOR', $scope.sorBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#manageSORs';
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
	
	
	$scope.loadYears = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchYears');
		response.success(function(data, status, headers, config) {
			$scope.years = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.loadStatus = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchStatus');
		response.success(function(data, status, headers, config) {
			$scope.statuses = data;
			$loading.finish('sample-1');
		});
	};
	
	
	$scope.addChapter = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to save the Chapter Definition data?")) {
			$loading.start('sample-1');
		
			var responsePromise = $http.post('addChapter', $scope.chapterBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;
				
				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#editSORForm/'+$routeParams.id;
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
	
	$scope.fetchChapter = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchChapterById/'+$routeParams.id+'/'+$routeParams.chapter);
		response.success(function(data, status, headers, config) {
			$scope.chapterBean = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.editChapter = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to update the data?")) {
			$loading.start('sample-1');
			
			$scope.chapterBean.chapter = $routeParams.chapter; 
			var responsePromise = $http.post('editChapter', $scope.chapterBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#editSORForm/'+$routeParams.id;
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
	$scope.loadThisChapterItemList = function() {
		$loading.start('sample-1');
		fetchThisChapterItemList($routeParams.id, $routeParams.chapter);
	};
	$scope.showHideDiv = function(){
		if($scope.IsVisible){
			$scope.IsVisible=false;
		}else{
			$scope.IsVisible=true;
		}
		if($scope.IsVisible1){
			$scope.IsVisible1=false;
		}else{
			$scope.IsVisible1=true;
		}
	}	
	$scope.showHideEditDiv = function(){
		if($scope.IsVisible2){
			$scope.IsVisible2=false;
		}else{
			$scope.IsVisible2=true;
		}
    }
	
	$scope.loadUnit = function() {

		$loading.start('sample-1');
		var response = $http.get('fetchUnit');
		response.success(function(data, status, headers, config) {
			$scope.units = data;
			$loading.finish('sample-1');
		});
	};
	
	$scope.addItem = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to save the Item Definition data?")) {
			$loading.start('sample-1');
			$scope.itemBean.chapter={};
			$scope.itemBean.chapter=$scope.chapterBean;
			var responsePromise = $http.post('addItem', $scope.itemBean);
			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#editChapterForm/'+$routeParams.id+'/'+$routeParams.chapter;
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
	
	/*$scope.fetchItem = function() {
		$loading.start('sample-1');
		var response = $http.get('fetchItemById/'+$scope.itemId);
		response.success(function(data, status, headers, config) {
			$scope.sorBean = data;
			$scope.sorBean.year.id=data.year.id+"";
			$loading.finish('sample-1');
		});
	};*/
	$scope.fetchItemById = function(id) {
		$scope.IsVisible=false;
		$loading.start('sample-1');
		var response = $http.get('fetchItemById/'+id);
		response.success(function(data, status, headers, config) {
			$scope.itemBean = data;
			var rate = document.getElementById("rateEdit");
			var lengthApplicable = document.getElementById("lengthApplicableEdit");
			var widthApplicable = document.getElementById("widthApplicableEdit");
			var heightApplicable = document.getElementById("heightApplicableEdit");
			var unit = document.getElementById("unitEdit");
			if($scope.itemBean.isParent=='Y'){
				rate.disabled = true;			
			} 
			if ($scope.itemBean.isParent=='Y' && $scope.itemBean.parentId!=null && $scope.itemBean.parentId!=''){
				rate.disabled = true;
				rate.value="";
				lengthApplicable.disabled = true;
				widthApplicable.disabled = true;
				heightApplicable.disabled = true;
				unit.disabled = true;
			} 
			if ($scope.itemBean.isParent=='N' && $scope.itemBean.parentId!=null && $scope.itemBean.parentId!=''){
				rate.disabled = false;
				//unit.disabled = true;
				lengthApplicable.disabled = true;
				widthApplicable.disabled = true;
				heightApplicable.disabled = true;
			}
			if($scope.itemBean.unit!=null && $scope.itemBean.unit.id!=null){
				$scope.itemBean.unit.id=$scope.itemBean.unit.id+"";
			}
			$loading.finish('sample-1');
		});
	};
	
	$scope.fetchSubItemById = function(id) {
		$loading.start('sample-1');
		$scope.IsVisible=false;
		$scope.IsVisible1=false;
		$scope.IsVisible3=true;
		
		var response = $http.get('fetchSubItemById/'+id);
		response.success(function(data, status, headers, config) {
			$scope.itemBean={};
			$scope.itemBean.parentId=data.parentId;
			$scope.itemBean.itemNumber = data.itemNumber;
			/*if($scope.itemBean.unit!=null){
				$scope.itemBean.unit.id=$scope.itemBean.unit.id+"";
			}*/
			$loading.finish('sample-1');
			
		});
	};

	
	$scope.updateItem = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to update the data?")) {
			$loading.start('sample-1');
			
			//$scope.chapterBean.chapter = $routeParams.chapter; 
			var responsePromise = $http.post('updateItem', $scope.itemBean);

			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#editChapterForm/'+$routeParams.id+'/'+$routeParams.chapter;
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
	
	
	$scope.deleteItem = function(id, sorId, chapterId, isParent) {	
		if(isParent=='N'){
		if (confirm("Are you sure to delete this item entry?")) {
			$loading.start('sample-1');

			var responsePromise = $http.get('deleteItem/'+ id);
			responsePromise.success(function(data, status, headers, config) {
				$rootScope.responseObject = data;
				if ($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						 $rootScope.responseObject.successMessage = null;
				    }, 5000);
					$window.location.href = '#editChapterForm/'+sorId+'/'+chapterId;
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
		}else{
			alert("Parent Items can not be deleted.");
		}
	};
	$scope.enableDisableItemFields = function () {
		var rate = document.getElementById("rate");
		var unit = document.getElementById("unit");
		var e = document.getElementById("isParent");
		var isParent = e.options[e.selectedIndex].value;
		if('Y' == isParent) {
			rate.disabled = true;
			rate.value=null;
			$scope.itemBean.rate=null;
			document.getElementById("rate").required = false;
			document.getElementById("rate").removeAttribute("required");
			
			unit.disabled = true;
			unit.value=null;
			document.getElementById("unit").required = false;
			document.getElementById("unit").removeAttribute("required");
			$scope.itemBean.unit=null;

		}
		else if('N' == isParent) {
			rate.disabled = false;
			rate.value=null;
			
			unit.disabled = false;
			unit.value="";
		}else{
			rate.disabled = false;
			unit.disabled = false;
			$scope.itemBean.unit=null;
		}
		
	};
	$scope.enableDisableItemFieldsSubItemNew = function () {
		var rate = document.getElementById("rateSubItem");
		var unit = document.getElementById("unitSubItem");
		var e = document.getElementById("isParentSubItem");
		var isParent = e.options[e.selectedIndex].value;
		if('Y' == isParent) {
			rate.disabled = true;
			rate.value=null;
			$scope.itemBean.rate=null;
			document.getElementById("rateSubItem").required = false;
			document.getElementById("rateSubItem").removeAttribute("required");
			
			unit.disabled = true;
			unit.value=null;
			document.getElementById("unitSubItem").required = false;
			document.getElementById("unitSubItem").removeAttribute("required");
			$scope.itemBean.unit=null;

		}
		else if('N' == isParent) {
			rate.disabled = false;
			rate.value=null;
			
			unit.disabled = false;
			unit.value="";
		}else{
			rate.disabled = false;
			unit.disabled = false;
			$scope.itemBean.unit=null;
		}
		
	};
	$scope.enableDisableItemFieldsUpdatePageNew = function () {
		var rate = document.getElementById("rateEdit");
		var unit = document.getElementById("unitEdit");
		var e = document.getElementById("isParentEdit");
		var isParent = e.options[e.selectedIndex].value;
		if('Y' == isParent) {
			rate.disabled = true;
			rate.value=null;
			$scope.itemBean.rate=null;
			document.getElementById("rateEdit").required = false;
			document.getElementById("rateEdit").removeAttribute("required");
			
			unit.disabled = true;
			unit.value=null;
			document.getElementById("unitEdit").required = false;
			document.getElementById("unitEdit").removeAttribute("required");
			$scope.itemBean.unit=null;

		}
		else if('N' == isParent) {
			rate.disabled = false;
			rate.value=null;
			
			unit.disabled = false;
			unit.value="";
		}else{
			rate.disabled = false;
			unit.disabled = false;
			$scope.itemBean.unit=null;
		}
		
	};
	
	$scope.enableDisableItemFieldsUpdatePage = function () {
		var rate = document.getElementById("rateEdit");
		var e = document.getElementById("isParentEdit");
		var isParent = e.options[e.selectedIndex].value;
		if('Y' == isParent) {
			rate.disabled = true;
			rate.value="";
			rate.required = false;
			rate.removeAttribute("required");

		}
		else if('N' == isParent) {
			rate.disabled = false;
		}

	};
	$scope.enableDisableSubItemFields = function () {
		var rate = document.getElementById("rateSubItem");
		var lengthApplicable = document.getElementById("lengthApplicableSubItem");
		var widthApplicable = document.getElementById("widthApplicableSubItem");
		var heightApplicable = document.getElementById("heightApplicableSubItem");
		var unitSubItem = document.getElementById("unitSubItem");
		var e = document.getElementById("isParentSubItem");
		var isParent = e.options[e.selectedIndex].value;
	if('Y' == isParent) {
		rate.disabled = true;
		rate.value="";
		lengthApplicable.disabled = true;
		widthApplicable.disabled = true;
		heightApplicable.disabled = true;
		unitSubItem.disabled = true;
	}
	else if('N' == isParent) {
		rate.disabled = false;
		lengthApplicable.disabled = true;
		widthApplicable.disabled = true;
		heightApplicable.disabled = true;
	}
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
			$scope.workData.blockId = $scope.workData.blockId+"";
			$scope.loadGramPanchayatByBlockCode($scope.workData.blockId);
			$scope.workData.gramPanchayatId = $scope.workData.gramPanchayatId+"";
			$scope.loadVillageByGramPanchayatCode($scope.workData.gramPanchayatId);
			$scope.workData.villageId = $scope.workData.villageId+"";
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
	
	$scope.addSubItem = function(form, isValid) {

		if (!isValid) 
			return false;
		
		if (confirm("Are you sure you want to save the Sub Item Definition data?")) {
			$loading.start('sample-1');
			$scope.itemBean.chapter={};
			$scope.itemBean.chapter=$scope.chapterBean;
			var responsePromise = $http.post('addSubItem', $scope.itemBean);
			responsePromise.success(function(data, status, headers, config) {

				$rootScope.responseObject = data;

				if($rootScope.responseObject.successMessage != null) {
					$timeout(function() {
						$rootScope.responseObject.successMessage = null;
					}, 10000);
					$window.location.href = '#editChapterForm/'+$routeParams.id+'/'+$routeParams.chapter;
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
	
$scope.fetchMeasurmentUnitDetails = function(id) {
		
		if(null!= id){
			$loading.start('sample-1');
			var response = $http.get('fetchUnitById/'+id);
			response.success(function(data, status, headers, config) {
				$scope.unitBean = data;
				if($scope.unitBean!=null){
					$scope.itemBean.lengthApplicable=$scope.unitBean.lengthApplicable;
					$scope.itemBean.widthApplicable=$scope.unitBean.widthApplicable;
					$scope.itemBean.heightApplicable=$scope.unitBean.heightDepthApplicable;
				}
				$loading.finish('sample-1');
			});
		}else{
			$scope.itemBean.lengthApplicable=null;
			$scope.itemBean.widthApplicable=null;
			$scope.itemBean.heightApplicable=null;
		}
		
	};
});