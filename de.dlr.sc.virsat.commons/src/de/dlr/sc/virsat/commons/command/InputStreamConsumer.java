/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.commons.command;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamConsumer extends Thread {

    private InputStream is;
    private BufferedWriter bw;

    public InputStreamConsumer(InputStream is, BufferedWriter bw) {
        this.is = is;
        this.bw = bw;
    }

    @Override
    public void run() {
    	try {
            int value = -1;
            while ((value = is.read()) != -1) {
            	bw.write((char) value);
                System.out.print((char) value);
            }
            bw.close();
        } catch (IOException exp) {
            exp.printStackTrace();
        }

    }

}
