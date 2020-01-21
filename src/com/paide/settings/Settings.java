package com.paide.settings;

import java.util.prefs.Preferences;

public class Settings {

    private Preferences preferences = Preferences.userRoot().node("com/paide");

    private int editorBackground;
    private int editorTextColor;
    private int editorGutterColor;
    private int editorInstructionColor;
    private int editorTerminalInstructionColor;
    private int editorDeclarationColor;
    private int editorOperatorColor;
    private int editorDataTypeColor;
    private int editorNumberColor;
    private int editorCommentColor;
    private int editorSeparatorColor;
    private int editorLabelColor;
    private int editorQuotedColor;
    private int editorErrorQuotedColor;
    private int editorCurrentLineHighlightColor;
    private int editorSelectionColor;
    private int editorSelectedTextColor;
    private int terminalBackground;
    private int terminalTextColor;
    private int terminalSelectionColor;
    private int terminalSelectedTextColor;
    private boolean editorIconRowHeader;
    private boolean editorLineNumbers;
    private boolean editorLineWrap;
    private String editorFontName;
    private int editorFontSize;
    private String terminalFontName;
    private int terminalFontSize;

    public Settings(){
        load();
    }

    public void load(){
        editorBackground = preferences.getInt("editorBackground", 0);
        editorTextColor = preferences.getInt("editorTextColor", 0);
        editorGutterColor = preferences.getInt("editorGutterColor", 0);
        editorInstructionColor = preferences.getInt("editorInstructionColor", 0);
        editorTerminalInstructionColor = preferences.getInt("editorTerminalInstructionColor", 0);
        editorDeclarationColor = preferences.getInt("editorDeclarationColor", 0);
        editorOperatorColor = preferences.getInt("editorOperatorColor", 0);
        editorDataTypeColor = preferences.getInt("editorDataTypeColor", 0);
        editorNumberColor = preferences.getInt("editorNumberColor", 0);
        editorCommentColor = preferences.getInt("editorCommentColor", 0);
        editorSeparatorColor = preferences.getInt("editorSeparatorColor", 0);
        editorLabelColor = preferences.getInt("editorLabelColor", 0);
        editorQuotedColor = preferences.getInt("editorQuotedColor", 0);
        editorErrorQuotedColor = preferences.getInt("editorErrorQuotedColor", 0);
        editorCurrentLineHighlightColor = preferences.getInt("editorCurrentLineHighlightColor", 0);
        editorSelectionColor = preferences.getInt("editorSelectionColor", 0);
        editorSelectedTextColor = preferences.getInt("editorSelectedTextColor", 0);
        terminalBackground = preferences.getInt("terminalBackground", 0);
        terminalTextColor = preferences.getInt("terminalTextColor", 0);
        terminalSelectionColor = preferences.getInt("terminalSelectionColor", 0);
        terminalSelectedTextColor = preferences.getInt("terminalSelectedTextColor", 0);
        editorIconRowHeader = preferences.getBoolean("editorIconRowHeader", false);
        editorLineNumbers = preferences.getBoolean("editorLineNumbers", false);
        editorLineWrap = preferences.getBoolean("editorLineWrap", false);
        editorFontName = preferences.get("editorFontName", "Monospaced");
        editorFontSize = preferences.getInt("editorFontSize", 16);
        terminalFontName = preferences.get("terminalFontName", "Monospaced");
        terminalFontSize = preferences.getInt("terminalFontSize", 16);
    }

    public void save(){
        preferences.putInt("editorBackground", editorBackground);
        preferences.putInt("editorTextColor", editorTextColor);
        preferences.putInt("editorGutterColor", editorGutterColor);
        preferences.putInt("editorInstructionColor", editorInstructionColor);
        preferences.putInt("editorTerminalInstructionColor", editorTerminalInstructionColor);
        preferences.putInt("editorDeclarationColor", editorDeclarationColor);
        preferences.putInt("editorOperatorColor", editorOperatorColor);
        preferences.putInt("editorDataTypeColor", editorDataTypeColor);
        preferences.putInt("editorNumberColor", editorNumberColor);
        preferences.putInt("editorCommentColor", editorCommentColor);
        preferences.putInt("editorSeparatorColor", editorSeparatorColor);
        preferences.putInt("editorLabelColor", editorLabelColor);
        preferences.putInt("editorQuotedColor", editorQuotedColor);
        preferences.putInt("editorErrorQuotedColor", editorErrorQuotedColor);
        preferences.putInt("editorCurrentLineHighlightColor", editorCurrentLineHighlightColor);
        preferences.putInt("editorSelectionColor", editorSelectionColor);
        preferences.putInt("editorSelectedTextColor", editorSelectedTextColor);
        preferences.putInt("terminalBackground", terminalBackground);
        preferences.putInt("terminalTextColor", terminalTextColor);
        preferences.putInt("terminalSelectionColor", terminalSelectionColor);
        preferences.putInt("terminalSelectedTextColor", terminalSelectedTextColor);
        preferences.putBoolean("editorIconRowHeader", editorIconRowHeader);
        preferences.putBoolean("editorLineNumbers", editorLineNumbers);
        preferences.putBoolean("editorLineWrap", editorLineWrap);
        preferences.put("editorFontName", editorFontName);
        preferences.putInt("editorFontSize", editorFontSize);
        preferences.put("terminalFontName", terminalFontName);
        preferences.putInt("terminalFontSize", terminalFontSize);
    }

    public int getEditorBackground() {
        return editorBackground;
    }

    public void setEditorBackground(int editorBackground) {
        this.editorBackground = editorBackground;
    }

    public int getEditorTextColor() {
        return editorTextColor;
    }

