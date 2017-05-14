package dsa.eetac.upc.edu.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.os.Debug;
import android.view.View;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    private String tag="Events";
    private List<Operation> list;
    private List<String> l;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lv = (ListView) findViewById(R.id.listV);
        Intent i = getIntent();
        list = (List<Operation>) i.getSerializableExtra("list");
        l=new ArrayList<>();
        for (int j=0; j<list.size();j++){
            String item=list.get(j).toString();
            l.add(item);
        }
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, l);
        // Set The Adapter
        lv.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        /*lv.setOnItemClickListener(new OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
            }
        });*/
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

    public void delete(View v){
        Intent delHistory = new Intent (getApplicationContext(),DeleteHistory.class);
        startActivityForResult(delHistory,101);

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Intent i= getIntent();
        if((requestCode==101)&&(resultCode== Activity.RESULT_OK)) {
            this.list.clear();
            this.l.clear();
            setResult(222, i);
            finish();
        }
    }

}