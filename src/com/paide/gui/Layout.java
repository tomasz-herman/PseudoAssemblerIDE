package com.paide.gui;

import com.hermant.program.Program;
import com.paide.gui.editor.Editor;
import com.paide.gui.emulator.Assembler;
import com.paide.gui.emulator.Emulator;
import com.paide.gui.terminal.Terminal;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import sun.misc.Signal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class Layout {
    public static final Font DEFAULT_FONT = new Font("Monospaced", Font.PLAIN,18);

    private JPanel mainPane;
    private RTextScrollPane editorPane;
    private JScrollPane terminalPane;
    private Editor editor;
    private Terminal terminal;
    private com.paide.gui.MenuBar menuBar;
    private Emulator emulator;

    private volatile boolean running = false;

    public Layout(){
        editor = new Editor(editorPane);
        terminal = new Terminal(terminalPane);
        menuBar = new MenuBar();
        editorPane.setViewportView(editor);
        emulator = new Emulator();
        UIManager.put("ToolTip.font", DEFAULT_FONT);
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

        menuBar.getMenuItem("Run").addActionListener(e -> execute(emulator::run));
        menuBar.getMenuItem("Debug").addActionListener(e -> execute(emulator::debug));
        menuBar.getMenuItem("Assemble and load").addActionListener(e -> execute(emulator::load));
        menuBar.getMenuItem("Assemble").addActionListener(e -> Assembler.assemble(editor));

        menuBar.getMenuItem("Run").setAccelerator(KeyStroke.getKeyStroke("ctrl F5"));
        menuBar.getMenuItem("Debug").setAccelerator(KeyStroke.getKeyStroke("ctrl shift F5"));
        menuBar.getMenuItem("Assemble and load").setAccelerator(KeyStroke.getKeyStroke("shift F5"));
        menuBar.getMenuItem("Assemble").setAccelerator(KeyStroke.getKeyStroke("F5"));
    }

    public JPanel getMainPane(){
        return mainPane;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    private void execute(Consumer<Program> executor) {
        Thread thread = new Thread(() -> {
            running = true;
            terminal.clear();
            Program program = Assembler.assemble(editor);
            if(program == null) {
                running = false;
                return;
            }
            try {
                executor.accept(program);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            terminal.flush();
            running = false;
        });
        if (running) {
            int result = JOptionPane.showConfirmDialog(null, "Some program is already running! Do you wish to stop it?", "Some program is running", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                while(running) Signal.raise(new Signal("INT"));
                thread.start();
            }
        } else thread.start();
    }

}
