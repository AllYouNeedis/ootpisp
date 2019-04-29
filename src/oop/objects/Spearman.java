package oop.objects;

public class Spearman extends Soilder {
    private int SpearLength;

    public Spearman(String name, String surname, int age, SexPool sex, int serviceTime, int salary, boolean isCaptain, int spearLength) {
        super(name, surname, age, sex, serviceTime, salary, isCaptain);
        SpearLength = spearLength;
    }

    public int getSpearLength() {
        return SpearLength;
    }

    public void setSpearLength(int spearLength) {
        SpearLength = spearLength;
    }



}
