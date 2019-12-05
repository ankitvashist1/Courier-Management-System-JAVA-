package com.cms.models;

public class Address {
	
	String addressLine;

	String city;
	
	String state;
	
	String pin;
	
	String country;
	
	String mobilenumber;

	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(String addressLine, String city, String state, String pin, String country,String mobilenumber) {
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.country = country;
		this.mobilenumber = mobilenumber;
	}


	public String getAddressLine() {
		return addressLine;
	}


	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getMobilenumber() {
		return mobilenumber;
	}


	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	@Override
	public String toString() {
		return "Address [addressLine=" + addressLine + ", city=" + city + ", state=" + state + ", pin=" + pin
				+ ", country=" + country + ", mobilenumber=" + mobilenumber + "]";
	}

}
