package oop.objects;

public class Swordman extends Soilder {
    private int SwordHoldRank;

    public Swordman(String name, String surname, int age, SexPool sex, int serviceTime, int salary, boolean isCaptain, int swordHoldRank) {
        super(name, surname, age, sex, serviceTime, salary, isCaptain);
        SwordHoldRank = swordHoldRank;
    }

    public int getSwordHoldRank() {
        return SwordHoldRank;
    }

    public void setSwordHoldRank(int swordHoldRank) {
        SwordHoldRank = swordHoldRank;
    }

}
