package oop.serialization;

import oop.ApplicationDataContext;
import oop.ObjectManipulator;
import oop.exception.CustomException;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;

public class Deserializator {
    private ObjectManipulator objectManipulator;

    public Deserializator(ObjectManipulator objectManipulator) {
        this.objectManipulator = objectManipulator;
    }

    public void deserialize(String filename) {
        int lastIndexOf = filename.lastIndexOf('.');
        if (lastIndexOf == -1)
            throw new CustomException("I'm custom");
        String ex = filename.substring(lastIndexOf+1);
        supportedFileFormats extension = supportedFileFormats.valueOf(ex);
        switch (extension) {
            case bin:
                deserializeFromBin(filename);
                break;
            case xml:
                deserializeFromxml(filename);
                break;

        }
    }

    private void deserializeFromBin(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            objectManipulator.setDataContext((ApplicationDataContext)(ois.readObject()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void deserializeFromxml(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmlDecoder = new XMLDecoder(bis);
            ArrayList<Object> arr = (ArrayList<Object>)xmlDecoder.readObject();
            ApplicationDataContext adc = new ApplicationDataContext();
            adc.setObjects(arr);
            objectManipulator.setDataContext(adc);
            xmlDecoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
