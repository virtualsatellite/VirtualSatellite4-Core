/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.data;

public class Workspace {

    private String user;

    Workspace() {
    }

    public Workspace(String name) {
        this.user = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String name) {
        this.user = name;
    }
}
