package com.paide.gui.editor;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;

public class AutoComplete {

    public static void install(Editor editor) {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        provider.addCompletion(new BasicCompletion(provider, "FLOAT"));
        provider.addCompletion(new BasicCompletion(provider, "INTEGER"));
        provider.addCompletion(new BasicCompletion(provider, "STRING"));
        provider.addCompletion(new BasicCompletion(provider, "CHAR"));

        provider.addCompletion(new ShorthandCompletion(provider, "dci",
                "DC\t  INTEGER()", "DC\t  INTEGER()\""));
        provider.addCompletion(new ShorthandCompletion(provider, "dsi",
                "DS\t  INTEGER", "DS\t  INTEGER"));
        provider.addCompletion(new ShorthandCompletion(provider, "dcf",
                "DC\t  FLOAT()", "DC\t  FLOAT()\""));
        provider.addCompletion(new ShorthandCompletion(provider, "dsf",
                "DS\t  FLOAT", "DS\t  FLOAT"));

        AutoCompletion completion = new AutoCompletion(provider);
        completion.install(editor);
    }
}
