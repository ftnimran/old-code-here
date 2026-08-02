package com.res.constants;

public interface RESConstants {
	
	public String LOCALE_EN = "en";

	public String LOCALE_HI = "hi";
	
	public static final String MAIL_SMTP_HOST = "mail.smtp.host";
	
	public static final String ROLE_ADMIN_VIEW = "ROLE_ADMIN_VIEW";

	public static final String MAIL_SMTP_PORT = "mail.smtp.port";
	
	public static final String NOTIFICATION_FILE = "notification";	

	public static final String CAPTCHA_LOGIN = "CAPTCHA_LOGIN";
	
	public static final String CAPTCHA_RESET = "CAPTCHA_RESET";
	
	public static final String CAPTCHA_SIGNUP = "CAPTCHA_SIGNUP";
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ROLE_EnC = "ROLE_EnC";
	
	public static final String ROLE_CE = "ROLE_CE";
	
	public static final String ROLE_SUPDT_ENGG = "ROLE_SUPDT_ENGG";
	
	public static final String ROLE_EE = "ROLE_EE";
	
	public static final String ROLE_AE = "ROLE_AE";
	
	public static final String ROLE_SDO = "ROLE_SDO";
	
	public static final String ROLE_SQM = "ROLE_SQM";
	
	public static final String ROLE_SUB_ENGG = "ROLE_SUB_ENGG";
	

	
	public static final String ROLE_DIR_GP = "ROLE_DIR_GP";
	
	public static final String ROLE_ACC_OFFICER = "ROLE_ACC_OFFICER";
	
	public static final String ROLE_CONTRACTOR = "ROLE_CONTRACTOR";
	
	public static final String ERROR_FETCHING_DATA = "Some error occured while fetching the data";
	
	public static final String ERROR_SAVING_DATA = "Some error occured while saving the data";
	
	public static final String BILL_SUCCESS_ERROR_MAIL_SEND = "Bill added successfully but Error in sending Email.";
	
	public static final String ERROR_DELETING_DATA = "Some error occured while deleting the data";
	
	public static final String ERROR_UNLOCKING_DATA = "Some error occured while unlocking the data";
	
	public static final String DUPLICATE_ENTRY = "Duplicate entry - ";
	
	public static final String STATUS_ACTIVE = "Active";
	
	public static final String STATUS_INACTIVE = "InActive";
	
	public static final String STATUS_PENDING_VERIFICATION = "Pending Verification";
	
	public static final String STATUS_PENDING_ACTIVATION = "Pending Activation";
	
	public static final String STATUS_RECONCILED = "Reconciled";
	
	public static final String STATUS_REJECTED = "Rejected";
	
	public static final Long STATUS_SAVE_AS_DRAFT_ID  = 1L;
	public static final String STATUS_SAVE_AS_DRAFT = "SaveAsDraft";
	
	public static final Long STATUS_SUBMITTED_FWD_TO_EE_ID  = 2L;
	
	public static final Long STATUS_SUBMITTED_FWD_TO_SubE_ID  = 11L;
	public static final String STATUS_SUBMITTED_FWD_TO_EE = "Fowarded For Inspection";
	
	public static final Long STATUS_FWD_FOR_INSPECTION_ID  = 3L;
	public static final String STATUS_FWD_FOR_INSPECTION = "Fowarded For Inspection";
	
	public static final Long STATUS_FWD_FOR_PAYMENT_ID  = 4L;
	public static final String STATUS_FWD_FOR_PAYMENT = "Fowarded For Payment";
	
	public static final Long STATUS_PHYSICAL_INSPECTION_COMPLETED_ID  = 5L;
	public static final String STATUS_PHYSICAL_INSPECTION_COMPLETED = "Physical Inspection Completed";
	

	public static final Long  STATUS_CONTENGENCY_COMPLETED_ID  = 6L;
	public static final String STATUS_CONTENGENCY_COMPLETED  = "Contengency Completed";

	public static final Long STATUS_PAYMENT_COMPLETED_ID  = 7L;
	public static final String STATUS_PAYMENT_COMPLETED  = "Payment Completed";
	
	public static final String STATUS_DELETED = "Deleted";
	public static final Long STATUS_DELETED_ID = 8L ;
	
	
	public static final Long STATUS_FINAL_BILL_REJECTED_ID  = 9L;
	
