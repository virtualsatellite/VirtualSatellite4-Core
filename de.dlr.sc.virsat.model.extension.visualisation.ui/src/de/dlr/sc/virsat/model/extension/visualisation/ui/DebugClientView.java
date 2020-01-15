/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.model.extension.visualisation.ui;

import java.sql.Timestamp;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.google.protobuf.TextFormat;
import com.google.protobuf.TextFormat.ParseException;

import de.dlr.sc.virsat.model.extension.visualisation.Activator;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.TreeManager;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.CommunicationClient;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.GeometryFileClient;
import de.dlr.sc.virsat.model.extension.visualisation.treemanager.networking.SceneGraphClient;
import de.dlr.sc.virsat.model.extension.visualisation.ui.debugClient.DebugClientVisUpdateHandler;
import de.dlr.sc.visproto.VisProto.SceneGraph;

/**
 * This class creates the view for the Debug Client View
 * 
 * @author bara_at
 *
 */
public class DebugClientView extends ViewPart {
	
	private static final int BUTTON_WIDTH = 85;
	private static final float LEFT_COMPOSITE_WIDTH = 0.6f;
	private static final float CONNECTION_STATE_COMPOSITE_WIDTH = 0.4f;
	
	private Activator activator = new Activator();
	private DebugClientView debugClientView;
	private CommunicationClient sceneGraphClient;
	private CommunicationClient geometryFileClient;
	private TreeManager debugClientTreeManager;
	private String currentSceneGraph;
	private String timestampLatestUpdate;
	
	private Composite rootComposite;
	private Composite leftComposite;
	private Composite leftButtonComposite;
	private Composite leftButtonAlignLeftComposite;
	private Composite leftButtonAlignRightComposite;
	private Composite leftInfoComposite;
	private Composite connectionStateComposite;
	private Composite connectionDescriptionComposite;
	private Composite leftLogComposite;
	private Composite rightComposite;
	private Composite rightButtonComposite;
	private Composite rightInfoComposite;
	private Composite rightLogComposite;

	private GridData gridDataLeftComposite;
	private GridData gridDataRightComposite;
	private GridData gridDataLeftButtonAlignLeftComposite;
	private GridData gridDataLeftButtonAlignRightComposite;
	private GridData gridDataConnectionStateComposite;
	private GridData gridDataConnectionDescriptionComposite;
	
	private Button connectButton;
	private Button disconnectButton;
	private Button clearButton;
	private Button sendButton;
	
	private StyledText connectionState;
	private StyledText subEndpointTitle;
	private StyledText pubEndpointTitle;
	private StyledText subEndpointDescription;
	private StyledText pubEndpointDescription;
	private StyledText timestampSceneGraphLog;
	private StyledText sceneGraphLogDebugMessage;
	
	private Text debugLog;
	private Text sceneGraphLog;
	
	@Override
	public void createPartControl(Composite parent) {
		debugClientView = this;
		rootComposite = parent;
		rootComposite.setLayout(new GridLayout(2, false));
		
		// Setup Contents of Parent Composite
		leftComposite = new Composite(rootComposite, SWT.NONE);
	    rightComposite = new Composite(rootComposite, SWT.NONE);
		gridDataLeftComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridDataRightComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
		leftComposite.setLayout(new GridLayout());
		rightComposite.setLayout(new GridLayout());
		leftComposite.setLayoutData(gridDataLeftComposite);
		rightComposite.setLayoutData(gridDataRightComposite);
		
		initializeLeftComposite();
		initializeRightComposite();
		attachResizeListeners();
		attachSelectionListeners();
	}

	/**
	 * Initializes controls for the message log.
	 */
	private void initializeLeftComposite() {
		initializeLeftButtonComposite();
		initializeLeftInfoComposite();
		initializeLeftLogComposite();
	}

	/**
	 * Initializes controls for the scene graph log.
	 */
	private void initializeRightComposite() {
		initializeRightButtonComposite();
		initializeRightInfoComposite();
		initializeRightLogComposite();
	}

