/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.generator.plugin;

import de.dlr.sc.virsat.model.concept.Activator;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.general.IQualifiedName;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class GenerateConceptImages {
  public void serializeModel(final Concept concept, final IFileSystemAccess fsa) {
    TreeIterator<Object> iterator = EcoreUtil.<Object>getAllContents(concept, true);
    String _name = concept.getName();
    final String uiPlugin = (_name + ".ui/");
    final String pathTo = (("../../" + uiPlugin) + "resources/icons/");
    this.createDefaultImage(concept, pathTo, GenerateConceptImages.getFileNameStandard(concept), GenerateConceptImages.getFileNameDisabled(concept));
    final Procedure1<Object> _function = (Object it) -> {
      if (((it instanceof Category) || (it instanceof StructuralElement))) {
        final IQualifiedName dvlmObject = ((IQualifiedName) it);
        final String fileName = GenerateConceptImages.getFileNameStandard(dvlmObject);
        final String fileNameDisabled = GenerateConceptImages.getFileNameDisabled(dvlmObject);
        this.createDefaultImage(concept, pathTo, fileName, fileNameDisabled);
      }
    };
    IteratorExtensions.<Object>forEach(iterator, _function);
  }
  
  public Long createDefaultImage(final Concept concept, final String pathTo, final String fileName, final String fileNameDisabled) {
    try {
      long _xblockexpression = (long) 0;
      {
        InputStream stream = null;
        InputStream stream2 = null;
        long _xtrycatchfinallyexpression = (long) 0;
        try {
          long _xblockexpression_1 = (long) 0;
          {
            stream = Activator.getDefault().getPluginResource("resources/icons/default.gif");
            stream2 = Activator.getDefault().getPluginResource("resources/icons/default.gif");
            final IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProject(concept.getName());
            if ((iProject == null)) {
              String _name = concept.getName();
              String _plus = ("Could not find project: " + _name);
              String _plus_1 = (_plus + " for finding the default image");
              throw new RuntimeException(_plus_1);
            }
            final IFile iFile = iProject.getFile(pathTo);
            if ((iFile == null)) {
              throw new RuntimeException("Could not find required folder in project");
            }
            final String osPath = iFile.getRawLocation().toOSString();
            Files.createDirectories(Paths.get(osPath));
            Files.copy(stream, Paths.get(((osPath + "/") + fileName)));
            _xblockexpression_1 = Files.copy(stream2, Paths.get(((osPath + "/") + fileNameDisabled)));
          }
          _xtrycatchfinallyexpression = _xblockexpression_1;
        } catch (final Throwable _t) {
          if (_t instanceof FileAlreadyExistsException) {
            ILog _log = Activator.getDefault().getLog();
            Status _status = new Status(Status.ERROR, "ConceptPlugin:", "File already existing: we do not override!");
            _log.log(_status);
          } else if (_t instanceof IOException) {
            throw new RuntimeException(("Could not copy image: " + fileName));
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        } finally {
          if ((stream != null)) {
            stream.close();
          }
          if ((stream2 != null)) {
            stream2.close();
          }
        }
        _xblockexpression = _xtrycatchfinallyexpression;
      }
      return Long.valueOf(_xblockexpression);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static String getFileNameStandard(final IQualifiedName qualifiedNameObject) {
    String _name = qualifiedNameObject.getName();
    return (_name + ".gif");
  }
  
  public static String getFileNameDisabled(final IQualifiedName qualifiedNameObject) {
    String _name = qualifiedNameObject.getName();
    return (_name + "_disabled.gif");
  }
}
