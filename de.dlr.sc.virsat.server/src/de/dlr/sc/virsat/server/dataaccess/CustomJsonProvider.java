/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.dataaccess;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.helpers.DefaultValidationEventHandler;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.glassfish.jersey.moxy.json.internal.ConfigurableMoxyJsonProvider;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.json.ReferenceAdapter;
import de.dlr.sc.virsat.model.dvlm.json.TypeInstanceAdapter;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryAllProperty;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryBeanA;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryComposition;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryCompositionArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryIntrinsicArray;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReference;
import de.dlr.sc.virsat.model.extension.tests.model.TestCategoryReferenceArray;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends ConfigurableMoxyJsonProvider {
	
	private ResourceSet resourceSet;
	private ValidationEventHandler eventHandler;
	// TODO: register test classes only in test
	private static final Collection<? extends Class<?>> REGISTER_CATEGORY_CLASSES = Arrays.asList(
			TestCategoryAllProperty.class,
			TestCategoryAllProperty.class,
			TestCategoryBeanA.class,
			TestCategoryComposition.class,
			TestCategoryReference.class,
			TestCategoryIntrinsicArray.class,
			TestCategoryCompositionArray.class,
			TestCategoryReferenceArray.class
	);

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		
		setFormattedOutput(true);
		eventHandler = new DefaultValidationEventHandler();
	}
	
	@Override
	protected void preWriteTo(Object object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, Marshaller marshaller) throws JAXBException {
		marshaller.setEventHandler(eventHandler);
	}

	@Override
	protected void preReadFrom(Class<Object> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			Unmarshaller unmarshaller) throws JAXBException {
		
		unmarshaller.setEventHandler(eventHandler);
		unmarshaller.setAdapter(new TypeInstanceAdapter(resourceSet));
		unmarshaller.setAdapter(new ReferenceAdapter(resourceSet));
	}
	
	@Override
	protected JAXBContext getJAXBContext(Set<Class<?>> domainClasses, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, ?> httpHeaders) throws JAXBException {
		
		Iterator<Class<?>> iterator = domainClasses.iterator();
		if (iterator.hasNext()) {
			if (IBeanCategoryAssignment.class.isAssignableFrom(iterator.next())) {
				domainClasses.addAll(REGISTER_CATEGORY_CLASSES);
			}
		}
		return super.getJAXBContext(domainClasses, annotations, mediaType, httpHeaders);
	}
}