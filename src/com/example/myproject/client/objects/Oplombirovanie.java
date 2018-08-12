package com.example.myproject.client.objects;

public class Oplombirovanie {
	int id;
	String date;
	int personalAcc;
	String address;
	String abonent;
	long phone;
	String add;
	String dateKont;
	int act;
	String providerDispatcher;
	
	public Oplombirovanie(int id, String date, int personalAcc, String address, String abonent, long phone, String add,
			String dateKont, int act, String providerDispatcher) {
		super();
		this.id = id;
		this.date = date;
		this.personalAcc = personalAcc;
		this.address = address;
		this.abonent = abonent;
		this.phone = phone;
		this.add = add;
		this.dateKont = dateKont;
		this.act = act;
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
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getDateKont() {
		return dateKont;
	}
	public void setDateKont(String dateKont) {
		this.dateKont = dateKont;
	}
	public int getAct() {
		return act;
	}
	public void setAct(int act) {
		this.act = act;
	}
	public String getProviderDispatcher() {
		return providerDispatcher;
	}
	public void setProviderDispatcher(String providerDispatcher) {
		this.providerDispatcher = providerDispatcher;
	}

	
}