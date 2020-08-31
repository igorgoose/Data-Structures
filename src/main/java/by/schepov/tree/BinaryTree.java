package by.schepov.tree;

public class BinaryTree {

    private Node root;
    private int size;

    public static class Node {

        private int key;
        private Node leftNode;
        private Node rightNode;
        private Node parentNode;

        private Node() {

        }

        private Node(int key) {
            this.key = key;
        }

        public int getKey() {
            return key;
        }

        private void setKey(int key) {
            this.key = key;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        private void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        private void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node getParentNode() {
            return parentNode;
        }

        private void setParentNode(Node parentNode) {
            this.parentNode = parentNode;
        }
    }

    public BinaryTree() {
    }

    public BinaryTree(int key) {
        root = new Node(key);
        size = 1;
    }

    public int getSize() {
        return size;
    }

    public void addKey(int key) {
        if(root == null){
            root = new Node(key);
            ++size;
            return;
        }
        Node current = root;
        while (current != null) {
            if (current.getKey() == key) {
                return;
            }
            if (current.getKey() < key) {
                if (current.getRightNode() == null) {
                    current.setRightNode(new Node(key));
                    current.getRightNode().setParentNode(current);
                    ++size;
                    return;
                }
                current = current.getRightNode();
                continue;
            }
            if (current.getLeftNode() == null) {
                current.setLeftNode(new Node(key));
                current.getLeftNode().setParentNode(current);
                ++size;
                return;
            }
            current = current.getLeftNode();
        }

    }

    public void removeKey(int key) {
        removeKey(root, key);
        --size;
    }

    private void removeKey(Node node, int key) {
        if (node == null) {
            return;
        }
        if (key < node.getKey()) {
            removeKey(node.getLeftNode(), key);
            return;
        }
        if (key > node.getKey()) {
            removeKey(node.getRightNode(), key);
            return;
        }
        if (size == 1) {
            root = null;
        }
        if (node.getRightNode() == null) {
            if (node.getParentNode().getLeftNode() == node) {
                node.getParentNode().setLeftNode(node.getLeftNode());
            } else {
                node.getParentNode().setRightNode(node.getLeftNode());
            }
            return;
        }
        if(node.getLeftNode() == null){
            if (node.getParentNode().getLeftNode() == node) {
                node.getParentNode().setLeftNode(node.getRightNode());
            } else {
                node.getParentNode().setRightNode(node.getRightNode());
            }
            return;
        }
        Node minNode = findMinNodeInSubtree(node);
        node.setKey(minNode.getKey());
        minNode.getParentNode().setLeftNode(null);
    }

    private Node findMinNodeInSubtree(Node node) {
        return node.getLeftNode() == null ? node : findMinNodeInSubtree(node.getLeftNode());
    }

    private Node getNodeFromSubtree(Node root, int key){
        if(root == null){
            return null;
        }
        if(root.getKey() == key){
            return root;
        }
        if(root.getKey() > key){
            return getNodeFromSubtree(root.getLeftNode(), key);
        }
        return getNodeFromSubtree(root.getRightNode(), key);
    }

    public Node getNode(int key){
        return getNodeFromSubtree(root, key);
    }



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        buildString(root, str);
        return str.toString().trim();
    }

    private void buildString(Node node, StringBuilder str){
        if(node == null){
            return;
        }
        str.append(node.getKey()).append(" ");
        buildString(node.getLeftNode(), str);
        buildString(node.getRightNode(), str);
    }

}
