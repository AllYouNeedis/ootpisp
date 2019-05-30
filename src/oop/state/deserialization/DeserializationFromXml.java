package oop.state.deserialization;

import oop.ApplicationDataContext;
import oop.ObjectManipulator;
import pluginInterface.CiphPluginInterface;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class DeserializationFromXml implements Deserializate {
    @Override
    public void DeserializateFromFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis;
            if (plugin == null) {
                bis = new BufferedInputStream(fis);
            } else {
                bis = new BufferedInputStream(plugin.getInputStream(fis));
            }
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
