/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.commonSorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Sorter for the Navigator which sorts the Added SEIs by their names.
 * Does also work for Repositories
 * @author fisc_ph
 *
 */
public class VirSatNavigatorSeiSorter extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		boolean e1Sei = e1 instanceof StructuralElementInstance;
		boolean e2Sei = e2 instanceof StructuralElementInstance;

		// In case one object is SEI, and the other isn't
		// Make sure that the SEIs come first
		if ((e1Sei) && !(e2Sei)) {
			return -1;
		} else if (!(e1Sei) && (e2Sei)) {
			return 1;
		}
		
		// In all other cases keep the existing order by considering objects "equal"
		return 0;
	}
}
