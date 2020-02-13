package cs5391;

/* All tree nodes must implement this interface.  It provides basic
   machinery for constructing the parent and child relationships
   between nodes. */

public interface Node {
    /* This method tells the node to add its argument to the node's
       list of children.  */
    public void addChild(Node n, int i);

    /* This method returns a child node.  The children are numbered
       from zero, left to right. */
    public Node getChild(int i);

    /* Return the number of children the node has. */
    public int getNumChildren();

    // Added by Boris
    /* clone a TreeNode, with subclasses overriding with their own implementation */
    public TreeNode clone(TreeNode node);
    /* Helper function to swap + and / and also double the numeric value. Each class implements its own version */
    public void swapAndDouble();
}