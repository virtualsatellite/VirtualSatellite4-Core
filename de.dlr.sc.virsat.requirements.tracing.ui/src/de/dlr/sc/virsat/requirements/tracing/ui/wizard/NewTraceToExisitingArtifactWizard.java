/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.ui.wizard;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.ui.wizard.page.TraceArtifactSelectionPage;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class NewTraceToExisitingArtifactWizard extends TraceTargetSelectionWizard {
	
	private TraceArtifactSelectionPage page;
	
	/**
	 * Create a new trace wizard
	 */
	public NewTraceToExisitingArtifactWizard() {
		super();
	}
	
	/**
	 * Perform Finish
	 * @return return true
	 */
	public boolean performFinish() {
		// create the traceElement
		TraceElement traceElement = createTraceElement(requirementSelection);
					
		IStructuredSelection structuredSelection = (IStructuredSelection)  page.getSelection();
		if (structuredSelection != null) {
			EObject targetObject = (EObject) structuredSelection.getFirstElement();
			traceElement.getTargetTraceElement().add(targetObject);
			
			// add it to the container
			addTraceElement(traceElement);
		}
		return true;
	}

	@Override
	public void addPages() {
		super.addPages();	
		page = new TraceArtifactSelectionPage("Traceability Page");
		page.setProject(getProject());
		addPage(page);
	}

}
