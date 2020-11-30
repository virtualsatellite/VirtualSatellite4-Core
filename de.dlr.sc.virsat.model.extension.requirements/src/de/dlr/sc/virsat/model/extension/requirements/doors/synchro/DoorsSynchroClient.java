/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.requirements.doors.synchro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import org.apache.http.HttpStatus;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.lyo.oslc4j.client.OSLCConstants;
import org.eclipse.lyo.oslc4j.client.OslcClient;
import org.eclipse.lyo.oslc4j.client.exception.ResourceNotFoundException;
import org.eclipse.lyo.oslc4j.client.resources.OslcQuery;
import org.eclipse.lyo.oslc4j.client.resources.OslcQueryParameters;
import org.eclipse.lyo.oslc4j.client.resources.OslcQueryResult;
import org.eclipse.lyo.oslc4j.client.resources.Requirement;
import org.eclipse.lyo.oslc4j.client.resources.RmConstants;
import org.eclipse.lyo.oslc4j.core.annotation.OslcPropertyDefinition;

public class DoorsSynchroClient {

	private static final Logger LOGGER = Logger.getLogger(DoorsSynchroClient.class.getName());

	private static final QName PROPERTY_PRIMARY_TEXT_WORKAROUNG = new QName(RmConstants.JAZZ_RM_NAMESPACE,
			"PrimaryText");
	private static final String CATALOG_URL_OPTION = "catalogUrl";
	private static final String PROVIDER_TITLE_OPTION = "providerTitle";
	private static final String REQUIREMENT_TITLE = "Demo requirement";
	
	//STEP 1: Create a new generic OslcClient
	private static final OslcClient CLIENT = new OslcClient();

	/**
	 * Access the RM service provider and perform some OSLC actions
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		
		Options options = new Options();

		options.addOption(CATALOG_URL_OPTION, true, "url");
		options.addOption(PROVIDER_TITLE_OPTION, true, "Service Provider Title");

		CommandLineParser cliParser = new GnuParser();

		//Parse the command line
		CommandLine cmd = cliParser.parse(options, args);
		
		if (!validateOptions(cmd)) {
			LOGGER.severe("Syntax:  java <class_name> -url https://<server>:port/<context>/<catalog_location> -providerTitle \"<provider title>\"");
			LOGGER.severe("Example: java GenericCMSample -url https://localhost:8080/OSLC4JRegistry/catalog/singleton -providerTitle \"OSLC Lyo Requirements Management Service Provider\"");
			return;
		}
		
		String catalogUrl = cmd.getOptionValue(CATALOG_URL_OPTION);
		String providerTitle = cmd.getOptionValue(PROVIDER_TITLE_OPTION);
		
		try {
			OslcQueryResult result = getAllRequirementsQuery(); // run a query for all requirements

			boolean processAsJavaObjects = true;
			processPageQueryResults(result, CLIENT, processAsJavaObjects);
			
			System.out.print("\n---------------------------\n");
			
			//SCENARIO B: Run a query for a specific requirement and then print it as raw XML.
			//Change the URL below to match a real requirement
			
			Response rawResponse = CLIENT.getResource("http://localhost:8086/adaptor-rm/services/requirements/3513", 
					OSLCConstants.CT_XML);
			processRawResponse(rawResponse);
			rawResponse.readEntity(String.class);
			
			//SCENARIO C: Requirement creation and update
			Requirement newRequirement = new Requirement();
			newRequirement.setTitle(REQUIREMENT_TITLE);
			//newRequirement.setTitle("");
			
			String creationFac = getCreationFactory();
			rawResponse = CLIENT.createResource(creationFac, newRequirement, OSLCConstants.CT_RDF);
			int statusCode = rawResponse.getStatus();
			rawResponse.readEntity(String.class);
			System.out.println("Status code for POST of new artifact: " + statusCode);
			
			if (statusCode == HttpStatus.SC_CREATED) {
				String location = rawResponse.getStringHeaders().getFirst("Location");
				newRequirement.setTitle("The schema needs to support new attributes");
				rawResponse = CLIENT.updateResource(location, newRequirement, OSLCConstants.CT_RDF);
				rawResponse.readEntity(String.class);
				statusCode = rawResponse.getStatus();
				System.out.println("Status code for PUT of updated artifact: " + statusCode);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	//STEP 2: Find the OSLC Service Provider for the service provider we want to work with
	public static String getServiceProviderUrl() throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = CLIENT.lookupServiceProviderUrl(CATALOG_URL_OPTION, PROVIDER_TITLE_OPTION);
		return serviceProviderUrl;
	}

	//STEP 3: Get the Query Capabilities and Creation Factory URLs so that we can run some OSLC queries
	public static String getQueryCapability() throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = getServiceProviderUrl();
		String queryCapability = CLIENT.lookupQueryCapability(serviceProviderUrl, OSLCConstants.OSLC_RM_V2, OSLCConstants.RM_REQUIREMENT_TYPE);
		return queryCapability;
	}
	
	public static String getCreationFactory() throws ResourceNotFoundException, IOException, URISyntaxException {
		String serviceProviderUrl = getServiceProviderUrl();
		String creationFactory = CLIENT.lookupCreationFactory(serviceProviderUrl, OSLCConstants.OSLC_RM_V2, OSLCConstants.RM_REQUIREMENT_TYPE);
		return creationFactory;
	}
	//SCENARIO A: Run a query for all Requirements
	private static OslcQueryResult getAllRequirementsQuery() throws ResourceNotFoundException, IOException, URISyntaxException {
		String queryCapability = getQueryCapability();
//		OslcQueryParameters queryParams = new OslcQueryParameters(where, select, searchTerms, orderBy, prefix);
		OslcQuery queryAllReq = new OslcQuery(CLIENT, queryCapability);
		OslcQueryResult result = queryAllReq.submit();
		return result;
	}
	
	private static void processPageQueryResults(OslcQueryResult result, OslcClient client, boolean asJavaObjects) {
		int page = 1;
		do {
			System.out.println("\nPage " + page + ":\n");
			processCurrentPage(result, client, asJavaObjects);
			if (result.hasNext()) {
				result = result.next();
				page++;
			} else {
				break;
			}
		} while (true);
	}
	
	private static void processCurrentPage(OslcQueryResult result, OslcClient client, boolean asJavaObjects) {
		for (String resultsUrl : result.getMembersUrls()) {
			System.out.println(resultsUrl);
			
			Response response = null;
			
			try {
				
				//Get a single artifact by its URL
				response = client.getResource(resultsUrl, OSLCConstants.CT_RDF);
				
				if (response != null) {
					
					//De-serialize it as a Java object
					if (asJavaObjects) {
						Requirement cr = response.readEntity(Requirement.class);
						printRequirementsInfo(cr); // print a few attributes
					} else {
						
						//Just print the raw RDF/XML (or process the XML as desired)
						processRawResponse(response);
					}
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Unable to process artifact at url; " + resultsUrl, e);
			}
		}
	}
	
	private static void processRawResponse(Response response) throws IOException {
		InputStream is = response.readEntity(InputStream.class);
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println();
	}
	
	private static void printRequirementsInfo(Requirement cr) {
		if (cr != null) {
			System.out.println("ID: " + cr.getIdentifier() + ", Title: " + cr.getTitle() + ", Description: " + cr.getDescription());
		}
	}
	
	private static boolean validateOptions(CommandLine cmd) {
		boolean isValid = true;

		if (! (cmd.hasOption("url") &&
			  (cmd.hasOption("providerTitle")))) {

			isValid = false;
		}
		return isValid;
	}
}