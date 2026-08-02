package com.res.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import com.res.constants.RESConstants;
import com.res.entity.DocumentUpload;
import com.res.entity.FileDetails;
import com.res.entity.ImageDetails;
import com.res.exception.RESBusinessException;

//@Component
//@ConfigurationProperties
public class RESUtil {
	
	static SecureRandom rnd = new SecureRandom();
	
	public static String[] acceptedContentTypes = { "application/pdf",
			"image/jpg", "image/jpeg", "image/png", "image/gif" };

	public static String[] pdfOnly = { "application/pdf" };

	public static String getImageString(byte[] profileImage) {
		return "data:image/jpg;base64,"
				+ Base64.encodeBase64String(profileImage);
	}

	public static Map<Long, String> finanicalYearMap = new HashMap<Long, String>();
	static 
	{
		finanicalYearMap.put(1L, RESConstants.F2010_2011);
		finanicalYearMap.put(2L, RESConstants.F2011_2012);
		finanicalYearMap.put(3L, RESConstants.F2012_2013);
		finanicalYearMap.put(4L, RESConstants.F2013_2014);
		finanicalYearMap.put(5L, RESConstants.F2014_2015);
		finanicalYearMap.put(6L, RESConstants.F2015_2016);
		finanicalYearMap.put(7L, RESConstants.F2016_2017);
		finanicalYearMap.put(8L, RESConstants.F2017_2018);
		finanicalYearMap.put(9L, RESConstants.F2018_2019);
		finanicalYearMap.put(10L, RESConstants.F2019_2020);
		finanicalYearMap.put(11L, RESConstants.F2020_2021);
		finanicalYearMap.put(12L, RESConstants.F2021_2022);
	}
	
	
	public static String generateCaptchaText(int captchaLength) {

		//String saltChars = "ABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		String saltChars = "123456789";
		StringBuffer captchaStrBuffer = new StringBuffer();

		// build a random captchaLength chars salt
		while (captchaStrBuffer.length() < captchaLength) {
			int index = (int) (rnd.nextFloat() * saltChars.length());
			captchaStrBuffer.append(saltChars.substring(index, index + 1));
		}

		return captchaStrBuffer.toString();
		//return "1";
	}

	public static String getMessage(String propertyFile, String key,
			Object[] params) {

		Locale locale = LocaleContextHolder.getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle(propertyFile, locale);

		return MessageFormat.format(bundle.getString(key), params);
	}

