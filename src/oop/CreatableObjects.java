package oop;

import oop.factories.Creatable;
import oop.objects.SexPool;

public enum CreatableObjects {
    Spearman("Копейщик"),
    Swordman("Мечник"),
    Archer("Лучник"),
    Catapult("Катапульта"),
    Battalion("Батальон");

    String Name;

    CreatableObjects(String name){
        this.Name = name;
    }

    public static String GetNameFromString(String a) {
        for (CreatableObjects el: CreatableObjects.values()) {
            if (el.name().equals(a))
                return el.Name;
        }
        return a;
    }

    public static String[] getLikeStrings() {
        int i = 0;
        String[] result = new String[CreatableObjects.getSize()];
        for (CreatableObjects element: CreatableObjects.values()) {
            result[i] = element.Name;
            i++;
        }
        return result;
    }

    private static int getSize() {
        int result = 0;
        for (CreatableObjects el: CreatableObjects.values()) {
            result++;
        }
        return result;
    }
}
