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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.dlr.sc.virsat.model.dvlm.qudv.AConversionBasedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.AQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.AUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedQuantityKind;
import de.dlr.sc.virsat.model.dvlm.qudv.DerivedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.Prefix;
import de.dlr.sc.virsat.model.dvlm.qudv.PrefixedUnit;
import de.dlr.sc.virsat.model.dvlm.qudv.QuantityKindFactor;
import de.dlr.sc.virsat.model.dvlm.qudv.QudvPackage;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfQuantities;
import de.dlr.sc.virsat.model.dvlm.qudv.SystemOfUnits;
import de.dlr.sc.virsat.model.dvlm.qudv.UnitFactor;
import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.model.dvlm.general.GeneralPackage;

/**
 * the command creation / controller class through which all commands should be executed which alter the QUDV library
 * this is more like a factory now for commands; the Editing domain needs to be given by the current project 
 * @author scha_vo
 *
 */
public class QudvModelCommandFactory {

	/**
	 * public constructor setting the Editing Domain
	 * @param ed the EditingDomain on which the commands will be created
	 */
	public QudvModelCommandFactory(EditingDomain ed) {		
		this.ed = ed;
	}
	
	private EditingDomain ed;
	
	/**
	 * Controller method to add a SimpleUnit of the QUDV data model to the UnitManagement of a Study
	 * @param um The UnitManagement to which to add the new SimpleUnit
	 * @param simpleUnit The SimpleUnit to add to the UnitManagement
	 * @return the command
	 */
	public Command addSimpleUnit(UnitManagement um, AUnit simpleUnit) { 

		SystemOfUnits systemOfUnits = um.getSystemOfUnit();
		Command cmd = AddCommand.create(ed, systemOfUnits, QudvPackage.eINSTANCE.getSystemOfUnits_Unit(), simpleUnit);
		return cmd;
		
	}
	
	/**
	 * Controller method to set the attributes of a SimpleUnit of the QUDV data model
	 * @param unit the unit to set
	 * @param name the name of the unit
	 * @param symbol the symbol of the unit
	 * @param description the description of the unit
	 * @param definitionURI the namespaceURI of the unit
	 * @param quantityKind the quantity kind associated to the unit
	 * @return the command
	 */
	public Command setSimpleUnit(AUnit unit, String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind) { 
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, unit, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
		Command setQuantityKindCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_QuantityKind(), quantityKind);
		cmpCmd.append(setQuantityKindCommand);
		
