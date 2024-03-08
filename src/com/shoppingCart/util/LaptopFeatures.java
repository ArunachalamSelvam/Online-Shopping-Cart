package com.shoppingCart.util;

public class LaptopFeatures implements Features {
	private String processor;
	private float processorSpeed;
	private String operatingSystem;
	private int ramInGb;
	private int storageInGb;
	private String screenSize;
	private String displayType;
	private Color color;
	private Country madeInCountry;
	private int madeInTheYear;
	private float weight;


	public LaptopFeatures(String processor, float processorSpeed, String operatingSystem, int ramInGb, int storageInGb,
			String screenSize, String displayType, Color color, Country madeInCountry, int madeInYear, float weight) {

		this.processor = processor;
		this.processorSpeed = processorSpeed;
		this.operatingSystem = operatingSystem;
		this.ramInGb = ramInGb;
		this.storageInGb = storageInGb;
		this.screenSize = screenSize;
		this.displayType = displayType;
		this.color = color;
		this.madeInCountry = madeInCountry;
		this.madeInTheYear = madeInYear;
		this.weight = weight;

	}

	@Override
	public String toString() {

		return processor + "," + processorSpeed + "," + operatingSystem + "," + ramInGb + "," + storageInGb + ","
				+ screenSize + "," + displayType + "," + color + "," + madeInCountry + "," + madeInTheYear + ","
				+ weight;
	}

	@Override
	public void displayFeatures() {
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("\t" + "\t" + "Features Description");
		System.out.println("---------------------------------------------------");
		System.out.println("\t" + "Processor : " + processor);
		System.out.println("\t" + "Processor Speed : " + processorSpeed + "GHz");
		System.out.println("\t" + "Operating System : " + operatingSystem);
		System.out.println("\t" + "RAM : " + ramInGb + "GB");
		System.out.println("\t" + "Storage : " + storageInGb + "GB");
		System.out.println("\t" + "Screen Size : " + screenSize);
		System.out.println("\t" + "Display Type :" + displayType);
		System.out.println("\t" + "Weight : " + weight + " Kg");
		System.out.println("---------------------------------------------------");

	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public float getProcessorSpeed() {
		return processorSpeed;
	}

	public void setProcessorSpeed(int processorSpeed) {
		this.processorSpeed = processorSpeed;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
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

	public String getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
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
