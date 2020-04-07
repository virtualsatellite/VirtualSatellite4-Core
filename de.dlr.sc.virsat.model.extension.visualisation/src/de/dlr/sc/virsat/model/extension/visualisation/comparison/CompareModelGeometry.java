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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.util.CategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.delta.ColorDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.GhostShapeDelta;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.model.AVisualisation;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.VisualisationShape;

/**
 * This class represents a simple comparison algorithm. 
 * It compares to models on a given property and indicates teh changed shape in red color
 * @author fisc_ph
 *
 */
public class CompareModelGeometry extends ACompareModelAlgorithm {

	
	private static final String VISUALISATION_CATEGORY_NAME = AVisualisation.FULL_QUALIFIED_CATEGORY_NAME;
	
	/**
	 * Constructor of the algorithm 
	 * @param pm a ProgressMonitor to report progress. COnstructor will convert it into subMonitor.
	 */
	public CompareModelGeometry(IProgressMonitor pm) {
		//CHECKSTYLE:OFF
		super(SubMonitor.convert(pm, 3));
		//CHECKSTYLE:ON
	}
	
	/**
	 * Call this method to compare the model geometry and to create a Delta Visualization Model
	 * @param baseRepo The VirSatProjectResource containing the original Model
	 * @param compareRepo The VirsatProjectResource containing the model to which to compare to
	 * @return The delta model that can be overlayed to the visualization.
	 */
	public VisualisationDeltaModel compare(Repository baseRepo, Repository compareRepo) {
		subMonitor.setTaskName("Comparing models geometry");
		VisualisationDeltaModel vdm = new VisualisationDeltaModel();
		
		//parent ID		
		Map<String, String> mapOfBaseParentID = new HashMap<String, String>();
		Map<String, String> mapOfCompareParentID  = new HashMap<String, String>();
		
		Map<String, CategoryAssignment> mapOfBaseVisCA = new HashMap<String, CategoryAssignment>();
		Map<String, CategoryAssignment> mapOfCompareVisCA = new HashMap<String, CategoryAssignment>();
		
		subMonitor.subTask("Finding visulisation in base model");
		// Find all correct property instances in the baseProject
		if (baseRepo.getRootEntities().size() != 0) {
			StructuralElementInstance rootSei = baseRepo.getRootEntities().get(0);
			collectVisCategoryAssignmentChildren(rootSei, null, mapOfBaseVisCA, mapOfBaseParentID);
		}
		subMonitor.worked(1);

		subMonitor.subTask("Finding visulisation in comparison model");
		// Find all correct property instances in the compareProject
		if (compareRepo.getRootEntities().size() != 0) {
			StructuralElementInstance rootSei = compareRepo.getRootEntities().get(0);
			collectVisCategoryAssignmentChildren(rootSei, null, mapOfCompareVisCA, mapOfCompareParentID);
		}
		subMonitor.worked(1);

		subMonitor.subTask("Find differences from base to compare model");
		// Now compare and update the DeltaModel
		for (Entry<String, CategoryAssignment> entry : mapOfBaseVisCA.entrySet()) {
			String key = entry.getKey();
			Visualisation baseVisualisation = new Visualisation(entry.getValue());
			if (mapOfCompareVisCA.containsKey(key)) {
				Visualisation compareVisualisation = new Visualisation(mapOfCompareVisCA.get(key));
				
				if (isVisualisationEqual(key, baseVisualisation, compareVisualisation, mapOfBaseVisCA, mapOfCompareVisCA, mapOfBaseParentID, mapOfCompareParentID)) {
					// unchanged geo							
					ColorDelta colorDelta = new ColorDelta(key, GEO_UNCHANGED_COLOR);					
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
				} else {
					// changed geo							
					ColorDelta colorDelta = new ColorDelta(key, GEO_EDITED_COLOR);	
					vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
				}				
			} else {
				// added geo				
				ColorDelta colorDelta = new ColorDelta(key, GEO_ADDED_COLOR);	
				vdm.colorDeltas.put(colorDelta.shapeId, colorDelta);
			}
		}
		for (Entry<String, CategoryAssignment> entry : mapOfCompareVisCA.entrySet()) {
			String key = entry.getKey();
			if (!mapOfBaseVisCA.containsKey(key)) {
				// removed geo
				Visualisation compareVis = new Visualisation(entry.getValue());
				String shapeGeometryString = compareVis.getShape();
				VisualisationShape shapeGeometry = VisualisationShape.valueOf(shapeGeometryString); 
				Shape shape = new Shape(key, compareVis.getGeometryFile(), 
						(float) compareVis.getSizeX(), (float) compareVis.getSizeY(), (float) compareVis.getSizeZ(), 
						(float) compareVis.getRadius(), compareVis.getPositionX(), compareVis.getPositionY(), 
						compareVis.getPositionZ(), compareVis.getRotationX(), compareVis.getRotationY(), compareVis.getRotationZ(),
						GEO_REMOVED_COLOR, (float) compareVis.getTransparency(), shapeGeometry);
				String parentID = mapOfCompareParentID.get(key);
				GhostShapeDelta ghostShapeDelta = new GhostShapeDelta(parentID, shape);
				vdm.ghostShapeDeltas.add(ghostShapeDelta);					

			}
		}
		subMonitor.worked(1);
		subMonitor.done();
		
		return vdm;
	}

