package cs5391;

/* This is a simple TreeNode that implements the Node interface. */

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeNode implements Node, Cloneable {
    public static final int NUM = 0;
    public static final int ADD = 1;
    public static final int MUL = 2;
    public static final int SUB = 3;
    public static final int DIV = 4;

    protected Node[] children;
    protected int id;

    public TreeNode(int i) {
        id = i;
    }

    /**
     * @param n - node to add as child
     * @param i - position (0 based)
     */
    public void addChild(Node n, int i) {
        if (children == null) {
            children = new Node[i + 1];  // initialize array
        } else if (i >= children.length) {
            Node c[] = new Node[i + 1];  // create new array of size one bigger
            System.arraycopy(children, 0, c, 0, children.length);  // copy old array into new
            children = c;  // set children to point to new array
        }
        children[i] = n;  // add child
    }

    public Node getChild(int i) {
        return children[i];
    }

    public int getNumChildren() {
        return (children == null) ? 0 : children.length;
    }

  /* You can override these two methods in subclasses of TreeNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do.

     Modified by Boris - TreeNodeNum holds integer value and will be of id == 0 always
     So, we modify toString here to display + or / according to the ID of TreeNode type object.
     */

    public String toString() {
        if (id == 1) {
            return "+";
        } else if (id == 4) {
            return "/";
        } else {
            return "Node: " + id;
        }
    }

    public String toString(String prefix) { return prefix + toString(); }

    /* Override this method if you want to customize how the node dumps
       out its children. */
    public void dump(String prefix) {
        System.out.println(toString(prefix));
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                TreeNode n = (TreeNode)children[i];
                if (n != null) {
                    n.dump(prefix + " ");
                }
            }
        }
    }

    /**
     * Clone a TreeNode type
     * @param node
     * @return
     */
    public TreeNode clone(TreeNode node) {
        TreeNode treeNode = new TreeNode(node.id);

        for (int i = 0; i < node.getNumChildren(); i++) {
            treeNode.addChild(node.getChild(i).clone((TreeNode) getChild(i)), i);
        }
        return treeNode;
    }

    /**
     * Builds a tree given a string array containing a postfix expression
     *
     * @param expression
     * @return
     */
    public static TreeNode buildTree(String[] expression) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (String element : expression) {
            // handle + operator, always a parent with two children
            if (element.equals("+")) {
                TreeNode addNode = new TreeNode(TreeNodeNum.ADD);
                addNode.addChild(stack.pop(), 1);  // right child first
                addNode.addChild(stack.pop(), 0);  // left child
                stack.push(addNode);
                // handle / operator, always a parent with two children
            } else if (element.equals("/")) {
                TreeNode divNode = new TreeNode(TreeNodeNum.DIV);
                divNode.addChild(stack.pop(), 1);  // right child first
                divNode.addChild(stack.pop(), 0);  // left child
                stack.push(divNode);
            } else {
                try {
                    int val = Integer.parseInt(element);
                    TreeNodeNum treeNodeNum = new TreeNodeNum(0, val);
                    stack.push(treeNodeNum);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Message:  " + e.getMessage());
                }
            }
        }
        return stack.pop();
    }

    /**
     * Evaluate the tree and return integer result
     * @param node
     * @return
     */
    public int evaluateTree(TreeNode node) {
        if ((node.getNumChildren() == 0)
                && (node instanceof TreeNodeNum)) {
            TreeNodeNum tn = (TreeNodeNum) node;
            return tn.value;
        }

        if (node.id == 1) {
            return evaluateTree((TreeNode) node.getChild(0)) + evaluateTree((TreeNode) node.getChild(1));
        } else {
            try {
                return evaluateTree((TreeNode) node.getChild(0)) / evaluateTree((TreeNode) node.getChild(1));
            } catch (ArithmeticException e) {
                throw new ArithmeticException("You attempted to divide by zero. Please try again.");
            }
        }
    }

    public void swapAndDouble() {
        if (id == 1) {
            id = 4;
        } else if (id == 4) {
            id = 1;
        }

        for (int i = 0; i < children.length; i++) {
            children[i].swapAndDouble();
        }
    }
}
