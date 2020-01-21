package com.paide.gui.terminal;

import com.paide.gui.layout.MainLayout;
import com.paide.settings.Settings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Terminal extends JTextArea {

    private int verticalScrollBarMaximumValue;
    private TerminalInputStream inputStream;
    private TerminalOutputStream outputStream;

    public Terminal(@NotNull JScrollPane scrollPane, Settings settings){
        this(scrollPane);
        applySettings(settings);
    }

    public Terminal(Settings settings){
        this();
        applySettings(settings);
    }

    public Terminal(@NotNull JScrollPane scrollPane){
        this();
        verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            if ((verticalScrollBarMaximumValue - e.getAdjustable().getMaximum()) == 0) return;
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        });
        scrollPane.setViewportView(this);
    }

    public Terminal(){
        setFont(MainLayout.DEFAULT_FONT);
        setCaretColor(Color.white);
        getCaret().setBlinkRate(450);
        disableArrowKeys();
        setEditable(false);
        setLineWrap(true);
        inputStream = (TerminalInputStream)createInputStream();
        outputStream = (TerminalOutputStream)createOutputStream();
        registerKeyboardAction(getActionForKeyStroke(KeyStroke.getKeyStroke("ctrl C")), KeyStroke.getKeyStroke("ctrl shift C"), WHEN_FOCUSED);
        getInputMap().remove(KeyStroke.getKeyStroke("ctrl C"));
    }

    public void applySettings(Settings settings){
        setFont(new Font(settings.getTerminalFontName(), Font.PLAIN, settings.getTerminalFontSize()));
        setSelectedTextColor(settings.getTerminalSelectedTextColor());
        setSelectionColor(settings.getTerminalSelectionColor());
        setTextColor(settings.getTerminalTextColor());
        setBackgroundColor(settings.getTerminalBackgroundColor());
    }

    private void disableArrowKeys() {
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "HOME", "ENTER"};
        for (String key : keys) {
            getInputMap().put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    @NotNull
    private InputStream createInputStream(){
        TerminalInputStream inputStream = new TerminalInputStream();
        addKeyListener(inputStream);
        System.setIn(inputStream);
        return inputStream;
    }

    @NotNull
    private OutputStream createOutputStream(){
        TerminalOutputStream outputStream = new TerminalOutputStream(this);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.setErr(printStream);
        return outputStream;
    }

    public void clear() {
        setText("");
        outputStream.reset();
        inputStream.reset();
    }

    public void flush() {
        outputStream.flush();
    }

    public void setTextColor(Color color){
        setForeground(color);
    }

    public void setBackgroundColor(Color color){
        setBackground(color);
    }

    public Color getTextColor(){
        return getForeground();
    }

    public Color getBackgroundColor(){
        return getBackground();
    }
}