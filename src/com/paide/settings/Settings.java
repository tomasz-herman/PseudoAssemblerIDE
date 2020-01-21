package com.paide.settings;

import java.awt.*;
import java.util.prefs.Preferences;

public class Settings {

    private Preferences preferences = Preferences.userRoot().node("com/paide");

    private int editorBackgroundColor;
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
    private int terminalBackgroundColor;
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
        editorBackgroundColor = preferences.getInt("editorBackground", 0);
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
        terminalBackgroundColor = preferences.getInt("terminalBackground", 0);
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
        preferences.putInt("editorBackground", editorBackgroundColor);
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
        preferences.putInt("terminalBackground", terminalBackgroundColor);
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

    public Color getEditorBackgroundColor() {
        return new Color(editorBackgroundColor);
    }

    public Color setEditorBackgroundColor(Color editorBackgroundColor) {
        this.editorBackgroundColor = editorBackgroundColor.getRGB();
        return editorBackgroundColor;
    }

    public Color getEditorTextColor() {
        return new Color(editorTextColor);
    }

    public Color setEditorTextColor(Color editorTextColor) {
        this.editorTextColor = editorTextColor.getRGB();
        return editorTextColor;
    }

    public Color getEditorGutterColor() {
        return new Color(editorGutterColor);
    }

    public Color setEditorGutterColor(Color editorGutterColor) {
        this.editorGutterColor = editorGutterColor.getRGB();
        return editorGutterColor;
    }

    public Color getEditorInstructionColor() {
        return new Color(editorInstructionColor);
    }

    public Color setEditorInstructionColor(Color editorInstructionColor) {
        this.editorInstructionColor = editorInstructionColor.getRGB();
        return editorInstructionColor;
    }

    public Color getEditorTerminalInstructionColor() {
        return new Color(editorTerminalInstructionColor);
    }

    public Color setEditorTerminalInstructionColor(Color editorTerminalInstructionColor) {
        this.editorTerminalInstructionColor = editorTerminalInstructionColor.getRGB();
        return editorTerminalInstructionColor;
    }

    public Color getEditorDeclarationColor() {
        return new Color(editorDeclarationColor);
    }

    public Color setEditorDeclarationColor(Color editorDeclarationColor) {
        this.editorDeclarationColor = editorDeclarationColor.getRGB();
        return editorDeclarationColor;
    }

    public Color getEditorOperatorColor() {
        return new Color(editorOperatorColor);
    }

    public Color setEditorOperatorColor(Color editorOperatorColor) {
        this.editorOperatorColor = editorOperatorColor.getRGB();
        return editorOperatorColor;
    }

    public Color getEditorDataTypeColor() {
        return new Color(editorDataTypeColor);
    }

    public Color setEditorDataTypeColor(Color editorDataTypeColor) {
        this.editorDataTypeColor = editorDataTypeColor.getRGB();
        return editorDataTypeColor;
    }

    public Color getEditorNumberColor() {
        return new Color(editorNumberColor);
    }

    public Color setEditorNumberColor(Color editorNumberColor) {
        this.editorNumberColor = editorNumberColor.getRGB();
        return editorNumberColor;
    }

    public Color getEditorCommentColor() {
        return new Color(editorCommentColor);
    }

    public Color setEditorCommentColor(Color editorCommentColor) {
        this.editorCommentColor = editorCommentColor.getRGB();
        return editorCommentColor;
    }

    public Color getEditorSeparatorColor() {
        return new Color(editorSeparatorColor);
    }

    public Color setEditorSeparatorColor(Color editorSeparatorColor) {
        this.editorSeparatorColor = editorSeparatorColor.getRGB();
        return editorSeparatorColor;
    }

    public Color getEditorLabelColor() {
        return new Color(editorLabelColor);
    }

    public Color setEditorLabelColor(Color editorLabelColor) {
        this.editorLabelColor = editorLabelColor.getRGB();
        return editorLabelColor;
    }

    public Color getEditorQuotedColor() {
        return new Color(editorQuotedColor);
    }

