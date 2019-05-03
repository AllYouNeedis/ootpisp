package oop.objects;

import java.util.ArrayList;

public class Battalion {
    private String name;
    private ArrayList<Object> composition;

    public Battalion(ArrayList<Object> data, String name) {
        composition = data;
        this.name = name;
    }

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
