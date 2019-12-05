/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.ui.provider;

import org.eclipse.jface.viewers.LabelProvider;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

/**
 * The label provider for showing active concepts in a repository
 * 
 * @author fisc_ph
 *
 */
public class ActiveConceptsLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof ActiveConceptConfigurationElement) {
			ActiveConceptConfigurationElement acce = (ActiveConceptConfigurationElement) element;
			return acce.getConceptNameWithVersion();
		} else if (element instanceof Concept) {
			Concept concept = (Concept) element;
			return ActiveConceptHelper.getConceptNameWithVersion(concept);
		}
		return super.getText(element);
	}
}
