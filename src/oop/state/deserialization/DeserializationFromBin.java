package oop.state.deserialization;

import oop.ApplicationDataContext;
import oop.ObjectManipulator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationFromBin implements Deserializate {
    @Override
    public void DeserializateFromFile(ObjectManipulator objectManipulator, String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            objectManipulator.setDataContext((ApplicationDataContext) (ois.readObject()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
