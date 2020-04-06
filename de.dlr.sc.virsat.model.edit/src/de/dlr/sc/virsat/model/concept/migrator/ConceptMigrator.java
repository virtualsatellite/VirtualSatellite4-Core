/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.concept.migrator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * Provides capabilities for migrating an old concept to the latest version available in the
 * repository of the same project.
 * @author muel_s8
 *
 */

public class ConceptMigrator {
	
	public static final String VERSION_ATTRIBUTE = "version";
	public static final String ID_ATTRIBUTE = "id";
	public static final String XMI_ATTRIBUTE = "xmi";
	
	private Concept oldConcept;
	private List<IMigratorDescriptor> migratorDescriptors;
	
	/**
	 * Describes a yet to be instantiated migrator and allows also
	 * its instantiation.
	 * @author muel_s8
	 *
	 */
	public interface IMigratorDescriptor {
		/**
		 * Gets the id of the migrator
		 * @return the id of the migrator
		 */
		String getID();
		
		/**
		 * Gets the version of the migrator
		 * @return the version of the migrator
		 */
		String getVersion();
		
		/**
		 * Gets the resource of the migrator
		 * @return the version of the migrator
		 */
		String getResource();
		
		/**
		 * Creates the migrator for this descriptor
		 * @return the migrator
		 * @throws CoreException thrown if the migrator cannot be created
		 */
		IMigrator createMigrator() throws CoreException;
	}
	
	/**
	 * A migrator descriptor that obtains its data from a configuration element 
	 * @author muel_s8
	 *
	 */
	private static class ConfigurationMigratorDescriptor implements IMigratorDescriptor {
		
		private IConfigurationElement configurationElement;
		
		/**
		 * Default constructor.
		 * @param configurationElement the configuration element
		 */
		ConfigurationMigratorDescriptor(IConfigurationElement configurationElement) {
			this.configurationElement = configurationElement;
		}
		
		@Override
		public String getID() {
			return configurationElement.getAttribute(ID_ATTRIBUTE);
		}

		@Override
		public String getVersion() {
			return configurationElement.getAttribute(VERSION_ATTRIBUTE);
		}
		
		@Override
		public String getResource() {
			return configurationElement.getAttribute(XMI_ATTRIBUTE);
		}

		@Override
		public IMigrator createMigrator() throws CoreException {
			return (IMigrator) configurationElement.createExecutableExtension("class");
		}
	}
	
	/**
	 * Constructor for preparing to migrate the desired concept to the latest
	 * version available in the same repository
	 * @param oldConcept the old concept to migrate
	 * @param migratorDescriptors the descriptors of the migrators
	 */
	public ConceptMigrator(Concept oldConcept, List<IMigratorDescriptor> migratorDescriptors) {
		this.oldConcept = oldConcept;
		this.migratorDescriptors = migratorDescriptors;
	}
	
	/**
	 * Default Constructor for preparing to migrate the desired concept to the latest
	 * version available in the same repository
	 * @param oldConcept the old concept to migrate
	 * @param oldConcept
	 */
	public ConceptMigrator(Concept oldConcept) {
		this.oldConcept = oldConcept;
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = registry.getConfigurationElementsFor(IMigrator.EXTENSION_POINT_ID);
		
		migratorDescriptors = new ArrayList<>();
		
		for (IConfigurationElement configurationElement : elements) {
			IMigratorDescriptor descriptor = new ConfigurationMigratorDescriptor(configurationElement);
			migratorDescriptors.add(descriptor);
		}
	}
	
	/**
	 * Checks if the wrapped concept requires migration
	 * @return true iff the wrapped concept requires to be migrated
	 */
	public boolean needsMigration() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = registry.getConfigurationElementsFor(IMigrator.EXTENSION_POINT_ID);
		
		for (IConfigurationElement configurationElement : elements) {
			// If we have a migrator for this concept
			if (configurationElement.getAttribute(ID_ATTRIBUTE).equals(oldConcept.getFullQualifiedName())) {
				String version = configurationElement.getAttribute(VERSION_ATTRIBUTE);
				if (ConceptMigrationHelper.compareVersions(version, oldConcept.getVersion()) > 0) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Migrates the passed concept to the latest version
	 * @param migrators will used in the passed order of the list
	 * to bring the concept to the desired version
	 * @param progressMonitor 
	 */
	public void migrate(List<IMigrator> migrators, IProgressMonitor progressMonitor) {
		// The first migrator corresponds to the current version, so we do not need to execute it
		// we just need it for reference in the next migrator
		IMigrator previousMigrator = migrators.remove(0);
		progressMonitor.beginTask("Perfoming migration of concept " +  oldConcept.getName(), migrators.size());
		for (IMigrator migrator : migrators) {
			migrator.migrate(oldConcept, previousMigrator);
			progressMonitor.worked(1);
			previousMigrator = migrator;
		}
		progressMonitor.done();
	}
	
	/**
	 * Hands back a sorted list of migrators that have to be executed on the concept
	 * such that it will be on the latest version
	 * @param concept the concept to migrate
	 * @throws CoreException if the creation of a migrator failed 
	 * @return a list of migrators
	 */
	
	public List<IMigrator> getSortedMigrators(Concept concept) throws CoreException {
		// Sort the extensions according to their version number in ascending order
		List<IMigratorDescriptor> elementsSorted = new ArrayList<>(migratorDescriptors);
		Collections.sort(elementsSorted, new Comparator<IMigratorDescriptor>() {
			@Override
			public int compare(IMigratorDescriptor mc1, IMigratorDescriptor mc2) {
				return ConceptMigrationHelper.compareVersions(mc1.getVersion(), mc2.getVersion());
			}
		});
		
		// Create the actual migrators
		List<IMigrator> migrators = new ArrayList<>();
		
		for (IMigratorDescriptor descriptor : elementsSorted) {
			// If we have a migrator for this concept
			String configId = descriptor.getID();
			if (configId.equals(concept.getFullQualifiedName())) {
				// If we have a migrator for a newer version
				if (ConceptMigrationHelper.compareVersions(descriptor.getVersion(), concept.getVersion()) >= 0) {
					IMigrator migrator = descriptor.createMigrator();
					migrator.setResource(descriptor.getResource());
					migrators.add(migrator);
				}
			}
		}
		
		return migrators;
	}
}
