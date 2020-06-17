/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.hamcrest.Matcher;

public class SwtBotHyperlink extends AbstractSWTBotControl<Hyperlink> {

	public SwtBotHyperlink(Hyperlink hyperlink, Matcher<Hyperlink> matcher) throws WidgetNotFoundException {
		super(hyperlink, matcher);
	}
	
	@Override
	public AbstractSWTBot<Hyperlink> click() {
		// The click function doesnt cause any reactions on linux systems
		// so directly triggering the hyperlink by injecting an enter (carriage return) press on it
		syncExec(new VoidResult() {
			public void run() {
				Event event = createEvent();
				event.character = SWT.CR;
				widget.notifyListeners(SWT.KeyDown, event);
			}
		});
		
		return this;
	}
}
