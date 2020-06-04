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

	private static final String CONCEPT_ID_EXTENDED_PS = de.dlr.sc.virsat.model.extension.ps.Activator.getPluginId();
	private static final String SEI_UUID = "ea816464-cea3-4db7-ae91-31d37c60a63c";
	private static final String DOCUMENT_UUID = "ea816464-aaaa-bbbb-ae91-31d37c60a63c";

	private Concept conceptEgscc;
	private Document testDocument;
	private Document testDocument2;
	private BeanIndependenceSolver beanIndependenceSolver;
	private ElementDefinition ed1;
	private ElementDefinition ed2;
	private StructuralElementInstance sei1;
	private StructuralElementInstance sei2;
	private Command command;

	@Override
	@Before
	public void setUp() throws CoreException {
		super.setUp();
		UserRegistry.getInstance().setSuperUser(true);

		addEditingDomainAndRepository();
		activateCoreConcept();

		//CHECKSTYLE:OFF
		ActiveConceptConfigurationElement accePs = new ActiveConceptConfigurationElement(null) {
			@Override
			public String getXmi() { return "concept/concept.xmi"; };
			@Override
			public String getId() { return CONCEPT_ID_EXTENDED_PS; };
		};

		//CHECKSTYLE:ON
		editingDomain.getVirSatCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				accePs.createAddActiveConceptCommand(editingDomain, repository).execute();
				ActiveConceptHelper acHelper = new ActiveConceptHelper(repository);
				conceptEgscc = acHelper.getConcept(CONCEPT_ID_EXTENDED_PS);
			}
		});

		Concept concept = DiagramHelper.getConcept(editingDomain, CONCEPT_ID_EXTENDED_PS);
		assertEquals(concept, conceptEgscc);

		testDocument = new Document(concept);
		testDocument2 = new Document(concept);
		testDocument.getTypeInstance().setUuid(new VirSatUuid(DOCUMENT_UUID));

		IDiagramTypeProvider dtp = getDiagramTypeProvider();
		beanIndependenceSolver = new BeanIndependenceSolver(dtp);

		ed1 = new ElementDefinition(conceptEgscc);
		ed2 = new ElementDefinition(conceptEgscc);
		sei1 = ed1.getStructuralElementInstance();
		sei2 = ed2.getStructuralElementInstance();
		sei1.setUuid(new VirSatUuid(SEI_UUID));
		ed1.add(testDocument2);
		getAndAddStructuralElementInstance(sei1);

		command = CreateAddStructuralElementInstanceCommand.create(editingDomain, repository, ed1.getStructuralElementInstance());
	}

	@Test
	public void testSolveIndependenceForSei() throws CoreException, IOException {
		editingDomain.getCommandStack().execute(command);

		// Test bean retrieval with key ed1 key
		String seiKeyForEd1 = beanIndependenceSolver.getKeyForBusinessObject(ed1);
		assertEquals("Test SEI key from bean", SEI_UUID, seiKeyForEd1);

		getAndAddStructuralElementInstance(sei1);
		checkDiagramWritePermissionForStructuralElement(sei1);
		addStructuralElementToResource(ed1);

		Object sei1Object = beanIndependenceSolver.getBusinessObjectForKey(seiKeyForEd1);
		assertEquals("Test Structural Element Bean from ed1 key", ed1, sei1Object);

		// Test bean retrieval with ed2 key
		getAndAddStructuralElementInstance(sei2);
		addStructuralElementToResource(ed2);

		Object sei2Object = beanIndependenceSolver.getBusinessObjectForKey(ed2.getUuid());
		assertEquals("Test Structural Element Bean from ed2 key", ed2, sei2Object);
	}

	@Test
	public void testSolveIndependenceForBeanProperty() throws CoreException, IOException {
		Object beanNoteObj = beanIndependenceSolver.getBusinessObjectForKey(testDocument2.getNoteBean().getUuid());
		assertEquals(testDocument2.getNoteBean(), beanNoteObj);
	}

	@Test
	public void testSolveIndependenceForCa() throws CoreException, IOException {
		String caKey = beanIndependenceSolver.getKeyForBusinessObject(testDocument);
		assertEquals(DOCUMENT_UUID, caKey);

		Object documentObj = beanIndependenceSolver.getBusinessObjectForKey(testDocument2.getUuid());
		assertEquals(testDocument2, documentObj);
	}

	@Test
	public void testSolveIndependenceNull() throws CoreException, IOException {
		assertEquals(null, beanIndependenceSolver.getKeyForBusinessObject(null));
	}

	private void checkDiagramWritePermissionForStructuralElement(StructuralElementInstance sei) {
		Boolean permission = DiagramHelper.hasDiagramWritePermission(sei);
		assertEquals(true, permission);
	}

	private void addStructuralElementToResource(ElementDefinition ed) {
		Resource resEd2 = rs.getStructuralElementInstanceResource(ed.getStructuralElementInstance());
		resEd2.getContents().add(ed.getStructuralElementInstance());
	}

	/**
	 * @return IDiagramTypeProvider
	 */
	private IDiagramTypeProvider getDiagramTypeProvider() {
		// Create The diagram
		Diagram diagram = Graphiti.getPeCreateService().createDiagram("test", "testDiagram", true);
		IFolder diagramFolder = testProject.getFolder("src/diagrams/");
		IFile diagramFile = diagramFolder.getFile("testDiagram" + "." + "test");
		URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		DiagramHelper.createDiagram(uri, diagram, rs);

		IDiagramTypeProvider dtp = GraphitiInternal.getEmfService().getDTPForDiagram(diagram);
		GraphitiInternal.getEmfService().wireDTPToDiagram(diagram, dtp);
		dtp.init(diagram, null);
		return dtp;
	}

	/**
	 * @param sei StructuralElementInstance
	 */
	private void getAndAddStructuralElementInstance(StructuralElementInstance sei) {
		executeAsCommand(() -> rs.getAndAddStructuralElementInstanceResource(sei));
	}
}
