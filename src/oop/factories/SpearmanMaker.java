package oop.factories;

import oop.CreatableObjects;
import oop.objects.SexPool;
import oop.objects.Spearman;

import java.util.HashMap;

public class SpearmanMaker implements Creatable {
    @Override
    public Object createObject(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 7)
                return -1;
            String name = data.get(0).toString();
            if (!name.matches("[а-яёА-ЯЁ]+"))
                return -1;
            String surname = data.get(1).toString();
            if (!surname.matches("[а-яёА-ЯЁ]+"))
                return -1;
            int age = Integer.parseInt(data.get(2).toString());
            if (age < 16 || age > 100)
                return -1;
            SexPool sex = SexPool.valueOf(data.get(3).toString());
            int serviceTime = Integer.parseInt(data.get(4).toString());
            if (serviceTime < 0)
                return -1;
            int salary = Integer.parseInt(data.get(5).toString());
            if (salary < 0)
                return -1;
            int spearLength = Integer.parseInt(data.get(6).toString());
            if (spearLength < 0)
                return -1;
            return new Spearman(name,surname,age,sex,serviceTime,salary,spearLength);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int setObjectFields(CreatableObjects E, HashMap<Integer, Object> data) {
        try {
            if (data.size() != 8)
                return -1;
            Spearman object = (Spearman) data.get(0);
            String name = data.get(1).toString();
            if (!name.matches("[а-яёА-ЯЁ]+"))
                return -1;
            String surname = data.get(2).toString();
            if (!surname.matches("[а-яёА-ЯЁ]+"))
                return -1;
            int age = Integer.parseInt(data.get(3).toString());
            if (age < 16 || age > 100)
                return -1;
            SexPool sex = SexPool.valueOf(data.get(4).toString());
            int serviceTime = Integer.parseInt(data.get(5).toString());
            if (serviceTime < 0)
                return -1;
            int salary = Integer.parseInt(data.get(6).toString());
            if (salary < 0)
                return -1;
            int spearLength = Integer.parseInt(data.get(7).toString());
            if (spearLength < 0)
                return -1;
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
