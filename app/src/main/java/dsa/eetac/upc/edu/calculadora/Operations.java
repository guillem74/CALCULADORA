package dsa.eetac.upc.edu.calculadora;



import java.io.Serializable;
import java.util.List;

/**
 * Created by Guillem on 11/05/2017.
 */

public class Operations implements Serializable {
    private List<Operation> opsList;

    public Operations(){

    }

    public List<Operation> getOpsList() {
        return opsList;
    }

    public void setOpsList(List<Operation> opsList) {
        this.opsList = opsList;
    }
}