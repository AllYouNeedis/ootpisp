package oop.objects;

import java.io.Serializable;

public class Spearman extends Soilder implements Serializable {
    private int SpearLength;

    public Spearman(String name, String surname, int age, SexPool sex, int serviceTime, int salary, int spearLength) {
        super(name, surname, age, sex, serviceTime, salary);
        this.SpearLength = spearLength;
    }

    public Spearman(){}

    public int getSpearLength() {
        return SpearLength;
    }

    public void setSpearLength(int spearLength) {
        SpearLength = spearLength;
    }



}
