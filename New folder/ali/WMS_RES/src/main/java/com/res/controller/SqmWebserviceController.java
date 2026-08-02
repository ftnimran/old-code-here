package com.res.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.res.bean.InspectionCompletedWorkBean;
import com.res.bean.WorkBean;
import com.res.bean.WorkDataInspectionBean;
import com.res.entity.DataJson;
import com.res.entity.DocumentUpload;
import com.res.entity.FileSqmJson;
import com.res.entity.ImageJson;
import com.res.entity.Role;
import com.res.entity.Users;
import com.res.entity.bhavan.Data;
import com.res.entity.bhavan.Group1;
import com.res.entity.bhavan.Group10;
import com.res.entity.bhavan.Group2;
import com.res.entity.bhavan.Group3;
import com.res.entity.bhavan.Group4;
import com.res.entity.bhavan.Group5;
import com.res.entity.bhavan.Group6;
import com.res.entity.bhavan.Group7;
import com.res.entity.bhavan.Group8;
import com.res.entity.bhavan.Group9;
import com.res.entity.bhavan.Meta;
import com.res.entity.sqm.OverallObservation;
import com.res.repository.DocumentRepository;
import com.res.repository.RoleRepository;
import com.res.repository.UserRepository;
import com.res.service.CommonService;
import com.res.util.RESUtil;

@RestController
@RequestMapping(value = { "/sqm/ws/*", "/ae/*" })
public class SqmWebserviceController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private DocumentRepository documentRepository;

	public static final Logger logger = LoggerFactory
			.getLogger(SqmWebserviceController.class);

	@RequestMapping(value = "/fetchInspectionPendingWorks/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WorkDataInspectionBean> fetchInspectionPendingWorks(
			@PathVariable Long userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching User data");
		}

		List<WorkDataInspectionBean> billBeanList = commonService
				.fetchInspectionPendingWorksForSQM(userId);

		return billBeanList;
	}
	//fetchInspectionCompletedWorks API by nikhil
	@RequestMapping(value = "/fetchInspectionCompletedWorks/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<WorkDataInspectionBean> fetchInspectionCompletedWorks(
			@PathVariable Long userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching User data");
		}
		
		
		

		List<WorkDataInspectionBean> billBeanList = commonService
				.fetchInspectionCompletedWorksForSQM(userId);

		return billBeanList;
	}
	
	// Sqm inspection Question Answers API By Nikhil /{workId}/{inspectedBy}
		@RequestMapping(value = "/fetchUploadedInspectionAnswersSqm/{sqmId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody InspectionCompletedWorkBean fetchInspectionPendingBills(
				@PathVariable Long sqmId, HttpServletRequest request) {

			User user = RESUtil.getUserDetail();
			if (null != user) {
				logger.info("User - " + user.getUsername() + ", Role - "
						+ user.getAuthorities() + " - Fetching SQM Inspection Data data");
			}

			InspectionCompletedWorkBean billBeanList = commonService
					.fetchInspectionCompletedSqm(sqmId);

			return billBeanList;
		}
	
	@RequestMapping(value = "/getBhavanInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody Data getBhavanInstance(@RequestParam Long id,@RequestParam Long userId,@RequestParam String roleId)
			throws Exception {
		
		System.err.println(userId);
		System.err.println(roleId);

		WorkBean workBean = commonService.fetchWorkDetailsByWorkId(id);

		Data data = getBhavanInstanceData(workBean,userId,roleId);

		return data;
	}
	
	@RequestMapping(value = "/getPuliaInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.pulia.Data getPuliaInstance(
			@RequestParam Long id,@RequestParam Long userId,@RequestParam String roleId) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByWorkId(id);
		 com.res.entity.pulia.Data data = getPuliaInstanceData(workBean,userId,roleId);
		 
		return data;
	}
	
	@RequestMapping(value = "/getKhelInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.Khel.Data getKhelInstance(
			@RequestParam Long id,@RequestParam Long userId,@RequestParam String roleId) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByWorkId(id);

		com.res.entity.Khel.Data data  = getKhelInstanceData(workBean,userId,roleId);

		return data;
	}
	
private Data getBhavanInstanceData(WorkBean workBean, Long userId, String roleId) {
		
		Group1 group1 = new Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());
		
		Users u = userRepository.findOne(userId);
		Role r=roleRepository.findOne(roleId);
		 String name= u.getName();
		 String post = r.getRoleName();
		 String adhikariNameNPost = name+"-"+post;
		/*if(roleId.equals("ROLE_AE")) {
			
		}
		if (workBean.getAssistantEngineer() != null) {
			group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());
		} else {
			group1.setAdhikariNameNPost("");
		}
*/       
		group1.setAdhikariNameNPost(adhikariNameNPost); 

		group1.setInspectionDate("");
		/*group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());*/

		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());
		
		Group2 group2 = new Group2();
		group2.setKaryaName(workBean.getWorkName());
		group2.setYojnaName(workBean.getAccountHeadName());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());
		group2.setVartmanstithi("");
		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());

		Meta meta = new Meta();
		meta.setInstanceID("build_Bhavan_1542969303");

		Group3 group3 = new Group3("", "", "");
		Group4 group4 = new Group4("", "", "");
		Group5 group5 = new Group5("", "", "", "");
		Group6 group6 = new Group6("", "", "", "", "", "");
		Group7 group7 = new Group7("", "", "", "");
		Group8 group8 = new Group8("", "");
		Group9 group9 = new Group9("", "", "", "", "", "");
		OverallObservation overallObservation = new OverallObservation("","");
		Group10 group10 = new Group10("", "", "", "", "", ""); // Image n File

		Data data = new Data(meta, group1, group2, group3, group4, group5,
				group6, group7, group8, group9,overallObservation, group10);
		
		return data;
	}

private com.res.entity.Khel.Data getKhelInstanceData(WorkBean workBean, Long userId, String roleId) {
	com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
	group1.setGram(workBean.getVillageBean().getVillageName());
	group1.setGramPanchayat(workBean.getGramPanchayatName());
	group1.setVikaskhand(workBean.getBlockName());
	group1.setLatitude(workBean.getWorkLocationLatitude());
	group1.setLongitude(workBean.getWorkLocationLongitude());
	
	group1.setJila(workBean.getDistrictName());
	
	Users u = userRepository.findOne(userId);
	Role r=roleRepository.findOne(roleId);
	 String name= u.getName();
	 String post = r.getRoleName();
	 String adhikariNameNPost = name+"-"+post;
	/*if(roleId.equals("ROLE_AE")) {
		
	}
	if (workBean.getAssistantEngineer() != null) {
		group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());
	} else {
		group1.setAdhikariNameNPost("");
	}
*/       
	group1.setAdhikariNameNPost(adhikariNameNPost); 

	group1.setInspectionDate("");
	/*group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());*/

	com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
	group2.setKaryaName(workBean.getWorkName());
	group2.setVartmanstithi("");
	group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
	group2.setYojnaName(workBean.getAccountHeadName());
	group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

	com.res.entity.Khel.Meta meta = new com.res.entity.Khel.Meta();
	meta.setInstanceID("build_KhelMaidan_1561110929");
	
	com.res.entity.Khel.InspectionDetails inspectionDetails = new com.res.entity.Khel.InspectionDetails("","","","","","","","","");
	
	com.res.entity.Khel.ImageAndFiles imageAndFiles = new com.res.entity.Khel.ImageAndFiles("","","","","","");



	com.res.entity.Khel.Data data = new com.res.entity.Khel.Data(
			group1, group2,meta, inspectionDetails,"","","",imageAndFiles);
	return data;
	
}

