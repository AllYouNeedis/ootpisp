package oop.state.deserialization;

import oop.ApplicationDataContext;
import oop.CreatableObjects;
import oop.ObjectManipulator;
import oop.factories.Factory;
import oop.objects.Battalion;
import pluginInterface.CiphPluginInterface;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeserializationFromYan implements Deserializate {
    private String thisObjectType;
    private String thisObjectRef;
    private String objectType;
    private String objectRef;
    private String objectFieldName;
    private String objectFieldValue;
    private ArrayList<Object> objectFields = new ArrayList<>();
    private ArrayList<String> objectRefs = new ArrayList<>();
    private ArrayList<DeserializationItem> deserializationItems = new ArrayList<>();

    @Override
    public void DeserializateFromFile(ObjectManipulator objectManipulator, String filename, CiphPluginInterface plugin) {
        try {
            Factory factory = new Factory();
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis;
            if (plugin == null) {
                bis = new BufferedInputStream(fis);
            } else {
                bis = new BufferedInputStream(plugin.getInputStream(fis));
            }

            byte[] dat = new byte[(int) file.length()];
            bis.read(dat);
            bis.close();
            String superdat = new String(dat,"UTF-8");
            String[] content = superdat.split("\n");
            String str;
            ApplicationDataContext adc = new ApplicationDataContext();
            ArrayList<Object> objects = new ArrayList<Object>();
            if (!content[0].equals("'yan'"))
                return;
            int j = 1;
            while (j < content.length) {
                str = content[j];
                HashMap<Integer,Object> data = new HashMap<>();
                getObject(0, str);
                Class myClass = Class.forName(thisObjectType);
                CreatableObjects type = CreatableObjects.valueOf(myClass.getSimpleName());
                int i = 0;
                for (Object field: objectFields) {
                    data.put(i,field);
                    i++;
                }
                Object newObject = factory.createObject(type,data);
                DeserializationItem item = new DeserializationItem(thisObjectRef,newObject,objectRefs);
                deserializationItems.add(item);
                objects.add(newObject);
                objectFields.clear();
                objectRefs = new ArrayList<>();
                j++;
            }
            adc.setObjects(objects);
            objectManipulator.setDataContext(adc);
            setObjectRefs(deserializationItems);
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    private void getObject(int i, String str) {
        i = getObjectType(i+1,str);
        i = getObjectRef(i+1, str);
        thisObjectType = objectType;
        thisObjectRef = objectRef;
        while (str.charAt(i) != '}') {
            i = getFieldName(i+2,str);
            i++;
            if (str.charAt(i) == '[') {
                i = getCompositeFieldValue(i+1,str);
                objectFields.add(new ArrayList<Object>());
                i++;
            } else {
                i = getSimpleFieldValue(i, str);
                objectFields.add(objectFieldValue);
            }
        }
    }

    private int getSimpleFieldValue(int i, String str) {
        int j = i;
        while (str.charAt(j) != ',' && str.charAt(j) != '}' ) {
            j++;
        }
        if (i == j)
            objectFieldValue = "";
        else
            objectFieldValue = str.substring(i,j);
        return j;
    }

    private int getCompositeFieldValue(int i, String str) {
        while (str.charAt(i) != ']') {
            i = getObjectType(i, str);
            i = getObjectRef(i+1, str);
            objectRefs.add(objectRef);
            char a = str.charAt(i);
            if (str.charAt(i) == ']')
                return i;
        }
        return i;
    }

    private int getFieldName(int i, String str) {
        int j = i;
        while (str.charAt(j) != '\"') {
            j++;
        }
        objectFieldName = str.substring(i,j++);
        return j;
    }

    private int getObjectType(int i, String str) {
        int j = i;
        while (str.charAt(j) != '@') {
            j++;
        }
        objectType = str.substring(i,j);
        return j;
    }

    private int getObjectRef(int i, String str) {
        int j = i;
        while (str.charAt(j) != ',' && str.charAt(j) != ']') {
            j++;
        }
        objectRef = str.substring(i,j);
        return j;
    }

    private void setObjectRefs(ArrayList<DeserializationItem> items) {
        ArrayList<Object> itemRefs;
        for (DeserializationItem item : items) {
            itemRefs = getObjectRefs(item,items);
            if (!itemRefs.isEmpty()) {
                ((Battalion)item.getItem()).setComposition(itemRefs);
            }
        }
    }

    private ArrayList<Object> getObjectRefs(DeserializationItem parent, ArrayList<DeserializationItem> items) {
        ArrayList<Object> result = new ArrayList<>();
        ArrayList<String> parentRefs = parent.getItemRefs();
        for (DeserializationItem item : items) {
            if (parentRefs.contains(item.getThisItemRef())) {
                result.add(item.getItem());
            }
        }
        return result;
    }
}

class DeserializationItem {
    private String thisItemRef;
    private Object item;
    private ArrayList<String> itemRefs;

    DeserializationItem(String thisItemRef, Object item, ArrayList<String> itemRefs) {
        this.thisItemRef = thisItemRef;
        this.item = item;
        this.itemRefs = itemRefs;
    }

    Object getItem() {
        return item;
    }
    String getThisItemRef() {
        return thisItemRef;
    }
    ArrayList<String> getItemRefs() {
        return itemRefs;
    }
}
