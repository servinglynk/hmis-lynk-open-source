package com.servinglynk.hmis.warehouse.rest;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.Client;
import com.servinglynk.hmis.warehouse.core.model.Clients;
import com.servinglynk.hmis.warehouse.core.model.CommercialSexualExploitation;
import com.servinglynk.hmis.warehouse.core.model.CommercialSexualExploitations;
import com.servinglynk.hmis.warehouse.core.model.Connectionwithsoar;
import com.servinglynk.hmis.warehouse.core.model.Connectionwithsoars;
import com.servinglynk.hmis.warehouse.core.model.Dateofengagement;
import com.servinglynk.hmis.warehouse.core.model.Dateofengagements;
import com.servinglynk.hmis.warehouse.core.model.Disabilities;
import com.servinglynk.hmis.warehouse.core.model.DisabilitiesList;
import com.servinglynk.hmis.warehouse.core.model.DomesticViolence;
import com.servinglynk.hmis.warehouse.core.model.DomesticViolences;
import com.servinglynk.hmis.warehouse.core.model.Employment;
import com.servinglynk.hmis.warehouse.core.model.Employments;
import com.servinglynk.hmis.warehouse.core.model.Enrollment;
import com.servinglynk.hmis.warehouse.core.model.Enrollments;
import com.servinglynk.hmis.warehouse.core.model.Exit;
import com.servinglynk.hmis.warehouse.core.model.ExitPlansActions;
import com.servinglynk.hmis.warehouse.core.model.ExitPlansActionsList;
import com.servinglynk.hmis.warehouse.core.model.Exits;
import com.servinglynk.hmis.warehouse.core.model.FamilyReunification;
import com.servinglynk.hmis.warehouse.core.model.FamilyReunifications;
import com.servinglynk.hmis.warehouse.core.model.HealthInsurance;
import com.servinglynk.hmis.warehouse.core.model.HealthInsurances;
import com.servinglynk.hmis.warehouse.core.model.HealthStatus;
import com.servinglynk.hmis.warehouse.core.model.HealthStatuses;
import com.servinglynk.hmis.warehouse.core.model.HousingAssessmentDisposition;
import com.servinglynk.hmis.warehouse.core.model.HousingAssessmentDispositions;
import com.servinglynk.hmis.warehouse.core.model.IncomeAndSource;
import com.servinglynk.hmis.warehouse.core.model.IncomeAndSources;
import com.servinglynk.hmis.warehouse.core.model.LastPermanentAddress;
import com.servinglynk.hmis.warehouse.core.model.LastPermanentAddresses;
import com.servinglynk.hmis.warehouse.core.model.Medicalassistance;
import com.servinglynk.hmis.warehouse.core.model.Medicalassistances;
import com.servinglynk.hmis.warehouse.core.model.NonCashBenefit;
import com.servinglynk.hmis.warehouse.core.model.NonCashBenefits;
import com.servinglynk.hmis.warehouse.core.model.Pathstatus;
import com.servinglynk.hmis.warehouse.core.model.Pathstatuses;
import com.servinglynk.hmis.warehouse.core.model.Percentami;
import com.servinglynk.hmis.warehouse.core.model.Percentamis;
import com.servinglynk.hmis.warehouse.core.model.ProjectCompletionStatus;
import com.servinglynk.hmis.warehouse.core.model.ProjectCompletionStatuses;
import com.servinglynk.hmis.warehouse.core.model.Referralsource;
import com.servinglynk.hmis.warehouse.core.model.Referralsources;
import com.servinglynk.hmis.warehouse.core.model.Residentialmoveindate;
import com.servinglynk.hmis.warehouse.core.model.Residentialmoveindates;
import com.servinglynk.hmis.warehouse.core.model.Rhybcpstatus;
import com.servinglynk.hmis.warehouse.core.model.Rhybcpstatuses;
import com.servinglynk.hmis.warehouse.core.model.Schoolstatus;
import com.servinglynk.hmis.warehouse.core.model.Schoolstatuses;
import com.servinglynk.hmis.warehouse.core.model.Services;
import com.servinglynk.hmis.warehouse.core.model.ServicesList;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.Sexualorientation;
import com.servinglynk.hmis.warehouse.core.model.Sexualorientations;
import com.servinglynk.hmis.warehouse.core.model.VeteranInfo;
import com.servinglynk.hmis.warehouse.core.model.VeteranInfos;
import com.servinglynk.hmis.warehouse.core.model.WorstHousingSituation;
import com.servinglynk.hmis.warehouse.core.model.WorstHousingSituations;
import com.servinglynk.hmis.warehouse.core.model.YouthCriticalIssue;
import com.servinglynk.hmis.warehouse.core.model.YouthCriticalIssues;

@RestController
@RequestMapping("/clients")
public class ClientsController extends ControllerBase {
	
	
	@RequestMapping(method=RequestMethod.POST)
	@APIMapping(value="CLIENT_API_CREATE_CLIENT",checkSessionToken=true,checkTrustedApp=true)
	public Client createClient(@RequestBody Client client , HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getClientService().createClient(client, session.getAccount().getUsername());	
	}
	
	
	@RequestMapping(value="/{clientid}", method=RequestMethod.PUT)
	@APIMapping(value="CLIENT_API_UPDATE_CLIENT",checkSessionToken=true,checkTrustedApp=true)
	public Client updateClient(@PathVariable("clientid") UUID clientId ,@RequestBody Client client , HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getClientService().updateClient(client, session.getAccount().getUsername());
	}
	
	
	
	@RequestMapping(value="/{clientid}", method=RequestMethod.DELETE)
	@APIMapping(value="CLIENT_API_DELETE_CLIENT",checkSessionToken=true,checkTrustedApp=true)
	public Client deleteClient(@PathVariable("clientid") UUID clientId , HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getClientService().deleteClient(clientId, session.getAccount().getUsername());
	}
	

	@RequestMapping(value="/{clientid}", method=RequestMethod.GET)
	@APIMapping(value="CLIENT_API_GET_CLIENT_BY_ID",checkSessionToken=true,checkTrustedApp=true)
	public Client getClientById(@PathVariable("clientid") UUID clientId ,HttpServletRequest request ) throws Exception {
		return serviceFactory.getClientService().getClientById(clientId);
	}

	@RequestMapping(method=RequestMethod.GET)
	@APIMapping(value="CLIENT_API_GET_ALL_CLIENTS",checkSessionToken=true,checkTrustedApp=true)
	public Clients getAllClien(@RequestParam(value="startIndex", required=false) Integer startIndex, 
            @RequestParam(value="maxItems", required=false) Integer maxItems ,HttpServletRequest request) throws Exception {

		 if (startIndex == null) startIndex =0;
         if (maxItems == null) maxItems =30;
         
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getClientService().getAllClients(session.getAccount().getUsername(),startIndex,maxItems);
	}
	
