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

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
import org.eclipse.emf.transaction.RecordingCommand;
import org.glassfish.jersey.moxy.json.internal.ConfigurableMoxyJsonProvider;

import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.ReferenceAdapter;
import de.dlr.sc.virsat.model.dvlm.json.TypeInstanceAdapter;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomJsonProvider extends ConfigurableMoxyJsonProvider {
	
	private ValidationEventHandler eventHandler;

	private VirSatTransactionalEditingDomain ed;
	private VirSatResourceSet resourceSet;
	
	private Set<Class<?>> dynamicClasses;

	public CustomJsonProvider() {
		setFormattedOutput(true);
		eventHandler = new DefaultValidationEventHandler();
	}
	
	public void setEd(VirSatTransactionalEditingDomain ed) {
		this.ed = ed;
		this.resourceSet = ed.getResourceSet();
		
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
		
		unmarshaller.setEventHandler(eventHandler);
		unmarshaller.setAdapter(new TypeInstanceAdapter(resourceSet));
		unmarshaller.setAdapter(new ReferenceAdapter(resourceSet));
	}
	
	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		// TODO: inner class
		List<Object> results = new ArrayList<>();
		RecordingCommand recordingCommand = new RecordingCommand(ed) {
			@Override
			protected void doExecute() {
				try {
					Object result = CustomJsonProvider.super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
					results.add(result);
				} catch (WebApplicationException e) {
					// TODO Auto-generated catch block
					// TODO: atomicexception
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		ed.getCommandStack().execute(recordingCommand);
		return results.get(0);
	}
	
	// TODO: test this class, test cashing here
	// it should cash over the domain classes so there should be no problem with the cashing
	@Override
	protected JAXBContext getJAXBContext(Set<Class<?>> domainClasses, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, ?> httpHeaders) throws JAXBException {
		
		domainClasses.addAll(dynamicClasses);
		return super.getJAXBContext(domainClasses, annotations, mediaType, httpHeaders);
	}
}