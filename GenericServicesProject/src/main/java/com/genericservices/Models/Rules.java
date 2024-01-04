package com.genericservices.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rules")
public class Rules {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
	
	@Column(name="SERVICE_NAME")
	private String service_name;
	
	@Column(name="RULE_TYPE")
	private String rule_type;
	
	@Column(name="RULE_DISCOUNT")
	private int rule_discount;

	
	public String getService_name() {
		return service_name;
	}

	public String getRule_type() {
		return rule_type;
	}

	public int getRule_discount() {
		return rule_discount;
	}

}
