package oop.factories;

import oop.CreatableObjects;
import oop.objects.Catapult;

import java.text.ParseException;
import java.util.HashMap;

public class CatapultMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            int Number = Integer.parseInt(data.get(0).toString());
            int FiringDistance = Integer.parseInt(data.get(1).toString());
            int FiringSpeed = Integer.parseInt(data.get(2).toString());
            return new Catapult(Number,FiringDistance,FiringSpeed);
        } catch (Exception e) {
            return -1;
        }
    }
}
