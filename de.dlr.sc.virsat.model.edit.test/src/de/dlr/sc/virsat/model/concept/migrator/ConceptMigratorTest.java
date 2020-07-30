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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;

/**
 * This class tests the ConceptMigrator
 * @author muel_s8
 *
 */

public class ConceptMigratorTest extends AConceptMigratorTest {
	private ConceptMigrator conceptMigrator;
	private ConceptMigrator.IMigratorDescriptor oldDescriptor;
	private ConceptMigrator.IMigratorDescriptor newDescriptor;
	
	/**
	 * A test implementation of IMigrator that updates the version
	 * of a given concept
	 * @author muel_s8
	 *
	 */
	
	private static class TestMigrator implements IMigrator {
		private String version;
		
		/**
		 * Constructor.
		 * @param version the version to migrate to
		 */
		TestMigrator(String version) {
			this.version = version;
		}

		@Override
		public void migrate(Concept concept, IMigrator previousMigrator) {
			concept.setVersion(version);
		}

		@Override
		public void setResource(String resource) {
		}

		@Override
		public String getResource() {
			return version;
		}

		@Override
		public Set<String> getNewDependencies(Concept concept, IMigrator previousMigrator) {
			return Collections.emptySet();
		}
		
	}
	
	@Before
	@Override
	public void setup() throws Exception {
		super.setup();
	
		oldDescriptor = new ConceptMigrator.IMigratorDescriptor() {
			
			@Override
			public String getVersion() {
				return "1.0";
			}
			
			@Override
			public String getResource() {
				return "";
			}
			
			@Override
			public String getID() {
				return oldConcept.getFullQualifiedName();
			}
			
			@Override
			public IMigrator createMigrator() throws CoreException {
				return new TestMigrator("1.0");
			}
		};

		newDescriptor = new ConceptMigrator.IMigratorDescriptor() {
			
			@Override
			public String getVersion() {
				return "1.1";
			}
			
			@Override
			public String getResource() {
				return "";
			}
			
			@Override
			public String getID() {
				return oldConcept.getFullQualifiedName();
			}
			
			@Override
			public IMigrator createMigrator() throws CoreException {
				return new TestMigrator("1.1");
			}
		};
		
		List<ConceptMigrator.IMigratorDescriptor> migratorDescriptors = new ArrayList<>();
		
		migratorDescriptors.add(newDescriptor);
		migratorDescriptors.add(oldDescriptor);
		
		conceptMigrator = new ConceptMigrator(oldConcept, migratorDescriptors);
	}
	
	@Test
	public void testMigrate() throws CoreException {
		IMigrator previousMigrator = oldDescriptor.createMigrator();
		IMigrator nextMigrator = newDescriptor.createMigrator();
		
		List<IMigrator> migrators = new ArrayList<>();
		migrators.add(previousMigrator);
		migrators.add(nextMigrator);
		
		conceptMigrator.migrate(migrators, new NullProgressMonitor());
		assertEquals("Migrator successfully executed", "1.1", oldConcept.getVersion());
	}
	
	@Test
	public void testGetSortedMigrators() throws CoreException {
		List<IMigrator> sortedMigrators = conceptMigrator.getSortedMigrators(oldConcept);
		assertEquals("Correct number of migrators have been created", 2, sortedMigrators.size());
		assertEquals("First migrator has correct version", "1.0", sortedMigrators.get(0).getResource());
		assertEquals("Second migrator has correct version", "1.1", sortedMigrators.get(1).getResource());
	}
}
