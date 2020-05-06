/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.property;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;

public class BeanPropertyFactory {

	private Object object;

	public BeanPropertyFactory(Object object) {
		this.object = object;
	}

	/**
	 * @return
	 */
	public ABeanUnitProperty<?> getBeanUnitProperty() {
		if (object instanceof ATypeInstance) {
			BeanPropertyFloat bean = new BeanPropertyFloat();
			bean.setATypeInstance((ATypeInstance) object);
			return bean;
		}		
		return null;
	}

}
