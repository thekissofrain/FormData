package com.test.formdata.common.dao;

/**
 * Created by satish.gummadi on 05-11-2014.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.test.formdata.common.io.XmlParser;
import com.test.formdata.dto.PropertyBean;
import com.test.formdata.dto.SectionBean;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class LocalUpgradeDB {
	public Context mCtx;
	private final String TAG =getClass().getSimpleName();
 
	
	public LocalUpgradeDB(Context context){
		mCtx = context;
	}
	public List<SectionBean> getDatabaseQuery(SQLiteDatabase database) {
		List<SectionBean> subListSections = null;
		try {
		  	XmlParser tp = new XmlParser("database.xml", mCtx);
			subListSections = tp.parse();
			 
		} catch (IOException e) {
           e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {

            e.printStackTrace();
		}
		return subListSections;
	}
	
	@SuppressWarnings("rawtypes")
	public void createTables(SQLiteDatabase db, Context ctx, int oldVersion, int presentVersion) {

		List<SectionBean> getQuerys = getDatabaseQuery(db);

		for (int i = oldVersion+1; i <= presentVersion; i++) {
			for (Iterator iterator = getQuerys.iterator(); iterator.hasNext();) {
				SectionBean sectionBean = (SectionBean) iterator.next();

				if(i == Integer.parseInt(sectionBean.getId())){
					List<SectionBean> subSectionList = sectionBean.getSectionBeans();
					for (Iterator iterator2 = subSectionList.iterator(); iterator2.hasNext();) {

						SectionBean sectionBean1 = (SectionBean) iterator2.next();



						Map<String, PropertyBean> subSectionPropertyMap = sectionBean1.getPropertiesMap();

						Set<String> subSecPropMapSet = subSectionPropertyMap.keySet();
						for (Iterator iterator3 = subSecPropMapSet.iterator(); iterator3.hasNext();) {
                            try{
                                String string = (String) iterator3.next();

                                db.execSQL(subSectionPropertyMap.get(string).getValue());
                            }catch (Exception e){
                                e.printStackTrace();
                            }

						}
					}
				}
			}
		}
 	}

	static void destroy(SQLiteDatabase db, Context ctx) {
		 
	}
}
