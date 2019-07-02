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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TMFactory;
import de.dlr.sc.virsat.requirements.tracing.model.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.util.TraceHelper;
import de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader;
import de.dlr.sc.virsat.requirements.ui.reqif.reader.impl.DefaultReqIFValueParser;
import de.dlr.sc.virsat.requirements.ui.wizard.AbstractSelectionWizard;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class TraceTargetSelectionWizard extends AbstractSelectionWizard {

	protected IReqIFReader reqIFValueParser = null;
	
	/**
	 * Create a new trace wizard
	 */
	public TraceTargetSelectionWizard() {
		super();
		setWindowTitle("Select Trace Target Artifact");	
		reqIFValueParser = new DefaultReqIFValueParser(0, 2, 1);
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.wizard.AbstractSelectionWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.wizard.AbstractSelectionWizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Adds the given trace element to the traceability link container
	 * @param traceElement the given trace Element
	 */
	protected void addTraceElement(TraceElement traceElement) {
		TraceHelper.persistTraceElement(traceElement);
	}
	
	/** 
	 * Method to create a Trace Element and adds the requirement to the sources
	 * @param requirementSelection the selected requirement
	 * @return the created Trace Element
	 */
	protected TraceElement createTraceElement(ISelection requirementSelection) {
		IStructuredSelection selection = (IStructuredSelection) requirementSelection;
		SpecHierarchy sks = (SpecHierarchy) selection.getFirstElement();
		TraceElement traceElement = TMFactory.eINSTANCE.createTraceElement();
		traceElement.setName(reqIFValueParser.getReqIdentifier(sks));
		traceElement.setDescription(reqIFValueParser.getReqDescription(sks));
		traceElement.setUuid(new VirSatUuid());
		traceElement.getSourceTraceElement().add(sks);
		return traceElement;
	}

}
