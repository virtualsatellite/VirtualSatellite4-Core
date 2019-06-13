/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.comparison;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.delta.ColorDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;

/**
 * This class represents a simple comparison algorithm. 
 * It compares to models on a given property and indicates the changed shape in red color
 * @author fisc_ph
 *
 */
public class CompareModelProperty extends ACompareModelAlgorithm {
	
	/**
	 * Constructor of the algorithm 
	 * @param pm a ProgressMonitor to report progress. COnstructor will convert it into subMonitor.
	 */
	public CompareModelProperty(IProgressMonitor pm) {
		//CHECKSTYLE:OFF
		super(SubMonitor.convert(pm, 3));
		//CHECKSTYLE:ON
	}
	
	/**
	 * Call this method to compare the model parameter and to create a Delta Visualization Model
	 * @param baseRepo The VirSatProjectResource containing the original Model
	 * @param compareRepo The VirsatProjectResource containing the model to which to compare to
	 * @param propertyFqn a full qualified name of the property that should be used for comparison
	 * @return The delta model that can be overlayed to the visualization.
	 */
	public VisualisationDeltaModel compare(Repository baseRepo, Repository compareRepo, String propertyFqn) {
		subMonitor.setTaskName("Comparing models on specific property");
		VisualisationDeltaModel vdm = new VisualisationDeltaModel();
		
		Map<String, UnitValuePropertyInstance> mapOfBaseVpis = new HashMap<>();
		Map<String, UnitValuePropertyInstance> mapOfCompareVpis = new HashMap<>();
		
		Set<String> changedSeiUUids = new HashSet<>();
		
		subMonitor.subTask("Finding specific property in base model");
		// Find all correct property instances in the baseProject
		EcoreUtil.getAllContents(baseRepo.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof UnitValuePropertyInstance) {
				UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) object;
				String fqn = uvpi.getType().getFullQualifiedName();
				
				if (fqn.equals(propertyFqn)) {
					ICategoryAssignmentContainer cac = uvpi.getCategoryAssignmentContainer();
					mapOfBaseVpis.put(cac.getUuid().toString(), uvpi);
				}
			}
		});
		subMonitor.worked(1);

		subMonitor.subTask("Finding specific property in comparison model");
		// Find all correct property instances in the compareProject
		EcoreUtil.getAllContents(compareRepo.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof ComposedPropertyInstance) {													
				ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
				String fqn = cpi.getType().getFullQualifiedName();		
								
				if (fqn.equals(propertyFqn)) {
					CategoryAssignment ca = cpi.getTypeInstance();
					UnitValuePropertyInstance uvpi = (UnitValuePropertyInstance) ca.getPropertyInstances().get(0);
					
					ICategoryAssignmentContainer cac = uvpi.getCategoryAssignmentContainer();	
					mapOfCompareVpis.put(cac.getUuid().toString(), uvpi);				
				} 
			}
		});
		subMonitor.worked(1);

		subMonitor.subTask("Find differences from base to compare model");
		// Now compare each property and update the DeltaModel
		for (String keyUuids : mapOfBaseVpis.keySet()) {
			if (mapOfCompareVpis.containsKey(keyUuids)) {
				UnitValuePropertyInstance vpiBase = mapOfBaseVpis.get(keyUuids);
				UnitValuePropertyInstance vpiCompare = mapOfCompareVpis.get(keyUuids);

				double valueBase = new BeanPropertyFloat(vpiBase).getValueToBaseUnit();
				double valueCompare = new BeanPropertyFloat(vpiCompare).getValueToBaseUnit();
				
				if (valueBase == valueCompare) {
					// unchanged 							
					ColorDelta colorDelta = new ColorDelta(keyUuids, PARAMETER_UNCHANGED_COLOR);	
					changedSeiUUids.add(keyUuids);
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
				} else if (valueBase > valueCompare) {
					// changed high							
					ColorDelta colorDelta = new ColorDelta(keyUuids, PARAMETER_EDITED_HIGHER_COLOR);
					changedSeiUUids.add(keyUuids);
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
				} else if (valueBase < valueCompare) {
					// changed lower							
					ColorDelta colorDelta = new ColorDelta(keyUuids, PARAMETER_EDITED_LOWER_COLOR);	
					changedSeiUUids.add(keyUuids);
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
				}	
			} else {
				// added 				
				ColorDelta colorDelta = new ColorDelta(keyUuids, PARAMETER_ADDED_COLOR);
				changedSeiUUids.add(keyUuids);
				vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
			}
		}
		
		// Mark the ones with deleted property
		for (String keyUuids : mapOfCompareVpis.keySet()) {			
			if (!mapOfBaseVpis.containsKey(keyUuids)) {
				ColorDelta colorDelta = new ColorDelta(keyUuids, PARAMETER_REMOVED_COLOR);	
				changedSeiUUids.add(keyUuids);
				vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
			}
		}		
		
		// Grey out all the SEIs that have not been touched and colored
		EcoreUtil.getAllContents(baseRepo.getRootEntities(), true).forEachRemaining((object) -> {
			if (object instanceof StructuralElementInstance) {
				StructuralElementInstance sei = (StructuralElementInstance) object;
				String seiUuid = sei.getUuid().toString();
				if (!changedSeiUUids.contains(seiUuid)) {
					ColorDelta colorDelta = new ColorDelta(seiUuid, COLOR_GREY);	
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
				}
			}
		});
		
		subMonitor.worked(1);
		subMonitor.done();
		
		return vdm;
	}
}
