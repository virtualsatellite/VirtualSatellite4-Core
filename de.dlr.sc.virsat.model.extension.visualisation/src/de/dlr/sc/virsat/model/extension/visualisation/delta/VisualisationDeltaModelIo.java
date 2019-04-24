/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.visualisation.delta;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class handles the serialization and deserialization of a 
 * Delta Model into an XML representation.
 * 
 * @author fisc_ph
 *
 */
public class VisualisationDeltaModelIo {
	
	/**
	 * It is a utility class and therefore the constructor has no use to anyone
	 */
	private VisualisationDeltaModelIo() {
	}

	/**
	 * Use this method to throw the DeltaModel into an OuputStream.
	 * The method will create an XML representation out of the model
	 * @param vdm The Visualization Delta Model to be serialized into XML
	 * @param os the output stream to which to write the model to
	 */
	public static void writeModel(VisualisationDeltaModel vdm, OutputStream os) {
		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(os));
		e.writeObject(vdm);
		e.close();
	}
	
	/**
	 * Method to read an XML input stream and to convert it back into a Delta Model
	 * @param is The input stream from which to read the XML
	 * @return the Delta Model in case it could be created
	 */
	public static VisualisationDeltaModel readModel(InputStream is) {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(is));
		Object vdm = d.readObject();
		d.close();
		return (VisualisationDeltaModel) vdm;
	}
}
