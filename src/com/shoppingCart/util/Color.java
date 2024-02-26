package com.shoppingCart.util;

public enum Color {

	BLACK, WHITE, SILVER, GRAY, GOLD, ROSE_GOLD, SPACE_GRAY, MIDNIGHT_BLACK, PEARL_WHITE, PLATINUM, CHAMPAGNE,
	SAPPHIRE_BLUE, RUBY_RED, EMERALD_GREEN, OCEAN_BLUE, SUNSET_ORANGE, LILAC_PURPLE, TITANIUM_GRAY, BRONZE, COPPER,
	JET_BLACK, MINT_GREEN, AMETHYST_PURPLE, CORAL_BLUE, LAVA_RED, NEON_YELLOW, INDIGO_BLUE, TUNGSTEN, COBALT_BLUE,
	CERAMIC_WHITE, CRIMSON_RED, FOREST_GREEN, MARBLE_WHITE;
	
	public static boolean isContains(String color) {

		for (Color colors : Color.values()) {
			if (colors.name().equals(color)) {
				return true;
			}
		}
		return false;
	}
	
	public static Color getColors() {
		while(true) {
			String color = InputScanner.getStringInUpper("Enter Your State : ");
			if(isContains(color)) {
				return Color.valueOf(color);
				
			}
			else {
				System.out.println("Enter The Valid Color..");
				continue;
			}
		}
	}
}
