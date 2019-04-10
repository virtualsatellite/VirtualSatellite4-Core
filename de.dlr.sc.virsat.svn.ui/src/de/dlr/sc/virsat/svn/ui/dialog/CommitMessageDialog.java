/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.svn.ui.dialog;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.team.svn.core.utility.FileUtility;
import org.eclipse.team.svn.ui.SVNTeamUIPlugin;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;

import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;

/**
 * a simple commit Dialog that let's the user enter a commit message
 * @author scha_vo
 *
 */
public class CommitMessageDialog extends Dialog {
	
	public static final String TEMPLATE_USER_NAME = "@user";
	
	private String title;
	private String message;
	private String value;
	private Text text;

	/**
	 * public constructor
	 * @param parentShell the parent shell of the dialog
	 * @param dialogTitle the title string
	 * @param dialogMessage the commit message 
	 * @param proposedComment the default proposed text of the commit message 
	 */
	public CommitMessageDialog(Shell parentShell, String dialogTitle, String dialogMessage, String proposedComment) {
		super(parentShell);
		this.title = dialogTitle;
		message = dialogMessage;
		if (proposedComment != null) {
			value = proposedComment;
		}

	}
	
	/**
	 * method to configure the shell of the dialog
	 * @param shell the shell
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (title != null) {
			shell.setText(title);
		}
	}

	/**
	 * method which creates the buttons of the dialog
	 * @param parent the composite where to add the buttons
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

		text.setFocus();
		if (value != null) {
			text.setText(value);
			text.selectAll();
		}
	}

	// CHECKSTYLE:OFF
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		composite.setLayout(null);
		composite.setSize(400, 200);
		
		Label label = new Label(composite, SWT.WRAP);
		label.setText(message);
		label.setBounds(12, 6, 360, 14);
		label.setFont(parent.getFont());

		IPreferenceStore store = SVNTeamUIPlugin.instance().getPreferenceStore();
		String[] templates = FileUtility.decodeStringToArray(SVNTeamPreferences.getCommentTemplatesString(store, SVNTeamPreferences.COMMENT_TEMPLATES_LIST_NAME));
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(12, 24, 347, 14);
		combo.setItems(templates);
		
		combo.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				templateChanged(combo);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				templateChanged(combo);
			}
		});
		
		text = new Text(composite, SWT.MULTI | SWT.WRAP | SWT.BORDER);
		text.setBounds(12, 52, 347, 102);

		text.setFocus();
		
		if (value == null) {
			combo.select(0);
			templateChanged(combo);
		}
		
		text.setText(value);
		text.selectAll(); 

		applyDialogFont(composite);
		return composite;
	}
	// CHECKSTYLE:ON

	/**
	 * Method for reacting to a change in the template combo box. 
	 * Fills in the selected template into the text box.
	 * @param templateCombo the template combo box where a changes has occured
	 */
	private void templateChanged(Combo templateCombo) {
		String template = templateCombo.getText();
		value = template.replaceAll(TEMPLATE_USER_NAME, UserRegistry.getInstance().getUserName());
		text.setText(value);
	}
	
	/**
	 * method for the code whcih will be executed when the user presses the button
	 * @param buttonId the ID of the button
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			value = text.getText();
		} else {
			value = null;
		}
		super.buttonPressed(buttonId);
	}

	/**
	 * getter method to retrieve the text
	 * @return Text the text
	 */
	public Text getText() {
		return text;
	}

	/**
	 * getter method to retrieve the value 
	 * @return String value 
	 */
	public String getValue() {
		return value;
	}
}

