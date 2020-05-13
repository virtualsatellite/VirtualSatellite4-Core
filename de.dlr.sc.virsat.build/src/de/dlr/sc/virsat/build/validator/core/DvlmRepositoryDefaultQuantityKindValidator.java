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

import org.eclipse.core.resources.IMarker;

import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AQudvTypeProperty;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.util.QudvUnitHelper;
import de.dlr.sc.virsat.model.dvlm.validator.IRepositoryValidator;

/** 
 * Validator that checks that a concept does not have a default quantity kind which is not defined in Unit Management
 */ 

public class DvlmRepositoryDefaultQuantityKindValidator extends ADvlmCoreValidator implements IRepositoryValidator { 
	 
	@Override 	
	public boolean validate(Repository repo) { 		
		boolean success = true; 		
		for (Concept concept: repo.getActiveConcepts()) { 	
			for (Category category: concept.getCategories()) { 	
				for (AProperty property: category.getAllProperties()) { 				
					if (property instanceof AQudvTypeProperty) { 					
						AQudvTypeProperty qudvProperty = (AQudvTypeProperty) property; 			
						if (qudvProperty.getQuantityKindName() != null) { 						
							String defaultQk = qudvProperty.getQuantityKindName(); 				
							QudvUnitHelper unitHelper = QudvUnitHelper.getInstance(); 				
							AQuantityKind qk = null; 						
							for (SystemOfQuantities soq: repo.getUnitManagement().getSystemOfUnit().getSystemOfQuantities()) { 	
								qk = unitHelper.getQuantityKindByName(soq, defaultQk); 							
								if (qk != null) { 				
									return true; 			
								} 						
							} 		
							if (qk == null) { 						
								vvmHelper.createDVLMValidationMarker(IMarker.SEVERITY_WARNING, "The concept \'" + concept.getFullQualifiedName() 
																							 + "\' contains the default quantity kind \'" + defaultQk
																							 + "\' in property \'" + property.getName()
																							 + "\' in category \'" + category.getName() + "\'.", concept); 					
								success = false; 					
							} 			
						} 				
					} 				
				} 	
			} 	
		} 		
		return success; 	
	}
}

