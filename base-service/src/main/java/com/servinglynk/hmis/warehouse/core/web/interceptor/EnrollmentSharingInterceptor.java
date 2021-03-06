package com.servinglynk.hmis.warehouse.core.web.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.servinglynk.hmis.warehouse.base.service.core.BaseServiceFactory;
import com.servinglynk.hmis.warehouse.base.service.core.PropertyReaderServiceImpl;
import com.servinglynk.hmis.warehouse.common.security.LoggedInUser;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.service.exception.ResourceNotFoundException;

public class EnrollmentSharingInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	protected BaseServiceFactory serviceFactory;
	
	@Autowired protected Environment env;
	
	@Autowired private SessionHelper sessionHelper;
	
	@Autowired private PropertyReaderServiceImpl propertyReaderServiceImpl;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(!request.getContextPath().contains("clientapi")) return true;
		
/*		String value =	propertyReaderServiceImpl.readSharingRuleProperty();
		if(value==null || value.equalsIgnoreCase("false")) return true;
*/		
		Session session = sessionHelper.getSession(request);
		
		Boolean checkProjectGroup = true;

		if(request.getMethod().equalsIgnoreCase("GET")) {

			if(request.getRequestURI().contains("/search")) return true;
			if(request.getRequestURI().contains("clients") || request.getRequestURI().contains("enrollments")) {
				
				 Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
				 Map<String,String> map2 = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
				 map2.putAll(pathVariables);
				 if(map2.get("clientid")!=null) {
					 	UUID clientId = UUID.fromString(map2.get("clientid"));
						Boolean sharedClient = serviceFactory.getProjectSharingRuleService().isSharedClient(clientId,session.getAccount().getAccountId(),session.getAccount().getProjectGroup().getProjectGroupCode());
						checkProjectGroup = false;
				 }
				 if(map2.get("enrollmentid")!=null) {
					 	UUID enrollmentId = UUID.fromString(map2.get("enrollmentid"));
						Boolean sharedEnrollment = serviceFactory.getProjectSharingRuleService().isSharedEnrollment(enrollmentId,session.getAccount().getAccountId(),session.getAccount().getProjectGroup().getProjectGroupCode());
						checkProjectGroup = false;
				 }

				 	SecurityContext context =  SecurityContextHolder.getContext();
					LoggedInUser account = (LoggedInUser) context.getAuthentication().getPrincipal();
					LoggedInUser loggedInUser = new LoggedInUser(account.getUsername(),account.getProjectGroup(),account.getProfileId(),account.getUserId(), checkProjectGroup);
					Authentication authentication = new UsernamePasswordAuthenticationToken(loggedInUser,"");
					SecurityContextHolder.clearContext();
			        SecurityContextHolder.getContext().setAuthentication(authentication);				
				
			}			
		}
		
		return true;
	}
	

	public boolean preHandleOld(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if(!request.getContextPath().contains("clientapi")) return true;
		
		String value =	propertyReaderServiceImpl.readSharingRuleProperty();
		if(value==null || value.equalsIgnoreCase("false")) return true;
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("Inside sharing interceptor ");
			
			System.out.println("Schema year is "+request.getContextPath().replaceAll("/hmis-clientapi-", ""));
			
			String schemaYear = request.getContextPath().replaceAll("/hmis-clientapi-", "");
			
			Boolean isClientSearch = request.getRequestURI().contains("/search") ;
			
		
			List<UUID> enrollments  =	new ArrayList<>();
			List<UUID> clients		= new ArrayList<>();
			
			List<UUID> projects = serviceFactory.getSharingRuleService().getSharedProjects();
			if(!isClientSearch) {
				enrollments = serviceFactory.getSharingRuleService().getSharedEnrollments(projects,schemaYear);
				if(!enrollments.isEmpty()) clients = serviceFactory.getSharingRuleService().getSharedClients(enrollments, schemaYear);
			}else {
				String schemas = env.getProperty("client.api.schemas");
				if(schemas!=null) {
					String[] schemaYears = schemas.split(",");
					for(String schemayear : schemaYears) {
						List<UUID> versionEnrollments = serviceFactory.getSharingRuleService().getSharedEnrollments(projects,schemayear);
						if(!versionEnrollments.isEmpty()) {
							List<UUID> versionClients = serviceFactory.getSharingRuleService().getSharedClients(versionEnrollments, schemayear);
							clients.addAll(versionClients);
						}
						enrollments.addAll(versionEnrollments);						
					}
				}
			}
			System.out.println("Shared enrollments count "+enrollments.size()+" clients count "+clients.size());
/*			SecurityContext context =  SecurityContextHolder.getContext();
			LoggedInUser account = (LoggedInUser) context.getAuthentication().getPrincipal();
			LoggedInUser loggedInUser = new LoggedInUser(account.getUsername(),account.getProjectGroup(),account.getProfileId(),account.getUserId(), enrollments, clients);
			Authentication authentication = new UsernamePasswordAuthenticationToken(loggedInUser,"");
			SecurityContextHolder.clearContext();
	        SecurityContextHolder.getContext().setAuthentication(authentication);*/
	}
		return true;
	}

}
