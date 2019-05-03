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
            String Name = data.get(0).toString();
            String Surname = data.get(1).toString();
            int Age = Integer.parseInt(data.get(2).toString());
            SexPool Sex = SexPool.values()[Integer.parseInt(data.get(3).toString())];
            int ServiceTime = Integer.parseInt(data.get(4).toString());
            int Salary = Integer.parseInt(data.get(5).toString());
            int FiringDistance = Integer.parseInt(data.get(6).toString());
            int FiringSpeed = Integer.parseInt(data.get(7).toString());
            return new Archer(Name,Surname,Age,Sex,ServiceTime,Salary,FiringDistance,FiringSpeed);
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
            String name = data.get(1).toString();
            String surname = data.get(2).toString();
            int age = Integer.parseInt(data.get(3).toString());
            SexPool sex = SexPool.values()[Integer.parseInt(data.get(4).toString())];
            int serviceTime = Integer.parseInt(data.get(5).toString());
            int salary = Integer.parseInt(data.get(6).toString());
            int firingDistance = Integer.parseInt(data.get(7).toString());
            int firingSpeed = Integer.parseInt(data.get(8).toString());
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
