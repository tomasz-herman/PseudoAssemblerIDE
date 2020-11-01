package com.paide.emulator;

import com.hermant.parser.ParseException;
import com.hermant.parser.Parser;
import com.hermant.program.Program;
import com.hermant.serializer.Serializer;
import com.paide.gui.editor.Editor;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.parser.AbstractParser;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParseResult;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParserNotice;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Assembler extends AbstractParser {

    private static final String ERROR_AT_LINE = "Error at line %d: %s%n";
    private static final String BUILD_FINISHED_WITH_ERRORS = "Build finished with %d error(s) in %d ms.%n";
    private static final String BUILD_FINISHED_SUCCESSFULLY = "Build finished successfully in %d ms.%n";

    private final DefaultParseResult result = new DefaultParseResult(this);

    @Nullable
    public static Program assemble(@NotNull Editor editor){
        if(!editor.isEditable()) return null;
        if(editor.getFile() == null) editor.save();
        long start = System.nanoTime();
        editor.clearErrors();
        Program program = null;
        final var text = editor.getText();
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
                System.out.printf(ERROR_AT_LINE, lineNumbers.get(e.getLineNumber() - 1), e.getErrorMessage());
                editor.markError(lineNumbers.get(e.getLineNumber() - 1), e.getErrorMessage());
                lines.remove(e.getLineNumber() - 1);
                lineNumbers.remove(e.getLineNumber() - 1);
            }
        } while(exception);
        long time = (System.nanoTime() - start) / 1000000;
        if(exceptions != 0){
            System.out.printf(BUILD_FINISHED_WITH_ERRORS, exceptions, time);
            return null;
        }
        if(editor.getFile() != null)
            try {
                Serializer.serializeProgram(program, editor.getFile().getPath() + ".out");
            } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.printf(BUILD_FINISHED_SUCCESSFULLY, time);
        return program;
    }

    @Override
    public ParseResult parse(RSyntaxDocument doc, String style) {
        result.clearNotices();
        String text = "";
        try {
            text = doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = text.lines().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> lineNumbers = IntStream.rangeClosed(1, lines.size()).boxed().collect(Collectors.toCollection(ArrayList::new));
        boolean exception;
        do {
            exception = false;
            try {
                Parser.parse(lines::stream, false);
            } catch (ParseException e) {
                exception = true;
                result.addNotice(new DefaultParserNotice(this, e.getErrorMessage(), lineNumbers.get(e.getLineNumber() - 1) - 1));
                lines.remove(e.getLineNumber() - 1);
                lineNumbers.remove(e.getLineNumber() - 1);
            }
        } while(exception);
        return result;
    }
}
