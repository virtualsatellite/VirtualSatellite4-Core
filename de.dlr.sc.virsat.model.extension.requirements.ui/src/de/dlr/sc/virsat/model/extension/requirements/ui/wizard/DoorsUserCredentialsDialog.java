/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.requirements.ui.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DoorsUserCredentialsDialog extends Dialog {
	private static final String TITLE = "Please provide your Doors User ID and password";
	private static final int WIDTH = 450;
	private static final int HEIGHT = 300;

	private Text txtUser;
	private Text txtPassword;
	private String user = "";
	private String password = "";

	public DoorsUserCredentialsDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);
		
		Shell shell = getShell();
		shell.setText(TITLE);
		
		Label lblUser = new Label(container, SWT.NONE);
		lblUser.setText("User ID:");

		txtUser = new Text(container, SWT.BORDER);
		txtUser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtUser.setText(user);
		txtUser.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String userText = textWidget.getText();
			user = userText;
		});

		Label lblPassword = new Label(container, SWT.NONE);
		GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false);
		data.horizontalIndent = 1;
		lblPassword.setLayoutData(data);
		lblPassword.setText("Password:");

		txtPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		txtPassword.setText(password);
		txtPassword.addModifyListener(e -> {
			Text textWidget = (Text) e.getSource();
			String passwordText = textWidget.getText();
			password = passwordText;
		});
		return container;
	}

	// override method to use "Login" as label for the OK button
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Login", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(WIDTH, HEIGHT);
	}

	@Override
	protected void okPressed() {
		user = txtUser.getText();
		password = txtPassword.getText();
		super.okPressed();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}