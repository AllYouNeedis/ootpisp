package oop.state.serialization;

import oop.ObjectManipulator;
import oop.state.supportedFileFormats;
import pluginInterface.CiphPluginInterface;

import java.util.HashMap;

public class Serializator {
    private HashMap<supportedFileFormats, Serializate> serializators;
    public Serializator() {
        serializators = new HashMap<>();
        serializators.put(supportedFileFormats.bin, new SerializationToBin());
        serializators.put(supportedFileFormats.xml, new SerializationToXml());
        serializators.put(supportedFileFormats.yan, new SerializationToYan());
    }

    public void serialize(CiphPluginInterface plugin, supportedFileFormats format, ObjectManipulator objectManipulator, String filename) {
        serializators.get(format).SerializateToFile(objectManipulator,filename,plugin);
    }
}
