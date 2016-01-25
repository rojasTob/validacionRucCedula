package com.validation.validator.impl;

import com.validation.util.ValidationUtil;
import com.validation.validator.Document;

public class Cedula implements Document{
		
	private final int [] NATURAL_PERSON_COEFFICIENTS = {2,1,2,1,2,1,2,1,2};
	private String document;
	
	
	public Cedula(){}
	
	public Cedula(String document){
		this.document=document;
	}
	

	@Override
	public boolean validateDocument() {
		
		int result = 0;
		int lastDigit = 0;
		boolean isValid = false;
		
		if(couldBeADocument()){
	        result = evaluateWithCoefficients();
	        lastDigit =  Integer.parseInt(this.document.split("")[9]);
	        isValid = (result == lastDigit);
		}
		
		if(!isValid){
			return new PublicEntity(this.document).validateDocument();
		}
		
		System.out.println(this.document+": Is a valid Cedula.");	
		return isValid;
	}

	@Override
	public int evaluateWithCoefficients() {
		int totalDigitXCoefficient=0;
        int digit=0;
        int digitXCoefficient=0;
        String [] cedula = this.document.split("");
        
		for(int i= 0;i<NATURAL_PERSON_COEFFICIENTS.length;i++){
            digit =  Integer.parseInt(cedula[i]);
            digitXCoefficient=NATURAL_PERSON_COEFFICIENTS[i] * digit;
            if(digitXCoefficient > 9){
                digitXCoefficient = digitXCoefficient - 9;
            }
            totalDigitXCoefficient= totalDigitXCoefficient + digitXCoefficient;
        }
		System.out.println("totalDigitXCoefficient: "+totalDigitXCoefficient);
        int mod= totalDigitXCoefficient % 10;
        mod = (mod==0) ? 10 : mod;
        int result = 10-mod;
		return result;
	}
	

	@Override
	public boolean couldBeADocument() {
		return (ValidationUtil.containsOnlyNumbers(this.document) && hasTenDigits());
	}
	
	private boolean hasTenDigits(){		
		return (this.document.length() == 10);
	}

}
