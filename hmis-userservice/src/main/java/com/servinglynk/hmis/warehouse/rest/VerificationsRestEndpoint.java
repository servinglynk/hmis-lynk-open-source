package com.servinglynk.hmis.warehouse.rest;


import static com.servinglynk.hmis.warehouse.common.Constants.USER_SERVICE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.servinglynk.hmis.warehouse.annotations.APIMapping;
import com.servinglynk.hmis.warehouse.core.model.Verification;


@RestController
@RequestMapping("/verifications")
public class VerificationsRestEndpoint extends RestEndpointBase {

	
	@RequestMapping(value = "/{verificationToken}/status", method = RequestMethod.PUT)
	@APIMapping(value="USR_UPDATE_VERIFICATION_STATUS",checkSessionToken=false, checkTrustedApp=true)
	public Verification updateVerificationStatus(@PathVariable("verificationToken") String verificationToken, @RequestBody Verification verification,
			HttpServletRequest request) throws Exception {
		
		verification.setToken(verificationToken);
		serviceFactory.getVerificationService().updateVerificationStatus(verification, USER_SERVICE);
		
		Verification returnVerification = new Verification();
		returnVerification.setStatus(verification.getStatus());
		returnVerification.setToken(verificationToken);

		return returnVerification;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@APIMapping(value="USR_CREATE_VERIFICATION",checkSessionToken=false, checkTrustedApp=true)
	public Verification createVerification(@RequestBody Verification verification,
			HttpServletRequest request) throws Exception {
		
		 
		   	serviceFactory.getVerificationService().createVerification(verification);
		
			Verification returnVerification = new Verification();
			return returnVerification;
	}

}
