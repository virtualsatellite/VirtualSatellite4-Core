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

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;

/**
 * Some Helper with general BoilerPlate Code introduced by the transactional
 * Editing of our EMF model 
 * @author fisc_ph
 *
 */
public class TransactionalEditingDomainHelper {

	/**
	 * class constructor
	 */
	protected TransactionalEditingDomainHelper() {
	}

	/**
	 * Method to wrap the reading to the data model into transactions
	 * @param ed the transactional editing domain where the the data model will be readed
	 * @param transactionalRunnable The runnable which should be executed
	 * @param <T> the generic return
	 * @return The result of the exclusive execution
	 */
	public static synchronized <T> T tryRunExclusive(TransactionalEditingDomain ed, RunnableWithResult<? extends T> transactionalRunnable) {
		try {
			// Now we check if we found and Editing Domain. If yes we read the model transactional
			// If we have no correct ED we try to read the model the old fashioned non transacitonal way
			if (ed != null) {
				return TransactionUtil.runExclusive(ed, transactionalRunnable);
			} else {
				transactionalRunnable.run();
				return transactionalRunnable.getResult();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			DVLMEditPlugin.getPlugin().getLog().log(new Status(Status.ERROR, "de.dlr.sc.virsat.model.edit", "Interupted Transactional Content Accessor! "));
			return null;
		}
	}
}
