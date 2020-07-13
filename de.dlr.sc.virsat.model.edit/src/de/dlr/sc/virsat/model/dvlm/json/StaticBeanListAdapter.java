package de.dlr.sc.virsat.model.dvlm.json;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import de.dlr.sc.virsat.model.concept.list.IBeanList;

public class StaticBeanListAdapter<TYPE> extends XmlAdapter<StaticBeanListPOJO<TYPE>, IBeanList<TYPE>> {

	@Override
	public IBeanList<TYPE> unmarshal(StaticBeanListPOJO<TYPE> v) throws Exception {
		// TODO: get the corresponding arrayinstance via a resource set??
		// How do we pass it because of the generics?
		System.out.println("Called");
		return null;
	}

	@Override
	public StaticBeanListPOJO<TYPE> marshal(IBeanList<TYPE> v) throws Exception {
		StaticBeanListPOJO<TYPE> pojo = new StaticBeanListPOJO<TYPE>();
		List<TYPE> beanList = pojo.getBeanList();
		for (TYPE bean : v) {
			beanList.add(bean);
		}
		pojo.setName(v.getArrayInstance().getFullQualifiedInstanceName());
		
		return pojo;
	}

}
