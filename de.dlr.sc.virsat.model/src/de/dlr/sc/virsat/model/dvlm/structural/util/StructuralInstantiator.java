/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.util;

import de.dlr.sc.virsat.model.dvlm.structural.GeneralRelation;
import de.dlr.sc.virsat.model.dvlm.structural.RelationInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;

/**
 * This class handles the instantiation of StructuralElements and Relations.
 * @author fisc_ph
 *
 */
public class StructuralInstantiator {

	/**
	 * Call this method to create an instance of a given StructuralElement
	 * @param se The StructuralElement which shall be instantiated
	 * @param instanceName the name of the instance that should be assigned
	 * @return the instantiated StructuralElement
	 */
	public StructuralElementInstance generateInstance(StructuralElement se, String instanceName) {
		StructuralElementInstance sei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		sei.setType(se);
		sei.setName(instanceName);
		return sei;
	}

	/**
	 * Call this method to create an instance of a given Relation or its sub types
	 * @param gr The GeneralRelation which shall be instantiated
	 * @param instanceName the name of the instance that should be assigned
	 * @return the instantiated Relation
	 */
	public RelationInstance generateInstance(GeneralRelation gr, String instanceName) {
		RelationInstance ri = StructuralFactory.eINSTANCE.createRelationInstance();
		ri.setType(gr);
		ri.setName(instanceName);
		return ri;
	}
}
