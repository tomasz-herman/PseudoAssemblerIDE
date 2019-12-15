package com.paide.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window() {
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMinimumSize(new Dimension(640, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pseudo-Assembler IDE");
        pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
