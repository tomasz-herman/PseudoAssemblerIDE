package com.paide;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.paide.gui.layout.MainLayout;
import com.paide.gui.WindowBuilder;

import javax.swing.*;
import java.util.ResourceBundle;

public class Main {
    public static final ResourceBundle I18N = ResourceBundle.getBundle("i18n");
    public static void main(String[] args) {
        FlatIntelliJLaf.install();
        SwingUtilities.invokeLater(() -> {
            MainLayout mainLayout = new MainLayout();
            JFrame window = new WindowBuilder()
                    .setContentPane(mainLayout.getMainPane())
                    .setPreferredSize(1280, 720)
                    .setMinimumSize(320, 240)
                    .setMenuBar(mainLayout.getMenuBar())
                    .setTitle("Pseudo-Assembler IDE")
                    .setMaximized(true)
                    .setNothingOnClose()
                    .buildFrame();
            mainLayout.addCloseHandler();
        });
    }
}
