package com.genericservices.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genericservices.Models.Rules;
import com.genericservices.Models.Services;
import com.genericservices.Repo.RulesRepo;
import com.genericservices.Repo.ServicesRepo;
import com.genericservices.services.ClientServices;

@RestController
public class ServicesController {
	
	
	@Autowired
	private final ClientServices clientServices;
	
	@Autowired
	private ServicesRepo servicesRepo;
	
	@Autowired
	private RulesRepo rulesRepo;
	
	public ServicesController(ClientServices clientServices) {
		this.clientServices = clientServices;		
	}
	
	@GetMapping(value = "/getFinalPriceByServiceAndTypeAndRuleName")
	public List<Services> getFinalPriceByServiceAndTypeAndRuleName(@RequestParam("serviceName") String serviceName, @RequestParam("serviceType") String serviceType, @RequestParam("ruleType") String ruleType) {
		double additionalDiscount = 0;
		double price = 0;
		double discount = 0;
		
		List<Services> services = servicesRepo.getPriceByServiceAndType(serviceName, serviceType);	
		if (services == null || services.size() <= 0) {
		    throw new RuntimeException("No such Service");
		}
		List<Rules> rules = rulesRepo.getDiscountByServiceAndRule(serviceName, ruleType);
		Calendar date = Calendar.getInstance(); 
        String today = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        System.out.println("Today is: "+today);
		List<Rules> additionalRules = rulesRepo.checkForAdditionalDiscount(today);

		price = services.get(0).getService_price();
		System.out.println("The original Price for "+serviceName+" '"+serviceType+"' is:"+price);		
		
		if (rules != null && rules.size() > 0) {
			discount = rules.get(0).getRule_discount();
			System.out.println("Discount for "+serviceName+ " is:"+discount +"% due to "+ruleType);		
		}

		if (additionalRules != null && additionalRules.size() > 0) {
			additionalDiscount = additionalRules.get(0).getRule_discount();
   			System.out.println("You get an additional discount of "+ additionalDiscount +"% for "+serviceName+ " as today it's "+today);		
		}
        clientServices.calculatefinalPrice(price, discount, additionalDiscount);
		return services;
	}	

	//Get the price from Services table per service name and service type
	@GetMapping(value = "/getPriceByServiceAndType")
	public List<Services> getPriceByServiceAndType(@RequestParam("serviceName") String serviceName, @RequestParam("serviceType") String serviceType) {
		List<Services> services = servicesRepo.getPriceByServiceAndType(serviceName, serviceType);	
		return services;
	}	
	
	//Get the discount from Rules table per service name and rule type
	@GetMapping(value = "/getDiscountByServiceAndRule")
	public List<Rules> getDiscountByServiceAndRule(@RequestParam("serviceName") String serviceName, @RequestParam("ruleType") String ruleType) {
		List<Rules> rules = rulesRepo.getDiscountByServiceAndRule(serviceName, ruleType);	
		return rules;
	}	
	
	@GetMapping(value="/services")
	public List<Services> getServices() {
		return servicesRepo.findAll();
	}
	
	@GetMapping(value="/rules")
	public List<Rules> getRules() {
		return rulesRepo.findAll();
	}
	

}
