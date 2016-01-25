package com.validation.validator.impl;

import com.validation.util.ValidationUtil;
import com.validation.validator.Ruc;

public class JuridicalPerson implements Ruc{
	
	private static int [] JURIDICAL_PERSON_COEFFICIENTS = {4,3,2,7,6,5,4,3,2};
	
	private String document;
	
	
	public JuridicalPerson() {}
	
	public JuridicalPerson(String document) {
		this.document= document;
	}

	@Override
	public boolean couldBeADocument() {
		return (ValidationUtil.containsOnlyNumbers(this.document) && ValidationUtil.hasDigitsLikeARuc(this.document) && hasTheIdentifierForThisTypeOfRuc());
	}
	
	@Override
	public boolean hasTheIdentifierForThisTypeOfRuc() {
		return (this.document.charAt(2) == '9');
	}

	@Override
	public boolean validateDocument() {
		int result = 0;
		int checker = 0;
		boolean isValid = false;
		if(couldBeADocument()){
        	result = evaluateWithCoefficients();
        	checker = getDigitCheckerForThisRUC();
        	isValid =  (result == checker);
        }
		
		if(!isValid){
			return new NaturalPerson(this.document).validateDocument();
		}
		
		System.out.println(this.document+": Is a RUC of a juridical person.");	
		return isValid;
	}

	@Override
	public int evaluateWithCoefficients() {
		int sumaTotalDigitosPorCoeficiente = 0;
        int digito = 0;
        int valor = 0;
        String [] ruc = document.split("");
        
		for(int i= 0;i<JURIDICAL_PERSON_COEFFICIENTS.length;i++){
            digito= Integer.parseInt(ruc[i]);
            valor=JURIDICAL_PERSON_COEFFICIENTS[i] * digito;
            sumaTotalDigitosPorCoeficiente= sumaTotalDigitosPorCoeficiente + valor;
        }
        int modulo= sumaTotalDigitosPorCoeficiente % 11;
        int resultado=0;

        if(modulo!=0){
            resultado=11-modulo;
        }
		return resultado;
	}

	@Override
	public int getDigitCheckerForThisRUC() {
		return Integer.parseInt(this.document.split("")[9]);
	}

}
