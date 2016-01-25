package com.validation.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import com.validation.validator.impl.PublicEntity;

public class RucPublicEntityTest {

	PublicEntity validator = new PublicEntity();
	
	
	@Test
	public void testCouldBeAOKPublicEntityRUC() {
		String ruc="1760000150001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(true));		
	}
	
	@Test
	public void testCouldBeABadPublicEntityRUC(){
		String ruc="1770210130001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(false));	
	}
	
	@Test
	public void testCouldBeABadPublicEntityRUCWithLetters(){
		String ruc="1770adc130001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(false));	
	}
	
	@Test
	public void testCouldBeABadPublicEntityRUCWithLessDigits(){
		String ruc="1770";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.couldBeADocument(),is(false));	
	}

	@Test
	public void testGetResultFromEvaluationWithCoefficients(){
		String ruc="1760000150001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.evaluateWithCoefficients(), is(5));
	}
	
	@Test
	public void testValidateACorrectPublicEntityRUC(){
		String ruc="1760000150001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.validateDocument(), is(true));
	}
	
	@Test
	public void testValidateAFakePublicEntityRUC(){
		String ruc="1760101150001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.validateDocument(), is(false));
	}
	
	@Test
	public void testValidateABadPublicEntityRUCWithLetters(){
		String ruc="1770adc130001";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.validateDocument(),is(false));	
	}
	
	@Test
	public void testValidateABadPublicEntityRUCWithLessDigits(){
		String ruc="1770";
		validator = new PublicEntity(ruc);
		assertThat(ruc, validator.validateDocument(),is(false));	
	}
}
