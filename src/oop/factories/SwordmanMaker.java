package oop.factories;

import oop.CreatableObjects;
import oop.objects.SexPool;
import oop.objects.Swordman;

import java.util.HashMap;

public class SwordmanMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            String name = data.get(0).toString();
            String surname = data.get(1).toString();
            int age = Integer.parseInt(data.get(2).toString());
            SexPool sex = SexPool.values()[Integer.parseInt(data.get(3).toString())];
            int serviceTime = Integer.parseInt(data.get(4).toString());
            int salary = Integer.parseInt(data.get(5).toString());
            int swordHoldRank = Integer.parseInt(data.get(6).toString());
            return new Swordman(name,surname,age,sex,serviceTime,salary,swordHoldRank);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            Swordman object = (Swordman) data.get(0);
            String name = data.get(1).toString();
            String surname = data.get(2).toString();
            int age = Integer.parseInt(data.get(3).toString());
            SexPool sex = SexPool.values()[Integer.parseInt(data.get(4).toString())];
            int serviceTime = Integer.parseInt(data.get(5).toString());
            int salary = Integer.parseInt(data.get(6).toString());
            int swordHoldRank = Integer.parseInt(data.get(7).toString());
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
