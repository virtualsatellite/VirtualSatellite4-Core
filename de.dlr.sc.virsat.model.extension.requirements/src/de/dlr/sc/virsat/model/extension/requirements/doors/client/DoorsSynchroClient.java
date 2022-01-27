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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
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

import de.dlr.sc.virsat.model.extension.requirements.Activator;

public class DoorsSynchroClient {

	private static final String CATALOG_URL = "https://gk-sl0002.intra.dlr.de:9443/rm/oslc_rm/catalog";
	private static final int SUCCESSFULL_HTTP_STATUS = 200;

	private DoorsUtils doorsUtils;

	private static ArrayList<RequirementCollection> reqSpecificationsList = new ArrayList<RequirementCollection>();
	private static HashMap<RequirementCollection, ArrayList<Requirement>> mapOfRequirements = new HashMap<RequirementCollection, ArrayList<Requirement>>();
	private static OslcClient client = new OslcClient();
	private static String serviceProviderUrl;
	public static DoorsSynchroClient doorsSynchroClient = new DoorsSynchroClient();

	/**
	 * Init the doors synchro client by getting an Oslc Client and look up provided
	 * services
	 * 
	 * @param server   server url
	 * @param user     user name
	 * @param password password
	 * @param project  project name
	 * @throws UnrecoverableKeyException
	 * @throws MalformedURLException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 * @throws URISyntaxException
	 */
	public void init(String server, String user, String password, String project) throws UnrecoverableKeyException,
			MalformedURLException, CertificateException, IOException, ResourceNotFoundException, URISyntaxException {
		doorsUtils = new DoorsUtils(server, user, password);
		client = doorsUtils.getClient();
		serviceProviderUrl = lookUpServiceProviderUrl(project);
		doorsSynchroClient = new DoorsSynchroClient();
	}

	public static void setOslcClient(OslcClient oslcClient) {
		client = oslcClient;
		
	}
	
	public static void setDoorsSynchroClient (DoorsSynchroClient synchroClient) {
		doorsSynchroClient = synchroClient;
	}
	
	/**
	 * Look up service provider url to access doors projects in further requests
	 * 
	 * @param project project name
	 * @return serviceProviderUrl
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String lookUpServiceProviderUrl(String project)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = client.lookupServiceProviderUrl(CATALOG_URL, project);
		return serviceProviderUrl;
	}

	/**
	 * Look up query capability url for further requests
	 * 
	 * @return queryCapability
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String lookUpQueryCapability() throws ResourceNotFoundException, IOException, URISyntaxException {
		String queryCapabilty = client.lookupQueryCapability(serviceProviderUrl, OSLCConstants.OSLC_RM_V2,
				OSLCConstants.RM_REQUIREMENT_TYPE);
		return queryCapabilty;
	}

	/**
	 * Query requirement by id
	 * 
	 * @param id id of a requirement
	 * @return requirement
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static Requirement getRequirementById(String id)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		String queryCapability = lookUpQueryCapability();
		OslcQueryParameters queryParams = new OslcQueryParameters();
		queryParams.setSelect("*");
		queryParams.setPrefix(
				"rdf=<http://www.w3.org/1999/02/22-rdf-syntax-ns%23>,dcterms=<http://purl.org/dc/terms/>,oslc_rm=<http://open-services.net/ns/rm%23");
		queryParams.setWhere("dcterms:identifier=" + id);
		OslcQuery queryReqCollections = new OslcQuery(client, queryCapability, queryParams);
		OslcQueryResult result = queryReqCollections.submit();
		Requirement req = result.getMembers(Requirement.class).iterator().next();
		return req;
	}

	/**
	 * Query all services (projects)
	 * 
	 * @return serviceProvider or null if not existing
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static ServiceProvider getServiceProvider()
			throws ResourceNotFoundException, IOException, URISyntaxException {
		Response responseServer = client.getResource(serviceProviderUrl, OslcMediaType.APPLICATION_RDF_XML);
		if (responseServer.getStatus() == SUCCESSFULL_HTTP_STATUS) {
			ServiceProvider serviceProvider = responseServer.readEntity(ServiceProvider.class);
			return serviceProvider;
		} else {
			return null;
		}
	}

	/**
	 * Query to get all possible resource shapes
	 * 
	 * @return resourceShapes or null if not existing
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static ArrayList<ResourceShape> getResourceShapes()
			throws ResourceNotFoundException, IOException, URISyntaxException {
		ServiceProvider serviceProvider = getServiceProvider();
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
	 * Get resource shape from given requirement
	 * 
	 * @param req given requirement
	 * @return resourceShape or null
	 */
	public static ResourceShape getResourceShape(Requirement req) {
		URI reqLink = req.getInstanceShape().iterator().next().getValue();
		Response response = client.getResource(reqLink.toString(), OSLCConstants.CT_RDF);
		ResourceShape resourceShape = response.readEntity(ResourceShape.class);
		if (resourceShape != null) {
			return resourceShape;
		}
		return null;
	}

