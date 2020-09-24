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
	
	public static final String SYSTEM_PROP_CONTEXT_FACTORY = "javax.xml.bind.context.factory";
	public static final String CLASSPATH_JAXB_CONTEXT_FACTORY = "org.eclipse.persistence.jaxb.JAXBContextFactory";
	
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
		createContext(registerClasses);
	}
	
	private void init() {
		System.setProperty(SYSTEM_PROP_CONTEXT_FACTORY, CLASSPATH_JAXB_CONTEXT_FACTORY);
		properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
		properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
	}
	
	public JAXBContext createContext(@SuppressWarnings("rawtypes") Class[] registerClasses) throws JAXBException {
		jaxbCtx = JAXBContext.newInstance(registerClasses, properties);
		return jaxbCtx;
	}
	
	public JAXBContext getContext() {
		return jaxbCtx;
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
	
	/**
	 * Create a new Marshaller from the context
	 * @return Marshaller or null if no context is set
	 * @throws JAXBException
	 */
	public Marshaller getJsonMarshaller() throws JAXBException {
		if (jaxbCtx == null) {
			return null;
		}
		
		Marshaller jsonMarshaller = jaxbCtx.createMarshaller();
		jsonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		jsonMarshaller.setEventHandler(new DefaultValidationEventHandler());
		
		return jsonMarshaller;
	}
	
	/**
	 * Create a new Unmarshaller from the context and the resourceSet
	 * @param resourceSet containing the elements to be recognized during unmarshalling
	 * @return Unmarshaller or null if no context is set
	 * @throws JAXBException
	 */
	public Unmarshaller getJsonUnmarshaller(ResourceSet resourceSet) throws JAXBException {
		if (jaxbCtx == null) {
			return null;
		}
		
		Unmarshaller jsonUnmarshaller = jaxbCtx.createUnmarshaller();
		
		jsonUnmarshaller.setEventHandler(new DefaultValidationEventHandler());
		
		IUuidAdapter uuidAdapter = new IUuidAdapter(resourceSet);
		jsonUnmarshaller.setAdapter(uuidAdapter);
		
		ABeanObjectAdapter aBeanObjectAdapter = new ABeanObjectAdapter(resourceSet);
		jsonUnmarshaller.setAdapter(aBeanObjectAdapter);
		
		ABeanStructuralElementInstanceAdapter aBeanSeiAdapter = new ABeanStructuralElementInstanceAdapter(resourceSet);
		jsonUnmarshaller.setAdapter(aBeanSeiAdapter);
		
		return jsonUnmarshaller;
	}
}
