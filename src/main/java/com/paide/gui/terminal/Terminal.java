package com.paide.gui.terminal;

import com.hermant.terminal.JTerminal;
import com.hermant.terminal.TerminalController;
import com.hermant.terminal.io.TerminalInputStream;
import com.hermant.terminal.io.TerminalOutputStream;
import com.paide.settings.Settings;

import java.awt.*;

public class Terminal {

    private final JTerminal terminal;

    public Terminal(JTerminal terminal, Settings settings) {
        this.terminal = terminal;
        terminal.bindToSystemStreams();
        applySettings(settings);
    }

    public void applySettings(Settings settings) {
        terminal.getTis().setEchoToTos(true);
        terminal.setBackgroundColor(settings.getTerminalBackgroundColor());
        terminal.setTextColor(settings.getTerminalTextColor());
        terminal.setSelectedTextColor(settings.getTerminalSelectedTextColor());
        terminal.setSelectionColor(settings.getTerminalSelectionColor());
        terminal.setTerminalFont(new Font(settings.getTerminalFontName(), Font.PLAIN, settings.getTerminalFontSize()));
    }

    public TerminalController getTerminalController() {
        return terminal.getTerminalController();
    }

    public TerminalInputStream getInputStream() {
        return terminal.getTis();
    }

    public TerminalOutputStream getOutputStream() {
        return terminal.getTos();
    }
}