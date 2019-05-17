package oop.serialization;

import oop.ObjectManipulator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationToBin implements Serializate {
    @Override
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectManipulator.getDataContext());
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
