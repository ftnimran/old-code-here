package com.res.service.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.res.bean.SMSBean;
import com.res.exception.RESBusinessException;

@Service
public class SMSServiceImpl {

	@Value("${sms.userName}")
	private String userName;
	
	@Value("${sms.password}")
	private String password;
	
	@Value("${sms.senderId}")
	private String senderId;
	
	@Value("${sms.url}")
	private String url;

	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// Method for sending single SMS.
	public void sendSingleUnicodeSMS(SMSBean smsBean) throws RESBusinessException {
		try {
			String serviceType = "unicodemsg"; // For single message
			URL smscUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) smscUrl
					.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			HttpURLConnection.setFollowRedirects(true);

			String query = MessageFormat
					.format("username={0}&password={1}&smsservicetype={2}&content={3}&mobileno={4}&senderid={5}",
							URLEncoder.encode(userName,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(password,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(serviceType,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(smsBean.getSmsText(),
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(smsBean.getMobileNumber(),
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(senderId,
									StandardCharsets.UTF_8.toString()));
			int queryLength = query.length();

			connection.setRequestProperty("Content-length",
					String.valueOf(queryLength));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());

			// write out the data
			output.writeBytes(query);
			output.close();

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read()) {
				//System.out.print((char) c);
			}
			input.close();
			System.out.println("Resp Code:" + connection.getResponseCode());
			System.out.println("Resp Message:"
					+ connection.getResponseMessage());
		} catch (Exception e) {
			throw new RESBusinessException(e.getMessage(), e);
		}
	}

	// Method for sending bulk SMS.
	public void sendBulkSMS(SMSBean smsBean) {
		try {
			String serviceType = "bulkmsg"; // For bulk message
			URL smscUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) smscUrl.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			HttpURLConnection.setFollowRedirects(true);

			String query = MessageFormat
					.format("username={0}&password={1}&smsservicetype={2}&content={3}&bulkmobno={4}&senderid={5}",
							URLEncoder.encode(userName,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(password,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(serviceType,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(smsBean.getSmsText(),
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(smsBean.getMobileNumber(),
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(senderId,
									StandardCharsets.UTF_8.toString()));
			int queryLength = query.length();

			connection.setRequestProperty("Content-length",
					String.valueOf(queryLength));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());

			// write out the data
			output.writeBytes(query);
			output.close();

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read()) {
				System.out.print((char) c);
			}
			input.close();
			System.out.println("Resp Code:" + connection.getResponseCode());
			System.out.println("Resp Message:"
					+ connection.getResponseMessage());
		} catch (Exception e) {
			System.out.println("Something bad just happened.");
			System.out.println(e);
			e.printStackTrace();
		}
	}

	// Method for sending scheduled SMS.
	public void sendScheduledSMS(SMSBean smsBean,
			final String scheduledTime) {
		// StartTime Format: YYYYMMDD hh:mm:ss
		// String scheduledTime = "20110701 02:27:00";
		try {
			String serviceType = "schmsg"; // For scheduled message
			URL smscUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) smscUrl
					.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			HttpURLConnection.setFollowRedirects(true);

			String query = MessageFormat
					.format("username={0}&password={1}&smsservicetype={2}&content={3}&bulkmobno={4}&senderid={5}&time={6}",
							URLEncoder.encode(userName,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(password,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(serviceType,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(smsBean.getSmsText(),
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(smsBean.getMobileNumber(),
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(senderId,
									StandardCharsets.UTF_8.toString()),
							URLEncoder.encode(scheduledTime,
									StandardCharsets.UTF_8.toString()));
			int queryLength = query.length();

			connection.setRequestProperty("Content-length",
					String.valueOf(queryLength));
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(
					connection.getOutputStream());

			// write out the data
			output.writeBytes(query);
			output.close();

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(
					connection.getInputStream());

			// read in each character until end-of-stream is detected
			for (int c = input.read(); c != -1; c = input.read()) {
				System.out.print((char) c);
			}
			input.close();
			System.out.println("Resp Code:" + connection.getResponseCode());
			System.out.println("Resp Message:"
					+ connection.getResponseMessage());
		} catch (Exception e) {
			System.out.println("Something bad just happened.");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
