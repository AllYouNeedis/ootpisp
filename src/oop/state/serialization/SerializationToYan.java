package oop.state.serialization;

import oop.ObjectManipulator;
import oop.state.FieldGetter;
import pluginInterface.CiphPluginInterface;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SerializationToYan implements Serializate {
    @Override
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream fileWriter;
            if (plugin == null) {
                fileWriter = new BufferedOutputStream(fos);
            } else {
                fileWriter = new BufferedOutputStream(plugin.getOutputStream(fos));
            }
            fileWriter.write("'yan'".getBytes());
            fileWriter.write("\n".getBytes());
            fileWriter.flush();
            ArrayList<Object> objects = objectManipulator.getDataContext().getObjects();
            for (int i = 0; i < objects.size(); i++) {
                Object object = objects.get(i);
//            for (Object object : objectManipulator.getDataContext().getObjects()) {
                fileWriter.write("{".getBytes());
                Class myClass = object.getClass();
                ArrayList<Field> fields = FieldGetter.getObjectFields(myClass);
                fileWriter.write(object.toString().concat(",").getBytes());
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldType = field.getName();
                    fileWriter.write("\"".concat(fieldType).concat("\"").getBytes());
                    fileWriter.write(":".getBytes());
                    Object d = field.get(object);
                    if (d == null)
                        fileWriter.write("".getBytes());
                    else
                        fileWriter.write(d.toString().getBytes());
                    if (!field.equals(fields.get(fields.size() - 1)))
                        fileWriter.write(",".getBytes());
                }
                fileWriter.write("}".getBytes());
                if (!object.equals(objects.get(objects.size()-1))) {
                    fileWriter.write("\n".getBytes());
                }
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
