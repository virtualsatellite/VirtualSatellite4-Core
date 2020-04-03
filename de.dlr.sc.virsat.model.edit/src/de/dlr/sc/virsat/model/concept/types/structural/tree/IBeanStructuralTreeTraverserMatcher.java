/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.concept.types.structural.tree;

import java.util.Collection;

import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.tree.ITreeTraverserMatcher;

/**
 * Interface for plugging different logic into {@link BeanStructuralTreeTraverser}
 * For example for building a tree consisting only of nodes with a certain Category attached
 */
public interface IBeanStructuralTreeTraverserMatcher extends ITreeTraverserMatcher<IBeanStructuralElementInstance> {

	default Collection<IBeanStructuralElementInstance> getChildren(IBeanStructuralElementInstance treeNode) {
		return treeNode.getChildren(IBeanStructuralElementInstance.class);
	}
}