	@RequestMapping(value="/{clientid}/enrollments",method=RequestMethod.POST)
	@APIMapping(value="CLIENT_API_CREATE_ENROLLMENT",checkSessionToken=true,checkTrustedApp=true)
	public Enrollment createEnrollment(@RequestBody Enrollment enrollment,@PathVariable("clientid") UUID clientId ,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getEnrollmentService().createEnrollment(enrollment, clientId, session.getAccount().getUsername());
	}
	
	
	@RequestMapping(value="/{clientid}/enrollments/{enrollmentid}",method=RequestMethod.PUT)	
	@APIMapping(value="CLIENT_API_UPDATE_ENROLLMENT",checkSessionToken=true,checkTrustedApp=true)
	public Enrollment updateEnrollment(@RequestBody Enrollment enrollment,@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getEnrollmentService().updateEnrollment(enrollment, clientId, session.getAccount().getUsername());
	}
	
	@RequestMapping(value="/{clientid}/enrollments/{enrollmentid}",method=RequestMethod.DELETE)
	@APIMapping(value="CLIENT_API_DELETE_ENROLLMENT",checkSessionToken=true,checkTrustedApp=true)
	public Enrollment deleteEnrollment(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,HttpServletRequest request) throws Exception {
		Session session = sessionHelper.getSession(request);
		return serviceFactory.getEnrollmentService().deleteEnrollment(enrollmentId,clientId, session.getAccount().getUsername());
	}
	
