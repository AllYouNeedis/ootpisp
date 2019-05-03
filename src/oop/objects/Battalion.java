package oop.objects;

import java.util.ArrayList;
import java.util.HashMap;


public class Battalion {
    private ArrayList<Object> composition;

    public Battalion(ArrayList<Object> data) {
//        composition = new ArrayList<>();
        composition = data;
//        data.forEach((n) -> composition.add(n));
    }

    public void setComposition(ArrayList<Object> composition) {
        this.composition = composition;
    }

    public ArrayList<Object> getComposition() {
        return composition;
    }
}
