/**
 * This file is part of the VirSat project.
 *
 * Copyright (c) 2008-2018s
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
package de.dlr.sc.virsat.model.extension.funcelectrical.test;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.junit.Before;

import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceTypeCollection;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementConfiguration;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
import de.dlr.sc.virsat.project.structure.command.CreateAddSeiWithFileStructureCommand;
import de.dlr.sc.virsat.project.test.AProjectTestCase;
/**
 * Abstract class for import tests
 * @author bell_er
 *
 */
public class ExcelTestCase extends AProjectTestCase {
	protected static final String CONCEPT_ID_EGSCC = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	protected static final String CONCEPT_ID_FUNCELECTRICAL = Activator.getPluginId();
	protected static final String EXTENSION_ID = "de.dlr.sc.virsat.model.Concept";
	protected static final int THREE = 3;
	protected static final int FOUR = 4;
	protected static final int FIVE = 5;
	protected InterfaceTypeCollection itc;
	protected ElementDefinition ed;
	protected ProductTree pt;
	protected ProductTreeDomain ptd;
	protected ElementConfiguration ec;
	protected ElementConfiguration ec2;
	protected ConfigurationTree ct;
	
	protected Interface iface;
	protected Interface iface2;
	 
	protected	Concept conceptEgscc;
	protected	Concept conceptFuncelectrical;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		UserRegistry.getInstance().setSuperUser(true);
	
		addEditingDomainAndRepository();
	    
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IConfigurationElement[] configSections = registry.getConfigurationElementsFor(EXTENSION_ID);
		ActiveConceptConfigurationElement acElement = ActiveConceptConfigurationElement.getPropperAddActiveConceptConfigurationElement(configSections, CONCEPT_ID_EGSCC);
		Command command = acElement.createAddActiveConceptCommand(editingDomain, repository);
		editingDomain.getCommandStack().execute(command);
	    
		acElement = ActiveConceptConfigurationElement.getPropperAddActiveConceptConfigurationElement(configSections, CONCEPT_ID_FUNCELECTRICAL);
		command = acElement.createAddActiveConceptCommand(editingDomain, repository);
		editingDomain.getCommandStack().execute(command);
	    
	    ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
	    conceptEgscc = acHelper.getConcept(CONCEPT_ID_EGSCC);
	    conceptFuncelectrical = acHelper.getConcept(CONCEPT_ID_FUNCELECTRICAL);
	    
	    
	    // Interface Type Collection to be changed	    
	    itc = new InterfaceTypeCollection(conceptFuncelectrical);
	    itc.setName("InterfaceTypeCollection");
	    itc.getStructuralElementInstance().setUuid(new VirSatUuid("af0b3603-637d-4fb6-b50c-c89b99f43b15"));	    
	    InterfaceType i1 = new InterfaceType(conceptFuncelectrical);
	    i1.getTypeInstance().setUuid(new VirSatUuid("ea816464-cea3-4db7-ae91-31d37c60a63c"));
	    i1.setName("KILL"); 
	    InterfaceType i2 = new InterfaceType(conceptFuncelectrical);
	    i2.getTypeInstance().setUuid(new VirSatUuid("1cd42892-eb5f-42ac-881d-e8ef4825254a"));
	    i2.setName("MILL");	    
	    InterfaceType i3 = new InterfaceType(conceptFuncelectrical);
	    i3.getTypeInstance().setUuid(new VirSatUuid("88544856-794e-49fd-a873-266a4008a3fc"));
	    i3.setName("HILL");
	    itc.add(i1);
	    itc.add(i2);
	    itc.add(i3);
	    
	    // add product tree and ptd and ed
	    
	    pt = new ProductTree(conceptEgscc);
	    
	    ptd = new ProductTreeDomain(conceptEgscc);
	    
