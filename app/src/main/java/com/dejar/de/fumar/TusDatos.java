package com.dejar.de.fumar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TusDatos extends Activity {

	static final int DATE_DIALOG_ID = 0;
    private int mYear;
    private int mMonth;
    private int mDay;
    private TextView mDateDisplay;
    
    private EditText cigarrillos_por_dia;
    private EditText precio;
    private EditText cantidad;
    private EditText fecha;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tusdatos);
		
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mDateDisplay = (TextView) findViewById(R.id.fecha_inicio);
        cigarrillos_por_dia = (EditText) findViewById(R.id.cigarrillos_por_dia);
        precio = (EditText) findViewById(R.id.precio);
        cantidad = (EditText) findViewById(R.id.cantidad);
        fecha = (EditText) findViewById(R.id.fecha_inicio);
		Button btset = (Button) findViewById(R.id.btn_set_date);
	
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(TusDatos.this); 
		
		Bundle b = getIntent().getExtras();
		String xActualizar = b.getString("Actualizar");
		
		if (xActualizar.equals("SI"))
		{
			cigarrillos_por_dia.setText(prefs.getString("cig_por_dia", ""));
			precio.setText(prefs.getString("precio", ""));
			cantidad.setText(prefs.getString("cantidad", ""));
			fecha.setText(prefs.getString("fecha", ""));
		}
		
		
		btset.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	showDialog(DATE_DIALOG_ID);
            }
          
        });
		
        updateDisplay();
              

		Button btcontinuar = (Button) findViewById(R.id.continuar);
		
		btcontinuar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	if (cigarrillos_por_dia.getText().toString().length()==0||cigarrillos_por_dia.getText().toString().equals("0"))
            	{
            		Toast.makeText(TusDatos.this,getString(R.string.tus_cant_cig),Toast.LENGTH_LONG).show();
            	}
            	else
            	{
            		if (precio.getText().toString().length()==0||precio.getText().toString().equals("0"))
            		{
            			Toast.makeText(TusDatos.this,getString(R.string.tus_precio),Toast.LENGTH_LONG).show();
            		}
            		else
            		{
                		if (cantidad.getText().toString().length()==0||cantidad.getText().toString().equals("0"))
                		{
                			Toast.makeText(TusDatos.this,getString(R.string.tus_cig_por_dia),Toast.LENGTH_LONG).show();
                		}
                		else
                		{
                			
                	        Date ahora = new Date();
                	        String sHoy;
                	        Date dHoy = new Date();
                	        
                	        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
                	        
                	        sHoy = formateador.format(ahora);
                	        try {
                	        	dHoy = formateador.parse(sHoy);
                			} catch (ParseException e) {
                				// TODO Auto-generated catch block
                				e.printStackTrace();
                			}
                	        
                	        Date dFecha = new Date();
                	        try {
                	        	dFecha = formateador.parse(fecha.getText().toString());
                			} catch (ParseException e) {
                				// TODO Auto-generated catch block
                				e.printStackTrace();
                			}
                	        
                			if (dFecha.after(dHoy))
                			{
                				Toast.makeText(TusDatos.this,getString(R.string.tus_fecha),Toast.LENGTH_LONG).show();
                			}
                			else
                			{
	                			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TusDatos.this);
	                			SharedPreferences.Editor editor = preferences.edit();
	
	                			editor.putString("cig_por_dia", cigarrillos_por_dia.getText().toString());
	                			editor.putString("precio", precio.getText().toString());
	                			editor.putString("cantidad", cantidad.getText().toString());
	                			editor.putString("fecha", fecha.getText().toString());
	                			editor.commit();
	                			
								Intent intent = new Intent(TusDatos.this,MenuPrincipal.class);
								
								startActivity(intent);
								TusDatos.this.finish();
                			}
                		}
            		}
            	}
            }
          
        });

	}

    // updates the date in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay).append("-")        
                    .append(mMonth + 1).append("-")
                    .append(mYear).append(" "));
    }
    
    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, 
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };
            
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
