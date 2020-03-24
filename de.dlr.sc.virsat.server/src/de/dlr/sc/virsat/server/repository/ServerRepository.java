package de.dlr.sc.virsat.server.repository;

/**
 * Entry point to the eclipse project
 */
public class ServerRepository {
	private RepositoryConfiguration repositoryConfiguration;
	private IProject project;
	private Repository virSatRepository;

	
	public ServerRepository(RepositoryConfiguration repositoryConfiguration) {
		this.repositoryConfiguration = repositoryConfiguration;
		
		//checkout the project to workspace
	}
	
	
}
