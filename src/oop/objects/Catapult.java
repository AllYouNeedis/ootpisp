package oop.objects;

public class Catapult {
    private int Number;
    private int FiringDistance;
    private int FiringSpeed;

    public Catapult(int number, int firingDistance, int firingSpeed) {
        Number = number;
        FiringDistance = firingDistance;
        FiringSpeed = firingSpeed;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
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
