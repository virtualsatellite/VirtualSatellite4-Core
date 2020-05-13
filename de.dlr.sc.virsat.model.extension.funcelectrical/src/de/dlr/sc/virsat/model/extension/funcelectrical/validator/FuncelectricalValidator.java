/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.validator;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Objects;

import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ReferencePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.general.IUuid;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;
import de.dlr.sc.virsat.model.ecore.VirSatEcoreUtil;
import de.dlr.sc.virsat.model.extension.funcelectrical.marker.VirSatFuncelectricalMarkerHelper;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;


// *****************************************************************
// * Class Declaration
// *****************************************************************

/**
 * Auto Generated Class inheriting from Generator Gap Class
 * 
 * This class is generated once, do your changes here
 * 
 * Concept for modeling Functional Electrical Architecture (FEA)
 * 
 */
public class FuncelectricalValidator extends AFuncelectricalValidator implements IStructuralElementInstanceValidator {

	private BeanCategoryAssignmentHelper bCaHelper = new BeanCategoryAssignmentHelper();
	private VirSatFuncelectricalMarkerHelper vfmHelper = new VirSatFuncelectricalMarkerHelper();

	public static final String WARNING_MISSING_IFT = "InterfaceType is not set for InterfaceEnd ";
	public static final String WARNING_MISSING_IFEFROM = "InterfaceEndFrom is not set for Interface ";
	public static final String WARNING_MISSING_IFETO = "InterfaceEndTo is not set for Interface ";
	public static final String WARNING_DIFFERENT_IFETS = "InterfaceEndTypes of InterfaceEndTo and InterfaceEndFrom are different in Interface ";
	public static final String WARNING_SAME_IFES = "InterfaceEndTo is the same like InterfaceEndFrom in Interface ";
	public static final String WARNING_ALREADY_USED_IFE = "InterfaceEnd is already used in Interface ";
	public static final String WARNING_DIFFERENT_TREES = "InterfaceEnds are within different trees ";
		
	@Override
	public boolean validate(StructuralElementInstance sei) {
		boolean allInfoValid = true;
		List<InterfaceEnd> interfaceEnds = bCaHelper.getAllBeanCategories(sei, InterfaceEnd.class);
		List<Interface> interfaces = bCaHelper.getAllBeanCategories(sei, Interface.class);
		
		for (InterfaceEnd ife : interfaceEnds) {
			// check if the InterfaceType is set for every InterfaceEnd otherwise give Warning
			InterfaceType ift = ife.getType();
			if (ift == null) {
				allInfoValid = false;
				String fqn = ife.getTypeInstance().getFullQualifiedInstanceName();
				ReferencePropertyInstance rpi = ife.getTypeReferenceProperty();
				IUuid iUuid = rpi;
				// Don't know yet if we will need the feature
																																
				vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, WARNING_MISSING_IFT + fqn, iUuid);
			}
		}
		
		for (Interface iF : interfaces) {
			// check if the InterfaceEnds are set for every Interface otherwise give Warning
			InterfaceEnd ifeFrom = iF.getInterfaceEndFrom();
			InterfaceEnd ifeTo = iF.getInterfaceEndTo();
			if (ifeFrom == null || ifeTo == null) {
				allInfoValid = false;
				String fqn = iF.getTypeInstance().getFullQualifiedInstanceName();	
				ReferencePropertyInstance rpiFrom = iF.getIfeFromReferenceProperty();
				ReferencePropertyInstance rpiTo = iF.getIfeToReferenceProperty();
				IUuid iUuidFrom = rpiFrom;
				IUuid iUuidTo = rpiTo; 
				
				if (ifeFrom == null) {
					vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, WARNING_MISSING_IFEFROM + fqn, iUuidFrom);
				}
				if (ifeTo == null) {
					vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, WARNING_MISSING_IFETO + fqn, iUuidTo);
				}
				
			} else {
				// check if the set InterfaceEnds have the same Type
				InterfaceType iftFrom = ifeFrom.getType();
				InterfaceType iftTo = ifeTo.getType();
				if (!Objects.equal(iftFrom, iftTo)) {
					allInfoValid = false;
					String fqn = iF.getTypeInstance().getFullQualifiedInstanceName();

					vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, WARNING_DIFFERENT_IFETS + fqn, iF.getIfeToReferenceProperty(), iF.getIfeFromReferenceProperty());
				} else {
					// now check whether they are the same
					if (ifeFrom.equals(ifeTo)) {
						allInfoValid = false;
						String fqn = iF.getTypeInstance().getFullQualifiedInstanceName();	

						vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, WARNING_SAME_IFES + fqn, iF.getIfeToReferenceProperty(), iF.getIfeFromReferenceProperty());
					}
				}
				
				// check if the set interfaceEnds are in the same tree, that is
				// they have the same root element
				
				EObject rootContainerFrom = VirSatEcoreUtil.getRootContainer(ifeFrom.getATypeInstance());
				EObject rootContainerTo = VirSatEcoreUtil.getRootContainer(ifeTo.getATypeInstance());
				
				if (rootContainerFrom != rootContainerTo) {
					allInfoValid = false;
					String fqn = iF.getTypeInstance().getFullQualifiedInstanceName();	

					vfmHelper.createFEAValidationMarker(IMarker.SEVERITY_WARNING, WARNING_DIFFERENT_TREES + fqn, iF.getIfeToReferenceProperty(), iF.getIfeFromReferenceProperty());
				}
				
			}
			
		}
		
		// now check if there are InterfaceEnds which are used more than once
		
		return super.validate(sei) && allInfoValid;
	}
}
