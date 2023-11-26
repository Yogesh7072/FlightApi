package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Flight;

public interface ServiceImp {

	public List<Flight> getdata();

//	public List<Flight> getdataWebClient();
	
	public List<Flight> getdataWebClientSyn();

	List<Flight> getdataWebClient(String s);

}
