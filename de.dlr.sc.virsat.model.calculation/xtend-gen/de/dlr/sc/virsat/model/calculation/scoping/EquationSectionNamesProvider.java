/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.calculation.scoping;

import de.dlr.sc.virsat.model.calculation.resource.EquationSectionXtextResource;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeDefinition;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.general.IInstance;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

/**
 * This class helps the scoping from the Xtext Calculation Editor into our model
 * Our model is using UUIDs which are not usable for the calculationLanguage.
 * Therefore this class is asking the model for their FQN instead.
 */
@SuppressWarnings("all")
public class EquationSectionNamesProvider extends DefaultDeclarativeQualifiedNameProvider {
  /**
   * Get the qualified name for an EquationSection. The method is
   * aware of the fact that the Equation section might be a direct content
   * of an xtext resource or that it might be an contained eObject in a VirSatResource
   * @param equationSection of which to get the FQN
   * @return the FQN of the equationSection
   */
  public QualifiedName qualifiedName(final EquationSection equationSection) {
    final EObject eContainer = equationSection.eContainer();
    if ((eContainer == null)) {
      final Resource resource = equationSection.eResource();
      if ((resource instanceof EquationSectionXtextResource)) {
        final EquationSectionXtextResource equationSectionXtextResource = ((EquationSectionXtextResource)resource);
        final EquationSection section = equationSectionXtextResource.getEquationSection();
        EObject _eContainer = section.eContainer();
        final IInstance instance = ((IInstance) _eContainer);
        final QualifiedName qualifiedName = this.getConverter().toQualifiedName(instance.getFullQualifiedInstanceName());
        return qualifiedName;
      }
    } else {
      if ((eContainer instanceof IInstance)) {
        final String name = ((IInstance) eContainer).getFullQualifiedInstanceName();
        return this.getConverter().toQualifiedName(name);
      }
    }
    return null;
  }
  
  public QualifiedName qualifiedName(final IInstance obj) {
    final IInstance dvlmInstance = ((IInstance) obj);
    return this.getConverter().toQualifiedName(dvlmInstance.getFullQualifiedInstanceName());
  }
  
  public QualifiedName qualifiedName(final ATypeDefinition typeDefinition) {
    final Concept concept = ActiveConceptHelper.getConcept(typeDefinition);
    final QualifiedName qualifiedConceptName = this.getConverter().toQualifiedName(concept.getFullQualifiedName());
    final QualifiedName qualifiedName = this.getConverter().toQualifiedName(typeDefinition.getFullQualifiedName());
    final QualifiedName name = qualifiedName.skipFirst(qualifiedConceptName.getSegmentCount());
    return name;
  }
}
