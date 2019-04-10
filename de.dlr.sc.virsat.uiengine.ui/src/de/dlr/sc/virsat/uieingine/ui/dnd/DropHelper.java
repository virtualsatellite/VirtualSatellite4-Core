/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uieingine.ui.dnd;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Control;

/**
 * Use this class to support drag and drop functionality from the navigator into
 * the editor.
 * @author muel_s8
 *
 */

public class DropHelper {
	
	/**
	 * Hidden private constructor
	 */
	
	private DropHelper() {
	}
	
	/**
	 * This creates a drop target for the passed UI element and adds the passed drop listener
	 * to it
	 * @param control a ui element that should support dropping
	 * @param aDropTargetListener a concrete class for ADropTargetListener
	 * @return the created drop target
	 */
	
	public static DropTarget createDropTarget(Control control, ADropSelectionTargetListener aDropTargetListener) {
		final int OPERTATIONS = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
		DropTarget target = new DropTarget(control, OPERTATIONS);
		Transfer[] transfers = new Transfer[] { LocalSelectionTransfer.getTransfer() };
		target.setTransfer(transfers);
		target.addDropListener(aDropTargetListener);
		return target;
	}
}
