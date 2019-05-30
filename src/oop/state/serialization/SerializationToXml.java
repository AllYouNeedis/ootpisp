package oop.state.serialization;

import oop.ObjectManipulator;
import pluginInterface.CiphPluginInterface;
import sun.nio.cs.UTF_32;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SerializationToXml implements Serializate {
    @Override
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos;
            if (plugin != null) {
                bos = new BufferedOutputStream(plugin.getOutputStream(fos));
            } else {
                bos = new BufferedOutputStream(fos);
            }
            XMLEncoder xmlEncoder = new XMLEncoder(bos);
            xmlEncoder.writeObject(objectManipulator.getDataContext().getObjects());
            xmlEncoder.flush();
            xmlEncoder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
