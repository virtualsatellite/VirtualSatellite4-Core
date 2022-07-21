/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.types.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.model.dvlm.provider.DVLMEditPlugin;

/**
 * Bean registry, that scans all Configuration Elements in the plugin.xml
 * related to the Bean Factories.
 * 
 * @author fisc_ph
 *
 */
public class BeanRegistry {

	private String extensionPointID;
	private String extensionPointElementID;
	private String extensionPointElementBeanAttributeID;
	private String extensionPointElementBeanClassAttributeID;
	private boolean extensionRegistryAvailable;

	private Map<String, IConfigurationElement> mapIdToBean = new HashMap<>();
	public static final String BEAN_PACKAGE_NAME = "model";

	/**
	 * Creates the class for the given extension point
	 * 
	 * @param extensionPointID
	 *            the Extension point id to look for
	 * @param extensionPointElementID
	 *            the actual element in the extension point to look for
	 * @param extensionPointElementBeanAttributeID
	 *            the ID attribute in the element
	 * @param extensionPointElementBeanClassAttributeID
	 *            the Class attribute in the element
	 */
	public BeanRegistry(String extensionPointID, String extensionPointElementID,
			String extensionPointElementBeanAttributeID, String extensionPointElementBeanClassAttributeID) {
		this.extensionPointID = extensionPointID;
		this.extensionPointElementID = extensionPointElementID;
		this.extensionPointElementBeanAttributeID = extensionPointElementBeanAttributeID;
		this.extensionPointElementBeanClassAttributeID = extensionPointElementBeanClassAttributeID;
		init();
	}

	/**
	 * Call this method to actually scan for correct entries in the plugin.xml
	 * and Configuration Elements respectively
	 */
	protected void init() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		extensionRegistryAvailable = registry != null;
		if (extensionRegistryAvailable) {
			IConfigurationElement[] elements = registry.getConfigurationElementsFor(extensionPointID);

			for (IConfigurationElement configurationElement : elements) {
				if (configurationElement.getName().equals(extensionPointElementID)) {
					String id = configurationElement.getAttribute(extensionPointElementBeanAttributeID);
					if (id != null) {
						mapIdToBean.put(id, configurationElement);
					}
				}
			}
		}
	}

	/**
	 * Call this method to create the corresponding bean for the given ID
	 * 
	 * @param id
	 *            the ID / full qualified name of the DVLM type in the concept
	 *            for which the corresponding bean should be created
	 * @return The corresponding bean that fits to the given id / fqn
	 * @throws CoreException
	 *             an exception in case the Bean cannot be created
	 */
	public Object createBeanInstanceForId(String id) throws CoreException {
		if (extensionRegistryAvailable) {
			IConfigurationElement configurationElement = mapIdToBean.get(id);
			if (configurationElement == null) {
				throw new CoreException(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, "Could not create bean of type " + id));
			} else {
				Object object = configurationElement.createExecutableExtension(extensionPointElementBeanClassAttributeID);
				return object;
			}
		} else {
			// No registry available, this means the code runs as plain java
			// application (from Apps), try to load the bean class directly
			int lastDotIndex = id.lastIndexOf('.');
			String beanClassName = id.substring(0, lastDotIndex) + "." + BEAN_PACKAGE_NAME
					+ id.substring(lastDotIndex);
			Object instance;
			try {
				instance = Class.forName(beanClassName).getDeclaredConstructor().newInstance();
				return instance;
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new CoreException(new Status(Status.ERROR, DVLMEditPlugin.PLUGIN_ID, "Could not create bean of type " + id, e));
			}
		}
	}
}
