/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.builder.resources

import de.dlr.sc.virsat.model.concept.Activator
import java.io.FileInputStream
import java.io.InputStream
import java.util.ArrayList
import java.util.Arrays
import java.util.Map
import java.util.jar.Attributes
import java.util.jar.Manifest
import javax.xml.parsers.DocumentBuilderFactory
import org.eclipse.core.filesystem.EFS
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.IResourceDelta
import org.eclipse.core.resources.IncrementalProjectBuilder
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Status
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtext.util.StringInputStream
import org.w3c.dom.Element
import org.w3c.dom.Node

/**
 * The Resource Access Builder reads and parses files such as the plugin.xml
 * the manifest.mf and similar to create Java files with static attributes for
 * easier access to IDs for example
 */
class ResourceAccessBuilder extends IncrementalProjectBuilder {

	public static final String MANIFEST_MF = "MANIFEST.MF";
	public static final String PLUGIN_XML = "plugin.xml";
	
	public static final String MANIFEST_MF_JAVA = "ManifestMf.java";
	public static final String PLUGIN_XML_JAVA = "PluginXml.java";
	
	public static final String BUILDER_ID = "de.dlr.sc.virsat.model.concept.resourceAccessBuilder";

	override protected build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		Activator.getDefault().getLog().log(
			new Status(Status.INFO, Activator.getPluginId(), Status.OK, "ResourceAccessBuilder: Try to trigger build",
				null));

		var delta = getDelta(project);

		switch (kind) {
			case FULL_BUILD:
				fullBuild(monitor)
			case INCREMENTAL_BUILD:
				incrementalBuild(delta, monitor)
			case AUTO_BUILD:
				if (delta === null) {
					fullBuild(monitor)
				} else {
					incrementalBuild(delta, monitor)
				}
			default:
				fullBuild(monitor)
		}

		Activator.getDefault().getLog().log(
			new Status(Status.INFO, Activator.getPluginId(), Status.OK, "ResourceAccessBuilder: Finished build", null));

