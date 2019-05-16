package oop.objects;

import java.io.Serializable;

public abstract class Human implements Serializable {
    private String Name;
    private String Surname;
    private int Age;
    private SexPool Sex;

    public Human(String name, String surname, int age, SexPool sex) {
        Name = name;
        Surname = surname;
        Age = age;
        Sex = sex;
    }

    public Human(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public SexPool getSex() {
        return Sex;
    }

    public void setSex(SexPool sex) {
        Sex = sex;
    }
}
