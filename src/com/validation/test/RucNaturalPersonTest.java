package com.validation.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.validation.validator.impl.NaturalPerson;

public class RucNaturalPersonTest {

	NaturalPerson validador = new NaturalPerson();
	
	@Test
	public void testCouldBeAOKRuc() {
		String ruc="1002367447001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.couldBeADocument(), is(true));
	}

	@Test
	public void testCouldBeABadRucWithLetters() {
		String ruc="10623abc17001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.couldBeADocument(), is(false));
	}
	
	@Test
	public void testCouldBeABadRucWithALotOfDigits() {
		String ruc="1062363217001000001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.couldBeADocument(), is(false));
	}
	
	@Test
	public void testCouldBeAFakeRuc() {
		String ruc="1062363217001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.couldBeADocument(), is(false));
	}
	
	@Test
	public void testhasTheIdentifier9ForThisTypeOfRuc() {
		String ruc="1092363217001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.hasTheIdentifierForThisTypeOfRuc(), is(false));
	}
	
	@Test
	public void testhasTheIdentifier6ForThisTypeOfRuc() {
		String ruc="1062363217001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.hasTheIdentifierForThisTypeOfRuc(), is(false));
	}
	
	@Test
	public void testGetResultFromEvaluationWithCoefficients(){
		String ruc="1002367447001";
		validador = new NaturalPerson(ruc);
		int digitChecker = Integer.parseInt(ruc.split("")[9]);
		assertThat(ruc, validador.couldBeADocument(), is(true));
		assertThat(ruc, validador.evaluateWithCoefficients(),is(digitChecker));
	}
	
	@Test
	public void testValidateCorrectRuc(){
		String ruc="1721220000001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.validateDocument(), is(true));
	}
	
	@Test
	public void testValidateFakeRuc(){
		String ruc="1062363217001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.validateDocument(), is(false));
	}
	
	@Test
	public void testValidateABadRucWithLetters() {
		String ruc="10623abc17001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.validateDocument(), is(false));
	}
	
	@Test
	public void testValidateABadRucWithALotOfDigits() {
		String ruc="1062363217001000001";
		validador = new NaturalPerson(ruc);
		assertThat(ruc, validador.validateDocument(), is(false));
	}
}
