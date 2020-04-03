package com.paide.gui.layout;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.paide.Main;
import com.paide.gui.editor.Editor;
import com.paide.gui.terminal.Terminal;
import com.paide.settings.Settings;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;

public class SettingsLayout {
    private JPanel mainPanel;

    private JComboBox<String> editorFontCombo;
    private JComboBox<String> terminalFontCombo;
    private JTextField editorFontSize;
    private JTextField terminalFontSize;

    private JButton cancelButton;
    private JButton applyButton;
    private JButton okButton;

    private JPanel editorBackgroundColor;
    private JPanel gutterColor;
    private JPanel editorTextColor;
    private JPanel functionColor;
    private JPanel function2Color;
    private JPanel declarationColor;
    private JPanel operatorColor;
    private JPanel dataTypeColor;
    private JPanel numberColor;
    private JPanel commentColor;
    private JPanel labelColor;
    private JPanel bracketsColor;
    private JPanel quotedColor;
    private JPanel errorQuotedColor;
    private JPanel currentLineColor;
    private JPanel terminalBackgroundColor;
    private JPanel terminalTextColor;
    private JPanel editorSelectionColor;
    private JPanel editorSelectedTextColor;
    private JPanel terminalSelectionColor;
    private JPanel terminalSelectedTextColor;
    private JCheckBox showLineNumbersCheckBox;
    private JCheckBox showIconRowHeaderCheckBox;
    private JCheckBox wrapLinesCheckBox;

    private Settings settings;

    public SettingsLayout(Editor editor, Terminal terminal) {
        settings = new Settings();
        initColors();
        initFonts();
        initButtons(editor, terminal);
    }

    public void initButtons(Editor editor, Terminal terminal) {
        applyButton.addActionListener(e -> apply(editor, terminal));
        okButton.addActionListener(e -> ok(editor, terminal));
        cancelButton.addActionListener(e -> cancel());
        showIconRowHeaderCheckBox.setSelected(settings.isEditorIconRowHeader());
        showLineNumbersCheckBox.setSelected(settings.isEditorLineNumbers());
        wrapLinesCheckBox.setSelected(settings.isEditorLineWrap());
    }

    public void initColors() {
        setupColorChooser(editorBackgroundColor).setBackground(settings.getEditorBackgroundColor());
        setupColorChooser(gutterColor).setBackground(settings.getEditorGutterColor());
        setupColorChooser(editorTextColor).setBackground(settings.getEditorTextColor());
        setupColorChooser(functionColor).setBackground(settings.getEditorInstructionColor());
        setupColorChooser(function2Color).setBackground(settings.getEditorTerminalInstructionColor());
        setupColorChooser(declarationColor).setBackground(settings.getEditorDeclarationColor());
        setupColorChooser(operatorColor).setBackground(settings.getEditorOperatorColor());
        setupColorChooser(dataTypeColor).setBackground(settings.getEditorDataTypeColor());
        setupColorChooser(numberColor).setBackground(settings.getEditorNumberColor());
        setupColorChooser(commentColor).setBackground(settings.getEditorCommentColor());
        setupColorChooser(labelColor).setBackground(settings.getEditorLabelColor());
        setupColorChooser(bracketsColor).setBackground(settings.getEditorSeparatorColor());
        setupColorChooser(quotedColor).setBackground(settings.getEditorQuotedColor());
        setupColorChooser(errorQuotedColor).setBackground(settings.getEditorErrorQuotedColor());
        setupColorChooser(currentLineColor).setBackground(settings.getEditorCurrentLineHighlightColor());
        setupColorChooser(editorSelectionColor).setBackground(settings.getEditorSelectionColor());
        setupColorChooser(editorSelectedTextColor).setBackground(settings.getEditorSelectedTextColor());
        setupColorChooser(terminalBackgroundColor).setBackground(settings.getTerminalBackgroundColor());
        setupColorChooser(terminalTextColor).setBackground(settings.getTerminalTextColor());
        setupColorChooser(terminalSelectionColor).setBackground(settings.getTerminalSelectionColor());
        setupColorChooser(terminalSelectedTextColor).setBackground(settings.getTerminalSelectedTextColor());
    }

