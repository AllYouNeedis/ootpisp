package oop;

import oop.factories.Factory;
import oop.objects.Battalion;
import pluginInterface.CiphPluginInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectManipulator {
    private final Factory ObjectFactory = new Factory();
    private ApplicationDataContext DataContext;
    private ArrayList<CiphPluginInterface> pluginsPull = new ArrayList<>();
    public int add(CreatableObjects E, HashMap<Integer,Object> Data) {
        Object NewObject = ObjectFactory.createObject(E,Data);
        if (NewObject.equals(-1)) {
            return -1;
        } else {
            DataContext.getObjects().add(NewObject);
            return 0;
        }
    }

    public int set(CreatableObjects E,HashMap<Integer,Object> Data) {
        Object newObject = ObjectFactory.setObjectFields(E,Data);
        if (newObject.equals(-1)) {
            return -1;
        } else {
            return 0;
        }
    }

    public void setPluginsPull(ArrayList<CiphPluginInterface> pluginsPull) {
        this.pluginsPull = pluginsPull;
    }

    public ArrayList<CiphPluginInterface> getPluginsPull(){
        return pluginsPull;
    }

    ObjectManipulator(ApplicationDataContext dataContext) {
        this.DataContext = dataContext;
    }

    public ApplicationDataContext getDataContext() {
        return DataContext;
    }

    public ArrayList<Battalion> getBattalions() {
        ArrayList<Object> allObjects = DataContext.getObjects();
        ArrayList<Battalion> result = new ArrayList<>();
        for (Object object : allObjects) {
            if (object.getClass().getName().equals("oop.objects.Battalion"))
                result.add((Battalion)object);
        }
        return result;
    }

    public void setDataContext(ApplicationDataContext adc) {
        this.DataContext = adc;
    }
}
