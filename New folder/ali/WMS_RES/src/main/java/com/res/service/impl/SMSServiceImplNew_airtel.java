package com.res.service.impl;


import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.res.bean.EmailBean;
import com.res.bean.SMSBean;
import com.res.entity.Notification;
import com.res.repository.NotificationRepository;


@Service
public class SMSServiceImplNew_airtel {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public static final Logger logger = LoggerFactory.getLogger(SMSServiceImplNew_airtel.class);

	@Value("${sms.userName}")
	private String userName;
	
	@Value("${sms.password}")
	private String password;
	
	@Value("${sms.senderId}")
	private String senderId;
	
	@Value("${sms.url}")
	private String url;
	
	@Value("${sms.secureKey}")
	private String secureKey;
	
	@Value("${sms.campName}")
	private String campName;
	
	@Value("${sms.dltTmId}")
	private String dltTmId;
	
	@Value("${sms.dltPeid}")
	private String dltPeid;

	// Method for sending single SMS.
	public String sendSingleUnicodeSMS(SMSBean smsBean) {
		Notification notification=new Notification();
		
		long starttime = System.currentTimeMillis();
		long endtime = 0;
		StringBuilder qString = new StringBuilder("");
		double timetaken =0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String responseString = "";
		String templateid=(smsBean.getTemplateId()!=null && !smsBean.getTemplateId().equals(""))?smsBean.getTemplateId():"1007184447196416666";
		try {
			
			notification =prepareNotificationObjForSendingEmail(smsBean);
			
			qString.append("loginID="+URLEncoder.encode(userName,"UTF-8")+"&"); 
			qString.append("password="+URLEncoder.encode(password,"UTF-8")+"&");
			qString.append("mobile="+URLEncoder.encode(smsBean.getMobileNumber(),"UTF-8")+"&");
			qString.append("text="+URLEncoder.encode(smsBean.getSmsText(),"UTF-8")+"&");
			qString.append("senderid="+URLEncoder.encode(senderId,"UTF-8")+"&");
			qString.append("dlt_tm_id="+URLEncoder.encode(dltTmId,"UTF-8")+"&");
			qString.append("dlt_ct_id="+URLEncoder.encode(templateid,"UTF-8")+"&");
			qString.append("dlt_pe_id="+URLEncoder.encode(dltPeid,"UTF-8")+"&");
			qString.append("route_id="+URLEncoder.encode("BULK_IMPLICT","UTF-8")+"&");
			qString.append("Unicode="+URLEncoder.encode("2","UTF-8")+"&");
			qString.append("camp_name="+URLEncoder.encode(campName,"UTF-8")+"");
            
			URL smscUrl = new URL(url+"?"+qString);
			
			HttpURLConnection connection = (HttpURLConnection) smscUrl.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			HttpURLConnection.setFollowRedirects(true);

		   //connection.setRequestProperty("Content-length",String.valueOf(queryLength));
		   connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		   connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection.getInputStream());

			// read in each character until end-of-stream is detected
			String responseTxt = "";
			for (int c = input.read(); c != -1; c = input.read()) {
				
				responseTxt +=(char) c;
			}
			input.close();
			System.out.println(smsBean.getSmsText());
//			System.out.println("Resp Code:" + connection.getResponseCode());
//			System.out.println("Resp Message:"+ connection.getResponseMessage());
//			System.out.println("Resp Text:"+ responseTxt);
			
			if(connection.getResponseCode() != 200) {
			    //resendSingleUnicodeSMS(smsBean);
			}
			responseString=connection.getResponseMessage();
			endtime = System.currentTimeMillis();
			timetaken = (double)(endtime - starttime)/1000;
		    String logText = "SMS Delivery:transaction logs||"+smsBean.getMobileNumber()+"||"+ sdf.format(date) + "||"+ timetaken + "||"+connection.getResponseCode()+"||"+url.toString()+"?"+qString+"||"+connection.getResponseMessage() +":"+responseTxt ;	
			logger.info(logText);
			notification.setSendDate(new Date());
			notification.setStatus("Success");
		} catch (Exception e) {
			notification.setStatus("Fail");
			notification.setFailReason(e.getMessage());
			e.printStackTrace();
			endtime = System.currentTimeMillis();
			timetaken = (double)(endtime - starttime)/1000;	
			logger.error("SMS Delivery:transaction logs||"+smsBean.getMobileNumber()+"||"+ sdf.format(date) + "||"+ timetaken + "||"+url.toString()+"?"+qString+"||"+e );
			//throw new SUPBusinessException(e.getMessage(), e);
		}finally {
			notificationRepository.save(notification);
		}
		
