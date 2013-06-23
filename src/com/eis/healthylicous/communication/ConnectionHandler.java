package com.eis.healthylicous.communication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.Node;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.Subscription;


import android.content.Intent;
import android.util.Log;

public class ConnectionHandler{

	String topicID;
	DataHandler pub;
//	public static final String TOPIC = "Kalories";
	private static final String USER = "android";
	private static final String PASSWORD = "openfire";
			
	static XMPPConnection xmppcon;

	public ConnectionHandler(ConnectionConfiguration config) {
		InitPubSub.init();
		Log.d("HEALTHYCONTROLLER", "InitPubSub Success!");
		xmppcon = new XMPPConnection(config);
	}
	public void connect() throws XMPPException{ 
		xmppcon.connect();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUser() {
		return xmppcon.getUser();
	}
	
	/**
	 * Verbindung zum Server login
	 * @throws XMPPException 
	 */
	public void login() throws XMPPException {
			xmppcon.login(USER, PASSWORD);
	}
	
	/**
	 * Verbindung zum Server login
	 * @return
	 */
	public boolean isconnected(){
			return xmppcon.isConnected();
//		return xmppcon.isAuthenticated();
		}
		
	/**
	 * alle XMPP Methoden werden hier aufgelistet
	 */
	public void publishItem() {
		Log.d("XMPP", "publish");
			
	}

	public void disconnect() {
		xmppcon.disconnect();
	}

	/**
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public Node createTopic(String topicID) throws XMPPException {
		ConfigureForm form = new ConfigureForm(FormType.submit);
		form.setPersistentItems(true);
		form.setDeliverPayloads(true);
		form.setAccessModel(AccessModel.open);
		form.setPublishModel(PublishModel.open);
		form.setNotifyDelete(true);
		form.setSubscribe(true);
		this.topicID = topicID;
		Node n = createPubSubManager().createNode(topicID, form);
		return n;
	}

	/**
	 * 
	 * @return
	 */
	private PubSubManager createPubSubManager() {
		PubSubManager mgr = new PubSubManager(ConnectionHandler.xmppcon, "pubsub."
				+ ConnectionHandler.xmppcon.getServiceName());
		return mgr;
	}

	/**
	 * 
	 * @param topicID
	 * @return
	 * @throws XMPPException
	 */
	private Node getNode(String topicID) throws XMPPException {
		PubSubManager mgr = new PubSubManager(ConnectionHandler.xmppcon, "pubsub."
				+ ConnectionHandler.xmppcon.getServiceName());
		Node n = mgr.getNode(topicID);
		return n;
	}

	/**
	 * 
	 * @param topicID
	 * @param input
	 * @throws XMPPException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void publishPayload(PayloadItem payload) throws XMPPException {
		((LeafNode) getNode(topicID)).publish(payload);
	}

	public void publishPayload(String topicID, PayloadItem<?> payload) throws XMPPException {
		((LeafNode) getNode(topicID)).publish(payload);
	}

	/**
	 * 
	 * @param jid
	 * @param topicID
	 * @throws XMPPException
	 */
	public void subscribe(String topicID, String jid) throws XMPPException {
		getNode(topicID).subscribe(jid);
	}

	/**
	 * 
	 * @param jid
	 * @param topicID
	 * @throws XMPPException
	 */
	public void unSubscribe(String topicID, String jid) throws XMPPException {
		getNode(topicID).unsubscribe(jid);
	}

	/**
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public void listener(String topicID) throws XMPPException {
		getNode(topicID).addItemEventListener(new ItemEventCoordinator());
	}

	/**
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public String getTopicID() throws XMPPException {
		return getNode(topicID).getId();
	}

	/**
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public void discoverNodes(String topicID) throws XMPPException {
		System.out.println(createPubSubManager().discoverNodes(topicID).toXML());
	}

	/**
	 * 
	 * @param topicID
	 * @return
	 */
	public Boolean doesNodeExist(String topicID) {
		Boolean exist = false;
		try {
			createPubSubManager().discoverNodes(topicID);
			exist = true;
		} catch (XMPPException e) {
			e.getMessage();
			exist = false;
		}
		return exist;
	}

	/**
	 * Only the Owner of the Node has permission for deletion
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public void deleteTopic(String topicID) throws XMPPException {
		createPubSubManager().deleteNode(topicID);
		
	}

	/**
	 * 
	 * @param topicID
	 * @return
	 * @throws XMPPException
	 */
	public String discoItems() throws XMPPException {
		DiscoverItems disco = ((LeafNode) getNode(topicID)).discoverItems();
		// System.out.println("DiscoResult: " + disco.toXML());
		return disco.toXML();
	}

	/**
	 * Get List of Items inclusive current per Subscription
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public void getCurrentItems() throws XMPPException {
		Iterable<Subscription> id = (Iterable<Subscription>) getNode(topicID)
				.getSubscriptions();
		for (Subscription i : id) {
			System.out.println("SubscriptionID: " + i.getId());
			System.out.println("ItemResult: "
					+ ((LeafNode) getNode(topicID)).getItems(i.getId()));
		}
	}

	public void getCurrentItemsOf(String topicID) throws XMPPException {
		Iterable<Subscription> id = (Iterable<Subscription>) getNode(topicID)
				.getSubscriptions();
		for (Subscription i : id) {
//			System.out.println("SubscriptionID: " + i.getId());
//			System.out.println("ItemResult: "
//					+ ((LeafNode) getNode(topicID)).getItems(i.getId()));
		}
	}

	/**
	 * Get the last subscriber
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public String getThisSubscriber() throws XMPPException {
		String sub = null;
		if (getNode(topicID).getSubscriptions().isEmpty()) {
			System.out.println("keine Subscriber");
		} else {
			int index = getNode(topicID).getSubscriptions().size() - 1;
			sub = getNode(topicID).getSubscriptions().get(index).getId();
		}
		return sub;
	}

	/**
	 * Get Items of the last subscriber
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public String getItem() throws XMPPException {
		String give = null;
		if (getNode(topicID).getSubscriptions().isEmpty()) {
			System.out.println("keine Subscriber");
		} else {
			int index = getNode(topicID).getSubscriptions().size() - 1;
			String sub = getNode(topicID).getSubscriptions().get(index).getId();
			give = ((LeafNode) getNode(topicID)).getItems(sub).toString();
		}
		return give;
	}

	/**
	 * Results only the last ItemID in Topic
	 * 
	 * @param topicID
	 * @return
	 * @throws XMPPException
	 */
	public String getLastItemId() throws XMPPException {
		Pattern regExp = Pattern.compile("name=\"[a-z0-9-]*");
		String give = this.discoItems();
		String result = null;
		Matcher ma = regExp.matcher(give);
		while (ma.find()) {
			result = ma.group();
			String[] ja = ma.group().split("name=\"");
			for (String r : ja) {
				result = r;
			}
		}
		return result;
	}

	/**
	 * 
	 * @return
	 * @throws XMPPException
	 */
	public String getItemId() throws XMPPException {
		Pattern regExp = Pattern.compile("<item id='[a-z0-9]*");
		String give = this.getItem();
		String result = null;
		Matcher ma = regExp.matcher(give);
		while (ma.find()) {
			result = ma.group();
			String[] ja = ma.group().split("<item id='");
			for (String r : ja) {
				result = r;
			}
		}
		return result;
	}

	/**
	 * Get List of persisted Items per Subscription
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public void getPersistedItems() throws XMPPException {
		Iterable<Subscription> id = (Iterable<Subscription>) getNode(topicID)
				.getSubscriptions();
		for (Subscription i : id) {
			System.out.println("SubscriptionID: " + i.getId());
			System.out.println("ItemResult: "
					+ ((LeafNode) getNode(topicID)).getItems(i.getId()));
		}
	}

	/**
	 * Get the subscriptions currently associated with this node.
	 * 
	 * @param topicID
	 * @return
	 * @throws XMPPException
	 */
	public String getSubscriptions() throws XMPPException {
		Iterable<Subscription> id = (Iterable<Subscription>) getNode(topicID)
				.getSubscriptions();
		String subs = null;
		for (Subscription i : id) {
			subs = i.getId();
			// System.out.println("SubscriptionID: " + i.getId());
		}
		return subs;
	}

	/**
	 * Only the Owner of the Node has permission for deletion
	 * 
	 * @param topicID
	 * @throws XMPPException
	 */
	public void delAllItems() throws XMPPException {
		((LeafNode) getNode(topicID)).deleteAllItems();
	}
	
	public void sendProfil(){
		
	}
	
}