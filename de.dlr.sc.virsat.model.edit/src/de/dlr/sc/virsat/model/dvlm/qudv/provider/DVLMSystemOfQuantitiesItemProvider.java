/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.provider;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;

/**
 * DVLM implementation of the SystemOfQuantitiesItemProvider. Implements a special policy
 * for deleting quantity kinds, namely that we do not delete them if they are still being
 * referenced.
 * @author muel_s8
 *
 */

public class DVLMSystemOfQuantitiesItemProvider extends SystemOfQuantitiesItemProvider {

	/**
	 * Default constructor
	 * @param adapterFactory the adapter factory
	 */
	
	public DVLMSystemOfQuantitiesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection) {
		
		for (Object obj : collection) {
			// In case of a quantity kind we do not allow the deletion if there exists some reference
			if (obj instanceof AQuantityKind) {
				AQuantityKind quantityKind = (AQuantityKind) obj;
				Collection<Setting> usages = EcoreUtil.UsageCrossReferencer.find(quantityKind, domain.getResourceSet());
				if (!usages.isEmpty()) {
					return UnexecutableCommand.INSTANCE;
				}
			}
		}

		return super.createRemoveCommand(domain, owner, feature, collection);
	}
}
