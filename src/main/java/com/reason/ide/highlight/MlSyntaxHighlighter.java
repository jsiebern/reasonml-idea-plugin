package com.reason.ide.highlight;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.reason.lang.LexerAdapter;
import com.reason.lang.MlTypes;
import com.reason.lang.ocaml.OclTypes;
import com.reason.lang.reason.RmlTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static com.intellij.psi.TokenType.BAD_CHARACTER;

public class MlSyntaxHighlighter extends SyntaxHighlighterBase {

    private static final Set<IElementType> RML_KEYWORD_TYPES = of(
            RmlTypes.INSTANCE.OPEN, RmlTypes.INSTANCE.MODULE, RmlTypes.INSTANCE.FUN, RmlTypes.INSTANCE.LET, RmlTypes.INSTANCE.TYPE,
            RmlTypes.INSTANCE.INCLUDE, RmlTypes.INSTANCE.EXTERNAL, RmlTypes.INSTANCE.IF, RmlTypes.INSTANCE.ELSE, RmlTypes.INSTANCE.SWITCH,
            RmlTypes.INSTANCE.TRY, RmlTypes.INSTANCE.RAISE, RmlTypes.INSTANCE.FOR, RmlTypes.INSTANCE.IN, RmlTypes.INSTANCE.TO, RmlTypes.INSTANCE.TRUE, RmlTypes.INSTANCE.FALSE,
            RmlTypes.INSTANCE.REF, RmlTypes.INSTANCE.EXCEPTION, RmlTypes.INSTANCE.WHEN, RmlTypes.INSTANCE.AND, RmlTypes.INSTANCE.REC, RmlTypes.INSTANCE.WHILE, RmlTypes.INSTANCE.ASR,
            RmlTypes.INSTANCE.CLASS, RmlTypes.INSTANCE.CONSTRAINT, RmlTypes.INSTANCE.DOWNTO, RmlTypes.INSTANCE.FUNCTOR, RmlTypes.INSTANCE.INHERIT,
            RmlTypes.INSTANCE.INITIALIZER, RmlTypes.INSTANCE.LAND, RmlTypes.INSTANCE.LOR, RmlTypes.INSTANCE.LSL,
            RmlTypes.INSTANCE.LSR, RmlTypes.INSTANCE.LXOR, RmlTypes.INSTANCE.METHOD, RmlTypes.INSTANCE.MOD, RmlTypes.INSTANCE.NEW,
            RmlTypes.INSTANCE.NONREC, RmlTypes.INSTANCE.OR, RmlTypes.INSTANCE.PRIVATE, RmlTypes.INSTANCE.VIRTUAL, RmlTypes.INSTANCE.VAL, RmlTypes.INSTANCE.PUB
    );

    private static final Set<IElementType> RML_OPERATION_SIGN_TYPES = of(
            RmlTypes.INSTANCE.ANDAND, RmlTypes.INSTANCE.SHORTCUT, RmlTypes.INSTANCE.ARROW, RmlTypes.INSTANCE.PIPE_FORWARD, RmlTypes.INSTANCE.PIPE_FIRST,
            RmlTypes.INSTANCE.EQEQEQ, RmlTypes.INSTANCE.EQEQ, RmlTypes.INSTANCE.EQ, RmlTypes.INSTANCE.NOT_EQEQ, RmlTypes.INSTANCE.NOT_EQ,
            RmlTypes.INSTANCE.DIFF, RmlTypes.INSTANCE.COLON, RmlTypes.INSTANCE.QUOTE, RmlTypes.INSTANCE.CARRET, RmlTypes.INSTANCE.PLUSDOT,
            RmlTypes.INSTANCE.MINUSDOT, RmlTypes.INSTANCE.SLASHDOT, RmlTypes.INSTANCE.STARDOT, RmlTypes.INSTANCE.PLUS, RmlTypes.INSTANCE.MINUS,
            RmlTypes.INSTANCE.SLASH, RmlTypes.INSTANCE.STAR, RmlTypes.INSTANCE.PERCENT, RmlTypes.INSTANCE.PIPE, RmlTypes.INSTANCE.ARROBASE,
            RmlTypes.INSTANCE.SHARP, RmlTypes.INSTANCE.SHARPSHARP, RmlTypes.INSTANCE.QUESTION_MARK, RmlTypes.INSTANCE.EXCLAMATION_MARK,

            RmlTypes.INSTANCE.AMPERSAND, RmlTypes.INSTANCE.LEFT_ARROW, RmlTypes.INSTANCE.RIGHT_ARROW, RmlTypes.INSTANCE.COLON_EQ,
            RmlTypes.INSTANCE.COLON_GT, RmlTypes.INSTANCE.GT, RmlTypes.INSTANCE.GT_BRACE, RmlTypes.INSTANCE.GT_BRACKET, RmlTypes.INSTANCE.BRACKET_GT,
            RmlTypes.INSTANCE.BRACKET_LT, RmlTypes.INSTANCE.BRACE_LT, RmlTypes.INSTANCE.DOTDOT
    );

