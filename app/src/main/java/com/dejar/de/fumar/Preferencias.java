package com.dejar.de.fumar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Preferencias extends PreferenceActivity {
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        addPreferencesFromResource(R.xml.preferences);
 
        Preference fooBarPref = (Preference) findPreference("preReset");
        fooBarPref.setOnPreferenceClickListener(new OnPreferenceClickListener(){
            public boolean onPreferenceClick(Preference preference)
            {
            	
            	AlertDialog.Builder alert = new AlertDialog.Builder(Preferencias.this);

            	alert.setTitle("Reset");
            	
            	alert.setMessage(getString(R.string.pre_compartir_titulo));
            	
            	//alert.setIcon(R.drawable.logo);
            	
            	alert.setPositiveButton("Si",new DialogInterface.OnClickListener() {
            	  public void onClick(DialogInterface dialog, int id) 
            	  {
          			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Preferencias.this);
        			SharedPreferences.Editor editor = preferences.edit();

        			editor.putString("cig_por_dia", "");
        			editor.putString("precio", "");
        			editor.putString("cantidad", "");
        			editor.putString("fecha", "");
        			editor.commit();
            			
    				Intent intent = new Intent(Preferencias.this,TusDatos.class);
    	    	    Bundle b = new Bundle();
    	    	    b.putString("Actualizar", "NO"); 
    	    	    intent.putExtras(b);    				
    				startActivity(intent);
            	  }
            	 });
            	
            	alert.setNegativeButton("No",
            	 new DialogInterface.OnClickListener() {
            	  public void onClick(DialogInterface dialog, int id) {
            	   //Toast.makeText(Preferencias.this, "Fail", Toast.LENGTH_SHORT).show();
            	  }
            	 });

            	alert.show();
            	
                return false;
            }
      });
        
        
        Preference preComp = (Preference) findPreference("preCompartir");
        preComp.setOnPreferenceClickListener(new OnPreferenceClickListener(){
            public boolean onPreferenceClick(Preference preference)
            {
            	
		    	String texto;
		    	texto = getString(R.string.prin_comp_titulo)+"\n";
		    	texto = texto + getString(R.string.pre_compartir_apli)+"\n";
		    	texto = texto +"https://play.google.com/store/apps/details?id=com.dejar.de.fumar";
		    	
			    Intent sendIntent = new Intent();
			    sendIntent.setAction(Intent.ACTION_SEND);
			    sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
			    sendIntent.setType("text/plain");
			    //sendIntent.setType("text/html");
			    startActivity(sendIntent);
            	
                return false;
            }
      });
        
        Preference preActualizar = (Preference) findPreference("preActualizar");
        preActualizar.setOnPreferenceClickListener(new OnPreferenceClickListener(){
            public boolean onPreferenceClick(Preference preference)
            {
    	       Intent intent = new Intent(Preferencias.this,TusDatos.class);
    	       Bundle b = new Bundle();
    	       b.putString("Actualizar", "SI"); 
    	       intent.putExtras(b);
    		   startActivity(intent);
               finish();
               
               return false;
            }
      });
    }
}