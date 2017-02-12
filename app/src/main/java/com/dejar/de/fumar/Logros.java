package com.dejar.de.fumar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Logros extends Activity {
	/** Called when the activity is first created. */

	private AdView adView;
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logros);

		
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
	    
	    
        ArrayList<ItemDetails> image_details = GetSearchResults();
        
        final ListView lv1 = (ListView) findViewById(R.id.listView);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));
        
    }
    
    private ArrayList<ItemDetails> GetSearchResults(){
    	ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();

    	SharedPreferences prefs;
    	int xcantidad;
    	float xahorro;
    	
		prefs = PreferenceManager.getDefaultSharedPreferences(Logros.this); 
			
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		Date hoy = new Date();
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha_entrada = null;
		try 
		{
			fecha_entrada = formatoDelTexto.parse(prefs.getString("fecha", ""));
		}
		catch (ParseException ex) 
		{
			ex.printStackTrace();
		}
		
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
		int anio = Integer.parseInt(dateFormat1.format(fecha_entrada));
		
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM");
		int mes = Integer.parseInt(dateFormat2.format(fecha_entrada));

		SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd");
		int dia = Integer.parseInt(dateFormat3.format(fecha_entrada));

		
		Calendar calendar = new GregorianCalendar(anio, mes-1, dia);
		Date fecha = new java.sql.Date(calendar.getTimeInMillis());
		
		Integer diferencia = (int)(long)((hoy.getTime() - fecha.getTime() )/MILLSECS_PER_DAY);
		
		xcantidad = Integer.parseInt(prefs.getString("cig_por_dia", ""))*(int)diferencia;
		xahorro = (Float.parseFloat(prefs.getString("cig_por_dia", ""))/Float.parseFloat(prefs.getString("cantidad", "")))*Float.parseFloat(prefs.getString("precio", ""))*diferencia;
		
		
		String xdivisas = prefs.getString("preDivisa", "$");
		
    	ItemDetails item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo1));
    	item_details.setItemDescription(getString(R.string.logro_0));
    	if (diferencia>=1)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(1);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo1));
    	//item_details.setItemDescription("Primer "+xdivisas+" ahorrado");
    	item_details.setItemDescription(getString(R.string.logro_1).replace("#", xdivisas));
    	if (xahorro>=1)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(2);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo1));
    	item_details.setItemDescription(getString(R.string.logro_2));
    	if (diferencia>=5)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(3);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo2));
    	item_details.setItemDescription(getString(R.string.logro_3).replace("#", xdivisas));
    	if (xahorro>=10)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(4);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo2));
    	item_details.setItemDescription(getString(R.string.logro_4));
    	if (diferencia>=10)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(5);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo2));
    	item_details.setItemDescription(getString(R.string.logro_5));
    	if (diferencia>=15)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(6);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo3));
    	item_details.setItemDescription(getString(R.string.logro_6));
    	if (diferencia>=20)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(7);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo3));
    	item_details.setItemDescription(getString(R.string.logro_7));
    	if (diferencia>=30)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(8);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo4));
    	item_details.setItemDescription(getString(R.string.logro_8).replace("#", xdivisas));
    	if (xahorro>=50)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(9);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo4));
    	item_details.setItemDescription(getString(R.string.logro_9));
    	if (diferencia>=60)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(10);
    	results.add(item_details);    	

    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo4));
    	item_details.setItemDescription(getString(R.string.logro_10));
    	if (diferencia>=100)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(11);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo4));
    	item_details.setItemDescription(getString(R.string.logro_11));
    	if (diferencia>=120)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(12);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo5));
    	item_details.setItemDescription(getString(R.string.logro_12));
    	if (diferencia>=180)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(13);
    	results.add(item_details);    

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo5));
    	item_details.setItemDescription(getString(R.string.logro_13));
    	if (diferencia>=200)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(14);
    	results.add(item_details);    
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo5));
    	item_details.setItemDescription(getString(R.string.logro_14));
    	if (diferencia>=240)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(15);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo6));
    	item_details.setItemDescription(getString(R.string.logro_15).replace("#", xdivisas));
    	if (xahorro>=100)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(16);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo6));
    	item_details.setItemDescription(getString(R.string.logro_16));
    	if (diferencia>=300)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(17);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo7));
    	item_details.setItemDescription(getString(R.string.logro_17));
    	if (diferencia>=365)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(18);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo7));
    	item_details.setItemDescription(getString(R.string.logro_18));
    	if (diferencia>=420)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(19);
    	results.add(item_details);

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo7));
    	item_details.setItemDescription(getString(R.string.logro_19));
    	if (diferencia>=480)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(20);
    	results.add(item_details);
    	

    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo7));
    	item_details.setItemDescription(getString(R.string.logro_20));
    	if (diferencia>=540)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(21);
    	results.add(item_details);
    	
    	item_details = new ItemDetails();
    	item_details.setName(getString(R.string.logro_titulo8));
    	item_details.setItemDescription(getString(R.string.logro_21));
    	if (diferencia>=730)
    	{
    	   item_details.setPrice("1");
    	}
    	else
    	{
    	   item_details.setPrice("0");
    	}
    	item_details.setImageNumber(22);
    	results.add(item_details);
    	
    	return results;
    }
    
}
