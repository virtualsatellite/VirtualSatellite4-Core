/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.inheritance;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Interface to the Inheritance Copier to allow for easier testing of
 * the corresponding builder
 * @author fisc_ph
 *
 */
public interface IInheritanceCopier {

	/**
	 * States if the SEI should be updated, obeys all SEIs inherited from
	 * @param subSei SEI to be updated 
	 * @return statement if should be updated
	 */
	boolean needsUpdateInOrder(StructuralElementInstance subSei);

	/**
	 * Updates the SEI following the order of the SEIs from where it inherits.
	 * @param repo The repository which should be completely updated
	 * @param monitor A ProgressMonitor
	 * @return Call Category Assignments which have been copied and or adjusted due to the inheritance
	 */
	Set<CategoryAssignment> updateAllInOrder(Repository repo, IProgressMonitor monitor);

	/**
	 * Updates the SEI following the order of the SEIs from where it inherits.
	 * @param subSei to be updated
	 * @param monitor A ProgressMonitor
	 * @param repo The Repository in which all SEIs reside
	 * @return Call Category Assignments which have been copied and or adjusted due to the inheritance
	 */
	Set<CategoryAssignment> updateInOrderFrom(StructuralElementInstance subSei, Repository repo, IProgressMonitor monitor);

}