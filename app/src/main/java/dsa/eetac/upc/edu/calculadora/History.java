package dsa.eetac.upc.edu.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class History extends AppCompatActivity {
    private List<Operation> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Bundle extra = getIntent().getExtras();
        if (extra == null){

        }
        else{
            Operations ops = (Operations) extra.get("list");
            list = ops.getOpsList(); //obtenemos la lista
        }
    }


}
