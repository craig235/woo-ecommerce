package com.qa.woo.ecommerce.validation;

public class InputValidation {
	
	public static boolean validInt(int id) {
		boolean valid = false;
		if(id > 0)
			valid = true;
		return valid;
	}

}
