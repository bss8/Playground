package cs5391;

public class TreeNodeNum extends TreeNode {

//    protected int value;

    public TreeNodeNum(int i) {
        super(i);
    }

    @Override
    public TreeNodeNum clone() throws CloneNotSupportedException {
        TreeNodeNum clone = (TreeNodeNum) super.clone();
        return new TreeNodeNum(1);
    }
}
