package com.eis.healthylicous.communication;

import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

public class ItemEventCoordinator implements ItemEventListener {
	String tag, gewicht, name;
	@Override
	public void handlePublishedItems(ItemPublishEvent items) { 
		
//		if(items.getNodeId().contains("android@doro-f5sr/Smack")) {
		
			 tag = new DataHandler().getResultTag(items.getItems().toString());
			 gewicht = new DataHandler().getResultGewicht(items.getItems().toString());
			 name = new DataHandler().getResultName(items.getItems().toString());
		        new DataHandler().getUser(items);
		        new DataHandler().getResults(items.getItems().toString());        

//		}
       
        
	}
	
	public String getTag() {
		return tag;
	}
	
	public String getGewicht() {
		return gewicht;
	}
	
	public String getName() {
		return name;
	}
	
	

}