package com.company.myproject.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class Lookups {
	private static final Logger LOGGER = Logger.getLogger(Lookups.class);

	private Map<String, Object> lookupsMap = new HashMap<String, Object>();

	
	@PostConstruct
    public void init() {
		LOGGER.info("Initializing Lookups.");
		
	}
	
	
	public Map<String, Object> getLookupsMap() {
		return lookupsMap;
	}

	public void setLookupsMap(Map<String, Object> lookupsMap) {
		this.lookupsMap = lookupsMap;
	}


	
	
	
}
