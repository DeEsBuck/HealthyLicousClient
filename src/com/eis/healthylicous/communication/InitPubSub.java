package com.eis.healthylicous.communication;

import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.pubsub.provider.SubscriptionProvider;

import android.util.Log;

	//Namespaces bekannt machen für die aSmack API
public class InitPubSub {
	public static void init() {

    ProviderManager pm = ProviderManager.getInstance();
    
    pm.addIQProvider("query", "http://jabber.org/protocol/disco#items",new org.jivesoftware.smackx.provider.DiscoverItemsProvider());

    pm.addIQProvider("query", "http://jabber.org/protocol/disco#info",new org.jivesoftware.smackx.provider.DiscoverInfoProvider());

    pm.addIQProvider("pubsub", "http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.PubSubProvider());

    pm.addExtensionProvider("subscription", PubSubNamespace.BASIC.getXmlns(), new SubscriptionProvider());

    pm.addExtensionProvider("create","http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.SimpleNodeProvider());

    pm.addExtensionProvider("items", "http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.ItemsProvider());

    pm.addExtensionProvider("item", "http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.ItemProvider());

    pm.addExtensionProvider("item", "",new org.jivesoftware.smackx.pubsub.provider.ItemProvider());

    pm.addExtensionProvider("subscriptions","http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.SubscriptionsProvider());

    pm.addExtensionProvider("subscriptions","http://jabber.org/protocol/pubsub#owner",new org.jivesoftware.smackx.pubsub.provider.SubscriptionsProvider());

    pm.addExtensionProvider("affiliations","http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.AffiliationsProvider());

    pm.addExtensionProvider("affiliation","http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.AffiliationProvider());

    pm.addExtensionProvider("options", "http://jabber.org/protocol/pubsub",new org.jivesoftware.smackx.pubsub.provider.FormNodeProvider());

    pm.addIQProvider("pubsub", "http://jabber.org/protocol/pubsub#owner",new org.jivesoftware.smackx.pubsub.provider.PubSubProvider());

    pm.addExtensionProvider("configure","http://jabber.org/protocol/pubsub#owner",new org.jivesoftware.smackx.pubsub.provider.FormNodeProvider());

    pm.addExtensionProvider("default","http://jabber.org/protocol/pubsub#owner",new org.jivesoftware.smackx.pubsub.provider.FormNodeProvider());

    pm.addExtensionProvider("event","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.EventProvider());

    pm.addExtensionProvider("configuration","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.ConfigEventProvider());

    pm.addExtensionProvider("delete","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.SimpleNodeProvider());

    pm.addExtensionProvider("options","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.FormNodeProvider());

    pm.addExtensionProvider("items","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.ItemsProvider());

    pm.addExtensionProvider("item","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.ItemProvider());

    pm.addExtensionProvider("headers", "http://jabber.org/protocol/shim",new org.jivesoftware.smackx.provider.HeaderProvider());

    pm.addExtensionProvider("header", "http://jabber.org/protocol/shim",new org.jivesoftware.smackx.provider.HeadersProvider());

    pm.addExtensionProvider("retract","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.RetractEventProvider());

    pm.addExtensionProvider("purge","http://jabber.org/protocol/pubsub#event",new org.jivesoftware.smackx.pubsub.provider.SimpleNodeProvider());

    pm.addExtensionProvider("x", "jabber:x:data",new org.jivesoftware.smackx.provider.DataFormProvider());

    SmackConfiguration.setPacketReplyTimeout(60000);
    Log.d("HEALTHYCONTROLLER", "InitPubSub Success!");
}
}

