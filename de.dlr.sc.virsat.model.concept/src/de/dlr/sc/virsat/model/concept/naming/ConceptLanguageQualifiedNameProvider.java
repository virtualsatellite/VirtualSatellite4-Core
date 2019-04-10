/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.naming;

import java.util.Arrays;
import java.util.List;

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;

/**
 * This class handles the full qualified names as they are used in the DVLM 
 * concepts. Xtext needs them for correct Corss-Referencing.
 * @author fisc_ph
 *
 */
public class ConceptLanguageQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

	@Override
	protected QualifiedName qualifiedName(Object ele) {
		QualifiedName fullQualifiedName = null;
		
		if (ele instanceof Concept) {
			Concept concept = (Concept) ele;
			String textualFullQualifiedName = concept.getName();
			fullQualifiedName = createQualifiedName(textualFullQualifiedName);
		} else if (ele instanceof ATypeDefinition) {
			ATypeDefinition typeDefinition = (ATypeDefinition) ele;
			String textualFullQualifiedName = ActiveConceptHelper.getFullQualifiedId(typeDefinition);
			fullQualifiedName = createQualifiedName(textualFullQualifiedName);
		} else {
			fullQualifiedName = super.qualifiedName(ele);
		}
		
		return fullQualifiedName;
	}
	
	/**
	 * Call this method to create a Xtext compatible Qualified Name from a DVLM qualifed name
	 * @param dvlmQualifiedName The FQN from the DVLM
	 * @return Xtext Qualified Name
	 */
	private QualifiedName createQualifiedName(String dvlmQualifiedName) {
		String[] textualFullQualifiedNameSegments = dvlmQualifiedName.split(ActiveConceptHelper.FQID_DELIMITER_REGEX);
		List<String> uqlifiedNameSegments = Arrays.asList(textualFullQualifiedNameSegments);
		QualifiedName fullQualifiedName = QualifiedName.create(uqlifiedNameSegments);
		return fullQualifiedName;
	}
}
