package oop.objects;

public enum SexPool {
    MALE("Мужской"),
    FEMALE("Женский");

    String Name;
    SexPool(String name) {
        this.Name = name;
    }

    public static String[] getLikeStrings() {
        int i = 0;
        String[] result = new String[SexPool.getSize()];
        for (SexPool element: SexPool.values()) {
            result[i] = element.Name;
            i++;
        }
        return result;
    }

    public int getNumberFromSex() {
        int res = 0;
        for (SexPool el: SexPool.values()) {
            if (el == this)
                return res;
            res++;
        }
        return -1;
    }

    private static int getSize() {
        int result = 0;
        for (SexPool el : SexPool.values()) {
            result++;
        }
        return result;
    }
}
