/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.va.bip.archetypetest.exception;

import gov.va.bip.framework.messages.MessageKeys;
import gov.va.bip.framework.messages.MessageSeverity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author rthota
 */
public class ArchetypeTestServiceExceptionTest {
	ArchetypeTestServiceException instance;

	private static final String NAME = "NO_KEY";
	private static final String MESSAGE = "NO_KEY";

	@Before
	public void setUp() {
		instance = new ArchetypeTestServiceException(MessageKeys.NO_KEY, MessageSeverity.ERROR, null);
	}

	/**
	 * Test of getSeverity method, of class ArchetypeTestServiceException.
	 *
	 * @throws Exception
	 */
	@Test
	public void testGetSeverity() {
		System.out.println("getSeverity");
		MessageSeverity expResult = MessageSeverity.ERROR;
		MessageSeverity result = instance.getExceptionData().getSeverity();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getKey method, of class ArchetypeTestServiceException.
	 */
	@Test
	public void testGetKey() {
		System.out.println("getKey");
		String expResult = NAME;
		String result = instance.getExceptionData().getKey();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getMessage method, of class ArchetypeTestServiceException.
	 */
	@Test
	public void testGetMessage() {
		System.out.println("getMessage");
		String expResult = MESSAGE;
		String result = instance.getMessage();
		assertEquals(expResult, result);

	}
}
