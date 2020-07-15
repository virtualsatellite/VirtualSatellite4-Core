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

public class JAXBUtility {

	private Map<String, Object> properties = new HashMap<>();
	private JAXBContext jaxbCtx;
	private BeanPropertyStringListAdapter listAdapter;
	
	
	public JAXBUtility() {
		init();
	}
	
	public JAXBUtility(@SuppressWarnings("rawtypes") Class[] registerClasses) throws JAXBException {
		init();
		initJsonProperties();
		createContext(registerClasses);
	}
	
	private void init() {
		System.setProperty("javax.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
		listAdapter = new BeanPropertyStringListAdapter();
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
	
	public Marshaller getJsonMarshaller() throws JAXBException {
		Marshaller jsonMarshaller = jaxbCtx.createMarshaller();
		jsonMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		jsonMarshaller.setEventHandler(new DefaultValidationEventHandler());
		
		jsonMarshaller.setAdapter(listAdapter);
		
		return jsonMarshaller;
	}
	
	public Unmarshaller getJsonUnmarshaller(ResourceSet resourceSet) throws JAXBException {
		Unmarshaller jsonUnmarshaller = jaxbCtx.createUnmarshaller();
		
		jsonUnmarshaller.setEventHandler(new DefaultValidationEventHandler());
		
		TypeInstanceAdapter typeInstanceAdapter = new TypeInstanceAdapter(resourceSet);
		jsonUnmarshaller.setAdapter(typeInstanceAdapter);
		
		listAdapter.setResourceSet(resourceSet);
		jsonUnmarshaller.setAdapter(listAdapter);
		
		return jsonUnmarshaller;
	}
}
