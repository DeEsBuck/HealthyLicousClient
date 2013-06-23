package com.eis.healthylicous;

import java.io.File;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.eis.healthylicous.communication.ConnectionHandler;
import com.eis.healthylicous.communication.ConnectionTask;
import com.eis.healthylicous.communication.DataHandler;

public class Profil extends Activity {

		private static final String TAG = Profil.class.getSimpleName();
//		private static final String FILENAME = TAG + ".txt";
		private final String DATEINAME = "test.txt";
		private final String VERZEICHNIS = "testdir";
		private File verzeichnis;
		private File datei;
		public EditText name; 
		public EditText alter;
		public EditText gewicht;
		public EditText groesse;
		public RadioGroup geschlecht;
		String gender = "";
		String ausgabe = "";
		DataHandler pub;
		private static final String HOST = "192.168.0.125";
		private static final int PORT = 5222;
		public static final String TOPIC = "Kalories";
		private static final String USER = "android";
		private static final String PASSWORD = "openfire";
		Button bSave;

	
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.profil);
			// das Eingabefeld
			name = (EditText) findViewById(R.id.etName);
			alter = (EditText) findViewById(R.id.etAlter);
			gewicht = (EditText) findViewById(R.id.etGewicht);
			groesse = (EditText) findViewById(R.id.etGroesse);
//			geschlecht = (RadioGroup) findViewById(R.id.geschlecht);
		
			//allergien fehlen noch
			
			// Leeren
//			final Button bClear = (Button) findViewById(R.id.clear);
//			bClear.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					edit.setText("");
//				}
//			});
//			// Laden
//			final Button bLoad = (Button) findViewById(R.id.load);
//			bLoad.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					edit.setText(load());
//				}
//			});
			
			// Speichern
			final Button bSave = (Button) findViewById(R.id.bSave);
			bSave.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {	
					 //get selected radio button from radioGroup
					   RadioGroup radioGroup = (RadioGroup) findViewById(R.id.geschlecht);
					   int selectedId = radioGroup.getCheckedRadioButtonId();
					 //find the radio button by returned id
					   RadioButton osButton = (RadioButton) findViewById(selectedId);
					 gender = osButton.getText().toString();
					   
//					ProfilTask conTask = new ProfilTask();
//					conTask.execute(USER , PASSWORD, gender); 
					   
					   
					ausgabe += "<profil xmlns=\"http://www.example.org/profil\" ";	
					ausgabe += "user='" + name.getText() + "' >";
					ausgabe += "<alter>" + alter.getText() + "</alter> \n";
					ausgabe += "<gewicht metric='kg'>" + gewicht.getText() + "</gewicht> \n";
					ausgabe += "<groesse metric='cm'>" + groesse.getText() + "</groesse> \n";
					ausgabe += "<geschlecht>" + osButton.getText() + "</geschlecht> \n";
					ausgabe += "</profil>";
					Log.d("Profil", ausgabe);
//					( "Profile" , pub.setProfile(name.getText().toString(), alter.getText().toString(), gewicht.getText().toString(), groesse.getText().toString(), "female"));		
					
					SimplePayload payload = new SimplePayload("profil",	"http://www.example.org/profil", "tada");
					PayloadItem<SimplePayload> payloaditem = new PayloadItem<SimplePayload>(null, payload);
					try {
						MainActivity.connection.publishPayload( "Profile" , payloaditem);
					} catch (XMPPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					File verzeichnis = getDir(VERZEICHNIS, MODE_PRIVATE);	
//					File datei = new File(verzeichnis, DATEINAME);
					
					MainActivity.connection.publishItem();
//					pub = new DataHandler();
//					Log.d("pub","Datahandler");
//					if (!con.doesNodeExist("Profile")){
//					con.createTopic("Profile");
//					}
//					else {
//					Menu.ct.deleteTopic("Profile");
//					Menu.ct.createTopic("Profile");
//					PubSubManager mgr = new PubSubManager(Menu.ct, "pubsub."+xmppcon.getServiceName());
//					Node n = mgr.getNode("Profile");
//					((LeafNode)n).publish(pub.setProfile(name.getText().toString(), alter.getText().toString(), gewicht.getText().toString(), groesse.getText().toString(), gender));
//					Menu.ct.publishPayload( "Profile" , pub.setProfile(name.getText().toString(), alter.getText().toString(), gewicht.getText().toString(), groesse.getText().toString(), gender));
//					Log.d("Payload","bla");
//					Menu.ct.disconnect();
//					Log.d("Profil","gesendetdisconnect");
//					}			
					
				}
					
					
//							try {
//								FileInputStream fi=new FileInputStream(datei);		
//								BufferedReader fileInBuffer= new BufferedReader(new InputStreamReader(fi));
//								String zeile="";
//							
//							try {
//								while((zeile=fileInBuffer.readLine()) != null){
//									String inputXML = zeile;
//								}
//							} catch (IOException e) {
//								e.printStackTrace();
//							}finally{
//								try {
//									fileInBuffer.close();
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//								Log.d("File", "save");
//							}
//							
//						} catch (FileNotFoundException e1) {
//							e1.printStackTrace();
//						}
				
			});
		
		}

	}
	