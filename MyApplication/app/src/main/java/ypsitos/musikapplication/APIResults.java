package ypsitos.musikapplication;

import java.util.ArrayList;

/**
 * Created by David on 3/13/16.
 */
public class APIResults {
    private ArrayList<Field> fields;

    public void setItems(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }
}
