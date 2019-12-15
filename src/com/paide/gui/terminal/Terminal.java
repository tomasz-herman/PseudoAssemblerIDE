package com.paide.gui.terminal;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Terminal {

    private int verticalScrollBarMaximumValue;
    private JTextArea textArea;
    private TerminalInputStream inputStream;
    private TerminalOutputStream outputStream;

    public Terminal(JScrollPane scrollPane){
        var font = new Font("Noto Sans Mono", Font.PLAIN,18);
        verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> {
            if ((verticalScrollBarMaximumValue - e.getAdjustable().getMaximum()) == 0) return;
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            verticalScrollBarMaximumValue = scrollPane.getVerticalScrollBar().getMaximum();
        });
        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setCaretColor(Color.white);
        textArea.getCaret().setBlinkRate(450);
        disableArrowKeys(textArea.getInputMap());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);
        inputStream = (TerminalInputStream)createInputStream(textArea);
        outputStream = (TerminalOutputStream)createOutputStream(textArea);
    }

    public Terminal(){
        textArea = new JTextArea();
        disableArrowKeys(textArea.getInputMap());
        textArea.setEditable(false);
        inputStream = (TerminalInputStream)createInputStream(textArea);
        outputStream = (TerminalOutputStream)createOutputStream(textArea);
    }

    private void disableArrowKeys(InputMap inputMap) {
        String[] keys = {"UP", "DOWN", "LEFT", "RIGHT", "HOME", "ENTER"};
        for (String key : keys) {
            inputMap.put(KeyStroke.getKeyStroke(key), "none");
        }
    }

    private InputStream createInputStream(JTextArea terminal){
        TerminalInputStream inputStream = new TerminalInputStream();
        terminal.addKeyListener(inputStream);
        System.setIn(inputStream);
        return inputStream;
    }

    private OutputStream createOutputStream(JTextArea terminal){
        TerminalOutputStream outputStream = new TerminalOutputStream(terminal);
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        System.setErr(printStream);
        return outputStream;
    }

    void clear() {
        textArea.setText("");
        outputStream.reset();
        inputStream.reset();
    }

    void flush() {
        outputStream.flush();
    }

    void setFont(Font font){
        textArea.setFont(font);
    }

    void setTextColor(Color color){
        textArea.setForeground(color);
    }

    void setBackgroundColor(Color color){
        textArea.setBackground(color);
    }

    Color getTextColor(){
        return textArea.getForeground();
    }

    Color getBackgroundColor(){
        return textArea.getBackground();
    }

    JTextArea getTextArea(){
        return textArea;
    }
}