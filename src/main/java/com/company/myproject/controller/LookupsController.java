package com.company.myproject.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.myproject.util.Lookups;

@Controller
@RequestMapping(value = "/lookups")
public class LookupsController {
	private static final Logger LOGGER = Logger.getLogger(LookupsController.class);
	@Autowired private Lookups lookups;
	
	@RequestMapping (value = "all", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAll() {
		return lookups.getLookupsMap();
	}
	
}
