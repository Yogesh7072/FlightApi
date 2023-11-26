package com.example.demo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Flight;
import com.example.demo.services.ServiceI;

@RestController
public class WebController {

	private static final Logger logger = Logger.getLogger(WebController.class);

	@Autowired
	ServiceI serviceI;

	@GetMapping(value = "/getAll", produces = "application/json")
	public ResponseEntity<String> getAllData() {
		// TODO Auto-generated method stub

		List<Flight> getdata = serviceI.getdata();

		// System.out.println(allData);
		return new ResponseEntity(getdata, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllSync", produces = "application/json")
	public ResponseEntity<List<Flight>> getAllDatasync() {
		// TODO Auto-generated method stub
		logger.info(" getAllSync controller");

		List<Flight> getdataWebClientSyn = serviceI.getdataWebClientSyn();
		logger.info(" getAllSync controller  list of flight  : -  " + getdataWebClientSyn);

		System.out.println(getdataWebClientSyn);
		return new ResponseEntity(getdataWebClientSyn, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllAsync", produces = "application/json")
	public ResponseEntity<List<Flight>> getAllDataAsync() {
		// TODO Auto-generated method stub
		System.out.println("controller " + Thread.currentThread().getName());

		List<Flight> getdataWebClient = serviceI.getdataWebClient(null);
		System.out.println(getdataWebClient);
		System.out.println("controller " + Thread.currentThread().getName());

		// System.out.println(allData);
		return new ResponseEntity(getdataWebClient, HttpStatus.OK);
	}

}
