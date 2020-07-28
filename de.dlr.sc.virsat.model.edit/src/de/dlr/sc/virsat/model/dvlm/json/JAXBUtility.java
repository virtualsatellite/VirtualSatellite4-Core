/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.json;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

/**
 * Provides functionality to create a JAXBContext
 * and corresponding marshaller and unmarshaller.
 * The Class saves the state of the properties and the context.
 */
public class JAXBUtility {

	private Map<String, Object> properties = new HashMap<>();
	private JAXBContext jaxbCtx;
	
	/**
	 * Default constructor
	 */
	public JAXBUtility() {
		init();
	}
	
	/**
	 * Create a instance with JSON properties and a context from the registerClasses
	 * @param registerClasses classes to be recognized by the context
	 * @throws JAXBException
	 */
	public JAXBUtility(@SuppressWarnings("rawtypes") Class[] registerClasses) throws JAXBException {
		init();
		initJsonProperties();
		createContext(registerClasses);
	}
	
	private void init() {
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
	}
	
	public void initJsonProperties() {
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
	}
	
	public JAXBContext createContext(@SuppressWarnings("rawtypes") Class[] registerClasses) throws JAXBException {
		jaxbCtx = JAXBContext.newInstance(registerClasses, properties);
		return jaxbCtx;
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	
	/**
	 * Create a new Marshaller from the context
	 * @return Marshaller
	 * @throws JAXBException
	 */
	public Marshaller getJsonMarshaller() throws JAXBException {
		Marshaller jsonMarshaller = jaxbCtx.createMarshaller();
		jsonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		jsonMarshaller.setEventHandler(new DefaultValidationEventHandler());
		
		return jsonMarshaller;
	}
	
	/**
	 * Create a new Unmarshaller from the context and the resourceSet
	 * @param resourceSet containing the elements to be recognized during unmarshalling
	 * @return Unmarshaller
	 * @throws JAXBException
	 */
	public Unmarshaller getJsonUnmarshaller(ResourceSet resourceSet) throws JAXBException {
		Unmarshaller jsonUnmarshaller = jaxbCtx.createUnmarshaller();
		
		jsonUnmarshaller.setEventHandler(new DefaultValidationEventHandler());
		
		TypeInstanceAdapter typeInstanceAdapter = new TypeInstanceAdapter(resourceSet);
		jsonUnmarshaller.setAdapter(typeInstanceAdapter);
		
		ReferenceAdapter referenceAdapter = new ReferenceAdapter(resourceSet);
		jsonUnmarshaller.setAdapter(referenceAdapter);
		
		return jsonUnmarshaller;
	}
}
