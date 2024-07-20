package lab5; // package il.ac.telhai.ds.stack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

import static java.io.StreamTokenizer.*;

public class EvaluatePostfix {
    
    private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
    private static Stack<Double> myStack = new DLinkedListStack<Double>();
    
    private static void error() {
        System.err.println(tokenizer);
        System.err.println(myStack);
        System.exit(1);
    }
    
    public static void main(String[] args) throws IOException {
        tokenizer.slashSlashComments(false);
        tokenizer.ordinaryChar('/');
        while (tokenizer.nextToken() != TT_EOF) {
            if (tokenizer.ttype == TT_WORD) {
                if (tokenizer.sval.equals("quit") || tokenizer.sval.equals("EOF"))
                    break;
                else error();
            } else if (tokenizer.ttype == TT_NUMBER) {
                myStack.push(tokenizer.nval);
            } else if (tokenizer.ttype == '+') {
                Double d1, d2;
                d1 = myStack.pop();
                d2 = myStack.pop();
                if (d1 == null || d2 == null) error();
                myStack.push(d1 + d2);
            } else if (tokenizer.ttype == '-') {
                Double d1, d2;
                d2 = myStack.pop();
                d1 = myStack.pop();
                if (d1 == null || d2 == null) error();
                myStack.push(d1 - d2);
            } else if (tokenizer.ttype == '*') {
                Double d1, d2;
                d1 = myStack.pop();
                d2 = myStack.pop();
                if (d1 == null || d2 == null) error();
                myStack.push(d1 * d2);
            } else if (tokenizer.ttype == '/') {
                Double d1, d2;
                d2 = myStack.pop();
                d1 = myStack.pop();
                if (d1 == null || d2 == null) error();
                myStack.push(d1 / d2);
            }
        } // END WHITE
        if (myStack.isEmpty()) error();
        Double val = myStack.pop();
        if (!myStack.isEmpty()) error();
        System.out.println(val);
    } // MAIN
}