package RrsCaseStudy.model;

import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Generated;
import lombok.ToString;
@Document(collection = "reservation")

public class Reservation {
	@Id
	@Generated
	public int rId;
	public int trainNo;
	private String startLocation;
	public String destination;
	private double ticketFair;
	private int passengers;
	public String status;
	public double totalTicketFair;
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	
	public Reservation(int rId, int trainNo, String startLocation, String destination, double ticketFair,
			int passengers, String status, double totalTicketFair) {
		super();
		this.rId = rId;
		this.trainNo = trainNo;
		this.startLocation = startLocation;
		this.destination = destination;
		this.ticketFair = ticketFair;
		this.passengers = passengers;
		this.status = status;
		this.totalTicketFair = totalTicketFair;
	}
	
	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public int getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
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

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalTicketFair() {
		return this.totalTicketFair;
	}

	public void setTotalTicketFair(double totalTicketFair) {
		this.totalTicketFair = totalTicketFair;
	}

	@Override
	public String toString() {
		return "Reservation [rId=" + rId + ", trainNo=" + trainNo + ", startLocation=" + startLocation
				+ ", destination=" + destination + ", ticketFair=" + ticketFair + ", passengers=" + passengers
				+ ", status=" + status + ", TotalTicketFair=" + totalTicketFair + "]";
	}
	
	
}
