/**
 * This file is part of the VirSat project.
 *
 * This is a Virtual Satellite VirSat App
 *
 * Copyright (c) 2008-2016
 * German Aerospace Center (DLR), Simulation and Software Technology, Germany
 * All rights reserved
 *
 */
import de.dlr.sc.virsat.model.dvlm.Repository;
import de.dlr.sc.virsat.apps.api.external.ModelAPI;

/**
 * Example App
 * 
 * @author VirSat
 *
 */
public class de.dlr.virsat.app.app {
	public static void main(String[] args) {
		ModelAPI modelAPI = new ModelAPI();
		
		Repository repository = modelAPI.getRepository();
		
		System.out.println("Infos of Repository: " + repository);
		System.out.println("Currently Stored Units");

		modelAPI.getUnitManagement().getSystemOfUnit().getUnit().forEach((unit) -> System.out.println(unit));
	}
}