		return responseString;
	}


	// Method for sending single SMS.
	public String sendSingleSMS(SMSBean smsBean) {
		
		long starttime = System.currentTimeMillis();
		long endtime = 0;
		StringBuilder qString = new StringBuilder("");
		double timetaken =0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String responseString = "";
		String templateid=(smsBean.getTemplateId()!=null && !smsBean.getTemplateId().equals(""))?smsBean.getTemplateId():"1007184447196416666";
		try {
			qString.append("loginID="+URLEncoder.encode(userName,"UTF-8")+"&"); 
			qString.append("password="+URLEncoder.encode(password,"UTF-8")+"&");
			qString.append("mobile="+URLEncoder.encode(smsBean.getMobileNumber(),"UTF-8")+"&");
			qString.append("text="+URLEncoder.encode(smsBean.getSmsText(),"UTF-8")+"&");
			qString.append("senderid="+URLEncoder.encode(senderId,"UTF-8")+"&");
			qString.append("DLT_TM_ID="+URLEncoder.encode("1001096933494158","UTF-8")+"&");
			qString.append("route_id="+URLEncoder.encode("BULK_IMPLICT","UTF-8")+"&");
			qString.append("Unicode="+URLEncoder.encode("0","UTF-8")+"&");
			qString.append("camp_name="+URLEncoder.encode("mpsrlm_user","UTF-8")+"&");
			qString.append("DLT_CT_ID="+URLEncoder.encode(templateid,"UTF-8")+"&");
			qString.append("DLT_PE_ID="+URLEncoder.encode("1001722338211311505","UTF-8")+"");
            
			URL smscUrl = new URL(url+"?"+qString);
			
			HttpURLConnection connection = (HttpURLConnection) smscUrl.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			HttpURLConnection.setFollowRedirects(true);

		   //connection.setRequestProperty("Content-length",String.valueOf(queryLength));
		   connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		   connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

			// get ready to read the response from the cgi script
			DataInputStream input = new DataInputStream(connection.getInputStream());

			// read in each character until end-of-stream is detected
			String responseTxt = "";
			for (int c = input.read(); c != -1; c = input.read()) {
				
				responseTxt +=(char) c;
			}
			input.close();
			System.out.println(smsBean.getSmsText());
//			System.out.println("Resp Code:" + connection.getResponseCode());
//			System.out.println("Resp Message:"+ connection.getResponseMessage());
//			System.out.println("Resp Text:"+ responseTxt);
			
			if(connection.getResponseCode() != 200) {
			    //resendSingleUnicodeSMS(smsBean);
			}
			responseString=connection.getResponseMessage();
			endtime = System.currentTimeMillis();
			timetaken = (double)(endtime - starttime)/1000;
		    String logText = "SMS Delivery:transaction logs||"+smsBean.getMobileNumber()+"||"+ sdf.format(date) + "||"+ timetaken + "||"+connection.getResponseCode()+"||"+url.toString()+"?"+qString+"||"+connection.getResponseMessage() +":"+responseTxt ;	
			logger.info(logText);
		} catch (Exception e) {
			endtime = System.currentTimeMillis();
			timetaken = (double)(endtime - starttime)/1000;	
			logger.error("SMS Delivery:transaction logs||"+smsBean.getMobileNumber()+"||"+ sdf.format(date) + "||"+ timetaken + "||"+url.toString()+"?"+qString+"||"+e );
			//throw new SUPBusinessException(e.getMessage(), e);
		}
		
		return responseString;
	}

	
	/*public void resendSingleUnicodeSMS(SMSBean smsBean) throws SUPBusinessException {
        try {
            String serviceType = "unicodemsg"; // For single message
            URL smscUrl = new URL(resendUrl);
            HttpURLConnection connection = (HttpURLConnection) smscUrl
                    .openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            HttpURLConnection.setFollowRedirects(true);

            String query = MessageFormat
                    .format("username={0}&password={1}&smsservicetype={2}&content={3}&mobileno={4}&senderid={5}",
                            URLEncoder.encode(resendUserName,
                                    StandardCharsets.UTF_8.toString()),
                            URLEncoder.encode(resendPassword,
                                    StandardCharsets.UTF_8.toString()),
                            URLEncoder.encode(serviceType,
                                    StandardCharsets.UTF_8.toString()),
                            URLEncoder.encode(smsBean.getSmsText(),
                                    StandardCharsets.UTF_8.toString()),
                            URLEncoder.encode(smsBean.getMobileNumber(),
                                    StandardCharsets.UTF_8.toString()),
                            URLEncoder.encode(resendSenderId,
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
            throw new SUPBusinessException(e.getMessage(), e);
        }
    }*/
	
	
	
	
	private static String MD5(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  
	{ 
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] md5 = new byte[64];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5 = md.digest();
		return convertedToHex(md5);
	}
	
	private static String convertedToHex(byte[] data) 
	{ 
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < data.length; i++) 
		{ 
			int halfOfByte = (data[i] >>> 4) & 0x0F;
			int twoHalfBytes = 0;

			do 
			{ 
				if ((0 <= halfOfByte) && (halfOfByte <= 9)) 
				{
					buf.append( (char) ('0' + halfOfByte) );
				}

				else 
				{
					buf.append( (char) ('a' + (halfOfByte - 10)) );
				}

				halfOfByte = data[i] & 0x0F;

			} while(twoHalfBytes++ < 1);
		} 
		return buf.toString();
	}
	
	/**
	 * Send Single Unicode OTP text SMS
	 * @param username : Department Login User Name
	 * @param password : Department Login Password
	 * @param message  : Unicode Message e.g. 'à¤µà¤¿à¤•à¤¾à¤¸ à¤†à¤£à¤¿ à¤ªà¥à¤°à¤—à¤¤ à¤¸à¤‚à¤—à¤£à¤¨ à¤•à¥‡à¤‚à¤¦à¥à¤° à¤®à¤§à¥à¤¯à¥‡ à¤¸à¥à¤µà¤¾à¤—à¤¤ à¤†à¤¹à¥‡'
	 * @param senderId	: Department allocated SenderID
	 * @param mobileNumber : Bulk Mobile Number with comma separated e.g. '99XXXXXXX,99XXXXXXXX' 
	 * @param secureKey :  Department key generated by login to services portal
	 * @return {@link String} response from Mobile Seva Gateway e.g. '402,MsgID = 150620161466003974245msdgsms' 
	 * @see <a href="https://mgov.gov.in/msdp_sms_push.jsp">Return types code details</a>
	 * 
	 */
		
	protected String hashGenerator(String userName, String senderId, String content, String secureKey) {
		// TODO Auto-generated method stub
		StringBuffer finalString=new StringBuffer();
		finalString.append(userName.trim()).append(senderId.trim()).append(content.trim()).append(secureKey.trim());
		//		logger.info("Parameters for SHA-512 : "+finalString);
		String hashGen=finalString.toString();
		StringBuffer sb = new StringBuffer("");
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(hashGen.getBytes());
			byte byteData[] = md.digest();
			//convert the byte to hex format method 1
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	private Notification prepareNotificationObjForSendingEmail(SMSBean smsBean) {
		Notification notification = new Notification();
		notification.setMobileNo(smsBean.getMobileNumber());
		notification.setScheduleDate(new Date());
		notification.setMessage(smsBean.getSmsText());
		notification.setType("SMS");
		notification.setStatus("Pending");
        return notification;
	}
}
