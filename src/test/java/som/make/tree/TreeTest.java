package som.make.tree;

import org.junit.jupiter.api.Test;

public class TreeTest {

    @Test
    public void test1() {
        AvlTree<Integer> avlTree = new AvlTree<>();
        avlTree.insert(7);
        avlTree.insert(5);
        avlTree.insert(12);
        avlTree.insert(3);
        avlTree.insert(16);
        avlTree.insert(14);
        avlTree.insert(11);
        System.out.println(avlTree.serializePrefix());
        avlTree.insert(15);
        System.out.println(avlTree.serializePrefix());
        avlTree.remove(14);
        System.out.println(avlTree.serializePrefix());
    }

}
