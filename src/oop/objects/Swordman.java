package oop.objects;

public class Swordman extends Soilder {
    private int SwordHoldRank;

    public Swordman(String name, String surname, int age, SexPool sex, int serviceTime, int salary, int swordHoldRank) {
        super(name, surname, age, sex, serviceTime, salary);
        SwordHoldRank = swordHoldRank;
    }

    public int getSwordHoldRank() {
        return SwordHoldRank;
    }

    public void setSwordHoldRank(int swordHoldRank) {
        SwordHoldRank = swordHoldRank;
    }

}
