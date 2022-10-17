package com.qa.woo.ecommerce.ui;

public class DataUtil {

	public static boolean convertToBoolean(String inputString) {
		boolean booleanValue = false;
		if(inputString.equals("Y"))
			booleanValue = true;
		else if(inputString.equals("N"))
			booleanValue = false;
		return booleanValue;
	}

}