	@RequestMapping(value="/{clientid}/enrollments/{enrollmentid}",method=RequestMethod.GET)
	@APIMapping(value="CLIENT_API_GET_ENROLLMENT_BY_ID",checkSessionToken=true,checkTrustedApp=true)
	public Enrollment getClientEnrollmentById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,HttpServletRequest request) throws Exception {
		return serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId,clientId);
	}

	@RequestMapping(value="/{clientid}/enrollments",method=RequestMethod.GET)
	@APIMapping(value="CLIENT_API_GET_ALL_CLIENT_ENROLLMENTS",checkSessionToken=true,checkTrustedApp=true)
	public Enrollments getAllClientEnrollments(@PathVariable("clientid") UUID clientId,
			@RequestParam(value="startIndex", required=false) Integer startIndex, 
            @RequestParam(value="maxItems", required=false) Integer maxItems ,HttpServletRequest request) throws Exception {

		if (startIndex == null) startIndex =0;
         if (maxItems == null) maxItems =30;

		return serviceFactory.getEnrollmentService().getEnrollmentsByClientId(clientId,startIndex,maxItems);
	}
	
	
	// Exits API start
	
	
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_EXIT",checkTrustedApp=true,checkSessionToken=true)
	   public Exit createExit(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Exit exit,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        serviceFactory.getClientService().getClientById(clientId);
	//        exit.setEnrollment(enrollmentId);
	        return serviceFactory.getExitService().createExit(exit,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_EXIT",checkTrustedApp=true,checkSessionToken=true)
	   public Exit updateExit(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "exitid" ) UUID exitId,@RequestBody Exit exit,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        serviceFactory.getClientService().getClientById(clientId);
	    //    exit.setEnrollment(enrollmentId);
	        return serviceFactory.getExitService().updateExit(exit,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_EXIT",checkTrustedApp=true,checkSessionToken=true)
	   public Exit deleteExit(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "exitid" ) UUID exitId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	        serviceFactory.getClientService().getClientById(clientId);
	        serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId);
	        return serviceFactory.getExitService().deleteExit(exitId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_EXIT_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Exit getExitById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "exitid" ) UUID exitId,HttpServletRequest request) throws Exception{
	        serviceFactory.getClientService().getClientById(clientId);
	        serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId);
		   return serviceFactory.getExitService().getExitById(exitId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_EXITS",checkTrustedApp=true,checkSessionToken=true)
	   public Exits getAllEnrollmentExits(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
			   			   @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
		        serviceFactory.getClientService().getClientById(clientId);
		        serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId);
	        return serviceFactory.getExitService().getAllEnrollmentExits(enrollmentId,startIndex,maxItems); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/employments",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_EMPLOYMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Employment createEmployment(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Employment employment,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getEmploymentService().createEmployment(employment,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/employments/{employmentid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_EMPLOYMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Employment updateEmployment(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "employmentid" ) UUID employmentId,@RequestBody Employment employment,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getEmploymentService().updateEmployment(employment,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/employments/{employmentid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_EMPLOYMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Employment deleteEmployment(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "employmentid" ) UUID employmentId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getEmploymentService().deleteEmployment(employmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/employments/{employmentid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_EMPLOYMENT_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Employment getEmploymentById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "employmentid" ) UUID employmentId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getEmploymentService().getEmploymentById(employmentId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/employments",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_EMPLOYMENTS",checkTrustedApp=true,checkSessionToken=true)
	   public Employments getAllEnrollmentEmployments(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getEmploymentService().getAllEnrollmentEmployments(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	// Veteran Info API Start
	   
	   @RequestMapping(value="/{clientid}/veteraninfos",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_VETERANINFO",checkTrustedApp=true,checkSessionToken=true)
	   public VeteranInfo createVeteranInfo(@PathVariable("clientid") UUID clientId,@RequestBody VeteranInfo veteranInfo,HttpServletRequest request) throws Exception{
		   Session session = sessionHelper.getSession(request);
		   return serviceFactory.getVeteranInfoService().createVeteranInfo(veteranInfo, clientId, session.getAccount().getUsername());
	   }

	   @RequestMapping(value="/{clientid}/veteraninfos/{veteranInfoid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_VETERANINFO",checkTrustedApp=true,checkSessionToken=true)
	   public VeteranInfo updateVeteranInfo(@PathVariable("clientid") UUID clientId, @PathVariable( "veteranInfoid" ) UUID veteranInfoId,@RequestBody VeteranInfo veteranInfo,HttpServletRequest request) throws Exception{
		   Session session = sessionHelper.getSession(request);
		   return serviceFactory.getVeteranInfoService().updateVeteranInfo(veteranInfo, clientId, session.getAccount().getUsername());
	   }

	   @RequestMapping(value="/{clientid}/veteraninfos/{veteranInfoid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_VETERANINFO",checkTrustedApp=true,checkSessionToken=true)
	   public VeteranInfo deleteVeteranInfo(@PathVariable("clientid") UUID clientId,@PathVariable( "veteranInfoid" ) UUID veteranInfoId,HttpServletRequest request) throws Exception{
		   serviceFactory.getClientService().getClientById(clientId);
		   Session session = sessionHelper.getSession(request);
		   return serviceFactory.getVeteranInfoService().deleteVeteranInfo(veteranInfoId,  session.getAccount().getUsername());
	   }

	   @RequestMapping(value="/{clientid}/veteraninfos/{veteranInfoid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_VETERANINFO_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public VeteranInfo getVeteranInfoById(@PathVariable("clientid") UUID clientId,@PathVariable( "veteranInfoid" ) UUID veteranInfoId,HttpServletRequest request) throws Exception{
		   serviceFactory.getClientService().getClientById(clientId);
		   return serviceFactory.getVeteranInfoService().getVeteranInfoById(veteranInfoId);
	   }

	   @RequestMapping(value="/{clientid}/veteraninfos",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_CLIENT_VETERANINFOS",checkTrustedApp=true,checkSessionToken=true)
	   public VeteranInfos getAllEnrollmentVeteranInfos(@PathVariable("clientid") UUID clientId,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	           serviceFactory.getClientService().getClientById(clientId);
	           return serviceFactory.getVeteranInfoService().getAllClientVeteranInfos(clientId, startIndex, maxItems);
	           
	          }
	   
	// Disabilities API Start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/disabilitiess",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_DISABILITIES",checkTrustedApp=true,checkSessionToken=true)
	   public Disabilities createDisabilities(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Disabilities disabilities,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getDisabilitiesService().createDisabilities(disabilities,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/disabilitiess/{disabilitiesid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_DISABILITIES",checkTrustedApp=true,checkSessionToken=true)
	   public Disabilities updateDisabilities(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "disabilitiesid" ) UUID disabilitiesId,@RequestBody Disabilities disabilities,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getDisabilitiesService().updateDisabilities(disabilities,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/disabilitiess/{disabilitiesid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_DISABILITIES",checkTrustedApp=true,checkSessionToken=true)
	   public Disabilities deleteDisabilities(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "disabilitiesid" ) UUID disabilitiesId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDisabilitiesService().deleteDisabilities(disabilitiesId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/disabilitiess/{disabilitiesid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_DISABILITIES_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Disabilities getDisabilitiesById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "disabilitiesid" ) UUID disabilitiesId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDisabilitiesService().getDisabilitiesById(disabilitiesId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/disabilitiess",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_DISABILITIES",checkTrustedApp=true,checkSessionToken=true)
	   public DisabilitiesList getAllEnrollmentDisabilitiess(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDisabilitiesService().getAllEnrollmentDisabilitiess(enrollmentId,startIndex,maxItems); 
	   }

	   // ConnectionWithSoar API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/connectionwithsoars",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_CONNECTIONWITHSOAR",checkTrustedApp=true,checkSessionToken=true)
	   public Connectionwithsoar createConnectionwithsoar(@PathVariable("clientid") UUID clientId, 
			   	@PathVariable("enrollmentid") UUID enrollmentId ,
			   	@PathVariable("exitid") UUID exitId,
			   	@RequestBody Connectionwithsoar connectionwithsoar,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getConnectionwithsoarService().createConnectionwithsoar(connectionwithsoar,exitId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/connectionwithsoars/{connectionwithsoarid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_CONNECTIONWITHSOAR",checkTrustedApp=true,checkSessionToken=true)
	   public Connectionwithsoar updateConnectionwithsoar(@PathVariable("clientid") UUID clientId, 
			 @PathVariable("enrollmentid") UUID enrollmentId ,
			 @PathVariable("exitid") UUID exitId ,
			 @PathVariable( "connectionwithsoarid" ) UUID connectionwithsoarId,@RequestBody Connectionwithsoar connectionwithsoar,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getConnectionwithsoarService().updateConnectionwithsoar(connectionwithsoar,exitId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/connectionwithsoars/{connectionwithsoarid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_CONNECTIONWITHSOAR",checkTrustedApp=true,checkSessionToken=true)
	   public Connectionwithsoar deleteConnectionwithsoar(@PathVariable("clientid") UUID clientId,
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
			   @PathVariable( "connectionwithsoarid" ) UUID connectionwithsoarId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getConnectionwithsoarService().deleteConnectionwithsoar(connectionwithsoarId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/connectionwithsoars/{connectionwithsoarid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_CONNECTIONWITHSOAR_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Connectionwithsoar getConnectionwithsoarById(@PathVariable("clientid") UUID clientId,
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
			   @PathVariable( "connectionwithsoarid" ) UUID connectionwithsoarId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getConnectionwithsoarService().getConnectionwithsoarById(connectionwithsoarId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/connectionwithsoars",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_EXIT_CONNECTIONWITHSOARS",checkTrustedApp=true,checkSessionToken=true)
	   public Connectionwithsoars getAllEnrollmentConnectionwithsoars(@PathVariable("clientid") UUID clientId,
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getConnectionwithsoarService().getAllExitConnectionwithsoars(exitId,startIndex,maxItems); 
	   }
	   
	   // Exit Plans Actions API Start
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/exitPlansActionss",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_EXITPLANSACTIONS",checkTrustedApp=true,checkSessionToken=true)
	   public ExitPlansActions createExitPlansActions(@PathVariable("clientid") UUID clientId, 
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
			   @RequestBody ExitPlansActions exitPlansActions,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getExitPlansActionsService().createExitPlansActions(exitPlansActions,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/exitPlansActionss/{exitPlansActionsid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_EXITPLANSACTIONS",checkTrustedApp=true,checkSessionToken=true)
	   public ExitPlansActions updateExitPlansActions(@PathVariable("clientid") UUID clientId, 
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
			   @PathVariable( "exitPlansActionsid" ) UUID exitPlansActionsId,@RequestBody ExitPlansActions exitPlansActions,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getExitPlansActionsService().updateExitPlansActions(exitPlansActions,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/exitPlansActionss/{exitPlansActionsid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_EXITPLANSACTIONS",checkTrustedApp=true,checkSessionToken=true)
	   public ExitPlansActions deleteExitPlansActions(@PathVariable("clientid") UUID clientId,
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
			   @PathVariable( "exitPlansActionsid" ) UUID exitPlansActionsId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getExitPlansActionsService().deleteExitPlansActions(exitPlansActionsId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/exitPlansActionss/{exitPlansActionsid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_EXITPLANSACTIONS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public ExitPlansActions getExitPlansActionsById(@PathVariable("clientid") UUID clientId,
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
			   @PathVariable( "exitPlansActionsid" ) UUID exitPlansActionsId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getExitPlansActionsService().getExitPlansActionsById(exitPlansActionsId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/exitPlansActionss",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_EXIT_EXITPLANSACTIONS",checkTrustedApp=true,checkSessionToken=true)
	   public ExitPlansActionsList getAllEnrollmentExitPlansActionss(@PathVariable("clientid") UUID clientId,
			   @PathVariable("enrollmentid") UUID enrollmentId ,
			   @PathVariable("exitid") UUID exitId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getExitPlansActionsService().getAllExitExitPlansActionss(enrollmentId,startIndex,maxItems); 
	   }

	   
	   // Family Reunification API start
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/familyReunifications",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_FAMILYREUNIFICATION",checkTrustedApp=true,checkSessionToken=true)
	   public FamilyReunification createFamilyReunification(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId,@RequestBody FamilyReunification familyReunification,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getFamilyReunificationService().createFamilyReunification(familyReunification,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/familyReunifications/{familyReunificationid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_FAMILYREUNIFICATION",checkTrustedApp=true,checkSessionToken=true)
	   public FamilyReunification updateFamilyReunification(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "familyReunificationid" ) UUID familyReunificationId,@RequestBody FamilyReunification familyReunification,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getFamilyReunificationService().updateFamilyReunification(familyReunification,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/familyReunifications/{familyReunificationid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_FAMILYREUNIFICATION",checkTrustedApp=true,checkSessionToken=true)
	   public FamilyReunification deleteFamilyReunification(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "familyReunificationid" ) UUID familyReunificationId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId);
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getFamilyReunificationService().deleteFamilyReunification(familyReunificationId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/familyReunifications/{familyReunificationid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_FAMILYREUNIFICATION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public FamilyReunification getFamilyReunificationById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "familyReunificationid" ) UUID familyReunificationId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getFamilyReunificationService().getFamilyReunificationById(familyReunificationId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/familyReunifications",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_EXIT_FAMILYREUNIFICATION",checkTrustedApp=true,checkSessionToken=true)
	   public FamilyReunifications getAllEnrollmentFamilyReunifications(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable("exitid") UUID exitId, 
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getFamilyReunificationService().getAllEnrollmentFamilyReunifications(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // Housing Assessment Dispositions API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/housingAssessmentDispositions",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_HOUSINGASSESSMENTDISPOSITION",checkTrustedApp=true,checkSessionToken=true)
	   public HousingAssessmentDisposition createHousingAssessmentDisposition(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId,@RequestBody HousingAssessmentDisposition housingAssessmentDisposition,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getHousingAssessmentDispositionService().createHousingAssessmentDisposition(housingAssessmentDisposition,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/housingAssessmentDispositions/{housingAssessmentDispositionid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_HOUSINGASSESSMENTDISPOSITION",checkTrustedApp=true,checkSessionToken=true)
	   public HousingAssessmentDisposition updateHousingAssessmentDisposition(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "housingAssessmentDispositionid" ) UUID housingAssessmentDispositionId,@RequestBody HousingAssessmentDisposition housingAssessmentDisposition,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getHousingAssessmentDispositionService().updateHousingAssessmentDisposition(housingAssessmentDisposition,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/housingAssessmentDispositions/{housingAssessmentDispositionid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_HOUSINGASSESSMENTDISPOSITION",checkTrustedApp=true,checkSessionToken=true)
	   public HousingAssessmentDisposition deleteHousingAssessmentDisposition(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "housingAssessmentDispositionid" ) UUID housingAssessmentDispositionId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getHousingAssessmentDispositionService().deleteHousingAssessmentDisposition(housingAssessmentDispositionId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/housingAssessmentDispositions/{housingAssessmentDispositionid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_HOUSINGASSESSMENTDISPOSITION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public HousingAssessmentDisposition getHousingAssessmentDispositionById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "housingAssessmentDispositionid" ) UUID housingAssessmentDispositionId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getHousingAssessmentDispositionService().getHousingAssessmentDispositionById(housingAssessmentDispositionId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/housingAssessmentDispositions",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_EXIT_HOUSINGASSESSMENTDISPOSITION",checkTrustedApp=true,checkSessionToken=true)
	   public HousingAssessmentDispositions getAllEnrollmentHousingAssessmentDispositions(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable("exitid") UUID exitId, 
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	         serviceFactory.getExitService().getExitById(exitId);
	        return serviceFactory.getHousingAssessmentDispositionService().getAllEnrollmentHousingAssessmentDispositions(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // Project Completion Status API
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/projectCompletionStatuss",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_PROJECTCOMPLETIONSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public ProjectCompletionStatus createProjectCompletionStatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId,@RequestBody ProjectCompletionStatus projectCompletionStatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getProjectCompletionStatusService().createProjectCompletionStatus(projectCompletionStatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/projectCompletionStatuss/{projectCompletionStatusid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_PROJECTCOMPLETIONSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public ProjectCompletionStatus updateProjectCompletionStatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "projectCompletionStatusid" ) UUID projectCompletionStatusId,@RequestBody ProjectCompletionStatus projectCompletionStatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getProjectCompletionStatusService().updateProjectCompletionStatus(projectCompletionStatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/projectCompletionStatuss/{projectCompletionStatusid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_PROJECTCOMPLETIONSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public ProjectCompletionStatus deleteProjectCompletionStatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "projectCompletionStatusid" ) UUID projectCompletionStatusId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getProjectCompletionStatusService().deleteProjectCompletionStatus(projectCompletionStatusId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/projectCompletionStatuss/{projectCompletionStatusid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_PROJECTCOMPLETIONSTATUS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public ProjectCompletionStatus getProjectCompletionStatusById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId , @PathVariable("exitid") UUID exitId, @PathVariable( "projectCompletionStatusid" ) UUID projectCompletionStatusId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getProjectCompletionStatusService().getProjectCompletionStatusById(projectCompletionStatusId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/exits/{exitid}/projectCompletionStatuss",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_EXIT_PROJECTCOMPLETIONSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public ProjectCompletionStatuses getAllEnrollmentProjectCompletionStatuss(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable("exitid") UUID exitId, 
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getProjectCompletionStatusService().getAllEnrollmentProjectCompletionStatuss(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // IncomeAndSources API
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/incomeAndSources",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_INCOMEANDSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public IncomeAndSource createIncomeAndSource(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody IncomeAndSource incomeAndSource,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getIncomeAndSourceService().createIncomeAndSource(incomeAndSource,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/incomeAndSources/{incomeAndSourceid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_INCOMEANDSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public IncomeAndSource updateIncomeAndSource(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "incomeAndSourceid" ) UUID incomeAndSourceId,@RequestBody IncomeAndSource incomeAndSource,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getIncomeAndSourceService().updateIncomeAndSource(incomeAndSource,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/incomeAndSources/{incomeAndSourceid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_INCOMEANDSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public IncomeAndSource deleteIncomeAndSource(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "incomeAndSourceid" ) UUID incomeAndSourceId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getIncomeAndSourceService().deleteIncomeAndSource(incomeAndSourceId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/incomeAndSources/{incomeAndSourceid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_INCOMEANDSOURCE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public IncomeAndSource getIncomeAndSourceById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "incomeAndSourceid" ) UUID incomeAndSourceId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getIncomeAndSourceService().getIncomeAndSourceById(incomeAndSourceId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/incomeAndSources",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_INCOMEANDSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public IncomeAndSources getAllEnrollmentIncomeAndSources(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getIncomeAndSourceService().getAllEnrollmentIncomeAndSources(enrollmentId,startIndex,maxItems); 
	   }

	   // HealthInsurance API 
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthInsurances",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_HEALTHINSURANCE",checkTrustedApp=true,checkSessionToken=true)
	   public HealthInsurance createHealthInsurance(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody HealthInsurance healthInsurance,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getHealthInsuranceService().createHealthInsurance(healthInsurance,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthInsurances/{healthInsuranceid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_HEALTHINSURANCE",checkTrustedApp=true,checkSessionToken=true)
	   public HealthInsurance updateHealthInsurance(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "healthInsuranceid" ) UUID healthInsuranceId,@RequestBody HealthInsurance healthInsurance,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getHealthInsuranceService().updateHealthInsurance(healthInsurance,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthInsurances/{healthInsuranceid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_HEALTHINSURANCE",checkTrustedApp=true,checkSessionToken=true)
	   public HealthInsurance deleteHealthInsurance(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "healthInsuranceid" ) UUID healthInsuranceId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getHealthInsuranceService().deleteHealthInsurance(healthInsuranceId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthInsurances/{healthInsuranceid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_HEALTHINSURANCE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public HealthInsurance getHealthInsuranceById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "healthInsuranceid" ) UUID healthInsuranceId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getHealthInsuranceService().getHealthInsuranceById(healthInsuranceId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthInsurances",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_HEALTHINSURANCE",checkTrustedApp=true,checkSessionToken=true)
	   public HealthInsurances getAllEnrollmentHealthInsurances(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getHealthInsuranceService().getAllEnrollmentHealthInsurances(enrollmentId,startIndex,maxItems); 
	   }

	   
	   // Services API
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/servicess",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_SERVICES",checkTrustedApp=true,checkSessionToken=true)
	   public Services createServices(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Services services,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getServicesService().createServices(services,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/servicess/{servicesid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_SERVICES",checkTrustedApp=true,checkSessionToken=true)
	   public Services updateServices(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "servicesid" ) UUID servicesId,@RequestBody Services services,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getServicesService().updateServices(services,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/servicess/{servicesid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_SERVICES",checkTrustedApp=true,checkSessionToken=true)
	   public Services deleteServices(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "servicesid" ) UUID servicesId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getServicesService().deleteServices(servicesId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/servicess/{servicesid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_SERVICES_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Services getServicesById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "servicesid" ) UUID servicesId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getServicesService().getServicesById(servicesId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/servicess",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_SERVICES",checkTrustedApp=true,checkSessionToken=true)
	   public ServicesList getAllEnrollmentServicess(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getServicesService().getAllEnrollmentServicess(enrollmentId,startIndex,maxItems); 
	   }

	   // Health Status API status
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthStatuses",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_HEALTHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public HealthStatus createHealthStatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody HealthStatus healthStatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getHealthStatusService().createHealthStatus(healthStatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthStatuses/{healthStatusid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_HEALTHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public HealthStatus updateHealthStatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "healthStatusid" ) UUID healthStatusId,@RequestBody HealthStatus healthStatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getHealthStatusService().updateHealthStatus(healthStatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthStatuses/{healthStatusid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_HEALTHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public HealthStatus deleteHealthStatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "healthStatusid" ) UUID healthStatusId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getHealthStatusService().deleteHealthStatus(healthStatusId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthStatuses/{healthStatusid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_HEALTHSTATUS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public HealthStatus getHealthStatusById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "healthStatusid" ) UUID healthStatusId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getHealthStatusService().getHealthStatusById(healthStatusId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/healthStatuses",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_HEALTHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public HealthStatuses getAllEnrollmentHealthStatuss(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getHealthStatusService().getAllEnrollmentHealthStatuss(enrollmentId,startIndex,maxItems); 
	   }

	   // Commercial Sexual Exploitation API Start
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/commercialSexualExploitations",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_COMMERCIAL_SEXUAL_EXPLOITATION",checkTrustedApp=true,checkSessionToken=true)
	   public CommercialSexualExploitation createCommercialSexualExploitation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody CommercialSexualExploitation commercialSexualExploitation,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getCommercialSexualExploitationService().createCommercialSexualExploitation(commercialSexualExploitation,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/commercialSexualExploitations/{commercialSexualExploitationid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_COMMERCIAL_SEXUAL_EXPLOITATION",checkTrustedApp=true,checkSessionToken=true)
	   public CommercialSexualExploitation updateCommercialSexualExploitation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "commercialSexualExploitationid" ) UUID commercialSexualExploitationId,@RequestBody CommercialSexualExploitation commercialSexualExploitation,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getCommercialSexualExploitationService().updateCommercialSexualExploitation(commercialSexualExploitation,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/commercialSexualExploitations/{commercialSexualExploitationid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_COMMERCIAL_SEXUAL_EXPLOITATION",checkTrustedApp=true,checkSessionToken=true)
	   public CommercialSexualExploitation deleteCommercialSexualExploitation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "commercialSexualExploitationid" ) UUID commercialSexualExploitationId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getCommercialSexualExploitationService().deleteCommercialSexualExploitation(commercialSexualExploitationId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/commercialSexualExploitations/{commercialSexualExploitationid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_COMMERCIAL_SEXUAL_EXPLOITATION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public CommercialSexualExploitation getCommercialSexualExploitationById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "commercialSexualExploitationid" ) UUID commercialSexualExploitationId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getCommercialSexualExploitationService().getCommercialSexualExploitationById(commercialSexualExploitationId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/commercialSexualExploitations",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_COMMERCIAL_SEXUAL_EXPLOITATION",checkTrustedApp=true,checkSessionToken=true)
	   public CommercialSexualExploitations getAllEnrollmentCommercialSexualExploitations(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getCommercialSexualExploitationService().getAllEnrollmentCommercialSexualExploitations(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // Date Of Engagement API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/dateofengagements",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_DATEOFENGAGEMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Dateofengagement createDateofengagement(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Dateofengagement dateofengagement,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getDateofengagementService().createDateofengagement(dateofengagement,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/dateofengagements/{dateofengagementid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_DATEOFENGAGEMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Dateofengagement updateDateofengagement(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "dateofengagementid" ) UUID dateofengagementId,@RequestBody Dateofengagement dateofengagement,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getDateofengagementService().updateDateofengagement(dateofengagement,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/dateofengagements/{dateofengagementid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_DATEOFENGAGEMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Dateofengagement deleteDateofengagement(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "dateofengagementid" ) UUID dateofengagementId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDateofengagementService().deleteDateofengagement(dateofengagementId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/dateofengagements/{dateofengagementid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_DATEOFENGAGEMENT_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Dateofengagement getDateofengagementById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "dateofengagementid" ) UUID dateofengagementId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDateofengagementService().getDateofengagementById(dateofengagementId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/dateofengagements",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_DATEOFENGAGEMENT",checkTrustedApp=true,checkSessionToken=true)
	   public Dateofengagements getAllEnrollmentDateofengagements(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDateofengagementService().getAllEnrollmentDateofengagements(enrollmentId,startIndex,maxItems); 
	   }

	  // Domestic Violence API 
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/domesticViolences",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_DOMESTICVIOLENCE",checkTrustedApp=true,checkSessionToken=true)
	   public DomesticViolence createDomesticViolence(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody DomesticViolence domesticViolence,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getDomesticViolenceService().createDomesticViolence(domesticViolence,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/domesticViolences/{domesticViolenceid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_DOMESTICVIOLENCE",checkTrustedApp=true,checkSessionToken=true)
	   public DomesticViolence updateDomesticViolence(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "domesticViolenceid" ) UUID domesticViolenceId,@RequestBody DomesticViolence domesticViolence,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getDomesticViolenceService().updateDomesticViolence(domesticViolence,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/domesticViolences/{domesticViolenceid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_DOMESTICVIOLENCE",checkTrustedApp=true,checkSessionToken=true)
	   public DomesticViolence deleteDomesticViolence(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "domesticViolenceid" ) UUID domesticViolenceId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDomesticViolenceService().deleteDomesticViolence(domesticViolenceId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/domesticViolences/{domesticViolenceid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_DOMESTICVIOLENCE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public DomesticViolence getDomesticViolenceById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "domesticViolenceid" ) UUID domesticViolenceId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDomesticViolenceService().getDomesticViolenceById(domesticViolenceId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/domesticViolences",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_DOMESTICVIOLENCE",checkTrustedApp=true,checkSessionToken=true)
	   public DomesticViolences getAllEnrollmentDomesticViolences(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getDomesticViolenceService().getAllEnrollmentDomesticViolences(enrollmentId,startIndex,maxItems); 
	   }

	   
	   // Last Permanent Address API Start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/lastpermanentaddresses",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_LASTPERMANENTADDRESS",checkTrustedApp=true,checkSessionToken=true)
	   public LastPermanentAddress createLastPermanentAddress(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody LastPermanentAddress lastPermanentAddress,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getLastPermanentAddressService().createLastPermanentAddress(lastPermanentAddress,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/lastpermanentaddresses/{lastpermanentaddressid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_LASTPERMANENTADDRESS",checkTrustedApp=true,checkSessionToken=true)
	   public LastPermanentAddress updateLastPermanentAddress(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "lastpermanentaddressid" ) UUID lastPermanentAddressId,@RequestBody LastPermanentAddress lastPermanentAddress,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getLastPermanentAddressService().updateLastPermanentAddress(lastPermanentAddress,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/lastpermanentaddresses/{lastpermanentaddressid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_LASTPERMANENTADDRESS",checkTrustedApp=true,checkSessionToken=true)
	   public LastPermanentAddress deleteLastPermanentAddress(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "lastpermanentaddressid" ) UUID lastPermanentAddressId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getLastPermanentAddressService().deleteLastPermanentAddress(lastPermanentAddressId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/lastpermanentaddresses/{lastpermanentaddressid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_LASTPERMANENTADDRESS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public LastPermanentAddress getLastPermanentAddressById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "lastpermanentaddressid" ) UUID lastPermanentAddressId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getLastPermanentAddressService().getLastPermanentAddressById(lastPermanentAddressId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/lastpermanentaddresses",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_LASTPERMANENTADDRESS",checkTrustedApp=true,checkSessionToken=true)
	   public LastPermanentAddresses getAllEnrollmentLastPermanentAddresss(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getLastPermanentAddressService().getAllEnrollmentLastPermanentAddresss(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   
	   
	   // Youth Critical Issues API start
	   
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/youthcriticalissues",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_YOUTHCRITICALISSUES",checkTrustedApp=true,checkSessionToken=true)
	   public YouthCriticalIssue createYouthCriticalIssues(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody YouthCriticalIssue youthCriticalIssues,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getYouthCriticalIssuesService().createYouthCriticalIssues(youthCriticalIssues,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/youthcriticalissues/{youthCriticalIssuesid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_YOUTHCRITICALISSUES",checkTrustedApp=true,checkSessionToken=true)
	   public YouthCriticalIssue updateYouthCriticalIssues(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "youthCriticalIssuesid" ) UUID youthCriticalIssuesId,@RequestBody YouthCriticalIssue youthCriticalIssues,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getYouthCriticalIssuesService().updateYouthCriticalIssues(youthCriticalIssues,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/youthcriticalissues/{youthCriticalIssuesid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_YOUTHCRITICALISSUES",checkTrustedApp=true,checkSessionToken=true)
	   public YouthCriticalIssue deleteYouthCriticalIssues(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "youthCriticalIssuesid" ) UUID youthCriticalIssuesId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getYouthCriticalIssuesService().deleteYouthCriticalIssues(youthCriticalIssuesId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/youthcriticalissues/{youthCriticalIssuesid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_YOUTHCRITICALISSUES_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public YouthCriticalIssue getYouthCriticalIssuesById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "youthCriticalIssuesid" ) UUID youthCriticalIssuesId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getYouthCriticalIssuesService().getYouthCriticalIssuesById(youthCriticalIssuesId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/youthcriticalissues",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_YOUTHCRITICALISSUES",checkTrustedApp=true,checkSessionToken=true)
	   public YouthCriticalIssues getAllEnrollmentYouthCriticalIssuess(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getYouthCriticalIssuesService().getAllEnrollmentYouthCriticalIssuess(enrollmentId,startIndex,maxItems); 
	   }

	   // Worst Housing Situation API start
	      
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/worsthousingsituations",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_WORSTHOUSINGSITUATION",checkTrustedApp=true,checkSessionToken=true)
	   public WorstHousingSituation createWorstHousingSituation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody WorstHousingSituation worstHousingSituation,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getWorstHousingSituationService().createWorstHousingSituation(worstHousingSituation,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/worsthousingsituations/{worstHousingSituationid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_WORSTHOUSINGSITUATION",checkTrustedApp=true,checkSessionToken=true)
	   public WorstHousingSituation updateWorstHousingSituation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "worstHousingSituationid" ) UUID worstHousingSituationId,@RequestBody WorstHousingSituation worstHousingSituation,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getWorstHousingSituationService().updateWorstHousingSituation(worstHousingSituation,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/worsthousingsituations/{worstHousingSituationid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_WORSTHOUSINGSITUATION",checkTrustedApp=true,checkSessionToken=true)
	   public WorstHousingSituation deleteWorstHousingSituation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "worstHousingSituationid" ) UUID worstHousingSituationId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getWorstHousingSituationService().deleteWorstHousingSituation(worstHousingSituationId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/worsthousingsituations/{worstHousingSituationid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_WORSTHOUSINGSITUATION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public WorstHousingSituation getWorstHousingSituationById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "worstHousingSituationid" ) UUID worstHousingSituationId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getWorstHousingSituationService().getWorstHousingSituationById(worstHousingSituationId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/worsthousingsituations",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_WORSTHOUSINGSITUATION",checkTrustedApp=true,checkSessionToken=true)
	   public WorstHousingSituations getAllEnrollmentWorstHousingSituations(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getWorstHousingSituationService().getAllEnrollmentWorstHousingSituations(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	  // Sexualorientation API start
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/sexualorientations",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_SEXUALORIENTATION",checkTrustedApp=true,checkSessionToken=true)
	   public Sexualorientation createSexualorientation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Sexualorientation sexualorientation,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getSexualorientationService().createSexualorientation(sexualorientation,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/sexualorientations/{sexualorientationid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_SEXUALORIENTATION",checkTrustedApp=true,checkSessionToken=true)
	   public Sexualorientation updateSexualorientation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "sexualorientationid" ) UUID sexualorientationId,@RequestBody Sexualorientation sexualorientation,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getSexualorientationService().updateSexualorientation(sexualorientation,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/sexualorientations/{sexualorientationid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_SEXUALORIENTATION",checkTrustedApp=true,checkSessionToken=true)
	   public Sexualorientation deleteSexualorientation(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "sexualorientationid" ) UUID sexualorientationId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getSexualorientationService().deleteSexualorientation(sexualorientationId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/sexualorientations/{sexualorientationid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_SEXUALORIENTATION_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Sexualorientation getSexualorientationById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "sexualorientationid" ) UUID sexualorientationId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getSexualorientationService().getSexualorientationById(sexualorientationId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/sexualorientations",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_SEXUALORIENTATION",checkTrustedApp=true,checkSessionToken=true)
	   public Sexualorientations getAllEnrollmentSexualorientations(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getSexualorientationService().getAllEnrollmentSexualorientations(enrollmentId,startIndex,maxItems); 
	   }

	   // School status API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/schoolstatuss",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_SCHOOLSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Schoolstatus createSchoolstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Schoolstatus schoolstatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getSchoolstatusService().createSchoolstatus(schoolstatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/schoolstatuss/{schoolstatusid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_SCHOOLSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Schoolstatus updateSchoolstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "schoolstatusid" ) UUID schoolstatusId,@RequestBody Schoolstatus schoolstatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getSchoolstatusService().updateSchoolstatus(schoolstatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/schoolstatuss/{schoolstatusid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_SCHOOLSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Schoolstatus deleteSchoolstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "schoolstatusid" ) UUID schoolstatusId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getSchoolstatusService().deleteSchoolstatus(schoolstatusId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/schoolstatuss/{schoolstatusid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_SCHOOLSTATUS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Schoolstatus getSchoolstatusById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "schoolstatusid" ) UUID schoolstatusId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getSchoolstatusService().getSchoolstatusById(schoolstatusId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/schoolstatuss",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_SCHOOLSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Schoolstatuses getAllEnrollmentSchoolstatuss(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getSchoolstatusService().getAllEnrollmentSchoolstatuss(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // Medicalassistances API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/medicalassistances",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_MEDICALASSISTANCE",checkTrustedApp=true,checkSessionToken=true)
	   public Medicalassistance createMedicalassistance(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Medicalassistance medicalassistance,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getMedicalassistanceService().createMedicalassistance(medicalassistance,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/medicalassistances/{medicalassistanceid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_MEDICALASSISTANCE",checkTrustedApp=true,checkSessionToken=true)
	   public Medicalassistance updateMedicalassistance(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "medicalassistanceid" ) UUID medicalassistanceId,@RequestBody Medicalassistance medicalassistance,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getMedicalassistanceService().updateMedicalassistance(medicalassistance,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/medicalassistances/{medicalassistanceid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_MEDICALASSISTANCE",checkTrustedApp=true,checkSessionToken=true)
	   public Medicalassistance deleteMedicalassistance(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "medicalassistanceid" ) UUID medicalassistanceId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getMedicalassistanceService().deleteMedicalassistance(medicalassistanceId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/medicalassistances/{medicalassistanceid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_MEDICALASSISTANCE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Medicalassistance getMedicalassistanceById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "medicalassistanceid" ) UUID medicalassistanceId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getMedicalassistanceService().getMedicalassistanceById(medicalassistanceId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/medicalassistances",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_MEDICALASSISTANCE",checkTrustedApp=true,checkSessionToken=true)
	   public Medicalassistances getAllEnrollmentMedicalassistances(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getMedicalassistanceService().getAllEnrollmentMedicalassistances(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   
	   // Pathstatus API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/pathstatuses",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_PATHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Pathstatus createPathstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Pathstatus pathstatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getPathstatusService().createPathstatus(pathstatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/pathstatuses/{pathstatusid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_PATHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Pathstatus updatePathstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "pathstatusid" ) UUID pathstatusId,@RequestBody Pathstatus pathstatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getPathstatusService().updatePathstatus(pathstatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/pathstatuses/{pathstatusid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_PATHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Pathstatus deletePathstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "pathstatusid" ) UUID pathstatusId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getPathstatusService().deletePathstatus(pathstatusId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/pathstatuses/{pathstatusid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_PATHSTATUS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Pathstatus getPathstatusById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "pathstatusid" ) UUID pathstatusId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getPathstatusService().getPathstatusById(pathstatusId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/pathstatuses",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_PATHSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Pathstatuses getAllEnrollmentPathstatuss(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getPathstatusService().getAllEnrollmentPathstatuss(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   
	   // PercentAMI API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/percentamis",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_PERCENTAMI",checkTrustedApp=true,checkSessionToken=true)
	   public Percentami createPercentami(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Percentami percentami,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getPercentamiService().createPercentami(percentami,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/percentamis/{percentamiid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_PERCENTAMI",checkTrustedApp=true,checkSessionToken=true)
	   public Percentami updatePercentami(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "percentamiid" ) UUID percentamiId,@RequestBody Percentami percentami,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getPercentamiService().updatePercentami(percentami,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/percentamis/{percentamiid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_PERCENTAMI",checkTrustedApp=true,checkSessionToken=true)
	   public Percentami deletePercentami(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "percentamiid" ) UUID percentamiId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getPercentamiService().deletePercentami(percentamiId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/percentamis/{percentamiid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_PERCENTAMI_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Percentami getPercentamiById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "percentamiid" ) UUID percentamiId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getPercentamiService().getPercentamiById(percentamiId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/percentamis",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_PERCENTAMI",checkTrustedApp=true,checkSessionToken=true)
	   public Percentamis getAllEnrollmentPercentamis(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getPercentamiService().getAllEnrollmentPercentamis(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // Referralsource API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/referralsources",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_REFERRALSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public Referralsource createReferralsource(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Referralsource referralsource,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getReferralsourceService().createReferralsource(referralsource,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/referralsources/{referralsourceid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_REFERRALSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public Referralsource updateReferralsource(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "referralsourceid" ) UUID referralsourceId,@RequestBody Referralsource referralsource,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getReferralsourceService().updateReferralsource(referralsource,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/referralsources/{referralsourceid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_REFERRALSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public Referralsource deleteReferralsource(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "referralsourceid" ) UUID referralsourceId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getReferralsourceService().deleteReferralsource(referralsourceId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/referralsources/{referralsourceid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_REFERRALSOURCE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Referralsource getReferralsourceById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "referralsourceid" ) UUID referralsourceId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getReferralsourceService().getReferralsourceById(referralsourceId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/referralsources",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_REFERRALSOURCE",checkTrustedApp=true,checkSessionToken=true)
	   public Referralsources getAllEnrollmentReferralsources(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getReferralsourceService().getAllEnrollmentReferralsources(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
	   // Residentialmoveindates API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/residentialmoveindates",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_RESIDENTIALMOVEINDATE",checkTrustedApp=true,checkSessionToken=true)
	   public Residentialmoveindate createResidentialmoveindate(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Residentialmoveindate residentialmoveindate,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getResidentialmoveindateService().createResidentialmoveindate(residentialmoveindate,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/residentialmoveindates/{residentialmoveindateid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_RESIDENTIALMOVEINDATE",checkTrustedApp=true,checkSessionToken=true)
	   public Residentialmoveindate updateResidentialmoveindate(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "residentialmoveindateid" ) UUID residentialmoveindateId,@RequestBody Residentialmoveindate residentialmoveindate,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getResidentialmoveindateService().updateResidentialmoveindate(residentialmoveindate,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/residentialmoveindates/{residentialmoveindateid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_RESIDENTIALMOVEINDATE",checkTrustedApp=true,checkSessionToken=true)
	   public Residentialmoveindate deleteResidentialmoveindate(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "residentialmoveindateid" ) UUID residentialmoveindateId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getResidentialmoveindateService().deleteResidentialmoveindate(residentialmoveindateId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/residentialmoveindates/{residentialmoveindateid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_RESIDENTIALMOVEINDATE_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Residentialmoveindate getResidentialmoveindateById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "residentialmoveindateid" ) UUID residentialmoveindateId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getResidentialmoveindateService().getResidentialmoveindateById(residentialmoveindateId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/residentialmoveindates",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_RESIDENTIALMOVEINDATE",checkTrustedApp=true,checkSessionToken=true)
	   public Residentialmoveindates getAllEnrollmentResidentialmoveindates(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getResidentialmoveindateService().getAllEnrollmentResidentialmoveindates(enrollmentId,startIndex,maxItems); 
	   }
	   
	   // Rhybcpstatus API Start
	   
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/rhybcpstatuses",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_RHYBCPSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Rhybcpstatus createRhybcpstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody Rhybcpstatus rhybcpstatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getRhybcpstatusService().createRhybcpstatus(rhybcpstatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/rhybcpstatuses/{rhybcpstatusid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_RHYBCPSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Rhybcpstatus updateRhybcpstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "rhybcpstatusid" ) UUID rhybcpstatusId,@RequestBody Rhybcpstatus rhybcpstatus,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getRhybcpstatusService().updateRhybcpstatus(rhybcpstatus,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/rhybcpstatuses/{rhybcpstatusid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_RHYBCPSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Rhybcpstatus deleteRhybcpstatus(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "rhybcpstatusid" ) UUID rhybcpstatusId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getRhybcpstatusService().deleteRhybcpstatus(rhybcpstatusId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/rhybcpstatuses/{rhybcpstatusid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_RHYBCPSTATUS_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public Rhybcpstatus getRhybcpstatusById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "rhybcpstatusid" ) UUID rhybcpstatusId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getRhybcpstatusService().getRhybcpstatusById(rhybcpstatusId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/rhybcpstatuses",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_RHYBCPSTATUS",checkTrustedApp=true,checkSessionToken=true)
	   public Rhybcpstatuses getAllEnrollmentRhybcpstatuss(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getRhybcpstatusService().getAllEnrollmentRhybcpstatuss(enrollmentId,startIndex,maxItems); 
	   }

	   
	   // NonCashBenifits API start
	   
	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/noncashbenefits",method=RequestMethod.POST)
	   @APIMapping(value="CLIENT_API_CREATE_NONCASHBENEFIT",checkTrustedApp=true,checkSessionToken=true)
	   public NonCashBenefit createNonCashBenefit(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@RequestBody NonCashBenefit nonCashBenefit,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getNonCashBenefitService().createNonCashBenefit(nonCashBenefit,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/noncashbenefits/{nonCashBenefitid}",method=RequestMethod.PUT)
	   @APIMapping(value="CLIENT_API_UPDATE_NONCASHBENEFIT",checkTrustedApp=true,checkSessionToken=true)
	   public NonCashBenefit updateNonCashBenefit(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "nonCashBenefitid" ) UUID nonCashBenefitId,@RequestBody NonCashBenefit nonCashBenefit,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	        return serviceFactory.getNonCashBenefitService().updateNonCashBenefit(nonCashBenefit,enrollmentId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/noncashbenefits/{nonCashBenefitid}",method=RequestMethod.DELETE)
	   @APIMapping(value="CLIENT_API_DELETE_NONCASHBENEFIT",checkTrustedApp=true,checkSessionToken=true)
	   public NonCashBenefit deleteNonCashBenefit(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "nonCashBenefitid" ) UUID nonCashBenefitId,HttpServletRequest request) throws Exception{
	        Session session = sessionHelper.getSession(request); 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getNonCashBenefitService().deleteNonCashBenefit(nonCashBenefitId,session.getAccount().getUsername()); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/noncashbenefits/{nonCashBenefitid}",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_NONCASHBENEFIT_BY_ID",checkTrustedApp=true,checkSessionToken=true)
	   public NonCashBenefit getNonCashBenefitById(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,@PathVariable( "nonCashBenefitid" ) UUID nonCashBenefitId,HttpServletRequest request) throws Exception{
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getNonCashBenefitService().getNonCashBenefitById(nonCashBenefitId); 
	   }

	   @RequestMapping(value="/{clientid}/enrollments/{enrollmentid}/noncashbenefits",method=RequestMethod.GET)
	   @APIMapping(value="CLIENT_API_GET_ALL_ENROLLMENT_NONCASHBENEFIT",checkTrustedApp=true,checkSessionToken=true)
	   public NonCashBenefits getAllEnrollmentNonCashBenefits(@PathVariable("clientid") UUID clientId, @PathVariable("enrollmentid") UUID enrollmentId ,
	                       @RequestParam(value="startIndex", required=false) Integer startIndex, 
	                       @RequestParam(value="maxItems", required=false) Integer maxItems,
	                       HttpServletRequest request) throws Exception {
	           if (startIndex == null) startIndex =0;
	           if (maxItems == null) maxItems =30;
	 
	         serviceFactory.getClientService().getClientById(clientId); 
	         serviceFactory.getEnrollmentService().getEnrollmentByClientIdAndEnrollmentId(enrollmentId, clientId); 
	        return serviceFactory.getNonCashBenefitService().getAllEnrollmentNonCashBenefits(enrollmentId,startIndex,maxItems); 
	   }
	   
	   
}
