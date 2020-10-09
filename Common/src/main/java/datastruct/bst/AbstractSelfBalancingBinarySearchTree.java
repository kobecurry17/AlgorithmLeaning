package datastruct.bst;

@SuppressWarnings("all")
public class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

    /**
     * 左旋
     *
     * @param node
     * @return
     */
    protected Node rotateLeft(Node node) {
        Node temp = node.right;

        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
        }

        temp.left = node;
        node.parent = temp;

        if (null != temp.parent) {
            if (temp.parent.left == node) {
                temp.parent.left = temp;
            } else if (temp.parent.right == node) {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
        return temp;
    }


    protected Node rotateRight(Node node) {
        Node temp = node.left;
        temp.parent = node.parent;

        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }

        temp.right = node;
        node.parent = temp;

        // temp took over node's place so now its parent should point to temp
        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }

        return temp;
    }

}
