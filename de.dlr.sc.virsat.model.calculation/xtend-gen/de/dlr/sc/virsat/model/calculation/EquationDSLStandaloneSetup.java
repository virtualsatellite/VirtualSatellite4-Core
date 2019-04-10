/**
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package de.dlr.sc.virsat.model.calculation;

import de.dlr.sc.virsat.model.calculation.EquationDSLStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class EquationDSLStandaloneSetup extends EquationDSLStandaloneSetupGenerated {
  public static void doSetup() {
    new EquationDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}
