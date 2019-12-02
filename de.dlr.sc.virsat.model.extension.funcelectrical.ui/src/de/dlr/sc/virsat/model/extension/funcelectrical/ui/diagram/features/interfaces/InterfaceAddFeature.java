/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.ui.diagram.features.interfaces;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
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
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;

/**
 * Feature for adding interfaces to the interface diagram.
 * @author muel_s8
 *
 */

public class InterfaceAddFeature extends VirSatAddConnectionFeature {

	private static final IColorConstant INTERFACE_FOREGROUND = new ColorConstant(98, 131, 167);
	
	private static final double DECORATOR_LOCATION = 0.5;
	private static final int LABEL_X = 10;
	
	/**
	 * Default constructor.
	 * @param fp the feature provider.
	 */
	
	public InterfaceAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Interface i = new Interface((CategoryAssignment) context.getNewObject());
		
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
			
			InterfaceEnd[] interfaceEnds = { i.getInterfaceEndFrom(), i.getInterfaceEndTo() };
			
			final PictogramElement[] pes = getFeatureProvider().getDiagramTypeProvider().getNotificationService().calculateRelatedPictogramElements(interfaceEnds);
			
			for (PictogramElement pe : pes) {
				if (pe instanceof Anchor) {
					InterfaceEnd ie = (InterfaceEnd) getBusinessObjectForPictogramElement(pe);
					if (ie.equals(i.getInterfaceEndFrom())) {
						fromAnchor = (Anchor) pe;
					}
					
					if (ie.equals(i.getInterfaceEndTo())) {
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
			polyline.setLineWidth(2);
			polyline.setForeground(manageColor(INTERFACE_FOREGROUND));
			link(connection, i);
			
			// Create text element containing name of interface
			ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(connection, true, DECORATOR_LOCATION, true);
			Text text = gaService.createDefaultText(getDiagram(), textDecorator);
			text.setForeground(manageColor(IColorConstant.BLACK));
			gaService.setLocation(text, LABEL_X, 0);
			String interfaceName = i.getName();
			text.setValue(interfaceName);
			link(text.getPictogramElement(), i);
			
			// Create arrow
			ConnectionDecorator cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);
			DiagramHelper.createArrow(cd, manageColor(INTERFACE_FOREGROUND));
		}
		
		return connection;
	}
}
