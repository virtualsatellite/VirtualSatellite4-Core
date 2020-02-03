/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.scoping

import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import de.dlr.sc.virsat.model.dvlm.general.IInstance
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider

/**
 * This class helps the scoping from the Xtext Calculation Editor into our model
 * Our model is using UUIDs which are not usable for the calculationLanguage.
 * Therefore this class is asking the model for their FQN instead.
 */
class EquationSectionNamesProvider extends DefaultDeclarativeQualifiedNameProvider {
	
	/**
	 * Get the qualified name for an EquationSection. The method is
	 * aware of the fact that the Equation section might be a direct content
	 * of an xtext resource or that it might be an contained eObject in a VirSatResource 
	 */
	def qualifiedName(EquationSection equationSection) {
		val eContainer = equationSection.eContainer
		if (eContainer ===  null) {
			// in case it is not contained we know that the EquationSection is currently opened 
			// in an xtext resource. This means that we have to get the qualified name of the
			// EquationSection by looking in which resource it is contained. Therefore we use the link
			// to the original VirSat Resource to determine the qualified name.
			val resource = equationSection.eResource;
			if (resource instanceof EquationSectionXtextResource) {
				val EquationSectionXtextResource equationSectionXtextResource = resource;
				val section = equationSectionXtextResource.equationSection;
				val instance = section.eContainer() as IInstance;
				val qualifiedName = converter.toQualifiedName(instance.fullQualifiedInstanceName);
				return qualifiedName;
			}
		} else if (eContainer instanceof IInstance) {
			val name = (eContainer as IInstance).fullQualifiedInstanceName;
			return converter.toQualifiedName(name);
		}
	}

	def qualifiedName(IInstance obj) {
		val dvlmInstance = obj as IInstance;
		return converter.toQualifiedName(dvlmInstance.fullQualifiedInstanceName);
	}
	
	def qualifiedName(ATypeDefinition typeDefinition) {
		val concept = ActiveConceptHelper.getConcept(typeDefinition);
		val qualifiedConceptName = converter.toQualifiedName(concept.fullQualifiedName);
		val qualifiedName = converter.toQualifiedName(typeDefinition.fullQualifiedName);
		val name =  qualifiedName.skipFirst(qualifiedConceptName.segmentCount);
		return name;
	}
	
}
