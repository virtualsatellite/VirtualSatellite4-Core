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
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import de.dlr.sc.virsat.commons.exception.AtomicExceptionReference;
import de.dlr.sc.virsat.model.concept.types.category.IBeanCategoryAssignment;
import de.dlr.sc.virsat.model.concept.types.factory.BeanCategoryAssignmentFactory;
import de.dlr.sc.virsat.model.concept.types.factory.BeanStructuralElementInstanceFactory;
import de.dlr.sc.virsat.model.concept.types.structural.IBeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.categories.Category;
import de.dlr.sc.virsat.model.dvlm.concepts.Concept;
import de.dlr.sc.virsat.model.dvlm.json.ABeanObjectAdapter;
import de.dlr.sc.virsat.model.dvlm.json.ABeanStructuralElementInstanceAdapter;
import de.dlr.sc.virsat.model.dvlm.json.IUuidAdapter;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.VirSatResourceSet;
import de.dlr.sc.virsat.server.repository.ServerRepository;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionalJsonProvider extends MOXyJsonProvider {
	
	private ValidationEventHandler eventHandler;
	
	private ServerRepository repo;
	private VirSatTransactionalEditingDomain ed;
	private VirSatResourceSet resourceSet;
	
	private static final Set<Class<?>> LIST_CLASSES = new HashSet<Class<?>>(
			Arrays.asList(
				IUuidAdapter.class,
				ABeanStructuralElementInstanceAdapter.class
	));
	
	public TransactionalJsonProvider() {
		setFormattedOutput(true);
		eventHandler = new DefaultValidationEventHandler();
	}
	
	public void setServerRepository(ServerRepository repo) {
		this.repo = repo;
		this.ed = repo.getEd();
		this.resourceSet = repo.getResourceSet();
	}

	/**
	 * Get all category assignment classes that are present in the current concepts
	 * @return Set<Class<?>> the classes
	 */
	private Set<Class<?>> getClassesToRegister() {
		Set<Class<?>> allCaClasses = new HashSet<>();
		BeanCategoryAssignmentFactory beanCaFactory = new BeanCategoryAssignmentFactory();
		BeanStructuralElementInstanceFactory beanSeiFactory = new BeanStructuralElementInstanceFactory();
		
		for (Concept concept : resourceSet.getRepository().getActiveConcepts()) {
			for (Category category : concept.getNonAbstractCategories()) {
				try {
					IBeanCategoryAssignment bean = beanCaFactory.getInstanceFor(category);
					allCaClasses.add(bean.getClass());
				} catch (CoreException e) {
					throw new RuntimeException(e);
				}
			}
			for (StructuralElement se : concept.getStructuralElements()) {
				try {
					IBeanStructuralElementInstance bean = beanSeiFactory.getInstanceFor(se);
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
		super.preWriteTo(object, type, genericType, annotations, mediaType, httpHeaders, marshaller);
		marshaller.setEventHandler(eventHandler);
	}
	
	@Override
	public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		// Useful for debugging
		super.writeTo(object, type, genericType, annotations, mediaType, httpHeaders, entityStream);
	}
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// Useful for debugging
		Boolean isWriteable = super.isWriteable(type, genericType, annotations, mediaType);
		return isWriteable;
	}
	
	@Override
	protected void preReadFrom(Class<Object> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			Unmarshaller unmarshaller) throws JAXBException {
		super.preReadFrom(type, genericType, annotations, mediaType, httpHeaders, unmarshaller);
		unmarshaller.setEventHandler(eventHandler);
		unmarshaller.setAdapter(new IUuidAdapter(resourceSet));
		unmarshaller.setAdapter(new ABeanObjectAdapter(resourceSet));
	}
	
	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		ReadFromArguments arguments = new ReadFromArguments(
				type, genericType, annotations, mediaType, httpHeaders, entityStream);
		ReadFromCommand readFromCommand = new ReadFromCommand(ed, arguments);
		
		ed.getCommandStack().execute(readFromCommand);
		
		readFromCommand.throwExceptionsIfSet();
		
		return readFromCommand.getResult().iterator().next();
	}
	
	/**
	 * POJO to bundle the arguments for the readFrom function
	 */
	private static class ReadFromArguments {
		
		private Class<Object> type;
		private Type genericType;
		private Annotation[] annotations;
		private MediaType mediaType;
		private MultivaluedMap<String, String> httpHeaders;
		private InputStream entityStream;
		
		/**
		 * Constructor with all required arguments
		 * @param type
		 * @param genericType
		 * @param annotations
		 * @param mediaType
		 * @param httpHeaders
		 * @param entityStream
		 */
		ReadFromArguments(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {
			this.type = type;
			this.genericType = type;
			this.annotations = annotations;
			this.mediaType = mediaType;
			this.httpHeaders = httpHeaders;
			this.entityStream = entityStream;
		}
		
		public Class<Object> getType() {
			return type;
		}
		
		public Type getGenericType() {
			return genericType;
		}
		
		public Annotation[] getAnnotations() {
			return annotations;
		}
		
		public MediaType getMediaType() {
			return mediaType;
		}
		
		public MultivaluedMap<String, String> getHttpHeaders() {
			return httpHeaders;
		}
		
		public InputStream getEntityStream() {
			return entityStream;
		}
	}
	
	private class ReadFromCommand extends RecordingCommand {
		
		private Collection<Object> results = new ArrayList<>();
		private ReadFromArguments arguments;

		private AtomicExceptionReference<WebApplicationException> atomicWebAppException;
		private AtomicExceptionReference<IOException> atomicIoException;
		private AtomicExceptionReference<Exception> atomicException;
		
		/**
		 * Create a command to call ConfigurableMoxyJsonProvider.readFrom()
		 * over the transactional editing domain
		 * @param domain the ed
		 * @param arguments for the readFrom function 
		 */
		ReadFromCommand(TransactionalEditingDomain domain, ReadFromArguments arguments) {
			super(domain);
			this.arguments = arguments;
			
			atomicIoException = new AtomicExceptionReference<>();
			atomicWebAppException = new AtomicExceptionReference<>();
			atomicException = new AtomicExceptionReference<>();
		}

		@Override
		protected void doExecute() {
			try {
				Object result = TransactionalJsonProvider.super.readFrom(
					arguments.getType(),
					arguments.getGenericType(),
					arguments.getAnnotations(),
					arguments.getMediaType(),
					arguments.getHttpHeaders(),
					arguments.getEntityStream());
				resourceSet.saveAllResources(new NullProgressMonitor(), ed);
				repo.syncRepository();
				results.add(result);
			} catch (WebApplicationException e) {
				atomicWebAppException.set(e);
			} catch (IOException e) {
				atomicIoException.set(e);
			} catch (Exception e) {
				atomicException.set(e);
			}
		}
		
		/**
		 * Returns the result of ConfigurableMoxyJsonProvider.readFrom()
		 */
		@Override
		public Collection<?> getResult() {
			return results;
		}
		
		public void throwExceptionsIfSet() throws IOException, WebApplicationException {
			atomicWebAppException.throwIfSet();
			atomicIoException.throwIfSet();
			// Has to be thrown as RuntimeException because
			// we can't change the method signature in the parent class
			atomicException.throwAsRuntimeExceptionIfSet();
		}
	}
	
	@Override
	protected JAXBContext getJAXBContext(Set<Class<?>> domainClasses, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, ?> httpHeaders) throws JAXBException {
		
		// If the root element is a List we have to wrap it in a GenericEntity.
		// This causes JAXB to not register the classes (in this case the adapters)
		// so we have to register them manually, else an exception is thrown when creating the context.
		// And we don't want to register the CA because their depending classes
		// (e.g. the property beans) won't be resolved either and result in errors.
		if (domainClasses.contains(List.class)) {
			// Currently this is only the case for the RootSeis,
			// so we only register those missing classes here.
			domainClasses.addAll(LIST_CLASSES);
		} else {
			// We assume that the registered classes in the concept can change any time
			// so no cashing is possible and we have to get the current ones on each request
			if (ed != null) {
				domainClasses.addAll(getClassesToRegister());
			}
		}
		
		// But the contexts are being cashed on domainClasses
		// So it will reuse a context for the same domainClasses
		return super.getJAXBContext(domainClasses, annotations, mediaType, httpHeaders);
	}
}
