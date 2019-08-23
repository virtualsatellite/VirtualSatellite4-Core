/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.html;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.transaction.RecordingCommand;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceType;


/**
 * Test Case for HTML Export
 * @author bell_er
 *
 */
public class HTMLExporterTest extends AConceptProjectTestCase {
	private static final String CONCEPT_ID_EGSCC = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String CONCEPT_ID_FUNCELECTRICAL = Activator.getPluginId();

	
	public static final String FRAGMENT_ID = "de.dlr.sc.virsat.model.extension.funcelectrical.test";

	StructuralElementInstance seiCT;
	StructuralElementInstance seiEC1;
	StructuralElementInstance seiEC2;
	StructuralElementInstance seiHarness;
	StructuralElementInstance seiITC;
	

	Concept conceptEgscc;
	Concept conceptFuncelectrical;


	@Before
	public void setUp() throws CoreException {
		super.setUp();
		UserRegistry.getInstance().setSuperUser(true);

		addEditingDomainAndRepository();
		conceptEgscc  = loadConceptFromPlugin(CONCEPT_ID_EGSCC);
		conceptFuncelectrical = loadConceptFromPlugin(CONCEPT_ID_FUNCELECTRICAL);
		
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				repository.getActiveConcepts().add(conceptEgscc);
				repository.getActiveConcepts().add(conceptFuncelectrical);
			}
		});
		// Create Interface Type 
		  
	    InterfaceType mil = new InterfaceType(conceptFuncelectrical);
	    mil.getTypeInstance().setUuid(new VirSatUuid("1cd42892-eb5f-42ac-881d-e8ef4825254a"));
	    mil.setName("MIL");	     
		
	     // Create interface ends
	    InterfaceEnd ec1a = new InterfaceEnd(conceptFuncelectrical);
	    InterfaceEnd ec1b = new InterfaceEnd(conceptFuncelectrical);
	    InterfaceEnd ec2a = new InterfaceEnd(conceptFuncelectrical);
	    InterfaceEnd ec2b = new InterfaceEnd(conceptFuncelectrical);
	    InterfaceEnd ec2c = new InterfaceEnd(conceptFuncelectrical);
	    InterfaceEnd noType =  new InterfaceEnd(conceptFuncelectrical);
	    noType.setName("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	    ec1a.setName("EC1A");
	    ec1b.setName("EC1B");
	    ec2a.setName("EC2A");
	    ec2b.setName("EC2B");
	    ec2c.setName("EC2C");
	    ec1a.setType(mil);
	    ec1b.setType(mil);
	    ec2a.setType(mil);
	    ec2b.setType(mil);
	    
	    // create interfaces
	    Interface interfaceA =  new Interface(conceptFuncelectrical);
	    Interface interfaceB =  new Interface(conceptFuncelectrical);
	    
	    interfaceA.setInterfaceEndFrom(ec1a);
	    interfaceA.setInterfaceEndTo(ec2a);
	    
	    interfaceB.setInterfaceEndFrom(ec1b);
	    interfaceB.setInterfaceEndTo(ec2b);
	    // Create InterfaceTypeCollection
	    
	    seiITC = StructuralFactory.eINSTANCE.createStructuralElementInstance();
	    seiITC.setType(ActiveConceptHelper.getStructuralElement(conceptFuncelectrical, "InterfaceTypeCollection"));
	    seiITC.setUuid(new VirSatUuid("9eb671d2-8c65-469f-ad77-2e3481e747ea"));
	    seiITC.setName("InterfaceTypeCollection");
		// Create A configuration Tree And its Children
		
		seiCT = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiCT.setType(ActiveConceptHelper.getStructuralElement(conceptEgscc, "ConfigurationTree"));
		seiCT.setUuid(new VirSatUuid("7047cb78-13c5-45ed-bc5f-c831d1a1dbb2"));
		seiCT.setName("ConfigurationTree");
		
		seiEC1 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEC1.setType(ActiveConceptHelper.getStructuralElement(conceptEgscc, "ElementConfiguration"));
		seiEC1.setName("ElementConfiguration1");
		seiEC1.setUuid(new VirSatUuid("a9ae563e-385c-4871-b8a6-b98d5158ab9f"));
		seiEC1.getCategoryAssignments().add(ec1a.getTypeInstance());
		seiEC1.getCategoryAssignments().add(ec1b.getTypeInstance());
		seiEC1.getCategoryAssignments().add(noType.getTypeInstance());
		
		seiEC2 = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiEC2.setType(ActiveConceptHelper.getStructuralElement(conceptEgscc, "ElementConfiguration"));
		seiEC2.setName("ElementConfiguration2");
		seiEC2.setUuid(new VirSatUuid("f18c1cf5-ed6e-4d29-8e56-c4efe5c25593"));
		seiEC2.getCategoryAssignments().add(ec2a.getTypeInstance());
		seiEC2.getCategoryAssignments().add(ec2b.getTypeInstance());
		seiEC2.getCategoryAssignments().add(ec2c.getTypeInstance());
		
		seiHarness = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		seiHarness.setType(ActiveConceptHelper.getStructuralElement(conceptEgscc, "ElementConfiguration"));
		seiHarness.setName("Harness");
		seiHarness.setUuid(new VirSatUuid("9ae21aa5-5e6b-4b78-a3c9-76c8a5d36849"));
		seiHarness.getCategoryAssignments().add(interfaceA.getTypeInstance());
		seiHarness.getCategoryAssignments().add(interfaceB.getTypeInstance());
		
		seiCT.getChildren().add(seiEC1);
		seiCT.getChildren().add(seiEC2);
		seiCT.getChildren().add(seiHarness);
	}
	/**
	 * Method to remove the date and exporter name of html exports will be used to compare two files to test them
	 * @param str the html file
	 * @return the html file without exporter name and date
	 */
	public String removeDate(String str) {
		int first1 = str.lastIndexOf("<footer>") + "<footer>".length();
		int second2 = str.indexOf("</footer>");
		StringBuilder sb = new StringBuilder(str);

		sb.delete(first1, second2);

		String result = sb.toString();
		return result;
	}
	/**
	 * Method to access the fragments contents from the resource folder and to ahnd it back as string
	 * @param resourcePath the path to the resource starting with "resource/"
	 * @return the content of the resource as string
	 * @throws IOException throws
	 */
	public static InputStream getResourceContentAsString(String resourcePath) throws IOException {
		URL url;
		url = new URL("platform:/plugin/" + FRAGMENT_ID + resourcePath);
		InputStream inputStream = url.openConnection().getInputStream();

		return inputStream;
	}
	@Test
	public void exportConfigurationTree() throws IOException  { 
		ExportHelper eh = new ExportHelper();

		String path = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
		eh.export(seiCT, path);

		InputStream is = getResourceContentAsString("/resources/ConfigurationTree.htm");
		File file = new File(path + "/" + ExportHelper.INDEX);
		InputStream is2 = new FileInputStream(file);			
		String first = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
		String trimmedFirst = removeDate(first);
		trimmedFirst = trimmedFirst.replaceAll("\r\n", System.lineSeparator());
		String second = CharStreams.toString(new InputStreamReader(is2, Charsets.UTF_8));
		String trimmedSecond = removeDate(second);
		assertEquals(trimmedFirst, trimmedSecond);
	}
	@Test
	public void interfaceTypeCollection() throws IOException  { 
		ExportHelper eh = new ExportHelper();

		String path = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toOSString();
		eh.export(seiITC, path);

		InputStream is = getResourceContentAsString("/resources/InterfaceTypeCollection.htm");
		File file = new File(path + "/" + ExportHelper.INDEX);
		InputStream is2 = new FileInputStream(file);			
		String first = CharStreams.toString(new InputStreamReader(is, Charsets.UTF_8));
		String trimmedFirst = removeDate(first);
		trimmedFirst = trimmedFirst.replaceAll("\r\n", System.lineSeparator());
		String second = CharStreams.toString(new InputStreamReader(is2, Charsets.UTF_8));
		String trimmedSecond = removeDate(second);
		assertEquals(trimmedFirst, trimmedSecond);
	}
	


}
