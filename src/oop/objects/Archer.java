package oop.objects;

import java.io.Serializable;

public class Archer extends Soilder implements Serializable {
    private int FiringDistance;
    private int FiringSpeed;

    public Archer(String name, String surname, int age, SexPool sex, int serviceTime, int salary, int firingDistance, int firingSpeed) {
        super(name, surname, age, sex, serviceTime, salary);
        FiringDistance = firingDistance;
        FiringSpeed = firingSpeed;
    }

    public Archer() {

    }

    public int getFiringDistance() {
        return FiringDistance;
    }

    public void setFiringDistance(int firingDistance) {
        FiringDistance = firingDistance;
    }

    public int getFiringSpeed() {
        return FiringSpeed;
    }

    public void setFiringSpeed(int firingSpeed) {
        FiringSpeed = firingSpeed;
    }
}
