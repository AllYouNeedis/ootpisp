package oop.state.deserialization;

import oop.ObjectManipulator;
import oop.state.supportedFileFormats;

import java.util.HashMap;

public class Deserializator {
    private HashMap<supportedFileFormats, Deserializate> deserializators;

    public Deserializator() {
        this.deserializators = new HashMap<>();
        deserializators.put(supportedFileFormats.bin, new DeserializationFromBin());
        deserializators.put(supportedFileFormats.xml, new DeserializationFromXml());
        deserializators.put(supportedFileFormats.yan, new DeserializationFromYan());
    }

    public void deserialize(ObjectManipulator objectManipulator, String filename) {
        int lastIndexOf = filename.lastIndexOf('.');
        if (lastIndexOf == -1)
            return;
        try {
            String ex = filename.substring(lastIndexOf+1);
            supportedFileFormats extension = supportedFileFormats.valueOf(ex);
            deserializators.get(extension).DeserializateFromFile(objectManipulator,filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
