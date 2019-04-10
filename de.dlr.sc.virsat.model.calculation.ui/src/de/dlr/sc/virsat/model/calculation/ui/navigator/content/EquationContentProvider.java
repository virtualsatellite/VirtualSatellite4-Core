/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.navigator.content;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;

import de.dlr.sc.virsat.model.dvlm.calculation.AExpression;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;

/**
 * This class helps to display the equation section in an equation section container
 * @author muel_s8
 *
 */
public class EquationContentProvider extends VirSatTransactionalAdapterFactoryContentProvider {

	@Override
	public Object[] getChildren(Object object) {		
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Object[]>() {
			public void run() {

				Object[] superObjects = EquationContentProvider.super.getChildren(object);
				Set<Object> objects = new HashSet<>();
				
				if (object instanceof IEquationSectionContainer) {
					IEquationSectionContainer equationSectionContainer = (IEquationSectionContainer) object;
					EquationSection equationSection = equationSectionContainer.getEquationSection();
					if (equationSection != null) {
						objects.add(equationSection);
					} 
				}

				objects.addAll(Arrays.asList(superObjects));
				Object[] result = objects.stream().filter(Objects::nonNull).filter(child -> 
						child instanceof EquationSection 
					|| 	child instanceof Equation 
					|| 	child instanceof AExpression
				).toArray();
				setResult(result);
				
			}
		});
	}
	
	@Override
	public boolean hasChildren(Object object) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Boolean>() {
			public void run() {
				boolean hasChildren = getChildren(object).length > 0;
				setResult(hasChildren);
			}
		});
	}
	
	private static ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	
	/**
	 * Constructor which initializes the adapterFactory already
	 */
	public EquationContentProvider() {
		super(adapterFactory);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
