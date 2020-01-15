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
import java.util.ArrayList
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
import org.eclipse.xtext.util.StringInputStream
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.net.URI
import java.io.InputStream
import org.eclipse.jdt.core.IPackageFragmentRoot
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import java.util.Arrays
import org.eclipse.jdt.core.IClasspathEntry

/**
 * The Resource Access Builder reads and parses files such as the plugin.xml
 * the manifest.mf and similar to create Java files with static attributes for
 * easier access to IDs for example
 */
class ResourceAccessBuilder extends IncrementalProjectBuilder {

	public static final String MANIFEST_MF = "MANIFEST.MF";
	public static final String PLUGIN_XML = "plugin.xml";
	public static final String BUILDER_ID = "de.dlr.sc.virsat.model.concept.resourceAccessBuilder";
 

	val String[] CONCATENATE_ID = #['CategoryAssignmentBean', 'extension', 'command', 'image', 'handler',
		 'conceptImage']
	val String[] CONCATENATE_VERSION = #['migrator']
	val String[] CONCATENATE_TABLE = #['uiSnippet']
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
					val iFileManifestMf = MANIFEST_MF.equalsIgnoreCase(iFile.name);
					val iFilePluginXml = PLUGIN_XML.equalsIgnoreCase(iFile.name);

					if (iFileExists) {
						if (iFileManifestMf) {
							var iFileManifest = project.getFile("META-INF/" + MANIFEST_MF);
								var iFileManifestUri = iFileManifest.rawLocationURI;
								var fileManifest = EFS.getStore(iFileManifestUri).toLocalFile(0, new NullProgressMonitor());
								var manifestFileInputStream = new FileInputStream(fileManifest);
								writeAccessClass(buildManifestAccessClass(manifestFileInputStream), "ManifestMf.java");
						} else if (iFilePluginXml) {
							var iFilePlugin = project.getFile("plugin.xml");
							var iFilePluginXmlUri = iFilePlugin.rawLocationURI;
							var filePluginXml = EFS.getStore(iFilePluginXmlUri).toLocalFile(0, new NullProgressMonitor());
							var pluginFileInputStream = new FileInputStream(filePluginXml);
							writeAccessClass(buildPluginXmlAccessClass(pluginFileInputStream), "PluginXml.java");
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
			var iFileManifest = project.getFile("META-INF/" + MANIFEST_MF);
			var iFileManifestUri = iFileManifest.rawLocationURI;
			var fileManifest = EFS.getStore(iFileManifestUri).toLocalFile(0, new NullProgressMonitor());
			var manifestFileInputStream = new FileInputStream(fileManifest);
			
			
			
			writeAccessClass(buildManifestAccessClass(manifestFileInputStream), "ManifestMf.java");
			var iFilePluginXml = project.getFile("plugin.xml");
			var iFilePluginXmlUri = iFilePluginXml.rawLocationURI;
			var filePluginXml = EFS.getStore(iFilePluginXmlUri).toLocalFile(0, new NullProgressMonitor());
			var pluginFileInputStream = new FileInputStream(filePluginXml);
			writeAccessClass(buildPluginXmlAccessClass(pluginFileInputStream), "PluginXml.java");
		}


		/**
		 * This method builds the manifest access java file
		 */
		def buildManifestAccessClass(InputStream manifestFileInputStream) {
			
			var manifest = new Manifest(manifestFileInputStream);
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
					«IF            attributes.get(entryKey).toString().contains(";") »
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
		 */
		def buildPluginXmlAccessClass(InputStream pluginFileInputStream) {
			

			val dbFactory = DocumentBuilderFactory.newInstance();
			val dBuilder = dbFactory.newDocumentBuilder();
			var doc = dBuilder.parse(pluginFileInputStream);

			
			val n1 = doc.getDocumentElement() as Node;
			val classSource = createPluginXmlAccessClass(getTheProject().name, n1);
			val classSourceStream = new StringInputStream(classSource.toString);
			return classSourceStream;
		}


		def writeAccessClass(StringInputStream classSourceStream , String fileName){
			val iFolderSrc = getProject().getFolder("src-gen");
			if (!iFolderSrc.exists){
				iFolderSrc.create(true, true, new NullProgressMonitor());
				val javaProject = JavaCore.create(getProject());
				val packageRoot = javaProject.getPackageFragmentRoot(iFolderSrc);
				val classPath = javaProject.getRawClasspath();
				val entries = new ArrayList(Arrays.asList(classPath));
				entries.add(JavaCore.newSourceEntry(packageRoot.getPath()));
				javaProject.setRawClasspath(entries.toArray(classPath) , new NullProgressMonitor());
				packageRoot.createPackageFragment(project.name, true , new NullProgressMonitor());	
				
			}
			val iFolderPackage = iFolderSrc.getFolder(project.name.replace(".","\\"));
			if (! iFolderPackage.exists) {
				val javaProject = JavaCore.create(getProject());
				val packageRoot = javaProject.getPackageFragmentRoot(iFolderSrc);
				val classPath = javaProject.getRawClasspath();
				val entries = new ArrayList(Arrays.asList(classPath));
				entries.add(JavaCore.newSourceEntry(packageRoot.getPath()));
				packageRoot.createPackageFragment(project.name, true , new NullProgressMonitor());	
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
				public static class commands {
					«createPluginXmlAccessClass(node, "command", "org.eclipse.ui.commands")»
				}
				public static class conceptImages {
					«createPluginXmlAccessClass(node, "conceptImage", "ConceptImageContribution")»
				}
				public static class uiSnippets {
					«createPluginXmlAccessClass(node, "uiSnippet", "EditorUiSnippets")»
				}
				public static class handlers {
					«createPluginXmlAccessClass(node, "handler", "org.eclipse.ui.handlers")»
				}
				public static class conceptMigrators {
					«createPluginXmlAccessClass(node, "migrator", "ConceptMigrator")»
				}
				public static class concept {
					«createPluginXmlAccessClass(node, "concept", "Concept")»
				}
				public static class ExtensionPoints {
					«createExtensionPoints(node, "extension-point", "plugin")»
				}
			}
			
		'''
		
		def Object createPluginXmlAccessClass(Node node, String s, String group) '''
			«IF !node.getNodeName().contains("#") && getExtensionPointID(node, group)»
				«FOR childrenNode: getChildrenNodeList(node)»
					«IF childrenNode.getNodeType() == Node.ELEMENT_NODE && childrenNode.nodeName.equals(s)»
						public static class «classNameChecker(childrenNode).toFirstUpper» {
							«FOR attributeNode: getAttributeList(childrenNode)»
								public static final String «classNameChecker(attributeNode).toUpperCase» = "«attributeNode.getNodeValue()»";
							«ENDFOR»
						}	
					«ENDIF» 	
				«ENDFOR»	
			«ELSE»
				«FOR childNode: getChildrenNodeList(node)»
					«createPluginXmlAccessClass(childNode, s, group)»
				«ENDFOR»
			«ENDIF»
		'''

		def createExtensionPoints(Node node, String s, String group) '''
			«FOR childrenNode : getChildrenNodeList(node)»
				«IF childrenNode.getNodeType() == Node.ELEMENT_NODE && childrenNode.nodeName.equals(s)»
					public static class «classNameChecker(childrenNode).toFirstUpper.replace("-","")» {
						«FOR attributeNode: getAttributeList(childrenNode)»
							public static final String «classNameChecker(attributeNode).toUpperCase» = "«attributeNode.getNodeValue()»";
						«ENDFOR»
					}	
				«ENDIF» 	
			«ENDFOR»	
			
		'''

		def getChildrenNodeList(Node node) {
			val arraylist = new ArrayList<Node>;
			for (i : 0 ..< node.childNodes.length) {
				arraylist.add(node.childNodes.item(i));
			}
			return arraylist;
		}

		def getAttributeList(Node node) {
			val eElement = node as Element;
			val nodeMap = eElement.getAttributes();
			val arraylist = new ArrayList<Node>;
			for (i : 0 ..< nodeMap.length) {
				arraylist.add(nodeMap.item(i));
			}
			return arraylist;
		}

		def classNameChecker(Node node) {
			if(node.nodeName.contains('extension-point')){
				return extensionPointID(node);
			}else	if (CONCATENATE_ID.contains(node.nodeName)) {
				return concatenateID(node);
			} else if (CONCATENATE_VERSION.contains(node.nodeName)) {
				return concatenateVersion(node);
			} else if (CONCATENATE_TABLE.contains(node.nodeName)) {
				return concatenateTable(node);
			} else if (node.nodeName.equals("class")) {
				return "className"
			} else {
				return node.nodeName;
			}
		}

		def concatenateID(Node node) {
			var name = node.attributeList.get(0).toString();
            name = name.replace("\"", "");
            val delims = "[.]";
            val tokens = name.split(delims);
            val lastpart = tokens.last;
            return node.nodeName + lastpart;
		}

		def concatenateVersion(Node node) {
			val attributes = getAttributeList(node)
			for (n : attributes) {
				if (n.nodeName.equals("version")) {
					return node.nodeName + n.nodeValue.replace(".", "_");
				}
			}
		}
	
		def extensionPointID(Node node){
			var name = node.attributeList.get(0).toString();
			name = name.replaceAll("[^a-zA-Z0-9]", "");
			name = name.replaceAll("id", "");
			return name;
		}
		def concatenateTable(Node node) {
			var name = node.attributeList.get(0).toString();
			name = name.replace("\"", "");
			val delims = "[.]";
			val tokens = name.split(delims);
			val lastpart = tokens.last;
			val beforeLastPart = tokens.get(tokens.length - 2);
			return node.nodeName + beforeLastPart.toFirstUpper + lastpart.toFirstUpper;

		}

		def getExtensionPointID(Node node, String s) {
			var attributes = getAttributeList(node);
			for (a : attributes) {
				if (a.nodeValue.contains(s)) {
					return true;
				}
			}
			return false;
		}
		
		def getTheProject() {
			return project;
		}
	}
	