	public static User getUserDetail() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		User user = null;
		if (null != securityContext) {
			Authentication authentication = securityContext.getAuthentication();
			if (null != authentication) {
				if (authentication.getPrincipal() instanceof String) {
					user = null;
				} else {
					user = (User) authentication.getPrincipal();
				}
			}
		}
		return user;
	}

	public static String generatePassword() {
		return generateSessionKey(8);
	}

	private static String generateSessionKey(int length) {
		String alphabet = new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"); // 9
		int n = alphabet.length(); // 10

		String result = new String();

		for (int i = 0; i < length; i++)
			// 12
			result = result + alphabet.charAt(rnd.nextInt(n)); // 13

		return result;
	}

	public static Date convertStringToDate(String inputDate) throws RESBusinessException{

		SimpleDateFormat dateFormat = new SimpleDateFormat(RESConstants.DATE_FORMAT);

		try {
			return dateFormat.parse(inputDate.trim());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
			
		}

	}
	public static Date convertStringToDate_yyyy_mm_dd(String inputDate) throws RESBusinessException{

		SimpleDateFormat dateFormat = new SimpleDateFormat(RESConstants.DATE_FORMAT_yyyy_mm_dd);

		try {
			return dateFormat.parse(inputDate.trim());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
			
		}

	}
	
	
	
	public static Date convertStringToDateFormat(String inputDate, String format) throws RESBusinessException{

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		try {
			return dateFormat.parse(inputDate.trim());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		}

	}
	
	public static Date convertStringToDateFormat_yyyy_MM_dd_T_HH_mm_ss(
	        String inputDate, String format) throws RESBusinessException {

	    if (inputDate == null || inputDate.trim().isEmpty()) {
	        return null;
	    }

	    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	    dateFormat.setLenient(false);

	    try {
	        return dateFormat.parse(inputDate.trim());
	    } catch (ParseException e) {
	        throw new RESBusinessException(
	                "Invalid date format. Expected: " + format + ", value: " + inputDate, e);
	    }
	}


	 

	public static String convertDateToString(Date inputDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				RESConstants.DATE_FORMAT);
		if(inputDate!=null)
			return dateFormat.format(inputDate);
		else
			return null;
	}
	
	public static String convertDateToStringWithFormat(Date inputDate, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				format);
		if(inputDate!=null)
			return dateFormat.format(inputDate);
		else
			return null;
	}

	/*public static boolean isThisDateWithinExistingRange(String dateToValidate,
			String dateFromat) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);

			// current date after 3 months
			Calendar currentDateAfter3Months = Calendar.getInstance();
			currentDateAfter3Months.add(Calendar.MONTH, 3);

			// current date before 3 months
			Calendar currentDateBefore3Months = Calendar.getInstance();
			currentDateBefore3Months.add(Calendar.MONTH, -3);

	 *//*************** verbose ***********************//*
			System.out.println("\n\ncurrentDate : "
					+ Calendar.getInstance().getTime());
			System.out.println("currentDateAfter3Months : "
					+ currentDateAfter3Months.getTime());
			System.out.println("currentDateBefore3Months : "
					+ currentDateBefore3Months.getTime());
			System.out.println("dateToValidate : " + dateToValidate);
	  *//************************************************//*

			if (date.before(currentDateAfter3Months.getTime())
					&& date.after(currentDateBefore3Months.getTime())) {

				//ok everything is fine, date in range
				return true;

			} else {

				return false;

			}

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

	}*/

	public static boolean isThisDateBeforeTheGivenDate(String dateToValidateStr, String givenDateStr){

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			// if not valid, it will throw ParseException
			Date dateToValidate = sdf.parse(dateToValidateStr);
			Date givenDate = sdf.parse(givenDateStr);

			if(dateToValidate.compareTo(givenDate) < 1){
				return true;
			}else
				return false;

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}
	}

	public static String minusOneMonth(String dateToMinus) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToMinus);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.add(Calendar.MONTH, -1);

			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);

			month++;// calender's month indexing starts from 0
			if(String.valueOf(month).length()<2){
				return "0"+month+"/"+year;
			}
			return month+"/"+year;
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getFinancialYear(){

		int year = Calendar.getInstance().get(Calendar.YEAR);

		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (month < 4) {
			return (year - 1)%100 + "-" + year%100;
		} else {
			return year%100 + "-" + (year + 1)%100;
		}
	}
	
	public static String getCurrentFinancialFullYear(){

		int year = Calendar.getInstance().get(Calendar.YEAR);

		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (month < 4) {
			return (year - 1) + "-" + year;
		} else {
			return year + "-" + (year + 1);
		}
	}
	
	public static String saveAgreementFile(String documentsPath, String requestId,
			MultipartFile mpresFile) throws RESBusinessException {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date); 
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = RESConstants.AGREEMENT_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			createdFileName = RESConstants.AGREEMENT_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	public static String saveCCDispatchFile(String documentsPath, String requestId,
			MultipartFile mpresFile, String fileType) throws RESBusinessException {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date); 
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = fileType
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			createdFileName = fileType
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	public static String saveAdminSanctionFile(String documentsPath, String requestId,
			MultipartFile mpresFile) throws RESBusinessException  {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date); 
		
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath + File.separator);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = RESConstants.ADMINISTRATION_SANCTION_FILE 
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			/*createdFileName = FilenameUtils.removeExtension(mpresFile
					.getOriginalFilename())
					+ RESConstants.ADMINISTRATION_SANCTION_FILE 
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];*/
			createdFileName = RESConstants.ADMINISTRATION_SANCTION_FILE 
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			throw new RESBusinessException(
					"System is unable to process.");
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	public static String saveTechnicalSanctionFile(String documentsPath, String requestId,
			MultipartFile mpresFile) throws RESBusinessException {
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date);  
		
		File serverFile = null;
		String createdFileName = null;

		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = RESConstants.TECHNICAL_SANCTION_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
					serverFile = new File(dir.getAbsolutePath() + File.separator
							+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			createdFileName = RESConstants.TECHNICAL_SANCTION_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	public static String saveDrawingCopyFile(String documentsPath, String requestId,
			MultipartFile mpresFile) throws RESBusinessException {
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date);  
		
		File serverFile = null;
		String createdFileName = null;

		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = RESConstants.DRAWING_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
					serverFile = new File(dir.getAbsolutePath() + File.separator
							+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			createdFileName = RESConstants.DRAWING_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	public static String saveEstimationCopyFile(String documentsPath, String requestId,
			MultipartFile mpresFile) throws RESBusinessException {
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date);  
		
		File serverFile = null;
		String createdFileName = null;

		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = RESConstants.ESTIMATION_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
					serverFile = new File(dir.getAbsolutePath() + File.separator
							+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			createdFileName = RESConstants.ESTIMATION_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	
	public static  DocumentUpload uploadAgreementDocument(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = saveAgreementFile(documentPath,
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
		return documentUpload;
	}
	
	
	public static  DocumentUpload uploadAsWorkDocument(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {
		String fileName = saveAdminSanctionFile(documentPath,
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	public static  DocumentUpload uploadTsWorkDocument(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = saveTechnicalSanctionFile(documentPath,
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	public static  DocumentUpload uploadDrawingDocument(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = saveDrawingCopyFile(documentPath,
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	public static  DocumentUpload uploadEstimationDocument(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = saveEstimationCopyFile(documentPath,
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	public static DocumentUpload uploadCCDocument(String documentPath, String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = saveCCDispatchFile(documentPath,
				String.valueOf(workid), document, documentType);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	
	public static  DocumentUpload uploadDocument(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws RESBusinessException {

		String fileName = saveRequisitionFile(documentPath,
				String.valueOf(workid), document);

		DocumentUpload documentUpload = new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
//		documentUpload.setDocumentType(new MasterLegalDocumentType(documentType));
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	
	public static String saveRequisitionFile(String documentsPath, String requestId,
			MultipartFile mpresFile) throws RESBusinessException {
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date); 
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			// Create the file on server
			/*createdFileName = mpresFile.getOriginalFilename();
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);*/
			createdFileName = RESConstants.REQUISITION_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			// Create the file on server
			/*serverFile = new File(dir.getAbsolutePath() + File.separator
					+ mpresFile.getOriginalFilename());

			createdFileName = mpresFile.getOriginalFilename();
			if (serverFile.exists()) {
				// Create the file on server
				createdFileName = FilenameUtils.removeExtension(mpresFile
						.getOriginalFilename())
						+ "_"
						+ System.currentTimeMillis()
						+ "."
						+ mpresFile.getOriginalFilename().split("\\.")[1];
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}*/
			createdFileName = RESConstants.REQUISITION_COPY_FILE
					+ strDate
					+ "."
					+ mpresFile.getOriginalFilename().split("\\.")[1];
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(mpresFile.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return createdFileName;
	}
	
	
	public static String savebase64EncodedImage(String base,  String documentsPath, String requestId, String encodedImg, int count) 
			throws RESBusinessException {
		
		byte[] decodedImg = Base64.decodeBase64(encodedImg.getBytes(StandardCharsets.UTF_8));
		
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
	    String strDate= formatter.format(date); 
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			 
			createdFileName = base + count + "_"
					+ strDate
					+ "."
					+ "jpg";
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			 
			createdFileName = base + count + "_"
					+ strDate
					+ "."
					+ "jpg";
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(decodedImg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return createdFileName;
	}
	
	
	public static String savebase64EncodedImageWithName(
	        String base,
	        String documentsPath,
	        String requestId,
	        ImageDetails encodedImg,
	        int count) throws RESBusinessException {

	    try {

	        String base64String = encodedImg.getImageBase64();

	        if (base64String == null || base64String.isEmpty()) {
	            throw new RESBusinessException("Image Base64 is empty", null);
	        }

	        // Remove prefix
	        if (base64String.contains(",")) {
	            base64String = base64String.split(",")[1];
	        }

	        // Clean spaces
	        base64String = base64String.replaceAll("\\s", "");

	        // Fix padding
	        int padding = base64String.length() % 4;
	        if (padding > 0) {
	            base64String += "====".substring(padding);
	        }

	    	byte[] decodedImg = Base64.decodeBase64(encodedImg.getImageBase64().getBytes(StandardCharsets.UTF_8));
			String imageName = encodedImg.getImageName();
	       // byte[] decodedImg = Base64.getDecoder().decode(base64String);

	        File dir = new File(documentsPath);
	        if (!dir.exists()) {
	            dir.mkdirs();
	        }

	        String strDate = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss")
	                .format(new Date());

	        String createdFileName = base + count + "_" + strDate + ".jpg";

	        File serverFile = new File(dir, createdFileName);

	        try (BufferedOutputStream stream =
	                     new BufferedOutputStream(new FileOutputStream(serverFile))) {

	            stream.write(decodedImg);
	        }

	        return createdFileName;

	    } catch (Exception e) {
	        throw new RESBusinessException("Failed to save image", e);
	    }
	}

	
	
	public static String savebase64EncodedImageWithNameWithFile(String base,  String documentsPath, String requestId, FileDetails encodedImg, int count) 
			throws RESBusinessException {
		
		byte[] decodedImg = Base64.decodeBase64(encodedImg.getFileBase64().getBytes(StandardCharsets.UTF_8));
		String imageName = encodedImg.getFileName();
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			 
			createdFileName = imageName
				
					;
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			 
			createdFileName = imageName
					
					;
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(decodedImg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return createdFileName;
	}
	
	public static String savebase64EncodedImageWithNameFile(String base,  String documentsPath, String requestId, FileDetails encodedImg, int count) 
			throws RESBusinessException {
		
		byte[] decodedImg = Base64.decodeBase64(encodedImg.getFileBase64().getBytes(StandardCharsets.UTF_8));
		String imageName = encodedImg.getFileName();
		
		File serverFile = null;
		String createdFileName = null;
		File dir = new File(documentsPath);
		if (!dir.exists()) {
			dir.mkdirs();
			 
			createdFileName = imageName
				
					;
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		} else {
			 
			createdFileName = imageName
					
					;
			serverFile = new File(dir.getAbsolutePath() + File.separator
					+ createdFileName);
		}

		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(decodedImg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RESBusinessException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (null != stream) {
					stream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return createdFileName;
	}
	
	public static Timestamp getTimestampFromDate(String inputDate)
			throws RESBusinessException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				RESConstants.DATE_FORMAT);

		try {
			if (inputDate != null) {
				Date date = dateFormat.parse(inputDate.trim());
				return new Timestamp(date.getTime());
			} else {
				return null;
			}
		} catch (ParseException e) {
			throw new RESBusinessException(
					"System is not able to process.");
		}

	}
	
	public static Date convertStringToDateForBetween(String inputDate) throws RESBusinessException{

		SimpleDateFormat dateFormat = new SimpleDateFormat(RESConstants.DATE_FORMAT_FOR_BETWEEN);

		try {
			return dateFormat.parse(inputDate.trim());
		} catch (ParseException e) {
			throw new RESBusinessException(
					"System is not able to process.");
		}

	}

	public static String getFinacialYearString(Date date) throws RESBusinessException {
		
		if(date == null) {
			return ("AS Pending");
		}
		
		if (date.after(RESUtil.getTimestampFromDate("01/04/2010"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2011"))) {
			 return RESConstants.F2010_2011;
			
		} else if (date.after(RESUtil.getTimestampFromDate("01/04/2011"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2012"))) {
			return (RESConstants.F2011_2012);
			
		} else if (date.after(RESUtil.getTimestampFromDate("01/04/2012"))
				&& date	.before(RESUtil.getTimestampFromDate("31/03/2013"))) {
			return (RESConstants.F2012_2013);
			
		} else if (date.after(RESUtil.getTimestampFromDate("01/04/2013"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2014"))) {
			return (RESConstants.F2013_2014);
			
		} else if (date.after(RESUtil.getTimestampFromDate("01/04/2014"))
				&& date	.before(RESUtil	.getTimestampFromDate("31/03/2015"))) {
			return (RESConstants.F2014_2015);
			
		} else if (date	.after(RESUtil.getTimestampFromDate("01/04/2015"))
				&& date	.before(RESUtil	.getTimestampFromDate("31/03/2016"))) {
			return (RESConstants.F2015_2016);
			
		} else if (date	.after(RESUtil.getTimestampFromDate("01/04/2016"))
				&& date	.before(RESUtil.getTimestampFromDate("31/03/2017"))) {
			return (RESConstants.F2016_2017);
			
		} else if (date.after(RESUtil.getTimestampFromDate("01/04/2017"))
				&& date	.before(RESUtil.getTimestampFromDate("31/03/2018"))) {
			return (RESConstants.F2017_2018);
			
		} else if (date	.after(RESUtil.getTimestampFromDate("01/04/2018"))
				&& date.before(RESUtil	.getTimestampFromDate("31/03/2019"))) {
			return (RESConstants.F2018_2019);
			
		} else if (date.after(RESUtil.getTimestampFromDate("01/04/2019"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2020"))) {
			return (RESConstants.F2019_2020);
			
		} 
		else if (date.after(RESUtil.getTimestampFromDate("01/04/2020"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2021"))) {
			return (RESConstants.F2020_2021);
			
		}
		else if (date.after(RESUtil.getTimestampFromDate("01/04/2021"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2022"))) {
			return (RESConstants.F2021_2022);
			
		}
		else if (date.after(RESUtil.getTimestampFromDate("01/04/2022"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2023"))) {
			return (RESConstants.F2022_2023);
			
		}else if (date.after(RESUtil.getTimestampFromDate("01/04/2023"))
				&& date.before(RESUtil.getTimestampFromDate("31/03/2024"))) {
			return (RESConstants.F2023_2024);
			
		}
		else {
			return ("AS Pending");
		}

	} 

	public static String getFinacialYearFromDate(Date date) throws RESBusinessException {

		String financialYear = "AS Pending";

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int CurrentYear = cal.get(Calendar.YEAR);
		int CurrentMonth = (cal.get(Calendar.MONTH) + 1);
		String financialYearFrom = "";
		String financiyalYearTo = "";
		if (CurrentMonth < 4) {
			financialYearFrom = "1/April/" + (CurrentYear - 1);
			financiyalYearTo = "31/March/" + (CurrentYear);
		} else {
			financialYearFrom = "1/April/" + (CurrentYear);
			financiyalYearTo = "31/March/" + (CurrentYear + 1);
		}
		if (!financialYearFrom.isEmpty() && !financiyalYearTo.isEmpty()) {
			financialYear = financialYearFrom + "-" + financiyalYearTo;
		}
		return financialYear;
	}


	public static  DocumentUpload uploadKMLDocumentForWorkLocation(String documentPath,String workid,
			MultipartFile document, String documentDesc, String documentType)
			throws Exception {
		DocumentUpload documentUpload =null;
		String fileName = saveKMLDocumentForWorkLocation(documentPath,
				String.valueOf(workid), document,documentType);
		
		if(fileName==null) {
			throw new  Exception("file name blank (file did not upload)") ;
		}

		documentUpload=new DocumentUpload();
		documentUpload.setDocumentName(fileName);
		documentUpload.setDocumentDesc(documentDesc);
		documentUpload.setDocumentType(documentType);
		documentUpload.setDocumentUploadPath(documentPath);
//		legalDocumentUpload.setCreatedOnAndCreatedBy();
		return documentUpload;
	}
	

public static String saveKMLDocumentForWorkLocation(String documentsPath, String requestId,
		MultipartFile mpresFile,String documentType) throws Exception {
	String createdFileName=null;
	BufferedOutputStream stream = null;
	
		try {
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");  
			String strDate= formatter.format(date); 
			
			File serverFile = null;
			File dir = new File(documentsPath);
			String fileExtension=documentType;
			if(null!= mpresFile.getOriginalFilename()) {
				String[] fileArr=mpresFile.getOriginalFilename().split("\\.");
				int length=fileArr.length;
				fileExtension=fileArr[length-1];
			}
			String add = "";
			if(requestId.contains("file")) {
				add = requestId;
			}
			if (!dir.exists()) {
				dir.mkdirs();
				
				createdFileName = RESConstants.KML_Work_location_COPY_FILE
						+ strDate + add
						+ "."
						+ fileExtension;
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			} else {
			
				createdFileName = RESConstants.KML_Work_location_COPY_FILE
						+ strDate + add
						+ "."
						+ fileExtension;
				serverFile = new File(dir.getAbsolutePath() + File.separator
						+ createdFileName);
			}

			
			
				stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(mpresFile.getBytes());
				return createdFileName;
					


			
		} 
		finally {
			try {
				if (null != stream) {
					stream.close();

				}
			} catch (Exception e) {
				
				e.printStackTrace();
				throw e;
			}
		}


}
	
}
