package oop.factories;

import oop.CreatableObjects;
import oop.objects.Archer;
import oop.objects.SexPool;

import java.util.HashMap;

public class ArcherMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 8)
                return -1;
            String name = data.get(0).toString();
            if (name.matches("\\d+"))
                return -1;
            String surname = data.get(1).toString();
            if (surname.matches("\\d+"))
                return -1;
            int age = Integer.parseInt(data.get(2).toString());
            if (age < 16 || age > 100)
                return -1;
            SexPool sex = SexPool.values()[Integer.parseInt(data.get(3).toString())];
            int serviceTime = Integer.parseInt(data.get(4).toString());
            if (serviceTime < 0)
                return -1;
            int salary = Integer.parseInt(data.get(5).toString());
            if (salary < 0)
                return -1;
            int firingDistance = Integer.parseInt(data.get(6).toString());
            if (firingDistance < 0)
                return -1;
            int firingSpeed = Integer.parseInt(data.get(7).toString());
            if (firingSpeed < 0)
                return -1;
            return new Archer(name,surname,age,sex,serviceTime,salary,firingDistance,firingSpeed);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 9)
                return -1;
            Archer object = (Archer) data.get(0);
            String name = data.get(0).toString();
            if (name.matches("\\d+"))
                return -1;
            String surname = data.get(1).toString();
            if (surname.matches("\\d+"))
                return -1;
            int age = Integer.parseInt(data.get(2).toString());
            if (age < 16 || age > 100)
                return -1;
            SexPool sex = SexPool.values()[Integer.parseInt(data.get(3).toString())];
            int serviceTime = Integer.parseInt(data.get(4).toString());
            if (serviceTime < 0)
                return -1;
            int salary = Integer.parseInt(data.get(5).toString());
            if (salary < 0)
                return -1;
            int firingDistance = Integer.parseInt(data.get(6).toString());
            if (firingDistance < 0)
                return -1;
            int firingSpeed = Integer.parseInt(data.get(7).toString());
            if (firingSpeed < 0)
                return -1;
            object.setName(name);
            object.setSurname(surname);
            object.setAge(age);
            object.setSex(sex);
            object.setServiceTime(serviceTime);
            object.setSalary(salary);
            object.setFiringDistance(firingDistance);
            object.setFiringSpeed(firingSpeed);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
