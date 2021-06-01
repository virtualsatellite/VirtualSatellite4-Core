/*******************************************************************************
 * Copyright (c) 2021 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.factory;

import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindDerived;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanQuantityKindSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.IBeanQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvSwitch;

public class BeanQuantityKindFactory {

	private BeanQuantityKindFactorySwitch bufs = new BeanQuantityKindFactorySwitch();
	
	public IBeanQuantityKind<? extends AQuantityKind> getInstanceFor(AQuantityKind quantityKind) {
		IBeanQuantityKind<? extends AQuantityKind> beanQuantityKind = bufs.doSwitch(quantityKind);
		beanQuantityKind.setAQuantityKind(quantityKind);
		return beanQuantityKind;
	}
	
	private static class BeanQuantityKindFactorySwitch extends QudvSwitch<IBeanQuantityKind<? extends AQuantityKind>> {
		@Override
		public IBeanQuantityKind<? extends AQuantityKind> caseDerivedQuantityKind(DerivedQuantityKind object) {
			return new BeanQuantityKindDerived();
		}
		
		@Override
		public IBeanQuantityKind<? extends AQuantityKind> caseSimpleQuantityKind(SimpleQuantityKind object) {
			return new BeanQuantityKindSimple();
		}
	}
}
