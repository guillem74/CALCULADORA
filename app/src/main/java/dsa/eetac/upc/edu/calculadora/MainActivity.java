package dsa.eetac.upc.edu.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.os.Debug;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String tag="Events";
    private List<Operation> opsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "Event a onCreate()");
        opsList=new ArrayList<>();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(tag,"Event a onStart()");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d(tag,"Event a onTetart()");
    }


    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
       /* try {
            Bundle extra = getIntent().getExtras();
            String ex=extra.getString("borra");
            if (ex == "ok") {
                operaciones = null;
            }
        }
        catch (Exception e){}***/
        Log.d(tag, "event onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Log.d(tag, "event onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first
        Log.d(tag, "event onStop()");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass
        // Stop method tracing that the activity started during onCreate()
        Log.d(tag, "event onDestroy()");
        Debug.stopMethodTracing();
    }

    public void result(View v){
        EditText result = (EditText) findViewById(R.id.result);//Obtenemos el valor del textView result
        EditText num1 = (EditText) findViewById(R.id.num1);//Obtenemos los valores de los campos donde hemos introducido los valores
        EditText num2 = (EditText) findViewById(R.id.num2);
        int n1=0;
        int n2=0;
        int res = 0;

        try {//probamos de obtener valores para detectar si los campos están vacíos
            n1 = Integer.parseInt(num1.getText().toString());
            n2 = Integer.parseInt(num2.getText().toString());
            RadioGroup rg=(RadioGroup) findViewById(R.id.operations);//obtenemos la operacion
            int seleccion=rg.getCheckedRadioButtonId();//obtenemos el id del radiobutton seleccionado
            RadioButton rdio=(RadioButton) findViewById(seleccion);//creamos un radio button nuevo a partir de esa id
            String op=rdio.getText().toString();//obtenemos el texto de ese radiobutton
            StringBuilder sol=new StringBuilder();//creamos el StringBuilder que contendrá la solución

            if (op.equals("+")){//buscamos la solución y realizamos la operacion correspondiente
                sol.append(n2+n1);
                res = n2+n1;
                opsList.add(new Operation(n1,n2,res,op));

            }
            if (op.equals("-")){
                sol.append(n1-n2);//
                res = n1-n2;
                opsList.add(new Operation(n1,n2,res,op));
            }
            if (op.equals("*")){
                sol.append(n2*n1);
                res = n2*n1;
                opsList.add(new Operation(n1,n2,res,op));
            }
            if (op.equals("/")){
                if (n1 == 0){
                    sol.append("Infinity");
                }
                else{
                    if (n1%n2 != 0){
                        sol.append("0");
                        Toast.makeText(getApplicationContext(),"Solo hacer divisiones enteras",Toast.LENGTH_LONG).show();
                    }
                    else{
                        sol.append(n1 / n2);
                        res = n1/n2;
                        opsList.add(new Operation(n1,n2,res,op));
                    }
                }
            }
            result.setText(sol);
        }
        catch(Exception e){  //Detectamos si alguno de los dos esta vacio
            Toast.makeText(getApplicationContext(),"Campos vacíos o formato incorrecto",Toast.LENGTH_LONG).show();
        }

    }

    public void reset(View v){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        EditText result = (EditText) findViewById(R.id.result);
        num1.setText("");
        num2.setText("");
        result.setText("");
    }

    public void history(View v){
        Intent history = new Intent (getApplicationContext(),History.class);
        history.putExtra("list",(Serializable)opsList); //nombre y valor
        startActivityForResult(history,100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if((requestCode == 100) && (resultCode == 222)){
            this.opsList.clear();
            EditText num1 = (EditText) findViewById(R.id.num1);
            EditText num2 = (EditText) findViewById(R.id. num2);
            EditText result = (EditText) findViewById(R.id.result);
            num1.setText("");
            num2.setText("");
            result.setText("");

        }
    }
}