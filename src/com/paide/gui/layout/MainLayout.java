package com.paide.gui.layout;

import com.hermant.program.Program;
import com.paide.gui.MenuBar;
import com.paide.gui.WindowBuilder;
import com.paide.gui.editor.Editor;
import com.paide.gui.emulator.Assembler;
import com.paide.gui.emulator.Emulator;
import com.paide.gui.terminal.Terminal;
import org.fife.ui.rtextarea.RTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import sun.misc.Signal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class MainLayout {
    public static final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.PLAIN,18);


    private JPanel mainPane;
    private JScrollPane terminalPane;
    private RTextScrollPane editorPane;
    private com.paide.gui.MenuBar menuBar;
    private Terminal terminal;
    private Editor editor;
    private Emulator emulator;

    private volatile boolean running = false;

    public MainLayout(){
        terminal = new Terminal(terminalPane);
        editor = new Editor(editorPane);
        menuBar = new com.paide.gui.MenuBar();
        emulator = new Emulator();
        UIManager.put("ToolTip.font", DEFAULT_FONT);
        setupMenuItems();
    }

    public JPanel getMainPane(){
        return mainPane;
    }

    public MenuBar getMenuBar() {
        return menuBar;
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

        menuBar.getMenuItem("Undo").setAction(RTextArea.getAction(RTextArea.UNDO_ACTION));
        menuBar.getMenuItem("Redo").setAction(RTextArea.getAction(RTextArea.REDO_ACTION));
        menuBar.getMenuItem("Cut").setAction(RTextArea.getAction(RTextArea.CUT_ACTION));
        menuBar.getMenuItem("Copy").setAction(RTextArea.getAction(RTextArea.COPY_ACTION));
        menuBar.getMenuItem("Paste").setAction(RTextArea.getAction(RTextArea.PASTE_ACTION));
        menuBar.getMenuItem("Delete").setAction(RTextArea.getAction(RTextArea.DELETE_ACTION));
        menuBar.getMenuItem("Select all").setAction(RTextArea.getAction(RTextArea.SELECT_ALL_ACTION));

        menuBar.getMenuItem("Undo").setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        menuBar.getMenuItem("Redo").setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
        menuBar.getMenuItem("Cut").setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        menuBar.getMenuItem("Copy").setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        menuBar.getMenuItem("Paste").setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        menuBar.getMenuItem("Delete").setAccelerator(KeyStroke.getKeyStroke("DELETE"));
        menuBar.getMenuItem("Select all").setAccelerator(KeyStroke.getKeyStroke("ctrl A"));

        menuBar.getMenuItem("Undo").setToolTipText(null);
        menuBar.getMenuItem("Redo").setToolTipText(null);
        menuBar.getMenuItem("Cut").setToolTipText(null);
        menuBar.getMenuItem("Copy").setToolTipText(null);
        menuBar.getMenuItem("Paste").setToolTipText(null);
        menuBar.getMenuItem("Delete").setToolTipText(null);
        menuBar.getMenuItem("Select all").setToolTipText(null);

        menuBar.getMenuItem("Run").addActionListener(e -> execute(emulator::run));
        menuBar.getMenuItem("Debug").addActionListener(e -> execute(emulator::debug));
        menuBar.getMenuItem("Assemble and load").addActionListener(e -> execute(emulator::load));
        menuBar.getMenuItem("Assemble").addActionListener(e -> Assembler.assemble(editor));

        menuBar.getMenuItem("Run").setAccelerator(KeyStroke.getKeyStroke("ctrl F5"));
        menuBar.getMenuItem("Debug").setAccelerator(KeyStroke.getKeyStroke("ctrl shift F5"));
        menuBar.getMenuItem("Assemble and load").setAccelerator(KeyStroke.getKeyStroke("shift F5"));
        menuBar.getMenuItem("Assemble").setAccelerator(KeyStroke.getKeyStroke("F5"));

        menuBar.getMenuItem("Settings").addActionListener(e -> getSettingsDialog());
        menuBar.getMenuItem("Settings").setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
    }

    private void getSettingsDialog() {
        new WindowBuilder()
                .setContentPane(new SettingsLayout(editor, terminal).getMainPanel())
                .setTitle("Settings")
                .setMinimumSize(480, 640)
                .setPreferredSize(540, 720)
                .setResizable(true)
                .setDocumentModal()
                .setOwner((JFrame) SwingUtilities.getWindowAncestor(mainPane))
                .buildDialog();
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