	public static final Long STATUS_REJECTED_BY_SUBE_AND_FORWARDED_BACK_TO_CONTRACTOR= 12L;
	
	public static final Long STATUS_FWD_FOR_TS_ID  = 3L;
	public static final String STATUS_FWD_FOR_TS = "Estimation Approved & Fwd for TS";
	
	public static final Long ESTIMATION_STATUS_APPROVED = 5L;
	
	public static final Long TS_STATUS_DISPATCHED = 3L;	
	
	public static final Long STATUS_FWD_FOR_ESTIMATION_ID  = 2L;
	public static final String STATUS_FWD_FOR_ESTIMATION = "Submitted & Fwd for Estimation";

	
	
	public static final String PRESENT = "Present";
	
	String DATE_FORMAT = "dd/MM/yyyy";
	String DATE_FORMAT_yyyy_mm_dd = "yyyy-MM-dd";
	
	String DATE_FORMAT_HH_MM = "dd/MM/yyyy hh:mm:ss a";
	
	String DATE_FORMAT_HH_MM1 = "yyyy-MM-dd HH:mm:ss";
	
	String DATE_FORMAT_dd_MMM_yyyy = "dd-MMM-yyyy";
	String DATE_FORMAT__T_dd_MMM_yyyy = "yyyy-MM-dd'T'HH:mm:ss";
	
	String UNIT_NOS = "Nos";
	
	String PENDING = "Pending";
	
	String COMPLETED = "Completed";
	
	public static final Long STATUS_NOT_STARTED_ID = 1L;
	
	public static final Long STATUS_IN_PROGRESS_ID = 2L;
	
	String STATE_MP = "Madhya Pradesh";
	
