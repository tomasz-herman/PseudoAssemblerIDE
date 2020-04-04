package com.paide.gui.editor;

import com.paide.gui.layout.MainLayout;
import org.fife.ui.autocomplete.*;
import org.jetbrains.annotations.NotNull;

import java.util.Base64;

public class AutoComplete {

    public static void install(Editor editor) {
        DefaultCompletionProvider provider = new DefaultCompletionProvider();

        addTokensCompletion(provider);
        addConstantDeclarationCompletions(provider);
        addSpaceDeclarationCompletions(provider);
        addCodeCompletions(provider);
        addHandyCompletions(provider);

        AutoCompletion completion = new AutoCompletion(provider);
        CompletionCellRenderer completionCellRenderer = new CompletionCellRenderer();
        completion.setAutoActivationEnabled(true);
        completion.setAutoCompleteEnabled(true);
        completionCellRenderer.setDisplayFont(MainLayout.DEFAULT_FONT);
        completion.setListCellRenderer(completionCellRenderer);
        completion.install(editor);
    }

    private static void addTokensCompletion(@NotNull AbstractCompletionProvider provider) {
        provider.addCompletion(new BasicCompletion(provider, "PUSHA"));
        provider.addCompletion(new BasicCompletion(provider, "FPUSHA"));
        provider.addCompletion(new BasicCompletion(provider, "POPA"));
        provider.addCompletion(new BasicCompletion(provider, "FPOPA"));
        provider.addCompletion(new BasicCompletion(provider, "ENTER"));
        provider.addCompletion(new BasicCompletion(provider, "LEAVE"));
        provider.addCompletion(new BasicCompletion(provider, "LD  \t"));
        provider.addCompletion(new BasicCompletion(provider, "FLD \t"));
        provider.addCompletion(new BasicCompletion(provider, "ST  \t"));
        provider.addCompletion(new BasicCompletion(provider, "FST \t"));
        provider.addCompletion(new BasicCompletion(provider, "LDA \t"));
        provider.addCompletion(new BasicCompletion(provider, "OUT \t"));
        provider.addCompletion(new BasicCompletion(provider, "BOUT\t"));
        provider.addCompletion(new BasicCompletion(provider, "COUT\t"));
        provider.addCompletion(new BasicCompletion(provider, "IOUT\t"));
        provider.addCompletion(new BasicCompletion(provider, "FOUT\t"));
        provider.addCompletion(new BasicCompletion(provider, "NOP"));
        provider.addCompletion(new BasicCompletion(provider, "XCHG\t"));
        provider.addCompletion(new BasicCompletion(provider, "FXCH\t"));
        provider.addCompletion(new BasicCompletion(provider, "LDB \t"));
        provider.addCompletion(new BasicCompletion(provider, "LDBU\t"));
        provider.addCompletion(new BasicCompletion(provider, "STB \t"));
        provider.addCompletion(new BasicCompletion(provider, "FILD\t"));
        provider.addCompletion(new BasicCompletion(provider, "FIST\t"));
        provider.addCompletion(new BasicCompletion(provider, "RAND\t"));
        provider.addCompletion(new BasicCompletion(provider, "HALT"));
        provider.addCompletion(new BasicCompletion(provider, "TIME\t"));
        provider.addCompletion(new BasicCompletion(provider, "IN  \t"));
        provider.addCompletion(new BasicCompletion(provider, "SLEEP   "));
        provider.addCompletion(new BasicCompletion(provider, "PUSH\t"));
        provider.addCompletion(new BasicCompletion(provider, "FPUSH   "));
        provider.addCompletion(new BasicCompletion(provider, "POP \t"));
        provider.addCompletion(new BasicCompletion(provider, "FPOP\t"));
        provider.addCompletion(new BasicCompletion(provider, "PUSHF   "));
        provider.addCompletion(new BasicCompletion(provider, "POPF\t"));
        provider.addCompletion(new BasicCompletion(provider, "ADD \t"));
        provider.addCompletion(new BasicCompletion(provider, "SUB \t"));
        provider.addCompletion(new BasicCompletion(provider, "MUL \t"));
        provider.addCompletion(new BasicCompletion(provider, "DIV \t"));
        provider.addCompletion(new BasicCompletion(provider, "IDIV\t"));
        provider.addCompletion(new BasicCompletion(provider, "CMP \t"));
        provider.addCompletion(new BasicCompletion(provider, "FADD\t"));
        provider.addCompletion(new BasicCompletion(provider, "FSUB\t"));
        provider.addCompletion(new BasicCompletion(provider, "FMUL\t"));
        provider.addCompletion(new BasicCompletion(provider, "FDIV\t"));
        provider.addCompletion(new BasicCompletion(provider, "FCMP\t"));
        provider.addCompletion(new BasicCompletion(provider, "NEG \t"));
        provider.addCompletion(new BasicCompletion(provider, "INC \t"));
        provider.addCompletion(new BasicCompletion(provider, "DEC \t"));
        provider.addCompletion(new BasicCompletion(provider, "FSQRT   "));
        provider.addCompletion(new BasicCompletion(provider, "FABS\t"));
        provider.addCompletion(new BasicCompletion(provider, "FSIN\t"));
        provider.addCompletion(new BasicCompletion(provider, "FCOS\t"));
        provider.addCompletion(new BasicCompletion(provider, "FTAN\t"));
        provider.addCompletion(new BasicCompletion(provider, "FXAM\t"));
        provider.addCompletion(new BasicCompletion(provider, "FTST\t"));
        provider.addCompletion(new BasicCompletion(provider, "AND \t"));
        provider.addCompletion(new BasicCompletion(provider, "OR  \t"));
        provider.addCompletion(new BasicCompletion(provider, "XOR \t"));
        provider.addCompletion(new BasicCompletion(provider, "TEST\t"));
        provider.addCompletion(new BasicCompletion(provider, "NOT \t"));
        provider.addCompletion(new BasicCompletion(provider, "SHR \t"));
        provider.addCompletion(new BasicCompletion(provider, "SHL \t"));
        provider.addCompletion(new BasicCompletion(provider, "SAR \t"));
        provider.addCompletion(new BasicCompletion(provider, "SAL \t"));
        provider.addCompletion(new BasicCompletion(provider, "ROR \t"));
        provider.addCompletion(new BasicCompletion(provider, "ROL \t"));
        provider.addCompletion(new BasicCompletion(provider, "JMP \t"));
        provider.addCompletion(new BasicCompletion(provider, "JE  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JZ  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNE \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNZ \t"));
        provider.addCompletion(new BasicCompletion(provider, "JG  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNLE\t"));
        provider.addCompletion(new BasicCompletion(provider, "JGE \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNL \t"));
        provider.addCompletion(new BasicCompletion(provider, "JL  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNGE\t"));
        provider.addCompletion(new BasicCompletion(provider, "JLE \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNG \t"));
        provider.addCompletion(new BasicCompletion(provider, "JA  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNBE\t"));
        provider.addCompletion(new BasicCompletion(provider, "JAE \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNB \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNC \t"));
        provider.addCompletion(new BasicCompletion(provider, "JB  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNAE\t"));
        provider.addCompletion(new BasicCompletion(provider, "JC  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JBE \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNA \t"));
        provider.addCompletion(new BasicCompletion(provider, "JO  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNO \t"));
        provider.addCompletion(new BasicCompletion(provider, "JS  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNS \t"));
        provider.addCompletion(new BasicCompletion(provider, "JP  \t"));
        provider.addCompletion(new BasicCompletion(provider, "JNP \t"));
        provider.addCompletion(new BasicCompletion(provider, "LOOP\t"));
        provider.addCompletion(new BasicCompletion(provider, "CALL\t"));
        provider.addCompletion(new BasicCompletion(provider, "FLOAT"));
        provider.addCompletion(new BasicCompletion(provider, "INTEGER"));
        provider.addCompletion(new BasicCompletion(provider, "STRING"));
        provider.addCompletion(new BasicCompletion(provider, "CHAR"));
        provider.addCompletion(new BasicCompletion(provider, "EXIT"));
        provider.addCompletion(new BasicCompletion(provider, "RET"));
        provider.addCompletion(new BasicCompletion(provider, "DC  \t", "Declare constant", "Declares constant of a given type."));
        provider.addCompletion(new BasicCompletion(provider, "DS  \t", "Declare space"));
    }

