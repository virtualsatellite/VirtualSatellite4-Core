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

public abstract class ABeanListAdapter<TYPE extends IBeanProperty<? extends APropertyInstance,?>> extends XmlAdapter<BeanListPOJO<TYPE>, IBeanList<TYPE>> {
	
	private ResourceSet resourceSet;
	private Map<String, ArrayInstance> objectMap = new HashMap<String, ArrayInstance>();
	private Class<TYPE> beanClass;
	
	public ABeanListAdapter() { }
	
	public ABeanListAdapter(Class<TYPE> beanClass) {
		this.beanClass = beanClass;
	}
	
//	public ABeanListAdapter(ResourceSet resourceSet) {
//		this.resourceSet = resourceSet;
//	}
	
	public void setResourceSet(ResourceSet resourceSet) { 
		this.resourceSet = resourceSet;
	}

	@Override
	public IBeanList<TYPE> unmarshal(BeanListPOJO<TYPE> v) throws Exception {
		String uuid = v.getUuid();
		
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

		IBeanList<TYPE> newBeanList = new TypeSafeArrayInstanceList<TYPE>(beanClass);
		newBeanList.setArrayInstance(arrayInstance);
		
		List<TYPE> beanList = v.getBeanList();
		
		// if the arraymodifier is static we have to check the size
		if (arrayModifier instanceof StaticArrayModifier) {
			if (newBeanList.size() == beanList.size()) {
				for (int i = 0; i < beanList.size(); i++) {
					// TODO: cannot call set here
					TYPE oldBean = newBeanList.get(i);
					oldBean.setATypeInstance(beanList.get(i).getATypeInstance());
				}
			} else {
				throw new IllegalArgumentException("not same length");
			}
		} else {
			newBeanList.clear();
			newBeanList.addAll(beanList);
		}
		
		return newBeanList;
	}

	@Override
	public BeanListPOJO<TYPE> marshal(IBeanList<TYPE> v) throws Exception {
		BeanListPOJO<TYPE> pojo = new BeanListPOJO<TYPE>();
		List<TYPE> beanList = pojo.getBeanList();
		
		for (TYPE bean : v) {
			beanList.add(bean);
		}
		pojo.setUuid(v.getArrayInstance().getUuid().toString());
		
		return pojo;
	}

}
