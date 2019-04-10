/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.ui.wizards;

import org.eclipse.nebula.widgets.treemapper.ISemanticTreeMapperSupport;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * @author bell_er
 *
 */
public class VirsatSemanticTreeMapperSupport implements ISemanticTreeMapperSupport<StructuralElementInstanceMappingBean, StructuralElementInstance, StructuralElementInstance> {
	@Override
	public StructuralElementInstanceMappingBean createSemanticMappingObject(StructuralElementInstance leftSEI, StructuralElementInstance rightSEI) {
		StructuralElementInstanceMappingBean mb = new StructuralElementInstanceMappingBean(); 
		mb.setLeft(leftSEI);
		mb.setRight(rightSEI); 
		return mb;
	}
	@Override
	public StructuralElementInstance resolveLeftItem(StructuralElementInstanceMappingBean semanticMappingObject) {
		return semanticMappingObject.getLeft();
	}
	@Override
	public StructuralElementInstance resolveRightItem(StructuralElementInstanceMappingBean semanticMappingObject) {
		return semanticMappingObject.getRight();
	}
}
