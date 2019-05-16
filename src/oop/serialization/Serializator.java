package oop.serialization;

import oop.ObjectManipulator;

import java.beans.XMLEncoder;
import java.io.*;

public class Serializator {
    private ObjectManipulator objectManipulator;
    public Serializator(ObjectManipulator objectManipulator) {
        this.objectManipulator = objectManipulator;
    }

    public void serialize(supportedFileFormats format, String filename) {

        switch (format) {
            case bin:
                seriaizationToBin(objectManipulator,filename);
                break;
            case xml:
                serializationToxml(objectManipulator,filename);
                break;
            case yan:

        }
    }

    private void seriaizationToBin(ObjectManipulator objectManipulator,String filename) {
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

    private void serializationToxml(ObjectManipulator objectManipulator,String filename) {
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
