/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.delta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.IVisualisationTreeManager;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.shape.Shape;
import de.dlr.sc.virsat.model.extension.visualisation.shape.ShapeEditObserver;
import de.dlr.sc.virsat.model.extension.visualisation.shape.ShapeHelper;

/**
 * A version of ShapeHelper that process visualisation deltas
 */
public class DeltaShapeHelper extends ShapeHelper {
	
	private static final String CLONE_SHAPE_SUFFIX = "_clone";
	private static final String GHOST_SHAPE_SUFFIX = "_ghost";

	private Map<String, Integer> colorChanges = new HashMap<>();
	private Map<String, Shape> cloneShapes = new HashMap<>();
	private List<GhostShapeDelta> ghostShapeDeltas;
	private Set<String> ghostShapeIds = new HashSet<>();
	
	/**
	 * Constructor
	 * @param visualisationTreeManager The VisualizationTreeManager to connetc to
	 * @param shapeEditObserver The Edit Observer for detecting changes
	 * @param deltaModel Visualisation Deltas
	 */
	public DeltaShapeHelper(IVisualisationTreeManager visualisationTreeManager, ShapeEditObserver shapeEditObserver, VisualisationDeltaModel deltaModel) {
		super(visualisationTreeManager, shapeEditObserver);
		
		for (Map.Entry<String, ColorDelta> entry: deltaModel.colorDeltas.entrySet()) {
			colorChanges.put(entry.getValue().shapeId, entry.getValue().newColor);
		}
		
		for (CloneShapeDelta cloneDelta : deltaModel.cloneShapeDeltas) {
			cloneShapes.put(cloneDelta.shape.id, cloneDelta.shape);
		}
		
		ghostShapeDeltas = deltaModel.ghostShapeDeltas;
		for (GhostShapeDelta ghostDelta : ghostShapeDeltas) {
			ghostShapeIds.add(ghostDelta.shape.id);
		}
	}
	
	@Override
	protected Shape createShapeFromVisualisationBean(Visualisation visBean, String shapeId) {
		Shape shape = super.createShapeFromVisualisationBean(visBean, shapeId);
		if (colorChanges.containsKey(shapeId)) {
			shape.color = colorChanges.get(shapeId);
		}
		return shape;
	}
	
	@Override
	protected void createShape(Visualisation visBean, String shapeId) {
		super.createShape(visBean, shapeId);
		if (cloneShapes.containsKey(shapeId)) {
			Shape cloneShape = cloneShapes.get(shapeId);
			cloneShape.id += CLONE_SHAPE_SUFFIX;
			visualisationTreeManager.createShape(cloneShape);
		}
	}
	
	@Override
	protected void setShapeParent(String parentShapeId, String shapeId) {
		super.setShapeParent(parentShapeId, shapeId);
		if (cloneShapes.containsKey(shapeId)) {
			visualisationTreeManager.setParent(shapeId + CLONE_SHAPE_SUFFIX, parentShapeId);
		}
	}
	
	@Override
	public void traverseTreeAndCreateShapes(StructuralElementInstance rootSei) {
		super.traverseTreeAndCreateShapes(rootSei);
		
		pauseVisTreeManagerUpdateSending();
		for (GhostShapeDelta ghostShapeDelta : ghostShapeDeltas) {
			Shape ghostShape = ghostShapeDelta.shape;
			ghostShape.id += GHOST_SHAPE_SUFFIX;
			visualisationTreeManager.createShape(ghostShape);
		}

		for (GhostShapeDelta ghostShapeDelta : ghostShapeDeltas) {
			if (ghostShapeDelta.parentId != null) {
				String ghostShapeId = ghostShapeDelta.shape.id;
				String ghostShapeParentId = ghostShapeDelta.parentId;
				if (ghostShapeIds.contains(ghostShapeDelta.parentId)) {
					ghostShapeParentId += GHOST_SHAPE_SUFFIX;
				}
				visualisationTreeManager.setParent(ghostShapeId, ghostShapeParentId);
			}
		}
		resumeVisTreeManagerUpdateSending();
	}
}
