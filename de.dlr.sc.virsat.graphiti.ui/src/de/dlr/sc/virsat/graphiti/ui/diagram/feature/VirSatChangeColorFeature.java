/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.ui.diagram.feature;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.dlr.sc.virsat.graphiti.util.DiagramHelper;

/**
 * Adds the option to change the color of an element in the context menu.
 * @author bell_er
 *
 */
public class VirSatChangeColorFeature extends AbstractCustomFeature {
	/**
	 * calls super
	 * @param fp the IFeatureProvider
	 *
	 */
	public VirSatChangeColorFeature(IFeatureProvider fp) {
		super(fp);
	}
	@Override
	public String getName() {
		return "Change Color";
	}
	@Override
	public String getDescription() {
		return "Change the Color ";
	}
	@Override
	public boolean canExecute(ICustomContext context) {
		for (PictogramElement pe : context.getPictogramElements()) {
			Object bo = getBusinessObjectForPictogramElement(pe);
			if (!DiagramHelper.hasBothWritePermission(bo, pe)) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void execute(ICustomContext context) {
		Shell s = new Shell(Display.getCurrent());
		s.setLocation(context.getX(), context.getY());
		ColorDialog colorDialog = new ColorDialog(s);
		RGB rgbColor = colorDialog.open();
		if (rgbColor != null) {
			IColorConstant colorConstant =
					new ColorConstant(rgbColor.red, rgbColor.green, rgbColor.blue);
			Color color = manageColor(colorConstant);
			PictogramElement[] pes = context.getPictogramElements();
			if (pes != null && pes.length == 1) {
				for (PictogramElement pe : pes) {
					if (pe instanceof Connection) {
						changeConnectionColor((Connection) pe, color);
					} else {
						changeGenericPictogramElementColor(pe, color);
					}
				}
			}
		}	
	}
	
	/**
	 * Changes the color of a generic pictorgram element
	 * @param pe the pictogram element
	 * @param color the target color
	 */
	private void changeGenericPictogramElementColor(PictogramElement pe, Color color) {
		GraphicsAlgorithm ga = pe.getGraphicsAlgorithm();
		ga.setBackground(color);
		for (GraphicsAlgorithm childGa : ga.getGraphicsAlgorithmChildren()) {
			if (childGa instanceof RoundedRectangle || childGa instanceof Rectangle) {
				childGa.setBackground(color);
			}
		}
	}
	
	/**
	 * Changes the color of a connection
	 * @param connection the connection
	 * @param color the target color
	 */
	private void changeConnectionColor(Connection connection, Color color) {
		GraphicsAlgorithm ga = connection.getGraphicsAlgorithm();
		// Connections & connection decorators use the foreground color to determine their color
		ga.setForeground(color);
		for (ConnectionDecorator connectionDecorator : connection.getConnectionDecorators()) {
			GraphicsAlgorithm connectionDecoratorGa = connectionDecorator.getGraphicsAlgorithm();
			if (!(connectionDecoratorGa instanceof Text)) {
				connectionDecoratorGa.setForeground(color);
			}
		}
	}
}

