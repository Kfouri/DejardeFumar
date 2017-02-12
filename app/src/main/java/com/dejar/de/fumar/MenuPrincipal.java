package com.dejar.de.fumar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MenuPrincipal extends Activity {
	
	private static final int RESULT_SETTINGS = 1;
	private AdView adView;
	private InterstitialAd interstitial;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menuprincipal);

	    // Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId("ca-app-pub-8178489486911148/6893056719");

	    // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    RelativeLayout layout = (RelativeLayout) findViewById(R.id.ad);
	    layout.setGravity(Gravity.CENTER_HORIZONTAL);
	    layout.addView(adView);

	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	        .addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
	        .build();

	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);

	    
	    // Create the interstitial.
	    interstitial = new InterstitialAd(this);
	    interstitial.setAdUnitId("ca-app-pub-8178489486911148/8058926314");

	    // Create ad request.
	    AdRequest adRequest2 = new AdRequest.Builder().build();

	    // Begin loading your interstitial.
	    interstitial.loadAd(adRequest2);
	    
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MenuPrincipal.this); 
		
		if (prefs.getString("fecha", "").equals(""))
		{
			Intent intent = new Intent(MenuPrincipal.this,TusDatos.class);
 	        Bundle b = new Bundle();
 	        b.putString("Actualizar", "NO"); 
 	        intent.putExtras(b);
 	        displayInterstitial();
			startActivity(intent);

		}
		
		ImageView img = (ImageView) findViewById(R.id.imagePrincipal);
		img.setOnClickListener(new OnClickListener() 
		{
		    public void onClick(View v) 
		    {
				Intent intent = new Intent(MenuPrincipal.this,Principal.class);
				displayInterstitial();
				startActivity(intent);
		    }
		});

		ImageView img2 = (ImageView) findViewById(R.id.ImageMarket);
		img2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				displayInterstitial();
				Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:MAKP+Team"));
				startActivity(intent1);
			}
		});

		ImageView img3 = (ImageView) findViewById(R.id.ImageLogros);
		img3.setOnClickListener(new OnClickListener() 
		{
		    public void onClick(View v) 
		    {
		    	displayInterstitial();
				Intent intent = new Intent(MenuPrincipal.this,Logros.class);
				startActivity(intent);
		    }
		});
		
		ImageView img4 = (ImageView) findViewById(R.id.ImagePreferencias);
		img4.setOnClickListener(new OnClickListener() 
		{
		    public void onClick(View v) 
		    {
		    	displayInterstitial();
		    	Intent i = new Intent(MenuPrincipal.this, Preferencias.class);
		        startActivityForResult(i, RESULT_SETTINGS);
		    }
		});
		
		AppRater.app_launched(this);
		

	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	  // Invoke displayInterstitial() when you are ready to display an interstitial.
	  public void displayInterstitial() {
	    if (interstitial.isLoaded()) {
	      interstitial.show();
	    }
	  }
}
