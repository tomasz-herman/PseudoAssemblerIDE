package com.paide.gui;

import com.paide.gui.editor.Editor;
import com.paide.gui.terminal.Terminal;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class Layout {
    public static final Font DEFAULT_FONT = new Font("Monospaced", Font.PLAIN,18);

    private JPanel mainPane;
    private RTextScrollPane editorPane;
    private JScrollPane terminalPane;
    private Editor editor;
    private Terminal terminal;
    private com.paide.gui.MenuBar menuBar;

    public Layout(){
        editor = new Editor(editorPane);
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
