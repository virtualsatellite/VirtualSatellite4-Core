package de.dlr.sc.virsat.model.concept.generator;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public interface IGeneratorGapGenerator<TYPE extends EObject> {
  public abstract void serializeModel(final Concept concept, final IFileSystemAccess fsa);
  
  public abstract String createConcreteClassFileName(final Concept concept, final TYPE conceptPart);
  
  public abstract String createAbstractClassFileName(final Concept concept, final TYPE conceptPart);
  
  public abstract CharSequence createConcreteClass(final Concept concept, final TYPE conceptPart);
  
  public abstract CharSequence createAbstractClass(final Concept concept, final TYPE conceptPart);
}