	/**
	 * Recursively collects all visualisation children of a given StructuralElementInstance into a given map
	 * @param sei The Structural Element Instance of which to get the Vis CA
	 * @param parentID The parent ID to which this method finds the new 
	 * @param visCAMap Map to store the ID and the Visualization Category with it
	 * @param parentIDMap a Map of child IDs vs. parent IDs
	 */
	private void collectVisCategoryAssignmentChildren(StructuralElementInstance sei, String parentID, Map<String, CategoryAssignment> visCAMap,  Map<String, String> parentIDMap) {
		List<CategoryAssignment> visCAs = CategoryAssignmentHelper.getCategoryAssignmentsOfType(sei, VISUALISATION_CATEGORY_NAME);
		if (!visCAs.isEmpty()) {
			String id = sei.getUuid().toString();
			CategoryAssignment visCA = visCAs.get(0);
			visCAMap.put(id, visCA);
			if (parentID != null) {
				parentIDMap.put(id, parentID);	
			}			
			
			parentID = id;
		}
		
		for (StructuralElementInstance child : sei.getChildren()) {
			collectVisCategoryAssignmentChildren(child, parentID, visCAMap, parentIDMap);
		}
	}
	
	/**
	 * Is Visualization Bean Rotation and Position value zero
	 * @param visualisation 
	 * @return isNone
	 */
	private boolean isVisBeanPositonAndRotationNone(Visualisation visualisation) {
		return (visualisation.getPositionX() == 0 && visualisation.getPositionY() == 0 && visualisation.getPositionZ() == 0 
				&& visualisation.getRotationX() == 0 && visualisation.getRotationY() == 0 && visualisation.getRotationZ() == 0);
		
	}
	
	/**
	 * compare two Visualisation Beans and if they have equal position and rotation return ture 
	 * @param vis1 
	 * @param vis2 
	 * @return isEqual
	 */
	private boolean isVisBeanPositionAndRotationEqual(Visualisation vis1, Visualisation vis2) {	
		boolean isEqual = false;

		if (vis1.getPositionX() == vis2.getPositionX() && vis1.getPositionY() == vis2.getPositionY() && vis1.getPositionZ() == vis2.getPositionZ()
				&& vis1.getRotationX() == vis2.getRotationX() && vis1.getRotationY() == vis2.getRotationY() && vis1.getRotationZ() == vis2.getRotationZ()) {
			isEqual = true;
		}
		return isEqual;
	}
	
