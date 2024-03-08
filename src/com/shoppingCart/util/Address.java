package com.shoppingCart.util;

public class Address {
	
	private String doorNo;
	private String streetName;
	private String village;
	private District district;
	private State state;
	private Country country;
	private int pincode;
	
	
	public Address(String doorNo, String streetName, String village, District district, State state, int pincode) {
		
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.village = village;
		this.district = district;
		this.state = state;
		this.country = Country.INDIA;
		this.pincode = pincode;
	}


	@Override
	public String toString() {
		  
		        return 
		                "\n"+"\t"+"doorNo='" + doorNo + '\'' +
		                ", "+"\n"+"\t"+"streetName='" + streetName + '\'' +", "+"\n"+"\t"+
		                "village='" + village + '\'' + ", "+"\n"+"\t"+
		                "district='" + district + '\'' + ", "+"\n"+"\t"+
		                "state='" + state + '\'' + ", "+"\n"+"\t"+
		                "country='" + country + '\'' +  ", "+"\n"+"\t"+
		                "pincode=" + pincode ;
		    
	}

	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
	
}
