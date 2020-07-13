package de.dlr.sc.virsat.model.dvlm.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class StaticBeanListPOJO<TYPE> {

	@XmlElement
	private List<TYPE> beanList;
	@XmlElement
	private String name;
	
	public StaticBeanListPOJO() {
		setBeanList(new ArrayList<TYPE>());
	}

	public List<TYPE> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<TYPE> beanList) {
		this.beanList = beanList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
