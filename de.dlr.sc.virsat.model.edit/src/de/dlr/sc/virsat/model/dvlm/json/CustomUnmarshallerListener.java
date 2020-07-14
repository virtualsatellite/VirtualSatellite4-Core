package de.dlr.sc.virsat.model.dvlm.json;

import javax.xml.bind.Unmarshaller.Listener;

public class CustomUnmarshallerListener extends Listener {

	public Object target;
	public Object parent;
	
	public CustomUnmarshallerListener() { }
	
	@Override
	public void beforeUnmarshal(Object target, Object parent) {
		// TODO Auto-generated method stub
		super.beforeUnmarshal(target, parent);
	
		this.target = target;
		this.parent = parent;
	}
}
