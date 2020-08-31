package by.schepov.tree;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


public class AVLBinaryTreeTest {

    @Test
    public void defaultConstructorTest(){
        assertEquals("", new AVLBinaryTree().toString());
    }

    @Test
    public void insertKeyTestRightSide(){
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(1);
        tree.addKey(2);
        tree.addKey(5);
        tree.addKey(4);
        tree.addKey(6);
        tree.addKey(3);
        assertEquals("4 2 1 3 5 6", tree.toString());
    }

    @Test
    public void insertKeyTestLeftSide(){
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(6);
        tree.addKey(5);
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(1);
        tree.addKey(4);
        assertEquals("3 2 1 5 4 6", tree.toString());
    }

    @Test
    public void removeKeyTestNoRightSubtree() {
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(6);
        tree.addKey(5);
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(1);
        tree.addKey(4);
        tree.removeKey(1);
        assertEquals("3 2 5 4 6", tree.toString());
    }

    @Test
    public void removeKeyTestWithRightSubtree() {
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(7);
        tree.addKey(6);
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(1);
        tree.addKey(4);
        tree.addKey(5);
        tree.removeKey(3);
        assertEquals("4 2 1 6 5 7", tree.toString());
    }

    @Test
    public void removeKeyTestOffBalance() {
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(4);
        tree.addKey(2);
        tree.addKey(6);
        tree.addKey(5);
        tree.addKey(7);
        tree.removeKey(2);
        assertEquals("6 4 5 7", tree.toString());
    }

    @Test
    public void findNodeTestTreeHasKey(){
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        assertEquals(0, tree.getNode(0).getKey());
    }

    @Test
    public void findNodeTestTreeHasntGotKey(){
        AVLBinaryTree tree = new AVLBinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        assertNull(tree.getNode(5));
    }

}