	/**
	 * compare two Visualisation Beans and if they are equal return turn 
	 * @param vis1 
	 * @param vis2 
	 * @return isEqual
	 */
	private boolean isVisualisationEqual(Visualisation vis1, Visualisation vis2) {	
		boolean isEqual = false;

		if (vis1.getShape().equals(vis2.getShape()) && vis1.getRadius() == vis2.getRadius() 
				&& vis1.getColor() == vis2.getColor() && vis1.getTransparency() == vis2.getTransparency()
				&& vis1.getSizeX() == vis2.getSizeX() && vis1.getSizeY() == vis2.getSizeY() && vis1.getSizeZ() == vis2.getSizeZ()
				&& vis1.getPositionX() == vis2.getPositionX() && vis1.getPositionY() == vis2.getPositionY() && vis1.getPositionZ() == vis2.getPositionZ()
				&& vis1.getRotationX() == vis2.getRotationX() && vis1.getRotationY() == vis2.getRotationY() && vis1.getRotationZ() == vis2.getRotationZ()) {
			isEqual = true;
		}
		return isEqual;
	}
	/**
	 * Compare visualisations and one step parent visulisations if they are equal return turn 
	 * @param visID visID
	 * @param vis1 Visualisation
	 * @param vis2 Visualisation
	 * @param mapOfBaseVisCA  map of Visualisation CategoryAssignment of base project
	 * @param mapOfCompareVisCA  map of Visualisation CategoryAssignment of compare project
	 * @param mapOfBaseParentID  map of Visualisation parent ID of base project
	 * @param mapOfCompareParentID  map of Visualisation parent ID of compare project
	 * @return isEqual
	 */
	private boolean isVisualisationEqual(String visID, Visualisation vis1, Visualisation vis2, Map<String, CategoryAssignment> mapOfBaseVisCA, Map<String, CategoryAssignment> mapOfCompareVisCA, Map<String, String> mapOfBaseParentID, Map<String, String> mapOfCompareParentID) {	
		boolean isEqual = false;
		
		int baseParentNum = 0;
		int compareParentNum = 0;
		String parentIDBaseForCount = visID;
		String parentIDCompareForCount = visID;
		while (mapOfBaseParentID.containsKey(parentIDBaseForCount)) {
			baseParentNum++;
			parentIDBaseForCount = mapOfBaseParentID.get(parentIDBaseForCount);
			
		}
		while (mapOfCompareParentID.containsKey(parentIDCompareForCount)) {
			compareParentNum++;
			parentIDCompareForCount = mapOfCompareParentID.get(parentIDCompareForCount);
		}
		
		
		if (isVisualisationEqual(vis1, vis2)) {				
			if (baseParentNum > compareParentNum) {
				String parentIDBase = visID;
				String parentIDCompare = visID;
				Visualisation baseVisualisationParent;
				Visualisation compareVisualisationParent;
				for (int i = 0; i < compareParentNum; i++) {
					parentIDBase = mapOfBaseParentID.get(parentIDBase);
					parentIDCompare = mapOfCompareParentID.get(parentIDCompare);					
					baseVisualisationParent = new Visualisation(mapOfBaseVisCA.get(parentIDBase));
					compareVisualisationParent = new Visualisation(mapOfCompareVisCA.get(parentIDCompare));					
					isEqual = isVisBeanPositionAndRotationEqual(baseVisualisationParent, compareVisualisationParent);	
					if (!isEqual) {
						return isEqual;
					}
				}
				if (isEqual) {
					for (int i = 0; i < baseParentNum - compareParentNum; i++) {
						parentIDBase = mapOfBaseParentID.get(parentIDBase);				
						baseVisualisationParent = new Visualisation(mapOfBaseVisCA.get(parentIDBase));			
						isEqual = isVisBeanPositonAndRotationNone(baseVisualisationParent);	
						if (!isEqual) {
							return isEqual;
						}
					}
				}
			} else if (baseParentNum < compareParentNum) {
				String parentIDBase = visID;
				String parentIDCompare = visID;
				Visualisation baseVisualisationParent;
				Visualisation compareVisualisationParent;
				for (int i = 0; i < baseParentNum; i++) {
					parentIDBase = mapOfBaseParentID.get(parentIDBase);
					parentIDCompare = mapOfCompareParentID.get(parentIDCompare);					
					baseVisualisationParent = new Visualisation(mapOfBaseVisCA.get(parentIDBase));
					compareVisualisationParent = new Visualisation(mapOfCompareVisCA.get(parentIDCompare));					
					isEqual = isVisBeanPositionAndRotationEqual(baseVisualisationParent, compareVisualisationParent);	
					if (!isEqual) {
						return isEqual;
					}
				}
				if (isEqual) {
					for (int i = 0; i < compareParentNum - baseParentNum; i++) {
						parentIDCompare = mapOfCompareParentID.get(parentIDCompare);				
						compareVisualisationParent = new Visualisation(mapOfCompareVisCA.get(parentIDCompare));			
						isEqual = isVisBeanPositonAndRotationNone(compareVisualisationParent);	
						if (!isEqual) {
							return isEqual;
						}
					}
				}
			} else if (baseParentNum == compareParentNum) {
				if (baseParentNum == 0) {
					isEqual = true;
				} else {
					String parentIDBase = visID;
					String parentIDCompare = visID;
					Visualisation baseVisualisationParent;
					Visualisation compareVisualisationParent;
					for (int i = 0; i < baseParentNum; i++) {
						parentIDBase = mapOfBaseParentID.get(parentIDBase);
						parentIDCompare = mapOfCompareParentID.get(parentIDCompare);					
						baseVisualisationParent = new Visualisation(mapOfBaseVisCA.get(parentIDBase));
						compareVisualisationParent = new Visualisation(mapOfCompareVisCA.get(parentIDCompare));					
						isEqual = isVisBeanPositionAndRotationEqual(baseVisualisationParent, compareVisualisationParent);	
						if (!isEqual) {
							return isEqual;
						}
					}
				}
				
			}	
	    } else {
	    	isEqual = false;	    	
	    }
		return isEqual;

	}
	
}
