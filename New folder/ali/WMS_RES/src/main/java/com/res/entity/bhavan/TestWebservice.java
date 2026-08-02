package com.res.entity.bhavan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestWebservice {

	/*@RequestMapping(value = "/getBhavanInstance", method = RequestMethod.GET)
	public @ResponseBody Data getAllMessages() throws Exception {

		Group1 group1 = new Group1("Bhopal", "Bhopal", "Bhopal", "Bhopal",
				"12/12/2018", "Richa");
		group1.setAdhikariNameNPost("Richa");

		Group2 group2 = new Group2("Work", "YojnaName", "prashaskiya", "swikratVarsh,", "vartmanstithi");
		group2.setKaryaName("Work");
		
		
		Meta meta = new Meta();
		meta.setInstanceID("uuid:2ff08073-4378-4587-9a42-df06986d23f7");
		
		Data data = new Data(group1, group2);
		data.setMeta(meta);
		data.setGroup1(group1);
		data.setGroup2(group2);
		
		
		return data;
	}*/
	
	
	/*@RequestMapping(value = "/getBhavanTemplate", method = RequestMethod.GET, produces = "application/xml") //mimeMultipartData
	 public ResponseEntity<InputStreamResource> download() throws IOException {
		
		String fileName = "Bhavan.xml";
		
	  System.out.println("Calling Download:- " + fileName);
	  ClassPathResource pdfFile = new ClassPathResource("downloads/" + fileName);
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.parseMediaType("application/json"));
	  headers.add("Access-Control-Allow-Origin", "*");
	  headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
	  headers.add("Access-Control-Allow-Headers", "Content-Type");
	  headers.add("Content-Disposition", "filename=" + fileName);
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache");
	  headers.add("Expires", "0");

	  headers.setContentLength(pdfFile.contentLength());
	  ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
	    new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
	  return response;

	 }*/
	
	

}
