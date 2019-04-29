package oop.gui;

import oop.CreatableObjects;
import oop.ObjectManipulator;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

class CreateAddingWindow {
    private final String WhereIsI = "oop.objects.";
    CreateAddingWindow(CreatableObjects E, ObjectManipulator objectManipulator, JFrame owner){
        ArrayList<Field> AllFields;
        String name = new String(WhereIsI);
        name = name.concat(E.name());
        try {
            Class MyClass = Class.forName(name);
            AllFields = getFields(MyClass);
            AddingWindow window = new AddingWindow(AllFields,E.name(),owner);
            window.setSize(600,400);
            window.AddButtonAction(E,objectManipulator);
            window.setVisible(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
