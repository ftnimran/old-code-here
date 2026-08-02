package com.res.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.res.bean.EmailBean;
import com.res.constants.RESConstants;
import com.res.entity.Notification;
import com.res.exception.RESBusinessException;
import com.res.repository.NotificationRepository;

@Service
public class EmailServiceImpl {
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Value("${mail.smtp.host}")
	private String host;
	
	@Value("${mail.from}")	
	private String sender;
	
	@Value("${mail.smtp.port}")
	private String port;

	@Value("${mail.username}")
	private String userName;

	@Value("${mail.password}")
	private String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmailServiceImpl() {
		
	}

	/**
	 * This method sends email message to recipients and CC address specified in
	 * dto and also it sets message body, subject to the value specified in dto
	 *
	 * @param email
	 * @param subject
	 * @throws EmailException
	 */
	public void sendEmailmessage(final EmailBean email) throws RESBusinessException {
		Notification notification=new Notification();

		try {
			// Get the session object
			Properties props = System.getProperties();
			props.put(RESConstants.MAIL_SMTP_HOST, host);
			props.put(RESConstants.MAIL_SMTP_PORT, port);
			props.put("mail.transport.protocol", "smtp");

			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.checkserveridentity", true);
			

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(userName,
									password);
						}
					});
			final Message msg = new MimeMessage(session);
			InternetAddress[] toAddrs = null, ccAddrs = null;

			// if priority is passed then set it to message header
			if (email.getPriority() != null) {
				msg.addHeader("X-Priority", email.getPriority());
			}
			email.setSender(sender);
			toAddrs = InternetAddress.parse(email.getRecipients(), false);
			msg.setRecipients(Message.RecipientType.TO, toAddrs);
			msg.setFrom(new InternetAddress(userName, email.getSender()));
			msg.setSubject(email.getSubject());

			// setting CC if one set on dto
			if (email.getCC() != null) {
				ccAddrs = InternetAddress.parse(email.getCC(), false);
				msg.setRecipients(Message.RecipientType.CC, ccAddrs);
			}

			String mimeType = null;
			if (email.isHTML()) {
				mimeType = "text/html; charset=UTF-8";
			} else {
				mimeType = "text/plain; charset=UTF-8";
			}
			msg.setDataHandler(new DataHandler(email.getBody(), mimeType));
			notification =prepareNotificationObjForSendingEmail(email);
			sendEmail(msg);
			notification.setSendDate(new Date());
			notification.setStatus("Success");
		} catch (AddressException ex) {
			notification.setStatus("Fail");
			notification.setFailReason(ex.getMessage());
			throw new RESBusinessException("AddressException Occured while parsing email ids.", ex);
		} catch (MessagingException ex) {
			notification.setStatus("Fail");
			notification.setFailReason(ex.getMessage());
			throw new RESBusinessException("MessagingException Occured while preparing email message object.", ex);
		} catch (UnsupportedEncodingException ex) {
			notification.setStatus("Fail");
			notification.setFailReason(ex.getMessage());
			throw new RESBusinessException("UnsupportedEncodingException Occured while preparing email message object.", ex);
		}catch (Exception ex) {
			ex.printStackTrace();
			notification.setStatus("Fail");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
			notification.setFailReason(exceptionAsString);
			throw new RESBusinessException("Some Error Occured while sending Email.", ex);
		}
		finally {
			notificationRepository.save(notification);
		}
	}

	private void sendEmail(Message msg) throws RESBusinessException {
		try {
			Transport.send(msg);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			throw new RESBusinessException("MessagingException Occured while sending email without retry.", ex);
		}
	}
	
	private Notification prepareNotificationObjForSendingEmail(EmailBean email) {
		Notification notification = new Notification();
		notification.setEmail(email.getRecipients());
		notification.setScheduleDate(new Date());
		notification.setMessage(email.getBody());
		notification.setEmailSubject(email.getSubject());
		notification.setType("Email");
		notification.setStatus("Pending");
        return notification;
	}

}
