package oop.state.deserialization;

import oop.ApplicationDataContext;
import oop.ObjectManipulator;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DeserializationFromXml implements Deserializate {
    @Override
    public void DeserializateFromFile(ObjectManipulator objectManipulator, String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            XMLDecoder xmlDecoder = new XMLDecoder(bis);
            ArrayList<Object> arr = (ArrayList<Object>) xmlDecoder.readObject();
            ApplicationDataContext adc = new ApplicationDataContext();
            adc.setObjects(arr);
            objectManipulator.setDataContext(adc);
            xmlDecoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
