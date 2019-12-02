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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.model.extension.visualisation.delta.ColorDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.model.AVisualisation;

/**
 * This class represents a simple comparison algorithm. 
 * It compares to models on a given property and indicates the changed shape in red color
 * @author fisc_ph
 *
 */
public class ModelPropertyColorMap extends ACompareModelAlgorithm {
	
	private static final String VISUALISATION_CATEGORY_NAME = AVisualisation.FULL_QUALIFIED_CATEGORY_NAME;
	
	private ColorMap colorMap = new ColorMap(0, 0);
	
	/**
	 * Constructor of the algorithm 
	 * @param pm a ProgressMonitor to report progress. COnstructor will convert it into subMonitor.
	 */
	public ModelPropertyColorMap(IProgressMonitor pm) {
		//CHECKSTYLE:OFF
		super(SubMonitor.convert(pm, 3));
		//CHECKSTYLE:ON
	}
	
	/**
	 * Call this method to get the minimum ColorMap
	 * @return The minimum value of ColorMap
	 * */
	public double getColorMapMinValue() {
		return colorMap.getMin();
	}
	
	/**
	 * Call this method to get the maximum ColorMap
	 * @return The maximum value of ColorMap
	 * */
	public double getColorMapMaxValue() {
		return colorMap.getMax();
	}
	/**
	 * Call this method to create a Delta Visualization Model for parameter ColorMap
	 * @param baseRepo The Repository containing the original Model
	 * @param propertyFqn a full qualified name of the property that should be used for comparison
	 * @return The delta model that can be overlayed to the visualization.
	 * */
	public VisualisationDeltaModel colorMap(Repository baseRepo, String propertyFqn) {
		subMonitor.setTaskName("color map of specific property");
		VisualisationDeltaModel vdm = new VisualisationDeltaModel();
		
		List<UnitValuePropertyInstance> listOfVpis = new ArrayList<>();
				
		subMonitor.subTask("Finding specific property in base model");
		// Find all correct property instances in the baseProject
		EcoreUtil.getAllContents(baseRepo.getRootEntities(), true).forEachRemaining((object) -> {
			
			if (object instanceof APropertyInstance) {
				APropertyInstance pi = (APropertyInstance) object;
				String fqn = pi.getType().getFullQualifiedName();
				
				if (fqn.equals(propertyFqn)) {
					UnitValuePropertyInstance uvpi = null;
					
					if (object instanceof ComposedPropertyInstance) {
						ComposedPropertyInstance cpi = (ComposedPropertyInstance) object;
						
						uvpi = cpi.getTypeInstance().getPropertyInstances().stream()
								.filter(p -> p instanceof UnitValuePropertyInstance)
								.map(p -> (UnitValuePropertyInstance) p)
								.findFirst().orElse(null);
					} else if (object instanceof UnitValuePropertyInstance) {
						uvpi = (UnitValuePropertyInstance) object;
					}
					
					if (uvpi != null) {
						listOfVpis.add(uvpi);
					}
				}
			}
			
			if (object instanceof CategoryAssignment) {
				CategoryAssignment ca = (CategoryAssignment) object;
				String fqn = ca.getType().getFullQualifiedName();
				if (fqn.equals(VISUALISATION_CATEGORY_NAME)) {
					ColorDelta colorDelta = new ColorDelta(ca.getCategoryAssignmentContainer().getUuid().toString(), COLOR_GREY);
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);					
				}
				
			}
			
		});
		subMonitor.worked(1);

		subMonitor.subTask("Creat color map diagram");
		double maxValue = 0;
		double minValue = 0;
		boolean init = false;
		for (UnitValuePropertyInstance uvpi : listOfVpis) {
			BeanPropertyFloat bpf = new BeanPropertyFloat(uvpi);
			double value = bpf.getValueToBaseUnit();
		
			if (!init) {
				maxValue = value;
				minValue = value;
				init = true;
			} else {
				if (value < minValue) {
					minValue = value;
				}
				if (value > maxValue) {
					maxValue = value;												
				} 
			}
		}
		
		colorMap.setMax(maxValue);
		colorMap.setMin(minValue);
	
		subMonitor.worked(1);
		
		subMonitor.subTask("Creat color map model");
	
		for (UnitValuePropertyInstance uvpi : listOfVpis) {
			double value = new BeanPropertyFloat(uvpi).getValueToBaseUnit();
			
			ICategoryAssignmentContainer cac = uvpi.getCategoryAssignmentContainer();	
			ColorDelta colorDelta = new ColorDelta(cac.getUuid().toString(), colorMap.valueToColor(value));
			vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
		}
		
		subMonitor.worked(1);
		subMonitor.done();
		
		return vdm;
	}
}
