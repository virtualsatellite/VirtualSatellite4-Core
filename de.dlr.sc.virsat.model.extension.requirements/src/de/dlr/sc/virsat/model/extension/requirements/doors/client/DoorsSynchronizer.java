/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.requirements.doors.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc.domains.rm.RequirementCollection;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.extension.requirements.model.AttributeValue;
import de.dlr.sc.virsat.model.extension.requirements.model.Requirement;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementGroup;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementObject;
import de.dlr.sc.virsat.model.extension.requirements.model.RequirementsSpecification;
import de.dlr.sc.virsat.model.extension.requirements.model.SpecificationMapping;

public class DoorsSynchronizer {
	public Command updateRequirements(EditingDomain editingDomain, IBeanList<SpecificationMapping> specs) {
		CompoundCommand cc = new CompoundCommand();

		for (SpecificationMapping spec : specs) {
			RequirementsSpecification specification = spec.getSpecification();
			String projectName = spec.getExternalIdentifier();
			IBeanList<RequirementObject> reqsToSynchronize = specification.getRequirements();
			try {
				ArrayList<RequirementCollection> reqCollections = DoorsSynchroClient
						.queryRequirementsSpecifications(projectName);
				for (RequirementCollection reqCollection : reqCollections) {
					if (reqCollection.getTitle().equals(projectName)) {
						ArrayList<org.eclipse.lyo.oslc.domains.rm.Requirement> reqsFromDoors = DoorsSynchroClient
								.getReqsFromCollection(reqCollection);
						prepareSynchronization(reqsToSynchronize, reqsFromDoors);

					}
				}
			} catch (ResourceNotFoundException | IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cc;
	}

	private void prepareSynchronization(IBeanList<RequirementObject> r,
			ArrayList<org.eclipse.lyo.oslc.domains.rm.Requirement> reqsFromDoors) {
		for (org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors : reqsFromDoors) {
			String reqIdDoors = reqFromDoors.getIdentifier();
			for (RequirementObject rObject : r) {
				if (rObject instanceof RequirementGroup)
					prepareSynchronization(((RequirementGroup) rObject).getChildren(), reqsFromDoors);
				else if (rObject instanceof Requirement) {
					Requirement req = (Requirement) rObject;

					for (AttributeValue att : req.getElements()) {
						if (att.getAttType().getName().contentEquals("ID")) {
							String reqId = att.getValue();
							if (reqId.contentEquals(reqIdDoors)) {
								if (isModified(req, reqFromDoors)) {
									synchronizeReqFromDoors(req, reqFromDoors);
								}
							}
						}
					}
				}
			}
		}
	}

	private void synchronizeReqFromDoors(RequirementObject requirement,
			org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors) {

	}

	private boolean isModified(Requirement req, org.eclipse.lyo.oslc.domains.rm.Requirement reqFromDoors) {
		for (AttributeValue att : req.getElements()) {
			if (att.getName().contains("ModifiedOn")) {
				String lastModifiedReq = att.getValue();
				String lastModifiedReqFromDoors = reqFromDoors.getModified().toInstant().toString();
				String lastModifiedReqFromDoorsWithoutZ = lastModifiedReqFromDoors.substring(0,
						lastModifiedReqFromDoors.length() - 1);

				if (!lastModifiedReq.contentEquals(lastModifiedReqFromDoorsWithoutZ)) {
					return true;
				}
			}
		}
		return false;
	}

}