    public void setEditorTextColor(int editorTextColor) {
        this.editorTextColor = editorTextColor;
    }

    public int getEditorGutterColor() {
        return editorGutterColor;
    }

    public void setEditorGutterColor(int editorGutterColor) {
        this.editorGutterColor = editorGutterColor;
    }

    public int getEditorInstructionColor() {
        return editorInstructionColor;
    }

    public void setEditorInstructionColor(int editorInstructionColor) {
        this.editorInstructionColor = editorInstructionColor;
    }

    public int getEditorTerminalInstructionColor() {
        return editorTerminalInstructionColor;
    }

    public void setEditorTerminalInstructionColor(int editorTerminalInstructionColor) {
        this.editorTerminalInstructionColor = editorTerminalInstructionColor;
    }

    public int getEditorDeclarationColor() {
        return editorDeclarationColor;
    }

    public void setEditorDeclarationColor(int editorDeclarationColor) {
        this.editorDeclarationColor = editorDeclarationColor;
    }

    public int getEditorOperatorColor() {
        return editorOperatorColor;
    }

    public void setEditorOperatorColor(int editorOperatorColor) {
        this.editorOperatorColor = editorOperatorColor;
    }

    public int getEditorDataTypeColor() {
        return editorDataTypeColor;
    }

    public void setEditorDataTypeColor(int editorDataTypeColor) {
        this.editorDataTypeColor = editorDataTypeColor;
    }

    public int getEditorNumberColor() {
        return editorNumberColor;
    }

    public void setEditorNumberColor(int editorNumberColor) {
        this.editorNumberColor = editorNumberColor;
    }

    public int getEditorCommentColor() {
        return editorCommentColor;
    }

    public void setEditorCommentColor(int editorCommentColor) {
        this.editorCommentColor = editorCommentColor;
    }

    public int getEditorSeparatorColor() {
        return editorSeparatorColor;
    }

    public void setEditorSeparatorColor(int editorSeparatorColor) {
        this.editorSeparatorColor = editorSeparatorColor;
    }

    public int getEditorLabelColor() {
        return editorLabelColor;
    }

    public void setEditorLabelColor(int editorLabelColor) {
        this.editorLabelColor = editorLabelColor;
    }

    public int getEditorQuotedColor() {
        return editorQuotedColor;
    }

    public void setEditorQuotedColor(int editorQuotedColor) {
        this.editorQuotedColor = editorQuotedColor;
    }

    public int getEditorErrorQuotedColor() {
        return editorErrorQuotedColor;
    }

    public void setEditorErrorQuotedColor(int editorErrorQuotedColor) {
        this.editorErrorQuotedColor = editorErrorQuotedColor;
    }

    public int getEditorCurrentLineHighlightColor() {
        return editorCurrentLineHighlightColor;
    }

    public void setEditorCurrentLineHighlightColor(int editorCurrentLineHighlightColor) {
        this.editorCurrentLineHighlightColor = editorCurrentLineHighlightColor;
    }

    public int getEditorSelectionColor() {
        return editorSelectionColor;
    }

    public void setEditorSelectionColor(int editorSelectionColor) {
        this.editorSelectionColor = editorSelectionColor;
    }

    public int getEditorSelectedTextColor() {
        return editorSelectedTextColor;
    }

    public void setEditorSelectedTextColor(int editorSelectedTextColor) {
        this.editorSelectedTextColor = editorSelectedTextColor;
    }

    public int getTerminalBackground() {
        return terminalBackground;
    }

    public void setTerminalBackground(int terminalBackground) {
        this.terminalBackground = terminalBackground;
    }

    public int getTerminalTextColor() {
        return terminalTextColor;
    }

    public void setTerminalTextColor(int terminalTextColor) {
        this.terminalTextColor = terminalTextColor;
    }

    public int getTerminalSelectionColor() {
        return terminalSelectionColor;
    }

    public void setTerminalSelectionColor(int terminalSelectionColor) {
        this.terminalSelectionColor = terminalSelectionColor;
    }

    public int getTerminalSelectedTextColor() {
        return terminalSelectedTextColor;
    }

    public void setTerminalSelectedTextColor(int terminalSelectedTextColor) {
        this.terminalSelectedTextColor = terminalSelectedTextColor;
    }

    public boolean isEditorIconRowHeader() {
        return editorIconRowHeader;
    }

    public void setEditorIconRowHeader(boolean editorIconRowHeader) {
        this.editorIconRowHeader = editorIconRowHeader;
    }

    public boolean isEditorLineNumbers() {
        return editorLineNumbers;
    }

    public void setEditorLineNumbers(boolean editorLineNumbers) {
        this.editorLineNumbers = editorLineNumbers;
    }

    public boolean isEditorLineWrap() {
        return editorLineWrap;
    }

    public void setEditorLineWrap(boolean editorLineWrap) {
        this.editorLineWrap = editorLineWrap;
    }

    public String getEditorFontName() {
        return editorFontName;
    }

    public void setEditorFontName(String editorFontName) {
        this.editorFontName = editorFontName;
    }

    public int getEditorFontSize() {
        return editorFontSize;
    }

    public void setEditorFontSize(int editorFontSize) {
        this.editorFontSize = editorFontSize;
    }

    public String getTerminalFontName() {
        return terminalFontName;
    }

    public void setTerminalFontName(String terminalFontName) {
        this.terminalFontName = terminalFontName;
    }

    public int getTerminalFontSize() {
        return terminalFontSize;
    }

    public void setTerminalFontSize(int terminalFontSize) {
        this.terminalFontSize = terminalFontSize;
    }
}
