/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.editingDomain.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import de.dlr.sc.virsat.model.dvlm.transactional.util.TransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;

/**
 * Some Helper with general BoilerPlate Code introduced by the Transactional
 * Editing of our EMF model 
 * @author fisc_ph
 *
 */
public class VirSatTransactionalEditingDomainHelper extends TransactionalEditingDomainHelper {

	/**
	 * private constructor for the {@link VirSatTransactionalEditingDomainHelper} 
	 */
	private VirSatTransactionalEditingDomainHelper() {
	}

	/**
	 * Method to wrap the reading to the data model into transactions
	 * @param <T> Defines the return type of the runnable that will be executed
	 * @param object the object of which to receive the correct Editing Domain
	 * @param transactionalRunnable The runnable which should be executed
	 * @return The result of the exclusive execution
	 */
	public static synchronized <T> T tryRunExclusive(Object object, RunnableWithResult<? extends T> transactionalRunnable) {
		// First try to find out the Editing Domain which handles the current Object
		// Remember in VirSat we have one Editing Domain for each Project
		// Accordingly we have to ask every time what is our current Editing Domain
		TransactionalEditingDomain ed = null;
		if (object instanceof EObject) {
			ed = VirSatEditingDomainRegistry.INSTANCE.getEd((EObject) object);
		}
		
		return tryRunExclusive(ed, transactionalRunnable);
	}
}
