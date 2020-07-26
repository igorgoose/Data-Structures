package by.schepov.tree;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;


public class BinaryTreeTest {

    @Test
    public void defaultConstructorTest(){
        assertEquals("", new BinaryTree().toString());
    }

    @Test
    public void constructorWithRootKeyTest(){
        assertEquals("1", new BinaryTree(1).toString());
    }

    @Test
    public void addNodeTest()
    {
        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(0);
        tree.addNode(-1);
        tree.addNode(1);
        assertEquals("2 0 -1 1 3", tree.toString());
    }

    @Test
    public void removeNodeWithTwoSubtreesTest(){
        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(0);
        tree.addNode(-1);
        tree.addNode(1);
        tree.removeNode(0);
        assertEquals("2 -1 1 3", tree.toString());
    }

    @Test
    public void removeNodeWithRightSubtreeTest(){
        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(0);
        tree.addNode(1);
        tree.removeNode(0);
        assertEquals("2 1 3", tree.toString());
    }

    @Test
    public void removeNodeWithLeftSubtreeTest(){
        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(0);
        tree.addNode(-1);
        tree.removeNode(0);
        assertEquals("2 -1 3", tree.toString());
    }

    @Test
    public void removeLeafTest(){
        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(0);
        tree.addNode(-1);
        tree.removeNode(-1);
        assertEquals("2 0 3", tree.toString());
    }

    @Test
    public void removeRoot(){
        BinaryTree tree = new BinaryTree();
        tree.addNode(2);
        tree.addNode(3);
        tree.addNode(0);
        tree.addNode(-1);
        tree.removeNode(-1);
        assertEquals("2 0 3", tree.toString());
    }

}