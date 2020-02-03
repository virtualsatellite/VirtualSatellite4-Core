/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.concept.builder.resources;

import de.dlr.sc.virsat.model.concept.Activator;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The Resource Access Builder reads and parses files such as the plugin.xml
 * the manifest.mf and similar to create Java files with static attributes for
 * easier access to IDs for example
 */
@SuppressWarnings("all")
public class ResourceAccessBuilder extends IncrementalProjectBuilder {
  public static final String MANIFEST_MF = "MANIFEST.MF";
  
  public static final String PLUGIN_XML = "plugin.xml";
  
  public static final String BUILDER_ID = "de.dlr.sc.virsat.model.concept.resourceAccessBuilder";
  
  private final String[] CONCATENATE_ID = { "CategoryAssignmentBean", "extension", "command", "image", "handler", "conceptImage" };
  
  private final String[] CONCATENATE_VERSION = { "migrator" };
  
  private final String[] CONCATENATE_TABLE = { "uiSnippet" };
  
  @Override
  protected IProject[] build(final int kind, final Map<String, String> args, final IProgressMonitor monitor) throws CoreException {
    ILog _log = Activator.getDefault().getLog();
    String _pluginId = Activator.getPluginId();
    Status _status = new Status(Status.INFO, _pluginId, Status.OK, "ResourceAccessBuilder: Try to trigger build", 
      null);
    _log.log(_status);
    IResourceDelta delta = this.getDelta(this.getProject());
    switch (kind) {
      case IncrementalProjectBuilder.FULL_BUILD:
        this.fullBuild(monitor);
        break;
      case IncrementalProjectBuilder.INCREMENTAL_BUILD:
        this.incrementalBuild(delta, monitor);
        break;
      case IncrementalProjectBuilder.AUTO_BUILD:
        if ((delta == null)) {
          this.fullBuild(monitor);
        } else {
          this.incrementalBuild(delta, monitor);
        }
        break;
      default:
        this.fullBuild(monitor);
        break;
    }
    ILog _log_1 = Activator.getDefault().getLog();
    String _pluginId_1 = Activator.getPluginId();
    Status _status_1 = new Status(Status.INFO, _pluginId_1, Status.OK, "ResourceAccessBuilder: Finished build", null);
    _log_1.log(_status_1);
    return null;
  }
  
