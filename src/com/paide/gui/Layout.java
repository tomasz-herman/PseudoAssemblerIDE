package com.paide.gui;

import com.paide.gui.editor.Editor;
import com.paide.gui.terminal.Terminal;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        setupMenuItems();
    }

    public void addCloseHandler(){
        SwingUtilities.getWindowAncestor(mainPane).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                editor.exit();
            }
        });
    }

    private void setupMenuItems(){
        menuBar.getMenuItem("New").addActionListener(e -> editor.openNew());
        menuBar.getMenuItem("Close").addActionListener(e -> editor.close());
        menuBar.getMenuItem("Save").addActionListener(e -> editor.save());
        menuBar.getMenuItem("Save as").addActionListener(e -> editor.saveAs());
        menuBar.getMenuItem("Open").addActionListener(e -> editor.open());
        menuBar.getMenuItem("Exit").addActionListener(e -> editor.exit());

        menuBar.getMenuItem("New").setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        menuBar.getMenuItem("Save").setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        menuBar.getMenuItem("Save as").setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
        menuBar.getMenuItem("Open").setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        menuBar.getMenuItem("Close").setAccelerator(KeyStroke.getKeyStroke("F4"));
        menuBar.getMenuItem("Exit").setAccelerator(KeyStroke.getKeyStroke("alt F4"));
    }

    public JPanel getMainPane(){
        return mainPane;
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }

}
