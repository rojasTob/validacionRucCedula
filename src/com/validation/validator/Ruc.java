package com.validation.validator;

public interface Ruc extends Document{
		
	public int getDigitCheckerForThisRUC();
	
	public boolean hasTheIdentifierForThisTypeOfRuc();
}
