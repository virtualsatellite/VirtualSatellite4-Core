/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator;

import de.dlr.sc.virsat.model.concept.generator.IGeneratorGapGenerator;
import de.dlr.sc.virsat.model.concept.generator.ImportManager;
import de.dlr.sc.virsat.model.concept.generator.util.ConceptGeneratorUtil;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public abstract class AGeneratorGapGenerator<TYPE extends EObject> implements IGeneratorGapGenerator<TYPE> {
  /**
   * This method hands back the Package as folder. It replaces the . with /
   */
  public String getPackageFolder(final Concept concept) {
    return this.getPackage(concept).replace(".", "/");
  }
  
  @Override
  public CharSequence createConcreteClass(final Concept concept, final TYPE conceptPart) {
    String _package = this.getPackage(concept);
    final ImportManager imCClass = new ImportManager(_package);
    final CharSequence bodyClass = this.declareClass(concept, conceptPart, imCClass);
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateFileHeader = ConceptGeneratorUtil.generateFileHeader();
    _builder.append(_generateFileHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _declarePackage = this.declarePackage(this.getPackage(concept));
    _builder.append(_declarePackage);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _declareImports = this.declareImports(imCClass, concept, conceptPart, this.getPackage(concept));
    _builder.append(_declareImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append(bodyClass);
    _builder.newLineIfNotEmpty();
    String fileOutputClass = _builder.toString();
    return fileOutputClass;
  }
  
  @Override
  public CharSequence createAbstractClass(final Concept concept, final TYPE conceptPart) {
    String _package = this.getPackage(concept);
    final ImportManager imAClass = new ImportManager(_package);
    final CharSequence bodyAClass = this.declareAClass(concept, conceptPart, imAClass);
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateFileHeader = ConceptGeneratorUtil.generateFileHeader();
    _builder.append(_generateFileHeader);
    _builder.newLineIfNotEmpty();
    CharSequence _declarePackage = this.declarePackage(this.getPackage(concept));
    _builder.append(_declarePackage);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _declareImports = this.declareImports(imAClass, concept, conceptPart, this.getPackage(concept));
    _builder.append(_declareImports);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append(bodyAClass);
    _builder.newLineIfNotEmpty();
    String fileOutputAClass = _builder.toString();
    return fileOutputAClass;
  }
  
  protected abstract String getPackage(final Concept concept);
  
  protected abstract CharSequence declareImports(final ImportManager importManager, final Concept concept, final TYPE type, final String conceptPackage);
  
  protected abstract CharSequence declarePackage(final String packageId);
  
  protected abstract CharSequence declareClass(final Concept concept, final TYPE type, final ImportManager manager);
  
  protected abstract CharSequence declareAClass(final Concept concept, final TYPE type, final ImportManager manager);
}
