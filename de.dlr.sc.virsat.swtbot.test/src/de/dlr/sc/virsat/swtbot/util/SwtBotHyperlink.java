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

import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
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
		// Clicking on hyperlinks that are not directly in view (which is the case here) has no effect
		// Setting the widget into focus and then hitting enter as a workaround
		setFocus();
		//pressShortcut(Keystrokes.CR);
		pressShortcut(Keystrokes.LF);
		return pressShortcut(Keystrokes.ESC);
	}
}
