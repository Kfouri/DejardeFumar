package com.dejar.de.fumar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Principal extends Activity {

	 private TextView mfecha;
	 private TextView mdias;
	 private TextView mcantidad;
	 private TextView mahorrado;
	 private AdView adView;
	 
	 SharedPreferences prefs;
	 long diferencia;
	 int xcantidad;
	 float xahorro;
	 String xdivisas;
	 DecimalFormat df;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal);
	
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
		
		mfecha = (TextView) findViewById(R.id.fecha);
		mdias =  (TextView) findViewById(R.id.dias);
		mcantidad =  (TextView) findViewById(R.id.cantidad);
		mahorrado =  (TextView) findViewById(R.id.ahorrado);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(Principal.this); 
		
		mfecha.setText(prefs.getString("fecha", ""));
		
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
		
		xdivisas = prefs.getString("preDivisa", "$");
		
		diferencia = (hoy.getTime() - fecha.getTime() )/MILLSECS_PER_DAY;
		diferencia = diferencia+1;
		
		mdias.setText(""+diferencia+" "+getString(R.string.dias));
		xcantidad = Integer.parseInt(prefs.getString("cig_por_dia", ""))*(int)diferencia;
		
		mcantidad.setText(""+xcantidad+" "+getString(R.string.cigarrillos));
					    
	    xahorro = (Float.parseFloat(prefs.getString("cig_por_dia", ""))/Float.parseFloat(prefs.getString("cantidad", "")))*Float.parseFloat(prefs.getString("precio", ""))*diferencia;
	    
	    df = new DecimalFormat("###.##");
	    	    
	    mahorrado.setText(""+df.format(xahorro)+xdivisas);

	    	    
		ImageView img1 = (ImageView) findViewById(R.id.compartir);
		img1.setOnClickListener(new OnClickListener() 
		{
		    public void onClick(View v) 
		    {
		    	String texto;
		    	texto = getString(R.string.prin_comp_titulo)+"\n";
		    	texto = texto +getString(R.string.prin_comp_fumo)+prefs.getString("fecha", "")+"\n";
		    	texto = texto +getString(R.string.prin_comp_dia_sin)+diferencia+"\n";
		    	texto = texto +getString(R.string.prin_comp_cig_no)+xcantidad+"\n";
		    	texto = texto +getString(R.string.prin_comp_dinero)+df.format(xahorro)+xdivisas+" \n";
		    	texto = texto +"https://play.google.com/store/apps/details?id=com.dejar.de.fumar";
		    	
			    Intent sendIntent = new Intent();
			    sendIntent.setAction(Intent.ACTION_SEND);
			    sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
			    sendIntent.setType("text/plain");
			    //sendIntent.setType("text/html");
			    startActivity(sendIntent);
		    }
		});

	}
 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
