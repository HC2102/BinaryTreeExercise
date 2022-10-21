import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    Node root;
    //check if empty
    public boolean isEmpty(){
        return root ==null;
    }
    //clear the tree
    public void clear(){
        root = null;
    }
    //insert node
    public void insert(int value){
        //new node
        Node newNode = new Node(value);
        //if tree is empty --> add to the root
        if(isEmpty()){
            root = newNode;
        }
        //else, traverse to the right position
        else{
            //focus to determine which side the new value will go
            Node focus = new Node();
            //pointer to traverse from root to leaf to reach the proper position
            Node pointer = root;
            while(pointer!=null){ //traverse until reach the dead end
                focus = pointer;
                if(focus.value == value) { // in case value is already exits in the tree --> discard it
                    System.out.println("Value already exits, discard");
                    return;
                }else{
                    //if new node value > focus, move right
                    if(pointer.value < value){
                        pointer = pointer.right;
                    }else{
                        //else move left
                        pointer = pointer.left;
                    }
                }
            }
            //insert node using focus (now pointer reach the null)
            if(focus.value < value){
                focus.right = newNode;
            }else{
                focus.left = newNode;
            }
        }
    }
    //search
    public Node search(int value){
        Node pointer = root;
        while(pointer != null){
            if(pointer.value == value){
                return pointer;
            }
            else{
                //move
                if(pointer.value < value){
                    pointer = pointer.right;
                }else{
                    pointer = pointer.left;
                }
            }
        }
        return null;
    }
    //breadth first search
    public void breadth(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            System.out.println(temp.value+" ");
            //add left child to the queue
            if(temp.left != null){
                queue.add(temp.left);
            }
            //add right child to the queue
            if(temp.right!=null){
                queue.add(temp.right);
            }
        }
    }

    //find height
    public int height(Node root){
        if(root == null) return 0; //if tree is empty then height is o
        else{
            int lheight = height(root.left); //left height
            int rheight = height(root.right); //right height
            if(lheight > rheight)
            {
                return lheight +1;
            }else{
                return rheight +1;
            }
        }
    }
    //print current level
    public void printCurrentLevel(Node root, int level){
        if(root == null) return;
        if(level==1) System.out.println(root.value+" ");
        else if(level>1){
            printCurrentLevel(root.left,level-1);
            printCurrentLevel(root.right, level-1);
        }
    }
    //depth search order
    public void inOrder(Node p){
        if(p == null ) return;
        inOrder(p.left);
        System.out.println(p.value);
        inOrder(p.right);
    }
    public void preOrder(Node p){
        if(p == null ) return;
        System.out.println(p.value);
        preOrder(p.left);
        preOrder(p.right);
    }
    public void posOrder(Node p){
        if(p == null ) return;
        posOrder(p.left);
        posOrder(p.right);
        System.out.println(p.value);
    }
    public int count(Node p){
        if(p == null){
            return 0;
        }
        //recursive call to left child and right child
        //add the result of these wth 1 (1 for counting the root)
        return 1+count(p.left)+count(p.right);
    }
    public int max(Node p){
        if( p ==null){
            return -1;
        }
        Node pointer = p;
        while(pointer.right != null){
            pointer = pointer.right;
        }
        return pointer.value;
    }
    public int min(Node p){
        if( p ==null){
            return -1;
        }
        Node pointer = p;
        while(pointer.left != null){
            pointer = pointer.left;
        }
        return pointer.value;
    }
    public Node getMinimumKey(Node curr){
        while(curr.left!= null){
            curr = curr.left;
        }
        return curr;
    }
    public Node deleteNode(Node root, int value){
        //pointer to store the parent of the current node
        Node parent = null;
        //start with the root node
        Node curr = root;
        //search key in the BST and set its parent pointer
        while(curr!=null && curr.value != value){
            //update the parent to the current node
            parent = curr;
            //if the given key is less than the current node, go to the left subtree
            if(value <curr.value){
                curr = curr.left;
            }
            //otherwise go to the right subtree
            else{
                curr = curr.right;
            }
        }
        //return if the key is not found in the tree
        if(curr == null){
            return root;
        }

        //case 1 node to be deleted has no children (leaf)
        if(curr.left ==null && curr.right == null){
            //if the node to delete is not a root node, then set its parent left, right child to null
            if(curr!= root){
                if(parent.left == curr){
                    parent.left = null;
                }
                else{
                    parent.right = null;
                }
            }
            else{
                root = null;
            }
        }
        //case 2: node to be deleted has two children
        else if (curr.left != null && curr.right != null){
            //find its inorder successor node
            Node successor = getMinimumKey(curr.right);
            int val = successor.value;
            //recursively delete the successor. Node that the successor will have at most one child
            deleteNode(root,successor.value);
            //copy value of the successor to the current node
            curr.value = val;
        }
        //case 3 node to be deleted has only one child
        else{
            //choose a child node
            Node child = (curr.left != null)? curr.left : curr.right;
            //if the node to be deleted is not a root node, set its parent to its child
            if(curr!= root){
                if(curr == parent.left) {
                    parent.left = child;
                }else{
                    parent.right = child;
                }
            }
            //if the node to be deleted is a root node, then set the root to the child
            else{
                root = child;
            }
        }
        return root;
    }
    //sum
    public int sum(Node root){
        if(root ==null){
            return 0;
        }return(root.value + sum(root.left)+sum(root.right));
    }
    public double avg(Node root){
        return((double)sum(root)/(double)count(root));
    }

    public boolean isBalance(Node node){
        int lh, rh;
        if(node == null){
            return true;
        }
        lh = height(node.left);
        /* Get the height of left and right subtrees */
        rh = height(node.right);
        if(Math.abs(lh-rh)<=1&& isBalance(node.left)&& isBalance(node.right)){
            return true;
        }
        /* If we reach here then tree is not height-balanced
         */
        return false;
    }
}