    private static final Set<IElementType> RML_OPTIONS_TYPES = of(RmlTypes.INSTANCE.NONE, RmlTypes.INSTANCE.SOME, RmlTypes.INSTANCE.OPTION);

    private static final Set<IElementType> OCL_KEYWORD_TYPES = of(
            OclTypes.INSTANCE.OPEN, OclTypes.INSTANCE.MODULE, OclTypes.INSTANCE.FUN, OclTypes.INSTANCE.LET, OclTypes.INSTANCE.TYPE,
            OclTypes.INSTANCE.INCLUDE, OclTypes.INSTANCE.EXTERNAL, OclTypes.INSTANCE.IF, OclTypes.INSTANCE.ELSE, OclTypes.INSTANCE.SWITCH,
            OclTypes.INSTANCE.TRY, OclTypes.INSTANCE.RAISE, OclTypes.INSTANCE.FOR, OclTypes.INSTANCE.IN, OclTypes.INSTANCE.TO,
            OclTypes.INSTANCE.TRUE, OclTypes.INSTANCE.FALSE, OclTypes.INSTANCE.REF, OclTypes.INSTANCE.EXCEPTION, OclTypes.INSTANCE.WHEN,
            OclTypes.INSTANCE.AND, OclTypes.INSTANCE.REC, OclTypes.INSTANCE.WHILE, OclTypes.INSTANCE.ASR, OclTypes.INSTANCE.CLASS,
            OclTypes.INSTANCE.CONSTRAINT, OclTypes.INSTANCE.DOWNTO, OclTypes.INSTANCE.FUNCTOR, OclTypes.INSTANCE.INHERIT,
            OclTypes.INSTANCE.INITIALIZER, OclTypes.INSTANCE.LAND, OclTypes.INSTANCE.LOR, OclTypes.INSTANCE.LSL, OclTypes.INSTANCE.LSR,
            OclTypes.INSTANCE.LXOR, OclTypes.INSTANCE.METHOD, OclTypes.INSTANCE.MOD, OclTypes.INSTANCE.NEW, OclTypes.INSTANCE.NONREC,
            OclTypes.INSTANCE.OR, OclTypes.INSTANCE.PRIVATE, OclTypes.INSTANCE.VIRTUAL, OclTypes.INSTANCE.AS, OclTypes.INSTANCE.MUTABLE,
            OclTypes.INSTANCE.OF, OclTypes.INSTANCE.VAL,
            // Ocaml
            OclTypes.INSTANCE.MATCH, OclTypes.INSTANCE.WITH, OclTypes.INSTANCE.DO, OclTypes.INSTANCE.DONE, OclTypes.INSTANCE.OBJECT,
            OclTypes.INSTANCE.BEGIN, OclTypes.INSTANCE.END, OclTypes.INSTANCE.LAZY, OclTypes.INSTANCE.ASSERT, OclTypes.INSTANCE.THEN,
            OclTypes.INSTANCE.FUNCTION, OclTypes.INSTANCE.STRUCT, OclTypes.INSTANCE.SIG
    );

