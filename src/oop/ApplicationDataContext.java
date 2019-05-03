package oop;

import java.util.ArrayList;


public class ApplicationDataContext {
    private ArrayList<Object> objects;

    ApplicationDataContext() {
        objects = new ArrayList<>();
    }
    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void addElement(Object element) {
        objects.add(element);
    }

    public void removeElement(int Index) {
        objects.remove(Index);
    }
}
