package oop.gui;

import oop.ApplicationDataContext;
import oop.CreatableObjects;
import oop.ObjectManipulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private DefaultListModel listModel;
    private JList list;

    private ApplicationDataContext objects;

    public MainWindow(ApplicationDataContext data, ObjectManipulator objectManipulator) {
        super("oop");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objects = data;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5,5));

        listModel = new DefaultListModel();

        list = new JList(listModel);
        mainPanel.add(new JScrollPane(list), BorderLayout.CENTER);
        mainPanel.add(list,BorderLayout.CENTER);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());

                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6,1,20,20));
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        JComboBox comboBox = new JComboBox(CreatableObjects.getLikeStrings());
        buttonPanel.add(comboBox,BorderLayout.EAST);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateAddingWindow(CreatableObjects.values()[comboBox.getSelectedIndex()],objectManipulator,MainWindow.this);
                redraw();
            }
        });
        addButton.setFocusable(false);
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Удалить");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index >= 0 && index < objects.getObjects().size()) {
                    objects.removeElement(index);
                    redraw();
                }
            }
        });
        removeButton.setFocusable(false);
        buttonPanel.add(removeButton,BorderLayout.EAST);

        pack();
        redraw();
        getContentPane().add(mainPanel);
        this.setVisible(true);
    }

    private void redraw() {
        listModel.clear();
        for (int i = 0; i < objects.getObjects().size(); i++) {
            String[] a = objects.getObjects().get(i).getClass().getTypeName().split("[.]");
            listModel.addElement((CreatableObjects.GetNameFromString(a[a.length-1])));
        }
    }

}
