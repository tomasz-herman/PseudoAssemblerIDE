package com.paide.gui;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {

    public static final String SERIF_FILENAME = "NotoSerif-Regular.ttf";
    public static final String SANS_FILENAME = "NotoSans-Regular.ttf";
    public static final String MONO_FILENAME = "NotoMono-Regular.ttf";

    private static Font SERIF = null;
    private static Font SANS = null;
    private static Font MONO = null;

    static {
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(SERIF_FILENAME)) {
            if(stream == null) throw new IOException("Couldn't open stream");
            SERIF = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(SANS_FILENAME)) {
            if(stream == null) throw new IOException("Couldn't open stream");
            SANS = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        try (InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(MONO_FILENAME)) {
            if(stream == null) throw new IOException("Couldn't open stream");
            MONO = Font.createFont(Font.TRUETYPE_FONT, stream);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font getSansFont(float size){
        return SANS.deriveFont(size);
    }

    public static Font getSerifFont(float size){
        return SERIF.deriveFont(size);
    }

    public static Font getMonoFont(float size){
        return MONO.deriveFont(size);
    }
}
