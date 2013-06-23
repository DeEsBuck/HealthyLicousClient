package com.eis.healthylicous;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Zweitagesplan extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ernaehrungszweitagesplan);
	}
    public void onNavButtonClick(final View view) {
    	switch (view.getId()) {
    	case R.id.btn_kalorien:
    		startActivity(new Intent(this, Kalorien.class));
    		break;
    	case R.id.nbt_einstellungen:
    		startActivity(new Intent(this, Einstellungen.class));
    		break;
    	case R.id.nbt_Tag:
    		startActivity(new Intent(this, Ernaehrungsplan.class));
    		break;
    	case R.id.nbt_Zweitage:
    		startActivity(new Intent(this, Zweitagesplan.class));
    		break;
    	case R.id.nbt_Woche:
    		startActivity(new Intent(this, Wochenplan.class));
    		break;
    		
    	}
    }
}
