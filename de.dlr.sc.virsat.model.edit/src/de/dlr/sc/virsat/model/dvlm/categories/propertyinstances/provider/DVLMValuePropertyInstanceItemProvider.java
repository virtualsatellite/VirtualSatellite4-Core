/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ValuePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.command.SetValuePropertyInstanceCommand;

/**
 * Overrides the getText method for value property instances
 * @author muel_s8
 *
 */
public class DVLMValuePropertyInstanceItemProvider extends ValuePropertyInstanceItemProvider {

	private static final String CUT_APPENDIX = "...";
	
	/**
	 * this class constructor is an instance of the factory and notifier 
	 * @param adapterFactory 
	 */
	public DVLMValuePropertyInstanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof ValuePropertyInstance) {
			ValuePropertyInstance vpi = (ValuePropertyInstance) object;
			String value = vpi.getValue() == null ? "" : vpi.getValue();

			//If string is multiline then just show the first line
			String lineSeperator = System.lineSeparator();
			if (value.indexOf(lineSeperator) > 0) {
				value = value.substring(0, value.indexOf(lineSeperator)) + CUT_APPENDIX;
			}
			
			return vpi.getType().getName() + ": " + value;
		}
		
		return super.getText(object);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) {
		return new SetValuePropertyInstanceCommand(domain, owner, feature, value);
	}
}
