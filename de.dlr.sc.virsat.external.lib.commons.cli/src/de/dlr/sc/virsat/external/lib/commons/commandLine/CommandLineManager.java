/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib.commons.commandLine;

import java.util.ListIterator;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.external.lib.commons.cli.Activator;

/**
 * The command line manager class handling the command line extension point
 * as well as set command line options when virtual satellite gets called
 * @author fisc_ph
 *
 */
@SuppressWarnings("deprecation")
public class CommandLineManager {

	private Options commandLineOptions = new Options();
	private CommandLine commandLine;
	private static final CommandLineManager COMMANDLINEMANGER = new CommandLineManager();	
	
	/**
	 * Constructor of the command line manager
	 */
	private CommandLineManager() {
		super();
	}

	/**
	 * Get the singleton instance of the command line manager
	 * @return the singleton instance of the commandline manager
	 */
	public static CommandLineManager getInstance() {
		return COMMANDLINEMANGER;
	}


	/**
	 * Call this emhtod to parse the complete command line
	 */
	public void parseCommandLineArguments() {
		String[] args = Platform.getCommandLineArgs();

		CommandLineParser parser = new VirSatParser();
		try {
			commandLine = parser.parse(commandLineOptions, args);
		} catch (ParseException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, "Activator", "Command Line Parser Error: _" + e));

		}
	}

	/**
	 * Method to add an expected option on the command line
	 * @param name the name
	 * @param argument the argument
	 * @param description the sescription of the option
	 */
	public void addOption(String name, String argument, String description) {
		OptionBuilder.hasArg();
		Option option = OptionBuilder.create(name);
		option.setArgs(1);
		option.setArgName(argument);
		option.setDescription(description);
		commandLineOptions.addOption(option);
	}

	/**
	 * method to add an option to the command line
	 * @param name the name of the option
	 * @param description a description of the option
	 */
	public void addOption(String name, String description) {
		Option option = OptionBuilder.create(name);
		option.setDescription(description);
		commandLineOptions.addOption(option);
	}

	/**
	 * method to check if a command line option ahs been set
	 * @param name the name of the option
	 * @return true in case it has been set otherwise false
	 */
	public boolean isCommandLineOptionSet(String name) {
		return commandLine.hasOption(name);
	}

	/**
	 * get the parameter that has been set with a command line option
	 * @param name the name of the command line option
	 * @return the value of the parameter
	 */
	public String getCommandLineOptionParameter(String name) {
		return commandLine.getOptionValue(name);
	}

	/**
	 * Get all set command line arguments
	 * @param name the name of the option
	 * @return an array of strings containing the arguments
	 */
	public String[] getCommandLineOptionArgument(String name) {
		return commandLine.getArgs();
	}

	/**
	 * Anonymous class repressing a specialized virsat parser
	 * @author fisc_ph
	 *
	 */
	static class VirSatParser extends PosixParser {
		@SuppressWarnings(value = {"rawtypes", "unchecked" })
		@Override
		protected void processOption(String arg, ListIterator iter) throws ParseException {
			try {
				super.processOption(arg, iter);
			} catch (ParseException e) {
				Activator.getDefault().getLog().log(new Status(Status.WARNING, "Activator", "CommandLine Argument is not understood: " + arg));
			}
		}

	}
}