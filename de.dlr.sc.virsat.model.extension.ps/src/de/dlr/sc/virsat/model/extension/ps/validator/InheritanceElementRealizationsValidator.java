/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import de.dlr.sc.virsat.build.validator.core.ADvlmCoreValidator;
import de.dlr.sc.virsat.build.validator.external.IRepositoryValidator;
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;

/**
 * Class for checking that ElementRealizations are only inherited once
 * @author lobe_el
 *
 */
public class InheritanceElementRealizationsValidator extends ADvlmCoreValidator implements IRepositoryValidator {

	@Override
	public boolean validate(Repository repo) {
		boolean validationSuccessful = true;
		Set<StructuralElementInstance> rootAssemblyTrees = new HashSet<StructuralElementInstance>();
		Map<StructuralElementInstance, Set<StructuralElementInstance>> superERtoSeis = new HashMap<StructuralElementInstance, Set<StructuralElementInstance>>();
		
		for (StructuralElementInstance rootEntity : repo.getRootEntities()) {
			if (rootEntity.getType().getName().equals("AssemblyTree")) {
				rootAssemblyTrees.add(rootEntity);
			}
		}
		
		for (StructuralElementInstance assemblyTree : rootAssemblyTrees) {
			EList<StructuralElementInstance> allChildren = assemblyTree.getDeepChildren();
			for (StructuralElementInstance child : allChildren) {
				Set<StructuralElementInstance> superErs = new HashSet<StructuralElementInstance>();
				for (StructuralElementInstance superSei : ((StructuralElementInstance) child).getSuperSeis()) {
					if (superSei.getType().getName().equals("ElementRealization")) {
						superErs.add(superSei);
					}
				}
				for (StructuralElementInstance er : superErs) {
					if (!superERtoSeis.containsKey(er)) {
						superERtoSeis.put(er, new HashSet<StructuralElementInstance>());
					}
					superERtoSeis.get(er).add((StructuralElementInstance) child);
				}
			}
		}
		
		for (StructuralElementInstance er : superERtoSeis.keySet()) {
			if (superERtoSeis.get(er).size() > 1) {
				Set<StructuralElementInstance> seis = superERtoSeis.get(er);
				for (StructuralElementInstance sei : seis) {
					vvmHelper.createInheritanceValidationMarker(IMarker.SEVERITY_WARNING, "The SEI \'" + sei.getFullQualifiedInstanceName() + "\' inherits from an ElementRealization which is already inherited from elsewhere", sei, er);
				}
				validationSuccessful = false;
			}
		}
		
		return validationSuccessful;
	}

}
