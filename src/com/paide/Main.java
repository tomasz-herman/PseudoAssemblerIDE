package com.paide;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.paide.gui.Layout;
import com.paide.gui.WindowBuilder;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        FlatIntelliJLaf.install();
        SwingUtilities.invokeLater(() -> {
            Layout layout = new Layout();
            JFrame window = new WindowBuilder()
                    .setContentPane(layout.getMainPane())
                    .setPreferredSize(1280, 720)
                    .setMinimumSize(320, 240)
                    .setMenuBar(layout.getMenuBar())
                    .setTitle("Pseudo-Assembler IDE")
                    .setMaximized(true)
                    .setNothingOnClose()
                    .buildFrame();
            layout.addCloseHandler();
        });
    }
}
