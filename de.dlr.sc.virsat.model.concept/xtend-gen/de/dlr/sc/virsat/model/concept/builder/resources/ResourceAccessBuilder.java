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
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
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
  
  public static final String MANIFEST_MF_JAVA = "ManifestMf.java";
  
  public static final String PLUGIN_XML_JAVA = "PluginXml.java";
  
  public static final String BUILDER_ID = "de.dlr.sc.virsat.model.concept.resourceAccessBuilder";
  
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
        final IResource deltaResource = it.getResource();
        if ((deltaResource instanceof IFile)) {
          IResource _resource = it.getResource();
          final IFile iFile = ((IFile) _resource);
          final boolean iFileExists = iFile.exists();
          if (iFileExists) {
            final boolean iFileManifestMf = ResourceAccessBuilder.MANIFEST_MF.equalsIgnoreCase(iFile.getName());
            final boolean iFilePluginXml = ResourceAccessBuilder.PLUGIN_XML.equalsIgnoreCase(iFile.getName());
            if (iFileManifestMf) {
              this.writeAccessClass(this.buildManifestAccessClass(), ResourceAccessBuilder.MANIFEST_MF_JAVA);
            } else {
              if (iFilePluginXml) {
                this.writeAccessClass(this.buildPluginXmlAccessClass(), ResourceAccessBuilder.PLUGIN_XML_JAVA);
              }
            }
          }
        }
        return true;
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
    this.writeAccessClass(this.buildManifestAccessClass(), ResourceAccessBuilder.MANIFEST_MF_JAVA);
    this.writeAccessClass(this.buildPluginXmlAccessClass(), ResourceAccessBuilder.PLUGIN_XML_JAVA);
  }
  
  /**
   * This method builds the manifest access java file
   * from the default manifest file in the project.
   */
  public StringInputStream buildManifestAccessClass() {
    try {
      StringInputStream _xblockexpression = null;
      {
        IFile iFileManifest = this.getProject().getFile(("META-INF/" + ResourceAccessBuilder.MANIFEST_MF));
        URI iFileManifestUri = iFileManifest.getRawLocationURI();
        IFileStore _store = EFS.getStore(iFileManifestUri);
        NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
        File fileManifest = _store.toLocalFile(0, _nullProgressMonitor);
        FileInputStream manifestFileInputStream = new FileInputStream(fileManifest);
        _xblockexpression = this.buildManifestAccessClass(manifestFileInputStream);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * This method builds the manifest access java file
   * from a given input stream.
   */
  public StringInputStream buildManifestAccessClass(final InputStream manifestInputStream) {
    try {
      Manifest manifest = new Manifest(manifestInputStream);
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
   * from the default plugin.xml in the project.
   */
  public StringInputStream buildPluginXmlAccessClass() {
    try {
      IFile iFilePlugin = this.getProject().getFile("plugin.xml");
      URI iFilePluginXmlUri = iFilePlugin.getRawLocationURI();
      IFileStore _store = EFS.getStore(iFilePluginXmlUri);
      NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
      File filePluginXml = _store.toLocalFile(0, _nullProgressMonitor);
      FileInputStream pluginFileInputStream = new FileInputStream(filePluginXml);
      return this.buildPluginXmlAccessClass(pluginFileInputStream);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * This method is called to build the plugin.xml access class
   * from a given input stream.
   */
  public StringInputStream buildPluginXmlAccessClass(final InputStream pluginInputStream) {
    try {
      final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(pluginInputStream);
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
    _builder.newLine();
    _builder.append("public class PluginXml {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class Commands {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass = this.createPluginXmlAccessClass(node, "command", "org.eclipse.ui.commands");
    _builder.append(_createPluginXmlAccessClass, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class ConceptImages {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_1 = this.createPluginXmlAccessClass(node, "conceptImage", "ConceptImageContribution");
    _builder.append(_createPluginXmlAccessClass_1, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class UiSnippets {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_2 = this.createPluginXmlAccessClass(node, "uiSnippet", "EditorUiSnippets");
    _builder.append(_createPluginXmlAccessClass_2, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class Handlers {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_3 = this.createPluginXmlAccessClass(node, "handler", "org.eclipse.ui.handlers");
    _builder.append(_createPluginXmlAccessClass_3, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class ConceptMigrators {");
    _builder.newLine();
    _builder.append("\t\t");
    Object _createPluginXmlAccessClass_4 = this.createPluginXmlAccessClass(node, "migrator", "ConceptMigrator");
    _builder.append(_createPluginXmlAccessClass_4, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static class Concept {");
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
    CharSequence _createPluginXmlInternalClasses = this.createPluginXmlInternalClasses(node, "extension-point");
    _builder.append(_createPluginXmlInternalClasses, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public Object createPluginXmlAccessClass(final Node node, final String extensionType, final String group) {
    StringConcatenation _builder = new StringConcatenation();
    final Node extenionPointNode = this.getExtensionPointNode(node, group);
    _builder.newLineIfNotEmpty();
    {
      if ((extenionPointNode != null)) {
        CharSequence _createPluginXmlInternalClasses = this.createPluginXmlInternalClasses(node, extensionType);
        _builder.append(_createPluginXmlInternalClasses);
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence createPluginXmlInternalClasses(final Node node, final String extensionType) {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<Node> _classDefiningChildren = this.getClassDefiningChildren(node, extensionType);
      for(final Node childNode : _classDefiningChildren) {
        _builder.append("public static class ");
        String _replace = this.getClassName(childNode).replace("-", "");
        _builder.append(_replace);
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        {
          ArrayList<Node> _attributes = this.getAttributes(childNode);
          for(final Node attributeNode : _attributes) {
            _builder.append("\t");
            _builder.append("public static final String ");
            String _attributeName = this.getAttributeName(attributeNode);
            _builder.append(_attributeName, "\t");
            _builder.append(" = \"");
            String _nodeValue = attributeNode.getNodeValue();
            _builder.append(_nodeValue, "\t");
            _builder.append("\";");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  /**
   * Gets all children of a node, including nested nodes.
   */
  public Iterable<Node> getAllChildren(final Node node) {
    final ArrayList<Node> children = this.getChildren(node);
    final Function1<Node, Iterable<Node>> _function = (Node it) -> {
      return this.getAllChildren(it);
    };
    final List<Node> deepChildren = IterableExtensions.<Node>toList(IterableExtensions.<Node, Node>flatMap(children, _function));
    final ArrayList<Node> allChildren = new ArrayList<Node>();
    allChildren.addAll(children);
    allChildren.addAll(deepChildren);
    return allChildren;
  }
  
  /**
   * Gets all child nodes that define a class in the java file.
   * Note that it is possible for an element with the same ID to reappear due to the user
   * declaring it twice in the plugin.xml.
   * 
   * The primary use case is when the user wants to "overwrite" an extension from the generated section
   * of a plugin.xml in the protected region (for example to refine the section of an uiSnippet).
   * In this case, Eclipse takes the last declaration, the resource builder should reflect this.
   */
  public List<Node> getClassDefiningChildren(final Node node, final String extensionType) {
    final Iterable<Node> children = this.getAllChildren(node);
    final Function1<Node, Boolean> _function = (Node childNode) -> {
      return Boolean.valueOf(((childNode.getNodeType() == Node.ELEMENT_NODE) && childNode.getNodeName().equals(extensionType)));
    };
    final List<Node> elementChildren = IterableExtensions.<Node>toList(IterableExtensions.<Node>filter(children, _function));
    final Function1<Node, String> _function_1 = (Node it) -> {
      return this.getClassName(it);
    };
    final List<String> elementChildrenClassNames = ListExtensions.<Node, String>map(elementChildren, _function_1);
    final Function1<Node, Boolean> _function_2 = (Node childNode) -> {
      final int index = elementChildren.indexOf(childNode);
      final String className = elementChildrenClassNames.get(index);
      final int lastIndex = elementChildrenClassNames.lastIndexOf(className);
      return Boolean.valueOf((index == lastIndex));
    };
    final List<Node> elementChildrenWithLastClassNames = IterableExtensions.<Node>toList(IterableExtensions.<Node>filter(elementChildren, _function_2));
    return elementChildrenWithLastClassNames;
  }
  
  /**
   * Gets an iterateable list of child nodes from a node.
   */
  public ArrayList<Node> getChildren(final Node node) {
    final ArrayList<Node> arraylist = new ArrayList<Node>();
    int _length = node.getChildNodes().getLength();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _length, true);
    for (final Integer i : _doubleDotLessThan) {
      arraylist.add(node.getChildNodes().item((i).intValue()));
    }
    return arraylist;
  }
  
  /**
   * Gets an iterateable list of attributes nodes from a node
   */
  public ArrayList<Node> getAttributes(final Node node) {
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
  
  /**
   * Tries to constructs a unique, versioned class name for a given extension.
   */
  public String getClassName(final Node node) {
    String _classNameFromIdentifier = this.getClassNameFromIdentifier(node);
    String _classSuffixFromVersion = this.getClassSuffixFromVersion(node);
    return (_classNameFromIdentifier + _classSuffixFromVersion);
  }
  
  /**
   * Remap the name of an attribute in case that it conflicts with a java keyword
   */
  public String getAttributeName(final Node node) {
    boolean _equals = node.getNodeName().equals("class");
    if (_equals) {
      return "CLASSNAME";
    } else {
      return node.getNodeName().toUpperCase();
    }
  }
  
  /**
   * Gets the name of the class for a given node.
   */
  public String getClassNameFromIdentifier(final Node node) {
    final String[] tokens = this.getIdentifierAttribute(node).getNodeValue().split("[.]");
    int _length = tokens.length;
    int _minus = (_length - 2);
    final String lastIDFragement = IterableExtensions.join(IterableExtensions.<String>drop(((Iterable<String>)Conversions.doWrapArray(tokens)), Math.max(_minus, 0)));
    return StringExtensions.toFirstUpper(lastIDFragement);
  }
  
  /**
   * Gets the identifier attribute for constructing class name from a node
   */
  public Node getIdentifierAttribute(final Node node) {
    Node identifierAttribute = node.getAttributes().getNamedItem("id");
    if ((identifierAttribute == null)) {
      identifierAttribute = node.getAttributes().getNamedItem("fullQualifiedID");
    }
    if ((identifierAttribute == null)) {
      identifierAttribute = node.getAttributes().getNamedItem("commandId");
    }
    if ((identifierAttribute == null)) {
      identifierAttribute = node.getAttributes().getNamedItem("class");
    }
    return identifierAttribute;
  }
  
  /**
   * Gets a version attribute from the extension, if one is defined.
   */
  public String getClassSuffixFromVersion(final Node node) {
    final Node versionAttribute = node.getAttributes().getNamedItem("version");
    if ((versionAttribute != null)) {
      return versionAttribute.getNodeValue().replace(".", "_");
    } else {
      return "";
    }
  }
  
  /**
   * Checks if a given node defines an extension point of the passed group.
   */
  public Node getExtensionPointNode(final Node node, final String group) {
    final ArrayList<Node> children = this.getChildren(node);
    for (final Node child : children) {
      short _nodeType = child.getNodeType();
      boolean _equals = (_nodeType == Node.ELEMENT_NODE);
      if (_equals) {
        final Node idAttribute = child.getAttributes().getNamedItem("point");
        final String nodeValue = idAttribute.getNodeValue();
        boolean _contains = nodeValue.contains(group);
        if (_contains) {
          return child;
        }
      }
    }
    return null;
  }
  
  public IProject getTheProject() {
    return this.getProject();
  }
}
