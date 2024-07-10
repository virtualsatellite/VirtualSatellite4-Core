/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.util;

import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IStartup;

public class AutoCheckoutStartup implements IStartup {

	@Override
	public void earlyStartup() {

		boolean autoCheckoutFlag = false;

		// Check for -remote flag in application arguments
		String[] args = Platform.getApplicationArgs();
		for (String arg : args) {
			if (arg.equals("-autocheckout")) {
				autoCheckoutFlag = true;
				break;
			}
		}
		if (autoCheckoutFlag) {
			var autoCheckout = new AutoCheckout();
			autoCheckout.storeCredentials();
			autoCheckout.performAutocheckout();
		}
	}
}