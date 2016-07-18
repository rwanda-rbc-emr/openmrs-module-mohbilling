/**
 * 
 */
package org.openmrs.module.mohbilling.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.api.context.Context;
import org.openmrs.module.mohbilling.businesslogic.ConsommationUtil;
import org.openmrs.module.mohbilling.businesslogic.GlobalBillUtil;
import org.openmrs.module.mohbilling.businesslogic.InsurancePolicyUtil;
import org.openmrs.module.mohbilling.model.Consommation;
import org.openmrs.module.mohbilling.model.GlobalBill;
import org.openmrs.module.mohbilling.model.InsurancePolicy;
import org.openmrs.module.mohbilling.service.BillingService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

/**
 * @author emr
 *
 */
public class MohBillingConsommationListController extends	ParameterizableViewController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.ParameterizableViewController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		Integer globalBillId = Integer.valueOf(request.getParameter("globalBillId"));
		String  insuranceCardNo =request.getParameter("ipCardNumber");
		InsurancePolicy ip = Context.getService(BillingService.class).getInsurancePolicyByCardNo(insuranceCardNo);
		GlobalBill globalBill = GlobalBillUtil.getGlobalBill(globalBillId);
		
		List<Consommation> consommations = ConsommationUtil.getConsommationsByGlobalBill(globalBill);
		mav.addObject("insurancePolicy", ip);
		mav.addObject("globalBill", globalBill);
		mav.addObject("consommations", consommations);		
		mav.setViewName(getViewName());	
		
		return mav;
	}
	

	

}