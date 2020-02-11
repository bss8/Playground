package cs5391;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Write a main program (which should be a class) to do the following in the specified order:
 * Read in a simplified arithmetic expression and build its binary tree representation;
 * Print its tree representation;
 * Evaluate the tree;
 * Clone the tree;
 * Swap the '+' and '/' operators in the cloned tree and double the numbers to produce a new tree;
 * Print the new tree;
 * Evaluate the new tree;
 * Re-print the original tree; and
 * Re-evaluate the original tree.
 */
public class Main {
    private static final int DIV_CHAR = '/';
    private static final int PLUS_CHAR = '+';

    public static void main(String...args) {
        System.out.println(">>> Simplified Arithmetic Post-fix Expression Evaluator <<<");
        System.out.println("Enter a simplified arithmetic post-fix expression:");

        String postfixExpression = getExpressionFromUser();  // receive user input
        System.out.println("Postfix expression is: " + postfixExpression);

        String[] expressionComponents = postfixExpression.split("\\s");

        Deque<Integer> stack = new ArrayDeque<>();
        TreeNode parentNode;
        TreeNodeNum childNode;

        for (String expressionComponent : expressionComponents) {
            if (expressionComponent.equals("+")) {
                parentNode = new TreeNode(TreeNode.ADD);
                stack.pop();
            } else if (expressionComponent.equals("/")) {
                parentNode = new TreeNode(TreeNode.DIV);
            } else {
                try {
                    stack.push(Integer.parseInt(expressionComponent));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    private static String getExpressionFromUser() {
        Scanner myObj = new Scanner(System.in);
        String postfixExpression;

        postfixExpression = myObj.nextLine();
        return postfixExpression;
    }
}