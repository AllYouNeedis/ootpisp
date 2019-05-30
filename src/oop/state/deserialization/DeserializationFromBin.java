package oop.state.deserialization;

import oop.ApplicationDataContext;
import oop.ObjectManipulator;
import pluginInterface.CiphPluginInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationFromBin implements Deserializate {
    @Override
    public void DeserializateFromFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois;
            if (plugin == null) {
                ois = new ObjectInputStream(fis);
            } else {
                ois = new ObjectInputStream(plugin.getInputStream(fis));
            }
            objectManipulator.setDataContext((ApplicationDataContext) (ois.readObject()));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