  /**
   * Do the incremental build
   */
  public void incrementalBuild(final IResourceDelta delta, final IProgressMonitor monitor) {
    try {
      final IResourceDeltaVisitor _function = (IResourceDelta it) -> {
        try {
          final IResource deltaResource = it.getResource();
          if ((deltaResource instanceof IFile)) {
            IResource _resource = it.getResource();
            final IFile iFile = ((IFile) _resource);
            final boolean iFileExists = iFile.exists();
            final boolean iFileManifestMf = ResourceAccessBuilder.MANIFEST_MF.equalsIgnoreCase(iFile.getName());
            final boolean iFilePluginXml = ResourceAccessBuilder.PLUGIN_XML.equalsIgnoreCase(iFile.getName());
            if (iFileExists) {
              if (iFileManifestMf) {
                IFile iFileManifest = this.getProject().getFile(("META-INF/" + ResourceAccessBuilder.MANIFEST_MF));
                URI iFileManifestUri = iFileManifest.getRawLocationURI();
                IFileStore _store = EFS.getStore(iFileManifestUri);
                NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
                File fileManifest = _store.toLocalFile(0, _nullProgressMonitor);
                FileInputStream manifestFileInputStream = new FileInputStream(fileManifest);
                this.writeAccessClass(this.buildManifestAccessClass(manifestFileInputStream), "ManifestMf.java");
              } else {
                if (iFilePluginXml) {
                  IFile iFilePlugin = this.getProject().getFile("plugin.xml");
                  URI iFilePluginXmlUri = iFilePlugin.getRawLocationURI();
                  IFileStore _store_1 = EFS.getStore(iFilePluginXmlUri);
                  NullProgressMonitor _nullProgressMonitor_1 = new NullProgressMonitor();
                  File filePluginXml = _store_1.toLocalFile(0, _nullProgressMonitor_1);
                  FileInputStream pluginFileInputStream = new FileInputStream(filePluginXml);
                  this.writeAccessClass(this.buildPluginXmlAccessClass(pluginFileInputStream), "PluginXml.java");
                }
              }
            }
          }
          return true;
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      delta.accept(_function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * do the full build
   */
  public void fullBuild(final IProgressMonitor monitor) {
    try {
      IFile iFileManifest = this.getProject().getFile(("META-INF/" + ResourceAccessBuilder.MANIFEST_MF));
      URI iFileManifestUri = iFileManifest.getRawLocationURI();
      IFileStore _store = EFS.getStore(iFileManifestUri);
      NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
      File fileManifest = _store.toLocalFile(0, _nullProgressMonitor);
      FileInputStream manifestFileInputStream = new FileInputStream(fileManifest);
      this.writeAccessClass(this.buildManifestAccessClass(manifestFileInputStream), "ManifestMf.java");
      IFile iFilePluginXml = this.getProject().getFile("plugin.xml");
      URI iFilePluginXmlUri = iFilePluginXml.getRawLocationURI();
      IFileStore _store_1 = EFS.getStore(iFilePluginXmlUri);
      NullProgressMonitor _nullProgressMonitor_1 = new NullProgressMonitor();
      File filePluginXml = _store_1.toLocalFile(0, _nullProgressMonitor_1);
      FileInputStream pluginFileInputStream = new FileInputStream(filePluginXml);
      this.writeAccessClass(this.buildPluginXmlAccessClass(pluginFileInputStream), "PluginXml.java");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * This method builds the manifest access java file
   */
  public StringInputStream buildManifestAccessClass(final InputStream manifestFileInputStream) {
    try {
      Manifest manifest = new Manifest(manifestFileInputStream);
      final CharSequence classSource = this.createManifestAccessClass(this.getTheProject().getName(), manifest.getMainAttributes());
      String _string = classSource.toString();
      final StringInputStream classSourceStream = new StringInputStream(_string);
      return classSourceStream;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * This method creates the string that should be written into the
   */
  public CharSequence createManifestAccessClass(final String packageName, final Attributes attributes) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*******************************************************************************");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This program and the accompanying materials are made available under the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* terms of the Eclipse Public License 2.0 which is available at");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* http://www.eclipse.org/legal/epl-2.0.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* SPDX-License-Identifier: EPL-2.0");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*******************************************************************************/");
    _builder.newLine();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class ManifestMf {");
    _builder.newLine();
    {
      Set<Object> _keySet = attributes.keySet();
      for(final Object entryKey : _keySet) {
        {
          boolean _contains = attributes.get(entryKey).toString().contains(";");
          if (_contains) {
            _builder.append("\t");
            final String[] myArray = attributes.get(entryKey).toString().split(";");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("public static final String ");
            String _replace = entryKey.toString().toUpperCase().replace("-", "_");
            _builder.append(_replace, "\t");
            _builder.append(" = \"");
            String _get = myArray[0];
            _builder.append(_get, "\t");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t");
            _builder.append("public static final String ");
            String _replace_1 = entryKey.toString().toUpperCase().replace("-", "_");
            _builder.append(_replace_1, "\t");
            _builder.append(" = \"");
            Object _get_1 = attributes.get(entryKey);
            _builder.append(_get_1, "\t");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * This method is called to build the plugin.xml access class
   */
  public StringInputStream buildPluginXmlAccessClass(final InputStream pluginFileInputStream) {
    try {
      final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(pluginFileInputStream);
      Element _documentElement = doc.getDocumentElement();
      final Node n1 = ((Node) _documentElement);
      final CharSequence classSource = this.createPluginXmlAccessClass(this.getTheProject().getName(), n1);
      String _string = classSource.toString();
      final StringInputStream classSourceStream = new StringInputStream(_string);
      return classSourceStream;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void writeAccessClass(final StringInputStream classSourceStream, final String fileName) {
    try {
      final IFolder iFolderSrc = this.getProject().getFolder("src-gen");
      boolean _exists = iFolderSrc.exists();
      boolean _not = (!_exists);
      if (_not) {
        NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
        iFolderSrc.create(true, true, _nullProgressMonitor);
        final IJavaProject javaProject = JavaCore.create(this.getProject());
        final IPackageFragmentRoot packageRoot = javaProject.getPackageFragmentRoot(iFolderSrc);
        final IClasspathEntry[] classPath = javaProject.getRawClasspath();
        List<IClasspathEntry> _asList = Arrays.<IClasspathEntry>asList(classPath);
        final ArrayList<IClasspathEntry> entries = new ArrayList<IClasspathEntry>(_asList);
        entries.add(JavaCore.newSourceEntry(packageRoot.getPath()));
        IClasspathEntry[] _array = entries.<IClasspathEntry>toArray(classPath);
        NullProgressMonitor _nullProgressMonitor_1 = new NullProgressMonitor();
        javaProject.setRawClasspath(_array, _nullProgressMonitor_1);
        String _name = this.getProject().getName();
        NullProgressMonitor _nullProgressMonitor_2 = new NullProgressMonitor();
        packageRoot.createPackageFragment(_name, true, _nullProgressMonitor_2);
      }
      final IFolder iFolderPackage = iFolderSrc.getFolder(this.getProject().getName().replace(".", "\\"));
      boolean _exists_1 = iFolderPackage.exists();
      boolean _not_1 = (!_exists_1);
      if (_not_1) {
        final IJavaProject javaProject_1 = JavaCore.create(this.getProject());
        final IPackageFragmentRoot packageRoot_1 = javaProject_1.getPackageFragmentRoot(iFolderSrc);
        final IClasspathEntry[] classPath_1 = javaProject_1.getRawClasspath();
        List<IClasspathEntry> _asList_1 = Arrays.<IClasspathEntry>asList(classPath_1);
        final ArrayList<IClasspathEntry> entries_1 = new ArrayList<IClasspathEntry>(_asList_1);
        entries_1.add(JavaCore.newSourceEntry(packageRoot_1.getPath()));
        String _name_1 = this.getProject().getName();
        NullProgressMonitor _nullProgressMonitor_3 = new NullProgressMonitor();
        packageRoot_1.createPackageFragment(_name_1, true, _nullProgressMonitor_3);
      }
      final IFile iFilePluginXmlAccessClassJava = iFolderPackage.getFile(fileName);
      boolean _exists_2 = iFilePluginXmlAccessClassJava.exists();
      if (_exists_2) {
        iFilePluginXmlAccessClassJava.setContents(classSourceStream, IResource.NONE, null);
      } else {
        iFilePluginXmlAccessClassJava.create(classSourceStream, IResource.NONE, null);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * This method creates the string that should be written into the
   */
  public CharSequence createPluginXmlAccessClass(final String packageName, final Node node) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/*******************************************************************************");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This program and the accompanying materials are made available under the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* terms of the Eclipse Public License 2.0 which is available at");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* http://www.eclipse.org/legal/epl-2.0.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* SPDX-License-Identifier: EPL-2.0");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*******************************************************************************/");
    _builder.newLine();
    _builder.append("package ");
    _builder.append(packageName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("public class PluginXml {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class commands {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass = this.createPluginXmlAccessClass(node, "command", "org.eclipse.ui.commands");
    _builder.append(_createPluginXmlAccessClass, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class conceptImages {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_1 = this.createPluginXmlAccessClass(node, "conceptImage", "ConceptImageContribution");
    _builder.append(_createPluginXmlAccessClass_1, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class uiSnippets {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_2 = this.createPluginXmlAccessClass(node, "uiSnippet", "EditorUiSnippets");
    _builder.append(_createPluginXmlAccessClass_2, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class handlers {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_3 = this.createPluginXmlAccessClass(node, "handler", "org.eclipse.ui.handlers");
    _builder.append(_createPluginXmlAccessClass_3, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class conceptMigrators {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_4 = this.createPluginXmlAccessClass(node, "migrator", "ConceptMigrator");
    _builder.append(_createPluginXmlAccessClass_4, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class concept {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_5 = this.createPluginXmlAccessClass(node, "concept", "Concept");
    _builder.append(_createPluginXmlAccessClass_5, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class ExtensionPoints {");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _createExtensionPoints = this.createExtensionPoints(node, "extension-point", "plugin");
    _builder.append(_createExtensionPoints, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public Object createPluginXmlAccessClass(final Node node, final String s, final String group) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if (((!node.getNodeName().contains("#")) && this.getExtensionPointID(node, group))) {
        {
          ArrayList<Node> _childrenNodeList = this.getChildrenNodeList(node);
          for(final Node childrenNode : _childrenNodeList) {
            {
              if (((childrenNode.getNodeType() == Node.ELEMENT_NODE) && childrenNode.getNodeName().equals(s))) {
                _builder.append("public static class ");
                String _firstUpper = StringExtensions.toFirstUpper(this.classNameChecker(childrenNode));
                _builder.append(_firstUpper);
                _builder.append(" {");
                _builder.newLineIfNotEmpty();
                {
                  ArrayList<Node> _attributeList = this.getAttributeList(childrenNode);
                  for(final Node attributeNode : _attributeList) {
                    _builder.append("\t");
                    _builder.append("public static final String ");
                    String _upperCase = this.classNameChecker(attributeNode).toUpperCase();
                    _builder.append(_upperCase, "\t");
                    _builder.append(" = \"");
                    String _nodeValue = attributeNode.getNodeValue();
                    _builder.append(_nodeValue, "\t");
                    _builder.append("\";");
                    _builder.newLineIfNotEmpty();
                  }
                }
                _builder.append("}\t");
                _builder.newLine();
              }
            }
          }
        }
      } else {
        {
          ArrayList<Node> _childrenNodeList_1 = this.getChildrenNodeList(node);
          for(final Node childNode : _childrenNodeList_1) {
            Object _createPluginXmlAccessClass = this.createPluginXmlAccessClass(childNode, s, group);
            _builder.append(_createPluginXmlAccessClass);
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence createExtensionPoints(final Node node, final String s, final String group) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ArrayList<Node> _childrenNodeList = this.getChildrenNodeList(node);
      for(final Node childrenNode : _childrenNodeList) {
        {
          if (((childrenNode.getNodeType() == Node.ELEMENT_NODE) && childrenNode.getNodeName().equals(s))) {
            _builder.append("public static class ");
            String _replace = StringExtensions.toFirstUpper(this.classNameChecker(childrenNode)).replace("-", "");
            _builder.append(_replace);
            _builder.append(" {");
            _builder.newLineIfNotEmpty();
            {
              ArrayList<Node> _attributeList = this.getAttributeList(childrenNode);
              for(final Node attributeNode : _attributeList) {
                _builder.append("\t");
                _builder.append("public static final String ");
                String _upperCase = this.classNameChecker(attributeNode).toUpperCase();
                _builder.append(_upperCase, "\t");
                _builder.append(" = \"");
                String _nodeValue = attributeNode.getNodeValue();
                _builder.append(_nodeValue, "\t");
                _builder.append("\";");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("}\t");
            _builder.newLine();
          }
        }
      }
    }
    _builder.newLine();
    return _builder;
  }
  
  public ArrayList<Node> getChildrenNodeList(final Node node) {
    final ArrayList<Node> arraylist = new ArrayList<Node>();
    int _length = node.getChildNodes().getLength();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
    for (final Integer i : _doubleDotLessThan) {
      arraylist.add(node.getChildNodes().item((i).intValue()));
    }
    return arraylist;
  }
  
  public ArrayList<Node> getAttributeList(final Node node) {
    final Element eElement = ((Element) node);
    final NamedNodeMap nodeMap = eElement.getAttributes();
    final ArrayList<Node> arraylist = new ArrayList<Node>();
    int _length = nodeMap.getLength();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
    for (final Integer i : _doubleDotLessThan) {
      arraylist.add(nodeMap.item((i).intValue()));
    }
    return arraylist;
  }
  
  public String classNameChecker(final Node node) {
    boolean _contains = node.getNodeName().contains("extension-point");
    if (_contains) {
      return this.extensionPointID(node);
    } else {
      boolean _contains_1 = ((List<String>)Conversions.doWrapArray(this.CONCATENATE_ID)).contains(node.getNodeName());
      if (_contains_1) {
        return this.concatenateID(node);
      } else {
        boolean _contains_2 = ((List<String>)Conversions.doWrapArray(this.CONCATENATE_VERSION)).contains(node.getNodeName());
        if (_contains_2) {
          return this.concatenateVersion(node);
        } else {
          boolean _contains_3 = ((List<String>)Conversions.doWrapArray(this.CONCATENATE_TABLE)).contains(node.getNodeName());
          if (_contains_3) {
            return this.concatenateTable(node);
          } else {
            boolean _equals = node.getNodeName().equals("class");
            if (_equals) {
              return "className";
            } else {
              return node.getNodeName();
            }
          }
        }
      }
    }
  }
  
  public String concatenateID(final Node node) {
    String name = this.getAttributeList(node).get(0).toString();
    name = name.replace("\"", "");
    final String delims = "[.]";
    final String[] tokens = name.split(delims);
    final String lastpart = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(tokens)));
    String _nodeName = node.getNodeName();
    return (_nodeName + lastpart);
  }
  
  public String concatenateVersion(final Node node) {
    final ArrayList<Node> attributes = this.getAttributeList(node);
    for (final Node n : attributes) {
      boolean _equals = n.getNodeName().equals("version");
      if (_equals) {
        String _nodeName = node.getNodeName();
        String _replace = n.getNodeValue().replace(".", "_");
        return (_nodeName + _replace);
      }
    }
    return null;
  }
  
  public String extensionPointID(final Node node) {
    String name = this.getAttributeList(node).get(0).toString();
    name = name.replaceAll("[^a-zA-Z0-9]", "");
    name = name.replaceAll("id", "");
    return name;
  }
  
  public String concatenateTable(final Node node) {
    String name = this.getAttributeList(node).get(0).toString();
    name = name.replace("\"", "");
    final String delims = "[.]";
    final String[] tokens = name.split(delims);
    final String lastpart = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(tokens)));
    int _length = tokens.length;
    int _minus = (_length - 2);
    final String beforeLastPart = tokens[_minus];
    String _nodeName = node.getNodeName();
    String _firstUpper = StringExtensions.toFirstUpper(beforeLastPart);
    String _plus = (_nodeName + _firstUpper);
    String _firstUpper_1 = StringExtensions.toFirstUpper(lastpart);
    return (_plus + _firstUpper_1);
  }
  
  public boolean getExtensionPointID(final Node node, final String s) {
    ArrayList<Node> attributes = this.getAttributeList(node);
    for (final Node a : attributes) {
      boolean _contains = a.getNodeValue().contains(s);
      if (_contains) {
        return true;
      }
    }
    return false;
  }
  
  public IProject getTheProject() {
    return this.getProject();
  }
}
