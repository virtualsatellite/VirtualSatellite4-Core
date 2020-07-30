/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.ps.dnd;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.roles.Discipline;
import de.dlr.sc.virsat.model.dvlm.roles.RolesFactory;
import de.dlr.sc.virsat.model.extension.ps.Activator;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ElementOccurence;

public class ProductStructureDragAndDropInheritanceCommandHelperTest extends AConceptProjectTestCase {

	private static final String CONCEPT_ID_PS = Activator.getPluginId();
	private Concept conceptPs;
	
	private ConfigurationTree ct;
	
	private ProductStructureDragAndDropInheritanceCommandHelper psDndHelper;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		addEditingDomainAndRepository();
		executeAsCommand(() -> loadConceptAndInstallToRepository(CONCEPT_ID_CORE));
		conceptPs = executeAsCommand(() -> loadConceptAndInstallToRepository(CONCEPT_ID_PS));
		
		ct = new ConfigurationTree(conceptPs);
		
		psDndHelper = new ProductStructureDragAndDropInheritanceCommandHelper();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreateDropCommandFails() {
		// Create a correct ED and set disciplines for testing.
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		
		ElementOccurence eo = new ElementOccurence(conceptPs);
		eo.setName("Test");		
		eo.getStructuralElementInstance().setAssignedDiscipline(discipline);
		ct.getStructuralElementInstance().setAssignedDiscipline(discipline);
		
		// Try to create a command to drop the EO onto the CT.
		// This operation does not make sense and should fail.
		Command commandInvalid = psDndHelper.createDropCommand(
				editingDomain,
				Collections.singleton(eo),
				ct
		);
		
		assertFalse("Command cannot be executed", commandInvalid.canExecute());
		
		// Try to drop an invalid random object
		// This operation does not make sense and should fail.
		Command commandInvalid2 = psDndHelper.createDropCommand(
				editingDomain,
				Collections.singleton(new Object()),
				ct
		);
		
		assertFalse("Command cannot be executed", commandInvalid2.canExecute());
		
		// Try to drop an empty list
		// This operation does not make sense and should fail.
		Command commandInvalid3 = psDndHelper.createDropCommand(
				editingDomain,
				Collections.EMPTY_LIST,
				ct
		);
		
		assertFalse("Command cannot be executed", commandInvalid3.canExecute());
	}
	
	@Test
	public void testCreateDropCommand() {
		// Create a correct ED and set disciplines for testing.
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		
		//Now create an element definition and try to add it.
		ElementDefinition ed = new ElementDefinition(conceptPs);
		ed.setName("Test");		
		ed.getStructuralElementInstance().setAssignedDiscipline(discipline);
		
		editingDomain.getVirSatCommandStack().execute(
			psDndHelper.createDropCommand(
				editingDomain,
				Collections.singleton(ed),
				ct
			)
		);
		
		assertEquals("Got new element with correct inheritance", ed, ct.getChildren(ElementConfiguration.class).get(0).getSuperSeis(ElementDefinition.class).get(0));
		assertThat(
				"Created Resource for new bean",
				editingDomain.getResourceSet().getResources(),
				hasItem(ct.getChildren(ElementConfiguration.class).get(0).getStructuralElementInstance().eResource())
		);
		
		// Now add a second time as a selection of SEI instead of bean
		editingDomain.getVirSatCommandStack().execute(
			psDndHelper.createDropCommand(
				editingDomain,
				Collections.singleton(ed.getStructuralElementInstance()),
				ct
			)
		);
		
		assertEquals("Got new element with correct inheritance", ed, ct.getChildren(ElementConfiguration.class).get(1).getSuperSeis(ElementDefinition.class).get(0));
		assertThat(
				"Created Resource for new bean",
				editingDomain.getResourceSet().getResources(),
				hasItem(ct.getChildren(ElementConfiguration.class).get(1).getStructuralElementInstance().eResource())
		);
	}

	@Test
	public void testCreateBeanStructuralElementInstanceForDrop() {
		// Create a correct ED and set disciplines for testing.
		Discipline discipline = RolesFactory.eINSTANCE.createDiscipline();
		
		ElementDefinition ed = new ElementDefinition(conceptPs);
		ed.setName("Test");		
		ed.getStructuralElementInstance().setAssignedDiscipline(discipline);
		ct.getStructuralElementInstance().setAssignedDiscipline(discipline);
		
		// Now create a drop bean for the ED in the CT, which should create a EC
		IBeanStructuralElementInstance createdBean = psDndHelper.createBeanStructuralElementInstanceForDrop(
				ed,
				ct
		);
		
		assertEquals("Got correct Bean", ElementConfiguration.class, createdBean.getClass());
		assertEquals("Set Name correctly", "Test", createdBean.getName());
		assertEquals("Set correct Discipline", discipline, createdBean.getStructuralElementInstance().getAssignedDiscipline());
	
		// Now add the created Bean to the CT. Than we create it again and check for correct change of the name
		ct.add(createdBean);
		IBeanStructuralElementInstance createdBean2 = psDndHelper.createBeanStructuralElementInstanceForDrop(
				ed,
				ct
		);
		
		assertEquals("Got correct Bean", ElementConfiguration.class, createdBean2.getClass());
		assertEquals("Set Name correctly", "Test_2", createdBean2.getName());
		assertNotSame("Realy created a new object", createdBean, createdBean2);
	}

	@Test
	public void testCreateBeanStructuralElementInstanceForDropFailures() {
		// if the concept for the target object cannot be identified the drop will not work as well
		executeAsCommand(() -> conceptPs.getStructuralElements().clear());
		IBeanStructuralElementInstance resultNoConcept = psDndHelper.createBeanStructuralElementInstanceForDrop(
				new ElementDefinition(conceptPs),
				ct
		);
		
		assertNull("Can only decide for SEIs what to create", resultNoConcept);
	}
}
