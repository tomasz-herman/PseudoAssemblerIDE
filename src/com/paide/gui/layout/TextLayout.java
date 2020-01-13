package com.paide.gui.layout;

import com.paide.Main;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

import java.awt.*;

import static com.paide.gui.layout.MainLayout.DEFAULT_FONT;

public class TextLayout {
    private JTextPane textArea;
    private JPanel mainPanel;
    private JButton dismissButton;
    private JPanel textPanel;

    public static final String ABOUT = Main.I18N.getString("about.text");
    public static final String HELP = Main.I18N.getString("help.text");
    public static final String LICENSE = Main.I18N.getString("license.text");

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
