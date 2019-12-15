package com.paide.gui.terminal;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.OutputStream;

public class TerminalOutputStream extends OutputStream {

    private StringBuilder line = new StringBuilder();
    private final StringBuilder buffer = new StringBuilder();

    private int lines = 0;
    private int bufferedLines = 0;

    private static final int MAX_LINES = 16384;
    private static final int TRUNK = 4096;
    private static final int BUFFER_FLUSH_SIZE = 16384;

    private final int FRAMES_PER_SECOND = 25;
    private final long SKIP_TICKS = 1000000000 / FRAMES_PER_SECOND;

    public TerminalOutputStream(JTextArea area) {
        Thread updater = getUpdaterThread(area);
        updater.start();
    }

    private Thread getUpdaterThread(JTextArea area){
        return new Thread(() -> {
            long lastUpdate = System.nanoTime();
            while(true){
                long now = System.nanoTime();
                long elapsed = now - lastUpdate;
                if(elapsed > SKIP_TICKS || buffer.length() > BUFFER_FLUSH_SIZE){
                    //update text area
                    lastUpdate = now;
                    synchronized (buffer){
                        appendArea(area);
                        while(lines > MAX_LINES) cleanArea(area);
                    }
                }
                try {
                    Thread.sleep(0, 10);
                } catch (InterruptedException ignored) { }
            }
        });
    }

    private void cleanArea(JTextArea area){
        try {
            area.getDocument().remove(0, area.getLineEndOffset(TRUNK - 1));
            lines -= TRUNK;
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void appendArea(JTextArea area){
        if(buffer.length() != 0){
            try {
                area.append(buffer.toString());
                buffer.delete(0, buffer.length());
                lines += bufferedLines;
                bufferedLines = 0;
            } catch (Error ignored) {}
        }
    }

    void reset(){
        synchronized (buffer){
            buffer.delete(0, buffer.length());
            bufferedLines = 0;
            line.setLength(0);
            lines = 0;
        }
    }

    @Override
    public void write(int b) {
        line.append((char)b);
        if(b=='\n'){
            synchronized (buffer){
                bufferedLines++;
                buffer.append(line.toString());
                line.setLength(0);
            }
            //if the updater thread is clogged slow down
            if(lines > MAX_LINES + TRUNK || buffer.length() > BUFFER_FLUSH_SIZE + TRUNK) try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) { }
        }
    }

    @Override
    public void flush() {
        synchronized (buffer){
            String s = line.toString();
            buffer.append(s);
            if(s.contains("\n"))bufferedLines++;
            line.setLength(0);
        }
    }

}
