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
        if (isLeaf()) return getValue();
        else if (height() == 1) return "(" + inOrder() + ")";
        else return "(" + getLeftET().infix() + " " + getValue() + " " + getRightET().infix() + ")";
    }
    
    /**
     * @return the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return preOrder();
    }
    
    private ExpressionTree getLeftET() { return (ExpressionTree) getLeft(); }
    
    private ExpressionTree getRightET() { return (ExpressionTree) getRight(); }
    
    /**
     * Evaluates the expression corresponding to the current tree.
     *
     * @return its value
     */
    public double evaluate() {
        return isLeaf() ? Double.parseDouble(getValue()) : switch (getValue()) {
            case "+" -> getLeftET().evaluate() + getRightET().evaluate();
            case "-" -> getLeftET().evaluate() - getRightET().evaluate();
            case "*" -> getLeftET().evaluate() * getRightET().evaluate();
            case "/" -> getLeftET().evaluate() / getRightET().evaluate();
            default -> throw new RuntimeException("cannot evaluate " + getValue());
        };
    }
}