		return null;
	}

	/**
	 * Do the incremental build
	 */
	def incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) {
		delta.accept [
			val deltaResource = it.resource;
			if (deltaResource instanceof IFile) {
				val iFile = it.resource as IFile;
				val iFileExists = iFile.exists;

				if (iFileExists) {
					val iFileManifestMf = MANIFEST_MF.equalsIgnoreCase(iFile.name);
					val iFilePluginXml = PLUGIN_XML.equalsIgnoreCase(iFile.name);
				
					if (iFileManifestMf) {
						writeAccessClass(buildManifestAccessClass(), MANIFEST_MF_JAVA);
					} else if (iFilePluginXml) {
						writeAccessClass(buildPluginXmlAccessClass(), PLUGIN_XML_JAVA);
					}
				}
			}
			return true;
		]
	}

	/**
	 * do the full build
	 */
	def fullBuild(IProgressMonitor monitor) {
		writeAccessClass(buildManifestAccessClass(), MANIFEST_MF_JAVA);
		writeAccessClass(buildPluginXmlAccessClass(), PLUGIN_XML_JAVA);
	}

	/**
	 * This method builds the manifest access java file
	 * from the default manifest file in the project.
	 */
	def buildManifestAccessClass() {
		var iFileManifest = project.getFile("META-INF/" + MANIFEST_MF);
		var iFileManifestUri = iFileManifest.rawLocationURI;
		var fileManifest = EFS.getStore(iFileManifestUri).toLocalFile(0, new NullProgressMonitor());
		var manifestFileInputStream = new FileInputStream(fileManifest);
		buildManifestAccessClass(manifestFileInputStream);
	}
	
	/**
	 * This method builds the manifest access java file
	 * from a given input stream.
	 */
	def buildManifestAccessClass(InputStream manifestInputStream) {
		var manifest = new Manifest(manifestInputStream);
		val classSource = createManifestAccessClass(getTheProject().name, manifest.mainAttributes);
		val classSourceStream = new StringInputStream(classSource.toString);
		return classSourceStream;
	}

	/**
	 * This method creates the string that should be written into the
	 */
	def createManifestAccessClass(String packageName, Attributes attributes) '''
		/*******************************************************************************
		 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
		 *
		 * This program and the accompanying materials are made available under the
		 * terms of the Eclipse Public License 2.0 which is available at
		 * http://www.eclipse.org/legal/epl-2.0.
		 *
		 * SPDX-License-Identifier: EPL-2.0
		 *******************************************************************************/
		package «packageName»;
		
		public class ManifestMf {
			«FOR entryKey : attributes.keySet»
				«IF attributes.get(entryKey).toString().contains(";") »
					« val String[] myArray = attributes.get(entryKey).toString().split(";") »
					public static final String «entryKey.toString.toUpperCase.replace("-", "_")» = "«myArray.get(0)»";
				«ELSE»
					public static final String «entryKey.toString.toUpperCase.replace("-", "_")» = "«attributes.get(entryKey)»";
				«ENDIF»
			«ENDFOR»
		}
	'''

	/**
	 * This method is called to build the plugin.xml access class
	 * from the default plugin.xml in the project.
	 */
	def buildPluginXmlAccessClass() {
		var iFilePlugin = project.getFile("plugin.xml");
		var iFilePluginXmlUri = iFilePlugin.rawLocationURI;
		var filePluginXml = EFS.getStore(iFilePluginXmlUri).toLocalFile(0, new NullProgressMonitor());
		var pluginFileInputStream = new FileInputStream(filePluginXml);
		return buildPluginXmlAccessClass(pluginFileInputStream);
	}
	
	/**
	 * This method is called to build the plugin.xml access class
	 * from a given input stream.
	 */
	def buildPluginXmlAccessClass(InputStream pluginInputStream) {
		val dbFactory = DocumentBuilderFactory.newInstance();
		val dBuilder = dbFactory.newDocumentBuilder();
		var doc = dBuilder.parse(pluginInputStream);

		val n1 = doc.getDocumentElement() as Node;
		val classSource = createPluginXmlAccessClass(getTheProject().name, n1);
		val classSourceStream = new StringInputStream(classSource.toString);
		return classSourceStream;
	}

	def writeAccessClass(StringInputStream classSourceStream, String fileName) {
		val iFolderSrc = getProject().getFolder("src-gen");
		if (!iFolderSrc.exists) {
			iFolderSrc.create(true, true, new NullProgressMonitor());
			val javaProject = JavaCore.create(getProject());
			val packageRoot = javaProject.getPackageFragmentRoot(iFolderSrc);
			val classPath = javaProject.getRawClasspath();
			val entries = new ArrayList(Arrays.asList(classPath));
			entries.add(JavaCore.newSourceEntry(packageRoot.getPath()));
			javaProject.setRawClasspath(entries.toArray(classPath), new NullProgressMonitor());
			packageRoot.createPackageFragment(project.name, true, new NullProgressMonitor());

		}
		val iFolderPackage = iFolderSrc.getFolder(project.name.replace(".", "\\"));
		if (!iFolderPackage.exists) {
			val javaProject = JavaCore.create(getProject());
			val packageRoot = javaProject.getPackageFragmentRoot(iFolderSrc);
			val classPath = javaProject.getRawClasspath();
			val entries = new ArrayList(Arrays.asList(classPath));
			entries.add(JavaCore.newSourceEntry(packageRoot.getPath()));
			packageRoot.createPackageFragment(project.name, true, new NullProgressMonitor());
		}
		val iFilePluginXmlAccessClassJava = iFolderPackage.getFile(fileName);
		if (iFilePluginXmlAccessClassJava.exists) {
			iFilePluginXmlAccessClassJava.setContents(classSourceStream, IResource.NONE, null);
		} else {
			iFilePluginXmlAccessClassJava.create(classSourceStream, IResource.NONE, null);
		}
	}

	/**
	 * This method creates the string that should be written into the
	 */
	def createPluginXmlAccessClass(String packageName, Node node) '''
		/*******************************************************************************
		 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
		 *
		 * This program and the accompanying materials are made available under the
		 * terms of the Eclipse Public License 2.0 which is available at
		 * http://www.eclipse.org/legal/epl-2.0.
		 *
		 * SPDX-License-Identifier: EPL-2.0
		 *******************************************************************************/
		package «packageName»;
		
		public class PluginXml {
			public static class Commands {
				«createPluginXmlAccessClass(node, "command", "org.eclipse.ui.commands")»
			}
			public static class ConceptImages {
				«createPluginXmlAccessClass(node, "conceptImage", "ConceptImageContribution")»
			}
			public static class UiSnippets {
				«createPluginXmlAccessClass(node, "uiSnippet", "EditorUiSnippets")»
			}
			public static class Handlers {
				«createPluginXmlAccessClass(node, "handler", "org.eclipse.ui.handlers")»
			}
			public static class ConceptMigrators {
				«createPluginXmlAccessClass(node, "migrator", "ConceptMigrator")»
			}
			public static class Concept {
				«createPluginXmlAccessClass(node, "concept", "Concept")»
			}
			public static class ExtensionPoints {
				«createPluginXmlInternalClasses(node, "extension-point")»
			}
		}
		
	'''

	def Object createPluginXmlAccessClass(Node node, String extensionType, String group) '''
		«val extenionPointNode = getExtensionPointNode(node, group)»
		«IF extenionPointNode !== null»
			«createPluginXmlInternalClasses(node, extensionType)»
		«ENDIF»
	'''

	def createPluginXmlInternalClasses(Node node, String extensionType) '''
		«FOR childNode : getClassDefiningChildren(node, extensionType)»
			public static class «getClassName(childNode).replace("-","")» {
				«FOR attributeNode : getAttributes(childNode)»
					public static final String «getAttributeName(attributeNode)» = "«attributeNode.nodeValue»";
				«ENDFOR»
			}
		«ENDFOR»
	'''
	
	/**
	 * Gets all children of a node, including nested nodes.
	 */
	def Iterable<Node> getAllChildren(Node node) {
		val children = getChildren(node);
		val deepChildren = children.flatMap[getAllChildren].toList;
		val allChildren = new ArrayList<Node>();
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
	def getClassDefiningChildren(Node node, String extensionType) {
		val children = getAllChildren(node);
		
		// filter out the children that do not propery define an extension
		val elementChildren = children.filter[childNode | 
			childNode.getNodeType() == Node.ELEMENT_NODE && childNode.nodeName.equals(extensionType)
		].toList;
		
		// filter out doubly defined class names and take the last node
		val elementChildrenClassNames = elementChildren.map[className];
		val elementChildrenWithLastClassNames = elementChildren.filter[childNode |
			val index = elementChildren.indexOf(childNode);
			val className = elementChildrenClassNames.get(index);
			//Filter out all nodes except for the last occurence of the class name
			val lastIndex = elementChildrenClassNames.lastIndexOf(className);
			return index == lastIndex;
		].toList;
		
		return elementChildrenWithLastClassNames;
	}
	
	/**
	 * Gets an iterateable list of child nodes from a node.
	 */
	def getChildren(Node node) {
		val arraylist = new ArrayList<Node>;
		for (i : 0 ..< node.childNodes.length) {
			arraylist.add(node.childNodes.item(i));
		}
		return arraylist;
	}

	/**
	 * Gets an iterateable list of attributes nodes from a node
	 */
	def getAttributes(Node node) {
		val eElement = node as Element;
		val nodeMap = eElement.getAttributes();
		val arraylist = new ArrayList<Node>;
		for (i : 0 ..< nodeMap.length) {
			arraylist.add(nodeMap.item(i));
		}
		return arraylist;
	}

	/**
	 * Tries to constructs a unique, versioned class name for a given extension.
	 */
	def getClassName(Node node) {
		return getClassNameFromIdentifier(node) + getClassSuffixFromVersion(node);
	}
	
	/**
	 * Remap the name of an attribute in case that it conflicts with a java keyword
	 */
	def getAttributeName(Node node) {
		if (node.nodeName.equals("class")) {
			return "CLASSNAME"
		} else {
			return node.nodeName.toUpperCase;
		}
	}

	/**
	 * Gets the name of the class for a given node.
	 */
	def getClassNameFromIdentifier(Node node) {		
		val tokens = getIdentifierAttribute(node).nodeValue.split("[.]");
		// Use the (up to) last two segments of an id for identificiation
		// We use two segments because sometimes just the last segment is not unqiue
		val lastIDFragement = tokens.drop(Math.max(tokens.length - 2, 0)).join;
		return lastIDFragement.toFirstUpper;
	}
	
	/** 
	 * Gets the identifier attribute for constructing class name from a node
	 */
	def getIdentifierAttribute(Node node) {
		var identifierAttribute = node.attributes.getNamedItem("id");
		if (identifierAttribute === null) {
			identifierAttribute = node.attributes.getNamedItem("fullQualifiedID");
		}
		if (identifierAttribute === null) {
			identifierAttribute = node.attributes.getNamedItem("commandId");
		}
		if (identifierAttribute === null) {
			identifierAttribute = node.attributes.getNamedItem("class");
		}
		
		return identifierAttribute;
	}

	/** 
	 * Gets a version attribute from the extension, if one is defined.
	 */
	def getClassSuffixFromVersion(Node node) {
		val versionAttribute = node.attributes.getNamedItem("version");
		if (versionAttribute !== null) {
			return versionAttribute.nodeValue.replace(".", "_");
		} else {
			return "";
		}
	}

	/**
	 * Checks if a given node defines an extension point of the passed group.
	 */
	def getExtensionPointNode(Node node, String group) {
		val children = getChildren(node);
	
		for (child : children) {
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				val idAttribute = child.attributes.getNamedItem("point");
				val nodeValue = idAttribute.nodeValue;
				if (nodeValue.contains(group)) {
					return child;
				}
			}
		}
		return null;
	}

	def getTheProject() {
		return project;
	}
}