    public Color setEditorQuotedColor(Color editorQuotedColor) {
        this.editorQuotedColor = editorQuotedColor.getRGB();
        return editorQuotedColor;
    }

    public Color getEditorErrorQuotedColor() {
        return new Color(editorErrorQuotedColor);
    }

    public Color setEditorErrorQuotedColor(Color editorErrorQuotedColor) {
        this.editorErrorQuotedColor = editorErrorQuotedColor.getRGB();
        return editorErrorQuotedColor;
    }

    public Color getEditorCurrentLineHighlightColor() {
        return new Color(editorCurrentLineHighlightColor);
    }

    public Color setEditorCurrentLineHighlightColor(Color editorCurrentLineHighlightColor) {
        this.editorCurrentLineHighlightColor = editorCurrentLineHighlightColor.getRGB();
        return editorCurrentLineHighlightColor;
    }

    public Color getEditorSelectionColor() {
        return new Color(editorSelectionColor);
    }

    public Color setEditorSelectionColor(Color editorSelectionColor) {
        this.editorSelectionColor = editorSelectionColor.getRGB();
        return editorSelectionColor;
    }

    public Color getEditorSelectedTextColor() {
        return new Color(editorSelectedTextColor);
    }

    public Color setEditorSelectedTextColor(Color editorSelectedTextColor) {
        this.editorSelectedTextColor = editorSelectedTextColor.getRGB();
        return editorSelectedTextColor;
    }

    public Color getTerminalBackgroundColor() {
        return new Color(terminalBackgroundColor);
    }

    public Color setTerminalBackgroundColor(Color terminalBackgroundColor) {
        this.terminalBackgroundColor = terminalBackgroundColor.getRGB();
        return terminalBackgroundColor;
    }

    public Color getTerminalTextColor() {
        return new Color(terminalTextColor);
    }

    public Color setTerminalTextColor(Color terminalTextColor) {
        this.terminalTextColor = terminalTextColor.getRGB();
        return terminalTextColor;
    }

    public Color getTerminalSelectionColor() {
        return new Color(terminalSelectionColor);
    }

    public Color setTerminalSelectionColor(Color terminalSelectionColor) {
        this.terminalSelectionColor = terminalSelectionColor.getRGB();
        return terminalSelectionColor;
    }

    public Color getTerminalSelectedTextColor() {
        return new Color(terminalSelectedTextColor);
    }

    public Color setTerminalSelectedTextColor(Color terminalSelectedTextColor) {
        this.terminalSelectedTextColor = terminalSelectedTextColor.getRGB();
        return terminalSelectedTextColor;
    }

    public boolean isEditorIconRowHeader() {
        return editorIconRowHeader;
    }

    public boolean setEditorIconRowHeader(boolean editorIconRowHeader) {
        this.editorIconRowHeader = editorIconRowHeader;
        return editorIconRowHeader;
    }

    public boolean isEditorLineNumbers() {
        return editorLineNumbers;
    }

    public boolean setEditorLineNumbers(boolean editorLineNumbers) {
        this.editorLineNumbers = editorLineNumbers;
        return editorLineNumbers;
    }

    public boolean isEditorLineWrap() {
        return editorLineWrap;
    }

    public boolean setEditorLineWrap(boolean editorLineWrap) {
        this.editorLineWrap = editorLineWrap;
        return editorLineWrap;
    }

    public String getEditorFontName() {
        return editorFontName;
    }

    public String setEditorFontName(String editorFontName) {
        this.editorFontName = editorFontName;
        return editorFontName;
    }

    public int getEditorFontSize() {
        return editorFontSize;
    }

    public int setEditorFontSize(int editorFontSize) {
        this.editorFontSize = editorFontSize;
        return editorFontSize;
    }

    public String getTerminalFontName() {
        return terminalFontName;
    }

    public String setTerminalFontName(String terminalFontName) {
        this.terminalFontName = terminalFontName;
        return terminalFontName;
    }

    public int getTerminalFontSize() {
        return terminalFontSize;
    }

    public int setTerminalFontSize(int terminalFontSize) {
        this.terminalFontSize = terminalFontSize;
        return terminalFontSize;
    }
}
