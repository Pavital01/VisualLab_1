package org.example;

import javax.swing.*;
import java.awt.*;

public class VerticalLayout implements LayoutManager {
    private int gap;

    public VerticalLayout(int gap) {
        this.gap = gap;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
    }

    @Override
    public void removeLayoutComponent(Component comp) {
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int width = 0;
        int height = 0;

        for (Component c : parent.getComponents()) {
            Dimension d = c.getPreferredSize();
            width = Math.max(width, d.width);
            height += d.height + gap;
        }

        height -= gap;
        Insets insets = parent.getInsets();
        width += insets.left + insets.right;
        height += insets.top + insets.bottom;

        return new Dimension(width, height);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        int width = 0;
        int height = 0;

        for (Component c : parent.getComponents()) {
            Dimension d = c.getMinimumSize();
            width = Math.max(width, d.width);
            height += d.height + gap;
        }

        height -= gap;
        Insets insets = parent.getInsets();
        width += insets.left + insets.right;
        height += insets.top + insets.bottom;

        return new Dimension(width, height);
    }

    @Override
    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int x = insets.left;
        int y = insets.top;

        for (Component c : parent.getComponents()) {
            Dimension d = c.getPreferredSize();
            c.setBounds(x, y, d.width, d.height);
            y += d.height + gap;
        }
    }
}
