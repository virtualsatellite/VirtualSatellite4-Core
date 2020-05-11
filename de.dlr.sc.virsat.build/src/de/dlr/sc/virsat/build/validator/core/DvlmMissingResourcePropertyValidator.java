/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.build.validator.core;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;

/**
 * Implementation of a validator that checks if a document linked to the data model 
 * exist in file system or not. checking for missing documents
 *  
 * @author desh_me
 *
 */

public class DvlmMissingResourcePropertyValidator extends ADvlmCoreValidator implements IStructuralElementInstanceValidator {

		
	@Override
	public boolean validate(StructuralElementInstance sei) {
		boolean validationSuccessful = true;
	    
		Set<ResourcePropertyInstance> iResProps = new HashSet<>();
		// Loop over all category assignemts to find all resourceproperty instances
		EcoreUtil.getAllContents(sei.getCategoryAssignments(), true).forEachRemaining((object) -> {
			if (object instanceof ResourcePropertyInstance) {
				ResourcePropertyInstance iInstance = (ResourcePropertyInstance) object;
				iResProps.add(iInstance);
			}
		});
	
		for (ResourcePropertyInstance iResourceProperty : iResProps) {
			BeanPropertyResource beanPropertyResource = new BeanPropertyResource(iResourceProperty);
			IFile resourceFile = beanPropertyResource.getFile();
			
			if (resourceFile != null && !resourceFile.exists()) {
		    	vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "The file \'" + resourceFile.getName() + "\' does not exist.", iResourceProperty);
		    	validationSuccessful = false;
		    }
		}
		
		return validationSuccessful;
	}

}
