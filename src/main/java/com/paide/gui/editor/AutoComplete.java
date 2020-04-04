package com.paide.gui.editor;

import com.paide.gui.layout.MainLayout;
import org.fife.ui.autocomplete.*;
import org.jetbrains.annotations.NotNull;

public class AutoComplete {

    public static void install(Editor editor) {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        addTokensCompletion(provider);
        addConstantDeclarationCompletions(provider);
        addSpaceDeclarationCompletions(provider);
        addCodeCompletions(provider);

        AutoCompletion completion = new AutoCompletion(provider);
        CompletionCellRenderer completionCellRenderer = new CompletionCellRenderer();
        completion.setAutoActivationEnabled(true);
        completionCellRenderer.setDisplayFont(MainLayout.DEFAULT_FONT);
        completion.setListCellRenderer(completionCellRenderer);
        completion.install(editor);
    }

    private static void addTokensCompletion(@NotNull AbstractCompletionProvider provider){
        provider.addCompletion(new BasicCompletion(provider, "FLOAT"));
        provider.addCompletion(new BasicCompletion(provider, "INTEGER"));
        provider.addCompletion(new BasicCompletion(provider, "STRING"));
        provider.addCompletion(new BasicCompletion(provider, "CHAR"));
    }

    private static void addConstantDeclarationCompletions(@NotNull AbstractCompletionProvider provider){
        provider.addCompletion(new ShorthandCompletion(provider, "dcs",
                "DC\t  STRING(\"\")", "DC\t  STRING(\"\")"));
        provider.addCompletion(new ShorthandCompletion(provider, "dci",
                "DC\t  INTEGER()", "DC\t  INTEGER()"));
        provider.addCompletion(new ShorthandCompletion(provider, "dcf",
                "DC\t  FLOAT()", "DC\t  FLOAT()\""));
        provider.addCompletion(new ShorthandCompletion(provider, "dcb",
                "DC\t  BYTE()", "DC\t  BYTE()\""));
        provider.addCompletion(new ShorthandCompletion(provider, "dcc",
                "DC\t  CHAR('')", "DC\t  CHAR('')\""));
    }

    private static void addSpaceDeclarationCompletions(@NotNull AbstractCompletionProvider provider){
        provider.addCompletion(new ShorthandCompletion(provider, "dsi",
                "DS\t  INTEGER", "DS\t  INTEGER"));
        provider.addCompletion(new ShorthandCompletion(provider, "dsf",
                "DS\t  FLOAT", "DS\t  FLOAT"));
        provider.addCompletion(new ShorthandCompletion(provider, "dsb",
                "DS\t  BYTE", "DS\t  BYTE"));
        provider.addCompletion(new ShorthandCompletion(provider, "dsc",
                "DS\t  CHAR", "DS\t  CHAR"));
    }

    private static void addCodeCompletions(AbstractCompletionProvider provider){
        provider.addCompletion(new ShorthandCompletion(provider, "main",
                "MAIN:\n\t\t\n\t\tEXIT", "MAIN:\n\t\t\n\t\tEXIT"));
        provider.addCompletion(new ShorthandCompletion(provider, "fun",
                "FUN:\n\t\tENTER\n\t\t\n\t\tLEAVE\n\t\tRET", "FUN:\n\t\tENTER\n\t\t\n\t\tLEAVE\n\t\tRET"));
    }
}
