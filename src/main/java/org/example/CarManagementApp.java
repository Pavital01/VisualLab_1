package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
//Питани

public class CarManagementApp extends JFrame {
    private static String actualFileName = null;
    private static boolean newFile = true;
    private static boolean fileIsSaved = false;
    private ArrayList<Car> carList;
    private JList<String> carListView;
    private DefaultListModel<String> carListModel;

    // GUI Components
    private static JTextField nameField;
    private static JTextField manufacturerField;
    private static JCheckBox backlitCheckbox;
    private static JComboBox<String> colorComboBox;
    private static JSpinner weightSpinner;
    private static JSpinner priceSpinner;
    private static JSpinner widthSpinner;
    private static JSpinner lengthSpinner;
    private static JSpinner heightSpinner;
    private JButton newFileButton,addButton, deleteButton,loadButton;
    private static JRadioButton radio1;
    private static JRadioButton radio2;
    private static JRadioButton radio3;
    private ButtonGroup radioGroup;
    private static JDateChooser datePicker;
    private static JFileChooser fileChooser;
    private  String currentDirectory = System.getProperty("user.dir");
    private static JSlider safetyIndexSlider;
//    private ImageIcon ;
    private ImageIcon newFileImageIcon,saveImageIcon,deleteImageIcon,loadImageIcon;



    public CarManagementApp() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(currentDirectory));

        // Создаем панель меню
        JMenuBar menuBar = new JMenuBar();

        // Создаем меню "Файл"
        JMenu fileMenu = new JMenu("Файл");

        // Создаем элемент меню "Сохранить"
        JMenuItem newFileMenuItem = new JMenuItem("Новый");
        JMenuItem saveMenuItem = new JMenuItem("Сохранить");
//        JMenuItem saveAsMenuItem = new JMenuItem("Сохранить как...");
        JMenuItem loadMenuItem = new JMenuItem("Загрузить");
        JMenuItem deleteMenuItem = new JMenuItem("Удалить");

        saveMenuItem.addActionListener(actionEvent -> save());

        loadMenuItem.addActionListener(actionEvent -> loadCarFromJsonFile());

        newFileMenuItem.addActionListener(actionEvent -> createNewFile());

        deleteMenuItem.addActionListener(actionEvent -> deleteCar());

        fileMenu.add(newFileMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(deleteMenuItem);
//        fileMenu.add(saveAsMenuItem);

        // Добавляем меню "Файл" в панель меню
        menuBar.add(fileMenu);

        // Устанавливаем панель меню в окно
        setJMenuBar(menuBar);

        carList = new ArrayList<>();
        carListModel = new DefaultListModel<>();
        carListView = new JList<>(carListModel);

//      Модели для спинеров
        SpinnerNumberModel safetyIndexSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 5.0, 0.1);
        SpinnerNumberModel priceSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 1000000000.0, 1000);
        SpinnerNumberModel widthSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 3.0, 0.1);
        SpinnerNumberModel lengthSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 20.0, 0.1);
        SpinnerNumberModel heightSpinnerModel = new SpinnerNumberModel(0.0, 0.0, 3.0, 0.1);
        SpinnerNumberModel weightSpinnerModel = new SpinnerNumberModel(0.0,0.0,2000,100);

        // Инициализация и настройка элементов

        nameField = new JTextField();
        manufacturerField = new JTextField();
        backlitCheckbox = new JCheckBox("Backlit");
        colorComboBox = new JComboBox<>(new String[]{"Red", "Blue", "Green", "Black"});
        weightSpinner = new JSpinner(weightSpinnerModel);
        priceSpinner = new JSpinner(priceSpinnerModel);
//        safetyIndexSpinner = new JSpinner(safetyIndexSpinnerModel);
        widthSpinner = new JSpinner(widthSpinnerModel);
        lengthSpinner = new JSpinner(lengthSpinnerModel);
        heightSpinner = new JSpinner(heightSpinnerModel);
//        JPanel addictionPanel = new JPanel(new GridLayout(1,2));
        JPanel addictionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        addictionPanel.setSize(0,200);


        Dimension safetyIndexSliderDimension = new Dimension(120, 30);
        Dimension safetyIndexValueLabelDimension = new Dimension(50, 20);
        safetyIndexSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
//        safetyIndexSlider.setMajorTickSpacing(5);
//        safetyIndexSlider.setPaintLabels(true);
        safetyIndexSlider.setPreferredSize(safetyIndexSliderDimension);
        // Добавляем слушателя изменений к слайдеру
        // Устанавливаем метки на ползунке с шагом 0.1
        safetyIndexSlider.setLabelTable(safetyIndexSlider.createStandardLabels(5));
        // Создаем метку для отображения текущего значения
        JLabel valueLabel = new JLabel("Value: " + safetyIndexSlider.getValue());
//        valueLabel.setPreferredSize(safetyIndexValueLabelDimension);
        safetyIndexSlider.addChangeListener(changeEvent -> valueLabel.setText("Value: " + safetyIndexSlider.getValue()));
