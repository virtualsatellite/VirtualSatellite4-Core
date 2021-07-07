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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.core.Response;

import org.eclipse.lyo.client.OSLCConstants;
import org.eclipse.lyo.client.OslcClient;
import org.eclipse.lyo.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.client.query.OslcQuery;
import org.eclipse.lyo.client.query.OslcQueryParameters;
import org.eclipse.lyo.client.query.OslcQueryResult;
import org.eclipse.lyo.oslc.domains.rm.Requirement;
import org.eclipse.lyo.oslc.domains.rm.RequirementCollection;
import org.eclipse.lyo.oslc4j.core.model.CreationFactory;
import org.eclipse.lyo.oslc4j.core.model.Link;
import org.eclipse.lyo.oslc4j.core.model.OslcMediaType;
import org.eclipse.lyo.oslc4j.core.model.ResourceShape;
import org.eclipse.lyo.oslc4j.core.model.Service;
import org.eclipse.lyo.oslc4j.core.model.ServiceProvider;

public class DoorsSynchroClient {

	private DoorsSynchroClient() {

	}

//	private static final String SERVER_NAME = "https://gk-sl0002.intra.dlr.de:9443/rm/";
	private static final String CATALOG_URL = "https://gk-sl0002.intra.dlr.de:9443/rm/oslc_rm/catalog";
//	private static final String PROJECT_NAME = "MBSE4GK Testprojekt";
	private static final int SUCCESCFULL_HTTP_STATUS = 200;

	private static ArrayList<RequirementCollection> reqSpecificationsList = new ArrayList<RequirementCollection>();
	private static HashMap<RequirementCollection, ArrayList<Requirement>> mapOfRequirements = new HashMap<RequirementCollection, ArrayList<Requirement>>();

	public static String lookUpServiceProviderUrl(OslcClient client, String projectName)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = client.lookupServiceProviderUrl(CATALOG_URL, projectName);
		return serviceProviderUrl;
	}

	public static String lookUpQueryCapability(OslcClient client, String serviceProvider)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		String queryCapabilty = client.lookupQueryCapability(serviceProvider, OSLCConstants.OSLC_RM_V2,
				OSLCConstants.RM_REQUIREMENT_TYPE);
		return queryCapabilty;
	}

	/**
	 * 
	 * @param client
	 * @param serviceProviderUrl
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 */
	public static ServiceProvider getServiceProvider(OslcClient client, String serverName)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = lookUpServiceProviderUrl(client, serverName);
		Response responseServer = client.getResource(serviceProviderUrl, OslcMediaType.APPLICATION_RDF_XML);
		if (responseServer.getStatus() == SUCCESCFULL_HTTP_STATUS) {
			ServiceProvider serviceProvider = responseServer.readEntity(ServiceProvider.class);
			return serviceProvider;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param serviceProvider
	 * @param client
	 * @return
	 */
	public static ArrayList<ResourceShape> getResourceShapes(ServiceProvider serviceProvider, OslcClient client) {
		for (Service service : serviceProvider.getServices()) {
			URI domain = service.getDomain();
			String oslcDomain = OSLCConstants.OSLC_RM_V2;
			if (domain != null && domain.toString().equals(oslcDomain)) {
				CreationFactory[] creationFactories = service.getCreationFactories();
				if (creationFactories != null && creationFactories.length > 0) {
					for (CreationFactory creationFactory : creationFactories) {
						for (URI resourceType : creationFactory.getResourceTypes()) {
							if (resourceType.toString() != null
									&& resourceType.toString().equals(OSLCConstants.RM_REQUIREMENT_TYPE)) {
								URI[] instanceShapes = creationFactory.getResourceShapes();
								if (instanceShapes != null) {
									ArrayList<ResourceShape> resourceShapes = new ArrayList<ResourceShape>();
									for (URI typeURI : instanceShapes) {
										Response response = client.getResource(typeURI.toString(),
												OSLCConstants.CT_RDF);
										ResourceShape resourceShape = response.readEntity(ResourceShape.class);
										resourceShapes.add(resourceShape);
									}
									return resourceShapes;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param client
	 * @param queryCapability
	 * @return
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ResourceNotFoundException
	 */
	public static ArrayList<RequirementCollection> queryRequirementsSpecifications(OslcClient client,
			String projectName) throws IOException, ResourceNotFoundException, URISyntaxException {
		String serviceProvider = lookUpServiceProviderUrl(client, projectName);
		String queryCapability = lookUpQueryCapability(client, serviceProvider);

		// query requirementCollections which are the specifications in virsat
		OslcQueryParameters queryParams = new OslcQueryParameters();
		queryParams.setSelect("*");
		queryParams.setPrefix(
				"rdf=<http://www.w3.org/1999/02/22-rdf-syntax-ns%23>,dcterms=<http://purl.org/dc/terms/>,oslc_rm=<http://open-services.net/ns/rm%23");
		queryParams.setWhere("rdf:type=<http://open-services.net/ns/rm%23RequirementCollection>");
		OslcQuery queryReqCollections = new OslcQuery(client, queryCapability, queryParams);
		OslcQueryResult result = queryReqCollections.submit();

		ArrayList<RequirementCollection> reqSpecifications = reqSpecificationsList;
		Iterator<RequirementCollection> reqCollectionsIterator = result.getMembers(RequirementCollection.class)
				.iterator();
		while (reqCollectionsIterator.hasNext()) {
			RequirementCollection reqCollection = reqCollectionsIterator.next();
			reqSpecifications.add(reqCollection);
		}
		return reqSpecifications;
	}

	/**
	 * 
	 * @param client
	 * @param projectName
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void queryRequirements(OslcClient client, String projectName)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		for (RequirementCollection reqCollection : reqSpecificationsList) {

			// do another get on the collection to have access to getUses(), which are the
			// underlying requirements
			Response requirementGet = client.getResource(reqCollection.getAbout().toURL().toString(),
					OslcMediaType.APPLICATION_RDF_XML);
			RequirementCollection requirementCollection = requirementGet.readEntity(RequirementCollection.class);
			ArrayList<Requirement> listOfRequirements = new ArrayList<Requirement>();
			try {
				if (requirementCollection != null) {
					if (requirementCollection.getUses() != null) {
						for (Link uses : requirementCollection.getUses()) {
							Response reqResource = client.getResource(uses.getValue().toString(),
									OslcMediaType.APPLICATION_RDF_XML);
							Requirement req = reqResource.readEntity(Requirement.class);
							listOfRequirements.add(req);
						}
						mapOfRequirements.put(requirementCollection, listOfRequirements);
					}
				}
			} catch (NullPointerException np) {
				np.getStackTrace();
			}
		}
	}
}