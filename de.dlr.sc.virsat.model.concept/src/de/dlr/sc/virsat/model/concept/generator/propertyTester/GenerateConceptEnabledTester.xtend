/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.generator.propertyTester

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper
import de.dlr.sc.virsat.model.dvlm.Repository
import de.dlr.sc.virsat.model.concept.generator.AGeneratorGapGenerator
import de.dlr.sc.virsat.model.concept.generator.ConceptOutputConfigurationProvider
import de.dlr.sc.virsat.model.concept.generator.ImportManager
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.generator.IFileSystemAccess
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil

class GenerateConceptEnabledTester extends AGeneratorGapGenerator<EObject> {
	
	override protected getPackage(Concept concept) {
		concept.name + "." + PACKAGE_FOLDER;
	}
	
	static def getConcreteClassName(EObject typeDefinition) {
		"ConceptEnabledTester";
	}
	
	static def getAbstractClassName(EObject typeDefinition) {
		"AConceptEnabledTester";
	}
	
	override createConcreteClassFileName(Concept concept, EObject eObject) {
		 "../../" + concept.name + ".ui/src/" + concept.packageFolder + "/" + eObject.concreteClassName + ".java"
	}
	
	override createAbstractClassFileName(Concept concept, EObject eObject) {
		  "../../" + concept.name + ".ui/src-gen/" + concept.packageFolder + "/" + eObject.abstractClassName + ".java"
	}
	
	public static val PACKAGE_FOLDER = "propertyTester";
	
	override serializeModel(Concept concept, IFileSystemAccess fsa) {
		// ************************************************************************************
		// Abstract Class
		// ************************************************************************************
		var fileOutputAClass = createAbstractClass(concept, concept);
		fsa.generateFile(createAbstractClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_SOURCE, fileOutputAClass);

		// ************************************************************************************
		// Concrete Class
		// ************************************************************************************
		var fileOutputClass = createConcreteClass(concept, concept);
		fsa.generateFile(createConcreteClassFileName(concept, concept), ConceptOutputConfigurationProvider.GENERATOR_OUTPUT_ID_GENERATE_ONCE, fileOutputClass);
	}

	/**
	 *  This method just creates the package declaration. The concept name needs to fit to the package name  
	 */
	override protected declarePackage(String packageId) '''
	package «packageId»;
	'''
	
	/**
	 * Handle the import statements
	 */
	override protected declareImports(ImportManager importManager, Concept concept, EObject conceptPart, String conceptPackage) '''
	«IF !importManager.importedClasses.empty»
		«FOR clazz : importManager.importedClasses»
			import «clazz»;
		«ENDFOR»
  	«ENDIF»
	'''

	// *************************************************************************************
	// * generate class
	// *************************************************************************************
	
	/**
	 *	Entry method to write the class body 
	 */
	override protected declareAClass(Concept concept, EObject conceptPart, ImportManager importManager) '''
	«importManager.register("de.dlr.sc.virsat.project.ui.editingDomain.propertyTester.EditingDomainPropertyTester")»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateAClassHeader(concept)»
	public abstract class «conceptPart.abstractClassName» extends EditingDomainPropertyTester {
	}
	'''

	/**
	 *	Entry method to write the class body 
	 */
	override protected declareClass(Concept concept, EObject conceptPart, ImportManager importManager) '''
	«importManager.register(Concept)»
	«importManager.register(Repository)»
	«importManager.register(ActiveConceptHelper)»
	
	«importManager.register("de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain")»
	
	// *****************************************************************
	// * Class Declaration
	// *****************************************************************
	
	«ConceptGeneratorUtil.generateClassHeader(concept)»
	public class «conceptPart.concreteClassName» extends «conceptPart.abstractClassName» {

		@Override
		public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
			VirSatTransactionalEditingDomain ed = getEditingDomainFromReceiver(receiver);
			
			// In case there is no editing domain than we cannot provide undo and redo
			if (ed == null) {
				return false;
			}
			
			Repository currentRepository = ed.getResourceSet().getRepository();
			ActiveConceptHelper acHelper = new ActiveConceptHelper(currentRepository);
			Concept activeConcept = acHelper.getConcept("«concept.name»");
			boolean hasActiveConcept =  activeConcept != null;
			return hasActiveConcept;
		}
	}
	'''
}
		