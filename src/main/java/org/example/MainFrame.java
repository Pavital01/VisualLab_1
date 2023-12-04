package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {

    public MainFrame() {
        // Устанавливаем параметры основного окна
        setTitle("Главное окно");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель для компонентов
        JPanel panel = new JPanel();

        // Создаем кнопки
        JButton loadButton = new JButton("Загрузить");
        JButton newButton = new JButton("Новый");

        // Добавляем слушателя для кнопки "Загрузить"
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Если файл выбран, открываем новое окно и закрываем текущее

                openCarManagementApp();
                    CarManagementApp.loadCarFromJsonFile();
                    dispose(); // Закрываем текущее окно

            }
        });

        // Добавляем слушателя для кнопки "Новый"
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCarManagementApp();
                dispose(); // Закрываем текущее окно
            }
        });

        // Добавляем кнопки на панель
        panel.add(loadButton);
        panel.add(newButton);

        // Добавляем панель на основное окно
        add(panel);
    }

    private void openNewFrame(String filePath) {
        // Создаем новое окно
        JFrame newFrame = new JFrame("Новое окно");
        newFrame.setSize(300, 200);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Добавляем метку с информацией о выбранном файле
        JLabel label = new JLabel();
        if (filePath != null) {
            label.setText("Выбранный файл: " + filePath);
        } else {
            label.setText("Новый файл");
        }
        newFrame.add(label);

        // Отображаем новое окно
        newFrame.setVisible(true);
    }

    private void openCarManagementApp() {
        // Создаем новый экземпляр CarManagementApp
        CarManagementApp carManagementApp = new CarManagementApp();

        // Отображаем CarManagementApp
        carManagementApp.setVisible(true);
    }

    public static void main(String[] args) {
        // Создаем и отображаем главное окно
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}
