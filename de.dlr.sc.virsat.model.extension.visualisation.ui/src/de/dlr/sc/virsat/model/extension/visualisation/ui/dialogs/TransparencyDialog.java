/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.ui.dialogs;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

/**
 * This class provides the dialog for selecting the percentage of transparency of a visualised object
 * @author wulk_ja
 *
 */
public class TransparencyDialog extends Dialog {

	private Scale scale;
	private int selection = 0;
	private static final int SCALE_MAXIMUM = 100;
	private static final int SCALE_MINIMUM = 0;
	/**
	 * Constructor
	 * @param parentShell 
	 */
	public TransparencyDialog(Shell parentShell) {
		super(parentShell);
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		
		Composite container = (Composite) super.createDialogArea(parent);
		scale = new Scale(container, SWT.HORIZONTAL);
		scale.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		scale.setMinimum(SCALE_MINIMUM);
		scale.setMaximum(SCALE_MAXIMUM);
		scale.setSelection(selection);

		scale.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				label.setText("Selected Transparency " + scale.getSelection() + "%");
				selection = scale.getSelection();
			}
		});
		
		label.setText("Selected Transparency " + scale.getSelection() + "%");
		return container;
	}
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Transparency");
	}
	/**
	 * Returns the selected value
	 * @return selected value
	 */
	public int getValue() {
		return selection;
	}
	/**
	 * Sets the initial value of the scale
	 * @param value initial value
	 */
	public void setInitialValue(double value) {
		selection = (int) (value * SCALE_MAXIMUM);
		
	}


}
