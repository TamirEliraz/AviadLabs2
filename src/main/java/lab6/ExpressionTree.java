package lab6;

import java.io.IOException;
import java.io.StreamTokenizer;

import static java.io.StreamTokenizer.*;

public class ExpressionTree extends FullBinaryTree<String> {
    public ExpressionTree(String value) {
        this(value, null, null);
    }
    
    public ExpressionTree(String value, ExpressionTree right, ExpressionTree left) {
        super(value, right, left);
    }
    
    private static final char[] OPERATORS = new char[]{ '+', '-', '*', '/' };
    
    /**
     * Read the stream tokenizer until EOF and construct
     * the expression tree corresponding to it.
     * The input contains a prefix expression.
     */
    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        if (tokenizer.nextToken() == TT_EOF) {
            return null;
        } else if (tokenizer.ttype == TT_NUMBER) {
            return new ExpressionTree(String.valueOf((int) tokenizer.nval));
        } else if (tokenizer.ttype == TT_WORD && tokenizer.sval.equals("–")) {
            return new ExpressionTree("-",
                    createTree(tokenizer),
                    createTree(tokenizer));
        } else {
            for (char operator : OPERATORS) {
                if (tokenizer.ttype == operator) {
                    return new ExpressionTree(String.valueOf((char) operator),
                            createTree(tokenizer),
                            createTree(tokenizer));
                }
            }
        }
        throw new RuntimeException("???????");
    }
    
    /**
     * @return the infix expression corresponding to the current tree (*)
     */
    public String infix() {
        return "";
    }
    
    /**
     * @return the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return "";
    }
    
    /**
     * Evaluates the expression corresponding to the current tree.
     *
     * @return its value
     */
    public double evaluate() {
        return 0;
    }
}
