package com.paide.gui;

import javax.swing.*;
import java.awt.*;

public class Layout {
    public static final Font DEFAULT_FONT = new Font("Monospaced", Font.PLAIN,18);

    private JPanel mainPane;
    private JScrollPane editorPane;
    private JScrollPane terminalPane;

    public Layout(){
        JTextArea editor = new JTextArea();
        editor.setFont(DEFAULT_FONT);
        editor.setText("This is editor");
        JTextArea terminal = new JTextArea();
        terminal.setFont(DEFAULT_FONT);
        terminal.setText("And this is terminal");

        editorPane.setViewportView(editor);
        terminalPane.setViewportView(terminal);
    }

    public JPanel getMainPane(){
        return mainPane;
    }
}
