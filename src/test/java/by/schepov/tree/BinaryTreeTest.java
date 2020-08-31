package by.schepov.tree;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


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
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        tree.addKey(-1);
        tree.addKey(1);
        assertEquals("2 0 -1 1 3", tree.toString());
    }

    @Test
    public void removeNodeWithTwoSubtreesTest(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        tree.addKey(-1);
        tree.addKey(1);
        tree.removeKey(0);
        assertEquals("2 -1 1 3", tree.toString());
    }

    @Test
    public void removeNodeWithRightSubtreeTest(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        tree.addKey(1);
        tree.removeKey(0);
        assertEquals("2 1 3", tree.toString());
    }

    @Test
    public void removeNodeWithLeftSubtreeTest(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        tree.addKey(-1);
        tree.removeKey(0);
        assertEquals("2 -1 3", tree.toString());
    }

    @Test
    public void removeLeafTest(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        tree.addKey(-1);
        tree.removeKey(-1);
        assertEquals("2 0 3", tree.toString());
    }

    @Test
    public void removeRoot(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        tree.addKey(-1);
        tree.removeKey(-1);
        assertEquals("2 0 3", tree.toString());
    }

    @Test
    public void findNodeTestTreeHasKey(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        assertEquals(0, tree.getNode(0).getKey());
    }

    @Test
    public void findNodeTestTreeHasntGotKey(){
        BinaryTree tree = new BinaryTree();
        tree.addKey(2);
        tree.addKey(3);
        tree.addKey(0);
        assertNull(tree.getNode(5));
    }
}