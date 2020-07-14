package de.dlr.sc.virsat.model.dvlm.json;

import javax.xml.bind.Marshaller.Listener;

public class CustomMarshallerListener extends Listener {

	public Object source;
	
	public CustomMarshallerListener() { }
	
	@Override
	public void beforeMarshal(Object source) {
		// TODO Auto-generated method stub
		super.beforeMarshal(source);
	
		this.source = source;
	}
}
