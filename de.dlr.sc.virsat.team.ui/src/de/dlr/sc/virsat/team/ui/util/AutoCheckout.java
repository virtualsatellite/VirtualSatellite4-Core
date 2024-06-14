/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.team.ui.util;

import java.io.File;

import org.eclipse.ui.IStartup;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
//import org.eclipse.egit.core.RepositoryUtil;

import de.dlr.sc.virsat.server.configuration.RepositoryConfiguration;
import de.dlr.sc.virsat.server.repository.ServerRepository;
import de.dlr.sc.virsat.team.VersionControlSystem;


public class AutoCheckout implements IStartup {

	@Override
	public void earlyStartup() {

		boolean autoCheckoutFlag = false;

        // Check for -remote flag in application arguments
        String[] args = Platform.getApplicationArgs();
        for (String arg : args) {
            if (arg.equals("-autocheckout")) {
            	autoCheckoutFlag = true;
                break;
            }
        }
		
		if (autoCheckoutFlag) {
			try {
				// Get Environment Variables
				VersionControlSystem versionControlSystemType = VersionControlSystem.valueOf(System.getenv("VS_VCS_TYPE"));        
				String remoteUri = System.getenv("VS_VCS_REMOTE_URI");
		        String vcsUsername = System.getenv("VS_VCS_USERNAME");
		        String vcsPassword = System.getenv("VS_VCS_PASSWORD");
		        String workspacePath = System.getenv("VS_WORKSPACE_PATH");
		        String projectName =  System.getenv("VS_PROJECT_NAME");
		        System.out.println(remoteUri);
		        System.out.println(vcsUsername);
		        System.out.println(vcsPassword);
		        System.out.println(workspacePath);
		        System.out.println(projectName);
		        
		        File localRepositoryHome = new File(workspacePath);
				RepositoryConfiguration repositoryConfiguration = new RepositoryConfiguration(
						projectName, //gives repo_<>
						projectName, //gives repo_<>/<>
						remoteUri,
						versionControlSystemType,
						vcsUsername,
						vcsPassword
						);
		        
		        File repositoryProjectName = new File(workspacePath + "/" + ServerRepository.PREFIX_LOCAL_REPO_NAME + projectName); 
		        if (!repositoryProjectName.exists()) {
					ServerRepository serverRepository = new ServerRepository(localRepositoryHome, repositoryConfiguration);
					serverRepository.checkoutRepository();
		        } else {
		        	repositoryConfiguration.getProjectName();		
		    		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		    		IProjectDescription projectDescription = workspace.newProjectDescription(projectName);
		    		
		    		String relativeLocalProjectPath = repositoryConfiguration.getLocalPath();
		    		File projectInLocalRepositoryPath = new File(repositoryProjectName, relativeLocalProjectPath);
		    		projectDescription.setLocationURI(projectInLocalRepositoryPath.toURI());

		    		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		    		project.create(projectDescription, new NullProgressMonitor());
		    		project.open(new NullProgressMonitor());
		        }
		        
		        switch (versionControlSystemType) {
			        case GIT:
			        //	RepositoryUtil repoUtil = RepositoryUtil.INSTANCE;
				    //    File repo = new File(repositoryProjectName.getAbsolutePath() + "/.git");
				      //  repoUtil.addConfiguredRepository(repo);
			        case SVN:
			        	System.out.println("VersionControlSystem Type not implemented yet.");
			        default:
			        	System.out.println("VersionControlSystem Type unrecognized. Try GIT or SVN.");
		        }
				
		        
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}