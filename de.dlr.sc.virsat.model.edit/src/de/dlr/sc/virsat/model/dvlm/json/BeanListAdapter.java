package de.dlr.sc.virsat.model.dvlm.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
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
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.AProperty;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.IArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertydefinitions.StaticArrayModifier;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ArrayInstance;

public class BeanListAdapter extends XmlAdapter<BeanListPOJO<Object>, IBeanList<Object>> {
	
	private ResourceSet resourceSet;
	private CustomMarshallerListener marshallerListener;
	private CustomUnmarshallerListener unmarshallerListener;
	private Map<String, ArrayInstance> objectMap = new HashMap<String, ArrayInstance>();
	
	public BeanListAdapter() { }
	
	public BeanListAdapter(ResourceSet resourceSet, CustomMarshallerListener marshallerListener, CustomUnmarshallerListener unmarshallerListener) {
		this.resourceSet = resourceSet;
		this.marshallerListener = marshallerListener;
		this.unmarshallerListener = unmarshallerListener;
	}

	@Override
	public IBeanList<Object> unmarshal(BeanListPOJO<Object> v) throws Exception {
		String uuid = v.getUuid();
		
		System.out.println(unmarshallerListener.parent);
		System.out.println(unmarshallerListener.target);
		System.out.println(unmarshallerListener.target.getClass().getName());
		
		// TODO redundant code from typeInstanceAdapter -> refactor: inheritence/composition?
		EcoreUtil.getAllContents(resourceSet, true).forEachRemaining(object -> {
			if (object instanceof ArrayInstance) {
				ArrayInstance ti = (ArrayInstance) object;
				if (ti.getUuid().toString().equals(uuid)) {
					objectMap.put(uuid, ti);
				}
			}
		});
		
		ArrayInstance arrayInstance = objectMap.get(uuid);
		IArrayModifier arrayModifier = ((AProperty) arrayInstance.getType()).getArrayModifier();
		// TODO: null check
		
		EList<APropertyInstance> propertyInstanceList = arrayInstance.getArrayInstances();
//		IBeanList<Object> staticBeanList = new TypeSafeArrayInstanceList<Object>(Object.class);
		
		List<Object> beanList = v.getBeanList();
		
		// if the arraymodifier is static we have to check the size
		if (arrayModifier instanceof StaticArrayModifier) {
			if (propertyInstanceList.size() == beanList.size()) {
				for (int i = 0; i < beanList.size(); i++) {
					ABeanProperty<?> bean = (ABeanProperty) beanList.get(i);
					propertyInstanceList.set(i, bean.getTypeInstance());
				}
			} else {
				throw new IllegalArgumentException("not same length");
			}
		} else {
			propertyInstanceList.clear();
			for (Object obj : beanList) {
				ABeanProperty<?> bean = (ABeanProperty) obj;
				propertyInstanceList.add(bean.getTypeInstance());
			}
		}
		
		
		// this will set the actual list to null
		// but it will be reset with the updated array instance
		// because of the safe access array method
		return null;
	}

	@Override
	public BeanListPOJO<Object> marshal(IBeanList<Object> v) throws Exception {
//		System.out.println(marshallerListener.source.getClass().getName());
		BeanListPOJO<Object> pojo = new BeanListPOJO<Object>();
		List<Object> beanList = pojo.getBeanList();
		
		for (Object bean : v) {
			String classname = bean.getClass().getName();
			beanList.add(Class.forName(classname).cast(bean));
		}
		pojo.setUuid(v.getArrayInstance().getUuid().toString());
		
		return pojo;
	}

}