	    pt.add(ptd);
	    
	  
	    		
	     
	    // Element Definition to be changed   
	    ed = new ElementDefinition(conceptEgscc);
	    ed.setName("BATTERY"); 
	    ed.getStructuralElementInstance().setUuid(new VirSatUuid("74ccc93a-281b-4ab8-ace4-cb7f2b927d4b"));
	 	    
	    
	    ptd.add(ed);
	    
	    InterfaceEnd ie1 = new InterfaceEnd(conceptFuncelectrical);
	    ie1.setName("POW_IN");
	    ie1.getTypeInstance().setUuid(new VirSatUuid("a2643ddb-e6a1-4015-9b80-be931f0c5a37"));
	    ie1.setType(i1);
	    InterfaceEnd ie2 = new InterfaceEnd(conceptFuncelectrical);
	    ie2.setName("POW_SOMETHING");
	    ie2.getTypeInstance().setUuid(new VirSatUuid("9f8b18e1-f6a1-4cf6-bcb7-0d4a5f0a86de"));
	    ie2.setType(i2);
	    InterfaceEnd ie3 = new InterfaceEnd(conceptFuncelectrical);
	    ie3.setName("POW_OUT");
	    ie3.getTypeInstance().setUuid(new VirSatUuid("e13ad850-21d8-43d7-aa61-20a3f8d1749b"));
	    ie3.setType(i3);
	    
	    ed.add(ie1);
	    ed.add(ie2);
	    ed.add(ie3);
    
	    // will be used for test 3
	    
	    ct = new ConfigurationTree(conceptEgscc);
	    ct.setName("ConfigurationTree1");
	    
	    InterfaceEnd ie4 = new InterfaceEnd(conceptFuncelectrical);
	    ie4.setName("POW_IN");
	    ie4.getTypeInstance().setUuid(new VirSatUuid("a2643ddb-e6a1-4015-9b80-be931f0c5a37"));
	    ie4.setType(i1);
	    InterfaceEnd ie5 = new InterfaceEnd(conceptFuncelectrical);
	    ie5.setName("POW_SOMETHING");
	    ie5.getTypeInstance().setUuid(new VirSatUuid("9f8b18e1-f6a1-4cf6-bcb7-0d4a5f0a86de"));
	    ie5.setType(i2);
	    InterfaceEnd ie6 = new InterfaceEnd(conceptFuncelectrical);
	    ie6.setName("POW_OUT");
	    ie6.getTypeInstance().setUuid(new VirSatUuid("e13ad850-21d8-43d7-aa61-20a3f8d1749b"));
	    ie6.setType(i3);
	    
	    iface = new Interface(conceptFuncelectrical);
	    iface.setInterfaceEndFrom(ie4);
	    iface.setInterfaceEndTo(ie6);

	    iface2 = new Interface(conceptFuncelectrical);
        iface2.getTypeInstance().setUuid(new VirSatUuid("e9e077d0-2320-4229-954a-42297b37004b"));
        iface2.setInterfaceEndFrom(ie5);
        iface2.setInterfaceEndTo(ie6);

	    // to hold interfaces
	    ec = new ElementConfiguration(conceptEgscc);
	    ec.setName("Interfaces");
	    ec.add(iface);
	   
	    // to hold interface ends
	    ec2 = new ElementConfiguration(conceptEgscc);
	    ec2.setName("InterfaceEnds");
	    ec2.add(ie4);
	    ec2.add(ie5);
	    ec2.add(ie6);
	    
	    ct.add(ec);
	    ct.add(ec2);   
	    
	}
	/**
	 * adds the all 3 elements to the repository when needed
	 */
	protected void addElementsToRepository() {
		  // Add the elements to the repository
		Command command = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, itc.getStructuralElementInstance());
	    editingDomain.getCommandStack().execute(command);
	    
	    command = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, pt.getStructuralElementInstance());
	    editingDomain.getCommandStack().execute(command);
	    
	    command = CreateAddSeiWithFileStructureCommand.create(editingDomain, repository, ct.getStructuralElementInstance());
	    editingDomain.getCommandStack().execute(command);
	}
}