    private static final Set<IElementType> OCL_OPERATION_SIGN_TYPES = of(
            OclTypes.INSTANCE.ANDAND, OclTypes.INSTANCE.SHORTCUT, OclTypes.INSTANCE.ARROW, OclTypes.INSTANCE.PIPE_FORWARD, OclTypes.INSTANCE.PIPE_FIRST,
            OclTypes.INSTANCE.EQEQEQ, OclTypes.INSTANCE.EQEQ, OclTypes.INSTANCE.EQ, OclTypes.INSTANCE.NOT_EQEQ, OclTypes.INSTANCE.NOT_EQ,
            OclTypes.INSTANCE.DIFF, OclTypes.INSTANCE.COLON, OclTypes.INSTANCE.QUOTE, OclTypes.INSTANCE.CARRET, OclTypes.INSTANCE.PLUSDOT,
            OclTypes.INSTANCE.MINUSDOT, OclTypes.INSTANCE.SLASHDOT, OclTypes.INSTANCE.STARDOT, OclTypes.INSTANCE.PLUS, OclTypes.INSTANCE.MINUS,
            OclTypes.INSTANCE.SLASH, OclTypes.INSTANCE.STAR, OclTypes.INSTANCE.PERCENT, OclTypes.INSTANCE.PIPE, OclTypes.INSTANCE.ARROBASE,
            OclTypes.INSTANCE.SHARP, OclTypes.INSTANCE.SHARPSHARP, OclTypes.INSTANCE.QUESTION_MARK, OclTypes.INSTANCE.EXCLAMATION_MARK,

            OclTypes.INSTANCE.AMPERSAND, OclTypes.INSTANCE.LEFT_ARROW, OclTypes.INSTANCE.RIGHT_ARROW, OclTypes.INSTANCE.COLON_EQ,
            OclTypes.INSTANCE.COLON_GT, OclTypes.INSTANCE.GT, OclTypes.INSTANCE.GT_BRACE, OclTypes.INSTANCE.GT_BRACKET, OclTypes.INSTANCE.BRACKET_GT,
            OclTypes.INSTANCE.BRACKET_LT, OclTypes.INSTANCE.BRACE_LT, OclTypes.INSTANCE.DOTDOT
    );

    private static final Set<IElementType> OCL_OPTIONS_TYPES = of(OclTypes.INSTANCE.NONE, OclTypes.INSTANCE.SOME, OclTypes.INSTANCE.OPTION);

    private static final TextAttributesKey TYPE_ARGUMENT_KEY = TextAttributesKey.createTextAttributesKey("TYPE_ARGUMENT");
    private static final TextAttributesKey POLY_VARIANT_KEY = TextAttributesKey.createTextAttributesKey("POLY_VARIANT");

