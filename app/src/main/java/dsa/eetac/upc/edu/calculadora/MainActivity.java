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
    private EditText num1;
    private EditText num2;
    private EditText result;
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "Event a onCreate()");
        opsList=new ArrayList<>();
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        result = (EditText) findViewById(R.id.result);
        rg=(RadioGroup) findViewById(R.id.operations);
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
        int n1=0;
        int n2=0;
        int res = 0;

        try {//probamos de obtener valores para detectar si los campos están vacíos
            n1 = Integer.parseInt(num1.getText().toString());
            n2 = Integer.parseInt(num2.getText().toString());
            int selected=rg.getCheckedRadioButtonId();//obtenemos el id del radiobutton seleccionado
            RadioButton rdio=(RadioButton) findViewById(selected);//creamos un radio button nuevo a partir de esa id
            String op=rdio.getText().toString();//obtenemos el texto de ese radiobutton
            StringBuilder sol=new StringBuilder();//creamos el StringBuilder que contendrá la solución

            if (op.equals("+")){//buscamos la solución y realizamos la operacion correspondiente
                sol.append(n2+n1);
                res = n2+n1;

            }
            if (op.equals("-")){
                sol.append(n1-n2);//
                res = n1-n2;
            }
            if (op.equals("*")){
                sol.append(n2*n1);
                res = n2*n1;
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

                    }
                }
            }
            opsList.add(new Operation(n1,n2,res,op));
            result.setText(sol);
        }
        catch(Exception e){  //Detectamos si alguno de los dos esta vacio
            Toast.makeText(getApplicationContext(),"Campos vacíos o formato incorrecto",Toast.LENGTH_LONG).show();
        }

    }

    public void reset(View v){
        num1.setText("");
        num2.setText("");
        result.setText("");
    }

    public void history(View v){
        Intent intent = new Intent (getApplicationContext(),History.class);
        intent.putExtra("list",(Serializable)opsList); //nombre y valor
        startActivityForResult(intent,100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 100){
            if (resultCode == 222) {//borrar historial
                this.opsList.clear();
                Toast.makeText(getApplicationContext(),"Historial borrado",Toast.LENGTH_LONG).show();
                num1.setText("");
                num2.setText("");
                result.setText("");
            }else if(resultCode==200){//editar operacion
                int i=getIndex(data);
                try{
                    Operation temp=opsList.get(i);
                    num1.setText(Integer.toString(temp.getNum1()));
                    num2.setText(Integer.toString(temp.getNum2()));
                    result.setText(Integer.toString(temp.getResult()));
                    selectOp(temp.getOp());

                }catch(ArrayIndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }

            }else if (resultCode==201){//eliminar operacion del historial
                int i=getIndex(data);
                try{
                    opsList.remove(i);
                    Toast.makeText(getApplicationContext(),"Operación borrada del historial",Toast.LENGTH_LONG).show();
                }catch(ArrayIndexOutOfBoundsException e){
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private void selectOp(String op){
        if(op.equals("+")){
            rg.check(R.id.sum);
        }else if(op.equals("-")){
            rg.check(R.id.sub);
        }else if(op.equals("*")){
            rg.check(R.id.mult);
        }else if(op.equals("/")){
            rg.check(R.id.div);
        }
    }

    private int getIndex(Intent data){
        Bundle extra = data.getExtras();
        int i=-1;
        if (extra != null){
            i=extra.getInt("op");
        }
        return i;
    }
}