    private static void addConstantDeclarationCompletions(@NotNull AbstractCompletionProvider provider) {
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

    private static void addSpaceDeclarationCompletions(@NotNull AbstractCompletionProvider provider) {
        provider.addCompletion(new ShorthandCompletion(provider, "dsi",
                "DS\t  INTEGER", "DS\t  INTEGER"));
        provider.addCompletion(new ShorthandCompletion(provider, "dsf",
                "DS\t  FLOAT", "DS\t  FLOAT"));
        provider.addCompletion(new ShorthandCompletion(provider, "dsb",
                "DS\t  BYTE", "DS\t  BYTE"));
        provider.addCompletion(new ShorthandCompletion(provider, "dsc",
                "DS\t  CHAR", "DS\t  CHAR"));
    }

    private static void addCodeCompletions(AbstractCompletionProvider provider) {
        provider.addCompletion(new ShorthandCompletion(provider, "main",
                "MAIN:\n\t\t\n\t\tEXIT", "MAIN:\n\t\t\n\t\tEXIT"));
        provider.addCompletion(new ShorthandCompletion(provider, "bfun",
                "FUN:\n\t\tENTER\n\t\t\n\t\tLEAVE\n\t\tRET", "function with AR setup"));
        provider.addCompletion(new ShorthandCompletion(provider, "fun",
                "FUN:\n\t\t\n\t\tRET", "function"));
    }

    private static void addHandyCompletions(AbstractCompletionProvider provider) {
        provider.addCompletion(new ShorthandCompletion(provider, "pi", "PI:\t\t DC      FLOAT(3.14159274)\n",
                "PI Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "e", "E:\t\t  DC      FLOAT(2.71828182)\n",
                "E Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "zero", "ZERO:\t   DC      INTEGER(0)\n",
                "Zero Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "one", "ONE:\t\tDC      INTEGER(1)\n",
                "One Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "four", "FOUR:\t   DC      INTEGER(4)\n",
                "Four Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "sqrt2", "SQRT2:\t  DC      FLOAT(1.41421356)\n",
                "sqrt(2) Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "sqrt3", "SQRT3:\t  DC      FLOAT(1.73205081)\n",
                "sqrt(3) Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "sqrt5", "SQRT5:\t  DC      FLOAT(2.23606798)\n",
                "sqrt(5) Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "ln2", "LN2:\t\tDC      FLOAT(0.69314718)\n",
                "ln(2) Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "ln10", "LN10:\t   DC      FLOAT(2.30258509)\n",
                "ln(2) Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "halfpi", "HALF_PI:\tDC      FLOAT(1.57079632)\n",
                "half pi Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "twopi", "TWO_PI:\t DC      FLOAT(6.28318531)\n",
                "two pi Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "fourpi", "FOUR_PI:\tDC      FLOAT(12.5663706)\n",
                "four pi Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "degtorad", "DEG_TO_RAD: DC      FLOAT(0.01745329252)\n",
                "deg to rad Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "radtodeg", "RAD_TO_DEG: DC      FLOAT(57.29577951)\n",
                "rad to deg Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, new String(Base64.getDecoder().decode("Y2hhYmFz")),
                new String(Base64.getDecoder().decode("Q0hBQkFTOiAgICAgICAgIERDICAgICAgU1RSSU5HKCJDSEFCQVMgU01JRUMiKQo="))));
    }
}
