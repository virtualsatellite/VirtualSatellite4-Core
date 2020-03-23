/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ext.core.provider;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.dlr.sc.virsat.model.ext.core.core.provider.ConceptEditPlugin;

/**
 *
 */
public class ConceptEditPluginTest {

	@Test
	public void testCreateEditPlugin() {

		assertEquals("Plugin and edit resource locator should be the same objects",
				ConceptEditPlugin.INSTANCE.getPluginResourceLocator(), ConceptEditPlugin.getPlugin());
	}
}
