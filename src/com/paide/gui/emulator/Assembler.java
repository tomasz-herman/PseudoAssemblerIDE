package com.paide.gui.emulator;

import com.hermant.parser.ParseException;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.Serializer;
import com.paide.gui.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Assembler {

    @Nullable
    public static Program assemble(@NotNull Editor editor){
        if(!editor.isEditable()) return null;
        if(editor.getFile() == null) editor.save();
        long start = System.nanoTime();
        Program program = null;
        final var text = editor.getSelectedText() == null ? editor.getText() : editor.getSelectedText();
        ArrayList<String> lines = text.lines().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> lineNumbers = IntStream.rangeClosed(1, lines.size()).boxed().collect(Collectors.toCollection(ArrayList::new));
        int exceptions = 0;
        boolean exception;
        do {
            exception = false;
            try {
                program = Parser.parse(lines::stream, false);
            } catch (ParseException e) {
                exception = true;
                exceptions++;
                System.out.printf("Error at line %d: %s%n", lineNumbers.get(e.getLineNumber() - 1), e.getErrorMessage());
                lines.remove(e.getLineNumber() - 1);
                lineNumbers.remove(e.getLineNumber() - 1);
            }
        } while(exception);
        long time = (System.nanoTime() - start) / 1000000;
        if(exceptions != 0){
            System.out.printf("Build finished with %d error(s) in %d ms.%n", exceptions, time);
            return null;
        }
        if(editor.getFile() != null)
            try {
                Serializer.serializeProgram(program, editor.getFile().getPath() + ".out");
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.printf("Build finished successfully in %d ms.%n", time);
        return program;
    }

}
