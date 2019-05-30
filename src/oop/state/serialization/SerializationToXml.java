package oop.state.serialization;

import oop.ObjectManipulator;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerializationToXml implements Serializate {
    @Override
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            XMLEncoder xmlEncoder = new XMLEncoder(bos);
            xmlEncoder.writeObject(objectManipulator.getDataContext().getObjects());
            xmlEncoder.flush();
            xmlEncoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