//        safetyIndexSlider.setSize(10,10);
        addictionPanel.add(safetyIndexSlider);
        addictionPanel.add(valueLabel);


        saveImageIcon = new ImageIcon("src/main/resources/save.png");
        deleteImageIcon = new ImageIcon("src/main/resources/delete.png");
        loadImageIcon = new ImageIcon("src/main/resources/load.png");
        newFileImageIcon = new ImageIcon("src/main/resources/newFile.png");

        // Устанавливаем желаемые размеры для изображения
        int width = 30;
        int height = 30;

        // Масштабирование изображения
        Image scaledImage = saveImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Создание нового ImageIcon с измененным размером
        saveImageIcon = new ImageIcon(scaledImage);

        addButton = new JButton();
        addButton.setIcon(saveImageIcon);

        scaledImage = deleteImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        deleteImageIcon = new ImageIcon(scaledImage);
        deleteButton = new JButton();
        deleteButton.setIcon(deleteImageIcon);

        loadButton = new JButton("");
        scaledImage = loadImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        loadImageIcon = new ImageIcon(scaledImage);
        loadButton = new JButton();
        loadButton.setIcon(loadImageIcon);

        scaledImage = newFileImageIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        newFileButton = new JButton();
        newFileImageIcon = new ImageIcon(scaledImage);
        newFileButton.setIcon(newFileImageIcon);

        addButton.addActionListener(actionEvent -> save());
        loadButton.addActionListener(actionEvent -> loadCarFromJsonFile());
        deleteButton.addActionListener(actionEvent -> deleteCar());
        newFileButton.addActionListener(actionEvent -> createNewFile());




        // настраиваю шаблон
        JPanel inputPanel = new JPanel(new GridLayout(11, 2,5,10));
        inputPanel.setSize(100,20);
        inputPanel.setBorder(new EmptyBorder(15, 25, 0, 0));
