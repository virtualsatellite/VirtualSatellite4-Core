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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
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
	public static final int FOUR_LINES_HEIGHT = 4;
	
	
	private String title;
	private String message;
	private String defaultCommitMessage;
	private Text text;

	/**
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
			defaultCommitMessage = proposedComment;
		}
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (title != null) {
			shell.setText(title);
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		Label label = new Label(composite, SWT.WRAP);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, true));
		label.setText(message);

		IPreferenceStore store = SVNTeamUIPlugin.instance().getPreferenceStore();
		String[] templates = FileUtility.decodeStringToArray(SVNTeamPreferences.getCommentTemplatesString(store, SVNTeamPreferences.COMMENT_TEMPLATES_LIST_NAME));
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setItems(templates);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
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
		GridData textLayoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		textLayoutData.heightHint = convertHeightInCharsToPixels(FOUR_LINES_HEIGHT);
		text.setLayoutData(textLayoutData);

		if (defaultCommitMessage == null) {
			combo.select(0);
			templateChanged(combo);
		} else {
			text.setText(defaultCommitMessage);
		}

		text.setFocus();
		text.selectAll(); 

		return composite;
	}

	/**
	 * Method for reacting to a change in the template combo box. 
	 * Fills in the selected template into the text box.
	 * @param templateCombo the template combo box where a changes has occurred
	 */
	private void templateChanged(Combo templateCombo) {
		String template = templateCombo.getText();
		String commitMessage = template.replaceAll(TEMPLATE_USER_NAME, UserRegistry.getInstance().getUserName());
		text.setText(commitMessage);
	}

	/**
	 * @return the commit message
	 */
	public String getCommitMessage() {
		return text.getText();
	}
}

