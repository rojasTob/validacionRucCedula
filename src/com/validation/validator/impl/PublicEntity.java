package com.validation.validator.impl;

import com.validation.util.ValidationUtil;
import com.validation.validator.Ruc;

public class PublicEntity implements Ruc{
	
	private static int [] PUBLIC_COMPANY_COEFFICIENTS = {3,2,7,6,5,4,3,2};
	
	private String documento;
	

	public PublicEntity() {
		
	}
	
	public PublicEntity(String documento) {
		this.documento = documento;
	}

	
	@Override
	public boolean couldBeADocument() {
		return (ValidationUtil.containsOnlyNumbers(this.documento) && ValidationUtil.hasDigitsLikeARuc(this.documento) && hasTheIdentifierForThisTypeOfRuc());
	}
	
	@Override
	public boolean validateDocument() {
		int result = 0;
        int checker = 0;
        boolean isValid = false ;
        
        if(couldBeADocument()){
        	result = evaluateWithCoefficients();
        	checker = getDigitCheckerForThisRUC();
        	isValid = (result==checker);
        }
        
        if(!isValid){
        	return new JuridicalPerson(this.documento).validateDocument();
        }
        
        System.out.println(this.documento+": Is a valid RUC of public entity.");	
		return isValid;
	}
	
	@Override
	public int evaluateWithCoefficients() {
		int sumaTotalDigitosPorCoeficiente = 0;
        int digito = 0;
        int valor = 0;
        String [] ruc = this.documento.split("");
        
		for(int i= 0;i<PUBLIC_COMPANY_COEFFICIENTS.length;i++){
            digito= Integer.parseInt(ruc[i]);
            valor=PUBLIC_COMPANY_COEFFICIENTS[i] * digito;
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
		return Integer.parseInt(this.documento.split("")[8]);
	}

	@Override
	public boolean hasTheIdentifierForThisTypeOfRuc(){
		return (this.documento.charAt(2) == '6');
	}
	

}
