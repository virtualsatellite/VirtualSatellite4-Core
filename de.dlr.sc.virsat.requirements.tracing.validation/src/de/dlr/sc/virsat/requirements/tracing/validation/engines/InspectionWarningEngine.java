/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.tracing.validation.engines;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;

import de.dlr.sc.virsat.model.dvlm.general.IName;
import de.dlr.sc.virsat.requirements.tracing.traceModel.TraceElement;
import de.dlr.sc.virsat.requirements.tracing.util.TraceHelper;
import de.dlr.sc.virsat.requirements.tracing.validation.Activator;
import de.dlr.sc.virsat.requirements.tracing.validation.IHistoryBased;
import de.dlr.sc.virsat.requirements.tracing.validation.IValidationEngine;
import de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader;
import de.dlr.sc.virsat.requirements.ui.reqif.reader.impl.DefaultReqIFValueParser;

/**
 * 
 * Engine that notifies about changes in the requirements
 * 
 * @author Tobias Franz 
 * tobias.franz@dlr.de
 *
 */
public class InspectionWarningEngine implements IValidationEngine, IHistoryBased {

	private static final String VALIDATION_ENGINE_NAME = "Inspection";
	private List<SpecObject> changedObjects;
	IReqIFReader reqIFReader = null;

	/**
	 * Constructor
	 */
	public InspectionWarningEngine() {
		reqIFReader = new DefaultReqIFValueParser(0, 2, 1);
	}

	@Override
	public boolean validate(TraceElement traceElement) {
		if (changedObjects == null) {
			return false;
		}
		if (changedObjects.size() <= 0) {
			return false;
		}
		if (traceElement.getSourceTraceElement().get(0) != null) {
			SpecHierarchy specH = (SpecHierarchy) traceElement.getSourceTraceElement().get(0);
			for (SpecObject s : changedObjects) {
				if (specH.getObject() != null && s.getIdentifier().equals(specH.getObject().getIdentifier())) {
					try {

						for (int i = 0; i < traceElement.getTargetTraceElement().size(); i++) {

							EObject target = traceElement.getTargetTraceElement().get(i);
							IResource resource = TraceHelper.getIResourceValue(target);

							IMarker marker = resource.createMarker(IMarker.PROBLEM);
							marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);

							marker.setAttribute(IMarker.MESSAGE,
									"Warning on " + reqIFReader.getValueWithColumnName(specH.getObject(), "ID")
											+ ", requirement has recently changed, " + ((IName) target).getName()
											+ " should be reviwed");

						}
						return true;
					} catch (CoreException | IllegalMonitorStateException e) {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(),
								"InspectionWarningEngine: Could not set validation marker!", e));
					}
				}
			}
		}

		return false;
	}

	@Override
	public boolean canValidate(TraceElement traceElement) {
		return true;
	}

	@Override
	public String getValidationEngineName() {
		return VALIDATION_ENGINE_NAME;
	}

	@Override
	public void setChangedSpecObjects(List<SpecObject> changedObjects) {
		this.changedObjects = changedObjects;

	}

	@Override
	public boolean canProvideSemantic() {
		return false;
	}

	@Override
	public String generateBoilerPlate(TraceElement traceElement) {
		return null;
	}

	@Override
	public String getSemantic() {
		return null;
	}
}
