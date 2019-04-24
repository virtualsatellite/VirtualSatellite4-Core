package de.dlr.sc.virsat.model.concept.generator

import de.dlr.sc.virsat.model.dvlm.concepts.Concept
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.generator.IFileSystemAccess

interface IGeneratorGapGenerator<TYPE extends EObject> {
	
	def void serializeModel(Concept concept, IFileSystemAccess fsa);
	
	def String createConcreteClassFileName(Concept concept, TYPE conceptPart);
	
	def String createAbstractClassFileName(Concept concept, TYPE conceptPart);
	
	def CharSequence createConcreteClass(Concept concept, TYPE conceptPart);
	
	def CharSequence createAbstractClass(Concept concept, TYPE conceptPart);
	
}