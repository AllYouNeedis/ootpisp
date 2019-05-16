package oop.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Battalion implements Serializable {
    private String name;
    private ArrayList<Object> composition;

    public Battalion(ArrayList<Object> data, String name) {
        composition = data;
        this.name = name;
    }

    public Battalion(){}

    public void setComposition(ArrayList<Object> composition) {
        this.composition = composition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Object> getComposition() {
        return composition;
    }
}
