package cs5391;

public class TreeNodeNum extends TreeNode {
    protected int value;

    public TreeNodeNum(int id, int value) {
        super(id);
        this.setValue(value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public String toString(String prefix) { return prefix + toString(); }

    /**
     * Clone a TreeNodeNum type
     * Deep copy - propagate throughout all children for their respective type.
     * @param node
     * @return
     */
    @Override
    public TreeNode clone(TreeNode node) {
        TreeNodeNum tmp = (TreeNodeNum) node;
        TreeNodeNum treeNodeNum = new TreeNodeNum(tmp.id, tmp.value);

       for (int i = 0; i < node.getNumChildren(); i++) {
           treeNodeNum.addChild(node.getChild(i).clone((TreeNode) getChild(i)), i);
       }
       return treeNodeNum;
    }

    /**
     * Evaluate the tree and return the integer result.
     * @param node
     * @return
     */
    @Override
    public int evaluateTree(TreeNode node) {
        if (node.getNumChildren() == 0) {
            return value;
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

    /**
     * For TreeNodeNum, we override swapAndDouble from TreeNode.
     * Here we double the value when the node is of type TreeNodeNum.
     */
    @Override
    public void swapAndDouble() {
        value = value * 2;
        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                children[i].swapAndDouble();
            }
        }
    }
}
