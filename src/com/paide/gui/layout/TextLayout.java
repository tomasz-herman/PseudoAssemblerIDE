package com.paide.gui.layout;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

import java.awt.*;

import static com.paide.gui.layout.MainLayout.DEFAULT_FONT;

public class TextLayout {
    private JTextPane textArea;
    private JPanel mainPanel;
    private JButton dismissButton;
    private JPanel textPanel;

    public static final String ABOUT =
            "<h1 style=\"font-size:32;\">Pseudo-Assembler IDE</h1>" +
            "<pre style=\"font-size:18;\">" +
            "Powered by open-source software\n" +
            "<a href=\"https://github.com/tomasz-herman/PseudoAssemblerEmulator\">Pseudo-Assembler Emulator</a> version: 3.1.6, available under <a href=\"https://github.com/tomasz-herman/PseudoAssemblerEmulator/blob/master/LICENSE\">MIT License</a>\n" +
            "<a href=\"https://github.com/bobbylight/RSyntaxTextArea\">RSyntaxTextArea</a> version: 3.0.4, available under <a href=\"https://github.com/bobbylight/RSyntaxTextArea/blob/master/RSyntaxTextArea/src/main/resources/META-INF/LICENSE\">BSD 3-Clause license</a>\n" +
            "<a href=\"https://github.com/JFormDesigner/FlatLaf\">FlatLaf</a> version: 0.22, available under <a href=\"https://github.com/JFormDesigner/FlatLaf/blob/master/LICENSE\">Apache 2.0 License</a>\n" +
            "Sources available at <a href=\"https://github.com/tomasz-herman/PseudoAssemblerIDE\">Github</a>\n" +
            "Copyright (c) 2019-2020 Tomasz Herman, Emil Dragańczuk\n" +
            "</pre>";
    public static final String HELP =
            "<pre style=\"font-size:18;\">" +
            "General shortcuts:\n" +
            "        Ctrl + S  - Save\n" +
            "        Ctrl + O  - Open\n" +
            "        Ctrl + N  - New\n" +
            "        Ctrl + F5 - Run\n" +
            "Shift + Ctrl + F5 - Debug\n" +
            "               F5 - Assemble\n" +
            "Shift        + F5 - Assemble and Load\n" +
            "Terminal specific shortcuts:\n" +
            "        Ctrl + C  - Trigger SIGINT\n" +
            "Shift + Ctrl + C  - Copy\n" +
            "Editor specific shortcuts:\n" +
            "        Ctrl + Z  - Undo\n" +
            "        Ctrl + Y  - Redo\n" +
            "        Ctrl + A  - Select all\n" +
            "        Ctrl + C  - Copy\n" +
            "        Ctrl + V  - Paste\n" +
            "        Ctrl + X  - Cut\n" +
            "Please refer to the Pseudo-Assembler reference manual for more help: <a href=\"https://github.com/tomasz-herman/PseudoAssemblerEmulator/raw/master/docs/reference_manual_pl.pdf/\">DOWNLOAD</a>" +
            "</pre>";
    public static final String LICENSE =
            "<pre style=\"font-size:18;\">" +
            "Copyright (c) 2019-2020 Tomasz Herman, Emil Dragańczuk\n" +
            "\n" +
            "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
            "of this software and associated documentation files (the \"Software\"), to deal\n" +
            "in the Software without restriction, including without limitation the rights\n" +
            "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
            "copies of the Software, and to permit persons to whom the Software is\n" +
            "furnished to do so, subject to the following conditions:\n" +
            "\n" +
            "The above copyright notice and this permission notice shall be included in all\n" +
            "copies or substantial portions of the Software.\n" +
            "\n" +
            "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
            "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
            "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
            "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
            "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
            "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
            "SOFTWARE." +
            "</pre>";

    public TextLayout(String text) {
        textPanel.setLayout(new FlowLayout());
        textArea.setFont(DEFAULT_FONT);
        textArea.setText(text);
        textArea.setEditable(false);
        textArea.setBackground(new JPanel().getBackground());
        textArea.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        dismissButton.addActionListener(e -> SwingUtilities.getWindowAncestor(mainPanel).setVisible(false));
        dismissButton.setFont(DEFAULT_FONT);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
