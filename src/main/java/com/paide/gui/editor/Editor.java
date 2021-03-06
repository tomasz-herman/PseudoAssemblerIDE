package com.paide.gui.editor;

import com.paide.Main;
import com.paide.gui.layout.MainLayout;
import com.paide.emulator.Assembler;
import com.paide.settings.Settings;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Editor extends RSyntaxTextArea {

    private File file = null;
    private JFileChooser inputChooser;
    private JFileChooser outputChooser;
    private boolean changed = false;
    private final RTextScrollPane panel;
    private static final ImageIcon ERROR_ICON;

    static {
        InputStream stream = Editor.class.getClassLoader().getResourceAsStream("error16x16.png");
        Image image = null;
        try {
            if (stream != null) image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (image != null) ERROR_ICON = new ImageIcon(image);
            else ERROR_ICON = new ImageIcon();
        }
    }

    public Editor(RTextScrollPane panel, Settings settings) {
        this(panel);
        applySettings(settings);
    }

    public Editor(RTextScrollPane panel) {
        this.panel = panel;
        setupFileChoosers();
        setupScrollPane();
        setupHighlighting();
        setup();
        setupChangeListener();
        setupHyperlinksSupport();
        registerKeyboardAction(getActionForKeyStroke(KeyStroke.getKeyStroke("ctrl Y")), KeyStroke.getKeyStroke("ctrl shift Z"), WHEN_FOCUSED);
        addParser(new Assembler());
        discardAllEdits();
    }

    public void applySettings(Settings settings) {
        setBackground(settings.getEditorBackgroundColor());
        setTextColor(settings.getEditorTextColor());
        setGutterBackgroundColor(settings.getEditorGutterColor());
        setInstructionColor(settings.getEditorInstructionColor());
        setTerminalInstructionColor(settings.getEditorTerminalInstructionColor());
        setDeclarationColor(settings.getEditorDeclarationColor());
        setOperatorColor(settings.getEditorOperatorColor());
        setDataTypeColor(settings.getEditorDataTypeColor());
        setNumberColor(settings.getEditorNumberColor());
        setCommentColor(settings.getEditorCommentColor());
        setSeparatorColor(settings.getEditorSeparatorColor());
        setLabelColor(settings.getEditorLabelColor());
        setQuotedColor(settings.getEditorQuotedColor());
        setErrorQuotedColor(settings.getEditorErrorQuotedColor());
        setCurrentLineHighlightColor(settings.getEditorCurrentLineHighlightColor());
        setSelectionColor(settings.getEditorSelectionColor());
        setSelectedTextColor(settings.getEditorSelectedTextColor());
        setIconRowHeaderEnabled(settings.isEditorIconRowHeader());
        setLineNumbersEnabled(settings.isEditorLineNumbers());
        setLineWrap(settings.isEditorLineWrap());
        setFont(new Font(settings.getEditorFontName(), java.awt.Font.PLAIN, settings.getEditorFontSize()));
        setGutterFont(new Font(settings.getEditorFontName(), java.awt.Font.PLAIN, settings.getEditorFontSize()));
        setupWorkingDirectory(settings.getWorkingDirectory());
    }

    private void setupHyperlinksSupport() {
        addHyperlinkListener(e -> {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setupFileChoosers() {
        inputChooser = new JFileChooser();
        outputChooser = new JFileChooser();
    }

    private void setupWorkingDirectory(String workingDirectoryPath) {
        File workingDirectory = new File(workingDirectoryPath);
        if(!workingDirectory.exists() || !workingDirectory.isDirectory()) {
            workingDirectory = new File(System.getProperty("user.home"));
        }
        inputChooser.setCurrentDirectory(workingDirectory);
        outputChooser.setCurrentDirectory(workingDirectory);
    }

    private void setupScrollPane() {
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.getGutter().setBackground(new JButton().getBackground());
        panel.setViewportView(this);
        panel.setLineNumbersEnabled(true);
        panel.setIconRowHeaderEnabled(true);
        panel.getGutter().setLineNumberFont(MainLayout.DEFAULT_FONT);
    }

    private void setupHighlighting() {
        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/pasm", "com.paide.gui.editor.PseudoAssemblerTokenMaker");
        setSyntaxEditingStyle("text/pasm");
    }

    private void setupChangeListener() {
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

    private void setup() {
        setTabsEmulated(true);
        setTabSize(4);
        setFadeCurrentLineHighlight(true);
        setEditable(false);
        setEnabled(false);
    }

    public void setIconRowHeaderEnabled(boolean enabled) {
        panel.setIconRowHeaderEnabled(enabled);
    }

    public boolean isIconRowHeaderEnabled() {
        return panel.isIconRowHeaderEnabled();
    }

    public void setLineNumbersEnabled(boolean enabled) {
        panel.setLineNumbersEnabled(enabled);
    }

    public boolean getLineNumbersEnabled() {
        return panel.getLineNumbersEnabled();
    }

    public void setGutterFont(Font font) {
        panel.getGutter().setLineNumberFont(font);
    }

    public void setGutterBackgroundColor(Color color) {
        panel.getGutter().setBackground(color);
        panel.setBackground(color);
        panel.getGutter().setIconRowHeaderInheritsGutterBackground(true);
    }

    public Color getGutterBackgroundColor() {
        return panel.getGutter().getBackground();
    }

    public void setTextColor(Color color) {
        setForeground(color);
        setCaretColor(color);
        panel.getGutter().setLineNumberColor(color);
    }

    public Color getTextColor() {
        return getForeground();
    }

    //DC, DS
    public void setDeclarationColor(Color color) {
        getSyntaxScheme().getStyle(Token.RESERVED_WORD).foreground = color;
        revalidate();
    }

    public Color getDeclarationColor() {
        return getSyntaxScheme().getStyle(Token.RESERVED_WORD).foreground;
    }

    //ass:
    public void setLabelColor(Color color) {
        getSyntaxScheme().getStyle(Token.PREPROCESSOR).foreground = color;
        revalidate();
    }

    public Color getLabelColor() {
        return getSyntaxScheme().getStyle(Token.PREPROCESSOR).foreground;
    }

    //INTEGER, BYTE, STRING
    public void setDataTypeColor(Color color) {
        getSyntaxScheme().getStyle(Token.DATA_TYPE).foreground = color;
        revalidate();
    }


    public Color getDataTypeColor() {
        return getSyntaxScheme().getStyle(Token.DATA_TYPE).foreground;
    }

    //EXIT, RET
    public void setTerminalInstructionColor(Color color) {
        getSyntaxScheme().getStyle(Token.RESERVED_WORD_2).foreground = color;
        revalidate();
    }

    public Color getTerminalInstructionColor() {
        return getSyntaxScheme().getStyle(Token.RESERVED_WORD_2).foreground;
    }

    //ADD, SUB, PUSH
    public void setInstructionColor(Color color) {
        getSyntaxScheme().getStyle(Token.FUNCTION).foreground = color;
        revalidate();
    }

    public Color getInstructionColor() {
        return getSyntaxScheme().getStyle(Token.FUNCTION).foreground;
    }

    //,+*
    public void setOperatorColor(Color color) {
        getSyntaxScheme().getStyle(Token.OPERATOR).foreground = color;
        revalidate();
    }

    public Color getOperatorColor() {
        return getSyntaxScheme().getStyle(Token.OPERATOR).foreground;
    }

    //3, 0x3, 34.0
    public void setNumberColor(Color color) {
        getSyntaxScheme().getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = color;
        getSyntaxScheme().getStyle(Token.LITERAL_NUMBER_FLOAT).foreground = color;
        getSyntaxScheme().getStyle(Token.LITERAL_NUMBER_HEXADECIMAL).foreground = color;
        revalidate();
    }

    public Color getNumberColor() {
        return getSyntaxScheme().getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground;
    }

    public void setCommentColor(Color color) {
        getSyntaxScheme().getStyle(Token.COMMENT_KEYWORD).foreground = color;
        getSyntaxScheme().getStyle(Token.COMMENT_MULTILINE).foreground = color;
        getSyntaxScheme().getStyle(Token.COMMENT_EOL).foreground = color;
        getSyntaxScheme().getStyle(Token.COMMENT_DOCUMENTATION).foreground = color;
        getSyntaxScheme().getStyle(Token.COMMENT_MARKUP).foreground = color;
        getSyntaxScheme().getStyle(Token.MARKUP_COMMENT).foreground = color;
        revalidate();
    }

    public Color getCommentColor() {
        return getSyntaxScheme().getStyle(Token.COMMENT_EOL).foreground;
    }

    //()
    public void setSeparatorColor(Color color) {
        getSyntaxScheme().getStyle(Token.SEPARATOR).foreground = color;
        revalidate();
    }

    public Color getSeparatorColor() {
        return getSyntaxScheme().getStyle(Token.SEPARATOR).foreground;
    }

    //''""
    public void setQuotedColor(Color color) {
        getSyntaxScheme().getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = color;
        getSyntaxScheme().getStyle(Token.LITERAL_CHAR).foreground = color;
        revalidate();
    }

    public Color getQuotedColor() {
        return getSyntaxScheme().getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground;
    }

    //'"
    public void setErrorQuotedColor(Color color) {
        getSyntaxScheme().getStyle(Token.ERROR_STRING_DOUBLE).foreground = color;
        getSyntaxScheme().getStyle(Token.ERROR_CHAR).foreground = color;
        revalidate();
    }

    public Color getErrorQuotedColor() {
        return getSyntaxScheme().getStyle(Token.ERROR_STRING_DOUBLE).foreground;
    }

    public void openNew() {
        if (showSaveChangesDialog()) return;
        clearErrors();
        setEnabled(true);
        setEditable(true);
        setText("");
        file = null;
        changed = false;
        discardAllEdits();
        grabFocus();
    }

    public void open() {
        if (showSaveChangesDialog()) return;
        int returnVal = inputChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = inputChooser.getSelectedFile();
            clearErrors();
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
            grabFocus();
        }
    }

    public boolean save() {
        if (file == null) return saveAs();
        try {
            changed = false;
            Files.writeString(file.toPath(), getText());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveAs() {
        int returnVal = outputChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = outputChooser.getSelectedFile();
            return save();
        }
        return false;
    }

    public void close() {
        if (showSaveChangesDialog()) return;
        clearErrors();
        setEnabled(false);
        setEditable(false);
        setText("");
        discardAllEdits();
        file = null;
        changed = false;
    }

    public void exit() {
        if (showSaveChangesDialog()) return;
        System.exit(0);
    }

    private boolean showSaveChangesDialog() {
        if (!changed) return false;
        int result = JOptionPane.showConfirmDialog(null, Main.I18N.getString("save.changes.before.closing"), "", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) return true;
        if (result == JOptionPane.YES_OPTION) return !save();
        return false;
    }

    public void markError(int line, String errorMessage) {
        try {
            panel.getGutter().addOffsetTrackingIcon(getLineStartOffset(line - 1), ERROR_ICON, errorMessage);
        } catch (BadLocationException ignored) {
        }
    }

    public void clearErrors() {
        panel.getGutter().removeAllTrackingIcons();
    }

    public File getFile() {
        return file;
    }
}
