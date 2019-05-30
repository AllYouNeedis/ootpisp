package oop.state.deserialization;

import oop.ObjectManipulator;
import oop.state.supportedFileFormats;
import pluginInterface.CiphPluginInterface;

import java.util.ArrayList;
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
        int indexOf = filename.indexOf('.');
        if (indexOf == -1)
            return;
        try {
            CiphPluginInterface plugin;
            String ex = getFileFormat(filename,indexOf+1);
            supportedFileFormats extension = supportedFileFormats.valueOf(ex);
            String ciphFormat = getFileFormat(filename,filename.lastIndexOf('.')+1);
            plugin = null;
            if (!ciphFormat.equals(ex)) {
                ArrayList<CiphPluginInterface> pluginsPull = objectManipulator.getPluginsPull();
                for (CiphPluginInterface plug : pluginsPull) {
                    if (plug.getExt().equals(ciphFormat)) {
                        plugin = plug;
                    }
                }
                if (plugin == null){
                    return;
                }
            }
            deserializators.get(extension).DeserializateFromFile(objectManipulator,filename,plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFileFormat(String string,int index) {
        int lastIndex = string.lastIndexOf('.');
        if (index >= lastIndex) {
            return string.substring(index);
        }
        String result = string.substring(index,lastIndex);
        return result;
    }
}
