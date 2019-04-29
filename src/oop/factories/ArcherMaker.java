package oop.factories;

import oop.CreatableObjects;
import oop.objects.Archer;
import oop.objects.SexPool;

import java.util.HashMap;

public class ArcherMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            String Name = data.get(0).toString();
            String Surname = data.get(1).toString();
            int Age = Integer.parseInt(data.get(2).toString());
            SexPool Sex = SexPool.values()[Integer.parseInt(data.get(3).toString())];
            int ServiceTime = Integer.parseInt(data.get(4).toString());
            int Salary = Integer.parseInt(data.get(5).toString());
            boolean IsCaptain = (boolean) data.get(6);
            int FiringDistance = Integer.parseInt(data.get(7).toString());
            int FiringSpeed = Integer.parseInt(data.get(8).toString());
            return new Archer(Name,Surname,Age,Sex,ServiceTime,Salary,IsCaptain,FiringDistance,FiringSpeed);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
