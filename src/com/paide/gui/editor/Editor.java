package com.paide.gui.editor;

import com.paide.gui.Layout;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class Editor extends RSyntaxTextArea {

    private RTextScrollPane panel;

    public Editor(RTextScrollPane panel) {
        this.panel = panel;
        setupScrollPane();
        setupDefaultTheme();
        registerKeyboardAction(getActionForKeyStroke(KeyStroke.getKeyStroke("ctrl Y")), KeyStroke.getKeyStroke("ctrl shift Z"), WHEN_FOCUSED);
        discardAllEdits();
    }

    private void setupScrollPane(){
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.getGutter().setBackground(new JButton().getBackground());
        panel.setViewportView(this);
        panel.setLineNumbersEnabled(true);
        panel.setIconRowHeaderEnabled(true);
        panel.getGutter().setLineNumberFont(Layout.DEFAULT_FONT);
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
}
