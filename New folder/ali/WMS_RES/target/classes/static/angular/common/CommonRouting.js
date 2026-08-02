/*var res = angular.module('res', ['ngRoute','darthwade.dwLoading','ngIdle','ui.bootstrap']);
res.config(['KeepaliveProvider', 'IdleProvider', function(KeepaliveProvider, IdleProvider) {
	  IdleProvider.idle(1800);
	  IdleProvider.timeout(2);
	  KeepaliveProvider.interval(2);
	}]);

res.run(['Idle', function(Idle) {
Idle.watch();
}]);*/
res
	.config( ['$routeProvider', function($routeProvider) {
		$routeProvider
			.when('/dashboard', {
				templateUrl: 'dashboard',
				controller : 'CommonController'
			})
			.when('/changepassword', {
				templateUrl: 'changepassword',
				controller : 'CommonController'
			})
			
			.when('/manageusers', {
				templateUrl: 'manageusers',
				controller : 'CommonController'
			})
			//Rakesh
			.when('/manageSqmUsers', {
				templateUrl: 'manageSqmUsers',
				controller : 'CommonController'
			})
			
			.when('/manageInspUsers', {
				templateUrl: 'manageInspUsers',
				controller : 'CommonController'
			})
			
			.when('/manageAccountHead', {
				templateUrl: 'manageAccountHead',
				controller : 'CommonController'
			})
			.when('/manageLineDepartment', {
				templateUrl: 'manageLineDepartment',
				controller : 'CommonController'
			})
			.when('/manageContractor', {
				templateUrl: 'manageContractor',
				controller : 'CommonController'
			}) 
			 
			.when('/addContractorForm', {
				templateUrl: 'addContractorForm',
				controller : 'CommonController'
			}) 
			.when('/editContractorForm/:id', {
				templateUrl: function(params){ return 'editContractorForm/' + params.id; }, 
				controller : 'CommonController'
			}) 
			.when('/editUserForm/:id', {
				templateUrl: function(params){ return 'editUserForm/' + params.id; }, 
				controller : 'CommonController'
			})
			//Rakesh
			.when('/addSqmUserForm', {
				templateUrl: 'addSqmUserForm',
				controller : 'CommonController'
			})
				.when('/addOffInspForm', {
				templateUrl: 'addOffInspForm',
				controller : 'CommonController'
			})
			//Rakesh
			.when('/editSqmUserForm/:id', {
				templateUrl: function(params){ return 'editSqmUserForm/' + params.id; }, 
				controller : 'CommonController'
			})
			.when('/manageBank', {
				templateUrl: 'manageBank',
				controller : 'CommonController'
			})
			.when('/addBankForm', {
				templateUrl: 'addBankForm',
				controller : 'CommonController'
			}) 
			.when('/editBankForm/:bankId', {
				templateUrl: function(params){ return 'editBankForm/' + params.bankId; }, 
				controller : 'CommonController'
			})
			
			.when('/viewBills', {
				templateUrl: 'viewBills',
				controller : 'CommonController'
			})
			
			.when('/viewPayments', {
				templateUrl: 'viewPayments',
				controller : 'CommonController'
			})
			
			.when('/addNewBill/:id',{
				templateUrl: function(params){ return 'addNewBill/' + params.id; }, 
				controller : 'CommonController'
			})
			.when('/searchWorkForBill', {
				templateUrl: 'searchWorkForBill',
				controller : 'CommonController'
			})
			.when('/editBill/:id', {
				templateUrl: function(params){ return 'editBill/' + params.id; }, 
				controller : 'CommonController'
			})
			
			.when('/reviseBill/:id', {
				templateUrl: function(params){ return 'reviseBill/' + params.id; }, 
				controller : 'CommonController'
			})
			
			.when('/viewBill/:id', {
				templateUrl: function(params){ return 'viewBill/' + params.id; }, 
				controller : 'CommonController'
			})
			.when('/printBill/:id', {
				templateUrl: function(params){ return 'printBill/' + params.id; }, 
				controller : 'CommonController'
			})
			.when('/printOriginalBill/:id', {
				templateUrl: function(params){ return 'printOriginalBill/' + params.id; }, 
				controller : 'CommonController'
			})
			
			.when('/billPaymentForm/:id', {
				templateUrl: function(params){ return 'billPaymentForm/' + params.id; }, 
				controller : 'CommonController'
			})
			
			.when('/contingencyForm/:id', {
				templateUrl: function(params){ return 'contingencyForm/' + params.id; }, 
				controller : 'CommonController'
			})
			.when('/viewContingency/:id', {
				templateUrl: function(params){ return 'viewContingency/' + params.id; }, 
				controller : 'CommonController'
			})
			.when('/viewPayment/:id', {
				templateUrl: function(params){ return 'viewPayment/' + params.id; }, 
				controller : 'CommonController'
			})
			
			.when('/viewEEReportsRoute',{
			templateUrl: 'viewEeReportsMapping',
			controller : 'CommonController'
			})
			.when('/viewEEReportsRouteStatus/:id',{
				templateUrl: function(params){ return 'viewEeReportsMappingStatus/' + params.id; }, 
			controller : 'CommonController'
			})
			
			.when('/viewSupdteReportsRoute',{
			templateUrl: 'viewSupdteReportsMapping',
			controller : 'CommonController'
			})
			
			.when('/viewCEReportsRoute',{
			templateUrl: 'viewCeReportsMapping',
			controller : 'CommonController'
			})
			
			.when('/manageLegacyDataRoute',{
			templateUrl: 'manageLegacyDataMapping',
			controller : 'CommonController'
			})
				.when('/manageLegacyDataRouteForENC',{
			templateUrl: 'manageLegacyDataMappingForENC',
			controller : 'CommonController'
			})
			.when('/manageWorkRequisitionDataRoute',{
			templateUrl: 'manageWorkRequisitionDataMapping',
			controller : 'CommonController'
			})
			.when('/viewAllWorks', {
			templateUrl: 'viewAllWorks',
			controller : 'CommonController'
		})
		.when('/viewAllWorksForSupdt', {
			templateUrl: 'viewAllWorksForSupdt',
			controller : 'CommonController'
		})
		.when('/viewAlLWorksForDirGp', {
			templateUrl: 'viewAllWorks',
			controller : 'CommonController'
		})
		.when('/viewAllWorksForSubE', {
			templateUrl: 'viewAllWorksForSubAe',
			controller : 'CommonController'
		})
		
			.when('/viewPendingWorkEstimations', {
				templateUrl: 'viewPendingWorkEstimations',
				controller : 'CommonController'
			})
		.when('/addWorkEstimation/:workTypeId/:id', {
			templateUrl: function(params){ return 'addWorkEstimation/' +params.workTypeId+'/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/reviseWorkEstimation/:estimationType/:id', {
			templateUrl: function(params){ return 'reviseWorkEstimation/' +params.estimationType+'/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/viewWorkEstimation/:workTypeId/:id', {
			templateUrl: function(params){ return 'viewWorkEstimation/' +params.workTypeId+'/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/viewHEstimation/:estimationId/:id', {
			templateUrl: function(params){ return 'viewHEstimation/' +params.estimationId+'/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/viewHistoryWorkEstimation/:id/:estimationId', {
			templateUrl: function(params){ return 'viewHistoryWorkEstimation/' + params.id+'/'+ params.estimationId;}, 
			controller : 'CommonController'
		})
		.when('/manageTechnicalSanctionDataRoute',{
			templateUrl: 'manageTechnicalSanctionDataMapping',
			controller : 'CommonController'
			})
			
			.when('/manageSqmInspection',{
			templateUrl: 'manageSqmInspection',
			controller : 'CommonController'
			})
			
			.when('/manageRandomInspection',{
			templateUrl: 'manageRandomInspection',
			controller : 'CommonController'
			})
			
			.when('/manageOfficerInspection',{
			templateUrl: 'manageOfficerInspection',
			controller : 'CommonController'
			})
			
			.when('/manageAdministrationSanctionDataRoute',{
			templateUrl: 'manageAdministrationSanctionDataMapping',
			controller : 'CommonController'
			})
			.when('/addAdminSanctionRoute/:workId/:technicalSanctionId', {
			templateUrl: function(params){ return 'addAdminSanction/' +params.workId+'/'+ params.technicalSanctionId; }, 
			controller : 'CommonController'
		})
		//Rakesh
		.when('/addAdminSanctionByParentRoute/:workId/:technicalSanctionId/:parentId', {
			templateUrl: function(params){ return 'addAdminSanctionByParent/' +params.workId+'/'+ params.technicalSanctionId+'/'+ params.parentId; }, 
			controller : 'CommonController'
		})
		//Rakesh
		//Long workId,@PathVariable Long workEstimationId
		.when('/viewHistoryWorkTechnicalSectionRoute/:workId/:workEstimationId', {
			templateUrl: function(params){ return 'viewHistoryWorkTechnicalSectionMapping/' +params.workId+'/'+ params.workEstimationId; }, 
			controller : 'CommonController'
		})
		//
		.when('/viewHistoryWorkAdminstrativeSanctionRoute/:workId/:technicalSanctionId', {
			templateUrl: function(params){ return 'viewHistoryWorkAdminstrativeSanctionRouteMapping/' +params.workId+'/'+ params.technicalSanctionId; }, 
			controller : 'CommonController'
		})
		
		.when('/editAdminSanctionRoute/:workId/:technicalSanctionId', {
			templateUrl: function(params){ return 'editAdminSanction/' +params.workId+'/'+ params.technicalSanctionId; }, 
			controller : 'CommonController'
		})
		/*.when('/viewAdminSection/:workTypeId/:id', {
			templateUrl: function(params){ return 'viewAdminSection/' +params.workTypeId+'/'+ params.id; }, 
			controller : 'CommonController'
		})*/
		
		.when('/administrationSanctionHindiForm/:workTypeId/:id', {
			templateUrl: function(params){ return 'administrationSanctionHindiForm/' +params.workTypeId+'/'+ params.id; }, 
			controller : 'CommonController'
		})
		
		/*.when('/editAdminSection/:workTypeId/:id', {
			templateUrl: function(params){ return 'editAdminSection/' +params.workTypeId+'/'+ params.id; }, 
			controller : 'CommonController'
		})*/
		.when('/manageLegacyDataRouteDuringReport/:workStatus/:isLegacy', {
			templateUrl: function(params){  return 'manageLegacyDataMappingDuringReport/' + params.workStatus+'/'+params.isLegacy; }, 
			controller : 'CommonController'
		}).when('/manageLegacyDataRouteDuringReportStatus/:workStatus/:id/:isLegacy', {
			templateUrl: function(params){  return 'manageLegacyDataRouteDuringReportStatus/' + params.workStatus+'/'+params.id+'/' +params.isLegacy; }, 
			controller : 'CommonController'
		})
		.when('/manageLegacyDataRouteDuringReportStatusCE/:workStatus/:officeId/:id/:isLegacy', {
			templateUrl: function(params){  return 'manageLegacyDataRouteDuringReportStatusSupdt/' + params.workStatus+'/'+params.id+'/'+params.id+'/' +params.isLegacy; }, 
			controller : 'CommonController'
		})
		
		.when('/manageLegacyDataRouteDuringReportStatusSupdt/:workStatus/:officeId/:id/:isLegacy', {
			templateUrl: function(params){  return 'manageLegacyDataRouteDuringReportStatusSupdt/' + params.workStatus+'/'+params.id+'/'+params.id+'/' +params.isLegacy; }, 
			controller : 'CommonController'
		})
		.when('/manageLegacyDataRouteDuringReportAdmin/:workStatus/:isLegacy/:officeId', {
			templateUrl: function(params){  return 'manageLegacyDataMappingDuringReportAdmin/' + params.workStatus+'/'+params.isLegacy+'/' +params.officeId; }, 
			controller : 'CommonController'
		})
		//
		.when('/manageListLegacyDataRouteDuringReportAdmin/:workStatus/:isLegacy/:officeId/:agencyTypeId/:exeOfficeId/:supdtOfficeId/:lineDepartmentId/:accountHeadId/:workStatusId/:workTypeId/:workSubTypeId', {
			templateUrl: function(params){  return 'manageListLegacyDataMappingDuringReportAdmin/' + params.workStatus+'/'+params.isLegacy+'/' +params.officeId+'/' +params.agencyTypeId+'/' +params.exeOfficeId+'/' +params.supdtOfficeId+'/' +params.lineDepartmentId+'/' +params.accountHeadId+'/' +params.workStatusId+'/' +params.workTypeId+'/' +params.workSubTypeId; }, 
			controller : 'CommonController'
		})
		.when('/manageDataForSupdtStatusWiseReports/:workStatus/:isLegacy/:officeId/:agencyTypeId/:exeOfficeId/:lineDepartmentId/:accountHeadId/:workStatusId/:workTypeId/:workSubTypeId', {
			templateUrl: function(params){  return 'manageDataForSupdtStatusWiseReports/' + params.workStatus+'/'+params.isLegacy+'/' +params.officeId+'/' +params.agencyTypeId+'/' +params.exeOfficeId+'/' +params.lineDepartmentId+'/' +params.accountHeadId+'/' +params.workStatusId+'/' +params.workTypeId+'/' +params.workSubTypeId; }, 
			controller : 'CommonController'
		})
		.when('/manageDataForPaymentWiseReport/:billStatus/:eeOfficeId/:fromYear/:endYear/:lineDepartmentId/:accountHeadId/:workStatusId/:executionAgencyId/:workTypeId/:workSubTypeId', {
			templateUrl: function(params){  return 'manageDataForPaymentWiseReport/' + params.billStatus+'/'+params.eeOfficeId+'/' +params.fromYear+'/' +params.endYear+'/' +params.lineDepartmentId+'/' +params.accountHeadId+'/' +params.workStatusId+'/' +params.executionAgencyId+'/' +params.workTypeId+'/' +params.workSubTypeId; }, 
			controller : 'CommonController'
		})
		.when('/manageDataForMultiStatusReport/:id/:agencyTypeId/:exeOfficeId/:lineDepartmentId/:accountHeadId/:workStatusId/:workTypeId/:workSubTypeId', {
			templateUrl: function(params){  return 'manageDataForMultiStatusReport/' + params.id+'/'+params.agencyTypeId+'/'+params.exeOfficeId+'/'+params.lineDepartmentId+'/'+params.accountHeadId+'/'+params.workStatusId+'/'+params.workTypeId+'/'+params.workSubTypeId; }, 
			controller : 'CommonController'
		})
		
		.when('/manageDataPendingForInspectionReport/:id/:agencyTypeId/:fromYear/:endYear/:lineDepartmentId/:accountHeadId/:workTypeId/:workSubTypeId/:name', {
			templateUrl: function(params){  return 'manageDataPendingForInspectionReport/' + params.id+'/'+params.agencyTypeId+'/' +params.fromYear+'/' +params.endYear+'/' +params.lineDepartmentId+'/'+params.accountHeadId+'/'+params.workTypeId+'/'+params.workSubTypeId+'/'+params.name; }, 
			controller : 'CommonController'
		})
		
		.when('/manageDataFinalBillPendingReport/:id/:agencyTypeId/:fromYear/:endYear/:lineDepartmentId/:accountHeadId/:workTypeId/:workSubTypeId/:name', {
			templateUrl: function(params){  return 'manageDataFinalBillPendingReport/' + params.id+'/'+params.agencyTypeId+'/' +params.fromYear+'/' +params.endYear+'/' +params.lineDepartmentId+'/'+params.accountHeadId+'/'+params.workTypeId+'/'+params.workSubTypeId+'/'+params.name; }, 
			controller : 'CommonController'
		})
		.when('/manageDataPhysicalCCDispatcheReport/:id/:agencyTypeId/:lineDepartmentId/:accountHeadId/:workTypeId/:workSubTypeId/:name', {
			templateUrl: function(params){  return 'manageDataPhysicalCCDispatcheReport/' + params.id+'/'+params.agencyTypeId+'/'+params.lineDepartmentId+'/'+params.accountHeadId+'/'+params.workTypeId+'/'+params.workSubTypeId+'/'+params.name; }, 
			controller : 'CommonController'
		})
		
		.when('/viewExeAgWiseExpenditureReportDetails/:parameter1/:eeOfficeId/:name/:lineDepartmentId/:accountHeadId/:workStatusId/:executionAgencyId/:workTypeId/:workSubTypeId', {
			templateUrl: function(params){  return 'viewExeAgWiseExpenditureReportDetails/' + params.parameter1+'/' +params.eeOfficeId+'/' +params.name+'/' +params.lineDepartmentId+'/' +params.accountHeadId+'/' +params.workStatusId+'/' +params.executionAgencyId+'/' +params.workTypeId+'/' +params.workSubTypeId; }, 
			controller : 'CommonController'
		})
		
		.when('/generateTSRoute/:estimationId', {
			templateUrl: function(params){ return 'generateTs/' + params.estimationId; },
			controller : 'CommonController'
		})
		.when('/dispatchTsRoute/:workId/:estimationId', {
			templateUrl: function(params){ return 'dispatchTs/' + params.workId + '/' + params.estimationId ; },
			controller : 'CommonController'
		})
		
		//Rakesh 
		.when('/dispatchTsByParentRoute/:workId/:estimationId/:parentId', {
			templateUrl: function(params){ return 'dispatchTsByParent/' + params.workId + '/' + params.estimationId+ '/' + params.parentId ; },
			controller : 'CommonController'
		})
		
		.when('/editTechnicalSanctionRoute/:workId/:estimationId', {
			templateUrl: function(params){ return 'editTechnicalSanction/' + params.workId + '/' + params.estimationId ; }, 
			controller : 'CommonController'
		})
		//Rakesh working
		.when('/viewTechnicalSanctionRoute/:workId/:estimationId', {
			templateUrl: function(params){ return 'viewTechnicalSanction/' + params.workId + '/' + params.estimationId ; }, 
			controller : 'CommonController'
		})
		//
		.when('/viewTsRoute/:id', {
			templateUrl: function(params){ return 'viewTs/' + params.id; },
			controller : 'EEController'
		}) 
		.when('/inspectionList',{
			templateUrl: 'inspectionList',
			controller : 'CommonController'
		})
		
		.when('/generalInspectionList',{
			templateUrl: 'generalInspectionList',
			controller : 'CommonController'
		})
		.when('/randomInspectionList',{
			templateUrl: 'randomInspectionList',
			controller : 'CommonController'
		})
		.when('/cc/ccInspectionList',{
			templateUrl: 'cc/ccInspectionList',
			controller : 'CommonController'
		})
		.when('/viewInspection/:id',{
			templateUrl:   function(params){ return 'viewInspection/' + params.id; },
			controller : 'CommonController'
			})
			
			.when('/viewGeneralInspection/:id',{
			templateUrl:   function(params){ return 'viewGeneralInspection/' + params.id; },
			controller : 'CommonController'
			})
			
			
			
			.when('/viewInspectionForSqm/:id/:inspectionId',{
				templateUrl:   function(params){ return 'viewInspectionForSqm/' + params.id + '/' + params.inspectionId ; },
				controller : 'CommonController'
				})
				
				.when('/viewInspectionForOfficer/:id/:inspectionId',{
				templateUrl:   function(params){ return 'viewInspectionForOfficer/' + params.id + '/' + params.inspectionId },
				controller : 'CommonController'
				})
				
				.when('/viewInspectionForSqmByWorkId/:workId',{
				templateUrl:   function(params){ return 'viewInspectionForSqmByWorkId/' + params.workId; },
				controller : 'CommonController'
				})
				
				.when('/viewInspectionForOfficerByWorkId/:workId',{
				templateUrl:   function(params){ return 'viewInspectionForOfficerByWorkId/' + params.workId; },
				controller : 'CommonController'
				})
				
				.when('/viewGeneralInspectionByWorkId/:workId',{
				templateUrl:   function(params){ return 'viewGeneralInspectionByWorkId/' + params.workId; },
				controller : 'CommonController'
				})
				
				
			.when('/viewInspectionEE/:id',{
			templateUrl:   function(params){ return 'viewInspectionEE/' + params.id; },
			controller : 'CommonController'
			})
		.when('/viewInspectionCC/:id',{
			templateUrl:   function(params){ return 'viewInspectionCC/' + params.id; },
			controller : 'CommonController'
			})
		.when('/printTsRoute/:workId/:workEstimationId', {
				templateUrl: function(params){ return 'printTs/' + params.workId + '/' + params.workEstimationId ; },
				controller : 'CommonController'
			})
		.when('/viewPanchayatiRajReportsRoute',{
			templateUrl: 'viewPanchayatiRajReportsMapping',
			controller : 'CommonController'
			})
		.when('/viewStatusWiseWorkReport',{
			templateUrl: 'viewStatusWiseWorkReport',
			controller : 'CommonController'
		})
		.when('/viewStatusWiseWorkReportWithSelection',{
			templateUrl: 'viewStatusWiseWorkReportWithSelection',
			controller : 'CommonController'
		})
		.when('/viewPendingForInspectionReport',{
			templateUrl: 'viewPendingForInspectionReport',
			controller : 'CommonController'
		})
		
		.when('/viewFinalBillPendingReport',{
			templateUrl: 'viewFinalBillPendingReport',
			controller : 'CommonController'
		})
		
		.when('/viewPhysicalCCDispatchReport',{
			templateUrl: 'viewPhysicalCCDispatchReport',
			controller : 'CommonController'
		})

		/*.when('/viewEstimationStatusWiseWorkReport',{
			templateUrl: 'viewEstimationStatusWiseWorkReport',
			controller : 'CommonController'
		})
		.when('/viewFYWiseExpenditureReport',{
			templateUrl: 'viewFYWiseExpenditureReport',
			controller : 'CommonController'
		})

		.when('/viewFYWiseExpenditureReport',{
			templateUrl: 'viewFYWiseExpenditureReport',
			controller : 'CommonController'
		})*/

		

		.when('/workAgreementList',{
			templateUrl: 'workAgreementList',
			controller : 'CommonController'
			})
			//Rakesh
			.when('/workAgreementHistoryList/:workId/:tenderId',{
				templateUrl: function(params){ return 'workAgreementHistoryList/' + params.workId + '/' + params.tenderId ; },
			controller : 'CommonController'
			})
		.when('/printAsRoute/:estimationId', {
				templateUrl: function(params){ return 'printAs/' + params.estimationId; },
				controller : 'CommonController'
			})
		.when('/printTsTwoRoute/:workId/:workEstimationId', {
				templateUrl: function(params){ return 'printTsTwo/' + params.workId + '/' + params.workEstimationId ; },
				controller : 'CommonController'
			})
		.when('/addWorkAgreement/:id', {
			templateUrl: function(params){ return 'addWorkAgreement/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/editWorkAgreement/:id', {
			templateUrl: function(params){ return 'editWorkAgreement/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/viewWorkAgreement/:id', {
			templateUrl: function(params){ return 'viewWorkAgreement/'+ params.id; }, 
			controller : 'CommonController'
		})
		//Rakesh
		.when('/viewWorkAgreementByTenderId/:workId/:tenderId', {
			templateUrl: function(params){ return 'viewWorkAgreementByTenderId/'+ params.workId+ '/' + params.tenderId; }, 
			controller : 'CommonController'
		})
		.when('/manageTenderDataRoute', {
			templateUrl: 'manageTenderDataRoute',
			controller : 'CommonController'
		})
		.when('/manageTenderDataRouteForEnc', {
			templateUrl: 'manageTenderDataRouteForEnc',
			controller : 'CommonController'
		})
		//Rakesh
		.when('/manageHistoryTenderDataRoute/:id/:administrationSanctionId', {
			//templateUrl: 'manageHistoryTenderDataRoute',
			templateUrl: function(params){ return 'manageHistoryTenderDataRoute/' + params.id+'/'+params.administrationSanctionId; },
			controller : 'CommonController'
		})
		.when('/editTenderDataRoute', {
			templateUrl: 'editTenderDataRoute',
			controller : 'CommonController'
		})
		.when('/addTender/:id/:administrationSanctionId', {
			templateUrl: function(params){ return 'addTender/' + params.id+'/'+params.administrationSanctionId; },
			controller : 'CommonController'
		})
		.when('/editTender/:id/:administrationSanctionId',  {
			templateUrl: function(params){ return 'editTender/' + params.id+'/'+params.administrationSanctionId; },
			controller : 'CommonController'
		})
		.when('/viewTender/:id/:administrationSanctionId', {
			templateUrl: function(params){ return 'viewTender/' + params.id+'/'+params.administrationSanctionId;; },
			controller : 'CommonController'
		})
		.when('/workFile', {
			templateUrl: 'workFile',
			controller : 'CommonController'
		})
		
		.when('/cc/workList', {
			templateUrl: 'cc/workList',
			controller : 'CommonController'
		})
		.when('/cc/workDetails/:id', {
			templateUrl: function(params){ return 'cc/workDetails/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/cc/initiateCC/:id', {
			templateUrl: function(params){ return 'cc/initiateCC/'+ params.id; }, 
			controller : 'CommonController'
		})
		.when('/cc/physicalCCList', {
			templateUrl: 'cc/physicalCCList',
			controller : 'CommonController'
		})
		.when('/cc/financialCCList', {
			templateUrl: 'cc/financialCCList',
			controller : 'CommonController'
		})
		.when('/cc/printPhysicalCC/:workId', {
			templateUrl: function(params){ return 'cc/printPhysicalCC/'+ params.workId; }, 
			controller : 'CommonController'
		})
		.when('/cc/printFinancialCC/:workId', {
			templateUrl: function(params){ return 'cc/printFinancialCC/'+ params.workId; }, 
			controller : 'CommonController'
		})
		.when('/viewFYWiseExpenditureReport',{
			templateUrl: 'viewFYWiseExpenditureReport',
			controller : 'CommonController'
		})
		
		.when('/viewExAgWiseExpenditureReport',{
			templateUrl: 'viewExAgWiseExpenditureReport',
			controller : 'CommonController'
		})
		.when('/viewPaymentWiseExpenditureReport',{
			templateUrl: 'viewPaymentWiseExpenditureReport',
			controller : 'CommonController'
		})
		.when('/viewExeAgWiseExpenditureReport',{
			templateUrl: 'viewExeAgWiseExpenditureReport',
			controller : 'CommonController'
		})
		.when('/viewExeAgWiseExpenditureReportPayment',{
			templateUrl: 'viewExeAgWiseExpenditureReportPayment',
			controller : 'CommonController'
		})
		.when('/viewExeAgWiseExpenditureReportContg',{
			templateUrl: 'viewExeAgWiseExpenditureReportContg',
			controller : 'CommonController'
		})
		.when('/viewEeWiseExpenditureReport/:monthYear',{
			templateUrl: function(params){ return 'viewEeWiseExpenditureReport/'+ params.monthYear; }, 
			controller : 'CommonController'
		})
		
		.when('/viewAccountHeadWiseExpenditureReport/:monthYear/:eeId',{
			templateUrl: function(params){ return 'viewAccountHeadWiseExpenditureReport/'+ params.monthYear + '/' + params.eeId ; }, 
			controller : 'CommonController'
		})
		.when('/viewLineDeptWiseExpenditureReport/:monthYear/:eeId',{
			templateUrl: function(params){ return 'viewLineDeptWiseExpenditureReport/'+ params.monthYear + '/' + params.eeId ; }, 
			controller : 'CommonController'
		})
		.when('/viewWorkTypeWiseExpenditureReport/:monthYear/:eeId',{
			templateUrl: function(params){ return 'viewWorkTypeWiseExpenditureReport/'+ params.monthYear + '/' + params.eeId ; }, 
			controller : 'CommonController'
		})
		.when('/viewWorkWiseExpenditureReportByWorkType/:monthYear/:eeId/:workType',{
			templateUrl: function(params){ return 'viewWorkWiseExpenditureReportByWorkType/'+ params.monthYear + '/' + params.eeId  + '/' + params.workType ; }, 
			controller : 'CommonController'
		})
		
		.when('/viewWorkWiseExpenditureReportByAccHead/:monthYear/:eeId/:accHead',{
			templateUrl: function(params){ return 'viewWorkWiseExpenditureReportByAccHead/'+ params.monthYear + '/' + params.eeId   + '/' + params.accHead ; }, 
			controller : 'CommonController'
		})
		
		.when('/viewWorkWiseExpenditureReportByLineDept/:monthYear/:eeId/:lineDept',{
			templateUrl: function(params){ return 'viewWorkWiseExpenditureReportByLineDept/'+ params.monthYear + '/' + params.eeId  + '/' + params.lineDept ; }, 
			controller : 'CommonController'
		})
		
		.when('/viewThreeTabExpenditureReport/:monthYear/:eeId',{
			templateUrl: function(params){ return 'viewThreeTabExpenditureReport/'+ params.monthYear + '/' + params.eeId ; }, 
			controller : 'CommonController'
		})
		
		.when('/viewCEWorksForLegacy',{
		templateUrl: 'viewCEWorksForLegacy',
			controller : 'CommonController'
	})
	
	.when('/viewCEWorksForNewData',{
		templateUrl: 'viewCEWorksForNewData',
			controller : 'CommonController'
	})
	.when('/viewCEWorksForAllData',{
		templateUrl: 'viewCEWorksForAllData',
			controller : 'CommonController'
	})
	
	.when('/manageLegacyDataRouteForAdmin',{
		templateUrl: 'manageLegacyDataMappingForAdmin',
		controller : 'CommonController'
	})

	.when('/manageNewDataRouteForAdmin',{
		templateUrl: 'manageNewDataMappingForAdmin',
		controller : 'CommonController'
	})
	
		.when('/viewAllWorksForAE', {
		templateUrl: 'viewAllWorksForSubAe',
		controller : 'CommonController'
	})
		.when('/editInspUserForm/:id', {
				templateUrl: function(params){ return 'editInspUserForm/' + params.id; }, 
				controller : 'CommonController'
			})
			
				.when('/finalExpAmountHistory',{
			templateUrl: 'manageExpAmountHistory',
			controller : 'CommonController'
			})
			
				.when('/changesHistory',{
			templateUrl: 'manageChangesHistory',
			controller : 'CommonController'
			})

		.when('/userchangepassword', {
				templateUrl: 'userchangepassword',
				controller: 'CommonController'
			})
		.otherwise({
			redirectTo: '/'
		});
	}]);