/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.dvlm.qudv.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AffineConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.LinearConversionUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvFactory;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.SimpleUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import de.dlr.sc.virsat.model.dvlm.types.impl.VirSatUuid;

/**
 * unit helper class
 * @author scha_vo
 *
 */
public class QudvUnitHelper {

	/**
	 * private constructor method
	 */
	private QudvUnitHelper() {		
	}
	
	private static final QudvUnitHelper INSTANCE = new QudvUnitHelper();
	
	private static final Double ERROR = 0.00001;
	
	/**
	 * public constructor returning a singleton instance of the class
	 * @return {@link QudvUnitHelper}
	 */
	public static QudvUnitHelper getInstance() {  
		return INSTANCE;
	}
	
	/**
	 * Creates a simpleUnit of the QUDV data model
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKind the quantityKind to reference
	 * @return the simple unit
	 */
	public SimpleUnit createSimpleUnit(String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind) {
		SimpleUnit unit = QudvFactory.eINSTANCE.createSimpleUnit();
		
		// Set the name and description
		unit.setName(name);
		unit.setSymbol(symbol);
		unit.setDescription(description);
		unit.setDefinitionURI(definitionURI);
		
		//set the reference to the quantityKind
		unit.setQuantityKind(quantityKind);
		
		return unit;
	}
	
	/**
	 * Creates a prefixedUnit of the QUDV data model
	 * and is creating the reference to the given prefix.
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKind the quantityKind to reference
	 * @param prefix the referenced prefix of the prefixed unit
	 * @param baseUnit the based unit for the prefixed unit
	 * @return the prefixed unit
	 */
	public PrefixedUnit createPrefixedUnit(String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, Prefix prefix, AUnit baseUnit) {
		PrefixedUnit unit = QudvFactory.eINSTANCE.createPrefixedUnit();
		
		// Set the name and description
		unit.setName(name);
		unit.setSymbol(symbol);
		unit.setDescription(description);
		unit.setDefinitionURI(definitionURI);
		unit.setQuantityKind(quantityKind);
		
		//all prefixes are invertible, so set the flag!
		unit.setIsInvertible(true);
		
		// Set the reference to the prefix
		unit.setPrefix(prefix);
		
		// Set the reference to the base unit
		unit.setReferenceUnit(baseUnit);
		
		return unit;
	}
	
	/**
	 * Creates a AffineConversionUnit of the QUDV data model
	 * and is creating the reference to the given reference unit.
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKind the quantityKind to reference
	 * @param refUnit the referenced base unit for the conversion
	 * @param factor The factor of the conversion
	 * @param offset The offset of the conversion
	 * @return the affine conversion unit
	 */
	public AffineConversionUnit createAffineConversionUnit(String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, AUnit refUnit, Double factor, Double offset) {
		AffineConversionUnit unit = QudvFactory.eINSTANCE.createAffineConversionUnit();
		
		// Set the name and description
		unit.setName(name);
		unit.setSymbol(symbol);
		unit.setDescription(description);
		unit.setDefinitionURI(definitionURI);
		unit.setQuantityKind(quantityKind);
		
		//all affine conversion units are invertible, so set the flag!
		unit.setIsInvertible(true);
		
		// Set the reference unit
		unit.setReferenceUnit(refUnit);
		
		// Set factor and offset
		unit.setFactor(factor);
		unit.setOffset(offset);
		
		return unit;
	}
	
	/**
	 * Creates a LinearConversionUnit of the QUDV data model
	 * and is creating the reference to the reference unit.
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKind the quantityKind to reference
	 * @param refUnit the referenced base unit for the conversion
	 * @param factor The factor of the conversion
	 * @return the linear conversion unit
	 */
	public LinearConversionUnit createLinearConversionUnit(String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, AUnit refUnit, Double factor) {
		LinearConversionUnit unit = QudvFactory.eINSTANCE.createLinearConversionUnit();
		
		// Set the name and description
		unit.setName(name);
		unit.setSymbol(symbol);
		unit.setDescription(description);
		unit.setDefinitionURI(definitionURI);
		unit.setQuantityKind(quantityKind);
		
		//all linear conversion units are invertible, so set the flag!
		unit.setIsInvertible(true);
		
		// Set the reference unit
		unit.setReferenceUnit(refUnit);
		
		// Set factor and offset
		unit.setFactor(factor);
		
		return unit;
	}
	
	/**
	 * Creates a DerivedUnit of the QUDV data model, similar to createSimpleUnit
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKind the quantityKind to reference
	 * @return the derived unit
	 */
	public DerivedUnit createDerivedUnit(String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind) {
		//The derived unit is in creation similar to the simple unit because it has the same attributes which have to be set
		//maybe I can use the simple Unit create method and avoid some duplicate code
		DerivedUnit unit = QudvFactory.eINSTANCE.createDerivedUnit();
		
		// Set the name and description
		unit.setName(name);
		unit.setSymbol(symbol);
		unit.setDescription(description);
		unit.setDefinitionURI(definitionURI);
		unit.setQuantityKind(quantityKind);
		
		return unit;
	}
	
	/**
	 * Creates a DerivedUnit of the QUDV data model, similar to createSimpleUnit
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKind the quantityKind to reference
	 * @param unitFactorMap a Map of UnitFactors
	 * @return the derived unit
	 */
	public DerivedUnit createAndAddDerivedUnit(String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, HashMap<AUnit, Double> unitFactorMap) {
		//The derived unit is in creation similar to the simple unit because it has the same attributes which have to be set
		//maybe I can use the simple Unit create method and avoid some duplicate code
		DerivedUnit unit = QudvFactory.eINSTANCE.createDerivedUnit();
		
		// Set the name and description
		unit.setName(name);
		unit.setSymbol(symbol);
		unit.setDescription(description);
		unit.setDefinitionURI(definitionURI);
		unit.setQuantityKind(quantityKind);
		
		Iterator<Entry<AUnit, Double>> iterator = unitFactorMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<AUnit, Double> m = (Map.Entry<AUnit, Double>) iterator.next();
			UnitFactor unitFactor = QudvUnitHelper.getInstance().createUnitFactor(m.getValue(), m.getKey());
			unit.getFactor().add(unitFactor);
		}
		
