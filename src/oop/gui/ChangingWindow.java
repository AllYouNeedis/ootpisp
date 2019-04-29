package oop.gui;

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
    private Map<Integer,JTextField> TextFields = new HashMap<>();
    private Map<Integer,JComboBox> ComboBoxes = new HashMap<>();
    private Map<Integer,JCheckBox> CheckBoxes = new HashMap<>();
    private Map<String,JButton> Buttons = new HashMap<>();
    public ChangingWindow(ArrayList<Field> Fields,JFrame owner,Object object) {
        super(owner,"изменяем",true);
        int i = 0;
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
        Buttons.put("AddButton",new JButton("Добавить"));
        MainPanel.add(Buttons.get("AddButton"));
        pack();
        getContentPane().add(MainPanel);
    }
}

