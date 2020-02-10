/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.provider;




import java.net.URL;

import java.util.HashMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * This is the central singleton for the DVLM edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class DVLMEditPlugin extends EMFPlugin {

	public static final String PLUGIN_ID = "de.dlr.sc.virsat.model.edit";
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * Keep track of the image registry.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final HashMap<String, URL> imageRegistry = new HashMap<String, URL>();

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DVLMEditPlugin INSTANCE = new DVLMEditPlugin();

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DVLMEditPlugin() {
		super
		  (new ResourceLocator [] {
		     EcoreEditPlugin.INSTANCE,
		   });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}
	
	/**
	 * *********************************
	 * VirSat Specific Code Generation
	 * *********************************
	 * Getter for the Image registry of the Edit Plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return A Hashmap with registered ConceptImages
	 * @generated
	 */
	public static HashMap<String, URL> getImageRegistry() {
		return imageRegistry;
	}
	

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static class Implementation extends EclipsePlugin {
		public static final String EXTENSION_POINT_ID_CONCEPT_IMAGE = "de.dlr.sc.virsat.model.edit.ConceptImageContribution";
		
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
		
		@Override
		public void start(BundleContext context) throws Exception {
			super.start(context);
			readConceptImageExtensionPoints();
		}
		
		/**
		 * *********************************
	 	 * VirSat Specific Code Generation
	     * *********************************
		 * call this method to read the extension point for the concept images
		 * It registers the found images in the bundles ImgageRegistry
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		public void readConceptImageExtensionPoints() {
			IExtensionRegistry registry = Platform.getExtensionRegistry();
			
			// Read all concept images through the extension point definitions in the various concept plugins
			IConfigurationElement[] conceptImagesConfigurations = registry.getConfigurationElementsFor(EXTENSION_POINT_ID_CONCEPT_IMAGE);
			
			for (IConfigurationElement configElement : conceptImagesConfigurations) {
				String key = configElement.getAttribute("fullQualifiedID");
				String pathToImage = configElement.getAttribute("pathToImage");
				String bundleID = configElement.getContributor().getName();
				
				Bundle bundle = Platform.getBundle(bundleID);
		        IPath path = new Path(pathToImage);
		        URL url = FileLocator.find(bundle, path, null);
				
				imageRegistry.put(key, url);
			}
		}
		
		@Override
		public void stop(BundleContext context) throws Exception {
			super.stop(context);
		}
		
	}

}
