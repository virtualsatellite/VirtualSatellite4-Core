/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.budget.cost.ui.snippet.chart.provider;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;

import de.dlr.sc.virsat.model.dvlm.categories.ICategoryAssignmentContainer;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.budget.cost.util.CostConceptHelper;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;

/**
 * Summary content provider that hands back all SEIs which either contain an
 * CostEquipment or a Cost Summary. On cost summary they actually stop diving deeper into
 * the tree to not display already summarized Equipments again
 * @author fisc_ph
 *
 */
public class CostConceptSummariesContentProvider extends VirSatFilteredWrappedTreeContentProvider {
	
	/**
	 * Constructor
	 * @param adapterFactory EMF based Adapter Factory
	 */
	public CostConceptSummariesContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		this.addClassFilter(StructuralElementInstance.class);
		this.addClassFilter(ICategoryAssignmentContainer.class);
		cch = new CostConceptHelper();
	}
	
	private CostConceptHelper cch;
	
	@Override
	public Object[] getElements(Object rootObject) {
		List<Object> contentElements = new LinkedList<>();
		LinkedList<Object> contentChildren = new LinkedList<>();
		contentChildren.addAll(Arrays.asList(super.getElements(rootObject)));
		
		// Crawl all children
		while (!contentChildren.isEmpty()) {
			Object firstObject = contentChildren.removeFirst();
			if (cch.initBeansForObject(firstObject) && (cch.costSummary != null)) {
				contentElements.add(firstObject);
			} else {
				if (cch.costEquipment != null) {
					contentElements.add(firstObject);
				}
				contentChildren.addAll(Arrays.asList(super.getChildren(firstObject)));
			}
		}
		
		return contentElements.toArray();
	}
};
