package datastruct.bst;

/**
 * AVL树：每个结点的左子树高度减右子树高度绝对值小等二
 */
public class AVLTree extends AbstractSelfBalancingBinarySearchTree {

    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        rebalance((AVLNode) newNode);
        return newNode;
    }

    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if (deleteNode != null) {
            Node successorNode = super.delete(deleteNode);
            if (successorNode != null) {
                // if replaced from getMinimum(deleteNode.right) then come back there and update
                // heights
                AVLNode minimum = successorNode.right != null ? (AVLNode) getMinimum(successorNode.right)
                        : (AVLNode) successorNode;
                recomputeHeight(minimum);
                rebalance((AVLNode) minimum);
            } else { // 并没有任何节点替代被删除节点的位置，被删除节点是孤零零被删除的
                recomputeHeight((AVLNode) deleteNode.parent);
                rebalance((AVLNode) deleteNode.parent);
            }
            return successorNode;
        }
        return null;
    }


    /**
     * Go up from inserted node, and update height and balance informations if
     * needed. If some node balance reaches 2 or -2 that means that subtree must be
     * rebalanced.
     *
     * @param node Inserted Node.
     */
    private void rebalance(AVLNode node) {
        while (node != null) {

            Node parent = node.parent;

            // 空树  -1
            // 叶节点  0
            int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
            int nodeBalance = rightHeight - leftHeight;
            // rebalance (-2 means left subtree outgrow, 2 means right subtree outgrow)
            if (nodeBalance == 2) {
                if (node.right.right != null && ((AVLNode) node.right.right).height == leftHeight + 1) {
                    node = (AVLNode) avlRotateLeft(node);
                } else {
                    node = (AVLNode) doubleRotateRightLeft(node);
                }
            } else if (nodeBalance == -2) {
                if (node.left.left != null && ((AVLNode) node.left.left).height == rightHeight + 1) {
                    node = (AVLNode) avlRotateRight(node);
                } else {
                    node = (AVLNode) doubleRotateLeftRight(node);
                }
            } else {
                updateHeight(node);
            }

            node = (AVLNode) parent;
        }
    }

    /**
     * Rotates to left side.
     */
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);

        updateHeight((AVLNode) temp.left);
        updateHeight((AVLNode) temp);
        return temp;
    }

    /**
     * Rotates to right side.
     */
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode) temp.right);
        updateHeight((AVLNode) temp);
        return temp;
    }

    /**
     * Take right child and rotate it to the right side first and then rotate node
     * to the left side.
     */
    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    /**
     * Take right child and rotate it to the right side first and then rotate node
     * to the left side.
     */
    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    /**
     * Recomputes height information from the node and up for all of parents. It
     * needs to be done after delete.
     */
    private void recomputeHeight(AVLNode node) {
        while (node != null) {
            node.height = maxHeight((AVLNode) node.left, (AVLNode) node.right) + 1;
            node = (AVLNode) node.parent;
        }
    }

    /**
     * Returns higher height of 2 nodes.
     */
    private int maxHeight(AVLNode node1, AVLNode node2) {
        if (node1 != null && node2 != null) {
            return node1.height > node2.height ? node1.height : node2.height;
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    /**
     * Updates height and balance of the node.
     *
     * @param node Node for which height and balance must be updated.
     */
    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value, parent, left, right);
    }

    protected static class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }
}
