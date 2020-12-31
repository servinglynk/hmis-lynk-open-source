
package com.servinglynk.hmis.warehouse.base.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.servinglynk.hmis.warehouse.base.dao.DeveloperCompanyDaoImpl;
import com.servinglynk.hmis.warehouse.base.dao.HmisUserDao;
import com.servinglynk.hmis.warehouse.base.dao.HmisUserDaoImpl;
import com.servinglynk.hmis.warehouse.base.service.BulkUploadService;
import com.servinglynk.hmis.warehouse.base.service.aop.SubscriptionInterceptor;
import com.servinglynk.hmis.warehouse.base.service.core.BaseServiceFactory;
import com.servinglynk.hmis.warehouse.base.service.core.BaseServiceFactoryImpl;
import com.servinglynk.hmis.warehouse.base.service.core.security.LocalApiAuthChecker;
import com.servinglynk.hmis.warehouse.base.service.impl.APIAccessServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.AccountServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ApiMethodServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.AuthorizationServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.BaseClientsServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.BaseProjectServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.BaseSearchServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.BulkUploadServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ClientConsentServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ClientDataElementsServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.DeveloperCompanyServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.FileExportServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.GenericEnrollmentServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.GlobalEnrollmentServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.GlobalHouseHoldServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.GlobalProjectServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.HMISNotificationsServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.HealthServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.PasswordResetServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.PermissionSetServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ProfileServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ProjectGroupServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ProjectSharingRuleServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ProjectSubGroupServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.ReportConfigServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.RoleServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.SessionServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.SharingRuleServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.SubscriptionEventServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.TrustedAppServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.UsernameChangeServiceImpl;
import com.servinglynk.hmis.warehouse.base.service.impl.VerificationServiceImpl;
import com.servinglynk.hmis.warehouse.common.ValidationBean;
import com.servinglynk.hmis.warehouse.core.web.interceptor.ApiAuthCheckInterceptor;
import com.servinglynk.hmis.warehouse.core.web.interceptor.ClientConsentInterceptor;
import com.servinglynk.hmis.warehouse.core.web.interceptor.EnrollmentSharingInterceptor;
import com.servinglynk.hmis.warehouse.core.web.interceptor.HslynkTraceFilter;
import com.servinglynk.hmis.warehouse.core.web.interceptor.SessionHelper;
import com.servinglynk.hmis.warehouse.core.web.interceptor.TrustedAppHelper;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableAsync
@Import({com.servinglynk.hmis.warehouse.client.config.SpringConfig.class,	
	com.servinglynk.hmis.warehouse.fileupload.config.FileUploadConfig.class})
public class BaseServiceConfig extends WebMvcConfigurerAdapter  {


	@Bean
	public TrustedAppHelper trustedAppHelper(){
		return new TrustedAppHelper();
	}
	
	
	@Bean
	public SessionHelper sessionHelper(){
		return new SessionHelper();
	}
	
	@Bean
	public LocalApiAuthChecker apiAuthChecker(){
		return new LocalApiAuthChecker();
	}
	
	 @Bean
	 public ClientConsentInterceptor clientConsentInterceptor() {
		 return new ClientConsentInterceptor();
	 }
	 
	 @Bean
	 public EnrollmentSharingInterceptor enrollmentSharingInterceptor() {
		 return new EnrollmentSharingInterceptor();
	 }
	 
