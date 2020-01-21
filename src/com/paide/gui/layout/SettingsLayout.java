package com.paide.gui.layout;

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

    public SettingsLayout(Editor editor, Terminal terminal){
        settings = new Settings();
        initColors();
        initFonts();
        initButtons(editor, terminal);
    }

    public void initButtons(Editor editor, Terminal terminal){
        applyButton.addActionListener(e -> apply(editor, terminal));
        okButton.addActionListener(e -> ok(editor, terminal));
        cancelButton.addActionListener(e -> cancel());
        showIconRowHeaderCheckBox.setSelected(settings.isEditorIconRowHeader());
        showLineNumbersCheckBox.setSelected(settings.isEditorLineNumbers());
        wrapLinesCheckBox.setSelected(settings.isEditorLineWrap());
    }

    public void initColors(){
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

    public void initFonts(){
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
        ((PlainDocument)editorFontSize.getDocument()).setDocumentFilter(new SmallIntegerFilter());
        ((PlainDocument)terminalFontSize.getDocument()).setDocumentFilter(new SmallIntegerFilter());
}

    public void ok(Editor editor, Terminal terminal){
        apply(editor, terminal);
        cancel();
    }

    public void apply(Editor editor, Terminal terminal){
        applyFonts(editor, terminal);
        applyColors(editor, terminal);
        applyOthers(editor, terminal);
        settings.save();
    }

    public void cancel(){
        SwingUtilities.getWindowAncestor(mainPanel).dispose();
    }

    public void applyColors(Editor editor, Terminal terminal){
        editor.setBackground(settings.setEditorBackgroundColor(editorBackgroundColor.getBackground()));
        editor.setTextColor(settings.setEditorTextColor(editorTextColor.getBackground()));
        editor.setGutterBackgroundColor(settings.setEditorGutterColor(gutterColor.getBackground()));
        editor.setInstructionColor(settings.setEditorInstructionColor(functionColor.getBackground()));
        editor.setTerminalInstructionColor(settings.setEditorTerminalInstructionColor(function2Color.getBackground()));
        editor.setDeclarationColor(settings.setEditorDeclarationColor(declarationColor.getBackground()));
        editor.setOperatorColor(settings.setEditorOperatorColor(operatorColor.getBackground()));
        editor.setDataTypeColor(settings.setEditorDataTypeColor(dataTypeColor.getBackground()));
        editor.setNumberColor(settings.setEditorNumberColor(numberColor.getBackground()));
        editor.setCommentColor(settings.setEditorCommentColor(commentColor.getBackground()));
        editor.setSeparatorColor(settings.setEditorSeparatorColor(bracketsColor.getBackground()));
        editor.setLabelColor(settings.setEditorLabelColor(labelColor.getBackground()));
        editor.setQuotedColor(settings.setEditorQuotedColor(quotedColor.getBackground()));
        editor.setErrorQuotedColor(settings.setEditorErrorQuotedColor(errorQuotedColor.getBackground()));
        editor.setCurrentLineHighlightColor(settings.setEditorCurrentLineHighlightColor(currentLineColor.getBackground()));
        editor.setSelectionColor(settings.setEditorSelectionColor(terminalSelectionColor.getBackground()));
        editor.setSelectedTextColor(settings.setEditorSelectedTextColor(terminalSelectedTextColor.getBackground()));
        terminal.setBackgroundColor(settings.setTerminalBackgroundColor(terminalBackgroundColor.getBackground()));
        terminal.setTextColor(settings.setTerminalTextColor(terminalTextColor.getBackground()));
        terminal.setSelectionColor(settings.setTerminalSelectionColor(terminalSelectionColor.getBackground()));
        terminal.setSelectedTextColor(settings.setTerminalSelectedTextColor(terminalSelectedTextColor.getBackground()));
    }

    private void applyOthers(Editor editor, Terminal terminal){
        editor.setIconRowHeaderEnabled(settings.setEditorIconRowHeader(showIconRowHeaderCheckBox.isSelected()));
        editor.setLineNumbersEnabled(settings.setEditorLineNumbers(showLineNumbersCheckBox.isSelected()));
        editor.setLineWrap(settings.setEditorLineWrap(wrapLinesCheckBox.isSelected()));
    }

    private JPanel setupColorChooser(JPanel panel){
        var listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) { }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                Color color = JColorChooser.showDialog(null, Main.I18N.getString("choose.color"), panel.getBackground());
                panel.setBackground(color);
            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }
            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        };
        panel.addMouseListener(listener);
        return panel;
    }

    public void applyFonts(Editor editor, Terminal terminal){
        editor.setFont(new Font(settings.setEditorFontName((String)editorFontCombo.getSelectedItem()), java.awt.Font.PLAIN, settings.setEditorFontSize(Integer.parseUnsignedInt(editorFontSize.getText()))));
        editor.setGutterFont(new Font(settings.setEditorFontName((String)editorFontCombo.getSelectedItem()), java.awt.Font.PLAIN, settings.setEditorFontSize(Integer.parseUnsignedInt(editorFontSize.getText()))));
        terminal.setFont(new Font(settings.setTerminalFontName((String)terminalFontCombo.getSelectedItem()), java.awt.Font.PLAIN, settings.setTerminalFontSize(Integer.parseUnsignedInt(terminalFontSize.getText()))));
    }

    public JPanel getMainPanel(){
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
     *  This class will change the bounds of the JComboBox popup menu to support
     *  different functionality. It will support the following features:
     *  -  a horizontal scrollbar can be displayed when necessary
     *  -  the popup can be wider than the combo box
     *  -  the popup can be displayed above the combo box
     *
     *  Class will only work for a JComboBox that uses a BasicComboPop.
     */
    private static class BoundsPopupMenuListener implements PopupMenuListener
    {
        private boolean scrollBarRequired = true;
        private boolean popupWider;
        private int maximumWidth = -1;
        private boolean popupAbove;
        private JScrollPane scrollPane;

        /**
         *  Convenience constructore to allow the display of a horizontal scrollbar
         *  when required.
         */
        public BoundsPopupMenuListener()
        {
            this(true, false, -1, false);
        }

        /**
         *  Convenience constructor that allows you to display the popup
         *  wider and/or above the combo box.
         *
         *  @param popupWider when true, popup width is based on the popup
         *                    preferred width
         *  @param popupAbove when true, popup is displayed above the combobox
         */
        public BoundsPopupMenuListener(boolean popupWider, boolean popupAbove)
        {
            this(true, popupWider, -1, popupAbove);
        }

        /**
         *  Convenience constructor that allows you to display the popup
         *  wider than the combo box and to specify the maximum width
         *
         *  @param maximumWidth the maximum width of the popup. The
         *                      popupAbove value is set to "true".
         */
        public BoundsPopupMenuListener(int maximumWidth)
        {
            this(true, true, maximumWidth, false);
        }

        /**
         *  General purpose constructor to set all popup properties at once.
         *
         *  @param scrollBarRequired display a horizontal scrollbar when the
         *         preferred width of popup is greater than width of scrollPane.
         *  @param popupWider display the popup at its preferred with
         *  @param maximumWidth limit the popup width to the value specified
         *         (minimum size will be the width of the combo box)
         *  @param popupAbove display the popup above the combo box
         *
         */
        public BoundsPopupMenuListener(
                boolean  scrollBarRequired, boolean popupWider, int maximumWidth, boolean popupAbove)
        {
            setScrollBarRequired( scrollBarRequired );
            setPopupWider( popupWider );
            setMaximumWidth( maximumWidth );
            setPopupAbove( popupAbove );
        }

        /**
         *  Return the maximum width of the popup.
         *
         *  @return the maximumWidth value
         */
        public int getMaximumWidth()
        {
            return maximumWidth;
        }

        /**
         *  Set the maximum width for the popup. This value is only used when
         *  setPopupWider( true ) has been specified. A value of -1 indicates
         *  that there is no maximum.
         *
         *  @param maximumWidth  the maximum width of the popup
         */
        public void setMaximumWidth(int maximumWidth)
        {
            this.maximumWidth = maximumWidth;
        }

        /**
         *  Determine if the popup should be displayed above the combo box.
         *
         *  @return the popupAbove value
         */
        public boolean isPopupAbove()
        {
            return popupAbove;
        }

        /**
         *  Change the location of the popup relative to the combo box.
         *
         *  @param popupAbove  true display popup above the combo box,
         *                     false display popup below the combo box.
         */
        public void setPopupAbove(boolean popupAbove)
        {
            this.popupAbove = popupAbove;
        }

        /**
         *  Determine if the popup might be displayed wider than the combo box
         *
         *  @return the popupWider value
         */
        public boolean isPopupWider()
        {
            return popupWider;
        }

        /**
         *  Change the width of the popup to be the greater of the width of the
         *  combo box or the preferred width of the popup. Normally the popup width
         *  is always the same size as the combo box width.
         *
         *  @param popupWider  true adjust the width as required.
         */
        public void setPopupWider(boolean popupWider)
        {
            this.popupWider = popupWider;
        }

        /**
         *  Determine if the horizontal scroll bar might be required for the popup
         *
         *  @return the scrollBarRequired value
         */
        public boolean isScrollBarRequired()
        {
            return scrollBarRequired;
        }

        /**
         *  For some reason the default implementation of the popup removes the
         *  horizontal scrollBar from the popup scroll pane which can result in
         *  the truncation of the rendered items in the popop. Adding a scrollBar
         *  back to the scrollPane will allow horizontal scrolling if necessary.
         *
         *  @param scrollBarRequired  true add horizontal scrollBar to scrollPane
         *                            false remove the horizontal scrollBar
         */
        public void setScrollBarRequired(boolean scrollBarRequired)
        {
            this.scrollBarRequired = scrollBarRequired;
        }

        /**
         *  Alter the bounds of the popup just before it is made visible.
         */
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e)
        {
            @SuppressWarnings("rawtypes") JComboBox comboBox = (JComboBox)e.getSource();

            if (comboBox.getItemCount() == 0) return;

            final Object child = comboBox.getAccessibleContext().getAccessibleChild(0);

            if (child instanceof BasicComboPopup)
                SwingUtilities.invokeLater(() -> customizePopup((BasicComboPopup) child));
        }

        protected void customizePopup(BasicComboPopup popup)
        {
            scrollPane = getScrollPane(popup);

            if (popupWider)
                popupWider( popup );

            checkHorizontalScrollBar( popup );

            //  For some reason in JDK7 the popup will not display at its preferred
            //  width unless its location has been changed from its default
            //  (ie. for normal "pop down" shift the popup and reset)

            Component comboBox = popup.getInvoker();
            Point location = comboBox.getLocationOnScreen();

            if (popupAbove)
            {
                int height = popup.getPreferredSize().height;
                popup.setLocation(location.x, location.y - height);
            }
            else
            {
                int height = comboBox.getPreferredSize().height;
                popup.setLocation(location.x, location.y + height - 1);
                popup.setLocation(location.x, location.y + height);
            }
        }

        /*
         *  Adjust the width of the scrollpane used by the popup
         */
        protected void popupWider(BasicComboPopup popup)
        {
            @SuppressWarnings("rawtypes") JList list = popup.getList();

            //  Determine the maximimum width to use:
            //  a) determine the popup preferred width
            //  b) limit width to the maximum if specified
            //  c) ensure width is not less than the scroll pane width

            int popupWidth = list.getPreferredSize().width
                    + 5  // make sure horizontal scrollbar doesn't appear
                    + getScrollBarWidth(popup, scrollPane);

            if (maximumWidth != -1)
            {
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
        private void checkHorizontalScrollBar(BasicComboPopup popup)
        {
            //  Reset the viewport to the left

            JViewport viewport = scrollPane.getViewport();
            Point p = viewport.getViewPosition();
            p.x = 0;
            viewport.setViewPosition( p );

            //  Remove the scrollbar so it is never painted

            if (! scrollBarRequired)
            {
                scrollPane.setHorizontalScrollBar( null );
                return;
            }

            //	Make sure a horizontal scrollbar exists in the scrollpane

            JScrollBar horizontal = scrollPane.getHorizontalScrollBar();

            if (horizontal == null)
            {
                horizontal = new JScrollBar(JScrollBar.HORIZONTAL);
                scrollPane.setHorizontalScrollBar( horizontal );
                scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
            }

            //	Potentially increase height of scroll pane to display the scrollbar

            if (horizontalScrollBarWillBeVisible(popup, scrollPane))
            {
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
        protected JScrollPane getScrollPane(BasicComboPopup popup)
        {
            @SuppressWarnings("rawtypes") JList list = popup.getList();
            Container c = SwingUtilities.getAncestorOfClass(JScrollPane.class, list);

            return (JScrollPane)c;
        }

        /*
         *  I can't find any property on the scrollBar to determine if it will be
         *  displayed or not so use brute force to determine this.
         */
        protected int getScrollBarWidth(BasicComboPopup popup, JScrollPane scrollPane)
        {
            int scrollBarWidth = 0;
            @SuppressWarnings("rawtypes") JComboBox comboBox = (JComboBox)popup.getInvoker();

            if (comboBox.getItemCount() > comboBox.getMaximumRowCount())
            {
                JScrollBar vertical = scrollPane.getVerticalScrollBar();
                scrollBarWidth = vertical.getPreferredSize().width;
            }

            return scrollBarWidth;
        }

        /*
         *  I can't find any property on the scrollBar to determine if it will be
         *  displayed or not so use brute force to determine this.
         */
        protected boolean horizontalScrollBarWillBeVisible(BasicComboPopup popup, JScrollPane scrollPane)
        {
            @SuppressWarnings("rawtypes") JList list = popup.getList();
            int scrollBarWidth = getScrollBarWidth(popup, scrollPane);
            int popupWidth = list.getPreferredSize().width + scrollBarWidth;

            return popupWidth > scrollPane.getPreferredSize().width;
        }

        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {}

        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
        {
            //  In its normal state the scrollpane does not have a scrollbar

            if (scrollPane != null)
            {
                scrollPane.setHorizontalScrollBar( null );
            }
        }
    }
}
