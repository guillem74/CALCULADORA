package dsa.eetac.upc.edu.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.*;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    private List<Operation> list;
    private List<String> l;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        /*Bundle extra = getIntent().getExtras();
        if (extra == null){
        }
        else{
            Operations ops = (Operations) extra.get("list");
            list = ops.getOpsList(); //obtener la lista
            System.out.println();
        }*/
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
                String selectedmovie=list.get(position);
                Toast.makeText(getApplicationContext(), "Movie Selected : "+selectedmovie,   Toast.LENGTH_LONG).show();
            }
        });*/
    }


}