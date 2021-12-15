package com.RrsCaseStudy.model;

import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Generated;
import lombok.ToString;
@Document(collection = "reservation")

@ToString
public class Reservation {
	@Id
	@Generated
	public int rId;
	public Collection<TrainAvailability>  train;
	public String status;
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	
	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public Reservation(int rId, Collection<TrainAvailability> train, String status) {
		super();
		this.rId = rId;
		this.train = train;
		this.status = status;
	}
	public Collection<TrainAvailability> getTrain() {
		return train;
	}
	public void setTrain(Collection<TrainAvailability> train) {
		this.train = train;
	}
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
