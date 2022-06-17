package som.make.structure.tree;

/**
 * 平衡二叉搜索树
 * @param <T>
 */
public class AvlTree<T extends Comparable<? super T>> {

    protected static class AvlNode<T> {

        protected T element;
        protected AvlNode<T> left;
        protected AvlNode<T> right;
        protected int height;

        public AvlNode(T theElement) {
            this(theElement, null, null);
        }

        public AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
        }

    }

    public AvlNode<T> root;

    // TODO: make these optional based on some sort of 'debug' flag?
    // at the very least, make them read-only properties
    public int countInsertions;
    public int countSingleRotations;
    public int countDoubleRotations;

    public AvlTree() {
        root = null;
        countInsertions = 0;
        countSingleRotations = 0;
        countDoubleRotations = 0;
    }

    public int height (AvlNode<T> t){
        return t == null ? -1 : t.height;
    }

    public int max (int a, int b){
        return Math.max(a, b);
    }

    public boolean insert (T x) {
        try {
            root = insert(x, root);
            countInsertions++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected AvlNode<T> insert(T x, AvlNode<T> t) throws Exception {
        if (t == null) {
            t = new AvlNode<>(x);
        } else if (x.compareTo(t.element) < 0) { // 插入的数据比当前节点小，左1次
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) { // 插入的数据比当前节点的左节点小，左2次
                    t = rotateWithLeftChild (t);
                    countSingleRotations++;
                } else { // 插入的数据比当前节点的左节点大，左1次，右一次
                    t = doubleWithLeftChild (t);
                    countDoubleRotations++;
                }
            }
        } else if (x.compareTo(t.element) > 0) {
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) { // 插入的数据比当前节点的右节点大，右2次
                    t = rotateWithRightChild(t);
                    countSingleRotations++;
                } else { // 插入的数据比当前节点的右节点小，左1次，右一次
                    t = doubleWithRightChile(t);
                    countDoubleRotations++;
                }
            }
        } else {
            throw new Exception("Attempting to insert duplicate value");
        }
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 左左情况的处理
     * @param k2 需要进行左左处理的root节点
     * @return 处理过后的root节点
     */
    protected AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * 右右情况的处理
     * @param k1 需要进行右右处理的root节点
     * @return 处理过后的root节点
     */
    protected AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;

        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * 左右情况的处理
     * 1、这种情况先将root左节点的两个节点按照右右进行处理
     * 2、此时3个节点变为了左左情况，按照左左情况处理
     * @param k3 需要进行左右处理的root节点
     * @return 处理过后的root节点
     */
    protected AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 右左情况的处理
     * 1、这种情况先将root左节点的两个节点按照左左进行处理
     * 2、此时3个节点变为了右右情况，按照左左情况处理
     * @param k4 需要进行右左处理的root节点
     * @return 处理过后的root节点
     */
    protected AvlNode<T> doubleWithRightChile(AvlNode<T> k4) {
        k4.right = rotateWithLeftChild(k4.right);
        return rotateWithRightChild(k4);
    }

    /**
     * 迭代tree将名称打印到字符串中
     * @return 最后的字符串
     */
    public String serializePrefix() {
        StringBuilder str = new StringBuilder();
        serializePrefix(root, str, " ");
        return str.toString();
    }


    private void serializePrefix(AvlNode<T> t, StringBuilder str, String sep) {
        if (t != null) {
            str.append(t.element.toString());
            str.append(sep);
            serializePrefix(t.left, str, sep);
            serializePrefix(t.right, str, sep);
        }
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public T findMin() {
        if (isEmpty()) {
            return null;
        }
        return findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            return null;
        }
        return findMax(root).element;
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null) {
            return null;
        }
        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    private AvlNode<T> findMax(AvlNode<T> t) {
        if( t == null ) {
            return null;
        }
        while(t.right != null) {
            t = t.right;
        }
        return t;
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    public AvlNode<T> remove(T x, AvlNode<T> t) {
        if (t == null) return null;
        if (x.compareTo(t.element) < 0) {
            t.left = remove(x, t.left);
            int l = t.left != null ? t.left.height : 0;
            if ((t.right != null) && (t.right.height - l >= 2)) {
                int rightHeight = t.right.right != null ? t.right.right.height : -1;
                int leftHeight = t.right.left != null ? t.right.left.height : -1;
                if (rightHeight >= leftHeight) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithRightChile(t);
                }
            }
        } else if (x.compareTo(t.element) > 0) {
            t.right = remove(x, t.right);
            int r = t.right != null ? t.right.height : 0;
            if ((t.left != null) && (t.left.height -r >= 2)) {
                int leftHeight = t.left.left != null ? t.left.left.height : -1;
                int rightHeight = t.left.right != null ? t.left.right.height : -1;
                if (leftHeight >= rightHeight) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (t.left != null) {
            t.element = findMax(t.left).element;
            if (t.left.left == null && t.left.right == null) {
                t.left = null;
            } else {
                remove(t.element, t.left);
            }
            int lh = t.left == null ? -1 : t.left.height;
            if ((t.right != null) && (t.right.height - lh >= 2)) {
                int rightHeight = t.right.right != null ? t.right.right.height : -1;
                int leftHeight = t.right.left != null ? t.right.left.height: -1;

                if (rightHeight >= leftHeight) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithRightChile(t);
                }
            }
        } else {
            t = t.right;
        }
        if (t != null) {
            int leftHeight = t.left != null ? t.left.height : 0;
            int rightHeight = t.right != null ? t.right.height : 0;
            t.height = Math.max(leftHeight, rightHeight);
        }
        return t;
    }

}
