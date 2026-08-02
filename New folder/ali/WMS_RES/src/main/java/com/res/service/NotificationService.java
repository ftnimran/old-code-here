package com.res.service;

import com.res.exception.RESBusinessException;

public interface NotificationService {

	void sendRegistrationNotification(String emailId, String mobileNumber, String verificationRandomString)
			throws RESBusinessException;

	void sendEmailVerificationNotification(String emailId, String mobileNumber) throws RESBusinessException;

	void sendAccountActivationNotification(String emailId, String mobileNumber, String websiteLink)
			throws RESBusinessException;
	
	void sendPasswordResetNotification(String emailId, String mobileNumber, String password) throws RESBusinessException;
	
	public void sendSqmAccountActivationNotification(String emailId, String mobileNumber, String websiteLink) throws RESBusinessException;

	void sendNotificationInspectionFwd(String eEmail, String eEmob, String billNo, String billType, String workRequisitionNo)
			throws RESBusinessException;

	void sendNotificationBillForwardToEE(String email, String mobNo, String billNo, String billType,
			String workRequisitionNo)
					throws RESBusinessException;
	
	//sendNotificationToContractor
	
	void sendNotificationToContractor(String email, String mobNo, String name)
					throws RESBusinessException;

	void sendNotificationBillForwardToSubE(String email, String mobNo, String billNo, String billType,
			String workRequisitionNo)
					throws RESBusinessException;
}
