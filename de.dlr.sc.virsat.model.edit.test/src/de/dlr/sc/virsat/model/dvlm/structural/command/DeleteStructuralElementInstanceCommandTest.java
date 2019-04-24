/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.structural.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.provider.PropertydefinitionsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.provider.PropertyinstancesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.categories.provider.DVLMCategoriesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.concepts.provider.ConceptsItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.general.provider.GeneralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.provider.DVLMDVLMItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.resource.provider.DVLMResourceItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.roles.provider.RolesItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.structural.provider.DVLMStructuralItemProviderAdapterFactory;
import de.dlr.sc.virsat.model.dvlm.structural.util.StructuralInstantiator;
import de.dlr.sc.virsat.model.dvlm.units.provider.UnitsItemProviderAdapterFactory;

/**
 * Class that tests the DeleteStructuralElementInstanceCommand class.
 * @author fisc_ph
 *
 */

public class DeleteStructuralElementInstanceCommandTest {

	public static final String TEST_MODEL_EXTENSION = "modeltest";
	
	private EditingDomain ed;
	private UserRegistry ur;

	private StructuralElementInstance referencingSei;
	private StructuralElementInstance seiToDelete;
	private StructuralInstantiator si = new StructuralInstantiator();

	private ResourceSet resSet;
	private Resource resource1;
	private Resource resource2;
	
	private URI resUri1;
	private URI resUri2;
	
	@Before
	public void setUp() throws Exception {
	    
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new DVLMResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMDVLMItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMStructuralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new GeneralItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ConceptsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new RolesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new UnitsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new DVLMCategoriesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertydefinitionsItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new PropertyinstancesItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack commandStack = new BasicCommandStack();
		ed = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
	
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put(TEST_MODEL_EXTENSION, new XMIResourceFactoryImpl());
	    resSet = ed.getResourceSet();
	    String wsFullPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
	    resUri1 = URI.createFileURI(wsFullPath + "/deleteSeiCommandTest/Component1." + TEST_MODEL_EXTENSION);
	    resUri2 = URI.createFileURI(wsFullPath + "/deleteSeiCommandTest/Component2." + TEST_MODEL_EXTENSION);
	    resource1 = resSet.createResource(resUri1);
	    resource2 = resSet.createResource(resUri2);
	
		
		ur = UserRegistry.getInstance();
		ur.setSuperUser(true);

		StructuralElement se = StructuralFactory.eINSTANCE.createStructuralElement();
		seiToDelete = si.generateInstance(se, "TEST_INSTANCE_NAME");
		
		StructuralElement referencingSe = StructuralFactory.eINSTANCE.createStructuralElement();
		referencingSe.setIsCanInheritFromAll(true);
		referencingSei = si.generateInstance(referencingSe, "REFERENCING_SEI");
		referencingSei.getSuperSeis().add(seiToDelete);
		
		resource1.getContents().add(seiToDelete);
		resource2.getContents().add(referencingSei);
	}

	@After
	public void tearDown() throws Exception {
		ur.setSuperUser(false);
	}

	@Test
	public void testPrepareCommand() {
		
		assertEquals("Referencing Sei currently has exactly 1 super sei", 1, referencingSei.getSuperSeis().size());
		assertNotNull("Sei has not been deleted yet and hence has a resource", seiToDelete.eResource());
		
		Command command = DeleteStructuralElementInstanceCommand.create(ed, seiToDelete);
		
		boolean canExecute = command.canExecute();
		
		assertTrue("Delete command can execute", canExecute);
		
		command.execute();
		
		assertNull("Sei has been deleted and is no longer in a resource", seiToDelete.eResource());
		assertEquals("Referencing Sei currently has exactly 0 super sei", 0, referencingSei.getSuperSeis().size());
	}

}
