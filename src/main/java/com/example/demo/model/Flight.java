package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

public class Flight {

	private int id;
	private String f;
	private String t;
	private long ticketNumber;
	private double payment;

	public Flight() {
		super();
	}

	public Flight(int id, String f, String t, long ticketNumber, double payment) {
		super();
		this.id = id;
		this.f = f;
		this.t = t;
		this.ticketNumber = ticketNumber;
		this.payment = payment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", f=" + f + ", t=" + t + ", ticketNumber=" + ticketNumber + ", payment=" + payment
				+ "]";
	}

}
