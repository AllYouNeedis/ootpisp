package oop.state.serialization;

import oop.ObjectManipulator;
import pluginInterface.CiphPluginInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationToBin implements Serializate {
    @Override
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos;
            if (plugin != null) {
                oos = new ObjectOutputStream(plugin.getOutputStream(fos));
            } else {
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(objectManipulator.getDataContext());
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
