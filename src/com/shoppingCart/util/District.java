package com.shoppingCart.util;

public enum District {
	
	ARIYALUR,
    CHENGALPATTU,
    CHENNAI,
    COIMBATORE,
    CUDDALORE,
    DHARMAPURI,
    DINDIGUL,
    ERODE,
    KALLAKURICHI,
    KANCHEEPURAM,
    KANNIYAKUMARI,
    KARUR,
    KRISHNAGIRI,
    MADURAI,
    NAGAPATTINAM,
    NAMAKKAL,
    NILGIRIS,
    PERAMBALUR,
    PUDUKKOTTAI,
    RAMANATHAPURAM,
    RANIPET,
    SALEM,
    SIVAGANGA,
    TENKASI,
    THANJAVUR,
    THENI,
    THIRUVALLUR,
    THIRUVANNAMALAI,
    THIRUVARUR,
    TIRUCHIRAPPALLI,
    TIRUNELVELI,
    TIRUPATHUR,
    TIRUPPUR,
    TIRUVANNAMALAI,
    TUTICORIN,
    VELLORE,
    VILUPPURAM,
    VIRUDHUNAGAR;
	
	public static boolean isContains(String district) {
		
		for(District districts : District.values()) {
			if(districts.name().equals(district)) {
				return true;
			}
		}
		return false;
	}
	
	public static District getDistrict() {
		while(true) {
			String district = InputScanner.getStringInUpper("Enter Your District : ");
			if(District.isContains(district)) {
				return District.valueOf(district);
				
			}
			else {
				System.out.println("Enter The Valid District..");
				continue;
			}
		}
	}
	
}
