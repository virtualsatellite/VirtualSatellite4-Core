/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.statemachines.ui.diagram.features.abstractConnection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import de.dlr.sc.virsat.graphiti.ui.diagram.feature.VirSatAddConnectionFeature;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.statemachines.model.State;
import de.dlr.sc.virsat.model.extension.statemachines.ui.Activator;

/**
 * Feature for adding connections in an state machine diagram. 
 * @author bell_Er 
 * */
public abstract class AbstractConnectionAddFeature extends VirSatAddConnectionFeature {

	private static final IColorConstant CONNECTION_FOREGROUND = new ColorConstant(98, 131, 167);
	private static final double DECORATOR_LOCATION = 0.5;
	private static final int LABEL_X = 10;
	/**	
	 * Default constructor.	 
	 * @param fp the feature provider.	
	 */
	public AbstractConnectionAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public PictogramElement add(IAddContext context) {
		CategoryAssignment ca = (CategoryAssignment) context.getNewObject();
		
		ABeanCategoryAssignment cc = null;
		try {
			cc = (ABeanCategoryAssignment) new BeanCategoryAssignmentFactory().getInstanceFor(ca);
		} catch (CoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "failed to create statemachinesconnection", e));
		}

		IPeCreateService peCreateService = Graphiti.getPeCreateService();

		Anchor fromAnchor = null;
		Anchor toAnchor = null;

		if (context instanceof IAddConnectionContext) {
			IAddConnectionContext connectionContext = (IAddConnectionContext) context;
			fromAnchor = connectionContext.getSourceAnchor();
			toAnchor = connectionContext.getTargetAnchor();
		} else {
			// If we arent given a context that has the anchors ready, we search for fitting anchors
			// Grab some fitting interfaceEnd matching the interface from as a start point
			// And some fitting interfaceEnd matching the interface to as an end point

			State[] states = getStates(ca);

			final PictogramElement[] pes = getFeatureProvider().getDiagramTypeProvider().getNotificationService().calculateRelatedPictogramElements(states);

			for (PictogramElement pe : pes) {
				if (pe instanceof Anchor) {
					State state = (State) getBusinessObjectForPictogramElement(pe);
					if (state.getTypeInstance() == states[0].getTypeInstance()) {
						fromAnchor = (Anchor) pe;
					}

					if (state.getTypeInstance() == states[1].getTypeInstance()) {
						toAnchor = (Anchor) pe;
					}
				}
			}
		}

		Connection connection = null;
		if (fromAnchor != null && toAnchor != null) {
			// Create a Polyline connection

			connection = peCreateService.createFreeFormConnection(getDiagram());
			connection.setStart(fromAnchor);
			connection.setEnd(toAnchor);

			IGaService gaService = Graphiti.getGaService();
			Polyline polyline = gaService.createPolyline(connection);
			polyline.setLineStyle(LineStyle.DASH);
			polyline.setLineWidth(2);
			polyline.setForeground(manageColor(getLineColor()));
			link(connection, cc);

			// Create text element containing name of interface
			if (setTextDecorator()) {
				ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(connection, true, DECORATOR_LOCATION, true);
				Text text = gaService.createDefaultText(getDiagram(), textDecorator);
				text.setForeground(manageColor(IColorConstant.BLACK));
				gaService.setLocation(text, LABEL_X, 0);
				String transitionName = ca.getName();
				text.setValue(transitionName);
				link(text.getPictogramElement(), cc);

			}
			// Create arrow
			ConnectionDecorator cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);
			DiagramHelper.createArrow(cd, manageColor(CONNECTION_FOREGROUND));
		}

		return connection;
	}
	
	/**
	 * to be overrided when text is not needed
	 * @return true or false
	 */
	protected boolean setTextDecorator() {
		return true;
	}

	/**
	 * the line color for constraints
	 * @return the color
	 */
	protected IColorConstant getLineColor() {
		return CONNECTION_FOREGROUND;
	}

	/**
	 * gets the states to draw a connection between them
	 * @param ca the category assignment as a link between two states
	 * @return states first and second state
	 */
	public abstract State[] getStates(CategoryAssignment ca);

}
