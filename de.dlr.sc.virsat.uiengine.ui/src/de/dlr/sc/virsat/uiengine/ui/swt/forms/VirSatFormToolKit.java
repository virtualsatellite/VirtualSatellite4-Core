/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.swt.forms;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * A Formtoolkit with special settings for the use of Virtual Satellite.
 * E.g. improved scrolling behavior of tables in scrolledforms
 */
public class VirSatFormToolKit extends FormToolkit {

	public VirSatFormToolKit(Display display) {
		super(display);
	}

	public VirSatFormToolKit(FormColors colors) {
		super(colors);
	}
	
	@Override
	public Table createTable(Composite parent, int style) {
		Table table = super.createTable(parent, style);
		VirSatMouseWheelScrollRedirectListener redirectScrollListener =  new VirSatMouseWheelScrollRedirectListener(table);
		redirectScrollListener.init();
		table.addMouseWheelListener(redirectScrollListener);
		return table;
	}
	
	@Override
	public Tree createTree(Composite parent, int style) {
		Tree tree = super.createTree(parent, style);
		VirSatMouseWheelScrollRedirectListener redirectScrollListener =  new VirSatMouseWheelScrollRedirectListener(tree);
		redirectScrollListener.init();
		tree.addMouseWheelListener(redirectScrollListener);
		return tree;
	}
}
