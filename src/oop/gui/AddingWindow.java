package oop.gui;

import oop.CreatableObjects;
import oop.ObjectManipulator;
import oop.objects.SexPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class AddingWindow extends JDialog {
    private Map<Integer,JTextField> TextFields = new HashMap<>();
    private Map<Integer,JComboBox> ComboBoxes = new HashMap<>();
    private Map<Integer,JCheckBox> CheckBoxes = new HashMap<>();
    private Map<String,JButton> Buttons = new HashMap<>();

    AddingWindow(ArrayList<Field> Fields, String FrameName,JFrame owner) {
        super(owner,FrameName,true);
        int i = 0;
        JPanel MainPanel = new JPanel();
        MainPanel.setLayout(new GridLayout(Fields.size()+1,3,20,20));
        for (Field field: Fields) {
            Class<?> type = field.getType();
            JLabel label = new JLabel(field.getName());
            MainPanel.add(label);
            if (type.isEnum()) {
                ComboBoxes.put(i,new JComboBox(SexPool.getLikeStrings()));
                MainPanel.add(ComboBoxes.get(i));
            } else if (type.isPrimitive()) {
                if ("boolean".equals(field.getGenericType().getTypeName())) {
                    CheckBoxes.put(i,new JCheckBox());
                    MainPanel.add(CheckBoxes.get(i));
                } else {
                    TextFields.put(i,new JTextField(20));
                    MainPanel.add(TextFields.get(i));
                }
            } else {
                TextFields.put(i,new JTextField(20));
                MainPanel.add(TextFields.get(i));
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
        Buttons.put("AddButton",new JButton("Добавить"));
        MainPanel.add(Buttons.get("AddButton"));
        pack();
        getContentPane().add(MainPanel);
    }
    public void AddButtonAction(CreatableObjects E,ObjectManipulator objectManipulator) {
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
                objectManipulator.add(E,Data);
                setVisible(false);
                dispose();
            }
        });
    }
}
