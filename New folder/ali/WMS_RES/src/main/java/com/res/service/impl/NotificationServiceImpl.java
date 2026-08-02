package com.res.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.res.bean.EmailBean;
import com.res.bean.SMSBean;
import com.res.constants.RESConstants;
import com.res.exception.RESBusinessException;
import com.res.service.NotificationService;
import com.res.util.RESUtil;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	public static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private static final String USER_SMS_REGISTRATION = "user.sms.registration";
	
	private static final String USER_INSPECTION_MESSAGE = "user.inspection.message";
	
	private static final String USER_FORWARD_TO_EE = "user.forward.to.ee";
	
	private static final String CONTRACTOR_AS_USER = "contractor.as.user";
	
	private static final String BECOME_A_NEW_USER = "become.a.new.user";
	
	private static final String USER_FORWARD_TO_SubE = "user.forward.to.sube";
	
	private static final String USER_FORWARD_TO_EE_SUBJECT = "user.forward.to.ee.subject";
	
	private static final String USER_INSPECTION_MESSAGE_SUBJECT = "user.inspection.messagesubject";

	private static final String USER_EMAILSUBJECT_REGISTRATION = "user.emailsubject.registration";

	private static final String USER_EMAILBODY_REGISTRATION = "user.emailbody.registration";
	
	
	private static final String USER_SMS_VERIFICATION = "user.sms.verification";
	
	private static final String USER_EMAILSUBJECT_VERIFICATION = "user.emailsubject.verification";

	private static final String USER_EMAILBODY_VERIFICATION = "user.emailbody.verification";
	
	
	private static final String USER_SMS_ACTIVATION = "user.sms.activation";
	
	private static final String USER_EMAILSUBJECT_ACTIVATION = "user.emailsubject.activation";

	private static final String USER_EMAILBODY_ACTIVATION = "user.emailbody.activation";
	
	private static final String USER_EMAILBODY_PWD_RESET = "user.emailbody.forgotpassword";
	
	private static final String USER_EMAILSUBJECT_PWD_RESET = "user.emailsubject.forgotpassword";
	
	private static final String USER_EMAILSUBJECT_SQM_ACTIVATION = "user.emailsubject.addSqm";

	private static final String USER_EMAILBODY_SQM_ACTIVATION = "user.emailbody.addSqm";
	
	private static final String USER_SQM_SMS_ACTIVATION = "user.sms.registration";
	
	private static final String USER_SMS_PWD_RESET = "user.sms.pwd.reset";

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private SMSServiceImplNew smsService;

	public EmailServiceImpl getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceImpl emailService) {
		this.emailService = emailService;
	}

	public SMSServiceImplNew getSmsService() {
		return smsService;
	}

	public void setSmsService(SMSServiceImplNew smsService) {
		this.smsService = smsService;
	}

	//sent after signup
	@Override
	public void sendRegistrationNotification(String emailId,
			String mobileNumber, String emailLink)
			throws RESBusinessException {
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	try {
			        		//String[] params = { userName, password };
			        		//SMSBean smsBean = new SMSBean();
			        		//smsBean.setMobileNumber(mobileNumber);
			        		//smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_SMS_REGISTRATION, null));
			        		//getSmsService().sendSingleUnicodeSMS(smsBean);
			        		String[] paramsEmail = { emailLink };
			        		EmailBean emailBean = new EmailBean();
			        		emailBean.setRecipients(emailId);
			        		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILBODY_REGISTRATION, paramsEmail));
			        		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILSUBJECT_REGISTRATION,
			        				null));
			        		emailBean.setHTML(true);
			        		getEmailService().sendEmailmessage(emailBean);
			        		}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();

	}
	
	//sendInspectionNotificationToAEEE
	@Override
	public void sendNotificationInspectionFwd(String subEmail, String subMob,String billNo,String billType,String workRequisitionNo)
			throws RESBusinessException {
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	String[] params = { billNo, billType,workRequisitionNo };
			        	try {
			        		SMSBean smsBean = new SMSBean();
			        		smsBean.setTemplateId("1007105337689223374");
			        		smsBean.setMobileNumber(subMob);
			        		/*smsBean.setSmsText(RESUtil.getMessage(USER_INSPECTION_MESSAGE, params) );*/
			        		smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_INSPECTION_MESSAGE, params));
			        		getSmsService().sendSingleUnicodeSMS(smsBean);
			    		}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        	finally {
			        		try {
				        		EmailBean emailBean = new EmailBean();
				        		emailBean.setRecipients(subEmail);
				        		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_INSPECTION_MESSAGE, params));
				        		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_INSPECTION_MESSAGE_SUBJECT,
				        				null));
				        		emailBean.setHTML(true);
				        		getEmailService().sendEmailmessage(emailBean);	
				    		}
				        	catch(Exception e) {
				        		e.printStackTrace();
				        	}
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();

	}
	
	//sendNotificationForwardToEE
	@Override
	public void sendNotificationBillForwardToEE(String subEmail, String subMob,String billNo,String billType,String workRequisitionNo)
			throws RESBusinessException {
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	String[] params = { billNo, billType,workRequisitionNo };
			        	try {
			    		SMSBean smsBean = new SMSBean();
			    		smsBean.setTemplateId("1007763138914576653");
			    		smsBean.setMobileNumber(subMob);
			    		/*smsBean.setSmsText(RESUtil.getMessage(USER_INSPECTION_MESSAGE, params) );*/
			    		smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_FORWARD_TO_EE, params));
			    		getSmsService().sendSingleUnicodeSMS(smsBean);
			    		}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        	finally {
			        		try {
					    		EmailBean emailBean = new EmailBean();
					    		emailBean.setRecipients(subEmail);
					    		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_FORWARD_TO_EE, params));
					    		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_FORWARD_TO_EE_SUBJECT,
					    				null));
					    		emailBean.setHTML(true);
					    		getEmailService().sendEmailmessage(emailBean);	
					    		}
					        	catch(Exception e) {
					        		e.printStackTrace();
					        	}
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();

	}
	
	//sendNotificationToContractor
	
	@Override
	public void sendNotificationToContractor(String email, String mobile,String name)
			throws RESBusinessException {
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	String[] params = { name };
			        	try {
			        		SMSBean smsBean = new SMSBean();
			        		smsBean.setTemplateId("1007822975749004378");
			        		smsBean.setMobileNumber(mobile);
			        		/*smsBean.setSmsText(RESUtil.getMessage(USER_INSPECTION_MESSAGE, params) );*/
			        		smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, CONTRACTOR_AS_USER, params));
			        		getSmsService().sendSingleUnicodeSMS(smsBean);
			    		}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        	finally {
			        		try {
				        		EmailBean emailBean = new EmailBean();
				        		emailBean.setRecipients(email);
				        		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, CONTRACTOR_AS_USER, params));
				        		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, BECOME_A_NEW_USER,
				        				null));
				        		emailBean.setHTML(true);
				        		getEmailService().sendEmailmessage(emailBean);
				    		}
				        	catch(Exception e) {
				        		e.printStackTrace();
				        	}
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();

	}
	
	@Override
	public void sendNotificationBillForwardToSubE(String subEmail, String subMob,String billNo,String billType,String workRequisitionNo)
			throws RESBusinessException {
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	String[] params = { billNo, billType,workRequisitionNo };
			        	try {
			    		SMSBean smsBean = new SMSBean();
			    		smsBean.setTemplateId("1007763138914576653");
			    		smsBean.setMobileNumber(subMob);
			    		/*smsBean.setSmsText(RESUtil.getMessage(USER_INSPECTION_MESSAGE, params) );*/
			    		smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_FORWARD_TO_SubE, params));
			    		getSmsService().sendSingleUnicodeSMS(smsBean);
			    		}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        	finally {
			        		try {
					    		EmailBean emailBean = new EmailBean();
					    		emailBean.setRecipients(subEmail);
					    		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_FORWARD_TO_SubE, params));
					    		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_FORWARD_TO_EE_SUBJECT,
					    				null));
					    		emailBean.setHTML(true);
					    		getEmailService().sendEmailmessage(emailBean);	
					    		}
					        	catch(Exception e) {
					        		e.printStackTrace();
					        	}
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();

	}
	
	//sent after email verification
	@Override
	public void sendEmailVerificationNotification(String emailId,
			String mobileNumber)
			throws RESBusinessException {
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	try {
			        		//String[] params = { userName, password };
			        		//SMSBean smsBean = new SMSBean();
			        		//smsBean.setMobileNumber(mobileNumber);
			        		//smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_SMS_VERIFICATION, null));
			        		//getSmsService().sendSingleUnicodeSMS(smsBean);
			        		EmailBean emailBean = new EmailBean();
			        		emailBean.setRecipients(emailId);
			        		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILBODY_VERIFICATION, null));
			        		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILSUBJECT_VERIFICATION,
			        				null));
			        		emailBean.setHTML(true);
			        		getEmailService().sendEmailmessage(emailBean);
					}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();

	}
	
	//sent after account activation
	@Override
	public void sendAccountActivationNotification(String emailId,
			String mobileNumber, String websiteLink)
			throws RESBusinessException {
		
		
		Runnable runnable =
			    new Runnable(){
			        public void run(){
			        	try {
			        		String[] params = { emailId };
							SMSBean smsBean = new SMSBean();
							smsBean.setTemplateId("1007603415210675156");
							smsBean.setMobileNumber(mobileNumber);
							smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_SMS_ACTIVATION, params));
							getSmsService().sendSingleUnicodeSMS(smsBean);
			        	}
			        	catch(Exception e) {
			        		e.printStackTrace();
			        	}
			        	finally {
			        		try {
				        		String[] paramsEmail = { emailId, websiteLink };
				        		EmailBean emailBean = new EmailBean();
				        		emailBean.setRecipients(emailId);
				        		emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILBODY_ACTIVATION, paramsEmail));
				        		emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILSUBJECT_ACTIVATION,
				        				null));
				        		emailBean.setHTML(true);

				        		getEmailService().sendEmailmessage(emailBean);		
				        	}
				        	catch(Exception e) {
				        		e.printStackTrace();
				        	}
			        	}
			        }
			    };
	
       Thread thread = new Thread(runnable);
		thread.start();
		
	}
	
		@Override
		public void sendPasswordResetNotification(String emailId,
				String mobileNumber, String password)
				throws RESBusinessException {
			
			Runnable runnable =
				    new Runnable(){
				        public void run(){
				        	String[] params = { emailId, password };
				        	try {
				        		logger.info("Sending SMS for password Reset for user: "+emailId);
							SMSBean smsBean = new SMSBean();
							smsBean.setTemplateId("1007114845598665413");
							smsBean.setMobileNumber(mobileNumber);
							smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_SMS_PWD_RESET, params));
							getSmsService().sendSingleUnicodeSMS(smsBean);
							logger.info("Success-Sending SMS for password Reset for user: "+emailId);
						   }
				        	catch(Exception e) {
				        		e.printStackTrace();
				        		logger.info("Failed-Sending SMS for password Reset for user: "+emailId);
				        	}
				        	finally {
				        		try {
					        		logger.info("Sending Email for password Reset for user: "+emailId);
								EmailBean emailBean = new EmailBean();
								emailBean.setRecipients(emailId);
								emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILBODY_PWD_RESET, params));
								emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILSUBJECT_PWD_RESET,
										null));
								emailBean.setHTML(true);
								getEmailService().sendEmailmessage(emailBean);
								logger.info("Success-Sending Email for password Reset for user: "+emailId);
							}
					        	catch(Exception e) {
					        		logger.error("Failed-Sending Email for password Reset for user: "+emailId);
					        		logger.error("An exception occurred.", e);
					        		e.printStackTrace();
					        	}
				        	}
				        }
				    };
		
	       Thread thread = new Thread(runnable);
			thread.start();

		}
		
		//sent after account activation
		@Override
		public void sendSqmAccountActivationNotification(String emailId,
				String mobileNumber, String password)
				throws RESBusinessException {
			
			Runnable runnable =
				    new Runnable(){
				        public void run(){
				        	String[] params = { emailId, password };
				        	try {
							SMSBean smsBean = new SMSBean();
							smsBean.setTemplateId("1007611817793193345");
							smsBean.setMobileNumber(mobileNumber);
							smsBean.setSmsText(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_SQM_SMS_ACTIVATION, params));
							getSmsService().sendSingleUnicodeSMS(smsBean);
				        	}
				        	catch(Exception e) {
				        		e.printStackTrace();
				        	}
				        	finally {
				        		try {
									EmailBean emailBean = new EmailBean();
									emailBean.setRecipients(emailId);
									emailBean.setBody(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILBODY_SQM_ACTIVATION, params));
									emailBean.setSubject(RESUtil.getMessage(RESConstants.NOTIFICATION_FILE, USER_EMAILSUBJECT_SQM_ACTIVATION,
											null));
									emailBean.setHTML(true);
									getEmailService().sendEmailmessage(emailBean);			
						        	}
						        	catch(Exception e) {
						        		e.printStackTrace();
						        	}
				        	}
				        }
				    };
		
	       Thread thread = new Thread(runnable);
			thread.start();

		}
}
