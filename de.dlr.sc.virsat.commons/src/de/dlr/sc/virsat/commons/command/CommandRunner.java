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
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

public class CommandRunner {
	
	
	private static BufferedWriter writer;
	
	private InputStreamConsumer isc;
	private InputStreamConsumer iscerr;
	
	private Process p;
	private boolean isRunning;
	private CommandRunnerType type;
	
	private final String outputfile = "output.txt";
	private final String errorfile = "error.txt";
	
	/**
	 * 
	 * @param applicationpath path to the application to run
	 * @param initcommand command to initialize the application
	 */
	public void startCommandRunner(String applicationpath, String initcommand, CommandRunnerType type) {
		this.type = type;
		
		try {
		
			ProcessBuilder pb = new ProcessBuilder(applicationpath + initcommand);
			
	        
	        switch (this.type) {
	        
	        	case INTERACTIVE:
	        		p = pb.start();
	    			OutputStream stdin = p.getOutputStream();
	    	        writer = new BufferedWriter(new OutputStreamWriter(stdin));
	    	        
	        		isc = new InputStreamConsumer(p.getInputStream(), writer);
	    			iscerr = new InputStreamConsumer(p.getErrorStream(), writer);
	    	        isc.start();
	    	        iscerr.start();
	    	        
	        		break;
	        	case STANDARD:
	        		File out = new File(outputfile);
	        		File err = new File(errorfile);
	        		pb.redirectError(err);
	        		pb.redirectOutput(out);
	        		break;
	        	default:
	        		break;
	        	
	        }
	        
	        this.isRunning = true;
	        
	        
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
	}
	
	/**
	 * Run a command for the initialized application
	 * @param command command to run
	 */
	public boolean runCommand(String command) {
		if (isRunning && type == CommandRunnerType.INTERACTIVE) {
			try {
				writer.write(command);
				writer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		
		return false;
	}
	
	/***
	 * Closes the running application 
	 */
	public void exitCommand() {
		if (isRunning && type == CommandRunnerType.INTERACTIVE) {
			
			try {
				
				try {
					writer.flush();
					writer.close();
					int exitCode = p.waitFor();
		        	isc.join();
		        	iscerr.join();
		        		
		        	
					
		        	System.out.println("Process terminated with " + exitCode);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		this.isRunning = false;
	}
}
