package com.validation.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.validation.validator.impl.JuridicalPerson;

public class RucJuridicalPersonTest {

	JuridicalPerson validator;
	
	
	@Test
	public void testCouldBeAOKJuridicalPersonRUC() {
		String ruc="1790011674001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(true));		
	}
	
	@Test
	public void testCouldBeABadJuridicalPersonRUC(){
		String ruc="17903cc74001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(false));	
	}
	
	@Test
	public void testCouldBeABadJuridicalPersonRUCWithLessDigits(){
		String ruc="1790011674001000000010001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(false));	
	}
	
	@Test
	public void testCouldBeABadJuridicalPersonRUCWithoutDigitChecker(){
		String ruc="1760011674001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(false));	
	}

	@Test
	public void testGetResultFromEvaluationWithCoefficients(){
		String ruc="1790011674001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.evaluateWithCoefficients(), is(4));
	}
	
	@Test
	public void testValidateFakeJuridicalPersonRUC(){
		String ruc="123456789456123456789";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.validateDocument(),is(false));	
	}
	
	@Test
	public void testValidateABadJuridicalPersonRUC(){
		String ruc="17903cc74001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.validateDocument(),is(false));	
	}
	
	@Test
	public void testValidateBadJuridicalPersonRUCWithoutDigitChecker(){
		String ruc="1760011674001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.validateDocument(),is(false));	
	}
	
	@Test
	public void testValidateACorrectJuridicalPersonRUC(){
		String ruc="1790011674001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.validateDocument(), is(true));
	}
	
	@Test
	public void testValidateAFakeJuridicalPersonRUC(){
		String ruc="1790011676001";
		validator = new JuridicalPerson(ruc);
		assertThat(ruc, validator.validateDocument(), is(false));
	}
}
