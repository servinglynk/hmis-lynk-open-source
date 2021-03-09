package com.servinglynk.hmis.warehouse.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.ReportConfig;
import com.servinglynk.hmis.warehouse.core.model.ReportConfigs;
import com.servinglynk.hmis.warehouse.core.model.Session;
import com.servinglynk.hmis.warehouse.core.model.exception.AccessDeniedException;
import com.servinglynk.hmis.warehouse.service.AWSService;


@RestController
@RequestMapping("/reports")
public class ReportConfigController extends ControllerBase {
	@Autowired
	AWSService awsService;
	private static final int BUFFER_SIZE = 4096;
	
		@RequestMapping(method = RequestMethod.POST)
		@APIMapping(value="CREATE_REPORT_CONFIG",checkSessionToken=true, checkTrustedApp=true)
		public ReportConfig createReport(@RequestBody ReportConfig reportConfig,HttpServletRequest request) throws Exception {			
		 Session session = sessionHelper.getSession(request);
		 String username = session.getAccount().getUsername();
		 ReportConfig createReportConfig = serviceFactory.getReportConfigService().createReportConfig(reportConfig,username);
		 return createReportConfig;
		}

	 	@RequestMapping(method = RequestMethod.GET)
		@APIMapping(value="GET_REPORT_CONFIG_BY_USER",checkSessionToken=true, checkTrustedApp=true)
		public ReportConfigs getReports(@RequestParam(value="startIndex", required=false) Integer startIndex, 
                @RequestParam(value="maxItems", required=false) Integer maxItems,HttpServletRequest request) throws Exception {			
		 Session session = sessionHelper.getSession(request);
		 String username = session.getAccount().getUsername();
		 return  serviceFactory.getReportConfigService().getReportConfigsByUser(startIndex, maxItems, username);
		}

		@RequestMapping(value="/{reportConfigId}",method=RequestMethod.GET)
		@APIMapping(value="GET_REPORT_CONFIG_BY_ID",checkSessionToken=true, checkTrustedApp=true)
		public ReportConfig getReportById(@PathVariable(value="reportConfigId") Long reportConfigId, 
                HttpServletRequest request) throws Exception {			
		 Session session = sessionHelper.getSession(request);
		 String username = session.getAccount().getUsername();
		 return  serviceFactory.getReportConfigService().getReportConfigById(reportConfigId);
		}
		
		@RequestMapping(value="/{reportConfigId}",method=RequestMethod.PUT)
		@APIMapping(value="UPDATE_REPORT_CONFIG",checkSessionToken=true, checkTrustedApp=true)
		public ReportConfig updateById(@PathVariable(value="reportConfigId") Long reportConfigId, 
                HttpServletRequest request, @RequestBody ReportConfig reportConfig) throws Exception {			
		 Session session = sessionHelper.getSession(request);
		 String username = session.getAccount().getUsername();
		 return  serviceFactory.getReportConfigService().updateReportConfig(reportConfigId, reportConfig, username);
		}
		
		@RequestMapping(value="/downloadZIP/{reportConfigId}",method = RequestMethod.GET)
		@APIMapping(value="GET_REPORT_CONFIG_BY_USER",checkSessionToken=true, checkTrustedApp=true)
		public void downloadZIPFile(@PathVariable(value="reportConfigId") Long reportConfigId,
				HttpServletRequest request, HttpServletResponse response) {
			try {
		        Session session = sessionHelper.getSession(request);
		        String bucketName = session.getAccount().getProjectGroup().getBucketName();
		        ReportConfig reportConfigById = serviceFactory.getReportConfigService().getReportConfigById(reportConfigId);
		        if(reportConfigById != null && StringUtils.equals(reportConfigById.getProjectGroupCode(), session.getAccount().getProjectGroup().getProjectGroupCode())) {
		        	   String downloadFile = awsService.downloadFile1(bucketName, reportConfigById.getReportType()+"/"+reportConfigId+".zip", null);
				        InputStream inputStream = new FileInputStream(downloadFile);
				        response.setContentType("application/force-download");
				        response.setHeader("Content-Disposition", "attachment; filename="+reportConfigId+".zip"); 
				        response.setHeader("x-filename",reportConfigId+".zip"); 
				        response.setHeader("Content-Disposition", "attachment; filename="+reportConfigId+".zip"); 
				        response.setHeader("x-filename",reportConfigId+".zip"); 
				        IOUtils.copy(inputStream, response.getOutputStream());
				        response.flushBuffer();
				        inputStream.close();
		        }
			}catch (Exception e){
		        logger.debug("Request could not be completed at this moment. Please try again.");
		        e.printStackTrace();
		    }
		}
		
		@RequestMapping(value="/download/{reportConfigId}/{type}",method = RequestMethod.POST)
		@APIMapping(value="GET_REPORT_CONFIG_BY_USER",checkSessionToken=true, checkTrustedApp=true)
		public ResponseEntity<byte[]> downloadPDFFile(@PathVariable(value="reportConfigId") Long reportConfigId,@PathVariable(value="type") String type,
				HttpServletRequest request, HttpServletResponse response) {
			try {
		        Session session = sessionHelper.getSession(request);
		        String bucketName = session.getAccount().getProjectGroup().getBucketName();
		        ReportConfig reportConfigById = serviceFactory.getReportConfigService().getReportConfigById(reportConfigId);
		        if(reportConfigById != null && StringUtils.equals(reportConfigById.getProjectGroupCode(), session.getAccount().getProjectGroup().getProjectGroupCode())) {
		        	InputStream inputStream = awsService.downloadFile(bucketName, reportConfigById.getReportType()+"/"+reportConfigId+"."+type, null);
			        response.setContentType("application/force-download");
			        response.setHeader("Content-Disposition", "attachment; filename="+reportConfigId+"."+type); 
			        response.setHeader("x-filename",reportConfigId+"."+type); 
			        IOUtils.copy(inputStream, response.getOutputStream());
			        response.flushBuffer();
			        inputStream.close();
		        }else {
		        	throw new AccessDeniedException("The User does not have access to download the report");
		        }
		        
		    } catch (Exception e){
		        logger.debug("Request could not be completed at this moment. Please try again.");
		        e.printStackTrace();
		    }
			 return null;
		}
}
