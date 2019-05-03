package oop.factories;

import oop.CreatableObjects;
import oop.objects.SexPool;
import oop.objects.Swordman;

import java.util.HashMap;

public class SwordmanMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 7)
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
            int swordHoldRank = Integer.parseInt(data.get(6).toString());
            if (swordHoldRank < 0)
                return -1;
            return new Swordman(name,surname,age,sex,serviceTime,salary,swordHoldRank);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 8)
                return -1;
            Swordman object = (Swordman) data.get(0);
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
            int swordHoldRank = Integer.parseInt(data.get(6).toString());
            if (swordHoldRank < 0)
                return -1;
            object.setName(name);
            object.setSurname(surname);
            object.setAge(age);
            object.setSex(sex);
            object.setServiceTime(serviceTime);
            object.setSalary(salary);
            object.setSwordHoldRank(swordHoldRank);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
