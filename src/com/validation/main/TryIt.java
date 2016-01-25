package com.validation.main;

import com.validation.validator.Document;
import com.validation.validator.impl.Cedula;

public class TryIt {

	public static void main(String[] args) {
		
		String document= "1790011674001"; //Insert a number of document CEDULA or RUC and start to validate it !!! :)
		Document document1 = new Cedula(document);
		document1.validateDocument();

	}

}
