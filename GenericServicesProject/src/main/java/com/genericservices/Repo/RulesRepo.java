package com.genericservices.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.genericservices.Models.Rules;
import com.genericservices.Models.Services;

public interface RulesRepo extends JpaRepository<Rules, Integer>{
	
	@Query(value = "SELECT u FROM Rules u WHERE u.service_name = :serviceName and u.rule_type = :ruleType")
	List<Rules> getDiscountByServiceAndRule(@Param("serviceName") String serviceName, @Param("ruleType") String ruleType);	
	
	@Query(value = "SELECT u FROM Rules u WHERE u.service_name = 'ALL' and u.rule_type = :checkToday")
	List<Rules> checkForAdditionalDiscount(@Param("checkToday") String checkToday);	
}
