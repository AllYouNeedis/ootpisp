package oop.factories;

import oop.CreatableObjects;

import java.util.HashMap;

public interface Creatable {
    Object createObject(CreatableObjects E, HashMap<Integer,Object> data);
    int setObjectFields(CreatableObjects E, HashMap<Integer,Object> data);
}
