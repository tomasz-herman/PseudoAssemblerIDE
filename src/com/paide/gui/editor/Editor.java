package com.paide.gui.editor;

import com.paide.gui.Layout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Editor extends RSyntaxTextArea {

    private File file = null;
    private JFileChooser inputChooser;
    private JFileChooser outputChooser;
    private boolean changed = false;
    private RTextScrollPane panel;

    public Editor(RTextScrollPane panel) {
        this.panel = panel;
        setupFileChoosers();
        setupScrollPane();
        setupDefaultTheme();
        setupChangeListener();
        registerKeyboardAction(getActionForKeyStroke(KeyStroke.getKeyStroke("ctrl Y")), KeyStroke.getKeyStroke("ctrl shift Z"), WHEN_FOCUSED);
        discardAllEdits();
    }

    private void setupFileChoosers(){
        File workingDirectory = new File(System.getProperty("user.dir"));
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
        inputChooser.setCurrentDirectory(workingDirectory);
        outputChooser.setCurrentDirectory(workingDirectory);
    }

    private void setupScrollPane(){
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.getGutter().setBackground(new JButton().getBackground());
        panel.setViewportView(this);
        panel.setLineNumbersEnabled(true);
        panel.setIconRowHeaderEnabled(true);
        panel.getGutter().setLineNumberFont(Layout.DEFAULT_FONT);
    }

    private void setupChangeListener(){
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed = true;
            }
        });
    }

    private void setupDefaultTheme(){
        setTabsEmulated(true);
        setTabSize(4);
        setFadeCurrentLineHighlight(true);
        setFont(Layout.DEFAULT_FONT);
        setGutterFont(Layout.DEFAULT_FONT);
        setCurrentLineHighlightColor(new JScrollPane().getBackground());
        setBackground(new JTextArea().getBackground());
        setTextColor(new JTextArea().getForeground());
        setGutterBackgroundColor(new JScrollPane().getBackground());
        revalidate();
        setEditable(false);
        setEnabled(false);
    }

    public void setGutterFont(Font font){
        panel.getGutter().setLineNumberFont(font);
    }

    public void setGutterBackgroundColor(Color color){
        panel.getGutter().setBackground(color);
        panel.setBackground(color);
        panel.getGutter().setIconRowHeaderInheritsGutterBackground(true);
    }

    public void setTextColor(Color color){
        setForeground(color);
        setCaretColor(color);
        panel.getGutter().setLineNumberColor(color);
    }

    public Color getTextColor(){
        return getForeground();
    }

    public void openNew(){
        if(showSaveChangesDialog())return;
        setEnabled(true);
        setEditable(true);
        setText("");
        file = null;
        changed = false;
        discardAllEdits();
    }

    public void open(){
        if(showSaveChangesDialog())return;
        int returnVal = inputChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = inputChooser.getSelectedFile();
            try {
                setText(Files.readString(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setEnabled(true);
            setEditable(true);
            changed = false;
            discardAllEdits();
            setCaretPosition(0);
        }
    }

    public boolean save(){
        if(file == null || !file.exists()) return saveAs();
        try {
            changed = false;
            Files.writeString(file.toPath(), getText());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveAs(){
        int returnVal = outputChooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = outputChooser.getSelectedFile();
            return save();
        }
        return false;
    }

    public void close(){
        if(showSaveChangesDialog())return;
        setEnabled(false);
        setEditable(false);
        setText("");
        discardAllEdits();
        file = null;
        changed = false;
    }

    public void exit(){
        if(showSaveChangesDialog())return;
        System.exit(0);
    }

    private boolean showSaveChangesDialog(){
        if(!changed) return false;
        int result = JOptionPane.showConfirmDialog(null, "Save changes before closing?", "", JOptionPane.YES_NO_CANCEL_OPTION);
        if(result == JOptionPane.CANCEL_OPTION)return true;
        if(result == JOptionPane.YES_OPTION) return !save();
        return false;
    }

    public File getFile() {
        return file;
    }
}
