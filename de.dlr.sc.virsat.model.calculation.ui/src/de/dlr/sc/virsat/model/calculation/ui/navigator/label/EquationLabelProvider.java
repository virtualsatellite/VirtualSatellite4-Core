/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.navigator.label;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;

import com.google.inject.Injector;

import de.dlr.sc.virsat.model.calculation.ui.internal.CalculationActivator;
import de.dlr.sc.virsat.model.calculation.ui.labeling.EquationDSLLabelProvider;
import de.dlr.sc.virsat.model.concept.provider.DVLMConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.DVLMPropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;

/**
 * Label provider for labeling equations and appearing sub expressions.
 * @author muel_s8
 *
 */

public class EquationLabelProvider extends VirSatTransactionalAdapterFactoryLabelProvider implements ILabelProvider {

	private static ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	private Injector injector = CalculationActivator.getInstance().getInjector(CalculationActivator.DE_DLR_SC_VIRSAT_MODEL_CALCULATION_EQUATIONDSL);
	private ILabelProvider delegateLabelProvider = (ILabelProvider) injector.getInstance(EquationDSLLabelProvider.class);
	
	
	@Override
	public String getText(Object object) {
		
		if (object instanceof EquationSection) {
			return EquationSection.class.getSimpleName();
		}
		
		String text = delegateLabelProvider.getText(object);
		if (text != null) {
			return text;
		}
		
		return super.getText(object);
	}
	
	/**
	 * Constructor initializing the adapterfactory
	 */
	public EquationLabelProvider() {
		super(adapterFactory);
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMPropertyinstancesItemProviderAdapterFactory());
	}
}
