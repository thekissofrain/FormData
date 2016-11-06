package com.test.formdata.common.io;

import android.content.Context;
import android.util.Xml;

import com.test.formdata.dto.PropertyBean;
import com.test.formdata.dto.SectionBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParser {
	 // We don't use namespaces
    private static final String ns = null;
    private static final String TAG = XmlParser.class.getSimpleName();
    
    String filePath;
    Context mContext;
    public XmlParser(String fileName, Context context){
    	this.filePath = fileName;
    	mContext = context;
    }
   
    public List<SectionBean> parse() throws XmlPullParserException, IOException {
    	InputStream inputStream = mContext.getAssets().open(filePath);
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
        	inputStream.close();
        }
    }
    
    
    private List<SectionBean> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<SectionBean> sections = new ArrayList<SectionBean>();

        parser.require(XmlPullParser.START_TAG, ns, "section");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("section")) {
            	
            	sections.add(readSection(parser));
            } else {
                skip(parser);
            }
        }  
        return sections;
    }


	private void skip(XmlPullParser parser) {
		
		
	}


	private SectionBean readSection(XmlPullParser parser) {
	 
	    
	    SectionBean sb =new SectionBean();
	    try {
	    	String sectionId = parser.getAttributeValue(null, "id");
	    	
	    	sb.setId(sectionId);
			while (parser.next() != XmlPullParser.END_TAG) {
			    if (parser.getEventType() != XmlPullParser.START_TAG) {
			        continue;
			    }
			    String name = parser.getName();
			    if (name.equals("property")) {
			        PropertyBean pb = readProperty(parser);
			        //set the property
			        Map<String, PropertyBean> proMap = sb.getPropertiesMap();
			        if(proMap ==null){
			        	proMap = new HashMap<String, PropertyBean>();
			        	sb.setPropertiesMap(proMap);
			        }
			        proMap.put(pb.getId(), pb);
			        
			    }else if (name.equals("section")) {
			        SectionBean childBean = readSection(parser);
			        
			        
			        List<SectionBean> childList= sb.getSectionBeans();
			        if(childList ==null){
			        	childList = new ArrayList<SectionBean>();
			        	sb.setSectionBeans(childList);
			        }
			        childList.add(childBean);
			    }
			    else {
			        skip(parser);
			    }
			}
		} catch (XmlPullParserException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

            e.printStackTrace();
		}
	   
	    return sb;
		
	}
	
	
	private PropertyBean readProperty(XmlPullParser parser) throws IOException, XmlPullParserException {
		PropertyBean pbean = new PropertyBean();
	    parser.require(XmlPullParser.START_TAG, ns, "property");
	    String pid = parser.getAttributeValue(null, "id");
	    pbean.setId(pid);
	    pbean.setValue(readText(parser));
	    parser.require(XmlPullParser.END_TAG, ns, "property");
	    return pbean;
	}
	
	// For the tags title and summary, extracts their text values.
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
	    String result = "";
	    if (parser.next() == XmlPullParser.TEXT) {
	        result = parser.getText();
	        parser.nextTag();
	    }
	    return result;
	}
	
	
    
    
}
