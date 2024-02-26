package com.shoppingCart.util;

public class MobileFeatures implements Features {
	private String screenSize;
	private int batteryCapacity;
	private int ramInGb;
	private int storageInGb;
	private int frontCameraCapacity;
	private int rearCameraCapacity;
	private String operatingSystem;
	private Color color;
	private Country madeInCountry;
	private int madeInTheYear;
	private float weight;

/*	public MobileFeatures() {
		
		this.screenSize = InputScanner.getString("Enter The Screen Size : ").intern();
		this.batteryCapacity = InputScanner.getString("Enter The Battery Capacity : ").intern();
		this.ramInGb = InputScanner.getString("Enter The RAM Size : ").intern();
		this.storageInGb = InputScanner.getString("Enter The Storage Size : ").intern();
		this.frontCameraCapacity = InputScanner.getString("Enter The Front Camera Capacity : ").intern();
		this.rearCameraCapacity = InputScanner.getString("Enter The Rear Camera Capacity : ").intern();
		this.operatingSystem = InputScanner.getString("Enter The OS : ").intern();
		while (true) {
			String color = InputScanner.getStringInUpper("Enter The Color : ");
			if (Color.isContains(color)) {
				this.color = Color.valueOf(color);
			} else {
				System.out.println("Enter The Valid Color ..");
				continue;
			}

			break;
		}

		this.madeInCountry = Country.valueOf(InputScanner.getStringInUpper("Enter The Made In Country : "));
		this.madeInTheYear = InputScanner.getInt("Enter The Made In Year : ");
		this.weight = InputScanner.getFloat("Enter The Product Weight : ");

	}
*/

	public MobileFeatures(String screenSize, int batteryCapacity, int ramInGb, int storageInGb,
			int frontCameraCapacity, int rearCameraCapacity, String operatingSystem, Color color,
			Country madeInCountry, int madeInYear, float weight) {

		this.screenSize = screenSize;
		this.batteryCapacity = batteryCapacity;
		this.ramInGb = ramInGb;
		this.storageInGb = storageInGb;
		this.frontCameraCapacity = frontCameraCapacity;
		this.rearCameraCapacity = rearCameraCapacity;
		this.operatingSystem = operatingSystem;
		this.color = color;
		this.madeInCountry = madeInCountry;
		this.madeInTheYear = madeInYear;
		this.weight = weight;

	}

	@Override
	public void displayFeatures() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("\t"+"\t"+"Features Description");
		System.out.println("---------------------------------------------------");
		System.out.println("\t" + "Screen Size: " + screenSize);
		System.out.println("\t" + "Battery Capacity: " + batteryCapacity + "mAh");
		System.out.println("\t" + "RAM : " + ramInGb + "GB");
		System.out.println("\t" + "Storage : " + storageInGb + "GB");
		System.out.println("\t" + "Front Camera : " + frontCameraCapacity + "MP");
		System.out.println("\t" + "Rear Camera : " + rearCameraCapacity + "MP");
		System.out.println("\t" + "OS: " + operatingSystem);
		System.out.println("\t" + "Color : " + color);
		System.out.println("\t" + "Made In Country : " + madeInCountry);
		System.out.println("\t" + "Made In The Year : " + madeInTheYear);
		System.out.println("\t" + "Weight : " + weight + " Kg");
		System.out.println("---------------------------------------------------");
		System.out.println();

	}

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public int getRamInGb() {
		return ramInGb;
	}

	public void setRamInGb(int ramInGb) {
		this.ramInGb = ramInGb;
	}

	public int getStorageInGb() {
		return storageInGb;
	}

	public void setStorageInGb(int storageInGb) {
		this.storageInGb = storageInGb;
	}

	public int getFrontCameraCapacity() {
		return frontCameraCapacity;
	}

	public void setFrontCameraCapacity(int frontCameraCapacity) {
		this.frontCameraCapacity = frontCameraCapacity;
	}

	public int getRearCameraCapacity() {
		return rearCameraCapacity;
	}

	public void setRearCameraCapacity(int rearCameraCapacity) {
		this.rearCameraCapacity = rearCameraCapacity;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Country getMadeInCountry() {
		return madeInCountry;
	}

	public void setMadeInCountry(Country madeInCountry) {
		this.madeInCountry = madeInCountry;
	}

	public int getMadeInTheYear() {
		return madeInTheYear;
	}

	public void setMadeInTheYear(int madeInTheYear) {
		this.madeInTheYear = madeInTheYear;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
