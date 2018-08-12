package com.example.myproject.client.objects;

import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Hyperlink;

public class Appeal {
	int id;
	String date;
	int personalAcc;
	String address;
	String abonent;
	long phone;
	String destination;
	String appealText;
	Anchor appealSound;
	String providerDispatcher;
	public Appeal(int id, String date, int personalAcc, String address, String abonent, long phone, String destination,
			String appealText, Anchor appealSound, String providerDispatcher) {
		super();
		this.id = id;
		this.date = date;
		this.personalAcc = personalAcc;
		this.address = address;
		this.abonent = abonent;
		this.phone = phone;
		this.destination = destination;
		this.appealText = appealText;
		this.appealSound = appealSound;
		this.providerDispatcher = providerDispatcher;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPersonalAcc() {
		return personalAcc;
	}
	public void setPersonalAcc(int personalAcc) {
		this.personalAcc = personalAcc;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAbonent() {
		return abonent;
	}
	public void setAbonent(String abonent) {
		this.abonent = abonent;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getAppealText() {
		return appealText;
	}
	public void setAppealText(String appealText) {
		this.appealText = appealText;
	}
	public Anchor getAppealSound() {
		return appealSound;
	}
	public void setAppealSound(Anchor appealSound) {
		this.appealSound = appealSound;
	}
	public String getProviderDispatcher() {
		return providerDispatcher;
	}
	public void setProviderDispatcher(String providerDispatcher) {
		this.providerDispatcher = providerDispatcher;
	}




	
}