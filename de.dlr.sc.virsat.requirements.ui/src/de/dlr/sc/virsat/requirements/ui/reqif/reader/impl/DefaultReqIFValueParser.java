/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.requirements.ui.reqif.reader.impl;

import java.util.List;

import org.eclipse.rmf.reqif10.AttributeDefinition;
import org.eclipse.rmf.reqif10.AttributeValue;
import org.eclipse.rmf.reqif10.AttributeValueEnumeration;
import org.eclipse.rmf.reqif10.AttributeValueReal;
import org.eclipse.rmf.reqif10.AttributeValueString;
import org.eclipse.rmf.reqif10.ReqIFContent;
import org.eclipse.rmf.reqif10.SpecHierarchy;
import org.eclipse.rmf.reqif10.SpecObject;
import org.eclipse.rmf.reqif10.SpecType;

import de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader;

/**
 * @author Tobias Franz
	tobias.franz@dlr.de
 *
 */
public class DefaultReqIFValueParser implements IReqIFReader {
	
	protected int indexID = 0;
	protected int indexDescription = 1;
	protected int indexName = 2;
	
	/**
	 * Constructor of this reader 
	 * @param indexID the index of the id
	 * @param indexName the index of the name
 	 * @param indexDescription the index of the description
	 */
	public DefaultReqIFValueParser(int indexID, int indexName, int indexDescription) {
		this.indexID = indexID;
		this.indexName = indexName;
		this.indexDescription = indexDescription;
	}
	

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader#getReqIdentifier(org.eclipse.rmf.reqif10.SpecHierarchy)
	 */
	@Override
	public String getReqIdentifier(SpecHierarchy requirement) {
		return valueToString(getRequirementObjectValue(requirement, indexID));
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader#getReqName(org.eclipse.rmf.reqif10.SpecHierarchy)
	 */
	@Override
	public String getReqName(SpecHierarchy requirement) {
		return valueToString(getRequirementObjectValue(requirement, indexName));
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader#getReqDescription(org.eclipse.rmf.reqif10.SpecHierarchy)
	 */
	@Override
	public String getReqDescription(SpecHierarchy requirement) {
		return valueToString(getRequirementObjectValue(requirement, indexDescription));
	}

	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader#getReqValues(org.eclipse.rmf.reqif10.SpecHierarchy)
	 */
	@Override
	public List<AttributeValue> getReqValues(SpecHierarchy requirement) {
		if (requirement.getObject() != null) {
			return requirement.getObject().getValues();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see de.dlr.sc.virsat.requirements.ui.reqif.reader.IReqIFReader#getValueWithColumnName(org.eclipse.rmf.reqif10.SpecHierarchy)
	 */
	@Override
	public String getValueWithColumnName(SpecObject specObject, String columnName) {
		String identifier = null;
		if (specObject == null || specObject.eContainer() == null) {
			return null;
		}

		if (specObject.eContainer() instanceof ReqIFContent) {
			ReqIFContent reqif = (ReqIFContent) specObject.eContainer();
			for (SpecType sType : reqif.getSpecTypes()) {

				if (specObject.getType().getIdentifier().equals(sType.getIdentifier())) {
					for (AttributeDefinition ad : sType.getSpecAttributes()) {
						if (ad.getLongName().equals(columnName)) {
							identifier = ad.getIdentifier();
						}
					}
				}
			}
		}	
		for (AttributeValue value : specObject.getValues()) {
			if (value instanceof AttributeValueEnumeration) {
				AttributeValueEnumeration enumValue = (AttributeValueEnumeration) value;
				if (enumValue.getDefinition().getIdentifier().equals(identifier)) {
					if (enumValue.getValues().size() > 0 && enumValue.getValues().get(0) != null) {
						return enumValue.getValues().get(0).getLongName();
					} else {
						return "";
					}

				}	
			}
			if (value instanceof AttributeValueString) {
				AttributeValueString stringValue = (AttributeValueString) value;
				if (stringValue.getDefinition().getIdentifier().equals(identifier)) {
					return stringValue.getTheValue();
				}
			}
			if (value instanceof AttributeValueReal) {
				AttributeValueReal realValue = (AttributeValueReal) value;
				if (realValue.getDefinition().getIdentifier().equals(identifier)) {
					return Double.toString(realValue.getTheValue());
				}
			}
		}
		return null;
	}
	
	/**
	 * Get the value of the requirement of a given index
	 * @param requirement the requirement
	 * @param number the index
	 * @return the value
	 */
	protected AttributeValue getRequirementObjectValue(SpecHierarchy requirement, int number) {
		AttributeValue value = null;
		if (requirement.getObject() != null) {
			value = (AttributeValue) requirement.getObject().getValues().get(number);
		}
		return value;
	}
	
	/**
	 * Convert the value to a string
	 * @param value the value
	 * @return the string
	 */
	protected String valueToString(AttributeValue value) {
		if (value instanceof AttributeValueString) {
			return ((AttributeValueString) value).getTheValue();
		} else if (value instanceof AttributeValueReal) {
			return ((AttributeValueString) value).getTheValue();
		}
		return null;
	}


}
