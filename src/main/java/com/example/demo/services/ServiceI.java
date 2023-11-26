package com.example.demo.services;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.controller.WebController;
import com.example.demo.model.Flight;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ServiceI implements ServiceImp {
	private static final Logger logger = Logger.getLogger(WebController.class);

	@Override
	public List<Flight> getdata() {
		// TODO Auto-generated method stub

		RestTemplate temp = new RestTemplate();
		ResponseEntity<String> forEntity = temp.getForEntity("http://localhost:8080/getData", String.class);

		String body = forEntity.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Flight> flights;
		try {
			flights = objectMapper.readValue(body, new TypeReference<List<Flight>>() {
			});

			for (int i = 0; i < flights.size(); i++) {

				System.out.println(flights.get(i));

				Flight fl = flights.get(i);
				System.out.println(fl.getF() + "  " + fl.getT());
				System.out.println(fl.getClass());

			}

			System.out.println("web client : --------" + body);

			System.out.println("hello yogesh");
			return flights;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Flight> fl;
	private static final Object lock = new Object();

	@Override
	public List<Flight> getdataWebClient(String responseBodys) {

		if (responseBodys == null) {
			// TODO Auto-generated method stub
			System.out.println(Thread.currentThread().getName());

			String url = "http://localhost:8080/getData";

			WebClient webClient = WebClient.create();

			webClient.get().uri(url).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
					.subscribe(responseBody -> {
						System.out.println("wwwwwwww " + fl);
						System.out.println("responseBody: " + responseBody);
						ServiceI.webClientString(responseBody);
						System.out.println("-----" + fl);
						// lock.notifyAll();
						System.out.println("------------llllllllll" + Thread.currentThread().getName());

						synchronized (lock) {
							System.out.println("------------llllllllll" + Thread.currentThread().getName());

							lock.notifyAll();
						}

						System.out.println("------------llllllllll" + Thread.currentThread().getName());

					});
			System.out.println("llllllllll" + Thread.currentThread().getName());

			System.out.println(Thread.currentThread().getName());

			synchronized (lock) {
				try {
					while (fl == null) {
						lock.wait();
					}
					return fl;

				} catch (InterruptedException e) {
					// Handle interruption
					e.printStackTrace();
					return null;
				}
			}
		} else {

			ObjectMapper ob = new ObjectMapper();

			try {
				fl = ob.readValue(responseBodys, new TypeReference<List<Flight>>() {
				});
				System.out.println("java object convert this string" + fl);

				for (int i = 0; i < fl.size(); i++) {
					System.out.println(fl.get(i));

				}
				System.out.println("123456789" + fl);
				return fl;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("--------------------------------------");
				return null;
			}

		}

	}

	private static void webClientString(String responseBody) {

		ServiceI serv = new ServiceI();
		List<Flight> getdataWebClient = serv.getdataWebClient(responseBody);
		System.out.println("getdataWebClientgetdataWebClient  " + getdataWebClient);
		System.out.println("webClientString  ::  " + responseBody);

	}

	@Override
	public List<Flight> getdataWebClientSyn() {
		// TODO Auto-generated method stub

		String url = "http://localhost:8080/getData";

		WebClient webClient = WebClient.create();
		String data = webClient.get().uri(url).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class)
				.block();
		logger.info("Syncronized string data  " + data);

		ObjectMapper obj = new ObjectMapper();
		List<Flight> readValue;
		try {
			readValue = obj.readValue(data, new TypeReference<List<Flight>>() {
			});
			logger.info("convert into java   " + readValue);

			System.out.println(readValue);
			System.out.println(data);

			return readValue;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
