package com.yrgo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Calls")
public class Call {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String customerId;

	private LocalDateTime timeAndDate;

	private String notes;
	public Call() {}

	public Call(String notes){
		// this defaults to a timestamp of "now"
		this (notes, LocalDateTime.now());
	}

	public Call(String notes, LocalDateTime timestamp){
		// this defaults to a timestamp of "now"
		this.timeAndDate = timestamp;
		this.notes = notes;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String toString()	{
		return this.timeAndDate + " : " + this.notes;
	}

	public LocalDateTime getTimeAndDate() {
		return timeAndDate;
	}

	public void setTimeAndDate(LocalDateTime timeAndDate) {
		this.timeAndDate = timeAndDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


}
