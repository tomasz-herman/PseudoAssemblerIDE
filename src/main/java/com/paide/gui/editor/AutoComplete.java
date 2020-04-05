package com.paide.gui.editor;

import com.paide.Main;
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
        completion.setShowDescWindow(true);
        completion.setAutoCompleteSingleChoices(false);
        completion.setListCellRenderer(completionCellRenderer);
        completion.install(editor);
    }

    private static void addTokensCompletion(@NotNull AbstractCompletionProvider provider) {
        provider.addCompletion(new BasicCompletion(provider, "PUSHA", Main.I18N.getString("pusha.short"), Main.I18N.getString("pusha.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FPUSHA", Main.I18N.getString("fpusha.short"), Main.I18N.getString("fpusha.summary")));
        provider.addCompletion(new BasicCompletion(provider, "POPA", Main.I18N.getString("popa.short"), Main.I18N.getString("popa.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FPOPA", Main.I18N.getString("fpopa.short"), Main.I18N.getString("fpopa.summary")));
        provider.addCompletion(new BasicCompletion(provider, "ENTER", Main.I18N.getString("enter.short"), Main.I18N.getString("enter.summary")));
        provider.addCompletion(new BasicCompletion(provider, "LEAVE", Main.I18N.getString("leave.short"), Main.I18N.getString("leave.summary")));
        provider.addCompletion(new BasicCompletion(provider, "LD  \t", Main.I18N.getString("ld.short"), Main.I18N.getString("ld.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FLD \t", Main.I18N.getString("fld.short"), Main.I18N.getString("fld.summary")));
        provider.addCompletion(new BasicCompletion(provider, "ST  \t", Main.I18N.getString("st.short"), Main.I18N.getString("st.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FST \t", Main.I18N.getString("fst.short"), Main.I18N.getString("fst.summary")));
        provider.addCompletion(new BasicCompletion(provider, "LDA \t", Main.I18N.getString("lda.short"), Main.I18N.getString("lda.summary")));
        provider.addCompletion(new BasicCompletion(provider, "OUT \t", Main.I18N.getString("out.short"), Main.I18N.getString("out.summary")));
        provider.addCompletion(new BasicCompletion(provider, "BOUT\t", Main.I18N.getString("bout.short"), Main.I18N.getString("bout.summary")));
        provider.addCompletion(new BasicCompletion(provider, "COUT\t", Main.I18N.getString("cout.short"), Main.I18N.getString("cout.summary")));
        provider.addCompletion(new BasicCompletion(provider, "IOUT\t", Main.I18N.getString("iout.short"), Main.I18N.getString("iout.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FOUT\t", Main.I18N.getString("fout.short"), Main.I18N.getString("fout.summary")));
        provider.addCompletion(new BasicCompletion(provider, "NOP", Main.I18N.getString("nop.short"), Main.I18N.getString("nop.summary")));
        provider.addCompletion(new BasicCompletion(provider, "XCHG\t", Main.I18N.getString("xchg.short"), Main.I18N.getString("xchg.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FXCH\t", Main.I18N.getString("fxch.short"), Main.I18N.getString("fxch.summary")));
        provider.addCompletion(new BasicCompletion(provider, "LDB \t", Main.I18N.getString("ldb.short"), Main.I18N.getString("ldb.summary")));
        provider.addCompletion(new BasicCompletion(provider, "LDBU\t", Main.I18N.getString("ldbu.short"), Main.I18N.getString("ldbu.summary")));
        provider.addCompletion(new BasicCompletion(provider, "STB \t", Main.I18N.getString("stb.short"), Main.I18N.getString("stb.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FILD\t", Main.I18N.getString("fild.short"), Main.I18N.getString("fild.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FIST\t", Main.I18N.getString("fist.short"), Main.I18N.getString("fist.summary")));
        provider.addCompletion(new BasicCompletion(provider, "RAND\t", Main.I18N.getString("rand.short"), Main.I18N.getString("rand.summary")));
        provider.addCompletion(new BasicCompletion(provider, "HALT", Main.I18N.getString("halt.short"), Main.I18N.getString("halt.summary")));
        provider.addCompletion(new BasicCompletion(provider, "TIME\t", Main.I18N.getString("time.short"), Main.I18N.getString("time.summary")));
        provider.addCompletion(new BasicCompletion(provider, "IN  \t", Main.I18N.getString("in.short"), Main.I18N.getString("in.summary")));
        provider.addCompletion(new BasicCompletion(provider, "SLEEP   ", Main.I18N.getString("sleep.short"), Main.I18N.getString("sleep.summary")));
        provider.addCompletion(new BasicCompletion(provider, "PUSH\t", Main.I18N.getString("push.short"), Main.I18N.getString("push.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FPUSH   ", Main.I18N.getString("fpush.short"), Main.I18N.getString("fpush.summary")));
        provider.addCompletion(new BasicCompletion(provider, "POP \t", Main.I18N.getString("pop.short"), Main.I18N.getString("pop.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FPOP\t", Main.I18N.getString("fpop.short"), Main.I18N.getString("fpop.summary")));
        provider.addCompletion(new BasicCompletion(provider, "PUSHF   ", Main.I18N.getString("pushf.short"), Main.I18N.getString("pushf.summary")));
        provider.addCompletion(new BasicCompletion(provider, "POPF\t", Main.I18N.getString("popf.short"), Main.I18N.getString("popf.summary")));
        provider.addCompletion(new BasicCompletion(provider, "ADD \t", Main.I18N.getString("add.short"), Main.I18N.getString("add.summary")));
        provider.addCompletion(new BasicCompletion(provider, "SUB \t", Main.I18N.getString("sub.short"), Main.I18N.getString("sub.summary")));
        provider.addCompletion(new BasicCompletion(provider, "MUL \t", Main.I18N.getString("mul.short"), Main.I18N.getString("mul.summary")));
        provider.addCompletion(new BasicCompletion(provider, "DIV \t", Main.I18N.getString("div.short"), Main.I18N.getString("div.summary")));
        provider.addCompletion(new BasicCompletion(provider, "IDIV\t", Main.I18N.getString("idiv.short"), Main.I18N.getString("idiv.summary")));
        provider.addCompletion(new BasicCompletion(provider, "CMP \t", Main.I18N.getString("cmp.short"), Main.I18N.getString("cmp.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FADD\t", Main.I18N.getString("fadd.short"), Main.I18N.getString("fadd.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FSUB\t", Main.I18N.getString("fsub.short"), Main.I18N.getString("fsub.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FMUL\t", Main.I18N.getString("fmul.short"), Main.I18N.getString("fmul.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FDIV\t", Main.I18N.getString("fdiv.short"), Main.I18N.getString("fdiv.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FCMP\t", Main.I18N.getString("fcmp.short"), Main.I18N.getString("fcmp.summary")));
        provider.addCompletion(new BasicCompletion(provider, "NEG \t", Main.I18N.getString("neg.short"), Main.I18N.getString("neg.summary")));
        provider.addCompletion(new BasicCompletion(provider, "INC \t", Main.I18N.getString("inc.short"), Main.I18N.getString("inc.summary")));
        provider.addCompletion(new BasicCompletion(provider, "DEC \t", Main.I18N.getString("dec.short"), Main.I18N.getString("dec.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FSQRT   ", Main.I18N.getString("fsqrt.short"), Main.I18N.getString("fsqrt.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FABS\t", Main.I18N.getString("fabs.short"), Main.I18N.getString("fabs.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FSIN\t", Main.I18N.getString("fsin.short"), Main.I18N.getString("fsin.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FCOS\t", Main.I18N.getString("fcos.short"), Main.I18N.getString("fcos.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FTAN\t", Main.I18N.getString("ftan.short"), Main.I18N.getString("ftan.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FXAM\t", Main.I18N.getString("fxam.short"), Main.I18N.getString("fxam.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FTST\t", Main.I18N.getString("ftst.short"), Main.I18N.getString("ftst.summary")));
        provider.addCompletion(new BasicCompletion(provider, "AND \t", Main.I18N.getString("and.short"), Main.I18N.getString("and.summary")));
        provider.addCompletion(new BasicCompletion(provider, "OR  \t", Main.I18N.getString("or.short"), Main.I18N.getString("or.summary")));
        provider.addCompletion(new BasicCompletion(provider, "XOR \t", Main.I18N.getString("xor.short"), Main.I18N.getString("xor.summary")));
        provider.addCompletion(new BasicCompletion(provider, "TEST\t", Main.I18N.getString("test.short"), Main.I18N.getString("test.summary")));
        provider.addCompletion(new BasicCompletion(provider, "NOT \t", Main.I18N.getString("not.short"), Main.I18N.getString("not.summary")));
        provider.addCompletion(new BasicCompletion(provider, "SHR \t", Main.I18N.getString("shr.short"), Main.I18N.getString("shr.summary")));
        provider.addCompletion(new BasicCompletion(provider, "SHL \t", Main.I18N.getString("shl.short"), Main.I18N.getString("shl.summary")));
        provider.addCompletion(new BasicCompletion(provider, "SAR \t", Main.I18N.getString("sar.short"), Main.I18N.getString("sar.summary")));
        provider.addCompletion(new BasicCompletion(provider, "SAL \t", Main.I18N.getString("sal.short"), Main.I18N.getString("sal.summary")));
        provider.addCompletion(new BasicCompletion(provider, "ROR \t", Main.I18N.getString("ror.short"), Main.I18N.getString("ror.summary")));
        provider.addCompletion(new BasicCompletion(provider, "ROL \t", Main.I18N.getString("rol.short"), Main.I18N.getString("rol.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JMP \t", Main.I18N.getString("jmp.short"), Main.I18N.getString("jmp.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JE  \t", Main.I18N.getString("je.short"), Main.I18N.getString("je.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JZ  \t", Main.I18N.getString("jz.short"), Main.I18N.getString("jz.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNE \t", Main.I18N.getString("jne.short"), Main.I18N.getString("jne.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNZ \t", Main.I18N.getString("jnz.short"), Main.I18N.getString("jnz.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JG  \t", Main.I18N.getString("jg.short"), Main.I18N.getString("jg.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNLE\t", Main.I18N.getString("jnle.short"), Main.I18N.getString("jnle.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JGE \t", Main.I18N.getString("jge.short"), Main.I18N.getString("jge.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNL \t", Main.I18N.getString("jnl.short"), Main.I18N.getString("jnl.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JL  \t", Main.I18N.getString("jl.short"), Main.I18N.getString("jl.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNGE\t", Main.I18N.getString("jnge.short"), Main.I18N.getString("jnge.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JLE \t", Main.I18N.getString("jle.short"), Main.I18N.getString("jle.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNG \t", Main.I18N.getString("jng.short"), Main.I18N.getString("jng.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JA  \t", Main.I18N.getString("ja.short"), Main.I18N.getString("ja.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNBE\t", Main.I18N.getString("jnbe.short"), Main.I18N.getString("jnbe.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JAE \t", Main.I18N.getString("jae.short"), Main.I18N.getString("jae.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNB \t", Main.I18N.getString("jnb.short"), Main.I18N.getString("jnb.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNC \t", Main.I18N.getString("jnc.short"), Main.I18N.getString("jnc.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JB  \t", Main.I18N.getString("jb.short"), Main.I18N.getString("jb.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNAE\t", Main.I18N.getString("jnae.short"), Main.I18N.getString("jnae.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JC  \t", Main.I18N.getString("jc.short"), Main.I18N.getString("jc.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JBE \t", Main.I18N.getString("jbe.short"), Main.I18N.getString("jbe.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNA \t", Main.I18N.getString("jna.short"), Main.I18N.getString("jna.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JO  \t", Main.I18N.getString("jo.short"), Main.I18N.getString("jo.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNO \t", Main.I18N.getString("jno.short"), Main.I18N.getString("jno.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JS  \t", Main.I18N.getString("js.short"), Main.I18N.getString("js.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNS \t", Main.I18N.getString("jns.short"), Main.I18N.getString("jns.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JP  \t", Main.I18N.getString("jp.short"), Main.I18N.getString("jp.summary")));
        provider.addCompletion(new BasicCompletion(provider, "JNP \t", Main.I18N.getString("jnp.short"), Main.I18N.getString("jnp.summary")));
        provider.addCompletion(new BasicCompletion(provider, "LOOP\t", Main.I18N.getString("loop.short"), Main.I18N.getString("loop.summary")));
        provider.addCompletion(new BasicCompletion(provider, "CALL\t", Main.I18N.getString("call.short"), Main.I18N.getString("call.summary")));
        provider.addCompletion(new BasicCompletion(provider, "EXIT", Main.I18N.getString("exit.short"), Main.I18N.getString("exit.summary")));
        provider.addCompletion(new BasicCompletion(provider, "RET", Main.I18N.getString("ret.short"), Main.I18N.getString("ret.summary")));
        provider.addCompletion(new BasicCompletion(provider, "DC  \t", Main.I18N.getString("dc.short"), Main.I18N.getString("dc.summary")));
        provider.addCompletion(new BasicCompletion(provider, "DS  \t", Main.I18N.getString("ds.short"), Main.I18N.getString("ds.summary")));
        provider.addCompletion(new BasicCompletion(provider, "FLOAT", Main.I18N.getString("float.short"), Main.I18N.getString("float.summary")));
        provider.addCompletion(new BasicCompletion(provider, "INTEGER", Main.I18N.getString("integer.short"), Main.I18N.getString("integer.summary")));
        provider.addCompletion(new BasicCompletion(provider, "STRING", Main.I18N.getString("string.short"), Main.I18N.getString("string.summary")));
        provider.addCompletion(new BasicCompletion(provider, "CHAR", Main.I18N.getString("char.short"), Main.I18N.getString("char.summary")));
        provider.addCompletion(new BasicCompletion(provider, "BYTE", Main.I18N.getString("byte.short"), Main.I18N.getString("byte.summary")));
    }

    private static void addConstantDeclarationCompletions(@NotNull AbstractCompletionProvider provider) {
        provider.addCompletion(new ShorthandCompletion(provider, "dcs",
                "DC\t  STRING(\"\")", "DC\t  STRING(\"\")"));
        provider.addCompletion(new ShorthandCompletion(provider, "dci",
                "DC\t  INTEGER()", "DC\t  INTEGER()"));
        provider.addCompletion(new ShorthandCompletion(provider, "dcf",
                "DC\t  FLOAT()", "DC\t  FLOAT()"));
        provider.addCompletion(new ShorthandCompletion(provider, "dcb",
                "DC\t  BYTE()", "DC\t  BYTE()"));
        provider.addCompletion(new ShorthandCompletion(provider, "dcc",
                "DC\t  CHAR('')", "DC\t  CHAR('')"));
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
                "MAIN:\n\t\t\n\t\tEXIT\n", "MAIN:\n\t\t\n\t\tEXIT"));
        provider.addCompletion(new ShorthandCompletion(provider, "bfun",
                "FUN:\n\t\tENTER\n\t\t\n\t\tLEAVE\n\t\tRET\n", "function with AR setup"));
        provider.addCompletion(new ShorthandCompletion(provider, "fun",
                "FUN:\n\t\t\n\t\tRET\n", "function"));
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
        provider.addCompletion(new ShorthandCompletion(provider, "fzero", "F_ZERO:\t   DC      FLOAT(0)\n",
                "Float Zero Macro"));
        provider.addCompletion(new ShorthandCompletion(provider, "fone", "F_ONE:\t\tDC      FLOAT(1)\n",
                "Float One Macro"));
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
