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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyInt;
import de.dlr.sc.virsat.model.concept.types.structural.BeanStructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.DVLMPackage;
import de.dlr.sc.virsat.model.dvlm.roles.UserRegistry;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElement;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralFactory;
import de.dlr.sc.virsat.project.editingDomain.VirSatTransactionalEditingDomain;
import de.dlr.sc.virsat.project.resources.command.CreateSeiResourceAndFileCommand;
import de.dlr.sc.virsat.server.test.AServerRepositoryTest;

public class TransactionalJsonProviderTest extends AServerRepositoryTest {

	private TransactionalJsonProvider provider;
	private BeanStructuralElementInstance testBean;
	private Class<?> type;
	private Set<Class<?>> beanClass = new HashSet<>();
	private MediaType mediaType;
	private String testString = "testString";
	private StructuralElementInstance testSei;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		UserRegistry.getInstance().setSuperUser(true);
		
		provider = new TransactionalJsonProvider();
		provider.setServerRepository(testServerRepository);
		VirSatTransactionalEditingDomain ed = testServerRepository.getEd();
		
		StructuralElement testSe = StructuralFactory.eINSTANCE.createStructuralElement();
		testSe.setIsRootStructuralElement(true);
		testSe.setName("testSe");

		testSei = StructuralFactory.eINSTANCE.createStructuralElementInstance();
		testSei.setType(testSe);
		testSei.setName(testString);
		
		Command addSeiToRepo = AddCommand.create(ed, ed.getResourceSet().getRepository(), 
				DVLMPackage.eINSTANCE.getRepository_RootEntities(), testSei);
		ed.getCommandStack().execute(addSeiToRepo);
		
		Command createSei = new CreateSeiResourceAndFileCommand(ed.getResourceSet(), testSei);
		ed.getCommandStack().execute(createSei);

		ed.getResourceSet().saveAllResources(new NullProgressMonitor(), ed);

		testBean = new BeanStructuralElementInstance(testSei);

		type = BeanStructuralElementInstance.class;
		beanClass.add(type);
		
		mediaType = MediaType.APPLICATION_JSON_TYPE;
		
		// Initial commit to have a valid HEAD
		Git git = Git.open(testServerRepository.getLocalRepositoryPath());
		git.add().addFilepattern(".").call();
		git.commit().setAll(true).setMessage("Initial commit").call();
		git.push().call();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
		UserRegistry.getInstance().setSuperUser(false);
	}

	/**
	 * Call writeTo and assert that the the output String contains the test value
	 * @return the output String
	 * @throws WebApplicationException
	 * @throws IOException
	 */
	private String writeToAndAssert(String testString) throws WebApplicationException, IOException {
		OutputStream entityStream = new ByteArrayOutputStream();
		provider.writeTo(testBean, type, type, null, mediaType, (MultivaluedMap<String, Object>) null, entityStream);
		
		String output = entityStream.toString();
		assertTrue(output.contains(testString));
		
		return output;
	}

	// Test the marshalling
	@Test
	public void testWriteTo() throws WebApplicationException, IOException, NoHeadException, GitAPIException, InterruptedException {
		
		// No changes don't create a commit
		int initialCommits = countCommits(testServerRepository.getLocalRepositoryPath());
		writeToAndAssert(testString);
		assertEquals("No new commit", initialCommits, countCommits(pathRepoRemote.toFile()));
		
		// Checkout the remote again and create a change that has to be 
		// resolved in the writeTo method
		Path localRepo = VirSatFileUtils.createAutoDeleteTempDirectory("localRepo");
		Git git = Git.cloneRepository()
				.setDirectory(localRepo.toFile())
				.setURI(testServerRepository.getRepositoryConfiguration().getRemoteUri())
				.call();
		
		String newString = "new";
		Path seiPathInLocal = Paths.get(localRepo.toString(), testSei.eResource().getURI().toPlatformString(false));
		
		String content = new String(Files.readAllBytes(seiPathInLocal), StandardCharsets.UTF_8);
		content = content.replaceAll(testString, newString);
		Files.write(seiPathInLocal, content.getBytes(StandardCharsets.UTF_8));
		
		git.add().addFilepattern(".").call();
		git.commit().setAll(true).setMessage("Added file").call();
		git.push().call();
		
		initialCommits = countCommits(testServerRepository.getLocalRepositoryPath());
		writeToAndAssert(newString);
		assertEquals("One new commit", initialCommits + 1, countCommits(pathRepoRemote.toFile()));
	}
	
	/**
	 * Open a repository and count the commits
	 * @param repoFile the repository to open
	 * @return number of commits in remote
	 * @throws IOException
	 * @throws NoHeadException
	 * @throws GitAPIException
	 * @throws InterruptedException
	 */
	private int countCommits(File repoFile) throws IOException, NoHeadException, GitAPIException, InterruptedException {
		Git git = Git.open(repoFile);
		Iterator<RevCommit> iterator = git.log().call().iterator();
		int commits = 0;
		while (iterator.hasNext()) {
			iterator.next();
			commits++;
		}
		git.close();
		return commits;
	}
	
	// Test the unmarshalling
	@SuppressWarnings("unchecked")
	@Test
	public void testReadFrom() throws WebApplicationException, IOException, NoHeadException, GitAPIException, InterruptedException {

		int initialCommits = countCommits(pathRepoRemote.toFile());
		
		String output = writeToAndAssert(testString);
		
		// No changes don't create a commit
		StringBuffer buf = new StringBuffer(output);
		InputStream entityStream = new ByteArrayInputStream(buf.toString().getBytes());
		provider.readFrom((Class<Object>) type, type, null, mediaType, null, entityStream);
		
		assertEquals(testString, testBean.getName());
		assertEquals("No new commit", initialCommits, countCommits(pathRepoRemote.toFile()));
		
		// Changes result in a commit
		String newValue = "new";
		output = output.replace(testString, newValue);
		buf = new StringBuffer(output);
		entityStream = new ByteArrayInputStream(buf.toString().getBytes());
		provider.readFrom((Class<Object>) type, type, null, mediaType, null, entityStream);
		
		assertEquals(newValue, testBean.getName());
		assertEquals("One new commit", initialCommits + 1, countCommits(pathRepoRemote.toFile()));
	}

	@Test
	public void testGetJAXBContext() throws JAXBException {
		JAXBContext context = provider.getJAXBContext(beanClass, null, mediaType, null);
		assertNotNull(context);
		
		JAXBContext context2 = provider.getJAXBContext(beanClass, null, mediaType, null);
		assertSame("Context got cashed", context, context2);
		
		beanClass.add(BeanPropertyInt.class);
		JAXBContext context3 = provider.getJAXBContext(beanClass, null, mediaType, null);
		assertNotSame("New context because the classes to register changed", context, context3);
	}

}