		return cmpCmd;
		
	}

	/**
	 * Controller method to add a PrefixedUnit of the QUDV data model to the UnitManagement of a Study
	 * @param um The UnitManagement to which to add the new SimpleUnit
	 * @param unit The PrefixedUnit to add to the UnitManagement
	 * @param prefix The prefix that will be referenced
	 * @param baseUnit the baseUnit of the prefixedUnit which will be added
	 * @return the command
	 */
	public Command addPrefixedUnit(UnitManagement um, PrefixedUnit unit, Prefix prefix, AUnit baseUnit) {

		SystemOfUnits systemOfUnits = um.getSystemOfUnit();
		CompoundCommand cmpCmd = new CompoundCommand();
				
		Command addUnitCommand = AddCommand.create(ed, systemOfUnits, QudvPackage.eINSTANCE.getSystemOfUnits_Unit(), unit);
		cmpCmd.append(addUnitCommand);
		Command setPrefixRefCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getPrefixedUnit_Prefix(), prefix);
		cmpCmd.append(setPrefixRefCommand);
		Command setBaseUnitCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAConversionBasedUnit_ReferenceUnit(), baseUnit);
		cmpCmd.append(setBaseUnitCommand);
		
		return cmpCmd;
		
	}
	
	/**
	 * Controller method to set a PrefixedUnit of the QUDV data model to the UnitManagement of a Study
	 * @param unit the unit to set the values for
	 * @param name the name of the unit
	 * @param symbol the symbol of the unit
	 * @param description the description of the unit
	 * @param definitionURI the namespace URI 
	 * @param quantityKind the associated quantity kind of this unit
	 * @param prefix the prefix
	 * @param baseUnit the referenced base for the linear conversion unit to be set
	 * @return the command
	 */
	
	public Command setPrefixedUnit(PrefixedUnit unit, String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, Prefix prefix, AUnit baseUnit) {
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, unit, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
		Command setQuantityKindCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_QuantityKind(), quantityKind);
		cmpCmd.append(setQuantityKindCommand);
		
		Command setPrefixRefCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getPrefixedUnit_Prefix(), prefix);
		cmpCmd.append(setPrefixRefCommand);
		Command setBaseUnitCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAConversionBasedUnit_ReferenceUnit(), baseUnit);
		cmpCmd.append(setBaseUnitCommand);
		
		return cmpCmd;
	}
	
	/**
	 * Controller method to add a ConversionBasedUnit of the QUDV data model to the UnitManagement of a Study
	 * @param um The UnitManagement to which to add the new SimpleUnit
	 * @param unit The conversionBasedUnit to add to the UnitManagement
	 * @param refUnit The unit that will be referenced, acts as base unit for the conversion
	 * @return the command
	 */
	public Command addConversionBasedUnit(UnitManagement um, AConversionBasedUnit unit, AUnit refUnit) {
	
		SystemOfUnits systemOfUnits = um.getSystemOfUnit();
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command addUnitCommand = AddCommand.create(ed, systemOfUnits, QudvPackage.eINSTANCE.getSystemOfUnits_Unit(), unit);
		cmpCmd.append(addUnitCommand);
		Command setRefUnitCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAConversionBasedUnit_ReferenceUnit(), refUnit);
		cmpCmd.append(setRefUnitCommand);
		
		return cmpCmd;
	}

	/**
	 * Controller method to add a PrefixedUnit of the QUDV data model to the UnitManagement of a Study
	 * @param unit the unit management which contains the unit to be modified
	 * @param name the name of the unit
	 * @param symbol the symbol of the unit
	 * @param description the description of the unit
	 * @param definitionURI the namespace URI 
	 * @param quantityKind the associated quantity kind of this unit
	 * @param factor the conversion factor to the base unit 
	 * @param baseUnit the referenced base for the linear conversion unit to be set
	 * @return the command 
	 */
	public Command setLinearConversionUnit(AConversionBasedUnit unit, String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, double factor, AUnit baseUnit) {
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, unit, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
		Command setQuantityKindCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_QuantityKind(), quantityKind);
		cmpCmd.append(setQuantityKindCommand);
		
		Command setFactorCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getLinearConversionUnit_Factor(), factor);
		cmpCmd.append(setFactorCommand);
		Command setBaseUnitCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAConversionBasedUnit_ReferenceUnit(), baseUnit);
		cmpCmd.append(setBaseUnitCommand);
		
		return cmpCmd;	
	}
	
	/**
	 * Controller method to add a PrefixedUnit of the QUDV data model to the UnitManagement of a Study
	 * @param unit the unit to set the information for
	 * @param name the name of the unit
	 * @param symbol the smbol of the unit
	 * @param description the description of the unit
	 * @param definitionURI the namespace URI of the unit
	 * @param quantityKind the associated qunatity kind
	 * @param factor the factor for the affine conversion
	 * @param offset the offset for the affine conversion
	 * @param baseUnit the base unit reference
	 * @return the command
	 */
	public Command setAffineConversionUnit(AConversionBasedUnit unit, String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, double factor, double offset, AUnit baseUnit) {
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, unit, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
		Command setQuantityKindCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_QuantityKind(), quantityKind);
		cmpCmd.append(setQuantityKindCommand);
		
		Command setFactorCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAffineConversionUnit_Factor(), factor);
		cmpCmd.append(setFactorCommand);
		Command setOffsetCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAffineConversionUnit_Offset(), offset);
		cmpCmd.append(setOffsetCommand);
		Command setBaseUnitCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAConversionBasedUnit_ReferenceUnit(), baseUnit);
		cmpCmd.append(setBaseUnitCommand);
		
		return cmpCmd;
		
	}
	
	/**
	 * Controller method to add a DerivedUnit of the QUDV data model to the UnitManagement of a Study
	 * this method also creates all necessary UnitFactors associated with the derived unit
	 * @param um The UnitManagement to which to add the new SimpleUnit
	 * @param unit The conversionBasedUnit to add to the UnitManagement
	 * @param unitFactorMap The unit that will be referenced, acts as base unit for the conversion
	 * @return the command
	 */
	public Command addDerivedUnit(UnitManagement um, DerivedUnit unit, HashMap<AUnit, Double> unitFactorMap) {

		SystemOfUnits systemOfUnits = um.getSystemOfUnit();
		CompoundCommand cmpCmd = new CompoundCommand();
		
		//add the derived unit to the list of units in the unit management
		Command addUnitCommand = AddCommand.create(ed, systemOfUnits, QudvPackage.eINSTANCE.getSystemOfUnits_Unit(), unit);
		cmpCmd.append(addUnitCommand);
		
		//create all necessary Unit Factors
        //get the iterator for the map, loop through all elements of the map and create the unit factors; 
		//then create the necessary add command
        Iterator<Entry<AUnit, Double>> iterator = unitFactorMap.entrySet().iterator();
        
        while (iterator.hasNext()) {
        	Map.Entry<AUnit, Double> m = (Map.Entry<AUnit, Double>) iterator.next();
        	UnitFactor unitFactor = QudvUnitHelper.getInstance().createUnitFactor(m.getValue(), m.getKey());
        	Command addUnitFactorCommand = AddCommand.create(ed, unit, QudvPackage.eINSTANCE.getDerivedUnit_Factor(), unitFactor);
        	cmpCmd.append(addUnitFactorCommand);        	
        }
        return cmpCmd;
	}
	
	/**
	 * Controller method to add a DerivedUnit of the QUDV data model to the UnitManagement of a Study
	 * this method also creates all necessary UnitFactors associated with the derived unit
	 * @param unit The conversionBasedUnit to add to the UnitManagement
	 * @param name the name of the unit
	 * @param symbol the symbol of the unit
	 * @param description the description of the unit
	 * @param definitionURI the namespace URI of this unit
	 * @param quantityKind the associated quantity kind
	 * @param unitFactorMap the map of factors to define the conversions
	 * @return the command
	 */
	public Command setDerivedUnit(DerivedUnit unit, String name, String symbol, String description, String definitionURI, AQuantityKind quantityKind, HashMap<AUnit, Double> unitFactorMap) {
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, unit, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
		Command setQuantityKindCommand = SetCommand.create(ed, unit, QudvPackage.eINSTANCE.getAUnit_QuantityKind(), quantityKind);
		cmpCmd.append(setQuantityKindCommand);
		
		//This remove and add procedure here is not very nice;
		//in fact, we should really check if something has changed in the List of unitFactors and then update, remove, and add only the according UnitFactors
		
		//remove the unit factors
		Command removeCommand = RemoveCommand.create(ed, unit, QudvPackage.eINSTANCE.getDerivedUnit_Factor(), unit.getFactor());
		cmpCmd.append(removeCommand);
		
		//create all new Unit Factors
        //get the iterator for the map, loop through all elements of the map and create the unit factors; 
		//then create the necessary add command
        Iterator<Entry<AUnit, Double>> iterator = unitFactorMap.entrySet().iterator();
        
        while (iterator.hasNext()) {
        	Map.Entry<AUnit, Double> m = (Map.Entry<AUnit, Double>) iterator.next();
        	UnitFactor unitFactor = QudvUnitHelper.getInstance().createUnitFactor(m.getValue(), m.getKey());
        	Command addUnitFactorCommand = AddCommand.create(ed, unit, QudvPackage.eINSTANCE.getDerivedUnit_Factor(), unitFactor);
        	cmpCmd.append(addUnitFactorCommand);        	
        }
		
        return cmpCmd;
	}
	
	/**
	 * This method removes units from a UnitManagement
	 * @param um The UnitManagement from which the Units will be removed
	 * @param units The List of Units that will be removed
	 * @return the command
	 */
	public Command removeUnit(UnitManagement um, List<AUnit> units) {
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		for (AUnit unit : units) {
			cmpCmd.append(createRemUnit(unit, um));	
		}
		
		return cmpCmd;
	};	
	
	/**
	 * This method creates command to remove a Unit from the UnitManagement
	 * @param unit The Unit which has to be removed from the Unit-Management
	 * @param um The Unit-Management from which the Unit will be removed from
	 * @return The command that will remove the Unit from the UnitManagement
	 */
	public Command createRemUnit(AUnit unit, UnitManagement um) {
		SystemOfUnits systemOfUnits = um.getSystemOfUnit();
		Command cmd = RemoveCommand.create(ed, systemOfUnits, QudvPackage.eINSTANCE.getSystemOfUnits_Unit(), unit);
		return cmd;
	}
	
	
	/**
	 * This method creates a list of Units which contains no prefixed Units
	 * @param systemOfUnits The systemOfUnits you want get the list from
	 * @return The List of Units except PrefixedUnits
	 */
	public static List<AUnit> getListOfNonPrefixedUnits(SystemOfUnits systemOfUnits) {
		List<AUnit> listOfUnits = new ArrayList<AUnit>(systemOfUnits.getUnit());
		List<AUnit> prefixedUnits = new ArrayList<AUnit>();
		for (AUnit unit : listOfUnits) {
			if (unit instanceof PrefixedUnit) {
				prefixedUnits.add(unit);
			}
		}
		listOfUnits.removeAll(prefixedUnits);
		return listOfUnits;
	}
	
	/**
	 * This method gives a list of QuantityKinds 
	 * @param systemOfUnits The systemOfUnits you want get the list from
	 * @return The List of QuantityKinds
	 */	
	public static List<AQuantityKind> getListOfQuantities(SystemOfUnits systemOfUnits) {
		List<AQuantityKind> listOfQuantityKinds = new ArrayList<AQuantityKind>(systemOfUnits.getSystemOfQuantities().get(0).getQuantityKind());
		return listOfQuantityKinds;
	}

	
	/**
	 * This method removes quantityKinds from a UnitManagement
	 * @param um The UnitManagement from which the QuantityKinds will be removed
	 * @param quantityKinds The List of QuantityKinds that will be removed
	 * @return the command
	 */
	public Command removeQuantityKind(UnitManagement um, List<AQuantityKind> quantityKinds) {
		CompoundCommand cmpCmd = new CompoundCommand();

		for (AQuantityKind quantityKind :  quantityKinds) {
			cmpCmd.append(createRemQuantityKind(quantityKind, um));	
		}
		return cmpCmd;
		
	}
	
	/**
	 * This method creates command to remove a quantityKind from the UnitManagement
	 * @param quantityKind The quantityKind which has to be removed from the Unit-Management
	 * @param um The Unit-Management from which the Unit will be removed from
	 * @return The command that will remove the quantityKinds from the UnitManagement
	 */
	public Command createRemQuantityKind(AQuantityKind quantityKind, UnitManagement um) {
		// getting the first object of the list secretly implies that we only have one systemOfQuantities
		// we should consider a change in the data model
		SystemOfQuantities systemOfQuantities = um.getSystemOfUnit().getSystemOfQuantities().get(0);
		Command cmd = RemoveCommand.create(ed, systemOfQuantities, QudvPackage.eINSTANCE.getSystemOfQuantities_QuantityKind(), quantityKind);
		// if this QuantityKind is removed now I can still have units referencing to it, I should clean them.
		// Or at least remove the references and show a marker in the table that indicates they need repair!
		
		return cmd;
	}
	
	/**
	 * Controller method to add a SimpleQuantityKind of the QUDV data model to the UnitManagement of a Study
	 * @param um The UnitManagement to which to add the new SimpleQuantityKind
	 * @param simpleQuantityKind The SimpleQuantityKind to add to the UnitManagement
	 * @return the command
	 */
	public Command addSimpleQuantityKind(UnitManagement um, AQuantityKind simpleQuantityKind) { 
		SystemOfQuantities systemOfQuantities = um.getSystemOfUnit().getSystemOfQuantities().get(0);
		Command cmd = AddCommand.create(ed, systemOfQuantities, QudvPackage.eINSTANCE.getSystemOfQuantities_QuantityKind(), simpleQuantityKind);
		return cmd;
	}
	
	/**
	 * Controller method to set the attributes of a SimpleQuantityKind of the QUDV data model
	 * @param simpleQuantityKind the simple quantity kind of which the attributes will be changed
	 * @param name the name of the quantity kind
	 * @param symbol the symbol of the quantity kind
	 * @param description the description of the quantity kind
	 * @param definitionURI the namespace URI of the quantity kind
	 * @return the command
	 */
	public Command setSimpleQuantityKind(AQuantityKind simpleQuantityKind, String name, String symbol, String description, String definitionURI) { 
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, simpleQuantityKind, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, simpleQuantityKind, QudvPackage.eINSTANCE.getAQuantityKind_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, simpleQuantityKind, QudvPackage.eINSTANCE.getAQuantityKind_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, simpleQuantityKind, QudvPackage.eINSTANCE.getAQuantityKind_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
	
		return cmpCmd;
	}

	/**
	 * this controller method which adds a derived quantity kind
	 * @param um the unit Management in which the unit will be added
	 * @param quantityKind the quantity kind which will be added
	 * @param quantityKindFactorMap a map with the factors to other quantity kinds
	 * @return the command
	 */
	public Command addDerivedQuantityKind(UnitManagement um, DerivedQuantityKind quantityKind, HashMap<AQuantityKind, Double> quantityKindFactorMap) {
		
		SystemOfQuantities systemOfQuantities = um.getSystemOfUnit().getSystemOfQuantities().get(0);
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		//add the derived quantityKind to the list of quantityKinds in the unit management
		Command addQuantityKindCommand = AddCommand.create(ed, systemOfQuantities, QudvPackage.eINSTANCE.getSystemOfQuantities_QuantityKind(), quantityKind);
		cmpCmd.append(addQuantityKindCommand);
		
		//create all necessary QuantityKind Factors
	    //get the iterator for the map, loop through all elements of the map and create the unit factors; 
		//then create the necessary add command
	    Iterator<Entry<AQuantityKind, Double>> iterator = quantityKindFactorMap.entrySet().iterator();
	    
	    while (iterator.hasNext()) {
	    	Map.Entry<AQuantityKind, Double> m = (Map.Entry<AQuantityKind, Double>) iterator.next();
	    	QuantityKindFactor quantityKindFactor = QudvUnitHelper.getInstance().createQuantityKindFactor(m.getValue(), m.getKey());
	    	Command addQuantityKindFactorCommand = AddCommand.create(ed, quantityKind, QudvPackage.eINSTANCE.getDerivedQuantityKind_Factor(), quantityKindFactor);
	    	cmpCmd.append(addQuantityKindFactorCommand);        	
	    }
		return cmpCmd;
	}
	
	/**
	 * Controller method to set all properties and references for derived quantity kinds 
	 * @param um the unitManagement
	 * @param quantityKind the quantity kind
	 * @param name the name of the quantity kind
	 * @param symbol the symbol of the quantity kind
	 * @param description the description of the quantity kind
	 * @param definitionURI the namespace URI of the quantity kind
	 * @param quantityKindFactorMap a map with the reference factors for the quantity kind
	 * @return the command
	 */
	public Command setDerivedQuantityKind(UnitManagement um, DerivedQuantityKind quantityKind, String name, String symbol, String description, String definitionURI, HashMap<AQuantityKind, Double> quantityKindFactorMap) {
		
		CompoundCommand cmpCmd = new CompoundCommand();
		
		Command setNameCommand = SetCommand.create(ed, quantityKind, GeneralPackage.eINSTANCE.getIName_Name(), name);
		cmpCmd.append(setNameCommand);
		Command setSymbolCommand = SetCommand.create(ed, quantityKind, QudvPackage.eINSTANCE.getAQuantityKind_Symbol(), symbol);
		cmpCmd.append(setSymbolCommand);
		Command setDescriptionCommand = SetCommand.create(ed, quantityKind, QudvPackage.eINSTANCE.getAQuantityKind_Description(), description);
		cmpCmd.append(setDescriptionCommand);
		Command setDefinitionURICommand = SetCommand.create(ed, quantityKind, QudvPackage.eINSTANCE.getAQuantityKind_DefinitionURI(), definitionURI);
		cmpCmd.append(setDefinitionURICommand);
		
		//This remove and add procedure here is not very nice;
		//in fact, we should really check if something has changed in the List of unitFactors and then update, remove, and add only the according UnitFactors

		//remove the quantityKind factors
		Command removeCommand = RemoveCommand.create(ed, quantityKind, QudvPackage.eINSTANCE.getDerivedQuantityKind_Factor(), quantityKind.getFactor());
		cmpCmd.append(removeCommand);

		//re-create all necessary QuantityKind Factors (same is in the add method)
	    //get the iterator for the map, loop through all elements of the map and create the unit factors; 
		//then create the necessary add command
	    Iterator<Entry<AQuantityKind, Double>> iterator = quantityKindFactorMap.entrySet().iterator();
	    
	    while (iterator.hasNext()) {
	    	Map.Entry<AQuantityKind, Double> m = (Map.Entry<AQuantityKind, Double>) iterator.next();
	    	QuantityKindFactor quantityKindFactor = QudvUnitHelper.getInstance().createQuantityKindFactor(m.getValue(), m.getKey());
	    	Command addQuantityKindFactorCommand = AddCommand.create(ed, quantityKind, QudvPackage.eINSTANCE.getDerivedQuantityKind_Factor(), quantityKindFactor);
	    	cmpCmd.append(addQuantityKindFactorCommand);        	
	    }
	 
	    return cmpCmd;
	}
}
