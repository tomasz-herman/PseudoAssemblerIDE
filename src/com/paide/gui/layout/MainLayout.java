package com.paide.gui.layout;

import com.hermant.program.Program;
import com.paide.Main;
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
        menuBar.getMenuItem("help").addActionListener(e -> getTextDialog(Main.I18N.getString("help"), TextLayout.HELP));
        menuBar.getMenuItem("about").addActionListener(e -> getTextDialog(Main.I18N.getString("about"), TextLayout.ABOUT));
        menuBar.getMenuItem("license").addActionListener(e -> getTextDialog(Main.I18N.getString("license"), TextLayout.LICENSE));

        menuBar.getMenuItem("help").setAccelerator(KeyStroke.getKeyStroke("F1"));
        menuBar.getMenuItem("license").setAccelerator(KeyStroke.getKeyStroke("F2"));
        menuBar.getMenuItem("about").setAccelerator(KeyStroke.getKeyStroke("F3"));

        menuBar.getMenuItem("new").addActionListener(e -> editor.openNew());
        menuBar.getMenuItem("close").addActionListener(e -> editor.close());
        menuBar.getMenuItem("save").addActionListener(e -> editor.save());
        menuBar.getMenuItem("save.as").addActionListener(e -> editor.saveAs());
        menuBar.getMenuItem("open").addActionListener(e -> editor.open());
        menuBar.getMenuItem("exit").addActionListener(e -> editor.exit());

        menuBar.getMenuItem("new").setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        menuBar.getMenuItem("save").setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
        menuBar.getMenuItem("save.as").setAccelerator(KeyStroke.getKeyStroke("ctrl shift S"));
        menuBar.getMenuItem("open").setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        menuBar.getMenuItem("close").setAccelerator(KeyStroke.getKeyStroke("F4"));
        menuBar.getMenuItem("exit").setAccelerator(KeyStroke.getKeyStroke("alt F4"));

        menuBar.getMenuItem("undo").setAction(RTextArea.getAction(RTextArea.UNDO_ACTION));
        menuBar.getMenuItem("redo").setAction(RTextArea.getAction(RTextArea.REDO_ACTION));
        menuBar.getMenuItem("cut").setAction(RTextArea.getAction(RTextArea.CUT_ACTION));
        menuBar.getMenuItem("copy").setAction(RTextArea.getAction(RTextArea.COPY_ACTION));
        menuBar.getMenuItem("paste").setAction(RTextArea.getAction(RTextArea.PASTE_ACTION));
        menuBar.getMenuItem("delete").setAction(RTextArea.getAction(RTextArea.DELETE_ACTION));
        menuBar.getMenuItem("select.all").setAction(RTextArea.getAction(RTextArea.SELECT_ALL_ACTION));

        menuBar.getMenuItem("undo").setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
        menuBar.getMenuItem("redo").setAccelerator(KeyStroke.getKeyStroke("ctrl Y"));
        menuBar.getMenuItem("cut").setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
        menuBar.getMenuItem("copy").setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
        menuBar.getMenuItem("paste").setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
        menuBar.getMenuItem("delete").setAccelerator(KeyStroke.getKeyStroke("DELETE"));
        menuBar.getMenuItem("select.all").setAccelerator(KeyStroke.getKeyStroke("ctrl A"));

        menuBar.getMenuItem("undo").setToolTipText(null);
        menuBar.getMenuItem("redo").setToolTipText(null);
        menuBar.getMenuItem("cut").setToolTipText(null);
        menuBar.getMenuItem("copy").setToolTipText(null);
        menuBar.getMenuItem("paste").setToolTipText(null);
        menuBar.getMenuItem("delete").setToolTipText(null);
        menuBar.getMenuItem("select.all").setToolTipText(null);

        menuBar.getMenuItem("run").addActionListener(e -> execute(emulator::run));
        menuBar.getMenuItem("debug").addActionListener(e -> execute(emulator::debug));
        menuBar.getMenuItem("assemble.and.load").addActionListener(e -> execute(emulator::load));
        menuBar.getMenuItem("assemble").addActionListener(e -> Assembler.assemble(editor));

        menuBar.getMenuItem("run").setAccelerator(KeyStroke.getKeyStroke("ctrl F5"));
        menuBar.getMenuItem("debug").setAccelerator(KeyStroke.getKeyStroke("ctrl shift F5"));
        menuBar.getMenuItem("assemble.and.load").setAccelerator(KeyStroke.getKeyStroke("shift F5"));
        menuBar.getMenuItem("assemble").setAccelerator(KeyStroke.getKeyStroke("F5"));

        menuBar.getMenuItem("settings").addActionListener(e -> getSettingsDialog());
        menuBar.getMenuItem("settings").setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
    }

    private void getTextDialog(String title, String text) {
        new WindowBuilder()
                .setContentPane(new TextLayout(text).getMainPanel())
                .setTitle(title)
                .setResizable(false)
                .setDocumentModal()
                .setOwner((JFrame) SwingUtilities.getWindowAncestor(mainPane))
                .buildDialog();
    }

    private void getSettingsDialog() {
        new WindowBuilder()
                .setContentPane(new SettingsLayout(editor, terminal).getMainPanel())
                .setTitle(Main.I18N.getString("settings"))
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
            int result = JOptionPane.showConfirmDialog(null, Main.I18N.getString("some.program.is.already.running.do.you.wish.to.stop.it"), Main.I18N.getString("some.program.is.running"), JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                while(running) Signal.raise(new Signal("INT"));
                thread.start();
            }
        } else thread.start();
    }
}