		return unit;
	}
	
	/**
	 * Creates a UnitFactor of the QUDV data model
	 * @param exponent The exponent of the UnitFactor
	 * @param refUnit the reference unit
	 * @return the unit factor
	 */
	public UnitFactor createUnitFactor(Double exponent, AUnit refUnit) {
		//The derived unit is in creation similar to the simple unit because it has the same attributes which have to be set
		//maybe I can use the simple Unit create method and avoid some duplicate code
		UnitFactor unitFactor = QudvFactory.eINSTANCE.createUnitFactor();
		
		// Set the name and description
		unitFactor.setExponent(exponent);
		unitFactor.setUnit(refUnit);
		
		return unitFactor;
	}
	
	/**
	 * Creates a QuantityKindFactor of the QUDV data model
	 * @param exponent The exponent of the QuantityKindFactor
	 * @param refQuantityKind the reference unit
	 * @return the quantity kind factor
	 */
	public QuantityKindFactor createQuantityKindFactor(Double exponent, AQuantityKind refQuantityKind) {
		QuantityKindFactor quantityKindFactor = QudvFactory.eINSTANCE.createQuantityKindFactor();
		
		quantityKindFactor.setExponent(exponent);
		quantityKindFactor.setQuantityKind(refQuantityKind);
		
		return quantityKindFactor;
	}
	
	/**
	 * Creates a simpleQuantityKind of the QUDV data model
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @return the simple QuantityKind 
	 */
	public SimpleQuantityKind createSimpleQuantityKind(String name, String symbol, String description, String definitionURI) {
		SimpleQuantityKind quantityKind = QudvFactory.eINSTANCE.createSimpleQuantityKind();
		
		// Set the name and description
		quantityKind.setName(name);
		quantityKind.setSymbol(symbol);
		quantityKind.setDescription(description);
		quantityKind.setDefinitionURI(definitionURI);
		
		return quantityKind;
	}
	
	/**
	 * Creates a DerivedQuantityKind of the QUDV data model
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @return the derived QuantityKind 
	 */
	public DerivedQuantityKind createDerivedQuantityKind(String name, String symbol, String description, String definitionURI) {
		DerivedQuantityKind quantityKind = QudvFactory.eINSTANCE.createDerivedQuantityKind();
		
		// Set the name and description
		quantityKind.setName(name);
		quantityKind.setSymbol(symbol);
		quantityKind.setDescription(description);
		quantityKind.setDefinitionURI(definitionURI);
		
		return quantityKind; 
	}
	
	/**
	 * Creates a DerivedQuantityKind of the QUDV data model
	 * @param name The name of the unit
	 * @param symbol The symbol of the unit
	 * @param description The description of the unit
	 * @param definitionURI the defining URI of the unit
	 * @param quantityKindFactorMap the map defining the quantity kinds for this derived unit
	 * @return the derived QuantityKind 
	 */
	public DerivedQuantityKind createAndAddDerivedQuantityKind(String name, String symbol, String description, String definitionURI, Map<AQuantityKind, Double> quantityKindFactorMap) {
		DerivedQuantityKind quantityKind = QudvFactory.eINSTANCE.createDerivedQuantityKind();
		
		// Set the name and description
		quantityKind.setName(name);
		quantityKind.setSymbol(symbol);
		quantityKind.setDescription(description);
		quantityKind.setDefinitionURI(definitionURI);
		
		Iterator<Entry<AQuantityKind, Double>> iterator = quantityKindFactorMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<AQuantityKind, Double> m = (Map.Entry<AQuantityKind, Double>) iterator.next();
			QuantityKindFactor quantityKindFactor = QudvUnitHelper.getInstance().createQuantityKindFactor(m.getValue(), m.getKey());
			quantityKind.getFactor().add(quantityKindFactor);
		}
		
		return quantityKind;
	}
	
	/**
	 * a method which initializes the systemOfUnits
	 * @param name the name of the SystemOfUnits
	 * @param symbol the symbol
	 * @param description a custom description
	 * @param definitionURI a URI defining the name space for the SystemOfUnits
	 * @return {@link SystemOfUnits}
	 */
	public SystemOfUnits initializeSystemOfUnits(String name, String symbol, String description, String definitionURI) {
		SystemOfUnits systemOfUnits = QudvFactory.eINSTANCE.createSystemOfUnits();
		systemOfUnits.setName(name);
		systemOfUnits.setSymbol(symbol);
		systemOfUnits.setDescription(description);
		systemOfUnits.setDefinitionURI(definitionURI);
		
		SystemOfQuantities systemOfQuantities = QudvFactory.eINSTANCE.createSystemOfQuantities();
		systemOfUnits.getSystemOfQuantities().add(systemOfQuantities);
		systemOfQuantities.setName("SystemOfQuantities");
		systemOfQuantities.setSymbol("SoQ");
		systemOfQuantities.setDescription("");
		systemOfQuantities.setDefinitionURI("");
		
		createLibraryOfUnitsAndQuantityKinds(systemOfUnits);
		
		return systemOfUnits;
	}
		
	static final String DIMENSIONLESS_QK_NAME = "Dimensionless"; 
	
	AQuantityKind dimensionlessQK;
	AQuantityKind length;
	AQuantityKind mass;
	AQuantityKind time;
	AQuantityKind temperature;
	AQuantityKind amountOfSubstance;
	AQuantityKind electricCurrent;
	AQuantityKind luminousIntensity;
	
	//turn the checkstyle off because there are so many magic numbers and stuff so it's a hassle at this point 
	// CHECKSTYLE:OFF
	private void createLibraryOfUnitsAndQuantityKinds(SystemOfUnits systemOfUnits) {

		// add twenty default SI prefixes to the SystemOfUnits so that they are always there!
		Prefix yotta = createPrefix("yotta", "Y", 1E24);
		Prefix zetta = createPrefix("zetta", "Z", 1E21);
		Prefix exa = createPrefix("exa", "E", 1E18);
		Prefix peta = createPrefix("peta", "P", 1E15);
		Prefix tera = createPrefix("tera", "T", 1E12);
		Prefix giga = createPrefix("giga", "G", 1E9);
		Prefix mega = createPrefix("mega", "M", 1E6);
		Prefix kilo = createPrefix("kilo", "k", 1E3);
		Prefix hecto = createPrefix("hecto", "h", 1E2);
		Prefix deka = createPrefix("deka", "da", 1E1);
		Prefix deci = createPrefix("deci", "d", 1E-1);
		Prefix centi = createPrefix("centi", "c", 1E-2);
		Prefix milli = createPrefix("milli", "m", 1E-3);
		Prefix micro = createPrefix("micro", "µ", 1E-6);
		Prefix nano = createPrefix("nano", "n", 1E-9);
		Prefix pico = createPrefix("pico", "p", 1E-12);
		Prefix femto = createPrefix("femto", "f", 1E-15);
		Prefix atto = createPrefix("atto", "a", 1E-18);
		Prefix zepto = createPrefix("zepto", "z", 1E-21);
		Prefix yocto = createPrefix("yocto", "y", 1E-24);
		
		systemOfUnits.getPrefix().add(yotta);
		systemOfUnits.getPrefix().add(zetta);
		systemOfUnits.getPrefix().add(exa);
		systemOfUnits.getPrefix().add(peta);
		systemOfUnits.getPrefix().add(tera);
		systemOfUnits.getPrefix().add(giga);
		systemOfUnits.getPrefix().add(mega);
		systemOfUnits.getPrefix().add(kilo);
		systemOfUnits.getPrefix().add(hecto);
		systemOfUnits.getPrefix().add(deka);
		systemOfUnits.getPrefix().add(deci);
		systemOfUnits.getPrefix().add(centi);
		systemOfUnits.getPrefix().add(milli);
		systemOfUnits.getPrefix().add(micro);
		systemOfUnits.getPrefix().add(nano);
		systemOfUnits.getPrefix().add(pico);
		systemOfUnits.getPrefix().add(femto);
		systemOfUnits.getPrefix().add(atto);
		systemOfUnits.getPrefix().add(zepto);
		systemOfUnits.getPrefix().add(yocto);
		
		SystemOfQuantities systemOfQuantities = systemOfUnits.getSystemOfQuantities().get(0);
		
		HashMap<AQuantityKind, Double> factorMap = new HashMap<AQuantityKind, Double>();
		factorMap.clear();	

		//create the 8 SI base QuantityKinds as reference in the helper class	
		dimensionlessQK = createSimpleQuantityKind(DIMENSIONLESS_QK_NAME, "U", "dimensionlessQK", "");
		length = createSimpleQuantityKind("Length", "L", "lengthQK", "");
		mass = createSimpleQuantityKind("Mass", "M", "massQK", "");
		time = createSimpleQuantityKind("Time", "T", "timeQK", "");
		temperature = createSimpleQuantityKind("Temperature", "Θ", "temperatureQK", "");
		amountOfSubstance = createSimpleQuantityKind("Amount of substance", "N", "amountOfSubstanceQK", "");
		electricCurrent = createSimpleQuantityKind("Electric current", "I", "electricCurrentQK", "");
		luminousIntensity = createSimpleQuantityKind("Luminous intensity", "J", "luminousIntensityQK", "");
		
		//add the 8 base quantityKinds to the system of quantities 
		systemOfQuantities.getQuantityKind().add(dimensionlessQK);
		systemOfQuantities.getQuantityKind().add(length);
		systemOfQuantities.getQuantityKind().add(mass);	
		systemOfQuantities.getQuantityKind().add(time);
		systemOfQuantities.getQuantityKind().add(temperature);
		systemOfQuantities.getQuantityKind().add(amountOfSubstance);
		systemOfQuantities.getQuantityKind().add(electricCurrent);
		systemOfQuantities.getQuantityKind().add(luminousIntensity);

		//create the 8 SI base Units accordingly to the quantityKinds 
		SimpleUnit noUnit = createSimpleUnit(UNIT_NAME_NO_UNIT, "", "noUnit", "", dimensionlessQK);
		SimpleUnit meter = createSimpleUnit(UNIT_NAME_METER, "m", "meterUnit", "", length);
		SimpleUnit gram = createSimpleUnit("Gram", "g", "gramUnit", "", mass); //instead of kilogram! this is prefixed in our case
		SimpleUnit second = createSimpleUnit("Second", "s", "secondUnit", "", time);
		SimpleUnit ampere = createSimpleUnit("Ampere", "A", "ampereUnit", "", electricCurrent);
		SimpleUnit kelvin = createSimpleUnit("Kelvin", "K", "kelvinUnit", "", temperature);
		SimpleUnit mole = createSimpleUnit("Mole", "mol", "moleUnit", "", amountOfSubstance);
		SimpleUnit candela = createSimpleUnit("Candela", "cd", "candelaUnit", "", luminousIntensity);
		
		systemOfUnits.getUnit().add(noUnit);
		systemOfUnits.getUnit().add(meter);
		systemOfUnits.getUnit().add(gram);
		systemOfUnits.getUnit().add(second);
		systemOfUnits.getUnit().add(ampere);
		systemOfUnits.getUnit().add(kelvin);
		systemOfUnits.getUnit().add(mole);
		systemOfUnits.getUnit().add(candela);
		
		factorMap.clear();	
		// some coefficient 
		final Double M1 = -1.0;
		final Double M2 = -2.0;
		final Double M3 = -3.0;
		final Double THREE = 3.0;
		
		//#################################
		//derived quantity kinds
		//#################################
		
		//area
		factorMap.put(length, 2.0);
		DerivedQuantityKind area = createAndAddDerivedQuantityKind("Area", "L²", "areaQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(area);
		
		//force
		factorMap.clear();
		factorMap.put(length, 1.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		DerivedQuantityKind force = createAndAddDerivedQuantityKind("Force", "L¹ M¹ T⁻²", "forceQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(force);
		
		//energy and work
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		DerivedQuantityKind energyAndWork = createAndAddDerivedQuantityKind("Energy and Work", "L² M¹ T⁻²", "energyAndWorkQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(energyAndWork);
		
		//density
		factorMap.clear();
		factorMap.put(length, M3);
		factorMap.put(mass, 1.0);
		DerivedQuantityKind density = createAndAddDerivedQuantityKind("Density", "L⁻³ M¹", "densityQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(density);

		//electric charge
		factorMap.clear();
		factorMap.put(time, 1.0);
		factorMap.put(electricCurrent, 1.0);
		DerivedQuantityKind electricChargeQK = createAndAddDerivedQuantityKind("Electric Charge", "T¹ I¹", "electricChargeQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(electricChargeQK);
		
		//frequency
		factorMap.clear();
		factorMap.put(time, M1);
		DerivedQuantityKind frequency = createAndAddDerivedQuantityKind("Frequency", "T⁻¹", "frequencyQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(frequency);
		
		//data rate: since the information unit is dimensionless, the data rate is effectively a frequency as well
		factorMap.clear();
		factorMap.put(frequency, 1.0);
		DerivedQuantityKind dataRateQK = createAndAddDerivedQuantityKind("Data rate", "T⁻¹", "dataRateQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(dataRateQK);
		
		//heat capacity and entropy
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		factorMap.put(temperature, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Heat Capacity and Entropy", "L² M¹ T⁻² Θ⁻¹", "heatCapacityAndEntropyQK", "",  factorMap));
		
		//heat flow rate
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Heat Flow Rate", "L² M¹ T⁻³", "heatFlowRateQK", "",  factorMap));
		
		//heat flow rate per Unit Area
		factorMap.clear();
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Heat Flow Rate per Unit Area", "M¹ T⁻³", "heatFlowRatePerUnitAreaQK", "",  factorMap));
		
		//inductance
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		factorMap.put(electricCurrent, M2);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Inductance", "L² M¹ T⁻² I⁻²", "inductanceQK", "",  factorMap));
		
		//acceleration
		factorMap.clear();
		factorMap.put(length, 1.0);
		factorMap.put(time, M2);
		DerivedQuantityKind acceleration = createAndAddDerivedQuantityKind("Acceleration", "L¹ T⁻²", "accelerationQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(acceleration);
		
		
		//angle
		factorMap.clear();
		factorMap.put(dimensionlessQK, 1.0);
		DerivedQuantityKind angle = createAndAddDerivedQuantityKind("Angle", "α", "angleQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(angle);
		
		//angular velocity
		factorMap.clear();
		factorMap.put(angle, 1.0);
		factorMap.put(time, M1);
		DerivedQuantityKind angularVelocity = createAndAddDerivedQuantityKind("Angular Velocity", "α¹ T⁻¹", "angularVelocityQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(angularVelocity);
		
		//moment of inertia
		factorMap.clear();
		factorMap.put(mass, 1.0);
		factorMap.put(length, 2.0);
		DerivedQuantityKind momentsOfInertia = createAndAddDerivedQuantityKind("Moment Of Inertia", "M¹ L²", "momentOfInertiaQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(momentsOfInertia);
		

		//linear momentum
		factorMap.clear();
		factorMap.put(length, 1.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Linear Momentum", "L¹ M¹ T⁻¹", "linearMomentumQK", "",  factorMap));
		
		//velocity
		factorMap.clear();
		factorMap.put(length, 1.0);
		factorMap.put(time, M1);
		DerivedQuantityKind velocity = createAndAddDerivedQuantityKind("Velocity", "L¹ T⁻¹", "velocityQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(velocity);
		
		//luminance
		factorMap.clear();
		factorMap.put(length, M2);
		factorMap.put(luminousIntensity, 1.0);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Luminance", "L¹ M¹ T⁻¹", "luminanceQK", "",  factorMap));
		
		//magnetic dipole moment
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(electricCurrent, 1.0);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Magnetic Dipole Moment", "L² I¹", "magneticDipoleMomentQK", "",  factorMap));

		//magnetic field strength
		factorMap.clear();
		factorMap.put(length, M1);
		factorMap.put(electricCurrent, 1.0);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Magnetic Field Strength", "L⁻¹ I¹", "magneticFieldStrengthQK", "",  factorMap));

		//magnetic flux
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		factorMap.put(electricCurrent, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Magnetic Flux", "L² M¹ T⁻² I⁻¹", "magneticFluxQK", "",  factorMap));
		
		//magnetic flux density
		factorMap.clear();
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		factorMap.put(electricCurrent, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Magnetic Flux Density", "M¹ T⁻² I⁻¹", "magneticFluxDensityQK", "",  factorMap));
		
		//molar energy
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		factorMap.put(amountOfSubstance, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Molar Energy", "L² M¹ T⁻² N⁻¹", "molarEnergyQK", "",  factorMap));
		
		//molar heat capacity
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		factorMap.put(temperature, M1);
		factorMap.put(amountOfSubstance, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Molar Heat Capacity", "L² M¹ T⁻² N⁻¹", "molarHeatCapacityQK", "",  factorMap));
		
		//power
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		DerivedQuantityKind power = createAndAddDerivedQuantityKind("Power", "L² M¹ T⁻³", "powerQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(power);
		
		//electric potential difference
		factorMap.clear();
		factorMap.put(length, 3.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		factorMap.put(electricCurrent, M1);
		DerivedQuantityKind electricPotentialDifference = createAndAddDerivedQuantityKind("Electric potential difference", "L² M¹ T⁻³ I⁻¹", "electricPotentialDifferenceQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(electricPotentialDifference);
		
		//power per unit area
		factorMap.clear();
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Power per Unit Area", "M¹ T⁻³", "powerPerUnitAreaQK", "",  factorMap));
		
		//mass per unit area
		factorMap.clear();
		factorMap.put(mass, 1.0);
		factorMap.put(length, M2);
		DerivedQuantityKind massPerUnitArea = createAndAddDerivedQuantityKind("Mass per Unit Area", "M¹ L⁻²", "massPerUnitAreaQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(massPerUnitArea);
		
		//pressure or stress
		factorMap.clear();
		factorMap.put(length, M1);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		DerivedQuantityKind pressureAndStress = createAndAddDerivedQuantityKind("PressureOrStress", "M¹ T⁻³", "pressureOrStressQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(pressureAndStress);

		//resistance
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		factorMap.put(electricCurrent, M2);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Resistance", "L² M¹ T⁻³ I⁻²", "resistanceQK", "",  factorMap));
		
		//specific energy
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(time, M2);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Specific Energy", "L² T⁻²", "specificEnergyQK", "",  factorMap));
		
		//specific heat capacity
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(time, M2);
		factorMap.put(temperature, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Specific Heat Capacity", "L² T⁻² Θ⁻¹", "specificHeatCapacityQK", "",  factorMap));

		//thermal conductivity
		factorMap.clear();
		factorMap.put(length, 1.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M3);
		factorMap.put(temperature, M1);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Thermal Conductivity", "L¹ M¹ T⁻³ Θ⁻¹", "thermalConductivityQK", "",  factorMap));
		
		//time squared
		factorMap.clear();
		factorMap.put(time, 2.0);
		systemOfQuantities.getQuantityKind().add(createAndAddDerivedQuantityKind("Time Squared", "T²", "timeSquaredQK", "",  factorMap));
		
		//torque
		factorMap.clear();
		factorMap.put(length, 2.0);
		factorMap.put(mass, 1.0);
		factorMap.put(time, M2);
		DerivedQuantityKind torque = createAndAddDerivedQuantityKind("Torque", "L² M¹ T⁻²", "torqueQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(torque);
		
		//volume
		factorMap.clear();
		factorMap.put(length, THREE);
		DerivedQuantityKind volume = createAndAddDerivedQuantityKind("Volume", "L³", "volumeQK", "",  factorMap);
		systemOfQuantities.getQuantityKind().add(volume);
		
		//prefixed unit kilometer
		PrefixedUnit kilometer = createPrefixedUnit("Kilometer", "km", "kilometerUnit", "", length, kilo, meter);
		systemOfUnits.getUnit().add(kilometer);
		//prefixed unit millimeter
		PrefixedUnit millimeter = createPrefixedUnit("Millimeter", "mm", "millimeterUnit", "", length, milli, meter);
		systemOfUnits.getUnit().add(millimeter);
		//prefixed unit centimeter
		PrefixedUnit centimeter = createPrefixedUnit("Centimeter", "cm", "centimeterUnit", "", length, centi, meter);
		systemOfUnits.getUnit().add(centimeter);
		//prefixed unit kilogram
		PrefixedUnit kilogram = createPrefixedUnit(UNIT_NAME_KILO_GRAMM, "kg", "kilogramUnit", "", mass, kilo, gram);
		systemOfUnits.getUnit().add(kilogram);
		
		
		//some time units
		AffineConversionUnit minute = createAffineConversionUnit("Minute", "min", "minuteUnit", "", time, second, 60.0, 0.0);
		systemOfUnits.getUnit().add(minute);
		
		AffineConversionUnit hour = createAffineConversionUnit("Hour", "h", "hourUnit", "", time, minute, 60.0, 0.0);
		systemOfUnits.getUnit().add(hour);
		
		AffineConversionUnit year = createAffineConversionUnit("Year", "y", "yearUnit", "", time, second, 31556926.0, 0.0);
		systemOfUnits.getUnit().add(year);
		
		
		//some temperature units
		//°C as affine conversion unit
		AffineConversionUnit degreeCelsius = createAffineConversionUnit("Degree Celsius", "°C", "degreeCelsiusUnit", "", temperature, kelvin, 1.0, 273.15);		
		systemOfUnits.getUnit().add(degreeCelsius);
		
		//°F as affine conversion unit
		final Double FIVE_DIV_NINE = 5.0 / 9.0;
		final Double HUNDREDSIXTY_DIV_NINE = -160.0 / 9.0;
		AffineConversionUnit degreeFahrenheit = createAffineConversionUnit("Degree Fahrenheit", "°F", "degreeFahrenheitUnit", "", temperature, degreeCelsius, FIVE_DIV_NINE, HUNDREDSIXTY_DIV_NINE);		
		systemOfUnits.getUnit().add(degreeFahrenheit);
		
		//some velocities
		// meter per second as Derived Unit with a link to the derived Quantity Kind velocity
		HashMap<AUnit, Double> unitFactorMap = new HashMap<AUnit, Double>();
		unitFactorMap.clear();
		unitFactorMap.put(meter, 1.0);
		unitFactorMap.put(second, M1);
		DerivedUnit meterPerSecond = createAndAddDerivedUnit("Meter Per Second", "m¹ s⁻¹", "meterPerSecondUnit", "", velocity, unitFactorMap);
		systemOfUnits.getUnit().add(meterPerSecond);
		
		//kilometer per hour as Derived Unit with a link to the derived Quantity Kind velocity
		unitFactorMap.clear();
		unitFactorMap.put(kilometer, 1.0);
		unitFactorMap.put(hour, M1);
		DerivedUnit kilometerPerHour = createAndAddDerivedUnit("Kilometer Per Hour", "km¹ h⁻¹", "kilometerPerHourUnit", "", velocity, unitFactorMap);
		systemOfUnits.getUnit().add(kilometerPerHour);
		
		//some areas as derived units with a link to the derived QK area
		//square kilometer
		unitFactorMap.clear();
		unitFactorMap.put(kilometer, 2.0);
		DerivedUnit squareKilometer = createAndAddDerivedUnit("Square Kilometer", "km²", "squareKilometerUnit", "", area, unitFactorMap);
		systemOfUnits.getUnit().add(squareKilometer);
		
		//square meter
		unitFactorMap.clear();
		unitFactorMap.put(meter, 2.0);
		DerivedUnit squareMeter = createAndAddDerivedUnit("Square Meter", "m²", "squareMeterUnit", "", area, unitFactorMap);
		systemOfUnits.getUnit().add(squareMeter);
		
		//square millimeter
		unitFactorMap.clear();
		unitFactorMap.put(millimeter, 2.0);
		DerivedUnit squareMilliMeter = createAndAddDerivedUnit("Square Millimeter", "mm²", "squareMillimeterUnit", "", area, unitFactorMap);
		systemOfUnits.getUnit().add(squareMilliMeter);
		
		//square centimeter
		unitFactorMap.clear();
		unitFactorMap.put(centimeter, 2.0);
		DerivedUnit squareCentimeter = createAndAddDerivedUnit("Square Centimeter", "cm²", "squareCentiMeterUnit", "", area, unitFactorMap);
		systemOfUnits.getUnit().add(squareCentimeter);

		//some volumes
		//cubic meter
		unitFactorMap.clear();
		unitFactorMap.put(meter, THREE);
		DerivedUnit cubicMeter = createAndAddDerivedUnit("Cubic Meter", "m³", "cubicMeterUnit", "", volume, unitFactorMap);
		systemOfUnits.getUnit().add(cubicMeter);
		
		//cubic centimeter
		unitFactorMap.clear();
		unitFactorMap.put(centimeter, THREE);
		DerivedUnit cubicCentimeter = createAndAddDerivedUnit("Cubic Centimeter", "cm³", "cubicCentimeterUnit", "", volume, unitFactorMap);
		systemOfUnits.getUnit().add(cubicCentimeter);
		
		//newton as derived unit 
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, 1.0);
		unitFactorMap.put(second, M2);
		DerivedUnit newton = createAndAddDerivedUnit("Newton", "N", "newtonUnit", "", force, unitFactorMap);
		systemOfUnits.getUnit().add(newton);
		
		//joule as derived unit
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, 2.0);
		unitFactorMap.put(second, M2);
		DerivedUnit joule = createAndAddDerivedUnit("Joule", "J", "jouleUnit", "", energyAndWork, unitFactorMap);
		systemOfUnits.getUnit().add(joule);
		
		//newtonmeter
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, 2.0);
		unitFactorMap.put(second, M2);
		DerivedUnit newtonMeter = createAndAddDerivedUnit("Newtonmeter", "Nm", "newtonMeterUnit", "", torque, unitFactorMap);
		systemOfUnits.getUnit().add(newtonMeter);
		
		//Hertz
		unitFactorMap.clear();
		unitFactorMap.put(second, M1);
		DerivedUnit hertz = createAndAddDerivedUnit("Hertz", "Hz", "hertzUnit", "", frequency, unitFactorMap);
		systemOfUnits.getUnit().add(hertz);
		
		//PerHour
		unitFactorMap.clear();
		unitFactorMap.put(hour, M1);
		DerivedUnit perHour = createAndAddDerivedUnit("Per Hour", "h⁻¹", "perHourUnit", "", frequency, unitFactorMap);
		systemOfUnits.getUnit().add(perHour);
		
		//meterPerSecondSquared
		unitFactorMap.clear();
		unitFactorMap.put(meter, 1.0);
		unitFactorMap.put(second, M2);
		DerivedUnit meterPerSecondSquared = createAndAddDerivedUnit("Meter Per Second Squared", "m s⁻²", "meterPerSecondSquaredUnit", "", acceleration, unitFactorMap);
		systemOfUnits.getUnit().add(meterPerSecondSquared);
		
		//kilogram per cubic meters
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, M3);
		DerivedUnit kilogramPerCubicMeter = createAndAddDerivedUnit("Kilogram Per Cubic Meter", "kg m⁻³", "kilogramPerCubicMeterUnit", "", density, unitFactorMap);
		systemOfUnits.getUnit().add(kilogramPerCubicMeter);
		
		//kilogram per meter squared
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, M2);
		DerivedUnit kilogramPerMeterSquared = createAndAddDerivedUnit("Kilogram Per Meter Squared", "kg m⁻²", "kilogramPerMeterSquaredUnit", "", massPerUnitArea, unitFactorMap);
		systemOfUnits.getUnit().add(kilogramPerMeterSquared);
		
		
		//power
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, 2.0);
		unitFactorMap.put(second, M3);
		DerivedUnit watt = createAndAddDerivedUnit("Watt", "W", "wattUnit", "", power, unitFactorMap);
		systemOfUnits.getUnit().add(watt);
		
		//volt
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, 2.0);
		unitFactorMap.put(second, M3);
		DerivedUnit volt = createAndAddDerivedUnit("Volt", "V", "voltUnit", "", electricPotentialDifference, unitFactorMap);
		systemOfUnits.getUnit().add(volt);
		
		//pascal
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, M1);
		unitFactorMap.put(second, M2);
		DerivedUnit pascal = createAndAddDerivedUnit("Pascal", "Pa", "pascalUnit", "", pressureAndStress, unitFactorMap);
		systemOfUnits.getUnit().add(pascal);
	
		//radian
		unitFactorMap.clear();
		unitFactorMap.put(noUnit, 1.0);
		DerivedUnit radian = createAndAddDerivedUnit(UNIT_NAME_RADIAN, "rad", "radianUnit", "", angle, unitFactorMap);
		systemOfUnits.getUnit().add(radian);
		
		//radianPerSecond
		unitFactorMap.clear();
		unitFactorMap.put(radian, 1.0);
		unitFactorMap.put(second, M1);
		DerivedUnit radianPerSecond = createAndAddDerivedUnit("RadianPerSecond", "rad s⁻¹", "radianPerSecondUnit", "", angularVelocity, unitFactorMap);
		systemOfUnits.getUnit().add(radianPerSecond);
		
		//degree
		AffineConversionUnit degree = createAffineConversionUnit(UNIT_NAME_DEGREE, "°", "degreeUnit", "", angle, radian, Math.PI / 180, 0.0);
		systemOfUnits.getUnit().add(degree);
		
		//degree per second
		unitFactorMap.clear();
		unitFactorMap.put(degree, 1.0);
		unitFactorMap.put(second, M1);
		DerivedUnit degreePerSecond = createAndAddDerivedUnit("DegreePerSecond", "° s⁻¹", "degreePerSecondUnit", "", angularVelocity, unitFactorMap);
		systemOfUnits.getUnit().add(degreePerSecond);
		
		//percent as prefixed unit of No Unit
		PrefixedUnit percent = createPrefixedUnit("Percent", "%", "percentUnit", "", dimensionlessQK, centi, noUnit);
		systemOfUnits.getUnit().add(percent);
		
		//kilogram meter squared as derived unit for moments of inertia
		unitFactorMap.clear();
		unitFactorMap.put(kilogram, 1.0);
		unitFactorMap.put(meter, 2.0);
		DerivedUnit kilogramMeterSquared = createAndAddDerivedUnit(UNIT_NAME_KILO_GRAMM_METER_2, "kg m²", "kilogramMeterSquaredUnit", "", momentsOfInertia, unitFactorMap);
		systemOfUnits.getUnit().add(kilogramMeterSquared);
	
		//data units: bits
		unitFactorMap.clear();
		unitFactorMap.put(noUnit, 1.0);
		DerivedUnit bit = createAndAddDerivedUnit("Bit", "bit", "bitUnit", "", dimensionlessQK, unitFactorMap);
		systemOfUnits.getUnit().add(bit);
		
		//data units: byte
		LinearConversionUnit byte_unit = createLinearConversionUnit("Byte", "byte", "byteUnit", "", dimensionlessQK, bit, 8.0);
		systemOfUnits.getUnit().add(byte_unit);
		
		//MegaByte
		PrefixedUnit megabyte = createPrefixedUnit("Megabyte", "MB", "megaByteUnit", "", dimensionlessQK, mega, byte_unit);
		systemOfUnits.getUnit().add(megabyte);
		
		//data rate: megabyte per second
		unitFactorMap.clear();
		unitFactorMap.put(second, M1);
		unitFactorMap.put(megabyte, 1.0);
		DerivedUnit megabytePerSecond = createAndAddDerivedUnit("Megabyte Per Second", "MB s⁻¹", "megabyterPerSecondUnit", "", dataRateQK, unitFactorMap);
		systemOfUnits.getUnit().add(megabytePerSecond);
		
		
		//Ampere hrs as electric charge
		unitFactorMap.clear();
		unitFactorMap.put(hour, 1.0);
		unitFactorMap.put(ampere, 1.0);
		DerivedUnit ampereHours = createAndAddDerivedUnit("Ampere hours", "Ah", "ampereHoursUnit", "", electricChargeQK, unitFactorMap);
		systemOfUnits.getUnit().add(ampereHours);
	}
	
	public static final String UNIT_NAME_DEGREE = "Degree";
	public static final String UNIT_NAME_RADIAN = "Radian";
	public static final String UNIT_NAME_METER = "Meter";
	public static final String UNIT_NAME_NO_UNIT = "No Unit";
	public static final String UNIT_NAME_KILO_GRAMM = "Kilogram";
	public static final String UNIT_NAME_KILO_GRAMM_METER_2 = "Kilogram Meter Squared";
	// CHECKSTYLE:ON
	
	/**
	 * this method converts the given value from the given Unit to its base unit.
	 * @param sourceUnit the source unit which will be converted in base units 
	 * @param value the value that should be converted into the base unit
	 * @return conversionValue the converted Value
	 */
	public double convertFromSourceUnitToBaseUnit(AUnit sourceUnit, double value) {
		double conversionValue = 1.0;
		if (sourceUnit instanceof SimpleUnit) {
			conversionValue = value;
		} else if (sourceUnit instanceof AffineConversionUnit) {
			AffineConversionUnit acu = (AffineConversionUnit) sourceUnit;
			
			conversionValue = value * acu.getFactor() + acu.getOffset();
			
			AUnit refUnit = acu.getReferenceUnit();
			conversionValue = convertFromSourceUnitToBaseUnit(refUnit, conversionValue);
		} else if (sourceUnit instanceof LinearConversionUnit) {
			LinearConversionUnit lcu = (LinearConversionUnit) sourceUnit;
			conversionValue = value * lcu.getFactor();
			AUnit refUnit = lcu.getReferenceUnit();
			conversionValue = convertFromSourceUnitToBaseUnit(refUnit, conversionValue);
		} else if (sourceUnit instanceof DerivedUnit) {
			DerivedUnit du = (DerivedUnit) sourceUnit;
			List<UnitFactor> listOfUnitfactors = du.getFactor();
			double subConValue; 
			for (UnitFactor uf : listOfUnitfactors) {
				//first get the conversion value recursively!
				subConValue = convertFromSourceUnitToBaseUnit(uf.getUnit(), 1.0);
				//apply the exponent
				subConValue = Math.pow(subConValue, uf.getExponent());
				conversionValue = conversionValue * subConValue;
			}
			conversionValue = conversionValue * value;
			
		} else if (sourceUnit instanceof PrefixedUnit) {
			PrefixedUnit pu = (PrefixedUnit) sourceUnit;
			conversionValue = pu.getPrefix().getFactor() * value;
			AUnit refUnit = pu.getReferenceUnit();
			conversionValue = convertFromSourceUnitToBaseUnit(refUnit, conversionValue);
			
		} else { // Unit was not set
			conversionValue = conversionValue * value;
		}
		
		return conversionValue;
	}

	/**
	 * this method is the inverse convert it converts the given value
	 * from its base unit to the given target unit.
	 * @param targetUnit the target unit in which you want to convert 
	 * @param value the value that should be converted into the target unit
	 * @return conversionValue the converted Value
	 */
	public double convertFromBaseUnitToTargetUnit(AUnit targetUnit, double value) {
		double conversionValue = 1.0;
		if (targetUnit instanceof SimpleUnit) {
			conversionValue = value;
		} else if (targetUnit instanceof AffineConversionUnit) {
			AffineConversionUnit acu = (AffineConversionUnit) targetUnit;
			AUnit refUnit = acu.getReferenceUnit();
			conversionValue = convertFromBaseUnitToTargetUnit(refUnit, value);
			conversionValue = (conversionValue - acu.getOffset()) / acu.getFactor();
		} else if (targetUnit instanceof LinearConversionUnit) {
			LinearConversionUnit lcu = (LinearConversionUnit) targetUnit;
			AUnit refUnit = lcu.getReferenceUnit();
			conversionValue = convertFromBaseUnitToTargetUnit(refUnit, value);
			conversionValue = conversionValue / lcu.getFactor();
		} else if (targetUnit instanceof DerivedUnit) {
			DerivedUnit du = (DerivedUnit) targetUnit;
			List<UnitFactor> listOfUnitfactors = du.getFactor();
			double subConValue; 
			for (UnitFactor uf : listOfUnitfactors) {
				//first get the conversion value recursively!
				subConValue = convertFromBaseUnitToTargetUnit(uf.getUnit(), 1.0);
				//apply the exponent
				subConValue = Math.pow(subConValue, uf.getExponent());
				conversionValue = conversionValue / subConValue;
			}
			conversionValue = value / conversionValue;
			
		} else if (targetUnit instanceof PrefixedUnit) {
			PrefixedUnit pu = (PrefixedUnit) targetUnit;
			AUnit refUnit = pu.getReferenceUnit();
			conversionValue = 1 / pu.getPrefix().getFactor() * value;
			conversionValue = convertFromBaseUnitToTargetUnit(refUnit, conversionValue);
		} else { // Unit was not set
			conversionValue = value / conversionValue;
		}
		
		return conversionValue;
	}
	
	/**
	 * retrieves a unit by name
	 * @param systemOfUnits the systemOfUnits in which you want to look for 
	 * @param name the name of the unit as a String
	 * @return the unit which was found or null
	 */
	public AUnit getUnitByName(SystemOfUnits systemOfUnits, String name) {
		if (systemOfUnits == null) {
			return null;
		}
		
		List<AUnit> units = systemOfUnits.getUnit();
		for (AUnit unit : units) {
			if (unit.getName().equals(name)) {
				return unit;
			}
		}
		return null;
	}
	
	/**
	 * retrieves a QuantityKind by name
	 * @param systemOfQuantities the systemOfQuantities in which you want to look for 
	 * @param name the name of the QuantityKind as a String
	 * @return the QuantityKind which was found or null
	 */
	public AQuantityKind getQuantityKindByName(SystemOfQuantities systemOfQuantities, String name) {
		if (systemOfQuantities == null) {
			return null;
		}
		
		List<AQuantityKind> quantityKinds = systemOfQuantities.getQuantityKind();
		for (AQuantityKind qk : quantityKinds) {
			if (qk.getName().equals(name)) {
				return qk;
			}
		}
		return null;
	}
	
	/**
	 * Retrieves a quantity kind by the base quantities
	 * @param systemOfQuantities the systemOfQuantities in which you want to look for 
	 * @param baseQuantityKinds the base quantity kinds
	 * @return the derived quantity kind or null of no matching quantity kind could be found
	 */
	public List<AQuantityKind> getQuantityKindByBaseKinds(SystemOfQuantities systemOfQuantities, Map<AQuantityKind, Double> baseQuantityKinds) {
		if (systemOfQuantities == null) {
			return null;
		}
		
		List<AQuantityKind> quantityKinds = systemOfQuantities.getQuantityKind();
		List<AQuantityKind> derivedQuantityKinds = new ArrayList<>();
		for (AQuantityKind qk : quantityKinds) {
			Map<AQuantityKind, Double> baseQuantityKindsQk = getBaseQuantityKinds(qk);
			if (haveSameQuantityKind(baseQuantityKinds, baseQuantityKindsQk)) {
				derivedQuantityKinds.add(qk);
			}
		}
		
		return derivedQuantityKinds;
	}
	
	/**
	 * converts the given AUnit and double value to a specific targetUnit
	 * @param sourceUnit the source unit 
	 * @param sourceValue double defining the source value
	 * @param targetUnit the target unit in which you want your unit to be converted in
	 * @return the converted value
	 */
	public double convertFromSourceToTargetUnit(AUnit sourceUnit, double sourceValue, AUnit targetUnit) {
		double convertedValue = 0.0;
		if (!haveSameQuantityKind(sourceUnit, targetUnit)) {
			//conversion is not possible so we return the unconverted value!
			//pipe a message to the Logger!
			return sourceValue;
		}
		
		//we convert the source Unit to the basUnits and then to the given target unit
		convertedValue = convertFromSourceUnitToBaseUnit(sourceUnit, sourceValue);
		convertedValue = convertFromBaseUnitToTargetUnit(targetUnit, convertedValue);
		
		return convertedValue;
	}
	
	/**
	 * this method creates a set of standard elements in the SystemOfUnits like Prefixes,
	 * QuantityKinds and Units. Be aware that any existing elements in the SystemOfUnits are erased. 
	 * @param systemOfUnits the system of Units 
	 */
	public void createQudvStandardLibrary(SystemOfUnits systemOfUnits) {
		//SystemOfQuantities systemOfQuantities = systemOfUnits.getSystemOfQuantities().get(0);
		//flush everything in the system of units!
		systemOfUnits.getUnit().clear();
		systemOfUnits.getPrefix().clear();
		systemOfUnits.getSystemOfQuantities().get(0).getQuantityKind().clear();
		
		createLibraryOfUnitsAndQuantityKinds(systemOfUnits);
	}
	
	/**
	 * 
	 * @param name the name of the prefix
	 * @param symbol the symbol of the prefix
	 * @param factor the conversion factor associated to the prefix
	 * @return {@link Prefix} the prefix which was being created
	 */
	private Prefix createPrefix(String name, String symbol, Double factor) {

		Prefix prefix = QudvFactory.eINSTANCE.createPrefix();
		prefix.setName(name);
		prefix.setSymbol(symbol);
		prefix.setFactor(factor);

		return prefix;
	}
	
	// --------------------------------------------------------------------------
	private static final Map<String, Object>	WRITE_OPTIONS	= new HashMap<String, Object>();
	private static final Map<String, Object>	READ_OPTIONS	= new HashMap<String, Object>();

	static {
		WRITE_OPTIONS.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE); // Write default data to file
		// WRITE_OPTIONS.put(XMLResource.OPTION_SAVE_ONLY_IF_CHANGED, Boolean.TRUE);
		// WRITE_OPTIONS.put(XMLResource.OPTION_FORMATTED, Boolean.TRUE);
		// WRITE_OPTIONS.put(XMLResource.OPTION_LINE_WIDTH, 1);
		WRITE_OPTIONS.put(XMLResource.NO_NAMESPACE_SCHEMA_LOCATION, Boolean.TRUE);
		// WRITE_OPTIONS.put(XMLResource.OPTION_ZIP, Boolean.TRUE);
		READ_OPTIONS.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
	}

	/**
	 * a method to export the unit model to a file
	 * @param systemOfUnits the system of units to export
	 * @param destination a destination on the file system
	 * @throws IOException the exception which can be caused
	 */
	public void exportModeltoFile(SystemOfUnits systemOfUnits, String destination) throws IOException {
		Resource resource = new XMLResourceImpl();
		resource.getContents().add(systemOfUnits);
		
		try (FileOutputStream fileOut = new FileOutputStream(destination)) {
			resource.save(fileOut, WRITE_OPTIONS);
		} 
	}
	
	/**
	 * a method to import a system of units 
	 * @param destination the destination of the file to import
	 * @return {@link SystemOfUnits} the imported SystemOfUnits
	 * @throws IOException 
	 */
	public SystemOfUnits importModelFromFile(String destination) throws IOException {
		Resource resource = new XMLResourceImpl();
		
		try (FileInputStream fileIn = new FileInputStream(destination)) {
			resource.load(fileIn, READ_OPTIONS);
		} 
		
		EObject root = resource.getContents().get(0);
		
		if (root instanceof SystemOfUnits) {
			return (SystemOfUnits) root;
		}
		return null;
		
	}
	
	/**
	 * Copies a SystemOfUnits and all its descendants. It is based on the Ecore.util
	 * and is also taking care of any cross references.
	 * @param sourceSystemOfUnits to copy including its descendants and references
	 * @return the copied SystemOfUnits
	 */
	public SystemOfUnits copySystemOfUnits(SystemOfUnits sourceSystemOfUnits) {
		EcoreUtil.Copier copier = new EcoreUtil.Copier(false, true) {

			private static final long serialVersionUID = 1L;

			@Override
			public void copyReferences() {
				super.copyReferences();
			}
			
			@Override
			protected EObject createCopy(EObject object) {
				EObject result = super.createCopy(object);

				// All Items with an ID will be updated to a new random UUID
				// as soon as they are copied.
				if (result instanceof SystemOfUnits) {
					((SystemOfUnits) result).setUuid(new VirSatUuid());
				} else if (result instanceof AUnit) {
					((AUnit) result).setUuid(new VirSatUuid());
				} else if (result instanceof AQuantityKind) {
					((AQuantityKind) result).setUuid(new VirSatUuid());
				} else if (result instanceof SystemOfQuantities) {
					((SystemOfQuantities) result).setUuid(new VirSatUuid());
				}
				return result;
			}

			@Override
			protected void copyAttribute(EAttribute attribute, EObject object, EObject copyEObject) {
				// holy quakomolee
				// We do not copy the attribute ID because it has been already updated
				// IDs have to be unique !!!
				if (attribute == GeneralPackage.Literals.IUUID__UUID) {
					return;
					// } else if (attribute == QudvPackage.Literals.AUNIT__ID) {
					// return;
					// } else if (attribute == QudvPackage.Literals.AQUANTITY_KIND__ID) {
					// return;
					// } else if (attribute == QudvPackage.Literals.SYSTEM_OF_QUANTITIES__ID) {
					// return;
				}
				super.copyAttribute(attribute, object, copyEObject);
			}

		};

		SystemOfUnits copiedSoU = (SystemOfUnits) copier.copy(sourceSystemOfUnits);
		copier.copyReferences();

		return copiedSoU;
	}

	/**
	 * this method checks if the given unit is dimensionless
	 * 
	 * @param unit the first unit to check against the second
	 * @return true if the QuantityKind is dimensionless
	 */
	public boolean isDimensionless(AUnit unit) {
		AQuantityKind unitQK = unit.getQuantityKind(); 
		if (unitQK == null) {
			return false;
		}
		
		if (unitQK.getName().equals(DIMENSIONLESS_QK_NAME)) {
			return true;
		}
		
		return false;
	}

	/**
	 * this method checks if two units have the same QuantityKind
	 * 
	 * @param unit1 the first unit to check against the second
	 * @param unit2 the second unit to check against the first
	 * @return true if the QuantityKinds are the same and false otherwise
	 */
	public boolean haveSameQuantityKind(AUnit unit1, AUnit unit2) {
		//check for Null Pointers and eliminate them by returning false directly
		AQuantityKind unit1QK = unit1.getQuantityKind();
		AQuantityKind unit2QK = unit2.getQuantityKind(); 
		
		if (unit1QK == null || unit2QK == null) {
			return false;
		}

		Map<AQuantityKind, Double> outputQKbaseMap = getBaseQuantityKinds(unit2QK);
		Map<AQuantityKind, Double> inputQKbaseMap = getBaseQuantityKinds(unit1QK);
	
		//now the only thing left to do is comparing the input and output map
		boolean flag = haveSameQuantityKind(inputQKbaseMap, outputQKbaseMap);
		return flag;
	}
	
	/**
	 * this method check if two maps are equal in terms of key and value
	 * this is a separate method from Map.equals because we need to relax the equality check on the value part of the Map
	 * here we can have precision issues due to the Double data type in value
	 * @param map1 the first map for the check
	 * @param map2 the second map for the check
	 * @return true if the maps are the same; false otherwise
	 */
	public boolean haveSameQuantityKind(Map<AQuantityKind, Double> map1, Map<AQuantityKind, Double> map2) {
		boolean areSame = false;
		
		if (map1.size() != map2.size()) {
			//if on map is empty we have to check for the special case of dimensionless
			if (map1.isEmpty()) {
				areSame = map2.keySet().stream().anyMatch(qk -> qk.getName().equals(DIMENSIONLESS_QK_NAME));
			} else if (map2.isEmpty()) {
				areSame = map1.keySet().stream().anyMatch(qk -> qk.getName().equals(DIMENSIONLESS_QK_NAME));
			}
			return areSame;
		
		} else { 
			areSame = true;
			for (Entry<AQuantityKind, Double> entry1: map1.entrySet()) {
				//maybe I need to add my precision comparison here!
				if (!entry1.getValue().equals(map2.get(entry1.getKey()))) {
					areSame = false;
					break;
				}
			}
			return areSame;
		}
	}
	
	/**
	 * this method recursively look at the given QuantityKind and finds the base quantity kinds
	 * @param qk the QuantityKind of which you want to know the base quantity kind
	 * @return a HashMap with base QuantityKinds
	 */
	public Map<AQuantityKind, Double> getBaseQuantityKinds(AQuantityKind qk) {
		Map<AQuantityKind, Double> myMap = new HashMap<AQuantityKind, Double>();
		
		if (qk instanceof DerivedQuantityKind) {
			DerivedQuantityKind dqk = (DerivedQuantityKind) qk;
			
			List<QuantityKindFactor> qkFactors = dqk.getFactor();

			for (QuantityKindFactor qkFactor : qkFactors) {
				AQuantityKind currentQK = qkFactor.getQuantityKind();
				if (currentQK instanceof DerivedQuantityKind) {
					DerivedQuantityKind currentDQK = (DerivedQuantityKind) currentQK;
					Map<AQuantityKind, Double> subMap = getBaseQuantityKinds(currentDQK);
					
					//before we can merge the maps we need to apply the exponent of the current QK
					Double calcExponent;
					for (Entry<AQuantityKind, Double> entry : subMap.entrySet()) {
						calcExponent = qkFactor.getExponent() * entry.getValue(); 
						subMap.put(entry.getKey(), calcExponent);
					}
					
					myMap = mergeMaps(myMap, subMap, QudvCalcMethod.ADD);
				} else { //Simple Quantity Kind
					myMap.put(currentQK, qkFactor.getExponent());
				}
			}
		} else { //assuming its a simple quantity Kind
			myMap.put(qk, 1.0);
		}
		return myMap;
	}

	
	
	/**
	 * this method merges two maps of quantity kinds considering the exponent (Double) value
	 * @param map1 the first map to merge
	 * @param map2 the second map to merge
	 * @param calcMethod QudvCalcMethod defines if exponents should be added or subtracted
	 * @return the merged Map of QuantityKinds with matching exponents
	 */
	public Map<AQuantityKind, Double> mergeMaps(Map<AQuantityKind, Double> map1, Map<AQuantityKind, Double> map2, QudvCalcMethod calcMethod) {
		// Merging a map with the undefined QK with any other map also yields a map containing only the undefined QK
		if (map1.get(getUndefinedQK()) != null || map2.get(getUndefinedQK()) != null) {
			return createUndefinedQKMap();
		}
		
		HashMap<AQuantityKind, Double> merged = new HashMap<AQuantityKind, Double>();
		int calcSign = calcMethod.getCalcSign();
		
		//include elements from the first map and add or subtract values of the second map if they exist
		for (Entry<AQuantityKind, Double> entry1 : map1.entrySet()) {
			AQuantityKind qk = entry1.getKey(); 
			Double x = entry1.getValue();
			Double y = map2.getOrDefault(qk, 0d);
			merged.put(qk, x + calcSign * y);
		}

		//include elements from the second map
		for (Entry<AQuantityKind, Double> entry2 : map2.entrySet()) {
			AQuantityKind qk = entry2.getKey();
			Double x = entry2.getValue();
			merged.putIfAbsent(qk, calcSign * x);
		}
		
		//remove elements which are zero in their Double value: gekuerzt!
		merged.entrySet().removeIf(entry -> Math.abs(entry.getValue()) < ERROR);
		
		//remove elements which are dimensionless, because they don't have any influence
		merged.keySet().removeIf(qk -> qk.getName().equals(DIMENSIONLESS_QK_NAME));
		
		return merged;
	}
	
	public static final String UNDEFINED_QK_NAME = "Undefined"; 
	private AQuantityKind undefinedQuantityKind;
	
	/**
	 * Creates a map containing the undefined QK only
	 * @return a map containing the undefined QK only
	 */
	public Map<AQuantityKind, Double> createUndefinedQKMap() {
		Map<AQuantityKind, Double> undefinedQKMap = new HashMap<>();
		undefinedQKMap.put(getUndefinedQK(), 1d);
		return undefinedQKMap;
	}
	
	/**
	 * Gets the QK representing the Undefined Quantity Kind.
	 * Intended to be used for operations on incompatible quantity kinds.
	 * Example: Performing an addition where the summands have differing quantity kinds.
	 * @return the undefined QK
	 */
	public AQuantityKind getUndefinedQK() {
		if (undefinedQuantityKind == null) {
			undefinedQuantityKind = createSimpleQuantityKind(UNDEFINED_QK_NAME, "UNDEFINED", "undefinedQK", "");
		}
		
		return undefinedQuantityKind;
	}
	
	/**
	 * Converts a map of quantity kinds and their factors to a string representation
	 * @param quantityKinds a map of quantity kinds and their factors
	 * @return the converted string
	 */
	public String convertToString(Map<AQuantityKind, Double> quantityKinds) {
		StringBuilder result = new StringBuilder();
		Iterator<Entry<AQuantityKind, Double>> iterator = quantityKinds.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<AQuantityKind, Double> m = (Map.Entry<AQuantityKind, Double>) iterator.next();
			AQuantityKind currentUnit = m.getKey();
			result.append(currentUnit.getSymbol() + convertFactorToUnicodeSymbol(m.getValue()));
		}
		return result.toString();
	}

	public static final String SUPERSCRIPT_MINUS = "\u207b";
	public static final String SUPERSCRIPT_ZERO = "\u2070";
	public static final String SUPERSCRIPT_ONE = "\u00b9";
	public static final String SUPERSCRIPT_TWO = "\u00b2";
	public static final String SUPERSCRIPT_THREE = "\u00b3";
	public static final String SUPERSCRIPT_FOUR = "\u2074";
	public static final String SUPERSCRIPT_FIVE = "\u2075";
	public static final String SUPERSCRIPT_SIX = "\u2076";
	public static final String SUPERSCRIPT_SEVEN = "\u2077";
	public static final String SUPERSCRIPT_EIGHT = "\u2078";
	public static final String SUPERSCRIPT_NINE = "\u2079";

	/**
	 * A method to retrieve the unicode escape statement of a quantity kind factor
	 * Example: Given a factor 2 this method returns ².
	 * 
	 * In the case of non-integers this method will just return ^non-integer-number,
	 * as there is no unicode symbol for a superscrip "."
	 * 
	 * @param f the quantity kind factor
	 * @return the unicode statement as String
	 */
	public String convertFactorToUnicodeSymbol(Double f) {
		String str = "";

		final double epsilon = 1E-5;
		int intValue = f.intValue();

		// Check if we need to care about the decimal point
		if (Math.abs(f - intValue) < epsilon) {
			if (intValue != 1) {
				// Only show a factor if its not ^1
				str = String.valueOf(intValue);
				str = str.replaceAll("-", SUPERSCRIPT_MINUS);
				str = str.replaceAll("0", SUPERSCRIPT_ZERO);
				str = str.replaceAll("1", SUPERSCRIPT_ONE);
				str = str.replaceAll("2", SUPERSCRIPT_TWO);
				str = str.replaceAll("3", SUPERSCRIPT_THREE);
				str = str.replaceAll("4", SUPERSCRIPT_FOUR);
				str = str.replaceAll("5", SUPERSCRIPT_FIVE);
				str = str.replaceAll("6", SUPERSCRIPT_SIX);
				str = str.replaceAll("7", SUPERSCRIPT_SEVEN);
				str = str.replaceAll("8", SUPERSCRIPT_EIGHT);
				str = str.replaceAll("9", SUPERSCRIPT_NINE); 
			}
		} else {
			str = "^" + String.valueOf(f);
		}

		return str + " ";
	}
	
	/**
	 * little embedded enumeration class to define the exponent calculation method, ADD or SUBTRACT
	 * @author scha_vo
	 *
	 */
	public enum QudvCalcMethod {
		ADD, SUBTRACT;
		
		/**
		 * Gets the sign for the calculation method.
		 * @return 1 if ADD, -1 if SUBTRACT
		 */
		public int getCalcSign() {
			switch (this) {
				case ADD:
					return 1;
				case SUBTRACT:
					return -1;
				default:
					throw new RuntimeException("Unsupported qudv calculation sign: " + this);
			}
		}
	}
}