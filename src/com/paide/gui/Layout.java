package com.paide.gui;

import com.paide.gui.terminal.Terminal;

import javax.swing.*;
import java.awt.*;

public class Layout {
    public static final Font DEFAULT_FONT = new Font("Monospaced", Font.PLAIN,18);

    private JPanel mainPane;
    private JScrollPane editorPane;
    private JScrollPane terminalPane;
    private Terminal terminal;
    private com.paide.gui.MenuBar menuBar;

    public Layout(){
        JTextArea editor = new JTextArea();
        editor.setFont(DEFAULT_FONT);
        editor.setText("This is editor");
        terminal = new Terminal(terminalPane);
        menuBar = new MenuBar();
        editorPane.setViewportView(editor);
    }

    public JPanel getMainPane(){
        return mainPane;
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }

}
