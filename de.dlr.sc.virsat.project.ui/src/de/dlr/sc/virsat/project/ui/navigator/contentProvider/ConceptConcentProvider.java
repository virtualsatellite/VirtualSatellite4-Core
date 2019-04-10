/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.project.ui.navigator.contentProvider;

import java.util.Arrays;
import java.util.Objects;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ITreeContentProvider;

import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.project.editingDomain.util.VirSatTransactionalEditingDomainHelper;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatTransactionalAdapterFactoryContentProvider;

/**
 * Content provider for concepts and contained data.
 * @author muel_s8
 *
 */

public class ConceptConcentProvider extends VirSatTransactionalAdapterFactoryContentProvider implements ITreeContentProvider {
	
	@Override
	public Object[] getChildren(Object object) {
		return VirSatTransactionalEditingDomainHelper.tryRunExclusive(object, new RunnableWithResult.Impl<Object[]>() {
			public void run() {
				Object[] children = ConceptConcentProvider.super.getChildren(object);
				Object[] result = Arrays.stream(children).filter(Objects::nonNull).filter(child -> 
						child instanceof Concept
					||	child instanceof ATypeDefinition
					|| 	child instanceof StructuralElement
				).toArray();
				setResult(result);
			}
		});
	}

	private static ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	
	/**
	 * Constructor which initializes the adapterFactory already
	 */
	public ConceptConcentProvider() {
		super(adapterFactory);
	}
}
