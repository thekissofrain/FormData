package com.test.formdata.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by satish.gummadi on 05-11-2014.
 */
public class SectionBean {
	String id;
	List<SectionBean> sectionBeans;
	Map<String, PropertyBean> propertiesMap;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<SectionBean> getSectionBeans() {
		return sectionBeans;
	}
	public void setSectionBeans(List<SectionBean> sectionBeans) {
		this.sectionBeans = sectionBeans;
	}
	public Map<String, PropertyBean> getPropertiesMap() {
		return propertiesMap;
	}
	public void setPropertiesMap(Map<String, PropertyBean> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}
	

}
