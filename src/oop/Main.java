package oop;

import java.awt.*;

import oop.gui.*;

public class Main {
    public static void main(String[] args) {
        ApplicationDataContext allObjects = new ApplicationDataContext();
        ObjectManipulator objectManipulator = new ObjectManipulator(allObjects);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow frame = new MainWindow(allObjects,objectManipulator);
                frame.setSize(600,400);
            }
        });

    }
}
