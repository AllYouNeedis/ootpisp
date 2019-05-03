package oop.gui;

import oop.ApplicationDataContext;
import oop.CreatableObjects;
import oop.ObjectManipulator;
import oop.objects.Battalion;
import oop.objects.SexPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangingWindow extends JDialog {
    private HashMap<Integer,Integer> indexConformity;
    private Map<Integer,JTextField> TextFields = new HashMap<>();
    private Map<Integer,JComboBox> ComboBoxes = new HashMap<>();
    private Map<Integer,JCheckBox> CheckBoxes = new HashMap<>();
    private Map<Integer,JList> Lists = new HashMap<>();
    private Map<String,JButton> Buttons = new HashMap<>();
    public ChangingWindow(ArrayList<Field> Fields, JFrame owner, Object object, ObjectManipulator objectManipulator) {
        super(owner,"изменяем",true);
        int i = 1;
        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayout(Fields.size()+1,3,20,20));
        for (Field field: Fields) {
            if (!field.isAccessible())
                field.setAccessible(true);
            Class<?> type = field.getType();
            JLabel label = new JLabel(field.getName());
            MainPanel.add(label);
            try {
                if (type.isEnum()) {
                    ComboBoxes.put(i, new JComboBox(SexPool.getLikeStrings()));
                    SexPool a = SexPool.valueOf(field.get(object).toString());
                    int index = a.getNumberFromSex();
                    ComboBoxes.get(i).setSelectedIndex(index);
                    MainPanel.add(ComboBoxes.get(i));
                } else if (type.isPrimitive()) {
                    if ("boolean".equals(field.getGenericType().getTypeName())) {
                        CheckBoxes.put(i, new JCheckBox());
                        CheckBoxes.get(i).setSelected((boolean)(field.get(object)));
                        MainPanel.add(CheckBoxes.get(i));
                    } else {
                        TextFields.put(i, new JTextField(20));
                        TextFields.get(i).setText(field.get(object).toString());

                        MainPanel.add(TextFields.get(i));
                    }
                } else if (type.getName().equals("java.util.ArrayList")) {
                    ListModel model = new DefaultListModel();
                    Lists.put(i,new JList(model));
                    Integer j;
                    int indexInNewList = 0;
                    boolean isAlreadyUsed;
                    ArrayList<Integer> mustBeSelected = new ArrayList<>();
                    ArrayList<Object> objects = objectManipulator.getDataContext().getObjects();
                    ArrayList<Battalion> battalions = objectManipulator.getBattalions();
                    indexConformity = new HashMap<>();
                    for (j = 0; j < objects.size(); j++) {
                        isAlreadyUsed = false;
                        if (!objects.get(j).getClass().getName().equals("oop.objects.Battalion")) {
                            String[] temp = objects.get(j).getClass().getName().split("[.]");
                            for (Battalion battalion : battalions) {
                                if (!battalion.equals(object)) {
                                    if (battalion.getComposition().contains(objects.get(j))) {
                                        isAlreadyUsed = true;
                                    }
                                }
                            }
                            if (!isAlreadyUsed) {
                                indexConformity.put(indexInNewList,j);
                                ((DefaultListModel) model).addElement((CreatableObjects.GetNameFromString(temp[temp.length-1])).concat(" ".concat(j.toString())));
                                if (((Battalion)object).getComposition().contains(objects.get(j))) {
                                    mustBeSelected.add(indexInNewList);
                                }
                                indexInNewList++;
                            }
                        }
                    }
                    int[] a = new int[mustBeSelected.size()];
                    for (j = 0; j<mustBeSelected.size(); j++) {a[j] = mustBeSelected.get(j);}
                    Lists.get(i).setSelectedIndices(a);
                    MainPanel.add(Lists.get(i));
                } else {
                    TextFields.put(i, new JTextField(20));
                    TextFields.get(i).setText(field.get(object).toString());
                    MainPanel.add(TextFields.get(i));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            i++;
        }
        Buttons.put("CancelButton",new JButton("Отменить"));
        Buttons.get("CancelButton").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        MainPanel.add(Buttons.get("CancelButton"));
        Buttons.put("AddButton",new JButton("Изменить"));

        MainPanel.add(Buttons.get("AddButton"));
        pack();
        getContentPane().add(MainPanel);
    }

    public void AddButtonAction(Object object,CreatableObjects E, ObjectManipulator objectManipulator) {
        Buttons.get("AddButton").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<Integer,Object> Data = new HashMap<>();
                for (Map.Entry<Integer,JComboBox> entry : ComboBoxes.entrySet()) {
                    Data.put(entry.getKey(),entry.getValue().getSelectedIndex());
                }
                for (Map.Entry<Integer,JTextField> entry : TextFields.entrySet()) {
                    Data.put(entry.getKey(),entry.getValue().getText());
                }
                for (Map.Entry<Integer,JCheckBox> entry : CheckBoxes.entrySet()) {
                    Data.put(entry.getKey(),entry.getValue().isSelected());
                }
                for (Map.Entry<Integer,JList> entry : Lists.entrySet()) {
                    int[] indexes = entry.getValue().getSelectedIndices();
                    ArrayList<Object> selectedValues = new ArrayList<>();
                    for (int index: indexes) {
                        selectedValues.add(objectManipulator.getDataContext().getObjects().get(indexConformity.get(index)));
                    }
                    Data.put(entry.getKey(),selectedValues);
                }
                Data.put(0,object);
                if (objectManipulator.set(E,Data) == -1) {
                    JOptionPane.showMessageDialog(ChangingWindow.this,"что-то вы ввели не так, попробуйте еще раз","ошибка!!",JOptionPane.WARNING_MESSAGE);
                }
                setVisible(false);
                dispose();
            }
        });
    }
}

