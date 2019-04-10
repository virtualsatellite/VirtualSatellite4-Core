/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.transactional.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;


/**
 * Class to test the TransactionalEditingDOmainHelper, which is capable of deciding
 * how to execute a runnable, if it is directly executed or in the thread context
 * of a transaction. In both cases it should always execute unless it is stopped/interuppted
 * @author fisc_ph
 *
 */
public class TransactionalEditingDomainHelperTest {

	@Test
	public void testTryRunExclusive() {
		
		/**
		 * Anonymous class to be used to simulate an execution through the tarnsactional helper class
		 * @author fisc_ph
		 *
		 */
		class TestRunnableWithResult implements RunnableWithResult<String> {
			
			protected String testInfoRetVal = "ReturnObject";
			protected boolean testInfoExecuted = false;
			
			@Override
			public void run() {
				testInfoExecuted = true;
			}

			@Override
			public String getResult() {
				return testInfoRetVal;
			}

			@Override
			public void setStatus(IStatus status) {
			}

			@Override
			public IStatus getStatus() {
				return null;
			}
		}

		// ---------------- CASE 1 --------------------------
		// In the first Case execute without an editing domain
		TestRunnableWithResult testRunnable1 = new TestRunnableWithResult();
		
		String returnValue1 = TransactionalEditingDomainHelper.tryRunExclusive(null, testRunnable1);
		
		assertTrue("Runnable got executed", testRunnable1.testInfoExecuted);
		assertEquals("Runnable returned correct Result", testRunnable1.testInfoRetVal, returnValue1);
		
		
		// ---------------- CASE 2 --------------------------
		// Second test case, execute it with an Editing Domain
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		TransactionalEditingDomain ted = new TransactionalEditingDomainImpl(adapterFactory);
		TestRunnableWithResult testRunnable2 = new TestRunnableWithResult();
		
		String returnValue2 = TransactionalEditingDomainHelper.tryRunExclusive(ted, testRunnable2);
		
		assertTrue("Runnable got executed", testRunnable2.testInfoExecuted);
		assertEquals("Runnable returned correct Result", testRunnable2.testInfoRetVal, returnValue2);
	
		// ---------------- CASE 3 --------------------------
		// now check what happens if thread gets interrupted during execution
		/**
		 * This class simulates an Editing DOmain that stalls when starting the transaction
		 * @author fisc_ph
		 *
		 */
		class InteruptingEditingDomain extends TransactionalEditingDomainImpl {

			/**
			 * Constructor of the domain
			 * @param adapterFactory the standard ComposedAdapterFactory
			 */
			InteruptingEditingDomain(AdapterFactory adapterFactory) {
				super(adapterFactory);
			}
			
			@Override
			public InternalTransaction startTransaction(boolean readOnly, Map<?, ?> options) throws InterruptedException {
				throw new InterruptedException();
			}
		}
		
		TestRunnableWithResult testRunnable3 = new TestRunnableWithResult();
		InteruptingEditingDomain ied = new InteruptingEditingDomain(adapterFactory);
		
		String returnValue3 = TransactionalEditingDomainHelper.tryRunExclusive(ied, testRunnable3);
		
		assertFalse("Runnable got executed", testRunnable3.testInfoExecuted);
		assertNull("No Result expected", returnValue3);
	}
}