	String MONTH[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	String ADMIN_USERNAME = "admin";

	public static final String ADMINISTRATION_SANCTION_DOC = "ADMINISTRATION SANCTION DOC";
	
	public static final String ADMINISTRATION_SANCTION_FILE = "admin_sanction_";
	
	public static final String TECHNICAL_SANCTION_FILE = "technical_sanction_";
	
	public static final String DRAWING_COPY_FILE = "drawing_copy_";
	
	public static final String ESTIMATION_COPY_FILE = "estimation_copy_";
	
	public static final String AGREEMENT_COPY_FILE = "agreement_copy_";
	
	public static final String REQUISITION_COPY_FILE = "requisition_copy_";
	
	public static final String INSPECTION_FILE = "inspection_image_";
	
	public static final String SQM_INSPECTION_FILE = "sqm_inspection_file_";
	
	public static final String SQM_INSPECTION_IMAGE = "sqm_inspection_image_";
	
	public static final String OFFICER_INSPECTION_IMAGE = "officer_inspection_image_";
	
	public static final String OFFICER_INSPECTION_FILE = "officer_inspection_file_";

	public static final String PHYSICAL_CC_FILE = "physicalCC_file_";	

	public static final String FINANCIAL_CC_FILE = "financialCC_file_";

	public static final String INSPECTION_CC_FILE = "inspectionCC_image_";	

	public static final String FINAL_INSPECTION_FILE = "final_inspection_image_";
	
	public static final Long EE_OFFICE_TYPE_ID = 4L;
	
	public static final Long SUPDTE_OFFICE_TYPE_ID = 3L;
	
	public static final Long CE_OFFICE_TYPE_ID = 2L;
	
	public static final String LOGGED_IN_USER_ROLE = "loggedInUserRole";
	
	public static final String VERIFY_EMAIL_SERVICE_NAME = "verifyEmail";
	
	public static final Long AGENCY_TYPE_NIVIDA = 1L;
	public static final Long AGENCY_TYPE_GP = 2L;
	public static final Long AGENCY_TYPE_VIBHAGIYA = 3L;
	
	//Work Request Status 
	public static final Long REQUEST_STATUS_DRAFT = 1L;
	public static final Long REQUEST_STATUS_SUBMITTED = 2L;
	public static final Long REQUEST_ESTIMATION_APPROVED_FOR_TS = 3L;
	public static final Long REQUEST_TS_GENERATED_AND_FORWARD_FOR_AS = 4L;
	public static final Long REQUEST_STATUS_AS_RECEIVED_AND_FWD_FOR_WORK_ORDERTENDER_DETAILS = 5L;
	public static final Long REQUEST_STATUS_FWD_FOR_AGGREEMENT_ID  = 6L;
	public static final Long REQUEST_STATUS_WORK_AGREEMENT_DONE_FWD_FOR_BILLING_INSPECTION_ID  = 7L;
	public static final Long REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID  = 8L;
	public static final Long REQUEST_STATUS_Final_Inspecion_Completed_ID  = 9L;
	public static final Long REQUEST_STATUS_CC_Rejected_ID  = 10L;
	public static final Long REQUEST_STATUS_Fwd_for_Physical_CC_ID  = 11L;
	public static final Long REQUEST_STATUS_Physical_CC_Issued_ID  = 12L;
	public static final Long REQUEST_STATUS_Physical_CC_Disptached_ID  = 13L;
	public static final Long REQUEST_STATUS_Final_CC_Issued_ID  = 14L;
	public static final Long REQUEST_STATUS_Final_CC_Disptached_ID  = 15L;
	
	
	public static final Long TS_GENERATED = 11L;
	public static final Long AS_RECEIVED = 2L;
	
	public static final String WORK_TYPE = "workType";
	public static final String WORK_NATURE = "workNature";
	
	
	
	public static final String WORK_SUB_TYPE = "workSubType";
	
	public static final String LINE_DEPARTMENT = "lineDepartment";
	
	
	
	public static final String ACCOUNT_HEAD = "accountHead";
	
	public static final String EXECUTION_AGENCY = "executionAgency";
	
	public static final String WORK_STATUS = "workStatus";
	
	public static final String BLOCK = "block";
	
	public static final String GRAM_PANCHAYAT = "gramPanchayat";
	
	public static final String VILLAGE = "village";
	
	public static final String CONTRACTOR = "contractor";
	
	public static final String FINANCIAL_YEAR = "financialYear";
	
	public static final String F2010_2011 = "1/April/2010-31/March/2011";
	
	public static final String F2011_2012 = "1/April/2011-31/March/2012";
	
	public static final String F2012_2013 = "1/April/2012-31/March/2013";
	
	public static final String F2013_2014 = "1/April/2013-31/March/2014";
	
	public static final String F2014_2015 = "1/April/2014-31/March/2015";
	
	public static final String F2015_2016 = "1/April/2015-31/March/2016";
	
	public static final String F2016_2017 = "1/April/2016-31/March/2017";
	
	public static final String F2017_2018 = "1/April/2017-31/March/2018";
	
	public static final String F2018_2019 = "1/April/2018-31/March/2019";
	
	public static final String F2019_2020 = "1/April/2019-31/March/2020";
	public static final String F2020_2021 = "1/April/2020-31/March/2021";
	public static final String F2021_2022 = "1/April/2021-31/March/2022";
	public static final String F2022_2023 = "1/April/2022-31/March/2023";
	public static final String F2023_2024 = "1/April/2023-31/March/2024";
	
	String DATE_FORMAT_FOR_BETWEEN = "yyyy-MM-dd";

	public String KML_Work_location_COPY_FILE = "kml_work_location_file_";

	public static final Short ENABLED = (short) 1;

	public static final String overallObservationGrading = "overallObservationGrading";
	
	public static final String OFFICE = "office";
	
	public static final Long TS_TYPE_STATUS_REVISED  = 2L;
	public static final Long TS_TYPE_STATUS_ORIGINAL  = 1L;
	public static final Long AS_TYPE_STATUS_ORIGINAL  = 1L;
	public static final Long AS_TYPE_STATUS_REVISED  = 2L;
	
	public static final String BILL_TYPE_RUNNING  = "Running";
	public static final String BILL_TYPE_FINAL  = "Final";
	public static final String ERROR_SAVING_DATA_ALREADY_EXISTS = "User Already Exists!";
	public static final String BILL_EXTRA_PAYMENT_PERC = "10";
	
	public static final String ESTIMATION_REVISED  = "REVISED";
	public static final String ESTIMATION_ORIGINAL  = "ORIGINAL";
	
	
}