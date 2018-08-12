package com.example.myproject.client.objects;

public class Pu {
	
	int id;
	String date;
	int lkschet;
	String address;
	String abonent;
	int pu;
	String place;
	int previous;
	int current;
	String providerDispatcher;
	public Pu(int id, String date, int lkschet, String address, String abonent, int pu, String place, int previous,
			int current, String providerDispatcher) {
		super();
		this.id = id;
		this.date = date;
		this.lkschet = lkschet;
		this.address = address;
		this.abonent = abonent;
		this.pu = pu;
		this.place = place;
		this.previous = previous;
		this.current = current;
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
	public int getLkschet() {
		return lkschet;
	}
	public void setLkschet(int lkschet) {
		this.lkschet = lkschet;
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
	public int getPu() {
		return pu;
	}
	public void setPu(int pu) {
		this.pu = pu;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public String getProviderDispatcher() {
		return providerDispatcher;
	}
	public void setProviderDispatcher(String providerDispatcher) {
		this.providerDispatcher = providerDispatcher;
	}

	

}
