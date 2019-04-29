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
            boolean IsCaptain = (boolean) data.get(6);
            int SpearLength = Integer.parseInt(data.get(7).toString());
            return new Spearman(Name,Surname,Age,Sex,ServiceTime,Salary,IsCaptain,SpearLength);
        } catch (Exception e) {
            return -1;
        }
    }
}
