/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.apps.initializer

class ContentExampleApp {
	
	def getContents(String appName) '''
	/*******************************************************************************
	 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
	 *
	 * This program and the accompanying materials are made available under the
	 * terms of the Eclipse Public License 2.0 which is available at
	 * http://www.eclipse.org/legal/epl-2.0.
	 *
	 * SPDX-License-Identifier: EPL-2.0
	 *******************************************************************************/
	import de.dlr.sc.virsat.model.dvlm.Repository;
	import de.dlr.sc.virsat.apps.api.external.ModelAPI;
	
	/**
	 * Example App
	 * 
	 * @author VirSat
	 *
	 */
	public class «appName» {
		public static void main(String[] args) {
			ModelAPI modelAPI = new ModelAPI();
			
			Repository repository = modelAPI.getRepository();
			
			System.out.println("Infos of Repository: " + repository);
			System.out.println("Currently Stored Units");
	
			modelAPI.getUnitManagement().getSystemOfUnit().getUnit().forEach((unit) -> System.out.println(unit));
		}
	}
	'''
}