    public static final TextAttributesKey CODE_LENS_ = createTextAttributesKey("REASONML_CODE_LENS", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey RML_COMMENT_ = createTextAttributesKey("REASONML_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey MODULE_NAME_ = createTextAttributesKey("REASONML_MODULE_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey VARIANT_NAME_ = createTextAttributesKey("REASONML_VARIANT_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey STRING_ = createTextAttributesKey("REASONML_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER_ = createTextAttributesKey("REASONML_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey MARKUP_TAG_ = createTextAttributesKey("REASONML_MARKUP_TAG", DefaultLanguageHighlighterColors.MARKUP_TAG);
    public static final TextAttributesKey MARKUP_ATTRIBUTE_ = createTextAttributesKey("REASONML_MARKUP_ATTRIBUTE", DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
    public static final TextAttributesKey OPTION_ = createTextAttributesKey("REASONML_OPTION");
    public static final TextAttributesKey KEYWORD_ = createTextAttributesKey("REASONML_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEMICOLON_ = createTextAttributesKey("REASONML_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey BRACKETS_ = createTextAttributesKey("REASONML_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BRACES_ = createTextAttributesKey("REASONML_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey PARENS_ = createTextAttributesKey("REASONML_PARENS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey OPERATION_SIGN_ = createTextAttributesKey("REASONML_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey ANNOTATION_ = createTextAttributesKey("REASONML_ANNOTATION", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey TYPE_ARGUMENT_ = createTextAttributesKey("REASONML_TYPE_ARGUMENT", TYPE_ARGUMENT_KEY);
    public static final TextAttributesKey POLY_VARIANT_ = createTextAttributesKey("REASONML_POLY_VARIANT", POLY_VARIANT_KEY);
    private static final TextAttributesKey DOT_ = createTextAttributesKey("REASONML_OPERATION_SIGN", DefaultLanguageHighlighterColors.DOT);
    private static final TextAttributesKey COMMA_ = createTextAttributesKey("REASONML_OPERATION_SIGN", DefaultLanguageHighlighterColors.COMMA);
    private static final TextAttributesKey BAD_CHAR_ = createTextAttributesKey("REASONML_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER_};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{RML_COMMENT_};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING_};
    private static final TextAttributesKey[] TYPE_ARGUMENT_KEYS = new TextAttributesKey[]{TYPE_ARGUMENT_};
    private static final TextAttributesKey[] POLY_VARIANT_KEYS = new TextAttributesKey[]{POLY_VARIANT_};
    private static final TextAttributesKey[] BRACKET_KEYS = new TextAttributesKey[]{BRACKETS_};
    private static final TextAttributesKey[] BRACE_KEYS = new TextAttributesKey[]{BRACES_};
    private static final TextAttributesKey[] PAREN_KEYS = new TextAttributesKey[]{PARENS_};
    private static final TextAttributesKey[] OPTION_KEYS = new TextAttributesKey[]{OPTION_};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD_};
    private static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{SEMICOLON_};
    private static final TextAttributesKey[] DOT_KEYS = new TextAttributesKey[]{DOT_};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA_};
    private static final TextAttributesKey[] OPERATION_SIGN_KEYS = new TextAttributesKey[]{OPERATION_SIGN_};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHAR_};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    private MlTypes m_types;

    public MlSyntaxHighlighter(MlTypes types) {
        m_types = types;
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LexerAdapter(m_types);
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(m_types.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(m_types.LBRACE) || tokenType.equals(m_types.RBRACE)) {
            return BRACE_KEYS;
        } else if (tokenType.equals(m_types.LBRACKET) || tokenType.equals(m_types.RBRACKET) || tokenType.equals(m_types.LARRAY) || tokenType.equals(m_types.RARRAY) || tokenType.equals(m_types.ML_STRING_OPEN) || tokenType.equals(m_types.ML_STRING_CLOSE) || tokenType.equals(m_types.JS_STRING_OPEN) || tokenType.equals(m_types.JS_STRING_CLOSE)) {
            return BRACKET_KEYS;
        } else if (tokenType.equals(m_types.LPAREN) || tokenType.equals(m_types.RPAREN)) {
            return PAREN_KEYS;
        } else if (tokenType.equals(m_types.INT) || tokenType.equals(m_types.FLOAT)) {
            return NUMBER_KEYS;
        } else if (m_types.DOT.equals(tokenType)) {
            return DOT_KEYS;
        } else if (m_types.TYPE_ARGUMENT.equals(tokenType)) {
            return TYPE_ARGUMENT_KEYS;
        } else if (m_types.POLY_VARIANT.equals(tokenType)) {
            return POLY_VARIANT_KEYS;
        } else if (m_types.COMMA.equals(tokenType)) {
            return COMMA_KEYS;
        } else if (m_types.SEMI.equals(tokenType) || m_types.SEMISEMI.equals(tokenType)) {
            return SEMICOLON_KEYS;
        } else if (m_types.STRING.equals(tokenType) || m_types.CHAR.equals(tokenType)) {
            return STRING_KEYS;
        } else if (m_types == RmlTypes.INSTANCE) {
            if (RML_KEYWORD_TYPES.contains(tokenType)) {
                return KEYWORD_KEYS;
            } else if (RML_OPERATION_SIGN_TYPES.contains(tokenType)) {
                return OPERATION_SIGN_KEYS;
            } else if (RML_OPTIONS_TYPES.contains(tokenType)) {
                return OPTION_KEYS;
            }
        } else if (m_types == OclTypes.INSTANCE) {
            if (OCL_KEYWORD_TYPES.contains(tokenType)) {
                return KEYWORD_KEYS;
            } else if (OCL_OPERATION_SIGN_TYPES.contains(tokenType)) {
                return OPERATION_SIGN_KEYS;
            } else if (OCL_OPTIONS_TYPES.contains(tokenType)) {
                return OPTION_KEYS;
            }
        } else if (BAD_CHARACTER.equals(tokenType)) {
            return BAD_CHAR_KEYS;
        }

        return EMPTY_KEYS;
    }

    private static Set<IElementType> of(IElementType... types) {
        Set<IElementType> result = new HashSet<>();
        Collections.addAll(result, types);
        return result;
    }
}
