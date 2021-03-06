package com.servinglynk.hmis.warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class ViSpdatView  extends Logging {

	 private SyncHBaseProcessor syncHBaseImport;
	 private Logger logger;
	 int inserted =0;
	 int updated= 0;
	 
	 public ViSpdatView(Logger logger) throws Exception {
	        this.logger = logger;
	        this.syncHBaseImport = new SyncHBaseProcessor();
	    }
	 
	public void createHbaseTable(String tableName) {
		syncHBaseImport.createHBASETable(tableName, logger);
	}
	
	 private void processViSpdat(String hbaseTable, String projectGroupCode,Survey survey) {
	       // log.info("Start sync for table: " + postgresTable);
	        HTable htable;
	        try {
	            htable = new HTable(HbaseUtil.getConfiguration(), hbaseTable);
	            List<Response> responsesForSurvey = getResponsesForSurvey("survey", survey.getSurveyId(),projectGroupCode);
	            List<String> existingKeysInHbase = syncHBaseImport.getAllKeyRecords(htable, logger);
	            List<String> existingKeysInPostgres = new ArrayList<>();
	            
	            List<Put> putsToUpdate = new ArrayList<>();
	            List<Put> putsToInsert = new ArrayList<>();
	            List<String> putsToDelete = new ArrayList<>();
	            Put p = null;
	            String key = "";
	            String previousSubmissionId = "";
	            boolean isFirstRecord = true;
	            for(Response response : responsesForSurvey) {
	            	 Boolean deleted = response.getDeleted();
	            	 key = response.getSubmissionId();
	            	 System.out.println("Processing submission_id: " + key);
	            	 p = new Put(Bytes.toBytes(key));
	                if (deleted !=null && deleted.booleanValue()) {
	                    if (existingKeysInHbase.contains(key)) {
	                        putsToDelete.add(key);
	                        if (putsToDelete.size() > syncHBaseImport.batchSize) {
	                            syncHBaseImport.deleteDataInBatch(htable, putsToDelete, logger);
	                            putsToDelete.clear();
	                        }
	                    } else {
	                        log.debug("Skip row with key: " + key);
	                        continue;
	                    }
	                } else {
	            	if(!StringUtils.equals(key, previousSubmissionId) && !isFirstRecord) {
	            		  if(p != null) {
	            			addColumn("client_id",String.valueOf(response.getClientId()), key, p,existingKeysInHbase);
	                    	addColumn("survey_id",String.valueOf(survey.getSurveyId()), key, p,existingKeysInHbase);
	                    	addColumn("survey_date",getCreatedAtString(response.getSurveyResponseDate()), key, p,existingKeysInHbase);
	     	            		 	System.out.println("Processing Client: " + response.getClientId());
	 	     	                     htable.put(p);
	 	     	                     updated++;
	     	                existingKeysInPostgres.add(key);
	            		  }
	     	            }
	            	}
	                previousSubmissionId = response.getSubmissionId();
	                isFirstRecord =false;
	                if(StringUtils.isNotBlank(response.getResponseText())) {
	                	 System.out.println("Processing Response: " + response.getResponseText() + " for question : "+ response.getQuestionId());
	                	 addColumn(response.getQuestionId(),String.valueOf(response.getResponseText()), key, p,existingKeysInHbase);
	                	 htable.put(p);
	                }
	            }
	            htable.put(p);
	                   	           
	            logger.info("Rows to delete for table " + hbaseTable + ": " + putsToDelete.size());
	            if (putsToDelete.size() > 0) {
	                syncHBaseImport.deleteDataInBatch(htable, putsToDelete, logger);
	            }

	            logger.info("Rows to insert for table " + hbaseTable + ": " + updated);
	            if (putsToInsert.size() > 0) {
	                htable.put(putsToInsert);
	            }

	            logger.info("Rows to update for table " + hbaseTable + ": " +updated);
	            if (putsToUpdate.size() > 0) {
	                htable.put(putsToUpdate);
	            }

	        } catch (Exception ex) {
	        	 ex.printStackTrace();
	            logger.error("Exception:::"+ex.getMessage());
	        }

	        log.info("Sync done for table: " + hbaseTable);
	    }
	 
	public void addColumn(String column, String value,String key,Put p,List<String> existingKeysInHbase) {
		System.out.println(" Add Column :"+column + " value :"+value);
		 if(StringUtils.isNotBlank(value) && StringUtils.isNotBlank(column) && !StringUtils.equals("null",column)  ) {
			if(existingKeysInHbase.contains(key)) {
				System.out.println(" Add Column :"+column + " value :"+value + " update" );
				  p.add(Bytes.toBytes("CF"),
		                  Bytes.toBytes(column),
		                  Bytes.toBytes(value));
			}else {
				System.out.println(" Add Column :"+column + " value :"+value + " add column" );
				p.addColumn(Bytes.toBytes("CF"),
		                  Bytes.toBytes(column),
		                  Bytes.toBytes(value));
			}
			  
		 }
	 }
	 private String getCreatedAtString(Timestamp timestamp) {
		 String pattern = "yyyy-MM-dd HH:mm:ss";
		    SimpleDateFormat format = new SimpleDateFormat(pattern);
		    String stringDate = format.format(timestamp);
		    return stringDate;
	}

	 public Survey getSurveyById(String schemaName,String surveyId) throws Exception{
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = SyncPostgresProcessor.getConnection();
            statement = connection.prepareStatement(ViewQuery.GET_SURVEY_BY_ID);
            statement.setObject(1, UUID.fromString(surveyId));
            resultSet = statement.executeQuery();
            String projectGroupCode = null;
            String surveyName =  null;
            Timestamp createdDate =  null;
            while(resultSet.next()) {
                 projectGroupCode = resultSet.getString("project_group_code");
                 surveyName = resultSet.getString("survey_title");
                 createdDate = resultSet.getTimestamp("created_at");
            }
            Survey survey = new Survey(projectGroupCode,UUID.fromString(surveyId) , surveyName, createdDate);
            return survey;
        }catch (Exception ex){
            throw ex;
        }
    }
	 
    public static String getQuestionDisplayTextByQuestionID(String schemaName,UUID questionId) throws Exception{
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = SyncPostgresProcessor.getConnection();
            statement = connection.prepareStatement(ViewQuery.GET_DISPLAY_TEXT_FROM_QUESTION);
            statement.setObject(1, questionId);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
               return (String)resultSet.getString("display_text");
            }

        }catch (Exception ex){
            throw ex;
        }
        return null;
    }
    public List<String> getDisinctSurveys(String schemaName,String projectGroupCode) throws Exception{
        List<String> tables = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = SyncPostgresProcessor.getConnection();
            statement = connection.prepareStatement(ViewQuery.GET_DISTINCT_SURVEY);
            statement.setString(1, projectGroupCode);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                tables.add(resultSet.getString("survey_id"));
            }

        }catch (Exception ex){
            throw ex;
        }
        return tables;
    }
    
    public static List<Response> getResponsesForSurvey(String schemaName,UUID surveyId,String projectGroupCode) throws Exception{
        List<Response> responses = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;		
        Connection connection = null;
        try{
            connection = SyncPostgresProcessor.getConnection();
            statement = connection.prepareStatement(ViewQuery.GET_CLIENTS_WITH_RESPONSE);
            statement.setObject(1, surveyId);
            statement.setString(2, projectGroupCode);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
            	String submissionId = resultSet.getString("submission_id");
            	String client  = resultSet.getString("client_id");
            	String questionId =  resultSet.getString("question_id");
            	String responseText = resultSet.getString("response_text");
            	Timestamp createdAt = resultSet.getTimestamp("created_at");
            	Boolean deleted = resultSet.getBoolean("deleted");
            	Response response = new Response(submissionId, questionId, client,responseText,createdAt,deleted);
            	responses.add(response);
            }
        }catch (Exception ex){
            throw ex;
        }
        return responses;
    }
    
	public static void main(String args[]) throws Exception {
		 Logger logger = Logger.getLogger(ViSpdatView.class.getName());
		 FileAppender appender = new FileAppender();
         String appenderName = "vi-spdat-view";
         appender.setName(appenderName);
         appender.setFile("logs/" + appenderName + ".log");
         appender.setImmediateFlush(true);
         appender.setAppend(true);
         appender.setLayout(new PatternLayout());
         appender.activateOptions();
         logger.addAppender(appender);
		 Properties props = new Properties();
		 props.generatePropValues();
		 String projectGroupCodes = Properties.PROJECT_GROUP_CODE;
		 String[] split = projectGroupCodes.split(",");
		 List<String> projectGroups = new ArrayList<>(Arrays.asList(split));
		 for(String projectGroupCode : projectGroups) {
			 ViSpdatView view = new ViSpdatView(logger);
			 List<String> disinctSurveys = view.getDisinctSurveys("survey",projectGroupCode);
			 for(String surveyId : disinctSurveys) {
				 Survey survey = view.getSurveyById("survey", surveyId);
			     String tableName =survey.getSurveyName().replaceAll("[^a-zA-Z0-9]", "_").toLowerCase()+"_"+survey.getProjectGroupCode();
			     view.createHbaseTable(tableName);
				 view.processViSpdat(tableName, projectGroupCode, survey);
			  }
			}
		 }
}