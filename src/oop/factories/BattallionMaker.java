package oop.factories;

import oop.CreatableObjects;
import oop.objects.Battalion;

import java.util.ArrayList;
import java.util.HashMap;

public class BattallionMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 2)
                return -1;
            String name = data.get(0).toString();
            ArrayList<Object> links = (ArrayList<Object>)(data.get(1));
            return new Battalion(links,name);
        } catch (Exception e) {
            return -1;
        }

    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 3)
                return -1;
            Battalion object = (Battalion)data.get(0);
            String name = data.get(1).toString();
            ArrayList<Object> links = (ArrayList<Object>)(data.get(2));
            object.setComposition(links);
            object.setName(name);
        }   catch (Exception e) {
            return -1;
        }


        return 0;
    }
}
