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
import java.util.HashSet;
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

import org.eclipse.core.runtime.CoreException;
import org.glassfish.jersey.moxy.json.internal.ConfigurableMoxyJsonProvider;

import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyBoolean;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyComposed;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyEnum;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyFloat;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyReference;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyResource;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.ReferenceAdapter;
import de.dlr.sc.virsat.model.dvlm.json.TypeInstanceAdapter;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends ConfigurableMoxyJsonProvider {
	
	private ValidationEventHandler eventHandler;
	
	private VirSatResourceSet resourceSet;
	// TODO: should cash this for each resource set?
	// Can these classes change at runtime? E.g. when we add a new concept 
	// Or should we instead register all possible classes
	private Set<Class<?>> dynamicClasses;
	
//	private static final Collection<? extends Class<?>> REGISTER_CATEGORY_CLASSES = Arrays.asList(
//			TestCategoryAllProperty.class,
//			TestCategoryAllProperty.class,
//			TestCategoryBeanA.class,
//			TestCategoryComposition.class,
//			TestCategoryReference.class,
//			TestCategoryIntrinsicArray.class,
//			TestCategoryCompositionArray.class,
//			TestCategoryReferenceArray.class
//	);
	private static final Collection<? extends Class<?>> REGISTER_PROPERTY_CLASSES = Arrays.asList(
			BeanPropertyBoolean.class,
			BeanPropertyString.class,
			BeanPropertyInt.class,
			BeanPropertyFloat.class,
			BeanPropertyEnum.class,
			BeanPropertyResource.class,
			BeanPropertyComposed.class,
			BeanPropertyReference.class
	);

	public CustomJsonProvider() {
		// TODO: do we want to set this permanently?
		// this breaks an old test case but it should be removed nevertheless
		setFormattedOutput(true);
		eventHandler = new DefaultValidationEventHandler();
	}
	
	public void setResourceSet(VirSatResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		
		// TODO: when and how do we have to update this set?
		// Do we add concepts via the server? Can concepts change at runtime?
		dynamicClasses = getClassesToRegister();
	}

	private Set<Class<?>> getClassesToRegister() {
		
		Set<Class<?>> allCaClasses = new HashSet<>();
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		
		for (Concept concept : resourceSet.getRepository().getActiveConcepts()) {
			for (Category category : concept.getNonAbstractCategories()) {
				try {
					IBeanCategoryAssignment bean = beanCaFactory.getInstanceFor(category);
					allCaClasses.add(bean.getClass());
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return allCaClasses;
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
		
//		unmarshaller.setEventHandler(eventHandler);
		unmarshaller.setAdapter(new TypeInstanceAdapter(resourceSet));
		unmarshaller.setAdapter(new ReferenceAdapter(resourceSet));
	}
	
	// TODO: test this class, test cashing here
	@Override
	protected JAXBContext getJAXBContext(Set<Class<?>> domainClasses, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, ?> httpHeaders) throws JAXBException {
		
		Iterator<Class<?>> iterator = domainClasses.iterator();
		if (iterator.hasNext()) {
			Class<?> domainClass = iterator.next();
			if (IBeanCategoryAssignment.class.isAssignableFrom(domainClass)
					|| BeanPropertyComposed.class.isAssignableFrom(domainClass)) {
				domainClasses.addAll(dynamicClasses);
			} else if (IBeanObject.class.isAssignableFrom(domainClass)) {
				domainClasses.addAll(REGISTER_PROPERTY_CLASSES);
			}
		}
		return super.getJAXBContext(domainClasses, annotations, mediaType, httpHeaders);
	}
}