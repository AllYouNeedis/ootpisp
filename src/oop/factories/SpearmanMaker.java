package oop.factories;

import oop.CreatableObjects;
import oop.objects.SexPool;
import oop.objects.Spearman;

import java.util.HashMap;

public class SpearmanMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            String Name = data.get(0).toString();
            String Surname = data.get(1).toString();
            int Age = Integer.parseInt(data.get(2).toString());
            SexPool Sex = SexPool.values()[Integer.parseInt(data.get(3).toString())];
            int ServiceTime = Integer.parseInt(data.get(4).toString());
            int Salary = Integer.parseInt(data.get(5).toString());
            int SpearLength = Integer.parseInt(data.get(6).toString());
            return new Spearman(Name,Surname,Age,Sex,ServiceTime,Salary,SpearLength);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            Spearman object = (Spearman) data.get(0);
            String name = data.get(1).toString();
            String surname = data.get(2).toString();
            int age = Integer.parseInt(data.get(3).toString());
            SexPool sex = SexPool.values()[Integer.parseInt(data.get(4).toString())];
            int serviceTime = Integer.parseInt(data.get(5).toString());
            int salary = Integer.parseInt(data.get(6).toString());
            int spearLength = Integer.parseInt(data.get(7).toString());
            object.setName(name);
            object.setSurname(surname);
            object.setAge(age);
            object.setSex(sex);
            object.setServiceTime(serviceTime);
            object.setSalary(salary);
            object.setSpearLength(spearLength);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