    public void initFonts() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        editorFontCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
        editorFontCombo.addPopupMenuListener(new BoundsPopupMenuListener(true, false));
        terminalFontCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");
        terminalFontCombo.addPopupMenuListener(new BoundsPopupMenuListener(true, false));
        for (String font : fonts) {
            editorFontCombo.addItem(font);
            terminalFontCombo.addItem(font);
        }
        editorFontCombo.setSelectedItem(settings.getEditorFontName());
        terminalFontCombo.setSelectedItem(settings.getTerminalFontName());
        editorFontSize.setText(Integer.toString(settings.getEditorFontSize()));
        terminalFontSize.setText(Integer.toString(settings.getTerminalFontSize()));
        ((PlainDocument) editorFontSize.getDocument()).setDocumentFilter(new SmallIntegerFilter());
        ((PlainDocument) terminalFontSize.getDocument()).setDocumentFilter(new SmallIntegerFilter());
    }

    public void ok(Editor editor, Terminal terminal) {
        apply(editor, terminal);
        cancel();
    }

    public void apply(Editor editor, Terminal terminal) {
        readFontsSettings();
        readColorSettings();
        readOtherSettings();
        terminal.applySettings(settings);
        editor.applySettings(settings);
        settings.save();
    }

    public void cancel() {
        SwingUtilities.getWindowAncestor(mainPanel).dispose();
    }

    public void readColorSettings() {
        settings.setEditorBackgroundColor(editorBackgroundColor.getBackground());
        settings.setEditorTextColor(editorTextColor.getBackground());
        settings.setEditorGutterColor(gutterColor.getBackground());
        settings.setEditorInstructionColor(functionColor.getBackground());
        settings.setEditorTerminalInstructionColor(function2Color.getBackground());
        settings.setEditorDeclarationColor(declarationColor.getBackground());
        settings.setEditorOperatorColor(operatorColor.getBackground());
        settings.setEditorDataTypeColor(dataTypeColor.getBackground());
        settings.setEditorNumberColor(numberColor.getBackground());
        settings.setEditorCommentColor(commentColor.getBackground());
        settings.setEditorSeparatorColor(bracketsColor.getBackground());
        settings.setEditorLabelColor(labelColor.getBackground());
        settings.setEditorQuotedColor(quotedColor.getBackground());
        settings.setEditorErrorQuotedColor(errorQuotedColor.getBackground());
        settings.setEditorCurrentLineHighlightColor(currentLineColor.getBackground());
        settings.setEditorSelectionColor(editorSelectionColor.getBackground());
        settings.setEditorSelectedTextColor(editorSelectedTextColor.getBackground());
        settings.setTerminalBackgroundColor(terminalBackgroundColor.getBackground());
        settings.setTerminalTextColor(terminalTextColor.getBackground());
        settings.setTerminalSelectionColor(terminalSelectionColor.getBackground());
        settings.setTerminalSelectedTextColor(terminalSelectedTextColor.getBackground());
    }

    private void readOtherSettings() {
        settings.setEditorIconRowHeader(showIconRowHeaderCheckBox.isSelected());
        settings.setEditorLineNumbers(showLineNumbersCheckBox.isSelected());
        settings.setEditorLineWrap(wrapLinesCheckBox.isSelected());
    }

    private JPanel setupColorChooser(JPanel panel) {
        var listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Color color = JColorChooser.showDialog(null, Main.I18N.getString("choose.color"), panel.getBackground());
                panel.setBackground(color);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        };
        panel.addMouseListener(listener);
        return panel;
    }

    public void readFontsSettings() {
        settings.setEditorFontName((String) editorFontCombo.getSelectedItem());
        settings.setEditorFontSize(Integer.parseUnsignedInt(editorFontSize.getText()));
        settings.setEditorFontName((String) editorFontCombo.getSelectedItem());
        settings.setEditorFontSize(Integer.parseUnsignedInt(editorFontSize.getText()));
        settings.setTerminalFontName((String) terminalFontCombo.getSelectedItem());
        settings.setTerminalFontSize(Integer.parseUnsignedInt(terminalFontSize.getText()));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), -1, -1));
        final JTabbedPane tabbedPane1 = new JTabbedPane();
        Font tabbedPane1Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, tabbedPane1.getFont());
        if (tabbedPane1Font != null) tabbedPane1.setFont(tabbedPane1Font);
        tabbedPane1.setTabPlacement(1);
        mainPanel.add(tabbedPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab(ResourceBundle.getBundle("i18n").getString("font"), panel1);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Monospaced", Font.BOLD, 24, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("i18n").getString("font"));
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        this.$$$loadLabelText$$$(label2, ResourceBundle.getBundle("i18n").getString("editor.font.size"));
        panel2.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        editorFontSize = new JTextField();
        Font editorFontSizeFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, editorFontSize.getFont());
        if (editorFontSizeFont != null) editorFontSize.setFont(editorFontSizeFont);
        editorFontSize.setHorizontalAlignment(11);
        panel2.add(editorFontSize, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        this.$$$loadLabelText$$$(label3, ResourceBundle.getBundle("i18n").getString("editor.font"));
        panel2.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editorFontCombo = new JComboBox();
        Font editorFontComboFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, editorFontCombo.getFont());
        if (editorFontComboFont != null) editorFontCombo.setFont(editorFontComboFont);
        panel2.add(editorFontCombo, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(240, -1), 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        this.$$$loadLabelText$$$(label4, ResourceBundle.getBundle("i18n").getString("terminal.font"));
        panel2.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        this.$$$loadLabelText$$$(label5, ResourceBundle.getBundle("i18n").getString("terminal.font.size"));
        panel2.add(label5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalFontCombo = new JComboBox();
        Font terminalFontComboFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, terminalFontCombo.getFont());
        if (terminalFontComboFont != null) terminalFontCombo.setFont(terminalFontComboFont);
        panel2.add(terminalFontCombo, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(240, -1), 0, false));
        terminalFontSize = new JTextField();
        Font terminalFontSizeFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, terminalFontSize.getFont());
        if (terminalFontSizeFont != null) terminalFontSize.setFont(terminalFontSizeFont);
        terminalFontSize.setHorizontalAlignment(11);
        panel2.add(terminalFontSize, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, -1), new Dimension(50, -1), 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        tabbedPane1.addTab(ResourceBundle.getBundle("i18n").getString("colors"), scrollPane1);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(24, 3, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(panel3);
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Monospaced", Font.BOLD, 24, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        this.$$$loadLabelText$$$(label6, ResourceBundle.getBundle("i18n").getString("colors"));
        panel3.add(label6, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        this.$$$loadLabelText$$$(label7, ResourceBundle.getBundle("i18n").getString("background"));
        panel3.add(label7, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editorBackgroundColor = new JPanel();
        editorBackgroundColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(editorBackgroundColor, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(120, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        this.$$$loadLabelText$$$(label8, ResourceBundle.getBundle("i18n").getString("gutter"));
        panel3.add(label8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gutterColor = new JPanel();
        gutterColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(gutterColor, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        this.$$$loadLabelText$$$(label9, ResourceBundle.getBundle("i18n").getString("text"));
        panel3.add(label9, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel3.add(spacer6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        editorTextColor = new JPanel();
        editorTextColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(editorTextColor, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        this.$$$loadLabelText$$$(label10, ResourceBundle.getBundle("i18n").getString("function"));
        panel3.add(label10, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        functionColor = new JPanel();
        functionColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(functionColor, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        this.$$$loadLabelText$$$(label11, ResourceBundle.getBundle("i18n").getString("function.2"));
        panel3.add(label11, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        function2Color = new JPanel();
        function2Color.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(function2Color, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        Font label12Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label12.getFont());
        if (label12Font != null) label12.setFont(label12Font);
        this.$$$loadLabelText$$$(label12, ResourceBundle.getBundle("i18n").getString("declaration"));
        panel3.add(label12, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        declarationColor = new JPanel();
        declarationColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(declarationColor, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        Font label13Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label13.getFont());
        if (label13Font != null) label13.setFont(label13Font);
        this.$$$loadLabelText$$$(label13, ResourceBundle.getBundle("i18n").getString("operator"));
        panel3.add(label13, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        operatorColor = new JPanel();
        operatorColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(operatorColor, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        Font label14Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label14.getFont());
        if (label14Font != null) label14.setFont(label14Font);
        this.$$$loadLabelText$$$(label14, ResourceBundle.getBundle("i18n").getString("data.type"));
        panel3.add(label14, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dataTypeColor = new JPanel();
        dataTypeColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(dataTypeColor, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        Font label15Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label15.getFont());
        if (label15Font != null) label15.setFont(label15Font);
        this.$$$loadLabelText$$$(label15, ResourceBundle.getBundle("i18n").getString("number"));
        panel3.add(label15, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberColor = new JPanel();
        numberColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(numberColor, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        Font label16Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label16.getFont());
        if (label16Font != null) label16.setFont(label16Font);
        this.$$$loadLabelText$$$(label16, ResourceBundle.getBundle("i18n").getString("comment"));
        panel3.add(label16, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commentColor = new JPanel();
        commentColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(commentColor, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        Font label17Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label17.getFont());
        if (label17Font != null) label17.setFont(label17Font);
        this.$$$loadLabelText$$$(label17, ResourceBundle.getBundle("i18n").getString("label"));
        panel3.add(label17, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelColor = new JPanel();
        labelColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(labelColor, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        Font label18Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label18.getFont());
        if (label18Font != null) label18.setFont(label18Font);
        this.$$$loadLabelText$$$(label18, ResourceBundle.getBundle("i18n").getString("brackets"));
        panel3.add(label18, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bracketsColor = new JPanel();
        bracketsColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(bracketsColor, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        Font label19Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label19.getFont());
        if (label19Font != null) label19.setFont(label19Font);
        this.$$$loadLabelText$$$(label19, ResourceBundle.getBundle("i18n").getString("quoted"));
        panel3.add(label19, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        quotedColor = new JPanel();
        quotedColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(quotedColor, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        Font label20Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label20.getFont());
        if (label20Font != null) label20.setFont(label20Font);
        this.$$$loadLabelText$$$(label20, ResourceBundle.getBundle("i18n").getString("error.quoted"));
        panel3.add(label20, new GridConstraints(15, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorQuotedColor = new JPanel();
        errorQuotedColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(errorQuotedColor, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        Font label21Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label21.getFont());
        if (label21Font != null) label21.setFont(label21Font);
        this.$$$loadLabelText$$$(label21, ResourceBundle.getBundle("i18n").getString("current.line"));
        panel3.add(label21, new GridConstraints(16, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentLineColor = new JPanel();
        currentLineColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(currentLineColor, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label22 = new JLabel();
        Font label22Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label22.getFont());
        if (label22Font != null) label22.setFont(label22Font);
        this.$$$loadLabelText$$$(label22, ResourceBundle.getBundle("i18n").getString("selection"));
        panel3.add(label22, new GridConstraints(17, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editorSelectionColor = new JPanel();
        editorSelectionColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(editorSelectionColor, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label23 = new JLabel();
        Font label23Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 20, label23.getFont());
        if (label23Font != null) label23.setFont(label23Font);
        this.$$$loadLabelText$$$(label23, ResourceBundle.getBundle("i18n").getString("terminal.color.scheme"));
        panel3.add(label23, new GridConstraints(19, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label24 = new JLabel();
        Font label24Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label24.getFont());
        if (label24Font != null) label24.setFont(label24Font);
        this.$$$loadLabelText$$$(label24, ResourceBundle.getBundle("i18n").getString("selected.text"));
        panel3.add(label24, new GridConstraints(18, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editorSelectedTextColor = new JPanel();
        editorSelectedTextColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(editorSelectedTextColor, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label25 = new JLabel();
        Font label25Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label25.getFont());
        if (label25Font != null) label25.setFont(label25Font);
        this.$$$loadLabelText$$$(label25, ResourceBundle.getBundle("i18n").getString("background"));
        panel3.add(label25, new GridConstraints(20, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalBackgroundColor = new JPanel();
        terminalBackgroundColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(terminalBackgroundColor, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label26 = new JLabel();
        Font label26Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label26.getFont());
        if (label26Font != null) label26.setFont(label26Font);
        this.$$$loadLabelText$$$(label26, ResourceBundle.getBundle("i18n").getString("text"));
        panel3.add(label26, new GridConstraints(21, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalTextColor = new JPanel();
        terminalTextColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(terminalTextColor, new GridConstraints(21, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label27 = new JLabel();
        Font label27Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label27.getFont());
        if (label27Font != null) label27.setFont(label27Font);
        this.$$$loadLabelText$$$(label27, ResourceBundle.getBundle("i18n").getString("selection"));
        panel3.add(label27, new GridConstraints(22, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalSelectionColor = new JPanel();
        terminalSelectionColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(terminalSelectionColor, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label28 = new JLabel();
        Font label28Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 16, label28.getFont());
        if (label28Font != null) label28.setFont(label28Font);
        this.$$$loadLabelText$$$(label28, ResourceBundle.getBundle("i18n").getString("selected.text"));
        panel3.add(label28, new GridConstraints(23, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalSelectedTextColor = new JPanel();
        terminalSelectedTextColor.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(terminalSelectedTextColor, new GridConstraints(23, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label29 = new JLabel();
        Font label29Font = this.$$$getFont$$$("Monospaced", Font.PLAIN, 20, label29.getFont());
        if (label29Font != null) label29.setFont(label29Font);
        this.$$$loadLabelText$$$(label29, ResourceBundle.getBundle("i18n").getString("editor.color.scheme"));
        panel3.add(label29, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab(ResourceBundle.getBundle("i18n").getString("general"), panel4);
        final JLabel label30 = new JLabel();
        Font label30Font = this.$$$getFont$$$("Monospaced", Font.BOLD, 24, label30.getFont());
        if (label30Font != null) label30.setFont(label30Font);
        this.$$$loadLabelText$$$(label30, ResourceBundle.getBundle("i18n").getString("general"));
        panel4.add(label30, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel4.add(spacer7, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        showLineNumbersCheckBox = new JCheckBox();
        Font showLineNumbersCheckBoxFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, showLineNumbersCheckBox.getFont());
        if (showLineNumbersCheckBoxFont != null) showLineNumbersCheckBox.setFont(showLineNumbersCheckBoxFont);
        this.$$$loadButtonText$$$(showLineNumbersCheckBox, ResourceBundle.getBundle("i18n").getString("show.line.numbers"));
        panel4.add(showLineNumbersCheckBox, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showIconRowHeaderCheckBox = new JCheckBox();
        Font showIconRowHeaderCheckBoxFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, showIconRowHeaderCheckBox.getFont());
        if (showIconRowHeaderCheckBoxFont != null) showIconRowHeaderCheckBox.setFont(showIconRowHeaderCheckBoxFont);
        this.$$$loadButtonText$$$(showIconRowHeaderCheckBox, ResourceBundle.getBundle("i18n").getString("show.icon.row.header"));
        panel4.add(showIconRowHeaderCheckBox, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wrapLinesCheckBox = new JCheckBox();
        Font wrapLinesCheckBoxFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, wrapLinesCheckBox.getFont());
        if (wrapLinesCheckBoxFont != null) wrapLinesCheckBox.setFont(wrapLinesCheckBoxFont);
        this.$$$loadButtonText$$$(wrapLinesCheckBox, ResourceBundle.getBundle("i18n").getString("wrap.lines"));
        panel4.add(wrapLinesCheckBox, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 4, new Insets(2, 5, 5, 2), -1, -1));
        mainPanel.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        Font cancelButtonFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, cancelButton.getFont());
        if (cancelButtonFont != null) cancelButton.setFont(cancelButtonFont);
        this.$$$loadButtonText$$$(cancelButton, ResourceBundle.getBundle("i18n").getString("cancel"));
        panel5.add(cancelButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel5.add(spacer8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        applyButton = new JButton();
        Font applyButtonFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, applyButton.getFont());
        if (applyButtonFont != null) applyButton.setFont(applyButtonFont);
        this.$$$loadButtonText$$$(applyButton, ResourceBundle.getBundle("i18n").getString("apply"));
        panel5.add(applyButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        okButton = new JButton();
        Font okButtonFont = this.$$$getFont$$$("Monospaced", Font.PLAIN, 18, okButton.getFont());
        if (okButtonFont != null) okButton.setFont(okButtonFont);
        this.$$$loadButtonText$$$(okButton, ResourceBundle.getBundle("i18n").getString("ok"));
        panel5.add(okButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private static class SmallIntegerFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        private boolean test(String text) {
            return text.matches("[1-9]\\d?\\d?");
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString())) {
                super.remove(fb, offset, length);
            }
        }
    }


    /**
     * This class will change the bounds of the JComboBox popup menu to support
     * different functionality. It will support the following features:
     * -  a horizontal scrollbar can be displayed when necessary
     * -  the popup can be wider than the combo box
     * -  the popup can be displayed above the combo box
     * <p>
     * Class will only work for a JComboBox that uses a BasicComboPop.
     */
    private static class BoundsPopupMenuListener implements PopupMenuListener {
        private boolean scrollBarRequired = true;
        private boolean popupWider;
        private int maximumWidth = -1;
        private boolean popupAbove;
        private JScrollPane scrollPane;

        /**
         * Convenience constructore to allow the display of a horizontal scrollbar
         * when required.
         */
        public BoundsPopupMenuListener() {
            this(true, false, -1, false);
        }

        /**
         * Convenience constructor that allows you to display the popup
         * wider and/or above the combo box.
         *
         * @param popupWider when true, popup width is based on the popup
         *                   preferred width
         * @param popupAbove when true, popup is displayed above the combobox
         */
        public BoundsPopupMenuListener(boolean popupWider, boolean popupAbove) {
            this(true, popupWider, -1, popupAbove);
        }

        /**
         * Convenience constructor that allows you to display the popup
         * wider than the combo box and to specify the maximum width
         *
         * @param maximumWidth the maximum width of the popup. The
         *                     popupAbove value is set to "true".
         */
        public BoundsPopupMenuListener(int maximumWidth) {
            this(true, true, maximumWidth, false);
        }

        /**
         * General purpose constructor to set all popup properties at once.
         *
         * @param scrollBarRequired display a horizontal scrollbar when the
         *                          preferred width of popup is greater than width of scrollPane.
         * @param popupWider        display the popup at its preferred with
         * @param maximumWidth      limit the popup width to the value specified
         *                          (minimum size will be the width of the combo box)
         * @param popupAbove        display the popup above the combo box
         */
        public BoundsPopupMenuListener(
                boolean scrollBarRequired, boolean popupWider, int maximumWidth, boolean popupAbove) {
            setScrollBarRequired(scrollBarRequired);
            setPopupWider(popupWider);
            setMaximumWidth(maximumWidth);
            setPopupAbove(popupAbove);
        }

        /**
         * Return the maximum width of the popup.
         *
         * @return the maximumWidth value
         */
        public int getMaximumWidth() {
            return maximumWidth;
        }

        /**
         * Set the maximum width for the popup. This value is only used when
         * setPopupWider( true ) has been specified. A value of -1 indicates
         * that there is no maximum.
         *
         * @param maximumWidth the maximum width of the popup
         */
        public void setMaximumWidth(int maximumWidth) {
            this.maximumWidth = maximumWidth;
        }

        /**
         * Determine if the popup should be displayed above the combo box.
         *
         * @return the popupAbove value
         */
        public boolean isPopupAbove() {
            return popupAbove;
        }

        /**
         * Change the location of the popup relative to the combo box.
         *
         * @param popupAbove true display popup above the combo box,
         *                   false display popup below the combo box.
         */
        public void setPopupAbove(boolean popupAbove) {
            this.popupAbove = popupAbove;
        }

        /**
         * Determine if the popup might be displayed wider than the combo box
         *
         * @return the popupWider value
         */
        public boolean isPopupWider() {
            return popupWider;
        }

        /**
         * Change the width of the popup to be the greater of the width of the
         * combo box or the preferred width of the popup. Normally the popup width
         * is always the same size as the combo box width.
         *
         * @param popupWider true adjust the width as required.
         */
        public void setPopupWider(boolean popupWider) {
            this.popupWider = popupWider;
        }

        /**
         * Determine if the horizontal scroll bar might be required for the popup
         *
         * @return the scrollBarRequired value
         */
        public boolean isScrollBarRequired() {
            return scrollBarRequired;
        }

        /**
         * For some reason the default implementation of the popup removes the
         * horizontal scrollBar from the popup scroll pane which can result in
         * the truncation of the rendered items in the popop. Adding a scrollBar
         * back to the scrollPane will allow horizontal scrolling if necessary.
         *
         * @param scrollBarRequired true add horizontal scrollBar to scrollPane
         *                          false remove the horizontal scrollBar
         */
        public void setScrollBarRequired(boolean scrollBarRequired) {
            this.scrollBarRequired = scrollBarRequired;
        }

        /**
         * Alter the bounds of the popup just before it is made visible.
         */
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            @SuppressWarnings("rawtypes") JComboBox comboBox = (JComboBox) e.getSource();

            if (comboBox.getItemCount() == 0) return;

            final Object child = comboBox.getAccessibleContext().getAccessibleChild(0);

            if (child instanceof BasicComboPopup)
                SwingUtilities.invokeLater(() -> customizePopup((BasicComboPopup) child));
        }

        protected void customizePopup(BasicComboPopup popup) {
            scrollPane = getScrollPane(popup);

            if (popupWider)
                popupWider(popup);

            checkHorizontalScrollBar(popup);

            //  For some reason in JDK7 the popup will not display at its preferred
            //  width unless its location has been changed from its default
            //  (ie. for normal "pop down" shift the popup and reset)

            Component comboBox = popup.getInvoker();
            Point location = comboBox.getLocationOnScreen();

            if (popupAbove) {
                int height = popup.getPreferredSize().height;
                popup.setLocation(location.x, location.y - height);
            } else {
                int height = comboBox.getPreferredSize().height;
                popup.setLocation(location.x, location.y + height - 1);
                popup.setLocation(location.x, location.y + height);
            }
        }

        /*
         *  Adjust the width of the scrollpane used by the popup
         */
        protected void popupWider(BasicComboPopup popup) {
            @SuppressWarnings("rawtypes") JList list = popup.getList();

            //  Determine the maximimum width to use:
            //  a) determine the popup preferred width
            //  b) limit width to the maximum if specified
            //  c) ensure width is not less than the scroll pane width

            int popupWidth = list.getPreferredSize().width
                    + 5  // make sure horizontal scrollbar doesn't appear
                    + getScrollBarWidth(popup, scrollPane);

            if (maximumWidth != -1) {
                popupWidth = Math.min(popupWidth, maximumWidth);
            }

            Dimension scrollPaneSize = scrollPane.getPreferredSize();
            popupWidth = Math.max(popupWidth, scrollPaneSize.width);

            //  Adjust the width

            scrollPaneSize.width = popupWidth;
            scrollPane.setPreferredSize(scrollPaneSize);
            scrollPane.setMaximumSize(scrollPaneSize);
        }

        /*
         *  This method is called every time:
         *  - to make sure the viewport is returned to its default position
         *  - to remove the horizontal scrollbar when it is not wanted
         */
        private void checkHorizontalScrollBar(BasicComboPopup popup) {
            //  Reset the viewport to the left

            JViewport viewport = scrollPane.getViewport();
            Point p = viewport.getViewPosition();
            p.x = 0;
            viewport.setViewPosition(p);

            //  Remove the scrollbar so it is never painted

            if (!scrollBarRequired) {
                scrollPane.setHorizontalScrollBar(null);
                return;
            }

            //	Make sure a horizontal scrollbar exists in the scrollpane

            JScrollBar horizontal = scrollPane.getHorizontalScrollBar();

            if (horizontal == null) {
                horizontal = new JScrollBar(JScrollBar.HORIZONTAL);
                scrollPane.setHorizontalScrollBar(horizontal);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            }

            //	Potentially increase height of scroll pane to display the scrollbar

            if (horizontalScrollBarWillBeVisible(popup, scrollPane)) {
                Dimension scrollPaneSize = scrollPane.getPreferredSize();
                scrollPaneSize.height += horizontal.getPreferredSize().height;
                scrollPane.setPreferredSize(scrollPaneSize);
                scrollPane.setMaximumSize(scrollPaneSize);
                scrollPane.revalidate();
            }
        }

        /*
         *  Get the scroll pane used by the popup so its bounds can be adjusted
         */
        protected JScrollPane getScrollPane(BasicComboPopup popup) {
            @SuppressWarnings("rawtypes") JList list = popup.getList();
            Container c = SwingUtilities.getAncestorOfClass(JScrollPane.class, list);

            return (JScrollPane) c;
        }

        /*
         *  I can't find any property on the scrollBar to determine if it will be
         *  displayed or not so use brute force to determine this.
         */
        protected int getScrollBarWidth(BasicComboPopup popup, JScrollPane scrollPane) {
            int scrollBarWidth = 0;
            @SuppressWarnings("rawtypes") JComboBox comboBox = (JComboBox) popup.getInvoker();

            if (comboBox.getItemCount() > comboBox.getMaximumRowCount()) {
                JScrollBar vertical = scrollPane.getVerticalScrollBar();
                scrollBarWidth = vertical.getPreferredSize().width;
            }

            return scrollBarWidth;
        }

        /*
         *  I can't find any property on the scrollBar to determine if it will be
         *  displayed or not so use brute force to determine this.
         */
        protected boolean horizontalScrollBarWillBeVisible(BasicComboPopup popup, JScrollPane scrollPane) {
            @SuppressWarnings("rawtypes") JList list = popup.getList();
            int scrollBarWidth = getScrollBarWidth(popup, scrollPane);
            int popupWidth = list.getPreferredSize().width + scrollBarWidth;

            return popupWidth > scrollPane.getPreferredSize().width;
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            //  In its normal state the scrollpane does not have a scrollbar

            if (scrollPane != null) {
                scrollPane.setHorizontalScrollBar(null);
            }
        }
    }
}
