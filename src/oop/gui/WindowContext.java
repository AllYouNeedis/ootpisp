package oop.gui;

import oop.CreatableObjects;
import oop.ObjectManipulator;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class WindowContext {
    private ArrayList<Field> allFields;
    private String frameName;
    private CreatableObjects E;
    WindowContext(CreatableObjects E){
        this.E = E;
        String name = "oop.objects.";
        name = name.concat(E.name());
        try {
            Class MyClass = Class.forName(name);
            allFields = getFields(MyClass);
            frameName = E.name();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    WindowContext(Object object) {
        Class MyClass = object.getClass();
        allFields = getFields(MyClass);
    }

    public  ArrayList<Field> getAllFields() {
        return allFields;
    }

    public String getFrameName() {
        return frameName;
    }

    public CreatableObjects getE() {
        return E;
    }

    private ArrayList<Field> getFields(Class MyClass) {
        if (MyClass.getName().equals(Object.class.getName())) {
            return new ArrayList<>(Arrays.asList(MyClass.getDeclaredFields()));
        } else {
            ArrayList<Field> result = new ArrayList<>(getFields(MyClass.getSuperclass()));
            result.addAll(new ArrayList<>(Arrays.asList(MyClass.getDeclaredFields())));
            return result;
        }
    }

}
