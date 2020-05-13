/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.dvlm.tree;

import java.util.Collection;

import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;


/**
 * Interface for plugging different logic into {@link BeanStructuralTreeTraverser} or a typed {@link TreeTraverser}
 * For example for building a tree consisting only of nodes with a certain Category attached
 */
public interface IStructuralElementInstanceTreeTraverserMatcher extends ITreeTraverserMatcher<StructuralElementInstance> {
	
	default Collection<StructuralElementInstance> getChildren(StructuralElementInstance treeNode) {
		return treeNode.getChildren();
	}
}
