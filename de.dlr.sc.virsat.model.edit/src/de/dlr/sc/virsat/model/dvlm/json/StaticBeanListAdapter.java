package de.dlr.sc.virsat.model.dvlm.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.dlr.sc.virsat.model.concept.list.IBeanList;
import de.dlr.sc.virsat.model.concept.list.TypeSafeArrayInstanceList;
import de.dlr.sc.virsat.model.concept.types.IBeanObject;
import de.dlr.sc.virsat.model.concept.types.property.ABeanProperty;
import de.dlr.sc.virsat.model.concept.types.property.BeanPropertyString;
import de.dlr.sc.virsat.model.concept.types.property.IBeanProperty;
import de.dlr.sc.virsat.model.dvlm.categories.ATypeInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;

public class StaticBeanListAdapter extends XmlAdapter<StaticBeanListPOJO<Object>, IBeanList<Object>> {
	
	private ResourceSet resourceSet;
	private Map<String, ArrayInstance> objectMap = new HashMap<String, ArrayInstance>();
	
	public StaticBeanListAdapter() { }
	
	public StaticBeanListAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Override
	public IBeanList<Object> unmarshal(StaticBeanListPOJO<Object> v) throws Exception {
		String uuid = v.getUuid();
		
		EcoreUtil.getAllContents(resourceSet, true).forEachRemaining(object -> {
			if (object instanceof ArrayInstance) {
				ArrayInstance ti = (ArrayInstance) object;
				if (ti.getUuid().toString().equals(uuid)) {
					objectMap.put(uuid, ti);
				}
			}
		});
		
		ArrayInstance arrayInstance = objectMap.get(uuid);
		// TODO: null check
		
		EList<APropertyInstance> propertyInstanceList = arrayInstance.getArrayInstances();
//		IBeanList<Object> staticBeanList = new TypeSafeArrayInstanceList<Object>(Object.class);
		
		List<Object> beanList = v.getBeanList();
		
		if(propertyInstanceList.size() == beanList.size()) {
			for (int i = 0; i < beanList.size(); i++) {
				ABeanProperty<?> bean = (ABeanProperty) beanList.get(i);
				propertyInstanceList.set(i, bean.getTypeInstance());
			}
		} else {
			throw new IllegalArgumentException("not same length");
		}
		
		return null;
	}

	@Override
	public StaticBeanListPOJO<Object> marshal(IBeanList<Object> v) throws Exception {
		StaticBeanListPOJO<Object> pojo = new StaticBeanListPOJO<Object>();
		List<Object> beanList = pojo.getBeanList();
		for (Object bean : v) {
			beanList.add(bean);
		}
		pojo.setUuid(v.getArrayInstance().getUuid().toString());
		
		return pojo;
	}

}