	 @Bean
	 public SubscriptionEventServiceImpl subscriptionEventService() {
		 return new SubscriptionEventServiceImpl();
	 }
	 
	 
	 @Bean
	 public SubscriptionInterceptor subscriptionInterceptor() {
		 return new SubscriptionInterceptor();
	 }

	 
		 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(apiAuthCheckInterceptor());
	        registry.addInterceptor(clientConsentInterceptor()).addPathPatterns("/clients/*/enrollments/**","/clients/*/enrollments","/clients/*/veteraninfos");
	      //  registry.addInterceptor(subscriptionInterceptor());//.addPathPatterns("/clients/*/enrollments/**","/clients/*/enrollments","/clients/*/veteraninfos");
	     //   registry.addInterceptor(enrollmentSharingInterceptor()).addPathPatterns("/clients/**,/clients/enrollments/**","/clients/*/enrollments/**","/clients/*/enrollments","/clients/*/veteraninfos","/search*/*");
	        registry.addInterceptor(enrollmentSharingInterceptor());
		 }
	
	@Bean(name="serviceFactory")
	public BaseServiceFactory serviceFactory(){
		return new BaseServiceFactoryImpl();
	}

	@Bean(name="hmisUserDao")
	public HmisUserDao hmisUserDao() {
		return new HmisUserDaoImpl();
	}

	
	@Bean
	public DeveloperCompanyDaoImpl developerCompanyDao(){
		return new DeveloperCompanyDaoImpl(); 
	}
	
	@Bean
	public DeveloperCompanyServiceImpl developerCompanyService(){
		return new DeveloperCompanyServiceImpl();
	}
	
	
	
	@Bean
	public ValidationBean validationBean(){
		ValidationBean bean =new ValidationBean();
		bean.setFnMaxLength(256);
		bean.setLnMaxLength(256);
		bean.setLockoutMinutes("200");
		bean.setMaxExternalDeviceIdLength(256);
		bean.setMaxFriendlyNameLength(256);
		bean.setMaxLength(256);
		bean.setMaxPwattempts(5);
		bean.setMinLength(6);
		bean.setPwMinLength(6);
		bean.setPwMaxLength(256);
		bean.setMnMaxLength(128);
		
		
		
		return bean;
	}
	
	@Bean
	public HslynkTraceFilter traceLogger() {
		return new HslynkTraceFilter();
	}
	
	
	@Bean
	public TrustedAppServiceImpl trustedAppService(){
		return new TrustedAppServiceImpl();
	}
	
	@Bean
	public SessionServiceImpl sessionService(){
		return new SessionServiceImpl();
	}
	
	@Bean
	public AccountServiceImpl accountService(){
		return new AccountServiceImpl();
	}
	
	@Bean 
	public AuthorizationServiceImpl authorizationService(){
		return new AuthorizationServiceImpl();
	}
	

	@Bean 
	public UsernameChangeServiceImpl usernameChangeService(){
		return new UsernameChangeServiceImpl();
	}
	
	@Bean 
	public PasswordResetServiceImpl passwordResetService(){
		return new PasswordResetServiceImpl();
	}
	
	@Bean 
	public VerificationServiceImpl verificationService(){
		return new VerificationServiceImpl();
	}

	 @Bean
	 public String notificationurl(){
		 return "http://localhost:8080/notification-service/rest";
	 }
	 
	 @Bean
	 public ApiMethodServiceImpl apiMethodService(){
		 return new ApiMethodServiceImpl();
	 }
	 
	 
	 @Bean
	 public ApiAuthCheckInterceptor apiAuthCheckInterceptor(){
		 return new ApiAuthCheckInterceptor();
	 }
	 
	 @Bean
	 public RoleServiceImpl roleService(){
		 return new RoleServiceImpl();
	 }
	 
	 @Bean
	 public ProfileServiceImpl profileService(){
		 return new ProfileServiceImpl();
	 }
	 
	 @Bean
	 public PermissionSetServiceImpl permissionSetService(){
		 return new PermissionSetServiceImpl();
	 }
	
	 @Bean
	 public ProjectGroupServiceImpl projectGroupService(){
		 return new ProjectGroupServiceImpl();
	 }	 
	 
	 @Bean
	 public BulkUploadService bulkUploadService() {
		 return new BulkUploadServiceImpl();
	 }
	 @Bean
	 public ReportConfigServiceImpl reportConfigService() {
		 return new ReportConfigServiceImpl();
	 }
	 
	 @Bean
	 public FileExportServiceImpl fileExportService() {
		 return new FileExportServiceImpl();
	 }
	 
	 @Bean
	 public BaseSearchServiceImpl baseSearchService(){
		 return new BaseSearchServiceImpl();
	 }
	 
	 @Bean
	 public HealthServiceImpl healthService(){
		 return new HealthServiceImpl();
	 }
	 
	 @Bean
	 public ClientConsentServiceImpl clientConsentService(){
		 return new ClientConsentServiceImpl();
	 }
	 
	 @Bean
	 public ClientDataElementsServiceImpl clientDataElementsService(){
		 return new ClientDataElementsServiceImpl();
	 }
	 
	 @Bean
	 public APIAccessServiceImpl apiAccessService() {
		 return new APIAccessServiceImpl();
	 }
	 
	 @Bean
	 public HMISNotificationsServiceImpl hmisNotificationsService() {
		 return new HMISNotificationsServiceImpl();
	 }
	 
	 @Bean
	 public GlobalEnrollmentServiceImpl globalEnrollmentService() {
		 return new GlobalEnrollmentServiceImpl();
	 }
	 
	 @Bean
	 public GlobalProjectServiceImpl globalProjectService() {
		 return new GlobalProjectServiceImpl();
	 }
	 
	 @Bean
	 public GenericEnrollmentServiceImpl genericEnrollmentService() {
		 return new GenericEnrollmentServiceImpl();
	 }
	 
	 @Bean
	 public GlobalHouseHoldServiceImpl globalHouseHoldService() {
		 return new GlobalHouseHoldServiceImpl();
	 }
	 
	 @Bean
	 public SharingRuleServiceImpl sharingRuleService() {
		 return new SharingRuleServiceImpl();
	 }
	 
	 @Bean
	 public BaseClientsServiceImpl baseClientsService() {
		 return new BaseClientsServiceImpl();
	 }
	 
	 @Bean
	 public ProjectSharingRuleServiceImpl projectSharingRuleService() {
		 return new ProjectSharingRuleServiceImpl();
	 }
	 
	 @Bean
	 public BaseProjectServiceImpl baseProjectService() {
		 return new BaseProjectServiceImpl();
	 }
	 
	 @Bean
	 public ProjectSubGroupServiceImpl projectSubGroupService() {
		 return new ProjectSubGroupServiceImpl();
	 }
}