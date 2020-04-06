/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.migrator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.migrator.IMigrator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Concept that defines visualisation properties
 * 
 */
public class Migrator1v1 extends AMigrator1v1 implements IMigrator {

	@Override
	public void migrate(Concept concept, IMigrator previousMigrator) {
		super.migrate(concept, previousMigrator);
	}
	
	private Map<CategoryAssignment, String> mapCaToStringValue = new HashMap<>();
	
	@Override
	public void migrate(Concept conceptPrevious, Concept conceptCurrent, Concept conceptNext) {
		super.migrate(conceptCurrent, conceptCurrent, conceptNext);
		
		for (Entry<CategoryAssignment, String> entry : mapCaToStringValue.entrySet()) {
			Visualisation visualisation = new Visualisation(entry.getKey());
			visualisation.setShape(entry.getValue());
		}
	}
	
	@Override
	protected void deleteProperty(Diff diff, Match match, AProperty property) {
		if (property.getName().equals(Visualisation.PROPERTY_SHAPE)) {
			
			Repository repository = cmHelper.getRepository();
			EcoreUtil.getAllContents(repository.getRootEntities(), true).forEachRemaining((object) -> {
				if (object instanceof CategoryAssignment) {
					CategoryAssignment ca = (CategoryAssignment) object;
					for (APropertyInstance pi : ca.getPropertyInstances()) {
						if (pi.getType().equals(property)) {
							ValuePropertyInstance vpi = (ValuePropertyInstance) pi;
							mapCaToStringValue.put(ca, vpi.getValue());
						}
					}
				}
			});
			
		} 
		
		super.deleteProperty(diff, match, property);
	}
}
