package oop.objects;

import java.io.Serializable;

public abstract class Soilder extends Human implements Serializable {
    private int ServiceTime;
    private int Salary;

    public Soilder(String name, String surname, int age, SexPool sex, int serviceTime, int salary) {
        super(name, surname, age, sex);
        ServiceTime = serviceTime;
        Salary = salary;
    }

    public Soilder(){}

    public int getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(int serviceTime) {
        ServiceTime = serviceTime;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

}
