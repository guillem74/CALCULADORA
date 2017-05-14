package dsa.eetac.upc.edu.calculadora;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OperationManager extends AppCompatActivity {

    private String tag="Events";
    private String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_manager);
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            operation=extra.getString("op");
        }
        TextView op= (TextView)findViewById(R.id.op);
        op.setText(operation);
    }

    public void onStart() {
        super.onStart();
        Log.d(tag, "event onStart()");

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
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
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first
        Log.d(tag, "event onRestart()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass
        // Stop method tracing that the activity started during onCreate()
        Log.d(tag, "event onDestroy()");
        Debug.stopMethodTracing();
    }

    //editar operación
    public void edit(View v){
        Intent intent = getIntent();
        intent.putExtra("op",operation);
        setResult(200, intent);
        finish();
    }

    //borrar operacion del historial
    public void delete(View v){
        Intent intent = getIntent();
        intent.putExtra("op",operation);
        setResult(201, intent);
        finish();
    }
}