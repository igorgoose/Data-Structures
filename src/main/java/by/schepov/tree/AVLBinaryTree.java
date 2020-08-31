package by.schepov.tree;

public class AVLBinaryTree {

    private Node root;
    private int size = 0;

    public static class Node {

        private int key;
        private Node leftNode;
        private Node rightNode;
        private Node parentNode;
        private int height = 0;

        private Node() {

        }

        private Node(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node getParentNode() {
            return parentNode;
        }

        public void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public int getSize() {
        return size;
    }

    public Node getRoot() {
        return root;
    }


    private int getNodeHeight(Node node) {
        return node != null ? node.getHeight() : 0;
    }

    private int getBalanceFactor(Node node) {
        return getNodeHeight(node.getRightNode()) - getNodeHeight(node.getLeftNode());
    }

    private void fixHeight(Node node) {
        int leftHeight = node.getLeftNode() == null ? 0 : node.getLeftNode().getHeight();
        int rightHeight = node.getRightNode() == null ? 0 : node.getRightNode().getHeight();
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private Node rotateLeft(Node node) {
        Node right = node.getRightNode();
        Node parent = node.getParentNode();
        node.setRightNode(right.getLeftNode());
        if (right.getLeftNode() != null) {
            right.getLeftNode().setParentNode(node);
        }
        right.setLeftNode(node);
        node.setParentNode(right);
        right.setParentNode(parent);
        if (parent != null) {
            if (node == parent.getLeftNode()) {
                parent.setLeftNode(right);
            } else {
                parent.setRightNode(right);
            }
        }
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.getLeftNode();
        Node parent = node.getParentNode();
        node.setLeftNode(left.getRightNode());
        if (left.getRightNode() != null) {
            left.getRightNode().setParentNode(node);
        }
        left.setRightNode(node);
        node.setParentNode(left);
        left.setParentNode(parent);
        if (parent != null) {
            if (node == parent.getLeftNode()) {
                parent.setLeftNode(left);
            } else {
                parent.setRightNode(left);
            }
        }
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    private Node balanceNode(Node node) {
        fixHeight(node);
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor == 2) {
            if (getBalanceFactor(node.getRightNode()) < 0) {
                rotateRight(node.getRightNode());
            }
            return rotateLeft(node);
        }
        if (balanceFactor == -2) {
            if (getBalanceFactor(node.getLeftNode()) > 0) {
                rotateLeft(node.getLeftNode());
            }
            return rotateRight(node);
        }
        return node;
    }

    private Node addKeyInSubtree(Node root, int key) {
        if (root == null) {
            ++size;
            return new Node(key);
        }
        if (key < root.getKey()) {
            Node newLeft = addKeyInSubtree(root.getLeftNode(), key);
            root.setLeftNode(newLeft);
            newLeft.setParentNode(root);
        } else if (key > root.getKey()) {
            Node newRight = addKeyInSubtree(root.getRightNode(), key);
            root.setRightNode(newRight);
            newRight.setParentNode(root);
        }
        return balanceNode(root);
    }

    public void addKey(int key) {
        root = addKeyInSubtree(root, key);
    }

    private Node findMinInSubtree(Node root) {
        return root.getLeftNode() == null ? root : findMinInSubtree(root.getLeftNode());
    }

    private Node removeMinInSubtree(Node root) {
        if (root.getLeftNode() == null) {
            return root.getRightNode();
        }
        root.setLeftNode(removeMinInSubtree(root.getLeftNode()));
        root.getLeftNode().setParentNode(root);
        return balanceNode(root);
    }

    private Node removeKeyInSubtree(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.getKey()) {
            Node newLeft = removeKeyInSubtree(root.getLeftNode(), key);
            root.setLeftNode(newLeft);
            if (newLeft != null) {
                newLeft.setParentNode(root);
            }
        } else if (key > root.getKey()) {
            Node newRight = removeKeyInSubtree(root.getRightNode(), key);
            root.setRightNode(newRight);
            if (newRight != null) {
                newRight.setParentNode(root);
            }
        } else {
            --size;
            Node right = root.getRightNode();
            Node left = root.getLeftNode();
            if (right == null) {
                return left;
            }
            Node min = findMinInSubtree(right);
            min.setRightNode(removeMinInSubtree(right));
            min.getRightNode().setParentNode(min);
            min.setLeftNode(left);
            left.setParentNode(min);
            return balanceNode(min);
        }
        return balanceNode(root);
    }

    public void removeKey(int key) {
        root = removeKeyInSubtree(root, key);
    }

    private Node getNodeFromSubtree(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (root.getKey() == key) {
            return root;
        }
        if (root.getKey() > key) {
            return getNodeFromSubtree(root.getLeftNode(), key);
        }
        return getNodeFromSubtree(root.getRightNode(), key);
    }

    public Node getNode(int key) {
        return getNodeFromSubtree(root, key);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        buildString(root, str);
        return str.toString().trim();
    }

    private void buildString(Node node, StringBuilder str) {
        if (node == null) {
            return;
        }
        str.append(node.getKey()).append(" ");
        buildString(node.getLeftNode(), str);
        buildString(node.getRightNode(), str);
    }

}
