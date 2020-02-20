package com.servinglynk.report.business;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.servinglynk.hive.connection.ImpalaConnection;
import com.servinglynk.hive.connection.ReportQuery;
import com.servinglynk.report.bean.Q04aDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.ProjectModel;

public class Q04aBeanMaker extends BaseBeanMaker {
	
			public static List<Q04aDataBean> getQ04aDataBeanList(String schema, String projectId,ReportData data) {
				Q04aDataBean q04aDataBean = new Q04aDataBean(); 
				q04aDataBean.setQ04acocNumber("OH-507");
				q04aDataBean.setQ04aHmisProjectType("1");
				q04aDataBean.setQ04aMethodOfTracking("0");
				if(data.isLiveMode()) {
					if(StringUtils.isNotBlank(projectId)) {
					populateProject(schema, projectId, q04aDataBean,data);
					}
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// dataBean.setHomePageEndDate(dateFormat.format(date));
				q04aDataBean.setQ04aReportStartDate(dateFormat.format(data.getReportStartDate()));
				q04aDataBean.setQ04aReportEndDate(dateFormat.format(data.getReportEndDate()));
	
				return Arrays.asList(q04aDataBean);
			}
			public static void populateProject(String schema,String id, Q04aDataBean q04aDataBean,ReportData data ) {
				ResultSet resultSet = null;
				PreparedStatement statement = null;
				Connection connection = null;
				try {
					connection = ImpalaConnection.getConnection();
					statement = connection.prepareStatement(String.format(ReportQuery.GET_PROJECT_BY_ID,schema));
					statement.setString(1, id);
					resultSet = statement.executeQuery();
					while(resultSet.next()) {
					 q04aDataBean.setQ04aProjectName(resultSet.getString("projectname"));
					 q04aDataBean.setQ04aHmisProjectType(resultSet.getString("projecttype"));
					 q04aDataBean.setQ04aProjectId(resultSet.getString("source_system_id"));
					 q04aDataBean.setQ04aMethodOfTracking(resultSet.getString("trackingmethod"));
				
					 q04aDataBean.setQ04aHmisSoftwareName("ServingLynk LLC");
				 //	 q04aDataBean.setQ04aGeoCode(q04aGeoCode);
				 //	 q04aDataBean.setQ04aVictimServiceProvider(q04aVictimServiceProvider);
				//	 q04aDataBean.setQ04aAffiliatedResidentialProject(q04aAffiliatedResidentialProject);
				//	 q04aDataBean.setQ04aProjectIdsOfAffiliation(q04aProjectIdsOfAffiliation);
				  // q04aDataBean.setQ04acocNumber(q04acocNumber);
					 String organizationId = resultSet.getString("organizationid");
					 ProjectModel project = new ProjectModel(q04aDataBean.getQ04aProjectName(), q04aDataBean.getQ04aHmisProjectType(), id, q04aDataBean.getQ04aOrgId(),resultSet.getString("trackingmethod"));
					 data.setProject(project);
					populateOranization(schema, organizationId, q04aDataBean);
				 }
					
					q04aDataBean.setQ04aProjectName("projectname");
					 q04aDataBean.setQ04aHmisProjectType("1");
					 q04aDataBean.setQ04aProjectId("source_system_id");
					 q04aDataBean.setQ04aMethodOfTracking("1");
					 q04aDataBean.setQ04aIdentityProjectId(BigInteger.ZERO);
					 q04aDataBean.setQ04aHmisProjectIdService(BigInteger.ZERO);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if (statement != null) {
						try {
							statement.close();
							//connection.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
				public static void populateOranization(String schema,String id, Q04aDataBean q04aDataBean) {
					ResultSet resultSet = null;
					PreparedStatement statement = null;
					Connection connection = null;
					try {
						connection = ImpalaConnection.getConnection();
						statement = connection.prepareStatement(String.format(ReportQuery.GET_ORG_BY_ID,schema));
						statement.setString(1, id);
						resultSet = statement.executeQuery();
//						int count = 0;
					 while(resultSet.next()) {
						 q04aDataBean.setQ04aOrgName(resultSet.getString("organizationname"));
						 q04aDataBean.setQ04aOrgId(resultSet.getString("source_system_id"));
					 }
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if (statement != null) {
							try {
								statement.close();
								//connection.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
}
