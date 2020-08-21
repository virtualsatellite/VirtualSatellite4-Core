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
import java.util.Collection;
import java.util.HashSet;
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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.glassfish.jersey.moxy.json.internal.ConfigurableMoxyJsonProvider;

import de.dlr.sc.virsat.commons.exception.AtomicExceptionReference;
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

	public CustomJsonProvider() {
		setFormattedOutput(true);
		eventHandler = new DefaultValidationEventHandler();
	}
	
	public void setEd(VirSatTransactionalEditingDomain ed) {
		this.ed = ed;
		this.resourceSet = ed.getResourceSet();
	}

	/**
	 * Get all category assignment classes that are present in the current concepts
	 * @return Set<Class<?>> the classes
	 */
	private Set<Class<?>> getClassesToRegister() {
		Set<Class<?>> allCaClasses = new HashSet<>();
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		
		for (Concept concept : resourceSet.getRepository().getActiveConcepts()) {
			for (Category category : concept.getNonAbstractCategories()) {
				try {
					IBeanCategoryAssignment bean = beanCaFactory.getInstanceFor(category);
					allCaClasses.add(bean.getClass());
				} catch (CoreException e) {
					throw new RuntimeException(e);
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
		
		AtomicExceptionReference<IOException> atomicIoException = new AtomicExceptionReference<>();
		AtomicExceptionReference<WebApplicationException> atomicWebAppException = new AtomicExceptionReference<>();
		
		RecordingCommand recordingCommand = new ReadFromCommand(ed, atomicIoException, atomicWebAppException,
				type, genericType, annotations, mediaType, httpHeaders, entityStream);
		ed.getCommandStack().execute(recordingCommand);
		
		atomicIoException.throwIfSet();
		atomicWebAppException.throwIfSet();
		
		return recordingCommand.getResult().iterator().next();
	}
	
	private class ReadFromCommand extends RecordingCommand {

		
		private Collection<Object> results = new ArrayList<>();
		
		private Class<Object> type;
		private Type genericType;
		private Annotation[] annotations;
		private MediaType mediaType;
		private MultivaluedMap<String, String> httpHeaders;
		private InputStream entityStream;

		private AtomicExceptionReference<WebApplicationException> atomicWebAppException;
		private AtomicExceptionReference<IOException> atomicIoException;
		
		/**
		 * Call ConfigurableMoxyJsonProvider.readFrom() over the
		 * transactional editing domain
		 * @param domain the ed
		 * @param atomicWebAppException 
		 * @param atomicIoException 
		 * @param atomicException 
		 * @param type Class<Object>
		 * @param genericType Type
		 * @param annotations Annotation[]
		 * @param mediaType MediaType
		 * @param httpHeaders MultivaluedMap<String, String>
		 * @param entityStream InputStream
		 */
		ReadFromCommand(TransactionalEditingDomain domain,
				AtomicExceptionReference<IOException> atomicIoException, AtomicExceptionReference<WebApplicationException> atomicWebAppException,
				Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
			super(domain);
			
			this.atomicIoException = atomicIoException;
			this.atomicWebAppException = atomicWebAppException;
			
			this.type = type;
			this.genericType = genericType;
			this.annotations = annotations;
			this.mediaType = mediaType;
			this.httpHeaders = httpHeaders;
			this.entityStream = entityStream;
		}

		@Override
		protected void doExecute() {
			try {
				Object result = CustomJsonProvider.super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
				results.add(result);
			} catch (WebApplicationException e) {
				atomicWebAppException.set(e);
			} catch (IOException e) {
				atomicIoException.set(e);
			}
		}
		
		/**
		 * Returns the result of ConfigurableMoxyJsonProvider.readFrom()
		 */
		@Override
		public Collection<?> getResult() {
			return results;
		}
	}
	
	@Override
	protected JAXBContext getJAXBContext(Set<Class<?>> domainClasses, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, ?> httpHeaders) throws JAXBException {
		
		// We assume that the registered classes in the concept can change any time
		// so no cashing is possible and we have to get the current ones on each request
		domainClasses.addAll(getClassesToRegister());
		
		// But the contexts are being cashed on domainClasses
		// So it will reuse a context for the same domainClasses
		return super.getJAXBContext(domainClasses, annotations, mediaType, httpHeaders);
	}
}