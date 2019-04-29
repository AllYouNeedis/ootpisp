package oop;

import oop.factories.Factory;
import java.util.HashMap;

public class ObjectManipulator {
    private final Factory ObjectFactory = new Factory();
    private ApplicationDataContext DataContext;
    public int add(CreatableObjects E, HashMap<Integer,Object> Data) {
        Object NewObject = ObjectFactory.createObject(E,Data);
        if (NewObject.equals(-1)) {
            return -1;
        } else {
            DataContext.getObjects().add(NewObject);
            return 0;
        }
    }

    public ObjectManipulator(ApplicationDataContext dataContext) {
        this.DataContext = dataContext;
    }
}
