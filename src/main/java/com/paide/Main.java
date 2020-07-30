package com.paide;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.paide.gui.layout.MainLayout;
import com.paide.gui.WindowBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class Main {
    public static final ResourceBundle I18N = ResourceBundle.getBundle("i18n");
    public static final ImageIcon ICON;

    static {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream("paide32.png");
        Image image = null;
        try {
            if(stream != null) image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(image != null) ICON = new ImageIcon(image);
            else ICON = new ImageIcon();
        }
    }

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
                    .setImageIcon(ICON.getImage())
                    .setMaximized(true)
                    .setNothingOnClose()
                    .buildFrame();
            mainLayout.addCloseHandler();
        });
    }
}