	/**
	 * Query all requirement collections (specifications) of a given project
	 * 
	 * @param projectName Doors project
	 * @return reqSpecifications
	 * @throws IOException
	 * @throws ResourceNotFoundException
	 * @throws URISyntaxException
	 */
	public static ArrayList<RequirementCollection> queryRequirementsSpecifications(String projectName)
			throws IOException, ResourceNotFoundException, URISyntaxException {
		String queryCapability = lookUpQueryCapability();
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
	 * Query all requirements of a given project
	 * 
	 * @param projectName
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void queryRequirements(String projectName)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		for (RequirementCollection reqCollection : reqSpecificationsList) {
			Response response = client.getResource(reqCollection.getAbout().toURL().toString(),
					OslcMediaType.APPLICATION_RDF_XML);
			RequirementCollection requirementCollection = response.readEntity(RequirementCollection.class);
			ArrayList<Requirement> listOfRequirements = new ArrayList<Requirement>();
			try {
				if (requirementCollection != null) {
					if (requirementCollection.getUses() != null) {
						// every link (getUses()) is a requirement contained by the
						// requirementCollection
						for (Link uses : requirementCollection.getUses()) {
							Response reqRequest = client.getResource(uses.getValue().toString(),
									OslcMediaType.APPLICATION_RDF_XML);
							Requirement requirement = reqRequest.readEntity(Requirement.class);
							listOfRequirements.add(requirement);
						}
						mapOfRequirements.put(requirementCollection, listOfRequirements);
					}
				}
			} catch (NullPointerException np) {
				Activator.getDefault().getLog().error(np.getMessage());
			}
		}
	}

	/**
	 * Query all requirements of a given Requirement Collection
	 * 
	 * @param reqCollection the given Requirement Collection
	 * @return list of requirements of given RequirementCollection
	 * @throws ResourceNotFoundException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static ArrayList<Requirement> queryRequirementsOfCollection(RequirementCollection reqCollection)
			throws ResourceNotFoundException, IOException, URISyntaxException {
		Response response = client.getResource(reqCollection.getAbout().toURL().toString(),
				OslcMediaType.APPLICATION_RDF_XML);
		RequirementCollection requirementCollection = response.readEntity(RequirementCollection.class);
		ArrayList<Requirement> listOfRequirements = new ArrayList<Requirement>();
		try {
			if (requirementCollection != null) {
				if (requirementCollection.getUses() != null) {
					// every link (getUses()) is a requirement contained by the
					// requirementCollection
					for (Link uses : requirementCollection.getUses()) {
						Response reqRequest = client.getResource(uses.getValue().toString(),
								OslcMediaType.APPLICATION_RDF_XML);
						Requirement requirement = reqRequest.readEntity(Requirement.class);
						listOfRequirements.add(requirement);
					}
				}
			}
		} catch (NullPointerException np) {
			Activator.getDefault().getLog().error(np.getMessage());
		}
		return listOfRequirements;
	}
}