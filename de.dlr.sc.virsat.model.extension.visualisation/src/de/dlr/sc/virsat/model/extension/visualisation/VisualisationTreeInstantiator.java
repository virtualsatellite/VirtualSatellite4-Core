/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation;

import java.util.List;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.visualisation.delta.DeltaShapeHelper;
import de.dlr.sc.virsat.model.extension.visualisation.delta.VisualisationDeltaModel;
import de.dlr.sc.virsat.model.extension.visualisation.model.VisualisationListener;
import de.dlr.sc.virsat.model.extension.visualisation.shape.ShapeEditObserver;
import de.dlr.sc.virsat.model.extension.visualisation.shape.ShapeHelper;

/**
 * 
 * @author kova_an
 *
 */
public class VisualisationTreeInstantiator {
	
	private ShapeHelper shapeHelper;
	
	private Repository repository;
	private VisualisationListener listener;
	private List<StructuralElementInstance> rootSeisToVisualise = null;
	
	/**
	 * Creates new VisualisationTreeInstantiator
	 * @param visualisationTreeManager is used to create visualisation objects
	 * @param repository a repository containing StructuralElementInstances to build visualisation tree from
	 */
	public VisualisationTreeInstantiator(IVisualisationTreeManager visualisationTreeManager, Repository repository) {
		this(visualisationTreeManager, repository, null);
	}
	
	/**
	 * Creates new VisualisationTreeInstantiator
	 * @param visualisationTreeManager is used to create visualisation objects
	 * @param repository a repository containing StructuralElementInstances to build visualisation tree from
	 * @param deltaModel delta model from comparison with some other project version
	 */
	public VisualisationTreeInstantiator(IVisualisationTreeManager visualisationTreeManager, Repository repository, VisualisationDeltaModel deltaModel) {
		ShapeEditObserver shapeEditObserver = new ShapeEditObserver();
		if (deltaModel != null) {
			shapeHelper = new DeltaShapeHelper(visualisationTreeManager, shapeEditObserver, deltaModel);
		} else {
			shapeHelper = new ShapeHelper(visualisationTreeManager, shapeEditObserver);
		}
		visualisationTreeManager.setShapeEditObserver(shapeEditObserver);
		this.repository = repository;
	}

	/**
	 * Sets root entities to visualise. null means no filtering
	 * @param rootSeisToVisualise a list of SEIs that should be visualized
	 */
	public void setRootSeisToVisualise(List<StructuralElementInstance> rootSeisToVisualise) {
		this.rootSeisToVisualise = rootSeisToVisualise;
	}
	
	/**
	 * @return if rootSeisToVisualise is set, returns it, otherwise returns all root entities in the repository
	 */
	private List<StructuralElementInstance> getRootSeisToVisualise() {
		return rootSeisToVisualise != null ? rootSeisToVisualise : repository.getRootEntities();
	}
	
	/**
	 * This method creates visualisation tree based on SEIs contained in the repository
	 * and which have Visualisation category attached
	 */
	public void createVisualisationObjects() {
		getRootSeisToVisualise().forEach(sei -> shapeHelper.traverseTreeAndCreateShapes(sei));
	}

	/**
	 * Attach visualisation listeners to the repository and all contained StructuralElementInstances
	 */
	public void attachVisualisationChangeListeners() {
		listener = new VisualisationListener(shapeHelper);
		repository.eAdapters().add(listener);
		getRootSeisToVisualise().forEach(sei -> sei.eAdapters().add(listener));
	}
	
	/**
	 * Detach visualisation listeners from the repository and all contained StructuralElementInstances
	 */
	public void removeVisualisationChangeListeners() {
		repository.eAdapters().remove(listener);
		repository.getRootEntities().forEach(sei -> sei.eAdapters().remove(listener));
	}
}
