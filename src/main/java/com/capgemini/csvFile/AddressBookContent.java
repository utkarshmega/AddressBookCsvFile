package com.capgemini.csvFile;

import com.opencsv.bean.CsvBindByName;

public class AddressBookContent {
	
	@CsvBindByName
	String firstName;
	
	@CsvBindByName
	String lastName;
	
	@CsvBindByName
	String address;
	
	@CsvBindByName
	String city;
	
	@CsvBindByName
	int zip;
	
	@CsvBindByName
	String phNo;
	
	@CsvBindByName
	String email;
	
	@CsvBindByName
	String state;

	public AddressBookContent(String fname, String lname, String add, String city, String state, int zip, String mobNo,
			String mail) {
		this.firstName = fname;
		this.lastName = lname;
		this.address = add;
		this.city = city;
		this.zip = zip;
		this.phNo = mobNo;
		this.email = mail;
		this.state = state;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public int getZip() {
		return zip;
	}

	public String getPhoneNumber() {
		return phNo;
	}

	public String getEmail() {
		return email;
	}

	public String getState() {
		return state;
	}

	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	public void setLastName(String lname) {
		this.lastName = lname;
	}

	public void setAddress(String addr) {
		this.address = addr;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(int zipc) {
		this.zip = zipc;
	}

	public void setPhoneNumber(String phn) {
		this.phNo = phn;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toString() {
		return "First name : " + firstName + "\nLast name : " + lastName + "\nAddress : " + address + "\nCity : " + city
				+ "\nState : " + state + "\nZip : " + zip + "\nPhone num : " + phNo + "\nEmail : " + email+"\n";
	}
}