	/**
	 * Initializes buttons for message log.
	 */
	private void initializeLeftButtonComposite() {
		leftButtonComposite = new Composite(leftComposite, SWT.NONE);
		leftButtonComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		leftButtonComposite.setLayout(new GridLayout(2, false));
		
		leftButtonAlignLeftComposite = new Composite(leftButtonComposite, SWT.NONE);
		leftButtonAlignLeftComposite.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, true, false));
		GridLayout layoutLeftButtonAlignLeftComposite = new GridLayout(2, false);
		layoutLeftButtonAlignLeftComposite.marginWidth = 0;
		layoutLeftButtonAlignLeftComposite.marginHeight = 0;
		leftButtonAlignLeftComposite.setLayout(layoutLeftButtonAlignLeftComposite);
		
		leftButtonAlignRightComposite = new Composite(leftButtonComposite, SWT.NONE);
		leftButtonAlignRightComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		GridLayout layoutLeftButtonAlignRightComposite = new GridLayout();
		layoutLeftButtonAlignRightComposite.marginWidth = 0;
		layoutLeftButtonAlignRightComposite.marginHeight = 0;
		leftButtonAlignRightComposite.setLayout(layoutLeftButtonAlignRightComposite);
		
		gridDataLeftButtonAlignLeftComposite = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridDataLeftButtonAlignRightComposite = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridDataLeftButtonAlignLeftComposite.widthHint = BUTTON_WIDTH;
		gridDataLeftButtonAlignRightComposite.widthHint = BUTTON_WIDTH;
		connectButton = new Button(leftButtonAlignLeftComposite, SWT.PUSH);
		connectButton.setText("Connect");
		connectButton.setLayoutData(gridDataLeftButtonAlignLeftComposite);
		disconnectButton = new Button(leftButtonAlignLeftComposite, SWT.PUSH);
		disconnectButton.setText("Disconnect");
		disconnectButton.setLayoutData(gridDataLeftButtonAlignLeftComposite);
		disconnectButton.setEnabled(false);
		clearButton = new Button(leftButtonAlignRightComposite, SWT.PUSH);
		clearButton.setText("Clear");
		clearButton.setLayoutData(gridDataLeftButtonAlignRightComposite);
	}
	
	/**
	 * Initializes information section for message log.
	 */
	/**
	 * 
	 */
	private void initializeLeftInfoComposite() {
		leftInfoComposite = new Composite(leftComposite, SWT.BORDER);
		leftInfoComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		leftInfoComposite.setLayout(new GridLayout(2, false));
		
		connectionStateComposite = new Composite(leftInfoComposite, SWT.NONE);
		gridDataConnectionStateComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
		connectionStateComposite.setLayoutData(gridDataConnectionStateComposite);
		GridLayout layoutConnectionStateComposite = new GridLayout();
		layoutConnectionStateComposite.marginWidth = 0;
		layoutConnectionStateComposite.marginHeight = 0;
		connectionStateComposite.setLayout(layoutConnectionStateComposite);
		
		connectionDescriptionComposite = new Composite(leftInfoComposite, SWT.NONE);
		gridDataConnectionDescriptionComposite = new GridData(SWT.FILL, SWT.FILL, true, true);
		connectionDescriptionComposite.setLayoutData(gridDataConnectionDescriptionComposite); 
		GridLayout layoutConnectionDescriptionComposite = new GridLayout(2, false);
		layoutConnectionDescriptionComposite.marginWidth = 0;
		layoutConnectionDescriptionComposite.marginHeight = 0;
		connectionDescriptionComposite.setLayout(layoutConnectionDescriptionComposite);
		
		connectionState = new StyledText(connectionStateComposite, SWT.NONE);
		connectionState.setText("Client disconnected");
	    connectionState.setEditable(false);
	    connectionState.setCaret(null);
	    connectionState.pack();
	    
		subEndpointTitle = new StyledText(connectionDescriptionComposite, SWT.NONE); 
		FontDescriptor boldDescriptorSubEndpointTitle = FontDescriptor.createFrom(subEndpointTitle.getFont()).setStyle(SWT.BOLD);
		subEndpointTitle.setFont(boldDescriptorSubEndpointTitle.createFont(subEndpointTitle.getDisplay())); 
		subEndpointTitle.setText("> SUB-Socket connected to: ");
	    subEndpointTitle.setEditable(false);
	    subEndpointTitle.setCaret(null); 
	    subEndpointTitle.pack();
	    
		subEndpointDescription = new StyledText(connectionDescriptionComposite, SWT.NONE);
		subEndpointDescription.setText("not connected");
		subEndpointDescription.setEditable(false);
	    subEndpointDescription.setCaret(null); 
	    subEndpointDescription.pack();
	    
		pubEndpointTitle = new StyledText(connectionDescriptionComposite, SWT.NONE);
		FontDescriptor boldDescriptorPubEndpointTitle = FontDescriptor.createFrom(subEndpointTitle.getFont()).setStyle(SWT.BOLD);
		pubEndpointTitle.setFont(boldDescriptorPubEndpointTitle.createFont(pubEndpointTitle.getDisplay())); 
		pubEndpointTitle.setText("> PUB-Socket connected to: ");
		pubEndpointTitle.setEditable(false);
	    pubEndpointTitle.setCaret(null); 
	    pubEndpointTitle.pack();
		
		pubEndpointDescription = new StyledText(connectionDescriptionComposite, SWT.NONE);
		pubEndpointDescription.setText("not connected");
		pubEndpointDescription.setEditable(false);
	    pubEndpointDescription.setCaret(null); 
	    pubEndpointDescription.pack();
	}
	
	/**
	 * Initializes text section for message log.
	 */
	private void initializeLeftLogComposite() {
		leftLogComposite = new Composite(leftComposite, SWT.NONE);
		leftLogComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		leftLogComposite.setLayout(new GridLayout());
		
		debugLog = new Text(leftLogComposite, SWT.WRAP | SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);	
		debugLog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	/**
	 * Initializes buttons for scene graph log.
	 */
	private void initializeRightButtonComposite() {
		rightButtonComposite = new Composite(rightComposite, SWT.NONE);
		rightButtonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false));
		rightButtonComposite.setLayout(new GridLayout());
		GridData gridDataRightButtonComposite = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridDataRightButtonComposite.widthHint = BUTTON_WIDTH;
		
		sendButton = new Button(rightButtonComposite, SWT.PUSH);
		sendButton.setText("Send");
		sendButton.setEnabled(false);
		sendButton.setLayoutData(gridDataRightButtonComposite);	
	}
	
	/**
	 * Initializes information section for scene graph log.
	 */
	private void initializeRightInfoComposite() {
		rightInfoComposite = new Composite(rightComposite, SWT.BORDER);
		rightInfoComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		rightInfoComposite.setLayout(new GridLayout());
		
		timestampSceneGraphLog = new StyledText(rightInfoComposite, SWT.NONE);
		timestampSceneGraphLog.setText("[" + new Timestamp(System.currentTimeMillis()) + "]");
		timestampSceneGraphLog.setEditable(false);
	    timestampSceneGraphLog.setCaret(null); 
	    timestampSceneGraphLog.pack();
	    
		sceneGraphLogDebugMessage = new StyledText(rightInfoComposite, SWT.NONE);
		sceneGraphLogDebugMessage.setText("MESSAGE >   " + "Scene Graph Log initialized.");
		sceneGraphLogDebugMessage.setEditable(false);
	    sceneGraphLogDebugMessage.setCaret(null); 
	    sceneGraphLogDebugMessage.pack();
	}
	
	/**
	 * Initializes text section for scene graph log.
	 */
	private void initializeRightLogComposite() {
		rightLogComposite = new Composite(rightComposite, SWT.NONE);
		rightLogComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		rightLogComposite.setLayout(new GridLayout());
		
		sceneGraphLog = new Text(rightLogComposite, SWT.WRAP | SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		sceneGraphLog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	/**
	 * Attaches listeners to buttons.
	 */
	private void attachSelectionListeners() {
		connectButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (sceneGraphClient == null || geometryFileClient == null) {
					DebugClientVisUpdateHandler.setDebugClientView(debugClientView);
					debugClientTreeManager = new TreeManager();
					sceneGraphClient = new SceneGraphClient(debugClientTreeManager, DebugClientVisUpdateHandler.getInstance());
					geometryFileClient = new GeometryFileClient(debugClientTreeManager, DebugClientVisUpdateHandler.getInstance());
					connectionState.setText("Client connected");
					subEndpointDescription.setText(CommunicationClient.IP_ADDRESS);
					pubEndpointDescription.setText(CommunicationClient.IP_ADDRESS);
					subEndpointDescription.pack();
					pubEndpointDescription.pack();
					disconnectButton.setEnabled(true);
					sendButton.setEnabled(true);
					connectButton.setEnabled(false);
				} else {
					connectionState.setText("Client connected");
				}
			}
		});
		
		disconnectButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (sceneGraphClient != null) {
					sceneGraphClient.close();
					geometryFileClient.close();
					subEndpointDescription.setText("not connected");
					pubEndpointDescription.setText("not connected");
					subEndpointDescription.pack();
					pubEndpointDescription.pack();
					connectionState.setText("Client disconnected");
					sceneGraphClient = null;
					geometryFileClient = null;
					disconnectButton.setEnabled(false);
					sendButton.setEnabled(false);
					connectButton.setEnabled(true);
				} else {
					connectionState.setText("Client disconnected");
				}
			}
		});
		
		clearButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				debugLog.setText("");
			}
		});
		
		sendButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				SceneGraph.Builder sceneGraphBuilder = createSceneGraphBuilderFromString();
				timestampSceneGraphLog.setText("[" + System.currentTimeMillis() + "]");
				if (sceneGraphBuilder != null) {
					sceneGraphClient.sendSceneGraph(sceneGraphBuilder.build());
					timestampSceneGraphLog.setText("[" + new Timestamp(System.currentTimeMillis()) + "]");
					sceneGraphLogDebugMessage.setText("MESSAGE >   " + "SceneGraph sent.");
					sceneGraphLogDebugMessage.pack();
				} else {
					timestampSceneGraphLog.setText("[" + new Timestamp(System.currentTimeMillis()) + "]");
					sceneGraphLogDebugMessage.setText("MESSAGE >   " + "Invalid SceneGraph, could not be sent.");
					sceneGraphLogDebugMessage.pack();
				}
			}
		});
	}
	
	/**
	 * Attaches resize listeners to rootComposite and leftInfoComposite.
	 */
	private void attachResizeListeners() {
		leftInfoComposite.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Point size = leftInfoComposite.getSize();
				gridDataConnectionStateComposite.widthHint = (int) (size.x * CONNECTION_STATE_COMPOSITE_WIDTH);
				gridDataConnectionDescriptionComposite.widthHint = size.x - gridDataConnectionStateComposite.widthHint;
			}
		});
		
		rootComposite.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Point size = rootComposite.getSize();
				gridDataLeftComposite.widthHint = (int) (size.x * LEFT_COMPOSITE_WIDTH);
				gridDataRightComposite.widthHint = size.x - gridDataLeftComposite.widthHint;
			}
			
		});
	}
	
	/**
	 * Creates a SceneGraph.Builder object by parsing a String representation of a SceneGraph.
	 * @return sceneGraphBuilder	null if parsing fails.
	 */
	private SceneGraph.Builder createSceneGraphBuilderFromString() {
		String sceneGraphString = sceneGraphLog.getText();
		SceneGraph.Builder sceneGraphBuilder = SceneGraph.newBuilder();
		try {
			TextFormat.getParser().merge(sceneGraphString, sceneGraphBuilder);
		} catch (ParseException e) {
			sceneGraphBuilder = null;
			activator.getLog().log(new Status(Status.ERROR, Activator.getPluginId(), "[Communication Client] Invalid SceneGraph, could not be sent.", null));
		}
		return sceneGraphBuilder;
	}
	
	/**
	 * Appends a message to the Debug Log.
	 * @param message 	The message to append.
	 */
	public void logDebugMessage(String message) {
		Display.getDefault().asyncExec(() -> { 
			if (!debugLog.isDisposed()) {
				debugLog.append(message + "\n"); 
			}
		});
	}
	
	/**
	 * Logs the latest Scene Graph received by this client.
	 * @param message 	The received message.
	 */
	public void logSceneGraphMessage(String message) {
		Display.getDefault().asyncExec(() -> { 
			if (!sceneGraphLog.isDisposed()) {
				currentSceneGraph = message;
				timestampSceneGraphLog.setText("[" + timestampLatestUpdate + "]");
				sceneGraphLogDebugMessage.setText("MESSAGE >   " + "SceneGraph received.");
				sceneGraphLogDebugMessage.pack();
				sceneGraphLog.setText("");
				sceneGraphLog.append(currentSceneGraph);
			}
		});
	}
	
	/**
	 * Sets a Timestamp which signifies when the last VisualisationMessage was received by this client.
	 * @param timestamp 	String containing time, when last VisualisationMessage was received,
	 */
	public void updateTimestamp(String timestamp) {
		timestampLatestUpdate = timestamp;
	}

	@Override
	public void setFocus() {
		connectButton.setFocus();
	}
	
	@Override
	public void dispose() {
		if (sceneGraphClient != null) {
			sceneGraphClient.close();
		}
		super.dispose();
	}
}
