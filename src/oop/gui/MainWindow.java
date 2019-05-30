package oop.gui;

import oop.CreatableObjects;
import oop.ObjectManipulator;
import oop.PluginLoader;
import oop.state.*;
import oop.state.deserialization.Deserializator;
import oop.state.serialization.Serializator;
import pluginInterface.CiphPluginInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MainWindow extends JFrame {
    private DefaultListModel listModel;
    private JList list;
    private JCheckBox ciphBox;
    private JComboBox ciphCombobox;
    private ArrayList<CiphPluginInterface> plugins = new ArrayList<>();
    private ObjectManipulator objectManipulator;

    public MainWindow(ObjectManipulator objectManipulator) {
        super("oop");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.objectManipulator = objectManipulator;
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(5,5));

        listModel = new DefaultListModel();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu saveMenu = new JMenu("Save as..");
        fileMenu.add(saveMenu);

        JMenuItem binarySaveItem = new JMenuItem(supportedFileFormats.bin.name());
        saveMenu.add(binarySaveItem);
        addSaveListener(binarySaveItem,supportedFileFormats.bin,objectManipulator);

        JMenuItem jsonSaveItem = new JMenuItem(supportedFileFormats.xml.name());
        saveMenu.add(jsonSaveItem);
        addSaveListener(jsonSaveItem,supportedFileFormats.xml,objectManipulator);

        JMenuItem yanSaveItem = new JMenuItem(supportedFileFormats.yan.name());
        saveMenu.add(yanSaveItem);
        addSaveListener(yanSaveItem,supportedFileFormats.yan,objectManipulator);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        fileMenu.add(loadMenuItem);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
//                FileNameExtensionFilter filterBin = new FileNameExtensionFilter(supportedFileFormats.bin.name(),supportedFileFormats.bin.name());
//                FileNameExtensionFilter filterJSON = new FileNameExtensionFilter(supportedFileFormats.xml.name(),supportedFileFormats.xml.name());
//                FileNameExtensionFilter filterYan = new FileNameExtensionFilter(supportedFileFormats.yan.name(),supportedFileFormats.yan.name());
//                fileChooser.setFileFilter(filterBin);
//                fileChooser.setFileFilter(filterJSON);
//                fileChooser.setFileFilter(filterYan);
                int ret = fileChooser.showDialog(MainWindow.this,"Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    Deserializator deserializator = new Deserializator();
                    deserializator.deserialize(objectManipulator,fileChooser.getSelectedFile().toString());
                    redraw();
                }
            }
        });

        list = new JList(listModel);
        mainPanel.add(new JScrollPane(list), BorderLayout.CENTER);
        mainPanel.add(list,BorderLayout.CENTER);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    Object object = objectManipulator.getDataContext().getObjects().get(index);
                    WindowContext context = new WindowContext(object);
                    ChangingWindow window = new ChangingWindow(context.getAllFields(),MainWindow.this,object,objectManipulator);
                    window.setSize(600,400);
                    window.AddButtonAction(object,CreatableObjects.valueOf(object.getClass().getSimpleName()),objectManipulator);
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
                    window.setVisible(true);
                    redraw();
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
                WindowContext context = new WindowContext(CreatableObjects.values()[comboBox.getSelectedIndex()]);
                AddingWindow window = new AddingWindow(context.getAllFields(),context.getFrameName(),MainWindow.this,objectManipulator);
                window.setSize(600,400);
                window.AddButtonAction(context.getE(),objectManipulator);
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
                window.setVisible(true);
                redraw();
            }
        });
        addButton.setFocusable(false);
        buttonPanel.add(addButton);

        JButton removeButton = new JButton("Удалить");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                if (index >= 0 && index < objectManipulator.getDataContext().getObjects().size()) {
                    objectManipulator.getDataContext().removeElement(index);
                    redraw();
                }
            }
        });
        removeButton.setFocusable(false);
        PluginLoader pluginLoader = new PluginLoader();
        buttonPanel.add(removeButton,BorderLayout.EAST);
        initPluginMenu(buttonPanel,pluginLoader.getPlugins());
        MainWindow.this.setJMenuBar(menuBar);
        pack();
        getContentPane().add(mainPanel);
        this.setVisible(true);
    }

    private void redraw() {
        listModel.clear();
        ArrayList<Object> objects = objectManipulator.getDataContext().getObjects();
        for (int i = 0; i < objects.size(); i++) {
            String[] a = objects.get(i).getClass().getTypeName().split("[.]");
            listModel.addElement((CreatableObjects.GetNameFromString(a[a.length-1])));
        }
    }

    private void addSaveListener(JMenuItem jMenuItem, supportedFileFormats type, ObjectManipulator objectManipulator) {
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CiphPluginInterface plugin;
                Serializator serializator = new Serializator();
                JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
                fileChooser.setDialogTitle("Сохранение файла");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int ret = fileChooser.showDialog(MainWindow.this,"Сохранить файл");
                if (ciphBox.isSelected()) {
                    plugin = plugins.get(ciphCombobox.getSelectedIndex());
                } else {
                    plugin = null;
                }
                if (ret == JFileChooser.APPROVE_OPTION) {
                    String filename = fileChooser.getSelectedFile().toString().concat(".").concat(type.name());
                    if (plugin != null)
                        filename = filename.concat(".").concat(plugin.getExt());
                    serializator.serialize(plugin,type,objectManipulator,filename);
                }
            }
        });
    }
    private void initPluginMenu(JPanel panel,ArrayList<Class> pluginsClasses) {
        String[] PluginsNames = new String[pluginsClasses.size()];
        for (int i = 0; i < pluginsClasses.size(); i++) {
            System.out.println(pluginsClasses.get(i).getName());
            try{
                plugins.add((CiphPluginInterface)pluginsClasses.get(i).newInstance());
                PluginsNames[i] = plugins.get(i).getName();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        objectManipulator.setPluginsPull(plugins);
        ciphBox = new JCheckBox("используем шифрование");
        panel.add(ciphBox);
        ciphCombobox = new JComboBox(PluginsNames);
        panel.add(ciphCombobox);
    }
}
