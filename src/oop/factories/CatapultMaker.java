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

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            Catapult object = (Catapult)(data.get(0));
            int Number = Integer.parseInt(data.get(1).toString());
            int FiringDistance = Integer.parseInt(data.get(2).toString());
            int FiringSpeed = Integer.parseInt(data.get(3).toString());
            object.setNumber(Number);
            object.setFiringDistance(FiringDistance);
            object.setFiringSpeed(FiringSpeed);
        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

}
