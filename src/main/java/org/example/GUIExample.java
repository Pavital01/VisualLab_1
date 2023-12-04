package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIExample extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem newMenuItem, openMenuItem, saveMenuItem, cutMenuItem, copyMenuItem, pasteMenuItem;
    private JPopupMenu contextMenu;
    private JMenuItem contextMenuItem1, contextMenuItem2;
    private JToolBar toolBar;
    private JButton newButton, openButton, saveButton, cutButton, copyButton, pasteButton;
    private JLabel statusBar;
    private ImageIcon newIcon, openIcon, saveIcon, cutIcon, copyIcon, pasteIcon;

    public GUIExample() {
        setTitle("GUI Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание главного меню
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Меню "Файл"
        fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        newMenuItem = new JMenuItem("Новый");
        openMenuItem = new JMenuItem("Открыть");
        saveMenuItem = new JMenuItem("Сохранить");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);

        // Меню "Правка"
        editMenu = new JMenu("Правка");
        menuBar.add(editMenu);

        cutMenuItem = new JMenuItem("Вырезать");
        copyMenuItem = new JMenuItem("Копировать");
        pasteMenuItem = new JMenuItem("Вставить");

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        // Создание контекстного меню
        contextMenu = new JPopupMenu();
        contextMenuItem1 = new JMenuItem("Контекстный пункт 1");
        contextMenuItem2 = new JMenuItem("Контекстный пункт 2");
        contextMenu.add(contextMenuItem1);
        contextMenu.add(contextMenuItem2);

        // Создание панели инструментов
        toolBar = new JToolBar();
        add(toolBar, BorderLayout.PAGE_START);

        newButton = new JButton("Новый");
        openButton = new JButton("Открыть");
        saveButton = new JButton("Сохранить");
        cutButton = new JButton("Вырезать");
        copyButton = new JButton("Копировать");
        pasteButton = new JButton("Вставить");

        toolBar.add(newButton);
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(cutButton);
        toolBar.add(copyButton);
        toolBar.add(pasteButton);

        // Создание изображений для кнопок
        newIcon = new ImageIcon("new.png");
        openIcon = new ImageIcon("open.png");
        saveIcon = new ImageIcon("src/main/resources/save.png");
        cutIcon = new ImageIcon("cut.png");
        copyIcon = new ImageIcon("copy.png");
        pasteIcon = new ImageIcon("paste.png");

        // Назначение изображений кнопкам
        newButton.setIcon(newIcon);
        openButton.setIcon(openIcon);
        saveButton.setIcon(saveIcon);
        cutButton.setIcon(cutIcon);
        copyButton.setIcon(copyIcon);
        pasteButton.setIcon(pasteIcon);

        // Строка состояния
        statusBar = new JLabel("Готово");
        add(statusBar, BorderLayout.SOUTH);

        // Обработчики событий для пунктов меню
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Выбран пункт 'Новый'");
            }
        });

        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Выбран пункт 'Открыть'");
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Выбран пункт 'Сохранить'");
            }
        });

        // Обработчики событий для контекстного меню
        contextMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Выбран контекстный пункт 1");
            }
        });

        contextMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Выбран контекстный пункт 2");
            }
        });

        // Обработчики событий для кнопок на панели инструментов
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Нажата кнопка 'Новый'");
            }
        });

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Нажата кнопка 'Открыть'");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Нажата кнопка 'Сохранить'");
            }
        });

        cutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Нажата кнопка 'Вырезать'");
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Нажата кнопка 'Копировать'");
            }
        });

        pasteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusBar.setText("Нажата кнопка 'Вставить'");
            }
        });

        // Обработчики событий для мыши
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    statusBar.setText("Клик мыши: " + e.getButton());
                } else if (e.getClickCount() == 2) {
                    statusBar.setText("Двойной клик мыши");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                statusBar.setText("Нажата кнопка мыши: " + e.getButton());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                statusBar.setText("Отпущена кнопка мыши: " + e.getButton());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                statusBar.setText("Мышь вошла в компонент");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                statusBar.setText("Мышь покинула компонент");
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                statusBar.setText("Движение мыши: x=" + e.getX() + ", y=" + e.getY());
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                statusBar.setText("Колесо мыши: " + e.getWheelRotation());
            }
        };

        // Добавление обработчиков событий мыши к компонентам
        this.addMouseListener(mouseAdapter);
        newButton.addMouseListener(mouseAdapter);
        openButton.addMouseListener(mouseAdapter);
        saveButton.addMouseListener(mouseAdapter);
        cutButton.addMouseListener(mouseAdapter);
        copyButton.addMouseListener(mouseAdapter);
        pasteButton.addMouseListener(mouseAdapter);
//        carListView.addMouseListener(mouseAdapter); // Предположим, у вас есть компонент carListView

        // Обработчик событий мыши для контекстного меню
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    contextMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIExample().setVisible(true));
    }
}

