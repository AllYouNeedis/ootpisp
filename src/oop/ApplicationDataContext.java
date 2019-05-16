package oop;

import java.io.Serializable;
import java.util.ArrayList;


public class ApplicationDataContext implements Serializable {
    private ArrayList<Object> objects;

    public ApplicationDataContext() {this.objects = new ArrayList<>();}
    public ArrayList<Object> getObjects() {
        return objects;
    }
    public void setObjects(ArrayList<Object> objects) {this.objects = objects;}

    public void removeElement(int Index) {
        objects.remove(Index);
    }
}
