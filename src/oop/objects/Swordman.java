package oop.objects;

import java.io.Serializable;

public class Swordman extends Soilder implements Serializable {
    private int SwordHoldRank;

    public Swordman(String name, String surname, int age, SexPool sex, int serviceTime, int salary, int swordHoldRank) {
        super(name, surname, age, sex, serviceTime, salary);
        SwordHoldRank = swordHoldRank;
    }

    public Swordman(){}

    public int getSwordHoldRank() {
        return SwordHoldRank;
    }

    public void setSwordHoldRank(int swordHoldRank) {
        SwordHoldRank = swordHoldRank;
    }

}
