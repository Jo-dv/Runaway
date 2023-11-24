package com.mycom.enjoy.index.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.index.dto.InfoDto;
import com.mycom.enjoy.index.service.IndexServiceImpl;

@RestController
public class IndexController {
	@Autowired
	IndexServiceImpl service;
	
	@GetMapping(value = "/")
	public InfoDto infoTotalCount() {
		return service.infoTotalCount();
	}

}
