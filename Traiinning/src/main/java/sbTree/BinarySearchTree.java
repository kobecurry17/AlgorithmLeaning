package sbTree;

public class BinarySearchTree extends BaseBinarySearchTree {



    public Node leftRotation(Node node) {
        if (null != node) {
            Node returnNode;
            if (null != node.left) {
                returnNode = node.left;
                node.left.parent = node.parent;
                node.left = returnNode.right;
                if(returnNode.right!=null){
                    returnNode.right.parent=node;
                }

            }


        }
        return node;
    }


}
