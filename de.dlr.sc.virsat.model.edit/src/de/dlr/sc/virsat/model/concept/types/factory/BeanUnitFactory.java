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

import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitAffineConversion;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitDerived;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitLinearConversion;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitPrefixed;
import de.dlr.sc.virsat.model.concept.types.qudv.BeanUnitSimple;
import de.dlr.sc.virsat.model.concept.types.qudv.IBeanUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvSwitch;

public class BeanUnitFactory {

	private BeanUnitFactorySwitch bufs = new BeanUnitFactorySwitch();
	
	public IBeanUnit getInstanceFor(AUnit unit) {
		IBeanUnit beanUnit = bufs.doSwitch(unit);
		beanUnit.setUnit(unit);
		return beanUnit;
	}
	
	private static class BeanUnitFactorySwitch extends QudvSwitch<IBeanUnit> {
		@Override
		public IBeanUnit caseAffineConversionUnit(AffineConversionUnit object) {
			return new BeanUnitAffineConversion();
		}
		
		@Override
		public IBeanUnit caseDerivedUnit(DerivedUnit object) {
			return new BeanUnitDerived();
		}
		
		@Override
		public IBeanUnit caseLinearConversionUnit(LinearConversionUnit object) {
			return new BeanUnitLinearConversion();
		}
		
		@Override
		public IBeanUnit casePrefixedUnit(PrefixedUnit object) {
			return new BeanUnitPrefixed();
		}
		
		@Override
		public IBeanUnit caseSimpleUnit(SimpleUnit object) {
			return new BeanUnitSimple();
		}
	}
	

}
