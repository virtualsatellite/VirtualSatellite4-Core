/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.graphiti.diagram;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.services.Graphiti;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.concept.unittest.util.test.AConceptProjectTestCase;
import de.dlr.sc.virsat.graphiti.util.DiagramHelper;
import de.dlr.sc.virsat.model.concept.types.category.ABeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.structural.ABeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.concepts.registry.ActiveConceptConfigurationElement;
import de.dlr.sc.virsat.model.dvlm.concepts.util.ActiveConceptHelper;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.command.CreateAddStructuralElementInstanceCommand;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.model.extension.ps.model.Document;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;

/**
 * test Class for independence solver
 * @author fisc_ph
 *
 */
@SuppressWarnings("restriction")
public class BeanIndependenceSolverTest extends AConceptProjectTestCase {

	private static final String CONCEPT_ID_EGSCC = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String UUID = "ea816464-cea3-4db7-ae91-31d37c60a63c";
	private static final String DOCUMENTUUID = "ea816464-aaaa-bbbb-ae91-31d37c60a63c";

	private Concept conceptEgscc;
	
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		UserRegistry.getInstance().setSuperUser(true);

		addEditingDomainAndRepository();
		activateCoreConcept();
		
		//CHECKSTYLE:OFF
		ActiveConceptConfigurationElement accePs = new ActiveConceptConfigurationElement(null) {
			public String getXmi() { return "concept/concept.xmi"; };
			public String getId() { return CONCEPT_ID_EGSCC; };
		};

		//CHECKSTYLE:ON
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				accePs.createAddActiveConceptCommand(editingDomain, repository).execute();
				ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
				conceptEgscc = acHelper.getConcept(CONCEPT_ID_EGSCC);

			}
		});	
	}

	@Test
	public void testSolveIndependence() throws CoreException, IOException { 	
		// test getConcept
		Concept concept = DiagramHelper.getConcept(editingDomain, CONCEPT_ID_EGSCC);
		assertEquals(concept, conceptEgscc);
		
		// create Seis and Cas
		ElementDefinition ed = new ElementDefinition(conceptEgscc);
		StructuralElementInstance sei = ed.getStructuralElementInstance();
		sei.setUuid(new VirSatUuid(UUID));
		
		Document testDocument2 = new Document(concept);
		ed.add(testDocument2);

		ElementDefinition ed2 = new ElementDefinition(conceptEgscc);
		StructuralElementInstance sei2 = ed2.getStructuralElementInstance();
		
		Document testDocument = new Document(concept);
		
		// Create The diagram
		Diagram diagram = Graphiti.getPeCreateService().createDiagram("test", "testDiagram", true);
		IFolder diagramFolder = testProject.getFolder("src/diagrams/");  
		IFile diagramFile = diagramFolder.getFile("testDiagram" + "." + "test");  
		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		DiagramHelper.createDiagram(uri, diagram, rs);

		IDiagramTypeProvider dtp = GraphitiInternal.getEmfService().getDTPForDiagram(diagram);
		GraphitiInternal.getEmfService().wireDTPToDiagram(diagram, dtp);
		dtp.init(diagram, null);
		BeanIndependenceSolver beanIndependenceSolver = new BeanIndependenceSolver(dtp);
		String seiKey = beanIndependenceSolver.getKeyForBusinessObject(ed);
		assertEquals(UUID, seiKey);

		seiKey = beanIndependenceSolver.getKeyForBusinessObject(ed);
		assertEquals(UUID, seiKey);

		testDocument.getTypeInstance().setUuid(new VirSatUuid(DOCUMENTUUID));
		String caKey = beanIndependenceSolver.getKeyForBusinessObject(testDocument);
		assertEquals(DOCUMENTUUID, caKey);

		caKey = beanIndependenceSolver.getKeyForBusinessObject(testDocument);
		assertEquals(DOCUMENTUUID, caKey);

		Command command = CreateAddStructuralElementInstanceCommand.create(editingDomain, repository, ed.getStructuralElementInstance());
		editingDomain.getCommandStack().execute(command);

		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				rs.getAndAddStructuralElementInstanceResource(sei2);
			}
		});

		Resource resEd2 = rs.getStructuralElementInstanceResource(ed2.getStructuralElementInstance());
		resEd2.getContents().add(ed2.getStructuralElementInstance());
		Object sei2Object = beanIndependenceSolver.getBusinessObjectForKey(ed2.getUuid());
		ABeanStructuralElementInstance aBean = (ABeanStructuralElementInstance) sei2Object;
		assertEquals(ed2, aBean);
		
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				rs.getAndAddStructuralElementInstanceResource(sei);
			}
		});

		Boolean permission = DiagramHelper.hasDiagramWritePermission(sei);
		assertEquals(true, permission);
		
		Resource resEd = rs.getStructuralElementInstanceResource(ed.getStructuralElementInstance());
		resEd.getContents().add(ed.getStructuralElementInstance());
		Object obj = beanIndependenceSolver.getBusinessObjectForKey(seiKey);
		aBean = (ABeanStructuralElementInstance) obj;
		assertEquals(ed, aBean);

		Object documentObj = beanIndependenceSolver.getBusinessObjectForKey(testDocument2.getUuid());
		ABeanCategoryAssignment aCategory = (ABeanCategoryAssignment) documentObj;
		assertEquals(testDocument2, aCategory);
		
		assertEquals(null, beanIndependenceSolver.getKeyForBusinessObject(null));

	}
}
