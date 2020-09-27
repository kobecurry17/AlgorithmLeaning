package sbTree;

/**
 * 搜索二叉树
 */
public class BaseBinarySearchTree {

    public Node root;

    protected int size;

    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    /**
     * 查找元素
     *
     * @param element
     * @return
     */
    protected Node search(int element) {
        Node node = root;
        while (node != null && node.value != null && node.value != element) {
            if (node.value < element) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node;
    }

    /**
     * 插入元素
     *
     * @param element
     * @return
     */
    public Node insert(int element) {
        if (null == root) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }
        Node parentNode = null;
        Node searchTempNode = root;
        while (searchTempNode != null) {
            parentNode = searchTempNode;
            if (searchTempNode.value < element) {
                searchTempNode = searchTempNode.right;
            } else {
                searchTempNode = searchTempNode.left;
            }
        }
        Node newNode = new Node(element, parentNode, null, null);
        if (element < parentNode.value) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * @param element
     * @return
     */
    public Node delete(int element) {
        Node deletedNode = search(element);
        if (null != deletedNode) {
            return delete(deletedNode);
        } else {
            return null;
        }
    }

    /**
     * 删除节点
     *
     * @param deleteNode
     * @return 替代删除结点的结点
     */
    protected Node delete(Node deleteNode) {
        if (deleteNode != null) {
            Node nodeToReturn = null;
            if (deleteNode != null) {
                if (deleteNode.left == null) {
                    // transplant(a,b)  b去替换a的环境，a断连掉，把b返回
                    nodeToReturn = transplant(deleteNode, deleteNode.right);
                } else if (deleteNode.right == null) {
                    nodeToReturn = transplant(deleteNode, deleteNode.left);
                } else {
                    Node successorNode = getMinimum(deleteNode.right);
                    if (successorNode.parent != deleteNode) {
                        transplant(successorNode, successorNode.right);
                        successorNode.right = deleteNode.right;
                        successorNode.right.parent = successorNode;
                    }
                    transplant(deleteNode, successorNode);
                    successorNode.left = deleteNode.left;
                    successorNode.left.parent = successorNode;
                    nodeToReturn = successorNode;
                }
                size--;
            }
            return nodeToReturn;
        }
        return null;
    }

    private Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node transplant(Node nodeToReplace, Node newNode) {
        if (null == nodeToReplace.parent) {
            this.root = newNode;
        } else if (nodeToReplace.parent.right == nodeToReplace) {
            nodeToReplace.parent.right = newNode;
        } else {
            nodeToReplace.parent.left = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;
        }
        return newNode;
    }


    /**
     * 二叉树节点
     */
    public static class Node {
        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

    }

    public int getSize() {
        return size;
    }
}
