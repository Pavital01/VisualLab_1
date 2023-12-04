package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GUIElementsDemo extends JFrame {
    public GUIElementsDemo() {
        setTitle("GUI Elements Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);








        // Create a JPanel for organizing elements
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // Label
        JLabel label = new JLabel("Label:");
        mainPanel.add(label);

        // Button
        JButton button = new JButton("Button");
        mainPanel.add(button);

        // TextBox
        JTextField textField = new JTextField(15);
        mainPanel.add(textField);

        // Check Box
        JCheckBox checkBox = new JCheckBox("Check Box");
        mainPanel.add(checkBox);

        // Radio Buttons
        JRadioButton radio1 = new JRadioButton("Radio 1");
        JRadioButton radio2 = new JRadioButton("Radio 2");
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        mainPanel.add(radio1);
        mainPanel.add(radio2);

        // Group Box
//        JPanel groupBox = new JPanel();
//        groupBox.setBorder(BorderFactory.createTitledBorder("Group Box"));
//        groupBox.setLayout(new GridLayout(2, 1));
//        groupBox.add(new JLabel("Group Box Element 1"));
//        groupBox.add(new JLabel("Group Box Element 2"));
//        mainPanel.add(groupBox);

        JPanel groupBox = new JPanel();
//
        groupBox.setBorder(BorderFactory.createTitledBorder("Group Box"));
        groupBox.setLayout(new GridLayout(2, 1));
        groupBox.add(radio1);
        groupBox.add(radio2);
        mainPanel.add(groupBox);

        // Panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setPreferredSize(new Dimension(150, 100));
        mainPanel.add(panel);

        // ToolTip
        button.setToolTipText("This is a Button");
        textField.setToolTipText("This is a TextBox");

        // ListBox
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JList<String> listBox = new JList<>(items);
        mainPanel.add(new JScrollPane(listBox));

        // ComboBox
        String[] comboBoxItems = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxItems);
        mainPanel.add(comboBox);

        // NumericUpDown (Spinner)
        JSpinner spinner = new JSpinner();
        mainPanel.add(spinner);

        // TrackBar (Slider)
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        mainPanel.add(slider);

        // ListView
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> listView = new JList<>(listModel);
        mainPanel.add(new JScrollPane(listView));

        // HscrollBar (Horizontal Scrollbar)
        JScrollBar hscrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        mainPanel.add(hscrollBar);

        // VscrollBar (Vertical Scrollbar)
        JScrollBar vscrollBar = new JScrollBar(JScrollBar.VERTICAL);
        mainPanel.add(vscrollBar);

        // Timer
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("Timer Fired!");
            }
        });
        timer.start();

        // Splitter (JSplitPane)
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(listView), new JScrollPane(textField));
        mainPanel.add(splitter);

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIElementsDemo());
    }
}