//        Border border = new LineBorder(Color.BLACK, 2); // цвет и толщина линии
//        inputPanel.setBorder(border);
//        ((GridLayout) inputPanel.getLayout()).setVgap(10);  // Вертикальный отступ
//        ((GridLayout) inputPanel.getLayout()).setHgap(5);  // Вертикальный отступ


        radio1 = new JRadioButton("Automatic");
        radio2 = new JRadioButton("Manual");
        radio3 = new JRadioButton("Robotic");
        radioGroup = new ButtonGroup();
        radio1.setSelected(true);
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        radioGroup.add(radio3);

        JPanel groupBox = new JPanel(new GridLayout(3,1));
        groupBox.setBorder(BorderFactory.createTitledBorder("Transmission"));
        groupBox.add(radio1);
        groupBox.add(radio2);
        groupBox.add(radio3);

        datePicker = new JDateChooser();

        Dimension fieldDimension = new Dimension(200, 30);
        nameField.setPreferredSize(fieldDimension);
        manufacturerField.setPreferredSize(fieldDimension);
        backlitCheckbox.setPreferredSize(fieldDimension);
        colorComboBox.setPreferredSize(fieldDimension);
        weightSpinner.setPreferredSize(fieldDimension);
        priceSpinner.setPreferredSize(fieldDimension);
        addictionPanel.setPreferredSize(fieldDimension);
        datePicker.setPreferredSize(fieldDimension);
        widthSpinner.setPreferredSize(fieldDimension);
        lengthSpinner.setPreferredSize(fieldDimension);
        heightSpinner.setPreferredSize(fieldDimension);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Manufacturer:"));
        inputPanel.add(manufacturerField);
        inputPanel.add(new JLabel("Backlit:"));
        inputPanel.add(backlitCheckbox);
        inputPanel.add(new JLabel("Color:"));
        inputPanel.add(colorComboBox);
        inputPanel.add(new JLabel("Weight:"));
        inputPanel.add(weightSpinner);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceSpinner);
        inputPanel.add(new JLabel("Safety Index:"));
        inputPanel.add(addictionPanel);
        inputPanel.add(new JLabel("Data"));
        inputPanel.add(datePicker);
        inputPanel.add(new JLabel("Width"));
        inputPanel.add(widthSpinner);
        inputPanel.add(new JLabel("Length"));
        inputPanel.add(lengthSpinner);
        inputPanel.add(new JLabel("Height"));
        inputPanel.add(heightSpinner);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newFileButton);
        buttonPanel.add(addButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(deleteButton);


        this.add(buttonPanel);
        this.add(groupBox);
        this.add(inputPanel);


        // Создаем ползунок
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        this.setLayout(new VerticalLayout(5));

        JPanel footer = new JPanel();
//        Label statusBarFileName()

        this.setSize(900, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void deleteCar() {
        if (actualFileName != null && !actualFileName.isEmpty()) {
            File fileToDelete = new File(actualFileName);

            // Проверяем, существует ли файл
            if (fileToDelete.exists()) {
                // Удаляем файл
                if (fileToDelete.delete()) {
                    JOptionPane.showMessageDialog(this, "Файл успешно удален: " + actualFileName);
                } else {
                    JOptionPane.showMessageDialog(this, "Не удалось удалить файл: " + actualFileName);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Файл не существует: " + actualFileName);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Не удалось получить имя файла для удаления.");
        }
        createNewFile();
    }
    public static void loadCarFromJsonFile() {
        newFile = false;
        fileIsSaved = true;

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            actualFileName = selectedFile.getName();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Car loadedCar = objectMapper.readValue(selectedFile, Car.class);

                // Обновите поля на экране с данными из JSON
                nameField.setText(loadedCar.getName());
                manufacturerField.setText(loadedCar.getManufacturer());
                backlitCheckbox.setSelected(loadedCar.isBacklit());
                colorComboBox.setSelectedItem(loadedCar.getColor());
                weightSpinner.setValue(loadedCar.getWeight());
                priceSpinner.setValue(loadedCar.getPrice());
                safetyIndexSlider.setValue(loadedCar.getSafetyIndex());
                widthSpinner.setValue(loadedCar.getWidth());
                lengthSpinner.setValue(loadedCar.getLength());
                heightSpinner.setValue(loadedCar.getHeight());

                // Установите выбранную радиокнопку в соответствии с transmission
                switch (loadedCar.getTransmission()) {
                    case Automatic:
                        radio1.setSelected(true);
                        break;
                    case Manual:
                        radio2.setSelected(true);
                        break;
                    case Robot:
                        radio3.setSelected(true);
                        break;
                }
                datePicker.setDate(loadedCar.getDateOfIssue());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void createNewFile() {
        actualFileName = null;
        fileIsSaved = false;
        newFile = true;

        radio1.setSelected(true);
        radio2.setSelected(false);
        radio3.setSelected(false);
        nameField.setText("");
        manufacturerField.setText("");
        backlitCheckbox.setSelected(false);
        colorComboBox.setSelectedIndex(0);
        weightSpinner.setValue(0.0);
        priceSpinner.setValue(0.0);
        safetyIndexSlider.setValue(25);
        datePicker.setDate(null);
        widthSpinner.setValue(0.0);
        lengthSpinner.setValue(0.0);
        heightSpinner.setValue(0.0);
    }

    private void save() {

        String name = nameField.getText();
        String manufacturer = manufacturerField.getText();
        boolean backlit = backlitCheckbox.isSelected();
        String color = (String) colorComboBox.getSelectedItem();
        Double weight = Double.valueOf(weightSpinner.getValue().toString()) ;
        Double price = Double.valueOf(priceSpinner.getValue().toString()) ;
        Integer safetyIndex = Integer.valueOf(safetyIndexSlider.getValue());
        Double widthIndex = Double.valueOf(widthSpinner.getValue().toString());
        Double lengthIndex = Double.valueOf(lengthSpinner.getValue().toString());
        Double heightIndex = Double.valueOf(heightSpinner.getValue().toString());
//        System.out.println("From save " + radioGroup.getSelection().getMnemonic());
        String string = getSelectedButtonText(radioGroup);
        Transmission transmission = Transmission.Automatic;
        switch (string){
            case "Automatic" : transmission = Transmission.Automatic;break;
            case "Manual" : transmission = Transmission.Manual;break;
            case "Robotic" : transmission = Transmission.Robot;break;
            default:transmission = Transmission.Automatic;
        }
        Date date = datePicker.getDate();

        // Create a new Car object and add it to the list
        Car car = new Car(name, manufacturer, weight, date, backlit, price, safetyIndex, color,
                lengthIndex, widthIndex, heightIndex, transmission);
        carList.add(car);

        // Update the JList
        carListModel.addElement(car.getName());

        if (actualFileName == null) {
            // Если newFile == true, то предложите пользователю выбрать имя и место сохранения файла
            int returnValue = fileChooser.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                actualFileName = selectedFile.getName();

                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Для форматированного вывода JSON

                    // Преобразовать объект Car в JSON и сохранить в выбранный файл
                    objectMapper.writeValue(selectedFile, car);

                    // Оповестить пользователя об успешном сохранении
                    JOptionPane.showMessageDialog(this, "Данные успешно сохранены в файл " + selectedFile.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            // Если newFile == false, то сохраните файл с существующим именем
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Для форматированного вывода JSON

                // Преобразовать объект Car в JSON и сохранить в существующий файл
                // Здесь предполагается, что у вас уже есть имя файла, например, "existing_file.json"
                File existingFile = new File(actualFileName);
                objectMapper.writeValue(existingFile, car);

                // Оповестить пользователя об успешном сохранении
                JOptionPane.showMessageDialog(this, "Данные успешно сохранены в существующий файл " + existingFile.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getActualFileName() {
        return actualFileName;
    }

    public static void setActualFileName(String actualFileName) {
        CarManagementApp.actualFileName = actualFileName;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarManagementApp());
    }
}
