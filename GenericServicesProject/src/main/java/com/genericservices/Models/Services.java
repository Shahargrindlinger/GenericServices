package com.genericservices.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Services")
public class Services {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
	
	@Column(name="SERVICE_NAME")
	private String service_name;
	
	@Column(name="SERVICE_TYPE")	
	private String service_type;
	
	@Column(name="SERVICE_PRICE")	
	private int service_price;
	
	public String getService_name() {
		return service_name;
	}

	public String getService_type() {
		return service_type;
	}

	public int getService_price() {
		return service_price;
	}

}


