package cs5391;

public class TreeNodeNum extends TreeNode {
    protected int value;

    public TreeNodeNum(int id) {
        super(id);
    }

    public TreeNodeNum(int id, int value) {
        super(id);
        this.setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public TreeNode clone() throws CloneNotSupportedException {
        TreeNodeNum clonedTree = (TreeNodeNum) super.clone();
        clonedTree = new TreeNodeNum(id, value);
        for (int i = 0; i < getNumChildren(); i++) {
            TreeNodeNum tmp = (TreeNodeNum) children[i];
            clonedTree.addChild(new TreeNodeNum(tmp.id), i);
        }
        return clonedTree;
    }
}
