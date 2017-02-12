package com.dejar.de.fumar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends Activity 
{
   private final int SPLASH_TIME = 4000;
   
   private TextView xVersion;
   private TextView xFraseTitulo;
   private TextView xFrase;
   private TextView xNombreApp;
   
   @Override
   public void onCreate(Bundle icicle) 
   {
      super.onCreate(icicle);
      setContentView(R.layout.splash_layout);
      int resultado;
      
      xVersion = (TextView) findViewById(R.id.version);
      xNombreApp = (TextView) findViewById(R.id.nombre_app);
      
      xFraseTitulo = (TextView) findViewById(R.id.frase_titulo);
      xFrase = (TextView) findViewById(R.id.frase);
	  PackageInfo packageInfo = null;

	  try 
	  {
			packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
	  } 
	  catch (NameNotFoundException e) 
	  {
			e.printStackTrace();
	  }
		
	  xNombreApp.setText(getString(R.string.name_app));
      xVersion.setText("V "+packageInfo.versionName);
      
      resultado = 1 + (int)(Math.random() * ((15-1)+1));
      
      //xFrase.setVisibility(View.INVISIBLE);
     
      if (resultado==1)
      {
    	  xFrase.setText(getString(R.string.frase1));  
      }
      else if (resultado==2)
      {
    	  xFrase.setText(getString(R.string.frase2));
      }
      else if (resultado==3)
      {
    	  xFrase.setText(getString(R.string.frase3));
      }    	 
      else if (resultado==4)
      {
    	  xFrase.setText(getString(R.string.frase4));
      }
      else if (resultado==5)
      {
    	  xFrase.setText(getString(R.string.frase5));
      }
      else if (resultado==6)
      {
    	  xFrase.setText(getString(R.string.frase6));
      }
      else if (resultado==7)
      {
    	  xFrase.setText(getString(R.string.frase7));
      }
      else if (resultado==8)
      {
    	  xFrase.setText(getString(R.string.frase8));
      }
      else if (resultado==9)
      {
    	  xFrase.setText(getString(R.string.frase9));
      }
      else if (resultado==10)
      {
    	  xFrase.setText(getString(R.string.frase10));
      }
      else if (resultado==11)
      {
    	  xFrase.setText(getString(R.string.frase11));
      }
      else if (resultado==12)
      {
    	  xFrase.setText(getString(R.string.frase12));
      }
      else if (resultado==13)
      {
    	  xFrase.setText(getString(R.string.frase13));
      }
      else if (resultado==14)
      {
    	  xFrase.setText(getString(R.string.frase14));
      }      
      else if (resultado==15)
      {
    	  xFrase.setText(getString(R.string.frase15));
      }
      else
      {
    	  xFrase.setText(getString(R.string.frase1));
      }
      
      /* Handler que quitar el splash screen despus del SPLASH_TIME y crear un
      intent de la actividad principal. */
      new Handler().postDelayed(new Runnable()
      {
         @Override
         public void run() 
         {
            /* Creamos un Intent que lanzar nuestra Actividad principal (en nuestro caso Main.java)*/
            Intent miIntent = new Intent(SplashScreen.this,MenuPrincipal.class);
            SplashScreen.this.startActivity(miIntent);
            SplashScreen.this.finish();
         }
       }, SPLASH_TIME);
   }
}
 