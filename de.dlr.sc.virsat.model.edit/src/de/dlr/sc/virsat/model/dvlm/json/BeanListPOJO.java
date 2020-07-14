package de.dlr.sc.virsat.model.dvlm.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class BeanListPOJO<TYPE> {

	@XmlElement
	private List<TYPE> beanList;
	@XmlElement
	private String uuid;
	
	public BeanListPOJO() {
		setBeanList(new ArrayList<TYPE>());
	}

	public List<TYPE> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<TYPE> beanList) {
		this.beanList = beanList;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
