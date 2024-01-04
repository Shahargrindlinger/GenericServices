package com.genericservices.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.genericservices.Models.Services;

public interface ServicesRepo extends JpaRepository<Services, Integer>{
	
	@Query("SELECT u FROM Services u WHERE u.service_name = :serviceName and u.service_type = :serviceType")
	List<Services> getPriceByServiceAndType(@Param("serviceName") String serviceName, @Param("serviceType") String serviceType);
	
}