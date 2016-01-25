package com.validation.validator.impl;

import com.validation.util.ValidationUtil;
import com.validation.validator.Ruc;

public class NaturalPerson implements Ruc{

	private final int [] NATURAL_PERSON_COEFFICIENTS = {2,1,2,1,2,1,2,1,2};
	
	private String document;
	
	private String cedula;
	
	
	public NaturalPerson() {
	}
	
	public NaturalPerson(String document) {
		this.document= document;
	}
	
	
	@Override
	public boolean couldBeADocument() {
		if(ValidationUtil.containsOnlyNumbers(this.document) && ValidationUtil.hasDigitsLikeARuc(this.document) && hasTheIdentifierForThisTypeOfRuc()){
			this.cedula = getCedulaFromARUC();
			return true;
		}
		return false;
	}

	@Override
	public boolean validateDocument() {
		int result = 0;
		int lastDigit = 0;
		boolean isValid = false;

		if(couldBeADocument()){			
	        result = evaluateWithCoefficients();
	        lastDigit =  getDigitCheckerForThisRUC();
	        isValid =  (result == lastDigit);
		}
		
		if(!isValid){
			System.out.println(this.document+ ": The document is not valid.");
			return false;
		}
		
		System.out.println(this.document+": Is a valid RUC of a natural person.");	
		return isValid;
	}

	@Override
	public int evaluateWithCoefficients() {
		int totalDigitXCoefficient=0;
        int digit=0;
        int digitXCoefficient=0;
        String [] cedula = this.cedula.split("");
        
		for(int i= 0;i<NATURAL_PERSON_COEFFICIENTS.length;i++){
            digit =  Integer.parseInt(cedula[i]);
            digitXCoefficient=NATURAL_PERSON_COEFFICIENTS[i] * digit;
            if(digitXCoefficient > 9){
                digitXCoefficient = digitXCoefficient - 9;
            }
            totalDigitXCoefficient= totalDigitXCoefficient + digitXCoefficient;
        }
        int mod= totalDigitXCoefficient % 10;
        mod = (mod==0) ? 10 : mod;
        int result = 10-mod;
		return result;
	}

	@Override
	public int getDigitCheckerForThisRUC() {
		return Integer.parseInt(this.document.split("")[9]);
	}

	@Override
	public boolean hasTheIdentifierForThisTypeOfRuc() {
		return (this.document.charAt(2) != '6' && this.document.charAt(2) != '9');	
	}
	
	private String getCedulaFromARUC(){
		return this.document.substring(0,10);
	}

}
