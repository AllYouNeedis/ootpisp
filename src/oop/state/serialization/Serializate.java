package oop.state.serialization;

import oop.ObjectManipulator;
import pluginInterface.CiphPluginInterface;

public interface Serializate {
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface ciphPluginInterface);
}
