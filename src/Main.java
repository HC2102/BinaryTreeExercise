public class Main {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.insert(5);
        bt.insert(4);
        bt.insert(3);
        bt.insert(2);
        bt.insert(1);
        System.out.println("PosOrder");
        bt.posOrder(bt.root);
        System.out.println("Inorder");
        bt.inOrder(bt.root);
        System.out.println("PreOrder");
        bt.preOrder(bt.root);
//        System.out.println("clear a tree");
//        bt.clear();
//        System.out.println("isEmpty: "+bt.isEmpty());
        System.out.println("Count: "+bt.count(bt.root));
        System.out.println("Min: "+bt.min(bt.root));
        System.out.println("Max: "+bt.max(bt.root));
        System.out.println("Sum: "+bt.sum(bt.root));
        System.out.println("Avg: "+bt.avg(bt.root));
        System.out.println("isBalance: "+bt.isBalance(bt.root));

    }
}