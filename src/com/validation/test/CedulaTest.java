package com.validation.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.validation.validator.impl.Cedula;

public class CedulaTest {

	Cedula validator;

	@Test
	public void testCouldBeAOKCedula(){
		String cedula="1713898144";
		validator = new Cedula(cedula);
		assertThat(cedula,validator.couldBeADocument(),is(true));
	}
	
	@Test
	public void testCouldBeABadCedulaWithLetters(){
		String cedula="1713abc144";
		validator = new Cedula(cedula);
		assertThat(cedula,validator.couldBeADocument(),is(false));
	}
	
	@Test
	public void testCouldBeABadCedulaWithInvalidNumberOfDigits(){
		String cedula="171389";
		validator = new Cedula(cedula);
		assertThat(cedula,validator.couldBeADocument(),is(false));
	}
	
	@Test
	public void testGetResultFromEvaluationWithCoefficients(){
		String cedula="1002367447";
		validator = new Cedula(cedula);
		int digitChecker = Integer.parseInt(cedula.split("")[9]);
		assertThat(cedula,validator.evaluateWithCoefficients(),is(digitChecker));
	}
	
	@Test
	public void testValidateCorrectCedula(){
		String cedula="1713898144";
		validator = new Cedula(cedula);
		assertThat(cedula, validator.validateDocument(), is(true));
	}
	
	@Test
	public void testValidateIncorrectCedula(){
		String cedula="1712418143";
		validator = new Cedula(cedula);
		assertThat(cedula, validator.validateDocument(), is(false));
	}
	
	@Test
	public void testValidateFakeCedula(){
		String cedula="123";
		validator = new Cedula(cedula);
		assertThat(cedula, validator.validateDocument(), is(false));
	}
}
