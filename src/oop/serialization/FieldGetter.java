package oop.serialization;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class FieldGetter {
    public static ArrayList<Field> getObjectFields(Class myClass) {
            if (myClass.getName().equals(Object.class.getName())) {
                return new ArrayList<>(Arrays.asList(myClass.getDeclaredFields()));
            } else {
                ArrayList<Field> result = new ArrayList<>(getObjectFields(myClass.getSuperclass()));
                result.addAll(new ArrayList<>(Arrays.asList(myClass.getDeclaredFields())));
                return result;
            }
    }
}
