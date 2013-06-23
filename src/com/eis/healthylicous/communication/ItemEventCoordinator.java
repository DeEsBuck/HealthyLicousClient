package com.eis.healthylicous.communication;

import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import android.util.Log;

public class ItemEventCoordinator implements ItemEventListener {
	String tag, gewicht, name;
	@Override
	public void handlePublishedItems(ItemPublishEvent items) { 
		
		
			 tag = new DataHandler().getResultTag(items.getItems().toString());
			 gewicht = new DataHandler().getResultGewicht(items.getItems().toString());
			 name = new DataHandler().getResultName(items.getItems().toString());
		        new DataHandler().getUser(items);
		        new DataHandler().getResults(items.getItems().toString());        
		        Log.d("ITEMEVENTLISTENER" , tag);
		        Log.d("ITEMEVENTLISTENER" , gewicht);
		        Log.d("ITEMEVENTLISTENER" , name);
        
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