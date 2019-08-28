/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.validation;

import de.dlr.sc.virsat.model.concept.validation.ConceptLanguageValidator;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.xtext.validation.Check;

/**
 * Validator with all checks - inherited core checks from the core validator
 * and checks that require certain environment setup which we want to skip in most unit tests
 */
@SuppressWarnings("all")
public class ConceptLanguageExtendedValidator extends ConceptLanguageValidator {
  public static final String CONCEPT_NAME_PLUGIN_NAME_MISMATCH = "conceptNamePluginNameMismatch";
  
  @Check
  public void CheckConceptNameEqualsToPluginName(final Concept concept) {
    final String platformString = concept.eResource().getURI().toPlatformString(true);
    IWorkspaceRoot _root = ResourcesPlugin.getWorkspace().getRoot();
    Path _path = new Path(platformString);
    final IFile currentFile = _root.getFile(_path);
    final String projectName = currentFile.getProject().getName();
    boolean _equals = concept.getName().equals(projectName);
    boolean _not = (!_equals);
    if (_not) {
      this.error(("Concept must have the same name as the plugin in which it is contained: " + projectName), 
        GeneralPackage.Literals.IQUALIFIED_NAME__NAME, 
        ConceptLanguageExtendedValidator.CONCEPT_NAME_PLUGIN_NAME_MISMATCH);
    }
  }
}
