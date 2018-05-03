package com.ayush.UserReg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	public static enum UseridPref {
		USERNAME, EMAIL, PHOME;
	}
	
	public static enum CommunicationMethod {
		EMAIL, SMS;
	}
	
	private String id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String country;
	private String address;
	@JsonProperty(value="contactMethodPreference")
	private CommunicationMethod prefContactMethod;
	@JsonProperty(value="useridPreference")
	private UseridPref idPref;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public CommunicationMethod getPrefContactMethod() {
		return prefContactMethod;
	}
	public void setPrefContactMethod(CommunicationMethod prefContactMethod) {
		this.prefContactMethod = prefContactMethod;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UseridPref getIdPref() {
		return idPref;
	}
	public void setIdPref(UseridPref idPref) {
		this.idPref = idPref;
	}
}
