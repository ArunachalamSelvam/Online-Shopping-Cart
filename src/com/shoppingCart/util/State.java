package com.shoppingCart.util;

public enum State {

	ANDAMAN_AND_NICOBAR_ISLANDS, ANDHRA_PRADESH, ARUNACHAL_PRADESH, ASSAM, BIHAR, CHANDIGARH, CHHATTISGARH,
	DADRA_AND_NAGAR_HAVELI_AND_DAMAN_AND_DIU, DELHI, GOA, GUJARAT, HARYANA, HIMACHAL_PRADESH, JAMMU_AND_KASHMIR,
	JHARKHAND, KARNATAKA, KERALA, LADAKH, LAKSHADWEEP, MADHYA_PRADESH, MAHARASHTRA, MANIPUR, MEGHALAYA, MIZORAM,
	NAGALAND, ODISHA, PUDUCHERRY, PUNJAB, RAJASTHAN, SIKKIM, TAMIL_NADU, TELANGANA, TRIPURA, UTTAR_PRADESH, UTTARAKHAND,
	WEST_BENGAL;

	public static boolean isContains(String state) {

		for (State states : State.values()) {
			if (states.name().equals(state)) {
				return true;
			}
		}
		return false;
	}
	
	public static State getState() {
		while(true) {
			String state = InputScanner.getStringInUpper("Enter Your State : ");
			if(State.isContains(state)) {
				return State.valueOf(state);
				
			}
			else {
				System.out.println("Enter The Valid State..");
				continue;
			}
		}
	}
}
