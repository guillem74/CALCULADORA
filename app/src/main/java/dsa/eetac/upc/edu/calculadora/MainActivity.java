package dsa.eetac.upc.edu.calculadora;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String tag = "Main:"; // tag que indica el ciclo de vida de la app
    String operacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "Event onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "Event onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "Event onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "Event onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "Event onStop()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "Event onRestart()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "Event onDestroy()");

    }

    //RadioGroup opcions
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        operacio = null;
        switch (view.getId()) {
            case R.id.division:
                if (checked)
                    // divisió
                    operacio = "/";
                break;
            case R.id.multiplicacion:
                if (checked)
                    // multiplicació
                    operacio = "*";
                break;
            case R.id.resta:
                if (checked)
                    // resta
                    operacio = "-";
                break;
            case R.id.suma:
                if (checked)
                    // suma
                    operacio = "+";
                break;
        }
        Log.d(tag, "operacio: " +operacio);
    }

    public void setValuesToCero(View view){

        EditText texto1 = (EditText) findViewById(R.id.num1);
        texto1.setText("0");
        EditText texto2 = (EditText) findViewById(R.id.num2);
        texto2.setText("0");
        EditText texto3 = (EditText) findViewById(R.id.res);
        texto3.setText("0");

        Context context = getApplicationContext();
        CharSequence text = "valores borrados";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void getOperacion(View view){

        try {
            EditText editTextnum1 = (EditText) findViewById(R.id.num1);
            float numero1 = Float.parseFloat(editTextnum1.getText().toString());
            Log.d(tag, "num1: "+numero1);
            EditText editTextnum2 = (EditText) findViewById(R.id.num2);
            float numero2 = Float.parseFloat(editTextnum2.getText().toString());
            Log.d(tag, "num1: "+numero2);

            float sol = 0;
            if ("/".equals(operacio)) {
                sol = numero1 / numero2;
            }
            if ("*".equals(operacio)) {
                sol = numero1 * numero2;
            }
            if ("-".equals(operacio)) {
                sol = numero1 - numero2;
            }
            if ("+".equals(operacio)) {
                sol = numero1 + numero2;
            }
            Log.d(tag, "Sol: "+sol);
            EditText editTextRes = (EditText) findViewById(R.id.res);
            editTextRes.setText("" + sol);
        } catch (Exception e) {
            //Llancem un TOAST com a missatge per indicar que falta un numero per insertar.
            //No ho fem en cas de que sigui decimal ja que num1 i num dos estan declarats de tipus int
            //i al textview que explica l'aplicació ja diu que han de introduir nombres enters

            Context context = getApplicationContext();
            CharSequence text = "Cal indicar els dos valors numèrics";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

    }

    public void goToListaOperaciones(View view){
        Intent inb1 = new Intent(MainActivity.this,HistorialActivity.class);
        startActivity(inb1);
    }


}