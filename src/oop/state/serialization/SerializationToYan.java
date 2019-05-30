package oop.state.serialization;

import oop.ObjectManipulator;
import oop.state.FieldGetter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SerializationToYan implements Serializate {
    @Override
    public void SerializateToFile(ObjectManipulator objectManipulator, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename, false);
            fileWriter.write("'yan'");
            fileWriter.append("\n");
            fileWriter.flush();

            for (Object object : objectManipulator.getDataContext().getObjects()) {
                fileWriter.write("{");
                Class myClass = object.getClass();
                ArrayList<Field> fields = FieldGetter.getObjectFields(myClass);
                fileWriter.write(object.toString() + ",");
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldType = field.getName();
                    fileWriter.write("\"".concat(fieldType).concat("\""));
                    fileWriter.write(":");
                    Object d = field.get(object);
                    if (d == null)
                        fileWriter.write("");
                    else
                        fileWriter.write(d.toString());
                    if (!field.equals(fields.get(fields.size() - 1)))
                        fileWriter.write(",");
                }
                fileWriter.write("}\n");
            }

            fileWriter.close();
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
