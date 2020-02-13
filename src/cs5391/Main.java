package cs5391;

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

    public static void main(String...args) {
        System.out.println(">>> Simplified Arithmetic Post-fix Expression Evaluator <<<");
        System.out.println("Enter a simplified arithmetic post-fix expression:");

        String postfixExpression = getExpressionFromUser();  // receive user input
        System.out.println("Postfix expression is: " + postfixExpression);

        // A quick check to validate user input:
        // "...input consisting of non-negative numbers and the '+' (addition) and '/' (division) operators."
        if (postfixExpression.contains("-") || postfixExpression.contains("*")) {
            throw new IllegalArgumentException("Please only enter positive values and the + or / operators.");
        }

        String[] expressionComponents = postfixExpression.split("\\s");
        System.out.println("The tree representation: ");
        TreeNode tree = TreeNode.buildTree(expressionComponents);
        tree.dump("");

        System.out.println("The value: " + tree.evaluateTree(tree));
        System.out.println();

        TreeNode treeCopy = tree.clone(tree);
        System.out.println("The new tree: ");
        treeCopy.swapAndDouble();
        treeCopy.dump("");

        System.out.println("The value of the new tree: " + treeCopy.evaluateTree(treeCopy));
        System.out.println();
        System.out.println("The original tree representation: ");
        tree.dump("");
    }

    /**
     * Obtain user input
     * @return a string containing space separated values
     */
    private static String getExpressionFromUser() {
        Scanner myObj = new Scanner(System.in);
        String postfixExpression;

        postfixExpression = myObj.nextLine();
        return postfixExpression;
    }
}
