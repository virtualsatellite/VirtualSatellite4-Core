/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.shape;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.transaction.RecordingCommand;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.visualisation.model.Visualisation;
import de.dlr.sc.virsat.model.extension.visualisation.model.VisualisationListener;
import de.dlr.sc.virsat.project.editingDomain.VirSatEditingDomainRegistry;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;

/**
 * Observer class which should be called on changes from visualisation side (Vista) and update the DVLM model
 */
public class ShapeEditObserver implements IShapeEditObserver {
	
	private Map<String, Visualisation> modelBeans = new HashMap<>();
	
	@Override
	public void notifyShapeEdited(Shape editedShape) {
		Visualisation visualisationBean = modelBeans.get(editedShape.id);
		updateVisualisationBeanFromShape(visualisationBean, editedShape);
	}

	/**
	 * Method to update the visualization bean from a shape
	 * @param visualisationBean the visualisation bean to be updated
	 * @param shape the shape to be used for updating
	 */
	private void updateVisualisationBeanFromShape(Visualisation visualisationBean, Shape shape) {
		CategoryAssignment underlyingCa = visualisationBean.getTypeInstance();
		VirSatTransactionalEditingDomain ed = VirSatEditingDomainRegistry.INSTANCE.getEd(underlyingCa);

		disableNotificationsFromVisualisationListener(underlyingCa); //to avoid notification loop
		
		Command editVisualisationCaCommand =  new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				visualisationBean.readFromShape(shape);				
			}
		};
				
		ed.getVirSatCommandStack().executeNoUndo(editVisualisationCaCommand);
		
		enableNotificationsFromVisualisationListener(underlyingCa);
	}
	
	/**
	 * Disable VisualisationListener for the given CategoryAssignment
	 * @param ca category assignment of which to disable the listener.
	 */
	private void disableNotificationsFromVisualisationListener(CategoryAssignment ca) {
		for (Adapter adapter : ca.eAdapters()) {
			if (adapter instanceof VisualisationListener) {
				((VisualisationListener) adapter).disableListening();
			}
		}
	}

	/**
	 * Enable VisualisationListener for the given CategoryAssignment
	 * @param ca the category assignment where to activate the listener
	 */
	private void enableNotificationsFromVisualisationListener(CategoryAssignment ca) {
		for (Adapter adapter : ca.eAdapters()) {
			if (adapter instanceof VisualisationListener) {
				((VisualisationListener) adapter).enableListening();
			}
		}
	}

	/**
	 * Adds a bean to the map of observable beans
	 * @param id the id of the visualisation bean
	 * @param bean the actual visualisaton bean
	 */
	public void startObservingBean(String id, Visualisation bean) {
		modelBeans.put(id, bean);
	}

	/**
	 * Removes a bean from the map of observable beans
	 * @param id the id of the bean to be removed
	 */
	public void stopObservingBeanWithId(String id) {
		modelBeans.remove(id);
	}
}