private com.res.entity.pulia.Data getPuliaInstanceData(WorkBean workBean, Long userId, String roleId){
	com.res.entity.pulia.Group1 group1 = new com.res.entity.pulia.Group1();
	group1.setKaryaName(workBean.getWorkName());
	group1.setVartmanstithi("");
	group1.setSwikratVarsh(workBean.getAdministrationSanctionDate());
	group1.setYojnaName(workBean.getAccountHeadName());
	group1.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());
	group1.setLatitude(workBean.getWorkLocationLatitude());
	group1.setLongitude(workBean.getWorkLocationLongitude());
	
	com.res.entity.pulia.Group6 group6 = new com.res.entity.pulia.Group6();
	group6.setGram(workBean.getVillageBean().getVillageName());
	group6.setGramPanchayat(workBean.getGramPanchayatName());
	group6.setVikaskhand(workBean.getBlockName());

	group6.setJila(workBean.getDistrictName());
	
	Users u = userRepository.findOne(userId);
	Role r=roleRepository.findOne(roleId);
	 String name= u.getName();
	 String post = r.getRoleName();
	 String adhikariNameNPost = name+"-"+post;
	/*if(roleId.equals("ROLE_AE")) {
		
	}
	if (workBean.getAssistantEngineer() != null) {
		group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());
	} else {
		group1.setAdhikariNameNPost("");
	}
*/       
	group6.setAdhikariNameNPost(adhikariNameNPost); 
	

	group6.setInspectionDate("");
	/*if(workBean.getAdhikariNameNPost() !=null) {
	group6.setAdhikariNameNPost(workBean.getAdhikariNameNPost());
	}else {
		group6.setAdhikariNameNPost("");
	}*/
	
	

	com.res.entity.pulia.Meta meta = new com.res.entity.pulia.Meta();
	meta.setInstanceID("build_pulia_1539914442");

	com.res.entity.pulia.Group2 group2 = new com.res.entity.pulia.Group2(
			"", "", "", "", "", "");

	com.res.entity.pulia.Group3 group3 = new com.res.entity.pulia.Group3(
			"", "", "", "", "", "", "", "", "");

	com.res.entity.pulia.Group4 group4 = new com.res.entity.pulia.Group4(
			"", "", "", "");

	com.res.entity.pulia.Group5 group5 = new com.res.entity.pulia.Group5(
			"", "", "", "", "", "");
	OverallObservation overallObservation = new OverallObservation("","");

	com.res.entity.pulia.Group7 group7 = new com.res.entity.pulia.Group7(
			"", "", "", "", "", ""); // Image n File

	com.res.entity.pulia.Data data = new com.res.entity.pulia.Data(meta,
			group1, group2, group3, group4, group5, group6,overallObservation, group7);

	return data;
}





	
	//pending sqm inspection
		@RequestMapping(value = "/getMargNirman.xml", method = RequestMethod.GET, produces = "application/xml")
		public @ResponseBody com.res.entity.sqm.Data getMargNirman(
				@RequestParam Long id,@RequestParam Long userId,@RequestParam String roleId) throws Exception {

			WorkBean workBean = commonService.fetchWorkDetailsByWorkId(id);

			com.res.entity.sqm.Data data  = getMargInstanceData(workBean,userId,roleId);

			return data;
		}
		
		
		
		
		
		private com.res.entity.sqm.Data getMargInstanceData(WorkBean workBean, Long userId, String roleId) {
			com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
			group1.setGram(workBean.getVillageBean().getVillageName());
			group1.setGramPanchayat(workBean.getGramPanchayatName());
			group1.setVikaskhand(workBean.getBlockName());
			group1.setLatitude(workBean.getWorkLocationLatitude());
			group1.setLongitude(workBean.getWorkLocationLongitude());
			
			group1.setJila(workBean.getDistrictName());
		

			Users u = userRepository.findOne(userId);
				Role r=roleRepository.findOne(roleId);
				 String name= u.getName();
				 String post = r.getRoleName();
				 String adhikariNameNPost = name+"-"+post;
				/*if(roleId.equals("ROLE_AE")) {
					
				}
				if (workBean.getAssistantEngineer() != null) {
					group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());
				} else {
					group1.setAdhikariNameNPost("");
				}
		*/       
				group1.setAdhikariNameNPost(adhikariNameNPost); 

			group1.setInspectionDate("");
			
			/*group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());*/

			com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
			group2.setKaryaName(workBean.getWorkName());
			group2.setVartmanstithi("");
			group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
			group2.setYojnaName(workBean.getAccountHead());
			group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

			com.res.entity.sqm.Meta meta = new com.res.entity.sqm.Meta();
			meta.setInstanceID("build_SQM-Marg-Nirman-Inspection_1558696771");
			
			
			
			com.res.entity.sqm.SettingOutGrading settingOutGrading = new com.res.entity.sqm.SettingOutGrading("",""); 
			com.res.entity.sqm.SettingOutAndWorking settingOutAndWorking = new com.res.entity.sqm.SettingOutAndWorking(
					"", "", "", "",settingOutGrading );
			
			com.res.entity.sqm.SiteClearanceGrading siteClearanceGrading = new com.res.entity.sqm.SiteClearanceGrading("","");

			com.res.entity.sqm.SiteClearanceAndGrubbing siteClearanceAndGrubbing = new com.res.entity.sqm.SiteClearanceAndGrubbing(
					"", "", "",siteClearanceGrading );
			
			
			com.res.entity.sqm.MandatoryTestsGrading mandatoryTestsGrading = new com.res.entity.sqm.MandatoryTestsGrading("","");
			
			com.res.entity.sqm.ObservationsAboutLaboratory observationsAboutLaboratory = new com.res.entity.sqm.ObservationsAboutLaboratory(
					"","","" );
			
			com.res.entity.sqm.ObservationsAboutTests observationsAboutTests = new com.res.entity.sqm.ObservationsAboutTests(
					"","","","","" );
			
			com.res.entity.sqm.QualityArrangements qualityArrangements = new com.res.entity.sqm.QualityArrangements(
					observationsAboutLaboratory, observationsAboutTests,mandatoryTestsGrading,"","","" );
			
			com.res.entity.sqm.GeometricsObservationsRoadWay geometricsObservationsRoadWay = new com.res.entity.sqm.GeometricsObservationsRoadWay(
					"","","","");
			
			com.res.entity.sqm.GeometricsObservationsSuper geometricsObservationsSuper = new com.res.entity.sqm.GeometricsObservationsSuper(
					"","","");
			
			com.res.entity.sqm.ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment = new com.res.entity.sqm.ObservationsQoMaterialForEmbankment(
					"","","");
			com.res.entity.sqm.ObservationsWorkmanship observationsWorkmanship = new com.res.entity.sqm.ObservationsWorkmanship(
					"","","","","","");
			
			com.res.entity.sqm.ObservationsSlide observationsSlide = new com.res.entity.sqm.ObservationsSlide(
					"","","");
			com.res.entity.sqm.ObservationsEarthWorkHilly observationsEarthWorkHilly = new com.res.entity.sqm.ObservationsEarthWorkHilly(
					"","","","");
			com.res.entity.sqm.ObservationsLongitudinal observationsLongitudinal = new com.res.entity.sqm.ObservationsLongitudinal(
					"","","");
			
			
			com.res.entity.sqm.ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading = new com.res.entity.sqm.ObservationsQoWEarthWorkGrading("","");
			
			
			com.res.entity.sqm.ObservationsWorkmanshipGrading observationsWorkmanshipGrading = new com.res.entity.sqm.ObservationsWorkmanshipGrading("","");
			
			com.res.entity.sqm.ObservationsLongitudinalGrading observationsLongitudinalGrading = new com.res.entity.sqm.ObservationsLongitudinalGrading("","");
			com.res.entity.sqm.ObservationsQoWEarthWork observationsQoWEarthWork = new com.res.entity.sqm.ObservationsQoWEarthWork(
					observationsQoMaterialForEmbankment,
					observationsQoWEarthWorkGrading,
					observationsWorkmanshipGrading,
					observationsLongitudinalGrading,
					observationsWorkmanship,
					observationsSlide,
					observationsEarthWorkHilly,observationsLongitudinal);
			
			com.res.entity.sqm.BaseCourseObservationsQoM baseCourseObservationsQoM = new com.res.entity.sqm.BaseCourseObservationsQoM(
					"","","","", "","","");
			
			com.res.entity.sqm.BaseCourseGrading baseCourseGrading = new com.res.entity.sqm.BaseCourseGrading("","");
			
			com.res.entity.sqm.BaseCourse baseCourse = new com.res.entity.sqm.BaseCourse(
					baseCourseObservationsQoM,baseCourseGrading);
			
			
			com.res.entity.sqm.SurfaceCourseGrading surfaceCourseGrading = new com.res.entity.sqm.SurfaceCourseGrading("","");
			
			com.res.entity.sqm.SurfaceCourseQoM surfaceCourseQoM = new com.res.entity.sqm.SurfaceCourseQoM(
					"","","","","");
			
			com.res.entity.sqm.SurfaceCourse surfaceCourse = new com.res.entity.sqm.SurfaceCourse(
					surfaceCourseQoM,
					"",surfaceCourseGrading);
			
			com.res.entity.sqm.ObservationsQoS observationsQoS = new com.res.entity.sqm.ObservationsQoS(
					"","","","","");
			
			com.res.entity.sqm.CrossDrainageWorksObservations crossDrainageWorksObservations = new com.res.entity.sqm.CrossDrainageWorksObservations(
					"","","","");
			
			com.res.entity.sqm.CrossDrainageWorksGrading crossDrainageWorksGrading = new com.res.entity.sqm.CrossDrainageWorksGrading("","");
			
			com.res.entity.sqm.CrossDrainageWorks crossDrainageWorks = new com.res.entity.sqm.CrossDrainageWorks(
					crossDrainageWorksObservations,
					crossDrainageWorksGrading,"");
			
			com.res.entity.sqm.SideDrainsObservations sideDrainsObservations = new com.res.entity.sqm.SideDrainsObservations(
					"","","","");
			
			
			com.res.entity.sqm.SideDrainsCatchGrading sideDrainsCatchGrading = new com.res.entity.sqm.SideDrainsCatchGrading("","");
			
			com.res.entity.sqm.SideDrains sideDrains = new com.res.entity.sqm.SideDrains(
					sideDrainsObservations,
					sideDrainsCatchGrading);
			
			com.res.entity.sqm.CcSemiRigidObservations ccSemiRigidObservations = new com.res.entity.sqm.CcSemiRigidObservations(
					"","","","","","");
			
			com.res.entity.sqm.CcSemiRigidGrading ccSemiRigidGrading = new com.res.entity.sqm.CcSemiRigidGrading("","");

			
			com.res.entity.sqm.CcSemiRigid ccSemiRigid = new com.res.entity.sqm.CcSemiRigid(
					ccSemiRigidObservations,
					ccSemiRigidGrading);
			
			com.res.entity.sqm.RoadFurnitureObservations roadFurnitureObservations = new com.res.entity.sqm.RoadFurnitureObservations(
					"");
			
			com.res.entity.sqm.RoadFurnitureQualityRoad roadFurnitureQualityRoad = new com.res.entity.sqm.RoadFurnitureQualityRoad(
					"","","","","","","");
			
			com.res.entity.sqm.RoadFurnitureMarkingsGrading roadFurnitureMarkingsGrading = new com.res.entity.sqm.RoadFurnitureMarkingsGrading("","");

			
			com.res.entity.sqm.RoadFurnitureMarkings roadFurnitureMarkings = new com.res.entity.sqm.RoadFurnitureMarkings(
					roadFurnitureObservations,roadFurnitureQualityRoad,
					roadFurnitureMarkingsGrading);
			
			com.res.entity.sqm.ObservationsQualityOfWork observationsQualityOfWork = new com.res.entity.sqm.ObservationsQualityOfWork(
					observationsQoWEarthWork,baseCourse,surfaceCourse,
					observationsQoS,
					crossDrainageWorks,
					sideDrains,
					ccSemiRigid,
					roadFurnitureMarkings);
			
			
			com.res.entity.sqm.GeneralObsSQM generalObsSQM = new com.res.entity.sqm.GeneralObsSQM(
					"","","","","");
			
			
			
			
			com.res.entity.sqm.GeometricsGrading geometricsGrading = new com.res.entity.sqm.GeometricsGrading("","");
			com.res.entity.sqm.Geometrics geometrics = new com.res.entity.sqm.Geometrics(
					geometricsObservationsRoadWay,
					geometricsObservationsSuper,
					geometricsGrading );
			com.res.entity.sqm.Group10 group10 = new com.res.entity.sqm.Group10("", "", "","",""); // Image 
			
			com.res.entity.sqm.Group11 group11 = new com.res.entity.sqm.Group11("", "", ""); // File
			
			com.res.entity.sqm.OverallObservation overallObservation = new com.res.entity.sqm.OverallObservation("", "");

			com.res.entity.sqm.Data data = new com.res.entity.sqm.Data(group1,group2,meta,
					settingOutAndWorking, siteClearanceAndGrubbing, qualityArrangements, geometrics, observationsQualityOfWork, generalObsSQM, "","",group10,group11,overallObservation);
			return data;
			
			
		}
		
		
		@RequestMapping(value = "/getSarovarInstance.xml", method = RequestMethod.GET, produces = "application/xml")
		public @ResponseBody com.res.entity.sarovar.Data getSarovarInstance(
				@RequestParam Long id,@RequestParam Long userId,@RequestParam String roleId) throws Exception {

			WorkBean workBean = commonService.fetchWorkDetailsByWorkId(id);

			com.res.entity.sarovar.Data data  = getSarovarInstanceData(workBean,userId,roleId);

			return data;
		}
		
		
		private com.res.entity.sarovar.Data getSarovarInstanceData(WorkBean workBean, Long userId, String roleId) {
			com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
			group1.setGram(workBean.getVillageBean().getVillageName());
			group1.setGramPanchayat(workBean.getGramPanchayatName());
			group1.setVikaskhand(workBean.getBlockName());
			group1.setLatitude(workBean.getWorkLocationLatitude());
			group1.setLongitude(workBean.getWorkLocationLongitude());
			
			group1.setJila(workBean.getDistrictName());
			
			Users u = userRepository.findOne(userId);
			Role r=roleRepository.findOne(roleId);
			 String name= u.getName();
			 String post = r.getRoleName();
			 String adhikariNameNPost = name+"-"+post;
			/*if(roleId.equals("ROLE_AE")) {
				
			}
			if (workBean.getAssistantEngineer() != null) {
				group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());
			} else {
				group1.setAdhikariNameNPost("");
			}
	*/       
			group1.setAdhikariNameNPost(adhikariNameNPost);

			group1.setInspectionDate("");
			
			/*group1.setAdhikariNameNPost(workBean.getAdhikariNameNPost());*/

			com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
			group2.setKaryaName(workBean.getWorkName());
			group2.setVartmanstithi("");
			group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
			group2.setYojnaName(workBean.getAccountHead());
			group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

			com.res.entity.sarovar.Meta meta = new com.res.entity.sarovar.Meta();
			meta.setInstanceID("build_SQM-Sarovar-Inspection_1554819267");
			
			
			
			com.res.entity.sarovar.SettingGrading settingGrading = new com.res.entity.sarovar.SettingGrading("",""); 
			com.res.entity.sarovar.SettingOut settingOut = new com.res.entity.sarovar.SettingOut(
					"", "", "", "","",settingGrading );
			
			com.res.entity.sarovar.SiteClearanceGrading siteClearanceGrading = new com.res.entity.sarovar.SiteClearanceGrading("","");

			com.res.entity.sarovar.SiteClearanceAndBenching siteClearanceAndBenching = new com.res.entity.sarovar.SiteClearanceAndBenching(
					"", "", "",siteClearanceGrading );
			
			
			
			
			com.res.entity.sqm.ObservationsAboutLaboratory observationsAboutLaboratory = new com.res.entity.sqm.ObservationsAboutLaboratory(
					"","","" );
			
			com.res.entity.sqm.ObservationsAboutTests observationsAboutTests = new com.res.entity.sqm.ObservationsAboutTests(
					"","","","","" );
			
			com.res.entity.sqm.MandatoryTestsGrading mandatoryTestsGrading = new com.res.entity.sqm.MandatoryTestsGrading("","");
			
			com.res.entity.sqm.QualityArrangements qualityArrangements = new com.res.entity.sqm.QualityArrangements(
					observationsAboutLaboratory, observationsAboutTests,mandatoryTestsGrading,"","","" );
			
			com.res.entity.sarovar.ObservationsQoMaterialForEmbankment observationsQoMaterialForEmbankment =  new com.res.entity.sarovar.ObservationsQoMaterialForEmbankment("","","");
			com.res.entity.sarovar.ObservationsQoWEarthWorkGrading observationsQoWEarthWorkGrading = new com.res.entity.sarovar.ObservationsQoWEarthWorkGrading("","");
			com.res.entity.sarovar.ObservationsWorkmanship observationsWorkmanship = new com.res.entity.sarovar.ObservationsWorkmanship("","","","","",""); 
			com.res.entity.sarovar.ObservationsWorkmanshipGrading observationsWorkmanshipGrading = new com.res.entity.sarovar.ObservationsWorkmanshipGrading("","");
			com.res.entity.sarovar.ObservationsSlide observationsSlide = new com.res.entity.sarovar.ObservationsSlide("","","");
			
			
			com.res.entity.sarovar.ObservationsQoWEarthWork observationsQoWEarthWork = new com.res.entity.sarovar.ObservationsQoWEarthWork(observationsQoMaterialForEmbankment,observationsQoWEarthWorkGrading,
					observationsWorkmanship,observationsWorkmanshipGrading,observationsSlide);
			
			com.res.entity.sarovar.QualityofMaterialandWorkmanship qualityofMaterialandWorkmanship= new com.res.entity.sarovar.QualityofMaterialandWorkmanship("","","","","","","");
			com.res.entity.sarovar.ExternalDrainageGrading externalDrainageGrading= new com.res.entity.sarovar.ExternalDrainageGrading("","");
			
			com.res.entity.sarovar.ExternalDrainageSystem externalDrainageSystem= new com.res.entity.sarovar.ExternalDrainageSystem(qualityofMaterialandWorkmanship,externalDrainageGrading);
			
			com.res.entity.sarovar.BaseCourseObservationsQoM baseCourseObservationsQoM = new com.res.entity.sarovar.BaseCourseObservationsQoM("","","","","");
			com.res.entity.sarovar.UpStreamGrading upStreamGrading = new com.res.entity.sarovar.UpStreamGrading("","");
			com.res.entity.sarovar.UpstreamProtectionWork upstreamProtectionWork = new com.res.entity.sarovar.UpstreamProtectionWork(baseCourseObservationsQoM,"",upStreamGrading);
			
			com.res.entity.sarovar.SurplusingArrangements surplusingArrangements = new com.res.entity.sarovar.SurplusingArrangements("","","","");
			
			
			
			com.res.entity.sarovar.ObservationsQualityOfWork observationsQualityOfWork = new com.res.entity.sarovar.ObservationsQualityOfWork(
					observationsQoWEarthWork,externalDrainageSystem,upstreamProtectionWork,surplusingArrangements);
			
			com.res.entity.sarovar.GeneralObsSQM generalObsSQM = new com.res.entity.sarovar.GeneralObsSQM("","","","","");
			
			
			
			
			
			com.res.entity.sqm.Group10 group10 = new com.res.entity.sqm.Group10("", "", "", "", ""); // Image and File
		
			com.res.entity.sqm.Group11 group11 = new com.res.entity.sqm.Group11("", "", "");
			
			
			com.res.entity.sqm.OverallObservation overallObservation = new com.res.entity.sqm.OverallObservation("", "");
			
			
			com.res.entity.sarovar.Data data = new com.res.entity.sarovar.Data(group1,group2,"","","",meta,settingOut,siteClearanceAndBenching
					,qualityArrangements,observationsQualityOfWork,generalObsSQM,"","",group10,group11,overallObservation);
			return data;
			
			
		}
		
		

		@RequestMapping(value = "/saveMargAnswer", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> saveSadakAnswers(
				@RequestBody DataJson dataJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();

			try {
				map = commonService.saveSqmInspectionAnwserForSadak(dataJson); 
			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				logger.error("Error in saving Marg Inspection Data.." + e.getStackTrace());
				return map;
			}
		

			logger.info("Marg Inspection Data saved for sqm..");

			return map;

		}
		
		@RequestMapping(value = "/saveBhavanAnswers", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> saveBhavanAnswers(
				@RequestBody DataJson dataJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();

			try {
				
				logger.info(dataJson +"");
				map = commonService.saveSqmInspectionAnwserForBhavan(dataJson);
			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				return map;
			}

		/*	map.put("code", "200");
			map.put("message", "SUCCESS");
			map.put("DetailMessage", "Bhavan Inspection Data saved");*/

			logger.info("Bhavan Inspection Data saved..");

			return map;

		}
		
		@RequestMapping(value = "/saveKhelAnswers", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> saveKhelAnswers(
				@RequestBody DataJson dataJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();
			//saveInspectionAnwserForKhel

			try {
				map =  commonService.saveSqmInspectionAnwserForKhel(dataJson); 
			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				logger.error("Error in saving Khel Maidan Inspection Data.." + e.getStackTrace());
				return map;
			}

			/*map.put("code", "200");
			map.put("message", "SUCCESS");
			map.put("DetailMessage", "Sadak Inspection Data saved");*/

			logger.info("Khel Maidan Sqm Inspection Data saved..");

			return map;

		}
		
		
		@RequestMapping(value = "/savePuliaAnswers", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> savePuliaAnswers(
				@RequestBody DataJson dataJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();

			try {
				map = commonService.saveSqmInspectionAnwserForPulia(dataJson);
			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				
				logger.error("Error in saving Pulia Inspection Data For Sqm.." + e.getStackTrace());
				
				return map;
			}

			/*map.put("code", "200");
			map.put("message", "SUCCESS");
			map.put("DetailMessage", "Pulia Inspection Data saved");*/

			logger.info("Pulia Inspection Data saved..");

			return map;

		}

		
		@RequestMapping(value = "/saveSarovarAnswers", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> saveSarovarAnswers(
				@RequestBody DataJson dataJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();

			try {
				map = commonService.saveSqmInspectionAnwserForSarovar(dataJson); 
			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				logger.error("Error in saving Marg Inspection Data.." + e.getStackTrace());
				return map;
			}

			/*map.put("code", "200");
			map.put("message", "SUCCESS");
			map.put("DetailMessage", "Sarovar Inspection Data saved for sqm");*/

			logger.info("Sarovar Inspection Data saved for sqm..");

			return map;

		}
		
		
		// Generic method for all Templates
		@RequestMapping(value = "/getTemplate/{fileName:.+}", method = RequestMethod.GET, produces = "application/xml")
		public ResponseEntity<InputStreamResource> getBhavanTemplate(
				@PathVariable String fileName) throws IOException {

			ClassPathResource pdfFile = new ClassPathResource("downloads/"
					+ fileName);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/xml"));
			headers.add("Access-Control-Allow-Origin", "*");
			headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
			headers.add("Access-Control-Allow-Headers", "Content-Type");
			headers.add("Content-Disposition", "filename=" + fileName);
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");

			headers.setContentLength(pdfFile.contentLength());
			ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
					new InputStreamResource(pdfFile.getInputStream()), headers,
					HttpStatus.OK);

			logger.info(fileName + " Sent..");
			return response;

		}
		
		//In Use
		@RequestMapping(value = "/imageUpload", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> imageUpload(
				@RequestBody ImageJson imageJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();
			try {
				commonService.saveImageSqm(imageJson);

			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				logger.error("Error in saving Image in Inspection Sqm Data.." + e.getStackTrace());
				return map;
			}

			map.put("code", "200");
			map.put("message", "SUCCESS");
			map.put("DetailMessage", "Image saved successfully !");

			logger.info("Image Data saved..");

			return map;
		}
		
		@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Map<String, String> fileUpload(
				@RequestBody FileSqmJson fileJson) throws IOException {

			Map<String, String> map = new HashMap<String, String>();
			try {
				commonService.saveFileSqm(fileJson);

			} catch (Exception e) {

				map.put("code", "500");
				map.put("message", "Internal Server Error");
				map.put("error", e.getMessage());
				logger.error("Error in saving File in Inspection Sqm Data.." + e.getStackTrace());
				return map;
			}

			map.put("code", "200");
			map.put("message", "SUCCESS");
			map.put("DetailMessage", "File uploaded successfully !");

			logger.info("File saved..");

			return map;
		}
		
		@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR , reason="Error in saving SQM Inspection Data")
		@ExceptionHandler(Exception.class)
		public void exceptionHandler(Exception e) 
		{
			logger.error("Error in saving SQM Inspection Data..");
			e.printStackTrace();
		}
		
		@RequestMapping(value = "/downloadDocumentImage/{imageName}", method = RequestMethod.GET)
		public void downloadDocument(@PathVariable String imageName, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			DocumentUpload doc=documentRepository.findByDocumentName(imageName+".jpg");
			
	         
			String fileName = commonService.fetchDownloadFileName(doc.getDocumentId());

			InputStream is=null;
			OutputStream os=null;
			try {
				if (fileName != null) {
					File file = new File(fileName);
					is = new FileInputStream(file);

					// MIME type of the file
					response.setContentType("application/octet-stream");
					// Response header
					response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
					// Read from the file and write into the response
					os = response.getOutputStream();
					byte[] buffer = new byte[1024];
					int len;
					while ((len = is.read(buffer)) != -1) {
						os.write(buffer, 0, len);
					}
			}
			}catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if(null!=os) {
					os.flush();
					os.close();
				}
				if(null!=is) {
					is.close();
				}
			}
			
				
			}
		

		
		/*
		 * @RequestMapping(value = "/saveSadakAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveSadakAnswers(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			commonService.saveInspectionAnwserForSadak(dataJson); 
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Sadak Inspection Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Sadak Inspection Data saved");

		logger.info("Sadak Inspection Data saved..");

		return map;

	}
		 * private Meta meta;
		private SettingOutAndWorking settingOutAndWorking;
		private SiteClearanceAndGrubbing siteClearanceAndGrubbing;
		private QualityArrangements qualityArrangements;
		private Geometrics geometrics;
		private ObservationsQualityOfWork observationsQualityOfWork;
		private GeneralObsSQM generalObsSQM;
		private String otherObservations;
		
		private com.res.entity.sqm.Data getMargInanceData(WorkBean workBean) {
			com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
			group1.setGram(workBean.getVillageBean().getVillageName());
			group1.setGramPanchayat(workBean.getGramPanchayatName());
			group1.setVikaskhand(workBean.getBlockName());
			group1.setLatitude(workBean.getWorkLocationLatitude());
			group1.setLongitude(workBean.getWorkLocationLongitude());
			
			group1.setJila(workBean.getDistrictName());
			if (workBean.getAssistantEngineer() != null) {
				group1.setAdhikariNameNPost(workBean.getAssistantEngineer()
						.getName());
			} else {
				group1.setAdhikariNameNPost("");
			}

			group1.setInspectionDate("");

			com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
			group2.setKaryaName(workBean.getWorkName());
			group2.setVartmanstithi("");
			group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
			group2.setYojnaName(workBean.getAccountHead());
			group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

			com.res.entity.sadak.Meta meta = new com.res.entity.sadak.Meta();
			meta.setInstanceID("build_marg_nirman_1529913473");

			com.res.entity.sadak.Group3 group3 = new com.res.entity.sadak.Group3(
					"", "", "", "", "", "", "", "", "");

			com.res.entity.sadak.Group4 group4 = new com.res.entity.sadak.Group4(
					"", "", "", "", "", "", "");

			com.res.entity.sadak.Group5 group5 = new com.res.entity.sadak.Group5(
					"", "", "", "", "", "", "");

			com.res.entity.sadak.Group6 group6 = new com.res.entity.sadak.Group6("");

			com.res.entity.sadak.Group7 group7 = new com.res.entity.sadak.Group7("");

			com.res.entity.sadak.Group8 group8 = new com.res.entity.sadak.Group8(
					"", "", "", "", "", "", "");

			com.res.entity.sadak.Group9 group9 = new com.res.entity.sadak.Group9(
					"", "", "", "", "", "");

			com.res.entity.sadak.Data data = new com.res.entity.sadak.Data(meta,
					group1, group2, group3, group4, group5, group6, group7, group8,
					group9);
			return data;
			
		}*/

	/*@RequestMapping(value = "/fetchInspectionCompletedRESBills/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillDataInspectionBean> fetchInspectionCompletedRESBills(
			@PathVariable String userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching User data");
		}

		List<BillDataInspectionBean> billBeanList = commonService
				.fetchInspectionCompletedBillsForRES(userId, RESConstants.STATUS_PHYSICAL_INSPECTION_COMPLETED_ID);

		return billBeanList;
	}

	@RequestMapping(value = "/fetchInspectionPendingBillsForGP/{workTypeId}/{userId:.+}", method = RequestMethod.GET)
	public @ResponseBody List<BillDataInspectionBean> fetchInspectionPendingBillsForGP(
			@PathVariable Long workTypeId, @PathVariable String userId,
			HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching User data");
		}

		Long agencyType = 2L;

		List<BillDataInspectionBean> billBeanList = commonService
				.fetchInspectionPendingBillsForGP(agencyType, workTypeId,
						userId);

		return billBeanList;
	}

	@RequestMapping(value = "/fetchInspectionCompletedGPBills/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillDataInspectionBean> fetchInspectionCompletedGPBills(
			@PathVariable String userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching User data");
		}

		Long agencyType = 2L;

		List<BillDataInspectionBean> billBeanList = commonService
				.fetchInspectionCompletedBillsForGP(agencyType, userId);

		return billBeanList;
	}

	// Generic method for all Templates
	@RequestMapping(value = "/getTemplate/{fileName:.+}", method = RequestMethod.GET, produces = "application/xml")
	public ResponseEntity<InputStreamResource> getBhavanTemplate(
			@PathVariable String fileName) throws IOException {

		ClassPathResource pdfFile = new ClassPathResource("downloads/"
				+ fileName);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/xml"));
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "filename=" + fileName);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		headers.setContentLength(pdfFile.contentLength());
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
				new InputStreamResource(pdfFile.getInputStream()), headers,
				HttpStatus.OK);

		logger.info(fileName + " Sent..");
		return response;

	}

	
	///Bhavan
	//CC Inspection
		
	@RequestMapping(value = "/getBhavanInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody Data getBhavanInstanceCC(@RequestParam Long id)
			throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);

		Data data = getBhavanInstanceData(workBean);

		return data;
	}
	
	// Inspection Bill
	@RequestMapping(value = "/getBhavanInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody Data getBhavanInstance(@RequestParam Long id)
			throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByBillId(id);

		Data data = getBhavanInstanceData(workBean);

		return data;
	}
	
	private Data getBhavanInstanceData(WorkBean workBean) {
		
		Group1 group1 = new Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());

		group1.setJila(workBean.getDistrictName());
		if (workBean.getAssistantEngineer() != null) {
			group1.setAdhikariNameNPost(workBean.getAssistantEngineer()
					.getName());
		} else {
			group1.setAdhikariNameNPost("");
		}

		group1.setInspectionDate("");

		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());
		
		Group2 group2 = new Group2();
		group2.setKaryaName(workBean.getWorkName());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());
		group2.setVartmanstithi("");
		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());

		Meta meta = new Meta();
		meta.setInstanceID("build_Bhavan_1528264386");

		Group3 group3 = new Group3("", "", "");
		Group4 group4 = new Group4("", "", "");
		Group5 group5 = new Group5("", "", "", "");
		Group6 group6 = new Group6("", "", "", "", "", "");
		Group7 group7 = new Group7("", "", "", "");
		Group8 group8 = new Group8("", "");
		Group9 group9 = new Group9("", "", "", "", "", "");
		Group10 group10 = new Group10("", "", "", "", "", ""); // Image n File

		Data data = new Data(meta, group1, group2, group3, group4, group5,
				group6, group7, group8, group9, group10);
		
		return data;
	}

	@RequestMapping(value = "/saveBhavanAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveBhavanAnswers(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			
			logger.info(dataJson +"");
			commonService.saveInspectionAnwserForBhavan(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Bhavan Inspection Data saved");

		logger.info("Bhavan Inspection Data saved..");

		return map;

	}

	// Pulia

	//CC Inspection
	@RequestMapping(value = "/getPuliaInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.pulia.Data getPuliaInstanceCC(
			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);
		com.res.entity.pulia.Data data = getPuliaInstanceData(workBean);
		 
		return data;
	}
	
	// Inspectoin Bill
	@RequestMapping(value = "/getPuliaInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.pulia.Data getPuliaInstance(
			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByBillId(id);
		 com.res.entity.pulia.Data data = getPuliaInstanceData(workBean);
		 
		return data;
	}

	private com.res.entity.pulia.Data getPuliaInstanceData(WorkBean workBean){
		com.res.entity.pulia.Group1 group1 = new com.res.entity.pulia.Group1();
		group1.setKaryaName(workBean.getWorkName());
		group1.setVartmanstithi("");
		group1.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group1.setYojnaName(workBean.getAccountHead());
		group1.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());
		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());
		
		com.res.entity.pulia.Group6 group6 = new com.res.entity.pulia.Group6();
		group6.setGram(workBean.getVillageBean().getVillageName());
		group6.setGramPanchayat(workBean.getGramPanchayatName());
		group6.setVikaskhand(workBean.getBlockName());

		group6.setJila(workBean.getDistrictName());
		if (workBean.getAssistantEngineer() != null) {
			group6.setAdhikariNameNPost(workBean.getAssistantEngineer()
					.getName());
		} else {
			group6.setAdhikariNameNPost("");
		}

		group6.setInspectionDate("");

		com.res.entity.pulia.Meta meta = new com.res.entity.pulia.Meta();
		meta.setInstanceID("build_Bhavan_1528264386");

		com.res.entity.pulia.Group2 group2 = new com.res.entity.pulia.Group2(
				"", "", "", "", "", "");

		com.res.entity.pulia.Group3 group3 = new com.res.entity.pulia.Group3(
				"", "", "", "", "", "", "", "", "");

		com.res.entity.pulia.Group4 group4 = new com.res.entity.pulia.Group4(
				"", "", "", "");

		com.res.entity.pulia.Group5 group5 = new com.res.entity.pulia.Group5(
				"", "", "", "", "", "");

		com.res.entity.pulia.Group7 group7 = new com.res.entity.pulia.Group7(
				"", "", "", "", "", ""); // Image n File

		com.res.entity.pulia.Data data = new com.res.entity.pulia.Data(meta,
				group1, group2, group3, group4, group5, group6, group7);

		return data;
	}
	
	
	@RequestMapping(value = "/savePuliaAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> savePuliaAnswers(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			commonService.saveInspectionAnwserForPulia(dataJson);
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			
			logger.error("Error in saving Pulia Inspection Data.." + e.getStackTrace());
			
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Pulia Inspection Data saved");

		logger.info("Pulia Inspection Data saved..");

		return map;

	}

	@RequestMapping(value = "/getWorkTypeList", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<WorkTypeBean> fetchWorkType() {

		List<WorkTypeBean> list = commonService.fetchWorkType();

		return list;
	}

	//In Use
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> imageUpload(
			@RequestBody ImageJson imageJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		try {
			commonService.saveImage(imageJson);

		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Image in Inspection Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Image saved successfully !");

		logger.info("Image Data saved..");

		return map;
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> fileUpload(
			@RequestBody FileJson fileJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();
		try {
			commonService.saveFile(fileJson);

		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving File in Inspection Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "File uploaded successfully !");

		logger.info("File saved..");

		return map;
	}

	
	//CC Inspection
	
	@RequestMapping(value = "/getSadakInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.sadak.Data getSadakInstanceCC(
			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);

		com.res.entity.sadak.Data data  = getSadakInstanceData(workBean);

		return data;
	}
	
	//Inspection bill
	@RequestMapping(value = "/getSadakInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.sadak.Data getSadakInstance(
			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.sadak.Data data  = getSadakInstanceData(workBean);

		return data;
	}
	
	private com.res.entity.sadak.Data getSadakInstanceData(WorkBean workBean) {
		com.res.entity.sadak.Group1 group1 = new com.res.entity.sadak.Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());
		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());
		
		group1.setJila(workBean.getDistrictName());
		if (workBean.getAssistantEngineer() != null) {
			group1.setAdhikariNameNPost(workBean.getAssistantEngineer()
					.getName());
		} else {
			group1.setAdhikariNameNPost("");
		}

		group1.setInspectionDate("");

		com.res.entity.sadak.Group2 group2 = new com.res.entity.sadak.Group2();
		group2.setKaryaName(workBean.getWorkName());
		group2.setVartmanstithi("");
		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

		com.res.entity.sadak.Meta meta = new com.res.entity.sadak.Meta();
		meta.setInstanceID("build_sadak_1529913473");

		com.res.entity.sadak.Group3 group3 = new com.res.entity.sadak.Group3(
				"", "", "", "", "", "", "", "", "");

		com.res.entity.sadak.Group4 group4 = new com.res.entity.sadak.Group4(
				"", "", "", "", "", "", "");

		com.res.entity.sadak.Group5 group5 = new com.res.entity.sadak.Group5(
				"", "", "", "", "", "", "");

		com.res.entity.sadak.Group6 group6 = new com.res.entity.sadak.Group6("");

		com.res.entity.sadak.Group7 group7 = new com.res.entity.sadak.Group7("");

		com.res.entity.sadak.Group8 group8 = new com.res.entity.sadak.Group8(
				"", "", "", "", "", "", "");

		com.res.entity.sadak.Group9 group9 = new com.res.entity.sadak.Group9(
				"", "", "", "", "", "");

		com.res.entity.sadak.Data data = new com.res.entity.sadak.Data(meta,
				group1, group2, group3, group4, group5, group6, group7, group8,
				group9);
		return data;
		
	}
	
	
	
	

	// Talab
	
	//CC Inspection
	@RequestMapping(value = "/getTalabInstanceCC.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.talab.Data getTalabInstanceCC(
			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetails(id);
		com.res.entity.talab.Data data = getTalabInstanceData(workBean);

		return data;
	}
	
	//Inspection bill
	@RequestMapping(value = "/getTalabInstance.xml", method = RequestMethod.GET, produces = "application/xml")
	public @ResponseBody com.res.entity.talab.Data getTalabInstance(
			@RequestParam Long id) throws Exception {

		WorkBean workBean = commonService.fetchWorkDetailsByBillId(id);

		com.res.entity.talab.Data data = getTalabInstanceData(workBean);

		return data;
	}

	private com.res.entity.talab.Data getTalabInstanceData(WorkBean workBean) {
		
		com.res.entity.talab.Group1 group1 = new com.res.entity.talab.Group1();
		group1.setGram(workBean.getVillageBean().getVillageName());
		group1.setGramPanchayat(workBean.getGramPanchayatName());
		group1.setVikaskhand(workBean.getBlockName());

		group1.setJila(workBean.getDistrictName());
		if (workBean.getAssistantEngineer() != null) {
			group1.setAdhikariNameNPost(workBean.getAssistantEngineer()
					.getName());
		} else {
			group1.setAdhikariNameNPost("");
		}

		group1.setInspectionDate("");

		group1.setLatitude(workBean.getWorkLocationLatitude());
		group1.setLongitude(workBean.getWorkLocationLongitude());
		
		com.res.entity.talab.Group2 group2 = new com.res.entity.talab.Group2();
		group2.setKaryaName(workBean.getWorkName());

		group2.setSwikratVarsh(workBean.getAdministrationSanctionDate());
		group2.setYojnaName(workBean.getAccountHead());
		group2.setPrashaskiyaSwikratRashi(workBean.getTotalCostString());

		group2.setVartmanstithi("");

		com.res.entity.talab.Meta meta = new com.res.entity.talab.Meta();
		meta.setInstanceID("build_talab_1529913456");

		com.res.entity.talab.Group3 group3 = new com.res.entity.talab.Group3(
				"", "", "", "");

		com.res.entity.talab.Group4 group4 = new com.res.entity.talab.Group4(
				"", "", "", "", "", "");

		com.res.entity.talab.Group5 group5 = new com.res.entity.talab.Group5(
				"", "", "", "", "", "");

		com.res.entity.talab.Group6 group6 = new com.res.entity.talab.Group6(
				"", "", "", "", "", "");

		com.res.entity.talab.Group7 group7 = new com.res.entity.talab.Group7(
				"", "", "", "", "", "");

		com.res.entity.talab.Data data = new com.res.entity.talab.Data(meta,
				group1, group2, group3, group4, group5, group6, group7);
		
		return data;
	}
	
	@RequestMapping(value = "/saveTalabAnswers", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveTalabAnswers(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		try {
			commonService.saveInspectionAnwserForTalab(dataJson);  
			
		} catch (Exception e) {

			map.put("code", "500");
			map.put("message", "Internal Server Error");
			map.put("error", e.getMessage());
			logger.error("Error in saving Talab Inspection Data.." + e.getStackTrace());
			return map;
		}

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Sadak Inspection Data saved");

		logger.info("Talab Inspection Data saved..");

		return map;

	}

	///CC Inspection 
	
	
	@RequestMapping(value = "/fetchCCInspectionPendingWorks/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillDataInspectionBean> fetchCCInspectionPendingWorks(@PathVariable String userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching  CCInspectionPendingWorks");
		}

		List<BillDataInspectionBean> billBeanList = commonService.fetchCCInspectionPendingWorks(userId, RESConstants.REQUEST_STATUS_Initiated_CC_Fwd_for_Final_Inspection_ID);
		return billBeanList;
	}

	@RequestMapping(value = "/fetchCCInspectionCompletedWorks/{userId:.+}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<BillDataInspectionBean> fetchCCInspectionCompletedWorks( @PathVariable String userId, HttpServletRequest request) {

		User user = RESUtil.getUserDetail();
		if (null != user) {
			logger.info("User - " + user.getUsername() + ", Role - "
					+ user.getAuthorities() + " - Fetching  CCInspectionCompletedWorks");
		}

		List<BillDataInspectionBean> billBeanList = commonService
				.fetchCCInspectionPendingWorks(userId, 	RESConstants.REQUEST_STATUS_Final_Inspecion_Completed_ID);

		return billBeanList;
	}
	
	
	@RequestMapping(value = "/saveBhavanAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveBhavanAnswersCC(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Bhavan :" + dataJson +"");
		
		commonService.saveInspectionAnwserForBhavanCC(dataJson);
		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Bhavan Inspection Data saved");

		logger.info("Bhavan Inspection Data saved..");

		return map;

	}

	@RequestMapping(value = "/savePuliaAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> savePuliaAnswersCC(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Pulia :" + dataJson +"");
		
	   commonService.saveInspectionAnwserForPuliaCC(dataJson);
		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Pulia Inspection Data saved");

		logger.info("Pulia Inspection Data saved..");

		return map;
	}
	
	@RequestMapping(value = "/saveSadakAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveSadakAnswersCC(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Sadak :" + dataJson +"");
		
		commonService.saveInspectionAnwserForSadakCC(dataJson); 
		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Sadak Inspection Data saved");

		logger.info("Sadak Inspection Data saved..");

		return map;

	}
	@RequestMapping(value = "/saveTalabAnswersCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> saveTalabAnswersCC(
			@RequestBody DataJson dataJson) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		logger.info("Incoming Request for Talab :" + dataJson +"");
		
		commonService.saveInspectionAnwserForTalabCC(dataJson);  

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Sadak Inspection Data saved");

		logger.info("Talab Inspection Data saved..");
		return map;

	}
	
	@RequestMapping(value = "/fileUploadCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> fileUploadCC(@RequestBody FileJson fileJson) throws Exception {

		Map<String, String> map = new HashMap<String, String>();
		 
		commonService.saveFileCC(fileJson);
 
		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "File uploaded successfully !");

		logger.info("File saved..");

		return map;
	}
	
	//in Use
	@RequestMapping(value = "/imageUploadCC", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Map<String, String> imageUploadCCC(
			@RequestBody ImageJson imageJson) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
	 
		commonService.saveImageCC(imageJson);

		map.put("code", "200");
		map.put("message", "SUCCESS");
		map.put("DetailMessage", "Image saved successfully !");

		logger.info("Image Data saved..");

		return map;
	}
	
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR , reason="Error in saving Inspection Data")
	@ExceptionHandler(Exception.class)
	public void exceptionHandler() 
	{
		logger.error("Error in saving Inspection Data..");
	}
	
	///////////////////
	
	/////////////////// This is one time activity
		
	@RequestMapping(value = "/updateWorkRequisitionScriptDone", method = RequestMethod.GET)
	
	public  void updateWorkRequisitionScript() throws Exception {
	
		commonService.updateWorkRequisitionScript();
	 
		System.out.println("Completed ......updateWorkRequisitionScript");
	}
	
	
	/////////////////// This is one time activity
	
	@RequestMapping(value = "/updateWorkRequisitionScriptForNonLegacy", method = RequestMethod.GET)
	
	public  void updateWorkRequisitionScriptForNonLegacy() throws Exception {
	
		commonService.updateWorkRequisitionScriptForNonLegacy();
	 
		System.out.println("Completed ......updateWorkRequisitionScriptForNonLegacy");
	}
	
	////////this is 1 time activity 
	
@RequestMapping(value = "/updateFinancialYearForWork", method = RequestMethod.GET)
	
	public  void updateFinancialYearForWork() throws Exception {
	long start = System.currentTimeMillis();
	try {
		commonService.updateFinancialYearForWork();
	} catch(Exception e ) {
		e.printStackTrace();
		
		long elapsedTimeMillis = System.currentTimeMillis() - start;
		// Get elapsed time in minutes
		float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);
		System.out.println("in exception controller.........." + elapsedTimeMin);
		
	}
	 
		System.out.println("Completed controller ......updateFinancialYearForWork");
	}*/
		
		@RequestMapping(value = "/downloadDocumentImages/{imageName}", method = RequestMethod.GET)
		public void downloadDocuments(@PathVariable String imageName, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			DocumentUpload doc=documentRepository.findByDocumentName(imageName+".jpg");
			
	         
			String fileName = commonService.fetchDownloadFileName(doc.getDocumentId());
			
			FileInputStream fin = null;
			try {
				if (fileName != null) {
					File file = new File(fileName);
					fin = new FileInputStream(file);
					PrintWriter p = response.getWriter();
					response.setContentType("image/jpeg");
					int i = 0;
					while (i != -1) {
					    i = fin.read();
					    p.write(i);
					}
			}
			}finally {
				if(null!=fin)
					fin.close();
			}
			
				
			}
		
	
	
	
}
