package oop.factories;

import oop.CreatableObjects;
import oop.objects.Catapult;

import java.util.HashMap;

public class CatapultMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 3)
                return -1;
            int number = Integer.parseInt(data.get(0).toString());
            if (number < 0)
                return -1;
            int firingDistance = Integer.parseInt(data.get(1).toString());
            if (firingDistance < 0)
                return -1;
            int firingSpeed = Integer.parseInt(data.get(2).toString());
            if (firingSpeed < 0)
                return -1;
            return new Catapult(number,firingDistance,firingSpeed);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 4)
                return -1;
            Catapult object = (Catapult)(data.get(0));
            int number = Integer.parseInt(data.get(1).toString());
            if (number < 0)
                return -1;
            int firingDistance = Integer.parseInt(data.get(2).toString());
            if (firingDistance < 0)
                return -1;
            int firingSpeed = Integer.parseInt(data.get(3).toString());
            if (firingSpeed < 0)
                return -1;
            object.setNumber(number);
            object.setFiringDistance(firingDistance);
            object.setFiringSpeed(firingSpeed);
        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

}
