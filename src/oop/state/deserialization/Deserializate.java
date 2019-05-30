package oop.state.deserialization;

import oop.ObjectManipulator;
import pluginInterface.CiphPluginInterface;

public interface Deserializate {
    public void DeserializateFromFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin);
}
