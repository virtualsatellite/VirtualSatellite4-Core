/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.build.validator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.build.test.ABuilderTest;
import de.dlr.sc.virsat.model.dvlm.categories.CategoriesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.PropertydefinitionsFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.ResourceProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.PropertyinstancesFactory;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ResourcePropertyInstance;
import de.dlr.sc.virsat.model.dvlm.validator.IStructuralElementInstanceValidator;

/**
 * TestCase for missing uploaded document test validator to inform user when
 * uploaded file is no longer available
 * 
 * @author desh_me
 *
 */

public class DvlmMissingResourcePropertyValidatorTest extends ABuilderTest {

	private Category cDocument;
	private CategoryAssignment caDocument;

	private ResourceProperty rp;
	private ResourcePropertyInstance rpi;

	private File fileNewDocument;

	@Before
	public void setUp() throws Exception {
		super.setUp();

		// Adding a Document category
		cDocument = CategoriesFactory.eINSTANCE.createCategory();
		cDocument.setName("Document");
		cDocument.getApplicableFor().add(se);

		// Adding a resource property to document
		rp = PropertydefinitionsFactory.eINSTANCE.createResourceProperty();
		ip.setName("DocumentResourceProperty");
		rpi = PropertyinstancesFactory.eINSTANCE.createResourcePropertyInstance();
		rpi.setType(rp);

		// this will attach a new document to seiObc
		caDocument = CategoriesFactory.eINSTANCE.createCategoryAssignment();
		caDocument.setType(cDocument);
		caDocument.setName("newDoc");
		caDocument.getPropertyInstances().add(rpi);
		seiEdObc.getCategoryAssignments().add(caDocument);

		// create a temp local file in a document folder of seiedObc
		String root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		String filePath = seiEdObc.eResource().getURI().toPlatformString(true).replace("StructuralElement.dvlm", "documents/newFile.txt");

		fileNewDocument = new File(root + "/" + filePath);
		if (!fileNewDocument.createNewFile()) {
			fail("Could not setup test case: Failed to create new document file!");
		}

		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

		// set resource property with file path
		URI platFormUri = URI.createPlatformResourceURI(filePath, false);
		rpi.setUri(platFormUri);
	}

	@Test
	public void testValidateOnMissingDocument() throws CoreException, IOException {
		// first check for no dvlm validator marker
		assertEquals("There are no markers yet", 0, fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
		IStructuralElementInstanceValidator seiValidator = new DvlmMissingResourcePropertyValidator();
		assertTrue("validator brings no error", seiValidator.validate(seiEdObc));

		// now delete locally created file
		if (!fileNewDocument.delete()) {
			fail("Could not proceed with test case: Failed to delete document file!");
		}
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);

		// now again check for dvlm validator marker, there should be one warning
		// against missing uploaded document
		assertFalse("validator finds missing file", seiValidator.validate(seiEdObc));
		assertEquals("There is a marker now", 1, fileObc.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE).length);
	}
}
