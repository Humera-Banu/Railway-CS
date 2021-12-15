package com.example.demo.model;

import java.util.Date;

public class TrainAvailability {
	
	private int trainNo;
	private String trainName;
	private String startLocation;
	private String destination;
	private double ticketFair;
	private Date availableDate;
	public TrainAvailability() {
		
	}

	public int getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public double getTicketFair() {
		return ticketFair;
	}
	public void setTicketFair(double ticketFair) {
		this.ticketFair = ticketFair;
	}

	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	@Override
	public String toString() {
		return "TrainAvailability [trainNo=" + trainNo + ", trainName=" + trainName + ", startLocation=" + startLocation
				+ ", destination=" + destination + ", ticketFair=" + ticketFair + ", availableDate=" + availableDate
				+ "]";
	}


}
