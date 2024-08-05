/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.virsat.server.example.model.init;

import java.util.ArrayList;

import de.dlr.virsat.server.example.model.Hello;
import de.dlr.virsat.server.example.model.Person;

public class ModelInitializer {
	
	public static final ModelInitializer INSTANCE = new ModelInitializer();
	
    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Hello> hellos = new ArrayList<>();

    
    /**
     * Private constructor for singleton pattern
     * and first example data initialization
     */
    private ModelInitializer() {
        persons.add(new Person("John"));
        persons.add(new Person("Sandra"));

        hellos.add(new Hello("Phil"));
        hellos.add(new Hello("Tobias"));
        hellos.add(new Hello("Mike"));
        hellos.add(new Hello("Jasmin"));
    }
    
    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Hello> getHellos() {
        return hellos;
    }
}