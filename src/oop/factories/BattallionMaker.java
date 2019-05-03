package oop.factories;

import oop.CreatableObjects;
import oop.objects.Battalion;

import java.util.ArrayList;
import java.util.HashMap;

public class BattallionMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        ArrayList<Object> links = (ArrayList<Object>)(data.get(0));
        return new Battalion(links);
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        Battalion object = (Battalion)data.get(0);
        ArrayList<Object> links = (ArrayList<Object>)(data.get(1));
        object.setComposition(links);

        return 0;
    }
}
